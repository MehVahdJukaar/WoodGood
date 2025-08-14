package net.mehvahdjukaar.every_compat.modules.fabric.excessive_building;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.*;
import net.yirmiri.excessive_building.block.DecorativeShelfBlock;
import net.yirmiri.excessive_building.block.EBCraftingTableBlock;
import net.yirmiri.excessive_building.block.VerticalStairsBlock;
import net.yirmiri.excessive_building.block.configurable.EBBookshelfBlock;
import net.yirmiri.excessive_building.block.configurable.EBLadderBlock;

//SUPPORT: v3.0.1+
// DIFFERENCE: The classes for blocks are not same in FABRIC & FORGE
//             FABRIC do not have hollow_log & hollow_stripped_log
public class ExcessiveBuildingModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> vertical_stairs;
    public final SimpleEntrySet<WoodType, Block> mosaics;
    public final SimpleEntrySet<WoodType, Block> mosaic_stairs;
    public final SimpleEntrySet<WoodType, Block> mosaic_slabs;
    public final SimpleEntrySet<WoodType, Block> mosaic_vertical_stairs;
    public final SimpleEntrySet<WoodType, Block> chiseled_planks;

    public final SimpleEntrySet<WoodType, Block> crafting_tables;
    public final SimpleEntrySet<WoodType, Block> decorative_shelves;
    public final SimpleEntrySet<WoodType, Block> bookshelves;

    public final SimpleEntrySet<WoodType, Block> ladders;

    public ExcessiveBuildingModule(String modId) {
        super(modId, "eb");
        ResourceLocation tab = modRes(modId);

//!! OAK
        vertical_stairs = SimpleEntrySet.builder(WoodType.class, "vertical_stairs",
                        getModBlock("oak_vertical_stairs"), () -> VanillaWoodTypes.OAK,
                        woodType -> new VerticalStairsBlock(Utils.copyPropertySafe(woodType.planks))
                )
                //TEXTURES: planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.STAIRS, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_STAIRS, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(vertical_stairs);

        mosaics = SimpleEntrySet.builder(WoodType.class, "mosaic",
                        getModBlock("oak_mosaic"), () -> VanillaWoodTypes.OAK,
                        woodType -> new Block(Utils.copyPropertySafe(woodType.planks))
                )
                .requiresChildren("slab") //REASON: recipes
                .addTexture(modRes("block/oak_mosaic"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(mosaics);

        mosaic_stairs = SimpleEntrySet.builder(WoodType.class, "mosaic_stairs",
                        getModBlock("oak_mosaic_stairs"), () -> VanillaWoodTypes.OAK,
                        woodType -> new StairBlock(mosaics.blocks.get(woodType).defaultBlockState(),
                                Utils.copyPropertySafe(woodType.planks).sound(SoundType.WOOD))
                )
                .requiresFromMap(mosaics.blocks) //REASON: recipes
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.STAIRS, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_STAIRS, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(mosaic_stairs);

        mosaic_slabs = SimpleEntrySet.builder(WoodType.class, "mosaic_slab",
                        getModBlock("oak_mosaic_slab"), () -> VanillaWoodTypes.OAK,
                        woodType -> new SlabBlock(Utils.copyPropertySafe(woodType.planks).sound(SoundType.WOOD))
                )
                .requiresFromMap(mosaics.blocks) //REASON: recipes
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.SLABS, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_SLABS, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(mosaic_slabs);

        mosaic_vertical_stairs = SimpleEntrySet.builder(WoodType.class, "mosaic_vertical_stairs",
                        getModBlock("oak_mosaic_vertical_stairs"), () -> VanillaWoodTypes.OAK,
                        woodType -> new VerticalStairsBlock(Utils.copyPropertySafe(woodType.planks).sound(SoundType.WOOD))
                )
                .requiresFromMap(mosaics.blocks) //REASON: recipes
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.STAIRS, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_STAIRS, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(mosaic_vertical_stairs);

        chiseled_planks = SimpleEntrySet.builder(WoodType.class, "planks", "chiseled",
                        getModBlock("chiseled_oak_planks"), () -> VanillaWoodTypes.OAK,
                        woodType -> new Block(Utils.copyPropertySafe(woodType.planks).sound(SoundType.WOOD))
                )
                .requiresChildren("slab") //REASON: recipes
                .addTexture(modRes("block/chiseled_oak_planks_side"))
                .addTexture(modRes("block/chiseled_oak_planks_top"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(chiseled_planks);

//!! SPRUCE
        crafting_tables = SimpleEntrySet.builder(WoodType.class, "crafting_table",
                        getModBlock("spruce_crafting_table"), () -> VanillaWoodTypes.SPRUCE,
                        woodType -> new EBCraftingTableBlock(Utils.copyPropertySafe(woodType.planks)
                                .sound(SoundType.WOOD)
                        )
                )
                //TEXTURE: texture is oak_craftng_table's texture
                .addTextureM(EveryCompat.res("block/spruce_crafting_table_front"), EveryCompat.res("block/eb/spruce_crafting_table_front_m"))
                .addTextureM(EveryCompat.res("block/spruce_crafting_table_side"), EveryCompat.res("block/eb/spruce_crafting_table_side_m"))
                .addTextureM(EveryCompat.res("block/spruce_crafting_table_top"), EveryCompat.res("block/eb/spruce_crafting_table_top_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(crafting_tables);

        decorative_shelves = SimpleEntrySet.builder(WoodType.class, "decorative_shelf",
                        getModBlock("oak_decorative_shelf"), () -> VanillaWoodTypes.OAK,
                        woodType -> new DecorativeShelfBlock(Utils.copyPropertySafe(Blocks.BOOKSHELF).sound(SoundType.WOOD))
                )
                .requiresFromMap(mosaics.blocks) //REASON: textures, recipes
                .addTexture(modRes("block/oak_decorative_shelf0"))
                .addTextureM(modRes("block/oak_decorative_shelf1"), EveryCompat.res("block/eb/oak_decorative_shelf1_m"))
                .addTextureM(modRes("block/oak_decorative_shelf2"), EveryCompat.res("block/eb/oak_decorative_shelf2_m"))
                .addTextureM(modRes("block/oak_decorative_shelf3"), EveryCompat.res("block/eb/oak_decorative_shelf3x_m"))
                .addTextureM(modRes("block/oak_decorative_shelf4"), EveryCompat.res("block/eb/oak_decorative_shelf3x_m"))
                .addTextureM(modRes("block/oak_decorative_shelf5"), EveryCompat.res("block/eb/oak_decorative_shelf5_m"))
                .addTextureM(modRes("block/oak_decorative_shelf6"), EveryCompat.res("block/eb/oak_decorative_shelf6_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(decorative_shelves);

        bookshelves = SimpleEntrySet.builder(WoodType.class, "bookshelf",
                        getModBlock("spruce_bookshelf"), () -> VanillaWoodTypes.SPRUCE,
                        woodType -> new EBBookshelfBlock(Utils.copyPropertySafe(Blocks.BOOKSHELF).sound(SoundType.WOOD))
                )
                //TEXTURES: planks
                .addTextureM(modRes("block/spruce_bookshelf"), EveryCompat.res("block/eb/spruce_bookshelf_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.ENCHANTMENT_POWER_PROVIDER, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(bookshelves);

//!! CRIMSON
        ladders = SimpleEntrySet.builder(WoodType.class, "ladder",
                        getModBlock("crimson_ladder"), () -> VanillaWoodTypes.CRIMSON,
                        woodType -> new EBLadderBlock(Utils.copyPropertySafe(Blocks.LADDER).sound(woodType.getSound()))
                )
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addTexture(modRes("block/crimson_ladder"))
                .addTexture(modRes("item/crimson_ladder"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.CLIMBABLE, Registries.BLOCK)
                .addTag(BlockTags.FALL_DAMAGE_RESETTING, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(ladders);

    }

}