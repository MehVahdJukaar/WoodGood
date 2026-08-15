package net.mehvahdjukaar.every_compat.modules.neoforge.xerca;

import com.google.gson.JsonObject;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.PaletteStrategies;
import net.mehvahdjukaar.every_compat.api.PaletteStrategy;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.misc.HardcodedBlockType;
import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceSink;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import xerca.xercamod.common.block.BlockCarvedLog;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.function.Consumer;

import static net.mehvahdjukaar.every_compat.api.PaletteStrategies.registerCached;

//SUPPORT: NOT-AVAILABLE
public class XercaModule extends EveryCompatModule {

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
                        getModBlock("carved_warped_1"), () -> VanillaWoodTypes.WARPED,
                        w -> new BlockCarvedLog(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addModelTransform(m -> m.replaceString("\"xercamod:block/carved_wood/carved_warped\"", "\"xercamod:block/carved_wood/carved_oak\""))
                .addTexture(modRes("block/carved_wood/carved_warped_1_top"), DARKER_PALETTE)
                .addTexture(modRes("block/carved_wood/carved_warped_1_side_abcd"), DARKER_PALETTE)
                .addRecipe(modRes("carving/carved_warped_1_from_warped_log_carving"))
                .addRecipe(modRes("carving/carved_warped_1_from_stripped_warped_log_carving"))
                .setTab(getTab(tab))
                .build();
        this.addEntry(carved1);

        carved2 = SimpleEntrySet.builder(WoodType.class, "2", "carved",
                        getModBlock("carved_warped_2"), () -> VanillaWoodTypes.WARPED,
                        w -> new BlockCarvedLog(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addModelTransform(m -> m.replaceString("\"xercamod:block/carved_wood/carved_warped\"", "\"xercamod:block/carved_wood/carved_oak\""))
                .addTexture(modRes("block/carved_wood/carved_warped_2_top"), DARKEST_PALETTE)
                .addTexture(modRes("block/carved_wood/carved_warped_2_side_abcd"), DARKEST_PALETTE)
                .addRecipe(modRes("carving/carved_warped_2_from_warped_log_carving"))
                .addRecipe(modRes("carving/carved_warped_2_from_stripped_warped_log_carving"))
                .setTab(getTab(tab))
                .build();
        this.addEntry(carved2);

        carved3 = SimpleEntrySet.builder(WoodType.class, "3", "carved",
                        getModBlock("carved_warped_3"), () -> VanillaWoodTypes.WARPED,
                        w -> new BlockCarvedLog(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addModelTransform(m -> m.replaceString("\"xercamod:block/carved_wood/carved_warped\"", "\"xercamod:block/carved_wood/carved_oak\""))
                .addTexture(modRes("block/carved_wood/carved_warped_3_top"), DARKEST_PALETTE)
                .addTexture(modRes("block/carved_wood/carved_warped_3_side_abcd"), DARKEST_PALETTE)
                .addRecipe(modRes("carving/carved_warped_3_from_warped_log_carving"))
                .addRecipe(modRes("carving/carved_warped_3_from_stripped_warped_log_carving"))
                .setTab(getTab(tab))
                .build();
        this.addEntry(carved3);

        carved4 = SimpleEntrySet.builder(WoodType.class, "4", "carved",
                        getModBlock("carved_warped_4"), () -> VanillaWoodTypes.WARPED,
                        w -> new BlockCarvedLog(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addModelTransform(m -> m.replaceString("\"xercamod:block/carved_wood/carved_warped\"", "\"xercamod:block/carved_wood/carved_oak\""))
                .addTexture(modRes("block/carved_wood/carved_warped_4_top"), DARKEST_PALETTE)
                .addTexture(modRes("block/carved_wood/carved_warped_4_side_abcd"), DARKEST_PALETTE)
                .addRecipe(modRes("carving/carved_warped_4_from_warped_log_carving"))
                .addRecipe(modRes("carving/carved_warped_4_from_stripped_warped_log_carving"))
                .setTab(getTab(tab))
                .build();
        this.addEntry(carved4);

        carved5 = SimpleEntrySet.builder(WoodType.class, "5", "carved",
                        getModBlock("carved_warped_5"), () -> VanillaWoodTypes.WARPED,
                        w -> new BlockCarvedLog(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addModelTransform(m -> m.replaceString("\"xercamod:block/carved_wood/carved_warped\"", "\"xercamod:block/carved_wood/carved_oak\""))
                .addTexture(modRes("block/carved_wood/carved_warped_5_top"), DARKEST_PALETTE)
                .addTexture(modRes("block/carved_wood/carved_warped_5_side_ab"), DARKEST_PALETTE)
                .addTexture(modRes("block/carved_wood/carved_warped_5_side_cd"), DARKEST_PALETTE)
                .addRecipe(modRes("carving/carved_warped_5_from_warped_log_carving"))
                .addRecipe(modRes("carving/carved_warped_5_from_stripped_warped_log_carving"))
                .setTab(getTab(tab))
                .build();
        this.addEntry(carved5);

        carved6 = SimpleEntrySet.builder(WoodType.class, "6", "carved",
                        getModBlock("carved_warped_6"), () -> VanillaWoodTypes.WARPED,
                        w -> new BlockCarvedLog(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addModelTransform(m -> m.replaceString("\"xercamod:block/carved_wood/carved_warped\"", "\"xercamod:block/carved_wood/carved_oak\""))
                .addTexture(modRes("block/carved_wood/carved_warped_6_top"), DARK_PALETTE)
                .addTexture(modRes("block/carved_wood/carved_warped_6_bottom"), DARK_PALETTE)
                .addTexture(modRes("block/carved_wood/carved_warped_6_side_a"), DARK_PALETTE)
                .addTexture(modRes("block/carved_wood/carved_warped_6_side_b"), DARK_PALETTE)
                .addTexture(modRes("block/carved_wood/carved_warped_6_side_c"), DARK_PALETTE)
                .addTexture(modRes("block/carved_wood/carved_warped_6_side_d"), DARK_PALETTE)
                .addRecipe(modRes("carving/carved_warped_6_from_warped_log_carving"))
                .addRecipe(modRes("carving/carved_warped_6_from_stripped_warped_log_carving"))
                .setTab(getTab(tab))
                .build();
        this.addEntry(carved6);

        carved7 = SimpleEntrySet.builder(WoodType.class, "7", "carved",
                        getModBlock("carved_warped_7"), () -> VanillaWoodTypes.WARPED,
                        w -> new BlockCarvedLog(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addModelTransform(m -> m.replaceString("\"xercamod:block/carved_wood/carved_warped\"", "\"xercamod:block/carved_wood/carved_oak\""))
                .addTexture(modRes("block/carved_wood/carved_warped_7_top"), NEUTRAL_PALETTE)
                .addTexture(modRes("block/carved_wood/carved_warped_7_side_a"), NEUTRAL_PALETTE)
                .addTexture(modRes("block/carved_wood/carved_warped_7_side_bcd"), NEUTRAL_PALETTE)
                .addRecipe(modRes("carving/carved_warped_7_from_warped_log_carving"))
                .addRecipe(modRes("carving/carved_warped_7_from_stripped_warped_log_carving"))
                .setTab(getTab(tab))
                .build();
        this.addEntry(carved7);

        carved8 = SimpleEntrySet.builder(WoodType.class, "8", "carved",
                        getModBlock("carved_warped_8"), () -> VanillaWoodTypes.WARPED,
                        w -> new BlockCarvedLog(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addModelTransform(m -> m.replaceString("\"xercamod:block/carved_wood/carved_warped\"", "\"xercamod:block/carved_wood/carved_oak\""))
                .addTexture(modRes("block/carved_wood/carved_warped_8_top"), NEUTRAL_PALETTE)
                .addTexture(modRes("block/carved_wood/carved_warped_8_side_abcd"), NEUTRAL_PALETTE)
                .addRecipe(modRes("carving/carved_warped_8_from_warped_log_carving"))
                .addRecipe(modRes("carving/carved_warped_8_from_stripped_warped_log_carving"))
                .setTab(getTab(tab))
                .build();
        this.addEntry(carved8);
    }

    public static final PaletteStrategy NEUTRAL_PALETTE = registerCached((blockType, manager) -> PaletteStrategies.makePaletteFromChild(
            blockType, manager, VanillaWoodChildKeys.PLANKS, null, p -> {
                p.add(p.increaseInner());
                p.reduceDown();
                p.reduceUp();
            })
    );

    public static final PaletteStrategy DARK_PALETTE = registerCached((blockType, manager) -> PaletteStrategies.makePaletteFromChild(
            blockType, manager, VanillaWoodChildKeys.PLANKS, null, p -> {
                p.add(p.increaseInner());
                p.reduceDown();
                p.reduceUp();
                p.reduceUp();
            })
    );

    public static final PaletteStrategy DARKER_PALETTE = registerCached((blockType, manager) -> PaletteStrategies.makePaletteFromChild(
            blockType, manager, VanillaWoodChildKeys.PLANKS, null, p -> {
                p.add(p.increaseInner());
                p.reduceDown();
                p.reduceUp();
                p.reduceUp();
                p.reduceUp();
            })
    );

    public static final PaletteStrategy DARKEST_PALETTE = registerCached((blockType, manager) -> PaletteStrategies.makePaletteFromChild(
            blockType, manager, VanillaWoodChildKeys.PLANKS, null, p -> {
                p.add(p.increaseInner());
                p.reduceDown();
                p.reduceDown();
                p.reduceUp();
                p.reduceUp();
                p.reduceUp();
            })
    );

    @Override
    // RECIPES
    public void addDynamicServerResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicServerResources(executor);

        executor.accept((manager, sink) -> {

            carved1.items.forEach((wood, item) -> {
                Block strippedLog = wood.getBlockOfThis("stripped_log");

                if (Objects.nonNull(strippedLog))
                    recipeCreator(wood.log.asItem(), Objects.requireNonNull(strippedLog).asItem(), 1, wood, sink);

                recipeCreator(wood.log.asItem(), item,1, wood, sink);
                createRecipeIfNotNull("stripped_log", item, 1, wood, sink);

                recipeCreator(wood.log.asItem(), carved2.items.get(wood), 2, wood, sink);
                createRecipeIfNotNull("stripped_log", carved2.items.get(wood), 2, wood, sink);

                recipeCreator(wood.log.asItem(), carved3.items.get(wood), 3, wood, sink);
                createRecipeIfNotNull("stripped_log", carved3.items.get(wood), 3, wood, sink);

                recipeCreator(wood.log.asItem(), carved4.items.get(wood), 4, wood, sink);
                createRecipeIfNotNull("stripped_log", carved4.items.get(wood), 4, wood, sink);

                recipeCreator(wood.log.asItem(), carved5.items.get(wood), 5, wood, sink);
                createRecipeIfNotNull("stripped_log", carved5.items.get(wood), 5, wood, sink);

                recipeCreator(wood.log.asItem(), carved6.items.get(wood), 6, wood, sink);
                createRecipeIfNotNull("stripped_log", carved6.items.get(wood), 6, wood, sink);

                recipeCreator(wood.log.asItem(), carved7.items.get(wood), 7, wood, sink);
                createRecipeIfNotNull("stripped_log", carved7.items.get(wood), 7, wood, sink);

                recipeCreator(wood.log.asItem(), carved8.items.get(wood), 8, wood, sink);
                createRecipeIfNotNull("stripped_log", carved8.items.get(wood), 8, wood, sink);

            });

        });
    }

    public void recipeCreator(Item input, Item output, int num, WoodType wood, ResourceSink sink) {
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
        sink.addJson(EveryCompat.res(pathBuilder + recipeName), json, ResType.RECIPES);
    }

    // Null check for stripped_log
    public void createRecipeIfNotNull(String input, Item output, int num, WoodType wood, ResourceSink sink) {
        if (Objects.nonNull(wood.getItemOfThis(input))) {
            recipeCreator(wood.getItemOfThis(input), output, num, wood, sink);
        }
    }

    @Override
    // MODELS
    public void addDynamicClientResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicClientResources(executor);
        executor.accept((manager, sink) -> {
            for (WoodType woodType : WoodTypeRegistry.INSTANCE) {
                ResourceLocation modelLocation = modRes("models/block/carved_wood/carved_dark_oak.json"); // get model JSON

                if (HardcodedBlockType.isKnownVanillaWood(woodType)) continue;


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

                    sink.addJson(EveryCompat.res("block/carved_wood/" + filenameBuilder), model, ResType.MODELS);
                } catch (IOException e) {
                    EveryCompat.LOGGER.error("Failed to get MODEL file @ {} : {}",modelLocation, e);
                }
            }
        });
    }

}
