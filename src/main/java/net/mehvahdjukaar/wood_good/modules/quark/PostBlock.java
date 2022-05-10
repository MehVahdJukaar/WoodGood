package net.mehvahdjukaar.wood_good.modules.quark;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import vazkii.quark.base.handler.RenderLayerHandler;
import vazkii.quark.base.handler.RenderLayerHandler.RenderTypeSkeleton;
import vazkii.quark.content.building.block.WoodPostBlock;

import javax.annotation.Nonnull;

public class PostBlock extends Block implements SimpleWaterloggedBlock {
    private static final VoxelShape SHAPE_X = Block.box(0.0D, 6.0D, 6.0D, 16.0D, 10.0D, 10.0D);
    private static final VoxelShape SHAPE_Y = Block.box(6.0D, 0.0D, 6.0D, 10.0D, 16.0D, 10.0D);
    private static final VoxelShape SHAPE_Z = Block.box(6.0D, 6.0D, 0.0D, 10.0D, 10.0D, 16.0D);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final EnumProperty<Axis> AXIS = BlockStateProperties.AXIS;
    public static final BooleanProperty[] CHAINED = WoodPostBlock.CHAINED;

    public PostBlock(Block parent, boolean nether) {
        super(Properties.copy(parent).sound(nether ? SoundType.STEM : SoundType.WOOD));
        BlockState state = this.stateDefinition.any().setValue(WATERLOGGED, false).setValue(AXIS, Axis.Y);

        for (BooleanProperty prop : CHAINED) {
            state = state.setValue(prop, false);
        }

        this.registerDefaultState(state);
        RenderLayerHandler.setRenderType(this, RenderTypeSkeleton.CUTOUT);
    }

    @Nonnull
    public VoxelShape getShape(BlockState state, @Nonnull BlockGetter worldIn, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
        return switch (state.getValue(AXIS)) {
            case X -> SHAPE_X;
            case Y -> SHAPE_Y;
            default -> SHAPE_Z;
        };
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, @Nonnull BlockGetter reader, @Nonnull BlockPos pos) {
        return !(Boolean) state.getValue(WATERLOGGED);
    }

    @Nonnull
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.getState(context.getLevel(), context.getClickedPos(), context.getClickedFace().getAxis());
    }

    @Nonnull
    public BlockState updateShape(BlockState state, @Nonnull Direction facing, @Nonnull BlockState facingState, @Nonnull LevelAccessor level, @Nonnull BlockPos pos, @Nonnull BlockPos facingPos) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }
        return super.updateShape(state, facing, facingState, level, pos, facingPos);
    }

    public void neighborChanged(@Nonnull BlockState state, @Nonnull Level worldIn, @Nonnull BlockPos pos, @Nonnull Block blockIn, @Nonnull BlockPos fromPos, boolean isMoving) {
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos, isMoving);
        BlockState newState = this.getState(worldIn, pos, (Axis) state.getValue(AXIS));
        if (!newState.equals(state)) {
            worldIn.setBlockAndUpdate(pos, newState);
        }

    }

    private BlockState getState(Level world, BlockPos pos, Axis axis) {
        BlockState state = this.defaultBlockState().setValue(WATERLOGGED, world.getFluidState(pos).getType() == Fluids.WATER).setValue(AXIS, axis);
        for (Direction d : Direction.values()) {
            if (d.getAxis() != axis) {
                BlockState sideState = world.getBlockState(pos.relative(d));
                if (sideState.getBlock() instanceof ChainBlock && sideState.getValue(BlockStateProperties.AXIS) == d.getAxis() || d == Direction.DOWN && sideState.getBlock() instanceof LanternBlock && (Boolean) sideState.getValue(LanternBlock.HANGING)) {
                    BooleanProperty prop = CHAINED[d.ordinal()];
                    state = state.setValue(prop, true);
                }
            }
        }
        return state;
    }

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED, AXIS);
        for (BooleanProperty prop : CHAINED) {
            builder.add(prop);
        }

    }
}
