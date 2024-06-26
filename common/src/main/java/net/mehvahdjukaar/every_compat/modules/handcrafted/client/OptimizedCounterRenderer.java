package net.mehvahdjukaar.every_compat.modules.handcrafted.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import earth.terrarium.handcrafted.Handcrafted;
import earth.terrarium.handcrafted.client.block.counter.CounterModel;
import earth.terrarium.handcrafted.common.block.chair.couch.ExpandableCouchBlock;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.mehvahdjukaar.every_compat.modules.handcrafted.HandcraftedModule;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

@Environment(EnvType.CLIENT)
public class OptimizedCounterRenderer implements BlockEntityRenderer<HandcraftedModule.compatCounterEntity> {
    public static final Map<ResourceLocation, Material> OBJECT_TO_TEXTURE = new Object2ObjectArrayMap<>();

    public static OptimizedCounterRenderer INSTANCE = null;
    private final CounterModel main;
    private final ModelPart base;
    private final ModelPart top;

    public OptimizedCounterRenderer(BlockEntityRendererProvider.Context ctx) {
        EntityModelSet modelSet = ctx.getModelSet();
        this.main = new CounterModel(modelSet.bakeLayer(CounterModel.LAYER_LOCATION));
        this.base = main.getMain().getChild("base");
        this.top = main.getMain().getChild("top");

        INSTANCE = this;
    }

    @Override
    public void render(HandcraftedModule.compatCounterEntity entity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource,
                       int packedLight, int packedOverlay) {
        ResourceLocation blockKey = Registry.BLOCK.getKey(entity.getBlockState().getBlock());

        doRender(Registry.ITEM.getKey(entity.getStack().getItem()), blockKey, entity.getBlockState().getValue(ExpandableCouchBlock.FACING),
                poseStack, bufferSource, packedLight, packedOverlay);
    }

    public void doRender(ResourceLocation overlay, ResourceLocation blockKey, Direction direction,
                         PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        poseStack.pushPose();
        poseStack.translate(0.5, 1.5, 0.5);
        poseStack.mulPose(Vector3f.YN.rotationDegrees(direction.toYRot()));
        poseStack.mulPose(Vector3f.XP.rotationDegrees(180));

        top.visible = false;
        base.visible = true;
        var blockTexture = OBJECT_TO_TEXTURE.get(blockKey);
        main.renderToBuffer(poseStack, blockTexture.buffer(buffer, RenderType::entityCutout), packedLight, packedOverlay,
                1.0f, 1.0f, 1.0f, 1.0f);
        top.visible = true;
        base.visible = false;
        if (!overlay.toString().equals("minecraft:air")) {
            main.renderToBuffer(poseStack, buffer.getBuffer(RenderType.entityCutout(
                    new ResourceLocation(Handcrafted.MOD_ID,
                            "textures/block/counter/counter/overlay/" + overlay.getPath() + ".png"))),
                    packedLight, packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        }
        poseStack.popPose();
    }

}
