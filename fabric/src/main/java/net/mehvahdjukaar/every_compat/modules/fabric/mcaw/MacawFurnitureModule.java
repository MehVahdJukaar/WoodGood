package net.mehvahdjukaar.every_compat.modules.fabric.mcaw;

import net.kikoz.mcwfurnitures.init.BlockInit;
import net.kikoz.mcwfurnitures.util.FurnitureGroup;
import net.kikoz.mcwfurnitures.objects.Chair;
import net.kikoz.mcwfurnitures.objects.chairs.StripedChair;
import net.kikoz.mcwfurnitures.objects.chairs.ModernChair;
import net.kikoz.mcwfurnitures.objects.counters.Counter;
import net.kikoz.mcwfurnitures.objects.counters.CupboardCounter;
import net.kikoz.mcwfurnitures.objects.counters.StorageCounter;
import net.kikoz.mcwfurnitures.objects.TallFurniture;
import net.kikoz.mcwfurnitures.objects.TallFurnitureHinge;
import net.kikoz.mcwfurnitures.objects.Desk;
import net.kikoz.mcwfurnitures.objects.Table;
import net.kikoz.mcwfurnitures.objects.TableHitbox;
import net.kikoz.mcwfurnitures.objects.WideFurniture;

import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;

// SUPPORT: v3.2.0
public class MacawFurnitureModule extends SimpleModule {

    //TYPE: BOOKSHELF
    public final SimpleEntrySet<WoodType, Block> bookshelf;
    public final SimpleEntrySet<WoodType, Block> cupboardBookshelf;
    public final SimpleEntrySet<WoodType, Block> strippedBookshelf;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_CUPBOARD_BOOKSHELF;

    //TYPE: CHAIR
    public final SimpleEntrySet<WoodType, Block> chair;
    public final SimpleEntrySet<WoodType, Block> modernChair;
    public final SimpleEntrySet<WoodType, Block> stripedChair;
    public final SimpleEntrySet<WoodType, Block> stool;

    public final SimpleEntrySet<WoodType, Block> strippedChair;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_MODERN_CHAIR;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_STRIPED_CHAIR;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_STOOL;

    //TYPE: COUNTER
    public final SimpleEntrySet<WoodType, Block> counter;
    public final SimpleEntrySet<WoodType, Block> drawerCounter;
    public final SimpleEntrySet<WoodType, Block> cupboardCounter;
    public final SimpleEntrySet<WoodType, Block> doubleDrawerCounter;

    public final SimpleEntrySet<WoodType, Block> strippedCounter;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_DRAWER_COUNTER;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_CUPBOARD_COUNTER;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_DOUBLE_DRAWER_COUNTER;

    //TYPE: DRAWER
    public final SimpleEntrySet<WoodType, Block> drawer;
    public final SimpleEntrySet<WoodType, Block> bookshelfDrawer;
    public final SimpleEntrySet<WoodType, Block> doubleDrawer;
    public final SimpleEntrySet<WoodType, Block> largeDrawer;
    public final SimpleEntrySet<WoodType, Block> lowerBookshelfDrawer;
    public final SimpleEntrySet<WoodType, Block> lowerTripleDrawer;
    public final SimpleEntrySet<WoodType, Block> TRIPLE_DRAWER;

    public final SimpleEntrySet<WoodType, Block> strippedBookshelfDrawer;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_DOUBLE_DRAWER;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_DRAWER;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_LARGE_DRAWER;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_LOWER_BOOKSHELF_DRAWER;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_LOWER_TRIPLE_DRAWER;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_TRIPLE_DRAWER;

    //TYPE: DESK
    public final SimpleEntrySet<WoodType, Block> desk;
    public final SimpleEntrySet<WoodType, Block> coveredDesk;
    public final SimpleEntrySet<WoodType, Block> modernDesk;

    public final SimpleEntrySet<WoodType, Block> STRIPPED_DESK;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_MODERN_DESK;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_COVERED_DESK;

    //TYPE: TABLE
    public final SimpleEntrySet<WoodType, Block> TABLE;
    public final SimpleEntrySet<WoodType, Block> glassTable;
    public final SimpleEntrySet<WoodType, Block> endTable;
    public final SimpleEntrySet<WoodType, Block> coffeeTable;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_END_TABLE;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_GLASS_TABLE;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_TABLE;
    public final SimpleEntrySet<WoodType, Block> strippedCoffeeTable;

    //TYPE: WARDROBE
    public final SimpleEntrySet<WoodType, Block> WARDROBE;
    public final SimpleEntrySet<WoodType, Block> doubleWardrobe;
    public final SimpleEntrySet<WoodType, Block> modernWardrobe;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_DOUBLE_WARDROBE;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_MODERN_WARDROBE;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_WARDROBE;

    public MacawFurnitureModule(String modId) {
        super(modId, "mcfur");
        CreativeModeTab tab = FurnitureGroup.FURNITUREGROUP;


        WARDROBE = SimpleEntrySet.builder(WoodType.class, "wardrobe",
                        () -> BlockInit.OAK_WARDROBE, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new TallFurnitureHinge(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("wardrobe"), Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                // .addTile(BlockEntityInit.FURNITURE_STORAGE)
                .defaultRecipe()
                .build();

        this.addEntry(WARDROBE);

        modernWardrobe = SimpleEntrySet.builder(WoodType.class, "modern_wardrobe",
                        () -> BlockInit.OAK_MODERN_WARDROBE, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new TallFurnitureHinge(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(modRes("modern_wardrobe"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                // .addTile(BlockEntityInit.FURNITURE_STORAGE)
                .defaultRecipe()
                .build();

        this.addEntry(modernWardrobe);

        doubleWardrobe = SimpleEntrySet.builder(WoodType.class, "double_wardrobe",
                        () -> BlockInit.OAK_DOUBLE_WARDROBE, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new TallFurniture(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(modRes("double_wardrobe"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                // .addTile(BlockEntityInit.FURNITURE_STORAGE)
                .defaultRecipe()
                .build();

        this.addEntry(doubleWardrobe);

        bookshelf = SimpleEntrySet.builder(WoodType.class, "bookshelf",
                        () -> BlockInit.OAK_BOOKSHELF, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new TallFurniture(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("bookshelf"), Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                // .addTile(BlockEntityInit.FURNITURE_STORAGE)
                .defaultRecipe()
                .build();

        this.addEntry(bookshelf);

        cupboardBookshelf = SimpleEntrySet.builder(WoodType.class, "bookshelf_cupboard",
                        () -> BlockInit.OAK_BOOKSHELF_CUPBOARD, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new TallFurnitureHinge(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(modRes("bookshelf_cupboard"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                // .addTile(BlockEntityInit.FURNITURE_STORAGE)
                .defaultRecipe()
                .build();

        this.addEntry(cupboardBookshelf);

        drawer = SimpleEntrySet.builder(WoodType.class, "drawer",
                        () -> BlockInit.OAK_DRAWER, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new WideFurniture(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("drawer"), Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                // .addTile(BlockEntityInit.FURNITURE_STORAGE)
                .defaultRecipe()
                .build();

        this.addEntry(drawer);

        doubleDrawer = SimpleEntrySet.builder(WoodType.class, "double_drawer",
                        () -> BlockInit.OAK_DOUBLE_DRAWER, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new WideFurniture(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(modRes("double_drawer"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                // .addTile(BlockEntityInit.FURNITURE_STORAGE)
                .defaultRecipe()
                .build();

        this.addEntry(doubleDrawer);

        bookshelfDrawer = SimpleEntrySet.builder(WoodType.class, "bookshelf_drawer",
                        () -> BlockInit.OAK_BOOKSHELF_DRAWER, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new WideFurniture(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(modRes("bookshelf_drawer"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                // .addTile(BlockEntityInit.FURNITURE_STORAGE)
                .defaultRecipe()
                .build();

        this.addEntry(bookshelfDrawer);

        lowerBookshelfDrawer = SimpleEntrySet.builder(WoodType.class, "lower_bookshelf_drawer",
                        () -> BlockInit.OAK_LOWER_BOOKSHELF_DRAWER, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new WideFurniture(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(modRes("lower_bookshelf_drawer"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                // .addTile(BlockEntityInit.FURNITURE_STORAGE)
                .defaultRecipe()
                .build();

        this.addEntry(lowerBookshelfDrawer);

        largeDrawer = SimpleEntrySet.builder(WoodType.class, "large_drawer",
                        () -> BlockInit.OAK_LARGE_DRAWER, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new WideFurniture(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(modRes("large_drawer"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                // .addTile(BlockEntityInit.FURNITURE_STORAGE)
                .defaultRecipe()
                .build();

        this.addEntry(largeDrawer);

        lowerTripleDrawer = SimpleEntrySet.builder(WoodType.class, "lower_triple_drawer",
                        () -> BlockInit.OAK_LOWER_TRIPLE_DRAWER, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new WideFurniture(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(modRes("lower_triple_drawer"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                // .addTile(BlockEntityInit.FURNITURE_STORAGE)
                .defaultRecipe()
                .build();

        this.addEntry(lowerTripleDrawer);

        TRIPLE_DRAWER = SimpleEntrySet.builder(WoodType.class, "triple_drawer",
                        () -> BlockInit.OAK_TRIPLE_DRAWER, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new WideFurniture(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(modRes("triple_drawer"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                // .addTile(BlockEntityInit.FURNITURE_STORAGE)
                .defaultRecipe()
                .build();

        this.addEntry(TRIPLE_DRAWER);

        desk = SimpleEntrySet.builder(WoodType.class, "desk",
                        () -> BlockInit.OAK_DESK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Desk(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("desk"), Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(desk);

        coveredDesk = SimpleEntrySet.builder(WoodType.class, "covered_desk",
                        () -> BlockInit.OAK_COVERED_DESK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Desk(Utils.copyPropertySafe(w.log)))
                .addTag(modRes("covered_desk"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(coveredDesk);

        modernDesk = SimpleEntrySet.builder(WoodType.class, "modern_desk",
                        () -> BlockInit.OAK_MODERN_DESK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Desk(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("modern_desk"), Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(modernDesk);

        TABLE = SimpleEntrySet.builder(WoodType.class, "table",
                        () -> BlockInit.OAK_TABLE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TableHitbox(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("table"), Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(TABLE);

        endTable = SimpleEntrySet.builder(WoodType.class, "end_table",
                        () -> BlockInit.OAK_END_TABLE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TableHitbox(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("end_table"), Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(endTable);

        coffeeTable = SimpleEntrySet.builder(WoodType.class, "coffee_table",
                        () -> BlockInit.OAK_COFFEE_TABLE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Table(Utils.copyPropertySafe(w.log)))
                .addTag(modRes("coffee_table"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(coffeeTable);

        glassTable = SimpleEntrySet.builder(WoodType.class, "glass_table",
                        () -> BlockInit.OAK_GLASS_TABLE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TableHitbox(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("glass_table"), Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(glassTable);

        chair = SimpleEntrySet.builder(WoodType.class, "chair",
                        () -> BlockInit.OAK_CHAIR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new StripedChair(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("chair"), Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(chair);

        modernChair = SimpleEntrySet.builder(WoodType.class, "modern_chair",
                        () -> BlockInit.OAK_MODERN_CHAIR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ModernChair(Utils.copyPropertySafe(w.log)))
                .addTag(modRes("modern_chair"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(modernChair);

        stripedChair = SimpleEntrySet.builder(WoodType.class, "striped_chair",
                        () -> BlockInit.OAK_STRIPED_CHAIR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new StripedChair(Utils.copyPropertySafe(w.log)))
                .addTag(modRes("striped_chair"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(stripedChair);

        stool = SimpleEntrySet.builder(WoodType.class, "stool_chair",
                        () -> BlockInit.OAK_STOOL_CHAIR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Chair(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("stool_chair"), Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(stool);

        counter = SimpleEntrySet.builder(WoodType.class, "counter",
                        () -> BlockInit.OAK_COUNTER, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new Counter(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("counter"), Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(counter);

        drawerCounter = SimpleEntrySet.builder(WoodType.class, "drawer_counter",
                        () -> BlockInit.OAK_DRAWER_COUNTER, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new StorageCounter(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(modRes("drawer_counter"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                // .addTile(BlockEntityInit.FURNITURE_STORAGE)
                .defaultRecipe()
                .build();

        this.addEntry(drawerCounter);

        doubleDrawerCounter = SimpleEntrySet.builder(WoodType.class, "double_drawer_counter",
                        () -> BlockInit.OAK_DOUBLE_DRAWER_COUNTER, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new StorageCounter(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(modRes("double_drawer_counter"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                // .addTile(BlockEntityInit.FURNITURE_STORAGE)
                .defaultRecipe()
                .build();

        this.addEntry(doubleDrawerCounter);

        cupboardCounter = SimpleEntrySet.builder(WoodType.class, "cupboard_counter",
                        () -> BlockInit.OAK_CUPBOARD_COUNTER, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new CupboardCounter(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(modRes("cupboard_counter"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::solid)
                .setTab(() -> tab)
                // .addTile(BlockEntityInit.FURNITURE_STORAGE)
                .defaultRecipe()
                .build();

        this.addEntry(cupboardCounter);

        STRIPPED_WARDROBE = SimpleEntrySet.builder(WoodType.class, "wardrobe", "stripped",
                        () -> BlockInit.STRIPPED_OAK_WARDROBE, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new TallFurnitureHinge(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("wardrobe"), Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                // .addTile(BlockEntityInit.FURNITURE_STORAGE)
                .defaultRecipe()
                .build();

        this.addEntry(STRIPPED_WARDROBE);

        STRIPPED_MODERN_WARDROBE = SimpleEntrySet.builder(WoodType.class, "modern_wardrobe", "stripped",
                        () -> BlockInit.STRIPPED_OAK_MODERN_WARDROBE, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new TallFurnitureHinge(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(modRes("modern_wardrobe"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                // .addTile(BlockEntityInit.FURNITURE_STORAGE)
                .defaultRecipe()
                .build();

        this.addEntry(STRIPPED_MODERN_WARDROBE);

        STRIPPED_DOUBLE_WARDROBE = SimpleEntrySet.builder(WoodType.class, "double_wardrobe", "stripped",
                        () -> BlockInit.STRIPPED_OAK_DOUBLE_WARDROBE, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new TallFurniture(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(modRes("double_wardrobe"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                // .addTile(BlockEntityInit.FURNITURE_STORAGE)
                .defaultRecipe()
                .build();

        this.addEntry(STRIPPED_DOUBLE_WARDROBE);

        strippedBookshelf = SimpleEntrySet.builder(WoodType.class, "bookshelf", "stripped",
                        () -> BlockInit.STRIPPED_OAK_BOOKSHELF, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new TallFurniture(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("bookshelf"), Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                // .addTile(BlockEntityInit.FURNITURE_STORAGE)
                .defaultRecipe()
                .build();

        this.addEntry(strippedBookshelf);

        STRIPPED_CUPBOARD_BOOKSHELF = SimpleEntrySet.builder(WoodType.class, "bookshelf_cupboard", "stripped",
                        () -> BlockInit.STRIPPED_OAK_BOOKSHELF_CUPBOARD, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new TallFurnitureHinge(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(modRes("bookshelf_cupboard"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                // .addTile(BlockEntityInit.FURNITURE_STORAGE)
                .defaultRecipe()
                .build();

        this.addEntry(STRIPPED_CUPBOARD_BOOKSHELF);

        STRIPPED_DRAWER = SimpleEntrySet.builder(WoodType.class, "drawer", "stripped",
                        () -> BlockInit.STRIPPED_OAK_DRAWER, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new WideFurniture(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("drawer"), Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                // .addTile(BlockEntityInit.FURNITURE_STORAGE)
                .defaultRecipe()
                .build();

        this.addEntry(STRIPPED_DRAWER);

        STRIPPED_DOUBLE_DRAWER = SimpleEntrySet.builder(WoodType.class, "double_drawer", "stripped",
                        () -> BlockInit.STRIPPED_OAK_DOUBLE_DRAWER, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new WideFurniture(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(modRes("double_drawer"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                // .addTile(BlockEntityInit.FURNITURE_STORAGE)
                .defaultRecipe()
                .build();

        this.addEntry(STRIPPED_DOUBLE_DRAWER);

        strippedBookshelfDrawer = SimpleEntrySet.builder(WoodType.class, "bookshelf_drawer", "stripped",
                        () -> BlockInit.STRIPPED_OAK_BOOKSHELF_DRAWER, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new WideFurniture(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(modRes("bookshelf_drawer"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                // .addTile(BlockEntityInit.FURNITURE_STORAGE)
                .defaultRecipe()
                .build();

        this.addEntry(strippedBookshelfDrawer);

        STRIPPED_LOWER_BOOKSHELF_DRAWER = SimpleEntrySet.builder(WoodType.class, "lower_bookshelf_drawer", "stripped",
                        () -> BlockInit.STRIPPED_OAK_LOWER_BOOKSHELF_DRAWER, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new WideFurniture(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(modRes("lower_bookshelf_drawer"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                // .addTile(BlockEntityInit.FURNITURE_STORAGE)
                .defaultRecipe()
                .build();

        this.addEntry(STRIPPED_LOWER_BOOKSHELF_DRAWER);

        STRIPPED_LARGE_DRAWER = SimpleEntrySet.builder(WoodType.class, "large_drawer", "stripped",
                        () -> BlockInit.STRIPPED_OAK_LARGE_DRAWER, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new WideFurniture(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(modRes("large_drawer"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                // .addTile(BlockEntityInit.FURNITURE_STORAGE)
                .defaultRecipe()
                .build();

        this.addEntry(STRIPPED_LARGE_DRAWER);

        STRIPPED_LOWER_TRIPLE_DRAWER = SimpleEntrySet.builder(WoodType.class, "lower_triple_drawer", "stripped",
                        () -> BlockInit.STRIPPED_OAK_LOWER_TRIPLE_DRAWER, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new WideFurniture(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(modRes("lower_triple_drawer"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                // .addTile(BlockEntityInit.FURNITURE_STORAGE)
                .defaultRecipe()
                .build();

        this.addEntry(STRIPPED_LOWER_TRIPLE_DRAWER);

        STRIPPED_TRIPLE_DRAWER = SimpleEntrySet.builder(WoodType.class, "triple_drawer", "stripped",
                        () -> BlockInit.STRIPPED_OAK_TRIPLE_DRAWER, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new WideFurniture(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(modRes("triple_drawer"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                // .addTile(BlockEntityInit.FURNITURE_STORAGE)
                .defaultRecipe()
                .build();

        this.addEntry(STRIPPED_TRIPLE_DRAWER);

        STRIPPED_DESK = SimpleEntrySet.builder(WoodType.class, "desk", "stripped",
                        () -> BlockInit.STRIPPED_OAK_DESK, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new Desk(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("desk"), Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(STRIPPED_DESK);

        STRIPPED_COVERED_DESK = SimpleEntrySet.builder(WoodType.class, "covered_desk", "stripped",
                        () -> BlockInit.STRIPPED_OAK_COVERED_DESK, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new Desk(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(modRes("covered_desk"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(STRIPPED_COVERED_DESK);

        STRIPPED_MODERN_DESK = SimpleEntrySet.builder(WoodType.class, "modern_desk", "stripped",
                        () -> BlockInit.STRIPPED_OAK_MODERN_DESK, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new Desk(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("modern_desk"), Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(STRIPPED_MODERN_DESK);

        STRIPPED_TABLE = SimpleEntrySet.builder(WoodType.class, "table", "stripped",
                        () -> BlockInit.STRIPPED_OAK_TABLE, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new TableHitbox(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("table"), Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(STRIPPED_TABLE);

        STRIPPED_END_TABLE = SimpleEntrySet.builder(WoodType.class, "end_table", "stripped",
                        () -> BlockInit.STRIPPED_OAK_END_TABLE, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new TableHitbox(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("end_table"), Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(STRIPPED_END_TABLE);

        strippedCoffeeTable = SimpleEntrySet.builder(WoodType.class, "coffee_table", "stripped",
                        () -> BlockInit.STRIPPED_OAK_COFFEE_TABLE, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new Table(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(modRes("coffee_table"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(strippedCoffeeTable);

        STRIPPED_GLASS_TABLE = SimpleEntrySet.builder(WoodType.class, "glass_table", "stripped",
                        () -> BlockInit.STRIPPED_OAK_GLASS_TABLE, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new TableHitbox(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("glass_table"), Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(STRIPPED_GLASS_TABLE);

        strippedChair = SimpleEntrySet.builder(WoodType.class, "chair", "stripped",
                        () -> BlockInit.STRIPPED_OAK_CHAIR, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new StripedChair(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("chair"), Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(strippedChair);

        STRIPPED_MODERN_CHAIR = SimpleEntrySet.builder(WoodType.class, "modern_chair", "stripped",
                        () -> BlockInit.STRIPPED_OAK_MODERN_CHAIR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ModernChair(Utils.copyPropertySafe(w.log)))
                .addTag(modRes("modern_chair"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(STRIPPED_MODERN_CHAIR);

        STRIPPED_STRIPED_CHAIR = SimpleEntrySet.builder(WoodType.class, "striped_chair", "stripped",
                        () -> BlockInit.STRIPPED_OAK_STRIPED_CHAIR, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new StripedChair(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(modRes("striped_chair"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(STRIPPED_STRIPED_CHAIR);

        STRIPPED_STOOL = SimpleEntrySet.builder(WoodType.class, "stool_chair", "stripped",
                        () -> BlockInit.STRIPPED_OAK_STOOL_CHAIR, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new Chair(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("stool_chair"), Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(STRIPPED_STOOL);

        strippedCounter = SimpleEntrySet.builder(WoodType.class, "counter", "stripped",
                        () -> BlockInit.STRIPPED_OAK_COUNTER, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new Counter(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("counter"), Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(strippedCounter);

        STRIPPED_DRAWER_COUNTER = SimpleEntrySet.builder(WoodType.class, "drawer_counter", "stripped",
                        () -> BlockInit.STRIPPED_OAK_DRAWER_COUNTER, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new StorageCounter(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(modRes("drawer_counter"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                // .addTile(BlockEntityInit.FURNITURE_STORAGE)
                .defaultRecipe()
                .build();

        this.addEntry(STRIPPED_DRAWER_COUNTER);

        STRIPPED_DOUBLE_DRAWER_COUNTER = SimpleEntrySet.builder(WoodType.class, "double_drawer_counter", "stripped",
                        () -> BlockInit.STRIPPED_OAK_DOUBLE_DRAWER_COUNTER, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new StorageCounter(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(modRes("double_drawer_counter"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                // .addTile(BlockEntityInit.FURNITURE_STORAGE)
                .defaultRecipe()
                .build();

        this.addEntry(STRIPPED_DOUBLE_DRAWER_COUNTER);

        STRIPPED_CUPBOARD_COUNTER = SimpleEntrySet.builder(WoodType.class, "cupboard_counter", "stripped",
                        () -> BlockInit.STRIPPED_OAK_CUPBOARD_COUNTER, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new CupboardCounter(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(modRes("cupboard_counter"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::solid)
                .setTab(() -> tab)
                // .addTile(BlockEntityInit.FURNITURE_STORAGE)
                .defaultRecipe()
                .build();

        this.addEntry(STRIPPED_CUPBOARD_COUNTER);
    }
}
