package net.mehvahdjukaar.every_compat.modules.fabric.mcaw;

import net.kikoz.mcwfurnitures.MacawsFurniture;
import net.kikoz.mcwfurnitures.init.BlockInit;
import net.kikoz.mcwfurnitures.objects.*;
import net.kikoz.mcwfurnitures.objects.chairs.ModernChair;
import net.kikoz.mcwfurnitures.objects.chairs.StripedChair;
import net.kikoz.mcwfurnitures.objects.counters.Counter;
import net.kikoz.mcwfurnitures.objects.counters.CupboardCounter;
import net.kikoz.mcwfurnitures.objects.counters.StorageCounter;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

import static net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys.STRIPPED_LOG;

// SUPPORT: v3.3.0+
public class MacawFurnitureModule extends SimpleModule {

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

    public MacawFurnitureModule(String modId) {
        super(modId, "mcfur");
        ResourceKey<CreativeModeTab> tab = MacawsFurniture.FURNITUREGROUP;

        wardrobe = SimpleEntrySet.builder(WoodType.class, "wardrobe",
                        () -> BlockInit.OAK_WARDROBE, () -> VanillaWoodTypes.OAK,
                        w -> new TallFurnitureHinge(copyStandardProperties())
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                //TEXTURES: log, stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("wardrobe"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(wardrobe);

        modernWardrobe = SimpleEntrySet.builder(WoodType.class, "modern_wardrobe",
                        () -> BlockInit.OAK_MODERN_WARDROBE, () -> VanillaWoodTypes.OAK,
                        w -> new TallFurnitureHinge(copyStandardProperties())
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                //TEXTURES: log, stripped_log
                .addTag(modRes("modern_wardrobe"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(modernWardrobe);

        doubleWardrobe = SimpleEntrySet.builder(WoodType.class, "double_wardrobe",
                        () -> BlockInit.OAK_DOUBLE_WARDROBE, () -> VanillaWoodTypes.OAK,
                        w -> new TallFurniture(copyStandardProperties())
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                //TEXTURES: log, stripped_log
                .addTag(modRes("double_wardrobe"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(doubleWardrobe);

        bookshelf = SimpleEntrySet.builder(WoodType.class, "bookshelf",
                        () -> BlockInit.OAK_BOOKSHELF, () -> VanillaWoodTypes.OAK,
                        w -> new TallFurniture(copyStandardProperties())
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                .addTile(getModTile("box_block"))
                //TEXTURES: log, stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("bookshelf"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(bookshelf);

        cupboardBookshelf = SimpleEntrySet.builder(WoodType.class, "bookshelf_cupboard",
                        () -> BlockInit.OAK_BOOKSHELF_CUPBOARD, () -> VanillaWoodTypes.OAK,
                        w -> new TallFurnitureHinge(copyStandardProperties())
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                //TEXTURES: log, stripped_log
                .addTag(modRes("bookshelf_cupboard"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(cupboardBookshelf);

        drawer = SimpleEntrySet.builder(WoodType.class, "drawer",
                        () -> BlockInit.OAK_DRAWER, () -> VanillaWoodTypes.OAK,
                        w -> new WideFurniture(copyStandardProperties())
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                //TEXTURES: log, stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("drawer"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(drawer);

        doubleDrawer = SimpleEntrySet.builder(WoodType.class, "double_drawer",
                        () -> BlockInit.OAK_DOUBLE_DRAWER, () -> VanillaWoodTypes.OAK,
                        w -> new WideFurniture(copyStandardProperties())
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                //TEXTURES: log, stripped_log
                .addTag(modRes("double_drawer"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(doubleDrawer);

        bookshelfDrawer = SimpleEntrySet.builder(WoodType.class, "bookshelf_drawer",
                        () -> BlockInit.OAK_BOOKSHELF_DRAWER, () -> VanillaWoodTypes.OAK,
                        w -> new WideFurniture(copyStandardProperties())
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                //TEXTURES: log, stripped_log
                .addTag(modRes("bookshelf_drawer"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(bookshelfDrawer);

        lowerBookshelfDrawer = SimpleEntrySet.builder(WoodType.class, "lower_bookshelf_drawer",
                        () -> BlockInit.OAK_LOWER_BOOKSHELF_DRAWER, () -> VanillaWoodTypes.OAK,
                        w -> new WideFurniture(copyStandardProperties())
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                //TEXTURES: log, stripped_log
                .addTag(modRes("lower_bookshelf_drawer"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(lowerBookshelfDrawer);

        largeDrawer = SimpleEntrySet.builder(WoodType.class, "large_drawer",
                        () -> BlockInit.OAK_LARGE_DRAWER, () -> VanillaWoodTypes.OAK,
                        w -> new WideFurniture(copyStandardProperties())
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                //TEXTURES: log, stripped_log
                .addTag(modRes("large_drawer"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(largeDrawer);

        lowerTripleDrawer = SimpleEntrySet.builder(WoodType.class, "lower_triple_drawer",
                        () -> BlockInit.OAK_LOWER_TRIPLE_DRAWER, () -> VanillaWoodTypes.OAK,
                        w -> new WideFurniture(copyStandardProperties())
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                //TEXTURES: log, stripped_log
                .addTag(modRes("lower_triple_drawer"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(lowerTripleDrawer);

        tripleDrawer = SimpleEntrySet.builder(WoodType.class, "triple_drawer",
                        () -> BlockInit.OAK_TRIPLE_DRAWER, () -> VanillaWoodTypes.OAK,
                        w -> new WideFurniture(copyStandardProperties())
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                //TEXTURES: log, stripped_log
                .addTag(modRes("triple_drawer"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(tripleDrawer);

        desk = SimpleEntrySet.builder(WoodType.class, "desk",
                        () -> BlockInit.OAK_DESK, () -> VanillaWoodTypes.OAK,
                        w -> new Desk(copyStandardProperties())
                )
                //TEXTURES: log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("desk"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(desk);

        coveredDesk = SimpleEntrySet.builder(WoodType.class, "covered_desk",
                        () -> BlockInit.OAK_COVERED_DESK, () -> VanillaWoodTypes.OAK,
                        w -> new Desk(copyStandardProperties())
                )
                //TEXTURES: log
                .addTag(modRes("covered_desk"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(coveredDesk);

        modernDesk = SimpleEntrySet.builder(WoodType.class, "modern_desk",
                        () -> BlockInit.OAK_MODERN_DESK, () -> VanillaWoodTypes.OAK,
                        w -> new Desk(copyStandardProperties())
                )
                //TEXTURES: log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("modern_desk"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(modernDesk);

        table = SimpleEntrySet.builder(WoodType.class, "table",
                        () -> BlockInit.OAK_TABLE, () -> VanillaWoodTypes.OAK,
                        w -> new TableHitbox(copyStandardProperties())
                )
                //TEXTURES: log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("table"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(table);

        endTable = SimpleEntrySet.builder(WoodType.class, "end_table",
                        () -> BlockInit.OAK_END_TABLE, () -> VanillaWoodTypes.OAK,
                        w -> new TableHitbox(copyStandardProperties())
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
                        () -> BlockInit.OAK_COFFEE_TABLE, () -> VanillaWoodTypes.OAK,
                        w -> new Table(copyStandardProperties())
                )
                //TEXTURES: log
                .addTag(modRes("coffee_table"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(coffeeTable);

        glassTable = SimpleEntrySet.builder(WoodType.class, "glass_table",
                        () -> BlockInit.OAK_GLASS_TABLE, () -> VanillaWoodTypes.OAK,
                        w -> new TableHitbox(copyStandardProperties())
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
                        () -> BlockInit.OAK_CHAIR, () -> VanillaWoodTypes.OAK,
                        w -> new StripedChair(copyStandardProperties())
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
                        () -> BlockInit.OAK_MODERN_CHAIR, () -> VanillaWoodTypes.OAK,
                        w -> new ModernChair(copyStandardProperties())
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
                        () -> BlockInit.OAK_STRIPED_CHAIR, () -> VanillaWoodTypes.OAK,
                        w -> new StripedChair(copyStandardProperties())
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
                        () -> BlockInit.OAK_STOOL_CHAIR, () -> VanillaWoodTypes.OAK,
                        w -> new Chair(copyStandardProperties())
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
                        () -> BlockInit.OAK_COUNTER, () -> VanillaWoodTypes.OAK,
                        w -> new Counter(copyStandardProperties())
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
                        () -> BlockInit.OAK_DRAWER_COUNTER, () -> VanillaWoodTypes.OAK,
                        w -> new StorageCounter(copyStandardProperties())
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                //TEXTURES: log, stripped
                .addTag(modRes("drawer_counter"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(drawerCounter);

        doubleDrawerCounter = SimpleEntrySet.builder(WoodType.class, "double_drawer_counter",
                        () -> BlockInit.OAK_DOUBLE_DRAWER_COUNTER, () -> VanillaWoodTypes.OAK,
                        w -> new StorageCounter(copyStandardProperties())
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                //TEXTURES: log, stripped
                .addTag(modRes("double_drawer_counter"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(doubleDrawerCounter);

        cupboardCounter = SimpleEntrySet.builder(WoodType.class, "cupboard_counter",
                        () -> BlockInit.OAK_CUPBOARD_COUNTER, () -> VanillaWoodTypes.OAK,
                        w -> new CupboardCounter(copyStandardProperties())
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                //TEXTURES: log, stripped
                .addTag(modRes("cupboard_counter"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setRenderType(RenderLayer.SOLID)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(cupboardCounter);

        strippedWardrobe = SimpleEntrySet.builder(WoodType.class, "wardrobe", "stripped",
                        () -> BlockInit.STRIPPED_OAK_WARDROBE, () -> VanillaWoodTypes.OAK,
                        w -> new TallFurnitureHinge(copyStandardProperties())
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                //TEXTURES: log, stripped
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("wardrobe"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(strippedWardrobe);

        strippedModernWardrobe = SimpleEntrySet.builder(WoodType.class, "modern_wardrobe", "stripped",
                        () -> BlockInit.STRIPPED_OAK_MODERN_WARDROBE, () -> VanillaWoodTypes.OAK,
                        w -> new TallFurnitureHinge(copyStandardProperties())
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                //TEXTURES: log, stripped_log
                .addTag(modRes("modern_wardrobe"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(strippedModernWardrobe);

        strippedDoubleWardrobe = SimpleEntrySet.builder(WoodType.class, "double_wardrobe", "stripped",
                        () -> BlockInit.STRIPPED_OAK_DOUBLE_WARDROBE, () -> VanillaWoodTypes.OAK,
                        w -> new TallFurniture(copyStandardProperties())
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                //TEXTURES: log, stripped_log
                .addTag(modRes("double_wardrobe"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(strippedDoubleWardrobe);

        strippedBookshelf = SimpleEntrySet.builder(WoodType.class, "bookshelf", "stripped",
                        () -> BlockInit.STRIPPED_OAK_BOOKSHELF, () -> VanillaWoodTypes.OAK,
                        w -> new TallFurniture(copyStandardProperties())
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                //TEXTURES: log, stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("bookshelf"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(strippedBookshelf);

        strippedCupboardBookshelf = SimpleEntrySet.builder(WoodType.class, "bookshelf_cupboard", "stripped",
                        () -> BlockInit.STRIPPED_OAK_BOOKSHELF_CUPBOARD, () -> VanillaWoodTypes.OAK,
                        w -> new TallFurnitureHinge(copyStandardProperties())
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                //TEXTURES: log, stripped_log
                .addTag(modRes("bookshelf_cupboard"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(strippedCupboardBookshelf);

        strippedDrawer = SimpleEntrySet.builder(WoodType.class, "drawer", "stripped",
                        () -> BlockInit.STRIPPED_OAK_DRAWER, () -> VanillaWoodTypes.OAK,
                        w -> new WideFurniture(copyStandardProperties())
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                //TEXTURES: log, stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("drawer"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(strippedDrawer);

        strippedDoubleDrawer = SimpleEntrySet.builder(WoodType.class, "double_drawer", "stripped",
                        () -> BlockInit.STRIPPED_OAK_DOUBLE_DRAWER, () -> VanillaWoodTypes.OAK,
                        w -> new WideFurniture(copyStandardProperties())
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                //TEXTURES: log, stripped_log
                .addTag(modRes("double_drawer"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(strippedDoubleDrawer);

        strippedBookshelfDrawer = SimpleEntrySet.builder(WoodType.class, "bookshelf_drawer", "stripped",
                        () -> BlockInit.STRIPPED_OAK_BOOKSHELF_DRAWER, () -> VanillaWoodTypes.OAK,
                        w -> new WideFurniture(copyStandardProperties())
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                //TEXTURES: log, stripped_log
                .addTag(modRes("bookshelf_drawer"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(strippedBookshelfDrawer);

        strippedLowerBookshelfDrawer = SimpleEntrySet.builder(WoodType.class, "lower_bookshelf_drawer", "stripped",
                        () -> BlockInit.STRIPPED_OAK_LOWER_BOOKSHELF_DRAWER, () -> VanillaWoodTypes.OAK,
                        w -> new WideFurniture(copyStandardProperties())
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                //TEXTURES: log, stripped_log
                .addTag(modRes("lower_bookshelf_drawer"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(strippedLowerBookshelfDrawer);

        strippedLargeDrawer = SimpleEntrySet.builder(WoodType.class, "large_drawer", "stripped",
                        () -> BlockInit.STRIPPED_OAK_LARGE_DRAWER, () -> VanillaWoodTypes.OAK,
                        w -> new WideFurniture(copyStandardProperties())
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                //TEXTURES: log, stripped_log
                .addTag(modRes("large_drawer"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(strippedLargeDrawer);

        strippedLowerTripleDrawer = SimpleEntrySet.builder(WoodType.class, "lower_triple_drawer", "stripped",
                        () -> BlockInit.STRIPPED_OAK_LOWER_TRIPLE_DRAWER, () -> VanillaWoodTypes.OAK,
                        w -> new WideFurniture(copyStandardProperties())
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                //TEXTURES: log, stripped_log
                .addTag(modRes("lower_triple_drawer"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(strippedLowerTripleDrawer);

        strippedTripleDrawer = SimpleEntrySet.builder(WoodType.class, "triple_drawer", "stripped",
                        () -> BlockInit.STRIPPED_OAK_TRIPLE_DRAWER, () -> VanillaWoodTypes.OAK,
                        w -> new WideFurniture(copyStandardProperties())
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                //TEXTURES: log, stripped_log
                .addTag(modRes("triple_drawer"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(strippedTripleDrawer);

        strippedDesk = SimpleEntrySet.builder(WoodType.class, "desk", "stripped",
                        () -> BlockInit.STRIPPED_OAK_DESK, () -> VanillaWoodTypes.OAK,
                        w -> new Desk(copyStandardProperties())
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
                        () -> BlockInit.STRIPPED_OAK_COVERED_DESK, () -> VanillaWoodTypes.OAK,
                        w -> new Desk(copyStandardProperties())
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
                        () -> BlockInit.STRIPPED_OAK_MODERN_DESK, () -> VanillaWoodTypes.OAK,
                        w -> new Desk(copyStandardProperties())
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
                        () -> BlockInit.STRIPPED_OAK_TABLE, () -> VanillaWoodTypes.OAK,
                        w -> new TableHitbox(copyStandardProperties())
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
                        () -> BlockInit.STRIPPED_OAK_END_TABLE, () -> VanillaWoodTypes.OAK,
                        w -> new TableHitbox(copyStandardProperties())
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
                        () -> BlockInit.STRIPPED_OAK_COFFEE_TABLE, () -> VanillaWoodTypes.OAK,
                        w -> new Table(copyStandardProperties())
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
                        () -> BlockInit.STRIPPED_OAK_GLASS_TABLE, () -> VanillaWoodTypes.OAK,
                        w -> new TableHitbox(copyStandardProperties())
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
                        () -> BlockInit.STRIPPED_OAK_CHAIR, () -> VanillaWoodTypes.OAK,
                        w -> new StripedChair(copyStandardProperties())
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
                        () -> BlockInit.STRIPPED_OAK_MODERN_CHAIR, () -> VanillaWoodTypes.OAK,
                        w -> new ModernChair(copyStandardProperties())
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
                        () -> BlockInit.STRIPPED_OAK_STRIPED_CHAIR, () -> VanillaWoodTypes.OAK,
                        w -> new StripedChair(copyStandardProperties())
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
                        () -> BlockInit.STRIPPED_OAK_STOOL_CHAIR, () -> VanillaWoodTypes.OAK,
                        w -> new Chair(copyStandardProperties())
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
                        () -> BlockInit.STRIPPED_OAK_COUNTER, () -> VanillaWoodTypes.OAK,
                        w -> new Counter(copyStandardProperties())
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
                        () -> BlockInit.STRIPPED_OAK_DRAWER_COUNTER, () -> VanillaWoodTypes.OAK,
                        w -> new StorageCounter(copyStandardProperties())
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                //TEXTURES: log, stripped_log
                .addTag(modRes("drawer_counter"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(strippedDrawerCounter);

        strippedDoubleDrawerCounter = SimpleEntrySet.builder(WoodType.class, "double_drawer_counter", "stripped",
                        () -> BlockInit.STRIPPED_OAK_DOUBLE_DRAWER_COUNTER, () -> VanillaWoodTypes.OAK,
                        w -> new StorageCounter(copyStandardProperties())
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                //TEXTURES: log, stripped_log
                .addTag(modRes("double_drawer_counter"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(strippedDoubleDrawerCounter);

        strippedCupboardCounter = SimpleEntrySet.builder(WoodType.class, "cupboard_counter", "stripped",
                        () -> BlockInit.STRIPPED_OAK_CUPBOARD_COUNTER, () -> VanillaWoodTypes.OAK,
                        w -> new CupboardCounter(copyStandardProperties())
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
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
}
