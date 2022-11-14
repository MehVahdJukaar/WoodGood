package net.mehvahdjukaar.every_compat.modules.quark;

import com.mojang.datafixers.util.Pair;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.misc.EventCalled;
import net.mehvahdjukaar.moonlight.api.misc.Registrator;
import net.mehvahdjukaar.moonlight.api.platform.ClientPlatformHelper;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.resources.textures.Respriter;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesType;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesTypeRegistry;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.moonlight.api.util.math.colors.HCLColor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.resources.metadata.animation.AnimationMetadataSection;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import vazkii.arl.util.RegistryHelper;
import vazkii.quark.base.block.QuarkBlock;
import vazkii.quark.base.handler.ToolInteractionHandler;
import vazkii.quark.content.building.block.*;
import vazkii.quark.content.building.client.render.be.VariantChestRenderer;
import vazkii.quark.content.building.module.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class QuarkModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> BOOKSHELVES;
    public final SimpleEntrySet<WoodType, Block> POSTS;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_POSTS;
    public final SimpleEntrySet<WoodType, Block> VERTICAL_PLANKS;
    public final SimpleEntrySet<WoodType, Block> VERTICAL_SLABS;
    public final SimpleEntrySet<WoodType, Block> LADDERS;
    public final SimpleEntrySet<WoodType, ? extends VariantChestBlock> CHESTS;
    public final SimpleEntrySet<WoodType, ? extends VariantTrappedChestBlock> TRAPPED_CHESTS;
    public final SimpleEntrySet<LeavesType, Block> HEDGES;
    public final SimpleEntrySet<LeavesType, Block> LEAF_CARPETS;

    public static BlockEntityType<? extends ChestBlockEntity> CHEST_TILE;
    public static BlockEntityType<? extends ChestBlockEntity> TRAPPED_CHEST_TILE;

    public final Field ARLModData;

    public QuarkModule(String modId) {
        super(modId, "q");

        Field f2 = null;
        try {
            f2 = ObfuscationReflectionHelper.findField(RegistryHelper.class, "modData");
            f2.setAccessible(true);
        } catch (Exception e) {
            EveryCompat.LOGGER.error("Failed to initialize {}: {}", this, e);
        }
        ARLModData = f2;

        BOOKSHELVES = QuarkSimpleEntrySet.builder(WoodType.class, "bookshelf",
                        VariantBookshelvesModule.class,
                        () -> getModBlock("acacia_bookshelf"),
                        () -> WoodTypeRegistry.getValue(new ResourceLocation("acacia")),
                        (w, m) -> new VariantBookshelfBlock(shortenedId() + "/" + w.getAppendableId(), m, w.canBurn()))
                .setTab(() -> CreativeModeTab.TAB_DECORATIONS)
                .useLootFromBase()
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(Tags.Items.BOOKSHELVES, Registry.BLOCK_REGISTRY)
                .addTag(Tags.Items.BOOKSHELVES, Registry.ITEM_REGISTRY)
                .addRecipe(modRes("building/crafting/acacia_bookshelf"))
                .addTextureM(EveryCompat.res("block/acacia_bookshelf"), EveryCompat.res("block/acacia_bookshelf_m"))
                .setPalette(this::bookshelfPalette)
                .build();

        this.addEntry(BOOKSHELVES);

        POSTS = QuarkSimpleEntrySet.builder(WoodType.class, "post",
                        VariantBookshelvesModule.class,
                        () -> getModBlock("oak_post"),
                        () -> WoodTypeRegistry.OAK_TYPE,
                        (w, m) -> {
                            if (w.getNamespace().equals("malum")) return null;
                            Block fence = w.getBlockOfThis("fence");
                            return fence == null ? null :
                                    new WoodPostBlock(m, fence, shortenedId() + "/" + w.getNamespace() + "/", w.canBurn());
                        })
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("posts"), Registry.BLOCK_REGISTRY)
                .setTab(() -> CreativeModeTab.TAB_DECORATIONS)
                .addRecipe(modRes("building/crafting/oak_post"))
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(POSTS);

        STRIPPED_POSTS = QuarkSimpleEntrySet.builder(WoodType.class, "post", "stripped",
                        VariantBookshelvesModule.class,
                        () -> getModBlock("stripped_oak_post"),
                        () -> WoodTypeRegistry.OAK_TYPE,
                        (w, m) -> {
                            if (w.getNamespace().equals("malum") || w.getNamespace().equals("twigs")) return null;
                            Block fence = w.getBlockOfThis("fence");
                            Block stripped = w.getBlockOfThis("stripped_log");
                            return (fence == null || stripped == null) ? null :
                                    new WoodPostBlock(m, fence, shortenedId() + "/" + w.getNamespace() + "/stripped_", w.canBurn());
                        })
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("posts"), Registry.BLOCK_REGISTRY)
                .setTab(() -> CreativeModeTab.TAB_DECORATIONS)
                .addRecipe(modRes("building/crafting/stripped_oak_post"))
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(STRIPPED_POSTS);

        VERTICAL_PLANKS = QuarkSimpleEntrySet.builder(WoodType.class, "planks", "vertical",
                        VerticalPlanksModule.class,
                        () -> getModBlock("vertical_oak_planks"),
                        () -> WoodTypeRegistry.OAK_TYPE,
                        (w, m) -> {
                            String name = shortenedId() + "/" + w.getVariantId("planks", "vertical");
                            return new QuarkBlock(name, m, CreativeModeTab.TAB_BUILDING_BLOCKS, Utils.copyPropertySafe(w.planks));
                        })
                .setTab(() -> CreativeModeTab.TAB_BUILDING_BLOCKS)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addRecipe(modRes("building/crafting/vertplanks/vertical_oak_planks"))
                .build();

        this.addEntry(VERTICAL_PLANKS);

        VERTICAL_SLABS = QuarkSimpleEntrySet.builder(WoodType.class, "vertical_slab",
                        VerticalSlabsModule.class,
                        () -> getModBlock("oak_vertical_slab"),
                        () -> WoodTypeRegistry.OAK_TYPE,
                        (w, m) -> {
                            Block slab = w.getBlockOfThis("slab");
                            return (slab == null) ? null : new VerticalSlabBlock(slab, m);
                        })
                .setTab(() -> CreativeModeTab.TAB_BUILDING_BLOCKS)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("wooden_vertical_slabs"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("wooden_vertical_slabs"), Registry.ITEM_REGISTRY)
                .addRecipe(modRes("building/crafting/vertslabs/oak_vertical_slab"))
                .addRecipe(modRes("building/crafting/vertslabs/oak_vertical_slab_revert"))
                .build();

        this.addEntry(VERTICAL_SLABS);

        LADDERS = QuarkSimpleEntrySet.builder(WoodType.class, "ladder",
                        VariantLaddersModule.class,
                        () -> getModBlock("spruce_ladder"),
                        () -> WoodTypeRegistry.getValue(new ResourceLocation("spruce")),
                        (w, m) -> {
                            String name = shortenedId() + "/" + w.getAppendableId();
                            return new VariantLadderBlock(name, m, w.canBurn());
                        })
                .setTab(() -> CreativeModeTab.TAB_BUILDING_BLOCKS)
                .addTag(BlockTags.CLIMBABLE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("ladders"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("ladders"), Registry.ITEM_REGISTRY)
                .addRecipe(modRes("building/crafting/spruce_ladder"))
                .addTexture(EveryCompat.res("block/spruce_ladder"))
                .build();

        this.addEntry(LADDERS);

        CHESTS = QuarkSimpleEntrySet.builder(WoodType.class, "chest",
                        VariantChestsModule.class,
                        () -> getModBlock("oak_chest", VariantChestBlock.class),
                        () -> WoodTypeRegistry.OAK_TYPE,
                        (w, m) -> {
                            if (w.getId().toString().equals("twilightforest:dark")) return null;
                            String name = shortenedId() + "/" + w.getAppendableId();
                            return new CompatChestBlock(w, name, m, Utils.copyPropertySafe(w.planks));
                        })
                .setTab(() -> CreativeModeTab.TAB_BUILDING_BLOCKS)
                .addTag(Tags.Blocks.CHESTS_WOODEN, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(Tags.Blocks.CHESTS_WOODEN, Registry.ITEM_REGISTRY)
                .addTag(new ResourceLocation("quark:revertable_chests"), Registry.ITEM_REGISTRY)
                .addTile(CompatChestBlockTile::new)
                .addCustomItem((w, b, p) -> new VariantChestBlock.Item(b, p))
                .addRecipe(modRes("building/crafting/chests/oak_chest"))
                .build();

        this.addEntry(CHESTS);

        TRAPPED_CHESTS = QuarkSimpleEntrySet.builder(WoodType.class, "trapped_chest",
                        VariantChestsModule.class,
                        () -> getModBlock("oak_trapped_chest", VariantTrappedChestBlock.class),
                        () -> WoodTypeRegistry.OAK_TYPE,
                        (w, m) -> {
                            if (!CHESTS.blocks.containsKey(w)) return null;
                            String name = shortenedId() + "/" + w.getAppendableId();
                            return new CompatTrappedChestBlock(w, name, m, Utils.copyPropertySafe(w.planks));
                        })
                .addCustomItem((w, b, p) -> new VariantChestBlock.Item(b, p))
                .setTab(() -> CreativeModeTab.TAB_BUILDING_BLOCKS)
                .addTag(Tags.Blocks.CHESTS_TRAPPED, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(Tags.Blocks.CHESTS_TRAPPED, Registry.ITEM_REGISTRY)
                .addTile(CompatTrappedChestBlockTile::new)

                .addRecipe(modRes("building/crafting/chests/oak_trapped_chest"))
                .build();

        this.addEntry(TRAPPED_CHESTS);


        //doing it this way because for some reason its nuking whatever block item I throw in here
        HEDGES = QuarkSimpleEntrySet.builder(LeavesType.class, "hedge",
                        HedgesModule.class,
                        () -> getModBlock("oak_hedge"),
                        () -> LeavesTypeRegistry.OAK_TYPE,
                        (w, m) -> {
                            if (w.getWoodType() == null) return null;
                            return new HedgeBlock(m, Blocks.OAK_FENCE, w.leaves);
                        })
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("hedges"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("hedges"), Registry.ITEM_REGISTRY)
                .setTab(() -> CreativeModeTab.TAB_BUILDING_BLOCKS)
                .addRecipe(modRes("building/crafting/oak_hedge"))
                .setRenderType(() -> RenderType::cutout)
                .build();
        this.addEntry(HEDGES);


        //doing it this way because for some reason its nuking whatever block item I throw in here
        LEAF_CARPETS = QuarkSimpleEntrySet.builder(LeavesType.class, "leaf_carpet",
                        LeafCarpetModule.class,
                        () -> getModBlock("oak_leaf_carpet"),
                        () -> LeavesTypeRegistry.OAK_TYPE,
                        (w, m) -> {
                            String name = shortenedId() + "/" + w.getVariantId("%s_leaf_carpet");
                            return new LeafCarpetBlock(name, w.leaves, m);
                        })
                .addTag(modRes("leaf_carpets"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("leaf_carpets"), Registry.ITEM_REGISTRY)
                .setTab(() -> CreativeModeTab.TAB_DECORATIONS)
                .addRecipe(modRes("building/crafting/oak_leaf_carpet"))
                .setRenderType(() -> RenderType::cutout)
                .build();
        this.addEntry(LEAF_CARPETS);

    }

    @Override
    public void onModSetup() {
        POSTS.blocks.forEach((w, post) -> {
            Block stripped = STRIPPED_POSTS.blocks.get(w);
            if (stripped != null) ToolInteractionHandler.registerInteraction(ToolActions.AXE_STRIP, post, stripped);
        });
        LEAF_CARPETS.blocks.forEach((w, leaf) -> {
            ComposterBlock.COMPOSTABLES.put(leaf, 0.2F);
        });
    }

    @Override
    public void registerWoodBlocks(Registrator<Block> registry, Collection<WoodType> woodTypes) {
        try { //ARL hax
            var data = (Map<String, ?>) ARLModData.get(null);
            super.registerWoodBlocks(registry, woodTypes);
            data.remove(EveryCompat.MOD_ID);
        } catch (IllegalAccessException e) {
            EveryCompat.LOGGER.error("Failed to onCommonSetup Wood Good Quark Module");
        }
    }

    @Override
    public void registerLeavesBlocks(Registrator<Block> registry, Collection<LeavesType> leavesTypes) {
        try { //ARL hax
            var data = (Map<String, ?>) ARLModData.get(null);
            super.registerLeavesBlocks(registry, leavesTypes);
            data.remove(EveryCompat.MOD_ID);
        } catch (IllegalAccessException e) {
            EveryCompat.LOGGER.error("Failed to onCommonSetup Wood Good Quark Module");
        }
    }

    @Override
    public void registerTiles(Registrator<BlockEntityType<?>> registry) {
        super.registerTiles(registry);
        CHEST_TILE = (BlockEntityType<? extends ChestBlockEntity>) CHESTS.getTileHolder().tile;
        TRAPPED_CHEST_TILE = (BlockEntityType<? extends ChestBlockEntity>) TRAPPED_CHESTS.getTileHolder().tile;
    }


    public void onFirstClientTick1() {
        var ic = Minecraft.getInstance().getItemColors();
        var bc = Minecraft.getInstance().getBlockColors();
        HEDGES.blocks.forEach((t, b) -> {
            var leaf = t.getChild("leaves");
            if (leaf instanceof Block block) {
                bc.register((s, l, p, i) -> bc.getColor(block.defaultBlockState(), l, p, i), b);
                ic.register((stack, tintIndex) -> ic.getColor(new ItemStack(block.asItem()), tintIndex), b.asItem());
            }
        });
    }

    @Override
    public void registerBlockColors(ClientPlatformHelper.BlockColorEvent event) {
        super.registerBlockColors(event);
    }

    @Override
    public void registerItemColors(ClientPlatformHelper.ItemColorEvent event) {
        HEDGES.blocks.forEach((t, b) -> {
            event.register((stack, tintIndex) -> event.getColor(new ItemStack(t.leaves), tintIndex), b.asItem());
            //blockColor.register((s, l, p, i) -> blockColor.getColor(bl.defaultBlockState(), l, p, i), b);
        });
    }

    @Override
    public void registerBlockEntityRenderers(ClientPlatformHelper.BlockEntityRendererEvent event) {
        super.registerBlockEntityRenderers(event);
        event.register(CHEST_TILE, VariantChestRenderer::new);
        event.register(TRAPPED_CHEST_TILE, VariantChestRenderer::new);
    }

    @Override
    public void onClientInit() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onTextureStitch);
    }

    private static final ResourceLocation CHEST_SHEET = new ResourceLocation("textures/atlas/chest.png");

    @EventCalled
    public void onTextureStitch(TextureStitchEvent.Pre event) {
        if (event.getAtlas().location().equals(CHEST_SHEET)) {
            CHESTS.blocks.values().forEach(c -> VariantChestRenderer.accept(event, c));
            TRAPPED_CHESTS.blocks.values().forEach(c -> VariantChestRenderer.accept(event, c));
        }

    }

    private Pair<List<Palette>, AnimationMetadataSection> bookshelfPalette(BlockType w, ResourceManager m) {
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
    public void addDynamicClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicClientResources(handler, manager);

        try (TextureImage normal = TextureImage.open(manager, modRes("model/chest/oak/normal"));
             TextureImage normal_m = TextureImage.open(manager, EveryCompat.res("model/oak_chest_normal_m"));
             TextureImage normal_o = TextureImage.open(manager, EveryCompat.res("model/oak_chest_normal_o"));
             TextureImage left = TextureImage.open(manager, modRes("model/chest/oak/left"));
             TextureImage left_m = TextureImage.open(manager, EveryCompat.res("model/oak_chest_left_m"));
             TextureImage left_o = TextureImage.open(manager, EveryCompat.res("model/oak_chest_left_o"));
             TextureImage right = TextureImage.open(manager, modRes("model/chest/oak/right"));
             TextureImage right_m = TextureImage.open(manager, EveryCompat.res("model/oak_chest_right_m"));
             TextureImage right_o = TextureImage.open(manager, EveryCompat.res("model/oak_chest_right_o"));
             TextureImage left_t = TextureImage.open(manager, EveryCompat.res("model/trapped_chest_left"));
             TextureImage right_t = TextureImage.open(manager, EveryCompat.res("model/trapped_chest_right"));
             TextureImage normal_t = TextureImage.open(manager, EveryCompat.res("model/trapped_chest_normal"))
        ) {

            Respriter respriterNormal = Respriter.masked(normal, normal_m);
            Respriter respriterLeft = Respriter.masked(left, left_m);
            Respriter respriterRight = Respriter.masked(right, right_m);

            Respriter respriterNormalO = Respriter.of(normal_o);
            Respriter respriterLeftO = Respriter.of(left_o);
            Respriter respriterRightO = Respriter.of(right_o);

            CHESTS.blocks.forEach((wood, block) -> {

                CompatChestBlock b = (CompatChestBlock) block;

                try (TextureImage plankTexture = TextureImage.open(manager,
                        RPUtils.findFirstBlockTextureLocation(manager, wood.planks))) {

                    List<Palette> targetPalette = Palette.fromAnimatedImage(plankTexture);

                    List<Palette> overlayPalette = new ArrayList<>();
                    for (var p : targetPalette) {
                        var d1 = p.getDarkest();
                        p.remove(d1);
                        var d2 = p.getDarkest();
                        p.remove(d2);
                        var n1 = new HCLColor(d1.hcl().hue(), d1.hcl().chroma() * 0.75f, d1.hcl().luminance() * 0.4f, d1.hcl().alpha());
                        var n2 = new HCLColor(d2.hcl().hue(), d2.hcl().chroma() * 0.75f, d2.hcl().luminance() * 0.6f, d2.hcl().alpha());
                        var pal = Palette.ofColors(List.of(n1, n2));
                        overlayPalette.add(pal);
                    }

                    {
                        ResourceLocation res = modRes(b.getChestTexturePath() + "normal");
                        if (!handler.alreadyHasTextureAtLocation(manager, res)) {
                            ResourceLocation trappedRes = modRes(b.getChestTexturePath() + "trap");

                            var img = respriterNormal.recolorWithAnimation(targetPalette, plankTexture.getMetadata());
                            img.applyOverlayOnExisting(respriterNormalO.recolorWithAnimation(overlayPalette, plankTexture.getMetadata()));

                            var trapped = img.makeCopy();

                            trapped.applyOverlayOnExisting(normal_t.makeCopy());

                            handler.dynamicPack.addAndCloseTexture(res, img);
                            handler.dynamicPack.addAndCloseTexture(trappedRes, trapped);
                        }
                    }
                    {
                        ResourceLocation res = modRes(b.getChestTexturePath() + "left");
                        if (!handler.alreadyHasTextureAtLocation(manager, res)) {
                            ResourceLocation trappedRes = modRes(b.getChestTexturePath() + "trap_left");

                            var img = respriterLeft.recolorWithAnimation(targetPalette, plankTexture.getMetadata());
                            img.applyOverlayOnExisting(respriterLeftO.recolorWithAnimation(overlayPalette, plankTexture.getMetadata()));

                            var trapped = img.makeCopy();
                            trapped.applyOverlayOnExisting(left_t.makeCopy());

                            handler.dynamicPack.addAndCloseTexture(res, img);
                            handler.dynamicPack.addAndCloseTexture(trappedRes, trapped);
                        }
                    }
                    {
                        ResourceLocation res = modRes(b.getChestTexturePath() + "right");
                        if (!handler.alreadyHasTextureAtLocation(manager, res)) {
                            ResourceLocation trappedRes = modRes(b.getChestTexturePath() + "trap_right");

                            var img = respriterRight.recolorWithAnimation(targetPalette, plankTexture.getMetadata());
                            img.applyOverlayOnExisting(respriterRightO.recolorWithAnimation(overlayPalette, plankTexture.getMetadata()));

                            var trapped = img.makeCopy();
                            trapped.applyOverlayOnExisting(right_t.makeCopy());

                            handler.dynamicPack.addAndCloseTexture(res, img);
                            handler.dynamicPack.addAndCloseTexture(trappedRes, trapped);
                        }
                    }


                } catch (Exception ex) {
                    handler.getLogger().error("Failed to generate Chest block texture for for {} : {}", b, ex);
                }
            });
        } catch (Exception ex) {
            handler.getLogger().error("Could not generate any Chest block texture : ", ex);
        }
    }
}
