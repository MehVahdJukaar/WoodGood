package net.mehvahdjukaar.every_compat.modules.blockus;

import com.brand.blockus.blocks.base.OrientableBlockBase;
import com.brand.blockus.blocks.base.PostBlock;
import com.brand.blockus.blocks.base.SmallHedgeBlock;
import com.brand.blockus.utils.helper.BlockFactory;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.PaletteStrategies;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.misc.CompatSpritesHelper;
import net.mehvahdjukaar.every_compat.misc.UtilityTag;
import net.mehvahdjukaar.moonlight.api.resources.BlockTypeResTransformer;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.resources.textures.Respriter;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesType;
import net.mehvahdjukaar.moonlight.api.set.leaves.VanillaLeavesTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.*;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

import static net.mehvahdjukaar.every_compat.EveryCompat.res;
import static net.mehvahdjukaar.every_compat.misc.UtilityMisc.copyChildrenPropertySafe;
import static net.mehvahdjukaar.every_compat.misc.UtilityMisc.doChildrenExistFor;

//SUPPORT: v2.9.10+ (FABRIC)
public class BlockusModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> herringbone_planks;
    public final SimpleEntrySet<WoodType, Block> mossy_planks;
    public final SimpleEntrySet<WoodType, Block> mossy_slab;
    public final SimpleEntrySet<WoodType, Block> mossy_stairs;
    public final SimpleEntrySet<WoodType, Block> grate;
    public final SimpleEntrySet<WoodType, Block> lattice;
    public final SimpleEntrySet<WoodType, Block> mosaic;
    public final SimpleEntrySet<WoodType, Block> mosaic_slab;
    public final SimpleEntrySet<WoodType, Block> mosaic_stairs;
    public final SimpleEntrySet<WoodType, Block> small_logs;
    public final SimpleEntrySet<WoodType, Block> timber_frame;
    public final SimpleEntrySet<WoodType, Block> diagonal_timber_frame;
    public final SimpleEntrySet<WoodType, Block> cross_timber_frame;
    public final SimpleEntrySet<WoodType, Block> stripped_post;
    public final SimpleEntrySet<WoodType, Block> post;
    public final SimpleEntrySet<LeavesType, Block> small_hedge;

    public BlockusModule(String modId) {
        super(modId, "bus");
        ResourceLocation tab = modRes("blockus_building_blocks");

        herringbone_planks = SimpleEntrySet.builder(WoodType.class, "planks", "herringbone",
                        getModBlock("herringbone_oak_planks"), () -> VanillaWoodTypes.OAK,
                        w -> new Block(Utils.copyPropertySafe(w.planks))
                )
                .addTexture(modRes("block/herringbone_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(UtilityTag.fabricTag("planks_that_burn"), Registries.BLOCK, Registries.ITEM)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .addTag(modRes("herringbone_planks_that_burn"), Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(herringbone_planks);

        mossy_planks = SimpleEntrySet.builder(WoodType.class, "planks", "mossy",
                        getModBlock("mossy_oak_planks"), () -> VanillaWoodTypes.OAK,
                        woodType -> new Block(Utils.copyPropertySafe(woodType.planks))
                )
                .addTextureM(modRes("block/mossy_oak_planks"), res("block/bus/mossy_oak_planks_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(UtilityTag.fabricTag("planks_that_burn"), Registries.BLOCK, Registries.ITEM)
                .addTag(modRes("all_mossy_planks"), Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("mossy_oak_planks_from_moss_block"))
                .addRecipe(modRes("mossy_oak_planks_from_vine"))
                .build();
        this.addEntry(mossy_planks);

        mossy_slab = SimpleEntrySet.builder(WoodType.class, "slab", "mossy",
                        getModBlock("mossy_oak_slab"), () -> VanillaWoodTypes.OAK,
                        woodType  -> new SlabBlock(copyChildrenPropertySafe("slab", woodType))
                )
                .requiresFromMap(mossy_planks.blocks) //REASON: textures, recipes
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.SLABS, Registries.BLOCK)
                .addTag(modRes("all_mossy_planks"), Registries.BLOCK)
                .addTag(ItemTags.SLABS, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(mossy_slab);

        mossy_stairs = SimpleEntrySet.builder(WoodType.class, "stairs", "mossy",
                        getModBlock("mossy_oak_stairs"), () -> VanillaWoodTypes.OAK,
                        woodType -> new StairBlock(mossy_planks.blocks.get(woodType).defaultBlockState(),
                                copyChildrenPropertySafe("stairs", woodType))

                )
                .requiresFromMap(mossy_planks.blocks) //REASON: textures, recipes
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.STAIRS, Registries.BLOCK)
                .addTag(modRes("all_mossy_planks"), Registries.ITEM)
                .addTag(ItemTags.STAIRS, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(mossy_stairs);

        grate = SimpleEntrySet.builder(WoodType.class, "grate",
                        getModBlock("oak_grate"), () -> VanillaWoodTypes.OAK,
                        w -> new WaterloggedTransparentBlock(Utils.copyPropertySafe(w.planks))
                )
                .addTexture(modRes("block/oak_grate"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("wooden_grates"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .setRenderType(RenderLayer.TRANSLUCENT)
                .build();
        this.addEntry(grate);

        lattice = SimpleEntrySet.builder(WoodType.class, "lattice",
                        getModBlock("oak_lattice"), () -> VanillaWoodTypes.OAK,
                        w -> new IronBarsBlock(Utils.copyPropertySafe(w.planks))
                )
                .requiresFromMap(grate.blocks) //REASON: textures, recipes
                .addTexture(modRes("block/oak_lattice_top"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("wooden_lattices"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .build();
        this.addEntry(lattice);

        mosaic = SimpleEntrySet.builder(WoodType.class, "mosaic",
                        getModBlock("oak_mosaic"), () -> VanillaWoodTypes.OAK,
                        woodType -> new Block(Utils.copyPropertySafe(woodType.planks))
                )
                .addTexture(modRes("block/oak_mosaic"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(UtilityTag.fabricTag("planks_that_burn"), Registries.BLOCK, Registries.ITEM)
                .addTag(modRes("all_wooden_mosaics"), Registries.BLOCK)
                .addTag(modRes("wooden_mosaic_that_burn"), Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(mosaic);

        mosaic_slab = SimpleEntrySet.builder(WoodType.class, "mosaic_slab",
                        getModBlock("oak_mosaic_slab"), () -> VanillaWoodTypes.OAK,
                        woodType -> new SlabBlock(copyChildrenPropertySafe("slab", woodType))
                )
                .requiresFromMap(mosaic.blocks) //REASON: textures, recipes
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.SLABS, Registries.BLOCK)
                .addTag(modRes("all_wooden_mosaics"), Registries.ITEM)
                .addTag(ItemTags.SLABS, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(mosaic_slab);

        mosaic_stairs = SimpleEntrySet.builder(WoodType.class, "mosaic_stairs",
                        getModBlock("oak_mosaic_stairs"), () -> VanillaWoodTypes.OAK,
                        woodType -> new StairBlock(mosaic.blocks.get(woodType).defaultBlockState(),
                                copyChildrenPropertySafe("stairs", woodType))

                )
                .requiresFromMap(mosaic.blocks) //REASON: textures, recipes
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.STAIRS, Registries.BLOCK)
                .addTag(modRes("all_wooden_mosaics"), Registries.ITEM)
                .addTag(ItemTags.STAIRS, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(mosaic_stairs);

        small_logs = SimpleEntrySet.builder(WoodType.class, "small_logs",
                        getModBlock("acacia_small_logs"), () -> VanillaWoodTypes.ACACIA,
                        w -> new RotatedPillarBlock(Utils.copyPropertySafe(w.planks))
                )
                .addTexture(modRes("block/acacia_small_logs"), PaletteStrategies.LOG_SIDE_STANDARD)
                //TEXTURE: manually generated texture below (acacia_small_logs_top.png)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.LOGS_THAT_BURN, Registries.BLOCK)
                .addTag(BlockTags.PARROTS_SPAWNABLE_ON, Registries.BLOCK)
                .addTag(BlockTags.LOGS, Registries.BLOCK)
                .addTag(BlockTags.COMPLETES_FIND_TREE_TUTORIAL, Registries.BLOCK)
                .addTag(ItemTags.COMPLETES_FIND_TREE_TUTORIAL, Registries.ITEM)
                .addTag(ItemTags.LOGS, Registries.ITEM)
                .addTag(ItemTags.LOGS_THAT_BURN, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(small_logs);

        timber_frame = SimpleEntrySet.builder(WoodType.class, "timber_frame",
                        getModBlock("oak_timber_frame"), () -> VanillaWoodTypes.OAK,
                        w -> new Block(Utils.copyPropertySafe(w.planks))
                )
                .addTextureM(modRes("block/oak_timber_frame"),
                        res("block/bus/oak_timber_frame_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("timber_frames"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(timber_frame);

        diagonal_timber_frame = SimpleEntrySet.builder(WoodType.class, "diagonal_timber_frame",
                        getModBlock("oak_diagonal_timber_frame"), () -> VanillaWoodTypes.OAK,
                        w -> new OrientableBlockBase(Utils.copyPropertySafe(w.planks))
                )
                .requiresFromMap(timber_frame.blocks) //REASON: recipes
                .addTextureM(modRes("block/oak_diagonal_timber_frame_left"),
                        res("block/bus/oak_diagonal_timber_frame_left_m"))
                .addTextureM(modRes("block/oak_diagonal_timber_frame_right"),
                        res("block/bus/oak_diagonal_timber_frame_right_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("timber_frames"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(diagonal_timber_frame);

        cross_timber_frame = SimpleEntrySet.builder(WoodType.class, "cross_timber_frame",
                        getModBlock("oak_cross_timber_frame"), () -> VanillaWoodTypes.OAK,
                        w -> new Block(Utils.copyPropertySafe(w.planks))
                )
                .requiresFromMap(diagonal_timber_frame.blocks) //REASON: recipes
                .addTextureM(modRes("block/oak_cross_timber_frame"),
                        res("block/bus/oak_cross_timber_frame_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("timber_frames"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(cross_timber_frame);

        stripped_post = SimpleEntrySet.builder(WoodType.class, "post", "stripped",
                        getModBlock("stripped_oak_post"), () -> VanillaWoodTypes.OAK,
                        w -> new PostBlock(
                                Utils.copyPropertySafe(Objects.requireNonNull(w.getBlockOfThis("stripped_log")))
                                        .forceSolidOn()
                        )
                )
                .requiresChildren("stripped_log") //REASON: textures, recipes
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("wooden_posts"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("oak_planks_from_stripped_oak_post"))
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(stripped_post);

        post = SimpleEntrySet.builder(WoodType.class, "post",
                        getModBlock("oak_post"), () -> VanillaWoodTypes.OAK,
                        w -> new PostBlock(Utils.copyPropertySafe(w.log).forceSolidOn())
                )
                //TEXTURES: log, log_top
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("wooden_posts"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addRecipe(modRes("oak_planks_from_oak_post"))
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(post);

        small_hedge = SimpleEntrySet.builder(LeavesType.class, "small_hedge",
                        getModBlock("oak_small_hedge"), () -> VanillaLeavesTypes.OAK,
                        leavesType -> new SmallHedgeBlock(Utils.copyPropertySafe(leavesType.leaves)
                                .isSuffocating(BlockFactory::never)
                                .isViewBlocking(BlockFactory::never)
                                .isValidSpawn(BlockFactory::canSpawnOnLeaves)
                        )
                )
                //TEXTURES: leaves
                .addTag(BlockTags.MINEABLE_WITH_HOE, Registries.BLOCK)
                .addTag(modRes("small_hedges"), Registries.BLOCK)
                .addTag(modRes("small_hedges"), Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .copyParentTint()
                .build();
        this.addEntry(small_hedge);

    }

    @Override
    public void onModSetup() {
        post.blocks.forEach((woodType, block) -> {
            if (doChildrenExistFor(woodType, stripped_post)) // For stripping the logs
                StrippableBlockRegistry.register(block, stripped_post.blocks.get(woodType));
        });
    }

    @Override
    // TEXTURES
    public void addDynamicClientResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicClientResources(executor);

        executor.accept((manager, sink) -> {
            String texturePath = "block/acacia_small_logs_top";
            ResourceLocation logTopResLoc = modRes(texturePath);

            try (TextureImage edgeMask = TextureImage.open(manager, res("block/bus/small_logs_top_edge_m"));
                 TextureImage insideMask = TextureImage.open(manager, res("block/bus/small_logs_top_inside_m"))
            ) {
                small_logs.blocks.forEach((woodType, block) -> {
                    ResourceLocation blockId = Utils.getID(block);

                    try (TextureImage baseLogTopTexture = TextureImage.open(manager, logTopResLoc);
                         TextureImage logSideTexture = TextureImage.open(manager,
                                 RPUtils.findFirstBlockTextureLocation(manager, woodType.log, CompatSpritesHelper.LOOKS_LIKE_SIDE_LOG_TEXTURE));
                         TextureImage planksTexture = TextureImage.open(manager,
                                 RPUtils.findFirstBlockTextureLocation(manager, woodType.planks))
                    ) {

                        String newPath = BlockTypeResTransformer.replaceTypeNoNamespace(texturePath, woodType, blockId, "acacia");

                        // Adding to the resource
                        sink.addTextureIfNotPresent(manager, newPath, () -> 
                                generateLogTopTexture(baseLogTopTexture, logSideTexture, insideMask, planksTexture, edgeMask));

                    } catch (Exception e) {
                        EveryCompat.LOGGER.error("Failed to generate texture for {} : {}", block, e);
                    }
                });
            } catch (Exception e) {
                EveryCompat.LOGGER.error("Failed to open the mask texture: ", e);
            }

        });
    }

    private TextureImage generateLogTopTexture(TextureImage baseTexture, TextureImage logSideTexture, TextureImage insideMask,
                                               TextureImage planksTexture, TextureImage edgeMask) {

        Respriter targetEdge = Respriter.masked(baseTexture, insideMask);

        try(TextureImage recoloredEdge = targetEdge.recolorWithAnimationOf(logSideTexture)) {

            Respriter targetInside = Respriter.masked(recoloredEdge, edgeMask);

            // Finished Texture
            return targetInside.recolorWithAnimationOf(planksTexture);
        }
    }

    @Override
    public List<String> getAlreadySupportedMods() {
        return List.of("promenade");
    }
}