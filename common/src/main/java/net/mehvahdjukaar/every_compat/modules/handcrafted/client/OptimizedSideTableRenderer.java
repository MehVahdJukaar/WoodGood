package net.mehvahdjukaar.every_compat.modules.handcrafted.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import earth.terrarium.handcrafted.client.block.table.side_table.SideTableModel;
import earth.terrarium.handcrafted.common.block.table.sidetable.SideTableBlock;
import earth.terrarium.handcrafted.common.block.table.sidetable.SideTableBlockEntity;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class OptimizedSideTableRenderer implements BlockEntityRenderer<SideTableBlockEntity> {
    public static OptimizedSideTableRenderer INSTANCE = null;
    private final SideTableModel model;
    private final ModelPart table;
    private final ModelPart overlayMain;

    public OptimizedSideTableRenderer(BlockEntityRendererProvider.Context ctx) {
        this.model = new SideTableModel(ctx.getModelSet().bakeLayer(SideTableModel.LAYER_LOCATION));
        this.table = model.getMain().getChild("table");
        this.overlayMain = model.getMain().getChild("overlay");
        INSTANCE = this;
    }

    @Override
    public void render(SideTableBlockEntity entity, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        Item block = entity.getBlockState().getBlock().asItem();
        Item sheet = entity.getStack().getItem();

        doRender(entity.getBlockState().getValue(SideTableBlock.FACING), poseStack, bufferSource, packedLight, packedOverlay, sheet, block);
    }

    public void doRender(Direction direction, PoseStack poseStack, MultiBufferSource buffer,
                         int packedLight, int packedOverlay, Item sheet, Item block) {
        poseStack.pushPose();

        poseStack.translate(0.5, 1.5, 0.5);
        poseStack.mulPose(Vector3f.YN.rotationDegrees(direction.getCounterClockWise().toYRot()));
        poseStack.mulPose(Vector3f.XP.rotationDegrees(180));

        var texture = OptimizedTableRenderer.OBJECT_TO_TEXTURE.get(block);

        overlayMain.visible = false;
        table.visible = true;
        model.renderToBuffer(poseStack, texture.buffer(buffer, RenderType::entityCutout), packedLight, packedOverlay,
                1.0f, 1.0f, 1.0f, 1.0f);

        if (sheet != Items.AIR) {
            overlayMain.visible = true;
            table.visible = false;
            var sheetTexture = OptimizedTableRenderer.OBJECT_TO_TEXTURE.get(sheet);
            if (sheetTexture != null)
                model.renderToBuffer(poseStack, sheetTexture.buffer(buffer, RenderType::entityCutout),
                        packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        }
        poseStack.popPose();
    }
}
