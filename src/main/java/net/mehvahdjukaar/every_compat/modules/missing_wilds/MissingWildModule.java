package net.mehvahdjukaar.every_compat.modules.missing_wilds;

import com.google.gson.JsonObject;
import me.ultrusmods.missingwilds.block.FallenLogBlock;
import me.ultrusmods.missingwilds.item.MissingWildsItemGroup;
import me.ultrusmods.missingwilds.register.MissingWildsBlocks;
import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.mehvahdjukaar.selene.block_set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.selene.resourcepack.RPUtils;
import net.mehvahdjukaar.selene.resourcepack.ResType;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.level.block.Block;

import java.io.IOException;
import java.io.InputStream;

public class MissingWildModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> FALLEN_LOGS;

    public MissingWildModule(String modId) {
        super(modId, "msw");

        FALLEN_LOGS = SimpleEntrySet.builder(WoodType.class, "log", "fallen",
                        MissingWildsBlocks.FALLEN_ACACIA_LOG, () -> WoodTypeRegistry.WOOD_TYPES.get(new ResourceLocation("acacia")),
                        w -> {
                            if (w.getNamespace().equals("byg") && w.getTypeName().equals("imparius")) return null;
                            return new FallenLogBlock(WoodGood.copySafe(w.planks).strength(2.0F).noOcclusion());
                        }
                )
                .addTag(modRes("fallen_logs"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("fallen_logs"), Registry.ITEM_REGISTRY)
                .setTab(() -> MissingWildsItemGroup.MISSING_WILDS)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();
        this.addEntry(FALLEN_LOGS);
    }

    @Override
    // Models
    public void addDynamicClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicClientResources(handler, manager);

        // correcting the model files for byg & enhanced_mushrooms with blockType: stem
        FALLEN_LOGS.blocks.forEach((woodType, block) -> {
            String namespace = woodType.getNamespace();
            String woodName = woodType.getTypeName();

            switch (namespace) {
                case "enhanced_mushrooms" -> correctingModel(woodType, handler, manager);
                case "byg" -> {
                    switch (woodName) {
                        case "bulbis" -> correctingModel(woodType, handler, manager);
//                        case "imparius" -> correctingModel("imparius", woodType, handler, manager);
                        case "sythian" -> correctingModel(woodType, handler, manager);
                    }
                }
            }
        });

    }

    //NOTE: only modify on woodType which are all "stem"
    private void correctingModel(WoodType woodType, ClientDynamicResourcesHandler handler, ResourceManager manager) {
        ResourceLocation resLoc = WoodGood.res(shortenedId() + "/" + woodType.getNamespace() + "/fallen_" + woodType.getTypeName() + "_log");

        try (InputStream stream = manager.getResource(ResType.BLOCK_MODELS.getPath(resLoc)).getInputStream()) {
            JsonObject model = RPUtils.deserializeJson(stream);

            // Editing the model
            model.getAsJsonObject().addProperty("parent", "missingwilds:block/template/fallen_log_template");

            model.getAsJsonObject("textures").addProperty("log_inner",
                    woodType.getNamespace() + ":block/stripped_" + woodType.getTypeName() + "_stem");

            handler.dynamicPack.addJson(resLoc, model, ResType.BLOCK_MODELS);

        } catch (IOException e) {
            //noinspection LoggingPlaceholderCountMatchesArgumentCount
            handler.getLogger().error("Failed to read the model file: {}", e);
        }
    }

}
