package net.mehvahdjukaar.every_compat.modules.quark;

import com.mojang.datafixers.util.Pair;
import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.selene.block_set.BlockType;
import net.mehvahdjukaar.selene.block_set.leaves.LeavesType;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.mehvahdjukaar.selene.block_set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.selene.client.asset_generators.textures.Palette;
import net.mehvahdjukaar.selene.client.asset_generators.textures.TextureImage;
import net.mehvahdjukaar.selene.resourcepack.RPUtils;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.resources.metadata.animation.AnimationMetadataSection;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;
import net.minecraftforge.registries.IForgeRegistry;
import vazkii.arl.util.RegistryHelper;
import vazkii.quark.base.block.QuarkBlock;
import vazkii.quark.base.handler.ToolInteractionHandler;
import vazkii.quark.base.module.ModuleLoader;
import vazkii.quark.content.building.block.HedgeBlock;
import vazkii.quark.content.building.block.VariantBookshelfBlock;
import vazkii.quark.content.building.block.VariantLadderBlock;
import vazkii.quark.content.building.block.WoodPostBlock;
import vazkii.quark.content.building.module.HedgesModule;
import vazkii.quark.content.building.module.VariantBookshelvesModule;
import vazkii.quark.content.building.module.VariantLaddersModule;
import vazkii.quark.content.building.module.VerticalPlanksModule;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class QuarkModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> BOOKSHELVES;
    public final SimpleEntrySet<WoodType, Block> POSTS;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_POSTS;
    public final SimpleEntrySet<WoodType, Block> VERTICAL_PLANKS;
    public final SimpleEntrySet<WoodType, Block> LADDERS;
    public final SimpleEntrySet<LeavesType, Block> HEDGES;

    public QuarkModule(String modId) {
        super(modId, "q");

        BOOKSHELVES = QuarkSimpleEntrySet.builder("bookshelf",
                        VariantBookshelvesModule.class,
                        () -> ForgeRegistries.BLOCKS.getValue(modRes("acacia_bookshelf")),
                        () -> WoodTypeRegistry.WOOD_TYPES.get(new ResourceLocation("acacia")),
                        (w, m) -> new VariantBookshelfBlock(shortenedId() + "/" + w.getAppendableId(), m, w.canBurn()))
                .setTab(CreativeModeTab.TAB_DECORATIONS)
                .useLootFromBase()
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(Tags.Items.BOOKSHELVES, Registry.BLOCK_REGISTRY)
                .addTag(Tags.Items.BOOKSHELVES, Registry.ITEM_REGISTRY)
                .addRecipe(modRes("building/crafting/acacia_bookshelf"))
                .addTextureM(WoodGood.res("block/acacia_bookshelf"), WoodGood.res("block/acacia_bookshelf_m"))
                .setPalette(this::bookshelfPalette)
                .build();

        this.addEntry(BOOKSHELVES);

        POSTS = QuarkSimpleEntrySet.builder("post",
                        VariantBookshelvesModule.class,
                        () -> ForgeRegistries.BLOCKS.getValue(modRes("oak_post")),
                        () -> WoodType.OAK_WOOD_TYPE,
                        (w, m) -> {
                            Block fence = w.getBlockOfThis("fence");
                            return fence == null ? null :
                                    new WoodPostBlock(m, fence, shortenedId() + "/" + w.getNamespace() + "/", w.canBurn());
                        })
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("posts"), Registry.BLOCK_REGISTRY)
                .setTab(CreativeModeTab.TAB_DECORATIONS)
                .addRecipe(modRes("building/crafting/oak_post"))
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(POSTS);

        STRIPPED_POSTS = QuarkSimpleEntrySet.builder("post", "stripped",
                VariantBookshelvesModule.class,
                () -> ForgeRegistries.BLOCKS.getValue(modRes("stripped_oak_post")),
                () -> WoodType.OAK_WOOD_TYPE,
                (w, m) -> {
                    if (w.getTypeName().contains("stripped")) return null;
                    Block fence = w.getBlockOfThis("fence");
                    Block stripped = w.getBlockOfThis("stripped_log");
                    return (fence == null || stripped == null) ? null :
                            new WoodPostBlock(m, fence, shortenedId() + "/" + w.getNamespace() + "/stripped_", w.canBurn());
                })
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("posts"), Registry.BLOCK_REGISTRY)
                .setTab(CreativeModeTab.TAB_DECORATIONS)
                .addRecipe(modRes("building/crafting/stripped_oak_post"))
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(STRIPPED_POSTS);

        VERTICAL_PLANKS = QuarkSimpleEntrySet.builder("planks", "vertical",
                VerticalPlanksModule.class,
                () -> ForgeRegistries.BLOCKS.getValue(modRes("vertical_oak_planks")),
                () -> WoodType.OAK_WOOD_TYPE,
                (w, m) -> {
                    String name = shortenedId() + "/" + w.getVariantId("planks", "vertical");
                    return new QuarkBlock(name, m, CreativeModeTab.TAB_BUILDING_BLOCKS, BlockBehaviour.Properties.copy(w.planks));
                })
                .setTab(CreativeModeTab.TAB_BUILDING_BLOCKS)
                .addRecipe(modRes("building/crafting/vertplanks/vertical_oak_planks"))
                .build();

        this.addEntry(VERTICAL_PLANKS);

        LADDERS = QuarkSimpleEntrySet.builder("ladder",
                        VariantLaddersModule.class,
                        () -> ForgeRegistries.BLOCKS.getValue(modRes("spruce_ladder")),
                        () -> WoodTypeRegistry.WOOD_TYPES.get(new ResourceLocation("spruce")),
                        (w, m) -> {
                            String name = shortenedId() + "/" + w.getTypeName();
                            return new VariantLadderBlock(name, m, BlockBehaviour.Properties.copy(w.planks), w.canBurn());
                        })
                .setTab(CreativeModeTab.TAB_BUILDING_BLOCKS)
                .addTag(modRes("ladders"),Registry.BLOCK_REGISTRY)
                .addTag(modRes("ladders"),Registry.ITEM_REGISTRY)
                .addRecipe(modRes("building/crafting/ladders/spruce_ladder"))
                .addTexture(modRes("block/spruce_ladder"))
                .build();

        this.addEntry(LADDERS);


        HEDGES = QuarkSimpleEntrySet.builder("hedge",
                HedgesModule.class,
                () -> ForgeRegistries.BLOCKS.getValue(modRes("oak_hedge")),
                () -> LeavesType.OAK_LEAVES_TYPE,
                (l, m) -> null)//this wont run
                .addTag(modRes("hedges"),Registry.BLOCK_REGISTRY)
                .addTag(modRes("hedges"),Registry.ITEM_REGISTRY)
                .setTab( CreativeModeTab.TAB_BUILDING_BLOCKS)
                .addRecipe(modRes("building/crafting/oak_hedge"))
                .setRenderType(()->RenderType::cutout)
                .build();

        this.addEntry(HEDGES);
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

    @Override
    public void registerLeavesBlocks(IForgeRegistry<Block> registry, Collection<LeavesType> leavesTypes) {
        //HAAAACK
        //removes stuff from autoreglib since it's too late to let it register these and we are registering them manually
        Field regName;
        Map<String, ?> ARLModData;
        try {
            Field f = ObfuscationReflectionHelper.findField(RegistryHelper.class, "modData");
            f.setAccessible(true);
            ARLModData = (Map<String, ?>) f.get(null);

            regName = ObfuscationReflectionHelper.findField(ForgeRegistryEntry.class, "registryName");
            regName.setAccessible(true);
        } catch (Exception e) {
            WoodGood.LOGGER.error("Failed to setup Wood Good Quark Module");
            return;
        }
        //hedges
        LeavesType.OAK_LEAVES_TYPE.addChild(shortenedId() + "/hedge", ForgeRegistries.BLOCKS.getValue(modRes("oak_hedge")));
        for (LeavesType l : leavesTypes) {
            String name = makeBlockId(l, "hedge");
            if (l.isVanilla() || isEntryAlreadyRegistered(name, l,registry)) continue;
            if (l.woodType != null) {
                Block fence = l.woodType.getBlockOfThis("fence");
                if (fence != null) {
                    var module = ModuleLoader.INSTANCE.getModuleInstance(HedgesModule.class);

                    Block block = new HedgeBlock(module, fence, l.leaves);
                    try {
                        regName.set(block, WoodGood.res(name)); //wack
                        HEDGES.blocks.put(l, block);
                        registry.register(block);
                        l.addChild(shortenedId() + "/hedge", block);

                    } catch (Exception e) {
                        throw new UnsupportedOperationException(String.format("Failed to set registry name for %s hedge", l));
                    }
                }
            }
        }

        ARLModData.remove(WoodGood.MOD_ID);
    }


    @Override
    public void registerColors(ColorHandlerEvent.Item event) {
        HEDGES.blocks.forEach((l, h) -> {
            ItemColors colors = event.getItemColors();
            ItemStack leafStack = new ItemStack(l.leaves);
            colors.register((stack, tintIndex) -> colors.getColor(leafStack, tintIndex), h.asItem());
        });
    }
}
