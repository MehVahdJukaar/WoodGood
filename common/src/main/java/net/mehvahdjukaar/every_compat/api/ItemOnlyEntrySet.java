package net.mehvahdjukaar.every_compat.api;

import com.mojang.datafixers.util.Pair;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.misc.ResourcesUtils;
import net.mehvahdjukaar.moonlight.api.events.AfterLanguageLoadEvent;
import net.mehvahdjukaar.moonlight.api.misc.Registrator;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.resources.BlockTypeResTransformer;
import net.mehvahdjukaar.moonlight.api.resources.assets.LangBuilder;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynClientResourcesGenerator;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynamicDataPack;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.set.BlockTypeRegistry;
import net.minecraft.client.resources.metadata.animation.AnimationMetadataSection;
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

    public ItemOnlyEntrySet(Class<T> type,
                            String name, @Nullable String prefix,
                            Function<T, I> itemFactory,
                            Supplier<@Nullable I> baseItem,
                            Supplier<T> baseType,
                            Supplier<ResourceKey<CreativeModeTab>> tab,
                            @Nullable BiFunction<T, ResourceManager, Pair<List<Palette>, @Nullable AnimationMetadataSection>> paletteSupplier,
                            @Nullable Consumer<BlockTypeResTransformer<T>> extraTransform,
                            boolean mergedPalette,
                            Predicate<T> condition) {
        super(type, name, prefix, baseType, tab, paletteSupplier, extraTransform, mergedPalette, condition);
        this.itemFactory = itemFactory;
        this.baseItem = baseItem;
    }

    @Override
    public boolean isDisabled() {
        return this.getBaseItem() == null;
    }

    public I getBaseItem() {
        return baseItem.get();
    }


    public void addTranslations(CompatModule module, AfterLanguageLoadEvent lang) {
        items.forEach((w, v) -> LangBuilder.addDynamicEntry(lang, "item_type." + module.getModId() + "." + typeName, w, v));
    }

    @Override
    public void registerBlocks(CompatModule module, Registrator<Block> registry, Collection<T> woodTypes) {

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
    public void registerItems(CompatModule module, Registrator<Item> registry) {
        //if (isDisabled()) return;
        //contrary to blocks other items could be null here

        BlockTypeRegistry<T> typeRegistry = BlockSetAPI.getTypeRegistry(this.type);
        for (T w : typeRegistry.getValues()) {
            String name = getItemName(w);
            String fullName = module.shortenedId() + "/" + w.getNamespace() + "/" + name;
            if (w.isVanilla() || module.isEntryAlreadyRegistered(name, w, BuiltInRegistries.ITEM)) continue;

            if (condition.test(w)) {
                I item = itemFactory.apply(w);
                //for blocks that fail
                if (item != null) {
                    this.items.put(w, item);

                    registry.register(EveryCompat.res(fullName), item);
                    w.addChild(getChildKey(module), item);
                    EveryCompat.ITEMS_TO_MODULES.put(item, module);
                }
            }
        }
        //populate default ones
    }

    @Override
    public void registerTiles(CompatModule module, Registrator<BlockEntityType<?>> registry) {
        Item base = getBaseItem();
        if (base == null || base == Items.AIR)
            //?? wtf im using disabled to allow for null??
            throw new UnsupportedOperationException("Base block cant be null (" + this.typeName + " for " + module.modId + " module)");

        String childKey = getChildKey(module);
        baseType.get().addChild(childKey,  base);

        //attempts adding all other children
        Set<String> alreadySupportedMods = new HashSet<>(module.getAlreadySupportedMods());
        alreadySupportedMods.add(module.modId);
        var possibleNamespaces =  alreadySupportedMods.toArray(String[]::new);
        for(var w : BlockSetAPI.getTypeRegistry(this.getTypeClass()).getValues()){
            if(!items.containsKey(w)){
                String path = getItemName(w);
                Item item = getOptionalItem(path, w.getNamespace());
                if(item == null) item = getOptionalItem(path, possibleNamespaces);
                if(item != null) w.addChild(childKey,  item);
            }
        }
    }

    @Nullable
    private static Item getOptionalItem(String path, String ...namespaces) {
        ResourceLocation id;
        for (var n : namespaces) {
            id = new ResourceLocation(n, path);
            var i = BuiltInRegistries.ITEM.getOptional(id);
            if(i.isPresent()){
                return i.get();
            }
        }
        return null;
    }

    @Override
    public void setRenderLayer() {

    }

    @Override
    public void generateLootTables(CompatModule module, DynamicDataPack pack, ResourceManager manager) {

    }

    @Override
    public void generateModels(CompatModule module, DynClientResourcesGenerator handler, ResourceManager manager) {
        if (isDisabled()) return;
        ResourcesUtils.addItemModels(module.getModId(), manager, handler, items, baseType.get(), extraTransform);
    }

    @Override
    public void registerEntityRenderers(CompatModule simpleModule, ClientHelper.BlockEntityRendererEvent event) {

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

        protected Builder(Class<T> type, String name, @Nullable String prefix, Supplier<T> baseType, Supplier<I> baseItem, Function<T, I> itemFactory) {
            super(type, name, prefix, baseType);
            this.baseItem = baseItem;
            this.itemFactory = itemFactory;
        }

        public ItemOnlyEntrySet<T, I> build() {
            var e = new ItemOnlyEntrySet<>(type, name, prefix, itemFactory, baseItem, baseType, tab,
                    palette, extraModelTransform, useMergedPalette, condition);
            e.recipeLocations.addAll(this.recipes);
            e.tags.putAll(this.tags);
            e.textures.addAll(textures);
            return e;
        }
    }
}
