package net.mehvahdjukaar.every_compat.modules.handcrafted;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import earth.terrarium.handcrafted.client.block.chair.couch.CouchModel;
import earth.terrarium.handcrafted.client.block.chair.couch.CouchRenderer;
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
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.IdentityHashMap;
import java.util.Map;

public class CompatCouchRenderer implements BlockEntityRenderer<CouchBlockEntity> {
    public static final Map<Item, Material> OBJECT_TO_TEXTURE = new IdentityHashMap<>();

    public static CompatCouchRenderer INSTANCE = null;
    private final CouchModel couchSingle;
    private final CouchModel couchCorner;
    private final CouchModel couchInvertedCorner;
    private final CouchModel couchLeft;
    private final CouchModel couchMiddle;
    private final CouchModel couchRight;

    public CompatCouchRenderer(BlockEntityRendererProvider.Context ctx) {
        EntityModelSet modelSet = ctx.getModelSet();
        this.couchSingle = new CouchModel(modelSet.bakeLayer(CouchModel.LAYER_LOCATION_SINGLE));
        this.couchCorner = new CouchModel(modelSet.bakeLayer(CouchModel.LAYER_LOCATION_CORNER));
        this.couchInvertedCorner = new CouchModel(modelSet.bakeLayer(CouchModel.LAYER_LOCATION_INVERTED_CORNER));
        this.couchLeft = new CouchModel(modelSet.bakeLayer(CouchModel.LAYER_LOCATION_LEFT));
        this.couchMiddle = new CouchModel(modelSet.bakeLayer(CouchModel.LAYER_LOCATION_MIDDLE));
        this.couchRight = new CouchModel(modelSet.bakeLayer(CouchModel.LAYER_LOCATION_RIGHT));

        INSTANCE = this;
    }

    @Override
    public void render(CouchBlockEntity entity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        Item block = entity.getBlockState().getBlock().asItem();
        Item cushion = entity.getStack().getItem();

        CouchShape shape = entity.getBlockState().getValue(ExpandableCouchBlock.COUCH_SHAPE);
        CouchModel model = switch (shape) {
            case SINGLE -> couchSingle;
            case LEFT -> couchLeft;
            case MIDDLE -> couchMiddle;
            case RIGHT -> couchRight;
            case INNER_LEFT, INNER_RIGHT ->couchCorner;
            case OUTER_LEFT, OUTER_RIGHT -> couchInvertedCorner;
        };
        doRender(block, cushion, model, entity.getBlockState().getValue(ExpandableCouchBlock.FACING), shape, poseStack, bufferSource, packedLight, packedOverlay);
    }

    public void doRender(Item block, Item cushion, CouchModel model, Direction direction, CouchShape shape, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        poseStack.pushPose();
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
        var blockTexture = OBJECT_TO_TEXTURE.get(block); // COMPAT
        model.renderToBuffer(poseStack, blockTexture.buffer(buffer, RenderType::entityCutout), packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f); // COMPAT
        if (cushion != Items.AIR) {
            var cushionTexture = OBJECT_TO_TEXTURE.get(cushion); // COMPAT
            model.renderToBuffer(poseStack, cushionTexture.buffer(buffer, RenderType::entityCutout), packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f); // COMPAT
        }
        poseStack.popPose();
    }
}
