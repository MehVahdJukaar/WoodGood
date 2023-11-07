package net.mehvahdjukaar.every_compat.modules.handcrafted;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import earth.terrarium.handcrafted.client.block.table.table.TableModel;
import earth.terrarium.handcrafted.common.block.property.SheetState;
import earth.terrarium.handcrafted.common.block.property.TableState;
import earth.terrarium.handcrafted.common.block.table.table.TableBlock;
import earth.terrarium.handcrafted.common.block.table.table.TableBlockEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.Material;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.IdentityHashMap;
import java.util.Map;

//this is bad. you should use custom baked models instead...
public class OptimizedTableRenderer implements BlockEntityRenderer<TableBlockEntity> {
    //item to texture map used for all renderers
    public static final Map<Item, Material> OBJECT_TO_TEXTURE = new IdentityHashMap<>();


    public static OptimizedTableRenderer INSTANCE = null;
    private final TableModel model;
    private final ModelPart northeastLeg;
    private final ModelPart northwestLeg;
    private final ModelPart southeastLeg;
    private final ModelPart northOverlay;
    private final ModelPart southwestLeg;
    private final ModelPart eastOverlay;
    private final ModelPart southOverlay;
    private final ModelPart westOverlay;

    public OptimizedTableRenderer(BlockEntityRendererProvider.Context ctx) {
        this.model = new TableModel(ctx.getModelSet().bakeLayer(TableModel.LAYER_LOCATION));
        this.northeastLeg = model.getMain().getChild("table").getChild("northeast_leg");
        this.northwestLeg = model.getMain().getChild("table").getChild("northwest_leg");
        this.southeastLeg = model.getMain().getChild("table").getChild("southeast_leg");
        this.southwestLeg = model.getMain().getChild("table").getChild("southwest_leg");
        this.northOverlay = model.getMain().getChild("overlay").getChild("overlay_side_north");
        this.eastOverlay = model.getMain().getChild("overlay").getChild("overlay_side_east");
        this.southOverlay = model.getMain().getChild("overlay").getChild("overlay_side_south");
        this.westOverlay = model.getMain().getChild("overlay").getChild("overlay_side_west");
        INSTANCE = this;
    }

    public void render(TableBlockEntity entity, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {

        var tableState = entity.getBlockState().getValue(TableBlock.TABLE_BLOCK_SHAPE);
        var sheetState = entity.getBlockState().getValue(TableBlock.TABLE_SHEET_SHAPE);
        Item b = entity.getBlockState().getBlock().asItem();
        Item sheet = entity.getStack().getItem();
        doRender(poseStack, buffer, packedLight, packedOverlay, tableState, sheetState, sheet, b);
    }

    public void doRender(PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay,
                         TableState tableState, SheetState sheetState, Item sheet, Item block) {
        poseStack.pushPose();

        poseStack.translate(0.5, 1.5, 0.5);
        poseStack.mulPose(Vector3f.XP.rotationDegrees(180.0F));
        switch (tableState) {
            case SINGLE -> {
                northeastLeg.visible = true;
                northwestLeg.visible = true;
                southeastLeg.visible = true;
                southwestLeg.visible = true;
            }
            case CENTER -> {
                northeastLeg.visible = false;
                northwestLeg.visible = false;
                southeastLeg.visible = false;
                southwestLeg.visible = false;
            }
            case NORTH_EAST_CORNER -> {
                northeastLeg.visible = false;
                northwestLeg.visible = false;
                southwestLeg.visible = false;
                southeastLeg.visible = true;
            }
            case NORTH_WEST_CORNER -> {
                northeastLeg.visible = false;
                northwestLeg.visible = false;
                southeastLeg.visible = false;
                southwestLeg.visible = true;
            }
            case SOUTH_EAST_CORNER -> {
                northeastLeg.visible = true;
                northwestLeg.visible = false;
                southeastLeg.visible = false;
                southwestLeg.visible = false;
            }
            case SOUTH_WEST_CORNER -> {
                northeastLeg.visible = false;
                northwestLeg.visible = true;
                southeastLeg.visible = false;
                southwestLeg.visible = false;
            }
            case NORTH_SIDE -> {
                northeastLeg.visible = false;
                northwestLeg.visible = false;
                southeastLeg.visible = true;
                southwestLeg.visible = true;
            }
            case EAST_SIDE -> {
                northeastLeg.visible = true;
                northwestLeg.visible = false;
                southeastLeg.visible = true;
                southwestLeg.visible = false;
            }
            case SOUTH_SIDE -> {
                northeastLeg.visible = true;
                northwestLeg.visible = true;
                southeastLeg.visible = false;
                southwestLeg.visible = false;
            }
            case WEST_SIDE -> {
                northeastLeg.visible = false;
                northwestLeg.visible = true;
                southeastLeg.visible = false;
                southwestLeg.visible = true;
            }
        }

        switch (sheetState) {
            case SINGLE -> {
                northOverlay.visible = true;
                eastOverlay.visible = true;
                southOverlay.visible = true;
                westOverlay.visible = true;
            }
            case CENTER -> {
                northOverlay.visible = false;
                eastOverlay.visible = false;
                southOverlay.visible = false;
                westOverlay.visible = false;
            }
            case NORTH_SIDE -> {
                northOverlay.visible = true;
                eastOverlay.visible = false;
                southOverlay.visible = false;
                westOverlay.visible = false;
            }
            case EAST_SIDE -> {
                northOverlay.visible = false;
                eastOverlay.visible = true;
                southOverlay.visible = false;
                westOverlay.visible = false;
            }
            case SOUTH_SIDE -> {
                northOverlay.visible = false;
                eastOverlay.visible = false;
                southOverlay.visible = true;
                westOverlay.visible = false;
            }
            case WEST_SIDE -> {
                northOverlay.visible = false;
                eastOverlay.visible = false;
                southOverlay.visible = false;
                westOverlay.visible = true;
            }
            case NORTH_EAST_CORNER -> {
                northOverlay.visible = true;
                eastOverlay.visible = true;
                southOverlay.visible = false;
                westOverlay.visible = false;
            }
            case NORTH_WEST_CORNER -> {
                northOverlay.visible = true;
                eastOverlay.visible = false;
                southOverlay.visible = false;
                westOverlay.visible = true;
            }
            case SOUTH_EAST_CORNER -> {
                northOverlay.visible = false;
                eastOverlay.visible = true;
                southOverlay.visible = true;
                westOverlay.visible = false;
            }
            case SOUTH_WEST_CORNER -> {
                northOverlay.visible = false;
                eastOverlay.visible = false;
                southOverlay.visible = true;
                westOverlay.visible = true;
            }
            case NORTH_COVER -> {
                northOverlay.visible = true;
                eastOverlay.visible = false;
                westOverlay.visible = false;
            }
            case EAST_COVER -> {
                northOverlay.visible = false;
                westOverlay.visible = false;
                southOverlay.visible = false;
                eastOverlay.visible = true;
            }
            case SOUTH_COVER -> {
                northOverlay.visible = false;
                westOverlay.visible = false;
                southOverlay.visible = true;
                eastOverlay.visible = false;
            }
            case WEST_COVER -> {
                northOverlay.visible = false;
                westOverlay.visible = true;
                southOverlay.visible = false;
                eastOverlay.visible = false;
            }
        }

        var texture = OBJECT_TO_TEXTURE.get(block);

        model.renderToBuffer(poseStack, texture.buffer(buffer, RenderType::entityCutout), packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        if (sheet != Items.AIR) {
            var sheetTexture = OBJECT_TO_TEXTURE.get(sheet);
            if (sheetTexture != null)
                model.renderToBuffer(poseStack, sheetTexture.buffer(buffer, RenderType::entityCutout), packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        }
        poseStack.popPose();
    }
}
