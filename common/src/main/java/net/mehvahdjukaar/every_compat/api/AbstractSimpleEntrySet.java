package net.mehvahdjukaar.every_compat.api;

import com.google.common.base.Suppliers;
import com.mojang.datafixers.util.Pair;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.configs.ModEntriesConfigs;
import net.mehvahdjukaar.every_compat.misc.ColoringUtils;
import net.mehvahdjukaar.every_compat.misc.ResourcesUtils;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
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
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
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
    protected final TabAddMode tabMode;
    protected final Map<ResourceLocation, Set<ResourceKey<?>>> tags = new HashMap<>();
    protected final Set<Supplier<ResourceLocation>> recipeLocations = new HashSet<>();
    protected final Set<TextureInfo> textures = new HashSet<>();
    @Nullable
    protected final BiFunction<T, ResourceManager, Pair<List<Palette>, @Nullable AnimationMetadataSection>> paletteSupplier;
    @Nullable
    protected final Consumer<BlockTypeResTransformer<T>> extraTransform;

    protected final Predicate<T> condition;

    protected final boolean copyTint;

    protected AbstractSimpleEntrySet(Class<T> type,
                                     String name, @Nullable String prefix,
                                     Supplier<T> baseType,
                                     Supplier<ResourceKey<CreativeModeTab>> tab,
                                     TabAddMode tabMode,
                                     @Nullable BiFunction<T, ResourceManager, Pair<List<Palette>, @Nullable AnimationMetadataSection>> paletteSupplier,
                                     @Nullable Consumer<BlockTypeResTransformer<T>> extraTransform,
                                     boolean mergePalette, boolean copyTint,
                                     Predicate<T> condition) {
        this.typeName = (prefix == null ? "" : prefix + (name.isEmpty() ? "" : "_")) + name;
        this.postfix = name;
        this.prefix = prefix;
        this.tab = tab;
        this.tabMode = tabMode;
        this.baseType = baseType;
        this.type = type;
        this.copyTint = copyTint;

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

        if (tab == null && PlatHelper.isDev()) {
            throw new UnsupportedOperationException("Creative tab cant be null. Found null one for entry set " + this.getName());
        }
    }

    public String getName() {
        return typeName;
    }

    @Override
    public @Nullable Item getItemOf(T type) {
        var i = items.get(type);
        if (ModEntriesConfigs.isEntryEnabled(type, i)) return i;
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
    public void registerBlockColors(ClientHelper.BlockColorEvent event) {
        if (copyTint) ColoringUtils.copyBlockTint(event, blocks);
    }

    @Override
    public void registerItemColors(ClientHelper.ItemColorEvent event) {
        if (copyTint) ColoringUtils.copyBlockTint(event, blocks);
    }

    @Override
    public void registerItemsToExistingTabs(CompatModule module, RegHelper.ItemToTabEvent event) {
        if (tab == null) {
            if (PlatHelper.isDev()) {
                throw new UnsupportedOperationException("Creative tab cant be null. Found null one for entry set " + this.getName());
            }
            return;
        }
        ResourceKey<CreativeModeTab> tab = this.tab.get();
        //verify tab
        if (!BuiltInRegistries.CREATIVE_MODE_TAB.containsKey(tab)) {
            throw new UnsupportedOperationException("Creative tab " + tab + " not registered found in the registries. " +
                    "This means that the target mod must have changed its name. " +
                    "You can either downgrade the mod" + tab.location().getNamespace() + " or wait for an Every Compat update");
        }
        if (tabMode == TabAddMode.AFTER_ALL) {
            event.add(tab, items.values().toArray(new Item[0]));
        } else if (tabMode == TabAddMode.AFTER_SAME_WOOD) {
            var reg = BlockSetAPI.getBlockSet(type);
            for (var e : items.entrySet()) {
                var item = e.getValue();
                var wood = e.getKey();
                //adds after first wooden block it finds. quite bad tbh
                event.addAfter(tab, s -> reg.getBlockTypeOf(s.getItem()) == wood, item);
            }
        } else if (tabMode == TabAddMode.AFTER_SAME_TYPE) {
            var reg = BlockSetAPI.getBlockSet(type);
            String childKey = getChildKey(module);
            Class<T> typeClass = this.getTypeClass();
            for (var e : items.entrySet()) {
                var item = e.getValue();
                event.addAfter(tab, s -> {
                    T type = reg.getBlockTypeOf(s.getItem());
                    if (type == null) return false;
                    return type.getClass() == typeClass
                            && Objects.equals(type.getChildKey(s.getItem()), childKey);
                }, item);
            }
        }
    }

    @Override
    public void generateTags(CompatModule module, DynamicDataPack pack, ResourceManager manager) {
        if (!tags.isEmpty()) {
            for (var tb : tags.entrySet()) {
                SimpleTagBuilder builder = SimpleTagBuilder.of(tb.getKey());
                for (var b : getDefaultEntries().entrySet()) {
                    if (ModEntriesConfigs.isEntryEnabled(b.getKey(), b.getValue())) {
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
                EveryCompat.LOGGER.error("Failed to generate recipes for template at location {} ", res, e);
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

            Set<ResourceLocation> keepNamespace = new HashSet<>();

            for (var textureLoc : textures) {
                ResourceLocation textureId = textureLoc.texture();

                try {
                    ResourceLocation maskId = textureLoc.mask();
                    TextureImage main = TextureImage.open(manager, textureId);
                    if (textureLoc.keepNamespace()) keepNamespace.add(textureId);
                    if (textureLoc.copyTexture()) {
                        respriters.put(maskId, Respriter.ofPalette(main, List.of(Palette.ofColors(List.of(new RGBColor(1))))));
                    } else {
                        images.add(main);

                        if (maskId != null) {
                            TextureImage mask;
                            if (textureLoc.autoMask()) {
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
                    EveryCompat.LOGGER.error("Could not generate textures for {}", textureLoc, e);
                } catch (Exception e) {
                    if (PlatHelper.isDev()) throw new RuntimeException(e);
                    EveryCompat.LOGGER.error("Failed to read block texture at {}", textureLoc, e);
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
                    ResourceLocation oldTextureId = re.getKey();
                    String oldPath = oldTextureId.getPath();

                    String newId = BlockTypeResTransformer.replaceTypeNoNamespace(oldPath, w,
                            blockId, baseType.get().getTypeName());
                    //hack
                    if (keepNamespace.contains(oldTextureId)) {
                        newId = oldTextureId.withPath(newId).toString();
                    }

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
                               String relativePath, Supplier<TextureImage> textureSupplier, boolean isOnAtlas) {
        handler.addTextureIfNotPresent(manager, relativePath, () -> {
            var t = textureSupplier.get();
            maybeFlowerAzalea(t, manager, wood);
            return t;
        }, isOnAtlas);

        handler.addTextureIfNotPresent(manager, relativePath, () -> {
            var t = textureSupplier.get();
            maybeBrimwood(t, manager, relativePath, wood);
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

    //for Regions-Unexplored's brimwood
    protected void maybeBrimwood(TextureImage image, ResourceManager manager, String path, WoodType woodType) {
        if (woodType.getId().toString().equals("regions_unexplored:brimwood")) {
            WoodType brimwood = WoodTypeRegistry.getValue(new ResourceLocation("regions_unexplored:brimwood"));
            if (brimwood != null) {
                try (TextureImage lavaOverlay = TextureImage.open(manager,
                        EveryCompat.res("block/regions_unexplored/brimwood_planks_lava"));
                     TextureImage plankTexture = TextureImage.open(manager,
                             EveryCompat.res("block/regions_unexplored/brimwood_planks"));

                ) {
                    String type = path.substring(path.lastIndexOf("brimwood_") + 9);

                    Respriter respriter = switch (type) {
                        case "barrel_side" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_barrel_side_m")
                        ));
                        case "barrel_top" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_barrel_top_m")
                        ));
                        case "beehive_front_honey" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_beehive_front_honey_m")
                        ));
                        case "beehive_side" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_beehive_side_m")
                        ));
                        case "bookshelf" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_bookshelf_m")
                        ));
                        case "cartography_table_side1" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_cartography_table_side1_m")
                        ));
                        case "cartography_table_side2" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_cartography_table_side2_m")
                        ));
                        case "cartography_table_top" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_cartography_table_top_m")
                        ));
                        case "chiseled_bookshelf_occupied" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_chiseled_bookshelf_occupied_m")
                        ));
                        case "crafting_table_front" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_crafting_table_front_m")
                        ));
                        case "crafting_table_side" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_crafting_table_side_m")
                        ));
                        case "fletching_table_front" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_fletching_table_front_m")
                        ));
                        case "fletching_table_side" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_fletching_table_side_m")
                        ));
                        case "fletching_table_top" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_fletching_table_top_m")
                        ));
                        case "lectern_base" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_lectern_base_m")
                        ));
                        case "lectern_front" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_lectern_front_m")
                        ));
                        case "smithing_table_bottom" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_smithing_table_bottom_m")
                        ));
                        case "smithing_table_front" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_smithing_table_front_m")
                        ));
                        case "smithing_table_side" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_smithing_table_side_m")
                        ));
                        case "smoker_bottom" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_smoker_bottom_m")
                        ));
                        case "smoker_front" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_smoker_front_m")
                        ));
                        case "smoker_side" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_smoker_side_m")
                        ));
                        default -> Respriter.of(image);
                    };

                    var temp = respriter.recolorWithAnimationOf(plankTexture);

                    if (path.contains("stairs") || path.contains("planks") || path.contains("slab") ||
                            path.contains("beehive") || path.contains("composter_bottom") || path.contains("composter_side")
                            || path.contains("lectern_side") || path.contains("lectern_top") || path.contains("bookshelf_side")
                            || path.contains("bookshelf_top")
                    )
                        image.applyOverlayOnExisting(temp, lavaOverlay);
                    else
                        image.applyOverlayOnExisting(temp);

                    temp.close();


                } catch (Exception e) {
                    EveryCompat.LOGGER.error("failed to open the texture for: {1}", e);
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
        protected Supplier<ResourceKey<CreativeModeTab>> tab = null;
        protected TabAddMode tabMode = TabAddMode.AFTER_SAME_TYPE;
        @Nullable
        protected BiFunction<T, ResourceManager, Pair<List<Palette>, @Nullable AnimationMetadataSection>> palette = null;
        protected final Map<ResourceLocation, Set<ResourceKey<?>>> tags = new HashMap<>();
        protected final Set<Supplier<ResourceLocation>> recipes = new HashSet<>();
        protected final Set<TextureInfo> textures = new HashSet<>();
        protected boolean useMergedPalette;
        @Nullable
        protected Consumer<BlockTypeResTransformer<T>> extraModelTransform = null;
        protected Predicate<T> condition = w -> true;
        protected boolean copyTint = false;

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

        public BL copyParentTint() {
            this.copyTint = true;
            return (BL) this;
        }

        public BL setTabMode(TabAddMode mode) {
            this.tabMode = mode;
            return (BL) this;
        }

        public BL setTabKey(ResourceLocation res) {
            var key = ResourceKey.create(Registries.CREATIVE_MODE_TAB, res);
            this.tab = () -> key;
            return (BL) this;
        }

        @Deprecated(forRemoval = true)
        public BL setTabKey(Supplier<ResourceKey<CreativeModeTab>> tab) {
            this.tab = tab;
            return (BL) this;
        }

        public BL setTabKey(ResourceKey<CreativeModeTab> key) {
            this.tab = () -> key;
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

        public BL addTexture(TextureInfo.Builder textureLoc) {
            this.textures.add(textureLoc.build());
            return (BL) this;
        }

        public BL addTexture(ResourceLocation resourceLocation) {
            return addTexture(TextureInfo.of(resourceLocation));
        }

        public BL addTextureM(ResourceLocation textureLocation, ResourceLocation maskLocation) {
            return addTexture(TextureInfo.of(textureLocation)
                    .mask(maskLocation));
        }

        // adds a texture with automatic masking. Experimental
        public BL addTextureAutoM(ResourceLocation textureLocation) {
            return addTexture(TextureInfo.of(textureLocation)
                    .autoMask());
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

