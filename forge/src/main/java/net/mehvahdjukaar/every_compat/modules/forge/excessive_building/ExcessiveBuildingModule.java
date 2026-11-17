package net.mehvahdjukaar.every_compat.modules.forge.excessive_building;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.yirmiri.excessive_building.block.custom.ModCraftingTableBlock;
import net.yirmiri.excessive_building.block.custom.VerticalStairBlock;

///SUPPORT: v1.2.2
@SuppressWarnings({"removal", "deprecation"})
public class ExcessiveBuildingModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> vertical_stairs;
    public final SimpleEntrySet<WoodType, Block> mosaics;
    public final SimpleEntrySet<WoodType, Block> mosaic_stairs;
    public final SimpleEntrySet<WoodType, Block> mosaic_slabs;
    public final SimpleEntrySet<WoodType, Block> mosaic_vertical_stairs;
    public final SimpleEntrySet<WoodType, Block> chiseled_planks;

    public final SimpleEntrySet<WoodType, Block> crafting_tables;

    public ExcessiveBuildingModule(String modId) {
        super(modId, "eb");
        ResourceLocation tab = modRes(modId);

//!! OAK
        vertical_stairs = SimpleEntrySet.builder(WoodType.class, "vertical_stairs",
                        () -> getModBlock("oak_vertical_stairs"), () -> WoodTypeRegistry.OAK_TYPE,
                        woodType -> new VerticalStairBlock(Utils.copyPropertySafe(woodType.planks)
                                .strength(2.0F).explosionResistance(3.0F).sound(woodType.getSound())
                        )
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
                        () -> getModBlock("oak_mosaic"), () -> WoodTypeRegistry.OAK_TYPE,
                        woodType -> new Block(Utils.copyPropertySafe(woodType.planks)
                                .strength(2.0F).explosionResistance(3.0F).sound(woodType.getSound())
                        )
                )
                .requiresChildren("slab") //REASON: recipes
                .addTexture(modRes("block/oak_mosaic"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(mosaics);

        mosaic_stairs = SimpleEntrySet.builder(WoodType.class, "mosaic_stairs",
                        () -> getModBlock("oak_mosaic_stairs"), () -> WoodTypeRegistry.OAK_TYPE,
                        woodType -> new StairBlock(mosaics.blocks.get(woodType).defaultBlockState(),
                                Utils.copyPropertySafe(woodType.planks)
                                        .strength(2.0F).explosionResistance(3.0F).sound(woodType.getSound())
                        )
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
                        () -> getModBlock("oak_mosaic_slab"), () -> WoodTypeRegistry.OAK_TYPE,
                        woodType -> new SlabBlock(Utils.copyPropertySafe(woodType.planks)
                                .strength(2.0F).explosionResistance(3.0F).sound(woodType.getSound())
                        )
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
                        () -> getModBlock("oak_mosaic_vertical_stairs"), () -> WoodTypeRegistry.OAK_TYPE,
                        woodType -> new VerticalStairBlock(Utils.copyPropertySafe(woodType.planks)
                                .strength(2.0F).explosionResistance(3.0F).sound(woodType.getSound())
                        )
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
                        () -> getModBlock("chiseled_oak"), () -> WoodTypeRegistry.OAK_TYPE,
                        woodType -> new Block(Utils.copyPropertySafe(woodType.planks)
                                .strength(2.0F).explosionResistance(3.0F).sound(woodType.getSound())
                        )
                )
                .requiresChildren("slab") //REASON: recipes
                //TEXTURES: planks
                .addTexture(modRes("block/chiseled_oak"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(chiseled_planks);

//!! SPRUCE
        crafting_tables = SimpleEntrySet.builder(WoodType.class, "crafting_table",
                        () -> getModBlock("spruce_crafting_table"), () -> WoodTypeRegistry.getValue(new ResourceLocation("spruce")),
                        woodType -> new ModCraftingTableBlock(BlockBehaviour.Properties.of(Material.WOOD)
                                .strength(2.5F).explosionResistance(2.5F).sound(SoundType.WOOD)
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

    }

}