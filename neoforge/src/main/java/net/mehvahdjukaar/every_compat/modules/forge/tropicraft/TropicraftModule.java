package net.mehvahdjukaar.every_compat.modules.forge.tropicraft;

import com.google.gson.JsonObject;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.tropicraft.core.common.block.BoardwalkBlock;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

//SUPPORT: v9.6.3+
public class TropicraftModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> boardwalks;

    public TropicraftModule(String modId) {
        super(modId, "tc");

        boardwalks = SimpleEntrySet.builder(WoodType.class, "boardwalk",
                        getModBlock("mangrove_boardwalk"), () -> WoodTypeRegistry.getValue(new ResourceLocation("mangrove")),
                        w -> new BoardwalkBlock(BlockBehaviour.Properties.of().noOcclusion())
                )
                //TEXTURE: using planks
                //REASON: tropicraft has its own planks texture for mangrove, Below is use mod's planks texture
                .addModelTransform(m -> m.addModifier((s, resLoc, woodType) -> {
                    if (woodType.getNamespace().equals("tfc"))
                        return s.replace("\"tropicraft:block/mangrove_planks\"",
                                "\"tfc:block/wood/planks/" + woodType.getTypeName() + "\"");

                    return s.replace("\"tropicraft:block/mangrove_planks\"",
                            "\"" + woodType.getNamespace() + ":block/" + woodType.getTypeName() + "_planks\"");
                        }
                ))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTab(getModTab("tropicraft"))
                .build();
        this.addEntry(boardwalks);

    }

    @Override
    // RECIPES
    public void addDynamicServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicServerResources(handler, manager);

        ResourceLocation recipePath = modRes("mangrove_boardwalk");

        boardwalks.blocks.forEach((wood, block) -> {

            try (InputStream recipeStream = manager.getResource(ResType.RECIPES.getPath(recipePath))
                    .orElseThrow(() -> new FileNotFoundException("Failed to open the recipe @ " + recipePath)).open()) {

                JsonObject recipe = RPUtils.deserializeJson(recipeStream);

                JsonObject underKey = recipe.getAsJsonObject("key").getAsJsonObject("X");

                // Editing the JSON
                underKey.addProperty("item", Utils.getID(wood.getBlockOfThis("slab")).toString());
                recipe.getAsJsonObject("result").addProperty("item", Utils.getID(block).toString());

                // Adding to the resource
                String newPath = shortenedId() +"/"+ wood.getAppendableId() + "_boardwalk";

                handler.dynamicPack.addJson(EveryCompat.res(newPath), recipe, ResType.RECIPES);

            } catch (IOException e) {
                handler.getLogger().error("Failed to generate the boardwalk recipe");
            }
        });
    }
}