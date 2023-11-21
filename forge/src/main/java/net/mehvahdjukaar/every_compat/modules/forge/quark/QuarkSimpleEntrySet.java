package net.mehvahdjukaar.every_compat.modules.forge.quark;

import com.mojang.datafixers.util.Pair;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.CompatModule;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.modules.forge.architect_palette.ArchitectsPaletteModule;
import net.mehvahdjukaar.moonlight.api.events.AfterLanguageLoadEvent;
import net.mehvahdjukaar.moonlight.api.misc.Registrator;
import net.mehvahdjukaar.moonlight.api.resources.BlockTypeResTransformer;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynamicDataPack;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.resources.metadata.animation.AnimationMetadataSection;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.apache.commons.lang3.function.TriFunction;
import org.jetbrains.annotations.Nullable;
import vazkii.quark.base.module.ModuleLoader;
import vazkii.quark.base.module.QuarkModule;

import java.util.Collection;
import java.util.List;
import java.util.function.*;

class QuarkSimpleEntrySet<T extends BlockType, B extends Block> extends SimpleEntrySet<T, B> {

    private final BiFunction<T, QuarkModule, B> blockSupplier;
    private final Class<? extends vazkii.quark.base.module.QuarkModule> quarkModule;

    public QuarkSimpleEntrySet(Class<T> type,
                               String name, @Nullable String prefix,
                               Class<? extends vazkii.quark.base.module.QuarkModule> module,
                               Supplier<B> baseBlock,
                               Supplier<T> baseType,
                               BiFunction<T, vazkii.quark.base.module.QuarkModule, B> blockSupplier,
                               Supplier<ResourceKey<CreativeModeTab>> tab,
                               LootTableMode tableMode,
                               @Nullable TriFunction<T, B, Item.Properties, Item> itemFactory,
                               @Nullable SimpleEntrySet.ITileHolder<?> tileFactory,
                               @Nullable Supplier<Supplier<RenderType>> renderType,
                               @Nullable BiFunction<T, ResourceManager, Pair<List<Palette>, @Nullable AnimationMetadataSection>> paletteSupplier,
                               @Nullable Consumer<BlockTypeResTransformer<T>> extraTransform,
                               boolean mergedPalette,
                               Predicate<T> condition) {
        super(type, name, prefix, null, baseBlock, baseType, tab, tableMode, itemFactory, tileFactory, renderType, paletteSupplier, extraTransform, mergedPalette, condition);
        this.blockSupplier = blockSupplier;
        this.quarkModule = module;
    }

    @Override
    public void generateLootTables(CompatModule module, DynamicDataPack pack, ResourceManager manager) {
        super.generateLootTables(module, pack, manager);
    }

    @Override
    public void addTranslations(CompatModule module, AfterLanguageLoadEvent lang) {
        super.addTranslations(module, lang);
    }

    @Override
    public void registerBlocks(CompatModule module, Registrator<Block> registry, Collection<T> woodTypes) {
        Block base = baseBlock.get();
        if (base == null)
            throw new UnsupportedOperationException("Base block cant be null");
        baseType.get().addChild(module.getModId() + ":" + typeName,(Object) base);

        for (T w : woodTypes) {
            String n = getBlockName(w);
            String name = module.shortenedId() + "/" + w.getNamespace() + "/" + n;
            if (w.isVanilla() || module.isEntryAlreadyRegistered(name, w, BuiltInRegistries.BLOCK)) continue;
            var m = ModuleLoader.INSTANCE.getModuleInstance(quarkModule);
            B block = blockSupplier.apply(w, m);
            if (block != null) {
                this.blocks.put(w, block);

                registry.register(EveryCompat.res(name), block); //does not set registry name
                w.addChild(module.getModId() + ":" + typeName,(Object) block);


                if (lootMode == LootTableMode.DROP_SELF && YEET_JSONS) {
                    SIMPLE_DROPS.add(block);
                }
            }
        }
    }


    //this does not work. all modules seem to be disabled here. why??
    /*
    @Override
    protected CreativeModeTab getTab(T w, B b) {
        boolean e = b instanceof IQuarkBlock qb ? qb.isEnabled() : ModuleLoader.INSTANCE.isModuleEnabled(quarkModule);
        return e ? super.getTab(w, b) : null;
    }*/

    public static <T extends BlockType, B extends Block> Builder<T, B> builder(
            Class<T> type,
            String name,
            Class<? extends vazkii.quark.base.module.QuarkModule> quarkModule,
            Supplier<B> baseBlock, Supplier<T> baseType,
            BiFunction<T, QuarkModule, B> factory) {
        return new Builder<>(type, name, null, quarkModule, baseType, baseBlock, factory);
    }

    public static <T extends BlockType, B extends Block> Builder<T, B> builder(
            Class<T> type,
            String name, String prefix,
            Class<? extends vazkii.quark.base.module.QuarkModule> quarkModule,
            Supplier<B> baseBlock, Supplier<T> baseType,
            BiFunction<T, QuarkModule, B> factory) {
        return new Builder<>(type, name, prefix, quarkModule, baseType, baseBlock, factory);
    }

    public static class Builder<T extends BlockType, B extends Block> extends SimpleEntrySet.Builder<T, B> {

        private final BiFunction<T, QuarkModule, B> blockSupplier;
        private final Class<? extends vazkii.quark.base.module.QuarkModule> quarkModule;

        protected Builder(Class<T> type, String name, @Nullable String prefix,
                          Class<? extends vazkii.quark.base.module.QuarkModule> quarkModule,
                          Supplier<T> baseType, Supplier<B> baseBlock, BiFunction<T, QuarkModule, B> factory) {
            super(type, name, prefix, baseType, baseBlock, null);
            this.quarkModule = quarkModule;
            this.blockSupplier = factory;
        }

        @Override
        public QuarkSimpleEntrySet<T, B> build() {
            var e = new QuarkSimpleEntrySet<>(type, name, prefix, quarkModule,
                    baseBlock, baseType, blockSupplier, tab, lootMode,
                    itemFactory, tileHolder, renderType, palette, extraModelTransform, useMergedPalette, condition);
            e.recipeLocations.addAll(this.recipes);
            e.tags.putAll(this.tags);
            e.textures.addAll(textures);
            return e;
        }
    }

}