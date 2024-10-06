package net.mehvahdjukaar.every_compat.modules.table_top_craft;

import andrews.table_top_craft.objects.blocks.ChessBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

public class CompatChessBlock extends ChessBlock {

    public CompatChessBlock(Material material, SoundType soundType) {
        super(material, soundType);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CompatChessBlockTile(pos, state);
    }
}