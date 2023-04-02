package net.mehvahdjukaar.every_compat.modules.forge.quark;

import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import vazkii.quark.base.block.IQuarkBlock;
import vazkii.quark.base.handler.RenderLayerHandler;
import vazkii.quark.base.module.QuarkModule;
import vazkii.quark.content.building.block.HollowPillarBlock;

@Deprecated(forRemoval = true)
public class CompatHollowLogBlock extends HollowPillarBlock {
    private final boolean flammable;


    public CompatHollowLogBlock(String name, Block sourceLog, QuarkModule module, boolean flammable) {
        super(name, module, CreativeModeTab.TAB_DECORATIONS, Utils.copyPropertySafe(sourceLog).isSuffocating((s, g, p) -> false));
        this.flammable = flammable;
        RenderLayerHandler.setRenderType(this, RenderLayerHandler.RenderTypeSkeleton.CUTOUT_MIPPED);
    }

    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return this.flammable;
    }
}
