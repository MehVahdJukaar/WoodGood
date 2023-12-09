package net.mehvahdjukaar.every_compat.modules.forge.buildersaddition;

import com.mrh0.buildersaddition.Index;
import com.mrh0.buildersaddition.blocks.*;
import com.mrh0.buildersaddition.itemgroup.ModGroup;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.platform.ClientPlatformHelper;
import net.mehvahdjukaar.moonlight.api.platform.PlatformHelper;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesType;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesTypeRegistry;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;


public class BuildersAdditionModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> arcades;
    public final SimpleEntrySet<WoodType, Block> bedsideTables;
    public final SimpleEntrySet<WoodType, Block> benches;
    public final SimpleEntrySet<WoodType, Block> bookshelves;
    public final SimpleEntrySet<WoodType, Block> cabinets;
    public final SimpleEntrySet<WoodType, Block> chairs;
    public final SimpleEntrySet<WoodType, Block> countersAndesite;
    public final SimpleEntrySet<WoodType, Block> countersBlackstone;
    public final SimpleEntrySet<WoodType, Block> countersDeepslate;
    public final SimpleEntrySet<WoodType, Block> countersDiorite;
    public final SimpleEntrySet<WoodType, Block> countersGranite;
    public final SimpleEntrySet<WoodType, Block> cupboards;
    public final SimpleEntrySet<LeavesType, Block> hedges;
    public final SimpleEntrySet<WoodType, Block> shelves;
    public final SimpleEntrySet<WoodType, Block> smallCupboards;
    public final SimpleEntrySet<WoodType, Block> stools;
    public final SimpleEntrySet<WoodType, Block> supportsBracket;
    public final SimpleEntrySet<WoodType, Block> tables;
    public final SimpleEntrySet<WoodType, Block> verticalSlab;

    public BuildersAdditionModule(String modId) {
        super(modId, "bca");
        CreativeModeTab tab = ModGroup.MAIN;

        verticalSlab = SimpleEntrySet.builder(WoodType.class, "vertical_slab",
                        Index.OAK_VERTICAL_SLAB, () -> WoodTypeRegistry.OAK_TYPE,
                        (w) -> {
                            if (PlatformHelper.isModLoaded("v_slab_compat")) return null;
                            return new VerticalSlab(shortenedId() + "/" + w.getAppendableId(), w.planks);
                        })
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addRecipe(modRes("vertical_slab/oak_vertical_slab"))
                .addRecipe(modRes("vertical_slab/reverse/oak_vertical_slab"))
                .setTab(() -> tab)
                .build();

        tables = SimpleEntrySet.builder(WoodType.class, "", "table",
                        Index.TABLE_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Table(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addRecipe(modRes("table/table_oak"))
                .setTab(() -> tab)
                .build();

        this.addEntry(tables);

        stools = SimpleEntrySet.builder(WoodType.class, "", "stool",
                        Index.STOOL_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Stool(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addRecipe(modRes("stool/stool_oak"))
                .setTab(() -> tab)
                .build();

        this.addEntry(stools);

        chairs = SimpleEntrySet.builder(WoodType.class, "",  "chair",
                        Index.CHAIR_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Chair(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addRecipe(modRes("chair/chair_oak"))
                .setTab(() -> tab)
                .build();

        this.addEntry(chairs);

        hedges = SimpleEntrySet.builder(LeavesType.class, "",  "hedge",
                        Index.HEDGE_OAK, () -> LeavesTypeRegistry.OAK_TYPE,
                        w -> {
                            var l = w.getBlockOfThis("leaves");
                            if (l == null) return null;
                            return new Hedge(shortenedId() + "/" + w.getAppendableId(), l);
                        })
                .addModelTransform(m -> m.replaceWithTextureFromChild("minecraft:block/oak_leaves",
                        "leaves", s -> !s.contains("/snow") && !s.contains("_snow")))
                .addModelTransform(m -> m.replaceLeavesTextures(LeavesTypeRegistry.OAK_TYPE))
                .addTag(BlockTags.MINEABLE_WITH_HOE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.LEAVES, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.LEAVES, Registry.ITEM_REGISTRY)
                .addRecipe(modRes("hedge/hedge_oak"))
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();

        this.addEntry(hedges);

        countersAndesite = SimpleEntrySet.builder(WoodType.class, "andesite",  "counter",
                        () -> getModBlock("counter_oak_andesite"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Counter(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addRecipe(modRes("counter/counter_oak_andesite"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .build();

        this.addEntry(countersAndesite);

        countersDiorite = SimpleEntrySet.builder(WoodType.class, "diorite",  "counter",
                        () -> getModBlock("counter_oak_diorite"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Counter(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addRecipe(modRes("counter/counter_oak_diorite"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .build();

        this.addEntry(countersDiorite);

        countersGranite = SimpleEntrySet.builder(WoodType.class, "granite",  "counter",
                        () -> getModBlock("counter_oak_granite"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Counter(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addRecipe(modRes("counter/counter_oak_granite"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .build();

        this.addEntry(countersGranite);

        countersBlackstone = SimpleEntrySet.builder(WoodType.class, "blackstone",  "counter",
                        () -> getModBlock("counter_oak_blackstone"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Counter(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addRecipe(modRes("counter/counter_oak_blackstone"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .build();

        this.addEntry(countersBlackstone);

        countersDeepslate = SimpleEntrySet.builder(WoodType.class, "deepslate",  "counter",
                        () -> getModBlock("counter_oak_deepslate"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Counter(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addRecipe(modRes("counter/counter_oak_deepslate"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .build();

        this.addEntry(countersDeepslate);

        bookshelves = SimpleEntrySet.builder(WoodType.class, "",   "bookshelf",
                        Index.BOOKSHELF_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CompatBookshelf(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addRecipe(modRes("bookshelf/bookshelf_oak"))
                .setTab(() -> tab)
                .build();

        this.addEntry(bookshelves);

        shelves = SimpleEntrySet.builder(WoodType.class, "",   "shelf",
                        Index.SHELF_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Shelf(shortenedId() + "/" + w.getAppendableId()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addRecipe(modRes("shelf/shelf_oak"))
                .addTile(Index.SHELF_TILE_ENTITY_TYPE)
                .setTab(() -> tab)
                .build();

        this.addEntry(shelves);

        cabinets = SimpleEntrySet.builder(WoodType.class, "",   "cabinet",
                        Index.CABINET_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CompatCabinet(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addRecipe(modRes("cabinet/cabinet_oak"))
                .setTab(() -> tab)
                .build();

        this.addEntry(cabinets);

        cupboards = SimpleEntrySet.builder(WoodType.class, "",  "cupboard",
                        Index.CUPBOARD_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Cupboard(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addRecipe(modRes("cupboard/cupboard_oak"))
                .setTab(() -> tab)
                .build();

        this.addEntry(cupboards);

        smallCupboards = SimpleEntrySet.builder(WoodType.class, "",  "small_cupboard",
                        Index.SMALL_CUPBOARD_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new SmallCupboard(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addRecipe(modRes("small_cupboard/small_cupboard_oak"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .build();

        this.addEntry(smallCupboards);

        benches = SimpleEntrySet.builder(WoodType.class, "", "bench",
                        Index.BENCH_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Bench(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addRecipe(modRes("bench/bench_oak"))
                .setTab(() -> tab)
                .build();

        this.addEntry(benches);

        supportsBracket = SimpleEntrySet.builder(WoodType.class, "",   "support_bracket",
                        Index.SUPPORT_BRACKET_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                ifHasChild(w -> new SupportBracket(shortenedId() + "/" + w.getAppendableId(), w.planks), "stripped_log"))
                .addRecipe(modRes("support_bracket/support_bracket_oak"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .build();

        this.addEntry(supportsBracket);

        bedsideTables = SimpleEntrySet.builder(WoodType.class, "",   "bedside_table",
                        () -> getModBlock("bedside_table_oak"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BedsideTable(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addRecipe(modRes("bedside_table/bedside_table_oak"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .requiresChildren("stripped_log")
                .setTab(() -> tab)
                .build();

        this.addEntry(bedsideTables);

        arcades = SimpleEntrySet.builder(WoodType.class, "", "arcade",
                        Index.ARCADE_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CompatArcade(shortenedId() + "/" + w.getAppendableId(), w.log))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .requiresChildren("stripped_log")
                .addRecipe(modRes("arcade/arcade_oak"))
                .setTab(() -> tab)
                .build();

        this.addEntry(arcades);
    }

    private static class CompatBookshelf extends Bookshelf {
        public CompatBookshelf(String name, Block source) {
            super("bookshelf_" + name);
        }
    }

//    @Override
//    public void registerBlockEntityRenderers(ClientPlatformHelper.BlockEntityRendererEvent event) {
//        event.register((BlockEntityType<CompatShelfTileEntity>) (SHELVES.getTileHolder().tile), ShelfRenderer::new);
//    }

    private static class CompatCabinet extends Cabinet {
        public CompatCabinet(String name, Block source) {
            super("cabinet_" + name);
        }
    }

    private static class CompatArcade extends Arcade {
        public CompatArcade(String name, Block source) {
            super("arcade_" + name);

        }
    }

    @Override
    public void registerBlockColors(ClientPlatformHelper.BlockColorEvent event) {
        super.registerBlockColors(event);
        hedges.blocks.forEach((t, b) -> {
            event.register((s, l, p, i) -> event.getColor(t.leaves.defaultBlockState(), l, p, i), b);
        });
    }

    @Override
    public void registerItemColors(ClientPlatformHelper.ItemColorEvent event) {
        super.registerItemColors(event);
        hedges.blocks.forEach((t, b) -> {
            event.register((stack, tintIndex) -> event.getColor(new ItemStack(t.leaves), tintIndex), b.asItem());
        });
    }
}
