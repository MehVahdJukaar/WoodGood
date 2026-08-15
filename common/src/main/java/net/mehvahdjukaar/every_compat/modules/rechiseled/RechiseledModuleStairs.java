package net.mehvahdjukaar.every_compat.modules.rechiseled;

import com.supermartijn642.rechiseled.blocks.RechiseledStairBlock;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

import java.util.Objects;

// See RechiseledModuleAbstract's Supported Version
public class RechiseledModuleStairs extends RechiseledModuleAbstract {

    public final SimpleEntrySet<WoodType, Block> oak_planks_beams_stairs, oak_planks_beams_stairs_connecting;
    public final SimpleEntrySet<WoodType, Block> oak_planks_brick_pattern_stairs, oak_planks_brick_pattern_stairs_connecting;
    public final SimpleEntrySet<WoodType, Block> oak_planks_brick_paving_stairs, oak_planks_brick_paving_stairs_connecting;
    public final SimpleEntrySet<WoodType, Block> oak_planks_bricks_stairs, oak_planks_bricks_stairs_connecting;
    public final SimpleEntrySet<WoodType, Block> oak_planks_crate_stairs, oak_planks_crate_stairs_connecting;
    public final SimpleEntrySet<WoodType, Block> oak_planks_diagonal_stripes_stairs, oak_planks_diagonal_stripes_stairs_connecting;
    public final SimpleEntrySet<WoodType, Block> oak_planks_diagonal_tiles_stairs, oak_planks_diagonal_tiles_stairs_connecting;
    public final SimpleEntrySet<WoodType, Block> oak_planks_dotted_stairs, oak_planks_dotted_stairs_connecting;
    public final SimpleEntrySet<WoodType, Block> oak_planks_flooring_stairs, oak_planks_flooring_stairs_connecting;
    public final SimpleEntrySet<WoodType, Block> oak_planks_large_tiles_stairs, oak_planks_large_tiles_stairs_connecting;
    public final SimpleEntrySet<WoodType, Block> oak_planks_pattern_stairs, oak_planks_pattern_stairs_connecting;
    public final SimpleEntrySet<WoodType, Block> oak_planks_rotated_bricks_stairs, oak_planks_rotated_bricks_stairs_connecting;
    public final SimpleEntrySet<WoodType, Block> oak_planks_small_bricks_stairs, oak_planks_small_bricks_stairs_connecting;
    public final SimpleEntrySet<WoodType, Block> oak_planks_small_tiles_stairs, oak_planks_small_tiles_stairs_connecting;
    public final SimpleEntrySet<WoodType, Block> oak_planks_squares_stairs, oak_planks_squares_stairs_connecting;
    public final SimpleEntrySet<WoodType, Block> oak_planks_tiles_stairs, oak_planks_tiles_stairs_connecting;
    public final SimpleEntrySet<WoodType, Block> oak_planks_wavy_stairs, oak_planks_wavy_stairs_connecting;
    public final SimpleEntrySet<WoodType, Block> oak_planks_woven_stairs, oak_planks_woven_stairs_connecting;
    public final SimpleEntrySet<WoodType, Block> oak_planks_mosaic_stairs;

    public RechiseledModuleStairs(String modId) {
        super(modId);
        setBlockType("Stairs");
        
        oak_planks_beams_stairs = SimpleEntrySet.builder(WoodType.class, "planks_beams_stairs",
                        getModBlock("oak_planks_beams_stairs"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledStairBlock(false,
                                getParentBlockState(type, "planks_beams"),
                                Utils.copyPropertySafe(type.planks)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_beams")))
                .addModelTransform(m -> m.addModifier((s, blockId, woodType) ->
                        s.replace("\"rechiseled:oak_planks_beams_stairs\"", "\"" + blockId.toString() + "\"")
                                .replaceAll("\"rechiseled:oak_(\\w+)\"",
                                        "\"" + createStandardId(woodType, "", "") + "_$1\"")

                ))
                //TEXTURES: planks_beams @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_beams_stairs);

        oak_planks_beams_stairs_connecting = SimpleEntrySet.builder(WoodType.class, "planks_beams_stairs_connecting",
                        getModBlock("oak_planks_beams_stairs_connecting"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledStairBlock(true,
                                getParentBlockState(type, "planks_beams_connecting"),
                                Utils.copyPropertySafe(type.planks)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_beams_connecting")))
                .addModelTransform(m -> m.addModifier((s, blockId, woodType) ->
                        s.replace("\"rechiseled:oak_planks_beams_stairs_connecting\"", "\"" + blockId.toString() + "\"")
                                .replaceAll("\"rechiseled:oak_(\\w+)\"",
                                        "\"" + createStandardId(woodType, "", "") + "_$1\"")

                ))
                //TEXTURES: planks_beams_connecting @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_beams_stairs_connecting);

        oak_planks_brick_pattern_stairs = SimpleEntrySet.builder(WoodType.class, "planks_brick_pattern_stairs",
                        getModBlock("oak_planks_brick_pattern_stairs"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledStairBlock(false,
                                getParentBlockState(type, "planks_brick_pattern"),
                                Utils.copyPropertySafe(type.planks)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_brick_pattern")))
                .addModelTransform(m -> m.addModifier((s, blockId, woodType) ->
                        s.replace("\"rechiseled:oak_planks_brick_pattern_stairs\"", "\"" + blockId.toString() + "\"")
                                .replaceAll("\"rechiseled:oak_(\\w+)\"",
                                        "\"" + createStandardId(woodType, "", "") + "_$1\"")

                ))
                //TEXTURES: planks_brick_pattern @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_brick_pattern_stairs);

        oak_planks_brick_pattern_stairs_connecting = SimpleEntrySet.builder(WoodType.class, "planks_brick_pattern_stairs_connecting",
                        getModBlock("oak_planks_brick_pattern_stairs_connecting"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledStairBlock(true,
                                getParentBlockState(type, "planks_brick_pattern_connecting"),
                                Utils.copyPropertySafe(type.planks)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_brick_pattern_connecting")))
                .addModelTransform(m -> m.addModifier((s, blockId, woodType) ->
                        s.replace("\"rechiseled:oak_planks_brick_pattern_stairs_connecting\"", "\"" + blockId.toString() + "\"")
                                .replaceAll("\"rechiseled:oak_(\\w+)\"",
                                        "\"" + createStandardId(woodType, "", "") + "_$1\"")

                ))
                //TEXTURES: planks_brick_pattern_connecting @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_brick_pattern_stairs_connecting);

        oak_planks_brick_paving_stairs = SimpleEntrySet.builder(WoodType.class, "planks_brick_paving_stairs",
                        getModBlock("oak_planks_brick_paving_stairs"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledStairBlock(false,
                                getParentBlockState(type, "planks_brick_paving"),
                                Utils.copyPropertySafe(type.planks)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_brick_paving")))
                .addModelTransform(m -> m.addModifier((s, blockId, woodType) ->
                        s.replace("\"rechiseled:oak_planks_brick_paving_stairs\"", "\"" + blockId.toString() + "\"")
                                .replaceAll("\"rechiseled:oak_(\\w+)\"",
                                        "\"" + createStandardId(woodType, "", "") + "_$1\"")

                ))
                //TEXTURES: planks_brick_paving @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_brick_paving_stairs);

        oak_planks_brick_paving_stairs_connecting = SimpleEntrySet.builder(WoodType.class, "planks_brick_paving_stairs_connecting",
                        getModBlock("oak_planks_brick_paving_stairs_connecting"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledStairBlock(true,
                                getParentBlockState(type, "planks_brick_paving_connecting"),
                                Utils.copyPropertySafe(type.planks)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_brick_paving_connecting")))
                .addModelTransform(m -> m.addModifier((s, blockId, woodType) ->
                        s.replace("\"rechiseled:oak_planks_brick_paving_stairs_connecting\"", "\"" + blockId.toString() + "\"")
                                .replaceAll("\"rechiseled:oak_(\\w+)\"",
                                        "\"" + createStandardId(woodType, "", "") + "_$1\"")

                ))
                //TEXTURES: planks_brick_paving_connecting @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_brick_paving_stairs_connecting);

        oak_planks_bricks_stairs = SimpleEntrySet.builder(WoodType.class, "planks_bricks_stairs",
                        getModBlock("oak_planks_bricks_stairs"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledStairBlock(false,
                                getParentBlockState(type, "planks_bricks"),
                                Utils.copyPropertySafe(type.planks)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_bricks")))
                .addModelTransform(m -> m.addModifier((s, blockId, woodType) ->
                        s.replace("\"rechiseled:oak_planks_bricks_stairs\"", "\"" + blockId.toString() + "\"")
                                .replaceAll("\"rechiseled:oak_(\\w+)\"",
                                        "\"" + createStandardId(woodType, "", "") + "_$1\"")

                ))
                //TEXTURES: planks_bricks @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_bricks_stairs);

        oak_planks_bricks_stairs_connecting = SimpleEntrySet.builder(WoodType.class, "planks_bricks_stairs_connecting",
                        getModBlock("oak_planks_bricks_stairs_connecting"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledStairBlock(true,
                                getParentBlockState(type, "planks_bricks_connecting"),
                                Utils.copyPropertySafe(type.planks)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_bricks_connecting")))
                .addModelTransform(m -> m.addModifier((s, blockId, woodType) ->
                        s.replace("\"rechiseled:oak_planks_bricks_stairs_connecting\"", "\"" + blockId.toString() + "\"")
                                .replaceAll("\"rechiseled:oak_(\\w+)\"",
                                        "\"" + createStandardId(woodType, "", "") + "_$1\"")

                ))
                //TEXTURES: planks_bricks_connecting @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_bricks_stairs_connecting);

        oak_planks_crate_stairs = SimpleEntrySet.builder(WoodType.class, "planks_crate_stairs",
                        getModBlock("oak_planks_crate_stairs"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledStairBlock(false,
                                getParentBlockState(type, "planks_crate"),
                                Utils.copyPropertySafe(type.planks)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_crate")))
                .addModelTransform(m -> m.addModifier((s, blockId, woodType) ->
                        s.replace("\"rechiseled:oak_planks_crate_stairs\"", "\"" + blockId.toString() + "\"")
                                .replaceAll("\"rechiseled:oak_(\\w+)\"",
                                        "\"" + createStandardId(woodType, "", "") + "_$1\"")

                ))
                //TEXTURES: planks_crate @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_crate_stairs);

        oak_planks_crate_stairs_connecting = SimpleEntrySet.builder(WoodType.class, "planks_crate_stairs_connecting",
                        getModBlock("oak_planks_crate_stairs_connecting"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledStairBlock(true,
                                getParentBlockState(type, "planks_crate_connecting"),
                                Utils.copyPropertySafe(type.planks)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_crate_connecting")))
                .addModelTransform(m -> m.addModifier((s, blockId, woodType) ->
                        s.replace("\"rechiseled:oak_planks_crate_stairs_connecting\"", "\"" + blockId.toString() + "\"")
                                .replaceAll("\"rechiseled:oak_(\\w+)\"",
                                        "\"" + createStandardId(woodType, "", "") + "_$1\"")

                ))
                //TEXTURES: planks_crate_connecting @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_crate_stairs_connecting);

        oak_planks_diagonal_stripes_stairs = SimpleEntrySet.builder(WoodType.class, "planks_diagonal_stripes_stairs",
                        getModBlock("oak_planks_diagonal_stripes_stairs"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledStairBlock(false,
                                getParentBlockState(type, "planks_diagonal_stripes"),
                                Utils.copyPropertySafe(type.planks)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_diagonal_stripes")))
                .addModelTransform(m -> m.addModifier((s, blockId, woodType) ->
                        s.replace("\"rechiseled:oak_planks_diagonal_stripes_stairs\"", "\"" + blockId.toString() + "\"")
                                .replaceAll("\"rechiseled:oak_(\\w+)\"",
                                        "\"" + createStandardId(woodType, "", "") + "_$1\"")

                ))
                //TEXTURES: planks_diagonal_stripes @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_diagonal_stripes_stairs);

        oak_planks_diagonal_stripes_stairs_connecting = SimpleEntrySet.builder(WoodType.class, "planks_diagonal_stripes_stairs_connecting",
                        getModBlock("oak_planks_diagonal_stripes_stairs_connecting"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledStairBlock(true,
                                getParentBlockState(type, "planks_diagonal_stripes_connecting"),
                                Utils.copyPropertySafe(type.planks)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_diagonal_stripes_connecting")))
                .addModelTransform(m -> m.addModifier((s, blockId, woodType) ->
                        s.replace("\"rechiseled:oak_planks_diagonal_stripes_stairs_connecting\"", "\"" + blockId.toString() + "\"")
                                .replaceAll("\"rechiseled:oak_(\\w+)\"",
                                        "\"" + createStandardId(woodType, "", "") + "_$1\"")

                ))
                //TEXTURES: planks_diagonal_stripes_connecting @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_diagonal_stripes_stairs_connecting);

        oak_planks_diagonal_tiles_stairs = SimpleEntrySet.builder(WoodType.class, "planks_diagonal_tiles_stairs",
                        getModBlock("oak_planks_diagonal_tiles_stairs"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledStairBlock(false,
                                getParentBlockState(type, "planks_diagonal_tiles"),
                                Utils.copyPropertySafe(type.planks)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_diagonal_tiles")))
                .addModelTransform(m -> m.addModifier((s, blockId, woodType) ->
                        s.replace("\"rechiseled:oak_planks_diagonal_tiles_stairs\"", "\"" + blockId.toString() + "\"")
                                .replaceAll("\"rechiseled:oak_(\\w+)\"",
                                        "\"" + createStandardId(woodType, "", "") + "_$1\"")

                ))
                //TEXTURES: planks_diagonal_tiles @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_diagonal_tiles_stairs);

        oak_planks_diagonal_tiles_stairs_connecting = SimpleEntrySet.builder(WoodType.class, "planks_diagonal_tiles_stairs_connecting",
                        getModBlock("oak_planks_diagonal_tiles_stairs_connecting"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledStairBlock(true,
                                getParentBlockState(type, "planks_diagonal_tiles_connecting"),
                                Utils.copyPropertySafe(type.planks)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_diagonal_tiles_connecting")))
                .addModelTransform(m -> m.addModifier((s, blockId, woodType) ->
                        s.replace("\"rechiseled:oak_planks_diagonal_tiles_stairs_connecting\"", "\"" + blockId.toString() + "\"")
                                .replaceAll("\"rechiseled:oak_(\\w+)\"",
                                        "\"" + createStandardId(woodType, "", "") + "_$1\"")

                ))
                //TEXTURES: planks_diagonal_tiles_connecting @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_diagonal_tiles_stairs_connecting);

        oak_planks_dotted_stairs = SimpleEntrySet.builder(WoodType.class, "planks_dotted_stairs",
                        getModBlock("oak_planks_dotted_stairs"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledStairBlock(false,
                                getParentBlockState(type, "planks_dotted"),
                                Utils.copyPropertySafe(type.planks)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_dotted")))
                .addModelTransform(m -> m.addModifier((s, blockId, woodType) ->
                        s.replace("\"rechiseled:oak_planks_dotted_stairs\"", "\"" + blockId.toString() + "\"")
                                .replaceAll("\"rechiseled:oak_(\\w+)\"",
                                        "\"" + createStandardId(woodType, "", "") + "_$1\"")

                ))
                //TEXTURES: planks_dotted @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_dotted_stairs);

        oak_planks_dotted_stairs_connecting = SimpleEntrySet.builder(WoodType.class, "planks_dotted_stairs_connecting",
                        getModBlock("oak_planks_dotted_stairs_connecting"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledStairBlock(true,
                                getParentBlockState(type, "planks_dotted_connecting"),
                                Utils.copyPropertySafe(type.planks)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_dotted_connecting")))
                .addModelTransform(m -> m.addModifier((s, blockId, woodType) ->
                        s.replace("\"rechiseled:oak_planks_dotted_stairs_connecting\"", "\"" + blockId.toString() + "\"")
                                .replaceAll("\"rechiseled:oak_(\\w+)\"",
                                        "\"" + createStandardId(woodType, "", "") + "_$1\"")

                ))
                //TEXTURES: planks_dotted_connecting @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_dotted_stairs_connecting);

        oak_planks_flooring_stairs = SimpleEntrySet.builder(WoodType.class, "planks_flooring_stairs",
                        getModBlock("oak_planks_flooring_stairs"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledStairBlock(false,
                                getParentBlockState(type, "planks_flooring"),
                                Utils.copyPropertySafe(type.planks)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_flooring")))
                .addModelTransform(m -> m.addModifier((s, blockId, woodType) ->
                        s.replace("\"rechiseled:oak_planks_flooring_stairs\"", "\"" + blockId.toString() + "\"")
                                .replaceAll("\"rechiseled:oak_(\\w+)\"",
                                        "\"" + createStandardId(woodType, "", "") + "_$1\"")

                ))
                //TEXTURES: planks_flooring @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_flooring_stairs);

        oak_planks_flooring_stairs_connecting = SimpleEntrySet.builder(WoodType.class, "planks_flooring_stairs_connecting",
                        getModBlock("oak_planks_flooring_stairs_connecting"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledStairBlock(true,
                                getParentBlockState(type, "planks_flooring_connecting"),
                                Utils.copyPropertySafe(type.planks)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_flooring_connecting")))
                .addModelTransform(m -> m.addModifier((s, blockId, woodType) ->
                        s.replace("\"rechiseled:oak_planks_flooring_stairs_connecting\"", "\"" + blockId.toString() + "\"")
                                .replaceAll("\"rechiseled:oak_(\\w+)\"",
                                        "\"" + createStandardId(woodType, "", "") + "_$1\"")

                ))
                //TEXTURES: planks_flooring_connecting @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_flooring_stairs_connecting);

        oak_planks_large_tiles_stairs = SimpleEntrySet.builder(WoodType.class, "planks_large_tiles_stairs",
                        getModBlock("oak_planks_large_tiles_stairs"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledStairBlock(false,
                                getParentBlockState(type, "planks_large_tiles"),
                                Utils.copyPropertySafe(type.planks)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_large_tiles")))
                .addModelTransform(m -> m.addModifier((s, blockId, woodType) ->
                        s.replace("\"rechiseled:oak_planks_large_tiles_stairs\"", "\"" + blockId.toString() + "\"")
                                .replaceAll("\"rechiseled:oak_(\\w+)\"",
                                        "\"" + createStandardId(woodType, "", "") + "_$1\"")

                ))
                //TEXTURES: planks_large_tiles @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_large_tiles_stairs);

        oak_planks_large_tiles_stairs_connecting = SimpleEntrySet.builder(WoodType.class, "planks_large_tiles_stairs_connecting",
                        getModBlock("oak_planks_large_tiles_stairs_connecting"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledStairBlock(true,
                                getParentBlockState(type, "planks_large_tiles_connecting"),
                                Utils.copyPropertySafe(type.planks)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_large_tiles_connecting")))
                .addModelTransform(m -> m.addModifier((s, blockId, woodType) ->
                        s.replace("\"rechiseled:oak_planks_large_tiles_stairs_connecting\"", "\"" + blockId.toString() + "\"")
                                .replaceAll("\"rechiseled:oak_(\\w+)\"",
                                        "\"" + createStandardId(woodType, "", "") + "_$1\"")

                ))
                //TEXTURES: planks_large_tiles_connecting @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_large_tiles_stairs_connecting);

        oak_planks_pattern_stairs = SimpleEntrySet.builder(WoodType.class, "planks_pattern_stairs",
                        getModBlock("oak_planks_pattern_stairs"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledStairBlock(false,
                                getParentBlockState(type, "planks_pattern"),
                                Utils.copyPropertySafe(type.planks)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_pattern")))
                .addModelTransform(m -> m.addModifier((s, blockId, woodType) ->
                        s.replace("\"rechiseled:oak_planks_pattern_stairs\"", "\"" + blockId.toString() + "\"")
                                .replaceAll("\"rechiseled:oak_(\\w+)\"",
                                        "\"" + createStandardId(woodType, "", "") + "_$1\"")

                ))
                //TEXTURES: planks_pattern @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_pattern_stairs);

        oak_planks_pattern_stairs_connecting = SimpleEntrySet.builder(WoodType.class, "planks_pattern_stairs_connecting",
                        getModBlock("oak_planks_pattern_stairs_connecting"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledStairBlock(true,
                                getParentBlockState(type, "planks_pattern_connecting"),
                                Utils.copyPropertySafe(type.planks)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_pattern_connecting")))
                .addModelTransform(m -> m.addModifier((s, blockId, woodType) ->
                        s.replace("\"rechiseled:oak_planks_pattern_stairs_connecting\"", "\"" + blockId.toString() + "\"")
                                .replaceAll("\"rechiseled:oak_(\\w+)\"",
                                        "\"" + createStandardId(woodType, "", "") + "_$1\"")

                ))
                //TEXTURES: planks_pattern_connecting @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_pattern_stairs_connecting);

        oak_planks_rotated_bricks_stairs = SimpleEntrySet.builder(WoodType.class, "planks_rotated_bricks_stairs",
                        getModBlock("oak_planks_rotated_bricks_stairs"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledStairBlock(false,
                                getParentBlockState(type, "planks_rotated_bricks"),
                                Utils.copyPropertySafe(type.planks)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_rotated_bricks")))
                .addModelTransform(m -> m.addModifier((s, blockId, woodType) ->
                        s.replace("\"rechiseled:oak_planks_rotated_bricks_stairs\"", "\"" + blockId.toString() + "\"")
                                .replaceAll("\"rechiseled:oak_(\\w+)\"",
                                        "\"" + createStandardId(woodType, "", "") + "_$1\"")

                ))
                //TEXTURES: planks_rotated_bricks @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_rotated_bricks_stairs);

        oak_planks_rotated_bricks_stairs_connecting = SimpleEntrySet.builder(WoodType.class, "planks_rotated_bricks_stairs_connecting",
                        getModBlock("oak_planks_rotated_bricks_stairs_connecting"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledStairBlock(true,
                                getParentBlockState(type, "planks_rotated_bricks_connecting"),
                                Utils.copyPropertySafe(type.planks)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_rotated_bricks_connecting")))
                .addModelTransform(m -> m.addModifier((s, blockId, woodType) ->
                        s.replace("\"rechiseled:oak_planks_rotated_bricks_stairs_connecting\"", "\"" + blockId.toString() + "\"")
                                .replaceAll("\"rechiseled:oak_(\\w+)\"",
                                        "\"" + createStandardId(woodType, "", "") + "_$1\"")

                ))
                //TEXTURES: planks_rotated_bricks_connecting @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_rotated_bricks_stairs_connecting);

        oak_planks_small_bricks_stairs = SimpleEntrySet.builder(WoodType.class, "planks_small_bricks_stairs",
                        getModBlock("oak_planks_small_bricks_stairs"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledStairBlock(false,
                                getParentBlockState(type, "planks_small_bricks"),
                                Utils.copyPropertySafe(type.planks)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_small_bricks")))
                .addModelTransform(m -> m.addModifier((s, blockId, woodType) ->
                        s.replace("\"rechiseled:oak_planks_small_bricks_stairs\"", "\"" + blockId.toString() + "\"")
                                .replaceAll("\"rechiseled:oak_(\\w+)\"",
                                        "\"" + createStandardId(woodType, "", "") + "_$1\"")

                ))
                //TEXTURES: planks_small_bricks @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_small_bricks_stairs);

        oak_planks_small_bricks_stairs_connecting = SimpleEntrySet.builder(WoodType.class, "planks_small_bricks_stairs_connecting",
                        getModBlock("oak_planks_small_bricks_stairs_connecting"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledStairBlock(true,
                                getParentBlockState(type, "planks_small_bricks_connecting"),
                                Utils.copyPropertySafe(type.planks)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_small_bricks_connecting")))
                .addModelTransform(m -> m.addModifier((s, blockId, woodType) ->
                        s.replace("\"rechiseled:oak_planks_small_bricks_stairs_connecting\"", "\"" + blockId.toString() + "\"")
                                .replaceAll("\"rechiseled:oak_(\\w+)\"",
                                        "\"" + createStandardId(woodType, "", "") + "_$1\"")

                ))
                //TEXTURES: planks_small_bricks_connecting @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_small_bricks_stairs_connecting);

        oak_planks_small_tiles_stairs = SimpleEntrySet.builder(WoodType.class, "planks_small_tiles_stairs",
                        getModBlock("oak_planks_small_tiles_stairs"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledStairBlock(false,
                                getParentBlockState(type, "planks_small_tiles"),
                                Utils.copyPropertySafe(type.planks)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_small_tiles")))
                .addModelTransform(m -> m.addModifier((s, blockId, woodType) ->
                        s.replace("\"rechiseled:oak_planks_small_tiles_stairs\"", "\"" + blockId.toString() + "\"")
                                .replaceAll("\"rechiseled:oak_(\\w+)\"",
                                        "\"" + createStandardId(woodType, "", "") + "_$1\"")

                ))
                //TEXTURES: planks_small_tiles @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_small_tiles_stairs);

        oak_planks_small_tiles_stairs_connecting = SimpleEntrySet.builder(WoodType.class, "planks_small_tiles_stairs_connecting",
                        getModBlock("oak_planks_small_tiles_stairs_connecting"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledStairBlock(true,
                                getParentBlockState(type, "planks_small_tiles_connecting"),
                                Utils.copyPropertySafe(type.planks)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_small_tiles_connecting")))
                .addModelTransform(m -> m.addModifier((s, blockId, woodType) ->
                        s.replace("\"rechiseled:oak_planks_small_tiles_stairs_connecting\"", "\"" + blockId.toString() + "\"")
                                .replaceAll("\"rechiseled:oak_(\\w+)\"",
                                        "\"" + createStandardId(woodType, "", "") + "_$1\"")

                ))
                //TEXTURES: planks_small_tiles_connecting @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_small_tiles_stairs_connecting);

        oak_planks_squares_stairs = SimpleEntrySet.builder(WoodType.class, "planks_squares_stairs",
                        getModBlock("oak_planks_squares_stairs"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledStairBlock(false,
                                getParentBlockState(type, "planks_squares"),
                                Utils.copyPropertySafe(type.planks)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_squares")))
                .addModelTransform(m -> m.addModifier((s, blockId, woodType) ->
                        s.replace("\"rechiseled:oak_planks_squares_stairs\"", "\"" + blockId.toString() + "\"")
                                .replaceAll("\"rechiseled:oak_(\\w+)\"",
                                        "\"" + createStandardId(woodType, "", "") + "_$1\"")

                ))
                //TEXTURES: planks_squares @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_squares_stairs);

        oak_planks_squares_stairs_connecting = SimpleEntrySet.builder(WoodType.class, "planks_squares_stairs_connecting",
                        getModBlock("oak_planks_squares_stairs_connecting"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledStairBlock(true,
                                getParentBlockState(type, "planks_squares_connecting"),
                                Utils.copyPropertySafe(type.planks)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_squares_connecting")))
                .addModelTransform(m -> m.addModifier((s, blockId, woodType) ->
                        s.replace("\"rechiseled:oak_planks_squares_stairs_connecting\"", "\"" + blockId.toString() + "\"")
                                .replaceAll("\"rechiseled:oak_(\\w+)\"",
                                        "\"" + createStandardId(woodType, "", "") + "_$1\"")

                ))
                //TEXTURES: planks_squares_connecting @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_squares_stairs_connecting);

        oak_planks_tiles_stairs = SimpleEntrySet.builder(WoodType.class, "planks_tiles_stairs",
                        getModBlock("oak_planks_tiles_stairs"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledStairBlock(false,
                                getParentBlockState(type, "planks_tiles"),
                                Utils.copyPropertySafe(type.planks)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_tiles")))
                .addModelTransform(m -> m.addModifier((s, blockId, woodType) ->
                        s.replace("\"rechiseled:oak_planks_tiles_stairs\"", "\"" + blockId.toString() + "\"")
                                .replaceAll("\"rechiseled:oak_(\\w+)\"",
                                        "\"" + createStandardId(woodType, "", "") + "_$1\"")

                ))
                //TEXTURES: planks_tiles @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_tiles_stairs);

        oak_planks_tiles_stairs_connecting = SimpleEntrySet.builder(WoodType.class, "planks_tiles_stairs_connecting",
                        getModBlock("oak_planks_tiles_stairs_connecting"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledStairBlock(true,
                                getParentBlockState(type, "planks_tiles_connecting"),
                                Utils.copyPropertySafe(type.planks)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_tiles_connecting")))
                .addModelTransform(m -> m.addModifier((s, blockId, woodType) ->
                        s.replace("\"rechiseled:oak_planks_tiles_stairs_connecting\"", "\"" + blockId.toString() + "\"")
                                .replaceAll("\"rechiseled:oak_(\\w+)\"",
                                        "\"" + createStandardId(woodType, "", "") + "_$1\"")

                ))
                //TEXTURES: planks_tiles_connecting @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_tiles_stairs_connecting);

        oak_planks_wavy_stairs = SimpleEntrySet.builder(WoodType.class, "planks_wavy_stairs",
                        getModBlock("oak_planks_wavy_stairs"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledStairBlock(false,
                                getParentBlockState(type, "planks_wavy"),
                                Utils.copyPropertySafe(type.planks)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_wavy")))
                .addModelTransform(m -> m.addModifier((s, blockId, woodType) ->
                        s.replace("\"rechiseled:oak_planks_wavy_stairs\"", "\"" + blockId.toString() + "\"")
                                .replaceAll("\"rechiseled:oak_(\\w+)\"",
                                        "\"" + createStandardId(woodType, "", "") + "_$1\"")

                ))
                //TEXTURES: planks_wavy @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_wavy_stairs);

        oak_planks_wavy_stairs_connecting = SimpleEntrySet.builder(WoodType.class, "planks_wavy_stairs_connecting",
                        getModBlock("oak_planks_wavy_stairs_connecting"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledStairBlock(true,
                                getParentBlockState(type, "planks_wavy_connecting"),
                                Utils.copyPropertySafe(type.planks)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_wavy_connecting")))
                .addModelTransform(m -> m.addModifier((s, blockId, woodType) ->
                        s.replace("\"rechiseled:oak_planks_wavy_stairs_connecting\"", "\"" + blockId.toString() + "\"")
                                .replaceAll("\"rechiseled:oak_(\\w+)\"",
                                        "\"" + createStandardId(woodType, "", "") + "_$1\"")

                ))
                //TEXTURES: planks_wavy_connecting @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_wavy_stairs_connecting);

        oak_planks_woven_stairs = SimpleEntrySet.builder(WoodType.class, "planks_woven_stairs",
                        getModBlock("oak_planks_woven_stairs"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledStairBlock(false,
                                getParentBlockState(type, "planks_woven"),
                                Utils.copyPropertySafe(type.planks)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_woven")))
                .addModelTransform(m -> m.addModifier((s, blockId, woodType) ->
                        s.replace("\"rechiseled:oak_planks_woven_stairs\"", "\"" + blockId.toString() + "\"")
                                .replaceAll("\"rechiseled:oak_(\\w+)\"",
                                        "\"" + createStandardId(woodType, "", "") + "_$1\"")

                ))
                //TEXTURES: planks_woven @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_woven_stairs);

        oak_planks_woven_stairs_connecting = SimpleEntrySet.builder(WoodType.class, "planks_woven_stairs_connecting",
                        getModBlock("oak_planks_woven_stairs_connecting"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledStairBlock(true,
                                getParentBlockState(type, "planks_woven_connecting"),
                                Utils.copyPropertySafe(type.planks)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_woven_connecting")))
                .addModelTransform(m -> m.addModifier((s, blockId, woodType) ->
                        s.replace("\"rechiseled:oak_planks_woven_stairs_connecting\"", "\"" + blockId.toString() + "\"")
                                .replaceAll("\"rechiseled:oak_(\\w+)\"",
                                        "\"" + createStandardId(woodType, "", "") + "_$1\"")

                ))
                //TEXTURES: planks_woven_connecting @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_woven_stairs_connecting);


        oak_planks_mosaic_stairs = SimpleEntrySet.builder(WoodType.class, "planks_mosaic_stairs",
                        getModBlock("oak_planks_mosaic_stairs"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledStairBlock(false,
                                getParentBlockState(type, "planks_mosaic"),
                                Utils.copyPropertySafe(type.planks)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "planks_mosaic")))
                .addModelTransform(m -> m.addModifier((s, blockId, woodType) ->
                        s.replace("\"rechiseled:oak_planks_mosaic_stairs\"", "\"" + blockId.toString() + "\"")
                                .replaceAll("\"rechiseled:oak_(\\w+)\"",
                                        "\"" + createStandardId(woodType, "", "") + "_$1\"")

                ))
                //TEXTURES: planks_mosaic @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_mosaic_stairs);

    }

}