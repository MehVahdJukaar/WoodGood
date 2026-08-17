package net.mehvahdjukaar.every_compat.modules.neoforge.quark;

import com.google.common.base.Preconditions;
import com.google.common.base.Suppliers;
import net.mehvahdjukaar.every_compat.api.PaletteStrategy;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.api.TabAddMode;
import net.mehvahdjukaar.every_compat.misc.ExtraModelConfiguration;
import net.mehvahdjukaar.moonlight.api.resources.BlockTypeResTransformer;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceSink;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.minecraft.core.Holder;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.apache.commons.lang3.function.TriFunction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.violetmoon.quark.base.Quark;
import org.violetmoon.zeta.module.IDisableable;
import org.violetmoon.zeta.module.ZetaModule;

import java.util.function.*;

@SuppressWarnings("DataFlowIssue")
public class QuarkSimpleEntrySet<T extends BlockType, B extends Block> extends SimpleEntrySet<T, B> {

    private final Supplier<ZetaModule> zetaModule;

    public QuarkSimpleEntrySet(Class<T> type,
                               String name, @Nullable String prefix,
                               Class<? extends ZetaModule> module,
                               Supplier<B> baseBlock,
                               Supplier<T> baseType,
                               Function<T, B> blockSupplier,
                               @NotNull Supplier<Holder<CreativeModeTab>> tab,
                               TabAddMode tabMode,
                               LootTableMode tableMode,
                               @Nullable TriFunction<T, B, Item.Properties, Item> itemFactory,
                               @Nullable SimpleEntrySet.ITileHolder<?> tileFactory,
                               @Nullable Object renderType,
                               @Nullable BiFunction<T, ResourceManager, PaletteStrategy.PaletteAndAnimation> paletteSupplier,
                               @Nullable Consumer<BlockTypeResTransformer<T>> extraTransform,
                               boolean mergedPalette,
                               boolean copyTint,
                               Predicate<T> condition,
                               ExtraModelConfiguration modelConfig
    ) {
        super(type, name, prefix, blockSupplier, baseBlock, baseType, tab, tabMode, tableMode, itemFactory,
                tileFactory, renderType, paletteSupplier, extraTransform, mergedPalette, copyTint, condition, modelConfig);
        var m = Preconditions.checkNotNull(module);
        this.zetaModule = Suppliers.memoize(() -> Quark.ZETA.modules.get(m));
    }

    @Override
    public void generateRecipes(SimpleModule module, ResourceManager manager, ResourceSink pack) {
        ZetaModule mod = zetaModule.get();
        if (mod == null || mod.isEnabled()) {
            super.generateRecipes(module, manager, pack);
        }
    }

    @Override
    public @Nullable Item getItemOf(T type) {
        ZetaModule mod = zetaModule.get();
        if (mod == null || mod.isEnabled()) {
            Item item = super.getItemOf(type);
            if (item instanceof IDisableable<?> d && !d.doesConditionApply()) {
                return null;
            }
            if (item instanceof BlockItem bi && bi.getBlock() instanceof IDisableable<?> d && !d.doesConditionApply()) {
                return null;
            }
            return item;
        }
        return null;
    }

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
            QuarkSimpleEntrySet<T, B> e = finishBuild(new QuarkSimpleEntrySet<>(type, name, prefix, quarkModule,
                    baseBlock, baseType, blockSupplier, tab, tabMode, lootMode,
                    itemFactory, tileHolder, renderType, null, extraModelTransform, useMergedPalette, copyTint, condition,
                    extraModelConfig
            ));
            for(var t : this.textures){
                if(this.palette != null) {
                    e.textures.add(t.cloneWithPalette((blockType, manager) ->
                            this.palette.apply((T) blockType, manager)));
                }else{
                    e.textures.add(t);
                }
            }
            return e;
        }
    }

}