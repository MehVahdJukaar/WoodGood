package net.mehvahdjukaar.every_compat.modules.iron_age_furniture;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.mcmoddev.ironagefurniture.api.Enumerations.BenchType;
import com.mcmoddev.ironagefurniture.api.entity.Seat;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CompatBench extends CompatBackBench {
    public CompatBench(float hardness, float blastResistance, SoundType sound, String name) {
        super(hardness, blastResistance, sound, name);
    }

    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult rayTraceResult) {
        return Seat.create(world, pos, 0.2, player);
    }

    protected void generateShapes(ImmutableList<BlockState> states) {
        ImmutableMap.Builder<BlockState, VoxelShape> builder = new ImmutableMap.Builder<>();

        for (BlockState state : states) {
            BenchType type = state.getValue(TYPE);
            VoxelShape shapes = Shapes.empty();
            shapes = Shapes.joinUnoptimized(shapes, getShapes(rotate(Block.box(0.0, 6.0, 1.0, 16.0, 7.0, 15.0), Direction.SOUTH))[state.getValue(DIRECTION).get2DDataValue()], BooleanOp.OR);
            switch (type) {
                case SINGLE:
                    shapes = Shapes.joinUnoptimized(shapes, getShapes(rotate(Block.box(2.0, 0.0, 4.0, 3.0, 7.0, 12.0), Direction.SOUTH))[state.getValue(DIRECTION).get2DDataValue()], BooleanOp.OR);
                    shapes = Shapes.joinUnoptimized(shapes, getShapes(rotate(Block.box(13.0, 0.0, 4.0, 14.0, 7.0, 12.0), Direction.SOUTH))[state.getValue(DIRECTION).get2DDataValue()], BooleanOp.OR);
                    break;
                case LEFT:
                    shapes = Shapes.joinUnoptimized(shapes, getShapes(rotate(Block.box(2.0, 0.0, 4.0, 3.0, 7.0, 12.0), Direction.SOUTH))[state.getValue(DIRECTION).get2DDataValue()], BooleanOp.OR);
                    break;
                case RIGHT:
                    shapes = Shapes.joinUnoptimized(shapes, getShapes(rotate(Block.box(13.0, 0.0, 4.0, 14.0, 7.0, 12.0), Direction.SOUTH))[state.getValue(DIRECTION).get2DDataValue()], BooleanOp.OR);
                case MIDDLE:
            }

            shapes = Shapes.joinUnoptimized(shapes, getShapes(rotate(Block.box(0.0, 2.0, 7.0, 16.0, 4.0, 9.0), Direction.SOUTH))[state.getValue(DIRECTION).get2DDataValue()], BooleanOp.OR);
            builder.put(state, shapes.optimize());
        }

        this._shapes = builder.build();
    }
}
