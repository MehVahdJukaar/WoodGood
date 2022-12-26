package net.mehvahdjukaar.every_compat.modules.quark;

import net.mehvahdjukaar.moonlight.api.item.WoodBasedBlockItem;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import vazkii.quark.content.building.block.VariantChestBlock;
import vectorwing.farmersdelight.common.registry.ModItems;

public class CompatChestBlock extends VariantChestBlock {

    private final WoodType woodType;

    public CompatChestBlock(WoodType woodType, String type, vazkii.quark.base.module.QuarkModule module, Properties props) {
        super(type, module, () -> QuarkModule.CHEST_TILE, props);
        this.woodType = woodType;
    }

    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new CompatChestBlockTile(pos, state);
    }

    @Override
    public String getChestTexturePath() {
        return "model/chest/everycompat/" + woodType.getAppendableId() + "_";
    }


    public static class Item extends VariantChestBlock.Item{

        public Item(Block block, Properties props) {
            super(block, props);
        }

        public final int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
            return 300;
        }

    }
}
