package net.mehvahdjukaar.every_compat.modules.neoforge.redeco;

import com.delta.redeco.CrateRenderer;
import com.delta.redeco.DisplayCaseRenderer;
import com.delta.redeco.PedestalRenderer;
import com.delta.redeco.ShelfRenderer;
import com.delta.redeco.block.custom.*;
import com.delta.redeco.block.entity.custom.CrateBlockEntity;
import com.delta.redeco.block.entity.custom.DisplayCaseBlockEntity;
import com.delta.redeco.block.entity.custom.PedestalBlockEntity;
import com.delta.redeco.block.entity.custom.ShelfBlockEntity;
import com.delta.redeco.tags.ModTags;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

//SUPPORT: v1.12.1+
public class ReDecoModule extends EveryCompatModule {

    public final SimpleEntrySet<WoodType, Block> tables,
            side_tables,
            drawers,
            coffee_tables,
            chairs,
            stools,
            benches,
            shelves,
            crates,
            display_cases,
            sword_mounts,
            pedestals,
            plank_fences,
            plank_gates,
            lattices,
            counters,
            drawer_counters,
            cabinet_counters,
            cabinets,
            polished_planks,
            polished_stairs,
            polished_slabs;

    public ReDecoModule(String modId) {
        super(modId, "rd");
        ResourceLocation tab = modRes("redeco_tab");;

        tables = SimpleEntrySet.builder(WoodType.class, "table",
                        getModBlock("oak_table"), () -> VanillaWoodTypes.OAK,
                        w -> new TableBlock(copyProperties(w, 1.0F))
                )
                .addTexture(modRes("block/table/oak_table"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTab(getModTab(tab))
                .defaultRecipe()
                .build();
        this.addEntry(tables);

        side_tables = SimpleEntrySet.builder(WoodType.class, "side_table",
                        getModBlock("oak_side_table"), () -> VanillaWoodTypes.OAK,
                        w -> new SideTableBlock(copyProperties(w, 1.0F))
                )
                .addTile(getModTile("side_table_block_entity"))
                .addTextureM(modRes("block/sidetable/oak_side_table"), EveryCompat.res("block/rd/oak_side_table_m"))
                .addTextureM(modRes("block/drawer_oak"), EveryCompat.res("block/rd/drawer_oak_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTab(getModTab(tab))
                .defaultRecipe()
                .build();
        this.addEntry(side_tables);

        drawers = SimpleEntrySet.builder(WoodType.class, "drawers",
                        getModBlock("oak_drawers"), () -> VanillaWoodTypes.OAK,
                        w -> new DrawersBlock(copyProperties(w, 1.0F))
                )
                .addTile(getModTile("drawers_block_entity"))
                .addTextureM(modRes("block/drawers/oak_drawers"), EveryCompat.res("block/rd/oak_drawers_m"))
                //TEXTURES: side_tables' drawer_oak (above)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTab(getModTab(tab))
                .defaultRecipe()
                .build();
        this.addEntry(drawers);

        coffee_tables = SimpleEntrySet.builder(WoodType.class, "coffee_table",
                        getModBlock("oak_coffee_table"), () -> VanillaWoodTypes.OAK,
                        w -> new CoffeeTableBlock(copyProperties(w, 1.0F))
                )
                //TEXTURES: tables' oak_table (above)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTab(getModTab(tab))
                .defaultRecipe()
                .build();
        this.addEntry(coffee_tables);

        chairs = SimpleEntrySet.builder(WoodType.class, "chair",
                        getModBlock("oak_chair"), () -> VanillaWoodTypes.OAK,
                        w -> new ChairBlock(copyProperties(w, 1.0F))
                )
                .addTile(getModTile("chair_block_entity"))
                .addTexture(modRes("block/chair/oak_chair"))
                .addTexture(modRes("block/chair/oak_back_0"))
                .addTexture(modRes("block/chair/oak_back_1"))
                .addTexture(modRes("block/chair/oak_back_2"))
                .addTexture(modRes("block/chair/oak_back_3"))
                .addTexture(modRes("block/chair/oak_back_4"))
                .addTexture(modRes("block/chair/oak_back_5"))
                .addTexture(modRes("block/chair/oak_back_6"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTab(getModTab(tab))
                .defaultRecipe()
                .copyParentDrop() //REASON: included the cushion
                .build();
        this.addEntry(chairs);

        stools = SimpleEntrySet.builder(WoodType.class, "stool",
                        getModBlock("oak_stool"), () -> VanillaWoodTypes.OAK,
                        w -> new StoolBlock(copyProperties(w, 1.0F))
                )
                .addTile(getModTile("stool_block_entity"))
                .addTexture(modRes("block/stool/oak_stool"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTab(getModTab(tab))
                .defaultRecipe()
                .copyParentDrop() //REASON: included the cushion
                .build();
        this.addEntry(stools);

        benches = SimpleEntrySet.builder(WoodType.class, "bench",
                        getModBlock("oak_bench"), () -> VanillaWoodTypes.OAK,
                        w -> new BenchBlock(copyProperties(w, 1.0F))
                )
                .addTile(getModTile("bench_block_entity"))
                .addTexture(modRes("block/bench/oak_bench"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTab(getModTab(tab))
                .defaultRecipe()
                .copyParentDrop() //REASON: included the cushion
                .build();
        this.addEntry(benches);

        shelves = SimpleEntrySet.builder(WoodType.class, "shelf",
                        getModBlock("oak_shelf"), () -> VanillaWoodTypes.OAK,
                        w -> new ShelfBlock(copyProperties(w, 1.0F))
                )
                .addTile(getModTile("shelf_block_entity"))
                .addTexture(modRes("block/shelf/oak_shelf"))
                .addTexture(modRes("block/shelf/oak_shelf_support"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTab(getModTab(tab))
                .defaultRecipe()
                .build();
        this.addEntry(shelves);

        crates = SimpleEntrySet.builder(WoodType.class, "crate",
                        getModBlock("oak_crate"), () -> VanillaWoodTypes.OAK,
                        w -> new CrateBlock(copyProperties(w, 1.0F))
                )
                .addTile(getModTile("crate_block_entity"))
                .addTexture(modRes("block/crate/oak_crate"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(ModTags.Items.UNCRATEABLE, Registries.ITEM)
                .setTab(getModTab(tab))
                .defaultRecipe()
                .build();
        this.addEntry(crates);

        display_cases = SimpleEntrySet.builder(WoodType.class, "display_case",
                        getModBlock("oak_display_case"), () -> VanillaWoodTypes.OAK,
                        w -> new DisplayCaseBlock(copyProperties(w, 1.0F).noOcclusion())
                )
                .addTile(getModTile("display_case_block_entity"))
                .addTexture(modRes("block/displaycase/oak_display_case"))
                .addTexture(modRes("block/displaycase/oak_inside"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTab(getModTab(tab))
                .defaultRecipe()
                .copyParentDrop() //REASON: included the cushion
                .build();
        this.addEntry(display_cases);

        sword_mounts = SimpleEntrySet.builder(WoodType.class, "sword_mount",
                        getModBlock("oak_sword_mount"), () -> VanillaWoodTypes.OAK,
                        w -> new SwordMountBlock(copyProperties(w, 1.0F).noOcclusion())
                )
                .addTile(getModTile("sword_mount_block_entity"))
                .addTexture(modRes("block/swordmount/oak_sword_mount"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTab(getModTab(tab))
                .defaultRecipe()
                .copyParentDrop() //REASON: included the cushion
                .build();
        this.addEntry(sword_mounts);

        pedestals = SimpleEntrySet.builder(WoodType.class, "pedestal",
                        getModBlock("oak_pedestal"), () -> VanillaWoodTypes.OAK,
                        w -> new PedestalBlock(copyProperties(w, 1.0F).noOcclusion())
                )
                .addTile(getModTile("pedestal_block_entity"))
                .addTexture(modRes("block/pedestal/oak_pedestal"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTab(getModTab(tab))
                .defaultRecipe()
                .copyParentDrop() //REASON: included the cushion
                .build();
        this.addEntry(pedestals);

        plank_fences = SimpleEntrySet.builder(WoodType.class, "plank_fence",
                        getModBlock("oak_plank_fence"), () -> VanillaWoodTypes.OAK,
                        w -> new PlankFenceBlock(copyProperties(w, 1.0F))
                )
                .addTexture(modRes("block/plankfence/oak_plank_fence"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.FENCES, Registries.BLOCK)
                .setTab(getModTab(tab))
                .defaultRecipe()
                .build();
        this.addEntry(plank_fences);

        plank_gates = SimpleEntrySet.builder(WoodType.class, "plank_gate",
                        getModBlock("oak_plank_gate"), () -> VanillaWoodTypes.OAK,
                        w -> new PlankGateBlock(copyProperties(w, 1.0F),
                                SoundEvents.FENCE_GATE_CLOSE, SoundEvents.FENCE_GATE_OPEN)
                )
                //TEXTURES: plank_fences' oak_plank_fence (above)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTab(getModTab(tab))
                .defaultRecipe()
                .build();
        this.addEntry(plank_gates);

        lattices = SimpleEntrySet.builder(WoodType.class, "lattice",
                        getModBlock("oak_lattice"), () -> VanillaWoodTypes.OAK,
                        w -> new LatticeBlock(Utils.copyPropertySafe(Blocks.LADDER)
                                .strength(1.0F).noOcclusion().sound(SoundType.WOOD))
                )
                .addTexture(modRes("block/lattice/oak_lattice"))
                .addTexture(modRes("item/oak_lattice"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.FALL_DAMAGE_RESETTING, Registries.BLOCK)
                .addTag(BlockTags.CLIMBABLE, Registries.BLOCK)
                .setTab(getModTab(tab))
                .defaultRecipe()
                .build();
        this.addEntry(lattices);

        counters = SimpleEntrySet.builder(WoodType.class, "counter",
                        getModBlock("oak_counter"), () -> VanillaWoodTypes.OAK,
                        w -> new CounterBlock(copyProperties(w, 1.0F))
                )
                .addTextureM(modRes("block/counters/oak_counters"), EveryCompat.res("block/rd/oak_counters_m"))
                //TEXTURES: polished_planks' polished_oak_planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTab(getModTab(tab))
                .defaultRecipe()
                .build();
        this.addEntry(counters);

        drawer_counters = SimpleEntrySet.builder(WoodType.class, "drawer_counter",
                        getModBlock("oak_drawer_counter"), () -> VanillaWoodTypes.OAK,
                        w -> new DrawerCounterBlock(copyProperties(w, 1.0F))
                )
                .addTile(getModTile("drawers_block_entity"))
                //TEXTURES: counters' oak_counters (above)
                //TEXTURES: drawers' drawer_oak (above)
                //TEXTURES: polished_planks' polished_oak_planks -below
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTab(getModTab(tab))
                .defaultRecipe()
                .build();
        this.addEntry(drawer_counters);

        cabinet_counters = SimpleEntrySet.builder(WoodType.class, "cabinet_counter",
                        getModBlock("oak_cabinet_counter"), () -> VanillaWoodTypes.OAK,
                        w -> new CabinetCounterBlock(copyProperties(w, 1.0F))
                )
                .addTile(getModTile("cabinet_block_entity"))
                //TEXTURES: counters' oak_counters (above)
                //TEXTURES: polished_planks' polished_oak_planks -below
                //TEXTURES: cabinets' cabinet_doors_oak -below
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTab(getModTab(tab))
                .defaultRecipe()
                .build();
        this.addEntry(cabinet_counters);

        cabinets = SimpleEntrySet.builder(WoodType.class, "cabinet",
                        getModBlock("oak_cabinet"), () -> VanillaWoodTypes.OAK,
                        w -> new CabinetBlock(copyProperties(w, 1.0F))
                )
                .addTile(getModTile("cabinet_block_entity"))
                .addTextureM(modRes("block/cabinet/oak_cabinet"), EveryCompat.res("block/rd/oak_cabinet_m"))
                .addTextureM(modRes("block/cabinet_doors_oak"), EveryCompat.res("block/rd/cabinet_doors_oak_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTab(getModTab(tab))
                .defaultRecipe()
                .build();
        this.addEntry(cabinets);

        polished_planks = SimpleEntrySet.builder(WoodType.class, "planks", "polished",
                        getModBlock("polished_oak_planks"), () -> VanillaWoodTypes.OAK,
                        w -> new PolishedPlankBlock(copyProperties(w, 1.0F))
                )
                .addTexture(modRes("block/polishedplanks/polished_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .setTab(getModTab(tab))
                .addRecipe(modRes("oak_polished_planks"))
                .build();
        this.addEntry(polished_planks);

        polished_stairs = SimpleEntrySet.builder(WoodType.class, "stairs", "polished",
                        getModBlock("polished_oak_stairs"), () -> VanillaWoodTypes.OAK,
                        w -> new PolishedPlankStairs(() -> copyState(w), stairsProperties(w))
                )
                //TEXTURES: polished_planks' polished_oak_planks (above)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.STAIRS, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_STAIRS, Registries.BLOCK)
                .addTag(ItemTags.STAIRS, Registries.ITEM)
                .addTag(ItemTags.WOODEN_STAIRS, Registries.ITEM)
                .setTab(getModTab(tab))
                .defaultRecipe()
                .build();
        this.addEntry(polished_stairs);

        polished_slabs = SimpleEntrySet.builder(WoodType.class, "slab", "polished",
                        getModBlock("polished_oak_slab"), () -> VanillaWoodTypes.OAK,
                        w -> new PolishedPlankSlab(copyProperties(w, 1.0F))
                )
                //TEXTURES: polished_planks' polished_oak_planks (above)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.SLABS, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_SLABS, Registries.BLOCK)
                .addTag(ItemTags.SLABS, Registries.ITEM)
                .addTag(ItemTags.WOODEN_SLABS, Registries.ITEM)
                .setTab(getModTab(tab))
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