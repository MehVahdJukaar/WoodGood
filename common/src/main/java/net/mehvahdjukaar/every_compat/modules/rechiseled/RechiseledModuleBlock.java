package net.mehvahdjukaar.every_compat.modules.rechiseled;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.supermartijn642.rechiseled.blocks.RechiseledBlock;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.EntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

import java.util.Objects;
import java.util.function.Consumer;

import static net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys.*;


// See RechiseledModuleAbstract's Supported Version
public class RechiseledModuleBlock extends RechiseledModuleAbstract {

    public final SimpleEntrySet<WoodType, Block> oak_planks_beams, oak_planks_beams_connecting;
    public final SimpleEntrySet<WoodType, Block> oak_planks_brick_pattern, oak_planks_brick_pattern_connecting;
    public final SimpleEntrySet<WoodType, Block> oak_planks_brick_paving, oak_planks_brick_paving_connecting;
    public final SimpleEntrySet<WoodType, Block> oak_planks_bricks, oak_planks_bricks_connecting;
    public final SimpleEntrySet<WoodType, Block> oak_planks_crate, oak_planks_crate_connecting;
    public final SimpleEntrySet<WoodType, Block> oak_planks_diagonal_stripes, oak_planks_diagonal_stripes_connecting;
    public final SimpleEntrySet<WoodType, Block> oak_planks_diagonal_tiles, oak_planks_diagonal_tiles_connecting;
    public final SimpleEntrySet<WoodType, Block> oak_planks_dotted, oak_planks_dotted_connecting;
    public final SimpleEntrySet<WoodType, Block> oak_planks_flooring, oak_planks_flooring_connecting;
    public final SimpleEntrySet<WoodType, Block> oak_planks_large_tiles, oak_planks_large_tiles_connecting;
    public final SimpleEntrySet<WoodType, Block> oak_planks_pattern, oak_planks_pattern_connecting;
    public final SimpleEntrySet<WoodType, Block> oak_planks_rotated_bricks, oak_planks_rotated_bricks_connecting;
    public final SimpleEntrySet<WoodType, Block> oak_planks_small_bricks, oak_planks_small_bricks_connecting;
    public final SimpleEntrySet<WoodType, Block> oak_planks_small_tiles, oak_planks_small_tiles_connecting;
    public final SimpleEntrySet<WoodType, Block> oak_planks_squares, oak_planks_squares_connecting;
    public final SimpleEntrySet<WoodType, Block> oak_planks_tiles, oak_planks_tiles_connecting;
    public final SimpleEntrySet<WoodType, Block> oak_planks_wavy, oak_planks_wavy_connecting;
    public final SimpleEntrySet<WoodType, Block> oak_planks_woven, oak_planks_woven_connecting;
    public final SimpleEntrySet<WoodType, Block> oak_planks_mosaic;

    public RechiseledModuleBlock(String modId) {
        super(modId);
        setBlockType("Block");

        oak_planks_beams = SimpleEntrySet.builder(WoodType.class, "planks_beams",
                        getModBlock("oak_planks_beams"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledBlock(false, Utils.copyPropertySafe(type.planks))
                )
                .addTexture(modRes("block/oak_planks_beams"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_beams);

        oak_planks_beams_connecting = SimpleEntrySet.builder(WoodType.class, "planks_beams_connecting",
                        getModBlock("oak_planks_beams_connecting"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledBlock(true, Utils.copyPropertySafe(type.planks))
                )
                //TEXTURES: blank_1 (above)
                .addModelTransform(m -> m.addModifier((s, blockId, woodType) ->
                        s.replace("\"rechiseled:oak_planks_beams_connecting\"", "\"" + blockId.toString() + "\"")
                                .replaceAll("\"rechiseled:oak_(\\w+)\"",
                                        "\"" + createStandardId(woodType, "", "") + "_$1\"")

                ))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_beams_connecting);

        oak_planks_brick_pattern = SimpleEntrySet.builder(WoodType.class, "planks_brick_pattern",
                        getModBlock("oak_planks_brick_pattern"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledBlock(false, Utils.copyPropertySafe(type.planks))
                )
                //REASON: FUSION's error: Image/frame size 128x128 is not a multiple of 'full' layout's 8 : 6 aspect ratio
                // Below is the only way to prevent an animated texture from being generated
                .addNonAnimatedTexture(modRes("block/oak_planks_brick_pattern"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_brick_pattern);

        oak_planks_brick_pattern_connecting = SimpleEntrySet.builder(WoodType.class, "planks_brick_pattern_connecting",
                        getModBlock("oak_planks_brick_pattern_connecting"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledBlock(true, Utils.copyPropertySafe(type.planks))
                )
                //TEXTURES: blank_2 (above)
                .addModelTransform(m -> m.addModifier((s, blockId, woodType) ->
                        s.replace("\"rechiseled:oak_planks_brick_pattern_connecting\"", "\"" + blockId.toString() + "\"")
                                .replaceAll("\"rechiseled:oak_(\\w+)\"",
                                        "\"" + createStandardId(woodType, "", "") + "_$1\"")

                ))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_brick_pattern_connecting);

        oak_planks_brick_paving = SimpleEntrySet.builder(WoodType.class, "planks_brick_paving",
                        getModBlock("oak_planks_brick_paving"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledBlock(false, Utils.copyPropertySafe(type.planks))
                )
                .addTexture(modRes("block/oak_planks_brick_paving"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_brick_paving);

        oak_planks_brick_paving_connecting = SimpleEntrySet.builder(WoodType.class, "planks_brick_paving_connecting",
                        getModBlock("oak_planks_brick_paving_connecting"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledBlock(true, Utils.copyPropertySafe(type.planks))
                )
                //TEXTURES: blank_3 (above)
                .addModelTransform(m -> m.addModifier((s, blockId, woodType) ->
                        s.replace("\"rechiseled:oak_planks_brick_paving_connecting\"", "\"" + blockId.toString() + "\"")
                                .replaceAll("\"rechiseled:oak_(\\w+)\"",
                                        "\"" + createStandardId(woodType, "", "") + "_$1\"")

                ))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_brick_paving_connecting);

        oak_planks_bricks = SimpleEntrySet.builder(WoodType.class, "planks_bricks",
                        getModBlock("oak_planks_bricks"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledBlock(false, Utils.copyPropertySafe(type.planks))
                )
                .addTexture(modRes("block/oak_planks_bricks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_bricks);

        oak_planks_bricks_connecting = SimpleEntrySet.builder(WoodType.class, "planks_bricks_connecting",
                        getModBlock("oak_planks_bricks_connecting"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledBlock(true, Utils.copyPropertySafe(type.planks))
                )
                //TEXTURES: blank_4 (above)
                .addModelTransform(m -> m.addModifier((s, blockId, woodType) ->
                        s.replace("\"rechiseled:oak_planks_bricks_connecting\"", "\"" + blockId.toString() + "\"")
                                .replaceAll("\"rechiseled:oak_(\\w+)\"",
                                        "\"" + createStandardId(woodType, "", "") + "_$1\"")

                ))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_bricks_connecting);

        oak_planks_crate = SimpleEntrySet.builder(WoodType.class, "planks_crate",
                        getModBlock("oak_planks_crate"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledBlock(false, Utils.copyPropertySafe(type.planks))
                )
                .addTexture(modRes("block/oak_planks_crate"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_crate);

        oak_planks_crate_connecting = SimpleEntrySet.builder(WoodType.class, "planks_crate_connecting",
                        getModBlock("oak_planks_crate_connecting"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledBlock(true, Utils.copyPropertySafe(type.planks))
                )
                //TEXTURES: blank_5 (above)
                .addModelTransform(m -> m.addModifier((s, blockId, woodType) ->
                        s.replace("\"rechiseled:oak_planks_crate_connecting\"", "\"" + blockId.toString() + "\"")
                                .replaceAll("\"rechiseled:oak_(\\w+)\"",
                                        "\"" + createStandardId(woodType, "", "") + "_$1\"")

                ))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_crate_connecting);

        oak_planks_diagonal_stripes = SimpleEntrySet.builder(WoodType.class, "planks_diagonal_stripes",
                        getModBlock("oak_planks_diagonal_stripes"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledBlock(false, Utils.copyPropertySafe(type.planks))
                )
                .addTexture(modRes("block/oak_planks_diagonal_stripes"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_diagonal_stripes);

        oak_planks_diagonal_stripes_connecting = SimpleEntrySet.builder(WoodType.class, "planks_diagonal_stripes_connecting",
                        getModBlock("oak_planks_diagonal_stripes_connecting"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledBlock(true, Utils.copyPropertySafe(type.planks))
                )
                //TEXTURES: blank_6 (above)
                .addModelTransform(m -> m.addModifier((s, blockId, woodType) ->
                        s.replace("\"rechiseled:oak_planks_diagonal_stripes_connecting\"", "\"" + blockId.toString() + "\"")
                                .replaceAll("\"rechiseled:oak_(\\w+)\"",
                                        "\"" + createStandardId(woodType, "", "") + "_$1\"")

                ))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_diagonal_stripes_connecting);

        oak_planks_diagonal_tiles = SimpleEntrySet.builder(WoodType.class, "planks_diagonal_tiles",
                        getModBlock("oak_planks_diagonal_tiles"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledBlock(false, Utils.copyPropertySafe(type.planks))
                )
                .addTexture(modRes("block/oak_planks_diagonal_tiles"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_diagonal_tiles);

        oak_planks_diagonal_tiles_connecting = SimpleEntrySet.builder(WoodType.class, "planks_diagonal_tiles_connecting",
                        getModBlock("oak_planks_diagonal_tiles_connecting"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledBlock(true, Utils.copyPropertySafe(type.planks))
                )
                //TEXTURES: blank_7 (above)
                .addModelTransform(m -> m.addModifier((s, blockId, woodType) ->
                        s.replace("\"rechiseled:oak_planks_diagonal_tiles_connecting\"", "\"" + blockId.toString() + "\"")
                                .replaceAll("\"rechiseled:oak_(\\w+)\"",
                                        "\"" + createStandardId(woodType, "", "") + "_$1\"")

                ))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_diagonal_tiles_connecting);

        oak_planks_dotted = SimpleEntrySet.builder(WoodType.class, "planks_dotted",
                        getModBlock("oak_planks_dotted"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledBlock(false, Utils.copyPropertySafe(type.planks))
                )
                .addTexture(modRes("block/oak_planks_dotted"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_dotted);

        oak_planks_dotted_connecting = SimpleEntrySet.builder(WoodType.class, "planks_dotted_connecting",
                        getModBlock("oak_planks_dotted_connecting"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledBlock(true, Utils.copyPropertySafe(type.planks))
                )
                //TEXTURES: blank_8 (above)
                .addModelTransform(m -> m.addModifier((s, blockId, woodType) ->
                        s.replace("\"rechiseled:oak_planks_dotted_connecting\"", "\"" + blockId.toString() + "\"")
                                .replaceAll("\"rechiseled:oak_(\\w+)\"",
                                        "\"" + createStandardId(woodType, "", "") + "_$1\"")

                ))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_dotted_connecting);

        oak_planks_flooring = SimpleEntrySet.builder(WoodType.class, "planks_flooring",
                        getModBlock("oak_planks_flooring"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledBlock(false, Utils.copyPropertySafe(type.planks))
                )
                .addTexture(modRes("block/oak_planks_flooring"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_flooring);

        oak_planks_flooring_connecting = SimpleEntrySet.builder(WoodType.class, "planks_flooring_connecting",
                        getModBlock("oak_planks_flooring_connecting"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledBlock(true, Utils.copyPropertySafe(type.planks))
                )
                //TEXTURES: blank_9 (above)
                .addModelTransform(m -> m.addModifier((s, blockId, woodType) ->
                        s.replace("\"rechiseled:oak_planks_flooring_connecting\"", "\"" + blockId.toString() + "\"")
                                .replaceAll("\"rechiseled:oak_(\\w+)\"",
                                        "\"" + createStandardId(woodType, "", "") + "_$1\"")

                ))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_flooring_connecting);

        oak_planks_large_tiles = SimpleEntrySet.builder(WoodType.class, "planks_large_tiles",
                        getModBlock("oak_planks_large_tiles"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledBlock(false, Utils.copyPropertySafe(type.planks))
                )
                .addTexture(modRes("block/oak_planks_large_tiles"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_large_tiles);

        oak_planks_large_tiles_connecting = SimpleEntrySet.builder(WoodType.class, "planks_large_tiles_connecting",
                        getModBlock("oak_planks_large_tiles_connecting"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledBlock(true, Utils.copyPropertySafe(type.planks))
                )
                //TEXTURES: blank_10 (above)
                .addModelTransform(m -> m.addModifier((s, blockId, woodType) ->
                        s.replace("\"rechiseled:oak_planks_large_tiles_connecting\"", "\"" + blockId.toString() + "\"")
                                .replaceAll("\"rechiseled:oak_(\\w+)\"",
                                        "\"" + createStandardId(woodType, "", "") + "_$1\"")

                ))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_large_tiles_connecting);

        oak_planks_pattern = SimpleEntrySet.builder(WoodType.class, "planks_pattern",
                        getModBlock("oak_planks_pattern"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledBlock(false, Utils.copyPropertySafe(type.planks))
                )
                .addTexture(modRes("block/oak_planks_pattern"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_pattern);

        oak_planks_pattern_connecting = SimpleEntrySet.builder(WoodType.class, "planks_pattern_connecting",
                        getModBlock("oak_planks_pattern_connecting"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledBlock(true, Utils.copyPropertySafe(type.planks))
                )
                //TEXTURES: blank_11 (above)
                .addModelTransform(m -> m.addModifier((s, blockId, woodType) ->
                        s.replace("\"rechiseled:oak_planks_pattern_connecting\"", "\"" + blockId.toString() + "\"")
                                .replaceAll("\"rechiseled:oak_(\\w+)\"",
                                        "\"" + createStandardId(woodType, "", "") + "_$1\"")

                ))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_pattern_connecting);

        oak_planks_rotated_bricks = SimpleEntrySet.builder(WoodType.class, "planks_rotated_bricks",
                        getModBlock("oak_planks_rotated_bricks"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledBlock(false, Utils.copyPropertySafe(type.planks))
                )
                .addTexture(modRes("block/oak_planks_rotated_bricks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_rotated_bricks);

        oak_planks_rotated_bricks_connecting = SimpleEntrySet.builder(WoodType.class, "planks_rotated_bricks_connecting",
                        getModBlock("oak_planks_rotated_bricks_connecting"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledBlock(true, Utils.copyPropertySafe(type.planks))
                )
                //TEXTURES: blank_12 (above)
                .addModelTransform(m -> m.addModifier((s, blockId, woodType) ->
                        s.replace("\"rechiseled:oak_planks_rotated_bricks_connecting\"", "\"" + blockId.toString() + "\"")
                                .replaceAll("\"rechiseled:oak_(\\w+)\"",
                                        "\"" + createStandardId(woodType, "", "") + "_$1\"")

                ))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_rotated_bricks_connecting);

        oak_planks_small_bricks = SimpleEntrySet.builder(WoodType.class, "planks_small_bricks",
                        getModBlock("oak_planks_small_bricks"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledBlock(false, Utils.copyPropertySafe(type.planks))
                )
                .addTexture(modRes("block/oak_planks_small_bricks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_small_bricks);

        oak_planks_small_bricks_connecting = SimpleEntrySet.builder(WoodType.class, "planks_small_bricks_connecting",
                        getModBlock("oak_planks_small_bricks_connecting"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledBlock(true, Utils.copyPropertySafe(type.planks))
                )
                //TEXTURES: blank_13 (above)
                .addModelTransform(m -> m.addModifier((s, blockId, woodType) ->
                        s.replace("\"rechiseled:oak_planks_small_bricks_connecting\"", "\"" + blockId.toString() + "\"")
                                .replaceAll("\"rechiseled:oak_(\\w+)\"",
                                        "\"" + createStandardId(woodType, "", "") + "_$1\"")

                ))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_small_bricks_connecting);

        oak_planks_small_tiles = SimpleEntrySet.builder(WoodType.class, "planks_small_tiles",
                        getModBlock("oak_planks_small_tiles"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledBlock(false, Utils.copyPropertySafe(type.planks))
                )
                .addTexture(modRes("block/oak_planks_small_tiles"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_small_tiles);

        oak_planks_small_tiles_connecting = SimpleEntrySet.builder(WoodType.class, "planks_small_tiles_connecting",
                        getModBlock("oak_planks_small_tiles_connecting"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledBlock(true, Utils.copyPropertySafe(type.planks))
                )
                //TEXTURES: blank_14 (above)
                .addModelTransform(m -> m.addModifier((s, blockId, woodType) ->
                        s.replace("\"rechiseled:oak_planks_small_tiles_connecting\"", "\"" + blockId.toString() + "\"")
                                .replaceAll("\"rechiseled:oak_(\\w+)\"",
                                        "\"" + createStandardId(woodType, "", "") + "_$1\"")

                ))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_small_tiles_connecting);

        oak_planks_squares = SimpleEntrySet.builder(WoodType.class, "planks_squares",
                        getModBlock("oak_planks_squares"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledBlock(false, Utils.copyPropertySafe(type.planks))
                )
                .addTexture(modRes("block/oak_planks_squares"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_squares);

        oak_planks_squares_connecting = SimpleEntrySet.builder(WoodType.class, "planks_squares_connecting",
                        getModBlock("oak_planks_squares_connecting"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledBlock(true, Utils.copyPropertySafe(type.planks))
                )
                //TEXTURES: blank_15 (above)
                .addModelTransform(m -> m.addModifier((s, blockId, woodType) ->
                        s.replace("\"rechiseled:oak_planks_squares_connecting\"", "\"" + blockId.toString() + "\"")
                                .replaceAll("\"rechiseled:oak_(\\w+)\"",
                                        "\"" + createStandardId(woodType, "", "") + "_$1\"")

                ))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_squares_connecting);

        oak_planks_tiles = SimpleEntrySet.builder(WoodType.class, "planks_tiles",
                        getModBlock("oak_planks_tiles"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledBlock(false, Utils.copyPropertySafe(type.planks))
                )
                .addTexture(modRes("block/oak_planks_tiles"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_tiles);

        oak_planks_tiles_connecting = SimpleEntrySet.builder(WoodType.class, "planks_tiles_connecting",
                        getModBlock("oak_planks_tiles_connecting"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledBlock(true, Utils.copyPropertySafe(type.planks))
                )
                //TEXTURES: blank_16 (above)
                .addModelTransform(m -> m.addModifier((s, blockId, woodType) ->
                        s.replace("\"rechiseled:oak_planks_tiles_connecting\"", "\"" + blockId.toString() + "\"")
                                .replaceAll("\"rechiseled:oak_(\\w+)\"",
                                        "\"" + createStandardId(woodType, "", "") + "_$1\"")

                ))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_tiles_connecting);

        oak_planks_wavy = SimpleEntrySet.builder(WoodType.class, "planks_wavy",
                        getModBlock("oak_planks_wavy"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledBlock(false, Utils.copyPropertySafe(type.planks))
                )
                .addTexture(modRes("block/oak_planks_wavy"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_wavy);

        oak_planks_wavy_connecting = SimpleEntrySet.builder(WoodType.class, "planks_wavy_connecting",
                        getModBlock("oak_planks_wavy_connecting"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledBlock(true, Utils.copyPropertySafe(type.planks))
                )
                //TEXTURES: blank_17 (above)
                .addModelTransform(m -> m.addModifier((s, blockId, woodType) ->
                        s.replace("\"rechiseled:oak_planks_wavy_connecting\"", "\"" + blockId.toString() + "\"")
                                .replaceAll("\"rechiseled:oak_(\\w+)\"",
                                        "\"" + createStandardId(woodType, "", "") + "_$1\"")

                ))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_wavy_connecting);

        oak_planks_woven = SimpleEntrySet.builder(WoodType.class, "planks_woven",
                        getModBlock("oak_planks_woven"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledBlock(false, Utils.copyPropertySafe(type.planks))
                )
                .addTexture(modRes("block/oak_planks_woven"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_woven);

        oak_planks_woven_connecting = SimpleEntrySet.builder(WoodType.class, "planks_woven_connecting",
                        getModBlock("oak_planks_woven_connecting"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledBlock(true, Utils.copyPropertySafe(type.planks))
                )
                //TEXTURES: blank_18 (above)
                .addModelTransform(m -> m.addModifier((s, blockId, woodType) ->
                        s.replace("\"rechiseled:oak_planks_woven_connecting\"", "\"" + blockId.toString() + "\"")
                                .replaceAll("\"rechiseled:oak_(\\w+)\"",
                                        "\"" + createStandardId(woodType, "", "") + "_$1\"")

                ))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_woven_connecting);


        oak_planks_mosaic = SimpleEntrySet.builder(WoodType.class, "planks_mosaic",
                        getModBlock("oak_planks_mosaic"), () -> VanillaWoodTypes.OAK,
                        type -> new RechiseledBlock(false, Utils.copyPropertySafe(type.planks))
                )
                .addTexture(modRes("block/oak_planks_mosaic"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(oak_planks_mosaic);

    }

    @Override
    // RECIPES
    public void addDynamicServerResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicServerResources(executor);

        executor.accept((manager, sink) ->

                        oak_planks_beams.blocks.forEach((woodType, block) -> {
                            JsonArray entriesArray = new JsonArray();

                            // Adding all supported-blocks of a StoneType to Array

                            for (EntrySet<?> entry : this.getEntries()) {
                                SimpleEntrySet<?, ?> currentEntry = ((SimpleEntrySet<?, ?>) entry);
                                String blockId = currentEntry.getName();

                                if (!blockId.contains("_connecting")) { // Skip the blocks with "_connecting"
                                    createAndAddEntry(entriesArray, woodType, modId, blockId, "_slab", "_stairs");
                                }
                            }

                            // Adding vanilla blocks to entriesArray
                            createAndAddEntry(entriesArray, woodType, "", PLANKS, SLAB, STAIRS);

                            // Recipes
                            JsonObject chiseling_recipe = new JsonObject();
                            chiseling_recipe.addProperty("type", "rechiseled:chiseling");
                            chiseling_recipe.addProperty("overwrite", false);
                            chiseling_recipe.add("entries", entriesArray);

                            // Adding to resources
                            ResourceLocation resLoc = EveryCompat.res("chiseling_recipes/" + woodType.getAppendableId());
                            if (!entriesArray.isEmpty()) sink.addJson(resLoc, chiseling_recipe, ResType.JSON);

                        })
        );
    }

    public void createAndAddEntry(JsonArray array, WoodType woodType, String modId, String blockId, String slabSuffix, String stairsSuffix) {
        JsonObject entry = new JsonObject();

        blockId = (modId.isEmpty()) ? blockId : modId + ":" + blockId;
        String slabId = (slabSuffix.contains("_")) ? blockId + slabSuffix : slabSuffix;
        String stairsId = (stairsSuffix.contains("_")) ? blockId + stairsSuffix : stairsSuffix;

        // Get the other block with "_connecting"
        String blockConnectingId = blockId + "_connecting";
        String slabConnectingId = slabId + "_connecting";
        String stairsConnectingId = stairsId + "_connecting";

        // Blocks
        Block currentBlock = woodType.getBlockOfThis(blockId);
        Block currentSlab = woodType.getBlockOfThis(slabId);
        Block currentStairs = woodType.getBlockOfThis(stairsId);

        // Connecting Blocks
        Block blockConnecting = woodType.getBlockOfThis(blockConnectingId);
        Block slabConnecting = woodType.getBlockOfThis(slabConnectingId);
        Block stairsConnecting = woodType.getBlockOfThis(stairsConnectingId);

        if (Objects.nonNull(currentBlock)) {

            entry.addProperty("block", Utils.getID(currentBlock).toString());
            if (Objects.nonNull(blockConnecting))
                entry.addProperty("connecting_block", Utils.getID(blockConnecting).toString());
            if (Objects.nonNull(currentSlab)) {
                entry.addProperty("slab", Utils.getID(currentSlab).toString());
                entry.addProperty("slab_worth", 0.5);
                if (Objects.nonNull(slabConnecting))
                    entry.addProperty("connecting_slab", Utils.getID(slabConnecting).toString());
                entry.addProperty("connecting_slab_worth", 0.5);
            }
            if (Objects.nonNull(currentStairs)) {
                entry.addProperty("stairs", Utils.getID(currentStairs).toString());
                if (Objects.nonNull(stairsConnecting))
                    entry.addProperty("connecting_stairs", Utils.getID(stairsConnecting).toString());
            }
        }

        if (!entry.isJsonNull()) array.add(entry);
    }
}