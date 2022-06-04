package net.mehvahdjukaar.every_compat.modules.quark;

import com.mojang.datafixers.util.Pair;
import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.modules.CompatModule;
import net.mehvahdjukaar.selene.block_set.BlockType;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.mehvahdjukaar.selene.block_set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.selene.client.asset_generators.textures.Palette;
import net.mehvahdjukaar.selene.client.asset_generators.textures.TextureImage;
import net.mehvahdjukaar.selene.resourcepack.AfterLanguageLoadEvent;
import net.mehvahdjukaar.selene.resourcepack.RPAwareDynamicTextureProvider;
import net.mehvahdjukaar.selene.resourcepack.RPUtils;
import net.mehvahdjukaar.selene.resourcepack.resources.TagBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.resources.metadata.animation.AnimationMetadataSection;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.commons.lang3.function.TriFunction;
import org.jetbrains.annotations.Nullable;
import vazkii.arl.util.RegistryHelper;
import vazkii.quark.base.handler.ToolInteractionHandler;
import vazkii.quark.base.module.ModuleLoader;
import vazkii.quark.content.building.block.VariantBookshelfBlock;
import vazkii.quark.content.building.block.WoodPostBlock;
import vazkii.quark.content.building.module.VariantBookshelvesModule;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public class QuarkModule extends SimpleModule {

    public final QuarkSimpleEntrySet<WoodType, Block> BOOKSHELVES;
    public final QuarkSimpleEntrySet<WoodType, Block> POSTS;
    public final QuarkSimpleEntrySet<WoodType, Block> STRIPPED_POSTS;

    public QuarkModule(String modId) {
        super(modId, "q");

        BOOKSHELVES = new QuarkSimpleEntrySet<>(
                "bookshelf",
                VariantBookshelvesModule.class,
                () -> ForgeRegistries.BLOCKS.getValue(modRes("acacia_bookshelf")),
                () -> WoodTypeRegistry.WOOD_TYPES.get(new ResourceLocation("acacia")),
                (w, m) -> new VariantBookshelfBlock(shortenedId() + "/" + w.getAppendableId(), m, w.canBurn()),
                CreativeModeTab.TAB_DECORATIONS,
                true, null, null, null,
                this::bookshelfPalette
        );
        BOOKSHELVES.texture(WoodGood.res("block/acacia_bookshelf"), WoodGood.res("block/acacia_bookshelf_m"));
        BOOKSHELVES.recipe(modRes("building/crafting/acacia_bookshelf"));
        BOOKSHELVES.tag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY);
        this.addEntry(BOOKSHELVES);

        POSTS = new QuarkSimpleEntrySet<>(
                "post",
                VariantBookshelvesModule.class,
                () -> ForgeRegistries.BLOCKS.getValue(modRes("oak_post")),
                () -> WoodType.OAK_WOOD_TYPE,
                (w, m) -> {
                    Block fence = w.getBlockOfThis("fence");
                    return fence == null ? null :
                            new WoodPostBlock(m, fence, shortenedId() + "/" + w.getNamespace() + "/", w.canBurn());
                },
                CreativeModeTab.TAB_DECORATIONS,
                false, null, null,
                () -> RenderType::cutout, null
        );
        POSTS.recipe(modRes("building/crafting/oak_post"));
        this.addEntry(POSTS);

        STRIPPED_POSTS = new QuarkSimpleEntrySet<>(
                "post", "stripped",
                VariantBookshelvesModule.class,
                () -> ForgeRegistries.BLOCKS.getValue(modRes("stripped_oak_post")),
                () -> WoodType.OAK_WOOD_TYPE,
                (w, m) -> {
                    Block fence = w.getBlockOfThis("fence");
                    return fence == null ? null :
                            new WoodPostBlock(m, fence, shortenedId() + "/" + w.getNamespace() + "/stripped_", w.canBurn());
                },
                CreativeModeTab.TAB_DECORATIONS,
                false, null, null,
                () -> RenderType::cutout, null
        );
        POSTS.recipe(modRes("building/crafting/stripped_oak_post"));
        this.addEntry(POSTS);
    }

    public Pair<List<Palette>, AnimationMetadataSection> bookshelfPalette(BlockType w, ResourceManager m) {
        try (TextureImage plankTexture = TextureImage.open(m,
                RPUtils.findFirstBlockTextureLocation(m, ((WoodType) w).planks))) {

            List<Palette> targetPalette = Palette.fromAnimatedImage(plankTexture);
            targetPalette.forEach(p -> {
                var l0 = p.getDarkest();
                p.increaseDown();
                p.increaseDown();
                p.increaseDown();
                p.increaseDown();
                p.remove(l0);
            });
            return Pair.of(targetPalette, plankTexture.getMetadata());
        } catch (Exception e) {
            throw new RuntimeException(String.format("Failed to generate palette for %s : %s", w, e));
        }
    }

    @Override
    public void onModSetup() {
        POSTS.blocks.forEach((w, post) -> {
            Block stripped = STRIPPED_POSTS.blocks.get(w);
            if (stripped != null) ToolInteractionHandler.registerInteraction(ToolActions.AXE_STRIP, post, stripped);
        });
    }

    @Override
    public void addStaticClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        super.addStaticClientResources(handler, manager);
    }

    @Override
    public void addTranslations(ClientDynamicResourcesHandler clientDynamicResourcesHandler, AfterLanguageLoadEvent lang) {
        super.addTranslations(clientDynamicResourcesHandler, lang);
    }

    @Override
    public void registerWoodBlocks(IForgeRegistry<Block> registry, Collection<WoodType> woodTypes) {
        //HAAAACK
        //removes stuff from autoreglib since it's too late to let it register these and we are registering them manually
        Map<String, ?> ARLModData;
        try {
            Field f = ObfuscationReflectionHelper.findField(RegistryHelper.class, "modData");
            f.setAccessible(true);
            ARLModData = (Map<String, ?>) f.get(null);
        } catch (Exception e) {
            WoodGood.LOGGER.error("Failed to setup Wood Good Quark Module");
            return;
        }

        super.registerWoodBlocks(registry, woodTypes);


        ARLModData.remove(WoodGood.MOD_ID);
    }



}
