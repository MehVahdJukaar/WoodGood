package net.mehvahdjukaar.every_compat.modules.another_furniture;

import com.crispytwig.another_furniture.block.entity.ShelfBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class CompatShelfBlockTile extends ShelfBlockEntity {

    public CompatShelfBlockTile(BlockPos pos, BlockState state) {
        super(pos, state);
    }

    @Override
    public BlockEntityType<?> getType() {
        return AnotherFurnitureModule.COMPAT_SHELF_TILE;
    }
}
