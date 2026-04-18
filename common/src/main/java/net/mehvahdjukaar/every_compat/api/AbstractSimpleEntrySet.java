package net.mehvahdjukaar.every_compat.api;

import com.google.common.base.Suppliers;
import com.mojang.datafixers.util.Pair;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.configs.ModEntriesConfigs;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.misc.ColoringUtils;
import net.mehvahdjukaar.every_compat.misc.ResourcesUtils;
import net.mehvahdjukaar.every_compat.misc.TextureGenHelper;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.mehvahdjukaar.moonlight.api.resources.BlockTypeResTransformer;
import net.mehvahdjukaar.moonlight.api.resources.SimpleTagBuilder;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceSink;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.moonlight.core.misc.McMetaFile;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.mehvahdjukaar.every_compat.configs.ECConfigs.NO_MOD_CREATIVE_TAB;
import static net.mehvahdjukaar.every_compat.misc.UtilityTag.addTagToAllBlocks;
import static net.mehvahdjukaar.every_compat.misc.UtilityTag.platformTag;

//contrary to popular belief this class is indeed not simple. Its usage however is
//@SuppressWarnings({"unused"})
public abstract class AbstractSimpleEntrySet<T extends BlockType, B extends Block, I extends Item> implements EntrySet<T> {

    protected static final ResourceLocation NO_TAB_MARKER = ResourceLocation.withDefaultNamespace("none");

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

    @Nullable
    private final Supplier<Holder<CreativeModeTab>> tab;
    protected final TabAddMode tabMode;
    protected final Map<ResourceLocation, Set<ResourceKey<?>>> tags = new HashMap<>();
    protected final Set<Supplier<ResourceLocation>> recipeLocations = new HashSet<>();
    protected final Set<TextureInfo> textures = new HashSet<>();
    @Nullable
    protected final Consumer<BlockTypeResTransformer<T>> extraModelTransform;

    protected final Predicate<T> condition;

    protected final boolean copyTint;

    protected AbstractSimpleEntrySet(Class<T> type,
                                     String name, @Nullable String prefix,
                                     Supplier<T> baseType,
                                     @Nullable Supplier<Holder<CreativeModeTab>> tab,
                                     TabAddMode tabMode,
                                     BiFunction<T, ResourceManager, PaletteStrategy.PaletteAndAnimation> paletteSupplier,
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
            var w = BlockSetAPI.getBlockSet(this.getTypeClass()).get(ResourceLocation.fromNamespaceAndPath(woodFrom, wood));
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
            return;
        }
        Holder<CreativeModeTab> tabHolder = getTab();
        if (tabHolder == null || NO_MOD_CREATIVE_TAB.get()) {
            return;
        }
        var tabKey = tabHolder.unwrapKey().get();
        if (tabMode == TabAddMode.AFTER_ALL) {
            event.add(tabKey, items.values().toArray(new Item[0]));
        } else if (tabMode == TabAddMode.AFTER_SAME_WOOD) {
            var reg = BlockSetAPI.getBlockSet(type);
            for (var e : items.entrySet()) {
                var item = e.getValue();
                var wood = e.getKey();
                //adds after first wooden block it finds. quite bad tbh
                event.addAfter(tabKey, s -> reg.getBlockTypeOf(s.getItem()) == wood, item);
            }
        } else if (tabMode == TabAddMode.AFTER_SAME_TYPE) {
            var reg = BlockSetAPI.getBlockSet(type);
            String childKey = makeChildKey(module);
            Class<T> typeClass = this.getTypeClass();
            for (var e : items.entrySet()) {
                var item = e.getValue();
                event.addAfter(tabKey, s -> {
                    T type = reg.getBlockTypeOf(s.getItem());
                    if (type == null) return false;
                    return type.getClass() == typeClass
                            && Objects.equals(type.getChildKey(s.getItem()), childKey);
                }, item);
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void generateTags(SimpleModule module, ResourceManager manager, ResourceSink sink) {
        if (!tags.isEmpty()) {
            for (var tb : tags.entrySet()) {
                SimpleTagBuilder builder = SimpleTagBuilder.of(tb.getKey());
                for (var b : getDefaultEntries().entrySet()) {
                    if (ModEntriesConfigs.isEntryEnabled(b.getKey(), b.getValue())) {
                        builder.addEntry(b.getValue());
                    }
                }
                for (var t : tb.getValue()) {
                    sink.addTag(builder, (ResourceKey<? extends Registry<?>>) t);
                }
            }
        }

        // Adding tag to a specific WoodType of all generated blocks
        /// Sully's Mod
        addTagToAllBlocks(blocks, "petrified", "sullysmod",
                BlockTags.MINEABLE_WITH_PICKAXE.location().toString(), true, false, sink);

        /// Soulful Nether
        String regEx = "\\w+_(log|planks|beehive|boards|sanded_wood|beam|parquet|trim|bookshelf|window|drawer|table|bookshelf|shelf|table|support|cabinet|board_stairs|board_slab|boards)";
        addTagToAllBlocks(blocks, "fright", "soulfulnether",
                BlockTags.SOUL_FIRE_BASE_BLOCKS.location().toString(), true, false, sink, regEx);

        /// Regions Unexplored
        addTagToAllBlocks(blocks, "(brimwood|cobalt|dead|yellow_bioshroom)", "regions_unexplored",
                BlockTags.STRIDER_WARM_BLOCKS.location().toString(),
                true, false, sink);
        addTagToAllBlocks(blocks, "(brimwood|cobalt|dead|yellow_bioshroom)", "regions_unexplored",
                "minecraft:non_flammable_wood",
                false, true, sink);

        /// Chests
        addTagToAllBlocks(blocks, "", "",
                platformTag("chests/wooden").toString(),
                true, true, sink, "^(?!trapped_)\\w+(?<!_trapped)_chest$");

        addTagToAllBlocks(blocks, "", "",
                platformTag("chests").toString(),
                true, true, sink, "^(?!trapped_)\\w+(?<!_trapped)_chest$");

        addTagToAllBlocks(blocks, "", "create", "chest_mounted_storage",
                true, false, sink, "^(?!trapped_)\\w+(?<!_trapped)_chest$");

        /// Trapped_Chests
        addTagToAllBlocks(blocks, "", "",
                platformTag("chests/wooden").toString(),
                true, true, sink, "^(?:\\w+)?trapped(?:\\w+)?_chest$");

        addTagToAllBlocks(blocks, "", "",
                platformTag("chests/trapped").toString(),
                true, true, sink, "^(?:\\w+)?trapped(?:\\w+)?_chest$");

        addTagToAllBlocks(blocks, "", "create", "chest_mounted_storage",
                true, false, sink, "^(?:\\w+)?trapped(?:\\w+)?_chest$");

    }

    @Nullable
    public Holder<CreativeModeTab> getTab() {
        if (tab == null) {
            return null;
        }
        var t = tab.get();
        if (t == null) throw new IllegalStateException("Failed to get creative tab holder!");
        return t;
    }

    public Map<T, ItemLike> getDefaultEntries() {
        return (Map) blocks;
    }

    @Override
    public void generateRecipes(SimpleModule module, ResourceManager manager, ResourceSink sink) {
        int i = 0;
        for (var r : this.recipeLocations) {
            var res = r.get();
            try {
                ResourcesUtils.addBlocksRecipes(manager, sink, items, res, baseType.get(), i++);
            } catch (Exception e) {
                EveryCompat.LOGGER.error("Failed to generate recipes for template at location {} ", res, e);
            }
        }
    }

    @Override
    public void generateTextures(SimpleModule module, ResourceManager manager, ResourceSink sink) {
        if (textures.isEmpty()) return;
        try {
            TextureGenHelper.generateDefault(sink, manager, module.modId, textures, getBaseType(),
                    mergePalette, this.getDefaultEntries());
        } catch (Exception e) {
            EveryCompat.LOGGER.error("Could not generate any block texture for entry set {}: {}",
                    module == null ? "dummy" : module.modRes(this.getName()), e);
        }
    }


    @SuppressWarnings("unchecked")
    protected static class Builder<BL extends Builder<BL, T, B, I>, T extends BlockType, B extends Block, I extends Item> {
        protected final Class<T> type;
        protected final Supplier<T> baseType;
        protected final String name;
        @Nullable
        protected final String prefix;
        protected Supplier<Holder<CreativeModeTab>> tab = null;
        protected TabAddMode tabMode = TabAddMode.AFTER_SAME_TYPE;
        /// Will be set to false if noTab() is used
        protected boolean addToTab = true;

        protected final Map<ResourceLocation, Set<ResourceKey<?>>> tags = new HashMap<>();
        protected final Set<Supplier<ResourceLocation>> recipes = new HashSet<>();
        protected final Set<TextureInfo> textures = new HashSet<>();
        protected boolean useMergedPalette;
        @Nullable
        protected Consumer<BlockTypeResTransformer<T>> extraModelTransform = null;
        protected Predicate<T> condition = w -> true;
        protected boolean copyTint = false;

        @Deprecated(forRemoval = true)
        protected BiFunction<T, ResourceManager, PaletteStrategy.PaletteAndAnimation> palette = null;

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

        /// Default Mode: AFTER_SAME_TYPE
        public BL setTabMode(TabAddMode mode) {
            this.tabMode = mode;
            return (BL) this;
        }

        public BL noTab() {
            this.tab = null;
            this.addToTab = false;
            return (BL) this;
        }

        /// Use {@link net.mehvahdjukaar.every_compat.api.AbstractSimpleEntrySet.Builder#setTab(java.util.function.Supplier)}
        /// with EveryCompatModule#getTab(ResourceLocation)
        @Deprecated(forRemoval = true)
        public BL setTabKey(ResourceLocation res) {
            ResourceKey<CreativeModeTab> key = ResourceKey.create(Registries.CREATIVE_MODE_TAB, res);
            this.setTabKey(key);
            return (BL) this;
        }

        /// Use {@link net.mehvahdjukaar.every_compat.api.AbstractSimpleEntrySet.Builder#setTab(java.util.function.Supplier)}
        /// with EveryCompatModule#getTab(ResourceKey)
        @Deprecated(forRemoval = true)
        public BL setTabKey(Supplier<ResourceKey<CreativeModeTab>> tab) {
            this.tab = () -> BuiltInRegistries.CREATIVE_MODE_TAB.getHolderOrThrow(tab.get());
            return (BL) this;
        }

        /// Use {@link net.mehvahdjukaar.every_compat.api.AbstractSimpleEntrySet.Builder#setTab(java.util.function.Supplier)}
        /// with EveryCompatModule#getTab(ResourceKey)
        @Deprecated(forRemoval = true)
        public BL setTabKey(ResourceKey<CreativeModeTab> key) {
            this.setTabKey(() -> key);
            return (BL) this;
        }

        public BL setTab(Supplier<CreativeModeTab> tab) {
            this.tab = Suppliers.memoize(() -> BuiltInRegistries.CREATIVE_MODE_TAB.wrapAsHolder(tab.get()));
            return (BL) this;
        }

        public BL addTag(ResourceLocation tag, ResourceKey<?> registries) {
            return this.addTag(tag, new ResourceKey[]{registries});
        }

        public BL addTag(ResourceLocation location, ResourceKey<?>... registries) {
            var s = this.tags.computeIfAbsent(location, b -> new HashSet<>());
            s.addAll(List.of(registries));
            return (BL) this;
        }

        public BL addTag(TagKey<?> tag, ResourceKey<?> registries) {
            return this.addTag(tag, new ResourceKey[]{registries});
        }


        public BL addTag(TagKey<?> tag, ResourceKey<?>... registries) {
            addTag(tag.location(), registries);
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
                    ClientDynamicResourcesHandler.getInstance().getPackResources().addNamespaces(info.texture().getNamespace());
                }
            }
            return (BL) this;
        }

        public BL addTexture(ResourceLocation resourceLocation) {
            return addTexture(TextureInfo.of(resourceLocation));
        }

        public BL addTexture(ResourceLocation resourceLocation, PaletteStrategy palette) {
            return addTexture(TextureInfo.of(resourceLocation)
                    .setPalette(palette));
        }

        public BL addTextureM(ResourceLocation textureLocation, ResourceLocation maskLocation) {
            return addTexture(TextureInfo.of(textureLocation)
                    .mask(maskLocation));
        }

        public BL addTextureM(ResourceLocation textureLocation, ResourceLocation maskLocation, ResourceLocation overlayLocation) {
            return addTexture(TextureInfo.of(textureLocation)
                    .mask(maskLocation)
                    .overlay(overlayLocation));
        }

        public BL addTextureM(ResourceLocation textureLocation, ResourceLocation maskLocation, PaletteStrategy palette) {
            return addTexture(TextureInfo.of(textureLocation)
                    .mask(maskLocation)
                    .setPalette(palette));
        }

        public BL addTextureM(ResourceLocation textureLocation, ResourceLocation maskLocation, ResourceLocation overlayLocation, PaletteStrategy palette) {
            return addTexture(TextureInfo.of(textureLocation)
                    .mask(maskLocation)
                    .overlay(overlayLocation)
                    .setPalette(palette));
        }

        /// Custom Texture Path is for placing the texture in the correct ResourceLocation
        public BL addTextureC(ResourceLocation textureLocation, String customTexturePath) {
            return addTexture(TextureInfo.of(textureLocation, customTexturePath));
        }

        public BL addTextureC(ResourceLocation textureLocation, PaletteStrategy palette, String customTexturePath) {
            return addTexture(TextureInfo.of(textureLocation, customTexturePath).setPalette(palette));
        }

        public BL addTextureMC(ResourceLocation textureLocation, ResourceLocation maskLocation, PaletteStrategy palette, String customTexturePath) {
            return addTexture(TextureInfo.of(textureLocation, customTexturePath)
                    .mask(maskLocation)
                    .setPalette(palette));
        }

        /// Copy the texture as it is to EC's Resources
        public BL copyTexture(ResourceLocation textureLocation) {
            return addTexture(TextureInfo.of(textureLocation).copyTexture());
        }

        // adds a texture with automatic masking. Experimental
//        public BL addTextureAutoM(ResourceLocation textureLocation) {
//            return addTexture(TextureInfo.of(textureLocation)
//                    .autoMask());
//        }

        public BL useMergedPalette() {
            this.useMergedPalette = true;
            return (BL) this;
        }


        //by default, they all use planks palette

        /// @deprecated Use {@link PaletteStrategies} to create a new strategies instead of setPalette()
        @Deprecated(forRemoval = true)
        public BL setPalette(BiFunction<T, ResourceManager, Pair<List<Palette>, @Nullable McMetaFile>> paletteProvider) {
            this.palette = (t, m) -> {
                var old = paletteProvider.apply(t, m);
                return PaletteStrategy.PaletteAndAnimation.of(old.getFirst(), old.getSecond());
            };
            return (BL) this;
        }

        //only works for oak type. Will fail if its used on leaves

        /// @deprecated Look at javadoc: {@link Builder#createPaletteFromChild(Consumer, String, Predicate)}
        @Deprecated(forRemoval = true)
        public BL createPaletteFromPlanks(Consumer<Palette> paletteTransform) {
            return createPaletteFromChild(paletteTransform, VanillaWoodChildKeys.PLANKS);
        }

        /// @deprecated Look at javadoc: {@link Builder#createPaletteFromChild(Consumer, String, Predicate)}
        @Deprecated(forRemoval = true)
        public BL createPaletteFromPlanks() {
            return createPaletteFromPlanks(p -> {
            });
        }

        /// @deprecated Look at javadoc: {@link Builder#createPaletteFromChild(Consumer, String, Predicate)}
        @Deprecated(forRemoval = true)
        public BL createPaletteFromChild(Consumer<Palette> paletteTransform, String childKey) {
            return createPaletteFromChild(paletteTransform, childKey, null);
        }

        /// @deprecated Look at javadoc: {@link Builder#createPaletteFromChild(Consumer, String, Predicate)}
        @Deprecated(forRemoval = true)
        public BL createPaletteFromChild(String childKey, Predicate<String> whichSide) {
            return createPaletteFromChild(p -> {
            }, childKey, whichSide);
        }

        /// @deprecated Look at javadoc: {@link Builder#createPaletteFromChild(Consumer, String, Predicate)}
        @Deprecated(forRemoval = true)
        public BL createPaletteFromChild(String childKey) {
            return createPaletteFromChild(p -> {
            }, childKey, null);
        }

        /**
         * @deprecated USE .addTexture(ResourceLocation, PaletteStrategy) or .addTextureM(ResourceLocation, ResourceLocation, PaletteStrategy),
         * the last parameter is PaletteStrategy<br>
         * Take a look at {@link PaletteStrategies} & Look for the FIELD which can be used as an argument for the last
         * parameter
         **/
        @Deprecated(forRemoval = true)
        public BL createPaletteFromChild(Consumer<Palette> paletteTransform, String childKey, Predicate<String> whichSide) {
            return this.setPalette((blockType, m) -> {
                var p = PaletteStrategies.makePaletteFromChild(blockType, m, childKey, whichSide, paletteTransform);
                return Pair.of(p.palette(), p.animation());
            });
        }
    }


    @Nullable
    @Override
    //for null tab
    public Item getItemForECTab(T type) {
        try {
            Holder<CreativeModeTab> tab = getTab();
            if (tab == null) {
                return null;
            }
        } catch (Exception e) {
            if (PlatHelper.isDev()) throw e;
            EveryCompat.LOGGER.error("Failed to get creative tab for EntrySet - {} : {}", Utils.getID(this.getBaseType()).toString(), e);
            return null;
        }

        return EntrySet.super.getItemForECTab(type);
    }


    @NotNull
    protected String makeEntryName(T w) {
        String name;
        if (prefix != null) {
            name = this.prefix + "_" + w.getTypeName();
            if (!this.postfix.isEmpty()) name += "_" + this.postfix;
        } else {
            name = w.getTypeName() + "_" + this.postfix;
        }
        return name;
    }


    protected @NotNull ResourceLocation makeFullEntryID(SimpleModule module, T blockType) {
        String name = makeEntryName(blockType);
        String fullName = module.shortenedId() + "/" + blockType.getNamespace() + "/" + name;
        return module.makeMyRes(fullName);
    }

}

