package net.mehvahdjukaar.every_compat.modules.forge.building_but_better;

import com.starfish_studios.bbb.block.*;
import com.starfish_studios.bbb.item.DescriptionBlockItem;
import com.starfish_studios.bbb.registry.BBBBlocks;
import com.starfish_studios.bbb.registry.BBBTags.BBBBlockTags;
import com.starfish_studios.bbb.registry.BBBTags.BBBItemTags;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.api.TabAddMode;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.material.PushReaction;

public class BuildingButBetterModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> layers;
    public final SimpleEntrySet<WoodType, Block> trims;
    public final SimpleEntrySet<WoodType, Block> beams;
    public final SimpleEntrySet<WoodType, Block> beamStairs;
    public final SimpleEntrySet<WoodType, Block> beamSlabs;
    public final SimpleEntrySet<WoodType, Block> supports;
    public final SimpleEntrySet<WoodType, Block> frames;
    public final SimpleEntrySet<WoodType, Block> pallets;
    public final SimpleEntrySet<WoodType, Block> lanterns;
    public final SimpleEntrySet<WoodType, Block> ladders;
    public final SimpleEntrySet<WoodType, Block> walls;

    public BuildingButBetterModule(String modId) {
        super(modId, "bbb");

        layers = SimpleEntrySet.builder(WoodType.class, "layer",
                BBBBlocks.OAK_LAYER, () -> WoodTypeRegistry.OAK_TYPE,
                w -> new LayerBlock(Utils.copyPropertySafe(w.planks)))
                .addTag(BBBItemTags.LAYERS, Registries.ITEM)
                .addTag(modRes("wooden_layers"), Registries.ITEM)
                .addTag(modRes("wooden_blocks"), Registries.BLOCK)
                .addTag(modRes("wooden_layers"), Registries.BLOCK)
                .addTag(BBBBlockTags.LAYERS, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(CreativeModeTabs.BUILDING_BLOCKS)
                .setTabMode(TabAddMode.AFTER_SAME_WOOD)
                .addCustomItem((wood, block, properties) -> new DescriptionBlockItem(block, properties))
                .defaultRecipe()
                .copyParentDrop()
                .addModelTransform(m -> m.replaceOakPlanks())
                .build();

        this.addEntry(layers);

        trims = SimpleEntrySet.builder(WoodType.class, "trim",
                BBBBlocks.OAK_TRIM, () -> WoodTypeRegistry.OAK_TYPE,
                w -> new FacingConnectingBlock(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/trim/oak_bottom"))
                .addTexture(modRes("block/trim/oak_middle"))
                .addTexture(modRes("block/trim/oak_none"))
                .addTexture(modRes("block/trim/oak_top"))
                .addTexture(modRes("block/trim/oak_top_face"))
                .addTag(modRes("wooden_blocks"), Registries.BLOCK)
                .addTag(modRes("trims"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .defaultRecipe()
                .setTabKey(CreativeModeTabs.BUILDING_BLOCKS)
                .setTabMode(TabAddMode.AFTER_SAME_WOOD)
                .build();

        this.addEntry(trims);

        beams = SimpleEntrySet.builder(WoodType.class, "beam",
                BBBBlocks.OAK_BEAM, () -> WoodTypeRegistry.OAK_TYPE,
                w -> new RotatedPillarBlock(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/beam/oak"))
                .addTexture(modRes("block/beam/oak_top"))
                .addTag(modRes("wooden_blocks"), Registries.BLOCK)
                .addTag(modRes("beams"), Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .defaultRecipe()
                .setTabKey(CreativeModeTabs.BUILDING_BLOCKS)
                .setTabMode(TabAddMode.AFTER_SAME_WOOD)
                .build();

        this.addEntry(beams);

        beamStairs = SimpleEntrySet.builder(WoodType.class, "beam_stairs",
                BBBBlocks.OAK_BEAM_STAIRS, () -> WoodTypeRegistry.OAK_TYPE,
                w -> new StairBlock(w.planks.defaultBlockState(), Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/beam/oak"))
                .addTexture(modRes("block/beam/oak_top"))
                .addTag(modRes("wooden_blocks"), Registries.BLOCK)
                .addTag(modRes("beams"), Registries.BLOCK)
                .addTag(BlockTags.STAIRS, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_STAIRS, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .defaultRecipe()
                .setTabKey(CreativeModeTabs.BUILDING_BLOCKS)
                .setTabMode(TabAddMode.AFTER_SAME_WOOD)
                .build();

        this.addEntry(beamStairs);

        beamSlabs = SimpleEntrySet.builder(WoodType.class, "beam_slab",
                BBBBlocks.OAK_BEAM_STAIRS, () -> WoodTypeRegistry.OAK_TYPE,
                w -> new FacingSlabBlock(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/beam/oak"))
                .addTexture(modRes("block/beam/oak_top"))
                .addTag(modRes("wooden_blocks"), Registries.BLOCK)
                .addTag(modRes("beams"), Registries.BLOCK)
                .addTag(BlockTags.SLABS, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_SLABS, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .defaultRecipe()
                .setTabKey(CreativeModeTabs.BUILDING_BLOCKS)
                .setTabMode(TabAddMode.AFTER_SAME_WOOD)
                .build();

        this.addEntry(beamSlabs);

        supports = SimpleEntrySet.builder(WoodType.class, "support",
                BBBBlocks.OAK_SUPPORT, () -> WoodTypeRegistry.OAK_TYPE,
                w -> new SupportBlock(Utils.copyPropertySafe(w.planks).noOcclusion()))
                .addTexture(modRes("block/beam/oak"))
                .addTexture(modRes("block/beam/oak_top"))
                .addTag(modRes("wooden_blocks"), Registries.BLOCK)
                .addTag(modRes("supports"), Registries.BLOCK)
                .addTag(modRes("supports"), Registries.ITEM)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(CreativeModeTabs.BUILDING_BLOCKS)
                .setTabMode(TabAddMode.AFTER_SAME_WOOD)
                .addCustomItem((wood, block, properties) -> new DescriptionBlockItem(block, properties))
                .defaultRecipe()
                .build();

        this.addEntry(supports);

        frames = SimpleEntrySet.builder(WoodType.class, "frame",
                BBBBlocks.OAK_FRAME, () -> WoodTypeRegistry.OAK_TYPE,
                w -> new FrameBlock(Utils.copyPropertySafe(w.planks).noOcclusion().noCollission().pushReaction(PushReaction.DESTROY)))
                .addTexture(modRes("block/frame/oak"))
                .addTexture(modRes("block/frame/oak_sticks"))
                .addTag(modRes("wooden_blocks"), Registries.BLOCK)
                .addTag(modRes("frames"), Registries.BLOCK)
                .addTag(modRes("frames"), Registries.ITEM)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(CreativeModeTabs.BUILDING_BLOCKS)
                .setTabMode(TabAddMode.AFTER_SAME_WOOD)
                .addCustomItem((wood, block, properties) -> new DescriptionBlockItem(block, properties))
                .defaultRecipe()
                .build();

        this.addEntry(frames);

        pallets = SimpleEntrySet.builder(WoodType.class, "pallet",
                BBBBlocks.OAK_FRAME, () -> WoodTypeRegistry.OAK_TYPE,
                w -> new PalletBlock(Utils.copyPropertySafe(w.planks).noOcclusion()))
                .addTexture(modRes("block/pallet/oak_pallet"))
                .addTag(modRes("wooden_blocks"), Registries.BLOCK)
                .addTag(modRes("pallets"), Registries.BLOCK)
                .addTag(modRes("pallets"), Registries.ITEM)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(CreativeModeTabs.BUILDING_BLOCKS)
                .setTabMode(TabAddMode.AFTER_SAME_WOOD)
                .addCustomItem((wood, block, properties) -> new DescriptionBlockItem(block, properties))
                .defaultRecipe()
                .build();

        this.addEntry(pallets);

        lanterns = SimpleEntrySet.builder(WoodType.class, "lantern",
                BBBBlocks.OAK_LANTERN, () -> WoodTypeRegistry.OAK_TYPE,
                w -> new WoodenLanternBlock(Utils.copyPropertySafe(w.planks).lightLevel((blockStatex) -> 15)))
                .addTextureM(modRes("block/lantern/oak"), EveryCompat.res("block/bbb/lantern/oak_m"))
                .addTag(modRes("wooden_lanterns"), Registries.BLOCK)
                .addTag(modRes("wooden_blocks"), Registries.BLOCK)
                .addTag(modRes("lanterns"), Registries.ITEM)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(CreativeModeTabs.BUILDING_BLOCKS)
                .setTabMode(TabAddMode.AFTER_SAME_WOOD)
                .addCustomItem((wood, block, properties) -> new DescriptionBlockItem(block, properties))
                .defaultRecipe()
                .build();

        this.addEntry(lanterns);

        ladders = SimpleEntrySet.builder(WoodType.class, "ladder",
                BBBBlocks.OAK_LADDER, () -> WoodTypeRegistry.OAK_TYPE,
                w -> new BBBLadderBlock(1, Utils.copyPropertySafe(Blocks.LADDER)))
                .addTexture(modRes("block/ladder/acacia/oak"))
                .addTexture(modRes("block/ladder/birch/oak"))
                .addTexture(modRes("block/ladder/cherry/oak"))
                .addTexture(modRes("block/ladder/crimson/oak"))
                .addTexture(modRes("block/ladder/dark_oak/oak"))
                .addTexture(modRes("block/ladder/jungle/oak"))
                .addTexture(modRes("block/ladder/mangrove/oak"))
                .addTexture(modRes("block/ladder/oak/oak"))
                .addTextureM(modRes("block/ladder/spruce/oak"), EveryCompat.res("block/bbb/ladder/spruce/oak_m"))
                .addTexture(modRes("block/ladder/warped/oak"))
                .setRenderType(() -> RenderType::cutout)
                .addTag(modRes("ladders"), Registries.BLOCK)
                .addTag(modRes("wooden_blocks"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.CLIMBABLE, Registries.BLOCK)
                .addTag(BlockTags.FALL_DAMAGE_RESETTING, Registries.BLOCK)
                .addTag(modRes("ladders"), Registries.ITEM)
                .setTabKey(CreativeModeTabs.BUILDING_BLOCKS)
                .setTabMode(TabAddMode.AFTER_SAME_WOOD)
                .addCustomItem((wood, block, properties) -> new DescriptionBlockItem(block, properties))
                .defaultRecipe()
                .build();

        this.addEntry(ladders);

        walls = SimpleEntrySet.builder(WoodType.class, "wall",
                BBBBlocks.OAK_WALL, () -> WoodTypeRegistry.OAK_TYPE,
                w -> new WoodenWallBlock(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/beam/oak"))
                .addTexture(modRes("block/beam/oak_top"))
                .addTag(modRes("wooden_walls"), Registries.BLOCK)
                .addTag(modRes("wooden_blocks"), Registries.BLOCK)
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .defaultRecipe()
                .setTabKey(CreativeModeTabs.BUILDING_BLOCKS)
                .setTabMode(TabAddMode.AFTER_SAME_WOOD)
                .build();

        this.addEntry(walls);
    }
}
