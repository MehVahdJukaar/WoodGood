package net.mehvahdjukaar.every_compat.api;

import net.mehvahdjukaar.every_compat.misc.CompatSpritesHelper;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.resources.textures.SpriteUtils;
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

    // ──────────────────────────────── Below Can Be Used In addTexture() or addTextureM() -────────────────────────────────
    public static final PaletteStrategy MAIN_CHILD = registerCached(PaletteStrategies::makePaletteFromMainChild);

    public static final PaletteStrategy PLANKS_STANDARD = registerCached((blockType, manager) -> PaletteStrategies.makePaletteFromChild(
            blockType, manager, VanillaWoodChildKeys.PLANKS, null, null));

    public static final PaletteStrategy LOG_SIDE_STANDARD = registerCached((blockType, manager) -> PaletteStrategies.makePaletteFromChild(
            blockType, manager, VanillaWoodChildKeys.PLANKS, CompatSpritesHelper.LOOKS_LIKE_SIDE_LOG_TEXTURE, null));

    //TODO: make this not side (top)? i guess. or always use the one below. otherwise these might be equal or just incorrect sinde side inst specified
    //so yeah delete, use below
    public static final PaletteStrategy STRIPPED_LOG_TOP_STANDARD = registerCached((blockType, manager) -> PaletteStrategies.makePaletteFromChild(
            blockType, manager, VanillaWoodChildKeys.STRIPPED_LOG, CompatSpritesHelper.LOOKS_LIKE_TOP_LOG_TEXTURE, null));

    public static final PaletteStrategy STRIPPED_LOG_SIDE_STANDARD = registerCached((t, manager) -> PaletteStrategies.makePaletteFromChild(
            t, manager, VanillaWoodChildKeys.STRIPPED_LOG, CompatSpritesHelper.LOOKS_LIKE_SIDE_LOG_TEXTURE, null));


    public static final PaletteStrategy SIGN_LIKE = registerCached((blockType, manager) -> {
        try (TextureImage plankTexture = TextureImage.open(manager,
                RPUtils.findFirstBlockTextureLocation(manager, blockType.getBlockOfThis(VanillaWoodChildKeys.PLANKS)))) {
            //that method likely sholdn't be in ML...
            List<Palette> targetPalette = SpriteUtils.extrapolateSignBlockPalette(plankTexture);
            return PaletteStrategy.PaletteAndAnimation.of(targetPalette, plankTexture.getMcMeta());
        } catch (Exception e) {
            return null;
        }
    });

    public static final PaletteStrategy PLANKS_REMOVE_DARKEST = registerCached((blockType, manager) ->
            PaletteStrategies.makePaletteFromChild(
                    blockType, manager, VanillaWoodChildKeys.PLANKS, null,
                    (p) -> {
                        if (p.size() > 2) p.remove(p.getDarkest());
                    }));

    public static final PaletteStrategy PLANKS_REMOVE_2_DARKEST = registerCached((blockType, manager) ->
            PaletteStrategies.makePaletteFromChild(
                    blockType, manager, VanillaWoodChildKeys.PLANKS, null,
                    (p) -> {
                        if (p.size() > 3) {
                            p.remove(p.getDarkest());
                            p.remove(p.getDarkest());
                        }
                    }));

    public static final PaletteStrategy PLANKS_LOW_CONTRAST = registerCached((blockType, manager) -> PaletteStrategies.makePaletteFromChild(
            blockType, manager, VanillaWoodChildKeys.PLANKS, null,
            (p) -> {
                //luminance step is the distance between 2 colors. Essentially contrast
                float averageStep = p.getAverageLuminanceStep();
                //lower step = lower contrast. Tweak as needed
                p.matchLuminanceStep(averageStep * 0.9f);
                //TODO: tweak that magic number as needed. below was old approach
                /*
                p.remove(p.getLightest());
                p.increaseInner();
                p.remove(p.getDarkest());
                p.increaseInner();
                p.remove(p.getLightest());
                p.increaseInner();
                p.remove(p.getDarkest());
                */
            }));

    public static final PaletteStrategy WOOD_ITEM = registerCached((blockType, manager) ->
            PaletteStrategies.makePaletteFromMainChild(blockType, manager,
                    SpriteUtils::extrapolateSignBlockPalette));

// ──────────────────────────────────────── End ────────────────────────────────────────

    //other bad code...

    public static PaletteStrategy.PaletteAndAnimation makePaletteFromMainChild(BlockType blockType, ResourceManager manager) {
        return makePaletteFromMainChild(blockType, manager, null);
    }

    public static PaletteStrategy.PaletteAndAnimation makePaletteFromMainChild(BlockType blockType, ResourceManager manager,
                                                                               @Nullable Consumer<Palette> paletteTransform) {
        ItemLike mainChild = blockType.mainChild();
        Block mainWoodTypeBlock = null;
        if (mainChild instanceof Block block) mainWoodTypeBlock = block;
        else if (mainChild instanceof BlockItem blockItem) mainWoodTypeBlock = blockItem.getBlock();
        if (mainWoodTypeBlock == null) {
            throw new UnsupportedOperationException("You need to provide a palette supplier for non block main child");
        }

        try (TextureImage plankTexture = TextureImage.open(manager,
                RPUtils.findFirstBlockTextureLocation(manager, mainWoodTypeBlock))) {
            var targetPalette = Palette.fromAnimatedImage(plankTexture);
            var animation = plankTexture.getMcMeta();
            if (paletteTransform != null) targetPalette.forEach(paletteTransform);
            return PaletteStrategy.PaletteAndAnimation.of(targetPalette, animation);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get mainChild texture for "+ blockType.getId() +" - "+ e);
        }
    }

    // utility function
    // no idea what it does anymore
    public static <T extends BlockType> PaletteStrategy.PaletteAndAnimation makePaletteFromChild(T blockType, ResourceManager m,
                                                                                                 String childKey,
                                                                                                 @Nullable Predicate<String> whichSide,
                                                                                                 @Nullable Consumer<Palette> paletteTransform) {
        var child = blockType.getChild(childKey);
        /// BLOCK
        if (child instanceof Block b) {
            if (whichSide != null) { /// PaletteSupplier: childkey with whichSide - example: log_side or log_top
                try (TextureImage blockTexture = TextureImage.open(m,
                        RPUtils.findFirstBlockTextureLocation(m, b, whichSide))) {

                    List<Palette> targetPalette = Palette.fromAnimatedImage(blockTexture);
                    if (paletteTransform != null) targetPalette.forEach(paletteTransform);
                    return PaletteStrategy.PaletteAndAnimation.of(targetPalette, blockTexture.getMcMeta());
                } catch (Exception e) {
                    throw new RuntimeException(String.format("Failed to generate palette for %s : %s", blockType, e));
                }
            } else { /// default PaletteSupplier: planks
                try (TextureImage plankTexture = TextureImage.open(m,
                        RPUtils.findFirstBlockTextureLocation(m, b))) {

                    List<Palette> targetPalette = Palette.fromAnimatedImage(plankTexture);
                    if (paletteTransform != null) targetPalette.forEach(paletteTransform);
                    return PaletteStrategy.PaletteAndAnimation.of(targetPalette, plankTexture.getMcMeta());
                } catch (Exception e) {
                    throw new RuntimeException(String.format("Failed to generate palette for %s : %s", blockType, e));
                }
            }
            /// ITEM
        } else if (child instanceof Item i) {
            /// Default PaletteSupplier: planks
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

    private static class Cached implements PaletteStrategy {
        private final Map<BlockType, PaletteStrategy.PaletteAndAnimation> cache = new HashMap<>();
        private final PaletteStrategy factory;

        private Cached(PaletteStrategy factory) {
            this.factory = factory;
        }

        @Override
        public PaletteAndAnimation getPaletteAndAnimation(BlockType t, ResourceManager manager) throws Exception {
            var existing = cache.get(t);
            if (existing == null) {
                existing = factory.getPaletteAndAnimation(t, manager);
                cache.put(t, existing);
            }
            return existing;
        }
    }


}
