package net.mehvahdjukaar.every_compat.common_classes;

import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TrappedChestBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class CompatChestBlockEntity extends ChestBlockEntity {
    private final WoodType woodType;
    private final boolean trapped;

    public CompatChestBlockEntity(BlockEntityType<?> arg, BlockPos pos, BlockState state) {
        super(arg, pos, state);
        var w = WoodTypeRegistry.INSTANCE.getBlockTypeOf(state.getBlock());
        this.woodType = w == null ? WoodTypeRegistry.OAK_TYPE : w;
        this.trapped = state.getBlock() instanceof CompatTrappedChestBlock;
    }

    public WoodType getWoodType() {
        return woodType;
    }

    @Override
    protected void signalOpenCount(Level level, BlockPos pos, BlockState state, int eventId, int eventParam) {
        super.signalOpenCount(level, pos, state, eventId, eventParam);
        if (trapped && eventId != eventParam) {
            Block block = state.getBlock();
            level.updateNeighborsAt(pos, block);
            level.updateNeighborsAt(pos.below(), block);
        }
    }

    public boolean isTrapped() {
        return trapped;
    }
}
