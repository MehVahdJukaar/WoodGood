package net.mehvahdjukaar.every_compat.modules.handcrafted.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import earth.terrarium.handcrafted.client.block.fancybed.FancyBedModel;
import earth.terrarium.handcrafted.common.block.chair.couch.ExpandableCouchBlock;
import earth.terrarium.handcrafted.common.block.chair.diningbench.DiningBenchBlock;
import earth.terrarium.handcrafted.common.block.fancybed.FancyBedBlock;
import earth.terrarium.handcrafted.common.block.fancybed.FancyBedBlockEntity;
import earth.terrarium.handcrafted.common.block.property.DirectionalBlockSide;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.state.properties.BedPart;

import java.util.Map;

@Environment(EnvType.CLIENT)
public class OptimizedFancyBedRenderer implements BlockEntityRenderer<FancyBedBlockEntity> {
    public static final Map<Item, Material> OBJECT_TO_TEXTURE = new Object2ObjectOpenHashMap<>();

    public static OptimizedFancyBedRenderer INSTANCE = null;
    private final FancyBedModel single;
    private final FancyBedModel left;
    private final FancyBedModel middle;
    private final FancyBedModel right;

    public OptimizedFancyBedRenderer(BlockEntityRendererProvider.Context ctx) {
        EntityModelSet modelSet = ctx.getModelSet();
        this.single = new FancyBedModel(modelSet.bakeLayer(FancyBedModel.LAYER_LOCATION_SINGLE));
        this.left = new FancyBedModel(modelSet.bakeLayer(FancyBedModel.LAYER_LOCATION_DOUBLE_LEFT));
        this.middle = new FancyBedModel(modelSet.bakeLayer(FancyBedModel.LAYER_LOCATION_DOUBLE_MIDDLE));
        this.right = new FancyBedModel(modelSet.bakeLayer(FancyBedModel.LAYER_LOCATION_SINGLE));

        INSTANCE = this;
    }

    @Override
    public void render(FancyBedBlockEntity entity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        Item block = entity.getBlockState().getBlock().asItem();
        Item sheet = entity.getSheet().getItem();
        Item cushion = entity.getStack().getItem();

        EntityModelSet modelSet = Minecraft.getInstance().getEntityModels();
        if (entity.getBlockState().getValue(BedBlock.PART) == BedPart.HEAD) {
            return;
        }
        DirectionalBlockSide doubleBed = entity.getBlockState().getValue(FancyBedBlock.BED_SHAPE);
        FancyBedModel model = switch (entity.getBlockState().getValue(DiningBenchBlock.DINING_BENCH_SHAPE)) {
            case SINGLE -> new FancyBedModel(modelSet.bakeLayer(FancyBedModel.LAYER_LOCATION_SINGLE));
            case LEFT -> new FancyBedModel(modelSet.bakeLayer(FancyBedModel.LAYER_LOCATION_DOUBLE_LEFT));
            case MIDDLE -> new FancyBedModel(modelSet.bakeLayer(FancyBedModel.LAYER_LOCATION_DOUBLE_MIDDLE));
            case RIGHT -> new FancyBedModel(modelSet.bakeLayer(FancyBedModel.LAYER_LOCATION_DOUBLE_RIGHT));
        };

        boolean visible = entity.getBlockState().getValue(BedBlock.OCCUPIED);
        model.getMain().getChild("sheets").getChild("with_player").visible = visible;
        model.getMain().getChild("sheets").getChild("without_player").visible = !visible;

        doRender(doubleBed, sheet, cushion, block, model, entity.getBlockState().getValue(ExpandableCouchBlock.FACING),
                poseStack, bufferSource, packedLight, packedOverlay);
    }

    public void doRender(DirectionalBlockSide doubleBed, Item sheet, Item cushion, Item block,
                                 FancyBedModel model, Direction direction, PoseStack poseStack,
                                 MultiBufferSource buffer, int packedLight, int packedOverlay) {
        poseStack.pushPose();
        poseStack.translate(0.5, 1.5, 0.5);
        poseStack.mulPose(Vector3f.YN.rotationDegrees(direction.getOpposite().toYRot()));
        poseStack.mulPose(Vector3f.XP.rotationDegrees(180));

        model.getMain().getChild("sheets").visible = false;
        if (doubleBed == DirectionalBlockSide.SINGLE) {
            var blockTexture = OBJECT_TO_TEXTURE.get(block);
            model.renderToBuffer(poseStack, blockTexture.buffer(buffer, RenderType::entityCutout),
                    packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        } else {
            var blockTexture = OptimizedTableRenderer.OBJECT_TO_TEXTURE.get(block);
            model.renderToBuffer(poseStack, blockTexture.buffer(buffer, RenderType::entityCutout), packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        }

        model.getMain().getChild("sheets").visible = true;
        if (sheet != Items.AIR) {
            model.getMain().getChild("frame").visible = false;
            var sheetTexture = OBJECT_TO_TEXTURE.get(sheet);
            model.renderToBuffer(poseStack, sheetTexture.buffer(buffer, RenderType::entityCutout),
                    packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        }

        if (cushion != Items.AIR) {
            model.getMain().getChild("sheets").visible = false;
            var cushionTexture = OBJECT_TO_TEXTURE.get(cushion);
            model.renderToBuffer(poseStack, cushionTexture.buffer(buffer, RenderType::entityCutout),
                    packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        }

        poseStack.popPose();
    }


}
