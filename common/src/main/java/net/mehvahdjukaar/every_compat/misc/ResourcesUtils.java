package net.mehvahdjukaar.every_compat.misc;

import com.google.common.base.Preconditions;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.configs.ModEntriesConfigs;
import net.mehvahdjukaar.moonlight.api.platform.ForgeHelper;
import net.mehvahdjukaar.moonlight.api.resources.BlockTypeResTransformer;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.StaticResource;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceSink;
import net.mehvahdjukaar.moonlight.api.resources.recipe.IRecipeTemplate;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesType;
import net.mehvahdjukaar.moonlight.api.set.leaves.VanillaLeavesTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("unused")
public class ResourcesUtils {

    @Deprecated(forRemoval = true)
    /// @deprecated USE {@link ResourcesUtils#generateStandardBlockFiles(ResourceManager, ResourceSink, Map, BlockType, BlockTypeResTransformer, BlockTypeResTransformer, ExtraModelConfiguration)}
    public static <B extends Block, T extends BlockType> void generateStandardBlockModels(
            ResourceManager manager, ResourceSink sink,
            Map<T, B> blocks, T baseType,
            BlockTypeResTransformer<T> modelTransformer,
            BlockTypeResTransformer<T> blockStateTransformer,
            ExtraModelConfiguration extraModelConfig
    ) {
        generateStandardBlockFiles(manager, sink, blocks, baseType, modelTransformer, blockStateTransformer, extraModelConfig);
    }

    @SuppressWarnings({"PointlessBooleanExpression", "OptionalGetWithoutIsPresent"})
    /// Generate Blockstate & models/block files
    public static <B extends Block, T extends BlockType> void generateStandardBlockFiles(
            ResourceManager manager, ResourceSink sink,
            Map<T, B> blocks, T baseType,
            BlockTypeResTransformer<T> modelTransformer,
            BlockTypeResTransformer<T> blockStateTransformer,
            ExtraModelConfiguration extraModelConfig
    ) {

        if (blocks.isEmpty()) return;

        TaskRunnerWithFailureCollection failures = TaskRunnerWithFailureCollection.active();

        //finds one entry to grab the baseType equivalent (oak, stone, iron or amethyst)
        var first = blocks.entrySet().stream().findFirst().get();
        Block baseBlock = BlockType.changeBlockType(first.getValue(), first.getKey(), baseType);

        if (baseBlock == null) {
            EveryCompat.LOGGER.error("Skipped generating some block assets because baseBlock is null for {}", Utils.getID(first.getValue()));
            return;
        }

        ResourceLocation baseId = Utils.getID(baseBlock);

        Set<String> modelsLoc = new HashSet<>();

        /// Blockstate & Models
        try {
            StaticResource oakBlockstate = StaticResource.getOrLog(manager, ResType.BLOCKSTATES.getPath(baseId));

            if (oakBlockstate == null) return;
            JsonElement insideBlockstates = RPUtils.deserializeJson(new ByteArrayInputStream(oakBlockstate.data));

            modelsLoc.addAll(RPUtils.findAllResourcesInJsonRecursive(insideBlockstates, s -> s.equals("model")));

            List<StaticResource> oakBlockModels = gatherNonVanillaModels(manager, modelsLoc, extraModelConfig);

            blocks.forEach((blockType, block) -> {
                ResourceLocation blockId = Utils.getID(block);
                failures.runSafely("blockstate", blockId::toString, () -> {
                    if (ModEntriesConfigs.isEntryEnabled(blockType, block) && true) { //generating all the times otherwise we get log spam
                        /// Creates blockstate
                        StaticResource newBlockState = blockStateTransformer.transform(oakBlockstate, blockId, blockType);
                        Preconditions.checkArgument(newBlockState.location != oakBlockstate.location,
                                "ids cant be the same: " + newBlockState.location);
                        //Adding to the resources
                        sink.addResourceIfNotPresent(manager, newBlockState);

                        /// Creates models/block
                        for (StaticResource model : oakBlockModels) {
                            failures.runSafely("block model", () -> blockId + " (" + model.location + ")", () -> {
                                // Modifying models' contents & path
                                StaticResource newModel = modelTransformer.transform(model, blockId, blockType);

                                Preconditions.checkArgument(newModel.location != model.location,
                                        "ids cant be the same: " + newModel.location);
                                //Adding to the resources
                                sink.addResourceIfNotPresent(manager, newModel);
                            });
                        }
                    } else {
                        //dummy blockstate so we don't generate models for this
                        sink.addJson(blockId, DUMMY_BLOCKSTATE, ResType.BLOCKSTATES);
                    }
                });
            });
        } catch (Exception e) {
            EveryCompat.LOGGER.error("Could not find blockstate definition for {}", baseId, e);
        }

    }

    private static List<StaticResource> gatherNonVanillaModels(ResourceManager manager, Set<String> modelsLoc, ExtraModelConfiguration modelConfig) {
        List<StaticResource> models = new ArrayList<>();

        for (var m : modelsLoc) {
            //remove the ones from mc namespace
            ResourceLocation modelRes = new ResourceLocation(m);
            if (!modelRes.getNamespace().equals("minecraft") || modelConfig.blockModel().contains(modelRes) || modelConfig.itemModel().contains(modelRes)) {
                StaticResource model = StaticResource.getOrLog(manager, ResType.MODELS.getPath(m));
                if (Objects.nonNull(model)) models.add(model);
            }
        }
        if (modelConfig.includeInGeneration()) {
            for (var currentModel : modelConfig.blockModel()) {
                StaticResource model = StaticResource.getOrLog(manager, ResType.MODELS.getPath(currentModel));
                if (Objects.nonNull(model)) models.add(model);
            }
        }
        return models;
    }


    //same as above just with just item models. a bunch of copy paste here... ugly
    @SuppressWarnings({"PointlessBooleanExpression", "OptionalGetWithoutIsPresent"})
    public static <I extends Item, T extends BlockType> void generateStandardItemModels(
            ResourceManager manager, ResourceSink sink,
            Map<T, I> items, T baseType, BlockTypeResTransformer<T> itemModelTransformer,
            ExtraModelConfiguration modelConfig
    ) {

        if (items.isEmpty()) return;

        TaskRunnerWithFailureCollection failures = TaskRunnerWithFailureCollection.active();

        //finds one entry. used so we can grab the oak equivalent
        var first = items.entrySet().stream().findFirst().get();
        Item oakItem = BlockType.changeItemType(first.getValue(), first.getKey(), baseType);

        if (oakItem == null) {
            EveryCompat.LOGGER.error("Skipped generating some item assets because oakItem is NULL for {}", Utils.getID(first.getValue()));
            return;
        }
        String baseItemName = baseType.getTypeName();

        Set<String> modelsLoc = new HashSet<>();

        /// Models/item
        try {
            //we cant use this since it might override parent too. Custom textured items need a custom model added manually with addBlockResources
            // modelModifier.replaceItemType(baseItemname);

            StaticResource oakItemModel = StaticResource.getOrLog(manager,
                    ResType.ITEM_MODELS.getPath(Utils.getID(oakItem)));

            if (oakItemModel == null) return;
            JsonObject json = RPUtils.deserializeJson(new ByteArrayInputStream(oakItemModel.data));
            //adds models/item references from here. not recursive
            modelsLoc.addAll(RPUtils.findAllResourcesInJsonRecursive(json, s -> s.equals("model") || s.equals("parent")));

            /// Modifying the parent inside models/item
            if (json.has("parent")) {
                String parent = json.get("parent").getAsString();
                if (parent.contains("item/generated")) {
                    itemModelTransformer.replaceItemType(baseItemName);
                }
            }

            items.forEach((blockType, item) -> {
                ResourceLocation id = Utils.getID(item);
                failures.runSafely("item model", id::toString, () -> {
                    StaticResource newRes = itemModelTransformer.transform(oakItemModel, id, blockType);
                    Preconditions.checkArgument(newRes.location != oakItemModel.location,
                            "ids cant be the same: " + newRes.location);
                    sink.addResourceIfNotPresent(manager, newRes);
                });
            });
        } catch (Exception e) {
            EveryCompat.LOGGER.error("Could not find item model for {}", oakItem, e);
        }


        //models
        List<StaticResource> oakItemModels = gatherNonVanillaModels(manager, modelsLoc, modelConfig);

        items.forEach((w, b) -> {
            ResourceLocation id = Utils.getID(b);
            if (ModEntriesConfigs.isEntryEnabled(w, b) && true) { //generating all the times otherwise we get log spam

                //creates item model
                for (StaticResource model : oakItemModels) {
                    failures.runSafely("item model", () -> id + " (" + model.location + ")", () -> {
                        StaticResource newModel = itemModelTransformer.transform(model, id, w);
                        assert newModel.location != model.location : "ids cant be the same";
                        sink.addResourceIfNotPresent(manager, newModel);
                    });
                }
            } else {
                //dummy blockstate so we don't generate models for this
                sink.addJson(id, DUMMY_BLOCKSTATE, ResType.ITEM_MODELS);
            }
        });
    }

    @NotNull
    @SuppressWarnings("UnusedReturnValue")
    public static <T extends BlockType> BlockTypeResTransformer<T> addBuiltinModelTransformer(
            BlockTypeResTransformer<T> transformer, T baseType) {
        String oldTypeName = baseType.getTypeName();

        // Modifying models' filename & ResourceLocation
        transformer.setIDModifier((text, id, w) ->
                BlockTypeResTransformer.replaceFullGenericType(text, w, id, oldTypeName, null, 2));

        // Modifying the model files' content
        if (baseType instanceof LeavesType leavesType) {
            CompatSpritesHelper.replaceLeavesTextures(transformer, leavesType);
            var woodT = leavesType.getAssociatedWoodType();
            if (woodT != null) {
                CompatSpritesHelper.replaceWoodTextures(transformer, woodT);
            }
        } else if (baseType instanceof WoodType woodType) {
            CompatSpritesHelper.replaceWoodTextures(transformer, woodType);
        }

        transformer.replaceGenericType(oldTypeName, "block");

        return transformer;
    }


    //creates and add new jsons based off the ones at the given resources with the provided modifiers
    public static <B extends Block, T extends BlockType> void addBlockResources(ResourceManager manager, ResourceSink sink,
                                                                                Map<T, B> blocks,
                                                                                BlockTypeResTransformer<T> modifier, ResourceLocation... jsonsLocations) {
        List<StaticResource> original = Arrays.stream(jsonsLocations).map(s -> StaticResource.getOrLog(manager, s)).toList();

        blocks.forEach((wood, value) -> {
            if (ModEntriesConfigs.isEntryEnabled(wood, value)) {
                ResourceLocation blockId = Utils.getID(value);
                for (var res : original) {
                    TaskRunnerWithFailureCollection.active().runSafely("block resource", () -> blockId + " (" + res.location + ")", () -> {
                        StaticResource newRes = modifier.transform(res, blockId, wood);

                        Preconditions.checkArgument(newRes.location != res.location,
                                "ids cant be the same: " + newRes.location);

                        sink.addResource(newRes);
                    });
                }
            }
        });
    }

    //creates and add new recipes based off the one at the given resource

    /**
     * Adds recipes based off an oak leaves based one
     */
    public static void addLeavesRecipes(String modId, ResourceManager manager, ResourceSink pack,
                                        Map<LeavesType, Item> blocks, String oakRecipe) {
        addBlocksRecipes(modId, manager, pack, blocks, oakRecipe, VanillaLeavesTypes.OAK);
    }

    /**
     * Adds recipes based off an oak planks based one
     */
    public static <B extends Item> void addWoodRecipes(String modId, ResourceManager manager, ResourceSink pack,
                                                       Map<WoodType, B> blocks, String oakRecipe) {
        addBlocksRecipes(modId, manager, pack, blocks, oakRecipe, VanillaWoodTypes.OAK);
    }

    /**
     * Adds recipes based off a given one
     */
    public static <B extends Item, T extends BlockType> void addBlocksRecipes(String modId, ResourceManager manager, ResourceSink pack,
                                                                              Map<T, B> blocks, String oakRecipe, T fromType) {
        addBlocksRecipes(manager, pack, blocks, new ResourceLocation(modId, oakRecipe), fromType, 0);
    }

    @SuppressWarnings("removal")
    public static <B extends Item, T extends BlockType> void addBlocksRecipes(ResourceManager manager, ResourceSink pack,
                                                                              Map<T, B> items, ResourceLocation baseRecipe, T fromType,
                                                                              int index) {
        IRecipeTemplate<?> template = RPUtils.readRecipeAsTemplate(manager,
                ResType.RECIPES.getPath(baseRecipe));

        items.forEach((blockType, i) -> {

            //check for disabled ones. //
            if (ModEntriesConfigs.isEntryEnabled(blockType, i)) {
                // Will actually crash if its null since vanilla recipe builder expects a non-null one
                try {
                    String blockId = RecipeBuilder.getDefaultRecipeId(i).toString();
                    FinishedRecipe newR;

                    String baseRecipePath = baseRecipe.getPath();
                    String modifiedRecipePath = baseRecipePath.substring(baseRecipePath.lastIndexOf("/") + 1).replace(fromType.getTypeName(), blockType.getTypeName());
                    String target = blockId.substring(blockId.lastIndexOf("/") + 1);
                    // Replaced the >text< with modifiedRecipe: everycomp:q/biomesoplenty/ >fir_vertical_slab<
                    String newId = blockId.replace(target, modifiedRecipePath);

                    // matches() ensure the last word, [a-z]_[a-z] is not one word, CASE: lightman's currency
                    if (!blockId.equals(newId) && newId.matches("\\w+:\\w+/\\w+/\\w+_\\w+")) {
                        newR = template.createSimilar(fromType, blockType, blockType.mainChild().asItem(), newId);
                    }
                    else {
                        newR = template.createSimilar(fromType, blockType, blockType.mainChild().asItem());
                    }
                    if (newR == null) return;

                    newR = ForgeHelper.addRecipeConditions(newR, template.getConditions()); //not even needed

                    // Adding to the resources
                    pack.addRecipe(newR);
                } catch (Exception e) {
                    EveryCompat.LOGGER.error("Failed to generate recipe @ {} for {}: {}", baseRecipe, i, e.getMessage());
                }
            }
        });
    }


    private static final JsonObject DUMMY_BLOCKSTATE;

    static {
        DUMMY_BLOCKSTATE = new JsonObject();
        DUMMY_BLOCKSTATE.addProperty("parent", "block/cube_all");
        JsonObject t = new JsonObject();
        t.addProperty("all", "everycomp:block/disabled");
        DUMMY_BLOCKSTATE.add("textures", t);
    }


    public static <T extends BlockType> Ingredient convertIngredient(Ingredient ingredient, T originalMat, T destinationMat) {
        Ingredient newIng = ingredient;
        for (var in : ingredient.getItems()) {
            Item it = in.getItem();
            if (it != Items.BARRIER) {
                ItemLike i = BlockType.changeItemType(it, originalMat, destinationMat);
                if (i != null) {
                    //converts first ingredient it finds
                    newIng = Ingredient.of(i);
                    break;
                }
            }
        }
        return newIng;
    }

    protected static final String RES_CHARS = "[a-z,A-Z,\\-,_./]*";
    protected static final Pattern RES_PATTERN = Pattern.compile("\"(" + RES_CHARS + ":" + RES_CHARS + ")\"");

    /*
     * NOTE:
     * if newItem is null, then m.group(0) will ensure that the value remain unchanged.
     * Utils.getId(NULL) is why it returned "minecraft:air" and the .orElseGet() doesn't work.
     *  CASE:
     * Quark's bookshelf and it's loot_table where it has "minecraft:booK" will be replaced with
     * "minecraft:air". A similar case with "minecraft:shulker_box"
    **/
    public static String convertItemIDinText(String text, BlockType fromType, BlockType toType) {
        Matcher matcher = RES_PATTERN.matcher(text);
        return matcher.replaceAll(m -> {
            var item = BuiltInRegistries.ITEM.getOptional(ResourceLocation.tryParse(m.group(1)));
            return item.map(value -> {
                Item newItem = BlockType.changeItemType(value, fromType, toType);

                if (newItem != null) return "\"" + Utils.getID(newItem).toString() + "\"";
                else return m.group(0);

            }).orElseGet(() -> m.group(0));
        });
    }

}
