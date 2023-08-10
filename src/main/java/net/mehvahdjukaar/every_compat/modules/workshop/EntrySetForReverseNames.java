package net.mehvahdjukaar.every_compat.modules.workshop;

import com.mojang.datafixers.util.Pair;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.selene.block_set.BlockType;
import net.mehvahdjukaar.selene.client.asset_generators.textures.Palette;
import net.mehvahdjukaar.selene.resourcepack.BlockTypeResTransformer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.resources.metadata.animation.AnimationMetadataSection;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.apache.commons.lang3.function.TriFunction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class EntrySetForReverseNames<T extends BlockType, B extends Block> extends SimpleEntrySet<T, B>
{
    public EntrySetForReverseNames(Class<T> type, String name, @Nullable String prefix, Function<T, B> blockSupplier, Supplier<B> baseBlock, Supplier<T> baseType, Supplier<CreativeModeTab> tab, boolean copyLoot, @Nullable TriFunction<T, B, Item.Properties, Item> itemFactory, @Nullable TileHolder<?> tileFactory, @Nullable Supplier<Supplier<RenderType>> renderType, @Nullable BiFunction<T, ResourceManager, Pair<List<Palette>, @Nullable AnimationMetadataSection>> paletteSupplier, Consumer<BlockTypeResTransformer<T>> extraTransform)
    {
        super(type, name, prefix, blockSupplier, baseBlock, baseType, tab, copyLoot, itemFactory, tileFactory, renderType, paletteSupplier, extraTransform);
    }

    @Override
    public @NotNull String getBlockName(T w)
    {
        String name;
        if (prefix != null) {
            name = this.prefix + "_" + this.postfix + "_" + w.getTypeName();
        } else {
            name = this.postfix + "_" + w.getTypeName();
        }
        return name;
    }



    public static <T extends BlockType, B extends Block> BuilderInternal<T, B> builderSpecial(Class<T> type, String name, Supplier<B> baseBlock, Supplier<T> baseType, Function<T, B> blockSupplier) {
        return new BuilderInternal<>(type, name, null, baseType, baseBlock, blockSupplier);
    }

    public static <T extends BlockType, B extends Block> BuilderInternal<T, B> builderSpecial(Class<T> type, String name, String prefix, Supplier<B> baseBlock, Supplier<T> baseType, Function<T, B> blockSupplier) {
        return new BuilderInternal<>(type, name, prefix, baseType, baseBlock, blockSupplier);
    }

    /////////////////////////////////////////////

    public static class BuilderInternal<T extends BlockType, B extends Block> extends SimpleEntrySet.Builder
    {
        protected BuilderInternal(Class type, String name, @Nullable String prefix, Supplier baseType, Supplier baseBlock, Function blockFactory)
        {
            super(type, name, prefix, baseType, baseBlock, blockFactory);
        }

        @Override
        public SimpleEntrySet<T, B> build() {
            var e = new EntrySetForReverseNames<>(type, name, prefix, blockFactory, baseBlock, baseType, tab, copyLoot,
                    itemFactory, tileFactory, renderType, palette, extraModelTransform);
            e.recipeLocations.addAll(this.recipes);
            e.tags.putAll(this.tags);
            e.textures.addAll(textures);
            return e;
        }
    }
}
