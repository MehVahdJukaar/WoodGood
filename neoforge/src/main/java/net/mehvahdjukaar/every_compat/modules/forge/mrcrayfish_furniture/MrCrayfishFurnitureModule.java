package net.mehvahdjukaar.every_compat.modules.forge.mrcrayfish_furniture;

import com.mrcrayfish.furniture.block.*;
import com.mrcrayfish.furniture.common.ModTags;
import com.mrcrayfish.furniture.core.ModBlockEntities;
import com.mrcrayfish.furniture.core.ModBlocks;
import com.mrcrayfish.furniture.core.ModCreativeTabs;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesType;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesTypeRegistry;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
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
    public final SimpleEntrySet<WoodType, Block> strippedUpgradedFences;
    public final SimpleEntrySet<WoodType, Block> upgradedGates;
    public final SimpleEntrySet<WoodType, Block> strippedUpgradedGates;

    public MrCrayfishFurnitureModule(String modId) {
        super(modId, "cfm");

        bedsideCabinets = SimpleEntrySet.builder(WoodType.class, "bedside_cabinet",
                        ModBlocks.BEDSIDE_CABINET_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BedsideCabinetBlock(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(ModTags.Items.BEDROOM, Registries.ITEM)
                .addTag(ModTags.Items.STORAGE, Registries.ITEM)
                .setTab(ModCreativeTabs.MAIN)
                .addTile(ModBlockEntities.BEDSIDE_CABINET)
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(bedsideCabinets);

        strippedBedsideCabinets = SimpleEntrySet.builder(WoodType.class, "bedside_cabinet", "stripped",
                        ModBlocks.BEDSIDE_CABINET_STRIPPED_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BedsideCabinetBlock(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(ModTags.Items.BEDROOM, Registries.ITEM)
                .addTag(ModTags.Items.STORAGE, Registries.ITEM)
                .setTab(ModCreativeTabs.MAIN)
                .requiresChildren("stripped_log")
                .addTile(ModBlockEntities.BEDSIDE_CABINET)
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(strippedBedsideCabinets);

        benches = SimpleEntrySet.builder(WoodType.class, "park_bench",
                        ModBlocks.PARK_BENCH_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ParkBenchBlock(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(ModTags.Items.OUTDOORS, Registries.ITEM)
                .addTag(ModTags.Items.STORAGE, Registries.ITEM)
                .setTab(ModCreativeTabs.MAIN)
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(benches);

        strippedBenches = SimpleEntrySet.builder(WoodType.class, "park_bench", "stripped",
                        ModBlocks.PARK_BENCH_STRIPPED_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ParkBenchBlock(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(ModTags.Items.OUTDOORS, Registries.ITEM)
                .addTag(ModTags.Items.STORAGE, Registries.ITEM)
                .requiresChildren("stripped_log")
                .setTab(ModCreativeTabs.MAIN)
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(strippedBenches);

        blinds = SimpleEntrySet.builder(WoodType.class, "blinds",
                        ModBlocks.BLINDS_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BlindsBlock(Utils.copyPropertySafe(w.log)))
                .requiresChildren("stripped_log")
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(ModTags.Items.BEDROOM, Registries.ITEM)
                .setTab(ModCreativeTabs.MAIN)
                .defaultRecipe()
                .build();
        this.addEntry(blinds);

        strippedBlinds = SimpleEntrySet.builder(WoodType.class, "blinds", "stripped",
                        ModBlocks.BLINDS_STRIPPED_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BlindsBlock(Utils.copyPropertySafe(w.log)))
                .requiresChildren("stripped_log")
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(ModTags.Items.BEDROOM, Registries.ITEM)
                .setTab(ModCreativeTabs.MAIN)
                .defaultRecipe()
                .build();
        this.addEntry(strippedBlinds);

        cabinets = SimpleEntrySet.builder(WoodType.class, "cabinet",
                        ModBlocks.CABINET_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CabinetBlock(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(ModTags.Items.STORAGE, Registries.ITEM)
                .setTab(ModCreativeTabs.MAIN)
                .addTile(ModBlockEntities.CABINET)
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(cabinets);

        strippedCabinets = SimpleEntrySet.builder(WoodType.class, "cabinet", "stripped",
                        ModBlocks.CABINET_STRIPPED_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CabinetBlock(Utils.copyPropertySafe(w.planks)))
                .requiresChildren("stripped_log")
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(ModTags.Items.STORAGE, Registries.ITEM)
                .setTab(ModCreativeTabs.MAIN)
                .addTile(ModBlockEntities.CABINET)
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(strippedCabinets);

        chairs = SimpleEntrySet.builder(WoodType.class, "chair",
                        ModBlocks.CHAIR_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ChairBlock(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(ModTags.Items.GENERAL, Registries.ITEM)
                .setTab(ModCreativeTabs.MAIN)
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(chairs);

        strippedChairs = SimpleEntrySet.builder(WoodType.class, "chair", "stripped",
                        ModBlocks.CHAIR_STRIPPED_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ChairBlock(Utils.copyPropertySafe(w.log)))
                .requiresChildren("stripped_log")
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(ModTags.Items.GENERAL, Registries.ITEM)
                .setTab(ModCreativeTabs.MAIN)
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(strippedChairs);

        coffeeTables = SimpleEntrySet.builder(WoodType.class, "coffee_table",
                        ModBlocks.COFFEE_TABLE_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CoffeeTableBlock(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(ModTags.Items.GENERAL, Registries.ITEM)
                .setTab(ModCreativeTabs.MAIN)
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(coffeeTables);

        strippedCoffeeTables = SimpleEntrySet.builder(WoodType.class, "coffee_table", "stripped",
                        ModBlocks.COFFEE_TABLE_STRIPPED_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CoffeeTableBlock(Utils.copyPropertySafe(w.log)))
                .requiresChildren("stripped_log")
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(ModTags.Items.GENERAL, Registries.ITEM)
                .setTab(ModCreativeTabs.MAIN)
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(strippedCoffeeTables);

        crates = SimpleEntrySet.builder(WoodType.class, "crate",
                        ModBlocks.CRATE_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CrateBlock(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(ModTags.Items.STORAGE, Registries.ITEM)
                .setTab(ModCreativeTabs.MAIN)
                .addTile(ModBlockEntities.CRATE)
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(crates);

        strippedCrates = SimpleEntrySet.builder(WoodType.class, "crate", "stripped",
                        ModBlocks.CRATE_STRIPPED_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CrateBlock(Utils.copyPropertySafe(w.log)))
                .requiresChildren("stripped_log")
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(ModTags.Items.STORAGE, Registries.ITEM)
                .setTab(ModCreativeTabs.MAIN)
                .addTile(ModBlockEntities.CRATE)
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(strippedCrates);

        desks = SimpleEntrySet.builder(WoodType.class, "desk",
                        ModBlocks.DESK_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DeskBlock(Utils.copyPropertySafe(w.planks), DeskBlock.MaterialType.OAK))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(ModTags.Items.BEDROOM, Registries.ITEM)
                .setTab(ModCreativeTabs.MAIN)
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(desks);

        strippedDesks = SimpleEntrySet.builder(WoodType.class, "desk", "stripped",
                        ModBlocks.DESK_STRIPPED_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DeskBlock(Utils.copyPropertySafe(w.log), DeskBlock.MaterialType.STRIPPED_OAK))
                .requiresChildren("stripped_log")
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(ModTags.Items.BEDROOM, Registries.ITEM)
                .setTab(ModCreativeTabs.MAIN)
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(strippedDesks);

        deskCabinets = SimpleEntrySet.builder(WoodType.class, "desk_cabinet",
                        ModBlocks.DESK_CABINET_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DeskCabinetBlock(Utils.copyPropertySafe(w.log), DeskBlock.MaterialType.OAK))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(ModTags.Items.BEDROOM, Registries.ITEM)
                .addTag(ModTags.Items.STORAGE, Registries.ITEM)
                .setTab(ModCreativeTabs.MAIN)
                .addTile(ModBlockEntities.DESK_CABINET)
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(deskCabinets);

        strippedDeskCabinets = SimpleEntrySet.builder(WoodType.class, "desk_cabinet", "stripped",
                        ModBlocks.DESK_CABINET_STRIPPED_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DeskCabinetBlock(Utils.copyPropertySafe(w.log), DeskBlock.MaterialType.STRIPPED_OAK)).requiresChildren("stripped_log")
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(ModTags.Items.BEDROOM, Registries.ITEM)
                .addTag(ModTags.Items.STORAGE, Registries.ITEM)
                .setTab(ModCreativeTabs.MAIN)
                .addTile(ModBlockEntities.DESK_CABINET)
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(strippedDeskCabinets);

        kitchenCounters = SimpleEntrySet.builder(WoodType.class, "kitchen_counter",
                        ModBlocks.KITCHEN_COUNTER_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new KitchenCounterBlock(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(ModTags.Items.KITCHEN, Registries.ITEM)
                .setTab(ModCreativeTabs.MAIN)
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(kitchenCounters);

        strippedKitchenCounters = SimpleEntrySet.builder(WoodType.class, "kitchen_counter", "stripped",
                        ModBlocks.KITCHEN_COUNTER_STRIPPED_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new KitchenCounterBlock(Utils.copyPropertySafe(w.log))).requiresChildren("stripped_log")
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(ModTags.Items.KITCHEN, Registries.ITEM)
                .setTab(ModCreativeTabs.MAIN)
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(strippedKitchenCounters);

        kitchenDrawers = SimpleEntrySet.builder(WoodType.class, "kitchen_drawer",
                        ModBlocks.KITCHEN_DRAWER_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new KitchenDrawerBlock(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(ModTags.Items.KITCHEN, Registries.ITEM)
                .addTag(ModTags.Items.STORAGE, Registries.ITEM)
                .addTile(ModBlockEntities.KITCHEN_DRAWER)
                .setTab(ModCreativeTabs.MAIN)
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(kitchenDrawers);

        strippedKitchenDrawers = SimpleEntrySet.builder(WoodType.class, "kitchen_drawer", "stripped",
                        ModBlocks.KITCHEN_DRAWER_STRIPPED_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new KitchenDrawerBlock(Utils.copyPropertySafe(w.log)))
                .requiresChildren("stripped_log")
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(ModTags.Items.KITCHEN, Registries.ITEM)
                .addTag(ModTags.Items.STORAGE, Registries.ITEM)
                .setTab(ModCreativeTabs.MAIN)
                .addTile(ModBlockEntities.KITCHEN_DRAWER)
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(strippedKitchenDrawers);

        kitchenSinkDark = SimpleEntrySet.builder(WoodType.class, "kitchen_sink_dark",
                        ModBlocks.KITCHEN_SINK_DARK_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new KitchenSinkBlock(Utils.copyPropertySafe(w.planks), true))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(ModTags.Items.KITCHEN, Registries.ITEM)
                .setTab(ModCreativeTabs.MAIN)
                .addTile(ModBlockEntities.KITCHEN_SINK)
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(kitchenSinkDark);

        strippedKitchenSinkDark = SimpleEntrySet.builder(WoodType.class, "kitchen_sink_dark", "stripped",
                        ModBlocks.KITCHEN_SINK_DARK_STRIPPED_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new KitchenSinkBlock(Utils.copyPropertySafe(w.log), true))
                .requiresChildren("stripped_log")
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(ModTags.Items.KITCHEN, Registries.ITEM)
                .setTab(ModCreativeTabs.MAIN)
                .addTile(ModBlockEntities.KITCHEN_SINK)
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(strippedKitchenSinkDark);

        kitchenSinkLight = SimpleEntrySet.builder(WoodType.class, "kitchen_sink_light",
                        ModBlocks.KITCHEN_SINK_LIGHT_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new KitchenSinkBlock(Utils.copyPropertySafe(w.planks), true))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(ModTags.Items.KITCHEN, Registries.ITEM)
                .setTab(ModCreativeTabs.MAIN)
                .addTile(ModBlockEntities.KITCHEN_SINK)
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(kitchenSinkLight);

        strippedKitchenSinkLight = SimpleEntrySet.builder(WoodType.class, "kitchen_sink_light", "stripped",
                        ModBlocks.KITCHEN_SINK_LIGHT_STRIPPED_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new KitchenSinkBlock(Utils.copyPropertySafe(w.log), true))
                .requiresChildren("stripped_log")
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(ModTags.Items.KITCHEN, Registries.ITEM)
                .setTab(ModCreativeTabs.MAIN)
                .addTile(ModBlockEntities.KITCHEN_SINK)
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(strippedKitchenSinkLight);

        mailBoxes = SimpleEntrySet.builder(WoodType.class, "mail_box",
                        ModBlocks.MAIL_BOX_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new MailBoxBlock(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(ModTags.Items.OUTDOORS, Registries.ITEM)
                .addTag(ModTags.Items.STORAGE, Registries.ITEM)
                .setTab(ModCreativeTabs.MAIN)
                .addTile(ModBlockEntities.MAIL_BOX)
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(mailBoxes);

        strippedMailBoxes = SimpleEntrySet.builder(WoodType.class, "mail_box", "stripped",
                        ModBlocks.MAIL_BOX_STRIPPED_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new MailBoxBlock(Utils.copyPropertySafe(w.log)))
                .requiresChildren("stripped_log")
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(ModTags.Items.OUTDOORS, Registries.ITEM)
                .addTag(ModTags.Items.STORAGE, Registries.ITEM)
                .setTab(ModCreativeTabs.MAIN)
                .addTile(ModBlockEntities.MAIL_BOX)
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(strippedMailBoxes);

        strippedTables = SimpleEntrySet.builder(WoodType.class, "table", "stripped",
                        ModBlocks.TABLE_STRIPPED_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TableBlock(Utils.copyPropertySafe(w.log)))
                .requiresChildren("stripped_log")
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(ModTags.Items.GENERAL, Registries.ITEM)
                .setTab(ModCreativeTabs.MAIN)
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(strippedTables);

        tables = SimpleEntrySet.builder(WoodType.class, "table",
                        ModBlocks.TABLE_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TableBlock(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(ModTags.Items.GENERAL, Registries.ITEM)
                .setTab(ModCreativeTabs.MAIN)
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(tables);

        upgradedFences = SimpleEntrySet.builder(WoodType.class, "upgraded_fence",
                        ModBlocks.UPGRADED_FENCE_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new UpgradedFenceBlock(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.FENCES, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(ModTags.Blocks.UPGRADED_FENCES, Registries.BLOCK)
                .addTag(ModTags.Items.OUTDOORS, Registries.ITEM)
                .addTag(ModTags.Items.UPGRADED_FENCES, Registries.ITEM)
                .setTab(ModCreativeTabs.MAIN)
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(upgradedFences);

        strippedUpgradedFences = SimpleEntrySet.builder(WoodType.class, "upgraded_fence", "stripped",
                        ModBlocks.UPGRADED_FENCE_STRIPPED_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new UpgradedFenceBlock(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.FENCES, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(ModTags.Blocks.UPGRADED_FENCES, Registries.BLOCK)
                .addTag(ModTags.Items.OUTDOORS, Registries.ITEM)
                .addTag(ModTags.Items.UPGRADED_FENCES, Registries.ITEM)
                .setTab(ModCreativeTabs.MAIN)
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(strippedUpgradedFences);

        upgradedGates = SimpleEntrySet.builder(WoodType.class, "upgraded_gate",
                        ModBlocks.UPGRADED_GATE_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new UpgradedGateBlock(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.FENCE_GATES, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.UNSTABLE_BOTTOM_CENTER, Registries.BLOCK)
                .addTag(ModTags.Blocks.UPGRADED_FENCE_GATES, Registries.BLOCK)
                .addTag(ModTags.Items.OUTDOORS, Registries.ITEM)
                .addTag(ModTags.Items.UPGRADED_FENCE_GATES, Registries.ITEM)
                .setTab(ModCreativeTabs.MAIN)
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(upgradedGates);

        strippedUpgradedGates = SimpleEntrySet.builder(WoodType.class, "upgraded_gate", "stripped",
                        ModBlocks.UPGRADED_GATE_STRIPPED_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new UpgradedGateBlock(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.FENCE_GATES, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.UNSTABLE_BOTTOM_CENTER, Registries.BLOCK)
                .addTag(ModTags.Blocks.UPGRADED_FENCE_GATES, Registries.BLOCK)
                .addTag(ModTags.Items.OUTDOORS, Registries.ITEM)
                .addTag(ModTags.Items.UPGRADED_FENCE_GATES, Registries.ITEM)
                .setTab(ModCreativeTabs.MAIN)
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(strippedUpgradedGates);

        hedges = SimpleEntrySet.builder(LeavesType.class, "hedge",
                        ModBlocks.HEDGE_OAK, () -> LeavesTypeRegistry.OAK_TYPE,
                        w -> {
                            var l = w.getBlockOfThis("leaves");
                            if (l == null) return null;
                            return new HedgeBlock(Utils.copyPropertySafe(l).lightLevel((s) -> 0));
                        })
                .requiresChildren("leaves") // Reason: RECIPES
                .addTag(ModTags.Blocks.HEDGES, Registries.BLOCK)
                .addTag(ModTags.Items.HEDGES, Registries.ITEM)
                .addTag(ModTags.Items.OUTDOORS, Registries.ITEM)
                .addModelTransform(m -> m.replaceWithTextureFromChild("minecraft:block/oak_leaves",
                        "leaves", s -> !s.contains("/snow") && !s.contains("_snow")))
                .setTab(ModCreativeTabs.MAIN)
                .defaultRecipe()
                .copyParentTint()
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .build();
        this.addEntry(hedges);
    }
}
