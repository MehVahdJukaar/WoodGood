package net.mehvahdjukaar.every_compat.modules.quark;

import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.violetmoon.quark.content.building.block.VariantChestBlock;
import org.violetmoon.quark.content.building.client.render.be.VariantChestRenderer;

public class CompatChestBlock extends VariantChestBlock {

    public final WoodType woodType;
    private final String texture;

    public CompatChestBlock(WoodType woodType, String type,  BlockBehaviour.Properties props) {
        super(type, null, () -> QuarkModule.CHEST_TILE, props);
        this.woodType = woodType;
        this.texture = woodType.getAppendableId();
    }

    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new CompatChestBlockTile(pos, state);
    }

    public String getTextureFolder() {
        return "quark_variant_chests/everycomp";
    }

    public String getTexturePath(){
        return texture;
    }
}
