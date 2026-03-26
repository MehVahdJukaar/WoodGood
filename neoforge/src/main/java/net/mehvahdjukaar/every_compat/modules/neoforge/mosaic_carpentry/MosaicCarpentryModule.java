package net.mehvahdjukaar.every_compat.modules.neoforge.mosaic_carpentry;

import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;


public class MosaicCarpentryModule extends EveryCompatModule {

    public final SimpleEntrySet<WoodType, Block> mosaics;
    public final SimpleEntrySet<WoodType, Block> mosaicStairs;
    public final SimpleEntrySet<WoodType, Block> mosaicSlabs;

    public MosaicCarpentryModule(String modId) {
        super(modId, "mc");
        ResourceLocation tab = modRes("mosaic_carpentry");


        mosaics = SimpleEntrySet.builder(WoodType.class, "mosaic",
                        getModBlock("spruce_mosaic"), () -> VanillaWoodTypes.SPRUCE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .addTexture(modRes("block/spruce_mosaic"))
                .setTab(getModTab(tab))
                .defaultRecipe()
                .build();
        this.addEntry(mosaics);

        mosaicStairs = SimpleEntrySet.builder(WoodType.class, "mosaic_stairs",
                        getModBlock("spruce_mosaic_stairs"), () -> VanillaWoodTypes.SPRUCE,
                        w -> new StairBlock(w.planks.defaultBlockState(), Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_STAIRS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_STAIRS, Registries.ITEM)
                .addTexture(modRes("block/spruce_mosaic"))
                .setTab(getModTab(tab))
                .defaultRecipe()
                .build();
        this.addEntry(mosaicStairs);

        mosaicSlabs = SimpleEntrySet.builder(WoodType.class, "mosaic_slab",
                        getModBlock("spruce_mosaic_slab"), () -> VanillaWoodTypes.SPRUCE,
                        w -> new SlabBlock(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_SLABS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_SLABS, Registries.ITEM)
                .addTexture(modRes("block/spruce_mosaic"))
                .setTab(getModTab(tab))
                .defaultRecipe()
                .build();
        this.addEntry(mosaicSlabs);
    }
}
