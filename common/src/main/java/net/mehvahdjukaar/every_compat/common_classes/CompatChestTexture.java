package net.mehvahdjukaar.every_compat.common_classes;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceSink;
import net.mehvahdjukaar.moonlight.api.resources.textures.*;
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

    private static String NormalSuffix = "";
    private static String TrappedSuffix = "";

    public static void setSuffix(String normalSuffix, String trappedSuffix) {
        NormalSuffix = normalSuffix;
        TrappedSuffix = trappedSuffix;
    }

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
            ResourceLocation from = new ResourceLocation(wood.getNamespace(),
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


    public static boolean copyHandmadeChestTextures(ResourceSink sink, ResourceManager manager,
                                                    String fromShortenedId, String toShortenedID, WoodType wood) {
        List<ResourceLocation> sources = new ArrayList<>();
        for (int idx = 0; idx < CHEST_TEXTURE_SUFFIXES.length; idx++) {
            ResourceLocation from = EveryCompat.res("entity/chest/" + fromShortenedId + "/" +
                    wood.getAppendableId() + CHEST_TEXTURE_SUFFIXES[idx]);
            ResourceLocation textureId = ResType.TEXTURES.getPath(from);

            if (manager.getResource(textureId).isEmpty()) return false;
            sources.add(textureId);
        }

        for (int idx = 0; idx < CHEST_TEXTURE_SUFFIXES.length; idx++) {
            ResourceLocation to = EveryCompat.res("entity/chest/" + toShortenedID + "/" +
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
        generateChestTexture(handler, manager, shortenedID, wood, block, normalRLoc, maskRLoc, overlayRLoc, trappedORLoc, 2, false);
    }

    public static void generateChestTexture(ResourceSink handler, ResourceManager manager,
                                            String shortenedID, WoodType wood, Block block,
                                            ResourceLocation normalRLoc, ResourceLocation maskRLoc, ResourceLocation overlayRLoc,
                                            ResourceLocation trappedORLoc, boolean useCUstomSuffix) {
        generateChestTexture(handler, manager, shortenedID, wood, block, normalRLoc, maskRLoc, overlayRLoc, trappedORLoc, 2, useCUstomSuffix);
    }

    public static void generateChestTexture(ResourceSink handler, ResourceManager manager,
                                            String shortenedID, WoodType wood, Block block,
                                            ResourceLocation normalRLoc, ResourceLocation maskRLoc, ResourceLocation overlayRLoc,
                                            ResourceLocation trappedORLoc, int removeDarkest) {
        generateChestTexture(handler, manager, shortenedID, wood, block, normalRLoc, maskRLoc, overlayRLoc, trappedORLoc, removeDarkest, false);
    }

    /**
     * Generate a texture for chest and trapped_chest
     *
     * @param removeDarkest 0: none removed, 1: removed once, 2: removed twice
     */
    public static void generateChestTexture(ResourceSink sink, ResourceManager manager,
                                            String shortenedID, WoodType wood, Block block,
                                            ResourceLocation normalRLoc, ResourceLocation maskRLoc, ResourceLocation overlayRLoc,
                                            ResourceLocation trappedORLoc, int removeDarkest, boolean useCustomSuffix) {

        try (TextureImage texture = TextureImage.open(manager, normalRLoc);
             @Nullable TextureImage mask = (maskRLoc == null) ? null : TextureImage.open(manager, maskRLoc);
             TextureImage overlay = TextureImage.open(manager, overlayRLoc);
             @Nullable TextureImage trapOverlay = (trappedORLoc == null) ? null : TextureImage.open(manager, trappedORLoc);
        ) {

            String normalSuffix = (useCustomSuffix && !NormalSuffix.isEmpty()) ? NormalSuffix : "_chest";
            String trappedSuffix = (useCustomSuffix && !TrappedSuffix.isEmpty()) ? TrappedSuffix : "_trapped_chest";

            Respriter respriterNormal = (mask == null) ? Respriter.of(texture) : Respriter.masked(texture, mask);
            Respriter respriterOverlay = Respriter.of(overlay);

            String path = "entity/chest/" + shortenedID + "/" + wood.getAppendableId() + normalSuffix;
            String trapped_path = "entity/chest/" + shortenedID + "/" + wood.getAppendableId() + trappedSuffix;
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

                plankPalette.forEach(palette -> {
                    PaletteColor darkest = palette.getDarkest();
                    PaletteColor darker = palette.getDarkest(1);
                    PaletteColor lightest = palette.getLightest();

                    // brimwood_chest need to retain their darkness
                    if (!wood.getId().toString().equals("regions_unexplored:brimwood")) {
                        HCLColor newHCL;
                        HCLColor newHCL2;
                        HCLColor newLight;

                        switch (removeDarkest) {
                            case 2 -> {
                                palette.reduceDown();
                                palette.reduceDown();

                                newHCL = new HCLColor(darkest.hcl().hue(), darkest.hcl().chroma() * 1.10F, darkest.hcl().luminance(), darkest.hcl().alpha());
                                newHCL2 = new HCLColor(darker.hcl().hue(), darker.hcl().chroma() * 1.10F, darker.hcl().luminance(), darker.hcl().alpha());
                                newLight = new HCLColor(lightest.hcl().hue(), lightest.hcl().chroma() * 1.15F, lightest.hcl().luminance(), lightest.hcl().alpha());

                                palette.add(new PaletteColor(newHCL));
                                palette.add(new PaletteColor(newHCL2));
                                palette.add(new PaletteColor(newLight));
                            }
                            case 1 -> {
                                palette.reduceDown();

                                newHCL = new HCLColor(darkest.hcl().hue(), darkest.hcl().chroma() * 1.10F, darkest.hcl().luminance(), darkest.hcl().alpha());
                                newLight = new HCLColor(lightest.hcl().hue(), lightest.hcl().chroma() * 1.15F, lightest.hcl().luminance(), lightest.hcl().alpha());

                                palette.add(new PaletteColor(newHCL));
                                palette.add(new PaletteColor(newLight));
                            }
                        }
                    }

                });

                List<Palette> overlayPalette = Palette.fromAnimatedImage(overlay);

                // Generating textures
                ResourceLocation res = EveryCompat.res(path);
                if (!sink.alreadyHasTextureAtLocation(manager, res)) {
                    ResourceLocation trappedRes = EveryCompat.res(trapped_path);

                    createChestTextures(respriterNormal, respriterOverlay, plankTexture.getMcMeta(),
                            plankPalette, overlayPalette, res, trappedRes, trapOverlay, wood, sink);
                }

            } catch (Exception ex) {
                EveryCompat.LOGGER.error("Failed to generate Chest block texture for for: {} - {}", block, ex);
            }
        } catch (Exception ex) {
            EveryCompat.LOGGER.error("Could not generate any Chest block texture: ", ex);
        }
    }

    private static void createChestTextures(Respriter respriter, Respriter respriterO,
                                            McMetaFile baseMeta, List<Palette> basePalette,
                                            List<Palette> overlayPalette, ResourceLocation normalRLoc,
                                            ResourceLocation trappedRLoc, TextureImage trappedOverlay,
                                            WoodType wood, ResourceSink sink) {

        try (TextureImage recoloredBase = respriter.recolorWithAnimation(basePalette, baseMeta);
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
