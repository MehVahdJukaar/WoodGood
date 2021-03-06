package net.mehvahdjukaar.every_compat.modules.quark;

import net.mehvahdjukaar.selene.block_set.wood.WoodType;
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

    private final WoodType woodType;

    public CompatChestBlock(WoodType woodType, String type, QuarkModule module, Properties props) {
        super(type, module, ()->net.mehvahdjukaar.every_compat.modules.quark.QuarkModule.CHEST_TILE, props);
        this.woodType = woodType;
    }

    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new CompatChestBlockTile(pos, state);
    }

    @Override
    public String getChestTexturePath() {
        return "model/chest/everycompat/" + woodType.getAppendableId() + "_";
    }


}
