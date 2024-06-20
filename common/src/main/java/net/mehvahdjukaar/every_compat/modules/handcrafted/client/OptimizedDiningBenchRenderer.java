package net.mehvahdjukaar.every_compat.modules.handcrafted.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import earth.terrarium.handcrafted.client.block.chair.diningbench.DiningBenchModel;
import earth.terrarium.handcrafted.common.block.chair.diningbench.DiningBenchBlock;
import earth.terrarium.handcrafted.common.block.chair.diningbench.DiningBenchBlockEntity;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;

public class OptimizedDiningBenchRenderer implements BlockEntityRenderer<DiningBenchBlockEntity> {

    public static OptimizedDiningBenchRenderer INSTANCE = null;
    private final DiningBenchModel single;
    private final DiningBenchModel left;
    private final DiningBenchModel middle;
    private final DiningBenchModel right;

    public OptimizedDiningBenchRenderer(BlockEntityRendererProvider.Context ctx) {
        EntityModelSet modelSet = ctx.getModelSet();
        this.single = new DiningBenchModel(modelSet.bakeLayer(DiningBenchModel.LAYER_LOCATION_SINGLE));
        this.left = new DiningBenchModel(modelSet.bakeLayer(DiningBenchModel.LAYER_LOCATION_LEFT));
        this.middle = new DiningBenchModel(modelSet.bakeLayer(DiningBenchModel.LAYER_LOCATION_MIDDLE));
        this.right = new DiningBenchModel(modelSet.bakeLayer(DiningBenchModel.LAYER_LOCATION_RIGHT));

        INSTANCE = this;
    }

    @Override
    public void render(DiningBenchBlockEntity entity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        Item block = entity.getBlockState().getBlock().asItem();

        DiningBenchModel model = switch (entity.getBlockState().getValue(DiningBenchBlock.DINING_BENCH_SHAPE)) {
            case SINGLE -> single;
            case LEFT -> left;
            case MIDDLE -> middle;
            case RIGHT -> right;
        };

        doRender(block, model, entity.getBlockState().getValue(DiningBenchBlock.FACING), poseStack,
                bufferSource, packedLight, packedOverlay);
    }

    public void doRender(Item block, DiningBenchModel model, Direction direction, PoseStack poseStack,
                                 MultiBufferSource buffer, int packedLight, int packedOverlay) {
        poseStack.pushPose();
        poseStack.translate(0.5, 1.5, 0.5);
        poseStack.mulPose(Vector3f.YN.rotationDegrees(direction.toYRot()));
        poseStack.mulPose(Vector3f.XP.rotationDegrees(180));

        var blockTexture = OptimizedTableRenderer.OBJECT_TO_TEXTURE.get(block);
        model.renderToBuffer(poseStack, blockTexture.buffer(buffer, RenderType::entityCutout),
                packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        poseStack.popPose();
    }

}
