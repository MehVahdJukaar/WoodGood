package net.mehvahdjukaar.every_compat.modules.handcrafted;

import com.mojang.blaze3d.vertex.PoseStack;
import earth.terrarium.handcrafted.Handcrafted;
import earth.terrarium.handcrafted.client.block.chair.couch.CouchModel;
import earth.terrarium.handcrafted.client.block.chair.diningbench.DiningBenchModel;
import earth.terrarium.handcrafted.client.block.chair.woodenbench.WoodenBenchModel;
import earth.terrarium.handcrafted.client.block.fancybed.FancyBedModel;
import earth.terrarium.handcrafted.common.block.property.CouchShape;
import earth.terrarium.handcrafted.common.block.property.DirectionalBlockSide;
import earth.terrarium.handcrafted.common.block.property.SheetState;
import earth.terrarium.handcrafted.common.block.property.TableState;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.modules.handcrafted.client.*;
import net.mehvahdjukaar.moonlight.api.client.ItemStackRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

@Environment(EnvType.CLIENT)
public class compatItemRenderer {

    public static class TableItemRenderer extends ItemStackRenderer {
        @Override
        public void renderByItem(ItemStack itemStack, ItemTransforms.TransformType transformType, PoseStack poseStack,
                                 MultiBufferSource multiBufferSource, int i, int i1) {
            OptimizedTableRenderer.INSTANCE.doRender(poseStack, multiBufferSource, i, i1,
                    TableState.SINGLE, SheetState.SINGLE, Items.AIR, itemStack.getItem());
        }
    }

    public static class ChairItemRenderer extends ItemStackRenderer {
        @Override
        public void renderByItem(ItemStack itemStack, ItemTransforms.TransformType transformType, PoseStack poseStack,
                                 MultiBufferSource multiBufferSource, int packedLight, int packedOverlay) {
            poseStack.scale(0.75f, 0.75f, 0.75f);
            OptimizedChairRenderer.INSTANCE.doRender(Direction.SOUTH, poseStack, multiBufferSource,
                    packedLight, packedOverlay, itemStack.getItem(), Items.AIR);
        }
    }

    public static class BenchItemRenderer extends ItemStackRenderer {

        @Override
        public void renderByItem(ItemStack itemStack, ItemTransforms.TransformType transformType, PoseStack poseStack,
                                 MultiBufferSource multiBufferSource, int packedLight, int packedOverlay) {
            WoodenBenchModel model = new WoodenBenchModel(Minecraft.getInstance().getEntityModels().bakeLayer(WoodenBenchModel.LAYER_LOCATION_SINGLE));
            poseStack.pushPose();
            poseStack.scale(0.75F, 0.75F, 0.75F);
            OptimizedBenchRenderer.INSTANCE.doRender(model, Direction.SOUTH, CouchShape.SINGLE,
                    poseStack, multiBufferSource, packedLight, packedOverlay, Items.AIR, itemStack.getItem());
            poseStack.popPose();
        }
    }

    public static class CouchItemRenderer extends ItemStackRenderer {

        @Override
        public void renderByItem(ItemStack itemStack, ItemTransforms.TransformType transformType, PoseStack poseStack,
                                 MultiBufferSource multiBufferSource, int packedLight, int packedOverlay) {
            CouchModel model = new CouchModel(Minecraft.getInstance().getEntityModels().bakeLayer(CouchModel.LAYER_LOCATION_SINGLE));
            Item whiteCushion = Registry.ITEM.get(new ResourceLocation(Handcrafted.MOD_ID, "white_cushion"));

            poseStack.pushPose();
            poseStack.scale(0.9f, 0.9f, 0.9f);
            poseStack.translate(0, 0, 0.12);
            OptimizedCouchRenderer.INSTANCE.doRender(model, Direction.SOUTH, CouchShape.SINGLE,
                    poseStack, multiBufferSource, packedLight, packedOverlay, itemStack.getItem(), whiteCushion);
            poseStack.popPose();
        }
    }

    public static class FancyBedItemRenderer extends ItemStackRenderer {

        @Override
        public void renderByItem(ItemStack itemStack, ItemTransforms.TransformType transformType, PoseStack poseStack,
                                 MultiBufferSource multiBufferSource, int packedLight, int packedOverlay) {
            FancyBedModel model = new FancyBedModel(Minecraft.getInstance().getEntityModels().bakeLayer(FancyBedModel.LAYER_LOCATION_SINGLE));
            Item sheet = Registry.ITEM.get(new ResourceLocation(Handcrafted.MOD_ID, "white_sheet"));
            Item cushion = Registry.ITEM.get(new ResourceLocation(Handcrafted.MOD_ID, "white_cushion"));

            poseStack.pushPose();
            poseStack.scale(0.75f, 0.75f, 0.75f);
            poseStack.translate(0, 0, -0.1);
            OptimizedFancyBedRenderer.INSTANCE.doRender(DirectionalBlockSide.SINGLE, sheet, cushion, itemStack.getItem(),
                    model, Direction.SOUTH, poseStack, multiBufferSource, packedLight, packedOverlay);
            poseStack.popPose();
        }
    }

    public static class DiningBenchItemRenderer extends ItemStackRenderer {

        @Override
        public void renderByItem(ItemStack itemStack, ItemTransforms.TransformType transformType, PoseStack poseStack,
                                 MultiBufferSource multiBufferSource, int packedLight, int packedOverlay) {
            DiningBenchModel model = new DiningBenchModel(Minecraft.getInstance().getEntityModels().bakeLayer(DiningBenchModel.LAYER_LOCATION_SINGLE));

            OptimizedDiningBenchRenderer.INSTANCE.doRender(itemStack.getItem(), model, Direction.SOUTH, poseStack,
                    multiBufferSource, packedLight, packedOverlay);
        }
    }

    public static class NightstandItemRenderer extends ItemStackRenderer {
        @Override
        public void renderByItem(ItemStack itemStack, ItemTransforms.TransformType transformType, PoseStack poseStack,
                                 MultiBufferSource multiBufferSource, int packedLight, int packedOverlay) {
            OptimizedNightstandRenderer.INSTANCE.doRender(Direction.SOUTH, poseStack, multiBufferSource,
                    packedLight, packedOverlay, Items.AIR, itemStack.getItem());
        }
    }

    public static class DeskItemRenderer extends ItemStackRenderer {
        @Override
        public void renderByItem(ItemStack itemStack, ItemTransforms.TransformType transformType, PoseStack poseStack,
                                 MultiBufferSource multiBufferSource, int packedLight, int packedOverlay) {
            OptimizedDeskRenderer.INSTANCE.doRender(Direction.SOUTH, poseStack, multiBufferSource,
                    packedLight, packedOverlay, Items.AIR, itemStack.getItem());
        }
    }

    public static class SideTableItemRenderer extends ItemStackRenderer {

        @Override
        public void renderByItem(ItemStack itemStack, ItemTransforms.TransformType transformType, PoseStack poseStack,
                                 MultiBufferSource multiBufferSource, int packedLight, int packedOverlay) {

            OptimizedSideTableRenderer.INSTANCE.doRender(Direction.SOUTH, poseStack, multiBufferSource,
                    packedLight, packedOverlay, Items.AIR, itemStack.getItem());
        }
    }

    public static class CounterItemRenderer extends ItemStackRenderer {

        @Override
        public void renderByItem(ItemStack itemStack, ItemTransforms.TransformType transformType, PoseStack poseStack,
                                 MultiBufferSource multiBufferSource, int packedLight, int packedOverlay) {
            var blockId = Registry.ITEM.getKey(itemStack.getItem());

            OptimizedCounterRenderer.INSTANCE.doRender(new ResourceLocation("calcite"), blockId,
                    Direction.NORTH, poseStack, multiBufferSource, packedLight, packedOverlay);
        }
    }

}
