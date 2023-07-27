package net.mehvahdjukaar.every_compat.modules.mcaw;

import com.mcwroofs.kikoz.MacawsRoofs;
import com.mcwroofs.kikoz.init.BlockInit;
import com.mcwroofs.kikoz.objects.roofs.*;
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

public class MacawRoofsModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> ATTIC_ROOFS;
    public final SimpleEntrySet<WoodType, Block> LOWER_ROOFS;
    public final SimpleEntrySet<WoodType, Block> ROOFS;
    public final SimpleEntrySet<WoodType, Block> STEEP_ROOFS;
    public final SimpleEntrySet<WoodType, Block> TOP_ROOFS;
    public final SimpleEntrySet<WoodType, Block> UPPER_LOWER_ROOFS;
    public final SimpleEntrySet<WoodType, Block> UPPER_STEEP_ROOFS;

    public final SimpleEntrySet<WoodType, Block> PLANKS_ATTIC_ROOFS;
    public final SimpleEntrySet<WoodType, Block> PLANKS_LOWER_ROOFS;
    public final SimpleEntrySet<WoodType, Block> PLANKS_ROOFS;
    public final SimpleEntrySet<WoodType, Block> PLANKS_STEEP_ROOFS;
    public final SimpleEntrySet<WoodType, Block> PLANKS_TOP_ROOFS;
    public final SimpleEntrySet<WoodType, Block> PLANKS_UPPER_LOWER_ROOFS;
    public final SimpleEntrySet<WoodType, Block> PLANKS_UPPER_STEEP_ROOFS;

    public MacawRoofsModule(String modId) {
        super(modId, "mcrf");

        ATTIC_ROOFS = SimpleEntrySet.builder(WoodType.class, "attic_roof",
                        BlockInit.OAK_ATTIC_ROOF, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new RoofGlass(WoodGood.copySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .build();

        this.addEntry(ATTIC_ROOFS);

        LOWER_ROOFS = SimpleEntrySet.builder(WoodType.class, "lower_roof",
                        BlockInit.OAK_LOWER_ROOF, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new BaseRoof(Blocks.OAK_PLANKS.defaultBlockState(), WoodGood.copySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::solid)
                .defaultRecipe()
                .build();

        this.addEntry(LOWER_ROOFS);

        ROOFS = SimpleEntrySet.builder(WoodType.class, "roof",
                        BlockInit.OAK_ROOF, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new BaseRoof(Blocks.OAK_PLANKS.defaultBlockState(), WoodGood.copySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::solid)
                .defaultRecipe()
                .build();

        this.addEntry(ROOFS);

        STEEP_ROOFS = SimpleEntrySet.builder(WoodType.class, "steep_roof",
                        BlockInit.OAK_STEEP_ROOF, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new SteepRoof(Blocks.OAK_PLANKS.defaultBlockState(), WoodGood.copySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::solid)
                .defaultRecipe()
                .build();

        this.addEntry(STEEP_ROOFS);

        TOP_ROOFS = SimpleEntrySet.builder(WoodType.class, "top_roof",
                        BlockInit.OAK_TOP_ROOF, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new RoofTopNew(WoodGood.copySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::solid)
                .defaultRecipe()
                .build();

        this.addEntry(TOP_ROOFS);

        UPPER_LOWER_ROOFS = SimpleEntrySet.builder(WoodType.class, "upper_lower_roof",
                        BlockInit.OAK_UPPER_LOWER_ROOF, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new Lower(Blocks.OAK_PLANKS.defaultBlockState(), WoodGood.copySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::solid)
                .defaultRecipe()
                .build();

        this.addEntry(UPPER_LOWER_ROOFS);

        UPPER_STEEP_ROOFS = SimpleEntrySet.builder(WoodType.class, "upper_steep_roof",
                        BlockInit.OAK_UPPER_STEEP_ROOF, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new Steep(Blocks.OAK_PLANKS.defaultBlockState(), WoodGood.copySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::solid)
                .defaultRecipe()
                .build();

        this.addEntry(UPPER_STEEP_ROOFS);

        PLANKS_ATTIC_ROOFS = SimpleEntrySet.builder(WoodType.class, "planks_attic_roof",
                        BlockInit.OAK_PLANKS_ATTIC_ROOF, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new RoofGlass(WoodGood.copySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .build();

        this.addEntry(PLANKS_ATTIC_ROOFS);

        PLANKS_LOWER_ROOFS = SimpleEntrySet.builder(WoodType.class, "planks_lower_roof",
                        BlockInit.OAK_PLANKS_LOWER_ROOF, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new BaseRoof(Blocks.OAK_PLANKS.defaultBlockState(), WoodGood.copySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::solid)
                .defaultRecipe()
                .build();

        this.addEntry(PLANKS_LOWER_ROOFS);

        PLANKS_ROOFS = SimpleEntrySet.builder(WoodType.class, "planks_roof",
                        BlockInit.OAK_PLANKS_ROOF, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new BaseRoof(Blocks.OAK_PLANKS.defaultBlockState(), WoodGood.copySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::solid)
                .defaultRecipe()
                .build();

        this.addEntry(PLANKS_ROOFS);

        PLANKS_STEEP_ROOFS = SimpleEntrySet.builder(WoodType.class, "planks_steep_roof",
                        BlockInit.OAK_PLANKS_STEEP_ROOF, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new SteepRoof(Blocks.OAK_PLANKS.defaultBlockState(), WoodGood.copySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::solid)
                .defaultRecipe()
                .build();

        this.addEntry(PLANKS_STEEP_ROOFS);

        PLANKS_TOP_ROOFS = SimpleEntrySet.builder(WoodType.class, "planks_top_roof",
                        BlockInit.OAK_PLANKS_TOP_ROOF, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new RoofTopNew(WoodGood.copySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::solid)
                .defaultRecipe()
                .build();

        this.addEntry(PLANKS_TOP_ROOFS);

        PLANKS_UPPER_LOWER_ROOFS = SimpleEntrySet.builder(WoodType.class, "planks_upper_lower_roof",
                        BlockInit.OAK_PLANKS_UPPER_LOWER_ROOF, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new Lower(Blocks.OAK_PLANKS.defaultBlockState(), WoodGood.copySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::solid)
                .defaultRecipe()
                .build();

        this.addEntry(PLANKS_UPPER_LOWER_ROOFS);

        PLANKS_UPPER_STEEP_ROOFS = SimpleEntrySet.builder(WoodType.class, "planks_upper_steep_roof",
                        BlockInit.OAK_PLANKS_UPPER_STEEP_ROOF, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new Steep(Blocks.OAK_PLANKS.defaultBlockState(), WoodGood.copySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::solid)
                .defaultRecipe()
                .build();

        this.addEntry(PLANKS_UPPER_STEEP_ROOFS);
    }
}