package net.mehvahdjukaar.every_compat.modules.quark;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.mehvahdjukaar.every_compat.ECPlatformStuff;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.*;
import net.mehvahdjukaar.every_compat.common_classes.*;
import net.mehvahdjukaar.every_compat.misc.CompatSpritesHelper;
import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.every_compat.modules.botanypots.BotanyPotsHelper;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceSink;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesType;
import net.mehvahdjukaar.moonlight.api.set.leaves.VanillaLeavesTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.BlockPos;
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
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import org.violetmoon.quark.content.building.block.*;
import org.violetmoon.quark.content.building.module.*;
import org.violetmoon.zeta.block.ZetaBlock;

import java.util.Objects;
import java.util.function.Consumer;

import static net.mehvahdjukaar.every_compat.api.PaletteStrategies.registerCached;
import static net.mehvahdjukaar.every_compat.common_classes.CompatChestTexture.generateChestTexture;
import static net.mehvahdjukaar.every_compat.misc.UtilityTag.getATagOrCreateANew;
import static net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys.*;

//SUPPORT: v4.0-435+
public class QuarkModule extends EveryCompatModule {

    public final SimpleEntrySet<WoodType, Block> verticalSlabs;
    public final SimpleEntrySet<WoodType, Block> bookshelves;
    public final SimpleEntrySet<WoodType, Block> posts;
    public final SimpleEntrySet<WoodType, Block> strippedPosts;
    public final SimpleEntrySet<WoodType, Block> verticalPlanks;
    public final SimpleEntrySet<WoodType, Block> ladders;
    public final SimpleEntrySet<WoodType, Block> hollowLogs;
    public final SimpleEntrySet<WoodType, Block> chests;
    public final SimpleEntrySet<WoodType, Block> trappedChests;
    public final SimpleEntrySet<LeavesType, Block> hedges;
    public final SimpleEntrySet<LeavesType, Block> leafCarpets;

    public QuarkModule(String modId) {
        super(modId, "q");
        ResourceKey<CreativeModeTab> tab = CreativeModeTabs.BUILDING_BLOCKS;

        verticalSlabs = QuarkSimpleEntrySet.builder(WoodType.class, "vertical_slab",
                        VerticalSlabsModule.class,
                        getModBlock("oak_vertical_slab"), () -> VanillaWoodTypes.OAK,
                        w -> new VerticalSlabBlock(() -> w.getBlockOfThis(SLAB),
                                Utils.copyPropertySafe(Objects.requireNonNull(w.getBlockOfThis(SLAB)))
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
                .addTextureM(EveryCompat.res("block/acacia_bookshelf"),
                        EveryCompat.res("block/acacia_bookshelf_m"),
                        bookshelfPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(ResourceLocation.parse("c:bookshelves"), Registries.BLOCK, Registries.ITEM)
                .setTabKey(tab)
                .setTabMode(TabAddMode.AFTER_SAME_WOOD)
                .addRecipe(modRes("building/crafting/bookshelves/acacia_bookshelf"))
                .copyParentDrop()
                .build();
        this.addEntry(bookshelves);

        posts = QuarkSimpleEntrySet.builder(WoodType.class, "post",
                        WoodenPostsModule.class,
                        getModBlock("oak_post"), () -> VanillaWoodTypes.OAK,
                        w -> {
                            Block fence = w.getBlockOfThis("fence");
                            return new WoodPostBlock(null, Objects.requireNonNull(fence),
                                    shortenedId() + "/" + w.getNamespace() + "/",
                                    Objects.requireNonNull(fence).defaultBlockState().getSoundType());
                        })
                .requiresChildren(FENCE, WOOD) //REASON: recipes
                //TEXTURES: log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("posts"), Registries.BLOCK, Registries.ITEM)
                .setTabKey(tab)
                .setTabMode(TabAddMode.AFTER_SAME_WOOD)
                .addRecipe(modRes("building/crafting/posts/oak_post"))
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(posts);

        strippedPosts = QuarkSimpleEntrySet.builder(WoodType.class, "post", "stripped",
                        WoodenPostsModule.class,
                        getModBlock("stripped_oak_post"), () -> VanillaWoodTypes.OAK,
                        w -> {
                            if (w.getNamespace().equals("malum") || w.getNamespace().equals("twigs")) return null;
                            Block fence = w.getBlockOfThis("fence");
                            // required stripped_log texture & fence as an ingredients
                            return new WoodPostBlock(null, Objects.requireNonNull(fence),
                                    shortenedId() + "/" + w.getNamespace() + "/stripped_",
                                    Objects.requireNonNull(fence).defaultBlockState().getSoundType());
                        })
                .requiresChildren("fence", "stripped_log", "stripped_wood") //REASON: textures, recipes
                //TEXTURES: stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("posts"), Registries.BLOCK, Registries.ITEM)
                .setTabKey(tab)
                .setTabMode(TabAddMode.AFTER_SAME_WOOD)
                .addRecipe(modRes("building/crafting/posts/stripped_oak_post"))
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .build();
        this.addEntry(strippedPosts);

        verticalPlanks = QuarkSimpleEntrySet.builder(WoodType.class, "planks", "vertical",
                        VerticalPlanksModule.class,
                        getModBlock("vertical_oak_planks"), () -> VanillaWoodTypes.OAK,
                        w -> {
                            String name = shortenedId() + "/" + w.getVariantId("planks", "vertical");
                            return new ZetaBlock(name, null,
                                    BlockBehaviour.Properties.of()
                                            .mapColor(MapColor.WOOD)
                                            .ignitedByLava()
                                            .instrument(NoteBlockInstrument.BASS)
                                            .strength(2.0F, 3.0F)
                                            .sound(SoundType.WOOD)
                            );
                        }
                )
                .addCondition(w -> !w.getId().toString().equals("gardens_of_the_dead:whistle_planks")) //REASON: The look is no different from a normal plank
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
                                null, Utils.copyPropertySafe(Blocks.LADDER).sound(w.getSound()), w.canBurn()))
                .setTabKey(tab)
                .setTabMode(TabAddMode.AFTER_SAME_WOOD)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.CLIMBABLE, Registries.BLOCK)
                .addTag(modRes("ladders"), Registries.BLOCK, Registries.ITEM)
                .addTexture(EveryCompat.res("block/spruce_ladder"))
                .addRecipe(modRes("building/crafting/ladders/spruce_ladder"))
                .setRenderType(RenderLayer.TRANSLUCENT)
                .build();
        this.addEntry(ladders);

        hollowLogs = QuarkSimpleEntrySet.builder(WoodType.class, "log", "hollow",
                        HollowLogsModule.class,
                        getModBlock("hollow_oak_log"), () -> VanillaWoodTypes.OAK,
                        w -> new HollowLogBlock(shortenedId() + "/" + w.getAppendableId(),
                                w.log, null, w.canBurn()))
                .requiresChildren("stripped_log") // Texture
                .setTabKey(tab)
                .setTabMode(TabAddMode.AFTER_SAME_WOOD)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("hollow_logs"), Registries.BLOCK, Registries.ITEM)
                .addRecipe(modRes("building/crafting/hollowlogs/hollow_oak_log"))
                .build();
        this.addEntry(hollowLogs);

        chests = QuarkSimpleEntrySet.builder(WoodType.class, "chest",
                        VariantChestsModule.class,
                        getModBlock("oak_chest"), () -> VanillaWoodTypes.OAK,
                        w -> new CompatChestBlock(this::getChestTile, Utils.copyPropertySafe(Blocks.CHEST))
                )
                .addTile(qChestBlockEntity::new)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("revertable_chests"), Registries.ITEM)
                .setTabKey(tab)
                .setTabMode(TabAddMode.AFTER_SAME_WOOD)
                .addRecipe(modRes("building/crafting/chests/oak_chest"))
                .addRecipe(modRes("building/chests/oak_chest_wood"))
                .addCustomItem((w, block, properties) -> new CompatChestItem(block, properties))
                .excludeBlockTypes("twilightforest", "dark")
                .build();
        this.addEntry(chests);

        trappedChests = QuarkSimpleEntrySet.builder(WoodType.class, "chest", "trapped",
                        VariantChestsModule.class,
                        getModBlock("trapped_oak_chest"), () -> VanillaWoodTypes.OAK,
                        w -> {
                            boolean isNamespaceLoaded = w.getNamespace().equals("twilightforest")
                                    || w.getNamespace().equals("blue_skies");
                            if (!chests.blocks.containsKey(w) && !isNamespaceLoaded) return null;
                            String name = shortenedId() + "/" + w.getAppendableId();
                            return new CompatTrappedChestBlock(this::getTrappedTile, Utils.copyPropertySafe(Blocks.TRAPPED_CHEST));
                        })
                .addTile(qTrappedBlockEntity::new)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("revertable_trapped_chests"), Registries.ITEM)
                .setTabKey(tab)
                .setTabMode(TabAddMode.AFTER_SAME_WOOD)
                .addRecipe(modRes("building/chests/trapped_oak_chest"))
                .addCustomItem((w, block, properties) -> new CompatChestItem(block, properties))
                .build();
        this.addEntry(trappedChests);

        //doing it this way because for some reason its nuking whatever block item I throw in here
        hedges = QuarkSimpleEntrySet.builder(LeavesType.class, "hedge",
                        HedgesModule.class,
                        getModBlock("oak_hedge"),
                        () -> VanillaLeavesTypes.OAK,
                        leavesType -> new HedgeBlock("", null, Blocks.OAK_FENCE, leavesType.leaves)
                )
                .addCondition(l-> l.getBlockOfThis(LOG) != null) // Reason: RECIPES. Yes leaves have log too.
                .addModelTransform(m -> m.replaceWithTextureFromChild("minecraft:block/oak_leaves",
                        "leaves", CompatSpritesHelper.LOOKS_LIKE_LEAF_TEXTURE))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("hedges"), Registries.BLOCK, Registries.ITEM)
                .setTabKey(tab)
                .setTabMode(TabAddMode.AFTER_SAME_WOOD)
                .copyParentTint()
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
                .addTag(ResourceLocation.parse("forge:mineable/sickle"), Registries.BLOCK)
                .addTag(ResourceLocation.parse("mynethersdelight:resurgent_soil_plant"), Registries.BLOCK)
                .setTabKey(tab)
                .setTabMode(TabAddMode.AFTER_SAME_WOOD)
                .addRecipe(modRes("building/crafting/leafcarpet/oak_leaf_carpet"))
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .copyParentTint()
                .build();
        this.addEntry(leafCarpets);

    }

    // GetTile -----------------------------------------------------------------------------------------------------------
    private BlockEntityType<? extends ChestBlockEntity> getChestTile() {
        return chests.getTile(CompatChestBlockEntity.class);
    }

    private BlockEntityType<? extends ChestBlockEntity> getTrappedTile() {
        return trappedChests.getTile(CompatChestBlockEntity.class);
    }

    // BlockEntity -----------------------------------------------------------------------------------------------------------
    private class qChestBlockEntity extends CompatChestBlockEntity {
        public qChestBlockEntity(BlockPos pos, BlockState state) {
            super(chests.getTile(), pos, state);
        }
    }

    private class qTrappedBlockEntity extends CompatChestBlockEntity {
        public qTrappedBlockEntity(BlockPos pos, BlockState state) {
            super(trappedChests.getTile(), pos, state);
        }
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
    @Environment(EnvType.CLIENT)
    public void registerBlockEntityRenderers(ClientHelper.BlockEntityRendererEvent event) {
        super.registerBlockEntityRenderers(event);
        CompatChestBlockRenderer.register(event, chests.getTile(CompatChestBlockEntity.class), shortenedId());
        CompatChestBlockRenderer.register(event, trappedChests.getTile(CompatChestBlockEntity.class), shortenedId());
    }

    public static final PaletteStrategy bookshelfPalette = registerCached((blockType, manager) ->
            PaletteStrategies.makePaletteFromChild(
                    blockType, manager, PLANKS, null,
                    p -> {
                        var l0 = p.getDarkest();
                        p.increaseDown();
                        p.increaseDown();
                        p.increaseDown();
                        p.increaseDown();
                        p.remove(l0);
                    }
            )
    );

    @Override
    // TEXTURES
    public void addDynamicClientResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicClientResources(executor);

        //extra task
        executor.accept(this::generateChestTextures);
    }

    private void generateChestTextures(ResourceManager manager, ResourceSink sink) {
        trappedChests.blocks.forEach((wood, block) -> {

            // SINGLE
            generateChestTexture(sink, manager, shortenedId(), wood, block,
                    modRes("quark_variant_chests/oak/normal"),
                    EveryCompat.res("model/oak_chest_normal_m"),
                    EveryCompat.res("model/oak_chest_normal_o"),
                    EveryCompat.res("model/trapped_chest_normal")
            );
            // LEFT
            generateChestTexture(sink, manager, shortenedId(), wood, block,
                    modRes("quark_variant_chests/oak/left"),
                    EveryCompat.res("model/oak_chest_left_m"),
                    EveryCompat.res("model/oak_chest_left_o"),
                    EveryCompat.res("model/trapped_chest_left")
            );
            // RIGHT
            generateChestTexture(sink, manager, shortenedId(), wood, block,
                    modRes("quark_variant_chests/oak/right"),
                    EveryCompat.res("model/oak_chest_right_m"),
                    EveryCompat.res("model/oak_chest_right_o"),
                    EveryCompat.res("model/trapped_chest_right")
            );
        });
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
                    "neoforge:conditions": [
                        {
                            "type": "zeta:flag",
                            "flag": "hedges"
                        }
                    ],
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
                        "id": "[HEDGE]",
                        "count": 2
                    }
                }\s
                """;

        WoodType woodType = leavesType.getAssociatedWoodType();
        if (Objects.nonNull(woodType)) {
            String newTag = getATagOrCreateANew("log", "cap", woodType, sink, manager).toString();

            String newRecipe = recipe.replace("[LEAVES]", Utils.getID(leavesType.leaves).toString())
                    .replace("[TAG]", newTag)
                    .replace("[HEDGE]", Utils.getID(hedge).toString());

            // Adding the finished recipe to ResourceLocation
            sink.addBytes(EveryCompat.res(leavesType.createPathWith(shortenedId(), "hedge")),
                    newRecipe.getBytes(),
                    ResType.RECIPES);
        } else
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
                        "id": "[SLAB]",
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
