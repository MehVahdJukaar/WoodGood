package net.mehvahdjukaar.every_compat.modules.fabric.bewitchment;

import com.google.gson.JsonObject;
import moriyashiine.bewitchment.api.block.PoppetShelfBlock;
import moriyashiine.bewitchment.common.registry.BWBlockEntityTypes;
import moriyashiine.bewitchment.common.registry.BWObjects;
import moriyashiine.bewitchment.common.registry.BWTags;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.ItemOnlyEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.PushReaction;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystemNotFoundException;

//SUPPORT: v1.20-8
public class BewitchmentModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> poppet_shelf;
    public final ItemOnlyEntrySet<WoodType, Item> bark;

    public BewitchmentModule(String modId) {
        super(modId, "bw");

        poppet_shelf = SimpleEntrySet.builder(WoodType.class, "poppet_shelf",
                        getModBlock("oak_poppet_shelf"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new PoppetShelfBlock(FabricBlockSettings.copyOf(w.planks)
                                .pistonBehavior(PushReaction.BLOCK))
                )
                .requiresChildren("slab") // Recipes
                .addTile(() -> BWBlockEntityTypes.POPPET_SHELF)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .defaultRecipe()
                .build();
        this.addEntry(poppet_shelf);

        bark = ItemOnlyEntrySet.builder(WoodType.class, "bark",
                        () -> BWObjects.OAK_BARK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Item(new Item.Properties())
                )
                .requiresChildren("stripped_log") // Recipes
                .createPaletteFromChild(p -> {}, "log")
                .addTexture(modRes("item/oak_bark"))
                .addTag(BWTags.BARKS, Registries.ITEM)
                .build();
        this.addEntry(bark);

    }

    @Override
    // Recipes
    public void addDynamicServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicServerResources(handler, manager);

        String recipePath_1 = "athame_stripping/oak_bark_from_oak_log";
        String recipePath_2 = "athame_stripping/oak_bark_from_oak_wood";


        try (InputStream recipeStream_1 = manager.getResource(ResType.RECIPES.getPath(modRes(recipePath_1)))
                .orElseThrow(() -> new FileSystemNotFoundException(
                        "Failed to get recipe at location: " + recipePath_1)).open();
             InputStream recipeStream_2 = manager.getResource(ResType.RECIPES.getPath(modRes(recipePath_2)))
                     .orElseThrow(() -> new FileSystemNotFoundException(
                             "Failed to get recipe at location: " + recipePath_2)).open()
        ) {
            JsonObject recipe_1 = RPUtils.deserializeJson(recipeStream_1);
            JsonObject recipe_2 = RPUtils.deserializeJson(recipeStream_2);

            bark.items.forEach((wood, item) -> {
                // Replacing "oak" in the path
                String prefix = shortenedId() + "/" + wood.getNamespace() + "/";

                String newPath_1 = prefix + recipePath_1.replace("oak", wood.getTypeName());
                String newPath_2 = prefix + recipePath_2.replace("oak", wood.getTypeName());

                // Editing recipe_1
                recipe_1.addProperty("log",
                        Utils.getID(wood.log).toString());
                recipe_1.addProperty("stripped_log",
                        Utils.getID(wood.getBlockOfThis("stripped_log")).toString());
                recipe_1.getAsJsonObject("result").addProperty("item",
                        Utils.getID(item).toString());

                // Adding to Resources
                handler.dynamicPack.addJson(EveryCompat.res(newPath_1), recipe_1, ResType.RECIPES);

                // Null check for wood - some wood mods doesn't include <type>_wood
                if (wood.getBlockOfThis("wood") != null) {
                    // Editing recipe_2
                    recipe_2.addProperty("log",
                            Utils.getID(wood.getBlockOfThis("wood")).toString());
                    recipe_2.addProperty("stripped_log",
                            Utils.getID(wood.getBlockOfThis("stripped_wood")).toString());
                    recipe_2.getAsJsonObject("result").addProperty("item",
                            Utils.getID(item).toString());

                    // Adding to Resources
                    handler.dynamicPack.addJson(EveryCompat.res(newPath_2), recipe_2, ResType.RECIPES);
                }
            });
        } catch (IOException e) {
            handler.getLogger().error("Failed to open the recipe: ", e);
        }

    }
}