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

import java.io.IOException;
import java.util.*;

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
            Palette oakPlanksPalette = Palette.fromImage(oakPlanksTexture); //This is for auto-masking experimment code found in STAGE-0 Method

            Map<ResourceLocation, Respriter> respriters = new HashMap<>();
            Map<ResourceLocation, TextureImage> partialRespriters = new HashMap<>();
            Palette globalPalette = Palette.empty();

            Multimap<ResourceLocation, TextureInfo> infoPerTextures = ArrayListMultimap.create();
            TaskRunnerWithFailureCollection failures = TaskRunnerWithFailureCollection.active();

            // STAGE 0 - Contains O(n)
            transformImagesIntoRespriters(manager, textureInfos, mergePalette, infoPerTextures, respriters, imagesToClose, globalPalette, partialRespriters, failures);

            // STAGE 1 - Contains O(n)
            AddRespriterstoMaps(partialRespriters, respriters, globalPalette);

            // STAGE 2 A to C - Contains O(n^3) - need to be improved to have better performance
            iterateEntryAndThenCreateNewPath(sink, manager, baseType, entries, failures, respriters, infoPerTextures);

        } finally {
            imagesToClose.forEach(TextureImage::close);
        }
    }

    ///STAGE-0: Adding multiple textures from one block into Respriter without/with mask ->
    /// <br>* infoPerTextures (MultiMap)
    /// <br>* TextureInfos (Set)
    /// <br>* Respriters (Map)
    /// <br>* imagesToClose (Map)
    /// <br>* partialRespriters (Map)
    private static void transformImagesIntoRespriters(ResourceManager manager, Set<TextureInfo> textureInfos,
                                                      boolean mergePalette,
                                                      Multimap<ResourceLocation, TextureInfo> infoPerTextures,
                                                      Map<ResourceLocation, Respriter> respriters,
                                                      List<TextureImage> imagesToClose,
                                                      Palette globalPalette,
                                                      Map<ResourceLocation, TextureImage> partialRespriters,
                                                      TaskRunnerWithFailureCollection failures) {
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
                        /// Disabled for now because autoMask() is not being used...
//                            if (textureInfo.autoMask()) {
//                                if (mergePalette) {
//                                    globalPalette.addAll(oakPlanksPalette);
//                                    partialRespriters.put(textureId, main);
//                                } else {
//                                    respriters.put(textureId, Respriter.ofPalette(main, oakPlanksPalette));
//                                }
//                            } else {
                            mask = TextureImage.open(manager, maskId);
                            if (mergePalette) {
                                globalPalette.addAll(Palette.fromImage(main, mask, 0));
                                partialRespriters.put(textureId, main);
                            } else {
                                respriters.put(textureId, Respriter.masked(main, mask));
                            }
//                            }

                    } else {
                        if (mergePalette) {
                            globalPalette.addAll(Palette.fromImage(main, null, 0));
                            partialRespriters.put(textureId, main);
                        } else {
                            respriters.put(textureId, Respriter.of(main));
                        }
                    }
                }
            } catch (IOException e) {
                failures.record("source texture", () -> String.valueOf(textureInfo), e);
            }
        }
    }

    ///STAGE-1: Add respriters from PartialRespriters (Map) to Respriters (Map) with Palette
    private static void AddRespriterstoMaps(Map<ResourceLocation, TextureImage> partialRespriters, Map<ResourceLocation, Respriter> respriters, Palette globalPalette) {
        for (var e : partialRespriters.entrySet()) {
            respriters.put(e.getKey(), Respriter.ofPalette(e.getValue(), globalPalette));
        }
    }

    /**
     * <p style="color: YELLOW;">STAGE-2A:</p>
     * Get BlockType & Block by iterating entries (Map)<br><br>
     *
     * <p style="color: SILVER;">DETAILS:</p>
     * 1) iterateEntryAndThenCreateNewPath()<br>
     * 2) createNewPathAndThenCreateTexture()<br>
     * 3) createTextureAndAddToResource()<br><br>
     *
     * Note the same keywords are seen in the method's name that are nested methods where 3 methods are sequencially executed
    **/
    private static <T extends BlockType, O extends ItemLike> void iterateEntryAndThenCreateNewPath(ResourceSink sink, ResourceManager manager, T baseType, Map<T, O> entries, TaskRunnerWithFailureCollection failures, Map<ResourceLocation, Respriter> respriters, Multimap<ResourceLocation, TextureInfo> infoPerTextures) {
        for (var entry : entries.entrySet()) {
            ItemLike block = entry.getValue();
            T blockType = entry.getKey();
            // skips disabled ones
            // actually we dont otherwise we get mission texture log spam. TODO: replace models with empty dummy instead
            // if (!ModConfigs.isEntryEnabled(w, b)) continue;
            ResourceLocation blockId = Utils.getID(block);

            failures.runSafely("block texture", blockId::toString, () -> {
                createNewPathAndThenCreateTexture(sink, manager, baseType, respriters, blockType, blockId, infoPerTextures, failures);
            });
        }
    }

    ///STAGE-2B: Creating new Path to add the new textures via the resources
    private static <T extends BlockType> void createNewPathAndThenCreateTexture(ResourceSink sink, ResourceManager manager, T baseType, Map<ResourceLocation, Respriter> respriters, T blockType, ResourceLocation blockId, Multimap<ResourceLocation, TextureInfo> infoPerTextures, TaskRunnerWithFailureCollection failures) {
        for (var respriterSet : respriters.entrySet()) {
            ResourceLocation oldTextureId = respriterSet.getKey();
            String baseOldPath = oldTextureId.getPath();

            String newPath = BlockTypeResTransformer.replaceTypeNoNamespace(baseOldPath, blockType, blockId, baseType.getTypeName());

            createTextureAndAddToResource(sink, manager, baseType, respriterSet, infoPerTextures, oldTextureId, failures, blockId, blockType, newPath);
        }
    }

    ///STAGE-2C: Generating the textures & Add it to the resource
    private static <T extends BlockType> void createTextureAndAddToResource(ResourceSink sink, ResourceManager manager, T baseType, Map.Entry<ResourceLocation, Respriter> respriterSet, Multimap<ResourceLocation, TextureInfo> infoPerTextures, ResourceLocation oldTextureId, TaskRunnerWithFailureCollection failures, ResourceLocation blockId, T blockType, String newPath) {
        for (var info : infoPerTextures.get(oldTextureId)) {
            failures.runSafely("texture generation", () -> blockId + " (" + info.texture() + ")", () -> {
                // return the texture of: WoodType: Planks, StoneType: stone, LeavesType: leaves
                var pal = info.paletteStrategy().getPaletteAndAnimation(blockType, manager);
                McMetaFile targetAnimation = pal.animation();
                List<Palette> targetPalette = pal.palette();

                //sanity check to verity that palette isn't changed. can be removed
                int oldSize = targetPalette.get(0).size();

                if (oldSize != targetPalette.get(0).size()) {
                    EveryCompat.LOGGER.error("TextureGenHelper Failure: {} with {}", oldTextureId, pal.id());
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
                    newId = new ResourceLocation(blockId.getNamespace(), newPath);
                }

                if (newId.getPath().isEmpty()) {
                    EveryCompat.LOGGER.error("The path of new texture is empty for: {}", info.texture());
                    return null;
                }

                ResourceLocation finalNewId = newId;
                sink.addTextureIfNotPresent(manager, newId.toString(), () -> {
                    Respriter respriter = respriterSet.getValue();
                    TextureImage img = (info.noAnimation()) ? respriter.recolor(targetPalette) : respriter.recolorWithAnimation(targetPalette, targetAnimation);
                    if (info.overlay() != null) getAndApplyOverlay(img, info.overlay(), manager);
                    postProcessSpecialTexture(blockType, finalNewId, manager, img, info);
                    return img;
                });
                return null;
            });
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
