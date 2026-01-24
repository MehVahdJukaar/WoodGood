package net.mehvahdjukaar.every_compat.modules.neoforge.tropicraft;

import com.google.gson.JsonObject;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.tropicraft.core.common.block.BoardwalkBlock;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.function.Consumer;

import static net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys.SLAB;

//SUPPORT: v9.6.3+
public class TropicraftModule extends EveryCompatModule {

    public final SimpleEntrySet<WoodType, Block> boardwalks;

    public TropicraftModule(String modId) {
        super(modId, "tc");

        boardwalks = SimpleEntrySet.builder(WoodType.class, "boardwalk",
                        getModBlock("mangrove_boardwalk"), () -> VanillaWoodTypes.MANGROVE,
                        w -> new BoardwalkBlock(BlockBehaviour.Properties.of().noOcclusion())
                )
                .requiresChildren(SLAB) //REASON: Recipes
                //TEXTURE: planks
                //REASON: tropicraft has its own planks texture for mangrove, Below is use mod's planks texture
                .addModelTransform(m -> m.replaceWithTextureFromChild("tropicraft:block/mangrove_planks",
                        "planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(modRes("tropicraft"))
                .build();
        this.addEntry(boardwalks);

    }

    @Override
    // RECIPES
    public void addDynamicServerResources(Consumer<ResourceGenTask> executor) {
        ResourceLocation recipePath = modRes("mangrove_boardwalk");
        executor.accept((manager, sink) -> {
            boardwalks.blocks.forEach((wood, block) -> {

                try (InputStream recipeStream = manager.getResource(ResType.RECIPES.getPath(recipePath))
                        .orElseThrow(() -> new FileNotFoundException("Failed to open the recipe @ " + recipePath)).open()) {

                    JsonObject recipe = RPUtils.deserializeJson(recipeStream);

                    JsonObject underKey = recipe.getAsJsonObject("key").getAsJsonObject("X");

                    // Editing the JSON`
                    underKey.addProperty("item", Utils.getID(Objects.requireNonNull(wood.getBlockOfThis(SLAB))).toString());
                    recipe.getAsJsonObject("result").addProperty("id", Utils.getID(block).toString());

                    // Adding to the resource
                    String newPath = shortenedId() + "/" + wood.getAppendableId() + "_boardwalk";

                    sink.addJson(EveryCompat.res(newPath), recipe, ResType.RECIPES);

                } catch (IOException e) {
                    EveryCompat.LOGGER.error("Failed to generate the boardwalk recipe for {}: {}", wood.getId(), e);
                }
            });

        });
    }
}