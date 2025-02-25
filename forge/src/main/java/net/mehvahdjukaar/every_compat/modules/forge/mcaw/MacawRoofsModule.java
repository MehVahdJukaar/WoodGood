package net.mehvahdjukaar.every_compat.modules.forge.mcaw;

import com.mcwroofs.kikoz.init.BlockInit;
import com.mcwroofs.kikoz.objects.roofs.*;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

//SUPPORT: v2.2.4+
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
        super(modId, "mcr");
        var tab = modRes(modId);

        ATTIC_ROOFS = SimpleEntrySet.builder(WoodType.class, "attic_roof",
                        BlockInit.OAK_ATTIC_ROOF, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new RoofGlass(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .setTabKey(tab)
                .build();
        this.addEntry(ATTIC_ROOFS);

        LOWER_ROOFS = SimpleEntrySet.builder(WoodType.class, "lower_roof",
                        BlockInit.OAK_LOWER_ROOF, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BaseRoof(Blocks.OAK_PLANKS.defaultBlockState(), Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setRenderType(RenderLayer.SOLID)
                .defaultRecipe()
                .setTabKey(tab)
                .build();
        this.addEntry(LOWER_ROOFS);

        ROOFS = SimpleEntrySet.builder(WoodType.class, "roof",
                        BlockInit.OAK_ROOF, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BaseRoof(Blocks.OAK_PLANKS.defaultBlockState(), Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setRenderType(RenderLayer.SOLID)
                .defaultRecipe()
                .setTabKey(tab)
                .build();
        this.addEntry(ROOFS);

        STEEP_ROOFS = SimpleEntrySet.builder(WoodType.class, "steep_roof",
                        BlockInit.OAK_STEEP_ROOF, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new SteepRoof(Blocks.OAK_PLANKS.defaultBlockState(), Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setRenderType(RenderLayer.SOLID)
                .defaultRecipe()
                .setTabKey(tab)
                .build();
        this.addEntry(STEEP_ROOFS);

        TOP_ROOFS = SimpleEntrySet.builder(WoodType.class, "top_roof",
                        BlockInit.OAK_TOP_ROOF, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new RoofTopNew(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setRenderType(RenderLayer.SOLID)
                .defaultRecipe()
                .setTabKey(tab)
                .build();
        this.addEntry(TOP_ROOFS);

        UPPER_LOWER_ROOFS = SimpleEntrySet.builder(WoodType.class, "upper_lower_roof",
                        BlockInit.OAK_UPPER_LOWER_ROOF, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Lower(Blocks.OAK_PLANKS.defaultBlockState(), Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setRenderType(RenderLayer.SOLID)
                .defaultRecipe()
                .setTabKey(tab)
                .build();
        this.addEntry(UPPER_LOWER_ROOFS);

        UPPER_STEEP_ROOFS = SimpleEntrySet.builder(WoodType.class, "upper_steep_roof",
                        BlockInit.OAK_UPPER_STEEP_ROOF, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Steep(Blocks.OAK_PLANKS.defaultBlockState(), Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setRenderType(RenderLayer.SOLID)
                .defaultRecipe()
                .setTabKey(tab)
                .build();
        this.addEntry(UPPER_STEEP_ROOFS);

        PLANKS_ATTIC_ROOFS = SimpleEntrySet.builder(WoodType.class, "planks_attic_roof",
                        BlockInit.OAK_PLANKS_ATTIC_ROOF, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new RoofGlass(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .setTabKey(tab)
                .build();
        this.addEntry(PLANKS_ATTIC_ROOFS);

        PLANKS_LOWER_ROOFS = SimpleEntrySet.builder(WoodType.class, "planks_lower_roof",
                        BlockInit.OAK_PLANKS_LOWER_ROOF, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BaseRoof(Blocks.OAK_PLANKS.defaultBlockState(), Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setRenderType(RenderLayer.SOLID)
                .defaultRecipe()
                .setTabKey(tab)
                .build();
        this.addEntry(PLANKS_LOWER_ROOFS);

        PLANKS_ROOFS = SimpleEntrySet.builder(WoodType.class, "planks_roof",
                        BlockInit.OAK_PLANKS_ROOF, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BaseRoof(Blocks.OAK_PLANKS.defaultBlockState(), Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setRenderType(RenderLayer.SOLID)
                .defaultRecipe()
                .setTabKey(tab)
                .build();
        this.addEntry(PLANKS_ROOFS);

        PLANKS_STEEP_ROOFS = SimpleEntrySet.builder(WoodType.class, "planks_steep_roof",
                        BlockInit.OAK_PLANKS_STEEP_ROOF, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new SteepRoof(Blocks.OAK_PLANKS.defaultBlockState(), Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setRenderType(RenderLayer.SOLID)
                .defaultRecipe()
                .setTabKey(tab)
                .build();
        this.addEntry(PLANKS_STEEP_ROOFS);

        PLANKS_TOP_ROOFS = SimpleEntrySet.builder(WoodType.class, "planks_top_roof",
                        BlockInit.OAK_PLANKS_TOP_ROOF, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new RoofTopNew(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setRenderType(RenderLayer.SOLID)
                .defaultRecipe()
                .setTabKey(tab)
                .build();
        this.addEntry(PLANKS_TOP_ROOFS);

        PLANKS_UPPER_LOWER_ROOFS = SimpleEntrySet.builder(WoodType.class, "planks_upper_lower_roof",
                        BlockInit.OAK_PLANKS_UPPER_LOWER_ROOF, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Lower(Blocks.OAK_PLANKS.defaultBlockState(), Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setRenderType(RenderLayer.SOLID)
                .defaultRecipe()
                .setTabKey(tab)
                .build();
        this.addEntry(PLANKS_UPPER_LOWER_ROOFS);

        PLANKS_UPPER_STEEP_ROOFS = SimpleEntrySet.builder(WoodType.class, "planks_upper_steep_roof",
                        BlockInit.OAK_PLANKS_UPPER_STEEP_ROOF, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Steep(Blocks.OAK_PLANKS.defaultBlockState(), Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setRenderType(RenderLayer.SOLID)
                .defaultRecipe()
                .setTabKey(tab)
                .build();
        this.addEntry(PLANKS_UPPER_STEEP_ROOFS);
    }
}
