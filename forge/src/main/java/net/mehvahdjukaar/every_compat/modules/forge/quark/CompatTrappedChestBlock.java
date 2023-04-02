package net.mehvahdjukaar.every_compat.modules.forge.quark;

import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import vazkii.quark.content.building.block.VariantTrappedChestBlock;

public class CompatTrappedChestBlock extends VariantTrappedChestBlock {

    private final WoodType woodType;

    public CompatTrappedChestBlock(WoodType woodType, String type, vazkii.quark.base.module.QuarkModule module, Properties props) {
        super(type, module, () -> QuarkModule.trappedChestTile, props);
        this.woodType = woodType;
    }

    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new CompatTrappedChestBlockTile(pos, state);
    }

    @Override
    public String getChestTexturePath() {
        return "model/chest/everycompat/" + woodType.getAppendableId() + "_";
    }


}
