package net.mehvahdjukaar.every_compat.api;

import com.mojang.datafixers.util.Pair;
import net.mehvahdjukaar.every_compat.misc.ModelConfiguration;
import net.mehvahdjukaar.every_compat.misc.ResourcesUtils;
import net.mehvahdjukaar.moonlight.api.events.AfterLanguageLoadEvent;
import net.mehvahdjukaar.moonlight.api.misc.Registrator;
import net.mehvahdjukaar.moonlight.api.resources.BlockTypeResTransformer;
import net.mehvahdjukaar.moonlight.api.resources.assets.LangBuilder;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceSink;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.set.BlockTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.moonlight.core.misc.McMetaFile;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.*;

//contrary to popular belief this class is indeed not simple. Its usage however is
public class ItemOnlyEntrySet<T extends BlockType, I extends Item> extends AbstractSimpleEntrySet<T, Block, I> {

    protected final Supplier<@Nullable I> baseItem;
    protected final Function<T, @Nullable I> itemFactory;
    protected ModelConfiguration modelConfig;

    public ItemOnlyEntrySet(Class<T> type,
                            String name, @Nullable String prefix,
                            Function<T, I> itemFactory,
                            Supplier<@Nullable I> baseItem,
                            Supplier<T> baseType,
                            @Nullable Supplier<ResourceKey<CreativeModeTab>> tab,
                            TabAddMode tabMode,
                            @Nullable BiFunction<T, ResourceManager, Pair<List<Palette>, @Nullable McMetaFile>> paletteSupplier,
                            @Nullable Consumer<BlockTypeResTransformer<T>> extraTransform,
                            boolean mergedPalette, boolean copyTint,
                            Predicate<T> condition,
                            ModelConfiguration modelConfig
    ) {
        super(type, name, prefix, baseType, tab, tabMode, paletteSupplier, extraTransform, mergedPalette, copyTint, condition);
        this.itemFactory = itemFactory;
        this.baseItem = baseItem;
        this.modelConfig = modelConfig;
    }

    public I getBaseItem() {
        return baseItem.get();
    }

    @Override
    public void addTranslations(SimpleModule module, AfterLanguageLoadEvent lang) {
        items.forEach((w, v) -> LangBuilder.addDynamicEntry(lang, "item_type." + module.getModId() + "." + typeName, w, v));
    }

    @Override
    public void registerBlocks(SimpleModule module, Registrator<Block> registry, Collection<T> woodTypes) {

    }

    @NotNull
    public String getItemName(T w) {
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
        BlockTypeRegistry<T> typeRegistry = BlockSetAPI.getTypeRegistry(this.type);
        for (T blockType : Objects.requireNonNull(typeRegistry).getValues()) {
            String name = getItemName(blockType);
            String fullName = module.shortenedId() + "/" + blockType.getNamespace() + "/" + name;
            if (module.isEntryAlreadyRegistered(name, blockType, BuiltInRegistries.ITEM)) continue;

            if (condition.test(blockType)) {
                I item = itemFactory.apply(blockType);
                //for blocks that fail
                if (item != null) {
                    this.items.put(blockType, item);

                    String childkey = getChildKey(module);
                    if (childkey.contains("minecraft")) childkey = childkey.replace("minecraft:", "");

                    registry.register(module.makeMyRes(fullName), item);
                    blockType.addChild(childkey, item);
                    totalChildren++;
                }
            }
        }
        //populate default ones
    }

    @Override
    public void registerTiles(SimpleModule module, Registrator<BlockEntityType<?>> registry) {
        Item base = getBaseItem();
        if (base == null || base == Items.AIR)
            //?? wtf im using disabled to allow for null??
            throw new UnsupportedOperationException("Base Item cant be null (" + this.typeName + " for " + module.modId + " module)");

        String childKey = getChildKey(module);
        baseType.get().addChild(childKey, base);

        //attempts adding all other children
        Set<String> alreadySupportedMods = new HashSet<>(module.getAlreadySupportedMods());
        alreadySupportedMods.add(module.modId);
        var possibleNamespaces = alreadySupportedMods.toArray(String[]::new);
        for (var w : Objects.requireNonNull( BlockSetAPI.getTypeRegistry(this.getTypeClass())).getValues() ) {
            if (!items.containsKey(w)) {
                String path = getItemName(w);
                Item item = getOptionalItem(path, w.getNamespace());
                if (item == null) item = getOptionalItem(path, possibleNamespaces);
                if (item != null) w.addChild(childKey, item);
            }
        }
    }

    @Nullable
    private static Item getOptionalItem(String path, String... namespaces) {
        ResourceLocation id;
        for (var n : namespaces) {
            id = new ResourceLocation(n, path);
            var i = BuiltInRegistries.ITEM.getOptional(id);
            if (i.isPresent()) {
                return i.get();
            }
        }
        return null;
    }

    @Override
    public void setRenderLayer() {

    }

    @Override
    public void generateLootTables(SimpleModule module, ResourceManager manager, ResourceSink sink) {

    }

    @Override
    public void generateModels(SimpleModule module, ResourceManager manager, ResourceSink handler) {
        ResourcesUtils.generateStandardItemModels(manager, handler, items, baseType.get(),
                makeModelTransformer(module, manager), modelConfig);
    }

    // items models
    protected BlockTypeResTransformer<T> makeModelTransformer(SimpleModule module, ResourceManager manager) {
        BlockTypeResTransformer<T> modelTransformer = BlockTypeResTransformer.create(module.modId, manager);
        if (extraModelTransform != null) extraModelTransform.accept(modelTransformer);

        ResourcesUtils.addBuiltinModelTransformer(modelTransformer, baseType.get());

        return modelTransformer;
    }

    @Override
    public Map<T, ?> getDefaultEntries() {
        return items;
    }

    //ok...
    public static <T extends BlockType, I extends Item> Builder<T, I> builder(Class<T> type, String name,
                                                                              Supplier<I> baseItem, Supplier<T> baseType,
                                                                              Function<T, I> itemSupplier) {

        return new Builder<>(type, name, null, baseType, baseItem, itemSupplier);
    }

    public static <T extends BlockType, I extends Item> Builder<T, I> builder(Class<T> type, String name, String prefix,
                                                                              Supplier<I> baseItem, Supplier<T> baseType,
                                                                              Function<T, I> itemSupplier) {

        return new Builder<>(type, name, prefix, baseType, baseItem, itemSupplier);
    }

    public static class Builder<T extends BlockType, I extends Item> extends AbstractSimpleEntrySet.Builder<Builder<T, I>, T, Block, I> {
        protected final Supplier<@Nullable I> baseItem;
        protected final Function<T, I> itemFactory;
        protected ModelConfiguration modelConfig = ModelConfiguration.EMPTY;

        protected Builder(Class<T> type, String name, @Nullable String prefix, Supplier<T> baseType, Supplier<I> baseItem, Function<T, I> itemFactory) {
            super(type, name, prefix, baseType);
            this.baseItem = baseItem;
            this.itemFactory = itemFactory;
        }

        public ItemOnlyEntrySet<T, I> build() {
            var e = new ItemOnlyEntrySet<>(type, name, prefix, itemFactory, baseItem, baseType, tab, tabMode,
                    palette, extraModelTransform, useMergedPalette, copyTint, condition, modelConfig);
            e.recipeLocations.addAll(this.recipes);
            e.tags.putAll(this.tags);
            e.textures.addAll(textures);
            return e;
        }

        public ItemOnlyEntrySet.Builder<T, I> defaultRecipe() {
            this.recipes.add(() -> Utils.getID(this.baseItem.get()));
            return this;
        }


        /// Add models/block files so it can be generated - Only MINECRAFT's
        public Builder<T, I> generateBlockModels(ResourceLocation... blockModels) {
            if (this.modelConfig == ModelConfiguration.EMPTY) {
                this.modelConfig = ModelConfiguration.createNew();
            }
            this.modelConfig.addBlockModel(blockModels);
//            GemsRealmModule.putInModelsToModify(blockModels);
            return this;
        }

        /// Add models/item files so it can be generated - Only MINECRAFT's
        public Builder<T, I> generateItemModels(ResourceLocation... itemModels) {
            if (this.modelConfig == ModelConfiguration.EMPTY) {
                this.modelConfig = ModelConfiguration.createNew();
            }
            this.modelConfig.addItemModel(itemModels);
            return this;
        }

    }
}
