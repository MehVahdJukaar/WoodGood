package net.mehvahdjukaar.every_compat.modules.forge.storage_delight;

import com.axperty.storagedelight.block.*;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

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
        ResourceLocation tab = (PlatHelper.getPlatform().isFabric()) ? modRes("title") : modRes(modId);

        drawer = SimpleEntrySet.builder(WoodType.class, "drawer",
                        getModBlock("oak_drawer"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DrawerBlock(Utils.copyPropertySafe(Blocks.BARREL))
                )
                .requiresChildren("slab", "trapdoor") //REASON: recipes
                .addTile(getModTile("drawer"))
                .addTextureM(modRes("block/oak_drawer_front"), EveryCompat.res("block/sdl/oak_drawer_front_m"))
                .addTextureM(modRes("block/oak_drawer_front_open"), EveryCompat.res("block/sdl/oak_drawer_front_open_m"))
                .addTexture(modRes("block/oak_cabinet_side"))
                .addTexture(modRes("block/oak_cabinet_top"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(drawer);

        drawer_with_door = SimpleEntrySet.builder(WoodType.class, "drawer_with_door",
                        getModBlock("oak_drawer_with_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DrawerDoorBlock(Utils.copyPropertySafe(Blocks.BARREL))
                )
                .requiresChildren("slab", "trapdoor") //REASON: recipes
                .addTile(getModTile("drawer_door"))
                //TEXTURES: drawer's cabinet_top, cabinet_side
                .addTextureM(modRes("block/oak_drawer_with_door_front"), EveryCompat.res("block/sdl/oak_drawer_with_door_front_m"))
                .addTextureM(modRes("block/oak_drawer_with_door_front_open"), EveryCompat.res("block/sdl/oak_drawer_with_door_front_open_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(drawer_with_door);

        drawer_with_books = SimpleEntrySet.builder(WoodType.class, "drawer_with_books",
                        getModBlock("oak_drawer_with_books"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DrawerBooksBlock(Utils.copyPropertySafe(Blocks.BARREL))
                )
                .requiresChildren("slab", "trapdoor") //REASON: recipes
                .addTile(getModTile("drawer_books"))
                //TEXTURES: drawer's cabinet_top, cabinet_side
                .addTextureM(modRes("block/oak_drawer_with_books_front"), EveryCompat.res("block/sdl/oak_drawer_with_books_front_m"))
                .addTextureM(modRes("block/oak_drawer_with_books_front_open"), EveryCompat.res("block/sdl/oak_drawer_with_books_front_open_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(drawer_with_books);

        small_drawers = SimpleEntrySet.builder(WoodType.class, "drawers", "small",
                        getModBlock("small_oak_drawers"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new SmallDrawersBlock(Utils.copyPropertySafe(Blocks.BARREL))
                )
                .requiresChildren("slab", "trapdoor") //REASON: recipes
                .addTile(getModTile("small_drawers"))
                //TEXTURES: drawer's cabinet_top, cabinet_side
                .addTextureM(modRes("block/small_oak_drawers_front"), EveryCompat.res("block/sdl/small_oak_drawers_front_m"))
                .addTextureM(modRes("block/small_oak_drawers_front_open"), EveryCompat.res("block/sdl/small_oak_drawers_front_open_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(small_drawers);

        bookshelf_with_door = SimpleEntrySet.builder(WoodType.class, "bookshelf_with_door",
                        getModBlock("oak_bookshelf_with_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BookshelfDoorBlock(Utils.copyPropertySafe(Blocks.BARREL))
                )
                .requiresChildren("slab", "trapdoor") //REASON: recipes
                .addTile(getModTile("bookshelf_door"))
                //TEXTURES: drawer's cabinet_top, cabinet_side
                .addTextureM(modRes("block/oak_bookshelf_with_door_front"), EveryCompat.res("block/sdl/oak_bookshelf_with_door_front_m"))
                .addTextureM(modRes("block/oak_bookshelf_with_door_front_open"), EveryCompat.res("block/sdl/oak_bookshelf_with_door_front_open_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(bookshelf_with_door);

        glass_cabinet = SimpleEntrySet.builder(WoodType.class, "cabinet", "glass",
                        getModBlock("glass_oak_cabinet"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new GlassCabinetBlock(Utils.copyPropertySafe(Blocks.BARREL))
                )
                .requiresChildren("slab", "trapdoor") //REASON: recipes
                .addTile(getModTile("glass_cabinet"))
                //TEXTURES: drawer's cabinet_top, cabinet_side
                .addTextureM(modRes("block/glass_oak_cabinet_front"), EveryCompat.res("block/sdl/glass_oak_cabinet_front_m"))
                .addTexture(modRes("block/glass_oak_cabinet_front_open"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(glass_cabinet);

        cabinet_with_glass_doors = SimpleEntrySet.builder(WoodType.class, "cabinet_with_glass_doors",
                        getModBlock("oak_cabinet_with_glass_doors"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CabinetVariantBlock(Utils.copyPropertySafe(Blocks.BARREL))
                )
                .requiresChildren("slab", "trapdoor") //REASON: recipes
                .addTile(getModTile("cabinet_variant"))
                //TEXTURES: drawer's cabinet_top, cabinet_side
                .addTextureM(modRes("block/oak_cabinet_with_glass_doors_front"), EveryCompat.res("block/sdl/oak_cabinet_with_glass_doors_front_m"))
                .addTextureM(modRes("block/oak_cabinet_with_glass_doors_front_open"), EveryCompat.res("block/sdl/oak_cabinet_with_glass_doors_front_open_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(cabinet_with_glass_doors);

        single_door_cabinet = SimpleEntrySet.builder(WoodType.class, "single_door_cabinet",
                        getModBlock("oak_single_door_cabinet"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CabinetVariantBlock(Utils.copyPropertySafe(Blocks.BARREL))
                )
                .requiresChildren("slab", "trapdoor") //REASON: recipes
                .addTile(getModTile("cabinet_variant"))
                //TEXTURES: drawer's cabinet_top, cabinet_side
                .addTextureM(modRes("block/oak_single_door_cabinet_front"), EveryCompat.res("block/sdl/oak_single_door_cabinet_front_m"))
                .addTextureM(modRes("block/oak_single_door_cabinet_front_open"), EveryCompat.res("block/sdl/oak_single_door_cabinet_front_open_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(single_door_cabinet);


    }
}