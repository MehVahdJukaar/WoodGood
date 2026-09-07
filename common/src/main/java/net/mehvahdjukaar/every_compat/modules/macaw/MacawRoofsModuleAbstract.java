package net.mehvahdjukaar.every_compat.modules.macaw;

import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

///SUPPORT: v2.2.4+
public abstract class MacawRoofsModuleAbstract extends SimpleModule {

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

    public MacawRoofsModuleAbstract(String modId) {
        super(modId, "mcr");
        ResourceLocation tab = (PlatHelper.getPlatform().isFabric())
                ? modRes("roofs")
                : modRes(modId);

        ATTIC_ROOFS = SimpleEntrySet.builder(WoodType.class, "attic_roof",
                        getModBlock("oak_attic_roof"), () -> VanillaWoodTypes.OAK,
                        this::newRoofGlass
                )
                //TEXTURES: log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .setTabKey(tab)
                .build();
        this.addEntry(ATTIC_ROOFS);

        LOWER_ROOFS = SimpleEntrySet.builder(WoodType.class, "lower_roof",
                        getModBlock("oak_lower_roof"), () -> VanillaWoodTypes.OAK,
                        this::newBaseRoof
                )
                //TEXTURES: log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setRenderType(RenderLayer.SOLID)
                .defaultRecipe()
                .setTabKey(tab)
                .build();
        this.addEntry(LOWER_ROOFS);

        ROOFS = SimpleEntrySet.builder(WoodType.class, "roof",
                        getModBlock("oak_roof"), () -> VanillaWoodTypes.OAK,
                        this::newBaseRoof
                )
                //TEXTURES: log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setRenderType(RenderLayer.SOLID)
                .defaultRecipe()
                .setTabKey(tab)
                .build();
        this.addEntry(ROOFS);

        STEEP_ROOFS = SimpleEntrySet.builder(WoodType.class, "steep_roof",
                        getModBlock("oak_steep_roof"), () -> VanillaWoodTypes.OAK,
                        this::newSteepRoof
                )
                //TEXTURES: log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setRenderType(RenderLayer.SOLID)
                .defaultRecipe()
                .setTabKey(tab)
                .build();
        this.addEntry(STEEP_ROOFS);

        TOP_ROOFS = SimpleEntrySet.builder(WoodType.class, "top_roof",
                        getModBlock("oak_top_roof"), () -> VanillaWoodTypes.OAK,
                        this::newRoofTopNew
                )
                //TEXTURES: log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setRenderType(RenderLayer.SOLID)
                .defaultRecipe()
                .setTabKey(tab)
                .build();
        this.addEntry(TOP_ROOFS);

        UPPER_LOWER_ROOFS = SimpleEntrySet.builder(WoodType.class, "upper_lower_roof",
                        getModBlock("oak_upper_lower_roof"), () -> VanillaWoodTypes.OAK,
                        this::newLower
                )
                //TEXTURES: log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setRenderType(RenderLayer.SOLID)
                .defaultRecipe()
                .setTabKey(tab)
                .build();
        this.addEntry(UPPER_LOWER_ROOFS);

        UPPER_STEEP_ROOFS = SimpleEntrySet.builder(WoodType.class, "upper_steep_roof",
                        getModBlock("oak_upper_steep_roof"), () -> VanillaWoodTypes.OAK,
                        this::newSteep
                )
                //TEXTURES: log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setRenderType(RenderLayer.SOLID)
                .defaultRecipe()
                .setTabKey(tab)
                .build();
        this.addEntry(UPPER_STEEP_ROOFS);

        PLANKS_ATTIC_ROOFS = SimpleEntrySet.builder(WoodType.class, "planks_attic_roof",
                        getModBlock("oak_planks_attic_roof"), () -> VanillaWoodTypes.OAK,
                        this::newRoofGlass
                )
                //TEXTURES: planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .setTabKey(tab)
                .build();
        this.addEntry(PLANKS_ATTIC_ROOFS);

        PLANKS_LOWER_ROOFS = SimpleEntrySet.builder(WoodType.class, "planks_lower_roof",
                        getModBlock("oak_planks_lower_roof"), () -> VanillaWoodTypes.OAK,
                        this::newBaseRoof
                )
                //TEXTURES: planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setRenderType(RenderLayer.SOLID)
                .defaultRecipe()
                .setTabKey(tab)
                .build();
        this.addEntry(PLANKS_LOWER_ROOFS);

        PLANKS_ROOFS = SimpleEntrySet.builder(WoodType.class, "planks_roof",
                        getModBlock("oak_planks_roof"), () -> VanillaWoodTypes.OAK,
                        this::newBaseRoof
                )
                //TEXTURES: planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setRenderType(RenderLayer.SOLID)
                .defaultRecipe()
                .setTabKey(tab)
                .build();
        this.addEntry(PLANKS_ROOFS);

        PLANKS_STEEP_ROOFS = SimpleEntrySet.builder(WoodType.class, "planks_steep_roof",
                        getModBlock("oak_planks_steep_roof"), () -> VanillaWoodTypes.OAK,
                        this::newSteepRoof
                )
                //TEXTURES: planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setRenderType(RenderLayer.SOLID)
                .defaultRecipe()
                .setTabKey(tab)
                .build();
        this.addEntry(PLANKS_STEEP_ROOFS);

        PLANKS_TOP_ROOFS = SimpleEntrySet.builder(WoodType.class, "planks_top_roof",
                        getModBlock("oak_planks_top_roof"), () -> VanillaWoodTypes.OAK,
                        this::newRoofTopNew
                )
                //TEXTURES: planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setRenderType(RenderLayer.SOLID)
                .defaultRecipe()
                .setTabKey(tab)
                .build();
        this.addEntry(PLANKS_TOP_ROOFS);

        PLANKS_UPPER_LOWER_ROOFS = SimpleEntrySet.builder(WoodType.class, "planks_upper_lower_roof",
                        getModBlock("oak_planks_upper_lower_roof"), () -> VanillaWoodTypes.OAK,
                        this::newLower
                )
                //TEXTURES: planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setRenderType(RenderLayer.SOLID)
                .defaultRecipe()
                .setTabKey(tab)
                .build();
        this.addEntry(PLANKS_UPPER_LOWER_ROOFS);

        PLANKS_UPPER_STEEP_ROOFS = SimpleEntrySet.builder(WoodType.class, "planks_upper_steep_roof",
                        getModBlock("oak_planks_upper_steep_roof"), () -> VanillaWoodTypes.OAK,
                        this::newSteep
                )
                //TEXTURES: planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setRenderType(RenderLayer.SOLID)
                .defaultRecipe()
                .setTabKey(tab)
                .build();
        this.addEntry(PLANKS_UPPER_STEEP_ROOFS);
    }

    public abstract Block newRoofGlass(WoodType woodType);
    public abstract Block newBaseRoof(WoodType woodType);
    public abstract Block newSteepRoof(WoodType woodType);
    public abstract Block newRoofTopNew(WoodType woodType);
    public abstract Block newLower(WoodType woodType);
    public abstract Block newSteep(WoodType woodType);

    public BlockBehaviour.Properties copyStandardProperties(WoodType woodType) {
        return BlockBehaviour.Properties.of()
                .mapColor(woodType.getColor())
                .strength(2.0F, 2.3F)
                .sound(woodType.getSound());
    }
}
