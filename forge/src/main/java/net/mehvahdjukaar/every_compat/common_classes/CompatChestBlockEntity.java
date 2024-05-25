package net.mehvahdjukaar.every_compat.common_classes;

import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class CompatChestBlockEntity extends ChestBlockEntity {
    private final WoodType woodType;

    public CompatChestBlockEntity(BlockEntityType<?> arg, BlockPos pos, BlockState state) {
        super(arg, pos, state);
        var w = WoodTypeRegistry.INSTANCE.getBlockTypeOf(state.getBlock());
        this.woodType = w == null ? WoodTypeRegistry.OAK_TYPE : w;
    }

    public WoodType getWoodType() {
        return woodType;
    }
}
