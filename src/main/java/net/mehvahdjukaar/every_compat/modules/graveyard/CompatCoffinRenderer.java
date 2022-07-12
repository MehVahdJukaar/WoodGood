package net.mehvahdjukaar.every_compat.modules.graveyard;

import com.finallion.graveyard.blockentities.SarcophagusBlockEntity;
import com.finallion.graveyard.blockentities.enums.SarcophagusPart;
import com.finallion.graveyard.blocks.SarcophagusBlock;
import com.finallion.graveyard.init.TGTileEntities;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import it.unimi.dsi.fastutil.floats.Float2FloatFunction;
import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.ModelBlockRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BrightnessCombiner;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.model.data.EmptyModelData;

@OnlyIn(Dist.CLIENT)
public class CompatCoffinRenderer<T extends BlockEntity & LidBlockEntity> implements BlockEntityRenderer<GraveyardModule.CompatCoffinBlockTile> {
    public static final ResourceLocation SARCOPHAGUS_FOOT = new ResourceLocation("graveyard", "block/sarcophagus_foot");
    public static final ResourceLocation SARCOPHAGUS_FOOT_LID = new ResourceLocation("graveyard", "block/sarcophagus_foot_lid");
    public static final ResourceLocation SARCOPHAGUS_HEAD_LID = new ResourceLocation("graveyard", "block/sarcophagus_head_lid");
    public static final ResourceLocation SARCOPHAGUS_HEAD = new ResourceLocation("graveyard", "block/sarcophagus_head");
    private final BakedModel sarcophagusModelHead;
    private final BakedModel sarcophagusModelFoot;
    private final BakedModel sarcophagusModelFootLid;
    private final BakedModel sarcophagusModelHeadLid;

    public CompatCoffinRenderer(BlockEntityRendererProvider.Context ctx) {
        Minecraft client = Minecraft.getInstance();
        this.sarcophagusModelHead = client.getModelManager().getModel(SARCOPHAGUS_HEAD);
        this.sarcophagusModelFoot = client.getModelManager().getModel(SARCOPHAGUS_FOOT);
        this.sarcophagusModelFootLid = client.getModelManager().getModel(SARCOPHAGUS_FOOT_LID);
        this.sarcophagusModelHeadLid = client.getModelManager().getModel(SARCOPHAGUS_HEAD_LID);
    }

    public void render(GraveyardModule.CompatCoffinBlockTile entity, float tickDelta, PoseStack matrixStack, MultiBufferSource vertexConsumers, int light, int overlay) {
        Level world = entity.getLevel();
        BlockState blockState = entity.getBlockState();
        String name = entity.getBlockState().getBlock().getDescriptionId();
        boolean isCoffin = entity.isCoffin();
        DoubleBlockCombiner.NeighborCombineResult<? extends SarcophagusBlockEntity> propertySource = DoubleBlockCombiner.combineWithNeigbour((BlockEntityType)TGTileEntities.SARCOPHAGUS_BLOCK_ENTITY.get(), SarcophagusBlock::getBlockType, SarcophagusBlock::getConnectedDirection, ChestBlock.FACING, blockState, world, entity.getBlockPos(), (worldx, pos) -> {
            return false;
        });
        float g = ((Float2FloatFunction)propertySource.apply(SarcophagusBlock.opennessCombiner(entity))).get(tickDelta);
        g = 1.0F - g;
        g = 1.0F - g * g * g;
        WoodType wood = entity.getWoodType();
        int k = ((Int2IntFunction)propertySource.apply(new BrightnessCombiner())).applyAsInt(light);
        BakedModel footLid = this.getModel(wood, isCoffin, 0);
        BakedModel headLid = this.getModel(wood, isCoffin, 1);
        BakedModel head = this.getModel(wood, isCoffin, 2);
        BakedModel foot = this.getModel(wood, isCoffin, 3);
        if (world != null) {
            if (isCoffin) {
                this.renderPart(entity, matrixStack, vertexConsumers, blockState.getValue(SarcophagusBlock.PART) == SarcophagusPart.HEAD ? head : foot, (Direction)blockState.getValue(SarcophagusBlock.FACING), vertexConsumers.getBuffer(ItemBlockRenderTypes.getRenderType(entity.getBlockState(), true)), k, overlay, false);
                this.renderLid(entity, matrixStack, vertexConsumers, blockState.getValue(SarcophagusBlock.PART) == SarcophagusPart.HEAD ? headLid : footLid, Direction.SOUTH, vertexConsumers.getBuffer(ItemBlockRenderTypes.getRenderType(entity.getBlockState(), true)), k, overlay, false, g);
            } else {
                this.renderPart(entity, matrixStack, vertexConsumers, blockState.getValue(SarcophagusBlock.PART) == SarcophagusPart.HEAD ? this.sarcophagusModelHead : this.sarcophagusModelFoot, (Direction)blockState.getValue(SarcophagusBlock.FACING), vertexConsumers.getBuffer(ItemBlockRenderTypes.getRenderType(entity.getBlockState(), true)), k, overlay, false);
                this.renderLid(entity, matrixStack, vertexConsumers, blockState.getValue(SarcophagusBlock.PART) == SarcophagusPart.HEAD ? this.sarcophagusModelHeadLid : this.sarcophagusModelFootLid, Direction.SOUTH, vertexConsumers.getBuffer(ItemBlockRenderTypes.getRenderType(entity.getBlockState(), true)), k, overlay, false, g);
            }
        } else if (isCoffin) {
            this.renderLid(entity, matrixStack, vertexConsumers, footLid, Direction.SOUTH, vertexConsumers.getBuffer(ItemBlockRenderTypes.getRenderType(entity.getBlockState(), true)), k, overlay, true, g);
            this.renderPart(entity, matrixStack, vertexConsumers, foot, Direction.SOUTH, vertexConsumers.getBuffer(ItemBlockRenderTypes.getRenderType(entity.getBlockState(), true)), k, overlay, true);
            this.renderLid(entity, matrixStack, vertexConsumers, headLid, Direction.SOUTH, vertexConsumers.getBuffer(ItemBlockRenderTypes.getRenderType(entity.getBlockState(), true)), k, overlay, false, g);
            this.renderPart(entity, matrixStack, vertexConsumers, head, Direction.SOUTH, vertexConsumers.getBuffer(ItemBlockRenderTypes.getRenderType(entity.getBlockState(), true)), k, overlay, false);
        } else {
            this.renderLid(entity, matrixStack, vertexConsumers, this.sarcophagusModelFootLid, Direction.SOUTH, vertexConsumers.getBuffer(ItemBlockRenderTypes.getRenderType(entity.getBlockState(), true)), k, overlay, true, g);
            this.renderPart(entity, matrixStack, vertexConsumers, this.sarcophagusModelFoot, Direction.SOUTH, vertexConsumers.getBuffer(ItemBlockRenderTypes.getRenderType(entity.getBlockState(), true)), k, overlay, true);
            this.renderLid(entity, matrixStack, vertexConsumers, this.sarcophagusModelHeadLid, Direction.SOUTH, vertexConsumers.getBuffer(ItemBlockRenderTypes.getRenderType(entity.getBlockState(), true)), k, overlay, false, g);
            this.renderPart(entity, matrixStack, vertexConsumers, this.sarcophagusModelHead, Direction.SOUTH, vertexConsumers.getBuffer(ItemBlockRenderTypes.getRenderType(entity.getBlockState(), true)), k, overlay, false);
        }

    }

    private void renderPart(SarcophagusBlockEntity entity, PoseStack matrices, MultiBufferSource vertexConsumers, BakedModel model, Direction direction, VertexConsumer vertexConsumer, int light, int overlay, boolean isFoot) {
        ModelBlockRenderer renderer = Minecraft.getInstance().getBlockRenderer().getModelRenderer();
        Level world = entity.getLevel();
        matrices.pushPose();
        matrices.translate(0.0, 0.0, isFoot ? -1.0 : 0.0);
        float f = ((Direction)entity.getBlockState().getValue(SarcophagusBlock.FACING)).getOpposite().toYRot();
        matrices.translate(0.5, 0.5, 0.5);
        matrices.mulPose(Vector3f.YP.rotationDegrees(-f));
        matrices.translate(-0.5, -0.5, -0.5);
        renderer.renderModel(matrices.last(), vertexConsumer, entity.getBlockState(), model, 1.0F, 1.0F, 1.0F, light, overlay, EmptyModelData.INSTANCE);
        matrices.popPose();
    }

    private void renderLid(SarcophagusBlockEntity entity, PoseStack matrices, MultiBufferSource vertexConsumers, BakedModel model, Direction direction, VertexConsumer vertexConsumer, int light, int overlay, boolean isFoot, float openFactor) {
        ModelBlockRenderer renderer = Minecraft.getInstance().getBlockRenderer().getModelRenderer();
        Level world = entity.getLevel();
        matrices.pushPose();
        matrices.translate(0.0, 0.0, isFoot ? -1.0 : 0.0);
        float f = ((Direction)entity.getBlockState().getValue(SarcophagusBlock.FACING)).getOpposite().toYRot();
        matrices.translate(0.5, 0.5, 0.5);
        matrices.mulPose(Vector3f.YP.rotationDegrees(-f));
        matrices.mulPose(Vector3f.ZN.rotationDegrees(openFactor * 70.0F));
        matrices.translate(isFoot ? -((double)openFactor * 0.25) : (double)openFactor * 0.25, (double)openFactor * 0.25, 0.0);
        matrices.translate(-0.5, -0.5, -0.5);
        renderer.renderModel(matrices.last(), vertexConsumer, entity.getBlockState(), model, 1.0F, 1.0F, 1.0F, light, overlay, EmptyModelData.INSTANCE);
        matrices.popPose();
    }

    private BakedModel getModel(WoodType w, boolean isCoffin, int part) {
        Minecraft client = Minecraft.getInstance();
        if (isCoffin) {
            String woodType = w.getAppendableId()+"_coffin";
            return switch (part) {
                case 1 ->
                        client.getModelManager().getModel(new ResourceLocation("everycomp", "block/gy/" + woodType + "_foot_lid"));
                case 2 ->
                        client.getModelManager().getModel(new ResourceLocation("everycomp", "block/gy/" + woodType + "_foot"));
                case 3 ->
                        client.getModelManager().getModel(new ResourceLocation("everycomp", "block/gy/" + woodType + "_head"));
                default ->
                        client.getModelManager().getModel(new ResourceLocation("everycomp", "block/gy/" + woodType + "_head_lid"));
            };
        } else {
            return client.getModelManager().getModel(new ResourceLocation("graveyard", "block/oak_coffin_head"));
        }
    }
}
