package net.mehvahdjukaar.every_compat.modules.farmersdelight;

import com.crispytwig.another_furniture.block.entity.ShelfBlockEntity;
import net.mehvahdjukaar.every_compat.modules.another_furniture.AnotherFurnitureModule;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class CompatCabinetBlockTile extends ShelfBlockEntity {

    public CompatCabinetBlockTile(BlockPos pos, BlockState state) {
        super(pos, state);
    }

    @Override
    public BlockEntityType<?> getType() {
        return FarmersDelightModule.COMPAT_CABINET_TILE;
    }
}
