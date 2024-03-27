package net.mehvahdjukaar.every_compat.modules.forge.workshop;

import moonfather.workshop_for_handsome_adventurer.Constants;
import moonfather.workshop_for_handsome_adventurer.blocks.*;
import moonfather.workshop_for_handsome_adventurer.initialization.ExternalWoodSupport;
import moonfather.workshop_for_handsome_adventurer.initialization.Registration;
import moonfather.workshop_for_handsome_adventurer.items.BlockItemEx;
import moonfather.workshop_for_handsome_adventurer.items.WorkstationPlacerItem;
import moonfather.workshop_for_handsome_adventurer.other.CreativeTab;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.ItemOnlyEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.resources.*;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;

import java.text.MessageFormat;
import java.util.*;

public class WorkshopForHandsomeAdventurerModule extends SimpleModule {
    private static final ResourceLocation SPRUCE = new ResourceLocation("spruce");
    private static final ResourceLocation TAG_FORGE_WORKBENCH = new ResourceLocation("forge", "workbench");
    private static final ResourceLocation TAG_PACKINGTAPE_BLACKLIST = new ResourceLocation("packingtape", "te_blacklist");
    public final SimpleEntrySet<WoodType, Block> BOOKSHELVES1, BOOKSHELVES2, BOOKSHELVES3, BOOKSHELVES4, BOOKSHELVES5;
    public final SimpleEntrySet<WoodType, Block> SIMPLE_TABLES, POTIONSHELVES1, DUAL_TABLE_PARTS_BL, DUAL_TABLE_PARTS_BR, DUAL_TABLE_PARTS_TL, DUAL_TABLE_PARTS_TR;
    public final SimpleEntrySet<WoodType, Block> TOOLRACKS1, TOOLRACKS2, TOOLRACKS3, TOOLRACKS4;
    public final ItemOnlyEntrySet<WoodType, Item> STATION_PLACERS;

    public WorkshopForHandsomeAdventurerModule(String modId) {
        super(modId, "wfha");

        BOOKSHELVES1 = SimpleEntrySet.builder(WoodType.class,
                        "", "book_shelf_double",
                        getModBlock("book_shelf_double_spruce"),
                        () -> WoodTypeRegistry.getValue(SPRUCE),
                        w -> new BookShelf.Dual("double"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(TAG_PACKINGTAPE_BLACKLIST, Registries.BLOCK)
                .addRecipe(modRes("book_shelf_double_spruce"))
                .setTab(() -> CreativeTab.TAB_WORKSHOP)
                .addTile(Registration.BOOK_SHELF_BE)
                .addCustomItem((wood, block, prop) -> new BlockItemEx( block, prop))
                .build();
        this.addEntry(BOOKSHELVES1);
        BOOKSHELVES2 = SimpleEntrySet.builder(WoodType.class,
                        "", "book_shelf_open_double",
                        getModBlock("book_shelf_open_double_spruce"),
                        () -> WoodTypeRegistry.getValue(SPRUCE),
                        w -> new BookShelf.Dual("open_double"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(TAG_PACKINGTAPE_BLACKLIST, Registries.BLOCK)
                .addRecipe(modRes("book_shelf_open_double_spruce"))
                .setTab(() -> CreativeTab.TAB_WORKSHOP)
                .addCustomItem((wood, block, prop) -> new BlockItemEx((Block) block, (Item.Properties) prop))
                .addTile(Registration.BOOK_SHELF_BE)
                .build();
        this.addEntry(BOOKSHELVES2);
        BOOKSHELVES3 = SimpleEntrySet.builder(WoodType.class,
                        "", "book_shelf_minimal",
                        getModBlock("book_shelf_minimal_spruce"),
                        () -> WoodTypeRegistry.getValue(SPRUCE),
                        w -> new BookShelf.TopSimple("minimal"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(TAG_PACKINGTAPE_BLACKLIST, Registries.BLOCK)
                .defaultRecipe()
                .setTab(() -> CreativeTab.TAB_WORKSHOP)
                .addCustomItem((wood, block, prop) -> new BlockItemEx((Block) block, (Item.Properties) prop))
                .addTile(Registration.BOOK_SHELF_BE)
                .build();
        this.addEntry(BOOKSHELVES3);
        BOOKSHELVES4 = SimpleEntrySet.builder(WoodType.class,
                        "", "book_shelf_open_minimal",
                        getModBlock("book_shelf_open_minimal_spruce"),
                        () -> WoodTypeRegistry.getValue(SPRUCE),
                        w -> new BookShelf.TopSimple("open_minimal"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(TAG_PACKINGTAPE_BLACKLIST, Registries.BLOCK)
                .addRecipe(modRes("book_shelf_open_minimal_spruce"))
                .addRecipe(modRes("book_shelf_open_minimal_from_double_spruce"))
                .setTab(() -> CreativeTab.TAB_WORKSHOP)
                .addCustomItem((wood, block, prop) -> new BlockItemEx((Block) block, (Item.Properties) prop))
                .addTile(Registration.BOOK_SHELF_BE)
                .build();
        this.addEntry(BOOKSHELVES4);
        BOOKSHELVES5 = SimpleEntrySet.builder(WoodType.class,
                        "", "book_shelf_with_lanterns",
                        getModBlock("book_shelf_with_lanterns_spruce"),
                        () -> WoodTypeRegistry.getValue(SPRUCE),
                        w -> new BookShelf.TopWithLanterns("with_lanterns"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(TAG_PACKINGTAPE_BLACKLIST, Registries.BLOCK)
                .defaultRecipe()
                .setTab(() -> CreativeTab.TAB_WORKSHOP)
                .addCustomItem((wood, block, prop) -> new BlockItemEx((Block) block, (Item.Properties) prop))
                .addTile(Registration.BOOK_SHELF_BE)
                .build();
        this.addEntry(BOOKSHELVES5);
        SIMPLE_TABLES = SimpleEntrySet.builder(WoodType.class,
                        "", "simple_table",
                        getModBlock("simple_table_spruce"),
                        () -> WoodTypeRegistry.getValue(SPRUCE),
                        (w) -> new SimpleTable())
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .requiresChildren("stripped_log")
                .addTag(TAG_FORGE_WORKBENCH, Registries.BLOCK)
                .addTag(TAG_FORGE_WORKBENCH, Registries.ITEM)
                .addRecipe(modRes("simple_table_normal_spruce"))
                .addRecipe(modRes("simple_table_replacement_spruce"))
                .setTab(() -> CreativeTab.TAB_WORKSHOP)
                .addCustomItem((wood, block, prop) -> new BlockItemEx((Block) block, (Item.Properties) prop))
                .addTile(Registration.SIMPLE_TABLE_BE)
                .build();
        this.addEntry(SIMPLE_TABLES);
        TOOLRACKS1 = SimpleEntrySet.builder(WoodType.class,
                        "", "tool_rack_framed",
                        getModBlock("tool_rack_framed_spruce"),
                        () -> WoodTypeRegistry.getValue(SPRUCE),
                        w -> new DualToolRack(6, "framed"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(TAG_PACKINGTAPE_BLACKLIST, Registries.BLOCK)
                .defaultRecipe()
                .setTab(() -> CreativeTab.TAB_WORKSHOP)
                .addCustomItem((wood, block, prop) -> new BlockItemEx((Block) block, (Item.Properties) prop))
                .addTile(Registration.TOOL_RACK_BE)
                .build();
        this.addEntry(TOOLRACKS1);
        TOOLRACKS2 = SimpleEntrySet.builder(WoodType.class,
                        "", "tool_rack_pframed",
                        getModBlock("tool_rack_pframed_spruce"),
                        () -> WoodTypeRegistry.getValue(SPRUCE),
                        w -> new DualToolRack(6, "pframed"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(TAG_PACKINGTAPE_BLACKLIST, Registries.BLOCK)
                .defaultRecipe()
                .setTab(() -> CreativeTab.TAB_WORKSHOP)
                .addCustomItem((wood, block, prop) -> new BlockItemEx((Block) block, (Item.Properties) prop))
                .addTile(Registration.TOOL_RACK_BE)
                .build();
        this.addEntry(TOOLRACKS2);
        TOOLRACKS3 = SimpleEntrySet.builder(WoodType.class,
                        "", "tool_rack_double",
                        getModBlock("tool_rack_double_spruce"),
                        () -> WoodTypeRegistry.getValue(SPRUCE),
                        w -> new DualToolRack(6, "double"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(TAG_PACKINGTAPE_BLACKLIST, Registries.BLOCK)
                .defaultRecipe()
                .setTab(() -> CreativeTab.TAB_WORKSHOP)
                .addCustomItem((wood, block, prop) -> new BlockItemEx((Block) block, (Item.Properties) prop))
                .addTile(Registration.TOOL_RACK_BE)
                .build();
        this.addEntry(TOOLRACKS3);
        TOOLRACKS4 = SimpleEntrySet.builder(WoodType.class,
                        "", "tool_rack_single",
                        getModBlock("tool_rack_single_spruce"),
                        () -> WoodTypeRegistry.getValue(SPRUCE),
                        w -> new ToolRack(2, "single"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(TAG_PACKINGTAPE_BLACKLIST, Registries.BLOCK)
                .defaultRecipe()
                .addRecipe(modRes("tool_rack_single_from_multi_spruce"))
                .setTab(() -> CreativeTab.TAB_WORKSHOP)
                .addCustomItem((wood, block, prop) -> new BlockItemEx((Block) block, (Item.Properties) prop))
                .addTile(Registration.TOOL_RACK_BE)
                .build();
        this.addEntry(TOOLRACKS4);
        POTIONSHELVES1 = SimpleEntrySet.builder(WoodType.class,
                        "", "potion_shelf",
                        getModBlock("potion_shelf_spruce"),
                        () -> WoodTypeRegistry.getValue(SPRUCE),
                        w -> new PotionShelf())
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(TAG_PACKINGTAPE_BLACKLIST, Registries.BLOCK)
                .defaultRecipe()
                .setTab(() -> CreativeTab.TAB_WORKSHOP)
                .addCustomItem((wood, block, prop) -> new BlockItemEx((Block) block, (Item.Properties) prop))
                .addTile(Registration.POTION_SHELF_BE)
                .build();
        this.addEntry(POTIONSHELVES1);
        DUAL_TABLE_PARTS_TR = SimpleEntrySet.builder(WoodType.class,
                        "", "dual_table_top_right",
                        getModBlock("dual_table_top_right_spruce"),
                        () -> WoodTypeRegistry.getValue(SPRUCE),
                        w -> new AdvancedTableTopSecondary())
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .noItem()
                .requiresChildren("stripped_log")
                .build();
        this.addEntry(DUAL_TABLE_PARTS_TR);
        DUAL_TABLE_PARTS_TL = SimpleEntrySet.builder(WoodType.class,
                        "", "dual_table_top_left",
                        getModBlock("dual_table_top_left_spruce"),
                        () -> WoodTypeRegistry.getValue(SPRUCE),
                        w -> new AdvancedTableTopSecondary())
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .noItem()
                .requiresChildren("stripped_log")
                .build();
        this.addEntry(DUAL_TABLE_PARTS_TL);
        DUAL_TABLE_PARTS_BR = SimpleEntrySet.builder(WoodType.class,
                        "", "dual_table_bottom_right",
                        getModBlock("dual_table_bottom_right_spruce"),
                        () -> WoodTypeRegistry.getValue(SPRUCE),
                        w -> new AdvancedTableBottomSecondary())
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .noItem()
                .requiresChildren("stripped_log")
                .build();
        this.addEntry(DUAL_TABLE_PARTS_BR);
        DUAL_TABLE_PARTS_BL = SimpleEntrySet.builder(WoodType.class,
                        "", "dual_table_bottom_left",
                        getModBlock("dual_table_bottom_left_spruce"),
                        () -> WoodTypeRegistry.getValue(SPRUCE),
                        w -> new AdvancedTableBottomPrimary())
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(TAG_PACKINGTAPE_BLACKLIST, Registries.BLOCK)
                .requiresChildren("stripped_log")
                .noItem()
                .addTile(Registration.DUAL_TABLE_BE)
                .build();
        this.addEntry(DUAL_TABLE_PARTS_BL);

        STATION_PLACERS = ItemOnlyEntrySet.builder(WoodType.class,"", "workstation_placer",
                        getModItem("workstation_placer_spruce"),
                        () -> WoodTypeRegistry.getValue(SPRUCE),
                        w -> new WorkstationPlacerItem(w.getTypeName(), new Item.Properties()))
                .addRecipe(modRes("workstation_placer_spruce"))
                .addCondition(DUAL_TABLE_PARTS_BL.blocks::containsKey)
                .build();
        this.addEntry(STATION_PLACERS);

    }

    @Override
    public void addDynamicServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicServerResources(handler, manager);

        // we need to generate tag file for supported planks
        SimpleTagBuilder tagBuilder = SimpleTagBuilder.of(new ResourceLocation(Constants.MODID, "supported_planks"));
        SIMPLE_TABLES.blocks.forEach((w, value) -> tagBuilder.add(ForgeRegistries.ITEMS.getKey(w.planks.asItem())));
        handler.dynamicPack.addTag(tagBuilder, Registries.ITEM);
    }

    @Override
    public void onModInit() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(WorkshopForHandsomeAdventurerModule::sendIMC);
    }


    public static void sendIMC(final InterModEnqueueEvent event) {
        ArrayList<String> blacklist = new ArrayList<>();
        for (WoodType w : WoodTypeRegistry.getTypes()) {
            if (!w.getNamespace().equals("minecraft")) {
                // we need to tell carry on not to break our multiblock structures
                blacklist.add(MessageFormat.format("{0}:wfha/{1}/tool_rack_double_{2}", EveryCompat.MOD_ID, w.getNamespace(), w.getTypeName()));
                blacklist.add(MessageFormat.format("{0}:wfha/{1}/tool_rack_framed_{2}", EveryCompat.MOD_ID, w.getNamespace(), w.getTypeName()));
                blacklist.add(MessageFormat.format("{0}:wfha/{1}/tool_rack_pframed_{2}", EveryCompat.MOD_ID, w.getNamespace(), w.getTypeName()));
                if (w.getBlockOfThis("stripped_log") != null) {
                    blacklist.add(MessageFormat.format("{0}:wfha/{1}/dual_table_bottom_left_{2}", EveryCompat.MOD_ID, w.getNamespace(), w.getTypeName()));
                    blacklist.add(MessageFormat.format("{0}:wfha/{1}/dual_table_bottom_right_{2}", EveryCompat.MOD_ID, w.getNamespace(), w.getTypeName()));
                    blacklist.add(MessageFormat.format("{0}:wfha/{1}/dual_table_top_left_{2}", EveryCompat.MOD_ID, w.getNamespace(), w.getTypeName()));
                    blacklist.add(MessageFormat.format("{0}:wfha/{1}/dual_table_top_right_{2}", EveryCompat.MOD_ID, w.getNamespace(), w.getTypeName()));
                    // we need to register things for the workstation placer item - so that it knows what blocks to use
                    ExternalWoodSupport.registerHostMod(w.getTypeName(), EveryCompat.MOD_ID);
                    ExternalWoodSupport.registerPrefix(w.getTypeName(), "wfha/" + w.getNamespace() + "/");
                }
            }
        }
        for (String block : blacklist) {
            InterModComms.sendTo("carryon", "blacklistBlock", () -> block);
        }
    }

}
