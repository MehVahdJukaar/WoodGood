package net.mehvahdjukaar.every_compat.modules.forge.workshop;

import com.google.gson.JsonObject;
import moonfather.workshop_for_handsome_adventurer.Constants;
import moonfather.workshop_for_handsome_adventurer.block_entities.*;
import moonfather.workshop_for_handsome_adventurer.blocks.*;
import moonfather.workshop_for_handsome_adventurer.initialization.ExternalWoodSupport;
import moonfather.workshop_for_handsome_adventurer.items.BlockItemEx;
import moonfather.workshop_for_handsome_adventurer.items.WorkstationPlacerItem;
import moonfather.workshop_for_handsome_adventurer.other.CreativeTab;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.configs.WoodConfigs;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.forge.BlockTypeEnabledCondition;
import net.mehvahdjukaar.moonlight.api.events.AfterLanguageLoadEvent;
import net.mehvahdjukaar.moonlight.api.misc.Registrator;
import net.mehvahdjukaar.moonlight.api.platform.ClientPlatformHelper;
import net.mehvahdjukaar.moonlight.api.resources.*;
import net.mehvahdjukaar.moonlight.api.resources.recipe.IRecipeTemplate;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.core.recipe.ShapedRecipeTemplate;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.Registry;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.registries.ForgeRegistries;

import java.io.ByteArrayInputStream;
import java.text.MessageFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class WorkshopForHandsomeAdventurerModule extends SimpleModule
{
    private static final ResourceLocation SPRUCE = new ResourceLocation("spruce");
    private static final ResourceLocation TAG_FORGE_WORKBENCH = new ResourceLocation("forge", "workbench");
    private static final ResourceLocation TAG_PACKINGTAPE_BLACKLIST = new ResourceLocation("packingtape", "te_blacklist");
    public final SimpleEntrySet<WoodType, Block> BOOKSHELVES1, BOOKSHELVES2, BOOKSHELVES3, BOOKSHELVES4, BOOKSHELVES5;
    public final SimpleEntrySet<WoodType, Block> SIMPLE_TABLES, POTIONSHELVES1, DUAL_TABLE_PARTS_BL, DUAL_TABLE_PARTS_BR, DUAL_TABLE_PARTS_TL, DUAL_TABLE_PARTS_TR;
    public final SimpleEntrySet<WoodType, Block> TOOLRACKS1, TOOLRACKS2, TOOLRACKS3, TOOLRACKS4;

    public WorkshopForHandsomeAdventurerModule(String modId)
    {
        super(modId, "wfha");

        BOOKSHELVES1 = SimpleEntrySet.builder(WoodType.class,
                                             "", "book_shelf_double",
                                             () -> getModBlock("book_shelf_double_spruce"),
                                             () -> WoodTypeRegistry.getValue(SPRUCE),
                                             w -> new WorkshopForHandsomeAdventurerBlockEntities.CompatBookShelfDual("double"))
                                     .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                                     .addTag(TAG_PACKINGTAPE_BLACKLIST, Registry.BLOCK_REGISTRY)
                                     .addRecipe(modRes("book_shelf_double_spruce"))
                                     .setTab(() -> CreativeTab.TAB_WORKSHOP)
                                     .addCustomItem( (wood, block, prop) -> new BlockItemEx((Block) block, (Item.Properties) prop) )
                                     .addTile(WorkshopForHandsomeAdventurerBlockEntities.CompatBookShelfBE::new)
                                     .build();
        this.addEntry(BOOKSHELVES1);
        BOOKSHELVES2 = SimpleEntrySet.builder(WoodType.class,
                                             "", "book_shelf_open_double",
                                             () -> getModBlock("book_shelf_open_double_spruce"),
                                             () -> WoodTypeRegistry.getValue(SPRUCE),
                                             w -> new WorkshopForHandsomeAdventurerBlockEntities.CompatBookShelfDual("open_double"))
                                     .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                                     .addTag(TAG_PACKINGTAPE_BLACKLIST, Registry.BLOCK_REGISTRY)
                                     .addRecipe(modRes("book_shelf_open_double_spruce"))
                                     .setTab(() -> CreativeTab.TAB_WORKSHOP)
                                     .addCustomItem( (wood, block, prop) -> new BlockItemEx((Block) block, (Item.Properties) prop) )
                                     .addTile(WorkshopForHandsomeAdventurerBlockEntities.CompatBookShelfBE::new)
                                     .build();
        this.addEntry(BOOKSHELVES2);
        BOOKSHELVES3 = SimpleEntrySet.builder(WoodType.class,
                                                                                           "", "book_shelf_minimal",
                                                                                                 () -> getModBlock("book_shelf_minimal_spruce"),
                                                                                                 () -> WoodTypeRegistry.getValue(SPRUCE),
                                                                                                 w -> new WorkshopForHandsomeAdventurerBlockEntities.CompatBookShelfTopSimple("minimal"))
                                                                                         .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                                                                                         .addTag(TAG_PACKINGTAPE_BLACKLIST, Registry.BLOCK_REGISTRY)
                                                                                         .defaultRecipe()
                                                                                         .setTab(() -> CreativeTab.TAB_WORKSHOP)
                                                                                         .addCustomItem( (wood, block, prop) -> new BlockItemEx((Block) block, (Item.Properties) prop) )
                                                                                         .addTile(WorkshopForHandsomeAdventurerBlockEntities.CompatBookShelfBE::new)
                                                                                         .build();
        this.addEntry(BOOKSHELVES3);
        BOOKSHELVES4 = SimpleEntrySet.builder(WoodType.class,
                                                                                           "", "book_shelf_open_minimal",
                                                                                                 () -> getModBlock("book_shelf_open_minimal_spruce"),
                                                                                                 () -> WoodTypeRegistry.getValue(SPRUCE),
                                                                                                 w -> new WorkshopForHandsomeAdventurerBlockEntities.CompatBookShelfTopSimple("open_minimal"))
                                                                                         .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                                                                                         .addTag(TAG_PACKINGTAPE_BLACKLIST, Registry.BLOCK_REGISTRY)
                                                                                         .addRecipe(modRes("book_shelf_open_minimal_spruce"))
                                                                                         .addRecipe(modRes("book_shelf_open_minimal_from_double_spruce"))
                                                                                         .setTab(() -> CreativeTab.TAB_WORKSHOP)
                                                                                         .addCustomItem( (wood, block, prop) -> new BlockItemEx((Block) block, (Item.Properties) prop) )
                                                                                         .addTile(WorkshopForHandsomeAdventurerBlockEntities.CompatBookShelfBE::new)
                                                                                         .build();
        this.addEntry(BOOKSHELVES4);
        BOOKSHELVES5 = SimpleEntrySet.builder(WoodType.class,
                                                                                           "", "book_shelf_with_lanterns",
                                                                                                 () -> getModBlock("book_shelf_with_lanterns_spruce"),
                                                                                                 () -> WoodTypeRegistry.getValue(SPRUCE),
                                                                                                 w -> new WorkshopForHandsomeAdventurerBlockEntities.CompatBookShelfTopWithLanterns("with_lanterns"))
                                                                                         .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                                                                                         .addTag(TAG_PACKINGTAPE_BLACKLIST, Registry.BLOCK_REGISTRY)
                                                                                         .defaultRecipe()
                                                                                         .setTab(() -> CreativeTab.TAB_WORKSHOP)
                                                                                         .addCustomItem( (wood, block, prop) -> new BlockItemEx((Block) block, (Item.Properties) prop) )
                                                                                         .addTile(WorkshopForHandsomeAdventurerBlockEntities.CompatBookShelfBE::new)
                                                                                         .build();
        this.addEntry(BOOKSHELVES5);
        SIMPLE_TABLES = SimpleEntrySet.builder(WoodType.class,
                                             "", "simple_table",
                                             () -> getModBlock("simple_table_spruce"),
                                             () -> WoodTypeRegistry.getValue(SPRUCE),
                                             (w) -> w.getBlockOfThis("stripped_log") != null ? new WorkshopForHandsomeAdventurerBlockEntities.CompatSimpleCraftingTable() : null)
                                     .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                                     .addTag(TAG_FORGE_WORKBENCH, Registry.BLOCK_REGISTRY)
                                     .addTag(TAG_FORGE_WORKBENCH, Registry.ITEM_REGISTRY)
                                     .addRecipe(modRes("simple_table_normal_spruce"))
                                     .addRecipe(modRes("simple_table_replacement_spruce"))
                                     .setTab(() -> CreativeTab.TAB_WORKSHOP)
                                     .addCustomItem( (wood, block, prop) -> new BlockItemEx((Block) block, (Item.Properties) prop) )
                                     .addTile(WorkshopForHandsomeAdventurerBlockEntities.CompatSimpleCraftingTableBE::new)
                                     .build();
        this.addEntry(SIMPLE_TABLES);
        TOOLRACKS1 = SimpleEntrySet.builder(WoodType.class,
                                                                                           "", "tool_rack_framed",
                                                                                                 () -> getModBlock("tool_rack_framed_spruce"),
                                                                                                 () -> WoodTypeRegistry.getValue(SPRUCE),
                                                                                                 w -> new WorkshopForHandsomeAdventurerBlockEntities.CompatDualToolRack1(6, "framed"))
                                                                                         .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                                                                                         .addTag(TAG_PACKINGTAPE_BLACKLIST, Registry.BLOCK_REGISTRY)
                                                                                         .defaultRecipe()
                                                                                         .setTab(() -> CreativeTab.TAB_WORKSHOP)
                                                                                         .addCustomItem( (wood, block, prop) -> new BlockItemEx((Block) block, (Item.Properties) prop) )
                                                                                         .addTile(WorkshopForHandsomeAdventurerBlockEntities.CompatToolRack1BE::new)
                                                                                         .build();
        this.addEntry(TOOLRACKS1);
        TOOLRACKS2 = SimpleEntrySet.builder(WoodType.class,
                                                                                         "","tool_rack_pframed",
                                                                                               () -> getModBlock("tool_rack_pframed_spruce"),
                                                                                               () -> WoodTypeRegistry.getValue(SPRUCE),
                                                                                               w -> new WorkshopForHandsomeAdventurerBlockEntities.CompatDualToolRack2(6, "pframed"))
                                                                                       .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                                                                                       .addTag(TAG_PACKINGTAPE_BLACKLIST, Registry.BLOCK_REGISTRY)
                                                                                       .defaultRecipe()
                                                                                       .setTab(() -> CreativeTab.TAB_WORKSHOP)
                                                                                       .addCustomItem( (wood, block, prop) -> new BlockItemEx((Block) block, (Item.Properties) prop) )
                                                                                       .addTile(WorkshopForHandsomeAdventurerBlockEntities.CompatToolRack2BE::new)
                                                                                       .build();
        this.addEntry(TOOLRACKS2);
        TOOLRACKS3 = SimpleEntrySet.builder(WoodType.class,
                                                                                         "","tool_rack_double",
                                                                                               () -> getModBlock("tool_rack_double_spruce"),
                                                                                               () -> WoodTypeRegistry.getValue(SPRUCE),
                                                                                               w -> new WorkshopForHandsomeAdventurerBlockEntities.CompatDualToolRack3(6, "double"))
                                                                                       .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                                                                                       .addTag(TAG_PACKINGTAPE_BLACKLIST, Registry.BLOCK_REGISTRY)
                                                                                       .defaultRecipe()
                                                                                       .setTab(() -> CreativeTab.TAB_WORKSHOP)
                                                                                       .addCustomItem( (wood, block, prop) -> new BlockItemEx((Block) block, (Item.Properties) prop) )
                                                                                       .addTile(WorkshopForHandsomeAdventurerBlockEntities.CompatToolRack3BE::new)
                                                                                       .build();
        this.addEntry(TOOLRACKS3);
        TOOLRACKS4 = SimpleEntrySet.builder(WoodType.class,
                                                                                         "", "tool_rack_single",
                                                                                               () -> getModBlock("tool_rack_single_spruce"),
                                                                                               () -> WoodTypeRegistry.getValue(SPRUCE),
                                                                                               w -> new WorkshopForHandsomeAdventurerBlockEntities.CompatToolRack4(2, "single"))
                                                                                       .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                                                                                       .addTag(TAG_PACKINGTAPE_BLACKLIST, Registry.BLOCK_REGISTRY)
                                                                                       .defaultRecipe()
                                                                                       .addRecipe(modRes("tool_rack_single_from_multi_spruce"))
                                                                                       .setTab(() -> CreativeTab.TAB_WORKSHOP)
                                                                                       .addCustomItem( (wood, block, prop) -> new BlockItemEx((Block) block, (Item.Properties) prop) )
                                                                                       .addTile(WorkshopForHandsomeAdventurerBlockEntities.CompatToolRack4BE::new)
                                                                                       .build();
        this.addEntry(TOOLRACKS4);
        POTIONSHELVES1 = SimpleEntrySet.builder(WoodType.class,
                                                                                         "", "potion_shelf",
                                                                                               () -> getModBlock("potion_shelf_spruce"),
                                                                                               () -> WoodTypeRegistry.getValue(SPRUCE),
                                                                                               w -> new WorkshopForHandsomeAdventurerBlockEntities.CompatPotionShelf())
                                                                                       .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                                                                                       .addTag(TAG_PACKINGTAPE_BLACKLIST, Registry.BLOCK_REGISTRY)
                                                                                       .defaultRecipe()
                                                                                       .setTab(() -> CreativeTab.TAB_WORKSHOP)
                                                                                       .addCustomItem( (wood, block, prop) -> new BlockItemEx((Block) block, (Item.Properties) prop) )
                                                                                       .addTile(WorkshopForHandsomeAdventurerBlockEntities.CompatPotionShelfBE::new)
                                                                                       .build();
        this.addEntry(POTIONSHELVES1);
        DUAL_TABLE_PARTS_TR = SimpleEntrySet.builder(WoodType.class,
                                                                                             "", "dual_table_top_right",
                                                                                                   () -> getModBlock("dual_table_top_right_spruce"),
                                                                                                   () -> WoodTypeRegistry.getValue(SPRUCE),
                                                                                                   w -> w.getBlockOfThis("stripped_log") != null ? new AdvancedTableTopSecondary() : null)
                                                                                           .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                                                                                           .noItem()
                                                                                           .build();
        this.addEntry(DUAL_TABLE_PARTS_TR);
        DUAL_TABLE_PARTS_TL = SimpleEntrySet.builder(WoodType.class,
                                                                                                  "", "dual_table_top_left",
                                                                                                        () -> getModBlock("dual_table_top_left_spruce"),
                                                                                                        () -> WoodTypeRegistry.getValue(SPRUCE),
                                                                                                        w -> w.getBlockOfThis("stripped_log") != null ? new AdvancedTableTopSecondary() : null)
                                                                                                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                                                                                                .noItem()
                                                                                                .build();
        this.addEntry(DUAL_TABLE_PARTS_TL);
        DUAL_TABLE_PARTS_BR = SimpleEntrySet.builder(WoodType.class,
                                                                                                  "", "dual_table_bottom_right",
                                                                                                        () -> getModBlock("dual_table_bottom_right_spruce"),
                                                                                                        () -> WoodTypeRegistry.getValue(SPRUCE),
                                                                                                        w -> w.getBlockOfThis("stripped_log") != null ? new AdvancedTableBottomSecondary() : null)
                                                                                                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                                                                                                .noItem()
                                                                                                .build();
        this.addEntry(DUAL_TABLE_PARTS_BR);
        DUAL_TABLE_PARTS_BL = SimpleEntrySet.builder(WoodType.class,
                                                                                                  "", "dual_table_bottom_left",
                                                                                                        () -> getModBlock("dual_table_bottom_left_spruce"),
                                                                                                        () -> WoodTypeRegistry.getValue(SPRUCE),
                                                                                                        w -> w.getBlockOfThis("stripped_log") != null ? new WorkshopForHandsomeAdventurerBlockEntities.CompatDualCraftingTable() : null)
                                                                                                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                                                                                                .addTag(TAG_PACKINGTAPE_BLACKLIST, Registry.BLOCK_REGISTRY)
                                                                                                .noItem()
                                                                                                .addTile(WorkshopForHandsomeAdventurerBlockEntities.CompatDualCraftingTableBE::new)
                                                                                                .build();
        this.addEntry(DUAL_TABLE_PARTS_BL);
    }



    @Override
    public void registerTiles(Registrator<BlockEntityType<?>> registry)
    {
        super.registerTiles(registry);
        WorkshopForHandsomeAdventurerBlockEntities.SIMPLE_TABLE_BE = (BlockEntityType<? extends SimpleTableBlockEntity>) SIMPLE_TABLES.getTileHolder().get();
        WorkshopForHandsomeAdventurerBlockEntities.BOOK_SHELF_BE = (BlockEntityType<? extends BookShelfBlockEntity>) BOOKSHELVES1.getTileHolder().get();
        WorkshopForHandsomeAdventurerBlockEntities.POTION_SHELF_BE = (BlockEntityType<? extends PotionShelfBlockEntity>) POTIONSHELVES1.getTileHolder().get();
        WorkshopForHandsomeAdventurerBlockEntities.DUAL_TABLE_BE = (BlockEntityType<? extends DualTableBlockEntity>) DUAL_TABLE_PARTS_BL.getTileHolder().get();
        // and now for something entirely stupid
        WorkshopForHandsomeAdventurerBlockEntities.TOOL_RACK_BE_1_AAARGH = (BlockEntityType<? extends ToolRackBlockEntity>) TOOLRACKS1.getTileHolder().get();
        WorkshopForHandsomeAdventurerBlockEntities.TOOL_RACK_BE_2_AHRGHARGG = (BlockEntityType<? extends ToolRackBlockEntity>) TOOLRACKS2.getTileHolder().get();
        WorkshopForHandsomeAdventurerBlockEntities.TOOL_RACK_BE_3_HAAARGH = (BlockEntityType<? extends ToolRackBlockEntity>) TOOLRACKS3.getTileHolder().get();
        WorkshopForHandsomeAdventurerBlockEntities.TOOL_RACK_BE_4_GAAARGHUUHG = (BlockEntityType<? extends ToolRackBlockEntity>) TOOLRACKS4.getTileHolder().get();
    }



    @Override
    public void registerBlockEntityRenderers(ClientPlatformHelper.BlockEntityRendererEvent event)
    {
        super.registerBlockEntityRenderers(event);
        event.register(WorkshopForHandsomeAdventurerBlockEntities.TOOL_RACK_BE_1_AAARGH,	ToolRackTESR::new);
        event.register(WorkshopForHandsomeAdventurerBlockEntities.POTION_SHELF_BE,	ToolRackTESR::new);
        event.register(WorkshopForHandsomeAdventurerBlockEntities.TOOL_RACK_BE_2_AHRGHARGG,	ToolRackTESR::new);
        event.register(WorkshopForHandsomeAdventurerBlockEntities.TOOL_RACK_BE_3_HAAARGH,	ToolRackTESR::new);
        event.register(WorkshopForHandsomeAdventurerBlockEntities.TOOL_RACK_BE_4_GAAARGHUUHG,	ToolRackTESR::new);
    }



    @Override
    public void registerWoodBlocks(Registrator<Block> registry, Collection<WoodType> woodTypes)
    {
        super.registerWoodBlocks(registry, woodTypes);
        for (WoodType w: woodTypes)
        {
            if (! w.getNamespace().equals("minecraft"))
            {
                // we need to tell carry on not to break our multiblock structures
                CarryOnBlacklist.addBlockToBlackList(MessageFormat.format("{0}:wfha/{1}/tool_rack_double_{2}", EveryCompat.MOD_ID, w.getNamespace(), w.getTypeName()));
                CarryOnBlacklist.addBlockToBlackList(MessageFormat.format("{0}:wfha/{1}/tool_rack_framed_{2}", EveryCompat.MOD_ID, w.getNamespace(), w.getTypeName()));
                CarryOnBlacklist.addBlockToBlackList(MessageFormat.format("{0}:wfha/{1}/tool_rack_pframed_{2}", EveryCompat.MOD_ID, w.getNamespace(), w.getTypeName()));
                if (w.getBlockOfThis("stripped_log") != null)
                {
                    CarryOnBlacklist.addBlockToBlackList(MessageFormat.format("{0}:wfha/{1}/dual_table_bottom_left_{2}", EveryCompat.MOD_ID, w.getNamespace(), w.getTypeName()));
                    CarryOnBlacklist.addBlockToBlackList(MessageFormat.format("{0}:wfha/{1}/dual_table_bottom_right_{2}", EveryCompat.MOD_ID, w.getNamespace(), w.getTypeName()));
                    CarryOnBlacklist.addBlockToBlackList(MessageFormat.format("{0}:wfha/{1}/dual_table_top_left_{2}", EveryCompat.MOD_ID, w.getNamespace(), w.getTypeName()));
                    CarryOnBlacklist.addBlockToBlackList(MessageFormat.format("{0}:wfha/{1}/dual_table_top_right_{2}", EveryCompat.MOD_ID, w.getNamespace(), w.getTypeName()));
                    // we need to register things for the workstation placer item - so that it knows what blocks to use
                    ExternalWoodSupport.registerHostMod(w.getTypeName(), EveryCompat.MOD_ID);
                    ExternalWoodSupport.registerPrefix(w.getTypeName(), "wfha/" + w.getNamespace() + "/");
                }
            }
        }
    }



    @Override
    public void registerItems(Registrator<Item> registry)
    {
        super.registerItems(registry);
        // we need to add workstation placer item
        SIMPLE_TABLES.blocks.forEach(
                (w, value) ->
        {
            if (WoodConfigs.isTypeEnabled(w))
            {
                CreativeModeTab tab = EveryCompat.MOD_TAB != null ? EveryCompat.MOD_TAB : CreativeTab.TAB_WORKSHOP;
                Item item = new WorkstationPlacerItem(w.getTypeName(), (new Item.Properties()).tab(tab));
                registry.register(new ResourceLocation(EveryCompat.MOD_ID, "wfha/" + w.getNamespace() + "/workstation_placer_" + w.getTypeName()), item);
            }
        });
    }



    @Override
    public void addDynamicClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager)
    {
        //this copy-pasta should make models for workstation placer item
        Item spruceWorkbench = ForgeRegistries.ITEMS.getValue(workstation);
        if (spruceWorkbench == null || spruceWorkbench.equals(Items.AIR)) { return; }
        try {
            String baseBlockName = "spruce";
            BlockTypeResTransformer<WoodType> itemModifier = standardModelTransformer2(modId, manager,  WoodTypeRegistry.getValue(SPRUCE), baseBlockName);
            StaticResource spruceItemModel = StaticResource.getOrFail(manager, ResType.ITEM_MODELS.getPath(ForgeRegistries.ITEMS.getKey(spruceWorkbench)));

            JsonObject json = RPUtils.deserializeJson(new ByteArrayInputStream(spruceItemModel.data));
            if (json.has("parent")) {
                String parent = json.get("parent").getAsString();
                if (parent.contains("item/generated")) {
                    itemModifier.replaceItemType(baseBlockName);
                }
            }

            SIMPLE_TABLES.blocks.forEach((w, b) ->
                {
                    String path = ForgeRegistries.BLOCKS.getKey(b).getPath();
                    path = path.replace("simple_table", "workstation_placer");
                    ResourceLocation id = new ResourceLocation(EveryCompat.MOD_ID, path);
                    try {
                        StaticResource newRes = itemModifier.transform(spruceItemModel, id, w);
                        assert newRes.location != spruceItemModel.location : "ids cant be the same";
                        handler.dynamicPack.addResource(newRes);
                    } catch (Exception e) {
                        EveryCompat.LOGGER.error("Failed to add {} item model json file:", b, e);
                    }
                } );
        } catch (Exception e) {
            EveryCompat.LOGGER.error("Could not find item model for spruce workstation. ~~~");
        }
        super.addDynamicClientResources(handler, manager);
    }
    private static BlockTypeResTransformer<WoodType> standardModelTransformer2(String modId, ResourceManager manager, WoodType baseType, String oldTypeName)
    {
        BlockTypeResTransformer<WoodType> modelModifier = BlockTypeResTransformer.create(modId, manager);
        modelModifier.IDReplaceType(oldTypeName);
        modelModifier.replaceWoodTextures(baseType);
        modelModifier.replaceGenericType(oldTypeName, "block");
        return modelModifier;
    }
    private final ResourceLocation workstation = new ResourceLocation(Constants.MODID, "workstation_placer_spruce");


    @Override
    public void addTranslations(ClientDynamicResourcesHandler clientDynamicResourcesHandler, AfterLanguageLoadEvent lang)
    {
        super.addTranslations(clientDynamicResourcesHandler, lang);
        // we need to add a translation for workstation placer
        SIMPLE_TABLES.blocks.forEach((w, b) ->
                {
                    String base = lang.getEntry("block_type.workshop_for_handsome_adventurer.workstation_placer");
                    if (base != null) {
                        String typeName = lang.getEntry(w.getTranslationKey());
                        if (typeName != null) {
                            String key = MessageFormat.format("item.{0}.wfha.{1}.workstation_placer_{2}", EveryCompat.MOD_ID, w.getNamespace(), w.getTypeName());
                            lang.addEntry(key, String.format(base, typeName));  // typeName.toLowerCase(Locale.ROOT) would be preferred
                        }
                    }
                } );
    }


    @Override
    public void addDynamicServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager)
    {
        super.addDynamicServerResources(handler, manager);

        // we need to generate tag file for supported planks
        SimpleTagBuilder tagBuilder = SimpleTagBuilder.of(new ResourceLocation(Constants.MODID, "supported_planks"));
        SIMPLE_TABLES.blocks.forEach((w, value) -> tagBuilder.add(ForgeRegistries.ITEMS.getKey(w.planks.asItem())));
        handler.dynamicPack.addTag(tagBuilder, Registry.ITEM_REGISTRY);

        // we need a recipe for workstations
        IRecipeTemplate<?> template = RPUtils.readRecipeAsTemplate(manager, ResType.RECIPES.getPath(workstation));
        WoodType spruce = WoodTypeRegistry.getValue(SPRUCE);
        SIMPLE_TABLES.blocks.forEach((w, b) ->
            {
                if (WoodConfigs.isTypeEnabled(w))
                {
                    try
                    {

                        FinishedRecipe newRecipe = null; // template.createSimilar() doesn't work for non-blocks
                        /////////////
                        String newId = MessageFormat.format("{2}:wfha/{0}/workstation_placer_{1}", w.getNamespace(), w.getTypeName(), EveryCompat.MOD_ID);
                        ShapedRecipeBuilder builder = new ShapedRecipeBuilder(ForgeRegistries.ITEMS.getValue(new ResourceLocation(newId)), 1);
                        boolean atLeastOneChanged = false;

                        Map.Entry e;
                        Ingredient ingredient;
                        ShapedRecipeTemplate shapedTemplate = (ShapedRecipeTemplate) template; // i can
                        for(Iterator var8 = shapedTemplate.keys.entrySet().iterator(); var8.hasNext(); builder.define((Character)e.getKey(), ingredient)) {
                            e = (Map.Entry)var8.next();
                            ingredient = (Ingredient)e.getValue();
                            ItemStack[] var11 = ingredient.getItems();
                            int var12 = var11.length;

                            for (int var13 = 0; var13 < var12; ++var13) {
                                ItemStack in = var11[var13];
                                Item it = in.getItem();
                                if (it != Items.BARRIER) {
                                    ItemLike i = BlockType.changeItemType(it, spruce, w);
                                    if (i != null) {
                                        atLeastOneChanged = true;
                                        ingredient = Ingredient.of(new ItemLike[]{i});
                                        break;  // so, we're just keeping one item?
                                    }
                                }
                            }
                        }
                        if (!atLeastOneChanged) {
                            newRecipe = null;
                        } else {
                            List<String> var10000 = shapedTemplate.pattern;
                            Objects.requireNonNull(builder);
                            var10000.forEach(builder::pattern);
                            builder.group(shapedTemplate.group);
                            builder.unlockedBy("has_planks", InventoryChangeTrigger.TriggerInstance.hasItems(new ItemLike[]{w.planks.asItem()}));
                            AtomicReference<ShapedRecipeBuilder.Result> newRecipe0 = new AtomicReference();
                            builder.save( (r) ->
                                {
                                    newRecipe0.set((ShapedRecipeBuilder.Result) r);
                                }); //! removed id here
                            newRecipe = (ShapedRecipeBuilder.Result) newRecipe0.get();
                        }

                        /////////////
                        if (newRecipe != null)
                        {
                            var builder2 = ConditionalRecipe.builder().addCondition(new BlockTypeEnabledCondition(w));
                            template.getConditions().forEach( c ->
                            {
                                if (c instanceof ICondition c2)
                                builder2.addCondition(c2);
                            } );
                            builder2.addRecipe(newRecipe).build(handler.dynamicPack::addRecipe, newRecipe.getId());
                        }
                    } catch (Exception e) {
                        EveryCompat.LOGGER.error("Failed to generate recipe for placer: " + e.getMessage());
                    }
                }
            } );
    }
}
