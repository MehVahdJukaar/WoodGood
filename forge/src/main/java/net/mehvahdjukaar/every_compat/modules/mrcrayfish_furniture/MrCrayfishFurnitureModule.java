package net.mehvahdjukaar.every_compat.modules.mrcrayfish_furniture;

import com.mrcrayfish.furniture.FurnitureMod;
import com.mrcrayfish.furniture.FurnitureModTab;
import com.mrcrayfish.furniture.block.*;
import com.mrcrayfish.furniture.client.renderer.tileentity.KitchenSinkBlockEntityRenderer;
import com.mrcrayfish.furniture.common.ModTags;
import com.mrcrayfish.furniture.core.ModBlocks;
import com.mrcrayfish.furniture.tileentity.KitchenSinkBlockEntity;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.misc.Registrator;
import net.mehvahdjukaar.moonlight.api.platform.ClientPlatformHelper;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesType;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesTypeRegistry;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

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
                        ModBlocks.BEDSIDE_CABINET_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BedsideCabinetBlock(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.BEDROOM, Registry.ITEM_REGISTRY)
                .addTag(ModTags.Items.STORAGE, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(BEDSIDE_CABINETS);

        STRIPPED_BEDSIDE_CABINETS = SimpleEntrySet.builder(WoodType.class, "bedside_cabinet", "stripped",
                        ModBlocks.BEDSIDE_CABINET_STRIPPED_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new BedsideCabinetBlock(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.BEDROOM, Registry.ITEM_REGISTRY)
                .addTag(ModTags.Items.STORAGE, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(STRIPPED_BEDSIDE_CABINETS);

        BENCHES = SimpleEntrySet.builder(WoodType.class, "park_bench",
                        ModBlocks.PARK_BENCH_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ParkBenchBlock(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.OUTDOORS, Registry.ITEM_REGISTRY)
                .addTag(ModTags.Items.STORAGE, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(BENCHES);

        STRIPPED_BENCHES = SimpleEntrySet.builder(WoodType.class, "park_bench", "stripped",
                        ModBlocks.PARK_BENCH_STRIPPED_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new ParkBenchBlock(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.OUTDOORS, Registry.ITEM_REGISTRY)
                .addTag(ModTags.Items.STORAGE, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(STRIPPED_BENCHES);

        BLINDS = SimpleEntrySet.builder(WoodType.class, "blinds",
                        ModBlocks.BLINDS_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new BlindsBlock(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.BEDROOM, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .build();

        this.addEntry(BLINDS);

        STRIPPED_BLINDS = SimpleEntrySet.builder(WoodType.class, "blinds", "stripped",
                        ModBlocks.BLINDS_STRIPPED_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new BlindsBlock(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.BEDROOM, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .build();

        this.addEntry(STRIPPED_BLINDS);

        CABINETS = SimpleEntrySet.builder(WoodType.class, "cabinet",
                        ModBlocks.CABINET_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CabinetBlock(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.STORAGE, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(CABINETS);

        STRIPPED_CABINETS = SimpleEntrySet.builder(WoodType.class, "cabinet", "stripped",
                        ModBlocks.CABINET_STRIPPED_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new CabinetBlock(Utils.copyPropertySafe(w.planks)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.STORAGE, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(STRIPPED_CABINETS);

        CHAIRS = SimpleEntrySet.builder(WoodType.class, "chair",
                        ModBlocks.CHAIR_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ChairBlock(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.GENERAL, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(CHAIRS);

        STRIPPED_CHAIRS = SimpleEntrySet.builder(WoodType.class, "chair", "stripped",
                        ModBlocks.CHAIR_STRIPPED_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new ChairBlock(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.GENERAL, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(STRIPPED_CHAIRS);

        COFFEE_TABLES = SimpleEntrySet.builder(WoodType.class, "coffee_table",
                        ModBlocks.COFFEE_TABLE_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CoffeeTableBlock(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.GENERAL, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(COFFEE_TABLES);

        STRIPPED_COFFEE_TABLES = SimpleEntrySet.builder(WoodType.class, "coffee_table", "stripped",
                        ModBlocks.COFFEE_TABLE_STRIPPED_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new CoffeeTableBlock(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.GENERAL, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(STRIPPED_COFFEE_TABLES);

        CRATES = SimpleEntrySet.builder(WoodType.class, "crate",
                        ModBlocks.CRATE_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CrateBlock(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.STORAGE, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(CRATES);

        STRIPPED_CRATES = SimpleEntrySet.builder(WoodType.class, "crate", "stripped",
                        ModBlocks.CRATE_STRIPPED_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new CrateBlock(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.STORAGE, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(STRIPPED_CRATES);

        DESKS = SimpleEntrySet.builder(WoodType.class, "desk",
                        ModBlocks.DESK_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DeskBlock(Utils.copyPropertySafe(w.planks), DeskBlock.MaterialType.OAK))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.BEDROOM, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(DESKS);

        STRIPPED_DESKS = SimpleEntrySet.builder(WoodType.class, "desk", "stripped",
                        ModBlocks.DESK_STRIPPED_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new DeskBlock(Utils.copyPropertySafe(w.log), DeskBlock.MaterialType.STRIPPED_OAK), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.BEDROOM, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(STRIPPED_DESKS);

        DESK_CABINETS = SimpleEntrySet.builder(WoodType.class, "desk_cabinet",
                        ModBlocks.DESK_CABINET_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DeskCabinetBlock(Utils.copyPropertySafe(w.log), DeskBlock.MaterialType.OAK))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.BEDROOM, Registry.ITEM_REGISTRY)
                .addTag(ModTags.Items.STORAGE, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(DESK_CABINETS);

        STRIPPED_DESK_CABINETS = SimpleEntrySet.builder(WoodType.class, "desk_cabinet", "stripped",
                        ModBlocks.DESK_CABINET_STRIPPED_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new DeskCabinetBlock(Utils.copyPropertySafe(w.log), DeskBlock.MaterialType.STRIPPED_OAK), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.BEDROOM, Registry.ITEM_REGISTRY)
                .addTag(ModTags.Items.STORAGE, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(STRIPPED_DESK_CABINETS);

        KITCHEN_COUNTERS = SimpleEntrySet.builder(WoodType.class, "kitchen_counter",
                        ModBlocks.KITCHEN_COUNTER_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new KitchenCounterBlock(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.KITCHEN, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(KITCHEN_COUNTERS);

        STRIPPED_KITCHEN_COUNTERS = SimpleEntrySet.builder(WoodType.class, "kitchen_counter", "stripped",
                        ModBlocks.KITCHEN_COUNTER_STRIPPED_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new KitchenCounterBlock(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.KITCHEN, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(STRIPPED_KITCHEN_COUNTERS);

        KITCHEN_DRAWERS = SimpleEntrySet.builder(WoodType.class, "kitchen_drawer",
                        ModBlocks.KITCHEN_DRAWER_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new KitchenDrawerBlock(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.KITCHEN, Registry.ITEM_REGISTRY)
                .addTag(ModTags.Items.STORAGE, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(KITCHEN_DRAWERS);

        STRIPPED_KITCHEN_DRAWERS = SimpleEntrySet.builder(WoodType.class, "kitchen_drawer", "stripped",
                        ModBlocks.KITCHEN_DRAWER_STRIPPED_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new KitchenDrawerBlock(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.KITCHEN, Registry.ITEM_REGISTRY)
                .addTag(ModTags.Items.STORAGE, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(STRIPPED_KITCHEN_DRAWERS);

        KITCHEN_SINK_DARK = SimpleEntrySet.builder(WoodType.class, "kitchen_sink_dark",
                        ModBlocks.KITCHEN_SINK_DARK_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CompatKitchenSinkBlock(Utils.copyPropertySafe(w.planks), true))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.KITCHEN, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .addTile(CompatKitchenSinkBlockEntity::new)
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(KITCHEN_SINK_DARK);

        STRIPPED_KITCHEN_SINK_DARK = SimpleEntrySet.builder(WoodType.class, "kitchen_sink_dark", "stripped",
                        ModBlocks.KITCHEN_SINK_DARK_STRIPPED_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new CompatKitchenSinkBlock(Utils.copyPropertySafe(w.log), true), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.KITCHEN, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(STRIPPED_KITCHEN_SINK_DARK);

        KITCHEN_SINK_LIGHT = SimpleEntrySet.builder(WoodType.class, "kitchen_sink_light",
                        ModBlocks.KITCHEN_SINK_LIGHT_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CompatKitchenSinkBlock(Utils.copyPropertySafe(w.planks), true))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.KITCHEN, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(KITCHEN_SINK_LIGHT);

        STRIPPED_KITCHEN_SINK_LIGHT = SimpleEntrySet.builder(WoodType.class, "kitchen_sink_light", "stripped",
                        ModBlocks.KITCHEN_SINK_LIGHT_STRIPPED_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new CompatKitchenSinkBlock(Utils.copyPropertySafe(w.log), true), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.KITCHEN, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(STRIPPED_KITCHEN_SINK_LIGHT);

        MAIL_BOXES = SimpleEntrySet.builder(WoodType.class, "mail_box",
                        ModBlocks.MAIL_BOX_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new MailBoxBlock(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.OUTDOORS, Registry.ITEM_REGISTRY)
                .addTag(ModTags.Items.STORAGE, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(MAIL_BOXES);

        STRIPPED_MAIL_BOXES = SimpleEntrySet.builder(WoodType.class, "mail_box", "stripped",
                        ModBlocks.MAIL_BOX_STRIPPED_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new MailBoxBlock(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.OUTDOORS, Registry.ITEM_REGISTRY)
                .addTag(ModTags.Items.STORAGE, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(STRIPPED_MAIL_BOXES);

        STRIPPED_TABLES = SimpleEntrySet.builder(WoodType.class, "table", "stripped",
                        ModBlocks.TABLE_STRIPPED_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new TableBlock(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.GENERAL, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .setTab(() -> FurnitureModTab.TAB_BUILDING_BLOCKS)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(STRIPPED_TABLES);

        TABLES = SimpleEntrySet.builder(WoodType.class, "table",
                        ModBlocks.TABLE_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TableBlock(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.GENERAL, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .setTab(() -> FurnitureModTab.TAB_BUILDING_BLOCKS)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(TABLES);

        UPGRADED_FENCES = SimpleEntrySet.builder(WoodType.class, "upgraded_fence",
                        ModBlocks.UPGRADED_FENCE_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new UpgradedFenceBlock(Utils.copyPropertySafe(w.planks)))
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
                        ModBlocks.UPGRADED_GATE_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new UpgradedGateBlock(Utils.copyPropertySafe(w.planks)))
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
                        ModBlocks.HEDGE_OAK, () -> LeavesTypeRegistry.OAK_TYPE,
                        w -> {
                            var l = w.getBlockOfThis("leaves");
                            if (l == null) return null;
                            return new HedgeBlock(Utils.copyPropertySafe(l).lightLevel((s) -> 0));
                        })
                .addTag(ModTags.Blocks.HEDGES, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.HEDGES, Registry.ITEM_REGISTRY)
                .addTag(ModTags.Items.OUTDOORS, Registry.ITEM_REGISTRY)
                .addModelTransform(m -> m.replaceWithTextureFromChild("minecraft:block/oak_leaves",
                        "leaves", s -> !s.contains("/snow") && !s.contains("_snow")))
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(HEDGES);
    }

    public static BlockEntityType<?> COMPAT_SINK;

    @Override
    public void registerBlockEntityRenderers(ClientPlatformHelper.BlockEntityRendererEvent event) {
        event.register((BlockEntityType<KitchenSinkBlockEntity>) (KITCHEN_SINK_DARK.getTileHolder().tile),
                KitchenSinkBlockEntityRenderer::new);
    }


    @Override
    public void registerTiles(Registrator<BlockEntityType<?>> event) {
        List<Block> blocks = new ArrayList<>();
        blocks.addAll(KITCHEN_SINK_LIGHT.blocks.values());
        blocks.addAll(KITCHEN_SINK_DARK.blocks.values());
        blocks.addAll(STRIPPED_KITCHEN_SINK_DARK.blocks.values());
        blocks.addAll(STRIPPED_KITCHEN_SINK_LIGHT.blocks.values());

        event.register(EveryCompat.res(this.shortenedId() + "_sink"),
                KITCHEN_SINK_DARK.getTileHolder().createInstance(blocks.toArray(Block[]::new)));
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
