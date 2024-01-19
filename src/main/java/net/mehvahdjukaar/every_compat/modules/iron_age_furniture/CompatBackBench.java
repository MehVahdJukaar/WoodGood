package net.mehvahdjukaar.every_compat.modules.iron_age_furniture;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.UnmodifiableIterator;
import com.mcmoddev.ironagefurniture.api.Blocks.Chair;
import com.mcmoddev.ironagefurniture.api.Enumerations.BenchType;
import com.mcmoddev.ironagefurniture.api.Enumerations.Rotation;
import com.mcmoddev.ironagefurniture.api.properties.BenchTypeProperty;
import com.mcmoddev.ironagefurniture.api.util.Swivel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CompatBackBench extends Chair {
    public static final BenchTypeProperty TYPE;

    public CompatBackBench(float hardness, float blastResistance, SoundType sound, String name) {
        super(hardness, blastResistance, sound, name);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(new Property[]{TYPE});
    }

    protected void generateShapes(ImmutableList<BlockState> states) {
        ImmutableMap.Builder<BlockState, VoxelShape> builder = new ImmutableMap.Builder();
        UnmodifiableIterator var3 = states.iterator();

        while(var3.hasNext()) {
            BlockState state = (BlockState)var3.next();
            BenchType type = (BenchType)state.getValue(TYPE);
            VoxelShape shapes = Shapes.empty();
            shapes = Shapes.joinUnoptimized(shapes, getShapes(rotate(Block.box(0.0, 6.0, 1.0, 16.0, 7.0, 15.0), Direction.SOUTH))[((Direction)state.getValue(DIRECTION)).get2DDataValue()], BooleanOp.OR);
            shapes = Shapes.joinUnoptimized(shapes, getShapes(rotate(Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 1.0), Direction.SOUTH))[((Direction)state.getValue(DIRECTION)).get2DDataValue()], BooleanOp.OR);
            switch (type) {
                case SINGLE:
                    shapes = Shapes.joinUnoptimized(shapes, getShapes(rotate(Block.box(0.0, 0.0, 1.0, 1.0, 10.0, 12.0), Direction.SOUTH))[((Direction)state.getValue(DIRECTION)).get2DDataValue()], BooleanOp.OR);
                    shapes = Shapes.joinUnoptimized(shapes, getShapes(rotate(Block.box(15.0, 0.0, 1.0, 16.0, 10.0, 12.0), Direction.SOUTH))[((Direction)state.getValue(DIRECTION)).get2DDataValue()], BooleanOp.OR);
                    break;
                case LEFT:
                    shapes = Shapes.joinUnoptimized(shapes, getShapes(rotate(Block.box(0.0, 0.0, 1.0, 1.0, 10.0, 12.0), Direction.SOUTH))[((Direction)state.getValue(DIRECTION)).get2DDataValue()], BooleanOp.OR);
                    break;
                case RIGHT:
                    shapes = Shapes.joinUnoptimized(shapes, getShapes(rotate(Block.box(15.0, 0.0, 1.0, 16.0, 10.0, 12.0), Direction.SOUTH))[((Direction)state.getValue(DIRECTION)).get2DDataValue()], BooleanOp.OR);
                case MIDDLE:
            }

            shapes = Shapes.joinUnoptimized(shapes, getShapes(rotate(Block.box(0.0, 2.0, 7.0, 16.0, 4.0, 9.0), Direction.SOUTH))[((Direction)state.getValue(DIRECTION)).get2DDataValue()], BooleanOp.OR);
            builder.put(state, shapes.optimize());
        }

        this._shapes = builder.build();
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Level world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState stateForPlacement = (BlockState)((BlockState)((BlockState)this.defaultBlockState().setValue(WATERLOGGED, world.getFluidState(context.getClickedPos()).getType() == Fluids.WATER)).setValue(DIRECTION, context.getHorizontalDirection())).setValue(TYPE, BenchType.SINGLE);
        if (!context.getPlayer().isShiftKeyDown()) {
            Direction benchAxis = this.getBenchToJoinTo(context.getHorizontalDirection(), world, pos);
            if (benchAxis != null) {
                BlockState blockStateToJoinTo = world.getBlockState(pos.relative(benchAxis));
                if (blockStateToJoinTo.getBlock().getRegistryName().equals(stateForPlacement.getBlock().getRegistryName())) {
                    Direction benchFacing = this.getBenchToJoinToFacing(benchAxis, world, pos);
                    boolean defaultFacing = true;
                    if (this.getBenchType(blockStateToJoinTo) == BenchType.SINGLE) {
                        if (benchFacing == Direction.NORTH && benchAxis == Direction.EAST) {
                            benchFacing = Swivel.Rotate(benchAxis, Rotation.TwoSeventy);
                            defaultFacing = false;
                        }

                        if (benchFacing == Direction.EAST && benchAxis == Direction.SOUTH) {
                            benchFacing = Swivel.Rotate(benchAxis, Rotation.TwoSeventy);
                            defaultFacing = false;
                        }

                        if (benchFacing == Direction.SOUTH && benchAxis == Direction.WEST) {
                            benchFacing = Swivel.Rotate(benchAxis, Rotation.TwoSeventy);
                            defaultFacing = false;
                        }

                        if (benchFacing == Direction.WEST && benchAxis == Direction.NORTH) {
                            benchFacing = Swivel.Rotate(benchAxis, Rotation.TwoSeventy);
                            defaultFacing = false;
                        }

                        if (defaultFacing) {
                            benchFacing = Swivel.Rotate(benchAxis, Rotation.Ninty);
                        }
                    }

                    stateForPlacement = this.traceBench2(benchAxis, world, pos, stateForPlacement, benchFacing);
                }
            }
        }

        return stateForPlacement;
    }

    private boolean isIAFBench(BlockState blockstate) {
        ResourceLocation resource = blockstate.getBlock().getRegistryName();
        return resource.getNamespace().equals("everycomp") && resource.getPath().contains("bench");
    }

    protected BenchType getBenchType(BlockState blockstate) {
        if (blockstate == null) {
            return null;
        } else {
            return this.isIAFBench(blockstate) ? (BenchType)blockstate.getValue(TYPE) : null;
        }
    }

    private Direction getBenchDirection(BlockState blockstate) {
        return this.isIAFBench(blockstate) ? (Direction)blockstate.getValue(DIRECTION) : null;
    }

    private int getOffset(Direction direction, LevelAccessor world, BlockPos pos) {
        int offset = 0;
        BlockState currentlyInspectedBenchState = world.getBlockState(pos.relative(direction));
        BenchType currentlyInspectedBenchType = this.getBenchType(currentlyInspectedBenchState);
        if (this.isBenchPiece(currentlyInspectedBenchType)) {
            Direction blockFacing = Swivel.Rotate(this.getBenchDirection(currentlyInspectedBenchState), Rotation.Ninty);
            String blockName = currentlyInspectedBenchState.getBlock().getRegistryName().getNamespace();
            String currentlyInspectedBlockName = blockName;

            while(this.isBenchPieceOnAxis(currentlyInspectedBenchType, direction, blockFacing, blockName, currentlyInspectedBlockName)) {
                ++offset;
                currentlyInspectedBenchState = world.getBlockState(pos.relative(direction, offset + 1));
                currentlyInspectedBenchType = this.getBenchType(currentlyInspectedBenchState);
                currentlyInspectedBlockName = currentlyInspectedBenchState.getBlock().getRegistryName().getNamespace();
                if (this.isBenchPiece(currentlyInspectedBenchType)) {
                    blockFacing = Swivel.Rotate(this.getBenchDirection(currentlyInspectedBenchState), Rotation.Ninty);
                }
            }
        }

        return offset;
    }

    private BlockState traceBench2(Direction direction, LevelAccessor world, BlockPos pos, BlockState blockState, Direction benchFacing) {
        boolean invertLeftRight = false;
        if (benchFacing == Direction.NORTH && direction == Direction.EAST) {
            invertLeftRight = true;
        }

        if (benchFacing == Direction.EAST && direction == Direction.SOUTH) {
            invertLeftRight = true;
        }

        if (benchFacing == Direction.SOUTH && direction == Direction.WEST) {
            invertLeftRight = true;
        }

        if (benchFacing == Direction.WEST && direction == Direction.NORTH) {
            invertLeftRight = true;
        }

        BenchType left = BenchType.LEFT;
        BenchType right = BenchType.RIGHT;
        if (invertLeftRight) {
            left = BenchType.RIGHT;
            right = BenchType.LEFT;
        }

        int positiveOffset = this.getOffset(direction.getOpposite(), world, pos);
        int negativeOffset = this.getOffset(direction, world, pos);
        int workingPositiveOffset = positiveOffset;
        int workingNegativeOffset = negativeOffset;
        if (positiveOffset == 0 && negativeOffset == 0) {
            return (BlockState)((BlockState)blockState.setValue(DIRECTION, benchFacing)).setValue(TYPE, BenchType.SINGLE);
        } else {
            BlockPos workingBlockPos;
            for(; workingNegativeOffset > 0; --workingNegativeOffset) {
                workingBlockPos = pos.relative(direction, workingNegativeOffset);
                if (workingNegativeOffset == negativeOffset) {
                    world.setBlock(workingBlockPos, (BlockState)((BlockState)world.getBlockState(workingBlockPos).setValue(DIRECTION, benchFacing)).setValue(TYPE, left), 0);
                } else {
                    world.setBlock(workingBlockPos, (BlockState)((BlockState)world.getBlockState(workingBlockPos).setValue(DIRECTION, benchFacing)).setValue(TYPE, BenchType.MIDDLE), 0);
                }
            }

            if (negativeOffset > 0 && positiveOffset == 0) {
                return (BlockState)((BlockState)blockState.setValue(DIRECTION, benchFacing)).setValue(TYPE, right);
            } else {
                for(; workingPositiveOffset > 0; --workingPositiveOffset) {
                    workingBlockPos = pos.relative(direction.getOpposite(), workingPositiveOffset);
                    if (workingPositiveOffset == positiveOffset) {
                        world.setBlock(workingBlockPos, (BlockState)((BlockState)world.getBlockState(workingBlockPos).setValue(DIRECTION, benchFacing)).setValue(TYPE, right), 0);
                    } else {
                        world.setBlock(workingBlockPos, (BlockState)((BlockState)world.getBlockState(workingBlockPos).setValue(DIRECTION, benchFacing)).setValue(TYPE, BenchType.MIDDLE), 0);
                    }
                }

                if (positiveOffset > 0 && negativeOffset == 0) {
                    return (BlockState)((BlockState)blockState.setValue(DIRECTION, benchFacing)).setValue(TYPE, left);
                } else {
                    return (BlockState)((BlockState)blockState.setValue(DIRECTION, benchFacing)).setValue(TYPE, BenchType.MIDDLE);
                }
            }
        }
    }

    private boolean isBenchPiece(BenchType benchType) {
        return benchType == BenchType.SINGLE || benchType == BenchType.MIDDLE || benchType == BenchType.LEFT || benchType == BenchType.RIGHT;
    }

    private boolean isBenchPieceOnAxis(BenchType benchType, Direction benchAxis, Direction blockAxis, String blockName, String currentBlockName) {
        if (!blockName.equals(currentBlockName)) {
            return false;
        } else if (benchType == BenchType.SINGLE) {
            return true;
        } else {
            return (benchType == BenchType.MIDDLE || benchType == BenchType.LEFT || benchType == BenchType.RIGHT) && (benchAxis == blockAxis || benchAxis == blockAxis.getOpposite());
        }
    }

    private Rotation GetOpposite(Rotation rotation) {
        switch (rotation) {
            case Ninty:
                return Rotation.TwoSeventy;
            case OneEighty:
                return Rotation.Zero;
            case TwoSeventy:
                return Rotation.Ninty;
            case Zero:
                return Rotation.OneEighty;
            default:
                return Rotation.Zero;
        }
    }

    private boolean isBenchEnd(Direction facing, Level world, BlockPos pos) {
        BenchType benchType = this.getBenchType(world.getBlockState(pos.relative(facing)));
        return benchType == BenchType.LEFT || benchType == BenchType.RIGHT;
    }

    private boolean isBenchSingle(Direction facing, Level world, BlockPos pos) {
        BenchType benchType = this.getBenchType(world.getBlockState(pos.relative(facing)));
        return benchType == BenchType.SINGLE;
    }

    private Direction getBenchToJoinToFacing(Direction benchDirection, Level world, BlockPos pos) {
        return this.getBenchDirection(world.getBlockState(pos.relative(benchDirection)));
    }

    private Direction getBenchToJoinTo(Direction playerFacing, Level world, BlockPos pos) {
        if (this.isBenchEnd(playerFacing, world, pos) || this.isBenchSingle(playerFacing, world, pos)) {
            if (this.isBenchSingle(playerFacing, world, pos)) {
                return playerFacing;
            }

            Direction targetFace = this.getBenchDirection(world.getBlockState(pos.relative(playerFacing)));
            if (targetFace == null) {
                return null;
            }

            targetFace = Swivel.Rotate(targetFace, Rotation.Ninty);
            if (this.isBenchEnd(playerFacing, world, pos) && (targetFace == playerFacing || targetFace == playerFacing.getOpposite())) {
                return playerFacing;
            }
        }

        Direction[] var9 = Direction.values();
        int var5 = var9.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            Direction face = var9[var6];
            if (face != Direction.UP && face != Direction.DOWN) {
                if (this.isBenchSingle(face, world, pos)) {
                    return face;
                }

                if (this.isBenchEnd(face, world, pos)) {
                    Direction targetFace = this.getBenchDirection(world.getBlockState(pos.relative(face)));
                    if (targetFace == null) {
                        return null;
                    }

                    targetFace = Swivel.Rotate(targetFace, Rotation.Ninty);
                    if (targetFace == face || targetFace == face.getOpposite()) {
                        return face;
                    }
                }
            }
        }

        return null;
    }

    public void destroy(LevelAccessor worldIn, BlockPos pos, BlockState state) {
        Direction benchFacing = this.getBenchDirection(state);
        Rotation defaultRotation = Rotation.Ninty;
        Direction benchAxis = Swivel.Rotate(this.getBenchDirection(state), defaultRotation);
        BenchType benchType = this.getBenchType(state);
        super.destroy(worldIn, pos, state);
        if (benchAxis != null && benchType != null && benchType != BenchType.SINGLE) {
            BlockState currentOffsetBlockState = worldIn.getBlockState(pos.relative(benchAxis));
            BenchType currentOffsetType = this.getBenchType(currentOffsetBlockState);
            if (currentOffsetType != null) {
                Direction currentOffsetFacing = Swivel.Rotate(this.getBenchDirection(currentOffsetBlockState), defaultRotation);
                if (currentOffsetFacing == benchAxis || currentOffsetFacing == benchAxis.getOpposite()) {
                    BlockState offsetBlockState = this.traceBench2(benchAxis, worldIn, pos.relative(benchAxis), worldIn.getBlockState(pos.relative(benchAxis)), benchFacing);
                    if (offsetBlockState != null && this.isBenchPiece(this.getBenchType(offsetBlockState))) {
                        worldIn.setBlock(pos.relative(benchAxis), offsetBlockState, 0);
                    }
                }
            }

            BlockState reverseCurrentOffsetBlockState = worldIn.getBlockState(pos.relative(benchAxis.getOpposite()));
            BenchType reverseOffsetType = this.getBenchType(reverseCurrentOffsetBlockState);
            if (reverseOffsetType != null) {
                Direction reverseOffsetFacing = Swivel.Rotate(this.getBenchDirection(reverseCurrentOffsetBlockState), this.GetOpposite(defaultRotation));
                if (reverseOffsetFacing == benchAxis || reverseOffsetFacing == benchAxis.getOpposite()) {
                    BlockState reverseOffsetBlockState = this.traceBench2(benchAxis.getOpposite(), worldIn, pos.relative(benchAxis.getOpposite()), worldIn.getBlockState(pos.relative(benchAxis.getOpposite())), benchFacing);
                    if (reverseOffsetBlockState != null && this.isBenchPiece(this.getBenchType(reverseOffsetBlockState))) {
                        worldIn.setBlock(pos.relative(benchAxis.getOpposite()), reverseOffsetBlockState, 0);
                    }
                }
            }
        }

    }

    static {
        TYPE = BenchTypeProperty.create("type", new BenchType[]{BenchType.SINGLE, BenchType.LEFT, BenchType.MIDDLE, BenchType.RIGHT});
    }
}
