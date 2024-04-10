package net.mehvahdjukaar.every_compat.modules.quark;

import com.google.common.base.Preconditions;
import com.google.common.base.Suppliers;
import com.mojang.datafixers.util.Pair;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.CompatModule;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.events.AfterLanguageLoadEvent;
import net.mehvahdjukaar.moonlight.api.misc.Registrator;
import net.mehvahdjukaar.moonlight.api.resources.BlockTypeResTransformer;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynamicDataPack;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.resources.metadata.animation.AnimationMetadataSection;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.apache.commons.lang3.function.TriFunction;
import org.jetbrains.annotations.Nullable;
import org.violetmoon.quark.base.Quark;
import org.violetmoon.zeta.module.ZetaModule;

import java.util.Collection;
import java.util.List;
import java.util.function.*;

class QuarkSimpleEntrySet<T extends BlockType, B extends Block> extends SimpleEntrySet<T, B> {

    private final Supplier<ZetaModule> zetaModule;

    public QuarkSimpleEntrySet(Class<T> type,
                               String name, @Nullable String prefix,
                               Class<? extends ZetaModule> module,
                               Supplier<B> baseBlock,
                               Supplier<T> baseType,
                               Function<T, B> blockSupplier,
                               Supplier<ResourceKey<CreativeModeTab>> tab,
                               LootTableMode tableMode,
                               @Nullable TriFunction<T, B, Item.Properties, Item> itemFactory,
                               @Nullable SimpleEntrySet.ITileHolder<?> tileFactory,
                               @Nullable Supplier<Supplier<RenderType>> renderType,
                               @Nullable BiFunction<T, ResourceManager, Pair<List<Palette>, @Nullable AnimationMetadataSection>> paletteSupplier,
                               @Nullable Consumer<BlockTypeResTransformer<T>> extraTransform,
                               boolean mergedPalette,
                               Predicate<T> condition) {
        super(type, name, prefix, blockSupplier, baseBlock, baseType, tab, tableMode, itemFactory,
                tileFactory, renderType, paletteSupplier, extraTransform, mergedPalette, condition);
        var m = Preconditions.checkNotNull(module);
        this.zetaModule = Suppliers.memoize(() -> Quark.ZETA.modules.get(m));
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
    public void generateRecipes(CompatModule module, DynamicDataPack pack, ResourceManager manager) {
        ZetaModule mod = zetaModule.get();
        if (mod == null || mod.enabled) {
            super.generateRecipes(module, pack, manager);
        }
    }

    @Override
    public @Nullable Item getItemOf(T type) {
        ZetaModule mod = zetaModule.get();
        if (mod == null || mod.enabled) {
            return super.getItemOf(type);
        }
        return null;
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
            Class<? extends ZetaModule> quarkModule,
            Supplier<B> baseBlock, Supplier<T> baseType,
            Function<T, B> factory) {
        return new Builder<>(type, name, null, quarkModule, baseType, baseBlock, factory);
    }

    public static <T extends BlockType, B extends Block> Builder<T, B> builder(
            Class<T> type,
            String name, String prefix,
            Class<? extends ZetaModule> quarkModule,
            Supplier<B> baseBlock, Supplier<T> baseType,
            Function<T, B> factory) {
        return new Builder<>(type, name, prefix, quarkModule, baseType, baseBlock, factory);
    }

    public static class Builder<T extends BlockType, B extends Block> extends SimpleEntrySet.Builder<T, B> {

        private final Function<T, B> blockSupplier;
        private final Class<? extends ZetaModule> quarkModule;

        protected Builder(Class<T> type, String name, @Nullable String prefix,
                          Class<? extends ZetaModule> quarkModule,
                          Supplier<T> baseType, Supplier<B> baseBlock, Function<T, B> factory) {
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