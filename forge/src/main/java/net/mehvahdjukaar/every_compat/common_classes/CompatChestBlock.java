package net.mehvahdjukaar.every_compat.common_classes;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class CompatChestBlock extends ChestBlock {

    private final Supplier<BlockEntityType<? extends ChestBlockEntity>> tile;

    public CompatChestBlock(Supplier<BlockEntityType<? extends ChestBlockEntity>> tile,
                            BlockBehaviour.Properties properties) {
        super(properties, tile);
        this.tile = tile;
    }

    public BlockEntityType<? extends ChestBlockEntity> getTileType() {
        return tile.get();
    }

    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new CompatChestBlockEntity(getTileType(), pos, state);
    }
}
