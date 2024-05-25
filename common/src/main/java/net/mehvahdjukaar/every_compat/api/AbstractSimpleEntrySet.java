package net.mehvahdjukaar.every_compat.api;

import com.google.common.base.Suppliers;
import com.mojang.datafixers.util.Pair;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.configs.ModConfigs;
import net.mehvahdjukaar.every_compat.misc.ResourcesUtils;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.resources.BlockTypeResTransformer;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.SimpleTagBuilder;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynClientResourcesGenerator;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynamicDataPack;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.resources.textures.Respriter;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage;
import net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.moonlight.api.util.math.colors.RGBColor;
import net.minecraft.client.resources.metadata.animation.AnimationMetadataSection;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.mehvahdjukaar.every_compat.api.AbstractSimpleEntrySet.Builder.AUTO_MASK_MARKER;

//contrary to popular belief this class is indeed not simple. Its usage however is
public abstract class AbstractSimpleEntrySet<T extends BlockType, B extends Block, I extends Item> implements EntrySet<T> {

    public final Map<T, B> blocks = new HashMap<>();
    public final Map<T, I> items = new HashMap<>();

    protected final Class<T> type;

    protected final Pattern nameScheme;

    protected final Supplier<T> baseType;

    public final String typeName;

    public final String postfix;
    @Nullable
    public final String prefix;
    protected final boolean mergePalette;

    protected final Supplier<ResourceKey<CreativeModeTab>> tab;
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
                                     Supplier<ResourceKey<CreativeModeTab>> tab,
                                     @Nullable BiFunction<T, ResourceManager, Pair<List<Palette>, @Nullable AnimationMetadataSection>> paletteSupplier,
                                     @Nullable Consumer<BlockTypeResTransformer<T>> extraTransform,
                                     boolean mergePalette,
                                     Predicate<T> condition) {
        this.typeName = (prefix == null ? "" : prefix + (name.isEmpty() ? "" : "_")) + name;
        this.postfix = name;
        this.prefix = prefix;
        this.tab = tab;
        this.baseType = baseType;
        this.type = type;

        this.extraTransform = extraTransform;
        this.paletteSupplier = paletteSupplier;
        this.mergePalette = mergePalette;

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

    public String getName() {
        return typeName;
    }

    @Override
    public @Nullable Item getItemOf(T type) {
        var i = items.get(type);
        if (ModConfigs.isEntryEnabled(type, i)) return i;
        return null;
    }

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

    @Override
    public void generateTags(CompatModule module, DynamicDataPack pack, ResourceManager manager) {
        if (!tags.isEmpty()) {
            for (var tb : tags.entrySet()) {
                SimpleTagBuilder builder = SimpleTagBuilder.of(tb.getKey());
                for (var b : getDefaultEntries().entrySet()) {
                    if (ModConfigs.isEntryEnabled(b.getKey(), b.getValue())) {
                        builder.addEntry(b.getValue());
                    }
                }
                for (var t : tb.getValue()) {
                    pack.addTag(builder, t);
                }
            }
        }
    }

    public Map<T, ?> getDefaultEntries() {
        return blocks;
    }

    @Override
    public void generateRecipes(CompatModule module, DynamicDataPack pack, ResourceManager manager) {
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

    // i have no fucking clue whats going on here
    @Override
    public void generateTextures(CompatModule module, DynClientResourcesGenerator handler, ResourceManager manager) {
        if (textures.isEmpty()) return;

        List<TextureImage> images = new ArrayList<>();
        try (TextureImage oakPlanksTexture = TextureImage.open(manager,
                RPUtils.findFirstBlockTextureLocation(manager, (Block) this.getBaseType().mainChild()))) {
            Palette oakPlanksPalette = Palette.fromImage(oakPlanksTexture);

            Map<ResourceLocation, Respriter> respriters = new HashMap<>();
            Map<ResourceLocation, TextureImage> partialRespriters = new HashMap<>();
            Palette globalPalette = Palette.ofColors(new ArrayList<RGBColor>());


            for (var p : textures) {
                ResourceLocation textureId = p.getFirst();

                try {
                    ResourceLocation maskId = p.getSecond();
                    //Simple texture copy. Ugly... Not even used for more than 1 mod
                    if (maskId != null && textureId == null) {
                        TextureImage main = TextureImage.open(manager, maskId);
                        respriters.put(maskId, Respriter.ofPalette(main, List.of(Palette.ofColors(List.of(new RGBColor(1))))));
                    } else {
                        TextureImage main = TextureImage.open(manager, textureId);
                        images.add(main);

                        if (maskId != null) {
                            TextureImage mask;
                            if (maskId.equals(AUTO_MASK_MARKER)) {
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
                    EveryCompat.LOGGER.error("Could not generate textures for {}", p, e);
                } catch (Exception e) {
                    if (PlatHelper.isDev()) throw new RuntimeException(e);
                    EveryCompat.LOGGER.error("Failed to read block texture at {}", p, e);
                }
            }

            for (var e : partialRespriters.entrySet()) {
                respriters.put(e.getKey(), Respriter.ofPalette(e.getValue(), globalPalette));
            }

            for (var entry : getDefaultEntries().entrySet()) {
                var b = entry.getValue();
                T w = entry.getKey();
                // skips disabled ones
                // actually we dont otherwise we get missign texture log spam. TODO: replace models with empty dummy instead
                // if (!ModConfigs.isEntryEnabled(w, b)) continue;

                ResourceLocation blockId = Utils.getID(b);

                List<Palette> targetPalette = null;
                AnimationMetadataSection animation = null;
                if (paletteSupplier != null) {
                    var pal = paletteSupplier.apply(w, manager);
                    animation = pal.getSecond();
                    targetPalette = pal.getFirst();
                } else {
                    var m = w.mainChild();
                    Block mainWoodTypeBlock = null;
                    if (m instanceof Block bb) mainWoodTypeBlock = bb;
                    else if (m instanceof BlockItem bii) mainWoodTypeBlock = bii.getBlock();
                    if (mainWoodTypeBlock == null) {
                        throw new UnsupportedOperationException("You need to provide a palette supplier for non wood type based blocks");
                    }

                    try (TextureImage plankTexture = TextureImage.open(manager,
                            RPUtils.findFirstBlockTextureLocation(manager, mainWoodTypeBlock))) {
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

                //sanity check to verity that palette isn't changed. can be removed
                int oldSize = finalTargetPalette.get(0).size();

                for (var re : respriters.entrySet()) {
                    if (oldSize != finalTargetPalette.get(0).size()) {
                        throw new RuntimeException("This should not happen");
                    }
                    String oldPath = re.getKey().getPath();

                    String newId = BlockTypeResTransformer.replaceTypeNoNamespace(oldPath, w, blockId, baseType.get().getTypeName());

                    Respriter respriter = re.getValue();
                    boolean isOnAtlas = !newId.startsWith("entity/");
                    if (type == WoodType.class) {
                        addWoodTexture((WoodType) w, handler, manager, newId, () ->
                                respriter.recolorWithAnimation(finalTargetPalette, finalAnimation), isOnAtlas);

                    } else {
                        handler.addTextureIfNotPresent(manager, newId, () ->
                                respriter.recolorWithAnimation(finalTargetPalette, finalAnimation), isOnAtlas);
                    }
                }
            }

        } catch (Exception e) {
            EveryCompat.LOGGER.error("Could not generate any block texture for entry set {} : ",
                    module == null ? "dummy" : module.modRes(this.getName()), e);
        } finally {
            for (var t : images) {
                t.close();
            }
        }

    }


    //post process some textures. currently only ecologics azalea
    public void addWoodTexture(WoodType wood, DynClientResourcesGenerator handler, ResourceManager manager,
                               String path, Supplier<TextureImage> textureSupplier, boolean isOnAtlas) {
        handler.addTextureIfNotPresent(manager, path, () -> {
            var t = textureSupplier.get();
            maybeFlowerAzalea(t, manager, wood);
            return t;
        }, isOnAtlas);
    }

    //for ecologics
    protected void maybeFlowerAzalea(TextureImage image, ResourceManager manager, WoodType woodType) {
        if (woodType.getId().toString().equals("ecologics:flowering_azalea")) {
            WoodType azalea = WoodTypeRegistry.getValue(new ResourceLocation("ecologics:azalea"));
            if (azalea != null) {
                try (TextureImage mask = TextureImage.open(manager,
                        EveryCompat.res("block/ecologics_overlay"));
                     TextureImage plankTexture = TextureImage.open(manager,
                             RPUtils.findFirstBlockTextureLocation(manager, azalea.planks))) {

                    Respriter respriter = Respriter.of(image);
                    var temp = respriter.recolorWithAnimationOf(plankTexture);

                    image.applyOverlayOnExisting(temp, mask);
                    temp.close();

                } catch (Exception e) {
                    EveryCompat.LOGGER.warn("failed to apply azalea overlay for wood type {} and image {}", woodType, image);
                }
            }
        }
    }


    protected static class Builder<BL extends Builder<BL, T, B, I>, T extends BlockType, B extends Block, I extends Item> {
        protected final Class<T> type;
        protected final Supplier<T> baseType;
        protected final String name;
        @Nullable
        protected final String prefix;
        protected Supplier<ResourceKey<CreativeModeTab>> tab = () -> CreativeModeTabs.BUILDING_BLOCKS;
        @Nullable
        protected BiFunction<T, ResourceManager, Pair<List<Palette>, @Nullable AnimationMetadataSection>> palette = null;
        protected final Map<ResourceLocation, Set<ResourceKey<?>>> tags = new HashMap<>();
        protected final Set<Supplier<ResourceLocation>> recipes = new HashSet<>();
        protected final Set<Pair<ResourceLocation, @Nullable ResourceLocation>> textures = new HashSet<>();
        protected boolean useMergedPalette;
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

        public BL setTabKey(Supplier<ResourceKey<CreativeModeTab>> tab) {
            this.tab = tab;
            return (BL) this;
        }

        public BL setTab(Supplier<CreativeModeTab> tab) {
            this.tab = Suppliers.memoize(() -> BuiltInRegistries.CREATIVE_MODE_TAB.getResourceKey(tab.get()).get());
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

        protected static final ResourceLocation AUTO_MASK_MARKER = new ResourceLocation("auto_mask");

        // adds a texture with automatic masking. Experimental
        public BL addTextureAutoM(ResourceLocation textureLocation) {
            this.textures.add(Pair.of(textureLocation, AUTO_MASK_MARKER));
            return (BL) this;
        }

        // Experimental. IDK why anybody would reuse the same texture seems like a waste of resources
        public BL copyTexture(ResourceLocation textureLocation) {
            this.textures.add(Pair.of(null, textureLocation));
            return (BL) this;
        }


        public BL useMergedPalette() {
            this.useMergedPalette = true;
            return (BL) this;
        }

        //by default, they all use planks palette
        public BL setPalette(BiFunction<T, ResourceManager, Pair<List<Palette>, @Nullable AnimationMetadataSection>> paletteProvider) {
            this.palette = paletteProvider;
            return (BL) this;
        }

        //only works for oak type. Will fail if its used on leaves
        public BL createPaletteFromOak(Consumer<Palette> paletteTransform) {
            return createPaletteFromChild(paletteTransform, "planks");
        }

        public BL createPaletteFromChild(Consumer<Palette> paletteTransform, String childKey) {
            return this.setPalette((w, m) -> {
                var c = w.getChild(childKey);
                if (c instanceof Block b) {
                    try (TextureImage plankTexture = TextureImage.open(m,
                            RPUtils.findFirstBlockTextureLocation(m, b))) {

                        List<Palette> targetPalette = Palette.fromAnimatedImage(plankTexture);
                        targetPalette.forEach(paletteTransform);
                        return Pair.of(targetPalette, plankTexture.getMetadata());
                    } catch (Exception e) {
                        throw new RuntimeException(String.format("Failed to generate palette for %s : %s", w, e));
                    }
                } else if (c instanceof Item i) {
                    try (TextureImage plankTexture = TextureImage.open(m,
                            RPUtils.findFirstItemTextureLocation(m, i))) {

                        List<Palette> targetPalette = Palette.fromAnimatedImage(plankTexture);
                        targetPalette.forEach(paletteTransform);
                        return Pair.of(targetPalette, plankTexture.getMetadata());
                    } catch (Exception e) {
                        throw new RuntimeException(String.format("Failed to generate palette for %s : %s", w, e));
                    }
                }
                throw new RuntimeException("No child with key " + childKey + "found");
            });
        }
    }


}

