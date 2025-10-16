package net.mehvahdjukaar.every_compat.misc;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.PaletteStrategies;
import net.mehvahdjukaar.every_compat.api.PaletteStrategy;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceSink;
import net.mehvahdjukaar.moonlight.api.resources.textures.Respriter;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureOps;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;

import java.util.ArrayList;
import java.util.List;

import static net.mehvahdjukaar.every_compat.misc.HardcodedBlockType.isKnownVanillaWood;

public class UtilityTexture {

    /**
     * modify the original Path of Texture's ResourceLocation by replacing oldTypeName with new WoodType's name
     * @param prefix use either block/ or item/
     * @return block/shortenedId/namespace/baseTexturePath<br>
     *         item/shortenedId/namespace/baseTexturePath
    **/
    public static String modifyTexturePath(String baseTexturePath, String prefix, String shortenedId, String oldTypeName,
                                           BlockType blockType) {
        String infix =  shortenedId +"/"+ blockType.getNamespace() + "/";
        return prefix + infix + baseTexturePath.substring(prefix.length()).replace(oldTypeName, blockType.getTypeName());
    }

    /**
     * The Log's texture has 2 parts: planks & log_side. This method focus on recolor 1 of 2 parts using the correct
     * palettes and then use the other palettes to recolor the other part.
     * @param logMaskLoc exclude the logs part from being recolored
     * @param planksMaskLoc exclude the planks part from being recolored
    **/
    public static void generateLogTexture(ResourceLocation baseTextureLoc,
                                          ResourceLocation logMaskLoc, ResourceLocation planksMaskLoc,
                                          String shortenedId, String oldTypeName,
                                          PaletteStrategy logPaletteStrategy,
                                          ResourceSink sink, ResourceManager manager) {
        try (
                TextureImage mainTexture = TextureImage.open(manager, baseTextureLoc);
                TextureImage logMask = TextureImage.open(manager, logMaskLoc);
                TextureImage planksMask = TextureImage.open(manager, planksMaskLoc)
        ) {

            for (WoodType woodType : WoodTypeRegistry.INSTANCE) {
                if (isKnownVanillaWood(woodType)) continue;

                String newPath = modifyTexturePath(baseTextureLoc.getPath(), "block/", shortenedId, oldTypeName, woodType);

                // Adding to the resource
                sink.addTextureIfNotPresent(manager, newPath, () -> {
                    // Recoloring the baseTexture
                    try {
                        var logPalette = logPaletteStrategy.getPaletteAndAnimation(woodType, manager);
                        var planksPalette = PaletteStrategies.PLANKS_STANDARD.getPaletteAndAnimation(woodType, manager);

                        /// Targetting planks
                        Respriter planksResprite = Respriter.masked(mainTexture, logMask);

                        TextureImage recoloredInner = planksResprite.recolorWithAnimation(planksPalette.palette(), planksPalette.animation());

                        /// Targetting logs
                        Respriter logResprite = Respriter.masked(recoloredInner, planksMask);

                        return logResprite.recolorWithAnimation(logPalette.palette(), logPalette.animation());

                    } catch (Exception e) {
                        EveryCompat.LOGGER.error("Failed to generate log texture: {} for {} - {}",
                                baseTextureLoc, woodType.getId(), e);
                    }
                    return mainTexture;
                });
            }
        } catch (Exception e) {
            EveryCompat.LOGGER.error("Failed to generate log texture: ", e);
        }
    }

    /// Apply log's texture over the baseTexture's log parts & swap out the planks' part
    public static void applyLogAndswapPlanksTexture(ResourceLocation baseTextureLoc,
                                                    ResourceLocation logMaskLoc, ResourceLocation planksMaskLoc,
                                                    String shortenedId, String oldTypeName,
                                                    ResourceSink sink, ResourceManager manager) {
        try (
                TextureImage baseTexture = TextureImage.open(manager, baseTextureLoc);
                TextureImage logMask = TextureImage.open(manager, logMaskLoc);
                TextureImage planksMask = TextureImage.open(manager, planksMaskLoc)
        ) {

            List<TextureImage> imagesToClose = new ArrayList<>();

            for (WoodType woodType : WoodTypeRegistry.INSTANCE) {
                if (isKnownVanillaWood(woodType)) continue;

                String newPath = modifyTexturePath(baseTextureLoc.getPath(), "block/", shortenedId, oldTypeName, woodType);

                try (
                        TextureImage logTexture = TextureImage.open(manager,
                                RPUtils.findFirstBlockTextureLocation(manager, woodType.log, CompatSpritesHelper.LOOKS_LIKE_SIDE_LOG_TEXTURE))
                ) {
                    var planksPalette = PaletteStrategies.PLANKS_REMOVE_DARKEST.getPaletteAndAnimation(woodType, manager);

                    TextureImage mainTexture = baseTexture.makeCopy();
                    TextureImage croppedTexture = logTexture.makeCopy();
                    TextureOps.applyMask(croppedTexture, planksMask);

                    TextureOps.applyOverlay(mainTexture, croppedTexture);

                    // Adding to the resource
                    sink.addTextureIfNotPresent(manager, newPath, () -> {
                        /// Targetting planks
                        Respriter planksResprite = Respriter.masked(mainTexture, logMask);
                        // Recoloring the baseTexture
                        return planksResprite.recolorWithAnimation(planksPalette.palette(), planksPalette.animation());
                    });

                    imagesToClose.add(mainTexture);
                    imagesToClose.add(croppedTexture);

                } catch (Exception e) {
                    EveryCompat.LOGGER.error("Failed to apply overlays & swap planks to texture: {} for {} - {}",
                            baseTextureLoc, woodType.getId(), e);
                } finally {
                    imagesToClose.forEach(TextureImage::close);
                }
            }
        } catch (Exception e) {
            EveryCompat.LOGGER.error("Failed to generate texture with logOverlay: ", e);
        }
    }

    /// Apply log's texture over the baseTexture's log parts
    public static void applyLogAndGenerateTexture(ResourceLocation baseTextureLoc,
                                                  ResourceLocation maskLoc,
                                                  String shortenedId, String oldTypeName,
                                                  ResourceSink sink, ResourceManager manager) {
        try (
                TextureImage baseTexture = TextureImage.open(manager, baseTextureLoc);
                TextureImage mask = TextureImage.open(manager, maskLoc)
        ) {

            List<TextureImage> imagesToClose = new ArrayList<>();

            for (WoodType woodType : WoodTypeRegistry.INSTANCE) {
                if (isKnownVanillaWood(woodType)) continue;

                String newPath = modifyTexturePath(baseTextureLoc.getPath(), "block/", shortenedId, oldTypeName, woodType);

                try (
                        TextureImage logTexture = TextureImage.open(manager,
                                RPUtils.findFirstBlockTextureLocation(manager, woodType.log, CompatSpritesHelper.LOOKS_LIKE_SIDE_LOG_TEXTURE))
                ) {
                    TextureImage mainTexture = baseTexture.makeCopy();
                    TextureImage logOverlay = logTexture.makeCopy();
                    TextureOps.applyMask(logOverlay, mask); // remove parts from texture for overlaying

                    TextureOps.applyOverlay(mainTexture, logOverlay);

                    // Adding to the resource
                    sink.addTextureIfNotPresent(manager, newPath, () -> mainTexture);

                    imagesToClose.add(mainTexture);
                    imagesToClose.add(logOverlay);

                } catch (Exception e) {
                    EveryCompat.LOGGER.error("Failed to apply overlays to texture: {} for {} - {}",
                            baseTextureLoc, woodType.getId(), e);
                } finally {
                    imagesToClose.forEach(TextureImage::close);
                }
            }
        } catch (Exception e) {
            EveryCompat.LOGGER.error("Failed to generate texture: ", e);
        }
    }

}
