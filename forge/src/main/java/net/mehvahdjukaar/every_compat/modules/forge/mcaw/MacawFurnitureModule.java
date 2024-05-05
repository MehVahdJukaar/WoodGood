package net.mehvahdjukaar.every_compat.modules.forge.mcaw;

import com.mcwfurnitures.kikoz.init.BlockEntityInit;
import com.mcwfurnitures.kikoz.init.BlockInit;
import com.mcwfurnitures.kikoz.init.TabInit;
import com.mcwfurnitures.kikoz.objects.Chair;
import com.mcwfurnitures.kikoz.objects.Desk;
import com.mcwfurnitures.kikoz.objects.Table;
import com.mcwfurnitures.kikoz.objects.TableHitbox;
import com.mcwfurnitures.kikoz.objects.TallFurniture;
import com.mcwfurnitures.kikoz.objects.TallFurnitureHinge;
import com.mcwfurnitures.kikoz.objects.WideFurniture;
import com.mcwfurnitures.kikoz.objects.bookshelves.BookCabinet;
import com.mcwfurnitures.kikoz.objects.bookshelves.BookCabinetHinge;
import com.mcwfurnitures.kikoz.objects.bookshelves.BookDrawer;
import com.mcwfurnitures.kikoz.objects.chairs.ClassicChair;
import com.mcwfurnitures.kikoz.objects.chairs.ModernChair;
import com.mcwfurnitures.kikoz.objects.chairs.StripedChair;
import com.mcwfurnitures.kikoz.objects.counters.Counter;
import com.mcwfurnitures.kikoz.objects.counters.CupboardCounter;
import com.mcwfurnitures.kikoz.objects.counters.StorageCounter;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;


public class MacawFurnitureModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> bookshelf;
    public final SimpleEntrySet<WoodType, Block> bookshelfDrawer;
    public final SimpleEntrySet<WoodType, Block> chair;
    public final SimpleEntrySet<WoodType, Block> coffeeTable;
    public final SimpleEntrySet<WoodType, Block> counter;
    public final SimpleEntrySet<WoodType, Block> coveredDesk;
    public final SimpleEntrySet<WoodType, Block> cupboardBookshelf;
    public final SimpleEntrySet<WoodType, Block> cupboardCounter;
    public final SimpleEntrySet<WoodType, Block> desk;
    public final SimpleEntrySet<WoodType, Block> doubleDrawer;
    public final SimpleEntrySet<WoodType, Block> doubleDrawerCounter;
    public final SimpleEntrySet<WoodType, Block> doubleWardrobe;
    public final SimpleEntrySet<WoodType, Block> drawer;
    public final SimpleEntrySet<WoodType, Block> drawerCounter;
    public final SimpleEntrySet<WoodType, Block> endTable;
    public final SimpleEntrySet<WoodType, Block> glassTable;
    public final SimpleEntrySet<WoodType, Block> largeDrawer;
    public final SimpleEntrySet<WoodType, Block> lowerBookshelfDrawer;
    public final SimpleEntrySet<WoodType, Block> lowerTripleDrawer;
    public final SimpleEntrySet<WoodType, Block> modernChair;
    public final SimpleEntrySet<WoodType, Block> modernDesk;
    public final SimpleEntrySet<WoodType, Block> modernWardrobe;
    public final SimpleEntrySet<WoodType, Block> stool;
    public final SimpleEntrySet<WoodType, Block> stripedChair;
    public final SimpleEntrySet<WoodType, Block> strippedBookshelf;
    public final SimpleEntrySet<WoodType, Block> strippedBookshelfDrawer;
    public final SimpleEntrySet<WoodType, Block> strippedChair;
    public final SimpleEntrySet<WoodType, Block> strippedCoffeeTable;
    public final SimpleEntrySet<WoodType, Block> strippedCounter;
    public final SimpleEntrySet<WoodType, Block> strippedCoveredDesk;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_CUPBOARD_BOOKSHELF;
    public final SimpleEntrySet<WoodType, Block> strippedCupboardCounter;
    public final SimpleEntrySet<WoodType, Block> strippedDesk;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_DOUBLE_DRAWER;
    public final SimpleEntrySet<WoodType, Block> strippedDoubleDrawerCounter;
    public final SimpleEntrySet<WoodType, Block> strippedDoubleWardrobe;
    public final SimpleEntrySet<WoodType, Block> strippedDrawer;
    public final SimpleEntrySet<WoodType, Block> strippedDrawerCounter;
    public final SimpleEntrySet<WoodType, Block> strippedEndTable;
    public final SimpleEntrySet<WoodType, Block> strippedGlassTable;
    public final SimpleEntrySet<WoodType, Block> strippedLargeDrawer;
    public final SimpleEntrySet<WoodType, Block> strippedLowerBookshelfDrawer;
    public final SimpleEntrySet<WoodType, Block> strippedLowerTripleDrawer;
    public final SimpleEntrySet<WoodType, Block> strippedModernChair;
    public final SimpleEntrySet<WoodType, Block> strippedModernDesk;
    public final SimpleEntrySet<WoodType, Block> strippedModernWardrobe;
    public final SimpleEntrySet<WoodType, Block> strippedStool;
    public final SimpleEntrySet<WoodType, Block> strippedStripedChair;
    public final SimpleEntrySet<WoodType, Block> strippedTable;
    public final SimpleEntrySet<WoodType, Block> strippedTripleDrawer;
    public final SimpleEntrySet<WoodType, Block> strippedWardrobe;
    public final SimpleEntrySet<WoodType, Block> table;
    public final SimpleEntrySet<WoodType, Block> tripleDrawer;
    public final SimpleEntrySet<WoodType, Block> wardrobe;

    public MacawFurnitureModule(String modId) {
        super(modId, "mcfur");
        var tab = TabInit.FURNITUREITEMGROUP;

        wardrobe = SimpleEntrySet.builder(WoodType.class, "wardrobe",
                        BlockInit.OAK_WARDROBE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TallFurnitureHinge(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("wardrobe"), Registries.BLOCK)
                .setTab(tab)
                .addTile(BlockEntityInit.FURNITURE_STORAGE)
                .defaultRecipe()
                .build();

        this.addEntry(wardrobe);

        modernWardrobe = SimpleEntrySet.builder(WoodType.class, "modern_wardrobe",
                        BlockInit.OAK_MODERN_WARDROBE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TallFurnitureHinge(Utils.copyPropertySafe(w.log))).requiresChildren("stripped_log")
                .addTag(modRes("modern_wardrobe"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTab(tab)
                .addTile(BlockEntityInit.FURNITURE_STORAGE)
                .defaultRecipe()
                .build();

        this.addEntry(modernWardrobe);

        doubleWardrobe = SimpleEntrySet.builder(WoodType.class, "double_wardrobe",
                        BlockInit.OAK_DOUBLE_WARDROBE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TallFurniture(Utils.copyPropertySafe(w.log))).requiresChildren("stripped_log")
                .addTag(modRes("double_wardrobe"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTab(tab)
                .addTile(BlockEntityInit.FURNITURE_STORAGE)
                .defaultRecipe()
                .build();

        this.addEntry(doubleWardrobe);

        bookshelf = SimpleEntrySet.builder(WoodType.class, "bookshelf",
                        BlockInit.OAK_BOOKSHELF, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BookCabinet(Utils.copyPropertySafe(w.log))).requiresChildren("stripped_log")
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("bookshelf"), Registries.BLOCK)
                .setTab(tab)
                .addTile(BlockEntityInit.FURNITURE_STORAGE)
                .defaultRecipe()
                .build();

        this.addEntry(bookshelf);

        cupboardBookshelf = SimpleEntrySet.builder(WoodType.class, "bookshelf_cupboard",
                        BlockInit.OAK_BOOKSHELF_CUPBOARD, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BookCabinetHinge(Utils.copyPropertySafe(w.log))).requiresChildren("stripped_log")
                .addTag(modRes("bookshelf_cupboard"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTab(tab)
                .addTile(BlockEntityInit.FURNITURE_STORAGE)
                .defaultRecipe()
                .build();

        this.addEntry(cupboardBookshelf);

        drawer = SimpleEntrySet.builder(WoodType.class, "drawer",
                        BlockInit.OAK_DRAWER, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WideFurniture(Utils.copyPropertySafe(w.log))).requiresChildren("stripped_log")
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("drawer"), Registries.BLOCK)
                .setTab(tab)
                .addTile(BlockEntityInit.FURNITURE_STORAGE)
                .defaultRecipe()
                .build();

        this.addEntry(drawer);

        doubleDrawer = SimpleEntrySet.builder(WoodType.class, "double_drawer",
                        BlockInit.OAK_DOUBLE_DRAWER, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WideFurniture(Utils.copyPropertySafe(w.log))).requiresChildren("stripped_log")
                .addTag(modRes("double_drawer"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTab(tab)
                .addTile(BlockEntityInit.FURNITURE_STORAGE)
                .defaultRecipe()
                .build();

        this.addEntry(doubleDrawer);

        bookshelfDrawer = SimpleEntrySet.builder(WoodType.class, "bookshelf_drawer",
                        BlockInit.OAK_BOOKSHELF_DRAWER, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BookDrawer(Utils.copyPropertySafe(w.log))).requiresChildren("stripped_log")
                .addTag(modRes("bookshelf_drawer"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTab(tab)
                .addTile(BlockEntityInit.FURNITURE_STORAGE)
                .defaultRecipe()
                .build();

        this.addEntry(bookshelfDrawer);

        lowerBookshelfDrawer = SimpleEntrySet.builder(WoodType.class, "lower_bookshelf_drawer",
                        BlockInit.OAK_LOWER_BOOKSHELF_DRAWER, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BookDrawer(Utils.copyPropertySafe(w.log))).requiresChildren("stripped_log")
                .addTag(modRes("lower_bookshelf_drawer"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTab(tab)
                .addTile(BlockEntityInit.FURNITURE_STORAGE)
                .defaultRecipe()
                .build();

        this.addEntry(lowerBookshelfDrawer);

        largeDrawer = SimpleEntrySet.builder(WoodType.class, "large_drawer",
                        BlockInit.OAK_LARGE_DRAWER, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WideFurniture(Utils.copyPropertySafe(w.log))).requiresChildren("stripped_log")
                .addTag(modRes("large_drawer"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTab(tab)
                .addTile(BlockEntityInit.FURNITURE_STORAGE)
                .defaultRecipe()
                .build();

        this.addEntry(largeDrawer);

        lowerTripleDrawer = SimpleEntrySet.builder(WoodType.class, "lower_triple_drawer",
                        BlockInit.OAK_LOWER_TRIPLE_DRAWER, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WideFurniture(Utils.copyPropertySafe(w.log))).requiresChildren("stripped_log")
                .addTag(modRes("lower_triple_drawer"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTab(tab)
                .addTile(BlockEntityInit.FURNITURE_STORAGE)
                .defaultRecipe()
                .build();

        this.addEntry(lowerTripleDrawer);

        tripleDrawer = SimpleEntrySet.builder(WoodType.class, "triple_drawer",
                        BlockInit.OAK_TRIPLE_DRAWER, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WideFurniture(Utils.copyPropertySafe(w.log))).requiresChildren("stripped_log")
                .addTag(modRes("triple_drawer"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTab(tab)
                .addTile(BlockEntityInit.FURNITURE_STORAGE)
                .defaultRecipe()
                .build();

        this.addEntry(tripleDrawer);

        desk = SimpleEntrySet.builder(WoodType.class, "desk",
                        BlockInit.OAK_DESK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Desk(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("desk"), Registries.BLOCK)
                .setTab(tab)
                .defaultRecipe()
                .build();

        this.addEntry(desk);

        coveredDesk = SimpleEntrySet.builder(WoodType.class, "covered_desk",
                        BlockInit.OAK_COVERED_DESK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Desk(Utils.copyPropertySafe(w.log)))
                .addTag(modRes("covered_desk"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTab(tab)
                .defaultRecipe()
                .build();

        this.addEntry(coveredDesk);

        modernDesk = SimpleEntrySet.builder(WoodType.class, "modern_desk",
                        BlockInit.OAK_MODERN_DESK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Desk(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("modern_desk"), Registries.BLOCK)
                .setTab(tab)
                .defaultRecipe()
                .build();

        this.addEntry(modernDesk);

        table = SimpleEntrySet.builder(WoodType.class, "table",
                        BlockInit.OAK_TABLE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TableHitbox(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("table"), Registries.BLOCK)
                .setTab(tab)
                .defaultRecipe()
                .build();

        this.addEntry(table);

        endTable = SimpleEntrySet.builder(WoodType.class, "end_table",
                        BlockInit.OAK_END_TABLE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TableHitbox(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("end_table"), Registries.BLOCK)
                .setTab(tab)
                .defaultRecipe()
                .build();

        this.addEntry(endTable);

        coffeeTable = SimpleEntrySet.builder(WoodType.class, "coffee_table",
                        BlockInit.OAK_COFFEE_TABLE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Table(Utils.copyPropertySafe(w.log)))
                .addTag(modRes("coffee_table"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTab(tab)
                .defaultRecipe()
                .build();

        this.addEntry(coffeeTable);

        glassTable = SimpleEntrySet.builder(WoodType.class, "glass_table",
                        BlockInit.OAK_GLASS_TABLE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TableHitbox(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("glass_table"), Registries.BLOCK)
                .setRenderType(() -> RenderType::cutout)
                .setTab(tab)
                .defaultRecipe()
                .build();

        this.addEntry(glassTable);

        chair = SimpleEntrySet.builder(WoodType.class, "chair",
                        BlockInit.OAK_CHAIR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ClassicChair(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("chair"), Registries.BLOCK)
                .setTab(tab)
                .defaultRecipe()
                .build();

        this.addEntry(chair);

        modernChair = SimpleEntrySet.builder(WoodType.class, "modern_chair",
                        BlockInit.OAK_MODERN_CHAIR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ModernChair(Utils.copyPropertySafe(w.log)))
                .addTag(modRes("modern_chair"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTab(tab)
                .defaultRecipe()
                .build();

        this.addEntry(modernChair);

        stripedChair = SimpleEntrySet.builder(WoodType.class, "striped_chair",
                        BlockInit.OAK_STRIPED_CHAIR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new StripedChair(Utils.copyPropertySafe(w.log)))
                .addTag(modRes("striped_chair"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setRenderType(() -> RenderType::cutout)
                .setTab(tab)
                .defaultRecipe()
                .build();

        this.addEntry(stripedChair);

        stool = SimpleEntrySet.builder(WoodType.class, "stool_chair",
                        BlockInit.OAK_STOOL_CHAIR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Chair(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("stool_chair"), Registries.BLOCK)
                .setTab(tab)
                .defaultRecipe()
                .build();

        this.addEntry(stool);

        counter = SimpleEntrySet.builder(WoodType.class, "counter",
                        BlockInit.OAK_COUNTER, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Counter(Utils.copyPropertySafe(w.log))).requiresChildren("stripped_log")
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("counter"), Registries.BLOCK)
                .setTab(tab)
                .defaultRecipe()
                .build();

        this.addEntry(counter);

        drawerCounter = SimpleEntrySet.builder(WoodType.class, "drawer_counter",
                        BlockInit.OAK_DRAWER_COUNTER, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new StorageCounter(Blocks.OAK_PLANKS.defaultBlockState(), Utils.copyPropertySafe(w.log))).requiresChildren("stripped_log")
                .addTag(modRes("drawer_counter"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTab(tab)
                .addTile(BlockEntityInit.FURNITURE_STORAGE)
                .defaultRecipe()
                .build();

        this.addEntry(drawerCounter);

        doubleDrawerCounter = SimpleEntrySet.builder(WoodType.class, "double_drawer_counter",
                        BlockInit.OAK_DOUBLE_DRAWER_COUNTER, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new StorageCounter(Blocks.OAK_PLANKS.defaultBlockState(), Utils.copyPropertySafe(w.log))).requiresChildren("stripped_log")
                .addTag(modRes("double_drawer_counter"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTab(tab)
                .addTile(BlockEntityInit.FURNITURE_STORAGE)
                .defaultRecipe()
                .build();

        this.addEntry(doubleDrawerCounter);

        cupboardCounter = SimpleEntrySet.builder(WoodType.class, "cupboard_counter",
                        BlockInit.OAK_CUPBOARD_COUNTER, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CupboardCounter(Utils.copyPropertySafe(w.log))).requiresChildren("stripped_log")
                .addTag(modRes("cupboard_counter"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setRenderType(() -> RenderType::solid)
                .setTab(tab)
                .addTile(BlockEntityInit.FURNITURE_STORAGE)
                .defaultRecipe()
                .build();

        this.addEntry(cupboardCounter);

        strippedWardrobe = SimpleEntrySet.builder(WoodType.class, "wardrobe", "stripped",
                        BlockInit.STRIPPED_OAK_WARDROBE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TallFurnitureHinge(Utils.copyPropertySafe(w.log))).requiresChildren("stripped_log")
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("wardrobe"), Registries.BLOCK)
                .setTab(tab)
                .addTile(BlockEntityInit.FURNITURE_STORAGE)
                .defaultRecipe()
                .build();

        this.addEntry(strippedWardrobe);

        strippedModernWardrobe = SimpleEntrySet.builder(WoodType.class, "modern_wardrobe", "stripped",
                        BlockInit.STRIPPED_OAK_MODERN_WARDROBE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TallFurnitureHinge(Utils.copyPropertySafe(w.log))).requiresChildren("stripped_log")
                .addTag(modRes("modern_wardrobe"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTab(tab)
                .addTile(BlockEntityInit.FURNITURE_STORAGE)
                .defaultRecipe()
                .build();

        this.addEntry(strippedModernWardrobe);

        strippedDoubleWardrobe = SimpleEntrySet.builder(WoodType.class, "double_wardrobe", "stripped",
                        BlockInit.STRIPPED_OAK_DOUBLE_WARDROBE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TallFurniture(Utils.copyPropertySafe(w.log))).requiresChildren("stripped_log")
                .addTag(modRes("double_wardrobe"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTab(tab)
                .addTile(BlockEntityInit.FURNITURE_STORAGE)
                .defaultRecipe()
                .build();

        this.addEntry(strippedDoubleWardrobe);

        strippedBookshelf = SimpleEntrySet.builder(WoodType.class, "bookshelf", "stripped",
                        BlockInit.STRIPPED_OAK_BOOKSHELF, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BookCabinet(Utils.copyPropertySafe(w.log))).requiresChildren("stripped_log")
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("bookshelf"), Registries.BLOCK)
                .setTab(tab)
                .addTile(BlockEntityInit.FURNITURE_STORAGE)
                .defaultRecipe()
                .build();

        this.addEntry(strippedBookshelf);

        STRIPPED_CUPBOARD_BOOKSHELF = SimpleEntrySet.builder(WoodType.class, "bookshelf_cupboard", "stripped",
                        BlockInit.STRIPPED_OAK_BOOKSHELF_CUPBOARD, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BookCabinetHinge(Utils.copyPropertySafe(w.log))).requiresChildren("stripped_log")
                .addTag(modRes("bookshelf_cupboard"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTab(tab)
                .addTile(BlockEntityInit.FURNITURE_STORAGE)
                .defaultRecipe()
                .build();

        this.addEntry(STRIPPED_CUPBOARD_BOOKSHELF);

        strippedDrawer = SimpleEntrySet.builder(WoodType.class, "drawer", "stripped",
                        BlockInit.STRIPPED_OAK_DRAWER, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WideFurniture(Utils.copyPropertySafe(w.log))).requiresChildren("stripped_log")
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("drawer"), Registries.BLOCK)
                .setTab(tab)
                .addTile(BlockEntityInit.FURNITURE_STORAGE)
                .defaultRecipe()
                .build();

        this.addEntry(strippedDrawer);

        STRIPPED_DOUBLE_DRAWER = SimpleEntrySet.builder(WoodType.class, "double_drawer", "stripped",
                        BlockInit.STRIPPED_OAK_DOUBLE_DRAWER, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WideFurniture(Utils.copyPropertySafe(w.log))).requiresChildren("stripped_log")
                .addTag(modRes("double_drawer"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTab(tab)
                .addTile(BlockEntityInit.FURNITURE_STORAGE)
                .defaultRecipe()
                .build();

        this.addEntry(STRIPPED_DOUBLE_DRAWER);

        strippedBookshelfDrawer = SimpleEntrySet.builder(WoodType.class, "bookshelf_drawer", "stripped",
                        BlockInit.STRIPPED_OAK_BOOKSHELF_DRAWER, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BookDrawer(Utils.copyPropertySafe(w.log)))
                .requiresChildren("stripped_log")
                .addTag(modRes("bookshelf_drawer"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTab(tab)
                .addTile(BlockEntityInit.FURNITURE_STORAGE)
                .defaultRecipe()
                .build();

        this.addEntry(strippedBookshelfDrawer);

        strippedLowerBookshelfDrawer = SimpleEntrySet.builder(WoodType.class, "lower_bookshelf_drawer", "stripped",
                        BlockInit.STRIPPED_OAK_LOWER_BOOKSHELF_DRAWER, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BookDrawer(Utils.copyPropertySafe(w.log)))
                .requiresChildren("stripped_log")
                .addTag(modRes("lower_bookshelf_drawer"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTab(tab)
                .addTile(BlockEntityInit.FURNITURE_STORAGE)
                .defaultRecipe()
                .build();

        this.addEntry(strippedLowerBookshelfDrawer);

        strippedLargeDrawer = SimpleEntrySet.builder(WoodType.class, "large_drawer", "stripped",
                        BlockInit.STRIPPED_OAK_LARGE_DRAWER, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WideFurniture(Utils.copyPropertySafe(w.log)))
                .requiresChildren("stripped_log")
                .addTag(modRes("large_drawer"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTab(tab)
                .addTile(BlockEntityInit.FURNITURE_STORAGE)
                .defaultRecipe()
                .build();

        this.addEntry(strippedLargeDrawer);

        strippedLowerTripleDrawer = SimpleEntrySet.builder(WoodType.class, "lower_triple_drawer", "stripped",
                        BlockInit.STRIPPED_OAK_LOWER_TRIPLE_DRAWER, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WideFurniture(Utils.copyPropertySafe(w.log)))
                .requiresChildren("stripped_log")
                .addTag(modRes("lower_triple_drawer"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTab(tab)
                .addTile(BlockEntityInit.FURNITURE_STORAGE)
                .defaultRecipe()
                .build();

        this.addEntry(strippedLowerTripleDrawer);

        strippedTripleDrawer = SimpleEntrySet.builder(WoodType.class, "triple_drawer", "stripped",
                        BlockInit.STRIPPED_OAK_TRIPLE_DRAWER, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WideFurniture(Utils.copyPropertySafe(w.log)))
                .requiresChildren("stripped_log")
                .addTag(modRes("triple_drawer"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTab(tab)
                .addTile(BlockEntityInit.FURNITURE_STORAGE)
                .defaultRecipe()
                .build();

        this.addEntry(strippedTripleDrawer);

        strippedDesk = SimpleEntrySet.builder(WoodType.class, "desk", "stripped",
                        BlockInit.STRIPPED_OAK_DESK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Desk(Utils.copyPropertySafe(w.log)))
                .requiresChildren("stripped_log")
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("desk"), Registries.BLOCK)
                .setTab(tab)
                .defaultRecipe()
                .build();

        this.addEntry(strippedDesk);

        strippedCoveredDesk = SimpleEntrySet.builder(WoodType.class, "covered_desk", "stripped",
                        BlockInit.STRIPPED_OAK_COVERED_DESK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Desk(Utils.copyPropertySafe(w.log)))
                .requiresChildren("stripped_log")
                .addTag(modRes("covered_desk"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTab(tab)
                .defaultRecipe()
                .build();

        this.addEntry(strippedCoveredDesk);

        strippedModernDesk = SimpleEntrySet.builder(WoodType.class, "modern_desk", "stripped",
                        BlockInit.STRIPPED_OAK_MODERN_DESK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Desk(Utils.copyPropertySafe(w.log)))
                .requiresChildren("stripped_log")
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("modern_desk"), Registries.BLOCK)
                .setTab(tab)
                .defaultRecipe()
                .build();

        this.addEntry(strippedModernDesk);

        strippedTable = SimpleEntrySet.builder(WoodType.class, "table", "stripped",
                        BlockInit.STRIPPED_OAK_TABLE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TableHitbox(Utils.copyPropertySafe(w.log)))
                .requiresChildren("stripped_log")
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("table"), Registries.BLOCK)
                .setTab(tab)
                .defaultRecipe()
                .build();

        this.addEntry(strippedTable);

        strippedEndTable = SimpleEntrySet.builder(WoodType.class, "end_table", "stripped",
                        BlockInit.STRIPPED_OAK_END_TABLE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TableHitbox(Utils.copyPropertySafe(w.log)))
                .requiresChildren("stripped_log")
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("end_table"), Registries.BLOCK)
                .setTab(tab)
                .defaultRecipe()
                .build();

        this.addEntry(strippedEndTable);

        strippedCoffeeTable = SimpleEntrySet.builder(WoodType.class, "coffee_table", "stripped",
                        BlockInit.STRIPPED_OAK_COFFEE_TABLE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Table(Utils.copyPropertySafe(w.log)))
                .requiresChildren("stripped_log")
                .addTag(modRes("coffee_table"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTab(tab)
                .defaultRecipe()
                .build();

        this.addEntry(strippedCoffeeTable);

        strippedGlassTable = SimpleEntrySet.builder(WoodType.class, "glass_table", "stripped",
                        BlockInit.STRIPPED_OAK_GLASS_TABLE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TableHitbox(Utils.copyPropertySafe(w.log)))
                .requiresChildren("stripped_log")
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("glass_table"), Registries.BLOCK)
                .setRenderType(() -> RenderType::cutout)
                .setTab(tab)
                .defaultRecipe()
                .build();

        this.addEntry(strippedGlassTable);

        strippedChair = SimpleEntrySet.builder(WoodType.class, "chair", "stripped",
                        BlockInit.STRIPPED_OAK_CHAIR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ClassicChair(Utils.copyPropertySafe(w.log)))
                .requiresChildren("stripped_log")
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("chair"), Registries.BLOCK)
                .setTab(tab)
                .defaultRecipe()
                .build();

        this.addEntry(strippedChair);

        strippedModernChair = SimpleEntrySet.builder(WoodType.class, "modern_chair", "stripped",
                        BlockInit.STRIPPED_OAK_MODERN_CHAIR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ModernChair(Utils.copyPropertySafe(w.log)))
                .addTag(modRes("modern_chair"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .requiresChildren("stripped_log")
                .setTab(tab)
                .defaultRecipe()
                .build();

        this.addEntry(strippedModernChair);

        strippedStripedChair = SimpleEntrySet.builder(WoodType.class, "striped_chair", "stripped",
                        BlockInit.STRIPPED_OAK_STRIPED_CHAIR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new StripedChair(Utils.copyPropertySafe(w.log)))
                .requiresChildren("stripped_log")
                .addTag(modRes("striped_chair"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setRenderType(() -> RenderType::cutout)
                .setTab(tab)
                .defaultRecipe()
                .build();

        this.addEntry(strippedStripedChair);

        strippedStool = SimpleEntrySet.builder(WoodType.class, "stool_chair", "stripped",
                        BlockInit.STRIPPED_OAK_STOOL_CHAIR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Chair(Utils.copyPropertySafe(w.log)))
                .requiresChildren("stripped_log")
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("stool_chair"), Registries.BLOCK)
                .setTab(tab)
                .defaultRecipe()
                .build();

        this.addEntry(strippedStool);

        strippedCounter = SimpleEntrySet.builder(WoodType.class, "counter", "stripped",
                        BlockInit.STRIPPED_OAK_COUNTER, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Counter(Utils.copyPropertySafe(w.log)))
                .requiresChildren("stripped_log")
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("counter"), Registries.BLOCK)
                .setTab(tab)
                .defaultRecipe()
                .build();

        this.addEntry(strippedCounter);

        strippedDrawerCounter = SimpleEntrySet.builder(WoodType.class, "drawer_counter", "stripped",
                        BlockInit.STRIPPED_OAK_DRAWER_COUNTER, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new StorageCounter(Blocks.OAK_PLANKS.defaultBlockState(), Utils.copyPropertySafe(w.log)))
                .requiresChildren("stripped_log")
                .addTag(modRes("drawer_counter"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTab(tab)
                .addTile(BlockEntityInit.FURNITURE_STORAGE)
                .defaultRecipe()
                .build();

        this.addEntry(strippedDrawerCounter);

        strippedDoubleDrawerCounter = SimpleEntrySet.builder(WoodType.class, "double_drawer_counter", "stripped",
                        BlockInit.STRIPPED_OAK_DOUBLE_DRAWER_COUNTER, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new StorageCounter(Blocks.OAK_PLANKS.defaultBlockState(), Utils.copyPropertySafe(w.log)))
                .requiresChildren("stripped_log")
                .addTag(modRes("double_drawer_counter"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTab(tab)
                .addTile(BlockEntityInit.FURNITURE_STORAGE)
                .defaultRecipe()
                .build();

        this.addEntry(strippedDoubleDrawerCounter);

        strippedCupboardCounter = SimpleEntrySet.builder(WoodType.class, "cupboard_counter", "stripped",
                        BlockInit.STRIPPED_OAK_CUPBOARD_COUNTER, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CupboardCounter(Utils.copyPropertySafe(w.log)))
                .requiresChildren("stripped_log")
                .addTag(modRes("cupboard_counter"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setRenderType(() -> RenderType::solid)
                .setTab(tab)
                .addTile(BlockEntityInit.FURNITURE_STORAGE)
                .defaultRecipe()
                .build();

        this.addEntry(strippedCupboardCounter);
    }
}
