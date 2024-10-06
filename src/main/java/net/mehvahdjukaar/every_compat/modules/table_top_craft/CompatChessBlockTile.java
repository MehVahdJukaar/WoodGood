package net.mehvahdjukaar.every_compat.modules.table_top_craft;

import andrews.table_top_craft.tile_entities.ChessTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class CompatChessBlockTile extends ChessTileEntity {

    public CompatChessBlockTile(BlockPos pos, BlockState state) {
        super(pos, state);
    }

    @Override
    public BlockEntityType<?> getType() {
        return TableTopCraftModule.CHESS_TILE;
    }
}