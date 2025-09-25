package net.mehvahdjukaar.every_compat.common_classes;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.PaletteStrategies;
import net.mehvahdjukaar.every_compat.api.PaletteStrategy;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceSink;
import net.mehvahdjukaar.moonlight.api.resources.textures.Respriter;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;

import static net.mehvahdjukaar.every_compat.misc.HardcodedBlockType.isKnownVanillaWood;

public class TextureUtility {

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
            EveryCompat.LOGGER.error("Failed to get mask texture: {}", e.getMessage());
        }
    }

    public record Quartet<
            L extends String,
            ML extends String,
            MR extends String,
            R extends PaletteStrategy>(L baseTexture, ML logMask, MR planksMask, R paletteStrategy) {

        public static <A extends String, B extends String, C extends String, D extends PaletteStrategy>
        Quartet<A, B, C, D> of(A baseTexture, B logMask, C planksMask, D paletteStrategy) {
            return new Quartet<>(baseTexture, logMask, planksMask, paletteStrategy);
        }

        public L baseTexture() {
            return this.baseTexture;
        }

        public ML logMask() {
            return this.logMask;
        }

        public MR planksMask() {
            return this.planksMask;
        }

        public R paletteStrategy() {
            return this.paletteStrategy;
        }
    }
}
