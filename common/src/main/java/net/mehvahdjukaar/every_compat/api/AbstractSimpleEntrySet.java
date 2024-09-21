package net.mehvahdjukaar.every_compat.api;

import com.mojang.datafixers.util.Pair;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.configs.WoodConfigs;
import net.mehvahdjukaar.every_compat.misc.ResourcesUtils;
import net.mehvahdjukaar.every_compat.misc.SpriteHelper;
import net.mehvahdjukaar.moonlight.api.resources.BlockTypeResTransformer;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.SimpleTagBuilder;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynClientResourcesProvider;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynamicDataPack;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.resources.textures.Respriter;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage;
import net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.resources.metadata.animation.AnimationMetadataSection;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.nio.file.ProviderNotFoundException;
import java.util.*;
import java.util.function.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Contrary to popular belief, this class is indeed not simple. Its usage, however is
public abstract class AbstractSimpleEntrySet<T extends BlockType, B extends Block, I extends Item> extends EntrySet<T, B, I> {

    protected final Class<T> type;

    protected final Pattern nameScheme;

    protected final Supplier<T> baseType;

    public final String postfix;
    @Nullable
    public final String prefix;

    protected final Supplier<CreativeModeTab> tab;
    protected final Map<ResourceLocation, Set<ResourceKey<?>>> tags = new HashMap<>();
    protected final Set<Supplier<ResourceLocation>> recipeLocations = new HashSet<>();
    protected final Set<Pair<ResourceLocation, @Nullable ResourceLocation>> textures = new HashSet<>();
    @Nullable
    protected final BiFunction<T, ResourceManager, Pair<List<Palette>, @Nullable AnimationMetadataSection>> paletteSupplier;
    @Nullable
    protected final Consumer<BlockTypeResTransformer<T>> extraTransform;

    protected final Predicate<T> condition;

    protected AbstractSimpleEntrySet(Class<T> type,
                                  String name, @Nullable String prefix,
                                  Supplier<T> baseType,
                                  Supplier<CreativeModeTab> tab,
                                  @Nullable BiFunction<T, ResourceManager, Pair<List<Palette>, @Nullable AnimationMetadataSection>> paletteSupplier,
                                  @Nullable Consumer<BlockTypeResTransformer<T>> extraTransform,
                                     Predicate<T> condition) {
        super((prefix == null ? "" : prefix + (name.isEmpty() ? "" : "_")) + name);
        this.postfix = name;
        this.prefix = prefix;
        this.tab = tab;
        this.baseType = baseType;
        this.type = type;

        this.extraTransform = extraTransform;
        this.paletteSupplier = paletteSupplier;

        if (this.prefix != null) {
            if (postfix.isEmpty()) {
                nameScheme = Pattern.compile("^" + prefix + "_(.+?)$");
            } else {
                nameScheme = Pattern.compile("^" + prefix + "_(.+?)_" + postfix + "$");
            }
        } else {
            nameScheme = Pattern.compile("^(.+?)_" + postfix + "$");
        }
        this.condition = condition;
    }

    public abstract boolean isDisabled();

    public Class<T> getTypeClass() {
        return type;
    }

    public T getBaseType() {
        return baseType.get();
    }

    public String getEquivalentBlock(CompatModule module, String oldName, String woodFrom) {
        String wood = parseWoodType(oldName);
        if (wood != null) {
            var w = BlockSetAPI.getBlockSet(this.getTypeClass()).get(new ResourceLocation(woodFrom, wood));
            if (w != null) {
                return module.shortenedId() + "/" + w.getNamespace() + "/" + oldName;
            }
        }
        return null;
    }

    //gets the wood type of the given name if it is in this entry set name format
    @Nullable
    public String parseWoodType(String oldName) {
        Matcher m = nameScheme.matcher(oldName);
        if (m.find()) {
            return m.group(1);
        }
        return null;
    }

    protected CreativeModeTab getTab(T w, B b) {
        return WoodConfigs.isEntryEnabled(w, b) ?
                (EveryCompat.MOD_TAB != null ? EveryCompat.MOD_TAB : this.tab.get()) : null;
    }

    @Override
    public void generateTags(CompatModule module, DynamicDataPack pack, ResourceManager manager) {
        if (isDisabled()) return;
        if (!tags.isEmpty()) {
            for (var tb : tags.entrySet()) {
                SimpleTagBuilder builder = SimpleTagBuilder.of(tb.getKey());
                for (var b : getMainEntryMap().entrySet()) {
                    if (WoodConfigs.isEntryEnabled(b.getKey(), b.getValue())) {
                        builder.addEntry(b.getValue());
                    }
                }
                for (var t : tb.getValue()) {
                    pack.addTag(builder, t);
                }
            }
        }
    }

    @Override
    public void generateRecipes(CompatModule module, DynamicDataPack pack, ResourceManager manager) {
        if (isDisabled()) return;
        int i = 0;
        for (var r : this.recipeLocations) {
            var res = r.get();
            try {
                ResourcesUtils.addBlocksRecipes(manager, pack, items, res, baseType.get(), i++);
            } catch (Exception e) {
                EveryCompat.LOGGER.error("Failed to generate recipes for template at location {} ", res);
            }
        }
    }

    @Override
    public void generateTextures(CompatModule module, DynClientResourcesProvider handler, ResourceManager manager) {
        if (isDisabled()) return;
        if (textures.isEmpty()) return;
        List<TextureImage> images = new ArrayList<>();
        try {
            Map<ResourceLocation, Respriter> respriters = new HashMap<>();
            for (var p : textures) {
                ResourceLocation textureId = p.getFirst();
                try {
                    TextureImage main = TextureImage.open(manager, textureId);
                    images.add(main);
                    ResourceLocation m = p.getSecond();
                    Respriter r;
                    if (m != null) {
                        TextureImage mask = TextureImage.open(manager, m);
                        images.add(main);
                        r = Respriter.masked(main, mask);
                    } else {
                        r = Respriter.of(main);
                    }
                    respriters.put(textureId, r);
                } catch (Exception e) {
                    EveryCompat.LOGGER.error("Failed to read block texture at: {}", p, e);
                }
            }

            for (var entry : getMainEntryMap().entrySet()) {
                ItemLike b = entry.getValue();
                T w = entry.getKey();
                //skips disabled ones

                // actually we dont otherwise we get missign texture log spam. TODO: replace models with empty dummy instead
//                if (!WoodConfigs.isEntryEnabled(w, b)) continue;

                ResourceLocation blockId = Utils.getID(b);

                List<Palette> targetPalette = null;
                AnimationMetadataSection animation = null;
                if (paletteSupplier != null) {
                    var pal = paletteSupplier.apply(w, manager);
                    animation = pal.getSecond();
                    targetPalette = pal.getFirst();
                } else {
                    var m = w.mainChild();
                    Block mainBlock = null;
                    if (m instanceof Block bb) mainBlock = bb;
                    else if (m instanceof BlockItem bii) mainBlock = bii.getBlock();
                    if (mainBlock == null) {
                        throw new UnsupportedOperationException("You need to provide a palette supplier for non wood type based blocks");
                    }

                    try (TextureImage plankTexture = TextureImage.open(manager,
                            RPUtils.findFirstBlockTextureLocation(manager, mainBlock))) {
                        targetPalette = Palette.fromAnimatedImage(plankTexture);
                        animation = plankTexture.getMetadata();
                    } catch (Exception ignored) {
                    }
                }
                if (targetPalette == null) {
                    EveryCompat.LOGGER.error("Could not get texture palette for block {} : ", b);
                    continue;
                }
                AnimationMetadataSection finalAnimation = animation;
                List<Palette> finalTargetPalette = targetPalette;

                //sanity check to verity that palette isnt changed. can be removed
                int oldSize = finalTargetPalette.get(0).size();

                for (var re : respriters.entrySet()) {
                    if (oldSize != finalTargetPalette.get(0).size()) {
                        throw new RuntimeException("This should not happen");
                    }
                    String oldPath = re.getKey().getPath();

                    String newId = BlockTypeResTransformer.replaceTypeNoNamespace(oldPath, w,
                            blockId, baseType.get().getTypeName());

                    Respriter respriter = re.getValue();
                    if (type == WoodType.class) {
                        module.addWoodTexture((WoodType) w, handler, manager, newId, () ->
                                respriter.recolorWithAnimation(finalTargetPalette, finalAnimation));

                    } else {
                        handler.addTextureIfNotPresent(manager, newId, () ->
                                respriter.recolorWithAnimation(finalTargetPalette, finalAnimation));

                    }
                }
            }

        } catch (Exception e) {
            EveryCompat.LOGGER.error("Could not generate any block texture for entry set {} : ", module.modRes(this.getName()), e);
        } finally {
            for (var t : images) {
                t.close();
            }
        }

    }

    protected abstract Map<T, ? extends ItemLike> getMainEntryMap();


    protected static class Builder<BL extends Builder<BL, T, B, I>, T extends BlockType, B extends Block, I extends Item> {
        protected final Class<T> type;
        protected final Supplier<T> baseType;
        protected final String name;
        @Nullable
        protected final String prefix;
        protected Supplier<CreativeModeTab> tab = () -> CreativeModeTab.TAB_DECORATIONS;
        @Nullable
        protected BiFunction<T, ResourceManager, Pair<List<Palette>, @Nullable AnimationMetadataSection>> palette = null;
        protected final Map<ResourceLocation, Set<ResourceKey<?>>> tags = new HashMap<>();
        protected final Set<Supplier<ResourceLocation>> recipes = new HashSet<>();
        protected final Set<Pair<ResourceLocation, @Nullable ResourceLocation>> textures = new HashSet<>();
        @Nullable
        protected Consumer<BlockTypeResTransformer<T>> extraModelTransform = null;
        protected Predicate<T> condition = w -> true;

        protected Builder(Class<T> type, String name, @Nullable String prefix, Supplier<T> baseType) {
            this.baseType = baseType;
            this.name = name;
            this.prefix = prefix;
            this.type = type;
        }

        //adds an extra model transform
        public BL addModelTransform(Consumer<BlockTypeResTransformer<T>> transform) {
            this.extraModelTransform = transform;
            return (BL) this;
        }

        //exclusive with addCondition
        public BL requiresChildren(String... childKeys) {
            this.addCondition(w -> {
                for (var c : childKeys) {
                    if (w.getChild(c) == null) return false;
                }
                return true;
            });
            return (BL) this;
        }

        public BL addCondition(Predicate<T> condition) {
            this.condition = condition;
            return (BL) this;
        }

        public BL setTab(Supplier<CreativeModeTab> tab) {
            this.tab = tab;
            return (BL) this;
        }

        public BL addTag(ResourceLocation location, ResourceKey<?> registry) {
            var s = this.tags.computeIfAbsent(location, b -> new HashSet<>());
            s.add(registry);
            return (BL) this;
        }

        public BL addTag(TagKey<?> tag, ResourceKey<?> registry) {
            addTag(tag.location(), registry);
            return (BL) this;
        }

        public BL addRecipe(ResourceLocation resourceLocation) {
            this.recipes.add(() -> resourceLocation);
            return (BL) this;
        }

        public BL addTexture(ResourceLocation resourceLocation) {
            this.textures.add(Pair.of(resourceLocation, null));
            return (BL) this;
        }

        public BL addTextureM(ResourceLocation textureLocation, ResourceLocation maskLocation) {
            this.textures.add(Pair.of(textureLocation, maskLocation));
            return (BL) this;
        }

        //by default, they all use planks palette
        public BL setPalette(BiFunction<T, ResourceManager, Pair<List<Palette>, @Nullable AnimationMetadataSection>> paletteProvider) {
            this.palette = paletteProvider;
            return (BL) this;
        }

        //only works for oak type. Will fail if its used on leaves
        public BL createPaletteFromOak(Consumer<Palette> paletteTransform) {
            return createPaletteFromChild(paletteTransform, "planks", null);
        }

        public BL createPaletteFromChild(Consumer<Palette> paletteTransform, String childKey) {
            return createPaletteFromChild(paletteTransform, childKey, SpriteHelper.LOOKS_LIKE_TOP_LOG_TEXTURE);
        }

        //only works for oak type. Will fail if its used on leaves
        public BL createPaletteFromChild(Consumer<Palette> paletteTransform, String childKey, java.util.function.Predicate<String> whichSide) {
            return this.setPalette((w, m) -> {
                if (whichSide != null) {
                    try (TextureImage plankTexture = TextureImage.open(m,
                            RPUtils.findFirstBlockTextureLocation(m, w.getBlockOfThis(childKey), whichSide))) {

                        List<Palette> targetPalette = Palette.fromAnimatedImage(plankTexture);
                        targetPalette.forEach(paletteTransform);
                        return Pair.of(targetPalette, plankTexture.getMetadata());
                    } catch (Exception e) {
                        throw new RuntimeException(String.format("Failed to generate palette for %s : %s", w, e));
                    }
                }

                try (TextureImage plankTexture = TextureImage.open(m,
                        RPUtils.findFirstBlockTextureLocation(m, w.getBlockOfThis(childKey)))) {

                    List<Palette> targetPalette = Palette.fromAnimatedImage(plankTexture);
                    targetPalette.forEach(paletteTransform);
                    return Pair.of(targetPalette, plankTexture.getMetadata());
                } catch (Exception e) {
                    throw new RuntimeException(String.format("Failed to generate palette for %s : %s", w, e));
                }
            });
        }
    }


}

