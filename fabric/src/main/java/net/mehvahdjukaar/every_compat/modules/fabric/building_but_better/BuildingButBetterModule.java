package net.mehvahdjukaar.every_compat.modules.fabric.building_but_better;

import com.starfish_studios.bbb.block.*;
import com.starfish_studios.bbb.item.DescriptionBlockItem;
import com.starfish_studios.bbb.registry.BBBTags.BBBBlockTags;
import com.starfish_studios.bbb.registry.BBBTags.BBBItemTags;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.resources.BlockTypeResTransformer;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.material.PushReaction;

// IMPORTANT:
// FABRIC version of this module is already included in the mod. It's currently OUTDATED

//SUPPORT: v1.0.2+
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
        ResourceKey<CreativeModeTab> tab = CreativeModeTabs.BUILDING_BLOCKS;

        layers = SimpleEntrySet.builder(WoodType.class, "layer",
                        getModBlock("oak_layer"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new LayerBlock(Utils.copyPropertySafe(w.planks))
                )
                .requiresChildren("slab") //REASON: recipes
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("wooden_blocks"), Registries.BLOCK)
                .addTag(modRes("wooden_layers"), Registries.BLOCK)
                .addTag(BBBBlockTags.LAYERS, Registries.BLOCK)
                .addTag(BBBItemTags.LAYERS, Registries.ITEM)
                .addTag(modRes("wooden_layers"), Registries.ITEM)
                .setTabKey(tab)
                .addCustomItem((wood, block, properties) -> new DescriptionBlockItem(block, properties))
                .defaultRecipe()
                .copyParentDrop()
                .addModelTransform(BlockTypeResTransformer::replaceOakPlanks)
                .build();
        this.addEntry(layers);

        trims = SimpleEntrySet.builder(WoodType.class, "trim",
                        getModBlock("oak_trim"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new FacingConnectingBlock(Utils.copyPropertySafe(w.planks))
                )
                .addTexture(modRes("block/trim/oak_bottom"))
                .addTexture(modRes("block/trim/oak_middle"))
                .addTexture(modRes("block/trim/oak_none"))
                .addTexture(modRes("block/trim/oak_top"))
                .addTexture(modRes("block/trim/oak_top_face"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("wooden_blocks"), Registries.BLOCK)
                .addTag(modRes("trims"), Registries.BLOCK)
                .defaultRecipe()
                .setTabKey(tab)
                .build();
        this.addEntry(trims);

        beams = SimpleEntrySet.builder(WoodType.class, "beam",
                        getModBlock("oak_beam"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new RotatedPillarBlock(Utils.copyPropertySafe(w.planks))
                )
                .requiresChildren("stripped_log") //REASON: recipes
                .addTexture(modRes("block/beam/oak"))
                .addTexture(modRes("block/beam/oak_top"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("wooden_blocks"), Registries.BLOCK)
                .addTag(modRes("beams"), Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .defaultRecipe()
                .setTabKey(tab)
                .build();
        this.addEntry(beams);

        beamStairs = SimpleEntrySet.builder(WoodType.class, "beam_stairs",
                        getModBlock("oak_beam_stairs"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new StairBlock(w.planks.defaultBlockState(), Utils.copyPropertySafe(w.planks))
                )
                .addCondition(w -> (beams.blocks.get(w) != null)) //REASON: recipes
                .addTexture(modRes("block/beam/oak"))
                .addTexture(modRes("block/beam/oak_top"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("wooden_blocks"), Registries.BLOCK)
                .addTag(modRes("beams"), Registries.BLOCK)
                .addTag(BlockTags.WOODEN_STAIRS, Registries.BLOCK)
                .addTag(BlockTags.STAIRS, Registries.BLOCK)
                .defaultRecipe()
                .setTabKey(tab)
                .build();
        this.addEntry(beamStairs);

        beamSlabs = SimpleEntrySet.builder(WoodType.class, "beam_slab",
                        getModBlock("oak_beam_slab"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new FacingSlabBlock(Utils.copyPropertySafe(w.planks))
                )
                .addCondition(w -> (beams.blocks.get(w) != null)) //REASON: recipes
                .addTexture(modRes("block/beam/oak"))
                .addTexture(modRes("block/beam/oak_top"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("wooden_blocks"), Registries.BLOCK)
                .addTag(modRes("beams"), Registries.BLOCK)
                .addTag(BlockTags.WOODEN_SLABS, Registries.BLOCK)
                .addTag(BlockTags.SLABS, Registries.BLOCK)
                .defaultRecipe()
                .setTabKey(tab)
                .build();
        this.addEntry(beamSlabs);

        supports = SimpleEntrySet.builder(WoodType.class, "support",
                        getModBlock("oak_support"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new SupportBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                .addTexture(modRes("block/beam/oak"))
                .addTexture(modRes("block/beam/oak_top"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("wooden_blocks"), Registries.BLOCK)
                .addTag(modRes("supports"), Registries.BLOCK)
                .addTag(modRes("supports"), Registries.ITEM)
                .setTabKey(tab)
                .addCustomItem((wood, block, properties) -> new DescriptionBlockItem(block, properties))
                .defaultRecipe()
                .build();
        this.addEntry(supports);

        frames = SimpleEntrySet.builder(WoodType.class, "frame",
                        getModBlock("oak_frame"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new FrameBlock(Utils.copyPropertySafe(w.planks).noOcclusion().noCollission().pushReaction(PushReaction.DESTROY))
                )
                .requiresChildren("slab") //REASON: recipes
                .addTexture(modRes("block/frame/oak_frame"))
                .addTexture(modRes("block/frame/oak_frame_sticks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(new ResourceLocation("create", "movable_empty_collider"), Registries.BLOCK)
                .addTag(modRes("wooden_blocks"), Registries.BLOCK)
                .addTag(modRes("frames"), Registries.BLOCK)
                .addTag(modRes("frames"), Registries.ITEM)
                .setTabKey(tab)
                .addCustomItem((wood, block, properties) -> new DescriptionBlockItem(block, properties))
                .defaultRecipe()
                .build();
        this.addEntry(frames);

        pallets = SimpleEntrySet.builder(WoodType.class, "pallet",
                        getModBlock("oak_pallet"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new PalletBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                .addTexture(modRes("block/pallet/oak_pallet"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("wooden_blocks"), Registries.BLOCK)
                .addTag(modRes("pallets"), Registries.BLOCK)
                .addTag(modRes("pallets"), Registries.ITEM)
                .setTabKey(tab)
                .addCustomItem((wood, block, properties) -> new DescriptionBlockItem(block, properties))
                .defaultRecipe()
                .build();
        this.addEntry(pallets);

        lanterns = SimpleEntrySet.builder(WoodType.class, "lantern",
                        getModBlock("oak_lantern"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WoodenLanternBlock(Utils.copyPropertySafe(w.planks).lightLevel((blockStatex) -> 15))
                )
                .requiresChildren("slab") //REASON: recipes
                .addTexture(modRes("block/lantern/oak"))
//                .addTextureM(modRes("block/lantern/oak"), EveryCompat.res("block/bbb/oak_lantern_m")) //REASON: the texture is 18x16, mask_texture had to be removed
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("wooden_lanterns"), Registries.BLOCK)
                .addTag(modRes("wooden_blocks"), Registries.BLOCK)
                .addTag(modRes("lanterns"), Registries.ITEM)
                .setTabKey(tab)
                .addCustomItem((wood, block, properties) -> new DescriptionBlockItem(block, properties))
                .defaultRecipe()
                .build();
        this.addEntry(lanterns);

        ladders = SimpleEntrySet.builder(WoodType.class, "ladder",
                        getModBlock("oak_ladder"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BBBLadderBlock(1, Utils.copyPropertySafe(Blocks.LADDER))
                )
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addTexture(modRes("block/ladder/acacia/oak"))
                .addTexture(modRes("block/ladder/birch/oak"))
                .addTexture(modRes("block/ladder/cherry/oak"))
                .addTexture(modRes("block/ladder/crimson/oak"))
                .addTexture(modRes("block/ladder/dark_oak/oak"))
                .addTexture(modRes("block/ladder/jungle/oak"))
                .addTexture(modRes("block/ladder/mangrove/oak"))
                .addTexture(modRes("block/ladder/oak/oak"))
                .addTextureM(modRes("block/ladder/spruce/oak"), EveryCompat.res("block/bbb/spruce-oak_ladder_m"))
                .addTexture(modRes("block/ladder/warped/oak"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("ladders"), Registries.BLOCK)
                .addTag(modRes("wooden_blocks"), Registries.BLOCK)
                .addTag(BlockTags.CLIMBABLE, Registries.BLOCK)
                .addTag(BlockTags.FALL_DAMAGE_RESETTING, Registries.BLOCK)
                .addTag(modRes("ladders"), Registries.ITEM)
                .setTabKey(tab)
                .addCustomItem((wood, block, properties) -> new DescriptionBlockItem(block, properties))
                .defaultRecipe()
                .build();
        this.addEntry(ladders);

        walls = SimpleEntrySet.builder(WoodType.class, "wall",
                        getModBlock("oak_wall"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WoodenWallBlock(Utils.copyPropertySafe(w.planks))
                )
                .requiresChildren("stripped_log") //REASON: recipes
                .addTexture(modRes("block/beam/oak"))
                .addTexture(modRes("block/beam/oak_top"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("wooden_walls"), Registries.BLOCK)
                .addTag(modRes("wooden_blocks"), Registries.BLOCK)
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .defaultRecipe()
                .setTabKey(tab)
                .build();
        this.addEntry(walls);
    }
}