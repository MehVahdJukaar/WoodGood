package net.mehvahdjukaar.every_compat.modules.project_brazier;

import com.mrcrayfish.backpacked.block.ShelfBlock;
import net.dark_roleplay.marg.common.material.MargMaterial;
import net.dark_roleplay.marg.common.material.MaterialCondition;
import net.dark_roleplay.projectbrazier.feature.blockentities.ZiplineBlockEntity;
import net.dark_roleplay.projectbrazier.feature.blocks.*;
import net.dark_roleplay.projectbrazier.feature.blocks.templates.AxisDecoBlock;
import net.dark_roleplay.projectbrazier.feature.blocks.templates.HAxisDecoBlock;
import net.dark_roleplay.projectbrazier.feature.blocks.templates.HFacedDecoBlock;
import net.dark_roleplay.projectbrazier.feature.registrars.BrazierBlocks;
import net.dark_roleplay.projectbrazier.feature.registrars.BrazierCreativeTabs;
import net.dark_roleplay.projectbrazier.feature_client.blockentityrenderers.ZiplineBlockEntityRenderer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.modules.backpacked.BackpackedModule;
import net.mehvahdjukaar.selene.block_set.BlockType;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.event.EntityRenderersEvent;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;


public class ProjectBrazierModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> ARMREST_CHAIRS;
    public final SimpleEntrySet<WoodType, Block> BENCHES;
    public final SimpleEntrySet<WoodType, Block> CLOSED_BARRELS;
    public final SimpleEntrySet<WoodType, Block> CROSS_LATTICES;
    public final SimpleEntrySet<WoodType, Block> CROSS_LATTICES_CENTERED;
    public final SimpleEntrySet<WoodType, Block> DENSE_VERTICAL_LATTICES;
    public final SimpleEntrySet<WoodType, Block> DENSE_VERTICAL_LATTICES_CENTERED;
    public final SimpleEntrySet<WoodType, Block> DIAMOND_LATTICES;
    public final SimpleEntrySet<WoodType, Block> DIAMOND_LATTICES_CENTERED;
    public final SimpleEntrySet<WoodType, Block> FIREWOODS;
    public final SimpleEntrySet<WoodType, Block> FLOWER_BARRELS;
    public final SimpleEntrySet<WoodType, Block> FLOWER_BUCKETS;
    public final SimpleEntrySet<WoodType, Block> GRID_LATTICES;
    public final SimpleEntrySet<WoodType, Block> GRID_LATTICES_CENTERED;
    public final SimpleEntrySet<WoodType, Block> HOLLOW_LOGS;
    public final SimpleEntrySet<WoodType, Block> LOG_BENCHES;
    public final SimpleEntrySet<WoodType, Block> LOG_CHAIRS;
    public final SimpleEntrySet<WoodType, Block> OPEN_BARRELS;
    public final SimpleEntrySet<WoodType, Block> PLANK_CHAIRS;
//    public final SimpleEntrySet<WoodType, Block> PLATFORMS;
    public final SimpleEntrySet<WoodType, Block> PLATFORMS_BOTTOM;
    public final SimpleEntrySet<WoodType, Block> PLATFORMS_BOTTOM_STAIRS;
    public final SimpleEntrySet<WoodType, Block> PLATFORMS_TOP;
    public final SimpleEntrySet<WoodType, Block> PLATFORMS_TOP_STAIRS;
    public final SimpleEntrySet<WoodType, Block> POLSTERED_BENCHES_ORANGE;
    public final SimpleEntrySet<WoodType, Block> POLSTERED_BENCHES_WHITE;
    public final SimpleEntrySet<WoodType, Block> SOLID_CHAIRS;
    public final SimpleEntrySet<WoodType, Block> SOLID_TABLES;
    public final SimpleEntrySet<WoodType, Block> STOOLS;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_HOLLOW_LOGS;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_LOG_BENCHES;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_LOG_CHAIRS;
    public final SimpleEntrySet<WoodType, Block> VERTICAL_LATTICES;
    public final SimpleEntrySet<WoodType, Block> VERTICAL_LATTICES_CENTERED;
    public final SimpleEntrySet<WoodType, Block> ZIPLINE_ANCHORS;

    private MargMaterial material;

    public ProjectBrazierModule(String modId) {
        super(modId, "pb");

        ARMREST_CHAIRS = SimpleEntrySet.builder(WoodType.class, "armrest_chair",
                        () -> this.getModBlock("oak_armrest_chair"), () -> WoodType.OAK_WOOD_TYPE,
                        ifCond(BrazierBlocks.WOOD_CHAIR_CON, w ->
                                new WoodChairBlock(BlockBehaviour.Properties.copy(w.planks)
                                        .strength(2.0F, 3.0F).noOcclusion(),
                                        "armrest_chair")))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(BrazierCreativeTabs::decor)
                .defaultRecipe()
                .build();

        this.addEntry(ARMREST_CHAIRS);

        BENCHES = SimpleEntrySet.builder(WoodType.class, "bench",
                        () -> this.getModBlock("oak_bench"), () -> WoodType.OAK_WOOD_TYPE,
                        ifCond(BrazierBlocks.WOOD_CHAIR_BENCH, w ->
                                new WoodBenchBlock(BlockBehaviour.Properties.copy(w.planks)
                                        .strength(2.0F, 3.0F).noOcclusion(),
                                        "default_wood_bench", "positive_wood_bench",
                                        "negative_wood_bench", "centered_wood_bench")))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(BrazierCreativeTabs::decor)
                .defaultRecipe()
                .build();

        this.addEntry(BENCHES);

        CLOSED_BARRELS = SimpleEntrySet.builder(WoodType.class, "closed_barrel",
                        () -> this.getModBlock("oak_closed_barrel"), () -> WoodType.OAK_WOOD_TYPE,
                        ifCond(BrazierBlocks.BARREL_CON, w ->
                                new BarrelBlock(material, BlockBehaviour.Properties.copy(w.planks)
                                        .strength(2.0F, 3.0F).noOcclusion(),
                                        "closed_barrel", true)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(BrazierCreativeTabs::decor)
                .defaultRecipe()
                .build();

        this.addEntry(CLOSED_BARRELS);

        CROSS_LATTICES = SimpleEntrySet.builder(WoodType.class, "cross_lattice",
                        () -> this.getModBlock("oak_cross_lattice"), () -> WoodType.OAK_WOOD_TYPE,
                        ifCond(BrazierBlocks.WOOD_LATTICE_CON, w ->
                                new FacedLatticeBlock(BlockBehaviour.Properties.copy(w.planks)
                                        .strength(2.0F, 3.0F).noOcclusion(),
                                        "lattice")))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(BrazierCreativeTabs::decor)
                .defaultRecipe()
                .build();

        this.addEntry(CROSS_LATTICES);

        CROSS_LATTICES_CENTERED = SimpleEntrySet.builder(WoodType.class, "cross_lattice_centered",
                        () -> this.getModBlock("oak_cross_lattice_centered"), () -> WoodType.OAK_WOOD_TYPE,
                        ifCond(BrazierBlocks.WOOD_LATTICE_CON, w ->
                                new AxisLatticeBlock(BlockBehaviour.Properties.copy(w.planks)
                                        .strength(2.0F, 3.0F).noOcclusion(),
                                        "lattice_centered")))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(BrazierCreativeTabs::decor)
                .defaultRecipe()
                .noItem()
                .build();

        this.addEntry(CROSS_LATTICES_CENTERED);

        DENSE_VERTICAL_LATTICES = SimpleEntrySet.builder(WoodType.class, "dense_vertical_lattice",
                        () -> this.getModBlock("oak_dense_vertical_lattice"), () -> WoodType.OAK_WOOD_TYPE,
                        ifCond(BrazierBlocks.WOOD_LATTICE_CON, w ->
                                new FacedLatticeBlock(BlockBehaviour.Properties.copy(w.planks)
                                        .strength(2.0F, 3.0F).noOcclusion(),
                                        "lattice")))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(BrazierCreativeTabs::decor)
                .defaultRecipe()
                .build();

        this.addEntry(DENSE_VERTICAL_LATTICES);

        DENSE_VERTICAL_LATTICES_CENTERED = SimpleEntrySet.builder(WoodType.class, "dense_vertical_lattice_centered",
                        () -> this.getModBlock("oak_dense_vertical_lattice_centered"), () -> WoodType.OAK_WOOD_TYPE,
                        ifCond(BrazierBlocks.WOOD_LATTICE_CON, w ->
                                new AxisLatticeBlock(BlockBehaviour.Properties.copy(w.planks)
                                        .strength(2.0F, 3.0F).noOcclusion(),
                                        "lattice_centered")))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(BrazierCreativeTabs::decor)
                .defaultRecipe()
                .noItem()
                .build();

        this.addEntry(DENSE_VERTICAL_LATTICES_CENTERED);

        DIAMOND_LATTICES = SimpleEntrySet.builder(WoodType.class, "diamond_lattice",
                        () -> this.getModBlock("oak_diamond_lattice"), () -> WoodType.OAK_WOOD_TYPE,
                        ifCond(BrazierBlocks.WOOD_LATTICE_CON, w ->
                                new FacedLatticeBlock(BlockBehaviour.Properties.copy(w.planks)
                                        .strength(2.0F, 3.0F).noOcclusion(),
                                        "lattice")))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(BrazierCreativeTabs::decor)
                .defaultRecipe()
                .build();

        this.addEntry(DIAMOND_LATTICES);

        DIAMOND_LATTICES_CENTERED = SimpleEntrySet.builder(WoodType.class, "diamond_lattice_centered",
                        () -> this.getModBlock("oak_diamond_lattice_centered"), () -> WoodType.OAK_WOOD_TYPE,
                        ifCond(BrazierBlocks.WOOD_LATTICE_CON, w ->
                                new AxisLatticeBlock(BlockBehaviour.Properties.copy(w.planks)
                                        .strength(2.0F, 3.0F).noOcclusion(),
                                        "lattice_centered")))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(BrazierCreativeTabs::decor)
                .defaultRecipe()
                .noItem()
                .build();

        this.addEntry(DIAMOND_LATTICES_CENTERED);

        FIREWOODS = SimpleEntrySet.builder(WoodType.class, "firewood",
                        () -> this.getModBlock("oak_firewood"), () -> WoodType.OAK_WOOD_TYPE,
                        ifCond(BrazierBlocks.FIREWOOD_CON, w ->
                                new HAxisDecoBlock(BlockBehaviour.Properties.copy(w.planks)
                                        .strength(2.0F, 3.0F).noOcclusion(),
                                        "full_block")))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(BrazierCreativeTabs::decor)
                .defaultRecipe()
                .build();

        this.addEntry(FIREWOODS);

        FLOWER_BARRELS = SimpleEntrySet.builder(WoodType.class, "flower_barrel",
                        () -> this.getModBlock("oak_flower_barrel"), () -> WoodType.OAK_WOOD_TYPE,
                        ifCond(BrazierBlocks.BARREL_CON, w ->
                                new FlowerContainerBlock(BlockBehaviour.Properties.copy(w.planks)
                                        .strength(2.0F, 3.0F).noOcclusion(),
                                        "flower_barrel", "flower_barrel_flower_area")))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(BrazierCreativeTabs::decor)
                .defaultRecipe()
                .build();

        this.addEntry(FLOWER_BARRELS);

        FLOWER_BUCKETS = SimpleEntrySet.builder(WoodType.class, "flower_bucket",
                        () -> this.getModBlock("oak_flower_bucket"), () -> WoodType.OAK_WOOD_TYPE,
                        ifCond(BrazierBlocks.WOOD_BUCKET_CON, w ->
                                new FlowerContainerBlock(BlockBehaviour.Properties.copy(w.planks)
                                        .strength(2.0F, 3.0F).noOcclusion(),
                                        "flower_bucket", "flower_bucket_flower_area")))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(BrazierCreativeTabs::decor)
                .defaultRecipe()
                .build();

        this.addEntry(FLOWER_BUCKETS);

        GRID_LATTICES = SimpleEntrySet.builder(WoodType.class, "grid_lattice",
                        () -> this.getModBlock("oak_grid_lattice"), () -> WoodType.OAK_WOOD_TYPE,
                        ifCond(BrazierBlocks.WOOD_LATTICE_CON, w ->
                                new FacedLatticeBlock(BlockBehaviour.Properties.copy(w.planks)
                                        .strength(2.0F, 3.0F).noOcclusion(),
                                        "lattice")))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(BrazierCreativeTabs::decor)
                .defaultRecipe()
                .build();

        this.addEntry(GRID_LATTICES);

        GRID_LATTICES_CENTERED = SimpleEntrySet.builder(WoodType.class, "grid_lattice_centered",
                        () -> this.getModBlock("oak_grid_lattice_centered"), () -> WoodType.OAK_WOOD_TYPE,
                        ifCond(BrazierBlocks.WOOD_LATTICE_CON, w ->
                                new FacedLatticeBlock(BlockBehaviour.Properties.copy(w.planks)
                                        .strength(2.0F, 3.0F).noOcclusion(),
                                        "lattice_centered")))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(BrazierCreativeTabs::decor)
                .defaultRecipe()
                .noItem()
                .build();

        this.addEntry(GRID_LATTICES_CENTERED);

        HOLLOW_LOGS = SimpleEntrySet.builder(WoodType.class, "log", "hollow",
                        () -> this.getModBlock("hollow_oak_log"), () -> WoodType.OAK_WOOD_TYPE,
                        ifCond(BrazierBlocks.STRIPPED_LOG_CON, w ->
                                new AxisDecoBlock(BlockBehaviour.Properties.copy(w.planks)
                                        .strength(2.0F, 3.0F).noOcclusion(),
                                        "hollow_log")))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(BrazierCreativeTabs::decor)
                .defaultRecipe()
                .build();

        this.addEntry(HOLLOW_LOGS);

        LOG_BENCHES = SimpleEntrySet.builder(WoodType.class, "log_bench",
                        () -> this.getModBlock("oak_log_bench"), () -> WoodType.OAK_WOOD_TYPE,
                        ifCond(BrazierBlocks.LOG_CHAIR_CON, w ->
                                new LogBenchBlock(BlockBehaviour.Properties.copy(w.planks)
                                        .strength(2.0F, 3.0F).noOcclusion(),
                                        "log_bench_single", "log_bench_multi")))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(BrazierCreativeTabs::decor)
                .defaultRecipe()
                .build();

        this.addEntry(LOG_BENCHES);

        LOG_CHAIRS = SimpleEntrySet.builder(WoodType.class, "log_chair",
                        () -> this.getModBlock("oak_log_chair"), () -> WoodType.OAK_WOOD_TYPE,
                        ifCond(BrazierBlocks.LOG_CHAIR_CON, w ->
                                new WoodChairBlock(BlockBehaviour.Properties.copy(w.planks)
                                        .strength(2.0F, 3.0F).noOcclusion(),
                                        "log_chair")))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(BrazierCreativeTabs::decor)
                .defaultRecipe()
                .build();

        this.addEntry(LOG_CHAIRS);

        OPEN_BARRELS = SimpleEntrySet.builder(WoodType.class, "open_barrel",
                        () -> this.getModBlock("oak_open_barrel"), () -> WoodType.OAK_WOOD_TYPE,
                        ifCond(BrazierBlocks.BARREL_CON, w ->
                                new BarrelBlock(material, BlockBehaviour.Properties.copy(w.planks)
                                        .strength(2.0F, 3.0F).noOcclusion(),
                                        "open_barrel", false)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(BrazierCreativeTabs::decor)
                .defaultRecipe()
                .build();

        this.addEntry(OPEN_BARRELS);

        PLANK_CHAIRS = SimpleEntrySet.builder(WoodType.class, "plank_chair",
                        () -> this.getModBlock("oak_plank_chair"), () -> WoodType.OAK_WOOD_TYPE,
                        ifCond(BrazierBlocks.WOOD_CHAIR_CON, w ->
                                new WoodChairBlock(BlockBehaviour.Properties.copy(w.planks)
                                        .strength(2.0F, 3.0F).noOcclusion(),
                                        "simple_chair")))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(BrazierCreativeTabs::decor)
                .defaultRecipe()
                .build();

        this.addEntry(PLANK_CHAIRS);

//        PLATFORMS = SimpleEntrySet.builder(WoodType.class, "platform",
//                        () -> this.getModBlock("oak_platform"), () -> WoodType.OAK_WOOD_TYPE,
//                        ifCond(BrazierBlocks.WOOD_PLATFORM_CON, w ->
//                                new HAxisDecoBlock(BlockBehaviour.Properties.copy(w.planks)
//                                        .strength(2.0F, 3.0F).noOcclusion(),
//                                        "wood_platform")))
//                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
//                .setTab(BrazierCreativeTabs::decor)
//                .defaultRecipe()
//                .build();
//
//        this.addEntry(PLATFORMS);

        PLATFORMS_BOTTOM = SimpleEntrySet.builder(WoodType.class, "platform", "bottom",
                        () -> this.getModBlock("bottom_oak_platform"), () -> WoodType.OAK_WOOD_TYPE,
                        ifCond(BrazierBlocks.WOOD_PLATFORM_CON, w ->
                                new HAxisDecoBlock(BlockBehaviour.Properties.copy(w.planks)
                                        .strength(2.0F, 3.0F).noOcclusion(),
                                        "bottom_wood_platform")))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(BrazierCreativeTabs::decor)
                .defaultRecipe()
                .noItem()
                .build();

        this.addEntry(PLATFORMS_BOTTOM);

        PLATFORMS_BOTTOM_STAIRS = SimpleEntrySet.builder(WoodType.class, "platform_stairs", "bottom",
                        () -> this.getModBlock("bottom_oak_platform_stairs"), () -> WoodType.OAK_WOOD_TYPE,
                        ifCond(BrazierBlocks.WOOD_PLATFORM_CON, w ->
                                new HFacedDecoBlock(BlockBehaviour.Properties.copy(w.planks)
                                        .strength(2.0F, 3.0F).noOcclusion(),
                                        "bottom_wood_platform_stairs")))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(BrazierCreativeTabs::decor)
                .defaultRecipe()
                .noItem()
                .build();

        this.addEntry(PLATFORMS_BOTTOM_STAIRS);

        PLATFORMS_TOP = SimpleEntrySet.builder(WoodType.class, "platform", "top",
                        () -> this.getModBlock("top_oak_platform"), () -> WoodType.OAK_WOOD_TYPE,
                        ifCond(BrazierBlocks.WOOD_PLATFORM_CON, w ->
                                new HAxisDecoBlock(BlockBehaviour.Properties.copy(w.planks)
                                        .strength(2.0F, 3.0F).noOcclusion(),
                                        "top_wood_platform")))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(BrazierCreativeTabs::decor)
                .defaultRecipe()
                .build();

        this.addEntry(PLATFORMS_TOP);

        PLATFORMS_TOP_STAIRS = SimpleEntrySet.builder(WoodType.class, "platform_stairs", "top",
                        () -> this.getModBlock("top_oak_platform_stairs"), () -> WoodType.OAK_WOOD_TYPE,
                        ifCond(BrazierBlocks.WOOD_PLATFORM_CON, w ->
                                new HFacedDecoBlock(BlockBehaviour.Properties.copy(w.planks)
                                        .strength(2.0F, 3.0F).noOcclusion(),
                                        "top_wood_platform_stairs")))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(BrazierCreativeTabs::decor)
                .defaultRecipe()
                .noItem()
                .build();

        this.addEntry(PLATFORMS_TOP_STAIRS);

        POLSTERED_BENCHES_ORANGE = SimpleEntrySet.builder(WoodType.class, "bench", "orange_polstered",
                        () -> this.getModBlock("orange_polstered_oak_bench"), () -> WoodType.OAK_WOOD_TYPE,
                        ifCond(BrazierBlocks.WOOD_CHAIR_BENCH, w ->
                                new WoodBenchBlock(BlockBehaviour.Properties.copy(w.planks)
                                        .strength(2.0F, 3.0F).noOcclusion(),
                                        "default_polstered_wood_bench", "positive_polstered_wood_bench",
                                        "negative_polstered_wood_bench", "centered_polstered_wood_bench")))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(BrazierCreativeTabs::decor)
                .defaultRecipe()
                .build();

        this.addEntry(POLSTERED_BENCHES_ORANGE);

        POLSTERED_BENCHES_WHITE = SimpleEntrySet.builder(WoodType.class, "bench", "white_polstered",
                        () -> this.getModBlock("white_polstered_oak_bench"), () -> WoodType.OAK_WOOD_TYPE,
                        ifCond(BrazierBlocks.WOOD_CHAIR_BENCH, w ->
                                new WoodBenchBlock(BlockBehaviour.Properties.copy(w.planks)
                                        .strength(2.0F, 3.0F).noOcclusion(),
                                        "default_polstered_wood_bench", "positive_polstered_wood_bench",
                                        "negative_polstered_wood_bench", "centered_polstered_wood_bench")))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(BrazierCreativeTabs::decor)
                .defaultRecipe()
                .build();

        this.addEntry(POLSTERED_BENCHES_WHITE);

        SOLID_CHAIRS = SimpleEntrySet.builder(WoodType.class, "solid_chair",
                        () -> this.getModBlock("oak_solid_chair"), () -> WoodType.OAK_WOOD_TYPE,
                        ifCond(BrazierBlocks.WOOD_CHAIR_CON, w ->
                                new WoodChairBlock(BlockBehaviour.Properties.copy(w.planks)
                                        .strength(2.0F, 3.0F).noOcclusion(),
                                        "simple_chair")))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(BrazierCreativeTabs::decor)
                .defaultRecipe()
                .build();

        this.addEntry(SOLID_CHAIRS);

        SOLID_TABLES = SimpleEntrySet.builder(WoodType.class, "table", "solid",
                        () -> this.getModBlock("solid_oak_table"), () -> WoodType.OAK_WOOD_TYPE,
                        ifCond(BrazierBlocks.SOLID_TABLE_CON, w ->
                                new PaneConnectedBlock(BlockBehaviour.Properties.copy(w.planks)
                                        .strength(2.0F, 3.0F).noOcclusion(),
                                        "solid_table")))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(BrazierCreativeTabs::decor)
                .defaultRecipe()
                .build();

        this.addEntry(SOLID_TABLES);

        STOOLS = SimpleEntrySet.builder(WoodType.class, "stool",
                        () -> this.getModBlock("oak_stool"), () -> WoodType.OAK_WOOD_TYPE,
                        ifCond(BrazierBlocks.WOOD_CHAIR_CON, w ->
                                new WoodStoolBlock(BlockBehaviour.Properties.copy(w.planks)
                                        .strength(2.0F, 3.0F).noOcclusion(),
                                        "stool")))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(BrazierCreativeTabs::decor)
                .defaultRecipe()
                .build();

        this.addEntry(STOOLS);

        STRIPPED_HOLLOW_LOGS = SimpleEntrySet.builder(WoodType.class, "log", "stripped_hollow",
                        () -> this.getModBlock("stripped_hollow_oak_log"), () -> WoodType.OAK_WOOD_TYPE,
                        ifCond(BrazierBlocks.STRIPPED_LOG_CON, w ->
                                new AxisDecoBlock(BlockBehaviour.Properties.copy(w.planks)
                                        .strength(2.0F, 3.0F).noOcclusion(),
                                        "hollow_log")))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(BrazierCreativeTabs::decor)
                .defaultRecipe()
                .build();

        this.addEntry(STRIPPED_HOLLOW_LOGS);

        STRIPPED_LOG_BENCHES = SimpleEntrySet.builder(WoodType.class, "log_bench", "stripped",
                        () -> this.getModBlock("stripped_oak_log_bench"), () -> WoodType.OAK_WOOD_TYPE,
                        ifCond(BrazierBlocks.STRIPPED_LOG_CHAIR_CON, w ->
                                new LogBenchBlock(BlockBehaviour.Properties.copy(w.planks)
                                        .strength(2.0F, 3.0F).noOcclusion(),
                                        "log_bench_single", "log_bench_multi")))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(BrazierCreativeTabs::decor)
                .defaultRecipe()
                .build();

        this.addEntry(STRIPPED_LOG_BENCHES);

        STRIPPED_LOG_CHAIRS = SimpleEntrySet.builder(WoodType.class, "log_chair", "stripped",
                        () -> this.getModBlock("stripped_oak_log_chair"), () -> WoodType.OAK_WOOD_TYPE,
                        ifCond(BrazierBlocks.STRIPPED_LOG_CHAIR_CON, w ->
                                new WoodChairBlock(BlockBehaviour.Properties.copy(w.planks)
                                        .strength(2.0F, 3.0F).noOcclusion(),
                                        "log_chair")))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(BrazierCreativeTabs::decor)
                .defaultRecipe()
                .build();

        this.addEntry(STRIPPED_LOG_CHAIRS);

        VERTICAL_LATTICES = SimpleEntrySet.builder(WoodType.class, "vertical_lattice",
                        () -> this.getModBlock("oak_vertical_lattice"), () -> WoodType.OAK_WOOD_TYPE,
                        ifCond(BrazierBlocks.WOOD_LATTICE_CON, w ->
                                new FacedLatticeBlock(BlockBehaviour.Properties.copy(w.planks)
                                        .strength(2.0F, 3.0F).noOcclusion(),
                                        "lattice")))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(BrazierCreativeTabs::decor)
                .defaultRecipe()
                .build();

        this.addEntry(VERTICAL_LATTICES);

        VERTICAL_LATTICES_CENTERED = SimpleEntrySet.builder(WoodType.class, "vertical_latticee_centered",
                        () -> this.getModBlock("oak_vertical_lattice_centered"), () -> WoodType.OAK_WOOD_TYPE,
                        ifCond(BrazierBlocks.WOOD_LATTICE_CON, w ->
                                new AxisLatticeBlock(BlockBehaviour.Properties.copy(w.planks)
                                        .strength(2.0F, 3.0F).noOcclusion(),
                                        "latticee_centered")))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(BrazierCreativeTabs::decor)
                .defaultRecipe()
                .noItem()
                .build();

        this.addEntry(VERTICAL_LATTICES_CENTERED);

        ZIPLINE_ANCHORS = SimpleEntrySet.builder(WoodType.class, "zipline_anchors",
                        () -> this.getModBlock("oak_zipline_anchor"), () -> WoodType.OAK_WOOD_TYPE,
                        ifCond(BrazierBlocks.STRIPPED_LOG_CON, w ->
                                new CompatZiplineBlock(BlockBehaviour.Properties.copy(w.planks)
                                        .strength(2.0F, 3.0F).noOcclusion(),
                                        "placeholder")))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTile(CompatZiplineBlockEntity::new)
                .setTab(BrazierCreativeTabs::decor)
                .defaultRecipe()
                .build();

        this.addEntry(ZIPLINE_ANCHORS);

//        MargMaterial material;
//        Iterator var4 = BrazierBlocks.WOOD_LATTICE_CON.iterator();
//
//        while(var4.hasNext()) {
//            material = (MargMaterial)var4.next();
//            ((FacedLatticeBlock)CROSS_LATTICES.blocks.get(material)).initOtherBlock((Block)CROSS_LATTICES_CENTERED.blocks.get(material));
//            ((AxisLatticeBlock)CROSS_LATTICES_CENTERED.blocks.get(material)).initOtherBlock((Block)CROSS_LATTICES.blocks.get(material));
//            ((FacedLatticeBlock)DENSE_VERTICAL_LATTICES.blocks.get(material)).initOtherBlock((Block)DENSE_VERTICAL_LATTICES_CENTERED.blocks.get(material));
//            ((AxisLatticeBlock)DENSE_VERTICAL_LATTICES_CENTERED.blocks.get(material)).initOtherBlock((Block)DENSE_VERTICAL_LATTICES.blocks.get(material));
//            ((FacedLatticeBlock)DIAMOND_LATTICES.blocks.get(material)).initOtherBlock((Block)DIAMOND_LATTICES_CENTERED.blocks.get(material));
//            ((AxisLatticeBlock)DIAMOND_LATTICES_CENTERED.blocks.get(material)).initOtherBlock((Block)DIAMOND_LATTICES.blocks.get(material));
//            ((FacedLatticeBlock)GRID_LATTICES.blocks.get(material)).initOtherBlock((Block)GRID_LATTICES_CENTERED.blocks.get(material));
//            ((AxisLatticeBlock)GRID_LATTICES_CENTERED.blocks.get(material)).initOtherBlock((Block)GRID_LATTICES.blocks.get(material));
//            ((FacedLatticeBlock)VERTICAL_LATTICES.blocks.get(material)).initOtherBlock((Block)VERTICAL_LATTICES_CENTERED.blocks.get(material));
//            ((AxisLatticeBlock)VERTICAL_LATTICES_CENTERED.blocks.get(material)).initOtherBlock((Block)VERTICAL_LATTICES.blocks.get(material));
//        }
    }

    @Override
    public void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer((BlockEntityType<ZiplineBlockEntity>) (ZIPLINE_ANCHORS.getTileHolder().tile), ZiplineBlockEntityRenderer::new);
    }

    class CompatZiplineBlockEntity extends ZiplineBlockEntity {

        public CompatZiplineBlockEntity(BlockPos pos, BlockState state) {
            super(pos, state);
        }

        @Override
        public BlockEntityType<?> getType() {
            return ZIPLINE_ANCHORS.getTileHolder().tile;
        }
    }

    private class CompatZiplineBlock extends ZiplineAnchorBlock {
        public CompatZiplineBlock(Properties properties, String shape) {
            super(properties, shape);
        }

        public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
            return new CompatZiplineBlockEntity(pos, state);
        }
    }

    public <T extends BlockType, B extends Block> Function<T, @Nullable B> ifCond(MaterialCondition cond, Function<T, B> supplier) {
        return ifHasChild(supplier, cond.getTextures().toArray(String[]::new));
    }
}
