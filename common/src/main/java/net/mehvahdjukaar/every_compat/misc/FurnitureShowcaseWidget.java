package net.mehvahdjukaar.every_compat.misc;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.AbstractSimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.CompatModule;
import net.mehvahdjukaar.every_compat.api.EntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.client.gui.ConfigScreenExtensions;
import net.mehvahdjukaar.moonlight.api.client.gui.FrameClock;
import net.mehvahdjukaar.moonlight.api.client.gui.particle.ScreenParticle;
import net.mehvahdjukaar.moonlight.api.client.gui.particle.ScreenParticleEngine;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.SpriteContents;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FastColor;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class FurnitureShowcaseWidget extends AbstractWidget {

    private static final ItemStack BARRIER = Items.BARRIER.getDefaultInstance();
    private static final float SECONDS_PER_TURN = 8f;
    private static final float BLOCK_FILL = 0.62f;
    private static final int CHIPS_PER_CLICK = 14;

    private final RandomSource random = RandomSource.create();
    private final ScreenParticleEngine chips = new ScreenParticleEngine();
    private final FrameClock clock = new FrameClock();

    @Nullable
    private List<List<Block>> woodSets;
    private List<Block> currentSet = List.of();
    @Nullable
    private BlockState state;
    private @Nullable TextureAtlasSprite chipSprite;
    private float yaw;

    public FurnitureShowcaseWidget(int x, int y, int width, int height) {
        super(x, y, width, height, Component.empty());
    }

    @Override
    protected void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        if (woodSets == null) collectWoodSets();

        if (state != null) {
            spin(clock.advance());
            renderBlock(graphics, state);
        } else {
            renderBarrier(graphics);
        }
        renderChips(graphics);
    }

    private void collectWoodSets() {
        List<List<Block>> found = new ArrayList<>();
        for (CompatModule module : EveryCompat.getActiveModules()) {
            if (!(module instanceof SimpleModule simpleModule)) continue;

            for (EntrySet<?> entry : simpleModule.getEntries()) {
                if (!(entry instanceof AbstractSimpleEntrySet<?, ?, ?> set)) continue;
                if (set.getTypeClass() != WoodType.class) {
                    continue;
                }

                List<Block> variants = new ArrayList<>();
                for (Block b : set.blocks.values()) {
                    if (b.defaultBlockState().getRenderShape() == RenderShape.MODEL) variants.add(b);
                }
                if (variants.size() > 1) found.add(List.copyOf(variants));
            }
        }
        this.woodSets = List.copyOf(found);
        pickNewFurniture();
    }

    private void pickNewFurniture() {
        if (woodSets == null || woodSets.isEmpty()) return;
        this.currentSet = woodSets.get(random.nextInt(woodSets.size()));
        setBlock(currentSet.get(random.nextInt(currentSet.size())));
    }

    private void pickNewWood() {
        if (currentSet.size() < 2) return;
        Block current = (state == null) ? null : state.getBlock();

        Block next;
        do {
            next = currentSet.get(random.nextInt(currentSet.size()));
        } while (next == current);
        setBlock(next);
    }

    private void setBlock(Block block) {
        this.state = block.defaultBlockState();
        this.chipSprite = Minecraft.getInstance().getBlockRenderer().getBlockModel(this.state).getParticleIcon();
        setMessage(block.getName());
        setTooltip(Tooltip.create(block.getName()));
    }

    private void spin(float dt) {
        float previous = yaw;
        yaw = (yaw + dt * 360f / SECONDS_PER_TURN) % 360f;
        if (yaw < previous || (previous < 180f && yaw >= 180f)) {
            pickNewFurniture();
        }
    }

    private void renderBlock(GuiGraphics graphics, BlockState blockState) {
        float size = Math.min(width, height) * BLOCK_FILL;

        PoseStack pose = graphics.pose();
        pose.pushPose();
        pose.translate(getX() + width / 2f, getY() + height / 2f, 150);
        pose.scale(size, -size, size);
        pose.mulPose(Axis.XP.rotationDegrees(15));
        pose.mulPose(Axis.YP.rotationDegrees(this.yaw));
        pose.translate(-0.5f, -0.5f, -0.5f);

        Lighting.setupFor3DItems();
        MultiBufferSource.BufferSource buffer = graphics.bufferSource();
        Minecraft.getInstance().getBlockRenderer().renderSingleBlock(blockState, pose, buffer,
                LightTexture.FULL_BRIGHT, OverlayTexture.NO_OVERLAY);

        graphics.flush();
        pose.popPose();
    }

    private void renderBarrier(GuiGraphics graphics) {
        int size = Math.round(Math.min(width, height) * BLOCK_FILL);
        PoseStack pose = graphics.pose();
        pose.pushPose();
        pose.translate(getX() + (width - size) / 2f, getY() + (height - size) / 2f, 0);
        pose.scale(size / 16f, size / 16f, 1);
        graphics.renderFakeItem(BARRIER, 0, 0);
        pose.popPose();
    }


    private void renderChips(GuiGraphics graphics) {
        if (chips.isEmpty()) return;
        PoseStack pose = graphics.pose();
        pose.pushPose();

        pose.translate(0, 0, 300);
        graphics.enableScissor(getX(), getY(), getX() + width, getY() + height);
        chips.renderAndTick(graphics);
        graphics.disableScissor();
        pose.popPose();
    }

    private void spawnChips() {
        if (chipSprite == null){
            return;
        }

        float centerX = getX() + width / 2f;
        float centerY = getY() + height / 2f;
        SpriteContents contents = chipSprite.contents();

        for (int i = 0; i < CHIPS_PER_CLICK; i++) {
            int pixel = ClientHelper.getPixelRGBA(chipSprite, 0, random.nextInt(contents.width()), random.nextInt(contents.height()));
            if (FastColor.ABGR32.alpha(pixel) == 0) continue;
            float angle = random.nextFloat() * Mth.TWO_PI;
            float speed = Mth.randomBetween(random, 30, 90);
            this.chips.add(ScreenParticle.square(centerX, centerY)
                    .velocity(Mth.cos(angle) * speed, Mth.sin(angle) * speed - speed * 0.5F)
                    .gravity(260)
                    .size(3)
                    .tint(FastColor.ABGR32.fromArgb32(pixel)) //just swaps red and blue so it works both ways
                    .fadeOut(0.75F)
                    .lifetime(Mth.randomBetween(random, 0.35F, 0.8F)));
        }
    }

    @Override
    public void onClick(double mouseX, double mouseY) {
        if (state == null) return;
        pickNewWood();
        spawnChips();

        SoundType sound = state.getSoundType();
        Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(sound.getBreakSound(),
                sound.getPitch() * 0.8f, (sound.getVolume() + 1f) / 2f));
    }

    @Override
    public void playDownSound(SoundManager handler) {
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput output) {
        output.add(NarratedElementType.TITLE, this.getMessage());
    }

    public static void register() {
        ConfigScreenExtensions.registerShowcase(EveryCompat.MOD_ID, new ConfigScreenExtensions.Showcase() {
            @Override
            public AbstractWidget create(String modId, int x, int y, int width, int maxHeight) {
                return new FurnitureShowcaseWidget(x, y, width, maxHeight);
            }

            @Override
            public boolean replacesCarousel() {
                return false;
            }
        });
    }
}
