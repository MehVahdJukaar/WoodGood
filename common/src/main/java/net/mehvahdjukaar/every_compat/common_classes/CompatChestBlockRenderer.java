package net.mehvahdjukaar.every_compat.common_classes;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import it.unimi.dsi.fastutil.floats.Float2FloatFunction;
import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BrightnessCombiner;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class CompatChestBlockRenderer extends ChestRenderer<CompatChestBlockEntity> {
    private final Map<WoodType, Material> single = new HashMap<>();
    private final Map<WoodType, Material> left = new HashMap<>();
    private final Map<WoodType, Material> right = new HashMap<>();
    private final Map<WoodType, Material> trapped = new HashMap<>();
    private final Map<WoodType, Material> trapped_left = new HashMap<>();
    private final Map<WoodType, Material> trapped_right = new HashMap<>();

    //assumes standard naming here. Generalize if needed
    public CompatChestBlockRenderer(BlockEntityRendererProvider.Context context, String shortenedId) {
        super(context);
        for (WoodType w : WoodTypeRegistry.getTypes()) {
            if (w.isVanilla()) continue;
            String path = "entity/chest/" + shortenedId + "/" + w.getAppendableId() + "_chest";
            String trapped_path = "entity/chest/" + shortenedId + "/" + w.getAppendableId() + "_trapped_chest";
            if (!w.isVanilla()) {
                single.put(w, new Material(Sheets.CHEST_SHEET, EveryCompat.res(path)));
                left.put(w, new Material(Sheets.CHEST_SHEET, EveryCompat.res(path + "_left")));
                right.put(w, new Material(Sheets.CHEST_SHEET, EveryCompat.res(path + "_right")));
                trapped.put(w, new Material(Sheets.CHEST_SHEET, EveryCompat.res(trapped_path)));
                trapped_left.put(w, new Material(Sheets.CHEST_SHEET, EveryCompat.res(trapped_path + "_left")));
                trapped_right.put(w, new Material(Sheets.CHEST_SHEET, EveryCompat.res(trapped_path + "_right")));
            }
        }
    }

    protected @NotNull Material getMaterial(CompatChestBlockEntity blockEntity, ChestType chestType) {
        WoodType w = blockEntity.getWoodType();
        if (blockEntity.isTrapped()) {
            return switch (chestType) {
                case LEFT -> trapped_left.get(w);
                case RIGHT -> trapped_right.get(w);
                default -> trapped.get(w);
            };
        } else {
            return switch (chestType) {
                case LEFT -> left.get(w);
                case RIGHT -> right.get(w);
                default -> single.get(w);
            };
        }
    }


    //copy pasted from ChestRenderer

    @Override
    public void render(CompatChestBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        Level level = blockEntity.getLevel();
        boolean flag = level != null;
        BlockState blockstate = flag ? blockEntity.getBlockState() : Blocks.CHEST.defaultBlockState().setValue(ChestBlock.FACING, Direction.SOUTH);
        ChestType chesttype = blockstate.hasProperty(ChestBlock.TYPE) ? blockstate.getValue(ChestBlock.TYPE) : ChestType.SINGLE;
        Block flag1 = blockstate.getBlock();
        if (flag1 instanceof AbstractChestBlock<?> abstractchestblock) {
            boolean flag1x = chesttype != ChestType.SINGLE;
            poseStack.pushPose();
            float f = (blockstate.getValue(ChestBlock.FACING)).toYRot();
            poseStack.translate(0.5F, 0.5F, 0.5F);
            poseStack.mulPose(Axis.YP.rotationDegrees(-f));
            poseStack.translate(-0.5F, -0.5F, -0.5F);
            DoubleBlockCombiner.NeighborCombineResult<? extends ChestBlockEntity> neighborcombineresult;
            if (flag) {
                neighborcombineresult = abstractchestblock.combine(blockstate, level, blockEntity.getBlockPos(), true);
            } else {
                neighborcombineresult = DoubleBlockCombiner.Combiner::acceptNone;
            }

            float f1 = neighborcombineresult.apply(ChestBlock.opennessCombiner(blockEntity)).get(partialTick);
            f1 = 1.0F - f1;
            f1 = 1.0F - f1 * f1 * f1;
            int i = neighborcombineresult.<Int2IntFunction>apply(new BrightnessCombiner<>()).applyAsInt(packedLight);
            Material material = this.getMaterial(blockEntity, chesttype);
            VertexConsumer vertexconsumer = material.buffer(buffer, RenderType::entityCutout);
            if (flag1x) {
                if (chesttype == ChestType.LEFT) {
                    this.render(poseStack, vertexconsumer, this.doubleLeftLid, this.doubleLeftLock, this.doubleLeftBottom, f1, i, packedOverlay);
                } else {
                    this.render(poseStack, vertexconsumer, this.doubleRightLid, this.doubleRightLock, this.doubleRightBottom, f1, i, packedOverlay);
                }
            } else {
                this.render(poseStack, vertexconsumer, this.lid, this.lock, this.bottom, f1, i, packedOverlay);
            }

            poseStack.popPose();
        }
    }
    private void render(PoseStack poseStack, VertexConsumer consumer, ModelPart lidPart, ModelPart lockPart, ModelPart bottomPart, float lidAngle, int packedLight, int packedOverlay) {
        lidPart.xRot = -(lidAngle * 1.5707964F);
        lockPart.xRot = lidPart.xRot;
        lidPart.render(poseStack, consumer, packedLight, packedOverlay);
        lockPart.render(poseStack, consumer, packedLight, packedOverlay);
        bottomPart.render(poseStack, consumer, packedLight, packedOverlay);
    }

    public static void register(ClientHelper.BlockEntityRendererEvent event, BlockEntityType<CompatChestBlockEntity> tile, String s) {
        event.register(tile, c -> new CompatChestBlockRenderer(c, s));
    }

}
