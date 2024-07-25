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
                                            WoodType wood, Block block, ResourceLocation normalPath, ResourceLocation trappedPath,
                                            ResourceLocation normalRLoc, ResourceLocation maskRLoc, ResourceLocation overlayRLoc,
                                            ResourceLocation trappedORLoc) {
        generateChestTexture(handler, manager, wood, block, normalPath, trappedPath, normalRLoc, maskRLoc, overlayRLoc, trappedORLoc, 2);
    }

    /**
     * Generate a texture for chest and trapped_chest
     * @param removeDarkest 0: none removed, 1: removed once, 2: removed twice
     */
    public static void generateChestTexture(ClientDynamicResourcesHandler handler, ResourceManager manager,
                                            WoodType wood, Block block, ResourceLocation normalPath, ResourceLocation trappedPath,
                                            ResourceLocation normalRLoc, ResourceLocation maskRLoc, ResourceLocation overlayRLoc,
                                            ResourceLocation trappedOverlayRLoc, int removeDarkest) {

        try (TextureImage normalTexture = TextureImage.open(manager, normalRLoc);
             TextureImage normalMask = TextureImage.open(manager, maskRLoc);
             TextureImage normalOverlay = TextureImage.open(manager, overlayRLoc)
        ) {

            TextureImage trapOverlay;
            if (trappedOverlayRLoc != null)  trapOverlay = TextureImage.open(manager, trappedOverlayRLoc);
            else trapOverlay = null;

            Respriter respriterNormal = Respriter.masked(normalTexture, normalMask);
            Respriter respriterOverlay = Respriter.of(normalOverlay);

            try (TextureImage plankTexture = TextureImage.open(manager,
                    RPUtils.findFirstBlockTextureLocation(manager, wood.planks))) {

                List<Palette> plankPalette = Palette.fromAnimatedImage(plankTexture);

                // Remove the lava color from brimwood_planks
                if (wood.getId().toString().equals("regions_unexplored:brimwood")) {
                    plankPalette.forEach(p -> {
                        p.remove(p.getLightest());
                        p.remove(p.getLightest());
                        p.remove(p.getLightest());
                        p.remove(p.getLightest());
                    });
                }

                AnimationMetadataSection plankMeta = plankTexture.getMetadata();

                List<Palette> overlayPalette = new ArrayList<>();
                for (var p : plankPalette) {
                    var d1 = p.getDarkest();
                    var d2 = p.getDarkest();

                    // brimwood_chest need to retain their darkness
                    if (!wood.getId().toString().equals("regions_unexplored:brimwood")) {
                        switch (removeDarkest) {
                            case 2:
                                p.remove(d2);
                            case 1:
                                p.remove(d1);
                        }
                    }

                    var n1 = new HCLColor(d1.hcl().hue(), d1.hcl().chroma() * 0.75f, d1.hcl().luminance() * 0.4f, d1.hcl().alpha());
                    var n2 = new HCLColor(d2.hcl().hue(), d2.hcl().chroma() * 0.75f, d2.hcl().luminance() * 0.6f, d2.hcl().alpha());
                    var pal = Palette.ofColors(List.of(n1, n2));
                    overlayPalette.add(pal);
                }

                // Generating textures
                if (!handler.alreadyHasTextureAtLocation(manager, normalPath)) {
                    createChestTextures(handler, respriterNormal, respriterOverlay, plankMeta,
                            plankPalette, overlayPalette, normalPath, trappedPath, trapOverlay, wood);
                }

            } catch (Exception ex) {
                handler.getLogger().error("Failed to generate Chest block texture for for: {} - {}", block, ex);
            }
        } catch (Exception ex) {
            handler.getLogger().error("Failed to open the Chest texture: ", ex);
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
            trapped.applyOverlay(trappedOverlay);
            handler.dynamicPack.addAndCloseTexture(trappedRLoc, trapped);
        }

        if (!wood.getNamespace().equals("blue_skies") || (wood.getNamespace().equals("blue_skies") && wood.getTypeName().equals("crystallized")))
            handler.dynamicPack.addAndCloseTexture(normalRLoc, recoloredBase);
    }

}