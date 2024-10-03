package net.mehvahdjukaar.every_compat.modules.fabric.wilder_wild;

import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.frozenblock.lib.axe.api.AxeBehaviors;
import net.frozenblock.wilderwild.block.HollowedLogBlock;
import net.frozenblock.wilderwild.entity.ai.TermiteManager;
import net.frozenblock.wilderwild.registry.RegisterBlocks;
import net.frozenblock.wilderwild.tag.WilderBlockTags;
import net.frozenblock.wilderwild.tag.WilderItemTags;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.SimpleTagBuilder;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

//SUPPORT: v2.4.6+
public class WilderWildModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, HollowedLogBlock> hollow_log;
    public final SimpleEntrySet<WoodType, HollowedLogBlock> stripped_hollow_log;

    public WilderWildModule(String modId) {
        super(modId, "ww");
        ResourceKey<CreativeModeTab> tab = CreativeModeTabs.BUILDING_BLOCKS;

        hollow_log = SimpleEntrySet.builder(WoodType.class, "log", "hollowed",
                        () -> RegisterBlocks.HOLLOWED_OAK_LOG, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new HollowedLogBlock(Utils.copyPropertySafe(RegisterBlocks.HOLLOWED_OAK_LOG))
                )
                .requiresChildren("stripped_log") // Textures
                .createPaletteFromChild(pallete -> {}, "log")
                .addTexture(modRes("block/hollowed_oak_log"))
                .addTexture(modRes("block/hollowed_oak_log_top"))
                // TEXTURE: using stripped_hollowed_oak_log from below
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .addTag(modRes("hollowed_logs"), Registries.ITEM)
                .addTag(modRes("hollowed_logs_that_burn"), Registries.ITEM)
                .addTag(ItemTags.LOGS_THAT_BURN, Registries.ITEM)
                .addTag(ItemTags.LOGS, Registries.ITEM)
                .addTag(ItemTags.COMPLETES_FIND_TREE_TUTORIAL, Registries.ITEM)
                //TAG: wilderwild:hollowed_<type>_logs
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.LAVA_POOL_STONE_CANNOT_REPLACE, Registries.BLOCK)
                .addTag(BlockTags.LOGS_THAT_BURN, Registries.BLOCK)
                .addTag(BlockTags.LOGS, Registries.BLOCK)
                .addTag(BlockTags.COMPLETES_FIND_TREE_TUTORIAL, Registries.BLOCK)
                .addTag(BlockTags.PARROTS_SPAWNABLE_ON, Registries.BLOCK)
                .addTag(modRes("hollowed_logs"), Registries.BLOCK)
                .addTag(modRes("splits_coconut"), Registries.BLOCK)
                .addRecipe(modRes("oak_wood_from_hollowed"))
                .build();
        this.addEntry(hollow_log);

        stripped_hollow_log = SimpleEntrySet.builder(WoodType.class, "log", "stripped_hollowed",
                        () -> RegisterBlocks.STRIPPED_HOLLOWED_OAK_LOG, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new HollowedLogBlock(Utils.copyPropertySafe(RegisterBlocks.STRIPPED_HOLLOWED_OAK_LOG))
                )
                .requiresChildren("stripped_log") // Textures
                .createPaletteFromChild(pallete -> {}, "stripped_log")
                .addTexture(modRes("block/stripped_hollowed_oak_log"))
                .addTexture(modRes("block/stripped_hollowed_oak_log_top"))
                .addTexture(modRes("block/stripped_hollowed_oak_log"))
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.LAVA_POOL_STONE_CANNOT_REPLACE, Registries.BLOCK)
                .addTag(BlockTags.LOGS_THAT_BURN, Registries.BLOCK)
                .addTag(BlockTags.LOGS, Registries.BLOCK)
                .addTag(BlockTags.COMPLETES_FIND_TREE_TUTORIAL, Registries.BLOCK)
                .addTag(BlockTags.PARROTS_SPAWNABLE_ON, Registries.BLOCK)
                .addTag(WilderBlockTags.HOLLOWED_LOGS, Registries.BLOCK)
                .addTag(WilderBlockTags.STRIPPED_HOLLOWED_LOGS, Registries.BLOCK)
                .addTag(WilderBlockTags.SPLITS_COCONUT, Registries.BLOCK)
                //TAG: wilderwild:hollowed_<type>_logs
                .addTag(WilderItemTags.HOLLOWED_LOGS, Registries.ITEM)
                .addTag(WilderItemTags.HOLLOWED_LOGS_THAT_BURN, Registries.ITEM)
                .addTag(ItemTags.LOGS_THAT_BURN, Registries.ITEM)
                .addTag(ItemTags.LOGS, Registries.ITEM)
                .addTag(ItemTags.COMPLETES_FIND_TREE_TUTORIAL, Registries.ITEM)
                .addRecipe(modRes("stripped_oak_wood_from_hollowed"))
                .build();
        this.addEntry(stripped_hollow_log);

    }

    @Override
    public void onModSetup() {
        super.onModSetup();

        hollow_log.blocks.forEach((wood, block) -> {
            StrippableBlockRegistry.register(block, stripped_hollow_log.blocks.get(wood));

            boolean isStem = Utils.getID(wood.log).toString().contains("stem");

            AxeBehaviors.register(wood.log, (context, level, pos, state, face, horizontal) ->
                    HollowedLogBlock.hollow(level, pos, state, face, hollow_log.blocks.get(wood), isStem));

            AxeBehaviors.register(wood.getBlockOfThis("stripped_log"), (context, level, pos, state, face, horizontal) ->
                    HollowedLogBlock.hollow(level, pos, state, face, stripped_hollow_log.blocks.get(wood), isStem));

            if (!isStem) {
                TermiteManager.Termite.addDegradable(block, stripped_hollow_log.blocks.get(wood));
            }

        });
    }

    @Override
    // Recipes & Tags
    public void addDynamicServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicServerResources(handler, manager);

        hollow_log.blocks.forEach((wood, block) -> {
                // Variables
            ResourceLocation recipeLoc = ResType.RECIPES.getPath( "wilderwild:oak_planks_from_hollowed");
            ResourceLocation tagRLoc = EveryCompat.res(shortenedId() + "/" + wood.getNamespace() + "/" + "hollowed_" +
                    wood.getTypeName() + "_logs");
            ResourceLocation newRecipeLoc = EveryCompat.res(wood.getTypeName() + "_planks_from_hollowed");
            boolean isTagFull = false;

                // TAGS ================================================================================================
            SimpleTagBuilder tagBuilder = SimpleTagBuilder.of(tagRLoc);

            // Adding to tag's list
            if (block != null) {
                tagBuilder.addEntry(block.asItem());
                tagBuilder.addEntry(stripped_hollow_log.blocks.get(wood).asItem());
                isTagFull = true;
            }

            // Adding to the resources
            if (isTagFull) {
                handler.dynamicPack.addTag(tagBuilder, Registries.BLOCK);
                handler.dynamicPack.addTag(tagBuilder, Registries.ITEM);
            }

            // RECIPE ==============================================================================================
            try (InputStream recipeStream = manager.getResource(recipeLoc)
                    .orElseThrow(() -> new FileNotFoundException("ResourceLocation: " + recipeLoc)).open()) {
                JsonObject recipe = RPUtils.deserializeJson(recipeStream);

                // Editing the recipe
                recipe.getAsJsonArray("ingredients").get(0).getAsJsonObject()
                        .addProperty("tag", tagRLoc.toString());

                recipe.getAsJsonObject("result")
                        .addProperty("item", Utils.getID(wood.planks).toString());

                // Adding to the resources
                if (isTagFull) {
                    handler.dynamicPack.addJson(newRecipeLoc, recipe, ResType.RECIPES);
                    EveryCompat.LOGGER.warn("RECIPES: PASSED - {}", wood.getTypeName());
                }

            } catch (IOException e) {
                handler.getLogger().error("Failed to open the recipe file: ", e);
            }
        });

    }
}