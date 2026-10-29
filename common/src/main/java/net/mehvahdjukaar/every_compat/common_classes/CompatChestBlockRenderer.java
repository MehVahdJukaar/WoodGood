package net.mehvahdjukaar.every_compat.common_classes;

import com.mojang.blaze3d.vertex.PoseStack;
import net.mehvahdjukaar.candlelight.api.ClientOnly;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.misc.HardcodedBlockType;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.client.model.object.chest.ChestModel;
import net.minecraft.client.renderer.MultiblockChestResources;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.renderer.blockentity.state.ChestRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.sprite.SpriteGetter;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

@ClientOnly
public class CompatChestBlockRenderer extends ChestRenderer<CompatChestBlockEntity> {
    public static final Identifier CHEST_SHEET = Identifier.withDefaultNamespace("textures/atlas/chest.png");
    private final Map<WoodType, MultiblockChestResources<SpriteId>> regular = new HashMap<>();
    private final Map<WoodType, MultiblockChestResources<SpriteId>> trapped = new HashMap<>();
    private final SpriteGetter sprites;
    private final MultiblockChestResources<ChestModel> models;
    private final boolean isChristmas;

    //assumes standard naming here. Generalize if needed
    public CompatChestBlockRenderer(BlockEntityRendererProvider.Context context, String shortenedId) {
        super(context);
        this.sprites = context.sprites();
        this.models = LAYERS.map(layer -> new ChestModel(context.bakeLayer(layer)));
        this.isChristmas = shortenedId.equals("abnww") && xmasTextures();

        for (WoodType w : WoodTypeRegistry.INSTANCE) {
            if (HardcodedBlockType.isKnownVanillaWood(w)) continue; // minecraft:pale_oak is from Perfect Parity: The Garden Awakens Edition
            String path = "entity/chest/" + shortenedId + "/" + w.getAppendableId() + "_chest";
            String trapped_path = "entity/chest/" + shortenedId + "/" + w.getAppendableId() + "_trapped_chest";

            regular.put(w, chestSprites(path));
            trapped.put(w, chestSprites(trapped_path));
        }
    }

    private static MultiblockChestResources<SpriteId> chestSprites(String path) {
        return new MultiblockChestResources<>(
                new SpriteId(CHEST_SHEET, EveryCompat.res(path)),
                new SpriteId(CHEST_SHEET, EveryCompat.res(path + "_left")),
                new SpriteId(CHEST_SHEET, EveryCompat.res(path + "_right")));
    }

    protected SpriteId getSprite(CompatChestBlockEntity blockEntity, ChestType chestType) {
        WoodType w = blockEntity.getWoodType();
        if (blockEntity.isTrapped()) return trapped.get(w).select(chestType);
        if (isChristmas) return Sheets.chooseSprite(ChestRenderState.ChestMaterialType.CHRISTMAS, chestType);
        return regular.get(w).select(chestType);
    }

    @Override
    public ChestRenderState createRenderState() {
        return new CompatChestRenderState();
    }

    @Override
    public void extractRenderState(CompatChestBlockEntity blockEntity, ChestRenderState state, float partialTicks,
                                   Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
        super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);
        ((CompatChestRenderState) state).sprite = getSprite(blockEntity, state.type);
    }

    //copy pasted from ChestRenderer

    @Override
    public void submit(ChestRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        poseStack.pushPose();
        poseStack.mulPose(modelTransformation(state.facing));
        float open = state.open;
        open = 1.0F - open;
        open = 1.0F - open * open * open;
        ChestModel model = this.models.select(state.type);
        submitNodeCollector.submitModel(
                model, open, poseStack, state.lightCoords, OverlayTexture.NO_OVERLAY, -1, ((CompatChestRenderState) state).sprite, this.sprites, 0, state.breakProgress
        );
        poseStack.popPose();
    }

    public static void register(ClientHelper.BlockEntityRendererEvent event, BlockEntityType<CompatChestBlockEntity> tile, String s) {
        event.register(tile, c -> new CompatChestBlockRenderer(c, s));
    }

    public static class CompatChestRenderState extends ChestRenderState {
        public SpriteId sprite;
    }
}
