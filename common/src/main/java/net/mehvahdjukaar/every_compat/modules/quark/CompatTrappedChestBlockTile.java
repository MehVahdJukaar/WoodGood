package net.mehvahdjukaar.every_compat.modules.quark;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.violetmoon.quark.content.building.block.be.VariantTrappedChestBlockEntity;

public class CompatTrappedChestBlockTile extends VariantTrappedChestBlockEntity {

    public CompatTrappedChestBlockTile(BlockPos pos, BlockState state) {
        super(pos, state);

    }

    @Override
    public BlockEntityType<?> getType() {
        return QuarkModule.TRAPPED_CHEST_TILE;
    }
}
