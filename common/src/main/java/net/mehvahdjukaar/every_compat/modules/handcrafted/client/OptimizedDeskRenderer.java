package net.mehvahdjukaar.every_compat.modules.handcrafted.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import earth.terrarium.handcrafted.client.block.table.desk.DeskModel;
import earth.terrarium.handcrafted.common.block.table.desk.DeskBlock;
import earth.terrarium.handcrafted.common.block.table.desk.DeskBlockEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

@Environment(EnvType.CLIENT)
public class OptimizedDeskRenderer implements BlockEntityRenderer<DeskBlockEntity> {
    public static OptimizedDeskRenderer INSTANCE = null;
    private final DeskModel model;
    private final ModelPart table;
    private final ModelPart overlayMain;

    public OptimizedDeskRenderer(BlockEntityRendererProvider.Context ctx) {
        this.model = new DeskModel(ctx.getModelSet().bakeLayer(DeskModel.LAYER_LOCATION));
        this.table = model.getMain().getChild("table");
        this.overlayMain = model.getMain().getChild("overlay");
        INSTANCE = this;
    }

    @Override
    public void render(DeskBlockEntity entity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        Item block = entity.getBlockState().getBlock().asItem();
        Item sheet = entity.getStack().getItem();

        doRender(entity.getBlockState().getValue(DeskBlock.FACING), poseStack, bufferSource, packedLight, packedOverlay, sheet, block);
    }

    public void doRender(Direction direction, PoseStack poseStack, MultiBufferSource buffer,
                          int packedLight, int packedOverlay, Item sheet, Item block) {
        poseStack.pushPose();

        poseStack.translate(0.5, 1.5, 0.5);
        poseStack.mulPose(Vector3f.YN.rotationDegrees(direction.toYRot()));
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
                model.renderToBuffer(poseStack,sheetTexture.buffer(buffer, RenderType::entityCutout),
                        packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        }
        poseStack.popPose();
    }
}
