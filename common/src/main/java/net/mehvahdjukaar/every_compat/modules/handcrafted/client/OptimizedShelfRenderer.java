package net.mehvahdjukaar.every_compat.modules.handcrafted.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import com.teamresourceful.resourcefullib.client.CloseablePoseStack;
import earth.terrarium.handcrafted.client.block.counter.ShelfRenderer;
import earth.terrarium.handcrafted.common.block.counter.ShelfBlock;
import earth.terrarium.handcrafted.common.block.counter.ShelfBlockEntity;
import earth.terrarium.handcrafted.common.block.crockery.CrockeryBlock;
import earth.terrarium.handcrafted.common.block.pot.PotBlock;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.mehvahdjukaar.every_compat.modules.handcrafted.HandcraftedModule;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WebBlock;

@Environment(EnvType.CLIENT)
public class OptimizedShelfRenderer implements BlockEntityRenderer<HandcraftedModule.compatShelfEntity> {
    public OptimizedShelfRenderer(BlockEntityRendererProvider.Context ctx) {
    }

    @Override
    public void render(HandcraftedModule.compatShelfEntity entity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        if (entity.getStack().isEmpty()) return;
        if (entity.getLevel() == null) return;

        Item item = entity.getStack().getItem();
        Block block = Registry.BLOCK.get(Registry.ITEM.getKey(item));
        Direction dir = entity.getBlockState().getValue(ShelfBlock.FACING);
        ResourceLocation id;
        try (var ignored = new CloseablePoseStack(poseStack)) {
            poseStack.mulPose(Vector3f.YN.rotationDegrees(dir.toYRot()));
            poseStack.translate(0, 1.0, 0);
            switch (dir) {
                case NORTH -> poseStack.translate(-1, 0, 0.001);
                case SOUTH -> poseStack.translate(0, 0, 1.001);
                case EAST -> poseStack.translate(-1, 0, 1.001);
                case WEST -> poseStack.translate(0, 0, 0.001);
            }
            poseStack.mulPose(Vector3f.XP.rotationDegrees(180));

            if (item instanceof BookItem || item instanceof EnchantedBookItem || item instanceof WritableBookItem || item instanceof WrittenBookItem) {
                id = ShelfRenderer.BOOKS;
            } else if (block instanceof WebBlock) {
                id = ShelfRenderer.COBWEBS;
            } else if (item instanceof PotionItem || item instanceof BottleItem) {
                id = ShelfRenderer.POTIONS;
            } else if (block instanceof PotBlock || block instanceof CrockeryBlock) {
                id = ShelfRenderer.POTS;
            } else {
                return;
            }

            VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutout(new ResourceLocation(id.getNamespace(), id.getPath().replace(".png", "_" + entity.getBlockState().getValue(ShelfBlock.SHELF_SHAPE).toString().toLowerCase() + ".png"))));
            int light = LevelRenderer.getLightColor(entity.getLevel(), entity.getBlockPos().relative(entity.getBlockState().getValue(ShelfBlock.FACING)));

            renderShelfOverlay(poseStack, vertexConsumer, dir, light, packedOverlay);
        }
    }

    private static void renderShelfOverlay(PoseStack poseStack, VertexConsumer consumer, Direction dir, int light, int overlay) {
        Vec3i normal = dir.getNormal();
        consumer.vertex(poseStack.last().pose(), 0, 0, 0).color(255, 255, 255, 255).uv(0, 0).overlayCoords(overlay).uv2(light).normal(poseStack.last().normal(), normal.getX(), normal.getY(), normal.getZ()).endVertex();
        consumer.vertex(poseStack.last().pose(), 0, 1, 0).color(255, 255, 255, 255).uv(0, 1).overlayCoords(overlay).uv2(light).normal(poseStack.last().normal(), normal.getX(), normal.getY(), normal.getZ()).endVertex();
        consumer.vertex(poseStack.last().pose(), 1, 1, 0).color(255, 255, 255, 255).uv(1, 1).overlayCoords(overlay).uv2(light).normal(poseStack.last().normal(), normal.getX(), normal.getY(), normal.getZ()).endVertex();
        consumer.vertex(poseStack.last().pose(), 1, 0, 0).color(255, 255, 255, 255).uv(1, 0).overlayCoords(overlay).uv2(light).normal(poseStack.last().normal(), normal.getX(), normal.getY(), normal.getZ()).endVertex();
    }
}
