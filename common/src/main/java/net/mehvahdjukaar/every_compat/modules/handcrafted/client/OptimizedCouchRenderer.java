package net.mehvahdjukaar.every_compat.modules.handcrafted.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import earth.terrarium.handcrafted.client.block.chair.couch.CouchModel;
import earth.terrarium.handcrafted.common.block.chair.couch.CouchBlockEntity;
import earth.terrarium.handcrafted.common.block.chair.couch.ExpandableCouchBlock;
import earth.terrarium.handcrafted.common.block.property.CouchShape;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.Map;

@Environment(EnvType.CLIENT)
public class OptimizedCouchRenderer implements BlockEntityRenderer<CouchBlockEntity> {
    public static final Map<Item, Material> OBJECT_TO_TEXTURE = new Object2ObjectOpenHashMap<>();

    public static OptimizedCouchRenderer INSTANCE = null;
    private final CouchModel single;
    private final CouchModel corner;
    private final CouchModel invertedCorner;
    private final CouchModel left;
    private final CouchModel middle;
    private final CouchModel right;

    public OptimizedCouchRenderer(BlockEntityRendererProvider.Context ctx) {
        EntityModelSet modelSet = ctx.getModelSet();
        this.single = new CouchModel(modelSet.bakeLayer(CouchModel.LAYER_LOCATION_SINGLE));
        this.corner = new CouchModel(modelSet.bakeLayer(CouchModel.LAYER_LOCATION_CORNER));
        this.invertedCorner = new CouchModel(modelSet.bakeLayer(CouchModel.LAYER_LOCATION_INVERTED_CORNER));
        this.left = new CouchModel(modelSet.bakeLayer(CouchModel.LAYER_LOCATION_LEFT));
        this.middle = new CouchModel(modelSet.bakeLayer(CouchModel.LAYER_LOCATION_MIDDLE));
        this.right = new CouchModel(modelSet.bakeLayer(CouchModel.LAYER_LOCATION_RIGHT));

        INSTANCE = this;
    }

    @Override
    public void render(CouchBlockEntity entity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        Item block = entity.getBlockState().getBlock().asItem();
        Item cushion = entity.getStack().getItem();
        CouchShape couchShape = entity.getBlockState().getValue(ExpandableCouchBlock.COUCH_SHAPE);

        CouchModel model = switch (couchShape) {
            case SINGLE -> single;
            case LEFT -> left;
            case MIDDLE -> middle;
            case RIGHT -> right;
            case INNER_LEFT, INNER_RIGHT -> corner;
            case OUTER_LEFT, OUTER_RIGHT -> invertedCorner;
        };

        doRender(model, entity.getBlockState().getValue(ExpandableCouchBlock.FACING), couchShape, poseStack,
                bufferSource, packedLight, packedOverlay, block, cushion);
    }

    public void doRender(CouchModel model, Direction direction, CouchShape couchShape, PoseStack poseStack, MultiBufferSource buffer,
                         int packedLight, int packedOverlay, Item block, Item cushion) {

        poseStack.pushPose();
        poseStack.translate(0.5, 1.5, 0.5);
        poseStack.mulPose(switch (direction) {
            case EAST -> switch (couchShape) {
                case OUTER_LEFT, INNER_LEFT, MIDDLE, LEFT, RIGHT, SINGLE -> Vector3f.YP.rotationDegrees(270);
                case OUTER_RIGHT, INNER_RIGHT -> Vector3f.YP.rotationDegrees(180);
            };
            case SOUTH -> switch (couchShape) {
                case OUTER_LEFT, INNER_LEFT, MIDDLE, LEFT, RIGHT, SINGLE -> Vector3f.YP.rotationDegrees(180);
                case OUTER_RIGHT, INNER_RIGHT -> Vector3f.YP.rotationDegrees(90);
            };
            case WEST -> switch (couchShape) {
                case OUTER_LEFT, INNER_LEFT, MIDDLE, LEFT, RIGHT, SINGLE -> Vector3f.YP.rotationDegrees(90);
                case OUTER_RIGHT, INNER_RIGHT -> Vector3f.YP.rotationDegrees(0);
            };
            default -> switch (couchShape) {
                 case OUTER_LEFT, INNER_LEFT, MIDDLE, LEFT, RIGHT, SINGLE -> Vector3f.YP.rotationDegrees(0);
                case OUTER_RIGHT, INNER_RIGHT -> Vector3f.YP.rotationDegrees(270);
            };
        });
        poseStack.mulPose(Vector3f.XP.rotationDegrees(180));

        var blockTexture = OBJECT_TO_TEXTURE.get(block);
        model.renderToBuffer(poseStack, blockTexture.buffer(buffer, RenderType::entityCutout),
                packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        if (cushion != Items.AIR) {
            var cushionTexture = OBJECT_TO_TEXTURE.get(cushion);
            model.renderToBuffer(poseStack, cushionTexture.buffer(buffer, RenderType::entityCutout),
                    packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        }
        poseStack.popPose();
    }
}
