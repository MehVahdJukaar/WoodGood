package net.mehvahdjukaar.every_compat.modules.quark;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.mehvahdjukaar.every_compat.ECPlatformStuff;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.*;
import net.mehvahdjukaar.every_compat.misc.CompatSpritesHelper;
import net.mehvahdjukaar.every_compat.modules.botanypots.BotanyPotsHelper;
import net.mehvahdjukaar.moonlight.api.misc.Registrator;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceSink;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.resources.textures.Respriter;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureOps;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesType;
import net.mehvahdjukaar.moonlight.api.set.leaves.VanillaLeavesTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.moonlight.api.util.math.colors.HCLColor;
import net.mehvahdjukaar.moonlight.core.misc.McMetaFile;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.violetmoon.quark.base.QuarkClient;
import org.violetmoon.quark.content.building.block.*;
import org.violetmoon.quark.content.building.client.render.be.VariantChestRenderer;
import org.violetmoon.quark.content.building.module.*;
import org.violetmoon.zeta.block.ZetaBlock;
import org.violetmoon.zeta.client.SimpleWithoutLevelRenderer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

import static net.mehvahdjukaar.every_compat.api.PaletteStrategies.registerCached;
import static net.mehvahdjukaar.every_compat.misc.UtilityTag.getATagOrCreateANew;
import static net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys.*;

//SUPPORT: v4.0-462+
public class QuarkModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> verticalSlabs;
    public final SimpleEntrySet<WoodType, Block> bookshelves;
    public final SimpleEntrySet<WoodType, Block> posts;
    public final SimpleEntrySet<WoodType, Block> strippedPosts;
    public final SimpleEntrySet<WoodType, Block> verticalPlanks;
    public final SimpleEntrySet<WoodType, Block> ladders;
    public final SimpleEntrySet<WoodType, Block> hollowLogs;
    public final SimpleEntrySet<WoodType, VariantChestBlock> chests;
    public final SimpleEntrySet<WoodType, VariantTrappedChestBlock> trappedChests;
    public final SimpleEntrySet<LeavesType, Block> hedges;
    public final SimpleEntrySet<LeavesType, Block> leafCarpets;

    public static BlockEntityType<ChestBlockEntity> CHEST_TILE;
    public static BlockEntityType<ChestBlockEntity> TRAPPED_CHEST_TILE;

    public QuarkModule(String modId) {
        super(modId, "q");
        ResourceKey<CreativeModeTab> tab = CreativeModeTabs.BUILDING_BLOCKS;

        verticalSlabs = QuarkSimpleEntrySet.builder(WoodType.class, "vertical_slab",
                        VerticalSlabsModule.class,
                        getModBlock("oak_vertical_slab"),
                        () -> VanillaWoodTypes.OAK,
                        w -> new VerticalSlabBlock(() -> w.getBlockOfThis(VanillaWoodChildKeys.SLAB),
                                Utils.copyPropertySafe(Objects.requireNonNull(w.getBlockOfThis(VanillaWoodChildKeys.SLAB)))
                                        .sound(w.getSound())
                        )
                )
                .requiresChildren(SLAB)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("wooden_vertical_slabs"), Registries.BLOCK, Registries.ITEM)
                .addTag(modRes("vertical_slabs"), Registries.BLOCK, Registries.ITEM)
                .addTag(modRes("vertical_slab"), Registries.BLOCK, Registries.ITEM)
                .setTabKey(tab)
                .setTabMode(TabAddMode.AFTER_SAME_WOOD)
                .addRecipe(modRes("building/crafting/vertslabs/oak_vertical_slab"))
                //RECIPES: See addDynamicServerResources for oak_vertical_slab_revert
                .addCondition(woodType -> !PlatHelper.isModLoaded("v_slab_compat"))
                .copyParentDrop()
                .build();
        this.addEntry(verticalSlabs);

        bookshelves = QuarkSimpleEntrySet.builder(WoodType.class, "bookshelf",
                        VariantBookshelvesModule.class,
                        getModBlock("acacia_bookshelf"),
                        () -> VanillaWoodTypes.ACACIA,
                        w -> new VariantBookshelfBlock(shortenedId() + "/" + w.getAppendableId(),
                                null, w.canBurn(), w.getSound())
                )
                .setTabKey(tab)
                .setTabMode(TabAddMode.AFTER_SAME_WOOD)
                .copyParentDrop()
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(new ResourceLocation("forge:bookshelves"), Registries.BLOCK, Registries.ITEM)
                .addRecipe(modRes("building/crafting/acacia_bookshelf"))
                .addTextureM(EveryCompat.res("block/acacia_bookshelf"),
                        EveryCompat.res("block/acacia_bookshelf_m"),
                        bookshelfPalette)
                .build();
        this.addEntry(bookshelves);

        posts = QuarkSimpleEntrySet.builder(WoodType.class, "post",
                        WoodenPostsModule.class,
                        getModBlock("oak_post"),
                        () -> VanillaWoodTypes.OAK,
                        w -> {
                            Block fence = w.getBlockOfThis("fence");
                            return new WoodPostBlock(null, Objects.requireNonNull(fence), shortenedId() + "/" + w.getNamespace() + "/",
                                    Objects.requireNonNull(fence.defaultBlockState().getSoundType()));
                        })
                .requiresChildren(FENCE, WOOD) //REASON: recipes
                //TEXTURES: log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("posts"), Registries.BLOCK, Registries.ITEM)
                .setTabKey(tab)
                .setTabMode(TabAddMode.AFTER_SAME_WOOD)
                .addRecipe(modRes("building/crafting/oak_post"))
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(posts);

        strippedPosts = QuarkSimpleEntrySet.builder(WoodType.class, "post", "stripped",
                        WoodenPostsModule.class,
                        getModBlock("stripped_oak_post"),
                        () -> VanillaWoodTypes.OAK,
                        w -> {
                            if (w.getNamespace().equals("malum") || w.getNamespace().equals("twigs")) return null;
                            Block fence = w.getBlockOfThis("fence");
                            // required stripped_log texture & fence as an ingredients
                            return new WoodPostBlock(null, Objects.requireNonNull(fence), shortenedId() + "/" + w.getNamespace() + "/stripped_",
                                    Objects.requireNonNull(fence.defaultBlockState().getSoundType()));
                        })
                .requiresChildren(FENCE, STRIPPED_LOG, STRIPPED_WOOD) //REASON: textures, recipes
                //TEXTURES: stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("posts"), Registries.BLOCK, Registries.ITEM)
                .setTabKey(tab)
                .setTabMode(TabAddMode.AFTER_SAME_WOOD)
                .addRecipe(modRes("building/crafting/stripped_oak_post"))
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .build();
        this.addEntry(strippedPosts);

        verticalPlanks = QuarkSimpleEntrySet.builder(WoodType.class, "planks", "vertical",
                        VerticalPlanksModule.class,
                        getModBlock("vertical_oak_planks"),
                        () -> VanillaWoodTypes.OAK,
                        w -> {
                            String name = shortenedId() + "/" + w.getVariantId("planks", "vertical");
                            return new ZetaBlock(name, null,
                                    Utils.copyPropertySafe(w.planks).sound(w.planks.defaultBlockState().getSoundType())
                            );
                        }
                )
                .setTabKey(tab)
                .setTabMode(TabAddMode.AFTER_SAME_WOOD)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK, Registries.ITEM)
                .addRecipe(modRes("building/crafting/vertplanks/vertical_oak_planks"))
                .build();
        this.addEntry(verticalPlanks);

        ladders = QuarkSimpleEntrySet.builder(WoodType.class, "ladder",
                        VariantLaddersModule.class,
                        getModBlock("spruce_ladder"),
                        () -> VanillaWoodTypes.SPRUCE,
                        w -> new VariantLadderBlock(shortenedId() + "/" + w.getAppendableId(),
                                null, BlockBehaviour.Properties.copy(Blocks.LADDER).sound(w.getSound()), w.canBurn()))
                .setTabKey(tab)
                .setTabMode(TabAddMode.AFTER_SAME_WOOD)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.CLIMBABLE, Registries.BLOCK)
                .addTag(modRes("ladders"), Registries.BLOCK, Registries.ITEM)
                .addTexture(EveryCompat.res("block/spruce_ladder"))
                .addRecipe(modRes("building/crafting/spruce_ladder"))
                .setRenderType(RenderLayer.TRANSLUCENT)
                .build();
        this.addEntry(ladders);

        hollowLogs = QuarkSimpleEntrySet.builder(WoodType.class, "log", "hollow",
                        HollowLogsModule.class,
                        getModBlock("hollow_oak_log"),
                        () -> VanillaWoodTypes.OAK,
                        w -> new HollowLogBlock(shortenedId() + "/" + w.getAppendableId(),
                                w.log, null, w.canBurn())
                )
                .requiresChildren(STRIPPED_LOG) // Texture
                .setTabKey(tab)
                .setTabMode(TabAddMode.AFTER_SAME_WOOD)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("hollow_logs"), Registries.BLOCK, Registries.ITEM)
                .addRecipe(modRes("building/crafting/hollowlogs/hollow_oak_log"))
                .build();
        this.addEntry(hollowLogs);

        chests = QuarkSimpleEntrySet.builder(WoodType.class, "chest",
                        VariantChestsModule.class,
                        getModBlock("oak_chest", VariantChestBlock.class),
                        () -> VanillaWoodTypes.OAK,
                        w -> new CompatChestBlock(w,
                                shortenedId() + "/" + w.getAppendableId(),
                                Utils.copyPropertySafe(Blocks.CHEST)
                                        .sound(w.planks.defaultBlockState().getSoundType())))
                .setTabKey(tab)
                .setTabMode(TabAddMode.AFTER_SAME_WOOD)
                .addTag(new ResourceLocation("forge:chests/wooden"), Registries.BLOCK, Registries.ITEM)
                .addTag(modRes("revertable_chests"), Registries.ITEM)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTile(CompatChestBlockTile::new)
                .excludeBlockTypes("twilightforest", "dark")
                .addRecipe(modRes("building/crafting/chests/oak_chest"))
                .build();
        this.addEntry(chests);

        trappedChests = QuarkSimpleEntrySet.builder(WoodType.class, "trapped_chest",
                        VariantChestsModule.class,
                        getModBlock("oak_trapped_chest", VariantTrappedChestBlock.class),
                        () -> VanillaWoodTypes.OAK,
                        w -> {
                            boolean isNamespaceLoaded = w.getNamespace().equals("twilightforest")
                                    || w.getNamespace().equals("blue_skies");
                            if (!chests.blocks.containsKey(w) && !isNamespaceLoaded) return null;
                            String name = shortenedId() + "/" + w.getAppendableId();
                            return new CompatTrappedChestBlock(w, name,
                                    Utils.copyPropertySafe(Blocks.TRAPPED_CHEST)
                                            .sound(w.planks.defaultBlockState().getSoundType()));
                        })
                .setTabKey(tab)
                .setTabMode(TabAddMode.AFTER_SAME_WOOD)
                .addTag(new ResourceLocation("forge:chests/trapped"), Registries.BLOCK, Registries.ITEM)
                .addTag(new ResourceLocation("forge:chests/wooden"), Registries.BLOCK, Registries.ITEM)
                .addTag(modRes("revertable_trapped_chests"), Registries.ITEM)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTile(CompatTrappedChestBlockTile::new)
                .addRecipe(modRes("building/crafting/chests/oak_trapped_chest"))
                .build();
        this.addEntry(trappedChests);

        //doing it this way because for some reason its nuking whatever block item I throw in here
        hedges = QuarkSimpleEntrySet.builder(LeavesType.class, "hedge",
                        HedgesModule.class,
                        getModBlock("oak_hedge"),
                        () -> VanillaLeavesTypes.OAK,
                        leavesType -> new HedgeBlock("", null, Blocks.OAK_FENCE, leavesType.leaves)
                )
                .addCondition(l-> l.getBlockOfThis(LOG) != null)
                //.requiresChildren(LOG) // Reason: RECIPES. Yes leaves have log too.
                .addModelTransform(m -> m.replaceWithTextureFromChild("minecraft:block/oak_leaves",
                        "leaves", CompatSpritesHelper.LOOKS_LIKE_LEAF_TEXTURE))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("hedges"), Registries.BLOCK, Registries.ITEM)
                .setTabKey(tab)
                .setTabMode(TabAddMode.AFTER_SAME_WOOD)
                .addRecipe(modRes("building/crafting/oak_hedge"))
                //RECIPES: Manually created below blc the recipe has a tag as an ingredient
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .copyParentTint()
                .build();
        this.addEntry(hedges);


        //doing it this way because for some reason its nuking whatever block item I throw in here
        leafCarpets = QuarkSimpleEntrySet.builder(LeavesType.class, "leaf_carpet",
                        LeafCarpetModule.class,
                        getModBlock("oak_leaf_carpet"),
                        () -> VanillaLeavesTypes.OAK,
                        leavesType -> {
                            String name = shortenedId() + "/" + leavesType.getVariantId("%s_leaf_carpet");
                            return new LeafCarpetBlock(name, leavesType.leaves, null);
                        })
                //RECIPES: leaves
                .addModelTransform(m -> m.replaceWithTextureFromChild("minecraft:block/oak_leaves",
                        "leaves", s -> !s.contains("/snow") && !s.contains("_snow")))
                .addTag(BlockTags.MINEABLE_WITH_HOE, Registries.BLOCK)
                .addTag(new ResourceLocation("forge:mineable/sickle"), Registries.BLOCK)
                .addTag(new ResourceLocation("mynethersdelight:resurgent_soil_plant"), Registries.BLOCK)
                .setTabKey(tab)
                .setTabMode(TabAddMode.AFTER_SAME_WOOD)
                .addRecipe(modRes("building/crafting/oak_leaf_carpet"))
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .copyParentTint()
                .build();
        this.addEntry(leafCarpets);

    }

    @Override
    public void onModSetup() {
        posts.blocks.forEach((w, post) -> {
            Block stripped = strippedPosts.blocks.get(w);
            if (stripped != null) ECPlatformStuff.registerStripping(post, stripped);
        });
        leafCarpets.blocks.forEach((w, leaf) -> ComposterBlock.COMPOSTABLES.put(leaf, 0.2F));
    }

    @Override
    public void registerTiles(Registrator<BlockEntityType<?>> registry) {
        super.registerTiles(registry);
        CHEST_TILE = chests.getTile(ChestBlockEntity.class);
        TRAPPED_CHEST_TILE = trappedChests.getTile(ChestBlockEntity.class);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void onClientSetup() {
        super.onClientSetup();
        QuarkClientModule.initClient(this);
    }

    @Environment(EnvType.CLIENT)
    public static class QuarkClientModule {
        private static void initClient(QuarkModule module) {
            for (var b : module.chests.blocks.values())
                QuarkClient.ZETA_CLIENT.setBlockEntityWithoutLevelRenderer(b.asItem(), new SimpleWithoutLevelRenderer(CHEST_TILE, b.defaultBlockState()));
            for (var b : module.trappedChests.blocks.values())
                QuarkClient.ZETA_CLIENT.setBlockEntityWithoutLevelRenderer(b.asItem(), new SimpleWithoutLevelRenderer(TRAPPED_CHEST_TILE, b.defaultBlockState()));
        }
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void registerBlockEntityRenderers(ClientHelper.BlockEntityRendererEvent event) {
        super.registerBlockEntityRenderers(event);
        event.register(CHEST_TILE, context -> new VariantChestRenderer(context, false));
        event.register(TRAPPED_CHEST_TILE, context -> new VariantChestRenderer(context, true));
    }

    public static final PaletteStrategy bookshelfPalette = registerCached((blockType, manager) ->
            PaletteStrategies.makePaletteFromChild(
                    blockType, manager, PLANKS, null,
                    (p) -> {
                        var l0 = p.getDarkest();
                        p.increaseDown();
                        p.increaseDown();
                        p.increaseDown();
                        p.increaseDown();
                        p.remove(l0);
                    }));

    @Override
    public void addDynamicClientResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicClientResources(executor);

        //extra task
        executor.accept((this::generateChestTextures));
    }

    private void generateChestTextures(ResourceManager manager, ResourceSink sink) {
        try (TextureImage normal = TextureImage.open(manager, modRes("quark_variant_chests/oak/normal"));
             TextureImage normal_m = TextureImage.open(manager, EveryCompat.res("model/oak_chest_normal_m"));
             TextureImage normal_o = TextureImage.open(manager, EveryCompat.res("model/oak_chest_normal_o"));
             TextureImage left = TextureImage.open(manager, modRes("quark_variant_chests/oak/left"));
             TextureImage left_m = TextureImage.open(manager, EveryCompat.res("model/oak_chest_left_m"));
             TextureImage left_o = TextureImage.open(manager, EveryCompat.res("model/oak_chest_left_o"));
             TextureImage right = TextureImage.open(manager, modRes("quark_variant_chests/oak/right"));
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

            trappedChests.blocks.forEach((wood, block) -> {

                CompatTrappedChestBlock b = (CompatTrappedChestBlock) block;

                try (TextureImage plankTexture = TextureImage.open(manager,
                        RPUtils.findFirstBlockTextureLocation(manager, wood.planks))) {

                    List<Palette> targetPalette = Palette.fromAnimatedImage(plankTexture);
                    var meta = plankTexture.getMcMeta();

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
                        ResourceLocation res = modRes(b.getTextureFolder() + "/" + b.getTexturePath() + "/normal");
                        if (!sink.alreadyHasTextureAtLocation(manager, res)) {
                            ResourceLocation trappedRes = modRes(b.getTextureFolder() + "/" + b.getTexturePath() + "/trap");

                            createChestTextures(sink, normal_t, respriterNormal, respriterNormalO, meta, targetPalette, overlayPalette, res, trappedRes, wood);
                        }
                    }
                    {
                        ResourceLocation res = modRes(b.getTextureFolder() + "/" + b.getTexturePath() + "/left");
                        if (!sink.alreadyHasTextureAtLocation(manager, res)) {
                            ResourceLocation trappedRes = modRes(b.getTextureFolder() + "/" + b.getTexturePath() + "/trap_left");

                            createChestTextures(sink, left_t, respriterLeft, respriterLeftO, meta, targetPalette, overlayPalette, res, trappedRes, wood);
                        }
                    }
                    {
                        ResourceLocation res = modRes(b.getTextureFolder() + "/" + b.getTexturePath() + "/right");
                        if (!sink.alreadyHasTextureAtLocation(manager, res)) {
                            ResourceLocation trappedRes = modRes(b.getTextureFolder() + "/" + b.getTexturePath() + "/trap_right");

                            createChestTextures(sink, right_t, respriterRight, respriterRightO, meta, targetPalette, overlayPalette, res, trappedRes, wood);
                        }
                    }


                } catch (Exception ex) {
                    EveryCompat.LOGGER.error("Failed to generate Chest block texture for for {} : {}", b, ex);
                }
            });
        } catch (Exception ex) {
            EveryCompat.LOGGER.error("Could not generate any Chest block texture : ", ex);
        }
    }

    private void createChestTextures(ResourceSink sink, TextureImage trappedOverlay,
                                     Respriter respriterLeft, Respriter respriterLeftO,
                                     McMetaFile baseMeta, List<Palette> basePalette,
                                     List<Palette> overlayPalette, ResourceLocation res, ResourceLocation trappedRes,
                                     WoodType wood) {

        try (TextureImage recoloredBase = respriterLeft.recolorWithAnimation(basePalette, baseMeta);
             TextureImage recoloredOverlay = respriterLeftO.recolorWithAnimation(overlayPalette, baseMeta)) {
            TextureOps.applyOverlay(recoloredBase, recoloredOverlay);
            try (TextureImage trapped = recoloredBase.makeCopy()) {

                if (!wood.getNamespace().equals("blue_skies") || (wood.getNamespace().equals("blue_skies") && wood.getTypeName().equals("crystallized")))
                    sink.addTexture(res, recoloredBase);

                TextureOps.applyOverlay(trapped, trappedOverlay);
                sink.addTexture(trappedRes, trapped);
            }
        }
    }

    @Override
    // RECIPES, TAGS
    public void addDynamicServerResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicServerResources(executor);
        executor.accept((manager, sink) -> {
            if (PlatHelper.isModLoaded("botanypots")) {
                hedges.items.forEach((leaves, item) -> {
                    var leavesItem = leaves.leaves.asItem();
                    BotanyPotsHelper.cropQuarkHedgeRecipe(this, item, leavesItem, sink, manager, leaves);
                });
            }

            // hedge's recipe & logs' tags
            hedges.blocks.forEach((leavesType, block) -> {
                if (block != null) createHedgeRecipe(leavesType, block, sink, manager);
            });

            verticalSlabs.blocks.forEach((woodType, block) ->
                    createVertSlabRecipe(woodType, block, sink));
        });
    }

    // Hedge's recipe has a tag as an ingredient
    public void createHedgeRecipe(LeavesType leavesType, Block hedge, ResourceSink sink, ResourceManager manager) {

        String recipe = """
                {
                    "type": "minecraft:crafting_shaped",
                    "pattern": [
                        "L",
                        "W"
                    ],
                    "key": {
                        "L": {
                            "item": "[LEAVES]"
                        },
                        "W": {
                            "tag": "[TAG]"
                        }
                    },
                    "result": {
                        "item": "[HEDGE]",
                        "count": 2
                    },
                    "conditions": [
                        {
                            "type": "quark:flag",
                            "flag": "hedges"
                        }
                    ]
                }\s
                """;

        WoodType woodType = leavesType.getAssociatedWoodType();
        if (Objects.nonNull(woodType)) { //why is this here? we already checked all leaves have an associated wood in hedge construction
            String newTag = getATagOrCreateANew("log", "cap", woodType, sink, manager).toString();

            String newRecipe = recipe.replace("[LEAVES]", Utils.getID(leavesType.leaves).toString())
                    .replace("[TAG]", newTag)
                    .replace("[HEDGE]", Utils.getID(hedge).toString());

            // Adding the finished recipe to ResourceLocation
            sink.addBytes(EveryCompat.res(leavesType.createPathWith(shortenedId(), "hedge")), newRecipe.getBytes(),
                    ResType.RECIPES);
        }
        else
            EveryCompat.LOGGER.error("Hedge's LeavesType do not have associated WoodType for: {}. HOW??", leavesType.getId().toString());
    }

    //REASON: vertical_slab_revert are not being generated, this will have to do for now
    public void createVertSlabRecipe(WoodType woodType, Block vertSlab, ResourceSink sink) {

        String recipe = """
                {
                    "type": "minecraft:crafting_shapeless",
                    "ingredients": [
                        {
                            "item": "[VERTICAL_SLAB]"
                        }
                    ],
                        "result": {
                        "item": "[SLAB]",
                        "count": 1
                    },
                    "conditions": [
                        {
                            "type": "quark:flag",
                            "flag": "vertical_slabs"
                        }
                    ]
                }\s
                """;

        if (Objects.nonNull(woodType.getBlockOfThis(SLAB))) {
            String newRecipe = recipe
                    .replace("[SLAB]", Utils.getID(Objects.requireNonNull(woodType.getBlockOfThis(SLAB))).toString())
                    .replace("[VERTICAL_SLAB]", Utils.getID(vertSlab).toString());

            // Adding the finished recipe to ResourceLocation
            sink.addBytes(EveryCompat.res(woodType.createPathWith(shortenedId(), "vertical_slab_revert")),
                    newRecipe.getBytes(), ResType.RECIPES);
        }
        else
            EveryCompat.LOGGER.warn("Skipped generating vertical_slab_revert recipe for: {} lacking SLAB.", woodType.getId().toString());
    }

}
