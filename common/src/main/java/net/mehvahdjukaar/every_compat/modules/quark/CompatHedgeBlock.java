package net.mehvahdjukaar.every_compat.modules.quark;

import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;
import vazkii.quark.base.module.QuarkModule;
import vazkii.quark.content.building.block.HedgeBlock;
import vazkii.quark.content.building.module.HedgesModule;

import javax.annotation.Nonnull;
import java.util.function.BooleanSupplier;

//we have to do this. using reflections hacks is causing issues

/**
 * Credits to Vazkii and Quark
 */
public class CompatHedgeBlock extends FenceBlock  { //implements IQuarkBlock
    private final QuarkModule module;
    private final Block leaf;
    private BooleanSupplier enabledSupplier = () -> true;
    public static final BooleanProperty EXTEND = HedgeBlock.EXTEND;

    public CompatHedgeBlock(QuarkModule module, Block fence, Block leaf) {
        super(Properties.copy(fence));
        this.module = module;
        this.leaf = leaf;
        this.registerDefaultState(this.defaultBlockState().setValue(EXTEND, false));
        //this.setRegistryName(id);

    }

    public boolean connectsTo(BlockState state, boolean isSideSolid, @Nonnull Direction direction) {
        return state.is(HedgesModule.hedgesTag);
    }

    public boolean canSustainPlant(@Nonnull BlockState state, @Nonnull BlockGetter world, @Nonnull BlockPos pos, @Nonnull Direction facing, @Nonnull IPlantable plantable) {
        return facing == Direction.UP && !(Boolean) state.getValue(WATERLOGGED) && plantable.getPlantType(world, pos) == PlantType.PLAINS;
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockGetter blockader = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        BlockPos down = blockpos.below();
        BlockState downState = blockader.getBlockState(down);
        return super.getStateForPlacement(context).setValue(EXTEND, downState.getBlock() instanceof HedgeBlock);
    }

    @Nonnull
    public BlockState updateShape(BlockState stateIn, @Nonnull Direction facing, @Nonnull BlockState facingState, @Nonnull LevelAccessor worldIn, @Nonnull BlockPos currentPos, @Nonnull BlockPos facingPos) {
        if (stateIn.getValue(WATERLOGGED)) {
            worldIn.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
        }

        return facing == Direction.DOWN ? stateIn.setValue(EXTEND, facingState.getBlock() instanceof HedgeBlock) : super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    protected void createBlockStateDefinition(@Nonnull StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(EXTEND);
    }

    @OnlyIn(Dist.CLIENT)
    public BlockColor getBlockColor(BlockColors colors) {
        BlockState leafState = this.leaf.defaultBlockState();
        return (state, world, pos, tintIndex) -> colors.getColor(leafState, world, pos, tintIndex);
    }

    @OnlyIn(Dist.CLIENT)
    public ItemColor getItemColor(ItemColors colors) {
        ItemStack leafStack = new ItemStack(this.leaf);
        return (stack, tintIndex) -> colors.getColor(leafStack, tintIndex);
    }

    private boolean isEnabled() {return true;
    }

    public QuarkModule getModule() {
        return this.module;
    }

    public CompatHedgeBlock setCondition(BooleanSupplier enabledSupplier) {
        this.enabledSupplier = enabledSupplier;
        return this;
    }

    public boolean doesConditionApply() {
        return this.enabledSupplier.getAsBoolean();
    }
}
