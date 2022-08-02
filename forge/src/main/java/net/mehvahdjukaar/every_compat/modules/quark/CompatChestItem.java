package net.mehvahdjukaar.every_compat.modules.quark;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;

public class CompatChestItem extends BlockItem {

    private final boolean trapped;

    public CompatChestItem(Block block, Properties props, boolean trapped) {
        super(block, props);
        this.trapped = trapped;
    }


    @OnlyIn(Dist.CLIENT)
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
                final Minecraft mc = Minecraft.getInstance();
                return new BlockEntityWithoutLevelRenderer(mc.getBlockEntityRenderDispatcher(), mc.getEntityModels()) {
                    private final BlockEntity tile;

                    {
                        this.tile = trapped ? new CompatTrappedChestBlockTile(BlockPos.ZERO, CompatChestItem.this.getBlock().defaultBlockState()) :
                                new CompatChestBlockTile(BlockPos.ZERO, CompatChestItem.this.getBlock().defaultBlockState());
                    }

                    @Override
                    public void renderByItem(ItemStack stack, ItemTransforms.TransformType transformType, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
                        mc.getBlockEntityRenderDispatcher().renderItem(this.tile, poseStack, buffer, packedLight, packedOverlay);
                    }
                };
            }
        });
    }
}