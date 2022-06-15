package net.mehvahdjukaar.every_compat.modules.mrcrayfish_furniture;

import com.mrcrayfish.furniture.FurnitureMod;
import com.mrcrayfish.furniture.FurnitureModTab;
import com.mrcrayfish.furniture.block.*;
import com.mrcrayfish.furniture.client.renderer.tileentity.KitchenSinkBlockEntityRenderer;
import com.mrcrayfish.furniture.common.ModTags;
import com.mrcrayfish.furniture.core.ModBlocks;
import com.mrcrayfish.furniture.tileentity.KitchenSinkBlockEntity;
import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.selene.block_set.leaves.LeavesType;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

public class MrCrayfishFurnitureModule extends SimpleModule {

    public final SimpleEntrySet<LeavesType, Block> HEDGES;
    public final SimpleEntrySet<WoodType, Block> BEDSIDE_CABINETS;
    public final SimpleEntrySet<WoodType, Block> BENCHES;
    public final SimpleEntrySet<WoodType, Block> BLINDS;
    public final SimpleEntrySet<WoodType, Block> CABINETS;
    public final SimpleEntrySet<WoodType, Block> CHAIRS;
    public final SimpleEntrySet<WoodType, Block> COFFEE_TABLES;
    public final SimpleEntrySet<WoodType, Block> CRATES;
    public final SimpleEntrySet<WoodType, Block> DESKS;
    public final SimpleEntrySet<WoodType, Block> DESK_CABINETS;
    public final SimpleEntrySet<WoodType, Block> KITCHEN_COUNTERS;
    public final SimpleEntrySet<WoodType, Block> KITCHEN_DRAWERS;
    public final SimpleEntrySet<WoodType, Block> KITCHEN_SINK_DARK;
    public final SimpleEntrySet<WoodType, Block> KITCHEN_SINK_LIGHT;
    public final SimpleEntrySet<WoodType, Block> MAIL_BOXES;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_BEDSIDE_CABINETS;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_BENCHES;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_BLINDS;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_CABINETS;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_CHAIRS;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_COFFEE_TABLES;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_CRATES;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_DESKS;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_DESK_CABINETS;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_KITCHEN_COUNTERS;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_KITCHEN_DRAWERS;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_KITCHEN_SINK_DARK;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_KITCHEN_SINK_LIGHT;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_MAIL_BOXES;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_TABLES;
    public final SimpleEntrySet<WoodType, Block> TABLES;
    public final SimpleEntrySet<WoodType, Block> UPGRADED_FENCES;
    public final SimpleEntrySet<WoodType, Block> UPGRADED_GATES;

    public MrCrayfishFurnitureModule(String modId) {
        super(modId, "cfm");

        BEDSIDE_CABINETS = SimpleEntrySet.builder(WoodType.class, "bedside_cabinet",
                        ModBlocks.BEDSIDE_CABINET_OAK, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new BedsideCabinetBlock(BlockBehaviour.Properties.copy(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.BEDROOM, Registry.ITEM_REGISTRY)
                .addTag(ModTags.Items.STORAGE, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(BEDSIDE_CABINETS);

        STRIPPED_BEDSIDE_CABINETS = SimpleEntrySet.builder(WoodType.class, "bedside_cabinet", "stripped",
                        ModBlocks.BEDSIDE_CABINET_STRIPPED_OAK, () -> WoodType.OAK_WOOD_TYPE,
                        ifHasChild(w -> new BedsideCabinetBlock(BlockBehaviour.Properties.copy(w.planks)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.BEDROOM, Registry.ITEM_REGISTRY)
                .addTag(ModTags.Items.STORAGE, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(STRIPPED_BEDSIDE_CABINETS);

        BENCHES = SimpleEntrySet.builder(WoodType.class, "park_bench",
                        ModBlocks.PARK_BENCH_OAK, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new ParkBenchBlock(BlockBehaviour.Properties.copy(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.OUTDOORS, Registry.ITEM_REGISTRY)
                .addTag(ModTags.Items.STORAGE, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(BENCHES);

        STRIPPED_BENCHES = SimpleEntrySet.builder(WoodType.class, "park_bench", "stripped",
                        ModBlocks.PARK_BENCH_STRIPPED_OAK, () -> WoodType.OAK_WOOD_TYPE,
                        ifHasChild(w -> new ParkBenchBlock(BlockBehaviour.Properties.copy(w.planks)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.OUTDOORS, Registry.ITEM_REGISTRY)
                .addTag(ModTags.Items.STORAGE, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(STRIPPED_BENCHES);

        BLINDS = SimpleEntrySet.builder(WoodType.class, "blinds",
                        ModBlocks.BLINDS_OAK, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new BlindsBlock(BlockBehaviour.Properties.copy(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.BEDROOM, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .build();

        this.addEntry(BLINDS);

        STRIPPED_BLINDS = SimpleEntrySet.builder(WoodType.class, "blinds", "stripped",
                        ModBlocks.BLINDS_STRIPPED_OAK, () -> WoodType.OAK_WOOD_TYPE,
                        ifHasChild(w -> new BlindsBlock(BlockBehaviour.Properties.copy(w.planks)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.BEDROOM, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .build();

        this.addEntry(STRIPPED_BLINDS);

        CABINETS = SimpleEntrySet.builder(WoodType.class, "cabinet",
                        ModBlocks.CABINET_OAK, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new CabinetBlock(BlockBehaviour.Properties.copy(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.STORAGE, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(CABINETS);

        STRIPPED_CABINETS = SimpleEntrySet.builder(WoodType.class, "cabinet", "stripped",
                        ModBlocks.CABINET_STRIPPED_OAK, () -> WoodType.OAK_WOOD_TYPE,
                        ifHasChild(w -> new CabinetBlock(BlockBehaviour.Properties.copy(w.planks)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.STORAGE, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(STRIPPED_CABINETS);

        CHAIRS = SimpleEntrySet.builder(WoodType.class, "chair",
                        ModBlocks.CHAIR_OAK, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new ChairBlock(BlockBehaviour.Properties.copy(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.GENERAL, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(CHAIRS);

        STRIPPED_CHAIRS = SimpleEntrySet.builder(WoodType.class, "chair", "stripped",
                        ModBlocks.CHAIR_STRIPPED_OAK, () -> WoodType.OAK_WOOD_TYPE,
                        ifHasChild(w -> new ChairBlock(BlockBehaviour.Properties.copy(w.planks)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.GENERAL, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(STRIPPED_CHAIRS);

        COFFEE_TABLES = SimpleEntrySet.builder(WoodType.class, "coffee_table",
                        ModBlocks.COFFEE_TABLE_OAK, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new CoffeeTableBlock(BlockBehaviour.Properties.copy(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.GENERAL, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(COFFEE_TABLES);

        STRIPPED_COFFEE_TABLES = SimpleEntrySet.builder(WoodType.class, "coffee_table", "stripped",
                        ModBlocks.COFFEE_TABLE_STRIPPED_OAK, () -> WoodType.OAK_WOOD_TYPE,
                        ifHasChild(w -> new CoffeeTableBlock(BlockBehaviour.Properties.copy(w.planks)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.GENERAL, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(STRIPPED_COFFEE_TABLES);

        CRATES = SimpleEntrySet.builder(WoodType.class, "crate",
                        ModBlocks.CRATE_OAK, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new CrateBlock(BlockBehaviour.Properties.copy(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.STORAGE, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(CRATES);

        STRIPPED_CRATES = SimpleEntrySet.builder(WoodType.class, "crate", "stripped",
                        ModBlocks.CRATE_STRIPPED_OAK, () -> WoodType.OAK_WOOD_TYPE,
                        ifHasChild(w -> new CrateBlock(BlockBehaviour.Properties.copy(w.planks)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.STORAGE, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(STRIPPED_CRATES);

        DESKS = SimpleEntrySet.builder(WoodType.class, "desk",
                        ModBlocks.DESK_OAK, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new DeskBlock(BlockBehaviour.Properties.copy(w.planks), DeskBlock.MaterialType.OAK))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.BEDROOM, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(DESKS);

        STRIPPED_DESKS = SimpleEntrySet.builder(WoodType.class, "desk", "stripped",
                        ModBlocks.DESK_STRIPPED_OAK, () -> WoodType.OAK_WOOD_TYPE,
                        ifHasChild(w -> new DeskBlock(BlockBehaviour.Properties.copy(w.planks), DeskBlock.MaterialType.STRIPPED_OAK), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.BEDROOM, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(STRIPPED_DESKS);

        DESK_CABINETS = SimpleEntrySet.builder(WoodType.class, "desk_cabinet",
                        ModBlocks.DESK_CABINET_OAK, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new DeskCabinetBlock(BlockBehaviour.Properties.copy(w.planks), DeskBlock.MaterialType.OAK))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.BEDROOM, Registry.ITEM_REGISTRY)
                .addTag(ModTags.Items.STORAGE, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(DESK_CABINETS);

        STRIPPED_DESK_CABINETS = SimpleEntrySet.builder(WoodType.class, "desk_cabinet", "stripped",
                        ModBlocks.DESK_CABINET_STRIPPED_OAK, () -> WoodType.OAK_WOOD_TYPE,
                        ifHasChild(w -> new DeskCabinetBlock(BlockBehaviour.Properties.copy(w.planks), DeskBlock.MaterialType.STRIPPED_OAK), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.BEDROOM, Registry.ITEM_REGISTRY)
                .addTag(ModTags.Items.STORAGE, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(STRIPPED_DESK_CABINETS);

        KITCHEN_COUNTERS = SimpleEntrySet.builder(WoodType.class, "kitchen_counter",
                        ModBlocks.KITCHEN_COUNTER_OAK, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new KitchenCounterBlock(BlockBehaviour.Properties.copy(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.KITCHEN, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(KITCHEN_COUNTERS);

        STRIPPED_KITCHEN_COUNTERS = SimpleEntrySet.builder(WoodType.class, "kitchen_counter", "stripped",
                        ModBlocks.KITCHEN_COUNTER_STRIPPED_OAK, () -> WoodType.OAK_WOOD_TYPE,
                        ifHasChild(w -> new KitchenCounterBlock(BlockBehaviour.Properties.copy(w.planks)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.KITCHEN, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(STRIPPED_KITCHEN_COUNTERS);

        KITCHEN_DRAWERS = SimpleEntrySet.builder(WoodType.class, "kitchen_drawer",
                        ModBlocks.KITCHEN_DRAWER_OAK, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new KitchenDrawerBlock(BlockBehaviour.Properties.copy(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.KITCHEN, Registry.ITEM_REGISTRY)
                .addTag(ModTags.Items.STORAGE, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(KITCHEN_DRAWERS);

        STRIPPED_KITCHEN_DRAWERS = SimpleEntrySet.builder(WoodType.class, "kitchen_drawer", "stripped",
                        ModBlocks.KITCHEN_DRAWER_STRIPPED_OAK, () -> WoodType.OAK_WOOD_TYPE,
                        ifHasChild(w -> new KitchenDrawerBlock(BlockBehaviour.Properties.copy(w.planks)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.KITCHEN, Registry.ITEM_REGISTRY)
                .addTag(ModTags.Items.STORAGE, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(STRIPPED_KITCHEN_DRAWERS);

        KITCHEN_SINK_DARK = SimpleEntrySet.builder(WoodType.class, "kitchen_sink_dark",
                        ModBlocks.KITCHEN_SINK_DARK_OAK, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new CompatKitchenSinkBlock(BlockBehaviour.Properties.copy(w.planks), true))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.KITCHEN, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .addTile(CompatKitchenSinkBlockEntity::new)
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(KITCHEN_SINK_DARK);

        STRIPPED_KITCHEN_SINK_DARK = SimpleEntrySet.builder(WoodType.class, "kitchen_sink_dark", "stripped",
                        ModBlocks.KITCHEN_SINK_DARK_STRIPPED_OAK, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new CompatKitchenSinkBlock(BlockBehaviour.Properties.copy(w.planks), true))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.KITCHEN, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(STRIPPED_KITCHEN_SINK_DARK);

        KITCHEN_SINK_LIGHT = SimpleEntrySet.builder(WoodType.class, "kitchen_sink_light",
                        ModBlocks.KITCHEN_SINK_LIGHT_OAK, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new CompatKitchenSinkBlock(BlockBehaviour.Properties.copy(w.planks), true))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.KITCHEN, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(KITCHEN_SINK_LIGHT);

        STRIPPED_KITCHEN_SINK_LIGHT = SimpleEntrySet.builder(WoodType.class, "kitchen_sink_light", "stripped",
                        ModBlocks.KITCHEN_SINK_LIGHT_STRIPPED_OAK, () -> WoodType.OAK_WOOD_TYPE,
                        ifHasChild(w -> new CompatKitchenSinkBlock(BlockBehaviour.Properties.copy(w.planks), true), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.KITCHEN, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(STRIPPED_KITCHEN_SINK_LIGHT);

        MAIL_BOXES = SimpleEntrySet.builder(WoodType.class, "mail_box",
                        ModBlocks.MAIL_BOX_OAK, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new MailBoxBlock(BlockBehaviour.Properties.copy(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.OUTDOORS, Registry.ITEM_REGISTRY)
                .addTag(ModTags.Items.STORAGE, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(MAIL_BOXES);

        STRIPPED_MAIL_BOXES = SimpleEntrySet.builder(WoodType.class, "mail_box", "stripped",
                        ModBlocks.MAIL_BOX_STRIPPED_OAK, () -> WoodType.OAK_WOOD_TYPE,
                        ifHasChild(w -> new MailBoxBlock(BlockBehaviour.Properties.copy(w.planks)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.OUTDOORS, Registry.ITEM_REGISTRY)
                .addTag(ModTags.Items.STORAGE, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(STRIPPED_MAIL_BOXES);

        STRIPPED_TABLES = SimpleEntrySet.builder(WoodType.class, "table", "stripped",
                        ModBlocks.TABLE_STRIPPED_OAK, () -> WoodType.OAK_WOOD_TYPE,
                        ifHasChild(w -> new TableBlock(BlockBehaviour.Properties.copy(w.planks)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.GENERAL, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .setTab(() -> FurnitureModTab.TAB_BUILDING_BLOCKS)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(STRIPPED_TABLES);

        TABLES = SimpleEntrySet.builder(WoodType.class, "table",
                        ModBlocks.TABLE_OAK, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new TableBlock(BlockBehaviour.Properties.copy(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.GENERAL, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .setTab(() -> FurnitureModTab.TAB_BUILDING_BLOCKS)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(TABLES);

        UPGRADED_FENCES = SimpleEntrySet.builder(WoodType.class, "upgraded_fence",
                        ModBlocks.UPGRADED_FENCE_OAK, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new UpgradedFenceBlock(BlockBehaviour.Properties.copy(w.planks)))
                .addTag(BlockTags.FENCES, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Blocks.UPGRADED_FENCES, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.OUTDOORS, Registry.ITEM_REGISTRY)
                .addTag(ModTags.Items.UPGRADED_FENCES, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(UPGRADED_FENCES);

        UPGRADED_GATES = SimpleEntrySet.builder(WoodType.class, "upgraded_gate",
                        ModBlocks.UPGRADED_GATE_OAK, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new UpgradedGateBlock(BlockBehaviour.Properties.copy(w.planks)))
                .addTag(BlockTags.FENCE_GATES, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.UNSTABLE_BOTTOM_CENTER, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Blocks.UPGRADED_FENCE_GATES, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.OUTDOORS, Registry.ITEM_REGISTRY)
                .addTag(ModTags.Items.UPGRADED_FENCE_GATES, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(UPGRADED_GATES);

        HEDGES = SimpleEntrySet.builder(LeavesType.class, "hedge",
                        ModBlocks.HEDGE_OAK, () -> LeavesType.OAK_LEAVES_TYPE,
                        w -> {
                            var l = w.getBlockOfThis("leaves");
                            if (l == null) return null;
                            return new HedgeBlock(BlockBehaviour.Properties.copy(l).lightLevel((s) -> 0));
                        })
                .addTag(ModTags.Blocks.HEDGES, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.HEDGES, Registry.ITEM_REGISTRY)
                .addTag(ModTags.Items.OUTDOORS, Registry.ITEM_REGISTRY)
                .addModelTransform(m -> m.replaceLeavesTextures(LeavesType.OAK_LEAVES_TYPE))
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(HEDGES);
    }

    public static BlockEntityType<?> COMPAT_SINK;

    @Override
    public void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer((BlockEntityType<KitchenSinkBlockEntity>) (KITCHEN_SINK_DARK.getTileHolder().tile),
                KitchenSinkBlockEntityRenderer::new);
    }

    @Override
    public void registerTiles(IForgeRegistry<BlockEntityType<?>> registry) {
        List<Block> blocks = new ArrayList<>();
        blocks.addAll(KITCHEN_SINK_LIGHT.blocks.values());
        blocks.addAll(KITCHEN_SINK_DARK.blocks.values());
        blocks.addAll(STRIPPED_KITCHEN_SINK_DARK.blocks.values());
        blocks.addAll(STRIPPED_KITCHEN_SINK_LIGHT.blocks.values());

        COMPAT_SINK = KITCHEN_SINK_DARK.getTileHolder().createInstance(blocks.toArray(Block[]::new));
        registry.register(COMPAT_SINK.setRegistryName(WoodGood.res(this.shortenedId() + "_sink")));
    }

    @Override
    public void registerColors(ColorHandlerEvent.Item event) {
        HEDGES.blocks.forEach((t, b) -> {
            ItemColors colors = event.getItemColors();
            colors.register((stack, tintIndex) -> colors.getColor(new ItemStack(t.leaves), tintIndex), b.asItem());

            BlockColors blockColor = event.getBlockColors();
            blockColor.register((s, l, p, i) -> blockColor.getColor(t.leaves.defaultBlockState(), l, p, i), b);
        });
    }

    @SubscribeEvent
    public void registerBlockColors(ColorHandlerEvent.Item event) {
        WoodGood.forAllModules(m -> m.registerColors(event));
    }

    class CompatKitchenSinkBlockEntity extends KitchenSinkBlockEntity {

        public CompatKitchenSinkBlockEntity(BlockPos pos, BlockState state) {
            super(pos, state);
        }

        @Override
        public BlockEntityType<?> getType() {
            return KITCHEN_SINK_DARK.getTileHolder().tile;
        }
    }

    private class CompatKitchenSinkBlock extends KitchenSinkBlock {
        public CompatKitchenSinkBlock(Properties properties, boolean bigSink) {
            super(properties, bigSink);
        }

        public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
            return new CompatKitchenSinkBlockEntity(pos, state);
        }
    }

//    class CompatKitchenSinkLightBlockEntity extends KitchenSinkBlockEntity {
//
//        public CompatKitchenSinkLightBlockEntity(BlockPos pos, BlockState state) {
//            super(pos, state);
//        }
//
//        @Override
//        public BlockEntityType<?> getTypeClass() {
//            return KITCHEN_SINK_LIGHT.getTileHolder().tile;
//        }
//    }
//
//    private class CompatKitchenSinkLightBlock extends KitchenSinkBlock {
//        private boolean bigSink;
//        public CompatKitchenSinkLightBlock(Properties properties, boolean bigSink) {
//            super(properties, bigSink);
//            this.bigSink = bigSink;
//        }
//
//        public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
//            return new CompatKitchenSinkLightBlockEntity(pos, state);
//        }
//    }
}
