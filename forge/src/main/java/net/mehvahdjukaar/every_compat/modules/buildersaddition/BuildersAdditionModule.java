package net.mehvahdjukaar.every_compat.modules.buildersaddition;

import com.mrh0.buildersaddition.Index;
import com.mrh0.buildersaddition.blocks.Arcade;
import com.mrh0.buildersaddition.blocks.BedsideTable;
import com.mrh0.buildersaddition.blocks.Bench;
import com.mrh0.buildersaddition.blocks.Bookshelf;
import com.mrh0.buildersaddition.blocks.Cabinet;
import com.mrh0.buildersaddition.blocks.Chair;
import com.mrh0.buildersaddition.blocks.Counter;
import com.mrh0.buildersaddition.blocks.Cupboard;
import com.mrh0.buildersaddition.blocks.Hedge;
import com.mrh0.buildersaddition.blocks.Shelf;
import com.mrh0.buildersaddition.blocks.SmallCupboard;
import com.mrh0.buildersaddition.blocks.Stool;
import com.mrh0.buildersaddition.blocks.SupportBracket;
import com.mrh0.buildersaddition.blocks.Table;
import com.mrh0.buildersaddition.blocks.VerticalSlab;
import com.mrh0.buildersaddition.itemgroup.ModGroup;
import com.mrh0.buildersaddition.tileentity.ShelfTileEntity;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.platform.ClientPlatformHelper;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesType;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesTypeRegistry;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;


public class BuildersAdditionModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> ARCADES;
    public final SimpleEntrySet<WoodType, Block> BEDSIDE_TABLES;
    public final SimpleEntrySet<WoodType, Block> BENCHES;
    public final SimpleEntrySet<WoodType, Block> BOOKSHELVES;
    public final SimpleEntrySet<WoodType, Block> CABINETS;
    public final SimpleEntrySet<WoodType, Block> CHAIRS;
    public final SimpleEntrySet<WoodType, Block> COUNTERS_ANDESITE;
    public final SimpleEntrySet<WoodType, Block> COUNTERS_BLACKSTONE;
    public final SimpleEntrySet<WoodType, Block> COUNTERS_DEEPSLATE;
    public final SimpleEntrySet<WoodType, Block> COUNTERS_DIORITE;
    public final SimpleEntrySet<WoodType, Block> COUNTERS_GRANITE;
    public final SimpleEntrySet<WoodType, Block> CUPBOARDS;
    public final SimpleEntrySet<LeavesType, Block> HEDGES;
    public final SimpleEntrySet<WoodType, Block> PANELS;
    public final SimpleEntrySet<WoodType, Block> SHELVES;
    public final SimpleEntrySet<WoodType, Block> SMALL_CUPBOARDS;
    public final SimpleEntrySet<WoodType, Block> STOOLS;
    public final SimpleEntrySet<WoodType, Block> SUPPORTS_BRACKET;
    public final SimpleEntrySet<WoodType, Block> TABLES;

    public BuildersAdditionModule(String modId) {
        super(modId, "bca");
        CreativeModeTab tab = ModGroup.MAIN;


        PANELS = SimpleEntrySet.builder(WoodType.class, "vertical_slab",
                        Index.OAK_VERTICAL_SLAB, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new VerticalSlab(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addTag(new ResourceLocation("quark:wooden_vertical_slabs"), Registry.BLOCK_REGISTRY)
                .addTag(new ResourceLocation("quark:wooden_vertical_slabs"), Registry.ITEM_REGISTRY)
                .addTag(new ResourceLocation("quark:planks_vertical_slab"), Registry.BLOCK_REGISTRY)
                .addTag(new ResourceLocation("quark:planks_vertical_slab"), Registry.ITEM_REGISTRY)
                .addTag(new ResourceLocation("quark:vertical_slab"), Registry.BLOCK_REGISTRY)
                .addTag(new ResourceLocation("quark:vertical_slab"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addRecipe(modRes("vertical_slab/oak_vertical_slab"))
                .setRenderType(() -> RenderType::solid)
                .setTab(() -> tab)
                .build();

        this.addEntry(PANELS);

        TABLES = SimpleEntrySet.builder(WoodType.class, "", "table",
                        Index.TABLE_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Table(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addRecipe(modRes("table/table_oak"))
                .setTab(() -> tab)
                .build();

        this.addEntry(TABLES);

        STOOLS = SimpleEntrySet.builder(WoodType.class, "", "stool",
                        Index.STOOL_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Stool(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addRecipe(modRes("stool/stool_oak"))
                .setTab(() -> tab)
                .build();

        this.addEntry(STOOLS);

        CHAIRS = SimpleEntrySet.builder(WoodType.class, "",  "chair",
                        Index.CHAIR_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Chair(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addRecipe(modRes("chair/chair_oak"))
                .setTab(() -> tab)
                .build();

        this.addEntry(CHAIRS);

        HEDGES = SimpleEntrySet.builder(LeavesType.class, "",  "hedge",
                        Index.HEDGE_OAK, () -> LeavesTypeRegistry.OAK_TYPE,
                        w -> {
                            var l = w.getBlockOfThis("leaves");
                            if (l == null) return null;
                            return new Hedge(shortenedId() + "/" + w.getAppendableId(), l);
                        })
                .addModelTransform(m -> m.replaceLeavesTextures(LeavesTypeRegistry.OAK_TYPE))
                .addTag(BlockTags.MINEABLE_WITH_HOE, Registry.BLOCK_REGISTRY)
                .addRecipe(modRes("hedge/hedge_oak"))
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();

        this.addEntry(HEDGES);

        COUNTERS_ANDESITE = SimpleEntrySet.builder(WoodType.class, "andesite",  "counter",
                        () -> getModBlock("counter_oak_andesite"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Counter(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addRecipe(modRes("counter/counter_oak_andesite"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .build();

        this.addEntry(COUNTERS_ANDESITE);

        COUNTERS_DIORITE = SimpleEntrySet.builder(WoodType.class, "diorite",  "counter",
                        () -> getModBlock("counter_oak_diorite"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Counter(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addRecipe(modRes("counter/counter_oak_diorite"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .build();

        this.addEntry(COUNTERS_DIORITE);

        COUNTERS_GRANITE = SimpleEntrySet.builder(WoodType.class, "granite",  "counter",
                        () -> getModBlock("counter_oak_granite"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Counter(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addRecipe(modRes("counter/counter_oak_granite"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .build();

        this.addEntry(COUNTERS_GRANITE);

        COUNTERS_BLACKSTONE = SimpleEntrySet.builder(WoodType.class, "blackstone",  "counter",
                        () -> getModBlock("counter_oak_blackstone"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Counter(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addRecipe(modRes("counter/counter_oak_blackstone"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .build();

        this.addEntry(COUNTERS_BLACKSTONE);

        COUNTERS_DEEPSLATE = SimpleEntrySet.builder(WoodType.class, "deepslate",  "counter",
                        () -> getModBlock("counter_oak_deepslate"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Counter(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addRecipe(modRes("counter/counter_oak_deepslate"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .build();

        this.addEntry(COUNTERS_DEEPSLATE);

        BOOKSHELVES = SimpleEntrySet.builder(WoodType.class, "",   "bookshelf",
                        Index.BOOKSHELF_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CompatBookshelf(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addRecipe(modRes("bookshelf/bookshelf_oak"))
                .setTab(() -> tab)
                .build();

        this.addEntry(BOOKSHELVES);

        SHELVES = SimpleEntrySet.builder(WoodType.class, "",   "shelf",
                        Index.SHELF_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CompatShelf(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addRecipe(modRes("shelf/shelf_oak"))
                .addTile(CompatShelfTileEntity::new)
                .setTab(() -> tab)
                .build();

        this.addEntry(SHELVES);

        CABINETS = SimpleEntrySet.builder(WoodType.class, "",   "cabinet",
                        Index.CABINET_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CompatCabinet(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addRecipe(modRes("cabinet/cabinet_oak"))
                .setTab(() -> tab)
                .build();

        this.addEntry(CABINETS);

        CUPBOARDS = SimpleEntrySet.builder(WoodType.class, "",  "cupboard",
                        Index.CUPBOARD_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Cupboard(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addRecipe(modRes("cupboard/cupboard_oak"))
                .setTab(() -> tab)
                .build();

        this.addEntry(CUPBOARDS);

        SMALL_CUPBOARDS = SimpleEntrySet.builder(WoodType.class, "",  "small_cupboard",
                        Index.SMALL_CUPBOARD_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new SmallCupboard(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addRecipe(modRes("small_cupboard/small_cupboard_oak"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .build();

        this.addEntry(SMALL_CUPBOARDS);

        BENCHES = SimpleEntrySet.builder(WoodType.class, "", "bench",
                        Index.BENCH_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Bench(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addRecipe(modRes("bench/bench_oak"))
                .setTab(() -> tab)
                .build();

        this.addEntry(BENCHES);

        SUPPORTS_BRACKET = SimpleEntrySet.builder(WoodType.class, "",   "support_bracket",
                        Index.SUPPORT_BRACKET_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new SupportBracket(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addRecipe(modRes("support_bracket/support_bracket_oak"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .build();

        this.addEntry(SUPPORTS_BRACKET);

        BEDSIDE_TABLES = SimpleEntrySet.builder(WoodType.class, "",   "bedside_table",
                        () -> getModBlock("bedside_table_oak"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BedsideTable(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addRecipe(modRes("bedside_table/bedside_table_oak"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .build();

        this.addEntry(BEDSIDE_TABLES);

        ARCADES = SimpleEntrySet.builder(WoodType.class, "", "arcade",
                        Index.ARCADE_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CompatArcade(shortenedId() + "/" + w.getAppendableId(), w.log))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addRecipe(modRes("arcade/arcade_oak"))
                .setTab(() -> tab)
                .build();

        this.addEntry(ARCADES);
    }

    private static class CompatBookshelf extends Bookshelf {
        public CompatBookshelf(String name, Block source) {
            super("bookshelf_" + name);
        }
    }

    class CompatShelfTileEntity extends ShelfTileEntity {
        public CompatShelfTileEntity(BlockPos pos, BlockState state) {
            super(pos, state);
        }

        @Override
        public BlockEntityType<?> getType() {
            return SHELVES.getTileHolder().tile;
        }
    }

    private class CompatShelf extends Shelf {
        public CompatShelf(String name, Block source) {
            super("shelf_" + name);
        }

        public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
            return new CompatShelfTileEntity(pos, state);
        }
    }

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
        HEDGES.blocks.forEach((t, b) -> {
            event.register((s, l, p, i) -> event.getColor(t.leaves.defaultBlockState(), l, p, i), b);
        });
    }

    @Override
    public void registerItemColors(ClientPlatformHelper.ItemColorEvent event) {
        super.registerItemColors(event);
        HEDGES.blocks.forEach((t, b) -> {
            event.register((stack, tintIndex) -> event.getColor(new ItemStack(t.leaves), tintIndex), b.asItem());
        });
    }
}
