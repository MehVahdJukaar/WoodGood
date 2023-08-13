package net.mehvahdjukaar.every_compat.modules.mcaw;

import com.mcwfurnitures.kikoz.MacawsFurnitures;
import com.mcwfurnitures.kikoz.init.BlockEntityInit;
import com.mcwfurnitures.kikoz.init.BlockInit;
import com.mcwfurnitures.kikoz.objects.Chair;
import com.mcwfurnitures.kikoz.objects.Desk;
import com.mcwfurnitures.kikoz.objects.Table;
import com.mcwfurnitures.kikoz.objects.TableHitbox;
import com.mcwfurnitures.kikoz.objects.TallFurniture;
import com.mcwfurnitures.kikoz.objects.WideFurniture;
import com.mcwfurnitures.kikoz.objects.bookshelves.BookCabinet;
import com.mcwfurnitures.kikoz.objects.bookshelves.BookDrawer;
import com.mcwfurnitures.kikoz.objects.chairs.ClassicChair;
import com.mcwfurnitures.kikoz.objects.chairs.ModernChair;
import com.mcwfurnitures.kikoz.objects.chairs.StripedChair;
import com.mcwfurnitures.kikoz.objects.counters.Counter;
import com.mcwfurnitures.kikoz.objects.counters.StorageCounter;
import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;


public class MacawFurnitureModule extends SimpleModule {

    // BOOKSHELF
    public final SimpleEntrySet<WoodType, Block> bookshelf;
    public final SimpleEntrySet<WoodType, Block> cupboardBookshelf;
    public final SimpleEntrySet<WoodType, Block> strippedBookshelf;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_CUPBOARD_BOOKSHELF;

    // CHAIR
    public final SimpleEntrySet<WoodType, Block> chair;
    public final SimpleEntrySet<WoodType, Block> modernChair;
    public final SimpleEntrySet<WoodType, Block> stripedChair;
    public final SimpleEntrySet<WoodType, Block> stool;

    public final SimpleEntrySet<WoodType, Block> strippedChair;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_MODERN_CHAIR;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_STRIPED_CHAIR;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_STOOL;

    // COUNTER
    public final SimpleEntrySet<WoodType, Block> counter;
    public final SimpleEntrySet<WoodType, Block> drawerCounter;
    public final SimpleEntrySet<WoodType, Block> cupboardCounter;
    public final SimpleEntrySet<WoodType, Block> doubleDrawerCounter;

    public final SimpleEntrySet<WoodType, Block> strippedCounter;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_DRAWER_COUNTER;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_CUPBOARD_COUNTER;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_DOUBLE_DRAWER_COUNTER;

    // DRAWER
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

    // DESK
    public final SimpleEntrySet<WoodType, Block> desk;
    public final SimpleEntrySet<WoodType, Block> coveredDesk;
    public final SimpleEntrySet<WoodType, Block> modernDesk;

    public final SimpleEntrySet<WoodType, Block> STRIPPED_DESK;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_MODERN_DESK;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_COVERED_DESK;

    // TABLE
    public final SimpleEntrySet<WoodType, Block> TABLE;
    public final SimpleEntrySet<WoodType, Block> glassTable;
    public final SimpleEntrySet<WoodType, Block> endTable;
    public final SimpleEntrySet<WoodType, Block> coffeeTable;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_END_TABLE;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_GLASS_TABLE;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_TABLE;
    public final SimpleEntrySet<WoodType, Block> strippedCoffeeTable;

    // WARDROBE
    public final SimpleEntrySet<WoodType, Block> WARDROBE;
    public final SimpleEntrySet<WoodType, Block> doubleWardrobe;
    public final SimpleEntrySet<WoodType, Block> modernWardrobe;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_DOUBLE_WARDROBE;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_MODERN_WARDROBE;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_WARDROBE;

    public MacawFurnitureModule(String modId) {
        super(modId, "mcfur");
        CreativeModeTab tab = MacawsFurnitures.FURNITUREITEMGROUP;

        // BOOKSHELF
        bookshelf = SimpleEntrySet.builder(WoodType.class, "bookshelf",
                        BlockInit.OAK_BOOKSHELF, () -> WoodType.OAK_WOOD_TYPE,
                        ifHasChild(w -> new BookCabinet(WoodGood.copySafe(w.log)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("bookshelf"), Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(bookshelf);

        cupboardBookshelf = SimpleEntrySet.builder(WoodType.class, "bookshelf_cupboard",
                        BlockInit.OAK_BOOKSHELF_CUPBOARD, () -> WoodType.OAK_WOOD_TYPE,
                        ifHasChild(w -> new BookCabinet(WoodGood.copySafe(w.log)), "stripped_log"))
                .addTag(modRes("bookshelf_cupboard"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(cupboardBookshelf);

        strippedBookshelf = SimpleEntrySet.builder(WoodType.class, "bookshelf", "stripped",
                        BlockInit.STRIPPED_OAK_BOOKSHELF, () -> WoodType.OAK_WOOD_TYPE,
                        ifHasChild(w -> new BookCabinet(WoodGood.copySafe(w.log)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("bookshelf"), Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(strippedBookshelf);

        STRIPPED_CUPBOARD_BOOKSHELF = SimpleEntrySet.builder(WoodType.class, "bookshelf_cupboard","stripped",
                        BlockInit.STRIPPED_OAK_BOOKSHELF_CUPBOARD, () -> WoodType.OAK_WOOD_TYPE,
                        ifHasChild(w -> new BookCabinet(WoodGood.copySafe(w.log)),"stripped_log"))
                .addTag(modRes("bookshelf_cupboard"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(STRIPPED_CUPBOARD_BOOKSHELF);


        chair = SimpleEntrySet.builder(WoodType.class, "chair",
                        BlockInit.OAK_CHAIR, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new ClassicChair(WoodGood.copySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("chair"), Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(chair);

        // CHAIR
        modernChair = SimpleEntrySet.builder(WoodType.class, "modern_chair",
                        BlockInit.OAK_MODERN_CHAIR, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new ModernChair(WoodGood.copySafe(w.log)))
                .addTag(modRes("modern_chair"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(modernChair);


        stripedChair = SimpleEntrySet.builder(WoodType.class, "striped_chair",
                        BlockInit.OAK_STRIPED_CHAIR, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new StripedChair(WoodGood.copySafe(w.log)))
                .addTag(modRes("striped_chair"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(stripedChair);


        stool = SimpleEntrySet.builder(WoodType.class, "stool_chair",
                        BlockInit.OAK_STOOL_CHAIR, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new Chair(WoodGood.copySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("stool_chair"), Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(stool);


        STRIPPED_MODERN_CHAIR = SimpleEntrySet.builder(WoodType.class, "modern_chair", "stripped",
                        BlockInit.STRIPPED_OAK_MODERN_CHAIR, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new ModernChair(WoodGood.copySafe(w.log)))
                .addTag(modRes("modern_chair"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(STRIPPED_MODERN_CHAIR);


        strippedChair = SimpleEntrySet.builder(WoodType.class, "chair", "stripped",
                        BlockInit.STRIPPED_OAK_CHAIR, () -> WoodType.OAK_WOOD_TYPE,
                        ifHasChild(w -> new ClassicChair(WoodGood.copySafe(w.log)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("chair"), Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(strippedChair);

        STRIPPED_STRIPED_CHAIR = SimpleEntrySet.builder(WoodType.class, "striped_chair", "stripped",
                        BlockInit.STRIPPED_OAK_STRIPED_CHAIR, () -> WoodType.OAK_WOOD_TYPE,
                        ifHasChild(w -> new StripedChair(WoodGood.copySafe(w.log)), "stripped_log"))
                .addTag(modRes("striped_chair"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(STRIPPED_STRIPED_CHAIR);

        STRIPPED_STOOL = SimpleEntrySet.builder(WoodType.class, "stool_chair", "stripped",
                        BlockInit.STRIPPED_OAK_STOOL_CHAIR, () -> WoodType.OAK_WOOD_TYPE,
                        ifHasChild(w -> new Chair(WoodGood.copySafe(w.log)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("stool_chair"), Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(STRIPPED_STOOL);


        // COUNTER
        counter = SimpleEntrySet.builder(WoodType.class, "counter",
                        BlockInit.OAK_COUNTER, () -> WoodType.OAK_WOOD_TYPE,
                        ifHasChild(w -> new Counter(Blocks.OAK_PLANKS.defaultBlockState(), WoodGood.copySafe(w.log)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("counter"), Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(counter);

        drawerCounter = SimpleEntrySet.builder(WoodType.class, "drawer_counter",
                        BlockInit.OAK_DRAWER_COUNTER, () -> WoodType.OAK_WOOD_TYPE,
                        ifHasChild(w -> new StorageCounter(Blocks.OAK_PLANKS.defaultBlockState(), WoodGood.copySafe(w.log)), "stripped_log"))
                .addTag(modRes("drawer_counter"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(drawerCounter);

        doubleDrawerCounter = SimpleEntrySet.builder(WoodType.class, "double_drawer_counter",
                        BlockInit.OAK_DOUBLE_DRAWER_COUNTER, () -> WoodType.OAK_WOOD_TYPE,
                        ifHasChild(w -> new StorageCounter(Blocks.OAK_PLANKS.defaultBlockState(), WoodGood.copySafe(w.log)), "stripped_log"))
                .addTag(modRes("double_drawer_counter"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(doubleDrawerCounter);

        cupboardCounter = SimpleEntrySet.builder(WoodType.class, "cupboard_counter",
                        BlockInit.OAK_CUPBOARD_COUNTER, () -> WoodType.OAK_WOOD_TYPE,
                        ifHasChild(w -> new StorageCounter(Blocks.OAK_PLANKS.defaultBlockState(), WoodGood.copySafe(w.log)), "stripped_log"))
                .addTag(modRes("cupboard_counter"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::solid)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(cupboardCounter);

        strippedCounter = SimpleEntrySet.builder(WoodType.class, "counter", "stripped",
                        BlockInit.STRIPPED_OAK_COUNTER, () -> WoodType.OAK_WOOD_TYPE,
                        ifHasChild(w -> new Counter(Blocks.OAK_PLANKS.defaultBlockState(), WoodGood.copySafe(w.log)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("counter"), Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(strippedCounter);

        STRIPPED_DRAWER_COUNTER = SimpleEntrySet.builder(WoodType.class, "drawer_counter", "stripped",
                        BlockInit.STRIPPED_OAK_DRAWER_COUNTER, () -> WoodType.OAK_WOOD_TYPE,
                        ifHasChild(w -> new StorageCounter(Blocks.OAK_PLANKS.defaultBlockState(), WoodGood.copySafe(w.log)), "stripped_log"))
                .addTag(modRes("drawer_counter"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(STRIPPED_DRAWER_COUNTER);

        STRIPPED_DOUBLE_DRAWER_COUNTER = SimpleEntrySet.builder(WoodType.class, "double_drawer_counter", "stripped",
                        BlockInit.STRIPPED_OAK_DOUBLE_DRAWER_COUNTER, () -> WoodType.OAK_WOOD_TYPE,
                        ifHasChild(w -> new StorageCounter(Blocks.OAK_PLANKS.defaultBlockState(), WoodGood.copySafe(w.log)), "stripped_log"))
                .addTag(modRes("double_drawer_counter"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(STRIPPED_DOUBLE_DRAWER_COUNTER);

        STRIPPED_CUPBOARD_COUNTER = SimpleEntrySet.builder(WoodType.class, "cupboard_counter", "stripped",
                        BlockInit.STRIPPED_OAK_CUPBOARD_COUNTER, () -> WoodType.OAK_WOOD_TYPE,
                        ifHasChild(w -> new StorageCounter(Blocks.OAK_PLANKS.defaultBlockState(), WoodGood.copySafe(w.log)), "stripped_log"))
                .addTag(modRes("cupboard_counter"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::solid)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(STRIPPED_CUPBOARD_COUNTER);

        // DRAWER
        drawer = SimpleEntrySet.builder(WoodType.class, "drawer",
                        BlockInit.OAK_DRAWER, () -> WoodType.OAK_WOOD_TYPE,
                        ifHasChild(w -> new WideFurniture(WoodGood.copySafe(w.log)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("drawer"), Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(drawer);

        doubleDrawer = SimpleEntrySet.builder(WoodType.class, "double_drawer",
                        BlockInit.OAK_DOUBLE_DRAWER, () -> WoodType.OAK_WOOD_TYPE,
                        ifHasChild(w -> new WideFurniture(WoodGood.copySafe(w.log)), "stripped_log"))
                .addTag(modRes("double_drawer"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(doubleDrawer);

        bookshelfDrawer = SimpleEntrySet.builder(WoodType.class, "bookshelf_drawer",
                        BlockInit.OAK_BOOKSHELF_DRAWER, () -> WoodType.OAK_WOOD_TYPE,
                        ifHasChild(w -> new BookDrawer(WoodGood.copySafe(w.log)), "stripped_log"))
                .addTag(modRes("bookshelf_drawer"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(bookshelfDrawer);

        lowerBookshelfDrawer = SimpleEntrySet.builder(WoodType.class, "lower_bookshelf_drawer",
                        BlockInit.OAK_LOWER_BOOKSHELF_DRAWER, () -> WoodType.OAK_WOOD_TYPE,
                        ifHasChild(w -> new BookDrawer(WoodGood.copySafe(w.log)), "stripped_log"))
                .addTag(modRes("lower_bookshelf_drawer"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(lowerBookshelfDrawer);

        largeDrawer = SimpleEntrySet.builder(WoodType.class, "large_drawer",
                        BlockInit.OAK_LARGE_DRAWER, () -> WoodType.OAK_WOOD_TYPE,
                        ifHasChild(w -> new WideFurniture(WoodGood.copySafe(w.log)), "stripped_log"))
                .addTag(modRes("large_drawer"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(largeDrawer);

        lowerTripleDrawer = SimpleEntrySet.builder(WoodType.class, "lower_triple_drawer",
                        BlockInit.OAK_LOWER_TRIPLE_DRAWER, () -> WoodType.OAK_WOOD_TYPE,
                        ifHasChild(w -> new WideFurniture(WoodGood.copySafe(w.log)), "stripped_log"))
                .addTag(modRes("lower_triple_drawer"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(lowerTripleDrawer);

        TRIPLE_DRAWER = SimpleEntrySet.builder(WoodType.class, "triple_drawer",
                        BlockInit.OAK_TRIPLE_DRAWER, () -> WoodType.OAK_WOOD_TYPE,
                        ifHasChild(w -> new WideFurniture(WoodGood.copySafe(w.log)), "stripped_log"))
                .addTag(modRes("triple_drawer"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(TRIPLE_DRAWER);

        STRIPPED_DRAWER = SimpleEntrySet.builder(WoodType.class, "drawer", "stripped",
                        BlockInit.STRIPPED_OAK_DRAWER, () -> WoodType.OAK_WOOD_TYPE,
                        ifHasChild(w -> new WideFurniture(WoodGood.copySafe(w.log)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("drawer"), Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(STRIPPED_DRAWER);

        STRIPPED_DOUBLE_DRAWER = SimpleEntrySet.builder(WoodType.class, "double_drawer", "stripped",
                        BlockInit.STRIPPED_OAK_DOUBLE_DRAWER, () -> WoodType.OAK_WOOD_TYPE,
                        ifHasChild(w -> new WideFurniture(WoodGood.copySafe(w.log)), "stripped_log"))
                .addTag(modRes("double_drawer"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(STRIPPED_DOUBLE_DRAWER);

        strippedBookshelfDrawer = SimpleEntrySet.builder(WoodType.class, "bookshelf_drawer", "stripped",
                        BlockInit.STRIPPED_OAK_BOOKSHELF_DRAWER, () -> WoodType.OAK_WOOD_TYPE,
                        ifHasChild(w -> new BookDrawer(WoodGood.copySafe(w.log)), "stripped_log"))
                .addTag(modRes("bookshelf_drawer"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(strippedBookshelfDrawer);

        STRIPPED_LOWER_BOOKSHELF_DRAWER = SimpleEntrySet.builder(WoodType.class, "lower_bookshelf_drawer", "stripped",
                        BlockInit.STRIPPED_OAK_LOWER_BOOKSHELF_DRAWER, () -> WoodType.OAK_WOOD_TYPE,
                        ifHasChild(w -> new BookDrawer(WoodGood.copySafe(w.log)), "stripped_log"))
                .addTag(modRes("lower_bookshelf_drawer"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(STRIPPED_LOWER_BOOKSHELF_DRAWER);

        STRIPPED_LARGE_DRAWER = SimpleEntrySet.builder(WoodType.class, "large_drawer", "stripped",
                        BlockInit.STRIPPED_OAK_LARGE_DRAWER, () -> WoodType.OAK_WOOD_TYPE,
                        ifHasChild(w -> new WideFurniture(WoodGood.copySafe(w.log)), "stripped_log"))
                .addTag(modRes("large_drawer"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(STRIPPED_LARGE_DRAWER);

        STRIPPED_LOWER_TRIPLE_DRAWER = SimpleEntrySet.builder(WoodType.class, "lower_triple_drawer", "stripped",
                        BlockInit.STRIPPED_OAK_LOWER_TRIPLE_DRAWER, () -> WoodType.OAK_WOOD_TYPE,
                        ifHasChild(w -> new WideFurniture(WoodGood.copySafe(w.log)), "stripped_log"))
                .addTag(modRes("lower_triple_drawer"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(STRIPPED_LOWER_TRIPLE_DRAWER);

        STRIPPED_TRIPLE_DRAWER = SimpleEntrySet.builder(WoodType.class, "triple_drawer", "stripped",
                        BlockInit.STRIPPED_OAK_TRIPLE_DRAWER, () -> WoodType.OAK_WOOD_TYPE,
                        ifHasChild(w -> new WideFurniture(WoodGood.copySafe(w.log)), "stripped_log"))
                .addTag(modRes("triple_drawer"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(STRIPPED_TRIPLE_DRAWER);


        // DESK
        desk = SimpleEntrySet.builder(WoodType.class, "desk",
                        BlockInit.OAK_DESK, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new Desk(WoodGood.copySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("desk"), Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(desk);

        coveredDesk = SimpleEntrySet.builder(WoodType.class, "covered_desk",
                        BlockInit.OAK_COVERED_DESK, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new Desk(WoodGood.copySafe(w.log)))
                .addTag(modRes("covered_desk"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(coveredDesk);

        modernDesk = SimpleEntrySet.builder(WoodType.class, "modern_desk",
                        BlockInit.OAK_MODERN_DESK, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new Desk(WoodGood.copySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("modern_desk"), Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(modernDesk);

        STRIPPED_DESK = SimpleEntrySet.builder(WoodType.class, "desk", "stripped",
                        BlockInit.STRIPPED_OAK_DESK, () -> WoodType.OAK_WOOD_TYPE,
                        ifHasChild(w -> new Desk(WoodGood.copySafe(w.log)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("desk"), Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(STRIPPED_DESK);

        STRIPPED_COVERED_DESK = SimpleEntrySet.builder(WoodType.class, "covered_desk", "stripped",
                        BlockInit.STRIPPED_OAK_COVERED_DESK, () -> WoodType.OAK_WOOD_TYPE,
                        ifHasChild(w -> new Desk(WoodGood.copySafe(w.log)), "stripped_log"))
                .addTag(modRes("covered_desk"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(STRIPPED_COVERED_DESK);

        STRIPPED_MODERN_DESK = SimpleEntrySet.builder(WoodType.class, "modern_desk", "stripped",
                        BlockInit.STRIPPED_OAK_MODERN_DESK, () -> WoodType.OAK_WOOD_TYPE,
                        ifHasChild(w -> new Desk(WoodGood.copySafe(w.log)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("modern_desk"), Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(STRIPPED_MODERN_DESK);


        // TABLE
        TABLE = SimpleEntrySet.builder(WoodType.class, "table",
                        BlockInit.OAK_TABLE, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new TableHitbox(WoodGood.copySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("table"), Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(TABLE);

        endTable = SimpleEntrySet.builder(WoodType.class, "end_table",
                        BlockInit.OAK_END_TABLE, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new TableHitbox(WoodGood.copySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("end_table"), Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(endTable);

        coffeeTable = SimpleEntrySet.builder(WoodType.class, "coffee_table",
                        BlockInit.OAK_COFFEE_TABLE, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new Table(WoodGood.copySafe(w.log)))
                .addTag(modRes("coffee_table"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(coffeeTable);

        glassTable = SimpleEntrySet.builder(WoodType.class, "glass_table",
                        BlockInit.OAK_GLASS_TABLE, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new TableHitbox(WoodGood.copySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("glass_table"), Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(glassTable);

        STRIPPED_TABLE = SimpleEntrySet.builder(WoodType.class, "table", "stripped",
                        BlockInit.STRIPPED_OAK_TABLE, () -> WoodType.OAK_WOOD_TYPE,
                        ifHasChild(w -> new TableHitbox(WoodGood.copySafe(w.log)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("table"), Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(STRIPPED_TABLE);

        STRIPPED_END_TABLE = SimpleEntrySet.builder(WoodType.class, "end_table", "stripped",
                        BlockInit.STRIPPED_OAK_END_TABLE, () -> WoodType.OAK_WOOD_TYPE,
                        ifHasChild(w -> new TableHitbox(WoodGood.copySafe(w.log)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("end_table"), Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(STRIPPED_END_TABLE);

        strippedCoffeeTable = SimpleEntrySet.builder(WoodType.class, "coffee_table", "stripped",
                        BlockInit.STRIPPED_OAK_COFFEE_TABLE, () -> WoodType.OAK_WOOD_TYPE,
                        ifHasChild(w -> new Table(WoodGood.copySafe(w.log)), "stripped_log"))
                .addTag(modRes("coffee_table"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(strippedCoffeeTable);

        STRIPPED_GLASS_TABLE = SimpleEntrySet.builder(WoodType.class, "glass_table", "stripped",
                        BlockInit.STRIPPED_OAK_GLASS_TABLE, () -> WoodType.OAK_WOOD_TYPE,
                        ifHasChild(w -> new TableHitbox(WoodGood.copySafe(w.log)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("glass_table"), Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(STRIPPED_GLASS_TABLE);


        // WARDROBE
        WARDROBE = SimpleEntrySet.builder(WoodType.class, "wardrobe",
                        BlockInit.OAK_WARDROBE, () -> WoodType.OAK_WOOD_TYPE,
                        ifHasChild(w -> new TallFurniture(WoodGood.copySafe(w.log)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("wardrobe"), Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(WARDROBE);

        modernWardrobe = SimpleEntrySet.builder(WoodType.class, "modern_wardrobe",
                        BlockInit.OAK_MODERN_WARDROBE, () -> WoodType.OAK_WOOD_TYPE,
                        ifHasChild(w -> new TallFurniture(WoodGood.copySafe(w.log)), "stripped_log"))
                .addTag(modRes("modern_wardrobe"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(modernWardrobe);

        doubleWardrobe = SimpleEntrySet.builder(WoodType.class, "double_wardrobe",
                        BlockInit.OAK_DOUBLE_WARDROBE, () -> WoodType.OAK_WOOD_TYPE,
                        ifHasChild(w -> new TallFurniture(WoodGood.copySafe(w.log)), "stripped_log"))
                .addTag(modRes("double_wardrobe"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(doubleWardrobe);

        STRIPPED_WARDROBE = SimpleEntrySet.builder(WoodType.class, "wardrobe", "stripped",
                        BlockInit.STRIPPED_OAK_WARDROBE, () -> WoodType.OAK_WOOD_TYPE,
                        ifHasChild(w -> new TallFurniture(WoodGood.copySafe(w.log)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("wardrobe"), Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(STRIPPED_WARDROBE);

        STRIPPED_MODERN_WARDROBE = SimpleEntrySet.builder(WoodType.class, "modern_wardrobe", "stripped",
                        BlockInit.STRIPPED_OAK_MODERN_WARDROBE, () -> WoodType.OAK_WOOD_TYPE,
                        ifHasChild(w -> new TallFurniture(WoodGood.copySafe(w.log)), "stripped_log"))
                .addTag(modRes("modern_wardrobe"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(STRIPPED_MODERN_WARDROBE);

        STRIPPED_DOUBLE_WARDROBE = SimpleEntrySet.builder(WoodType.class, "double_wardrobe", "stripped",
                        BlockInit.STRIPPED_OAK_DOUBLE_WARDROBE, () -> WoodType.OAK_WOOD_TYPE,
                        ifHasChild(w -> new TallFurniture(WoodGood.copySafe(w.log)), "stripped_log"))
                .addTag(modRes("double_wardrobe"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(STRIPPED_DOUBLE_WARDROBE);

    }
}
