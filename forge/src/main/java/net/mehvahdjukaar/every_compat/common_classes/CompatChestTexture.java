package net.mehvahdjukaar.every_compat.common_classes;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.resources.textures.Respriter;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.math.colors.HCLColor;
import net.minecraft.client.resources.metadata.animation.AnimationMetadataSection;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;

public class CompatChestTexture {

    public static void generateChestTexture(ClientDynamicResourcesHandler handler, ResourceManager manager,
                                    String shortenedID,
                                    WoodType wood, Block block,
                                ResourceLocation normalRLoc, ResourceLocation maskRLoc, ResourceLocation overlayRLoc,
                                    ResourceLocation trappedORLoc) {

        try (TextureImage normalTexture = TextureImage.open(manager, normalRLoc);
             TextureImage normalMask = TextureImage.open(manager, maskRLoc);
             TextureImage normalOverlay = TextureImage.open(manager, overlayRLoc)
            ) {

            TextureImage trapOverlay;
            if (trappedORLoc != null)  trapOverlay = TextureImage.open(manager, trappedORLoc);
            else trapOverlay = null;

            Respriter respriterNormal = Respriter.masked(normalTexture, normalMask);
            Respriter respriterOverlay = Respriter.of(normalOverlay);

                String path = "entity/chest/" + shortenedID + "/" + wood.getAppendableId() + "_chest";
                String trapped_path = "entity/chest/" + shortenedID + "/" + wood.getAppendableId() + "_trapped_chest";
                if (normalRLoc.toString().contains("left")) {
                    path += "_left";
                    trapped_path += "_left";
                }
                else if (normalRLoc.toString().contains("right")) {
                    path += "_right";
                    trapped_path += "_right";
                }

                try (TextureImage plankTexture = TextureImage.open(manager,
                        RPUtils.findFirstBlockTextureLocation(manager, wood.planks))) {

                    List<Palette> plankPalette = Palette.fromAnimatedImage(plankTexture);
                    AnimationMetadataSection plankMeta = plankTexture.getMetadata();

                    List<Palette> overlayPalette = new ArrayList<>();
                    for (var p : plankPalette) {
                        var d1 = p.getDarkest();
                        p.remove(d1);
                        var d2 = p.getDarkest();
                        p.remove(d2);
                        var n1 = new HCLColor(d1.hcl().hue(), d1.hcl().chroma() * 0.75f, d1.hcl().luminance() * 0.4f, d1.hcl().alpha());
                        var n2 = new HCLColor(d2.hcl().hue(), d2.hcl().chroma() * 0.75f, d2.hcl().luminance() * 0.6f, d2.hcl().alpha());
                        var pal = Palette.ofColors(List.of(n1, n2));
                        overlayPalette.add(pal);
                    }

                    // Generating textures
                    ResourceLocation res = EveryCompat.res(path);
                    if (!handler.alreadyHasTextureAtLocation(manager, res)) {
                        ResourceLocation trappedRes = EveryCompat.res(trapped_path);

                        createChestTextures(handler, respriterNormal, respriterOverlay, plankMeta,
                                plankPalette, overlayPalette, res, trappedRes, trapOverlay, wood);
                    }

                } catch (Exception ex) {
                    handler.getLogger().error("Failed to generate Chest block texture for for: {} - {}", block, ex);
                }
        } catch (Exception ex) {
            handler.getLogger().error("Could not generate any Chest block texture: ", ex);
        }
    }

    private static void createChestTextures(ClientDynamicResourcesHandler handler,
                                            Respriter respriter, Respriter respriterO,
                                            AnimationMetadataSection baseMeta, List<Palette> basePalette,
                                            List<Palette> overlayPalette, ResourceLocation normalRLoc,
                                            ResourceLocation trappedRLoc, TextureImage trappedOverlay,
                                            WoodType wood) {

        TextureImage recoloredBase = respriter.recolorWithAnimation(basePalette, baseMeta);
        TextureImage recoloredOverlay = respriterO.recolorWithAnimation(overlayPalette, baseMeta);
        recoloredBase.applyOverlay(recoloredOverlay);

        if (trappedOverlay != null) {
            TextureImage trapped = recoloredBase.makeCopy();
            trapped.applyOverlay(trappedOverlay.makeCopy());
            handler.dynamicPack.addAndCloseTexture(trappedRLoc, trapped);
        }

        if (!wood.getNamespace().equals("blue_skies") || (wood.getNamespace().equals("blue_skies") && wood.getTypeName().equals("crystallized")))
            handler.dynamicPack.addAndCloseTexture(normalRLoc, recoloredBase);
    }

}
