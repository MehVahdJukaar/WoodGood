package net.mehvahdjukaar.every_compat.modules.building_but_better;

import com.starfish_studios.bbb.block.*;
import com.starfish_studios.bbb.item.DescriptionBlockItem;
import net.mehvahdjukaar.every_compat.api.PaletteStrategies;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.material.PushReaction;

import java.util.Objects;

import static net.mehvahdjukaar.every_compat.EveryCompat.res;
import static net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys.STRIPPED_LOG;

//SUPPORT: v2.0pre3
public class BuildingButBetterModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> balustrade;
    public final SimpleEntrySet<WoodType, Block> beam;
    public final SimpleEntrySet<WoodType, Block> beam_slab;
    public final SimpleEntrySet<WoodType, Block> beam_stairs;
    public final SimpleEntrySet<WoodType, Block> frame;
    public final SimpleEntrySet<WoodType, Block> ladder;
    public final SimpleEntrySet<WoodType, Block> lantern;
    public final SimpleEntrySet<WoodType, Block> lattice;
    public final SimpleEntrySet<WoodType, Block> layer;
    public final SimpleEntrySet<WoodType, Block> pallet;
    public final SimpleEntrySet<WoodType, Block> support;
    public final SimpleEntrySet<WoodType, Block> trim;
    public final SimpleEntrySet<WoodType, Block> wall;

    public BuildingButBetterModule(String modId) {
        super(modId, "bbb");
        ResourceLocation tab = modRes("main");

        balustrade = SimpleEntrySet.builder(WoodType.class, "balustrade",
                        getModBlock("oak_balustrade"), () -> VanillaWoodTypes.OAK,
                        w -> new BalustradeBlock(Utils.copyPropertySafe(w.planks)
                                .noOcclusion()
                        )
                )
                //TEXTURES: beam/oak, planks
                .addTexture(modRes("block/balustrade/oak_sides"))
                .addTexture(modRes("block/balustrade/oak_top"))
                .addTexture(modRes("block/beam/oak"), PaletteStrategies.PLANKS_LOW_CONTRAST)
                .addTexture(modRes("block/beam/oak_top"), PaletteStrategies.PLANKS_LOW_CONTRAST)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("wooden_blocks"), Registries.BLOCK)
                .addTag(modRes("balustrades"), Registries.BLOCK, Registries.ITEM)
                .setTabKey(tab)
//                .defaultRecipe() // NOT AVAILABLE YET
                .addCustomItem((woodType, block, properties) -> new DescriptionBlockItem(block, properties))
                .build();
        this.addEntry(balustrade);

        beam = SimpleEntrySet.builder(WoodType.class, "beam",
                        getModBlock("oak_beam"), () -> VanillaWoodTypes.OAK,
                        w -> new RotatedPillarBlock(Utils.copyPropertySafe(Objects.requireNonNull(w.getBlockOfThis(STRIPPED_LOG))))
                )
                .requiresChildren(STRIPPED_LOG) //REASON: recipes
                //TEXTURES: beam/oak, beam/oak_top (@balustrade)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(modRes("wooden_blocks"), Registries.BLOCK)
                .addTag(modRes("beams"), Registries.BLOCK)
                .addTag(new ResourceLocation("alexscaves:grows_mussels"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("oak_planks_from_beam"))
                .build();
        this.addEntry(beam);

        beam_slab = SimpleEntrySet.builder(WoodType.class, "beam_slab",
                        getModBlock("oak_beam_slab"), () -> VanillaWoodTypes.OAK,
                        w -> new FacingSlabBlock(Utils.copyPropertySafe(w.planks))
                )
                .requiresFromMap(beam.blocks) //REASON: recipes
                //TEXTURES: beam/oak
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.SLABS, Registries.BLOCK)
                .addTag(modRes("wooden_blocks"), Registries.BLOCK)
                .addTag(modRes("beams"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("oak_planks_from_beam_slab"))
                .build();
        this.addEntry(beam_slab);

        beam_stairs = SimpleEntrySet.builder(WoodType.class, "beam_stairs",
                        getModBlock("oak_beam_stairs"), () -> VanillaWoodTypes.OAK,
                        w -> new StairBlock(w.planks.defaultBlockState(), Utils.copyPropertySafe(w.planks))
                )
                .requiresFromMap(beam.blocks) //REASON: recipes
                //TEXTURES: beam/oak, beam/oak_top
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.STAIRS, Registries.BLOCK)
                .addTag(modRes("wooden_blocks"), Registries.BLOCK)
                .addTag(modRes("beams"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(beam_stairs);

        frame = SimpleEntrySet.builder(WoodType.class, "frame",
                        getModBlock("oak_frame"), () -> VanillaWoodTypes.OAK,
                        w -> new FrameBlock(Utils.copyPropertySafe(w.planks)
                                .noOcclusion()
                                .noCollission()
                        )
                )
                //TEXTURES: planks
                .addTexture(modRes("block/frame/oak"))
                .addTexture(modRes("block/frame/oak_sticks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("wooden_blocks"), Registries.BLOCK)
                .addTag(modRes("wooden_frames"), Registries.BLOCK)
                .addTag(new ResourceLocation("create:movable_empty_collider"), Registries.BLOCK)
                .addTag(modRes("frames"), Registries.BLOCK, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .addCustomItem((woodType, block, properties) -> new DescriptionBlockItem(block, properties))
                .build();
        this.addEntry(frame);

        ladder = SimpleEntrySet.builder(WoodType.class, "ladder",
                        getModBlock("oak_ladder"), () -> VanillaWoodTypes.OAK,
                        w -> new BBBLadderBlock(1, Utils.copyPropertySafe(Blocks.LADDER))
                )
                .addTexture(modRes("block/ladder/oak/oak"))
                .addTexture(modRes("block/ladder/acacia/oak"))
                .addTexture(modRes("block/ladder/birch/oak"))
                .addTexture(modRes("block/ladder/cherry/oak"))
                .addTexture(modRes("block/ladder/crimson/oak"))
                .addTexture(modRes("block/ladder/dark_oak/oak"))
                .addTexture(modRes("block/ladder/jungle/oak"))
                .addTexture(modRes("block/ladder/mangrove/oak"))
                .addTextureM(modRes("block/ladder/spruce/oak"), res("block/bbb/spruce-oak_ladder_m"))
                .addTexture(modRes("block/ladder/warped/oak"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.CLIMBABLE, Registries.BLOCK)
                .addTag(BlockTags.FALL_DAMAGE_RESETTING, Registries.BLOCK)
                .addTag(modRes("wooden_blocks"), Registries.BLOCK)
                .addTag(modRes("ladders"), Registries.BLOCK, Registries.ITEM)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .addCustomItem((woodType, block, properties) -> new DescriptionBlockItem(block, properties))
                .build();
        this.addEntry(ladder);

        lantern = SimpleEntrySet.builder(WoodType.class, "lantern",
                        getModBlock("oak_lantern"), () -> VanillaWoodTypes.OAK,
                        w -> new WoodenLanternBlock(Utils.copyPropertySafe(w.planks)
                                .lightLevel((blockStatex) -> 15)
                                .noOcclusion()
                                .pushReaction(PushReaction.DESTROY)
                                .strength(0.3F))
                )
                //TEXTURES: planks
                .addTexture(modRes("block/lantern/oak"))
//                .addTextureM(modRes("block/lantern/oak"), EveryCompat.res("block/bbb/oak_lantern_m")) //REASON: the texture is 18x16, mask_texture had to be removed
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("wooden_blocks"), Registries.BLOCK)
                .addTag(modRes("wooden_lanterns"), Registries.BLOCK, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(lantern);

        lattice = SimpleEntrySet.builder(WoodType.class, "lattice",
                        getModBlock("oak_lattice"), () -> VanillaWoodTypes.OAK,
                        w -> new LatticeBlock(Utils.copyPropertySafe(w.planks)
                                .noOcclusion().pushReaction(PushReaction.DESTROY)
                        )
                )
                //TEXTURES: planks, log, log_top, leaves
                .addTexture(modRes("block/lattice/oak"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.CLIMBABLE, Registries.BLOCK)
                .addTag(BlockTags.FALL_DAMAGE_RESETTING, Registries.BLOCK)
                .addTag(modRes("wooden_blocks"), Registries.BLOCK)
                .addTag(modRes("lattices"), Registries.BLOCK, Registries.ITEM)
                .setTabKey(tab)
//                .defaultRecipe() //TODO: no recipe available - waiting for DEV to add it
                .addCustomItem((woodType, block, properties) -> new DescriptionBlockItem(block, properties))
                .build();
        this.addEntry(lattice);

        layer = SimpleEntrySet.builder(WoodType.class, "layer",
                        getModBlock("oak_layer"), () -> VanillaWoodTypes.OAK,
                        w -> new LayerBlock(Utils.copyPropertySafe(w.planks))
                )
                //TEXTURES: planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("wooden_blocks"), Registries.BLOCK)
                .addTag(modRes("wooden_layers"), Registries.BLOCK, Registries.ITEM)
                .addTag(modRes("layers"), Registries.BLOCK, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .addCustomItem((woodType, block, properties) -> new DescriptionBlockItem(block, properties))
                .build();
        this.addEntry(layer);

        pallet = SimpleEntrySet.builder(WoodType.class, "pallet",
                        getModBlock("oak_pallet"), () -> VanillaWoodTypes.OAK,
                        w -> new PalletBlock(Utils.copyPropertySafe(w.planks)
                                .noOcclusion()
                        )
                )
                //TEXTURES: planks
                .addTexture(modRes("block/pallet/oak_pallet"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("wooden_blocks"), Registries.BLOCK)
                .addTag(modRes("pallets"), Registries.BLOCK, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .addCustomItem((woodType, block, properties) -> new DescriptionBlockItem(block, properties))
                .build();
        this.addEntry(pallet);

        support = SimpleEntrySet.builder(WoodType.class, "support",
                        getModBlock("oak_support"), () -> VanillaWoodTypes.OAK,
                        w -> new SupportBlock(Utils.copyPropertySafe(w.planks)
                                .noOcclusion()
                        )
                )
                //TEXTURES: beam/oak, beam/oak_top
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("wooden_blocks"), Registries.BLOCK)
                .addTag(modRes("supports"), Registries.BLOCK, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .addCustomItem((woodType, block, properties) -> new DescriptionBlockItem(block, properties))
                .build();
        this.addEntry(support);

        trim = SimpleEntrySet.builder(WoodType.class, "trim",
                        getModBlock("oak_trim"), () -> VanillaWoodTypes.OAK,
                        w -> new FacingConnectingBlock(Utils.copyPropertySafe(w.planks))
                )
                //TEXTURES: planks
                .addTexture(modRes("block/sanded_planks/oak"))
                .addTexture(modRes("block/trim/oak"))
                .addTexture(modRes("block/trim/oak_bottom"))
                .addTexture(modRes("block/trim/oak_middle"))
                .addTexture(modRes("block/trim/oak_top"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("wooden_blocks"), Registries.BLOCK)
                .addTag(modRes("trims"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(trim);

        wall = SimpleEntrySet.builder(WoodType.class, "wall",
                        getModBlock("oak_wall"), () -> VanillaWoodTypes.OAK,
                        w -> new WoodenWallBlock(Utils.copyPropertySafe(w.planks))
                )
                .requiresChildren(STRIPPED_LOG) //REASON: recipes
                //TEXTURES: beam/oak, beam/oak_top
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .addTag(modRes("wooden_blocks"), Registries.BLOCK)
                .addTag(modRes("wooden_walls"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(wall);


    }
}