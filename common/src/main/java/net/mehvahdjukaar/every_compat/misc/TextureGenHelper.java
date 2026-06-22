package net.mehvahdjukaar.every_compat.misc;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.TextureInfo;
import net.mehvahdjukaar.moonlight.api.resources.BlockTypeResTransformer;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceSink;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.resources.textures.Respriter;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureOps;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.moonlight.api.util.math.colors.RGBColor;
import net.mehvahdjukaar.moonlight.core.misc.McMetaFile;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

import java.util.*;
import java.util.concurrent.Callable;

//Sprite Helper is too big
public class TextureGenHelper {

    //TODO: this is unmanageable. needs to be split in smaller manageable bits and commented better
    public static <T extends BlockType, O extends ItemLike> void generateDefault(ResourceSink sink, ResourceManager manager,
                                                             String modId,
                                                             Set<TextureInfo> textureInfos, T baseType,
                                                             boolean mergePalette,
                                                             Map<T, O> entries) throws Exception {

        List<TextureImage> imagesToClose = new ArrayList<>();

        ItemLike mainChild = baseType.mainChild();
        if (!(mainChild instanceof Block mainChildBlock)) return;
        // only works block types that have a main child as a block

        try (TextureImage oakPlanksTexture = TextureImage.open(manager,
                RPUtils.findFirstBlockTextureLocation(manager, mainChildBlock))) {
            Palette oakPlanksPalette = Palette.fromImage(oakPlanksTexture);

            Map<ResourceLocation, Respriter> respriters = new HashMap<>();
            Map<ResourceLocation, TextureImage> partialRespriters = new HashMap<>();
            Palette globalPalette = Palette.empty();

            Multimap<ResourceLocation, TextureInfo> infoPerTextures = ArrayListMultimap.create();
            TaskRunnerWithFailureCollection failures = TaskRunnerWithFailureCollection.active();

            /// Adding multiple textures from one block into Respriter without/with mask & infoPerTextures
            for (TextureInfo textureInfo : textureInfos) {
                ResourceLocation textureId = textureInfo.texture();

                try {
                    ResourceLocation maskId = textureInfo.mask();
                    TextureImage main = TextureImage.open(manager, textureId);

                    infoPerTextures.put(textureId, textureInfo);

                    if (textureInfo.copyTexture()) {
                        respriters.put(textureId, Respriter.ofPalette(main, Palette.ofColors(Set.of(new RGBColor(0)))));
                    } else {
                        imagesToClose.add(main);

                        if (maskId != null) {
                            TextureImage mask;
                            if (textureInfo.autoMask()) {
                                if (mergePalette) {
                                    globalPalette.addAll(oakPlanksPalette);
                                    partialRespriters.put(textureId, main);
                                } else {
                                    respriters.put(textureId, Respriter.ofPalette(main, oakPlanksPalette));
                                }
                            } else {
                                mask = TextureImage.open(manager, maskId);
                                if (mergePalette) {
                                    globalPalette.addAll(Palette.fromImage(main, mask, 0));
                                    partialRespriters.put(textureId, main);
                                } else {
                                    respriters.put(textureId, Respriter.masked(main, mask));
                                }
                            }

                        } else {
                            if (mergePalette) {
                                globalPalette.addAll(Palette.fromImage(main, null, 0));
                                partialRespriters.put(textureId, main);
                            } else {
                                respriters.put(textureId, Respriter.of(main));
                            }
                        }
                    }
                } catch (UnsupportedOperationException e) {
                    failures.record("source texture", () -> String.valueOf(textureInfo), e);
                } catch (Exception e) {
                    failures.record("source texture", () -> String.valueOf(textureInfo), e);
                }
            }

            for (var e : partialRespriters.entrySet()) {
                respriters.put(e.getKey(), Respriter.ofPalette(e.getValue(), globalPalette));
            }
            /// Swapping out the old palettes of the texture with new palettes
            for (var entry : entries.entrySet()) {
                ItemLike block = entry.getValue();
                T blockType = entry.getKey();
                ResourceLocation blockId = Utils.getID(block);

                failures.runSafely("block texture", blockId::toString, () -> {
                    /// Creating new Path to add the new textures via the resources
                    for (var respriterSet : respriters.entrySet()) {
                        ResourceLocation oldTextureId = respriterSet.getKey();
                        String baseOldPath = oldTextureId.getPath();

                        String newPath = BlockTypeResTransformer.replaceTypeNoNamespace(baseOldPath, blockType, blockId, baseType.getTypeName());

                        /// Adding the textures to the resource
                        for (var info : infoPerTextures.get(oldTextureId)) {
                            failures.runSafely("block texture", () -> blockId + " (" + info.texture() + ")", (Callable<Void>) () -> {
                                // return the texture of: WoodType: Planks, StoneType: stone, LeavesType: leaves
                                var pal = info.paletteStrategy().getPaletteAndAnimation(blockType, manager);
                                McMetaFile targetAnimation = pal.animation();
                                List<Palette> targetPalette = pal.palette();

                                //sanity check to verity that palette isn't changed. can be removed
                                int oldSize = targetPalette.getFirst().size();

                                if (oldSize != targetPalette.getFirst().size()) {
                                    EveryCompat.LOGGER.error("TextureGenHelper Failture: {} with {}", oldTextureId, pal.id());
                                    throw new RuntimeException("This should not happen. A palette of size 0 was found");
                                }

                                ResourceLocation newId;
                                /// Creating a new Id for the texture
                                if (info.customTexturePath() != null) {
                                    String textureOldPath = info.customTexturePath();
                                    String transformedPath = BlockTypeResTransformer.replaceTypeNoNamespace(textureOldPath, blockType, blockId, baseType.getTypeName());
                                    newId = blockId.withPath(transformedPath);
                                } else if (Objects.nonNull(info.replacePath())) {
                                    String transformedPath = newPath.replace(info.replacePath().getFirst(), info.replacePath().getSecond());
                                    newId = blockId.withPath(transformedPath);
                                } else if (info.keepNamespace()) {
                                    newId = oldTextureId.withPath(newPath);
                                } else { /// DEFAULT
                                    newId = ResourceLocation.fromNamespaceAndPath(blockId.getNamespace(), newPath);
                                }

                                if (newId.getPath().isEmpty()) {
                                    EveryCompat.LOGGER.error("The path of new texture is empty for: {}", info.texture());
                                    return null;
                                }

                                ResourceLocation finalNewId = newId;
                                sink.addTextureIfNotPresent(manager, newId, () -> {
                                    Respriter respriter = respriterSet.getValue();
                                    TextureImage img = respriter.recolorWithAnimation(targetPalette, targetAnimation);
                                    if (info.overlay() != null) getAndApplyOverlay(img, info.overlay(), manager);
                                    postProcessSpecialTexture(blockType, finalNewId, manager, img, info);
                                    return img;
                                });
                                return null;
                            });
                        }
                    }
                });
            }

        } finally {
            imagesToClose.forEach(TextureImage::close);
        }
    }

    //post process some textures.
    @SuppressWarnings("UnusedReturnValue")
    private static <T extends BlockType> TextureImage postProcessSpecialTexture(T blockType, ResourceLocation newId, ResourceManager manager,
                                                                                TextureImage texture, TextureInfo textureInfo) {
        if (blockType.getClass() == WoodType.class) {
            CompatSpritesHelper.maybePostProcessWoodTexture((WoodType) blockType, newId, manager, texture, textureInfo);
        }
        return texture;
    }

    private static void getAndApplyOverlay(TextureImage image, ResourceLocation overlayLocation, ResourceManager manager) {
        try (TextureImage overlayTexture = TextureImage.open(manager, overlayLocation)) {
            TextureOps.applyOverlay(image, overlayTexture);
        } catch (Exception e) {
            TaskRunnerWithFailureCollection.active().record("texture overlay", overlayLocation::toString, e);
        }
    }

}
