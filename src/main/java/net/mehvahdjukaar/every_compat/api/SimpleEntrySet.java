package net.mehvahdjukaar.every_compat.api;

import com.mojang.datafixers.util.Pair;
import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.every_compat.configs.EarlyConfigs;
import net.mehvahdjukaar.every_compat.misc.BlockTypeBasedBlockItem;
import net.mehvahdjukaar.every_compat.misc.Utils;
import net.mehvahdjukaar.every_compat.modules.CompatModule;
import net.mehvahdjukaar.selene.block_set.BlockSetManager;
import net.mehvahdjukaar.selene.block_set.BlockType;
import net.mehvahdjukaar.selene.block_set.leaves.LeavesType;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.mehvahdjukaar.selene.client.asset_generators.LangBuilder;
import net.mehvahdjukaar.selene.client.asset_generators.textures.Palette;
import net.mehvahdjukaar.selene.client.asset_generators.textures.Respriter;
import net.mehvahdjukaar.selene.client.asset_generators.textures.TextureImage;
import net.mehvahdjukaar.selene.resourcepack.*;
import net.mehvahdjukaar.selene.resourcepack.resources.TagBuilder;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.metadata.animation.AnimationMetadataSection;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.commons.lang3.function.TriFunction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//contrary to popular belief this class is indeed not simple. Its usage however is
public class SimpleEntrySet<T extends BlockType, B extends Block> extends EntrySet<T, B> {

    private final Class<T> type;

    private final Pattern nameScheme;

    protected final Supplier<T> baseType;
    protected final Supplier<B> baseBlock;

    public final String postfix;
    @Nullable
    public final String prefix;

    protected final Function<T, @Nullable B> blockFactory;
    @Nullable
    protected final TriFunction<T, B, Item.Properties, @Nullable Item> itemFactory;
    @Nullable
    protected final TileHolder<?> tileHolder;

    protected final CreativeModeTab tab;
    protected final boolean copyLoot;
    protected final Map<ResourceLocation, Set<ResourceKey<?>>> tags = new HashMap<>();
    protected final Set<Supplier<ResourceLocation>> recipeLocations = new HashSet<>();
    protected final Set<Pair<ResourceLocation, @Nullable ResourceLocation>> textures = new HashSet<>();
    @Nullable
    protected final BiFunction<T, ResourceManager, Pair<List<Palette>, @Nullable AnimationMetadataSection>> paletteSupplier;
    @Nullable
    protected final Supplier<Supplier<RenderType>> renderType;
    @Nullable
    protected final Consumer<BlockTypeResTransformer<T>> extraTransform;


    public SimpleEntrySet(Class<T> type,
            String name, @Nullable String prefix,
                          Function<T, B> blockSupplier,
                          Supplier<B> baseBlock,
                          Supplier<T> baseType,
                          CreativeModeTab tab,
                          boolean copyLoot,
                          @Nullable TriFunction<T, B, Item.Properties, Item> itemFactory,
                          @Nullable TileHolder<?> tileFactory,
                          @Nullable Supplier<Supplier<RenderType>> renderType,
                          @Nullable BiFunction<T, ResourceManager, Pair<List<Palette>, @Nullable AnimationMetadataSection>> paletteSupplier,
                          Consumer<BlockTypeResTransformer<T>> extraTransform) {
        super((prefix == null ? "" : prefix + "_") + name);
        this.postfix = name;
        this.blockFactory = blockSupplier;
        this.prefix = prefix;
        this.tileHolder = tileFactory;
        this.tab = tab;
        this.copyLoot = copyLoot;
        this.baseBlock = baseBlock;
        this.baseType = baseType;
        this.itemFactory = itemFactory;
        this.type = type;

        this.extraTransform = extraTransform;
        this.renderType = renderType;
        this.paletteSupplier = paletteSupplier;

        if (this.prefix != null) {
            nameScheme = Pattern.compile("^" + prefix + "_(.+?)_" + postfix + "$");
        } else {
            nameScheme = Pattern.compile("^(.+?)_" + postfix + "$");
        }
    }

    public TileHolder<?> getTileHolder() {
        return tileHolder;
    }

    public Class<T> getTypeClass() {
        return type;
    }

    public B getBaseBlock() {
        return baseBlock.get();
    }

    public T getBaseType() {
        return baseType.get();
    }

    public String getEquivalentBlock(CompatModule module, String oldName, String woodFrom) {
        String wood = parseWoodType(oldName);
        if (wood != null) {
            var w = BlockSetManager.getBlockSet(this.getTypeClass()).get(new ResourceLocation(woodFrom, wood));
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

    public void addTranslations(CompatModule module, AfterLanguageLoadEvent lang) {
        blocks.forEach((w, v) -> LangBuilder.addDynamicEntry(lang, "block_type." + module.getModId() + "." + typeName, (BlockType) w, v));
    }

    public void registerWoodBlocks(CompatModule module, IForgeRegistry<Block> registry, Collection<WoodType> woodTypes) {
        if (WoodType.class == getTypeClass()) {
            registerBlocks(module, registry, (Collection<T>) woodTypes);
        }
    }

    public void registerLeavesBlocks(CompatModule module, IForgeRegistry<Block> registry, Collection<LeavesType> leavesTypes) {
        if (LeavesType.class == getTypeClass()) {
            registerBlocks(module, registry, (Collection<T>) leavesTypes);
        }
    }

    @Override
    public void registerBlocks(CompatModule module, IForgeRegistry<Block> registry, Collection<T> woodTypes) {
        Block base = baseBlock.get();
        if (base == null || base == Blocks.AIR)
            throw new UnsupportedOperationException("Base block cant be null");
        baseType.get().addChild(module.shortenedId() + "/" + typeName, base);

        for (T w : woodTypes) {
            String name = getBlockName(w);
            String fullName = module.shortenedId() + "/" + w.getNamespace() + "/" + name;
            if (w.isVanilla() || module.isEntryAlreadyRegistered(name, w, registry)) continue;

            B block = blockFactory.apply(w);
            //for blocks that fail
            if (block != null) {
                this.blocks.put(w, block);
                registry.register(block.setRegistryName(WoodGood.res(fullName)));
                w.addChild(module.shortenedId() + "/" + typeName, block);
            }
        }
    }

    @NotNull
    public String getBlockName(T w) {
        String name;
        if (prefix != null) {
            name = this.prefix + "_" + w.getTypeName() + "_" + this.postfix;
        } else {
            name = w.getTypeName() + "_" + this.postfix;
        }
        return name;
    }

    protected CreativeModeTab getTab(T w, B b) {
        return EarlyConfigs.isTypeEnabled(w) ?
                (WoodGood.MOD_TAB != null ? WoodGood.MOD_TAB : this.tab) : null;
    }

    @Override
    public void registerItems(CompatModule module, IForgeRegistry<Item> registry) {
        blocks.forEach((w, value) -> {
            Item i;
            CreativeModeTab tab = getTab(w, value);

            if (itemFactory != null) {
                i = itemFactory.apply(w, value, new Item.Properties().tab(tab));
            } else {
                i = new BlockTypeBasedBlockItem<>(value, new Item.Properties().tab(tab), w);
            }
            //for ones that don't have item
            if (i != null) {
                this.items.put(w, i);
                registry.register(i.setRegistryName(value.getRegistryName()));
            }
        });
    }

    @Override
    public void registerTiles(CompatModule module, IForgeRegistry<BlockEntityType<?>> registry) {
        if (tileHolder != null) {
            var tile = this.tileHolder.createInstance(blocks.values().toArray(Block[]::new));
            registry.register(tile.setRegistryName(WoodGood.res(module.shortenedId() + "_" + this.getName())));
        }
    }

    @Override
    public void registerEntityRenderers(CompatModule simpleModule, EntityRenderersEvent.RegisterRenderers event) {
        if (this.tileHolder != null) {
            this.tileHolder.registerRenderer(event);
        }
    }

    @Override
    public void setRenderLayer() {
        if (renderType != null) {
            blocks.values().forEach(t -> ItemBlockRenderTypes.setRenderLayer(t, renderType.get().get()));
        }
    }

    @Override
    public void generateTags(CompatModule module, DynamicDataPack pack, ResourceManager manager) {
        if (!tags.isEmpty()) {
            for (var tb : tags.entrySet()) {
                TagBuilder builder = TagBuilder.of(tb.getKey()).addEntries(blocks.values());
                for (var t : tb.getValue()) {
                    pack.addTag(builder, t);
                }
            }
        }
    }

    @Override
    public void generateLootTables(CompatModule module, DynamicDataPack pack, ResourceManager manager) {
        if (copyLoot) {
            ResourceLocation reg = baseBlock.get().getRegistryName();
            Utils.addBlockResources(module.getModId(), manager, pack, blocks, baseType.get().getTypeName(),
                    ResType.BLOCK_LOOT_TABLES.getPath(reg));

        } else {
            //drop self
            blocks.forEach((wood, value) -> pack.addSimpleBlockLootTable(value));
        }
    }

    @Override
    public void generateRecipes(CompatModule module, DynamicDataPack pack, ResourceManager manager) {
        this.recipeLocations.forEach(r -> {
            var res = r.get();
            try {
                Utils.addBlocksRecipes(manager, pack, items,res , baseType.get());
            } catch (Exception e) {
                WoodGood.LOGGER.error("Failed to generate recipes for template at location {} ", res);
            }
        });

    }

    @Override
    public void generateModels(CompatModule module, DynamicTexturePack pack, ResourceManager manager) {
        Utils.addStandardResources(module.getModId(), manager, pack, blocks, baseType.get(), extraTransform);
    }

    @Override
    public void generateTextures(CompatModule module, RPAwareDynamicTextureProvider handler, ResourceManager manager) {
        if (textures.isEmpty()) return;
        boolean isWood = this.getTypeClass() == WoodType.class;
        if (paletteSupplier == null && !isWood) {
            throw new UnsupportedOperationException("You need to provide a palette supplier for non wood type based blocks");
        }
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
                    WoodGood.LOGGER.error("Failed to read block texture at: {}", p, e);
                }
            }

            for (var entry : blocks.entrySet()) {
                B b = entry.getValue();
                T w = entry.getKey();
                //skips disabled ones

                if (!EarlyConfigs.isTypeEnabled(w)) continue;

                ResourceLocation blockId = b.getRegistryName();

                List<Palette> targetPalette = null;
                AnimationMetadataSection animation = null;
                if (paletteSupplier != null) {
                    var pal = paletteSupplier.apply(w, manager);
                    animation = pal.getSecond();
                    targetPalette = pal.getFirst();
                } else {
                    try (TextureImage plankTexture = TextureImage.open(manager,
                            RPUtils.findFirstBlockTextureLocation(manager, ((WoodType) w).planks))) {
                        targetPalette = Palette.fromAnimatedImage(plankTexture);
                        animation = plankTexture.getMetadata();
                    } catch (Exception ignored) {
                    }
                }
                if (targetPalette == null) {
                    WoodGood.LOGGER.error("Could not get texture palette for block {} : ", b);
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

                    String newId = BlockTypeResTransformer.replaceTypeNoNamespace(oldPath, w, blockId, baseType.get().getTypeName());

                    Respriter respriter = re.getValue();
                    if (isWood) {
                        module.addWoodTexture((WoodType) w, handler, manager, newId, () ->
                                respriter.recolorWithAnimation(finalTargetPalette, finalAnimation));

                    } else {
                        handler.addTextureIfNotPresent(manager, newId, () ->
                                respriter.recolorWithAnimation(finalTargetPalette, finalAnimation));

                    }
                }
            }

        } catch (Exception e) {
            WoodGood.LOGGER.error("Could not generate any block texture for entry set {} : ", module.modRes(this.getName()), e);
        } finally {
            for (var t : images) {
                t.close();
            }
        }

    }

    //ok...
    public static <T extends BlockType, B extends Block> Builder<T, B> builder(Class<T> type,
            String name, Supplier<B> baseBlock, Supplier<T> baseType, Function<T, B> blockSupplier) {

        return new Builder<>(type, name, null, baseType, baseBlock, blockSupplier);
    }

    public static <T extends BlockType, B extends Block> Builder<T, B> builder(Class<T> type,
            String name, String prefix, Supplier<B> baseBlock, Supplier<T> baseType, Function<T, B> blockSupplier) {

        return new Builder<>(type,name, prefix, baseType, baseBlock, blockSupplier);
    }

    public static class Builder<T extends BlockType, B extends Block> {
        protected final Class<T> type;
        protected final Supplier<T> baseType;
        protected final Supplier<B> baseBlock;
        protected final String name;
        @Nullable
        protected final String prefix;
        protected CreativeModeTab tab = CreativeModeTab.TAB_DECORATIONS;
        protected boolean copyLoot = false;
        protected final Function<T, B> blockFactory;
        @Nullable
        protected TriFunction<T, B, Item.Properties, Item> itemFactory;
        @Nullable
        protected TileHolder<?> tileFactory;
        @Nullable
        protected Supplier<Supplier<RenderType>> renderType = null;
        @Nullable
        protected BiFunction<T, ResourceManager, Pair<List<Palette>, @Nullable AnimationMetadataSection>> palette = null;
        protected final Map<ResourceLocation, Set<ResourceKey<?>>> tags = new HashMap<>();
        protected final Set<Supplier<ResourceLocation>> recipes = new HashSet<>();
        protected final Set<Pair<ResourceLocation, @Nullable ResourceLocation>> textures = new HashSet<>();
        protected Consumer<BlockTypeResTransformer<T>> extraModelTransform = null;

        protected Builder(Class<T> type, String name, @Nullable String prefix, Supplier<T> baseType, Supplier<B> baseBlock, Function<T, B> blockFactory) {
            this.baseType = baseType;
            this.baseBlock = baseBlock;
            this.name = name;
            this.prefix = prefix;
            this.blockFactory = blockFactory;
            this.type = type;
        }

        public SimpleEntrySet<T, B> build() {
            var e = new SimpleEntrySet<>( type, name, prefix, blockFactory, baseBlock, baseType, tab, copyLoot,
                    itemFactory, tileFactory, renderType, palette, extraModelTransform);
            e.recipeLocations.addAll(this.recipes);
            e.tags.putAll(this.tags);
            e.textures.addAll(textures);
            return e;
        }

        public <H extends BlockEntity> Builder<T, B> addTile(BlockEntityType.BlockEntitySupplier<H> tileFactory) {
            this.tileFactory = new TileHolder<>(tileFactory);
            return this;
        }

        //currently, it seems not to be server safe. Register it in onClientInit instead
        public <H extends BlockEntity> Builder<T, B> addTile(BlockEntityType.BlockEntitySupplier<H> tileFactory,
                                                             Supplier<BlockEntityRendererProvider<H>> renderer) {
            if (FMLEnvironment.dist == Dist.CLIENT) {
                this.tileFactory = new TileHolder<>(tileFactory, renderer);
            } else {
                this.tileFactory = new TileHolder<>(tileFactory);
            }
            return this;
        }

        public Builder<T, B> addCustomItem(TriFunction<T, B, Item.Properties, Item> itemFactory) {
            this.itemFactory = itemFactory;
            return this;
        }

        //adds an extra model transform
        public Builder<T, B> addModelTransform(Consumer<BlockTypeResTransformer<T>> transform) {
            this.extraModelTransform = transform;
            return this;
        }

        public Builder<T, B> noItem() {
            this.itemFactory = (a, b, c) -> null;
            return this;
        }

        public Builder<T, B> setTab(CreativeModeTab tab) {
            this.tab = tab;
            return this;
        }

        /**
         * As opposed to just dropping itself
         */
        public Builder<T, B> useLootFromBase() {
            this.copyLoot = true;
            return this;
        }

        public Builder<T, B> setRenderType(Supplier<Supplier<RenderType>> renderType) {
            this.renderType = renderType;
            return this;
        }

        public Builder<T, B> addTag(ResourceLocation location, ResourceKey<?> registry) {
            var s = this.tags.computeIfAbsent(location, b -> new HashSet<>());
            s.add(registry);
            return this;
        }

        public Builder<T, B> addTag(TagKey<?> tag, ResourceKey<?> registry) {
            addTag(tag.location(), registry);
            return this;
        }

        public Builder<T, B> defaultRecipe() {
            this.recipes.add(() -> this.baseBlock.get().getRegistryName());
            return this;
        }

        public Builder<T, B> addRecipe(ResourceLocation resourceLocation) {
            this.recipes.add(() -> resourceLocation);
            return this;
        }

        public Builder<T, B> addTexture(ResourceLocation resourceLocation) {
            this.textures.add(Pair.of(resourceLocation, null));
            return this;
        }

        public Builder<T, B> addTextureM(ResourceLocation textureLocation, ResourceLocation maskLocation) {
            this.textures.add(Pair.of(textureLocation, maskLocation));
            return this;
        }

        //by default, they all use planks palette
        public Builder<T, B> setPalette(BiFunction<T, ResourceManager, Pair<List<Palette>, @Nullable AnimationMetadataSection>> paletteProvider) {
            this.palette = paletteProvider;
            return this;
        }

        //only works for oak type. Will fail if its used on leaves
        public Builder<T, B> createPaletteFromOak(Consumer<Palette> paletteTransform) {
            return this.setPalette((w, m) -> {
                try (TextureImage plankTexture = TextureImage.open(m,
                        RPUtils.findFirstBlockTextureLocation(m, ((WoodType) w).planks))) {

                    List<Palette> targetPalette = Palette.fromAnimatedImage(plankTexture);
                    targetPalette.forEach(paletteTransform);
                    return Pair.of(targetPalette, plankTexture.getMetadata());
                } catch (Exception e) {
                    throw new RuntimeException(String.format("Failed to generate palette for %s : %s", w, e));
                }
            });
        }


    }


    public static class TileHolder<H extends BlockEntity> {

        protected final BlockEntityType.BlockEntitySupplier<H> tileFactory;
        protected Supplier<BlockEntityRendererProvider<H>> renderer = null;
        public BlockEntityType<? extends H> tile = null;


        @OnlyIn(Dist.CLIENT)
        public TileHolder(BlockEntityType.BlockEntitySupplier<H> tileFactory,
                          Supplier<BlockEntityRendererProvider<H>> renderer) {
            this.tileFactory = tileFactory;
            this.renderer = renderer;
        }

        public TileHolder(BlockEntityType.BlockEntitySupplier<H> tileFactory) {
            this.tileFactory = tileFactory;
        }

        public BlockEntityType<? extends H> get() {
            return tile;
        }

        public BlockEntityType<? extends H> createInstance(Block... blocks) {
            if (tile != null) throw new UnsupportedOperationException("tile has already been created");
            this.tile = BlockEntityType.Builder.of(tileFactory, blocks).build(null);
            return tile;
        }

        @OnlyIn(Dist.CLIENT)
        public void registerRenderer(EntityRenderersEvent.RegisterRenderers event) {
            if (this.renderer != null) {
                event.registerBlockEntityRenderer(tile, this.renderer.get());
            }
        }
    }

}
