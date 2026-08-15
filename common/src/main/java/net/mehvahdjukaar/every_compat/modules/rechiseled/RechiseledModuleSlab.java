package net.mehvahdjukaar.every_compat.modules.rechiseled;

import com.supermartijn642.rechiseled.blocks.RechiseledSlabBlock;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

import java.util.Objects;

// See RechiseledModuleAbstract's Supported Version
public class RechiseledModuleSlab extends RechiseledModuleAbstract {

    public final SimpleEntrySet<WoodType, Block> oak_planks_beams_slab, oak_planks_beams_slab_connecting;
    public final SimpleEntrySet<WoodType, Block> oak_planks_brick_pattern_slab, oak_planks_brick_pattern_slab_connecting;
    public final SimpleEntrySet<WoodType, Block> oak_planks_brick_paving_slab, oak_planks_brick_paving_slab_connecting;
    public final SimpleEntrySet<WoodType, Block> oak_planks_bricks_slab, oak_planks_bricks_slab_connecting;
    public final SimpleEntrySet<WoodType, Block> oak_planks_crate_slab, oak_planks_crate_slab_connecting;
    public final SimpleEntrySet<WoodType, Block> oak_planks_diagonal_stripes_slab, oak_planks_diagonal_stripes_slab_connecting;
    public final SimpleEntrySet<WoodType, Block> oak_planks_diagonal_tiles_slab, oak_planks_diagonal_tiles_slab_connecting;
    public final SimpleEntrySet<WoodType, Block> oak_planks_dotted_slab, oak_planks_dotted_slab_connecting;
    public final SimpleEntrySet<WoodType, Block> oak_planks_flooring_slab, oak_planks_flooring_slab_connecting;
    public final SimpleEntrySet<WoodType, Block> oak_planks_large_tiles_slab, oak_planks_large_tiles_slab_connecting;
    public final SimpleEntrySet<WoodType, Block> oak_planks_pattern_slab, oak_planks_pattern_slab_connecting;
    public final SimpleEntrySet<WoodType, Block> oak_planks_rotated_bricks_slab, oak_planks_rotated_bricks_slab_connecting;
    public final SimpleEntrySet<WoodType, Block> oak_planks_small_bricks_slab, oak_planks_small_bricks_slab_connecting;
    public final SimpleEntrySet<WoodType, Block> oak_planks_small_tiles_slab, oak_planks_small_tiles_slab_connecting;
    public final SimpleEntrySet<WoodType, Block> oak_planks_squares_slab, oak_planks_squares_slab_connecting;
    public final SimpleEntrySet<WoodType, Block> oak_planks_tiles_slab, oak_planks_tiles_slab_connecting;
    public final SimpleEntrySet<WoodType, Block> oak_planks_wavy_slab, oak_planks_wavy_slab_connecting;
    public final SimpleEntrySet<WoodType, Block> oak_planks_woven_slab, oak_planks_woven_slab_connecting;
    public final SimpleEntrySet<WoodType, Block> oak_planks_mosaic_slab;

    public RechiseledModuleSlab(String modId) {
        super(modId);
        setBlockType("Slab");

        oak_planks_beams_slab = SimpleEntrySet.builder(WoodType.class, "planks_beams_slab",
                        getModBlock("oak_planks_beams_slab"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledSlabBlock(false, Utils.copyPropertySafe(type.planks))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_beams")))
                //TEXTURES: planks_beams @ RechiseledModuleBlock
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_beams_slab);

        oak_planks_beams_slab_connecting = SimpleEntrySet.builder(WoodType.class, "planks_beams_slab_connecting",
                        getModBlock("oak_planks_beams_slab_connecting"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledSlabBlock(true, Utils.copyPropertySafe(type.planks))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_beams_connecting")))
                //TEXTURES: planks_beams_connecting @ RechiseledModuleBlock
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_beams_slab_connecting);

        oak_planks_brick_pattern_slab = SimpleEntrySet.builder(WoodType.class, "planks_brick_pattern_slab",
                        getModBlock("oak_planks_brick_pattern_slab"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledSlabBlock(false, Utils.copyPropertySafe(type.planks))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_brick_pattern")))
                //TEXTURES: planks_brick_pattern @ RechiseledModuleBlock
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_brick_pattern_slab);

        oak_planks_brick_pattern_slab_connecting = SimpleEntrySet.builder(WoodType.class, "planks_brick_pattern_slab_connecting",
                        getModBlock("oak_planks_brick_pattern_slab_connecting"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledSlabBlock(true, Utils.copyPropertySafe(type.planks))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_brick_pattern_connecting")))
                //TEXTURES: planks_brick_pattern_connecting @ RechiseledModuleBlock
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_brick_pattern_slab_connecting);

        oak_planks_brick_paving_slab = SimpleEntrySet.builder(WoodType.class, "planks_brick_paving_slab",
                        getModBlock("oak_planks_brick_paving_slab"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledSlabBlock(false, Utils.copyPropertySafe(type.planks))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_brick_paving")))
                //TEXTURES: planks_brick_paving @ RechiseledModuleBlock
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_brick_paving_slab);

        oak_planks_brick_paving_slab_connecting = SimpleEntrySet.builder(WoodType.class, "planks_brick_paving_slab_connecting",
                        getModBlock("oak_planks_brick_paving_slab_connecting"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledSlabBlock(true, Utils.copyPropertySafe(type.planks))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_brick_paving_connecting")))
                //TEXTURES: planks_brick_paving_connecting @ RechiseledModuleBlock
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_brick_paving_slab_connecting);

        oak_planks_bricks_slab = SimpleEntrySet.builder(WoodType.class, "planks_bricks_slab",
                        getModBlock("oak_planks_bricks_slab"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledSlabBlock(false, Utils.copyPropertySafe(type.planks))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_bricks")))
                //TEXTURES: planks_bricks @ RechiseledModuleBlock
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_bricks_slab);

        oak_planks_bricks_slab_connecting = SimpleEntrySet.builder(WoodType.class, "planks_bricks_slab_connecting",
                        getModBlock("oak_planks_bricks_slab_connecting"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledSlabBlock(true, Utils.copyPropertySafe(type.planks))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_bricks_connecting")))
                //TEXTURES: planks_bricks_connecting @ RechiseledModuleBlock
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_bricks_slab_connecting);

        oak_planks_crate_slab = SimpleEntrySet.builder(WoodType.class, "planks_crate_slab",
                        getModBlock("oak_planks_crate_slab"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledSlabBlock(false, Utils.copyPropertySafe(type.planks))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_crate")))
                //TEXTURES: planks_crate @ RechiseledModuleBlock
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_crate_slab);

        oak_planks_crate_slab_connecting = SimpleEntrySet.builder(WoodType.class, "planks_crate_slab_connecting",
                        getModBlock("oak_planks_crate_slab_connecting"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledSlabBlock(true, Utils.copyPropertySafe(type.planks))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_crate_connecting")))
                //TEXTURES: planks_crate_connecting @ RechiseledModuleBlock
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_crate_slab_connecting);

        oak_planks_diagonal_stripes_slab = SimpleEntrySet.builder(WoodType.class, "planks_diagonal_stripes_slab",
                        getModBlock("oak_planks_diagonal_stripes_slab"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledSlabBlock(false, Utils.copyPropertySafe(type.planks))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_diagonal_stripes")))
                //TEXTURES: planks_diagonal_stripes @ RechiseledModuleBlock
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_diagonal_stripes_slab);

        oak_planks_diagonal_stripes_slab_connecting = SimpleEntrySet.builder(WoodType.class, "planks_diagonal_stripes_slab_connecting",
                        getModBlock("oak_planks_diagonal_stripes_slab_connecting"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledSlabBlock(true, Utils.copyPropertySafe(type.planks))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_diagonal_stripes_connecting")))
                //TEXTURES: planks_diagonal_stripes_connecting @ RechiseledModuleBlock
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_diagonal_stripes_slab_connecting);

        oak_planks_diagonal_tiles_slab = SimpleEntrySet.builder(WoodType.class, "planks_diagonal_tiles_slab",
                        getModBlock("oak_planks_diagonal_tiles_slab"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledSlabBlock(false, Utils.copyPropertySafe(type.planks))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_diagonal_tiles")))
                //TEXTURES: planks_diagonal_tiles @ RechiseledModuleBlock
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_diagonal_tiles_slab);

        oak_planks_diagonal_tiles_slab_connecting = SimpleEntrySet.builder(WoodType.class, "planks_diagonal_tiles_slab_connecting",
                        getModBlock("oak_planks_diagonal_tiles_slab_connecting"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledSlabBlock(true, Utils.copyPropertySafe(type.planks))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_diagonal_tiles_connecting")))
                //TEXTURES: planks_diagonal_tiles_connecting @ RechiseledModuleBlock
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_diagonal_tiles_slab_connecting);

        oak_planks_dotted_slab = SimpleEntrySet.builder(WoodType.class, "planks_dotted_slab",
                        getModBlock("oak_planks_dotted_slab"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledSlabBlock(false, Utils.copyPropertySafe(type.planks))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_dotted")))
                //TEXTURES: planks_dotted @ RechiseledModuleBlock
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_dotted_slab);

        oak_planks_dotted_slab_connecting = SimpleEntrySet.builder(WoodType.class, "planks_dotted_slab_connecting",
                        getModBlock("oak_planks_dotted_slab_connecting"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledSlabBlock(true, Utils.copyPropertySafe(type.planks))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_dotted_connecting")))
                //TEXTURES: planks_dotted_connecting @ RechiseledModuleBlock
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_dotted_slab_connecting);

        oak_planks_flooring_slab = SimpleEntrySet.builder(WoodType.class, "planks_flooring_slab",
                        getModBlock("oak_planks_flooring_slab"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledSlabBlock(false, Utils.copyPropertySafe(type.planks))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_flooring")))
                //TEXTURES: planks_flooring @ RechiseledModuleBlock
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_flooring_slab);

        oak_planks_flooring_slab_connecting = SimpleEntrySet.builder(WoodType.class, "planks_flooring_slab_connecting",
                        getModBlock("oak_planks_flooring_slab_connecting"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledSlabBlock(true, Utils.copyPropertySafe(type.planks))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_flooring_connecting")))
                //TEXTURES: planks_flooring_connecting @ RechiseledModuleBlock
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_flooring_slab_connecting);

        oak_planks_large_tiles_slab = SimpleEntrySet.builder(WoodType.class, "planks_large_tiles_slab",
                        getModBlock("oak_planks_large_tiles_slab"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledSlabBlock(false, Utils.copyPropertySafe(type.planks))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_large_tiles")))
                //TEXTURES: planks_large_tiles @ RechiseledModuleBlock
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_large_tiles_slab);

        oak_planks_large_tiles_slab_connecting = SimpleEntrySet.builder(WoodType.class, "planks_large_tiles_slab_connecting",
                        getModBlock("oak_planks_large_tiles_slab_connecting"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledSlabBlock(true, Utils.copyPropertySafe(type.planks))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_large_tiles_connecting")))
                //TEXTURES: planks_large_tiles_connecting @ RechiseledModuleBlock
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_large_tiles_slab_connecting);

        oak_planks_pattern_slab = SimpleEntrySet.builder(WoodType.class, "planks_pattern_slab",
                        getModBlock("oak_planks_pattern_slab"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledSlabBlock(false, Utils.copyPropertySafe(type.planks))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_pattern")))
                //TEXTURES: planks_pattern @ RechiseledModuleBlock
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_pattern_slab);

        oak_planks_pattern_slab_connecting = SimpleEntrySet.builder(WoodType.class, "planks_pattern_slab_connecting",
                        getModBlock("oak_planks_pattern_slab_connecting"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledSlabBlock(true, Utils.copyPropertySafe(type.planks))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_pattern_connecting")))
                //TEXTURES: planks_pattern_connecting @ RechiseledModuleBlock
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_pattern_slab_connecting);

        oak_planks_rotated_bricks_slab = SimpleEntrySet.builder(WoodType.class, "planks_rotated_bricks_slab",
                        getModBlock("oak_planks_rotated_bricks_slab"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledSlabBlock(false, Utils.copyPropertySafe(type.planks))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_rotated_bricks")))
                //TEXTURES: planks_rotated_bricks @ RechiseledModuleBlock
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_rotated_bricks_slab);

        oak_planks_rotated_bricks_slab_connecting = SimpleEntrySet.builder(WoodType.class, "planks_rotated_bricks_slab_connecting",
                        getModBlock("oak_planks_rotated_bricks_slab_connecting"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledSlabBlock(true, Utils.copyPropertySafe(type.planks))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_rotated_bricks_connecting")))
                //TEXTURES: planks_rotated_bricks_connecting @ RechiseledModuleBlock
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_rotated_bricks_slab_connecting);

        oak_planks_small_bricks_slab = SimpleEntrySet.builder(WoodType.class, "planks_small_bricks_slab",
                        getModBlock("oak_planks_small_bricks_slab"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledSlabBlock(false, Utils.copyPropertySafe(type.planks))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_small_bricks")))
                //TEXTURES: planks_small_bricks @ RechiseledModuleBlock
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_small_bricks_slab);

        oak_planks_small_bricks_slab_connecting = SimpleEntrySet.builder(WoodType.class, "planks_small_bricks_slab_connecting",
                        getModBlock("oak_planks_small_bricks_slab_connecting"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledSlabBlock(true, Utils.copyPropertySafe(type.planks))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_small_bricks_connecting")))
                //TEXTURES: planks_small_bricks_connecting @ RechiseledModuleBlock
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_small_bricks_slab_connecting);

        oak_planks_small_tiles_slab = SimpleEntrySet.builder(WoodType.class, "planks_small_tiles_slab",
                        getModBlock("oak_planks_small_tiles_slab"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledSlabBlock(false, Utils.copyPropertySafe(type.planks))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_small_tiles")))
                //TEXTURES: planks_small_tiles @ RechiseledModuleBlock
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_small_tiles_slab);

        oak_planks_small_tiles_slab_connecting = SimpleEntrySet.builder(WoodType.class, "planks_small_tiles_slab_connecting",
                        getModBlock("oak_planks_small_tiles_slab_connecting"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledSlabBlock(true, Utils.copyPropertySafe(type.planks))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_small_tiles_connecting")))
                //TEXTURES: planks_small_tiles_connecting @ RechiseledModuleBlock
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_small_tiles_slab_connecting);

        oak_planks_squares_slab = SimpleEntrySet.builder(WoodType.class, "planks_squares_slab",
                        getModBlock("oak_planks_squares_slab"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledSlabBlock(false, Utils.copyPropertySafe(type.planks))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_squares")))
                //TEXTURES: planks_squares @ RechiseledModuleBlock
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_squares_slab);

        oak_planks_squares_slab_connecting = SimpleEntrySet.builder(WoodType.class, "planks_squares_slab_connecting",
                        getModBlock("oak_planks_squares_slab_connecting"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledSlabBlock(true, Utils.copyPropertySafe(type.planks))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_squares_connecting")))
                //TEXTURES: planks_squares_connecting @ RechiseledModuleBlock
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_squares_slab_connecting);

        oak_planks_tiles_slab = SimpleEntrySet.builder(WoodType.class, "planks_tiles_slab",
                        getModBlock("oak_planks_tiles_slab"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledSlabBlock(false, Utils.copyPropertySafe(type.planks))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_tiles")))
                //TEXTURES: planks_tiles @ RechiseledModuleBlock
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_tiles_slab);

        oak_planks_tiles_slab_connecting = SimpleEntrySet.builder(WoodType.class, "planks_tiles_slab_connecting",
                        getModBlock("oak_planks_tiles_slab_connecting"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledSlabBlock(true, Utils.copyPropertySafe(type.planks))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_tiles_connecting")))
                //TEXTURES: planks_tiles_connecting @ RechiseledModuleBlock
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_tiles_slab_connecting);

        oak_planks_wavy_slab = SimpleEntrySet.builder(WoodType.class, "planks_wavy_slab",
                        getModBlock("oak_planks_wavy_slab"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledSlabBlock(false, Utils.copyPropertySafe(type.planks))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_wavy")))
                //TEXTURES: planks_wavy @ RechiseledModuleBlock
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_wavy_slab);

        oak_planks_wavy_slab_connecting = SimpleEntrySet.builder(WoodType.class, "planks_wavy_slab_connecting",
                        getModBlock("oak_planks_wavy_slab_connecting"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledSlabBlock(true, Utils.copyPropertySafe(type.planks))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_wavy_connecting")))
                //TEXTURES: planks_wavy_connecting @ RechiseledModuleBlock
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_wavy_slab_connecting);

        oak_planks_woven_slab = SimpleEntrySet.builder(WoodType.class, "planks_woven_slab",
                        getModBlock("oak_planks_woven_slab"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledSlabBlock(false, Utils.copyPropertySafe(type.planks))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_woven")))
                //TEXTURES: planks_woven @ RechiseledModuleBlock
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_woven_slab);

        oak_planks_woven_slab_connecting = SimpleEntrySet.builder(WoodType.class, "planks_woven_slab_connecting",
                        getModBlock("oak_planks_woven_slab_connecting"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledSlabBlock(true, Utils.copyPropertySafe(type.planks))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_woven_connecting")))
                //TEXTURES: planks_woven_connecting @ RechiseledModuleBlock
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_woven_slab_connecting);


        oak_planks_mosaic_slab = SimpleEntrySet.builder(WoodType.class, "planks_mosaic_slab",
                        getModBlock("oak_planks_mosaic_slab"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledSlabBlock(false, Utils.copyPropertySafe(type.planks))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_mosaic")))
                //TEXTURES: planks_mosaic @ RechiseledModuleBlock
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_mosaic_slab);

    }

}