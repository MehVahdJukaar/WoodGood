package net.mehvahdjukaar.every_compat.misc;

import com.google.common.base.Preconditions;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.common_classes.CompatChestBlock;
import net.mehvahdjukaar.every_compat.common_classes.CompatTrappedChestBlock;
import net.mehvahdjukaar.every_compat.configs.ModEntriesConfigs;
import net.mehvahdjukaar.moonlight.api.resources.BlockTypeResTransformer;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.StaticResource;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceSink;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesType;
import net.mehvahdjukaar.moonlight.api.set.leaves.VanillaLeavesTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.ByteArrayInputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("unused")
public class ResourcesUtils {


    @SuppressWarnings("PointlessBooleanExpression")
    public static <B extends Block, T extends BlockType> void generateStandardBlockFiles(
            ResourceManager manager, ResourceSink sink,
            Map<T, B> blocks, T baseType,
            BlockTypeResTransformer<T> modelTransformer,
            BlockTypeResTransformer<T> blockStateTransformer,
            ExtraModelConfiguration modelConfig
    ) {

        if (blocks.isEmpty()) return;

        TaskRunnerWithFailureCollection failures = TaskRunnerWithFailureCollection.active();

        //finds one entry to grab the baseType equivalent (oak, stone, iron or amethyst)
        var first = blocks.entrySet().stream().findFirst().get();
        Block baseBlock = BlockType.changeBlockType(first.getValue(), first.getKey(), baseType);

        if (baseBlock == null) {
            EveryCompat.LOGGER.error("Skipped generating some block assets because oakBlock is null for {}", Utils.getID(first.getValue()));
            return;
        }

        Identifier baseId = Utils.getID(baseBlock);

        Set<String> modelsLoc = new HashSet<>();

        /// Blockstate and Models
        try {
            StaticResource oakBlockstate = StaticResource.getOrLog(manager, ResType.BLOCKSTATES.getPath(baseId));

            if (oakBlockstate == null) return;
            JsonElement insideBlockstates = RPUtils.deserializeJson(new ByteArrayInputStream(oakBlockstate.data));

            modelsLoc.addAll(RPUtils.findAllResourcesInJsonRecursive(insideBlockstates, s -> s.equals("model")));

            List<StaticResource> oakBlockModels = gatherNonVanillaModels(manager, modelsLoc, modelConfig);

            blocks.forEach((blockType, block) -> {
                Identifier blockId = Utils.getID(block);
                failures.runSafely("blockstate", blockId::toString, () -> {
                    if (true || ModEntriesConfigs.isEntryEnabled(blockType, block)) { //generating all the times otherwise we get log spam
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
            Identifier modelRes = Identifier.parse(m);
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
    @SuppressWarnings("PointlessBooleanExpression")
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

        //oak chest might not even have a models/item json
        items.forEach((blockType, item) -> {
            if (item instanceof BlockItem bi && bi.getBlock() instanceof CompatChestBlock chest) {
                Identifier id = Utils.getID(item);
                failures.runSafely("chest item definition", id::toString,
                        () -> addChestItemDefinition(manager, sink, id, blockType, chest));
            }
        });

        /// Models/item
        try {
            //we cant use this since it might override parent too. Custom textured items need a custom model added manually with addBlockResources
            // modelModifier.replaceItemType(baseItemname);

            Identifier oakItemId = Utils.getID(oakItem);
            if (manager.getResource(ResType.ITEM_MODELS.getPath(oakItemId)).isEmpty()) {
                copyItemDefinitions(manager, sink, items, oakItemId, itemModelTransformer);
                return;
            }
            StaticResource oakItemModel = StaticResource.getOrLog(manager, ResType.ITEM_MODELS.getPath(oakItemId));

            if (oakItemModel == null) return;
            JsonArray oakTints = getItemDefinitionTints(manager, Utils.getID(oakItem));
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
                Identifier id = Utils.getID(item);
                failures.runSafely("item model", id::toString, () -> {
                    StaticResource newRes = itemModelTransformer.transform(oakItemModel, id, blockType);
                    Preconditions.checkArgument(newRes.location != oakItemModel.location,
                            "ids cant be the same: " + newRes.location);
                    sink.addResourceIfNotPresent(manager, newRes);
                    if (!isCompatChest(item)) addItemDefinition(manager, sink, id, newRes.location, oakTints);
                });
            });
        } catch (Exception e) {
            EveryCompat.LOGGER.error("Could not find item model for {}", oakItem, e);
        }


        //models
        List<StaticResource> oakItemModels = gatherNonVanillaModels(manager, modelsLoc, modelConfig);

        items.forEach((w, b) -> {
            Identifier id = Utils.getID(b);
            if (true || ModEntriesConfigs.isEntryEnabled(w, b)) { //generating all the times otherwise we get log spam

                //creates item model
                for (StaticResource model : oakItemModels) {
                    failures.runSafely("item model", () -> id + " (" + model.location + ")", () -> {
                        StaticResource newModel = itemModelTransformer.transform(model, id, w);
                        assert newModel.location != model.location : "ids cant be the same";
                        sink.addResourceIfNotPresent(manager, newModel);
                    });
                }
            }
        });
    }

    //items/ definition pointing at the generated models/item json. tints come from the oak one
    private static void addItemDefinition(ResourceManager manager, ResourceSink sink, Identifier itemId,
                                          Identifier modelLocation, @Nullable JsonArray tints) {
        Identifier modelId = modelLocation.withPath(p -> p.substring("models/".length(), p.length() - ".json".length()));
        sink.addJsonUnlessPresent(manager, ResType.ITEMS.getPath(itemId), () -> {
            JsonObject model = new JsonObject();
            model.addProperty("type", "minecraft:model");
            model.addProperty("model", modelId.toString());
            if (tints != null) model.add("tints", tints);
            JsonObject definition = new JsonObject();
            definition.add("model", model);
            return definition;
        });
    }

    //no models/item, so the items/ json points straight at block modesl (or a special renderer). copy it over
    private static <I extends Item, T extends BlockType> void copyItemDefinitions(ResourceManager manager, ResourceSink sink, Map<T, I> items,
                                                                                  Identifier oakItemId, BlockTypeResTransformer<T> transformer) {
        StaticResource oakDefinition = StaticResource.getOrLog(manager, ResType.ITEMS.getPath(oakItemId));
        if (oakDefinition == null) return;

        items.forEach((blockType, item) -> {
            if (isCompatChest(item)) return;
            Identifier id = Utils.getID(item);
            TaskRunnerWithFailureCollection.active().runSafely("item definition", id::toString, () -> {
                StaticResource newDefinition = transformer.transform(oakDefinition, id, blockType);
                sink.addJsonUnlessPresent(manager, ResType.ITEMS.getPath(id),
                        () -> RPUtils.deserializeJson(new ByteArrayInputStream(newDefinition.data)));
            });
        });
    }

    //same texture CompatChestBlockRenderer uses
    private static void addChestItemDefinition(ResourceManager manager, ResourceSink sink, Identifier itemId,
                                               BlockType type, CompatChestBlock chest) {
        String shortenedId = itemId.getPath().substring(0, itemId.getPath().indexOf('/'));
        String suffix = chest instanceof CompatTrappedChestBlock ? "_trapped_chest" : "_chest";
        Identifier texture = EveryCompat.res(shortenedId + "/" + type.getAppendableId() + suffix);

        sink.addJsonUnlessPresent(manager, ResType.ITEMS.getPath(itemId), () -> {
            JsonObject definition = new JsonObject();
            definition.add("model", chestSpecialModel(texture));
            return definition;
        });
    }

    private static boolean isCompatChest(Item item) {
        return item instanceof BlockItem bi && bi.getBlock() instanceof CompatChestBlock;
    }

    private static JsonObject chestSpecialModel(Identifier texture) {
        JsonObject chest = new JsonObject();
        chest.addProperty("type", "minecraft:chest");
        chest.addProperty("texture", texture.toString());
        JsonObject model = new JsonObject();
        model.addProperty("type", "minecraft:special");
        model.addProperty("base", "minecraft:item/chest");
        model.add("model", chest);
        return model;
    }

    @Nullable
    private static JsonArray getItemDefinitionTints(ResourceManager manager, Identifier itemId) {
        var resource = manager.getResource(ResType.ITEMS.getPath(itemId));
        if (resource.isEmpty()) return null;
        try (var stream = resource.get().open()) {
            JsonObject model = RPUtils.deserializeJson(stream).getAsJsonObject("model");
            return model != null && model.has("tints") ? model.getAsJsonArray("tints") : null;
        } catch (Exception e) {
            return null;
        }
    }

    @NotNull
    @SuppressWarnings("UnusedReturnValue")
    public static <T extends BlockType> BlockTypeResTransformer<T> addBuiltinModelTransformer(
            BlockTypeResTransformer<T> transformer, T baseType) {
        String oldTypeName = baseType.getTypeName();

        // Modifying models' filename & Identifier
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
                                                                                BlockTypeResTransformer<T> modifier, Identifier... jsonsLocations) {
        List<StaticResource> original = Arrays.stream(jsonsLocations).map(s -> StaticResource.getOrLog(manager, s)).toList();

        blocks.forEach((wood, value) -> {
            if (ModEntriesConfigs.isEntryEnabled(wood, value)) {
                Identifier blockId = Utils.getID(value);
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
                                                       Map<WoodType, B> blocks, String baseRecipe) {
        addBlocksRecipes(modId, manager, pack, blocks, baseRecipe, VanillaWoodTypes.OAK);
    }

    /**
     * Adds recipes based off a given one
     */
    public static <B extends Item, T extends BlockType> void addBlocksRecipes(String modId, ResourceManager manager, ResourceSink pack,
                                                                              Map<T, B> blocks, String baseRecipe, T fromType) {
        addBlocksRecipes(manager, pack, blocks, Identifier.fromNamespaceAndPath(modId, baseRecipe), fromType, 0);
    }

    public static <B extends Item, T extends BlockType> void addBlocksRecipes(ResourceManager manager, ResourceSink sink,
                                                                              Map<T, B> items, Identifier baseRecipe, T fromType,
                                                                              int index) {
        Recipe<?> template = RPUtils.readRecipe(manager, baseRecipe);
        items.forEach((blockType, i) -> {

            if (ModEntriesConfigs.isEntryEnabled(blockType, i)) {
                Identifier itemId = Utils.getID(i);
                TaskRunnerWithFailureCollection.active().runSafely("recipe", () -> itemId + " (" + baseRecipe + ")", () -> {
                    //check for disabled ones. Will actually crash if its null since vanilla recipe builder expects a non-null one
                    Identifier blockId = itemId;
                    String baseRecipePath = baseRecipe.getPath();

                    String modifiedRecipePath = baseRecipePath.substring(baseRecipePath.lastIndexOf("/") + 1).replace(fromType.getTypeName(), blockType.getTypeName());
                    String target = blockId.toString().substring(blockId.toString().lastIndexOf("/") + 1);

                    // Replaced the [???] with modifiedRecipe: everycomp:q/biomesoplenty/ [fir_vertical_slab]
                    String newId = blockId.toString().replace(target, modifiedRecipePath);

                    // matches() ensure the last word, [a-z]_[a-z] is not one word, CASE: lightman's currency
                    if (!blockId.toString().equals(newId) && newId.matches("\\w+:\\w+/\\w+/\\w+_\\w+")) {
                        sink.addBlockTypeSwapRecipe(manager, baseRecipe, fromType, blockType, Identifier.parse(newId));
                    }
                    else {
                        sink.addBlockTypeSwapRecipe(manager, baseRecipe, fromType, blockType, blockId);
                    }
                });
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
        for (var in : ingredient.items().toList()) {
            Item it = in.value();
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
            var item = BuiltInRegistries.ITEM.getOptional(Identifier.tryParse(m.group(1)));
            return item.map(value -> {
                Item newItem = BlockType.changeItemType(value, fromType, toType);

                if (newItem != null) return "\"" + Utils.getID(newItem).toString() + "\"";
                else return m.group(0);

            }).orElseGet(() -> m.group(0));
        });
    }

}
