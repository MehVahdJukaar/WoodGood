package net.mehvahdjukaar.every_compat.modules.quark;

import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.violetmoon.quark.content.building.block.VariantTrappedChestBlock;
import org.violetmoon.quark.content.building.client.render.be.VariantChestRenderer;
import org.violetmoon.quark.content.building.module.VariantChestsModule;

public class CompatTrappedChestBlock extends VariantTrappedChestBlock {

    public final WoodType woodType;

    public CompatTrappedChestBlock(WoodType woodType, String type, BlockBehaviour.Properties props) {
        super(type,null, () -> QuarkModule.TRAPPED_CHEST_TILE, props);
        this.woodType = woodType;
    }

    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new CompatTrappedChestBlockTile(pos, state);
    }

    public String getTextureFolder() {
        return "model/chest/everycompat";
    }

    public String getTexturePath(){
        return  woodType.getAppendableId();
    }
}
