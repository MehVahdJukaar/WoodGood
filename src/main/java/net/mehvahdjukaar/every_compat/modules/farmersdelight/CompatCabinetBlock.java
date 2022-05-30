package net.mehvahdjukaar.every_compat.modules.farmersdelight;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import vectorwing.farmersdelight.common.block.CabinetBlock;

public class CompatCabinetBlock extends CabinetBlock {
    public CompatCabinetBlock(Properties properties) {
        super(properties);
    }
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CompatCabinetBlockTile(pos, state);
    }
}
