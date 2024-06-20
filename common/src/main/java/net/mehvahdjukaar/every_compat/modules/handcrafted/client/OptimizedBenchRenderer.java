package net.mehvahdjukaar.every_compat.modules.handcrafted.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import earth.terrarium.handcrafted.client.block.chair.woodenbench.WoodenBenchModel;
import earth.terrarium.handcrafted.common.block.chair.couch.ExpandableCouchBlock;
import earth.terrarium.handcrafted.common.block.chair.woodenbench.WoodenBenchBlockEntity;
import earth.terrarium.handcrafted.common.block.property.CouchShape;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.minecraft.client.model.geom.ModelPart;
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
public class OptimizedBenchRenderer implements BlockEntityRenderer<WoodenBenchBlockEntity> {
    //item to texture map used for all renderers
    public static final Map<Item, Material> OBJECT_TO_TEXTURE = new Object2ObjectArrayMap<>();

    public static OptimizedBenchRenderer INSTANCE = null;
    public final WoodenBenchModel single;
    private final WoodenBenchModel corner;
    private final WoodenBenchModel inverted_corner;
    private final WoodenBenchModel left;
    private final WoodenBenchModel middle;
    private final WoodenBenchModel right;


    public OptimizedBenchRenderer(BlockEntityRendererProvider.Context ctx) {
        this.single = new WoodenBenchModel(ctx.getModelSet().bakeLayer(WoodenBenchModel.LAYER_LOCATION_SINGLE));
        this.corner = new WoodenBenchModel(ctx.getModelSet().bakeLayer(WoodenBenchModel.LAYER_LOCATION_CORNER));
        this.inverted_corner = new WoodenBenchModel(ctx.getModelSet().bakeLayer(WoodenBenchModel.LAYER_LOCATION_INVERTED_CORNER));
        this.left = new WoodenBenchModel(ctx.getModelSet().bakeLayer(WoodenBenchModel.LAYER_LOCATION_LEFT));
        this.middle = new WoodenBenchModel(ctx.getModelSet().bakeLayer(WoodenBenchModel.LAYER_LOCATION_MIDDLE));
        this.right = new WoodenBenchModel(ctx.getModelSet().bakeLayer(WoodenBenchModel.LAYER_LOCATION_RIGHT));

        INSTANCE = this;
    }

    @Override
    public void render(WoodenBenchBlockEntity entity, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        CouchShape shape = entity.getBlockState().getValue(ExpandableCouchBlock.COUCH_SHAPE);
        WoodenBenchModel model = switch (shape) {
            case SINGLE -> single;
            case LEFT -> left;
            case MIDDLE -> middle;
            case RIGHT -> right;
            case INNER_LEFT, INNER_RIGHT -> corner;
            case OUTER_LEFT, OUTER_RIGHT -> inverted_corner;
        };
        Item block = entity.getBlockState().getBlock().asItem();
        Item cushion = entity.getStack().getItem();

        doRender(model, entity.getBlockState().getValue(ExpandableCouchBlock.FACING), shape, poseStack,
                bufferSource, packedLight, packedOverlay, cushion, block);
    }

    public void doRender(WoodenBenchModel model, Direction direction, CouchShape shape, PoseStack poseStack,
                         MultiBufferSource buffer, int packedLight, int packedOverlay, Item cushion, Item block) {
        poseStack.pushPose();

        poseStack.translate(0.5, 1.5, 0.5);
        poseStack.mulPose(switch (direction) {
            case EAST -> switch (shape) {
                case OUTER_LEFT, INNER_RIGHT, MIDDLE, LEFT, RIGHT, SINGLE -> Vector3f.YP.rotationDegrees(90);
                case INNER_LEFT -> Vector3f.YP.rotationDegrees(180);
                case OUTER_RIGHT -> Vector3f.YP.rotationDegrees(0);
            };
            case SOUTH -> switch (shape) {
                case OUTER_LEFT, INNER_RIGHT, MIDDLE, LEFT, RIGHT, SINGLE -> Vector3f.YP.rotationDegrees(0);
                case INNER_LEFT -> Vector3f.YP.rotationDegrees(90);
                case OUTER_RIGHT -> Vector3f.YP.rotationDegrees(270);
            };
            case WEST -> switch (shape) {
                case OUTER_LEFT, INNER_RIGHT, MIDDLE, LEFT, RIGHT, SINGLE -> Vector3f.YP.rotationDegrees(270);
                case INNER_LEFT -> Vector3f.YP.rotationDegrees(0);
                case OUTER_RIGHT -> Vector3f.YP.rotationDegrees(180);
            };
            default -> switch (shape) {
                case OUTER_LEFT, INNER_RIGHT, MIDDLE, LEFT, RIGHT, SINGLE -> Vector3f.YP.rotationDegrees(180);
                case INNER_LEFT -> Vector3f.YP.rotationDegrees(270);
                case OUTER_RIGHT -> Vector3f.YP.rotationDegrees(90);
            };
        });
        poseStack.mulPose(Vector3f.XP.rotationDegrees(180));

        var texture = OBJECT_TO_TEXTURE.get(block);

        model.getMain().getChild("with_cushion").visible = false;
        model.renderToBuffer(poseStack, texture.buffer(buffer, RenderType::entityCutout),
                packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);

        if (cushion != Items.AIR) {
            model.getMain().getChild("with_cushion").visible = true;
            var cushionTexture = OBJECT_TO_TEXTURE.get(cushion);
            if (cushionTexture != null)
                model.renderToBuffer(poseStack, cushionTexture.buffer(buffer, RenderType::entityCutout),
                        packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        }
        poseStack.popPose();
    }
}
