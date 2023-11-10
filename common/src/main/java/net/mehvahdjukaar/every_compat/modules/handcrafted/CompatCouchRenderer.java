package net.mehvahdjukaar.every_compat.modules.handcrafted;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.teamresourceful.resourcefullib.client.CloseablePoseStack;
import earth.terrarium.handcrafted.client.block.chair.couch.CouchModel;
import earth.terrarium.handcrafted.common.block.chair.couch.CouchBlockEntity;
import earth.terrarium.handcrafted.common.block.chair.couch.ExpandableCouchBlock;
import earth.terrarium.handcrafted.common.block.property.CouchShape;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class CompatCouchRenderer implements BlockEntityRenderer<CouchBlockEntity> {
    public static CompatCouchRenderer INSTANCE = null;
    private final CouchModel couchSingle;
    private final CouchModel couchCorner;
    private final CouchModel couchInvertedCorner;
    private final CouchModel couchLeft;
    private final CouchModel couchMiddle;
    private final CouchModel couchRight;
    private final ModelPart single_r1;
    private final ModelPart single_r2;
    private final ModelPart single_r3;
    private final ModelPart corner_r1;
    private final ModelPart corner_r2;
    private final ModelPart invertedCorner_r1;
    private final ModelPart invertedCorner_r2;
    private final ModelPart left_r1;
    private final ModelPart left_r2;
    private final ModelPart middle_r1;
    private final ModelPart right_r1;
    private final ModelPart right_r2;

    @SuppressWarnings("unused")
    public CompatCouchRenderer(BlockEntityRendererProvider.Context ctx) {
        this.couchSingle = new CouchModel(ctx.getModelSet().bakeLayer(CouchModel.LAYER_LOCATION_SINGLE));
        this.single_r1 = couchSingle.getMain().getChild("cube_r1");
        this.single_r2 = couchSingle.getMain().getChild("cube_r2");
        this.single_r3 = couchSingle.getMain().getChild("cube_r3");

        this.couchCorner = new CouchModel(ctx.getModelSet().bakeLayer(CouchModel.LAYER_LOCATION_CORNER));
        this.corner_r1 = couchCorner.getMain().getChild("cube_r1");
        this.corner_r2 = couchCorner.getMain().getChild("cube_r2");

        this.couchInvertedCorner = new CouchModel(ctx.getModelSet().bakeLayer(CouchModel.LAYER_LOCATION_INVERTED_CORNER));
        this.invertedCorner_r1 = couchInvertedCorner.getMain().getChild("cube_r1");
        this.invertedCorner_r2 = couchInvertedCorner.getMain().getChild("cube_r2");

        this.couchLeft = new CouchModel(ctx.getModelSet().bakeLayer(CouchModel.LAYER_LOCATION_LEFT));
        this.left_r1 = couchLeft.getMain().getChild("cube_r1");
        this.left_r2 = couchLeft.getMain().getChild("cube_r2");

        this.couchMiddle = new CouchModel(ctx.getModelSet().bakeLayer(CouchModel.LAYER_LOCATION_MIDDLE));
        this.middle_r1 = couchMiddle.getMain().getChild("cube_r1");

        this.couchRight = new CouchModel(ctx.getModelSet().bakeLayer(CouchModel.LAYER_LOCATION_RIGHT));
        this.right_r1 = couchRight.getMain().getChild("cube_r1");
        this.right_r2 = couchRight.getMain().getChild("cube_r2");

        INSTANCE = this;
    }

    @Override
    public void render(CouchBlockEntity entity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        Item block = entity.getBlockState().getBlock().asItem();
        Item cushion = entity.getStack().getItem();

        EntityModelSet modelSet = Minecraft.getInstance().getEntityModels();
        CouchShape shape = entity.getBlockState().getValue(ExpandableCouchBlock.COUCH_SHAPE);
        CouchModel model = switch (shape) {
            case SINGLE -> new CouchModel(modelSet.bakeLayer(CouchModel.LAYER_LOCATION_SINGLE));
            case LEFT -> new CouchModel(modelSet.bakeLayer(CouchModel.LAYER_LOCATION_LEFT));
            case MIDDLE -> new CouchModel(modelSet.bakeLayer(CouchModel.LAYER_LOCATION_MIDDLE));
            case RIGHT -> new CouchModel(modelSet.bakeLayer(CouchModel.LAYER_LOCATION_RIGHT));
            case INNER_LEFT, INNER_RIGHT -> new CouchModel(modelSet.bakeLayer(CouchModel.LAYER_LOCATION_CORNER));
            case OUTER_LEFT, OUTER_RIGHT ->
                    new CouchModel(modelSet.bakeLayer(CouchModel.LAYER_LOCATION_INVERTED_CORNER));
        };
        doRender(block, cushion, model, entity.getBlockState().getValue(ExpandableCouchBlock.FACING), shape, poseStack, bufferSource, packedLight, packedOverlay);
    }

    public void doRender(Item block, Item cushion, CouchModel model, Direction direction, CouchShape shape, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        try (var ignored = new CloseablePoseStack(poseStack)) {
            poseStack.translate(0.5, 1.5, 0.5);
            poseStack.mulPose(switch (direction) {
                case EAST -> switch (shape) {
                    case OUTER_LEFT, INNER_LEFT, MIDDLE, LEFT, RIGHT, SINGLE -> Vector3f.YP.rotationDegrees(270);
                    case OUTER_RIGHT, INNER_RIGHT -> Vector3f.YP.rotationDegrees(180);
                };
                case SOUTH -> switch (shape) {
                    case OUTER_LEFT, INNER_LEFT, MIDDLE, LEFT, RIGHT, SINGLE -> Vector3f.YP.rotationDegrees(180);
                    case OUTER_RIGHT, INNER_RIGHT -> Vector3f.YP.rotationDegrees(90);
                };
                case WEST -> switch (shape) {
                    case OUTER_LEFT, INNER_LEFT, MIDDLE, LEFT, RIGHT, SINGLE -> Vector3f.YP.rotationDegrees(90);
                    case OUTER_RIGHT, INNER_RIGHT -> Vector3f.YP.rotationDegrees(0);
                };
                default -> switch (shape) {
                    case OUTER_LEFT, INNER_LEFT, MIDDLE, LEFT, RIGHT, SINGLE -> Vector3f.YP.rotationDegrees(0);
                    case OUTER_RIGHT, INNER_RIGHT -> Vector3f.YP.rotationDegrees(270);
                };
            });
            poseStack.mulPose(Vector3f.XP.rotationDegrees(180));
            var blockTexture = OptimizedTableRenderer.OBJECT_TO_TEXTURE.get(block); // COMPAT
            model.renderToBuffer(poseStack, blockTexture.buffer(buffer, RenderType::entityCutout), packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f); // COMPAT
//            model.renderToBuffer(poseStack, buffer.getBuffer(RenderType.entityCutout(new ResourceLocation(texture.getNamespace(), "textures/block/chair/couch/" + texture.getPath() + ".png"))), packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
            if (cushion != Items.AIR) {
                var cushionTexture = OptimizedTableRenderer.OBJECT_TO_TEXTURE.get(cushion); // COMPAT
                model.renderToBuffer(poseStack, cushionTexture.buffer(buffer, RenderType::entityCutout), packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f); // COMPAT
//                model.renderToBuffer(poseStack, buffer.getBuffer(RenderType.entityCutout(new ResourceLocation(texture.getNamespace(), "textures/block/chair/couch/cushion/" + cushion.getPath() + ".png"))), packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
            }
        }
    }
}
