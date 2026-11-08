package net.mehvahdjukaar.every_compat.modules.fabric.farmersdelight;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.modules.farmersdelight.FarmersDelightModuleAbstract;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceSink;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.Holder;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import vectorwing.farmersdelight.common.block.CabinetBlock;
import vectorwing.farmersdelight.common.crafting.CuttingBoardRecipe;
import vectorwing.farmersdelight.common.crafting.ingredient.ChanceResult;
import vectorwing.farmersdelight.common.item.FuelBlockItem;

import java.util.ArrayList;
import java.util.List;

import static net.mehvahdjukaar.every_compat.misc.HardcodedBlockType.IsBambooLike;

///See FarmersDelightModuleAbstract's SUPPORTED VERSION
public class FarmersDelightRefabricatedModule extends FarmersDelightModuleAbstract {

    public FarmersDelightRefabricatedModule(String modId) {
        super(modId);
        extendModuleName(" Refabricated");
    }

    @Override
    public Block newCabinetBlock(WoodType wOodType, BlockBehaviour.Properties properties) {
        return new CabinetBlock(Utils.copyPropertySafe(Blocks.BARREL));
    }

    @Override
    public Item newBlockItem(WoodType wOodType, Block block, Item.Properties properties) {
        return new FuelBlockItem(block, properties, 300);
    }

    @Override
    public void createCuttingRecipe(String recipeType, Block input, Block output, WoodType targetType, ResourceManager manager, ResourceSink sink) {

        if (input == null || output == null || IsBambooLike(targetType)) return;

        Recipe<?> recipe = RPUtils.readRecipe(manager, modRes("cutting/oak_" + recipeType).toString());

        if (recipe instanceof CuttingBoardRecipe cuttingRecipe) {
            List<ChanceResult> newResult = convertResults(cuttingRecipe.getRollableResults(), targetType);

            String newPath = targetType.createPathWith(shortenedId(), "cutting/", recipeType);
            CuttingBoardRecipe newRecipe = new CuttingBoardRecipe(modRes("cutting").toString(), Ingredient.of(input),
                    cuttingRecipe.getTool(), newResult, cuttingRecipe.getSoundEventHolder().orElse(null));

            sink.addRecipe(newRecipe, EveryCompat.res(newPath));
        }
    }

    @Override
    public void createSalvagingRecipe(String recipeType, WoodType newWoodType, ResourceManager manager, ResourceSink sink) {

        Recipe<?> recipe = RPUtils.readRecipe(manager, modRes("salvaging/oak_" + recipeType).toString());

        if (recipe instanceof CuttingBoardRecipe cuttingRecipe) {

            List<Item> newInputs = new ArrayList<>();
            for (Holder<Item> holder : cuttingRecipe.getInput().items().toList()) {
                Item oldItem = holder.value();
                WoodType oldWoodType = WoodTypeRegistry.INSTANCE.getBlockTypeOf(oldItem);
                if (oldWoodType == null) continue;
                Item newItem = BlockType.changeItemType(oldItem, oldWoodType, newWoodType);
                if (newItem != null) newInputs.add(newItem);
            }
            if (newInputs.isEmpty()) return;

            List<ChanceResult> newResult = convertResults(cuttingRecipe.getRollableResults(), newWoodType);

            String newPath = newWoodType.createPathWith(shortenedId(), "salvaging/", recipeType);
            CuttingBoardRecipe newRecipe = new CuttingBoardRecipe(modRes("cutting").toString(), Ingredient.of(newInputs.stream()),
                    cuttingRecipe.getTool(), newResult, cuttingRecipe.getSoundEventHolder().orElse(null));

            sink.addRecipe(newRecipe, EveryCompat.res(newPath));
        }
    }

    //only oak results get swapped bark and such stay
    private static List<ChanceResult> convertResults(List<ChanceResult> oldResults, WoodType targetType) {
        List<ChanceResult> newResults = new ArrayList<>(oldResults.size());
        for (ChanceResult chanceResult : oldResults) {
            ItemStackTemplate stack = chanceResult.stack();
            Item baseItem = stack.item().value();
            WoodType originalType = WoodTypeRegistry.INSTANCE.getBlockTypeOf(baseItem);
            Item newItem = originalType == VanillaWoodTypes.OAK ? BlockType.changeItemType(baseItem, originalType, targetType) : null;
            if (newItem != null) {
                newResults.add(new ChanceResult(new ItemStackTemplate(newItem, stack.components()).withCount(stack.count()), chanceResult.chance()));
            } else newResults.add(chanceResult);
        }
        return newResults;
    }
}

