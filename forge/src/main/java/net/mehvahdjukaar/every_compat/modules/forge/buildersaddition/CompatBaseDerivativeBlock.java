package net.mehvahdjukaar.every_compat.modules.forge.buildersaddition;

import com.mrh0.buildersaddition.blocks.base.BaseBlock;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class CompatBaseDerivativeBlock extends BaseBlock {
    private final Block source;

    public CompatBaseDerivativeBlock(String name, Block source) {
        super(name, Utils.copyPropertySafe(source));
        this.source = source;
        blockTagSort(this, source);
    }

    public static void blockTagSort(Block current, Block source) {
    }

    public Block getSourceBlock() {
        return this.source;
    }

    public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return this.source.isFlammable(this.source.defaultBlockState(), world, pos, face);
    }

    public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return this.source.getFlammability(this.source.defaultBlockState(), world, pos, face);
    }

    public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return this.source.getFireSpreadSpeed(this.source.defaultBlockState(), world, pos, face);
    }
}
