package net.mehvahdjukaar.every_compat.modules.neoforge.xerca;

import com.google.gson.JsonObject;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import xerca.xercamod.common.block.BlockCarvedLog;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;


public class XercaModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> carved1;
    public final SimpleEntrySet<WoodType, Block> carved2;
    public final SimpleEntrySet<WoodType, Block> carved3;
    public final SimpleEntrySet<WoodType, Block> carved4;
    public final SimpleEntrySet<WoodType, Block> carved5;
    public final SimpleEntrySet<WoodType, Block> carved6;
    public final SimpleEntrySet<WoodType, Block> carved7;
    public final SimpleEntrySet<WoodType, Block> carved8;

    public XercaModule(String modId) {
        super(modId, "x");
        ResourceKey<CreativeModeTab> tab = CreativeModeTabs.BUILDING_BLOCKS;

        carved1 = SimpleEntrySet.builder(WoodType.class, "1", "carved",
                        getModBlock("carved_warped_1"), () -> WoodTypeRegistry.getValue(ResourceLocation.parse("warped")),
                        w -> new BlockCarvedLog(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addModelTransform(m -> m.replaceString("\"xercamod:block/carved_wood/carved_warped\"", "\"xercamod:block/carved_wood/carved_oak\""))
                .addTexture(modRes("block/carved_wood/carved_warped_1_top"))
                .addTexture(modRes("block/carved_wood/carved_warped_1_side_abcd"))
                .addRecipe(modRes("carving/carved_warped_1_from_warped_log_carving"))
                .addRecipe(modRes("carving/carved_warped_1_from_stripped_warped_log_carving"))
                .createPaletteFromPlanks(this::darkestPalette)
                .setTabKey(tab)
                .build();
        this.addEntry(carved1);

        carved2 = SimpleEntrySet.builder(WoodType.class, "2", "carved",
                        getModBlock("carved_warped_2"), () -> WoodTypeRegistry.getValue(ResourceLocation.parse("warped")),
                        w -> new BlockCarvedLog(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addModelTransform(m -> m.replaceString("\"xercamod:block/carved_wood/carved_warped\"", "\"xercamod:block/carved_wood/carved_oak\""))
                .addTexture(modRes("block/carved_wood/carved_warped_2_top"))
                .addTexture(modRes("block/carved_wood/carved_warped_2_side_abcd"))
                .addRecipe(modRes("carving/carved_warped_2_from_warped_log_carving"))
                .addRecipe(modRes("carving/carved_warped_2_from_stripped_warped_log_carving"))
                .createPaletteFromPlanks(this::darkestestPalette)
                .setTabKey(tab)
                .build();
        this.addEntry(carved2);

        carved3 = SimpleEntrySet.builder(WoodType.class, "3", "carved",
                        getModBlock("carved_warped_3"), () -> WoodTypeRegistry.getValue(ResourceLocation.parse("warped")),
                        w -> new BlockCarvedLog(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addModelTransform(m -> m.replaceString("\"xercamod:block/carved_wood/carved_warped\"", "\"xercamod:block/carved_wood/carved_oak\""))
                .addTexture(modRes("block/carved_wood/carved_warped_3_top"))
                .addTexture(modRes("block/carved_wood/carved_warped_3_side_abcd"))
                .addRecipe(modRes("carving/carved_warped_3_from_warped_log_carving"))
                .addRecipe(modRes("carving/carved_warped_3_from_stripped_warped_log_carving"))
                .createPaletteFromPlanks(this::darkestestPalette)
                .setTabKey(tab)
                .build();
        this.addEntry(carved3);

        carved4 = SimpleEntrySet.builder(WoodType.class, "4", "carved",
                        getModBlock("carved_warped_4"), () -> WoodTypeRegistry.getValue(ResourceLocation.parse("warped")),
                        w -> new BlockCarvedLog(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addModelTransform(m -> m.replaceString("\"xercamod:block/carved_wood/carved_warped\"", "\"xercamod:block/carved_wood/carved_oak\""))
                .addTexture(modRes("block/carved_wood/carved_warped_4_top"))
                .addTexture(modRes("block/carved_wood/carved_warped_4_side_abcd"))
                .addRecipe(modRes("carving/carved_warped_4_from_warped_log_carving"))
                .addRecipe(modRes("carving/carved_warped_4_from_stripped_warped_log_carving"))
                .createPaletteFromPlanks(this::darkestestPalette)
                .setTabKey(tab)
                .build();
        this.addEntry(carved4);

        carved5 = SimpleEntrySet.builder(WoodType.class, "5", "carved",
                        getModBlock("carved_warped_5"), () -> WoodTypeRegistry.getValue(ResourceLocation.parse("warped")),
                        w -> new BlockCarvedLog(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addModelTransform(m -> m.replaceString("\"xercamod:block/carved_wood/carved_warped\"", "\"xercamod:block/carved_wood/carved_oak\""))
                .addTexture(modRes("block/carved_wood/carved_warped_5_top"))
                .addTexture(modRes("block/carved_wood/carved_warped_5_side_ab"))
                .addTexture(modRes("block/carved_wood/carved_warped_5_side_cd"))
                .addRecipe(modRes("carving/carved_warped_5_from_warped_log_carving"))
                .addRecipe(modRes("carving/carved_warped_5_from_stripped_warped_log_carving"))
                .createPaletteFromPlanks(this::darkestestPalette)
                .setTabKey(tab)
                .build();
        this.addEntry(carved5);

        carved6 = SimpleEntrySet.builder(WoodType.class, "6", "carved",
                        getModBlock("carved_warped_6"), () -> WoodTypeRegistry.getValue(ResourceLocation.parse("warped")),
                        w -> new BlockCarvedLog(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addModelTransform(m -> m.replaceString("\"xercamod:block/carved_wood/carved_warped\"", "\"xercamod:block/carved_wood/carved_oak\""))
                .addTexture(modRes("block/carved_wood/carved_warped_6_top"))
                .addTexture(modRes("block/carved_wood/carved_warped_6_bottom"))
                .addTexture(modRes("block/carved_wood/carved_warped_6_side_a"))
                .addTexture(modRes("block/carved_wood/carved_warped_6_side_b"))
                .addTexture(modRes("block/carved_wood/carved_warped_6_side_c"))
                .addTexture(modRes("block/carved_wood/carved_warped_6_side_d"))
                .addRecipe(modRes("carving/carved_warped_6_from_warped_log_carving"))
                .addRecipe(modRes("carving/carved_warped_6_from_stripped_warped_log_carving"))
                .createPaletteFromPlanks(this::darkerPalette)
                .setTabKey(tab)
                .build();
        this.addEntry(carved6);

        carved7 = SimpleEntrySet.builder(WoodType.class, "7", "carved",
                        getModBlock("carved_warped_7"), () -> WoodTypeRegistry.getValue(ResourceLocation.parse("warped")),
                        w -> new BlockCarvedLog(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addModelTransform(m -> m.replaceString("\"xercamod:block/carved_wood/carved_warped\"", "\"xercamod:block/carved_wood/carved_oak\""))
                .addTexture(modRes("block/carved_wood/carved_warped_7_top"))
                .addTexture(modRes("block/carved_wood/carved_warped_7_side_a"))
                .addTexture(modRes("block/carved_wood/carved_warped_7_side_bcd"))
                .addRecipe(modRes("carving/carved_warped_7_from_warped_log_carving"))
                .addRecipe(modRes("carving/carved_warped_7_from_stripped_warped_log_carving"))
                .createPaletteFromPlanks(this::nuetralPalette)
                .setTabKey(tab)
                .build();
        this.addEntry(carved7);

        carved8 = SimpleEntrySet.builder(WoodType.class, "8", "carved",
                        getModBlock("carved_warped_8"), () -> WoodTypeRegistry.getValue(ResourceLocation.parse("warped")),
                        w -> new BlockCarvedLog(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addModelTransform(m -> m.replaceString("\"xercamod:block/carved_wood/carved_warped\"", "\"xercamod:block/carved_wood/carved_oak\""))
                .addTexture(modRes("block/carved_wood/carved_warped_8_top"))
                .addTexture(modRes("block/carved_wood/carved_warped_8_side_abcd"))
                .addRecipe(modRes("carving/carved_warped_8_from_warped_log_carving"))
                .addRecipe(modRes("carving/carved_warped_8_from_stripped_warped_log_carving"))
                .createPaletteFromPlanks(this::nuetralPalette)
                .setTabKey(tab)
                .build();
        this.addEntry(carved8);
    }

    private void nuetralPalette(Palette p) {
        p.add(p.increaseInner());
        p.remove(p.getDarkest());
        p.remove(p.getLightest());
    }

    private void darkerPalette(Palette p) {
        p.add(p.increaseInner());
        p.remove(p.getDarkest());
        p.remove(p.getLightest());
        p.remove(p.getLightest());
    }

    private void darkestPalette(Palette p) {
        p.add(p.increaseInner());
        p.remove(p.getDarkest());
        p.remove(p.getLightest());
        p.remove(p.getLightest());
        p.remove(p.getLightest());
    }

    private void darkestestPalette(Palette p) {
        p.add(p.increaseInner());
        p.remove(p.getDarkest());
        p.remove(p.getDarkest());
        p.remove(p.getLightest());
        p.remove(p.getLightest());
        p.remove(p.getLightest());
    }

    @Override
    // Recipes
    public void addDynamicServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicServerResources(handler, manager);

        carved1.items.forEach((wood, item) -> {
            if (Objects.nonNull(wood.getBlockOfThis("stripped_log")))
                recipeCreator(handler, wood.log.asItem(),
                        Objects.requireNonNull(wood.getBlockOfThis("stripped_log")).asItem(), 1, wood);

            recipeCreator(handler, wood.log.asItem(), item,1, wood);
            createRecipeIfNotNull("stripped_log", item, 1, wood, handler);

            recipeCreator(handler, wood.log.asItem(), carved2.items.get(wood), 2, wood);
            createRecipeIfNotNull("stripped_log", carved2.items.get(wood), 2, wood, handler);

            recipeCreator(handler, wood.log.asItem(), carved3.items.get(wood), 3, wood);
            createRecipeIfNotNull("stripped_log", carved3.items.get(wood), 3, wood, handler);

            recipeCreator(handler, wood.log.asItem(), carved4.items.get(wood), 4, wood);
            createRecipeIfNotNull("stripped_log", carved4.items.get(wood), 4, wood, handler);

            recipeCreator(handler, wood.log.asItem(), carved5.items.get(wood), 5, wood);
            createRecipeIfNotNull("stripped_log", carved5.items.get(wood), 5, wood, handler);

            recipeCreator(handler, wood.log.asItem(), carved6.items.get(wood), 6, wood);
            createRecipeIfNotNull("stripped_log", carved6.items.get(wood), 6, wood, handler);

            recipeCreator(handler, wood.log.asItem(), carved7.items.get(wood), 7, wood);
            createRecipeIfNotNull("stripped_log", carved7.items.get(wood), 7, wood, handler);

            recipeCreator(handler, wood.log.asItem(), carved8.items.get(wood), 8, wood);
            createRecipeIfNotNull("stripped_log", carved8.items.get(wood), 8, wood, handler);

        });

    }

    public void recipeCreator(ServerDynamicResourcesHandler handler, Item input, Item output, int num, WoodType wood) {
        // pathBuilder: carving/x/namespace/
        String pathBuilder = this.shortenedId() + "/" + wood.getNamespace() + "/";
        String recipeName = wood.getTypeName() + "_log_from_" + wood.getTypeName() + "_log_carving";

        if (output == Objects.requireNonNull(wood.getBlockOfThis("stripped_log")).asItem()) {
            recipeName += "stripped_" + recipeName;
        }
        else {
            recipeName = recipeName.replaceAll(".*", "");
            recipeName += "carved_" + wood.getTypeName() + "_" + num;

            // IF statement
            recipeName += (input == Objects.requireNonNull(wood.getBlockOfThis("stripped_log")).asItem())
                    ? "_from_stripped_" + wood.getTypeName() + "_log_carving"
                    : "_from_" + wood.getTypeName() + "_log_carving";
        }

        // Creating a new recipe
        JsonObject ingredient = new JsonObject();
        ingredient.addProperty("item", Utils.getID(input).toString());

        JsonObject json = new JsonObject();
        json.addProperty("type","xercamod:carving");
        json.add("ingredient", ingredient);
        json.addProperty("result", Utils.getID(output).toString());
        json.addProperty("count", 1);

        // Adding to the resources
        handler.dynamicPack.addJson(EveryCompat.res("carving/" + pathBuilder + recipeName), json, ResType.RECIPES);
    }

    // Null check for stripped_log
    public void createRecipeIfNotNull(String input, Item output, int num, WoodType wood, ServerDynamicResourcesHandler handler) {
        if (Objects.nonNull(wood.getItemOfThis(input))) {
            recipeCreator(handler, wood.getItemOfThis(input), output, num, wood);
        }
    }

    @Override
    // Model files
    public void addDynamicClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicClientResources(handler, manager);

        for (WoodType woodType : WoodTypeRegistry.getTypes()) {
            ResourceLocation modelLocation = modRes("models/block/carved_wood/carved_dark_oak.json"); // get model JSON

            if (woodType.isVanilla()) continue;


            try (InputStream modelStream = manager.getResource(modelLocation).orElseThrow().open()) {
                JsonObject model = RPUtils.deserializeJson(modelStream);

                // VARIABLES
                String filenameBuilder = "carved_" + woodType.getTypeName();
                JsonObject underTextures = model.getAsJsonObject("textures");
                String log_topPath;
                if (Objects.equals(woodType.getNamespace(), "tfc")) {
                    log_topPath = ":block/wood/log_top/" + woodType.getTypeName();
                }
                else {
                    log_topPath = ":block/" + woodType.getTypeName() + "_log_top";
                }

                // Editing
                underTextures.addProperty("up",  woodType.getNamespace() + log_topPath);
                underTextures.addProperty("down",  woodType.getNamespace() + log_topPath);
                underTextures.addProperty("particle",  woodType.getNamespace() + log_topPath);

                handler.dynamicPack.addJson(EveryCompat.res("block/carved_wood/" + filenameBuilder), model, ResType.MODELS);
            } catch (IOException e) {
                EveryCompat.LOGGER.error("Failed to get MODEL file @ {} : {}",modelLocation, e);
            }
        }
    }

}
