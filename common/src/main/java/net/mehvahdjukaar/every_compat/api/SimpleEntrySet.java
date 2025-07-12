package net.mehvahdjukaar.every_compat.api;

import com.google.common.base.Preconditions;
import com.mojang.datafixers.util.Pair;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.mehvahdjukaar.every_compat.EveryCompatClient;
import net.mehvahdjukaar.every_compat.misc.ModelConfiguration;
import net.mehvahdjukaar.every_compat.misc.ResourcesUtils;
import net.mehvahdjukaar.moonlight.api.events.AfterLanguageLoadEvent;
import net.mehvahdjukaar.moonlight.api.item.BlockTypeBasedBlockItem;
import net.mehvahdjukaar.moonlight.api.misc.Registrator;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.resources.BlockTypeResTransformer;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.assets.LangBuilder;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceSink;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.moonlight.core.misc.McMetaFile;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.commons.lang3.function.TriFunction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.*;


//contrary to popular belief this class is indeed not simple. Its usage however is
public class SimpleEntrySet<T extends BlockType, B extends Block> extends AbstractSimpleEntrySet<T, B, Item> {

    protected final Supplier<@Nullable B> baseBlock;

    protected final Function<T, @Nullable B> blockFactory;
    @Nullable
    protected final TriFunction<T, B, Item.Properties, @Nullable Item> itemFactory;
    @Nullable
    protected final SimpleEntrySet.ITileHolder<?> tileHolder;

    protected final LootTableMode lootMode;
    @Nullable
    protected final Object renderType;

    protected ModelConfiguration modelConfiguration;


    public SimpleEntrySet(Class<T> type,
                          String name, @Nullable String prefix,
                          Function<T, B> blockSupplier,
                          Supplier<@Nullable B> baseBlock,
                          Supplier<T> baseType,
                          @NotNull Supplier<ResourceKey<CreativeModeTab>> tab,
                          TabAddMode tabMode,
                          LootTableMode lootMode,
                          @Nullable TriFunction<T, B, Item.Properties, Item> itemFactory,
                          @Nullable SimpleEntrySet.ITileHolder<?> tileFactory,
                          @Nullable Object renderType,
                          BiFunction<T, ResourceManager, Pair<List<Palette>, @Nullable McMetaFile>> paletteSupplier,
                          @Nullable Consumer<BlockTypeResTransformer<T>> extraTransform,
                          boolean mergedPalette, boolean copyTint,
                          Predicate<T> condition,
                          ModelConfiguration modelConfig
    ) {
        super(type, name, prefix, baseType, tab, tabMode, paletteSupplier, extraTransform, mergedPalette, copyTint, condition);
        this.blockFactory = blockSupplier;
        this.tileHolder = tileFactory;
        this.lootMode = lootMode;
        this.baseBlock = baseBlock;
        this.itemFactory = itemFactory;
        this.renderType = renderType;
        this.modelConfiguration = modelConfig;
    }

    @SuppressWarnings("unchecked")
    public <E extends BlockEntity> BlockEntityType<E> getTile(Class<E> tileClass) {
        Preconditions.checkNotNull(tileHolder, "Entry set has no tile entity!");
        return (BlockEntityType<E>) tileHolder.get();
    }

    public BlockEntityType<?> getTile() {
        Preconditions.checkNotNull(tileHolder, "Entry set has no tile entity!");
        return tileHolder.get();
    }

    public B getBaseBlock() {
        return baseBlock.get();
    }

    public void addTranslations(SimpleModule module, AfterLanguageLoadEvent lang) {
        blocks.forEach((w, v) -> LangBuilder.addDynamicEntry(lang, "block_type." + module.getModId() + "." + typeName, w, v));
    }

    @Override
    public void registerBlocks(SimpleModule module, Registrator<Block> registry, Collection<T> types) {
        Block base = getBaseBlock();
        if (base == null || base == Blocks.AIR)
            //?? wtf im using disabled to allow for null??
            throw new UnsupportedOperationException("Base block cant be null (" + this.typeName + " for " + module.modId + " module)");

        String childKey = getChildKey(module);
        if (childKey.contains("minecraft")) childKey = childKey.replace("minecraft:", "");
        for (T w : types) {
            String name = getBlockName(w);
            String fullName = module.shortenedId() + "/" + w.getNamespace() + "/" + name;
            if (module.isEntryAlreadyRegistered(name, w, BuiltInRegistries.BLOCK)) continue;

            if (condition.test(w)) {
                B block = blockFactory.apply(w);
                //for blocks that fail
                if (block != null) {
                    this.blocks.put(w, block);

                    registry.register(module.makeMyRes(fullName), block);
                    w.addChild(childKey, block);

                    if (lootMode == LootTableMode.DROP_SELF && YEET_JSONS) {
                        SIMPLE_DROPS.add(block);
                    }
                    totalChildren++;
                }
            }
        }

        //attempts adding all other children

        try {
            baseType.get().addChild(childKey, base);
        } catch (Exception ignored) {
        }

        Set<String> alreadySupportedMods = new HashSet<>(module.getAlreadySupportedMods());
        alreadySupportedMods.add(module.modId);
        var possibleNamespaces = alreadySupportedMods.toArray(String[]::new);
        for (var w : Objects.requireNonNull(BlockSetAPI.getTypeRegistry(this.getTypeClass())).getValues()) {
            if (!items.containsKey(w) && w.getChild(childKey) == null) {
                String path = getBlockName(w);
                Block block = getOptionalBlock(path, w.getNamespace());
                if (block == null) block = getOptionalBlock(path, possibleNamespaces);
                if (block != null && w.getChildKey(block) == null) {
                    try {
                        w.addChild(childKey, block);
                    } catch (Exception ignored) {
                    }
                }
            }
        }
    }

    @Nullable
    private static Block getOptionalBlock(String path, String... namespaces) {
        ResourceLocation id;
        for (var n : namespaces) {
            id = new ResourceLocation(n, path);
            var i = BuiltInRegistries.BLOCK.getOptional(id);
            if (i.isPresent()) {
                return i.get();
            }
        }
        return null;
    }

    @NotNull
    public String getBlockName(T w) {
        String name;
        if (prefix != null) {
            name = this.prefix + "_" + w.getTypeName();
            if (!this.postfix.isEmpty()) name += "_" + this.postfix;
        } else {
            name = w.getTypeName() + "_" + this.postfix;
        }
        return name;
    }

    @Override
    public void registerItems(SimpleModule module, Registrator<Item> registry) {
        blocks.forEach((w, value) -> {
            Item i;

            if (itemFactory != null) {
                i = itemFactory.apply(w, value, new Item.Properties());
            } else {
                i = new BlockTypeBasedBlockItem<>(value, new Item.Properties(), w);
            }
            //for ones that don't have item
            if (i != null) {
                this.items.put(w, i);
                registry.register(Utils.getID(value), i);
            }
        });
    }

    @Override
    public void registerTiles(SimpleModule module, Registrator<BlockEntityType<?>> registry) {
        if (tileHolder instanceof NewTileHolder<?> nt) {
            var tile = nt.createInstance(blocks.values().toArray(Block[]::new));
            registry.register(module.makeMyRes(module.shortenedId() + "_" + this.getName()), tile);
        }
    }


    @Override
    public void setupExistingTiles() {
        if (tileHolder instanceof ExistingTileHolder<?> et) {
            SimpleModule.appendTileEntityBlocks(et.get(), blocks.values());
        }
    }

    @Override
    public void setRenderLayer() {
        for (var e : blocks.entrySet()) {
            var w = e.getKey();
            var v = e.getValue();
            if (renderType != null || w.toString().equals("rats:pirat"))
                EveryCompatClient.registerRenderType(v, w, renderType);
        }
    }

    @Override
    public void generateLootTables(SimpleModule module, ResourceManager manager, ResourceSink sink) {
        if (lootMode == LootTableMode.COPY_FROM_PARENT) {
            ResourceLocation reg = Utils.getID(getBaseBlock());
            ResourcesUtils.addBlockResources(manager, sink, blocks,
                    makeLootTableTransformer(module, manager),
                    ResType.BLOCK_LOOT_TABLES.getPath(reg));

        } else if (lootMode == LootTableMode.DROP_SELF) {
            //drop self
            if (!YEET_JSONS) {
                blocks.forEach((wood, value) -> sink.addSimpleBlockLootTable(value));
            }
        }
    }

    @Override
    public void generateModels(SimpleModule module, ResourceManager manager, ResourceSink handler) {
        ResourcesUtils.generateStandardBlockModels(manager, handler, blocks, baseType.get(),
                makeModelTransformer(module, manager), makeBlockStateTransformer(module, manager), this.modelConfiguration
        );
        ResourcesUtils.generateStandardItemModels(manager, handler, items, baseType.get(),
                makeModelTransformer(module, manager), this.modelConfiguration
        );
    }

    // items and blocks
    protected BlockTypeResTransformer<T> makeModelTransformer(SimpleModule module, ResourceManager manager) {
        BlockTypeResTransformer<T> modelTransformer = BlockTypeResTransformer.create(module.modId, manager);
        if (extraModelTransform != null) extraModelTransform.accept(modelTransformer);

        ResourcesUtils.addBuiltinModelTransformer(modelTransformer, baseType.get());

        return modelTransformer;
    }

    protected BlockTypeResTransformer<T> makeBlockStateTransformer(SimpleModule module, ResourceManager manager) {
        String baseBlockName = baseType.get().getTypeName();
        return BlockTypeResTransformer.<T>create(module.modId, manager)
                .replaceWithTextureFromChild("minecraft:block/" + baseBlockName + "_planks", "planks")
                .replaceBlockType(baseBlockName)
                .IDReplaceType(baseBlockName);
    }

    protected BlockTypeResTransformer<T> makeLootTableTransformer(SimpleModule module, ResourceManager manager) {
        String oldTypeName = baseType.get().getTypeName();
        return BlockTypeResTransformer.<T>create(module.modId, manager)
                // Modifying the JSON filenames & path
                .setIDModifier((text, blockId, type) ->
                        BlockTypeResTransformer.replaceFullGenericType(text, type, blockId, oldTypeName, null, 2))
                // Modifying the JSON files' content
                .addModifier((text, blockId, type) ->
                        ResourcesUtils.convertItemIDinText(text, baseType.get(), type));
    }

    //ok...
    public static <T extends BlockType, B extends Block> Builder<T, B> builder(Class<T> type,
                                                                               String name, Supplier<B> baseBlock, Supplier<T> baseType, Function<T, B> blockSupplier) {

        return new Builder<>(type, name, null, baseType, baseBlock, blockSupplier);
    }

    public static <T extends BlockType, B extends Block> Builder<T, B> builder(Class<T> type,
                                                                               String name, String prefix, Supplier<B> baseBlock, Supplier<T> baseType, Function<T, B> blockSupplier) {

        return new Builder<>(type, name, prefix, baseType, baseBlock, blockSupplier);
    }

    @Environment(EnvType.CLIENT)
    public void registerTileRenderer(ClientHelper.BlockEntityRendererEvent event, BlockEntityRendererProvider<BlockEntity> renderer) {
        if (tileHolder != null) {
            tileHolder.registerRenderer(event, renderer);
        }
    }

    //!! SUBCLASS
    public static class Builder<T extends BlockType, B extends Block> extends AbstractSimpleEntrySet.Builder<Builder<T, B>, T, B, Item> {
        protected final Supplier<@Nullable B> baseBlock;
        protected LootTableMode lootMode = LootTableMode.DROP_SELF;
        protected final Function<T, B> blockFactory;
        @Nullable
        protected TriFunction<T, B, Item.Properties, Item> itemFactory;
        @Nullable
        protected SimpleEntrySet.ITileHolder<?> tileHolder;

        @Nullable
        protected Object renderType = null;

        protected ModelConfiguration modelConfig = ModelConfiguration.EMPTY;

        protected Builder(Class<T> type, String name, @Nullable String prefix, Supplier<T> baseType, Supplier<B> baseBlock, Function<T, B> blockFactory) {
            super(type, name, prefix, baseType);
            this.baseBlock = baseBlock;
            this.blockFactory = blockFactory;
        }

        public SimpleEntrySet<T, B> build() {
            if (tab == null && PlatHelper.isDev()) {
                throw new IllegalStateException("Tab for module " + name + " was null!");
            }
            var e = new SimpleEntrySet<>(type, name, prefix, blockFactory, baseBlock, baseType, tab, tabMode, lootMode,
                    itemFactory, tileHolder, renderType, palette, extraModelTransform, useMergedPalette, copyTint, condition,
                    this.modelConfig
            );
            e.recipeLocations.addAll(this.recipes);
            e.tags.putAll(this.tags);
            e.textures.addAll(textures);
            return e;
        }

        public <H extends BlockEntity> Builder<T, B> addTile(String idTile) {
            BlockEntityType<?> entityType = BuiltInRegistries.BLOCK_ENTITY_TYPE.getOptional(new ResourceLocation(idTile))
                    .orElseThrow(() -> new NoSuchElementException("Tile entity with ID" + idTile + " not found! ID must be wrong!"));

            this.tileHolder = new ExistingTileHolder<>(() -> entityType);
            return this;
        }

        public <H extends BlockEntity> Builder<T, B> addTile(Supplier<BlockEntityType<H>> tile) {
            this.tileHolder = new ExistingTileHolder<>(tile);
            return this;
        }

        public <H extends BlockEntity> Builder<T, B> addTile(BiFunction<BlockPos, BlockState, H> tileFactory) {
            this.tileHolder = new NewTileHolder<>(tileFactory);
            return this;
        }

        public Builder<T, B> addCustomItem(TriFunction<T, B, Item.Properties, Item> itemFactory) {
            this.itemFactory = itemFactory;
            return this;
        }

        public Builder<T, B> noItem() {
            this.itemFactory = (a, b, c) -> null;
            return this;
        }

        /**
         * As opposed to just dropping itself
         */
        public Builder<T, B> copyParentDrop() {
            this.lootMode = LootTableMode.COPY_FROM_PARENT;
            return this;
        }

        //default
        public Builder<T, B> dropSelf() {
            this.lootMode = LootTableMode.DROP_SELF;
            return this;
        }

        public Builder<T, B> noDrops() {
            this.lootMode = LootTableMode.NO_LOOT;
            return this;
        }

        @Deprecated(forRemoval = true)
        public Builder<T, B> setRenderType(Supplier<Supplier<Object>> renderType) {
            this.renderType = renderType;
            return this;
        }

        public Builder<T, B> setRenderType(RenderLayer renderType) {
            this.renderType = renderType;
            return this;
        }

        public Builder<T, B> defaultRecipe() {
            this.recipes.add(() -> Utils.getID(this.baseBlock.get()));
            return this;
        }


        /// Add models/block files so it can be generated - Only MINECRAFT's
        public Builder<T, B> generateBlockModels(ResourceLocation... blockModels) {
            if (this.modelConfig == ModelConfiguration.EMPTY) {
                this.modelConfig = ModelConfiguration.createNew();
            }
            this.modelConfig.addBlockModel(blockModels);
            return this;
        }

        /// Add models/block files to a List so it can be generated BUT Minecraft is excluded
        public Builder<T, B> generateBlockModels(boolean includeInGeneration, ResourceLocation... blockModels) {
            if (this.modelConfig == ModelConfiguration.EMPTY) {
                this.modelConfig = ModelConfiguration.createNew(includeInGeneration);
            }
            this.modelConfig.addBlockModel(blockModels);
            return this;
        }

        /// Add models/item files so it can be generated - Only MINECRAFT's
        public Builder<T, B> generateItemModels(ResourceLocation... itemModels) {
            if (this.modelConfig == ModelConfiguration.EMPTY) {
                this.modelConfig = ModelConfiguration.createNew();
            }
            this.modelConfig.addItemModel(itemModels);
            return this;
        }

        /// Add models/item files to a List so it can be generated BUT Minecraft is excluded
        public Builder<T, B> generateItemModels(boolean includeInGeneration, ResourceLocation... itemModels) {
            if (this.modelConfig == ModelConfiguration.EMPTY) {
                this.modelConfig = ModelConfiguration.createNew(includeInGeneration);
            }
            this.modelConfig.addItemModel(itemModels);
            return this;
        }


        /// Is there a way to get baseBlock?
//        public Builder<T, B> defaultBlockTexture() {
//            var test = this.baseBlock;
//            this.textures.add(TextureInfo.of(Utils.getID(this.baseBlock.get()).withPrefix("block/")).build());
//            return this;
//        }

//        public Builder<T, B> defaultItemTexture() {
//            this.textures.add(TextureInfo.of(Utils.getID(this.baseBlock.get()).withPrefix("item/")).build());
//            return this;
//        }

    }


    public interface ITileHolder<H extends BlockEntity> {

        BlockEntityType<H> get();

        @Environment(EnvType.CLIENT)
        default void registerRenderer(ClientHelper.BlockEntityRendererEvent event, BlockEntityRendererProvider<BlockEntity> renderer) {
            event.register(get(), renderer);
        }
    }

    public record ExistingTileHolder<H extends BlockEntity>(
            Supplier<BlockEntityType<H>> supplier) implements ITileHolder<H> {

        @Override
        public BlockEntityType<H> get() {
            return supplier.get();
        }
    }

    public static class NewTileHolder<H extends BlockEntity> implements ITileHolder<H> {

        protected final BiFunction<BlockPos, BlockState, H> tileFactory;
        protected Supplier<BlockEntityRendererProvider<H>> renderer = null;
        protected BlockEntityType<H> tile = null;


        public NewTileHolder(BiFunction<BlockPos, BlockState, H> tileFactory) {
            this.tileFactory = tileFactory;
        }

        public BlockEntityType<H> get() {
            return tile;
        }

        public BlockEntityType<? extends H> createInstance(Block... blocks) {
            if (tile != null) throw new UnsupportedOperationException("tile has already been created");
            this.tile = PlatHelper.newBlockEntityType(tileFactory::apply, blocks);
            return tile;
        }
    }


    protected static final boolean YEET_JSONS = true;
    protected static final Set<Block> SIMPLE_DROPS = new HashSet<>();

    public static boolean isSimpleDrop(Block block) {
        return SIMPLE_DROPS.contains(block);
    }

    public enum LootTableMode {
        DROP_SELF,
        COPY_FROM_PARENT,
        NO_LOOT
    }


}
