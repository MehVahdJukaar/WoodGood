package net.mehvahdjukaar.every_compat.misc;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.math.Axis;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.AbstractSimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.CompatModule;
import net.mehvahdjukaar.every_compat.api.EntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.client.gui.ConfigScreenExtensions;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;

import java.util.ArrayList;
import java.util.List;

public class FurnitureShowcaseWidget extends AbstractWidget {

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

    private static final ItemStack BARRIER = Items.BARRIER.getDefaultInstance();

    private static final float SECONDS_PER_TURN = 8f;
    private static final float TILT = 15f;
    private static final float BLOCK_FILL = 0.62f;

    private static final int TEXTURE_PIXELS = 16;
    private static final int CHIPS_PER_CLICK = 14;
    private static final int CHIP_SIZE = 3;
    private static final int CHIP_DEPTH = 300;
    private static final float CHIP_FADE = 0.25f;      // last fraction of the life spent fading out
    private static final float CHIP_GRAVITY = 260f;    // px per second squared
    private static final float MIN_CHIP_SPEED = 30f;   // px per second
    private static final float MAX_CHIP_SPEED = 90f;
    private static final float MIN_CHIP_LIFE = 0.35f;  // seconds
    private static final float MAX_CHIP_LIFE = 0.8f;

    private final RandomSource random = RandomSource.create();
    private final List<Chip> chips = new ArrayList<>();

    private @Nullable List<List<Block>> woodSets;
    private List<Block> currentSet = List.of();
    private @Nullable BlockState state;
    private @Nullable TextureAtlasSprite chipSprite;
    private float yaw;
    private long lastMs = -1;

    public FurnitureShowcaseWidget(int x, int y, int width, int height) {
        super(x, y, width, height, Component.empty());
    }

    @Override
    protected void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        if (this.woodSets == null) this.collectWoodSets();

        long now = Util.getMillis();
        float dt = this.lastMs < 0 ? 0 : Math.min((now - this.lastMs) / 1000f, 0.1f); // clamp screen-reopen gaps
        this.lastMs = now;

        if (this.state != null) {
            this.spin(dt);
            this.renderBlock(graphics, this.state);
        } else {
            this.renderBarrier(graphics);
        }
        this.renderChips(graphics, dt);
    }

    private void collectWoodSets() {
        List<List<Block>> found = new ArrayList<>();
        for (CompatModule module : EveryCompat.getActiveModules()) {
            if (!(module instanceof SimpleModule simpleModule)) continue;
            for (EntrySet<?> entry : simpleModule.getEntries()) {
                if (!(entry instanceof AbstractSimpleEntrySet<?, ?, ?> entrySet)) continue;
                if (entrySet.getTypeClass() != WoodType.class) continue;
                List<Block> variants = new ArrayList<>();
                for (Block block : entrySet.blocks.values()) {
                    if (block.defaultBlockState().getRenderShape() == RenderShape.MODEL) variants.add(block);
                }
                if (variants.size() > 1) found.add(List.copyOf(variants));
            }
        }
        this.woodSets = List.copyOf(found);
        this.pickNewFurniture();
    }

    private void pickNewFurniture() {
        if (this.woodSets == null || this.woodSets.isEmpty()) return;
        this.currentSet = this.woodSets.get(this.random.nextInt(this.woodSets.size()));
        this.setBlock(this.currentSet.get(this.random.nextInt(this.currentSet.size())));
    }

    private void pickNewWood() {
        if (this.currentSet.size() < 2) return;
        Block current = this.state == null ? null : this.state.getBlock();
        Block next;
        do {
            next = this.currentSet.get(this.random.nextInt(this.currentSet.size()));
        } while (next == current);
        this.setBlock(next);
    }

    private void setBlock(Block block) {
        this.state = block.defaultBlockState();
        this.chipSprite = Minecraft.getInstance().getBlockRenderer().getBlockModel(this.state).getParticleIcon();
        this.setMessage(block.getName());
        this.setTooltip(Tooltip.create(block.getName()));
    }

    private void spin(float dt) {
        float previous = this.yaw;
        this.yaw = (this.yaw + dt * 360f / SECONDS_PER_TURN) % 360f;
        boolean crossedHalfTurn = this.yaw < previous || (previous < 180f && this.yaw >= 180f);
        if (crossedHalfTurn) this.pickNewFurniture();
    }

    private void renderBlock(GuiGraphics graphics, BlockState state) {
        float size = Math.min(this.width, this.height) * BLOCK_FILL;

        PoseStack pose = graphics.pose();
        pose.pushPose();
        pose.translate(this.getX() + this.width / 2f, this.getY() + this.height / 2f, 150);
        pose.scale(size, -size, size);
        pose.mulPose(Axis.XP.rotationDegrees(TILT));
        pose.mulPose(Axis.YP.rotationDegrees(this.yaw));
        pose.translate(-0.5f, -0.5f, -0.5f); // the block renderer starts from the block corner

        Lighting.setupFor3DItems();
        MultiBufferSource.BufferSource buffer = graphics.bufferSource();
        Minecraft.getInstance().getBlockRenderer().renderSingleBlock(state, pose, buffer,
                LightTexture.FULL_BRIGHT, OverlayTexture.NO_OVERLAY);

        graphics.flush();
        pose.popPose();
    }

    // no wood mods installed, so there's nothing to show off. same barrier the all woods item falls back to
    private void renderBarrier(GuiGraphics graphics) {
        int size = Math.round(Math.min(this.width, this.height) * BLOCK_FILL);
        PoseStack pose = graphics.pose();
        pose.pushPose();
        pose.translate(this.getX() + (this.width - size) / 2f, this.getY() + (this.height - size) / 2f, 0);
        pose.scale(size / 16f, size / 16f, 1);
        graphics.renderFakeItem(BARRIER, 0, 0);
        pose.popPose();
    }

    private void renderChips(GuiGraphics graphics, float dt) {
        this.chips.removeIf(chip -> !chip.move(dt));
        if (this.chips.isEmpty()) return;

        PoseStack pose = graphics.pose();
        pose.pushPose();
        pose.translate(0, 0, CHIP_DEPTH); // the block is drawn at 150 and half its size deep, chips go in front of it
        graphics.enableScissor(this.getX(), this.getY(), this.getX() + this.width, this.getY() + this.height);

        // all chips are one quad off the block atlas, so they batch into a single draw. blit is no good here: it wants
        // uvs in atlas pixels, which means dividing the sprite's own uvs by the atlas size and multiplying them back
        RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
        RenderSystem.setShaderTexture(0, this.chips.getFirst().sprite.atlasLocation());
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        Matrix4f matrix = pose.last().pose();
        BufferBuilder buffer = Tesselator.getInstance().begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR);
        for (Chip chip : this.chips) {
            chip.addQuad(buffer, matrix);
        }
        BufferUploader.drawWithShader(buffer.buildOrThrow());
        RenderSystem.disableBlend();

        graphics.disableScissor();
        pose.popPose();
    }

    private void spawnChips() {
        if (this.chipSprite == null) return;
        float centerX = this.getX() + this.width / 2f;
        float centerY = this.getY() + this.height / 2f;
        // the texture is worth one 16x16 pixel per chip whatever the resource pack's resolution is
        float uStep = (this.chipSprite.getU1() - this.chipSprite.getU0()) / TEXTURE_PIXELS;
        float vStep = (this.chipSprite.getV1() - this.chipSprite.getV0()) / TEXTURE_PIXELS;
        for (int i = 0; i < CHIPS_PER_CLICK; i++) {
            float angle = this.random.nextFloat() * Mth.TWO_PI;
            float speed = Mth.randomBetween(this.random, MIN_CHIP_SPEED, MAX_CHIP_SPEED);
            Chip chip = new Chip(this.chipSprite);
            chip.x = centerX;
            chip.y = centerY;
            chip.velX = Mth.cos(angle) * speed;
            chip.velY = Mth.sin(angle) * speed - speed * 0.5f; // biased upwards so they arc back down
            chip.life = Mth.randomBetween(this.random, MIN_CHIP_LIFE, MAX_CHIP_LIFE);
            chip.u0 = this.chipSprite.getU0() + uStep * this.random.nextInt(TEXTURE_PIXELS);
            chip.v0 = this.chipSprite.getV0() + vStep * this.random.nextInt(TEXTURE_PIXELS);
            chip.u1 = chip.u0 + uStep;
            chip.v1 = chip.v0 + vStep;
            this.chips.add(chip);
        }
    }

    @Override
    public void onClick(double mouseX, double mouseY) {
        if (this.state == null) return;
        this.pickNewWood();
        this.spawnChips();
        SoundType sound = this.state.getSoundType();
        Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(sound.getBreakSound(),
                sound.getPitch() * 0.8f, (sound.getVolume() + 1f) / 2f));
    }

    @Override
    public void playDownSound(SoundManager handler) {
        // the block's own break sound is the click feedback
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput output) {
        output.add(NarratedElementType.TITLE, this.getMessage());
    }

    private static class Chip {
        // kept per chip so the ones still in the air aren't retextured when the wood changes under them
        private final TextureAtlasSprite sprite;
        private float x;
        private float y;
        private float velX;
        private float velY;
        private float life;
        private float age;
        private float u0;
        private float v0;
        private float u1;
        private float v1;

        private Chip(TextureAtlasSprite sprite) {
            this.sprite = sprite;
        }

        // false once it has outlived its life
        private boolean move(float dt) {
            this.age += dt;
            if (this.age > this.life) return false;
            this.velY += CHIP_GRAVITY * dt;
            this.x += this.velX * dt;
            this.y += this.velY * dt;
            return true;
        }

        private void addQuad(BufferBuilder buffer, Matrix4f matrix) {
            float left = Mth.floor(this.x);
            float top = Mth.floor(this.y);
            float right = left + CHIP_SIZE;
            float bottom = top + CHIP_SIZE;
            float alpha = Math.min(1f, (1f - this.age / this.life) / CHIP_FADE);
            buffer.addVertex(matrix, left, top, 0).setUv(this.u0, this.v0).setColor(1f, 1f, 1f, alpha);
            buffer.addVertex(matrix, left, bottom, 0).setUv(this.u0, this.v1).setColor(1f, 1f, 1f, alpha);
            buffer.addVertex(matrix, right, bottom, 0).setUv(this.u1, this.v1).setColor(1f, 1f, 1f, alpha);
            buffer.addVertex(matrix, right, top, 0).setUv(this.u1, this.v0).setColor(1f, 1f, 1f, alpha);
        }
    }
}
