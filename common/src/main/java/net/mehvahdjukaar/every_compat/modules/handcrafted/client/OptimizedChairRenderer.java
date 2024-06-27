package net.mehvahdjukaar.every_compat.modules.handcrafted.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import earth.terrarium.handcrafted.client.block.chair.chair.ChairModel;
import earth.terrarium.handcrafted.common.block.chair.chair.ChairBlockEntity;
import earth.terrarium.handcrafted.common.block.chair.couch.ExpandableCouchBlock;
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
public class OptimizedChairRenderer implements BlockEntityRenderer<ChairBlockEntity> {

    public static OptimizedChairRenderer INSTANCE = null;
    private final ChairModel model;
    protected final ModelPart base;
    protected final ModelPart seat;
    protected final ModelPart chair;
    protected final ModelPart withCushion;

    public OptimizedChairRenderer(BlockEntityRendererProvider.Context ctx) {
        this.model = new ChairModel(ctx.getModelSet().bakeLayer(ChairModel.LAYER_LOCATION));
        base = model.getMain().getChild("base");
        seat = model.getMain().getChild("seat");
        chair = model.getMain().getChild("chair");
        withCushion = model.getMain().getChild("with_cushion");
        INSTANCE = this;
    }

    @Override
    public void render(ChairBlockEntity entity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource,
                       int packedLight, int packedOverlay) {
        Item block = entity.getBlockState().getBlock().asItem();
        Item cushion = entity.getStack().getItem();
        doRender(entity.getBlockState().getValue(ExpandableCouchBlock.FACING), poseStack,
                bufferSource, packedLight, packedOverlay, block, cushion);
    }

    public void doRender(Direction direction, PoseStack poseStack,
                         MultiBufferSource buffer, int packedLight, int packedOverlay, Item block, Item cushion) {

        poseStack.pushPose();
        poseStack.translate(0.5, 1.5, 0.5);
        poseStack.mulPose(Vector3f.YN.rotationDegrees(direction.getOpposite().toYRot()));
        poseStack.mulPose(Vector3f.XP.rotationDegrees(180.0F));
        withCushion.visible = false;
        seat.visible = false;
        base.visible = true;
        chair.visible = true;

        var blockTexture = OptimizedTableRenderer.OBJECT_TO_TEXTURE.get(block); // COMPAT
        VertexConsumer vertex = blockTexture.buffer(buffer, RenderType::entityCutout); // COMPAT

        model.renderToBuffer(poseStack, vertex, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        base.visible = false;
        seat.visible = true;
        chair.visible = false;
        withCushion.visible = true;
        poseStack.scale(0.999F, 1.0F, 1.0F);
        model.renderToBuffer(poseStack, vertex, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        poseStack.scale(1.001F, 1.0F, 1.0F);
        if (cushion != Items.AIR) {
            var cushionTexture = OptimizedTableRenderer.OBJECT_TO_TEXTURE.get(cushion); // COMPAT
            model.renderToBuffer(poseStack, cushionTexture.buffer(buffer, RenderType::entityCutout), packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        }
        poseStack.popPose();
    }

}
