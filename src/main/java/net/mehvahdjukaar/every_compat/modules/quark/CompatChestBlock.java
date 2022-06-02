package net.mehvahdjukaar.every_compat.modules.quark;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import vazkii.quark.base.module.QuarkModule;
import vazkii.quark.content.building.block.VariantChestBlock;

import java.util.function.Supplier;

public class CompatChestBlock extends VariantChestBlock {

    public CompatChestBlock(String type, QuarkModule module, Supplier<BlockEntityType<? extends ChestBlockEntity>> supplier, Properties props) {
        super(type, module, supplier, props);
    }

    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new CompatChestBlockTile(pos, state);
    }
}
