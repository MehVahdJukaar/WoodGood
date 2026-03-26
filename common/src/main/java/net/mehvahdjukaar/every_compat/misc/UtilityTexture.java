package net.mehvahdjukaar.every_compat.misc;

import com.mojang.blaze3d.platform.NativeImage;
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
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;

import java.util.Objects;

import static net.mehvahdjukaar.every_compat.misc.HardcodedBlockType.isKnownVanillaWood;

@SuppressWarnings("LoggingSimilarMessage")
public class UtilityTexture {

    /**
     * modify the original Path of Texture's ResourceLocation by replacing oldTypeName with new WoodType's name
     * @param prefix use either block/ or item/
     * @return block/shortenedId/namespace/baseTexturePath<br>
     *         item/shortenedId/namespace/baseTexturePath
    **/
    public static ResourceLocation modifyTexturePath(String baseTexturePath, String prefix, String shortenedId, String oldTypeName,
                                           BlockType blockType) {
        String infix =  shortenedId +"/"+ blockType.getNamespace() + "/";
        return EveryCompat.res(prefix + infix + baseTexturePath.substring(prefix.length()).replace(oldTypeName, blockType.getTypeName()));
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
                TextureImage baseTexture = TextureImage.open(manager, baseTextureLoc);
                TextureImage logMask = TextureImage.open(manager, logMaskLoc);
                TextureImage planksMask = TextureImage.open(manager, planksMaskLoc)
        ) {

            for (WoodType woodType : WoodTypeRegistry.INSTANCE) {
                if (isKnownVanillaWood(woodType)) continue;

                ResourceLocation newResLoc = modifyTexturePath(baseTextureLoc.getPath(), "block/", shortenedId, oldTypeName, woodType);

                // Adding to the resource
                sink.addTextureIfNotPresent(manager, newResLoc, () -> {
                    // Recoloring the baseTexture
                    try {
                        var logPalette = logPaletteStrategy.getPaletteAndAnimation(woodType, manager);
                        var planksPalette = PaletteStrategies.PLANKS_STANDARD.getPaletteAndAnimation(woodType, manager);

                        /// Targetting planks
                        Respriter planksResprite = Respriter.masked(baseTexture, logMask);

                        TextureImage recoloredInner = planksResprite.recolorWithAnimation(planksPalette.palette(), planksPalette.animation());

                        /// Targetting logs
                        Respriter logResprite = Respriter.masked(recoloredInner, planksMask);

                        return logResprite.recolorWithAnimation(logPalette.palette(), logPalette.animation());

                    } catch (Exception e) {
                        EveryCompat.LOGGER.error("Failed to generate log texture: {} for {} - {}",
                                baseTextureLoc, woodType.getId(), e);
                    }
                    return baseTexture;
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

            for (WoodType woodType : WoodTypeRegistry.INSTANCE) {
                if (isKnownVanillaWood(woodType)) continue;

                ResourceLocation newPath = modifyTexturePath(baseTextureLoc.getPath(), "block/", shortenedId, oldTypeName, woodType);

                // Recoloring the baseTexture
                try (
                        TextureImage logTexture = TextureImage.open(manager,
                                RPUtils.findFirstBlockTextureLocation(manager, woodType.log, CompatSpritesHelper.LOOKS_LIKE_SIDE_LOG_TEXTURE))
                ) {
                    TextureImage currentLogTexture;
                    int height = logTexture.imageHeight();
                    int width = logTexture.imageWidth();

                    if (!(height == 16) && Objects.nonNull(logTexture.getMcMeta())) { // Shrink the texture to a 16x16
                        NativeImage standardSize = new NativeImage(16, 16, false);
                        standardSize.copyFrom(logTexture.getImage());
                        currentLogTexture = TextureImage.of(standardSize);
                        height = currentLogTexture.imageHeight();
                    }
                    else currentLogTexture = logTexture;

                    if (!(width == 16) || !(height == 16)) {
                        EveryCompat.LOGGER.error("ChippedLogModule - {}'s texture is a {}x{} for {}", Utils.getID(woodType.log), width, height, baseTextureLoc.getPath());
                        sink.addTextureIfNotPresent(manager, newPath, baseTexture::makeCopy);
                        return;
                    }

                    var planksPalette = PaletteStrategies.PLANKS_REMOVE_DARKEST.getPaletteAndAnimation(woodType, manager);

                    TextureOps.applyMask(currentLogTexture, planksMask);
                    TextureOps.applyOverlay(baseTexture, currentLogTexture);

                    // Adding to the resource
                    sink.addTextureIfNotPresent(manager, newPath, () -> {
                        /// Targetting planks
                        Respriter planksResprite = Respriter.masked(baseTexture, logMask);
                        return planksResprite.recolorWithAnimation(planksPalette.palette(), planksPalette.animation());
                    });

                } catch (Exception e) {
                    EveryCompat.LOGGER.error("Failed to apply overlays & generate texture: {} for {} - {}",
                            baseTextureLoc, woodType.getId(), e);
                }
            }
        } catch (Exception e) {
            EveryCompat.LOGGER.error("Failed to generate texture with logOverlay & planks' palettes: ", e);
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
            for (WoodType woodType : WoodTypeRegistry.INSTANCE) {
                if (isKnownVanillaWood(woodType)) continue;

                ResourceLocation newResLoc = modifyTexturePath(baseTextureLoc.getPath(), "block/", shortenedId, oldTypeName, woodType);

                try (
                        TextureImage logTexture = TextureImage.open(manager,
                                RPUtils.findFirstBlockTextureLocation(manager, woodType.log, CompatSpritesHelper.LOOKS_LIKE_SIDE_LOG_TEXTURE))
                ) {
                    int height = logTexture.imageHeight();
                    int width = logTexture.imageWidth();

                    TextureImage currentLogOverlay;
                    TextureImage mainTexture = baseTexture.makeCopy();

                    // Shrink the texture to a 16x16
                    if (!(height == 16) && Objects.nonNull(logTexture.getMcMeta())) {
                        NativeImage standardSize = new NativeImage(16, 16, false);
                        standardSize.copyFrom(logTexture.getImage());
                        currentLogOverlay = TextureImage.of(standardSize);
                        height = currentLogOverlay.imageHeight();
                    }
                    else currentLogOverlay = logTexture.makeCopy();

                    if (!(width == 16) || !(height == 16)) {
                        EveryCompat.LOGGER.error("ChippedLogModule - {}'s texture is a {}x{} for {}", Utils.getID(woodType.log), width, height, baseTextureLoc.getPath());
                        sink.addTextureIfNotPresent(manager, newResLoc, baseTexture::makeCopy);
                        return;
                    }


                    // Adding to the resource
                    sink.addTextureIfNotPresent(manager, newResLoc, () -> {
                        TextureOps.applyMask(currentLogOverlay, mask); // remove parts from texture for overlaying
                        TextureOps.applyOverlay(mainTexture, currentLogOverlay);

                        return mainTexture;
                    });

                } catch (Exception e) {
                    EveryCompat.LOGGER.error("Failed to apply overlays to texture: {} for {} - {}",
                            baseTextureLoc, woodType.getId(), e);
                }
            }
        } catch (Exception e) {
            EveryCompat.LOGGER.error("Failed to generate texture with logOverlay: ", e);
        }
    }

}
