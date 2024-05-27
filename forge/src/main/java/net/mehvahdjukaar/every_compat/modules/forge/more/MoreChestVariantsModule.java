package net.mehvahdjukaar.every_compat.modules.forge.more;

import com.google.gson.JsonObject;
import io.github.lieonlion.mcv.init.McvBlockInit;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.common_classes.CompatChestBlock;
import net.mehvahdjukaar.every_compat.common_classes.CompatChestBlockEntity;
import net.mehvahdjukaar.every_compat.common_classes.CompatChestBlockRenderer;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.resources.textures.Respriter;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.moonlight.api.util.math.colors.HCLColor;
import net.minecraft.client.resources.metadata.animation.AnimationMetadataSection;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.common.Tags;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class MoreChestVariantsModule extends SimpleModule {

        public final SimpleEntrySet<WoodType, Block> CHEST;
        public final SimpleEntrySet<WoodType, Block> TRAPPED_CHEST;

    public MoreChestVariantsModule(String modID) {
        super(modID, "mcv");

        CHEST = SimpleEntrySet.builder(WoodType.class, "chest",
                        McvBlockInit.OAK_CHEST, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CompatChestBlock(this::getChestTile,
                                Utils.copyPropertySafe(Blocks.CHEST).mapColor(MapColor.WOOD))
                )
                .addTile(moreChestBlockEntity::new)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.GUARDED_BY_PIGLINS, Registries.BLOCK)
                .addTag(modRes("chests/wooden"), Registries.BLOCK)
                .addTag(modRes("chests/normal"), Registries.BLOCK)
                .addTag(Tags.Blocks.CHESTS, Registries.BLOCK)
                .addTag(Tags.Blocks.CHESTS_WOODEN, Registries.BLOCK)
                .addTag(new ResourceLocation("quad", "cats_on_blocks/sit"), Registries.BLOCK)
                .addTag(new ResourceLocation("quad", "fuel/wood"), Registries.ITEM)
                .addTag(Tags.Items.CHESTS_WOODEN, Registries.ITEM)
                .addTag(Tags.Items.CHESTS, Registries.ITEM)
                .addTag(modRes("chests/normal"), Registries.ITEM)
                .addTag(modRes("chests/wooden"), Registries.ITEM)
                .build();
        this.addEntry(CHEST);

        TRAPPED_CHEST = SimpleEntrySet.builder(WoodType.class, "chest",
                        McvBlockInit.OAK_TRAPPED_CHEST, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CompatChestBlock(this::getTrappedTile,
                                Utils.copyPropertySafe(Blocks.TRAPPED_CHEST).mapColor(MapColor.WOOD))
                )
                .addTile(moreTrappedBlockEntity::new)
                .addTag(modRes("chests/wooden"), Registries.BLOCK)
                .addTag(modRes("chests/trapped"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.GUARDED_BY_PIGLINS, Registries.BLOCK)
                .addTag(Tags.Blocks.CHESTS, Registries.BLOCK)
                .addTag(Tags.Blocks.CHESTS_WOODEN, Registries.BLOCK)
                .addTag(new ResourceLocation("quad", "fuel/wood"), Registries.ITEM)
                .addTag(modRes("chests/trapped"), Registries.ITEM)
                .addTag(modRes("chests/wooden"), Registries.ITEM)
                .addTag(Tags.Items.CHESTS_WOODEN, Registries.ITEM)
                .addTag(Tags.Items.CHESTS, Registries.ITEM)
                .build();
        this.addEntry(TRAPPED_CHEST);

    }

    // GetTile -----------------------------------------------------------------------------------------------------------
    private BlockEntityType<? extends ChestBlockEntity> getChestTile() {
        return CHEST.getTile(CompatChestBlockEntity.class);
    }

    private BlockEntityType<? extends ChestBlockEntity> getTrappedTile() {
        return TRAPPED_CHEST.getTile(CompatChestBlockEntity.class);
    }

    // BlockEntity -----------------------------------------------------------------------------------------------------------
    private class moreChestBlockEntity extends CompatChestBlockEntity {
        public moreChestBlockEntity(BlockPos pos, BlockState state) {
            super(CHEST.getTile(), pos, state);
        }
    }

    private class moreTrappedBlockEntity extends CompatChestBlockEntity {
        public moreTrappedBlockEntity(BlockPos pos, BlockState state) {
            super(TRAPPED_CHEST.getTile(), pos, state);
        }
    }

    // Registry --------------------------------------------------------------------------------------------------------
    @Override
    public void registerBlockEntityRenderers(ClientHelper.BlockEntityRendererEvent event) {
        super.registerBlockEntityRenderers(event);
        event.register(CHEST.getTile(CompatChestBlockEntity.class), context -> new CompatChestBlockRenderer(context, shortenedId()));
        event.register(TRAPPED_CHEST.getTile(CompatChestBlockEntity.class), context -> new CompatChestBlockRenderer(context, shortenedId()));
    }

    @Deprecated(forRemoval = true)
    @Override
    // Textures
    public void addDynamicClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicClientResources(handler, manager);

        try (TextureImage normal = TextureImage.open(manager, modRes("entity/chest/oak"));
             TextureImage normal_m = TextureImage.open(manager, EveryCompat.res("entity/mcv_chest_normal_m"));
             TextureImage normal_o = TextureImage.open(manager, EveryCompat.res("entity/mcv_chest_normal_o"));
             // Left
             TextureImage left = TextureImage.open(manager, modRes("entity/chest/oak_left"));
             TextureImage left_m = TextureImage.open(manager, EveryCompat.res("entity/mcv_chest_left_m"));
             TextureImage left_o = TextureImage.open(manager, EveryCompat.res("entity/mcv_chest_left_o"));
             // Right
             TextureImage right = TextureImage.open(manager, modRes("entity/chest/oak_right"));
             TextureImage right_m = TextureImage.open(manager, EveryCompat.res("entity/mcv_chest_right_m"));
             TextureImage right_o = TextureImage.open(manager, EveryCompat.res("entity/mcv_chest_right_o"));
             // Trapped Overlay
             TextureImage left_t = TextureImage.open(manager, EveryCompat.res("model/trapped_chest_left"));
             TextureImage right_t = TextureImage.open(manager, EveryCompat.res("model/trapped_chest_right"));
             TextureImage normal_t = TextureImage.open(manager, EveryCompat.res("model/trapped_chest_normal"))
        ) {

            Respriter respriterNormal = Respriter.masked(normal, normal_m);
            Respriter respriter = Respriter.masked(left, left_m);
            Respriter respriterRight = Respriter.masked(right, right_m);

            Respriter respriterNormalO = Respriter.of(normal_o);
            Respriter respriterO = Respriter.of(left_o);
            Respriter respriterRightO = Respriter.of(right_o);

            CHEST.blocks.forEach((wood, block) -> {
                String path = MoreChestVariantsModule.this.shortenedId() + "/" + wood.getAppendableId() + "_chest";
                String trapped_path = MoreChestVariantsModule.this.shortenedId() + "/" + wood.getAppendableId() + "_trapped_chest";;
                try (TextureImage plankTexture = TextureImage.open(manager,
                        RPUtils.findFirstBlockTextureLocation(manager, wood.planks))) {

                    List<Palette> targetPalette = Palette.fromAnimatedImage(plankTexture);
                    AnimationMetadataSection meta = plankTexture.getMetadata();

                    List<Palette> overlayPalette = new ArrayList<>();
                    for (var p : targetPalette) {
                        var d1 = p.getDarkest();
                        p.remove(d1);
                        var d2 = p.getDarkest();
                        p.remove(d2);
                        var n1 = new HCLColor(d1.hcl().hue(), d1.hcl().chroma() * 0.75f, d1.hcl().luminance() * 0.4f, d1.hcl().alpha());
                        var n2 = new HCLColor(d2.hcl().hue(), d2.hcl().chroma() * 0.75f, d2.hcl().luminance() * 0.6f, d2.hcl().alpha());
                        var pal = Palette.ofColors(List.of(n1, n2));
                        overlayPalette.add(pal);
                    }

                    {
                        ResourceLocation res = EveryCompat.res("entity/chest/" + path);
                        if (!handler.alreadyHasTextureAtLocation(manager, res)) {
                            ResourceLocation trappedRes = EveryCompat.res("entity/chest/" + trapped_path);

                            createChestTextures(handler, normal_t, respriterNormal, respriterNormalO, meta,
                                                targetPalette, overlayPalette, res, trappedRes, wood);
                        }
                    }
                    {
                        ResourceLocation res = EveryCompat.res("entity/chest/" + path + "_left");
                        if (!handler.alreadyHasTextureAtLocation(manager, res)) {
                            ResourceLocation trappedRes = EveryCompat.res("entity/chest/" + trapped_path + "_left");

                            createChestTextures(handler, left_t, respriter, respriterO, meta, targetPalette, overlayPalette, res, trappedRes, wood);
                        }
                    }
                    {
                        ResourceLocation res = EveryCompat.res("entity/chest/" + path + "_right");
                        if (!handler.alreadyHasTextureAtLocation(manager, res)) {
                            ResourceLocation trappedRes = EveryCompat.res("entity/chest/" + trapped_path + "_right");

                            createChestTextures(handler, right_t, respriterRight, respriterRightO, meta, targetPalette, overlayPalette, res, trappedRes, wood);
                        }
                    }


                } catch (Exception ex) {
                    handler.getLogger().error("Failed to generate Chest block texture for for {} : {}", block, ex);
                }
                // MODEL BLOCK -----------------------------------------------------------------------------------------
                JsonObject modelBlock;
                JsonObject trappedModel;
                try (InputStream modelStream = manager.getResource(EveryCompat.res("models/block/" + path + ".json"))
                        .orElseThrow(() -> new TypeNotPresentException("Model file: " + path, new NoSuchElementException())).open();
                     InputStream trappedStream = manager.getResource(EveryCompat.res("models/block/" + trapped_path)) // TODO: correct this
                        .orElseThrow(() -> new TypeNotPresentException("Model file: " + trapped_path, new NoSuchElementException())).open()
                    ) {
                    modelBlock = RPUtils.deserializeJson(modelStream);
                    trappedModel = RPUtils.deserializeJson(trappedStream);
                    String textureID = EveryCompat.MOD_ID + ":entity/chest/" + path;
                    String trappedID = EveryCompat.MOD_ID + ":entity/chest/" + trapped_path;

                    // Editing
                    modelBlock.getAsJsonObject("textures").addProperty("wood_type", textureID);

                    trappedModel.getAsJsonObject("textures").addProperty("wood_type", trappedID);

                    // Add to Resource
                    handler.dynamicPack.addJson(EveryCompat.res(path + "_chest"), modelBlock, ResType.BLOCK_MODELS);
                    handler.dynamicPack.addJson(EveryCompat.res(trapped_path), trappedModel, ResType.BLOCK_MODELS);
                } catch (IOException e) {
                    handler.getLogger().error("MoreChestVariantsModule - failed to open the model file: {0}", e);
                }
            });
        } catch (Exception ex) {
            handler.getLogger().error("Could not generate any Chest block texture : ", ex);
        }
    }

    private void createChestTextures(ClientDynamicResourcesHandler handler, TextureImage trappedOverlay,
                                     Respriter respriter, Respriter respriterO,
                                     AnimationMetadataSection baseMeta, List<Palette> basePalette,
                                     List<Palette> overlayPalette, ResourceLocation res, ResourceLocation trappedRes,
                                     WoodType wood) {

        TextureImage recoloredBase = respriter.recolorWithAnimation(basePalette, baseMeta);
        TextureImage recoloredOverlay = respriterO.recolorWithAnimation(overlayPalette, baseMeta);
        recoloredBase.applyOverlay(recoloredOverlay);
        TextureImage trapped = recoloredBase.makeCopy();

        if (!wood.getNamespace().equals("blue_skies") || (wood.getNamespace().equals("blue_skies") && wood.getTypeName().equals("crystallized")))
            handler.dynamicPack.addAndCloseTexture(res, recoloredBase);

        trapped.applyOverlay(trappedOverlay.makeCopy());
        handler.dynamicPack.addAndCloseTexture(trappedRes, trapped);
    }
}
