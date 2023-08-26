package net.mehvahdjukaar.every_compat.modules.mosaic_carpentry;

import caittastic.mosaiccarpentry.MosaicCarpentry;
import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.mehvahdjukaar.selene.block_set.wood.WoodTypeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;


public class MosaicCarpentryModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> mosaics;
    public final SimpleEntrySet<WoodType, Block> mosaicStairs;
    public final SimpleEntrySet<WoodType, Block> mosaicSlabs;

    public MosaicCarpentryModule(String modId) {
        super(modId, "mc");
        CreativeModeTab tab = MosaicCarpentry.MOSAIC_CARPENTRY_TAB;


        mosaics = SimpleEntrySet.builder(WoodType.class, "mosaic",
                        () -> getModBlock("spruce_mosaic"), () -> WoodTypeRegistry.WOOD_TYPES.get(new ResourceLocation("spruce")),
                        w -> new Block(WoodGood.copySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.PLANKS, Registry.ITEM_REGISTRY)
                .addTexture(modRes("block/spruce_mosaic"))
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(mosaics);

        mosaicStairs = SimpleEntrySet.builder(WoodType.class, "mosaic_stairs",
                        () -> getModBlock("spruce_mosaic_stairs"), () -> WoodTypeRegistry.WOOD_TYPES.get(new ResourceLocation("spruce")),
                        w -> new StairBlock(w.planks.defaultBlockState(), WoodGood.copySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_STAIRS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.WOODEN_STAIRS, Registry.ITEM_REGISTRY)
                .addTexture(modRes("block/spruce_mosaic"))
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(mosaicStairs);

        mosaicSlabs = SimpleEntrySet.builder(WoodType.class, "mosaic_slab",
                        () -> getModBlock("spruce_mosaic_slab"), () -> WoodTypeRegistry.WOOD_TYPES.get(new ResourceLocation("spruce")),
                        w -> new SlabBlock(WoodGood.copySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_SLABS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.WOODEN_SLABS, Registry.ITEM_REGISTRY)
                .addTexture(modRes("block/spruce_mosaic"))
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(mosaicSlabs);
    }
}