package net.mehvahdjukaar.every_compat.misc;

import com.mojang.blaze3d.vertex.PoseStack;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class DuplicateItemRenderer extends BlockEntityWithoutLevelRenderer {

    private final List<ItemStack> DISPLAY_ITEMS = new ArrayList<>();

    public DuplicateItemRenderer(BlockEntityRenderDispatcher pBlockEntityRenderDispatcher, EntityModelSet pEntityModelSet) {
        super(pBlockEntityRenderDispatcher, pEntityModelSet);
        for (var c : WoodType.OAK_WOOD_TYPE.getChildren()) {
            if (c.getKey().contains("/")) {
                DISPLAY_ITEMS.add(c.getValue().asItem().getDefaultInstance());
            }
        }
    }

    @Override
    public void renderByItem(ItemStack stack, ItemTransforms.TransformType transformType,
                             PoseStack matrixStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {

        ItemStack item = getAnyItem();

        matrixStack.pushPose();
        matrixStack.translate(0.5D, 0.5D, 0.5D);
        Minecraft.getInstance().getItemRenderer().renderStatic(item, transformType, combinedLight, combinedOverlay, matrixStack, buffer, 0);
        matrixStack.popPose();

    }

    public ItemStack getAnyItem() {
        int size = DISPLAY_ITEMS.size();
        int time = (int) (Util.getMillis() / 625L);
        return DISPLAY_ITEMS.get(time % size);
    }
}
