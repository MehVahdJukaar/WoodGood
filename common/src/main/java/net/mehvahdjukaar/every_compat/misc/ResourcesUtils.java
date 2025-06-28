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
import net.mehvahdjukaar.moonlight.api.resources.pack.DynClientResourcesGenerator;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynamicDataPack;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynamicResourcePack;
import net.mehvahdjukaar.moonlight.api.resources.textures.SpriteUtils;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesType;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesTypeRegistry;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Map.entry;

@SuppressWarnings("unused")
public class ResourcesUtils {


    @SuppressWarnings("PointlessBooleanExpression")
    public static <B extends Block, T extends BlockType> void generateStandardBlockModels(
            ResourceManager manager, DynClientResourcesGenerator d,
            Map<T, B> blocks, T baseType,
            BlockTypeResTransformer<T> modelTransformer,
            BlockTypeResTransformer<T> blockStateTransformer) {

        if (blocks.isEmpty()) return;

        //finds one entry. used so we can grab the oak equivalent
        var first = blocks.entrySet().stream().findFirst().get();
        Block oakBlock = BlockType.changeBlockType(first.getValue(), first.getKey(), baseType);

        if (oakBlock == null) {
            EveryCompat.LOGGER.error("Failed to generate some block assets");
            return;
        }

        ResourceLocation oakId = Utils.getID(oakBlock);

        Set<String> modelsLoc = new HashSet<>();

        // Blockstate & Models
        try {
            StaticResource oakBlockstate = StaticResource.getOrFail(manager, ResType.BLOCKSTATES.getPath(oakId));

            JsonElement insideBlockstates = RPUtils.deserializeJson(new ByteArrayInputStream(oakBlockstate.data));

            modelsLoc.addAll(RPUtils.findAllResourcesInJsonRecursive(insideBlockstates, s -> s.equals("model")));

            List<StaticResource> oakBlockModels = gatherNonVanillaModels(manager, modelsLoc);

            blocks.forEach((blockType, block) -> {
                ResourceLocation blockId = Utils.getID(block);
                try {
                    if (true || ModEntriesConfigs.isEntryEnabled(blockType, block)) { //generating all the times otherwise we get log spam
                        //creates blockstate
                        StaticResource newBlockState = blockStateTransformer.transform(oakBlockstate, blockId, blockType);
                        Preconditions.checkArgument(newBlockState.location != oakBlockstate.location,
                                "ids cant be the same: " + newBlockState.location);
                        //Adding to the resources
                        d.addResourceIfNotPresent(manager, newBlockState);

                        //creates block model
                        for (StaticResource model : oakBlockModels) {
                            try {
                                // Modifying models' contents & path
                                StaticResource newModel = modelTransformer.transform(model, blockId, blockType);

                                Preconditions.checkArgument(newModel.location != model.location,
                                        "ids cant be the same: " + newModel.location);
                                //Adding to the resources
                                d.addResourceIfNotPresent(manager, newModel);
                            } catch (Exception exception) {
                                EveryCompat.LOGGER.error("Failed to add {} model json file:", block, exception);
                            }
                        }
                    } else {
                        //dummy blockstate so we don't generate models for this
                        d.getPack().addJson(blockId, DUMMY_BLOCKSTATE, ResType.BLOCKSTATES);
                    }

                } catch (Exception e) {
                    EveryCompat.LOGGER.error("Failed to add {} blockstate json file:", block, e);
                }
            });
        } catch (Exception e) {
            EveryCompat.LOGGER.error("Could not find blockstate definition for {}", oakBlock);
        }

    }

    private static List<StaticResource> gatherNonVanillaModels(ResourceManager manager, Set<String> modelsLoc) {
        List<StaticResource> models = new ArrayList<>();

        // List of vanilla wood types to filter out
        final Set<String> VANILLA_WOODS = Set.of(
            "oak", "spruce", "birch", "jungle", "acacia", "dark_oak", "mangrove", "cherry", "crimson", "warped"
        );
        for (var m : modelsLoc) {
            ResourceLocation modelRes = ResourceLocation.parse(m);
            String path = modelRes.getPath();
            boolean isVanillaWood = modelRes.getNamespace().equals("minecraft") &&
                VANILLA_WOODS.stream().anyMatch(wood -> path.contains(wood));
            if (!isVanillaWood) {
                StaticResource model = StaticResource.getOrLog(manager, ResType.MODELS.getPath(m));
                if (model != null) models.add(model);
            }
        }
        return models;
    }


    //same as above just with just item models. a bunch of copy paste here... ugly
    @SuppressWarnings("PointlessBooleanExpression")
    public static <I extends Item, T extends BlockType> void generateStandardItemModels(
            ResourceManager manager, DynClientResourcesGenerator d,
            Map<T, I> items, T baseType, BlockTypeResTransformer<T> itemModelTransformer) {

        if (items.isEmpty()) return;

        //finds one entry. used so we can grab the oak equivalent
        var first = items.entrySet().stream().findFirst().get();
        Item oakItem = BlockType.changeItemType(first.getValue(), first.getKey(), baseType);

        if (oakItem == null) {
            EveryCompat.LOGGER.error("Failed to generate some item assets");
            return;
        }
        String baseItemName = baseType.getTypeName();

        Set<String> modelsLoc = new HashSet<>();

        //item model
        try {
            //we cant use this since it might override partent too. Custom textured items need a custom model added manually with addBlockResources
            // modelModifier.replaceItemType(baseItemname);

            StaticResource oakItemModel = StaticResource.getOrFail(manager,
                    ResType.ITEM_MODELS.getPath(Utils.getID(oakItem)));

            JsonObject json = RPUtils.deserializeJson(new ByteArrayInputStream(oakItemModel.data));
            //adds models referenced from here. not recursive
            modelsLoc.addAll(RPUtils.findAllResourcesInJsonRecursive(json, s -> s.equals("model") || s.equals("parent")));

            if (json.has("parent")) {
                String parent = json.get("parent").getAsString();
                if (parent.contains("item/generated")) {
                    itemModelTransformer.replaceItemType(baseItemName);
                }
            }

            items.forEach((w, b) -> {
                ResourceLocation id = Utils.getID(b);
                try {
                    StaticResource newRes = itemModelTransformer.transform(oakItemModel, id, w);
                    Preconditions.checkArgument(newRes.location != oakItemModel.location,
                            "ids cant be the same: " + newRes.location);
                    d.addResourceIfNotPresent(manager, newRes);
                } catch (Exception e) {
                    EveryCompat.LOGGER.error("Failed to add {} item model json file:", b, e);
                }
            });
        } catch (Exception e) {
            EveryCompat.LOGGER.error("Could not find item model for {}", oakItem);
        }


        //models
        List<StaticResource> oakItemModels = gatherNonVanillaModels(manager, modelsLoc);

        items.forEach((w, b) -> {
            ResourceLocation id = Utils.getID(b);
            if (true || ModEntriesConfigs.isEntryEnabled(w, b)) { //generating all the times otherwise we get log spam

                //creates item model
                for (StaticResource model : oakItemModels) {
                    try {
                        StaticResource newModel = itemModelTransformer.transform(model, id, w);
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
    @SuppressWarnings("UnusedReturnValue")
    public static <T extends BlockType> BlockTypeResTransformer<T> addBuiltinModelTransformer(
            BlockTypeResTransformer<T> transformer, T baseType) {
        String oldTypeName = baseType.getTypeName();

        // Modifying models' filename & ResourceLocation
        transformer.setIDModifier((text, id, w) ->
                BlockTypeResTransformer.replaceFullGenericType(text, w, id, oldTypeName, null, 2));

        // Modifying the model files' content
        if (baseType instanceof LeavesType leavesType) {
            SpriteHelper.replaceLeavesTextures(transformer, leavesType);
            var woodT = leavesType.getWoodType();
            if (woodT != null) {
                SpriteHelper.replaceWoodTextures(transformer, woodT);
            }
        } else if (baseType instanceof WoodType woodType) {
            SpriteHelper.replaceWoodTextures(transformer, woodType);
        }

        transformer.replaceGenericType(oldTypeName, "block");

        return transformer;
    }


    //creates and add new jsons based off the ones at the given resources with the provided modifiers
    public static <B extends Block, T extends BlockType> void addBlockResources(ResourceManager manager, DynamicResourcePack pack,
                                                                                Map<T, B> blocks,
                                                                                BlockTypeResTransformer<T> modifier, ResourceLocation... jsonsLocations) {
        List<StaticResource> original = Arrays.stream(jsonsLocations).map(s -> StaticResource.getOrLog(manager, s)).toList();

        blocks.forEach((wood, value) -> {
            if (ModEntriesConfigs.isEntryEnabled(wood, value)) {
                for (var res : original) {

                    try {
                        StaticResource newRes = modifier.transform(res, Utils.getID(value), wood);

                        Preconditions.checkArgument(newRes.location != res.location,
                                "ids cant be the same: " + newRes.location);

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
        addBlocksRecipes(manager, pack, blocks, ResourceLocation.fromNamespaceAndPath(modId, oakRecipe), fromType, 0);
    }

    @SuppressWarnings("removal")
    public static <B extends Item, T extends BlockType> void addBlocksRecipes(ResourceManager manager, DynamicDataPack pack,
                                                                              Map<T, B> items, ResourceLocation oakRecipe, T fromType,
                                                                              int index) {
        Recipe<?> template = RPUtils.readRecipe(manager, oakRecipe);
        items.forEach((w, i) -> {

            if (ModEntriesConfigs.isEntryEnabled(w, i)) {
                try {
                    //check for disabled ones. Will actually crash if its null since vanilla recipe builder expects a non-null one
                    ResourceLocation id = RecipeBuilder.getDefaultRecipeId(i);
                    RecipeHolder<?> newR;
                    if (index != 0) {
                        id = id.withSuffix("_" + index);
                    }
                    newR = RPUtils.makeSimilarRecipe(template, fromType, w, id);

                    //not even needed
                    //newR = ForgeHelper.copyRecipeConditions(template, newR.value());

                    // Adding to the resources
                    pack.addRecipe(newR);
                } catch (Exception e) {
                    EveryCompat.LOGGER.error("Failed to generate recipe @ {} for {}: {}", oakRecipe, i, e.getMessage());
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

    public static String convertItemIDinText(String text, BlockType fromType, BlockType toType) {
        Matcher matcher = RES_PATTERN.matcher(text);
        return matcher.replaceAll(m -> {
            var item = BuiltInRegistries.ITEM.getOptional(ResourceLocation.tryParse(m.group(1)));
            return item.map(value ->
                    mapOfItem.getOrDefault(item.get().toString(),
                            "\"" + Utils.getID(BlockType.changeItemType(value, fromType, toType)).toString() + "\"")
                    ).orElseGet(() -> m.group(0));
        });
    }

    /**
     * if item (key) matched the following below, then instead of "minecraft:air", the value will be used
     * NOTE:
     * Quark's bookshelf and it's loot_table where it has "minecraft:booK" will be replaced with
     * "minecraft:air". A similar case with "minecraft:shulker_box"
    **/
    private static final Map<String, String> mapOfItem = Map.ofEntries(
            entry("shulker_box", "\"minecraft:shulker_box\""),
            entry("book", "\"minecraft:book\""),

            // Re: Deco
            entry("white_upholstery", "\"redeco:white_upholstery\""),
            entry("light_gray_upholstery", "\"redeco:light_gray_upholstery\""),
            entry("gray_upholstery", "\"redeco:gray_upholstery\""),
            entry("black_upholstery", "\"redeco:black_upholstery\""),
            entry("lime_upholstery", "\"redeco:lime_upholstery\""),
            entry("green_upholstery", "\"redeco:green_upholstery\""),
            entry("cyan_upholstery", "\"redeco:cyan_upholstery\""),
            entry("blue_upholstery", "\"redeco:blue_upholstery\""),
            entry("light_blue_upholstery", "\"redeco:light_blue_upholstery\""),
            entry("purple_upholstery", "\"redeco:purple_upholstery\""),
            entry("magenta_upholstery", "\"redeco:magenta_upholstery\""),
            entry("pink_upholstery", "\"redeco:pink_upholstery\""),
            entry("orange_upholstery", "\"redeco:orange_upholstery\""),
            entry("yellow_upholstery", "\"redeco:yellow_upholstery\""),
            entry("brown_upholstery", "\"redeco:brown_upholstery\"")
    );

}
