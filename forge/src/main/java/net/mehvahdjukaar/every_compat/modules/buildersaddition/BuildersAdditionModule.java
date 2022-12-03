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

    public final SimpleEntrySet<WoodType, Block> ARCADE;
    public final SimpleEntrySet<WoodType, Block> BEDSIDE_TABLE;
    public final SimpleEntrySet<WoodType, Block> BENCH;
    public final SimpleEntrySet<WoodType, Block> BOOKSHELF;
    public final SimpleEntrySet<WoodType, Block> CABINET;
    public final SimpleEntrySet<WoodType, Block> CHAIR;
    public final SimpleEntrySet<WoodType, Block> COUNTER_ANDESITE;
    public final SimpleEntrySet<WoodType, Block> COUNTER_BLACKSTONE;
    public final SimpleEntrySet<WoodType, Block> COUNTER_DEEPSLATE;
    public final SimpleEntrySet<WoodType, Block> COUNTER_DIORITE;
    public final SimpleEntrySet<WoodType, Block> COUNTER_GRANITE;
    public final SimpleEntrySet<WoodType, Block> CUPBOARD;
    public final SimpleEntrySet<LeavesType, Block> HEDGE;
    public final SimpleEntrySet<WoodType, Block> PANEL;
    public final SimpleEntrySet<WoodType, Block> SHELF;
    public final SimpleEntrySet<WoodType, Block> SMALL_CUPBOARD;
    public final SimpleEntrySet<WoodType, Block> STOOL;
    public final SimpleEntrySet<WoodType, Block> SUPPORT_BRACKET;
    public final SimpleEntrySet<WoodType, Block> TABLE;

    public BuildersAdditionModule(String modId) {
        super(modId, "bca");
        CreativeModeTab tab = ModGroup.MAIN;


        PANEL = SimpleEntrySet.builder(WoodType.class, "vertical_slab",
                        Index.OAK_VERTICAL_SLAB, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new VerticalSlab(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addTag(new ResourceLocation("quark:wooden_vertical_slabs"), Registry.BLOCK_REGISTRY)
                .addTag(new ResourceLocation("quark:wooden_vertical_slabs"), Registry.ITEM_REGISTRY)
                .addTag(new ResourceLocation("quark:planks_vertical_slab"), Registry.BLOCK_REGISTRY)
                .addTag(new ResourceLocation("quark:planks_vertical_slab"), Registry.ITEM_REGISTRY)
                .addTag(new ResourceLocation("quark:vertical_slab"), Registry.BLOCK_REGISTRY)
                .addTag(new ResourceLocation("quark:vertical_slab"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addRecipe(modRes("block/vertical_slab/oak_vertical_slab"))
                .setRenderType(() -> RenderType::solid)
                .setTab(() -> tab)
                .build();

        this.addEntry(PANEL);

        TABLE = SimpleEntrySet.builder(WoodType.class, "", "table",
                        Index.TABLE_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Table(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addRecipe(modRes("block/table/table_oak"))
                .setTab(() -> tab)
                .build();

        this.addEntry(TABLE);

        STOOL = SimpleEntrySet.builder(WoodType.class, "",  "stool",
                        Index.STOOL_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Stool(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addModelTransform(m -> modRes("stool/stool_oak"))
                .addRecipe(modRes("block/stool/stool_oak"))
                .setTab(() -> tab)
                .build();

        this.addEntry(STOOL);

        CHAIR = SimpleEntrySet.builder(WoodType.class, "",  "chair",
                        Index.CHAIR_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Chair(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addModelTransform(m -> modRes("chair/chair_oak"))
                .addRecipe(modRes("block/chair/chair_oak"))
                .setTab(() -> tab)
                .build();

        this.addEntry(CHAIR);

        HEDGE = SimpleEntrySet.builder(LeavesType.class, "",  "hedge",
                        Index.HEDGE_OAK, () -> LeavesTypeRegistry.OAK_TYPE,
                        w -> {
                            var l = w.getBlockOfThis("leaves");
                            if (l == null) return null;
                            return new Hedge(shortenedId() + "/" + w.getAppendableId(), l);
                        })
                .addModelTransform(m -> m.replaceLeavesTextures(LeavesTypeRegistry.OAK_TYPE))
                .addTag(BlockTags.MINEABLE_WITH_HOE, Registry.BLOCK_REGISTRY)
                .addModelTransform(m -> modRes("hedge/hedge_oak"))
                .addRecipe(modRes("block/hedge/hedge_oak"))
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();

        this.addEntry(HEDGE);

        COUNTER_ANDESITE = SimpleEntrySet.builder(WoodType.class, "andesite",  "counter",
                        () -> getModBlock("counter_oak_andesite"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Counter(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addModelTransform(m -> modRes("counter/counter_oak_andesite"))
                .addRecipe(modRes("block/counter/counter_oak_andesite"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .build();

        this.addEntry(COUNTER_ANDESITE);

        COUNTER_DIORITE = SimpleEntrySet.builder(WoodType.class, "diorite",  "counter",
                        () -> getModBlock("counter_oak_diorite"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Counter(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addModelTransform(m -> modRes("counter/counter_oak_diorite"))
                .addRecipe(modRes("block/counter/counter_oak_diorite"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .build();

        this.addEntry(COUNTER_DIORITE);

        COUNTER_GRANITE = SimpleEntrySet.builder(WoodType.class, "granite",  "counter",
                        () -> getModBlock("counter_oak_granite"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Counter(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addModelTransform(m -> modRes("counter/counter_oak_granite"))
                .addRecipe(modRes("block/counter/counter_oak_granite"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .build();

        this.addEntry(COUNTER_GRANITE);

        COUNTER_BLACKSTONE = SimpleEntrySet.builder(WoodType.class, "blackstone",  "counter",
                        () -> getModBlock("counter_oak_blackstone"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Counter(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addModelTransform(m -> modRes("counter/counter_oak_blackstone"))
                .addRecipe(modRes("block/counter/counter_oak_blackstone"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .build();

        this.addEntry(COUNTER_BLACKSTONE);

        COUNTER_DEEPSLATE = SimpleEntrySet.builder(WoodType.class, "deepslate",  "counter",
                        () -> getModBlock("counter_oak_deepslate"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Counter(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addModelTransform(m -> modRes("counter/counter_oak_deepslate"))
                .addRecipe(modRes("block/counter/counter_oak_deepslate"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .build();

        this.addEntry(COUNTER_DEEPSLATE);

        BOOKSHELF = SimpleEntrySet.builder(WoodType.class, "",  "bookshelf",
                        Index.BOOKSHELF_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CompatBookshelf(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addModelTransform(m -> modRes("bookshelf/bookshelf_oak"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addRecipe(modRes("block/bookshelf/bookshelf_oak"))
                .setTab(() -> tab)
                .build();

        this.addEntry(BOOKSHELF);

        SHELF = SimpleEntrySet.builder(WoodType.class, "",  "shelf",
                        Index.SHELF_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CompatShelf(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addModelTransform(m -> modRes("shelf/shelf_oak"))
                .addRecipe(modRes("block/shelf/shelf_oak"))
                .addTile(CompatShelfTileEntity::new)
                .setTab(() -> tab)
                .build();

        this.addEntry(SHELF);

        CABINET = SimpleEntrySet.builder(WoodType.class, "",  "cabinet",
                        Index.CABINET_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CompatCabinet(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addModelTransform(m -> modRes("cabinet/cabinet_oak"))
                .addRecipe(modRes("block/cabinet/cabinet_oak"))
                .setTab(() -> tab)
                .build();

        this.addEntry(CABINET);

        CUPBOARD = SimpleEntrySet.builder(WoodType.class, "",  "cupboard",
                        Index.CUPBOARD_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Cupboard(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addModelTransform(m -> modRes("cupboard/cupboard_oak"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addRecipe(modRes("block/cupboard/cupboard_oak"))
                .setTab(() -> tab)
                .build();

        this.addEntry(CUPBOARD);

        SMALL_CUPBOARD = SimpleEntrySet.builder(WoodType.class, "",  "small_cupboard",
                        Index.SMALL_CUPBOARD_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new SmallCupboard(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addModelTransform(m -> modRes("small_cupboard/small_cupboard_oak"))
                .addRecipe(modRes("block/small_cupboard/small_cupboard_oak"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .build();

        this.addEntry(SMALL_CUPBOARD);

        BENCH = SimpleEntrySet.builder(WoodType.class, "",  "bench",
                        Index.BENCH_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Bench(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addModelTransform(m -> modRes("bench/bench_oak"))
                .addRecipe(modRes("block/bench/bench_oak"))
                .setTab(() -> tab)
                .build();

        this.addEntry(BENCH);

        SUPPORT_BRACKET = SimpleEntrySet.builder(WoodType.class, "",  "support_bracket",
                        Index.SUPPORT_BRACKET_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new SupportBracket(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addModelTransform(m -> modRes("support_bracket/support_bracket_oak"))
                .addRecipe(modRes("block/support_bracket/support_bracket_oak"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .build();

        this.addEntry(SUPPORT_BRACKET);

        BEDSIDE_TABLE = SimpleEntrySet.builder(WoodType.class, "",  "bedside_table",
                        () -> getModBlock("bedside_table_oak"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BedsideTable(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addModelTransform(m -> modRes("bedside_table/bedside_table_oak"))
                .addRecipe(modRes("block/bedside_table/bedside_table_oak"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .build();

        this.addEntry(BEDSIDE_TABLE);

        ARCADE = SimpleEntrySet.builder(WoodType.class, "",  "arcade",
                        Index.ARCADE_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CompatArcade(shortenedId() + "/" + w.getAppendableId(), w.log))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addModelTransform(m -> modRes("arcade/arcade_oak"))
                .addRecipe(modRes("block/arcade/arcade_oak"))
                .setTab(() -> tab)
                .build();

        this.addEntry(ARCADE);
    }

    private class CompatBookshelf extends Bookshelf {
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
            return SHELF.getTileHolder().tile;
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

    private class CompatCabinet extends Cabinet {
        public CompatCabinet(String name, Block source) {
            super("cabinet_" + name);
        }
    }

    private class CompatArcade extends Arcade {
        public CompatArcade(String name, Block source) {
            super("arcade_" + name);
        }
    }

    @Override
    public void registerBlockColors(ClientPlatformHelper.BlockColorEvent event) {
        super.registerBlockColors(event);
        HEDGE.blocks.forEach((t, b) -> {
            event.register((s, l, p, i) -> event.getColor(t.leaves.defaultBlockState(), l, p, i), b);
        });
    }

    @Override
    public void registerItemColors(ClientPlatformHelper.ItemColorEvent event) {
        super.registerItemColors(event);
        HEDGE.blocks.forEach((t, b) -> {
            event.register((stack, tintIndex) -> event.getColor(new ItemStack(t.leaves), tintIndex), b.asItem());
        });
    }
}
