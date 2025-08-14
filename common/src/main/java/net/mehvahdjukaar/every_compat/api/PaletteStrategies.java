package net.mehvahdjukaar.every_compat.api;

import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Register here your palette strategies
 * Note that base interface can still be used but its heavily recommended to register here and use the returned static object
 */
public class PaletteStrategies {

    private static final Set<Cached> cachedStrategies = new HashSet<>();

    @ApiStatus.Internal
    public static void clearCache() {
        cachedStrategies.forEach(c -> c.cache.clear());
    }

    public static PaletteStrategy registerCached(PaletteStrategy factory) {
        Cached c = new Cached(factory);
        cachedStrategies.add(c);
        return c;
    }


    public static final PaletteStrategy MAIN_CHILD = registerCached(PaletteStrategies::makePaletteFromMainChild);

    public static final PaletteStrategy FROM_WOOD_PLANKS = registerCached((t, manager) -> PaletteStrategies.makePaletteFromChild(
            t, manager, VanillaWoodChildKeys.PLANKS, null, null));


    private static class Cached implements PaletteStrategy {
        private final Map<BlockType, PaletteStrategy.PaletteAndAnimation> cache = new HashMap<>();
        private final PaletteStrategy factory;

        private Cached(PaletteStrategy factory) {
            this.factory = factory;
        }

        @Override
        public PaletteAndAnimation getPaletteAndAnimation(BlockType t, ResourceManager manager) {
            return cache.computeIfAbsent(t, blockType -> factory.getPaletteAndAnimation(t, manager));
        }
    }


    //other bad code...
    public static PaletteStrategy.PaletteAndAnimation makePaletteFromMainChild(BlockType w, ResourceManager manager) {
        ItemLike mainChild = w.mainChild();
        Block mainWoodTypeBlock = null;
        if (mainChild instanceof Block bb) mainWoodTypeBlock = bb;
        else if (mainChild instanceof BlockItem bii) mainWoodTypeBlock = bii.getBlock();
        if (mainWoodTypeBlock == null) {
            throw new UnsupportedOperationException("You need to provide a palette supplier for non block main child");
        }

        try (TextureImage plankTexture = TextureImage.open(manager,
                RPUtils.findFirstBlockTextureLocation(manager, mainWoodTypeBlock))) {
            var targetPalette = Palette.fromAnimatedImage(plankTexture);
            var animation = plankTexture.getMcMeta();
            return PaletteStrategy.PaletteAndAnimation.of(targetPalette, animation);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get main block type texture", e);
        }
    }

    // utility function
    // no idea what it does anymore
    public static <T extends BlockType> PaletteStrategy.PaletteAndAnimation makePaletteFromChild(T blockType, ResourceManager m,
                                                                                                 String childKey,
                                                                                                 @Nullable Predicate<String> whichSide,
                                                                                                 @Nullable Consumer<Palette> paletteTransform) {
        var child = blockType.getChild(childKey);
        if (child instanceof Block b) {
            if (whichSide != null) {
                try (TextureImage blockTexture = TextureImage.open(m,
                        RPUtils.findFirstBlockTextureLocation(m, b, whichSide))) {

                    List<Palette> targetPalette = Palette.fromAnimatedImage(blockTexture);
                    if (paletteTransform != null) targetPalette.forEach(paletteTransform);
                    return PaletteStrategy.PaletteAndAnimation.of(targetPalette, blockTexture.getMcMeta());
                } catch (Exception e) {
                    throw new RuntimeException(String.format("Failed to generate palette for %s : %s", blockType, e));
                }
            } else { // whichSide should be defaulted to use all_texture (like planks)  -Xelbayria's assumption
                try (TextureImage plankTexture = TextureImage.open(m,
                        RPUtils.findFirstBlockTextureLocation(m, b))) {

                    List<Palette> targetPalette = Palette.fromAnimatedImage(plankTexture);
                    if (paletteTransform != null) targetPalette.forEach(paletteTransform);
                    return PaletteStrategy.PaletteAndAnimation.of(targetPalette, plankTexture.getMcMeta());
                } catch (Exception e) {
                    throw new RuntimeException(String.format("Failed to generate palette for %s : %s", blockType, e));
                }
            }
        } else if (child instanceof Item i) {
            try (TextureImage plankTexture = TextureImage.open(m,
                    RPUtils.findFirstItemTextureLocation(m, i))) {

                List<Palette> targetPalette = Palette.fromAnimatedImage(plankTexture);
                if (paletteTransform != null) targetPalette.forEach(paletteTransform);
                return PaletteStrategy.PaletteAndAnimation.of(targetPalette, plankTexture.getMcMeta());
            } catch (Exception e) {
                throw new RuntimeException(String.format("Failed to generate palette for %s : %s", blockType, e));
            }
        }
        throw new RuntimeException("No child with key \"" + childKey + "\" found for" + blockType.getId());
    }


}
