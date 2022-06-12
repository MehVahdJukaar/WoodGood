package net.mehvahdjukaar.every_compat.modules.quark;

import com.mojang.blaze3d.vertex.PoseStack;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.IItemRenderProperties;
import org.jetbrains.annotations.NotNull;
import vazkii.quark.base.module.QuarkModule;
import vazkii.quark.content.building.block.VariantChestBlock;

import javax.annotation.Nonnull;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class CompatChestBlock extends VariantChestBlock {

    private final WoodType woodType;

    public CompatChestBlock(WoodType woodType, String type, QuarkModule module, Supplier<BlockEntityType<? extends ChestBlockEntity>> supplier, Properties props) {
        super(type, module, supplier, props);
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
