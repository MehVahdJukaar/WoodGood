package net.mehvahdjukaar.every_compat.modules.forge.quark;

import com.mojang.datafixers.util.Pair;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.common_classes.CompatChestTexture;
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
import net.minecraft.world.level.block.SoundType;
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
import java.util.*;

//SUPPORT: v3.4-418+
public class QuarkModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> bookshelves;
    public final SimpleEntrySet<WoodType, Block> posts;
    public final SimpleEntrySet<WoodType, Block> strippedPosts;
    public final SimpleEntrySet<WoodType, Block> verticalPlanks;
    public final SimpleEntrySet<WoodType, Block> ladders;
    public final SimpleEntrySet<WoodType, Block> hollowLogs;
    public final SimpleEntrySet<WoodType, ? extends VariantChestBlock> chests;
    public final SimpleEntrySet<WoodType, ? extends VariantTrappedChestBlock> trappedChests;
    public final SimpleEntrySet<LeavesType, Block> hedges;
    public final SimpleEntrySet<LeavesType, Block> leafCarpets;

    public static BlockEntityType<? extends ChestBlockEntity> chestTile;
    public static BlockEntityType<? extends ChestBlockEntity> trappedChestTile;

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

        bookshelves = QuarkSimpleEntrySet.builder(WoodType.class, "bookshelf",
                        VariantBookshelvesModule.class,
                        () -> getModBlock("acacia_bookshelf"),
                        () -> WoodTypeRegistry.getValue(new ResourceLocation("acacia")),
                        (w, m) -> new VariantBookshelfBlock(shortenedId() + "/" + w.getAppendableId(), m, w.canBurn()))
                .setTab(() -> CreativeModeTab.TAB_DECORATIONS)
                .copyParentDrop()
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(Tags.Items.BOOKSHELVES, Registry.BLOCK_REGISTRY)
                .addTag(Tags.Items.BOOKSHELVES, Registry.ITEM_REGISTRY)
                .addRecipe(modRes("building/crafting/acacia_bookshelf"))
                .addTextureM(EveryCompat.res("block/acacia_bookshelf"), EveryCompat.res("block/acacia_bookshelf_m"))
                .setPalette(this::bookshelfPalette)
                .build();
        this.addEntry(bookshelves);

        posts = QuarkSimpleEntrySet.builder(WoodType.class, "post",
                        WoodenPostsModule.class,
                        () -> getModBlock("oak_post"),
                        () -> WoodTypeRegistry.OAK_TYPE,
                        (w, m) -> {
                            if (w.getNamespace().equals("malum")) return null;
                            Block fence = w.getBlockOfThis("fence");
                            //noinspection deprecation
                            return fence == null ? null :
                                    new WoodPostBlock(m, fence, shortenedId() + "/" + w.getNamespace() + "/",
                                            fence.getSoundType(fence.defaultBlockState()) == SoundType.STEM);
                        })
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("posts"), Registry.BLOCK_REGISTRY)
                .setTab(() -> CreativeModeTab.TAB_DECORATIONS)
                .addRecipe(modRes("building/crafting/oak_post"))
                .setRenderType(() -> RenderType::cutout)
                .build();
        this.addEntry(posts);

        strippedPosts = QuarkSimpleEntrySet.builder(WoodType.class, "post", "stripped",
                        WoodenPostsModule.class,
                        () -> getModBlock("stripped_oak_post"),
                        () -> WoodTypeRegistry.OAK_TYPE,
                        (w, m) -> {
                            if (w.getNamespace().equals("malum") || w.getNamespace().equals("twigs")) return null;
                            Block fence = w.getBlockOfThis("fence");
                            Block stripped = w.getBlockOfThis("stripped_log");
                            //noinspection deprecation
                            return (fence == null || stripped == null) ? null :
                                    new WoodPostBlock(m, fence, shortenedId() + "/" + w.getNamespace() + "/stripped_",
                                            fence.getSoundType(fence.defaultBlockState()) == SoundType.STEM);
                        })
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("posts"), Registry.BLOCK_REGISTRY)
                .setTab(() -> CreativeModeTab.TAB_DECORATIONS)
                .addRecipe(modRes("building/crafting/stripped_oak_post"))
                .setRenderType(() -> RenderType::cutout)
                .build();
        this.addEntry(strippedPosts);

        verticalPlanks = QuarkSimpleEntrySet.builder(WoodType.class, "planks", "vertical",
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
        this.addEntry(verticalPlanks);

        ladders = QuarkSimpleEntrySet.builder(WoodType.class, "ladder",
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
                .setRenderType(() -> RenderType::translucent)
                .build();
        this.addEntry(ladders);

        hollowLogs = QuarkSimpleEntrySet.builder(WoodType.class, "log", "hollow",
                    HollowLogsModule.class,
                    () -> getModBlock("hollow_oak_log"),
                    () -> WoodTypeRegistry.OAK_TYPE,
                    (w, m) -> {
                        boolean whistlecane = ( w.getNamespace().equals("gardens_of_the_dead") && w.getTypeName().equals("whistlecane") );
                        if (w.getBlockOfThis("stripped_log") == null && !whistlecane) return null;

                        String name = shortenedId() + "/" + w.getAppendableId();
                        return new HollowLogBlock(name, w.log, m, w.canBurn());
                    }
                )
//                .requiresChildren("stripped_log") //TODO: fix blc it's not working
                .setTab(() -> CreativeModeTab.TAB_BUILDING_BLOCKS)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("hollow_logs"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("hollow_logs"), Registry.ITEM_REGISTRY)
                .addRecipe(modRes("building/crafting/hollowlogs/hollow_oak_log"))
                .addModelTransform(m -> m.addModifier((s, resLoc, w) -> {
                    String namespace = w.getNamespace();
                    String typeName = w.getTypeName();
                    if (namespace.equals("gardens_of_the_dead") && typeName.equals("whistlecane"))
                        return s.replace("\"minecraft:block/stripped_oak_log\"",
                                "\"gardens_of_the_dead:block/whistlecane_block\"");

                    return s;
                }))
                .build();
        this.addEntry(hollowLogs);

        chests = QuarkSimpleEntrySet.builder(WoodType.class, "chest",
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
                .addTag(Tags.Blocks.CHESTS_WOODEN, Registry.ITEM_REGISTRY)
                .addTag(modRes("revertable_chests"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(new ResourceLocation("quark:revertable_chests"), Registry.ITEM_REGISTRY)
                .addTile(CompatChestBlockTile::new)
                .addCustomItem((w, b, p) -> new VariantChestBlock.Item(b, p))
                .addRecipe(modRes("building/crafting/chests/oak_chest"))
                .build();
        this.addEntry(chests);

        trappedChests = QuarkSimpleEntrySet.builder(WoodType.class, "trapped_chest",
                        VariantChestsModule.class,
                        () -> getModBlock("oak_trapped_chest", VariantTrappedChestBlock.class),
                        () -> WoodTypeRegistry.OAK_TYPE,
                        (w, m) -> {
                            if (!chests.blocks.containsKey(w)) return null;
                            String name = shortenedId() + "/" + w.getAppendableId();
                            return new CompatTrappedChestBlock(w, name, m, Utils.copyPropertySafe(w.planks));
                        })
                .addCustomItem((w, b, p) -> new CompatChestBlock.Item(b, p))
                .setTab(() -> CreativeModeTab.TAB_BUILDING_BLOCKS)
                .addTag(Tags.Blocks.CHESTS_TRAPPED, Registry.BLOCK_REGISTRY)
                .addTag(Tags.Blocks.CHESTS_TRAPPED, Registry.ITEM_REGISTRY)
                .addTag(Tags.Blocks.CHESTS_WOODEN, Registry.ITEM_REGISTRY)
                .addTag(Tags.Blocks.CHESTS_WOODEN, Registry.BLOCK_REGISTRY)
                .addTag(modRes("revertable_trapped_chests"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTile(CompatTrappedChestBlockTile::new)
                .addRecipe(modRes("building/crafting/chests/oak_trapped_chest"))
                .build();
        this.addEntry(trappedChests);


        //doing it this way because for some reason its nuking whatever block item I throw in here
        hedges = QuarkSimpleEntrySet.builder(LeavesType.class, "hedge",
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
        this.addEntry(hedges);


        //doing it this way because for some reason its nuking whatever block item I throw in here
        leafCarpets = QuarkSimpleEntrySet.builder(LeavesType.class, "leaf_carpet",
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
        this.addEntry(leafCarpets);

    }

    @Override
    public void onModSetup() {
        posts.blocks.forEach((w, post) -> {
            Block stripped = strippedPosts.blocks.get(w);
            if (stripped != null) ToolInteractionHandler.registerInteraction(ToolActions.AXE_STRIP, post, stripped);
        });
        leafCarpets.blocks.forEach((w, leaf) -> {
            ComposterBlock.COMPOSTABLES.put(leaf, 0.2F);
        });
    }

    @Override
    @SuppressWarnings("unchecked")
    public void registerWoodBlocks(Registrator<Block> registry, Collection<WoodType> woodTypes) {
        try { //ARL hax
            var data = (Map<String, ?>) ARLModData.get(null);
            super.registerWoodBlocks(registry, woodTypes);
            data.remove(EveryCompat.MOD_ID);
        } catch (IllegalAccessException e) {
            EveryCompat.LOGGER.error("Failed to onCommonSetup Wood Good Quark Module: {0}", e);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void registerLeavesBlocks(Registrator<Block> registry, Collection<LeavesType> leavesTypes) {
        try { //ARL hax
            var data = (Map<String, ?>) ARLModData.get(null);
            super.registerLeavesBlocks(registry, leavesTypes);
            data.remove(EveryCompat.MOD_ID);
        } catch (IllegalAccessException e) {
            EveryCompat.LOGGER.error("Failed to onCommonSetup Wood Good Quark Module: {0}", e);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void registerTiles(Registrator<BlockEntityType<?>> registry) {
        super.registerTiles(registry);
        chestTile = (BlockEntityType<? extends ChestBlockEntity>) Objects.requireNonNull(chests.getTileHolder()).get();
        trappedChestTile = (BlockEntityType<? extends ChestBlockEntity>) Objects.requireNonNull(trappedChests.getTileHolder()).get();
    }

    @Override
    public void registerBlockColors(ClientPlatformHelper.BlockColorEvent event) {
        super.registerBlockColors(event);
        for (Map.Entry<LeavesType, Block> entry : hedges.blocks.entrySet()) {
            LeavesType t = entry.getKey();
            Block b = entry.getValue();
            String namespace = t.getNamespace();
            String typeName = t.getTypeName();
            if (namespace.equals("regions_unexplored")) {
                if (typeName.equals("flowering")) continue;
            } else if (namespace.equals("blue_skies")) {
                if (typeName.equals("cherry")) continue;
            }
            event.register((bs, l, p, i) -> event.getColor(t.leaves.defaultBlockState(), l, p, i), b);
        }
        for (Map.Entry<LeavesType, Block> entry : leafCarpets.blocks.entrySet()) {
            LeavesType t = entry.getKey();
            Block b = entry.getValue();
            String namespace = t.getNamespace();
            String typeName = t.getTypeName();
            if (namespace.equals("regions_unexplored")) {
                if (typeName.equals("flowering")) continue;
            } else if (namespace.equals("blue_skies")) {
                if (typeName.equals("cherry")) continue;
            }
            event.register((bs, l, p, i) -> event.getColor(t.leaves.defaultBlockState(), l, p, i), b);
        }
    }

    @Override
    public void registerItemColors(ClientPlatformHelper.ItemColorEvent event) {
        for (Map.Entry<LeavesType, Block> entry : hedges.blocks.entrySet()) {
            LeavesType t = entry.getKey();
            Block b = entry.getValue();
            String namespace = t.getNamespace();
            String typeName = t.getTypeName();
            if (namespace.equals("regions_unexplored")) {
                if (typeName.equals("flowering")) continue;
            } else if (namespace.equals("blue_skies")) {
                if (typeName.equals("cherry")) continue;
            }
            event.register((stack, tintIndex) -> event.getColor(new ItemStack(t.leaves), tintIndex), b.asItem());
        }

        for (Map.Entry<LeavesType, Block> entry : leafCarpets.blocks.entrySet()) {
            LeavesType t = entry.getKey();
            Block b = entry.getValue();
            String namespace = t.getNamespace();
            String typeName = t.getTypeName();
            if (namespace.equals("regions_unexplored")) {
                if (typeName.equals("flowering")) continue;
            } else if (namespace.equals("blue_skies")) {
                if (typeName.equals("cherry")) continue;
            }
            event.register((stack, tintIndex) -> event.getColor(new ItemStack(t.leaves), tintIndex), b.asItem());
        }
    }

    @Override
    public void registerBlockEntityRenderers(ClientPlatformHelper.BlockEntityRendererEvent event) {
        super.registerBlockEntityRenderers(event);
        event.register(chestTile, VariantChestRenderer::new);
        event.register(trappedChestTile, VariantChestRenderer::new);
    }

    @Override
    public void onClientInit() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onTextureStitch);
    }

    private static final ResourceLocation CHEST_SHEET = new ResourceLocation("textures/atlas/chest.png");

    @EventCalled
    public void onTextureStitch(TextureStitchEvent.Pre event) {
        if (event.getAtlas().location().equals(CHEST_SHEET)) {
            chests.blocks.values().forEach(c -> VariantChestRenderer.accept(event, c));
            trappedChests.blocks.values().forEach(c -> VariantChestRenderer.accept(event, c));
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


            trappedChests.blocks.forEach((wood, block) -> {

                CompatTrappedChestBlock b = (CompatTrappedChestBlock) block;

                    {
                        ResourceLocation res = modRes(b.getChestTexturePath() + "normal");
                        ResourceLocation trappedRes = modRes(b.getChestTexturePath() + "trap");

                        CompatChestTexture.generateChestTexture(handler, manager, wood, b, res, trappedRes,
                                modRes("model/chest/oak/normal"),
                                EveryCompat.res("model/oak_chest_normal_m"),
                                EveryCompat.res("model/oak_chest_normal_o"),
                                EveryCompat.res("model/trapped_chest_normal")
                        );

                    }
                    {
                        ResourceLocation res = modRes(b.getChestTexturePath() + "left");
                        if (!handler.alreadyHasTextureAtLocation(manager, res)) {
                            ResourceLocation trappedRes = modRes(b.getChestTexturePath() + "trap_left");

                            CompatChestTexture.generateChestTexture(handler, manager, wood, b, res, trappedRes,
                                    modRes("model/chest/oak/left"),
                                    EveryCompat.res("model/oak_chest_left_m"),
                                    EveryCompat.res("model/oak_chest_left_o"),
                                    EveryCompat.res("model/trapped_chest_left")
                            );
                        }
                    }
                    {
                        ResourceLocation res = modRes(b.getChestTexturePath() + "right");
                        if (!handler.alreadyHasTextureAtLocation(manager, res)) {
                            ResourceLocation trappedRes = modRes(b.getChestTexturePath() + "trap_right");

                            CompatChestTexture.generateChestTexture(handler, manager, wood, b, res, trappedRes,
                                    modRes("model/chest/oak/right"),
                                    EveryCompat.res("model/oak_chest_right_m"),
                                    EveryCompat.res("model/oak_chest_right_o"),
                                    EveryCompat.res("model/trapped_chest_right")
                            );
                        }
                    }
            });
    }

}
