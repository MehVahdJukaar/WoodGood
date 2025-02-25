package net.mehvahdjukaar.every_compat.api;

import com.google.common.base.Suppliers;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.configs.ModEntriesConfigs;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.misc.ColoringUtils;
import net.mehvahdjukaar.every_compat.misc.ResourcesUtils;
import net.mehvahdjukaar.every_compat.misc.SpriteHelper;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.mehvahdjukaar.moonlight.api.resources.BlockTypeResTransformer;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.SimpleTagBuilder;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynClientResourcesGenerator;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynamicDataPack;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.resources.textures.Respriter;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage;
import net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.moonlight.api.util.math.colors.RGBColor;
import net.mehvahdjukaar.moonlight.core.misc.McMetaFile;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.InputStream;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//contrary to popular belief this class is indeed not simple. Its usage however is
@SuppressWarnings({"unused", "removal"})
public abstract class AbstractSimpleEntrySet<T extends BlockType, B extends Block, I extends Item> implements EntrySet<T> {

    protected static final ResourceLocation NO_TAB_MARKER = new ResourceLocation("none");


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
    protected final BiFunction<T, ResourceManager, Pair<List<Palette>, @Nullable McMetaFile>> paletteSupplier;
    @Nullable
    protected final Consumer<BlockTypeResTransformer<T>> extraModelTransform;

    protected final Predicate<T> condition;

    protected final boolean copyTint;

    protected AbstractSimpleEntrySet(Class<T> type,
                                     String name, @Nullable String prefix,
                                     Supplier<T> baseType,
                                     Supplier<ResourceKey<CreativeModeTab>> tab,
                                     TabAddMode tabMode,
                                     BiFunction<T, ResourceManager, Pair<List<Palette>, @Nullable McMetaFile>> paletteSupplier,
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

        this.extraModelTransform = extraTransform;
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

    @Override
    public int getBlockCount() {
        return this.blocks.size();
    }

    @Override
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
        if (copyTint) {
            ColoringUtils.copyBlockTint(event, blocks);
            ColoringUtils.copyItemTint(event, items);
        }
    }

    @Override
    public void registerItemsToExistingTabs(SimpleModule module, RegHelper.ItemToTabEvent event) {
        if (tab == null) {
            if (PlatHelper.isDev()) {
                throw new UnsupportedOperationException("Creative tab cant be null. Found null one for entry set " + this.getName());
            }
            return;
        }
        ResourceKey<CreativeModeTab> tab = this.tab.get();
        if (tab.location().equals(NO_TAB_MARKER)) {
            return;
        }
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
    public void generateTags(SimpleModule module, DynamicDataPack pack, ResourceManager manager) {
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
    public void generateRecipes(SimpleModule module, DynamicDataPack pack, ResourceManager manager) {
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
    public void generateTextures(SimpleModule module, DynClientResourcesGenerator handler, ResourceManager manager) {
        if (textures.isEmpty()) return;

        List<TextureImage> images = new ArrayList<>();
        try (TextureImage oakPlanksTexture = TextureImage.open(manager,
                RPUtils.findFirstBlockTextureLocation(manager, (Block) this.getBaseType().mainChild()))) {
            Palette oakPlanksPalette = Palette.fromImage(oakPlanksTexture);

            Map<ResourceLocation, Respriter> respriters = new HashMap<>();
            Map<ResourceLocation, TextureImage> partialRespriters = new HashMap<>();
            Palette globalPalette = Palette.ofColors(new ArrayList<RGBColor>());

            Multimap<ResourceLocation, TextureInfo> infoPerTextures = ArrayListMultimap.create();

            /// Adding multiple textures from one block into Respriter without/with mask & infoPerTextures
            for (var textureInfo : textures) {
                ResourceLocation textureId = textureInfo.texture();

                try {
                    ResourceLocation maskId = textureInfo.mask();
                    TextureImage main = TextureImage.open(manager, textureId);
                    main.getMetadata();

                    infoPerTextures.put(textureId, textureInfo);

                    if (textureInfo.copyTexture()) {
                        respriters.put(maskId, Respriter.ofPalette(main, List.of(Palette.ofColors(List.of(new RGBColor(1))))));
                    } else {
                        images.add(main);

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
            /// Swapping out the old palettes of the texture with new plattes
            for (var entry : getDefaultEntries().entrySet()) {
                var b = entry.getValue();
                T w = entry.getKey();
                // skips disabled ones
                // actually we dont otherwise we get missign texture log spam. TODO: replace models with empty dummy instead
                // if (!ModConfigs.isEntryEnabled(w, b)) continue;
                ResourceLocation blockId = Utils.getID(b);

                // return the texture of: WoodType: Planks, StoneType: stone, LeavesType: leaves
                var pal = paletteSupplier.apply(w, manager);
                McMetaFile targetAnimation = pal.getSecond();
                List<Palette> targetPalette = pal.getFirst();

                if (targetPalette == null) {
                    EveryCompat.LOGGER.error("Could not get texture palette for block {} : ", b);
                    continue;
                }

                //sanity check to verity that palette isn't changed. can be removed
                int oldSize = targetPalette.get(0).size();

                /// Creating new Path to add the new textures via the resources
                for (var re : respriters.entrySet()) {
                    if (oldSize != targetPalette.get(0).size()) {
                        throw new RuntimeException("This should not happen");
                    }
                    ResourceLocation oldTextureId = re.getKey();
                    String oldPath = oldTextureId.getPath();

                    // boatload's texture path has 2 folder
                    String newPath = (oldPath.startsWith("entity/") && module.modId.equals("boatload"))
                            ? BlockTypeResTransformer.replaceFullGenericType(oldPath, w, blockId, baseType.get().getTypeName(), null, 2)
                            // Default
                            : BlockTypeResTransformer.replaceTypeNoNamespace(oldPath, w, blockId, baseType.get().getTypeName());

                    String newId = "";

                    boolean isOnAtlas = true;

                    /// Adding the textures to the resource
                    for (var info : infoPerTextures.get(oldTextureId)) {
                        if (info != null) {
                            if (info.keepNamespace()) newId = oldTextureId.withPath(newPath).toString();
                            else
                                newId = new ResourceLocation(blockId.getNamespace(), newPath).toString();

                            if (newId.isEmpty()) {
                                EveryCompat.LOGGER.error("The path of new texture is empty for: {}", info.texture());
                                continue;
                            }

                            isOnAtlas = info.onAtlas();

                            /// TEMP: do not remove this until the mcmeta problem is fixed.
                            if (info.copyMCMETA()) {
                                ResourceLocation mcmetaLoc = ResType.MCMETA.getPath(oldTextureId);
                                Optional<Resource> getMCMETA = manager.getResource(mcmetaLoc);

                                if (getMCMETA.isPresent()) {
                                    InputStream mcmetaStream = getMCMETA.get().open();
                                    JsonObject mcmetaFile = RPUtils.deserializeJson(mcmetaStream);

                                    // Adding to the resources next to newtextures
                                    handler.dynamicPack.addJson(ResourceLocation.tryParse(newId), mcmetaFile, ResType.MCMETA);
                                    mcmetaStream.close();
                                } else
                                    handler.getLogger().error("The MCMETA file may no longer existing, check @ {}", mcmetaLoc);
                            }
                        }

                        Respriter respriter = re.getValue();

                        Supplier<TextureImage> textureSupplier = () -> respriter.recolorWithAnimation(targetPalette, targetAnimation);
                        textureSupplier = postProcessTexture(w, newId, manager, textureSupplier);

                        handler.addTextureIfNotPresent(manager, newId, textureSupplier, isOnAtlas);
                    }
                }
            }

        } catch (Exception e) {
            EveryCompat.LOGGER.error("Could not generate any block texture for entry set {}: {}",
                    module == null ? "dummy" : module.modRes(this.getName()), e.getMessage());
        } finally {
            for (var t : images) {
                t.close();
            }
        }
    }

    //post process some textures.
    public Supplier<TextureImage> postProcessTexture(T blockType, String newId, ResourceManager manager,
                                                     Supplier<TextureImage> textureSupplier) {
        if (blockType.getClass() == WoodType.class) {
            var changed = SpriteHelper.maybePostProcessWoodTexture((WoodType) blockType, newId, manager, textureSupplier);
            if (changed != null) {
                return changed;
            }
        }
        return textureSupplier;
    }

    private static Pair<List<Palette>, @Nullable McMetaFile> getPaletteFromMainChild(BlockType w, ResourceManager manager) {
        var mainChild = w.mainChild();
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
            return Pair.of(targetPalette, animation);
        } catch (Exception ignored) {
        }
        return Pair.of(null, null);
    }


    @SuppressWarnings("unchecked")
    protected static class Builder<BL extends Builder<BL, T, B, I>, T extends BlockType, B extends Block, I extends Item> {
        protected final Class<T> type;
        protected final Supplier<T> baseType;
        protected final String name;
        @Nullable
        protected final String prefix;
        protected Supplier<ResourceKey<CreativeModeTab>> tab = null;
        protected TabAddMode tabMode = TabAddMode.AFTER_SAME_TYPE;
        protected BiFunction<T, ResourceManager, Pair<List<Palette>, @Nullable McMetaFile>> palette = AbstractSimpleEntrySet::getPaletteFromMainChild;
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

        //exclusive with addCondition
        public BL requiresFromMap(Map<T, ?> entrySet) {
            this.addCondition(blockType -> !Objects.isNull(entrySet.get(blockType)));
            return (BL) this;
        }

        // Exclude Leaves | Wood | Stone - exclusive with addCondition
        public BL excludeBlockTypes(String regEx) {
            this.addCondition(blockType -> !blockType.getId().toString().matches(regEx));
            return (BL) this;
        }

        // Exclude Leaves | Wood | Stone - exclusive with addCondition
        public BL excludeBlockTypes(String modId, String... typeIds) {
            StringBuilder regexBuilder = new StringBuilder();

            // create "biomesoplenty:(fir)" or "biomesoplenty:(fir|dead|...)
            regexBuilder.append(modId).append(":(");
            for (int i = 0; i < typeIds.length; i++) {
                regexBuilder.append(typeIds[i]);
                if (i != (typeIds.length - 1)) regexBuilder.append("|"); // Don't append "|" to the last word's
            }
            regexBuilder.append(")");

            this.addCondition(blockType -> !blockType.getId().toString().matches(regexBuilder.toString()));
            return (BL) this;
        }

        public BL addCondition(Predicate<T> newCondition) {
            this.condition = this.condition == null ? newCondition :
                    this.condition.and(newCondition);
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

        public BL noTab() {
            return setTabKey(NO_TAB_MARKER);
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

        @Deprecated(forRemoval = true)
        @SuppressWarnings("OptionalGetWithoutIsPresent")
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
            if (PlatHelper.getPhysicalSide().isClient()) {
                TextureInfo info = textureLoc.build();
                this.textures.add(info);
                if (info.keepNamespace()) {
                    //hack so we assure namespace has been added since it could be NOT Ec one
                    ClientDynamicResourcesHandler.getInstance().dynamicPack
                            .addNamespaces(info.texture().getNamespace());
                }
            }
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
        public BL setPalette(BiFunction<T, ResourceManager, Pair<List<Palette>, @Nullable McMetaFile>> paletteProvider) {
            this.palette = paletteProvider;
            return (BL) this;
        }

        //only works for oak type. Will fail if its used on leaves
        public BL createPaletteFromPlanks(Consumer<Palette> paletteTransform) {
            return createPaletteFromChild(paletteTransform, "planks");
        }

        public BL createPaletteFromPlanks() {
            return createPaletteFromPlanks(p -> {
            });
        }

        public BL createPaletteFromChild(Consumer<Palette> paletteTransform, String childKey) {
            return createPaletteFromChild(paletteTransform, childKey, null);
        }

        public BL createPaletteFromChild(String childKey, Predicate<String> whichSide) {
            return createPaletteFromChild(p -> {
            }, childKey, whichSide);
        }

        public BL createPaletteFromChild(String childKey) {
            return createPaletteFromChild(p -> {
            }, childKey, null);
        }

        public BL createPaletteFromChild(Consumer<Palette> paletteTransform, String childKey, Predicate<String> whichSide) {
            return this.setPalette((blockType, m) -> makePaletteFromChild(paletteTransform, childKey, whichSide, blockType, m));
        }
    }

    // utility function
    public static <T extends BlockType> @NotNull Pair<List<Palette>, @Nullable McMetaFile> makePaletteFromChild(Consumer<Palette> paletteTransform, String childKey, Predicate<String> whichSide, T blockType, ResourceManager m) {
        var child = blockType.getChild(childKey);
        if (child instanceof Block b) {
            if (whichSide != null) {
                try (TextureImage blockTexture = TextureImage.open(m,
                        RPUtils.findFirstBlockTextureLocation(m, b, whichSide))) {

                    List<Palette> targetPalette = Palette.fromAnimatedImage(blockTexture);
                    targetPalette.forEach(paletteTransform);
                    return Pair.of(targetPalette, blockTexture.getMcMeta());
                } catch (Exception e) {
                    throw new RuntimeException(String.format("Failed to generate palette for %s : %s", blockType, e));
                }
            } else { // whichSide should be defaulted to use all_texture (like planks)  -Xelbayria's assumption
                try (TextureImage plankTexture = TextureImage.open(m,
                        RPUtils.findFirstBlockTextureLocation(m, b))) {

                    List<Palette> targetPalette = Palette.fromAnimatedImage(plankTexture);
                    targetPalette.forEach(paletteTransform);
                    return Pair.of(targetPalette, plankTexture.getMcMeta());
                } catch (Exception e) {
                    throw new RuntimeException(String.format("Failed to generate palette for %s : %s", blockType, e));
                }
            }
        } else if (child instanceof Item i) {
            try (TextureImage plankTexture = TextureImage.open(m,
                    RPUtils.findFirstItemTextureLocation(m, i))) {

                List<Palette> targetPalette = Palette.fromAnimatedImage(plankTexture);
                targetPalette.forEach(paletteTransform);
                return Pair.of(targetPalette, plankTexture.getMcMeta());
            } catch (Exception e) {
                throw new RuntimeException(String.format("Failed to generate palette for %s : %s", blockType, e));
            }
        }
        throw new RuntimeException("No child with key " + childKey + " found");
    }


    @Nullable
    @Override
    //for null tab
    public Item getItemForECTab(T type) {
        if (tab == null) {
            if (PlatHelper.isDev()) {
                throw new UnsupportedOperationException("Creative tab cant be null. Found null one for entry set " + this.getName());
            }
            EveryCompat.LOGGER.error("Creative tab cant be null. Found null one for entry set {}", this.getName());
            return null;
        }
        try {
            ResourceKey<CreativeModeTab> tagKey = tab.get();
            if (tagKey.location().equals(NO_TAB_MARKER)) {
                return null;
            }
        } catch (Exception e) {
            if (PlatHelper.isDev()) throw e;
            EveryCompat.LOGGER.error("Failed to get creative tab for entry set {}", this.getName(), e);
            return null;
        }

        return EntrySet.super.getItemForECTab(type);
    }
}

