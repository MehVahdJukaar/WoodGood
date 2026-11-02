package net.mehvahdjukaar.every_compat.misc;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.configs.WoodConfigs;
import net.mehvahdjukaar.moonlight.api.platform.ForgeHelper;
import net.mehvahdjukaar.moonlight.api.resources.BlockTypeResTransformer;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.StaticResource;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynClientResourcesGenerator;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynamicDataPack;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynamicResourcePack;
import net.mehvahdjukaar.moonlight.api.resources.recipe.IRecipeTemplate;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesType;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesTypeRegistry;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
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
import org.jetbrains.annotations.Nullable;

import java.io.ByteArrayInputStream;
import java.util.*;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResourcesUtils {

    public static <B extends Block, T extends BlockType> void addStandardResources(
            String modId, ResourceManager manager, DynClientResourcesGenerator pack, Map<T, B> blocks, T baseType) {
        addStandardResources(modId, manager, pack, blocks, baseType, null);
    }

    public static <B extends Block, T extends BlockType> void addStandardResources(
            String modId, ResourceManager manager, DynClientResourcesGenerator pack,
            Map<T, B> blocks, T baseType, @Nullable Consumer<BlockTypeResTransformer<T>> extraTransform) {

        if (blocks.isEmpty()) return;

        //finds one entry. used so we can grab the oak equivalent
        var first = blocks.entrySet().stream().findFirst().get();
        Block oakBlock = BlockType.changeBlockType(first.getValue(), first.getKey(), baseType);

        String baseBlockName = baseType.getTypeName();

        if (oakBlock == null) {
            EveryCompat.LOGGER.error("Skipped generating some block assets because baseBlock is null for {}", Utils.getID(first.getValue()));
            return;
        }
        ResourceLocation oakId = Utils.getID(oakBlock);

        BlockTypeResTransformer<T> modifier = BlockTypeResTransformer.create(modId, manager);
        modifier.IDReplaceType(baseBlockName).replaceBlockType(baseBlockName);
        if (extraTransform != null) extraTransform.accept(modifier); //idk about this

        BlockTypeResTransformer<T> modelModifier = standardModelTransformer(modId, manager, baseType, baseBlockName, extraTransform);

        Set<String> modelsLoc = new HashSet<>();

        Item oakItem = oakBlock.asItem();


        //if it has an item
        if (oakItem != Items.AIR) {
            ///models/item
            try {
                //we cant use this since it might override partent too. Custom textured items need a custom model added manually with addBlockResources
                // modelModifier.replaceItemType(baseBlockName);

                BlockTypeResTransformer<T> itemModifier = standardModelTransformer(modId, manager, baseType, baseBlockName, extraTransform);

                StaticResource oakItemModel = StaticResource.getOrFail(manager,
                        ResType.ITEM_MODELS.getPath(Utils.getID(oakItem)));

                JsonObject json = RPUtils.deserializeJson(new ByteArrayInputStream(oakItemModel.data));
                //adds models referenced from here. not recursive
                modelsLoc.addAll(RPUtils.findAllResourcesInJsonRecursive(json, s -> s.equals("model") || s.equals("parent")));

                if (json.has("parent")) {
                    String parent = json.get("parent").getAsString();
                    if (parent.contains("item/generated")) {
                        itemModifier.replaceItemType(baseBlockName);
                    }
                }

                blocks.forEach((w, b) -> {
                    ResourceLocation id = Utils.getID(b);
                    try {
                        StaticResource newRes = itemModifier.transform(oakItemModel, id, w);
                        assert newRes.location != oakItemModel.location : "ids cant be the same";
                        pack.addResourceIfNotPresent(manager, newRes);
                    } catch (Exception e) {
                        EveryCompat.LOGGER.error("Failed to add {} models/item json file:", b, e);
                    }
                });
            } catch (Exception e) {
                EveryCompat.LOGGER.error("Could not find block's models/item file for {}", oakBlock);
            }
        }

        ///blockstate
        try {
            StaticResource oakBlockstate = StaticResource.getOrFail(manager, ResType.BLOCKSTATES.getPath(oakId));
            //models
            JsonElement json = RPUtils.deserializeJson(new ByteArrayInputStream(oakBlockstate.data));

            modelsLoc.addAll(RPUtils.findAllResourcesInJsonRecursive(json, s -> s.equals("model")));
            List<StaticResource> oakModels = new ArrayList<>();

            for (var m : modelsLoc) {
                //remove the ones from mc namespace
                ResourceLocation modelRes = new ResourceLocation(m);
                if (!modelRes.getNamespace().equals("minecraft")) {
                    StaticResource model = StaticResource.getOrLog(manager, ResType.MODELS.getPath(m));
                    if (model != null) oakModels.add(model);
                }
            }

            blocks.forEach((w, b) -> {
                ResourceLocation id = Utils.getID(b);
                try {
                    if (true || WoodConfigs.isEntryEnabled(w, b)) { //generating all the times otherwise we get log spam
                        ///creates blockstate
                        StaticResource newBlockState = modifier.transform(oakBlockstate, id, w);
                        assert newBlockState.location != oakBlockstate.location : "ids cant be the same";
                        pack.addResourceIfNotPresent(manager, newBlockState);

                        ///creates models/block
                        for (StaticResource model : oakModels) {
                            try {
                                StaticResource newModel = modelModifier.transform(model, id, w);
                                assert newModel.location != model.location : "ids cant be the same";
                                pack.addResourceIfNotPresent(manager, newModel);
                            } catch (Exception exception) {
                                EveryCompat.LOGGER.error("Failed to add {} model json file:", b, exception);
                            }
                        }
                    } else {
                        //dummy blockstate so we don't generate models for this
                        pack.getPack().addJson(id, DUMMY_BLOCKSTATE, ResType.BLOCKSTATES);
                    }

                } catch (Exception e) {
                    EveryCompat.LOGGER.error("Failed to add {} blockstate json file:", b, e);
                }
            });
        } catch (Exception e) {
            EveryCompat.LOGGER.error("Could not find blockstate definition for {}", oakBlock);
        }

    }


    //same as above just with just item models. a bunch of copy paste here... ugly
    public static <I extends Item, T extends BlockType> void addItemModels(
            String modId, ResourceManager manager, DynClientResourcesGenerator d,
            Map<T, I> items, T baseType, @Nullable Consumer<BlockTypeResTransformer<T>> extraTransform) {

        if (items.isEmpty()) return;

        //finds one entry. used so we can grab the oak equivalent
        var first = items.entrySet().stream().findFirst().get();
        Item oakItem = BlockType.changeItemType(first.getValue(), first.getKey(), baseType);

        String baseItemname = baseType.getTypeName();

        if (oakItem == null) {
            EveryCompat.LOGGER.error("Skipped generating some item assets because baseItem is null for {}", Utils.getID(first.getValue()));
            return;
        }
        BlockTypeResTransformer<T> modifier = BlockTypeResTransformer.create(modId, manager);
        modifier.IDReplaceType(baseItemname).replaceBlockType(baseItemname);
        if (extraTransform != null) extraTransform.accept(modifier); //idk about this

        BlockTypeResTransformer<T> modelModifier = standardModelTransformer(modId, manager, baseType, baseItemname, extraTransform);

        Set<String> modelsLoc = new HashSet<>();

        ///item model
        try {
            //we cant use this since it might override partent too. Custom textured items need a custom model added manually with addBlockResources
            // modelModifier.replaceItemType(baseItemname);

            BlockTypeResTransformer<T> itemModifier = standardModelTransformer(modId, manager, baseType, baseItemname, extraTransform);

            StaticResource oakItemModel = StaticResource.getOrFail(manager,
                    ResType.ITEM_MODELS.getPath(Utils.getID(oakItem)));

            JsonObject json = RPUtils.deserializeJson(new ByteArrayInputStream(oakItemModel.data));
            //adds models referenced from here. not recursive
            modelsLoc.addAll(RPUtils.findAllResourcesInJsonRecursive(json, s -> s.equals("model") || s.equals("parent")));

            if (json.has("parent")) {
                String parent = json.get("parent").getAsString();
                if (parent.contains("item/generated")) {
                    itemModifier.replaceItemType(baseItemname);
                }
            }

            items.forEach((w, b) -> {
                ResourceLocation id = Utils.getID(b);
                try {
                    StaticResource newRes = itemModifier.transform(oakItemModel, id, w);
                    assert newRes.location != oakItemModel.location : "ids cant be the same";
                    d.addResourceIfNotPresent(manager, newRes);
                } catch (Exception e) {
                    EveryCompat.LOGGER.error("Failed to add {} item model json file:", b, e);
                }
            });
        } catch (Exception e) {
            EveryCompat.LOGGER.error("Could not find item's model file for {}", oakItem);
        }


        //blockstate
        //models
        List<StaticResource> oakModels = new ArrayList<>();

        for (var m : modelsLoc) {
            //remove the ones from mc namespace
            ResourceLocation modelRes = new ResourceLocation(m);
            if (!modelRes.getNamespace().equals("minecraft")) {
                StaticResource model = StaticResource.getOrLog(manager, ResType.MODELS.getPath(m));
                if (model != null) oakModels.add(model);
            }
        }

        items.forEach((w, b) -> {
            ResourceLocation id = Utils.getID(b);
            if (true || WoodConfigs.isEntryEnabled(w, b)) { //generating all the times otherwise we get log spam

                //creates item model
                for (StaticResource model : oakModels) {
                    try {
                        StaticResource newModel = modelModifier.transform(model, id, w);
                        assert newModel.location != model.location : "ids cant be the same";
                        d.addResourceIfNotPresent(manager, newModel);
                    } catch (Exception exception) {
                        EveryCompat.LOGGER.error("Failed to add {} model json file:", b, exception);
                    }
                }
            }
        });
    }

    @NotNull
    private static <T extends BlockType> BlockTypeResTransformer<T> standardModelTransformer(
            String modId, ResourceManager manager, T baseType, String oldTypeName, @Nullable Consumer<BlockTypeResTransformer<T>> extraTransform) {
        BlockTypeResTransformer<T> modelModifier = BlockTypeResTransformer.create(modId, manager);
        if (extraTransform != null) extraTransform.accept(modelModifier);
        modelModifier.IDReplaceType(oldTypeName);
        if (baseType instanceof LeavesType leavesType) {
            modelModifier.replaceLeavesTextures(leavesType);
            var woodT = leavesType.getWoodType();
            if (woodT != null) {
                modelModifier.replaceWoodTextures(woodT);
            }
        } else if (baseType instanceof WoodType woodType) {
            modelModifier.replaceWoodTextures(woodType);
        }

        modelModifier.replaceGenericType(oldTypeName, "block");
        //modelModifier.replaceBlockType(oldTypeName);
        return modelModifier;
    }


    //creates and add new jsons based off the ones at the given resources with the provided modifiers
    public static <B extends Block, T extends BlockType> void addBlockResources(String modId, ResourceManager manager, DynamicResourcePack pack,
                                                                                Map<T, B> blocks, String typeName, ResourceLocation... jsonsLocations) {
        addBlockResources(modId, manager, pack, blocks,
                BlockTypeResTransformer.<T>create(modId, manager)
                        .replaceSimpleType(typeName)
                        .IDReplaceType(typeName),
                jsonsLocations);
    }


    public static <B extends Block, T extends BlockType> void addBlockResources(String modId, ResourceManager manager, DynamicResourcePack pack,
                                                                                Map<T, B> blocks,
                                                                                BlockTypeResTransformer<T> modifier, ResourceLocation... jsonsLocations) {
        List<StaticResource> original = Arrays.stream(jsonsLocations).map(s -> StaticResource.getOrLog(manager, s)).toList();

        blocks.forEach((wood, value) -> {
            if (WoodConfigs.isEntryEnabled(wood, value)) {
                for (var res : original) {

                    try {
                        StaticResource newRes = modifier.transform(res, Utils.getID(value), wood);

                        assert newRes.location != res.location : "ids cant be the same";

                        pack.addResource(newRes);
                    } catch (Exception e) {
                        if (res != null) {
                            EveryCompat.LOGGER.error("Failed to generate json resource from {}", res.location);
                        }
                    }
                }
            }
        });
    }

    //creates and add new recipes based off the one at the given resource

    /**
     * Adds recipes based off an oak leaves based one
     */
    public static void addLeavesRecipes(String modId, ResourceManager manager, DynamicDataPack pack,
                                        Map<LeavesType, Item> blocks, String oakRecipe) {
        addBlocksRecipes(modId, manager, pack, blocks, oakRecipe, LeavesTypeRegistry.OAK_TYPE);
    }

    /**
     * Adds recipes based off an oak planks based one
     */
    public static <B extends Item> void addWoodRecipes(String modId, ResourceManager manager, DynamicDataPack pack,
                                                       Map<WoodType, B> blocks, String oakRecipe) {
        addBlocksRecipes(modId, manager, pack, blocks, oakRecipe, WoodTypeRegistry.OAK_TYPE);
    }

    /**
     * Adds recipes based off a given one
     */
    public static <B extends Item, T extends BlockType> void addBlocksRecipes(String modId, ResourceManager manager, DynamicDataPack pack,
                                                                              Map<T, B> blocks, String oakRecipe, T fromType) {
        addBlocksRecipes(manager, pack, blocks, new ResourceLocation(modId, oakRecipe), fromType, 0);
    }

    public static <B extends Item, T extends BlockType> void addBlocksRecipes(ResourceManager manager, DynamicDataPack pack,
                                                                              Map<T, B> items, ResourceLocation baseRecipe, T fromType,
                                                                              int index) {
        IRecipeTemplate<?> template = RPUtils.readRecipeAsTemplate(manager,
                ResType.RECIPES.getPath(baseRecipe));

        items.forEach((woodType, item) -> {

            if (WoodConfigs.isEntryEnabled(woodType, item)) {
                try {
                    String blockId = RecipeBuilder.getDefaultRecipeId(item).toString();
                    FinishedRecipe newR;

                    String baseRecipePath = baseRecipe.getPath();
                    String modifiedRecipePath = baseRecipePath.substring(baseRecipePath.lastIndexOf("/") + 1).replace(fromType.getTypeName(), woodType.getTypeName());
                    String target = blockId.substring(blockId.lastIndexOf("/") + 1);
                    // Replaced the >text< with modifiedRecipe: everycomp:q/biomesoplenty/ >fir_vertical_slab<
                    String newId = blockId.replace(target, modifiedRecipePath);

                    // matches() ensure the last word, [a-z]_[a-z] is not one word, CASE: lightman's currency
                    if (!blockId.equals(newId) && newId.matches("\\w+:\\w+/\\w+/\\w+_\\w+")) {
                        newR = template.createSimilar(fromType, woodType, woodType.mainChild().asItem(), newId);
                    }
                    else {
                        newR = template.createSimilar(fromType, woodType, woodType.mainChild().asItem());
                    }
                    if (newR == null) return;

                    newR = ForgeHelper.addRecipeConditions(newR, template.getConditions()); //not even needed

                    // Adding to the resources
                    pack.addRecipe(newR);
                } catch (Exception e) {
                    EveryCompat.LOGGER.error("Failed to generate recipe @ {} for {}: {}", baseRecipe, item, e.getMessage());
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

    public static   <T extends BlockType> Ingredient convertIngredient(Ingredient ingredient, T originalMat, T destinationMat ) {
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
