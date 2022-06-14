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
import net.minecraftforge.client.IItemRenderProperties;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public class CompatChestItem extends BlockItem {

    private final boolean trapped;
    public CompatChestItem(Block block, net.minecraft.world.item.Item.Properties props, boolean trapped) {
        super(block, props);
        this.trapped = trapped;
    }

    @OnlyIn(Dist.CLIENT)
    public void initializeClient(Consumer<IItemRenderProperties> consumer) {
        consumer.accept(new IItemRenderProperties() {
            public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
                final Minecraft mc = Minecraft.getInstance();
                return new BlockEntityWithoutLevelRenderer(mc.getBlockEntityRenderDispatcher(), mc.getEntityModels()) {
                    private final BlockEntity tile;

                    {
                        this.tile = trapped ? new CompatTrappedChestBlockTile(BlockPos.ZERO, CompatChestItem.this.getBlock().defaultBlockState()) :
                                new CompatChestBlockTile(BlockPos.ZERO, CompatChestItem.this.getBlock().defaultBlockState());
                    }

                    public void renderByItem(@Nonnull ItemStack stack, @Nonnull ItemTransforms.TransformType transformType, @Nonnull PoseStack pose, @Nonnull MultiBufferSource buffer, int x, int y) {
                        mc.getBlockEntityRenderDispatcher().renderItem(this.tile, pose, buffer, x, y);
                    }
                };
            }
        });
    }
}