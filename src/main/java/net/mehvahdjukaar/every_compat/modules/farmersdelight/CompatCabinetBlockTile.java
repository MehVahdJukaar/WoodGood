package net.mehvahdjukaar.every_compat.modules.farmersdelight;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import vectorwing.farmersdelight.common.block.entity.CabinetBlockEntity;

public class CompatCabinetBlockTile extends CabinetBlockEntity {

    public CompatCabinetBlockTile(BlockPos pos, BlockState state) {
        super(pos, state);
    }

    @Override
    public BlockEntityType<?> getType() {
        return FarmersDelightModule.COMPAT_CABINET_TILE;
    }
}
