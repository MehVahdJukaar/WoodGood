package net.mehvahdjukaar.every_compat.modules.fabric.storage_delight;

import com.axperty.storagedelight.block.*;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

//SUPPORT: v25.06.26+
//!! REASON: The classes via FORGE or FABRIC for these blocks are not SAME
public class StorageDelightModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> drawer;
    public final SimpleEntrySet<WoodType, Block> drawer_with_door;
    public final SimpleEntrySet<WoodType, Block> drawer_with_books;
    public final SimpleEntrySet<WoodType, Block> small_drawers;
    public final SimpleEntrySet<WoodType, Block> bookshelf_with_door;
    public final SimpleEntrySet<WoodType, Block> glass_cabinet;
    public final SimpleEntrySet<WoodType, Block> cabinet_with_glass_doors;
    public final SimpleEntrySet<WoodType, Block> single_door_cabinet;

    public StorageDelightModule(String modId) {
        super(modId, "sdl");
        ResourceLocation tab = modRes("title");

        drawer = SimpleEntrySet.builder(WoodType.class, "drawer",
                        getModBlock("oak_drawer"), () -> VanillaWoodTypes.OAK,
                        w -> new DrawerBlock()
                )
                .requiresChildren("slab", "trapdoor") //REASON: recipes
                .addTile(getModTile("drawer"))
                .addTexture(modRes("block/oak_drawer_front"))
                .addTexture(modRes("block/oak_drawer_front_open"))
                .addTexture(modRes("block/oak_cabinet_side"))
                .addTexture(modRes("block/oak_cabinet_top"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(drawer);

        drawer_with_door = SimpleEntrySet.builder(WoodType.class, "drawer_with_door",
                        getModBlock("oak_drawer_with_door"), () -> VanillaWoodTypes.OAK,
                        w -> new DrawerDoorBlock()
                )
                .requiresChildren("slab", "trapdoor") //REASON: recipes
                .addTile(getModTile("drawer_door"))
                //TEXTURES: drawer's cabinet_top, cabinet_side
                .addTexture(modRes("block/oak_drawer_with_door_front"))
                .addTexture(modRes("block/oak_drawer_with_door_front_open"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(drawer_with_door);

        drawer_with_books = SimpleEntrySet.builder(WoodType.class, "drawer_with_books",
                        getModBlock("oak_drawer_with_books"), () -> VanillaWoodTypes.OAK,
                        w -> new DrawerBooksBlock()
                )
                .requiresChildren("slab", "trapdoor") //REASON: recipes
                .addTile(getModTile("drawer_books"))
                //TEXTURES: drawer's cabinet_top, cabinet_side
                .addTexture(modRes("block/oak_drawer_with_books_front"))
                .addTexture(modRes("block/oak_drawer_with_books_front_open"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(drawer_with_books);

        small_drawers = SimpleEntrySet.builder(WoodType.class, "drawers", "small",
                        getModBlock("small_oak_drawers"), () -> VanillaWoodTypes.OAK,
                        w -> new SmallDrawersBlock()
                )
                .requiresChildren("slab", "trapdoor") //REASON: recipes
                .addTile(getModTile("small_drawers"))
                //TEXTURES: drawer's cabinet_top, cabinet_side
                .addTexture(modRes("block/small_oak_drawers_front"))
                .addTexture(modRes("block/small_oak_drawers_front_open"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(small_drawers);

        bookshelf_with_door = SimpleEntrySet.builder(WoodType.class, "bookshelf_with_door",
                        getModBlock("oak_bookshelf_with_door"), () -> VanillaWoodTypes.OAK,
                        w -> new BookshelfDoorBlock()
                )
                .requiresChildren("slab", "trapdoor") //REASON: recipes
                .addTile(getModTile("bookshelf_door"))
                //TEXTURES: drawer's cabinet_top, cabinet_side
                .addTexture(modRes("block/oak_bookshelf_with_door_front"))
                .addTexture(modRes("block/oak_bookshelf_with_door_front_open"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(bookshelf_with_door);

        glass_cabinet = SimpleEntrySet.builder(WoodType.class, "cabinet", "glass",
                        getModBlock("glass_oak_cabinet"), () -> VanillaWoodTypes.OAK,
                        w -> new GlassCabinetBlock()
                )
                .requiresChildren("slab", "trapdoor") //REASON: recipes
                .addTile(getModTile("glass_cabinet"))
                //TEXTURES: drawer's cabinet_top, cabinet_side
                .addTexture(modRes("block/glass_oak_cabinet_front"))
                .addTexture(modRes("block/glass_oak_cabinet_front_open"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(glass_cabinet);

        cabinet_with_glass_doors = SimpleEntrySet.builder(WoodType.class, "cabinet_with_glass_doors",
                        getModBlock("oak_cabinet_with_glass_doors"), () -> VanillaWoodTypes.OAK,
                        w -> new CabinetVariantBlock()
                )
                .requiresChildren("slab", "trapdoor") //REASON: recipes
                .addTile(getModTile("cabinet_variant"))
                //TEXTURES: drawer's cabinet_top, cabinet_side
                .addTexture(modRes("block/oak_cabinet_with_glass_doors_front"))
                .addTexture(modRes("block/oak_cabinet_with_glass_doors_front_open"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(cabinet_with_glass_doors);

        single_door_cabinet = SimpleEntrySet.builder(WoodType.class, "single_door_cabinet",
                        getModBlock("oak_single_door_cabinet"), () -> VanillaWoodTypes.OAK,
                        w -> new CabinetVariantBlock()
                )
                .requiresChildren("slab", "trapdoor") //REASON: recipes
                .addTile(getModTile("cabinet_variant"))
                //TEXTURES: drawer's cabinet_top, cabinet_side
                .addTexture(modRes("block/oak_single_door_cabinet_front"))
                .addTexture(modRes("block/oak_single_door_cabinet_front_open"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(single_door_cabinet);


    }
}