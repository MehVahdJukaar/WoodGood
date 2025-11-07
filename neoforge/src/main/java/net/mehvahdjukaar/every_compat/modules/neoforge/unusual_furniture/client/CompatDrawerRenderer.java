package net.mehvahdjukaar.every_compat.modules.neoforge.unusual_furniture.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.mehvahdjukaar.every_compat.modules.neoforge.unusual_furniture.compat_entity.CompatDrawerBlockEntity;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.toopa.unusualfurniture.block.JungleDrawerBlock;
import net.toopa.unusualfurniture.client.model.Modeljavadrawer;
import net.toopa.unusualfurniture.client.model.animations.javadrawerAnimation;
import net.toopa.unusualfurniture.procedures.OakDrawerClosePlaybackConditionProcedure;
import net.toopa.unusualfurniture.procedures.OakDrawerOpenPlaybackConditionProcedure;
import org.jetbrains.annotations.NotNull;

import static net.mehvahdjukaar.every_compat.modules.neoforge.unusual_furniture.UnusualFurnitureModule.BLOCK_TO_TEXTURE_MAP;

@OnlyIn(Dist.CLIENT)
public class CompatDrawerRenderer implements BlockEntityRenderer<CompatDrawerBlockEntity> {
        private final CustomHierarchicalModel model;
//        private final ResourceLocation texture;

        public CompatDrawerRenderer(BlockEntityRendererProvider.Context context, WoodType woodType, String shortenedId) {
            super();
            this.model = new CustomHierarchicalModel(context.bakeLayer(Modeljavadrawer.LAYER_LOCATION));
//            String textureLocation = woodType.createFullIdWith(EveryCompat.MOD_ID, "textures/block", shortenedId, "java_drawer", ".png");

//            this.texture = ResourceLocation.parse(textureLocation);
        }

        private void updateRenderState(CompatDrawerBlockEntity blockEntity) {
            int tickCount = (int) blockEntity.getLevel().getGameTime();
            blockEntity.animationState0.animateWhen(OakDrawerOpenPlaybackConditionProcedure.execute(blockEntity.getLevel(), blockEntity.getBlockPos().getX(), blockEntity.getBlockPos().getY(), blockEntity.getBlockPos().getZ()), tickCount);
            blockEntity.animationState1.animateWhen(OakDrawerClosePlaybackConditionProcedure.execute(blockEntity.getLevel(), blockEntity.getBlockPos().getX(), blockEntity.getBlockPos().getY(), blockEntity.getBlockPos().getZ()), tickCount);
        }

        public void render(@NotNull CompatDrawerBlockEntity blockEntity, float partialTick, PoseStack poseStack, @NotNull MultiBufferSource renderer, int light, int overlayLight) {
            this.updateRenderState(blockEntity);

            ResourceLocation textureLocation = BLOCK_TO_TEXTURE_MAP.get(blockEntity.getBlockState().getBlock());

            poseStack.pushPose();
            poseStack.scale(-1.0F, -1.0F, 1.0F);
            poseStack.translate(-0.5F, -0.5F, (double)0.5F);
            BlockState state = blockEntity.getBlockState();
            Direction facing = state.getValue(JungleDrawerBlock.FACING);
            switch (facing) {
                case EAST:
                    poseStack.mulPose(Axis.YP.rotationDegrees(90.0F));
                    break;
                case WEST:
                    poseStack.mulPose(Axis.YP.rotationDegrees(-90.0F));
                    break;
                case SOUTH:
                    poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
                case NORTH:
                default:
                    break;
            }

            poseStack.translate(0.0F, -1.0F, 0.0F);
            VertexConsumer builder = renderer.getBuffer(RenderType.entityCutout(textureLocation));
            this.model.setupBlockEntityAnim(blockEntity, (float) blockEntity.getLevel().getGameTime() + partialTick);
            this.model.renderToBuffer(poseStack, builder, light, overlayLight);
            poseStack.popPose();
        }

        private static final class CustomHierarchicalModel extends Modeljavadrawer {
            private final ModelPart root;
            private final CustomHierarchicalModel.BlockEntityHierarchicalModel animator = new CustomHierarchicalModel.BlockEntityHierarchicalModel();

            public CustomHierarchicalModel(ModelPart root) {
                super(root);
                this.root = root;
            }

            public void setupBlockEntityAnim(CompatDrawerBlockEntity blockEntity, float ageInTicks) {
                this.animator.setupBlockEntityAnim(blockEntity, ageInTicks);
                super.setupAnim(null, 0.0F, 0.0F, ageInTicks, 0.0F, 0.0F);
            }

            public ModelPart getRoot() {
                return this.root;
            }

            private class BlockEntityHierarchicalModel extends HierarchicalModel<Entity> {
                private BlockEntityHierarchicalModel() {
                    super();
                }

                public @NotNull ModelPart root() {
                    return CustomHierarchicalModel.this.root;
                }

                public void setupAnim(@NotNull Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
                }

                public void setupBlockEntityAnim(CompatDrawerBlockEntity blockEntity, float ageInTicks) {
                    CustomHierarchicalModel.this.animator.root().getAllParts().forEach(ModelPart::resetPose);
                    CustomHierarchicalModel.this.animator.animate(blockEntity.animationState0, javadrawerAnimation.Open, ageInTicks, 1.0F);
                    CustomHierarchicalModel.this.animator.animate(blockEntity.animationState1, javadrawerAnimation.Close, ageInTicks, 1.0F);
                }
            }
        }
    }
