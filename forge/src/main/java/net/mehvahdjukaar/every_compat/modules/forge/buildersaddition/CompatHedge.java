package net.mehvahdjukaar.every_compat.modules.forge.buildersaddition;

import com.mrh0.buildersaddition.blocks.Hedge;
import com.mrh0.buildersaddition.state.HedgeState;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.IForgeShearable;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("deprecation")
public class CompatHedge  extends CompatBaseDerivativeBlock implements SimpleWaterloggedBlock, IForgeShearable {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final EnumProperty<HedgeState> STATE = EnumProperty.create("state", HedgeState.class);

    private static final VoxelShape SHAPE_NONE = Block.box(4d, 0d, 4d, 12d, 16d, 12d);
    private static final VoxelShape SHAPE_STRAIGHT_Z = Block.box(0d, 0d, 4d, 16d, 16d, 12d);
    private static final VoxelShape SHAPE_STRAIGHT_X = Block.box(4d, 0d, 0d, 12d, 16d, 16d);

    private static final VoxelShape SHAPE_SHORT_N = Block.box(4d, 0d, 0d, 12d, 16d, 4d);
    private static final VoxelShape SHAPE_SHORT_E = Block.box(12d, 0d, 4d, 16d, 16d, 12d);
    private static final VoxelShape SHAPE_SHORT_S = Block.box(4d, 0d, 12d, 12d, 16d, 16d);
    private static final VoxelShape SHAPE_SHORT_W = Block.box(0d, 0d, 4d, 4d, 16d, 12d);

    private static final VoxelShape SHAPE_CORNER_NE = Shapes.or(SHAPE_NONE, SHAPE_SHORT_N, SHAPE_SHORT_E);
    private static final VoxelShape SHAPE_CORNER_NW = Shapes.or(SHAPE_NONE, SHAPE_SHORT_N, SHAPE_SHORT_W);
    private static final VoxelShape SHAPE_CORNER_SE = Shapes.or(SHAPE_NONE, SHAPE_SHORT_S, SHAPE_SHORT_E);
    private static final VoxelShape SHAPE_CORNER_SW = Shapes.or(SHAPE_NONE, SHAPE_SHORT_S, SHAPE_SHORT_W);

    private static final VoxelShape SHAPE_T_N = Shapes.or(SHAPE_STRAIGHT_Z, SHAPE_SHORT_N);
    private static final VoxelShape SHAPE_T_E = Shapes.or(SHAPE_STRAIGHT_X, SHAPE_SHORT_E);
    private static final VoxelShape SHAPE_T_S = Shapes.or(SHAPE_STRAIGHT_Z, SHAPE_SHORT_S);
    private static final VoxelShape SHAPE_T_W = Shapes.or(SHAPE_STRAIGHT_X, SHAPE_SHORT_W);

    private static final VoxelShape SHAPE_CROSS = Shapes.or(SHAPE_STRAIGHT_X, SHAPE_STRAIGHT_Z);

    private static final VoxelShape COL_NONE = Block.box(4d, 0d, 4d, 12d, 24d, 12d);
    private static final VoxelShape COL_STRAIGHT_Z = Block.box(0d, 0d, 4d, 16d, 24d, 12d);
    private static final VoxelShape COL_STRAIGHT_X = Block.box(4d, 0d, 0d, 12d, 24d, 16d);

    private static final VoxelShape COL_SHORT_N = Block.box(4d, 0d, 0d, 12d, 24d, 4d);
    private static final VoxelShape COL_SHORT_E = Block.box(12d, 0d, 4d, 16d, 24d, 12d);
    private static final VoxelShape COL_SHORT_S = Block.box(4d, 0d, 12d, 12d, 24d, 16d);
    private static final VoxelShape COL_SHORT_W = Block.box(0d, 0d, 4d, 4d, 24d, 12d);

    private static final VoxelShape COL_CORNER_NE = Shapes.or(COL_NONE, COL_SHORT_N, COL_SHORT_E);
    private static final VoxelShape COL_CORNER_NW = Shapes.or(COL_NONE, COL_SHORT_N, COL_SHORT_W);
    private static final VoxelShape COL_CORNER_SE = Shapes.or(COL_NONE, COL_SHORT_S, COL_SHORT_E);
    private static final VoxelShape COL_CORNER_SW = Shapes.or(COL_NONE, COL_SHORT_S, COL_SHORT_W);

    private static final VoxelShape COL_T_N = Shapes.or(COL_STRAIGHT_Z, COL_SHORT_N);
    private static final VoxelShape COL_T_E = Shapes.or(COL_STRAIGHT_X, COL_SHORT_E);
    private static final VoxelShape COL_T_S = Shapes.or(COL_STRAIGHT_Z, COL_SHORT_S);
    private static final VoxelShape COL_T_W = Shapes.or(COL_STRAIGHT_X, COL_SHORT_W);

    private static final VoxelShape COL_CROSS = Shapes.or(COL_STRAIGHT_X, COL_STRAIGHT_Z);

    public CompatHedge(String name, Block source) {
        super("hedge_" + name, source);
        registerDefaultState(defaultBlockState().setValue(WATERLOGGED, false).setValue(STATE, HedgeState.None));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(STATE, WATERLOGGED);
    }

    public VoxelShape getShape(BlockState state) {
        return switch (state.getValue(STATE)) {
            case None -> SHAPE_NONE;
            case Straight_X -> SHAPE_STRAIGHT_X;
            case Straight_Z -> SHAPE_STRAIGHT_Z;
            case Corner_NE -> SHAPE_CORNER_NE;
            case Corner_NW -> SHAPE_CORNER_NW;
            case Corner_SE -> SHAPE_CORNER_SE;
            case Corner_SW -> SHAPE_CORNER_SW;
            case TCross_N -> SHAPE_T_N;
            case TCross_E -> SHAPE_T_E;
            case TCross_S -> SHAPE_T_S;
            case TCross_W -> SHAPE_T_W;
            case Cross -> SHAPE_CROSS;
        };

    }

    @Override
    public @NotNull VoxelShape getCollisionShape(BlockState state, @NotNull BlockGetter worldIn, @NotNull BlockPos pos,
                                                 @NotNull CollisionContext context) {
        return switch (state.getValue(STATE)) {
            case None -> COL_NONE;
            case Straight_X -> COL_STRAIGHT_X;
            case Straight_Z -> COL_STRAIGHT_Z;
            case Corner_NE -> COL_CORNER_NE;
            case Corner_NW -> COL_CORNER_NW;
            case Corner_SE -> COL_CORNER_SE;
            case Corner_SW -> COL_CORNER_SW;
            case TCross_N -> COL_T_N;
            case TCross_E -> COL_T_E;
            case TCross_S -> COL_T_S;
            case TCross_W -> COL_T_W;
            case Cross -> COL_CROSS;
        };
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter worldIn, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return getShape(state);
    }

    @SuppressWarnings({"ConstantValue", "UnreachableCode"})
    public BlockState getState(BlockState state, BlockGetter worldIn, BlockPos pos) {
        BlockState bn = worldIn.getBlockState(pos.north());
        BlockState be = worldIn.getBlockState(pos.east());
        BlockState bs = worldIn.getBlockState(pos.south());
        BlockState bw = worldIn.getBlockState(pos.west());

        boolean n = bn.getBlock() instanceof Hedge;
        boolean e = be.getBlock() instanceof Hedge;
        boolean s = bs.getBlock() instanceof Hedge;
        boolean w = bw.getBlock() instanceof Hedge;

        if(n && e && s && w)
            return getNextState(state, HedgeState.Cross);

        if(!n && !e && !s && !w)
            return getNextState(state, HedgeState.None);

        else if(n && e && !s && w)
            return getNextState(state, HedgeState.TCross_N);
        else if(n && e && s && !w)
            return getNextState(state, HedgeState.TCross_E);
        else if(!n && e && s && w)
            return getNextState(state, HedgeState.TCross_S);
        else if(n && !e && s && w)
            return getNextState(state, HedgeState.TCross_W);

        else if(!e && !w && (n || s))
            return getNextState(state, HedgeState.Straight_X);
        else if(!n && !s && (e || w))
            return getNextState(state, HedgeState.Straight_Z);

        else if(n && e && !s && !w)
            return getNextState(state, HedgeState.Corner_NE);
        else if(n && !e && !s && w)
            return getNextState(state, HedgeState.Corner_NW);
        else if(!n && e && s && !w)
            return getNextState(state, HedgeState.Corner_SE);
        else if(!n && !e && s && w)
            return getNextState(state, HedgeState.Corner_SW);
        return this.defaultBlockState();
    }

    private BlockState getNextState(BlockState state, HedgeState shape) {
        return state.setValue(STATE, shape);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext c) {
        return getState(this.defaultBlockState(), c.getLevel(), c.getClickedPos()).setValue(WATERLOGGED,
                c.getLevel().getFluidState(c.getClickedPos()).getType() == Fluids.WATER);
    }

    @Override
    public @NotNull FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public boolean placeLiquid(@NotNull LevelAccessor world, @NotNull BlockPos pos, @NotNull BlockState state, @NotNull FluidState fluidStateIn) {
        return SimpleWaterloggedBlock.super.placeLiquid(world, pos, state, fluidStateIn);
    }

    @Override
    public boolean canPlaceLiquid(@NotNull BlockGetter world, @NotNull BlockPos pos, @NotNull BlockState state, @NotNull Fluid fluidIn) {
        return SimpleWaterloggedBlock.super.canPlaceLiquid(world, pos, state, fluidIn);
    }

    @Override
    public boolean isPathfindable(@NotNull BlockState state, @NotNull BlockGetter world, @NotNull BlockPos pos, PathComputationType type) {
        return switch (type) {
            case LAND, AIR -> false;
            case WATER -> world.getFluidState(pos).is(FluidTags.WATER);
        };
    }

    @Override
    public @NotNull BlockState updateShape(BlockState stateIn, @NotNull Direction facing, @NotNull BlockState facingState, @NotNull LevelAccessor worldIn,
                                           @NotNull BlockPos currentPos, @NotNull BlockPos facingPos) {
        if(stateIn.getValue(WATERLOGGED)) {
            worldIn.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
        }
        return getState(stateIn, worldIn, currentPos);
    }
}
