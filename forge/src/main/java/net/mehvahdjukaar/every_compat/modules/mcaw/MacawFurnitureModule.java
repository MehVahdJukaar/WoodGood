package net.mehvahdjukaar.every_compat.modules.mcaw;

import com.mcwfurnitures.kikoz.MacawsFurnitures;
import com.mcwfurnitures.kikoz.init.BlockInit;
import com.mcwfurnitures.kikoz.objects.Desk;
import com.mcwfurnitures.kikoz.objects.TallFurniture;
import com.mcwfurnitures.kikoz.objects.WideFurniture;
import com.mcwfurnitures.kikoz.objects.bookshelves.BookCabinet;
import com.mcwfurnitures.kikoz.objects.bookshelves.BookDrawer;
import com.mcwfurnitures.kikoz.objects.counters.StorageCounter;
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
import net.minecraft.world.level.block.Blocks;


public class MacawFurnitureModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> BOOKSHELF;
    public final SimpleEntrySet<WoodType, Block> BOOKSHELF_DRAWER;
    public final SimpleEntrySet<WoodType, Block> COVERED_DESK;
    public final SimpleEntrySet<WoodType, Block> CUPBOARD_BOOKSHELF;
    public final SimpleEntrySet<WoodType, Block> CUPBOARD_COUNTER;
    public final SimpleEntrySet<WoodType, Block> DESK;
    public final SimpleEntrySet<WoodType, Block> DOUBLE_DRAWER;
    public final SimpleEntrySet<WoodType, Block> DOUBLE_WARDROBE;
    public final SimpleEntrySet<WoodType, Block> DRAWER;
    public final SimpleEntrySet<WoodType, Block> END_TABLE;
    public final SimpleEntrySet<WoodType, Block> LARGE_DRAWER;
    public final SimpleEntrySet<WoodType, Block> LOWER_BOOKSHELF_DRAWER;
    public final SimpleEntrySet<WoodType, Block> LOWER_TRIPLE_DRAWER;
    public final SimpleEntrySet<WoodType, Block> MODERN_DESK;
    public final SimpleEntrySet<WoodType, Block> MODERN_WARDROBE;
    public final SimpleEntrySet<WoodType, Block> TABLE;
    public final SimpleEntrySet<WoodType, Block> TRIPLE_DRAWER;
    public final SimpleEntrySet<WoodType, Block> WARDROBE;

    public MacawFurnitureModule(String modId) {
        super(modId, "mcfur");
        CreativeModeTab tab = MacawsFurnitures.FURNITUREITEMGROUP;


        WARDROBE = SimpleEntrySet.builder(WoodType.class, "wardrobe",
                        BlockInit.OAK_WARDROBE, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new TallFurniture(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("wardrobe"), Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(WARDROBE);

        MODERN_WARDROBE = SimpleEntrySet.builder(WoodType.class, "modern_wardrobe",
                        BlockInit.OAK_MODERN_WARDROBE, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new TallFurniture(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(modRes("modern_wardrobe"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(MODERN_WARDROBE);

        DOUBLE_WARDROBE = SimpleEntrySet.builder(WoodType.class, "double_wardrobe",
                        BlockInit.OAK_DOUBLE_WARDROBE, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new TallFurniture(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(modRes("double_wardrobe"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(DOUBLE_WARDROBE);

        BOOKSHELF = SimpleEntrySet.builder(WoodType.class, "bookshelf",
                        BlockInit.OAK_BOOKSHELF, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new BookCabinet(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("bookshelf"), Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(BOOKSHELF);

        CUPBOARD_BOOKSHELF = SimpleEntrySet.builder(WoodType.class, "bookshelf_cupboard",
                        BlockInit.OAK_BOOKSHELF_CUPBOARD, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new BookCabinet(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(modRes("bookshelf_cupboard"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(CUPBOARD_BOOKSHELF);

        DRAWER = SimpleEntrySet.builder(WoodType.class, "drawer",
                        BlockInit.OAK_DRAWER, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new WideFurniture(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("drawer"), Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(DRAWER);

        DOUBLE_DRAWER = SimpleEntrySet.builder(WoodType.class, "double_drawer",
                        BlockInit.OAK_DOUBLE_DRAWER, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new WideFurniture(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(modRes("double_drawer"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(DOUBLE_DRAWER);

        BOOKSHELF_DRAWER = SimpleEntrySet.builder(WoodType.class, "bookshelf_drawer",
                        BlockInit.OAK_BOOKSHELF_DRAWER, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new BookDrawer(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(modRes("bookshelf_drawer"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(BOOKSHELF_DRAWER);

        LOWER_BOOKSHELF_DRAWER = SimpleEntrySet.builder(WoodType.class, "lower_bookshelf_drawer",
                        BlockInit.OAK_LOWER_BOOKSHELF_DRAWER, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new BookDrawer(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(modRes("lower_bookshelf_drawer"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(LOWER_BOOKSHELF_DRAWER);

        LARGE_DRAWER = SimpleEntrySet.builder(WoodType.class, "large_drawer",
                        BlockInit.OAK_LARGE_DRAWER, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new WideFurniture(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(modRes("large_drawer"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(LARGE_DRAWER);

        LOWER_TRIPLE_DRAWER = SimpleEntrySet.builder(WoodType.class, "lower_triple_drawer",
                        BlockInit.OAK_LOWER_TRIPLE_DRAWER, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new WideFurniture(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(modRes("lower_triple_drawer"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(LOWER_TRIPLE_DRAWER);

        TRIPLE_DRAWER = SimpleEntrySet.builder(WoodType.class, "triple_drawer",
                        BlockInit.OAK_TRIPLE_DRAWER, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new WideFurniture(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(modRes("triple_drawer"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(TRIPLE_DRAWER);

        DESK = SimpleEntrySet.builder(WoodType.class, "desk",
                        BlockInit.OAK_DESK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Desk(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("desk"), Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(DESK);

        COVERED_DESK = SimpleEntrySet.builder(WoodType.class, "covered_desk",
                        BlockInit.OAK_COVERED_DESK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Desk(Utils.copyPropertySafe(w.log)))
                .addTag(modRes("covered_desk"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(COVERED_DESK);

        MODERN_DESK = SimpleEntrySet.builder(WoodType.class, "modern_desk",
                        BlockInit.OAK_MODERN_DESK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Desk(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("modern_desk"), Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(MODERN_DESK);

        TABLE = SimpleEntrySet.builder(WoodType.class, "table",
                        BlockInit.OAK_TABLE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TableHitbox(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("table"), Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(TABLE);

        END_TABLE = SimpleEntrySet.builder(WoodType.class, "end_table",
                        BlockInit.OAK_END_TABLE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TableHitbox(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("end_table"), Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(END_TABLE);

        CUPBOARD_COUNTER = SimpleEntrySet.builder(WoodType.class, "cupboard_counter",
                        BlockInit.OAK_CUPBOARD_COUNTER, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new StorageCounter(Blocks.OAK_PLANKS.defaultBlockState(), Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(modRes("cupboard_counter"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::solid)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(CUPBOARD_COUNTER);
    }
}
