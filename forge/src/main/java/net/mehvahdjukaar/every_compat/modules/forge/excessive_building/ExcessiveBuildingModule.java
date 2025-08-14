package net.mehvahdjukaar.every_compat.modules.forge.excessive_building;

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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LadderBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.yirmiri.excessive_building.block.DecorativeShelfBlock;
import net.yirmiri.excessive_building.block.EBCraftingTableBlock;
import net.yirmiri.excessive_building.block.flammable.*;

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
    public final SimpleEntrySet<WoodType, Block> hollow_logs;
    public final SimpleEntrySet<WoodType, Block> hollow_stripped_logs;

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
                        woodType -> new FlammableVerticalStairBlock(Utils.copyPropertySafe(woodType.planks), 20, 5 )
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
                        woodType -> new FlammableBlock(Utils.copyPropertySafe(woodType.planks), 20, 5)
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
                        woodType -> new FlammableStairBlock(mosaics.blocks.get(woodType).defaultBlockState(),
                                Utils.copyPropertySafe(woodType.planks).sound(SoundType.WOOD), 20, 5)
                )
                .requiresFromMap(mosaics.blocks) //REASON: recipes
                //TEXTURES: mosaics (above)
                .addTag(BlockTags.STAIRS, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_STAIRS, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(mosaic_stairs);

        mosaic_slabs = SimpleEntrySet.builder(WoodType.class, "mosaic_slab",
                        getModBlock("oak_mosaic_slab"), () -> VanillaWoodTypes.OAK,
                        woodType -> new FlammableSlabBlock(Utils.copyPropertySafe(woodType.planks).sound(SoundType.WOOD), 20, 5)
                )
                .requiresFromMap(mosaics.blocks) //REASON: recipes
                //TEXTURES: mosaics (above)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.SLABS, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_SLABS, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(mosaic_slabs);

        mosaic_vertical_stairs = SimpleEntrySet.builder(WoodType.class, "mosaic_vertical_stairs",
                        getModBlock("oak_mosaic_vertical_stairs"), () -> VanillaWoodTypes.OAK,
                        woodType -> new FlammableVerticalStairBlock(Utils.copyPropertySafe(woodType.planks).sound(SoundType.WOOD), 20, 5)
                )
                .requiresFromMap(mosaics.blocks) //REASON: recipes
                //TEXTURES: mosaics (above)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.STAIRS, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_STAIRS, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(mosaic_vertical_stairs);

        chiseled_planks = SimpleEntrySet.builder(WoodType.class, "", "chiseled",
                        getModBlock("chiseled_oak"), () -> VanillaWoodTypes.OAK,
                        woodType -> new Block(Utils.copyPropertySafe(woodType.planks).sound(SoundType.WOOD))
                )
                .requiresChildren("slab") //REASON: recipes
                //TEXTURES: planks
                .addTexture(modRes("block/chiseled_oak"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(chiseled_planks);

        hollow_logs = SimpleEntrySet.builder(WoodType.class, "log","hollow",
                        getModBlock("hollow_oak_log"), () -> VanillaWoodTypes.OAK,
                        woodType -> new FlammableHollowRotatedPillarBlock(Utils.copyPropertySafe(woodType.log)
                                .sound(woodType.getSound())
                                .ignitedByLava(),
                                5, 5)
                )
                .requiresChildren("stripped_log") //REASON: textures
                //TEXTURES: log, stripped_log
                .addTexture(modRes("block/crimson_ladder"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(hollow_logs);

        hollow_stripped_logs = SimpleEntrySet.builder(WoodType.class, "log","hollow_stripped",
                        getModBlock("hollow_stripped_oak_log"), () -> VanillaWoodTypes.OAK,
                        woodType -> new FlammableHollowRotatedPillarBlock(Utils.copyPropertySafe(woodType.log)
                                .sound(woodType.getSound())
                                .ignitedByLava(),
                                5, 5)
                )
                .requiresChildren("stripped_log") //REASON: textures, recipes
                //TEXTURES: stripped_log
                .addTexture(modRes("block/crimson_ladder"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(hollow_stripped_logs);

//!! SPRUCE
        crafting_tables = SimpleEntrySet.builder(WoodType.class, "crafting_table",
                        getModBlock("spruce_crafting_table"), () -> VanillaWoodTypes.SPRUCE,
                        woodType -> new EBCraftingTableBlock(BlockBehaviour.Properties.of()
                                .mapColor(woodType.getColor())
                                .instrument(NoteBlockInstrument.BASS)
                                .strength(2.5F, 2.5F)
                                .sound(SoundType.WOOD)
                                .ignitedByLava()
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
                        getModBlock("spruce_decorative_shelf"), () -> VanillaWoodTypes.SPRUCE,
                        woodType -> new DecorativeShelfBlock(Utils.copyPropertySafe(Blocks.BOOKSHELF).sound(SoundType.WOOD), 30, 20)
                )
                .addTexture(modRes("block/spruce_empty_shelf"))
                .addTextureM(modRes("block/spruce_empty_potion_shelf"), EveryCompat.res("block/eb/spruce_empty_potion_shelf_m"))
                .addTextureM(modRes("block/spruce_water_potion_shelf"), EveryCompat.res("block/eb/spruce_x_potion_shelf_m"))
                .addTextureM(modRes("block/spruce_alchemist_shelf"), EveryCompat.res("block/eb/spruce_alchemist_shelf_m"))
                .addTextureM(modRes("block/spruce_potion_shelf"), EveryCompat.res("block/eb/spruce_x_potion_shelf_m"))
                .addTextureM(modRes("block/spruce_collectors_shelf"), EveryCompat.res("block/eb/spruce_collectors_shelf_m"))
                .addTextureM(modRes("block/spruce_nether_brickshelf"), EveryCompat.res("block/eb/spruce_x_brickshelf_m"))
                .addTextureM(modRes("block/spruce_brickshelf"), EveryCompat.res("block/eb/spruce_x_brickshelf_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(decorative_shelves);

        bookshelves = SimpleEntrySet.builder(WoodType.class, "bookshelf",
                        getModBlock("spruce_bookshelf"), () -> VanillaWoodTypes.SPRUCE,
                        woodType -> new FlammableBlock(Utils.copyPropertySafe(Blocks.BOOKSHELF).sound(SoundType.WOOD), 30, 20)
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
                        woodType -> new LadderBlock(Utils.copyPropertySafe(Blocks.LADDER).sound(woodType.getSound()))
                )
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addTexture(modRes("block/crimson_ladder"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.CLIMBABLE, Registries.BLOCK)
                .addTag(BlockTags.FALL_DAMAGE_RESETTING, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(ladders);

    }

}