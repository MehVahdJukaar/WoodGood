package net.mehvahdjukaar.every_compat.modules.neoforge.workshop;

import moonfather.workshop_for_handsome_adventurer.blocks.*;
import moonfather.workshop_for_handsome_adventurer.initialization.Registration;
import moonfather.workshop_for_handsome_adventurer.items.BlockItemEx;
import moonfather.workshop_for_handsome_adventurer.items.WorkstationPlacerItem;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.ItemOnlyEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.neoforge.EveryCompatForge;
import net.mehvahdjukaar.moonlight.api.resources.SimpleTagBuilder;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.fml.InterModComms;
import net.neoforged.fml.event.lifecycle.InterModEnqueueEvent;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

//SUPPORT: v1.31.2+
public class WorkshopForHandsomeAdventurerModule extends SimpleModule {
    private static final ResourceLocation SPRUCE = ResourceLocation.parse("spruce");
    private static final ResourceLocation TAG_FORGE_WORKBENCH = ResourceLocation.fromNamespaceAndPath("c", "workbench");
    private static final ResourceLocation TAG_PACKINGTAPE_BLACKLIST = ResourceLocation.fromNamespaceAndPath("packingtape", "te_blacklist");
    public final SimpleEntrySet<WoodType, Block> double_bookshelves, open_double_bookshelves, min_bookshelves, open_min_bookshelves, lantern_bookshelves;
    public final SimpleEntrySet<WoodType, Block> simple_tables, potionshelves1, dual_table_parts_bl, dual_table_parts_br, dual_table_parts_tl, dual_table_parts_tr;
    public final SimpleEntrySet<WoodType, Block> framed_toolracks, pframed_toolracks, double_toolracks, single_toolracks;
    public final ItemOnlyEntrySet<WoodType, Item> station_placers;

    public WorkshopForHandsomeAdventurerModule(String modId) {
        super(modId, "wfha");
        var tab = modRes(modId);

        double_bookshelves = SimpleEntrySet.builder(WoodType.class, "", "book_shelf_double",
                        getModBlock("book_shelf_double_spruce"),
                        () -> WoodTypeRegistry.getValue(SPRUCE),
                        w -> new BookShelf.Dual("double")
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(TAG_PACKINGTAPE_BLACKLIST, Registries.BLOCK)
                .addRecipe(modRes("book_shelf_double_spruce"))
                .setTabKey(tab)
                .addTile(Registration.BOOK_SHELF_BE)
                .addCustomItem((wood, block, prop) -> new BlockItemEx( block, prop))
                .build();
        this.addEntry(double_bookshelves);

        open_double_bookshelves = SimpleEntrySet.builder(WoodType.class, "", "book_shelf_open_double",
                        getModBlock("book_shelf_open_double_spruce"),
                        () -> WoodTypeRegistry.getValue(SPRUCE),
                        w -> new BookShelf.Dual("open_double")
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(TAG_PACKINGTAPE_BLACKLIST, Registries.BLOCK)
                .addRecipe(modRes("book_shelf_open_double_spruce"))
                .setTabKey(tab)
                .addCustomItem((wood, block, prop) -> new BlockItemEx(block, prop))
                .addTile(Registration.BOOK_SHELF_BE)
                .build();
        this.addEntry(open_double_bookshelves);

        min_bookshelves = SimpleEntrySet.builder(WoodType.class, "", "book_shelf_minimal",
                        getModBlock("book_shelf_minimal_spruce"),
                        () -> WoodTypeRegistry.getValue(SPRUCE),
                        w -> new BookShelf.TopSimple("minimal")
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(TAG_PACKINGTAPE_BLACKLIST, Registries.BLOCK)
                .defaultRecipe()
                .setTabKey(tab)
                .addCustomItem((wood, block, prop) -> new BlockItemEx(block, prop))
                .addTile(Registration.BOOK_SHELF_BE)
                .build();
        this.addEntry(min_bookshelves);

        open_min_bookshelves = SimpleEntrySet.builder(WoodType.class, "", "book_shelf_open_minimal",
                        getModBlock("book_shelf_open_minimal_spruce"),
                        () -> WoodTypeRegistry.getValue(SPRUCE),
                        w -> new BookShelf.TopSimple("open_minimal")
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(TAG_PACKINGTAPE_BLACKLIST, Registries.BLOCK)
                .addRecipe(modRes("book_shelf_open_minimal_spruce"))
                .addRecipe(modRes("book_shelf_open_minimal_from_double_spruce"))
                .setTabKey(tab)
                .addCustomItem((wood, block, prop) -> new BlockItemEx(block, prop))
                .addTile(Registration.BOOK_SHELF_BE)
                .build();
        this.addEntry(open_min_bookshelves);

        lantern_bookshelves = SimpleEntrySet.builder(WoodType.class, "", "book_shelf_with_lanterns",
                        getModBlock("book_shelf_with_lanterns_spruce"),
                        () -> WoodTypeRegistry.getValue(SPRUCE),
                        w -> new BookShelf.TopWithLanterns("with_lanterns")
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(TAG_PACKINGTAPE_BLACKLIST, Registries.BLOCK)
                .defaultRecipe()
                .setTabKey(tab)
                .addCustomItem((wood, block, prop) -> new BlockItemEx(block, prop))
                .addTile(Registration.BOOK_SHELF_BE)
                .build();
        this.addEntry(lantern_bookshelves);

        simple_tables = SimpleEntrySet.builder(WoodType.class, "", "simple_table",
                        getModBlock("simple_table_spruce"),
                        () -> WoodTypeRegistry.getValue(SPRUCE),
                        (w) -> new SimpleTable()
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .requiresChildren("stripped_log")
                .addTag(TAG_FORGE_WORKBENCH, Registries.BLOCK)
                .addTag(TAG_FORGE_WORKBENCH, Registries.ITEM)
                .addRecipe(modRes("simple_table_normal_spruce"))
                .addRecipe(modRes("simple_table_replacement_spruce"))
                .setTabKey(tab)
                .addCustomItem((wood, block, prop) -> new BlockItemEx(block, prop))
                .addTile(Registration.SIMPLE_TABLE_BE)
                .build();
        this.addEntry(simple_tables);

        framed_toolracks = SimpleEntrySet.builder(WoodType.class, "", "tool_rack_framed",
                        getModBlock("tool_rack_framed_spruce"),
                        () -> WoodTypeRegistry.getValue(SPRUCE),
                        w -> new DualToolRack(6, "framed")
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(TAG_PACKINGTAPE_BLACKLIST, Registries.BLOCK)
                .defaultRecipe()
                .setTabKey(tab)
                .addCustomItem((wood, block, prop) -> new BlockItemEx(block, prop))
                .addTile(Registration.TOOL_RACK_BE)
                .copyParentDrop()
                .build();
        this.addEntry(framed_toolracks);

        pframed_toolracks = SimpleEntrySet.builder(WoodType.class, "", "tool_rack_pframed",
                        getModBlock("tool_rack_pframed_spruce"),
                        () -> WoodTypeRegistry.getValue(SPRUCE),
                        w -> new DualToolRack(6, "pframed")
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(TAG_PACKINGTAPE_BLACKLIST, Registries.BLOCK)
                .defaultRecipe()
                .setTabKey(tab)
                .addCustomItem((wood, block, prop) -> new BlockItemEx(block, prop))
                .addTile(Registration.TOOL_RACK_BE)
                .copyParentDrop()
                .build();
        this.addEntry(pframed_toolracks);

        double_toolracks = SimpleEntrySet.builder(WoodType.class, "", "tool_rack_double",
                        getModBlock("tool_rack_double_spruce"),
                        () -> WoodTypeRegistry.getValue(SPRUCE),
                        w -> new DualToolRack(6, "double")
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(TAG_PACKINGTAPE_BLACKLIST, Registries.BLOCK)
                .defaultRecipe()
                .setTabKey(tab)
                .addCustomItem((wood, block, prop) -> new BlockItemEx(block, prop))
                .addTile(Registration.TOOL_RACK_BE)
                .copyParentDrop()
                .build();
        this.addEntry(double_toolracks);

        single_toolracks = SimpleEntrySet.builder(WoodType.class, "", "tool_rack_single",
                        getModBlock("tool_rack_single_spruce"),
                        () -> WoodTypeRegistry.getValue(SPRUCE),
                        w -> new ToolRack(2, "single")
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(TAG_PACKINGTAPE_BLACKLIST, Registries.BLOCK)
                .defaultRecipe()
                .addRecipe(modRes("tool_rack_single_from_multi_spruce"))
                .setTabKey(tab)
                .addCustomItem((wood, block, prop) -> new BlockItemEx(block, prop))
                .addTile(Registration.TOOL_RACK_BE)
                .build();
        this.addEntry(single_toolracks);

        potionshelves1 = SimpleEntrySet.builder(WoodType.class, "", "potion_shelf",
                        getModBlock("potion_shelf_spruce"),
                        () -> WoodTypeRegistry.getValue(SPRUCE),
                        w -> new PotionShelf()
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(TAG_PACKINGTAPE_BLACKLIST, Registries.BLOCK)
                .defaultRecipe()
                .setTabKey(tab)
                .addCustomItem((wood, block, prop) -> new BlockItemEx(block, prop))
                .addTile(Registration.POTION_SHELF_BE)
                .build();
        this.addEntry(potionshelves1);

        dual_table_parts_tr = SimpleEntrySet.builder(WoodType.class, "", "dual_table_top_right",
                        getModBlock("dual_table_top_right_spruce"),
                        () -> WoodTypeRegistry.getValue(SPRUCE),
                        w -> new AdvancedTableTopSecondary()
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .noItem().noTab()
                .requiresChildren("stripped_log")
                .build();
        this.addEntry(dual_table_parts_tr);

        dual_table_parts_tl = SimpleEntrySet.builder(WoodType.class, "", "dual_table_top_left",
                        getModBlock("dual_table_top_left_spruce"),
                        () -> WoodTypeRegistry.getValue(SPRUCE),
                        w -> new AdvancedTableTopSecondary()
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .noItem().noTab()
                .requiresChildren("stripped_log")
                .build();
        this.addEntry(dual_table_parts_tl);

        dual_table_parts_br = SimpleEntrySet.builder(WoodType.class, "", "dual_table_bottom_right",
                        getModBlock("dual_table_bottom_right_spruce"),
                        () -> WoodTypeRegistry.getValue(SPRUCE),
                        w -> new AdvancedTableBottomSecondary()
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .noItem().noTab()
                .requiresChildren("stripped_log")
                .build();
        this.addEntry(dual_table_parts_br);

        dual_table_parts_bl = SimpleEntrySet.builder(WoodType.class, "", "dual_table_bottom_left",
                        getModBlock("dual_table_bottom_left_spruce"),
                        () -> WoodTypeRegistry.getValue(SPRUCE),
                        w -> new AdvancedTableBottomPrimary()
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(TAG_PACKINGTAPE_BLACKLIST, Registries.BLOCK)
                .requiresChildren("stripped_log")
                .noItem().noTab()
                .addTile(Registration.DUAL_TABLE_BE)
                .build();
        this.addEntry(dual_table_parts_bl);

        station_placers = ItemOnlyEntrySet.builder(WoodType.class,"", "workstation_placer",
                        getModItem("workstation_placer_spruce"),
                        () -> WoodTypeRegistry.getValue(SPRUCE),
                        w -> new WorkstationPlacerItem(w.getTypeName(), new Item.Properties())
                )
                .noTab()
                .addRecipe(modRes("workstation_placer_spruce"))
                .addCondition(dual_table_parts_bl.blocks::containsKey)
                .build();
        this.addEntry(station_placers);

    }

    @Override
    public void addDynamicServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicServerResources(handler, manager);

        // we need to generate tag file for supported planks
        SimpleTagBuilder tagBuilder = SimpleTagBuilder.of(modRes("supported_planks"));
        simple_tables.blocks.forEach((w, value) -> tagBuilder.add(Utils.getID(w.planks.asItem())));
        handler.dynamicPack.addTag(tagBuilder, Registries.ITEM);
    }

    @Override
    public void onModInit() {
        EveryCompatForge.getModEventBus().addListener(WorkshopForHandsomeAdventurerModule::sendIMC);
    }


    @Override
    public List<String> getAlreadySupportedMods() {
        return List.of("biomesoplenty");
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

//                    ExternalWoodSupport.registerHostMod(w.getTypeName(), EveryCompat.MOD_ID);
//                    ExternalWoodSupport.registerPrefix(w.getTypeName(), "wfha/" + w.getNamespace() + "/");
                }
            }
        }
        for (String block : blacklist) {
            InterModComms.sendTo("carryon", "blacklistBlock", () -> block);
        }
    }

}
