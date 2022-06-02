package net.mehvahdjukaar.every_compat.misc;

import com.google.common.base.Stopwatch;
import com.google.gson.JsonElement;
import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.selene.block_set.BlockType;
import net.mehvahdjukaar.selene.block_set.leaves.LeavesType;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.mehvahdjukaar.selene.resourcepack.*;
import net.mehvahdjukaar.selene.resourcepack.recipe.IRecipeTemplate;
import net.minecraft.client.renderer.block.model.BlockModelDefinition;
import net.minecraft.client.renderer.block.model.multipart.MultiPart;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class Utils {
    public static <B extends Block, T extends BlockType> void addStandardResources(String modId, ResourceManager manager, DynamicResourcePack pack,
                                                                  Map<T, B> blocks) {
        assert !blocks.isEmpty() : "Block map must not be empty";
        //finds one entry. used so we can grab the oak equivalent
        var first = blocks.entrySet().stream().findFirst().get();
        ItemLike oi = BlockType.changeItemBlockType(first.getValue(), first.getKey(), WoodType.OAK_WOOD_TYPE);
        Block oakBlock;
        if (oi instanceof Block bl) {
            oakBlock = bl;
        } else {
            WoodGood.LOGGER.error("Failed to generate some assets");
            return;
        }
        ResourceLocation oakId = oakBlock.getRegistryName();

        BlockTypeResTransformer<T> modifier = BlockTypeResTransformer.create(modId, manager);
        modifier.IDReplaceType("oak").replaceBlockType("oak");


        Set<String> modelsLoc = new HashSet<>();

        //item model
        try {
            StaticResource oakItemModel = StaticResource.getOrFail(manager, ResType.ITEM_MODELS.getPath(oakId));
            JsonElement json = RPUtils.deserializeJson(oakItemModel.getInputStream());
            //adds models referenced from here. not recursive
            modelsLoc.addAll(RPUtils.findAllResourcesInJsonRecursive(json, s -> s.equals("model") || s.equals("parent")));

            blocks.forEach((w, b) -> {
                ResourceLocation id = b.getRegistryName();
                try {
                    StaticResource newRes = modifier.transform(oakItemModel, id, w);
                    assert newRes.location != oakItemModel.location : "ids cant be the same";
                    pack.addResource(newRes);
                } catch (Exception e) {
                    WoodGood.LOGGER.error("Failed to add {} item model json file:", b, e);
                }
            });
        } catch (Exception e) {
            WoodGood.LOGGER.error("Could not find item model for {}", oakBlock);
        }

        BlockTypeResTransformer<T> modelModifier = BlockTypeResTransformer.create(modId, manager);
        modelModifier.IDReplaceType("oak").replaceBlockType("oak")
                .replaceOakBark().replaceOakPlanks().replaceOakStripped();

        //blockstate
        try {
            StaticResource oakBlockstate = StaticResource.getOrFail(manager, ResType.BLOCKSTATES.getPath(oakId));

            //models
            JsonElement json = RPUtils.deserializeJson(oakBlockstate.getInputStream());

            modelsLoc.addAll(RPUtils.findAllResourcesInJsonRecursive(json, s -> s.equals("model")));
            List<StaticResource> oakModels = new ArrayList<>();

            for (var m : modelsLoc) {
                StaticResource model = StaticResource.getOrLog(manager, ResType.MODELS.getPath(m));
                if (model != null) oakModels.add(model);
            }

            blocks.forEach((w, b) -> {
                ResourceLocation id = b.getRegistryName();
                try {
                    StaticResource newRes = modifier.transform(oakBlockstate, id, w);
                    assert newRes.location != oakBlockstate.location : "ids cant be the same";
                    pack.addResource(newRes);

                    for (StaticResource model : oakModels) {
                        try {
                            StaticResource newModel = modelModifier.transform(model, id, w);
                            assert newModel.location != model.location : "ids cant be the same";
                            pack.addResource(newModel);
                        } catch (Exception exception) {
                            WoodGood.LOGGER.error("Failed to add {} model json file:", b, exception);
                        }
                    }

                } catch (Exception e) {
                    WoodGood.LOGGER.error("Failed to add {} blockstate json file:", b, e);
                }
            });
        } catch (Exception e) {
            WoodGood.LOGGER.error("Could not find blockstate definition for {}", oakBlock);
        }
    }


    //creates and add new jsons based off the ones at the given resources with the provided modifiers
    public static <B extends Block, T extends BlockType> void addBlockResources(String modId, ResourceManager manager, DynamicResourcePack pack,
                                                               Map<T, B> blocks, String replaceTarget, ResourceLocation... jsonsLocations) {
        addBlockResources(modId, manager, pack, blocks,
                BlockTypeResTransformer.<T>create(modId, manager)
                        .replaceSimpleBlock(modId, replaceTarget)
                        .IDReplaceBlock(replaceTarget),
                jsonsLocations);
    }


    public static <B extends Block, T extends BlockType> void addBlockResources(String modId, ResourceManager manager, DynamicResourcePack pack,
                                                               Map<T, B> blocks,
                                                               BlockTypeResTransformer<T> modifier, ResourceLocation... jsonsLocations) {
        List<StaticResource> original = Arrays.stream(jsonsLocations).map(s -> StaticResource.getOrLog(manager, s)).collect(Collectors.toList());

        blocks.forEach((wood, value) -> {

            for (var res : original) {
                try {
                    StaticResource newRes = modifier.transform(res, value.getRegistryName(), wood);

                    assert newRes.location != res.location : "ids cant be the same";

                    pack.addResource(newRes);
                } catch (Exception e) {
                    WoodGood.LOGGER.error("Failed to generate json resource from {}", res.location);
                }
            }
        });
    }

    //creates and add new recipes based off the one at the given resource

    /**
     * Adds recipes based off an oak leaves based one
     */
    public static void addLeavesRecipes(String modId, ResourceManager manager, DynamicDataPack pack,
                                        Map<LeavesType, Block> blocks, String oakRecipe) {
        addBlocksRecipes(modId, manager, pack, blocks, oakRecipe, LeavesType.OAK_LEAVES_TYPE);
    }

    /**
     * Adds recipes based off an oak planks based one
     */
    public static <B extends Block>  void addWoodRecipes(String modId, ResourceManager manager, DynamicDataPack pack,
                                      Map<WoodType, B> blocks, String oakRecipe) {
        addBlocksRecipes(modId, manager, pack, blocks, oakRecipe, WoodType.OAK_WOOD_TYPE);
    }

    /**
     * Adds recipes based off a given one
     */
    public static <B extends Block, T extends BlockType> void addBlocksRecipes(String modId, ResourceManager manager, DynamicDataPack pack,
                                                              Map<T, B> blocks, String oakRecipe, T fromType) {
        addBlocksRecipes(manager, pack, blocks, new ResourceLocation(modId, oakRecipe), fromType);
    }

    public static <B extends Block, T extends BlockType> void addBlocksRecipes(ResourceManager manager, DynamicDataPack pack,
                                                                               Map<T, B> blocks, ResourceLocation oakRecipe, T fromType) {
        IRecipeTemplate<?> template = RPUtils.readRecipeAsTemplate(manager,
                ResType.RECIPES.getPath( oakRecipe));

        blocks.forEach((w, b) -> {
            FinishedRecipe newR = template.createSimilar(fromType, w, w.mainChild().asItem());
            pack.addRecipe(newR);
        });
    }
}
