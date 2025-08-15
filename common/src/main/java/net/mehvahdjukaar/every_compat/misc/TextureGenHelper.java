package net.mehvahdjukaar.every_compat.misc;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.gson.JsonObject;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.TextureInfo;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.resources.BlockTypeResTransformer;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceSink;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.resources.textures.Respriter;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.moonlight.core.misc.McMetaFile;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

import java.io.InputStream;
import java.util.*;

//Sprite Helper is too big
public class TextureGenHelper {

    //TODO: this is unmanageable. needs to be split in smaller manageable bits and commented better
    public static <T extends BlockType> void generateDefault(ResourceSink sink, ResourceManager manager,
                                                             String modId,
                                                             Set<TextureInfo> textureInfos, T baseType,
                                                             boolean mergePalette,
                                                             Map<T, ?> entries) throws Exception {

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

            /// Adding multiple textures from one block into Respriter without/with mask & infoPerTextures
            for (TextureInfo textureInfo : textureInfos) {
                ResourceLocation textureId = textureInfo.texture();

                try {
                    ResourceLocation maskId = textureInfo.mask();
                    TextureImage main = TextureImage.open(manager, textureId);

                    infoPerTextures.put(textureId, textureInfo);

                    if (textureInfo.copyTexture()) {
                        respriters.put(textureId, Respriter.ofPalette(main, List.of(Palette.empty())));
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
                    EveryCompat.LOGGER.error("Could not generate textures for {}", textureInfo, e);
                } catch (Exception e) {
                    if (PlatHelper.isDev()) throw new RuntimeException(e);
                    EveryCompat.LOGGER.error("Failed to read block texture at {}", textureInfo, e);
                }
            }

            for (var e : partialRespriters.entrySet()) {
                respriters.put(e.getKey(), Respriter.ofPalette(e.getValue(), globalPalette));
            }
            /// Swapping out the old palettes of the texture with new palettes
            for (var entry : entries.entrySet()) {
                Object block = entry.getValue();
                T blockType = entry.getKey();
                // skips disabled ones
                // actually we dont otherwise we get mission texture log spam. TODO: replace models with empty dummy instead
                // if (!ModConfigs.isEntryEnabled(w, b)) continue;
                ResourceLocation blockId = Utils.getID(block);


                /// Creating new Path to add the new textures via the resources
                for (var re : respriters.entrySet()) {


                    ResourceLocation oldTextureId = re.getKey();
                    String oldPath = oldTextureId.getPath();

                    //TODO: ugly, change
                    // boatload's texture path has 2 folder
                    String newPath = (oldPath.startsWith("entity/") && modId.equals("boatload"))
                            ? BlockTypeResTransformer.replaceFullGenericType(oldPath, blockType, blockId, baseType.getTypeName(), null, 2)
                            // Default
                            : BlockTypeResTransformer.replaceTypeNoNamespace(oldPath, blockType, blockId, baseType.getTypeName());

                    String newId;
                    boolean isOnAtlas;

                    /// Adding the textures to the resource
                    for (var info : infoPerTextures.get(oldTextureId)) {

                        // return the texture of: WoodType: Planks, StoneType: stone, LeavesType: leaves
                        var pal = info.paletteStrategy().getPaletteAndAnimation(blockType, manager);
                        McMetaFile targetAnimation = pal.animation();
                        List<Palette> targetPalette = pal.palette();

                        //sanity check to verity that palette isn't changed. can be removed
                        int oldSize = targetPalette.get(0).size();

                        if (oldSize != targetPalette.get(0).size()) {
                            throw new RuntimeException("This should not happen. A palette of size 0 was found");
                        }

                        if (info.customTexturePath() != null) {
                            oldPath = info.customTexturePath();
                            newId = EveryCompat.MOD_ID + ":" + BlockTypeResTransformer.replaceTypeNoNamespace(oldPath, blockType, blockId, baseType.getTypeName());
                        } else if (info.keepNamespace()) {
                            newId = oldTextureId.withPath(newPath).toString();
                        } else {
                            newId = new ResourceLocation(blockId.getNamespace(), newPath).toString();
                        }
                        if (newId.isEmpty()) {
                            EveryCompat.LOGGER.error("The path of new texture is empty for: {}", info.texture());
                            continue;
                        }

                        isOnAtlas = info.onAtlas();

                        String finalNewId = newId;
                        sink.addTextureIfNotPresent(manager, newId, () -> {
                            Respriter respriter = re.getValue();
                            TextureImage img = respriter.recolorWithAnimation(targetPalette, targetAnimation);
                            postProcessSpecialTexture(blockType, finalNewId, manager, img);
                            return img;
                        }, isOnAtlas);
                    }
                }
            }

        } finally {
            imagesToClose.forEach(TextureImage::close);
        }
    }

    //post process some textures.
    private static <T extends BlockType> TextureImage postProcessSpecialTexture(T blockType, String newId, ResourceManager manager,
                                                                                TextureImage texture) {
        if (blockType.getClass() == WoodType.class) {
            CompatSpritesHelper.maybePostProcessWoodTexture((WoodType) blockType, newId, manager, texture);
        }
        return texture;
    }


}
