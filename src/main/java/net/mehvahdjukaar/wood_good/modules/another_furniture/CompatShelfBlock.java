package net.mehvahdjukaar.wood_good.modules.another_furniture;

import com.crispytwig.another_furniture.block.ShelfBlock;
import com.crispytwig.another_furniture.block.entity.ShelfBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class CompatShelfBlock extends ShelfBlock {
    public CompatShelfBlock(Properties properties) {
        super(properties);
    }
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CompatShelfBlockTile(pos, state);
    }
}
