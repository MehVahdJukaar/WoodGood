package net.mehvahdjukaar.every_compat.modules.forge.mosaic_carpentry;

import caittastic.mosaiccarpentry.MosaicCarpentry;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;


public class MosaicCarpentryModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> MOSAICS;
    public final SimpleEntrySet<WoodType, Block> MOSAIC_STAIRS;
    public final SimpleEntrySet<WoodType, Block> MOSAIC_SLABS;

    public MosaicCarpentryModule(String modId) {
        super(modId, "mc");
        CreativeModeTab tab = MosaicCarpentry.MOSAIC_CARPENTRY_TAB;


        MOSAICS = SimpleEntrySet.builder(WoodType.class, "mosaic",
                        () -> getModBlock("spruce_mosaic"), () -> WoodTypeRegistry.getValue(new ResourceLocation("spruce")),
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.PLANKS, Registry.ITEM_REGISTRY)
                .addTexture(modRes("block/spruce_mosaic"))
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(MOSAICS);

        MOSAIC_STAIRS = SimpleEntrySet.builder(WoodType.class, "mosaic_stairs",
                        () -> getModBlock("spruce_mosaic_stairs"), () -> WoodTypeRegistry.getValue(new ResourceLocation("spruce")),
                        w -> new StairBlock(w.planks.defaultBlockState(), Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_STAIRS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.WOODEN_STAIRS, Registry.ITEM_REGISTRY)
                .addTexture(modRes("block/spruce_mosaic"))
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(MOSAIC_STAIRS);

        MOSAIC_SLABS = SimpleEntrySet.builder(WoodType.class, "mosaic_slab",
                        () -> getModBlock("spruce_mosaic_slab"), () -> WoodTypeRegistry.getValue(new ResourceLocation("spruce")),
                        w -> new SlabBlock(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_SLABS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.WOODEN_SLABS, Registry.ITEM_REGISTRY)
                .addTexture(modRes("block/spruce_mosaic"))
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(MOSAIC_SLABS);
    }
}
