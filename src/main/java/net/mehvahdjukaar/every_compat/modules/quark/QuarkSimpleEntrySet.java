package net.mehvahdjukaar.every_compat.modules.quark;

import com.mojang.datafixers.util.Pair;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.modules.CompatModule;
import net.mehvahdjukaar.selene.block_set.BlockType;
import net.mehvahdjukaar.selene.client.asset_generators.textures.Palette;
import net.mehvahdjukaar.selene.resourcepack.RPAwareDynamicTextureProvider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.resources.metadata.animation.AnimationMetadataSection;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.commons.lang3.function.TriFunction;
import org.jetbrains.annotations.Nullable;
import vazkii.quark.base.module.ModuleLoader;
import vazkii.quark.base.module.QuarkModule;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;

class QuarkSimpleEntrySet<T extends BlockType, B extends Block> extends SimpleEntrySet<T, B> {

    private final BiFunction<T, QuarkModule, B> blockSupplier;
    private final Class<? extends vazkii.quark.base.module.QuarkModule> quarkModule;

    public QuarkSimpleEntrySet(String name,
                               Class<? extends vazkii.quark.base.module.QuarkModule> module,
                               Supplier<B> baseBlock, Supplier<T> baseType,
                               BiFunction<T, vazkii.quark.base.module.QuarkModule, B> blockSupplier,
                               CreativeModeTab tab, boolean copyLoot,
                               @Nullable TriFunction<T, B, Item.Properties, Item> itemFactory,
                               @Nullable SimpleEntrySet.TileHolder<?> tileFactory,
                               @Nullable Supplier<Supplier<RenderType>> renderType,
                               @Nullable BiFunction<T, ResourceManager, Pair<List<Palette>, @Nullable AnimationMetadataSection>> paletteSupplier) {
        this(name, null, module, baseBlock, baseType, blockSupplier, tab, copyLoot, itemFactory, tileFactory, renderType, paletteSupplier);
    }

    public QuarkSimpleEntrySet(String name, @Nullable String prefix,
                               Class<? extends vazkii.quark.base.module.QuarkModule> module,
                               Supplier<B> baseBlock, Supplier<T> baseType,
                               BiFunction<T, vazkii.quark.base.module.QuarkModule, B> blockSupplier,
                               CreativeModeTab tab, boolean copyLoot,
                               @Nullable TriFunction<T, B, Item.Properties, Item> itemFactory,
                               @Nullable SimpleEntrySet.TileHolder<?> tileFactory,
                               @Nullable Supplier<Supplier<RenderType>> renderType,
                               @Nullable BiFunction<T, ResourceManager, Pair<List<Palette>, @Nullable AnimationMetadataSection>> paletteSupplier) {
        super(name, prefix, null, baseBlock, baseType, tab, copyLoot, itemFactory, tileFactory, renderType, paletteSupplier);
        this.blockSupplier = blockSupplier;
        this.quarkModule = module;
    }

    public void texture(ResourceLocation main, ResourceLocation mask) {
        this.textures.add(Pair.of(main, mask));
    }

    public void recipe(ResourceLocation modRes) {
        this.recipeLocations.add(() -> modRes);
    }

    public void simpleRecipe() {
        this.recipeLocations.add(() -> this.baseBlock.get().getRegistryName());
    }

    public void tag(TagKey<B> key, ResourceKey<Registry<B>> registry) {
        var s = this.tags.computeIfAbsent(key.location(), b -> new HashSet<>());
        s.add(registry);
    }

    @Override
    public void generateTextures(CompatModule module, RPAwareDynamicTextureProvider handler, ResourceManager manager) {
        super.generateTextures(module, handler, manager);
    }

    @Override
    public void registerBlocks(CompatModule module, IForgeRegistry<Block> registry, Collection<T> woodTypes) {
        Block base = baseBlock.get();
        if (base == null)
            throw new UnsupportedOperationException("Base block cant be null");
        baseType.get().addChild(module.shortenedId() + "/" + baseName, base);

        for (T w : woodTypes) {
            String name = module.makeBlockId(w, this.name);
            if (w.isVanilla() || module.isEntryAlreadyRegistered(name, registry)) continue;
            var m = ModuleLoader.INSTANCE.getModuleInstance(quarkModule);
            B block = blockSupplier.apply(w, m);
            this.blocks.put(w, block);
            registry.register(block); //does not set registry name
            w.addChild(module.shortenedId() + "/" + baseName, block);
        }
    }


}