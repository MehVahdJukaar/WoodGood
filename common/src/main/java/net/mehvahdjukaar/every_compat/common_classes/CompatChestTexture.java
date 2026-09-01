package net.mehvahdjukaar.every_compat.common_classes;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceSink;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.resources.textures.Respriter;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureOps;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.math.colors.HCLColor;
import net.mehvahdjukaar.moonlight.core.misc.McMetaFile;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CompatChestTexture {

    // blueprint chests. mods that add wood ship them at <namespace>:textures/entity/chest/<wood>/<variant>.png
    private static final String[] MOD_CHEST_VARIANTS = {
            "normal", "normal_left", "normal_right",
            "trapped", "trapped_left", "trapped_right"
    };
    private static final String[] CHEST_TEXTURE_SUFFIXES = {
            "_chest", "_chest_left", "_chest_right",
            "_trapped_chest", "_trapped_chest_left", "_trapped_chest_right"
    };

    /**
     * Copies the chest textures that the mod adding this wood already ships onto our own chest path, so hand made
     * art wins over a generated recolor. Only copies when the whole set is there, otherwise the trapped variants
     * would be missing.
     *
     * @return false if the mod has no chest textures for this wood, meaning the caller has to generate them
     */
    public static boolean copyModProvidedChestTextures(ResourceSink sink, ResourceManager manager,
                                                       String shortenedID, WoodType wood) {
        List<ResourceLocation> sources = new ArrayList<>();
        for (String variant : MOD_CHEST_VARIANTS) {
            ResourceLocation from = ResourceLocation.fromNamespaceAndPath(wood.getNamespace(),
                    "entity/chest/" + wood.getTypeName() + "/" + variant);
            ResourceLocation textureId = ResType.TEXTURES.getPath(from);

            if (manager.getResource(textureId).isEmpty()) return false;
            sources.add(textureId);
        }

        for (int idx = 0; idx < CHEST_TEXTURE_SUFFIXES.length; idx++) {
            ResourceLocation to = EveryCompat.res("entity/chest/" + shortenedID + "/" +
                    wood.getAppendableId() + CHEST_TEXTURE_SUFFIXES[idx]);

            if (sink.alreadyHasTextureAtLocation(manager, to)) continue;
            sink.copyResource(manager, sources.get(idx), ResType.TEXTURES.getPath(to), true);
        }
        return true;
    }

    public static void generateChestTexture(ResourceSink handler, ResourceManager manager,
                                            String shortenedID, WoodType wood, Block block,
                                            ResourceLocation normalRLoc, ResourceLocation maskRLoc, ResourceLocation overlayRLoc,
                                            ResourceLocation trappedORLoc) {
        generateChestTexture(handler, manager, shortenedID, wood, block, normalRLoc, maskRLoc, overlayRLoc, trappedORLoc, 2);
    }

    /**
     * Generate a texture for chest and trapped_chest
     *
     * @param removeDarkest 0: none removed, 1: removed once, 2: removed twice
     */
    public static void generateChestTexture(ResourceSink sink, ResourceManager manager,
                                            String shortenedID, WoodType wood, Block block,
                                            ResourceLocation normalRLoc, ResourceLocation maskRLoc, ResourceLocation overlayRLoc,
                                            ResourceLocation trappedORLoc, int removeDarkest) {

        try (TextureImage texture = TextureImage.open(manager, normalRLoc);
             TextureImage mask = TextureImage.open(manager, maskRLoc);
             TextureImage overlay = TextureImage.open(manager, overlayRLoc);
             @Nullable TextureImage trapOverlay = trappedORLoc == null ? null : TextureImage.open(manager, trappedORLoc)
        ) {

            Respriter respriterNormal = Respriter.masked(texture, mask);
            Respriter respriterOverlay = Respriter.of(overlay);

            String path = "entity/chest/" + shortenedID + "/" + wood.getAppendableId() + "_chest";
            String trapped_path = "entity/chest/" + shortenedID + "/" + wood.getAppendableId() + "_trapped_chest";
            if (normalRLoc.toString().contains("left")) {
                path += "_left";
                trapped_path += "_left";
            } else if (normalRLoc.toString().contains("right")) {
                path += "_right";
                trapped_path += "_right";
            }

            try (TextureImage plankTexture = TextureImage.open(manager,
                    RPUtils.findFirstBlockTextureLocation(manager, wood.planks))) {

                List<Palette> plankPalette = Palette.fromAnimatedImage(plankTexture);

                // Remove the lava color from brimwood_planks
                if (wood.getId().toString().equals("regions_unexplored:brimwood")) {
                    plankPalette.forEach(p -> {
                        p.reduceUp();
                        p.reduceUp();
                        p.reduceUp();
                        p.reduceUp();
                    });
                }

                List<Palette> modifiedPlanksPalette = new ArrayList<>();
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
                    modifiedPlanksPalette.add(pal);
                }

                List<Palette> overlayPalette = Palette.fromAnimatedImage(overlay);

                // Generating textures
                ResourceLocation res = EveryCompat.res(path);
                if (!sink.alreadyHasTextureAtLocation(manager, res)) {
                    ResourceLocation trappedRes = EveryCompat.res(trapped_path);

                    createChestTextures(respriterNormal, respriterOverlay, plankTexture.getMcMeta(),
                            modifiedPlanksPalette, overlayPalette, res, trappedRes, trapOverlay, wood, sink);
                }

            } catch (Exception ex) {
                EveryCompat.LOGGER.error("Failed to generate Chest block texture for for: {} - {}", block, ex);
            }
        } catch (Exception ex) {
            EveryCompat.LOGGER.error("Could not generate any Chest block texture: ", ex);
        }
    }

    private static void createChestTextures(Respriter respriter, Respriter respriterO,
                                            McMetaFile baseMeta, List<Palette> planksPalette,
                                            List<Palette> overlayPalette, ResourceLocation normalRLoc,
                                            ResourceLocation trappedRLoc, TextureImage trappedOverlay,
                                            WoodType wood, ResourceSink sink) {

        try (TextureImage recoloredBase = respriter.recolorWithAnimation(planksPalette, baseMeta);
             TextureImage recoloredOverlay = respriterO.recolorWithAnimation(overlayPalette, baseMeta)) {

            TextureOps.applyOverlayOnExisting(recoloredBase, recoloredOverlay);

            if (trappedOverlay != null) {
                TextureImage trapped = recoloredBase.makeCopy();
                TextureOps.applyOverlay(trapped, trappedOverlay);
                sink.addTexture(trappedRLoc, trapped);
            }

            if (!wood.getNamespace().equals("blue_skies") || (wood.getNamespace().equals("blue_skies") && wood.getTypeName().equals("crystallized")))
                sink.addTexture(normalRLoc, recoloredBase);
        }
    }

}
