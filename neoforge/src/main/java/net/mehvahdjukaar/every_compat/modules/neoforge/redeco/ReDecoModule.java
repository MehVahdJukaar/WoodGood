package net.mehvahdjukaar.every_compat.modules.neoforge.redeco;

import com.delta.redeco.*;
import com.delta.redeco.block.custom.*;
import com.delta.redeco.block.entity.ModBlockEntities;
import com.delta.redeco.block.entity.custom.*;
import com.delta.redeco.item.ModCreativeModeTab;
import com.delta.redeco.tags.ModTags;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

//SUPPORT: v1.12.1+
public class ReDecoModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> tables;
    public final SimpleEntrySet<WoodType, Block> side_tables;
    public final SimpleEntrySet<WoodType, Block> drawers;
    public final SimpleEntrySet<WoodType, Block> coffee_tables;
    public final SimpleEntrySet<WoodType, Block> chairs;
    public final SimpleEntrySet<WoodType, Block> stools;
    public final SimpleEntrySet<WoodType, Block> benches;
    public final SimpleEntrySet<WoodType, Block> shelves;
    public final SimpleEntrySet<WoodType, Block> crates;
    public final SimpleEntrySet<WoodType, Block> display_cases;
    public final SimpleEntrySet<WoodType, Block> sword_mounts;
    public final SimpleEntrySet<WoodType, Block> pedestals;
    public final SimpleEntrySet<WoodType, Block> plank_fences;
    public final SimpleEntrySet<WoodType, Block> plank_gates;
    public final SimpleEntrySet<WoodType, Block> lattices;
    public final SimpleEntrySet<WoodType, Block> counters;
    public final SimpleEntrySet<WoodType, Block> drawer_counters;
    public final SimpleEntrySet<WoodType, Block> cabinet_counters;
    public final SimpleEntrySet<WoodType, Block> cabinets;
    public final SimpleEntrySet<WoodType, Block> polished_planks;
    public final SimpleEntrySet<WoodType, Block> polished_stairs;
    public final SimpleEntrySet<WoodType, Block> polished_slabs;

    public ReDecoModule(String modId) {
        super(modId, "rd");

        ResourceKey<CreativeModeTab> tab = ModCreativeModeTab.REDECO_TAB.getKey();

        tables = SimpleEntrySet.builder(WoodType.class, "table",
                        getModBlock("oak_table"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TableBlock(copyProperties(w, 1.0F))
                )
                .addTexture(modRes("block/table/oak_table"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(tables);

        side_tables = SimpleEntrySet.builder(WoodType.class, "side_table",
                        getModBlock("oak_side_table"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new SideTableBlock(copyProperties(w, 1.0F))
                )
                .addTile(ModBlockEntities.SIDE_TABLE_BLOCK_ENTITY)
                .addTextureM(modRes("block/sidetable/oak_side_table"), EveryCompat.res("block/rd/oak_side_table_m"))
                .addTextureM(modRes("block/drawer_oak"), EveryCompat.res("block/rd/drawer_oak_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(side_tables);

        drawers = SimpleEntrySet.builder(WoodType.class, "drawers",
                        getModBlock("oak_drawers"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DrawersBlock(copyProperties(w, 1.0F))
                )
                .addTile(ModBlockEntities.DRAWERS_BLOCK_ENTITY)
                .addTextureM(modRes("block/drawers/oak_drawers"), EveryCompat.res("block/rd/oak_drawers_m"))
                //TEXTURE: Using the side_tables' drawer_oak -above
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(drawers);

        coffee_tables = SimpleEntrySet.builder(WoodType.class, "coffee_table",
                        getModBlock("oak_coffee_table"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CoffeeTableBlock(copyProperties(w, 1.0F))
                )
                //TEXTURE: using the tables' oak_table -above
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(coffee_tables);

        chairs = SimpleEntrySet.builder(WoodType.class, "chair",
                        getModBlock("oak_chair"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ChairBlock(copyProperties(w, 1.0F))
                )
                .addTile(ModBlockEntities.CHAIR_BLOCK_ENTITY)
                .addTexture(modRes("block/chair/oak_chair"))
                .addTexture(modRes("block/chair/oak_back_0"))
                .addTexture(modRes("block/chair/oak_back_1"))
                .addTexture(modRes("block/chair/oak_back_2"))
                .addTexture(modRes("block/chair/oak_back_3"))
                .addTexture(modRes("block/chair/oak_back_4"))
                .addTexture(modRes("block/chair/oak_back_5"))
                .addTexture(modRes("block/chair/oak_back_6"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(chairs);

        stools = SimpleEntrySet.builder(WoodType.class, "stool",
                        getModBlock("oak_stool"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new StoolBlock(copyProperties(w, 1.0F))
                )
                .addTile(ModBlockEntities.STOOL_BLOCK_ENTITY)
                .addTexture(modRes("block/stool/oak_stool"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(stools);

        benches = SimpleEntrySet.builder(WoodType.class, "bench",
                        getModBlock("oak_bench"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BenchBlock(copyProperties(w, 1.0F))
                )
                .addTile(ModBlockEntities.BENCH_BLOCK_ENTITY)
                .addTexture(modRes("block/bench/oak_bench"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(benches);

        shelves = SimpleEntrySet.builder(WoodType.class, "shelf",
                        getModBlock("oak_shelf"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ShelfBlock(copyProperties(w, 1.0F))
                )
                .addTile(ModBlockEntities.SHELF_BLOCK_ENTITY)
                .addTexture(modRes("block/shelf/oak_shelf"))
                .addTexture(modRes("block/shelf/oak_shelf_support"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(shelves);

        crates = SimpleEntrySet.builder(WoodType.class, "crate",
                        getModBlock("oak_crate"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CrateBlock(copyProperties(w, 1.0F))
                )
                .addTile(ModBlockEntities.CRATE_BLOCK_ENTITY)
                .addTexture(modRes("block/crate/oak_crate"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(ModTags.Items.UNCRATEABLE, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(crates);

        display_cases = SimpleEntrySet.builder(WoodType.class, "display_case",
                        getModBlock("oak_display_case"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DisplayCaseBlock(copyProperties(w, 1.0F).noOcclusion())
                )
                .addTile(ModBlockEntities.DISPLAY_CASE_BLOCK_ENTITY)
                .addTexture(modRes("block/displaycase/oak_display_case"))
                .addTexture(modRes("block/displaycase/oak_inside"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(display_cases);

        sword_mounts = SimpleEntrySet.builder(WoodType.class, "sword_mount",
                        getModBlock("oak_sword_mount"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new SwordMountBlock(copyProperties(w, 1.0F).noOcclusion())
                )
                .addTile(ModBlockEntities.SWORD_MOUNT_BLOCK_ENTITY)
                .addTexture(modRes("block/swordmount/oak_sword_mount"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(sword_mounts);

        pedestals = SimpleEntrySet.builder(WoodType.class, "pedestal",
                        getModBlock("oak_pedestal"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new PedestalBlock(copyProperties(w, 1.0F).noOcclusion())
                )
                .addTile(ModBlockEntities.PEDESTAL_BLOCK_ENTITY)
                .addTexture(modRes("block/pedestal/oak_pedestal"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(pedestals);

        plank_fences = SimpleEntrySet.builder(WoodType.class, "plank_fence",
                        getModBlock("oak_plank_fence"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new PlankFenceBlock(copyProperties(w, 1.0F))
                )
                .addTexture(modRes("block/plankfence/oak_plank_fence"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.FENCES, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(plank_fences);

        plank_gates = SimpleEntrySet.builder(WoodType.class, "plank_gate",
                        getModBlock("oak_plank_gate"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new PlankGateBlock(copyProperties(w, 1.0F),
                                SoundEvents.FENCE_GATE_CLOSE, SoundEvents.FENCE_GATE_OPEN)
                )
                //TEXTURE: using plank_fences' oak_plank_fence -above
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(plank_gates);

        lattices = SimpleEntrySet.builder(WoodType.class, "lattice",
                        getModBlock("oak_lattice"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new LatticeBlock(Utils.copyPropertySafe(Blocks.LADDER)
                                .strength(1.0F).noOcclusion().sound(SoundType.WOOD))
                )
                .addTexture(modRes("block/lattice/oak_lattice"))
                .addTexture(modRes("item/oak_lattice"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.FALL_DAMAGE_RESETTING, Registries.BLOCK)
                .addTag(BlockTags.CLIMBABLE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(lattices);

        counters = SimpleEntrySet.builder(WoodType.class, "counter",
                        getModBlock("oak_counter"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CounterBlock(copyProperties(w, 1.0F))
                )
                .addTextureM(modRes("block/counters/oak_counters"), EveryCompat.res("block/rd/oak_counters_m"))
                //TEXTURE: Using polished_planks' polished_oak_planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(counters);

        drawer_counters = SimpleEntrySet.builder(WoodType.class, "drawer_counter",
                        getModBlock("oak_drawer_counter"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DrawerCounterBlock(copyProperties(w, 1.0F))
                )
                .addTile(ModBlockEntities.DRAWERS_BLOCK_ENTITY)
                //TEXTURE: Using counters' oak_counters -above
                //TEXTURE: Using drawers' drawer_oak -above
                //TEXTURE: Using polished_planks' polished_oak_planks -below
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(drawer_counters);

        cabinet_counters = SimpleEntrySet.builder(WoodType.class, "cabinet_counter",
                        getModBlock("oak_cabinet_counter"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CabinetCounterBlock(copyProperties(w, 1.0F))
                )
                .addTile(ModBlockEntities.CABINET_BLOCK_ENTITY)
                //TEXTURE: Using counters' oak_counters -above
                //TEXTURE: Using polished_planks' polished_oak_planks -below
                //TEXTURE: Using cabinets' cabinet_doors_oak -below
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(cabinet_counters);

        cabinets = SimpleEntrySet.builder(WoodType.class, "cabinet",
                        getModBlock("oak_cabinet"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CabinetBlock(copyProperties(w, 1.0F))
                )
                .addTile(ModBlockEntities.CABINET_BLOCK_ENTITY)
                .addTextureM(modRes("block/cabinet/oak_cabinet"), EveryCompat.res("block/rd/oak_cabinet_m"))
                .addTextureM(modRes("block/cabinet_doors_oak"), EveryCompat.res("block/rd/cabinet_doors_oak_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(cabinets);

        polished_planks = SimpleEntrySet.builder(WoodType.class, "planks", "polished",
                        getModBlock("polished_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new PolishedPlankBlock(copyProperties(w, 1.0F))
                )
                .addTexture(modRes("block/polishedplanks/polished_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .setTabKey(tab)
                .addRecipe(modRes("oak_polished_planks"))
                .build();
        this.addEntry(polished_planks);

        polished_stairs = SimpleEntrySet.builder(WoodType.class, "stairs", "polished",
                        getModBlock("polished_oak_stairs"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new PolishedPlankStairs(() -> copyState(w), stairsProperties(w))
                )
                //TEXTURE: Using polished_planks' polished_oak_planks -above
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.STAIRS, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_STAIRS, Registries.BLOCK)
                .addTag(ItemTags.STAIRS, Registries.ITEM)
                .addTag(ItemTags.WOODEN_STAIRS, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(polished_stairs);

        polished_slabs = SimpleEntrySet.builder(WoodType.class, "slab", "polished",
                        getModBlock("polished_oak_slab"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new PolishedPlankSlab(copyProperties(w, 1.0F))
                )
                //TEXTURE: Using polished_planks' polished_oak_planks -above
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.SLABS, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_SLABS, Registries.BLOCK)
                .addTag(ItemTags.SLABS, Registries.ITEM)
                .addTag(ItemTags.WOODEN_SLABS, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(polished_slabs);


    }

    /**
     * @return Properties of planks, strength and SoundType
     */
    public BlockBehaviour.Properties copyProperties(WoodType woodType, float strength) {
        return Utils.copyPropertySafe(woodType.planks).strength(strength).sound(SoundType.WOOD);
    }

    public BlockBehaviour.Properties stairsProperties(WoodType woodType) {
        Block stairs = woodType.getBlockOfThis("stairs");
        return (stairs != null) ? Utils.copyPropertySafe(stairs): Utils.copyPropertySafe(Blocks.OAK_STAIRS) ;
    }
    public BlockState copyState(WoodType woodType) {
        Block stairs = woodType.getBlockOfThis("stairs");
        return (stairs != null) ? stairs.defaultBlockState() : Blocks.OAK_STAIRS.defaultBlockState();
    }

    @Override
    public void registerBlockEntityRenderers(ClientHelper.BlockEntityRendererEvent event) {
        super.registerBlockEntityRenderers(event);
        event.register(crates.getTile(CrateBlockEntity.class), CrateRenderer::new);
        event.register(display_cases.getTile(DisplayCaseBlockEntity.class), DisplayCaseRenderer::new);
        event.register(pedestals.getTile(PedestalBlockEntity.class), PedestalRenderer::new);
        event.register(shelves.getTile(ShelfBlockEntity.class), ShelfRenderer::new);
    }

}