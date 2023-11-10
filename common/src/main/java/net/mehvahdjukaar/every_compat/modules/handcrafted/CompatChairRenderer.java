package net.mehvahdjukaar.every_compat.modules.handcrafted;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import com.teamresourceful.resourcefullib.client.CloseablePoseStack;
import earth.terrarium.handcrafted.client.block.chair.chair.ChairModel;
import earth.terrarium.handcrafted.common.block.chair.chair.ChairBlockEntity;
import earth.terrarium.handcrafted.common.block.chair.couch.ExpandableCouchBlock;
import earth.terrarium.handcrafted.common.block.property.CouchShape;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class CompatChairRenderer implements BlockEntityRenderer<ChairBlockEntity> {
    public static CompatChairRenderer INSTANCE = null;
    private final ChairModel model;
    private final ModelPart cube_r1;
    private final ModelPart cube_r2;
    private final ModelPart cube_r3;

    public CompatChairRenderer(BlockEntityRendererProvider.Context ctx) {
        this.model = new ChairModel(ctx.getModelSet().bakeLayer(ChairModel.LAYER_LOCATION));
        this.cube_r1 = model.getMain().getChild("chair").getChild("cube_r1");
        this.cube_r2 = model.getMain().getChild("chair").getChild("cube_r2");
        this.cube_r3 = model.getMain().getChild("chair").getChild("cube_r3");
        INSTANCE = this;
    }

    @Override
    public void render(ChairBlockEntity entity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource,
                       int packedLight, int packedOverlay) {
        Item block = entity.getBlockState().getBlock().asItem();
        Item cushion = entity.getStack().getItem();
//        CouchShape shape = entity.getBlockState().getValue(ExpandableCouchBlock.COUCH_SHAPE);
        doRender(block, cushion,
                entity.getBlockState().getValue(ExpandableCouchBlock.FACING), poseStack, bufferSource, packedLight, packedOverlay, 1, 0);
    }

    public void doRender(Item block, Item cushion, Direction direction, PoseStack poseStack,
                         MultiBufferSource buffer, int packedLight, int packedOverlay, float scale, double moveY ) {

        ChairModel modelChair = new ChairModel(Minecraft.getInstance().getEntityModels().bakeLayer(ChairModel.LAYER_LOCATION));
        CloseablePoseStack ignored = new CloseablePoseStack(poseStack);

        try {
            poseStack.translate(0.5, 1.5, 0.5);
            poseStack.scale(scale, scale,scale);
            poseStack.translate(0, moveY, 0);
            poseStack.mulPose(Vector3f.YN.rotationDegrees(direction.getOpposite().toYRot()));
            poseStack.mulPose(Vector3f.XP.rotationDegrees(180.0F));
            modelChair.getMain().getChild("with_cushion").visible = false;
            modelChair.getMain().getChild("seat").visible = false;
            var blockTexture = OptimizedTableRenderer.OBJECT_TO_TEXTURE.get(block); // COMPAT
//            VertexConsumer vertex = buffer.getBuffer(RenderType.entityCutout(new ResourceLocation(texture.getNamespace(), "textures/block/hc/" + s[1] + "/chair/chair/" + s[2] + ".png")));
            VertexConsumer vertex = blockTexture.buffer(buffer, RenderType::entityCutout); // COMPAT

            modelChair.renderToBuffer(poseStack, vertex, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
            modelChair.getMain().getChild("base").visible = false;
            modelChair.getMain().getChild("seat").visible = true;
            modelChair.getMain().getChild("chair").visible = false;
            poseStack.scale(0.999F, 1.0F, 1.0F);
            modelChair.renderToBuffer(poseStack, vertex, packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
            poseStack.scale(1.001F, 1.0F, 1.0F);
            modelChair.getMain().getChild("with_cushion").visible = true;
            if (cushion != Items.AIR) {
                var cushionTexture = OptimizedTableRenderer.OBJECT_TO_TEXTURE.get(cushion); // COMPAT
                modelChair.getMain().getChild("base").visible = false;
                modelChair.renderToBuffer(poseStack, cushionTexture.buffer(buffer, RenderType::entityCutout), packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
//                modelChair.renderToBuffer(poseStack, buffer.getBuffer(RenderType.entityCutout(new ResourceLocation(texture.getNamespace(), "textures/block/chair/chair/cushion/" + cushion.getPath() + ".png"))), packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
            } else {
                modelChair.getMain().getChild("with_cushion").visible = false;
            }
        } catch (Throwable var13) {
            try {
                ignored.close();
            } catch (Throwable var12) {
                var13.addSuppressed(var12);
            }

            throw var13;
        }

        ignored.close();
    }

}
