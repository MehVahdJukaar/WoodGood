package net.mehvahdjukaar.every_compat.modules.forge.mrcrayfish_furniture;

import com.mrcrayfish.furniture.FurnitureMod;
import com.mrcrayfish.furniture.FurnitureModTab;
import com.mrcrayfish.furniture.block.*;
import com.mrcrayfish.furniture.common.ModTags;
import com.mrcrayfish.furniture.core.ModBlockEntities;
import com.mrcrayfish.furniture.core.ModBlocks;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.platform.ClientPlatformHelper;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesType;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesTypeRegistry;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public class MrCrayfishFurnitureModule extends SimpleModule {

    public final SimpleEntrySet<LeavesType, Block> hedges;
    public final SimpleEntrySet<WoodType, Block> bedsideCabinets;
    public final SimpleEntrySet<WoodType, Block> benches;
    public final SimpleEntrySet<WoodType, Block> blinds;
    public final SimpleEntrySet<WoodType, Block> cabinets;
    public final SimpleEntrySet<WoodType, Block> chairs;
    public final SimpleEntrySet<WoodType, Block> coffeeTables;
    public final SimpleEntrySet<WoodType, Block> crates;
    public final SimpleEntrySet<WoodType, Block> desks;
    public final SimpleEntrySet<WoodType, Block> deskCabinets;
    public final SimpleEntrySet<WoodType, Block> kitchenCounters;
    public final SimpleEntrySet<WoodType, Block> kitchenDrawers;
    public final SimpleEntrySet<WoodType, Block> kitchenSinkDark;
    public final SimpleEntrySet<WoodType, Block> kitchenSinkLight;
    public final SimpleEntrySet<WoodType, Block> mailBoxes;
    public final SimpleEntrySet<WoodType, Block> strippedBedsideCabinets;
    public final SimpleEntrySet<WoodType, Block> strippedBenches;
    public final SimpleEntrySet<WoodType, Block> strippedBlinds;
    public final SimpleEntrySet<WoodType, Block> strippedCabinets;
    public final SimpleEntrySet<WoodType, Block> strippedChairs;
    public final SimpleEntrySet<WoodType, Block> strippedCoffeeTables;
    public final SimpleEntrySet<WoodType, Block> strippedCrates;
    public final SimpleEntrySet<WoodType, Block> strippedDesks;
    public final SimpleEntrySet<WoodType, Block> strippedDeskCabinets;
    public final SimpleEntrySet<WoodType, Block> strippedKitchenCounters;
    public final SimpleEntrySet<WoodType, Block> strippedKitchenDrawers;
    public final SimpleEntrySet<WoodType, Block> strippedKitchenSinkDark;
    public final SimpleEntrySet<WoodType, Block> strippedKitchenSinkLight;
    public final SimpleEntrySet<WoodType, Block> strippedMailBoxes;
    public final SimpleEntrySet<WoodType, Block> strippedTables;
    public final SimpleEntrySet<WoodType, Block> tables;
    public final SimpleEntrySet<WoodType, Block> upgradedFences;
    public final SimpleEntrySet<WoodType, Block> upgradedGates;

    public MrCrayfishFurnitureModule(String modId) {
        super(modId, "cfm");

        bedsideCabinets = SimpleEntrySet.builder(WoodType.class, "bedside_cabinet",
                        ModBlocks.BEDSIDE_CABINET_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BedsideCabinetBlock(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.BEDROOM, Registry.ITEM_REGISTRY)
                .addTag(ModTags.Items.STORAGE, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .addTile(ModBlockEntities.BEDSIDE_CABINET)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(bedsideCabinets);

        strippedBedsideCabinets = SimpleEntrySet.builder(WoodType.class, "bedside_cabinet", "stripped",
                        ModBlocks.BEDSIDE_CABINET_STRIPPED_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new BedsideCabinetBlock(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.BEDROOM, Registry.ITEM_REGISTRY)
                .addTag(ModTags.Items.STORAGE, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .addTile(ModBlockEntities.BEDSIDE_CABINET)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(strippedBedsideCabinets);

        benches = SimpleEntrySet.builder(WoodType.class, "park_bench",
                        ModBlocks.PARK_BENCH_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ParkBenchBlock(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.OUTDOORS, Registry.ITEM_REGISTRY)
                .addTag(ModTags.Items.STORAGE, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(benches);

        strippedBenches = SimpleEntrySet.builder(WoodType.class, "park_bench", "stripped",
                        ModBlocks.PARK_BENCH_STRIPPED_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new ParkBenchBlock(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.OUTDOORS, Registry.ITEM_REGISTRY)
                .addTag(ModTags.Items.STORAGE, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(strippedBenches);

        blinds = SimpleEntrySet.builder(WoodType.class, "blinds",
                        ModBlocks.BLINDS_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new BlindsBlock(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.BEDROOM, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .build();

        this.addEntry(blinds);

        strippedBlinds = SimpleEntrySet.builder(WoodType.class, "blinds", "stripped",
                        ModBlocks.BLINDS_STRIPPED_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new BlindsBlock(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.BEDROOM, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .build();

        this.addEntry(strippedBlinds);

        cabinets = SimpleEntrySet.builder(WoodType.class, "cabinet",
                        ModBlocks.CABINET_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CabinetBlock(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.STORAGE, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .addTile(ModBlockEntities.CABINET)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(cabinets);

        strippedCabinets = SimpleEntrySet.builder(WoodType.class, "cabinet", "stripped",
                        ModBlocks.CABINET_STRIPPED_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new CabinetBlock(Utils.copyPropertySafe(w.planks)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.STORAGE, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .addTile(ModBlockEntities.CABINET)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(strippedCabinets);

        chairs = SimpleEntrySet.builder(WoodType.class, "chair",
                        ModBlocks.CHAIR_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ChairBlock(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.GENERAL, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(chairs);

        strippedChairs = SimpleEntrySet.builder(WoodType.class, "chair", "stripped",
                        ModBlocks.CHAIR_STRIPPED_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new ChairBlock(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.GENERAL, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(strippedChairs);

        coffeeTables = SimpleEntrySet.builder(WoodType.class, "coffee_table",
                        ModBlocks.COFFEE_TABLE_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CoffeeTableBlock(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.GENERAL, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(coffeeTables);

        strippedCoffeeTables = SimpleEntrySet.builder(WoodType.class, "coffee_table", "stripped",
                        ModBlocks.COFFEE_TABLE_STRIPPED_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new CoffeeTableBlock(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.GENERAL, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(strippedCoffeeTables);

        crates = SimpleEntrySet.builder(WoodType.class, "crate",
                        ModBlocks.CRATE_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CrateBlock(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.STORAGE, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .addTile(ModBlockEntities.CRATE)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(crates);

        strippedCrates = SimpleEntrySet.builder(WoodType.class, "crate", "stripped",
                        ModBlocks.CRATE_STRIPPED_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new CrateBlock(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.STORAGE, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .addTile(ModBlockEntities.CRATE)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(strippedCrates);

        desks = SimpleEntrySet.builder(WoodType.class, "desk",
                        ModBlocks.DESK_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DeskBlock(Utils.copyPropertySafe(w.planks), DeskBlock.MaterialType.OAK))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.BEDROOM, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(desks);

        strippedDesks = SimpleEntrySet.builder(WoodType.class, "desk", "stripped",
                        ModBlocks.DESK_STRIPPED_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new DeskBlock(Utils.copyPropertySafe(w.log), DeskBlock.MaterialType.STRIPPED_OAK), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.BEDROOM, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(strippedDesks);

        deskCabinets = SimpleEntrySet.builder(WoodType.class, "desk_cabinet",
                        ModBlocks.DESK_CABINET_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DeskCabinetBlock(Utils.copyPropertySafe(w.log), DeskBlock.MaterialType.OAK))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.BEDROOM, Registry.ITEM_REGISTRY)
                .addTag(ModTags.Items.STORAGE, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .addTile(ModBlockEntities.DESK_CABINET)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(deskCabinets);

        strippedDeskCabinets = SimpleEntrySet.builder(WoodType.class, "desk_cabinet", "stripped",
                        ModBlocks.DESK_CABINET_STRIPPED_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new DeskCabinetBlock(Utils.copyPropertySafe(w.log), DeskBlock.MaterialType.STRIPPED_OAK), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.BEDROOM, Registry.ITEM_REGISTRY)
                .addTag(ModTags.Items.STORAGE, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .addTile(ModBlockEntities.DESK_CABINET)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(strippedDeskCabinets);

        kitchenCounters = SimpleEntrySet.builder(WoodType.class, "kitchen_counter",
                        ModBlocks.KITCHEN_COUNTER_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new KitchenCounterBlock(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.KITCHEN, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(kitchenCounters);

        strippedKitchenCounters = SimpleEntrySet.builder(WoodType.class, "kitchen_counter", "stripped",
                        ModBlocks.KITCHEN_COUNTER_STRIPPED_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new KitchenCounterBlock(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.KITCHEN, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(strippedKitchenCounters);

        kitchenDrawers = SimpleEntrySet.builder(WoodType.class, "kitchen_drawer",
                        ModBlocks.KITCHEN_DRAWER_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new KitchenDrawerBlock(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.KITCHEN, Registry.ITEM_REGISTRY)
                .addTag(ModTags.Items.STORAGE, Registry.ITEM_REGISTRY)
                .addTile(ModBlockEntities.KITCHEN_DRAWER)
                .setTab(() -> FurnitureMod.GROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(kitchenDrawers);

        strippedKitchenDrawers = SimpleEntrySet.builder(WoodType.class, "kitchen_drawer", "stripped",
                        ModBlocks.KITCHEN_DRAWER_STRIPPED_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new KitchenDrawerBlock(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.KITCHEN, Registry.ITEM_REGISTRY)
                .addTag(ModTags.Items.STORAGE, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .addTile(ModBlockEntities.KITCHEN_DRAWER)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(strippedKitchenDrawers);

        kitchenSinkDark = SimpleEntrySet.builder(WoodType.class, "kitchen_sink_dark",
                        ModBlocks.KITCHEN_SINK_DARK_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new KitchenSinkBlock(Utils.copyPropertySafe(w.planks), true))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.KITCHEN, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .addTile(ModBlockEntities.KITCHEN_SINK)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(kitchenSinkDark);

        strippedKitchenSinkDark = SimpleEntrySet.builder(WoodType.class, "kitchen_sink_dark", "stripped",
                        ModBlocks.KITCHEN_SINK_DARK_STRIPPED_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new KitchenSinkBlock(Utils.copyPropertySafe(w.log), true), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.KITCHEN, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .addTile(ModBlockEntities.KITCHEN_SINK)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(strippedKitchenSinkDark);

        kitchenSinkLight = SimpleEntrySet.builder(WoodType.class, "kitchen_sink_light",
                        ModBlocks.KITCHEN_SINK_LIGHT_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new KitchenSinkBlock(Utils.copyPropertySafe(w.planks), true))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.KITCHEN, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .addTile(ModBlockEntities.KITCHEN_SINK)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(kitchenSinkLight);

        strippedKitchenSinkLight = SimpleEntrySet.builder(WoodType.class, "kitchen_sink_light", "stripped",
                        ModBlocks.KITCHEN_SINK_LIGHT_STRIPPED_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new KitchenSinkBlock(Utils.copyPropertySafe(w.log), true), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.KITCHEN, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .addTile(ModBlockEntities.KITCHEN_SINK)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(strippedKitchenSinkLight);

        mailBoxes = SimpleEntrySet.builder(WoodType.class, "mail_box",
                        ModBlocks.MAIL_BOX_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new MailBoxBlock(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.OUTDOORS, Registry.ITEM_REGISTRY)
                .addTag(ModTags.Items.STORAGE, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .addTile(ModBlockEntities.MAIL_BOX)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(mailBoxes);

        strippedMailBoxes = SimpleEntrySet.builder(WoodType.class, "mail_box", "stripped",
                        ModBlocks.MAIL_BOX_STRIPPED_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new MailBoxBlock(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.OUTDOORS, Registry.ITEM_REGISTRY)
                .addTag(ModTags.Items.STORAGE, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .addTile(ModBlockEntities.MAIL_BOX)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(strippedMailBoxes);

        strippedTables = SimpleEntrySet.builder(WoodType.class, "table", "stripped",
                        ModBlocks.TABLE_STRIPPED_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new TableBlock(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.GENERAL, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .setTab(() -> FurnitureModTab.TAB_BUILDING_BLOCKS)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(strippedTables);

        tables = SimpleEntrySet.builder(WoodType.class, "table",
                        ModBlocks.TABLE_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TableBlock(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.GENERAL, Registry.ITEM_REGISTRY)
                .setTab(() -> FurnitureMod.GROUP)
                .setTab(() -> FurnitureModTab.TAB_BUILDING_BLOCKS)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(tables);

        upgradedFences = SimpleEntrySet.builder(WoodType.class, "upgraded_fence",
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

        this.addEntry(upgradedFences);

        upgradedGates = SimpleEntrySet.builder(WoodType.class, "upgraded_gate",
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

        this.addEntry(upgradedGates);

        hedges = SimpleEntrySet.builder(LeavesType.class, "hedge",
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

        this.addEntry(hedges);
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
