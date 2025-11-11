package net.mehvahdjukaar.every_compat.modules.macaw;

import net.kikoz.mcwfurnitures.MacawsFurniture;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

import static net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys.STRIPPED_LOG;

// SUPPORT: v3.4.0+
public abstract class MacawFurnitureAbstractModule extends SimpleModule {

    //TYPE: CABINET
    public final SimpleEntrySet<WoodType, Block> kitchen_cabinet,
            double_kitchen_cabinet,
            glass_kitchen_cabinet;

    public final SimpleEntrySet<WoodType, Block> stripped_kitchen_cabinet,
            stripped_double_kitchen_cabinet,
            stripped_glass_kitchen_cabinet;

    //TYPE: BOOKSHELF
    public final SimpleEntrySet<WoodType, Block> bookshelf,
            cupboardBookshelf,
            strippedBookshelf,
            strippedCupboardBookshelf;

    //TYPE: CHAIR
    public final SimpleEntrySet<WoodType, Block> chair,
            modernChair,
            stripedChair,
            stool;

    public final SimpleEntrySet<WoodType, Block> strippedChair,
            strippedModernChair,
            strippedStripedChair,
            strippedStool;

    //TYPE: COUNTER
    public final SimpleEntrySet<WoodType, Block> counter,
            drawerCounter,
            cupboardCounter,
            doubleDrawerCounter;

    public final SimpleEntrySet<WoodType, Block> strippedCounter,
            strippedDrawerCounter,
            strippedCupboardCounter,
            strippedDoubleDrawerCounter;

    //TYPE: DRAWER
    public final SimpleEntrySet<WoodType, Block> drawer,
            bookshelfDrawer,
            doubleDrawer,
            largeDrawer,
            lowerBookshelfDrawer,
            lowerTripleDrawer,
            tripleDrawer;

    public final SimpleEntrySet<WoodType, Block> strippedBookshelfDrawer,
            strippedDoubleDrawer,
            strippedDrawer,
            strippedLargeDrawer,
            strippedLowerBookshelfDrawer,
            strippedLowerTripleDrawer,
            strippedTripleDrawer;

    //TYPE: DESK
    public final SimpleEntrySet<WoodType, Block> desk,
            coveredDesk,
            modernDesk;

    public final SimpleEntrySet<WoodType, Block> strippedDesk,
            strippedModernDesk,
            strippedCoveredDesk;

    //TYPE: TABLE
    public final SimpleEntrySet<WoodType, Block> table,
            glassTable,
            endTable,
            coffeeTable,
            strippedEndTable,
            strippedGlassTable,
            strippedTable,
            strippedCoffeeTable;

    //TYPE: WARDROBE
    public final SimpleEntrySet<WoodType, Block> wardrobe,
            doubleWardrobe,
            modernWardrobe,
            strippedDoubleWardrobe,
            strippedModernWardrobe,
            strippedWardrobe;



    public MacawFurnitureAbstractModule(String modId) {
        super(modId, "mcfur", EveryCompat.MOD_ID);
        ResourceLocation tab = (PlatHelper.getPlatform().isFabric())
                ? MacawsFurniture.FURNITUREGROUP.location()
                : modRes("furnitures");

        String entityType = (PlatHelper.getPlatform().isFabric()) ? "box_block" : "furniture_storage";

        kitchen_cabinet = SimpleEntrySet.builder(WoodType.class, "kitchen_cabinet",
                        getModBlock("oak_kitchen_cabinet"), () -> VanillaWoodTypes.OAK,
                        this::newCabinetHinge
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                .addTile(getModTile(entityType))
                //TEXTURES: log, stripped_log
                .copyTexture(modRes("block/oak_handle"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("cabinet"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(kitchen_cabinet);

        double_kitchen_cabinet = SimpleEntrySet.builder(WoodType.class, "double_kitchen_cabinet",
                        getModBlock("oak_double_kitchen_cabinet"), () -> VanillaWoodTypes.OAK,
                        this::newCabinet
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                .addTile(getModTile(entityType))
                //TEXTURES: log, stripped_log, oak_handle (kitchen_cabinet)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("cabinet"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(double_kitchen_cabinet);

        glass_kitchen_cabinet = SimpleEntrySet.builder(WoodType.class, "glass_kitchen_cabinet",
                        getModBlock("oak_glass_kitchen_cabinet"), () -> VanillaWoodTypes.OAK,
                        this::newCabinet
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                .addTile(getModTile(entityType))
                //TEXTURES: log, stripped_log, oak_handle (kitchen_cabinet)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("cabinet"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(glass_kitchen_cabinet);


        stripped_kitchen_cabinet = SimpleEntrySet.builder(WoodType.class, "kitchen_cabinet", "stripped",
                        getModBlock("stripped_oak_kitchen_cabinet"), () -> VanillaWoodTypes.OAK,
                        this::newCabinetHinge
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures, recipes
                .addTile(getModTile(entityType))
                //TEXTURES: log, stripped_log, oak_handle (kitchen_cabinet)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("cabinet"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(stripped_kitchen_cabinet);

        stripped_double_kitchen_cabinet = SimpleEntrySet.builder(WoodType.class, "double_kitchen_cabinet", "stripped",
                        getModBlock("stripped_oak_double_kitchen_cabinet"), () -> VanillaWoodTypes.OAK,
                        this::newCabinet
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures, recipes
                .addTile(getModTile(entityType))
                //TEXTURES: log, stripped_log, oak_handle (kitchen_cabinet)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("cabinet"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(stripped_double_kitchen_cabinet);

        stripped_glass_kitchen_cabinet = SimpleEntrySet.builder(WoodType.class, "glass_kitchen_cabinet", "stripped",
                        getModBlock("stripped_oak_glass_kitchen_cabinet"), () -> VanillaWoodTypes.OAK,
                        this::newCabinet
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures, recipes
                .addTile(getModTile(entityType))
                //TEXTURES: log, stripped_log, oak_handle (kitchen_cabinet)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("cabinet"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(stripped_glass_kitchen_cabinet);


        wardrobe = SimpleEntrySet.builder(WoodType.class, "wardrobe",
                        getModBlock("oak_wardrobe"), () -> VanillaWoodTypes.OAK,
                        this::newTallFurnitureHinge
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                .addTile(getModTile(entityType))
                //TEXTURES: log, stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("wardrobe"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(wardrobe);

        modernWardrobe = SimpleEntrySet.builder(WoodType.class, "modern_wardrobe",
                        getModBlock("oak_modern_wardrobe"), () -> VanillaWoodTypes.OAK,
                        this::newTallFurnitureHinge
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                .addTile(getModTile(entityType))
                //TEXTURES: log, stripped_log
                .addTag(modRes("modern_wardrobe"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(modernWardrobe);

        doubleWardrobe = SimpleEntrySet.builder(WoodType.class, "double_wardrobe",
                        getModBlock("oak_double_wardrobe"), () -> VanillaWoodTypes.OAK,
                        this::newTallFurniture
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                .addTile(getModTile(entityType))
                //TEXTURES: log, stripped_log
                .addTag(modRes("double_wardrobe"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(doubleWardrobe);

        bookshelf = SimpleEntrySet.builder(WoodType.class, "bookshelf",
                        getModBlock("oak_bookshelf"), () -> VanillaWoodTypes.OAK,
                        this::newTallFurniture
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                .addTile(getModTile(entityType))
                //TEXTURES: log, stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("bookshelf"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(bookshelf);

        cupboardBookshelf = SimpleEntrySet.builder(WoodType.class, "bookshelf_cupboard",
                        getModBlock("oak_bookshelf_cupboard"), () -> VanillaWoodTypes.OAK,
                        this::newTallFurnitureHinge
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                .addTile(getModTile(entityType))
                //TEXTURES: log, stripped_log
                .addTag(modRes("bookshelf_cupboard"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(cupboardBookshelf);

        drawer = SimpleEntrySet.builder(WoodType.class, "drawer",
                        getModBlock("oak_drawer"), () -> VanillaWoodTypes.OAK,
                        this::newWideFurniture
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                .addTile(getModTile(entityType))
                //TEXTURES: log, stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("drawer"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(drawer);

        doubleDrawer = SimpleEntrySet.builder(WoodType.class, "double_drawer",
                        getModBlock("oak_double_drawer"), () -> VanillaWoodTypes.OAK,
                        this::newWideFurniture
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                .addTile(getModTile(entityType))
                //TEXTURES: log, stripped_log
                .addTag(modRes("double_drawer"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(doubleDrawer);

        bookshelfDrawer = SimpleEntrySet.builder(WoodType.class, "bookshelf_drawer",
                        getModBlock("oak_bookshelf_drawer"), () -> VanillaWoodTypes.OAK,
                        this::newWideFurniture
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                .addTile(getModTile(entityType))
                //TEXTURES: log, stripped_log
                .addTag(modRes("bookshelf_drawer"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(bookshelfDrawer);

        lowerBookshelfDrawer = SimpleEntrySet.builder(WoodType.class, "lower_bookshelf_drawer",
                        getModBlock("oak_lower_bookshelf_drawer"), () -> VanillaWoodTypes.OAK,
                        this::newWideFurniture
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                .addTile(getModTile(entityType))
                //TEXTURES: log, stripped_log
                .addTag(modRes("lower_bookshelf_drawer"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(lowerBookshelfDrawer);

        largeDrawer = SimpleEntrySet.builder(WoodType.class, "large_drawer",
                        getModBlock("oak_large_drawer"), () -> VanillaWoodTypes.OAK,
                        this::newWideFurniture
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                .addTile(getModTile(entityType))
                //TEXTURES: log, stripped_log
                .addTag(modRes("large_drawer"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(largeDrawer);

        lowerTripleDrawer = SimpleEntrySet.builder(WoodType.class, "lower_triple_drawer",
                        getModBlock("oak_lower_triple_drawer"), () -> VanillaWoodTypes.OAK,
                        this::newWideFurniture
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                .addTile(getModTile(entityType))
                //TEXTURES: log, stripped_log
                .addTag(modRes("lower_triple_drawer"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(lowerTripleDrawer);

        tripleDrawer = SimpleEntrySet.builder(WoodType.class, "triple_drawer",
                        getModBlock("oak_triple_drawer"), () -> VanillaWoodTypes.OAK,
                        this::newWideFurniture
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                .addTile(getModTile(entityType))
                //TEXTURES: log, stripped_log
                .addTag(modRes("triple_drawer"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(tripleDrawer);

        desk = SimpleEntrySet.builder(WoodType.class, "desk",
                        getModBlock("oak_desk"), () -> VanillaWoodTypes.OAK,
                        this::newDesk
                )
                //TEXTURES: log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("desk"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(desk);

        coveredDesk = SimpleEntrySet.builder(WoodType.class, "covered_desk",
                        getModBlock("oak_covered_desk"), () -> VanillaWoodTypes.OAK,
                        this::newDesk
                )
                //TEXTURES: log
                .addTag(modRes("covered_desk"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(coveredDesk);

        modernDesk = SimpleEntrySet.builder(WoodType.class, "modern_desk",
                        getModBlock("oak_modern_desk"), () -> VanillaWoodTypes.OAK,
                        this::newDesk
                )
                //TEXTURES: log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("modern_desk"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(modernDesk);

        table = SimpleEntrySet.builder(WoodType.class, "table",
                        getModBlock("oak_table"), () -> VanillaWoodTypes.OAK,
                        this::newTableHitbox
                )
                //TEXTURES: log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("table"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(table);

        endTable = SimpleEntrySet.builder(WoodType.class, "end_table",
                        getModBlock("oak_end_table"), () -> VanillaWoodTypes.OAK,
                        this::newTableHitbox
                )
                .requiresChildren(STRIPPED_LOG)
                //TEXTURES: stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("end_table"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(endTable);

        coffeeTable = SimpleEntrySet.builder(WoodType.class, "coffee_table",
                        getModBlock("oak_coffee_table"), () -> VanillaWoodTypes.OAK,
                        this::newTable
                )
                //TEXTURES: log
                .addTag(modRes("coffee_table"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(coffeeTable);

        glassTable = SimpleEntrySet.builder(WoodType.class, "glass_table",
                        getModBlock("oak_glass_table"), () -> VanillaWoodTypes.OAK,
                        this::newTableHitbox
                )
                //TEXTURES: log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("glass_table"), Registries.BLOCK)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(glassTable);

        chair = SimpleEntrySet.builder(WoodType.class, "chair",
                        getModBlock("oak_chair"), () -> VanillaWoodTypes.OAK,
                        this::newStripedChair
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                //TEXTURES: log, stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("chair"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(chair);

        modernChair = SimpleEntrySet.builder(WoodType.class, "modern_chair",
                        getModBlock("oak_modern_chair"), () -> VanillaWoodTypes.OAK,
                        this::newModernChair
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                //TEXTURES: log, stripped
                .addTag(modRes("modern_chair"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(modernChair);

        stripedChair = SimpleEntrySet.builder(WoodType.class, "striped_chair",
                        getModBlock("oak_striped_chair"), () -> VanillaWoodTypes.OAK,
                        this::newStripedChair
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                //TEXTURES: log, stripped
                .addTag(modRes("striped_chair"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(stripedChair);

        stool = SimpleEntrySet.builder(WoodType.class, "stool_chair",
                        getModBlock("oak_stool_chair"), () -> VanillaWoodTypes.OAK,
                        this::newChair
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                //TEXTURES: log, stripped
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("stool_chair"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(stool);

        counter = SimpleEntrySet.builder(WoodType.class, "counter",
                        getModBlock("oak_counter"), () -> VanillaWoodTypes.OAK,
                        this::newCounter
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                //TEXTURES: log, stripped
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("counter"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(counter);

        drawerCounter = SimpleEntrySet.builder(WoodType.class, "drawer_counter",
                        getModBlock("oak_drawer_counter"), () -> VanillaWoodTypes.OAK,
                        this::newStorageCounter
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                .addTile(getModTile(entityType))
                //TEXTURES: log, stripped
                .addTag(modRes("drawer_counter"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(drawerCounter);

        doubleDrawerCounter = SimpleEntrySet.builder(WoodType.class, "double_drawer_counter",
                        getModBlock("oak_double_drawer_counter"), () -> VanillaWoodTypes.OAK,
                        this::newStorageCounter
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                .addTile(getModTile(entityType))
                //TEXTURES: log, stripped
                .addTag(modRes("double_drawer_counter"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(doubleDrawerCounter);

        cupboardCounter = SimpleEntrySet.builder(WoodType.class, "cupboard_counter",
                        getModBlock("oak_cupboard_counter"), () -> VanillaWoodTypes.OAK,
                        this::newCupboardCounter
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                .addTile(getModTile(entityType))
                //TEXTURES: log, stripped
                .addTag(modRes("cupboard_counter"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setRenderType(RenderLayer.SOLID)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(cupboardCounter);

        strippedWardrobe = SimpleEntrySet.builder(WoodType.class, "wardrobe", "stripped",
                        getModBlock("stripped_oak_wardrobe"), () -> VanillaWoodTypes.OAK,
                        this::newTallFurnitureHinge
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                .addTile(getModTile(entityType))
                //TEXTURES: log, stripped
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("wardrobe"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(strippedWardrobe);

        strippedModernWardrobe = SimpleEntrySet.builder(WoodType.class, "modern_wardrobe", "stripped",
                        getModBlock("stripped_oak_modern_wardrobe"), () -> VanillaWoodTypes.OAK,
                        this::newTallFurnitureHinge
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                .addTile(getModTile(entityType))
                //TEXTURES: log, stripped_log
                .addTag(modRes("modern_wardrobe"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(strippedModernWardrobe);

        strippedDoubleWardrobe = SimpleEntrySet.builder(WoodType.class, "double_wardrobe", "stripped",
                        getModBlock("stripped_oak_double_wardrobe"), () -> VanillaWoodTypes.OAK,
                        this::newTallFurniture
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                .addTile(getModTile(entityType))
                //TEXTURES: log, stripped_log
                .addTag(modRes("double_wardrobe"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(strippedDoubleWardrobe);

        strippedBookshelf = SimpleEntrySet.builder(WoodType.class, "bookshelf", "stripped",
                        getModBlock("stripped_oak_bookshelf"), () -> VanillaWoodTypes.OAK,
                        this::newTallFurniture
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                .addTile(getModTile(entityType))
                //TEXTURES: log, stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("bookshelf"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(strippedBookshelf);

        strippedCupboardBookshelf = SimpleEntrySet.builder(WoodType.class, "bookshelf_cupboard", "stripped",
                        getModBlock("stripped_oak_bookshelf_cupboard"), () -> VanillaWoodTypes.OAK,
                        this::newTallFurnitureHinge
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                .addTile(getModTile(entityType))
                //TEXTURES: log, stripped_log
                .addTag(modRes("bookshelf_cupboard"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(strippedCupboardBookshelf);

        strippedDrawer = SimpleEntrySet.builder(WoodType.class, "drawer", "stripped",
                        getModBlock("stripped_oak_drawer"), () -> VanillaWoodTypes.OAK,
                        this::newWideFurniture
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                .addTile(getModTile(entityType))
                //TEXTURES: log, stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("drawer"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(strippedDrawer);

        strippedDoubleDrawer = SimpleEntrySet.builder(WoodType.class, "double_drawer", "stripped",
                        getModBlock("stripped_oak_double_drawer"), () -> VanillaWoodTypes.OAK,
                        this::newWideFurniture
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                .addTile(getModTile(entityType))
                //TEXTURES: log, stripped_log
                .addTag(modRes("double_drawer"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(strippedDoubleDrawer);

        strippedBookshelfDrawer = SimpleEntrySet.builder(WoodType.class, "bookshelf_drawer", "stripped",
                        getModBlock("stripped_oak_bookshelf_drawer"), () -> VanillaWoodTypes.OAK,
                        this::newWideFurniture
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                .addTile(getModTile(entityType))
                //TEXTURES: log, stripped_log
                .addTag(modRes("bookshelf_drawer"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(strippedBookshelfDrawer);

        strippedLowerBookshelfDrawer = SimpleEntrySet.builder(WoodType.class, "lower_bookshelf_drawer", "stripped",
                        getModBlock("stripped_oak_lower_bookshelf_drawer"), () -> VanillaWoodTypes.OAK,
                        this::newWideFurniture
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                .addTile(getModTile(entityType))
                //TEXTURES: log, stripped_log
                .addTag(modRes("lower_bookshelf_drawer"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(strippedLowerBookshelfDrawer);

        strippedLargeDrawer = SimpleEntrySet.builder(WoodType.class, "large_drawer", "stripped",
                        getModBlock("stripped_oak_large_drawer"), () -> VanillaWoodTypes.OAK,
                        this::newWideFurniture
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                .addTile(getModTile(entityType))
                //TEXTURES: log, stripped_log
                .addTag(modRes("large_drawer"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(strippedLargeDrawer);

        strippedLowerTripleDrawer = SimpleEntrySet.builder(WoodType.class, "lower_triple_drawer", "stripped",
                        getModBlock("stripped_oak_lower_triple_drawer"), () -> VanillaWoodTypes.OAK,
                        this::newWideFurniture
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                .addTile(getModTile(entityType))
                //TEXTURES: log, stripped_log
                .addTag(modRes("lower_triple_drawer"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(strippedLowerTripleDrawer);

        strippedTripleDrawer = SimpleEntrySet.builder(WoodType.class, "triple_drawer", "stripped",
                        getModBlock("stripped_oak_triple_drawer"), () -> VanillaWoodTypes.OAK,
                        this::newWideFurniture
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                .addTile(getModTile(entityType))
                //TEXTURES: log, stripped_log
                .addTag(modRes("triple_drawer"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(strippedTripleDrawer);

        strippedDesk = SimpleEntrySet.builder(WoodType.class, "desk", "stripped",
                        getModBlock("stripped_oak_desk"), () -> VanillaWoodTypes.OAK,
                        this::newDesk
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                //TEXTURES: log, stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("desk"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(strippedDesk);

        strippedCoveredDesk = SimpleEntrySet.builder(WoodType.class, "covered_desk", "stripped",
                        getModBlock("stripped_oak_covered_desk"), () -> VanillaWoodTypes.OAK,
                        this::newDesk
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                //TEXTURES: log, stripped_log
                .addTag(modRes("covered_desk"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(strippedCoveredDesk);

        strippedModernDesk = SimpleEntrySet.builder(WoodType.class, "modern_desk", "stripped",
                        getModBlock("stripped_oak_modern_desk"), () -> VanillaWoodTypes.OAK,
                        this::newDesk
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                //TEXTURES: log, stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("modern_desk"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(strippedModernDesk);

        strippedTable = SimpleEntrySet.builder(WoodType.class, "table", "stripped",
                        getModBlock("stripped_oak_table"), () -> VanillaWoodTypes.OAK,
                        this::newTableHitbox
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                //TEXTURES: log, stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("table"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(strippedTable);

        strippedEndTable = SimpleEntrySet.builder(WoodType.class, "end_table", "stripped",
                        getModBlock("stripped_oak_end_table"), () -> VanillaWoodTypes.OAK,
                        this::newTableHitbox
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                //TEXTURES: log, stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("end_table"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(strippedEndTable);

        strippedCoffeeTable = SimpleEntrySet.builder(WoodType.class, "coffee_table", "stripped",
                        getModBlock("stripped_oak_coffee_table"), () -> VanillaWoodTypes.OAK,
                        this::newTable
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                //TEXTURES: log, stripped_log
                .addTag(modRes("coffee_table"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(strippedCoffeeTable);

        strippedGlassTable = SimpleEntrySet.builder(WoodType.class, "glass_table", "stripped",
                        getModBlock("stripped_oak_glass_table"), () -> VanillaWoodTypes.OAK,
                        this::newTableHitbox
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                //TEXTURES: log, stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("glass_table"), Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT)
                .defaultRecipe()
                .build();
        this.addEntry(strippedGlassTable);

        strippedChair = SimpleEntrySet.builder(WoodType.class, "chair", "stripped",
                        getModBlock("stripped_oak_chair"), () -> VanillaWoodTypes.OAK,
                        this::newStripedChair
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                //TEXTURES: log, stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("chair"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(strippedChair);

        strippedModernChair = SimpleEntrySet.builder(WoodType.class, "modern_chair", "stripped",
                        getModBlock("stripped_oak_modern_chair"), () -> VanillaWoodTypes.OAK,
                        this::newModernChair
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                //TEXTURES: log, stripped_log
                .addTag(modRes("modern_chair"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(strippedModernChair);

        strippedStripedChair = SimpleEntrySet.builder(WoodType.class, "striped_chair", "stripped",
                        getModBlock("stripped_oak_striped_chair"), () -> VanillaWoodTypes.OAK,
                        this::newStripedChair
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                //TEXTURES: log, stripped_log
                .addTag(modRes("striped_chair"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT)
                .defaultRecipe()
                .build();
        this.addEntry(strippedStripedChair);

        strippedStool = SimpleEntrySet.builder(WoodType.class, "stool_chair", "stripped",
                        getModBlock("stripped_oak_stool_chair"), () -> VanillaWoodTypes.OAK,
                        this::newChair
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                //TEXTURES: log, stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("stool_chair"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(strippedStool);

        strippedCounter = SimpleEntrySet.builder(WoodType.class, "counter", "stripped",
                        getModBlock("stripped_oak_counter"), () -> VanillaWoodTypes.OAK,
                        this::newCounter
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                //TEXTURES: log, stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("counter"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(strippedCounter);

        strippedDrawerCounter = SimpleEntrySet.builder(WoodType.class, "drawer_counter", "stripped",
                        getModBlock("stripped_oak_drawer_counter"), () -> VanillaWoodTypes.OAK,
                        this::newStorageCounter
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                .addTile(getModTile(entityType))
                //TEXTURES: log, stripped_log
                .addTag(modRes("drawer_counter"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(strippedDrawerCounter);

        strippedDoubleDrawerCounter = SimpleEntrySet.builder(WoodType.class, "double_drawer_counter", "stripped",
                        getModBlock("stripped_oak_double_drawer_counter"), () -> VanillaWoodTypes.OAK,
                        this::newStorageCounter
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                .addTile(getModTile(entityType))
                //TEXTURES: log, stripped_log
                .addTag(modRes("double_drawer_counter"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(strippedDoubleDrawerCounter);

        strippedCupboardCounter = SimpleEntrySet.builder(WoodType.class, "cupboard_counter", "stripped",
                        getModBlock("stripped_oak_cupboard_counter"), () -> VanillaWoodTypes.OAK,
                        this::newCupboardCounter
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                .addTile(getModTile(entityType))
                //TEXTURES: log, stripped_log
                .addTag(modRes("cupboard_counter"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.SOLID)
                .defaultRecipe()
                .build();
        this.addEntry(strippedCupboardCounter);
    }

    public BlockBehaviour.Properties copyStandardProperties() {
        return BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(2.0F, 2.3F);
    }

    public abstract Block newCabinet(WoodType woodType);
    public abstract Block newCabinetHinge(WoodType woodType);
    public abstract Block newWideFurniture(WoodType woodType);
    public abstract Block newTallFurniture(WoodType woodType);
    public abstract Block newTallFurnitureHinge(WoodType woodType);
    public abstract Block newDesk(WoodType woodType);
    public abstract Block newTable(WoodType woodType);
    public abstract Block newTableHitbox(WoodType woodType);
    public abstract Block newStripedChair(WoodType woodType);
    public abstract Block newModernChair(WoodType woodType);
    public abstract Block newChair(WoodType woodType);
    public abstract Block newCounter(WoodType woodType);
    public abstract Block newStorageCounter(WoodType woodType);
    public abstract Block newCupboardCounter(WoodType woodType);
}
