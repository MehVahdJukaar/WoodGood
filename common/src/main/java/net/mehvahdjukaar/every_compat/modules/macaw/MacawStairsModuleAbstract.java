package net.mehvahdjukaar.every_compat.modules.macaw;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

import static net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys.*;

//SUPPORT: v1.0.1+
public abstract class MacawStairsModuleAbstract extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> terrace_stairs;
    public final SimpleEntrySet<WoodType, Block> skyline_stairs;
    public final SimpleEntrySet<WoodType, Block> compact_stairs;
    public final SimpleEntrySet<WoodType, Block> bulk_stairs;
    public final SimpleEntrySet<WoodType, Block> loft_stairs;
    public final SimpleEntrySet<WoodType, Block> balconies;
    public final SimpleEntrySet<WoodType, Block> railings;
    public final SimpleEntrySet<WoodType, Block> platforms;

    public MacawStairsModuleAbstract(String modId) {
        super(modId, "mws", EveryCompat.MOD_ID);
        ResourceLocation tab = modRes(modId);

        terrace_stairs = SimpleEntrySet.builder(WoodType.class, "terrace_stairs",
                        getModBlock("oak_terrace_stairs"), () -> VanillaWoodTypes.OAK,
                        this::newTerraceStairs
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures, recipes
                //TEXTURES: log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("terrace_stairs"), Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                //REASON: take a look at their textures, you'll see why.
                .excludeBlockTypes("terrestria:(sakura|yucca_palm)|betternether:(nether_mushroom|nether_reed)")
                .excludeBlockTypes("betternether:(nether_mushroom|nether_reed)")
                .build();
        this.addEntry(terrace_stairs);

        skyline_stairs = SimpleEntrySet.builder(WoodType.class, "skyline_stairs",
                        getModBlock("oak_skyline_stairs"), () -> VanillaWoodTypes.OAK,
                        this::newSkylineStairs
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures, recipes
                //TEXTURES: log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("skyline_stairs"), Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                //REASON: take a look at their textures, you'll see why.
                .excludeBlockTypes("terrestria:(sakura|yucca_palm)|betternether:(nether_mushroom|nether_reed)")
                .excludeBlockTypes("betternether:(nether_mushroom|nether_reed)")
                .build();
        this.addEntry(skyline_stairs);

        compact_stairs = SimpleEntrySet.builder(WoodType.class, "compact_stairs",
                        getModBlock("oak_compact_stairs"), () -> VanillaWoodTypes.OAK,
                        this::newCompactStairs
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures, recipes
                //TEXTURES: log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("compact_stairs"), Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                //REASON: take a look at their textures, you'll see why.
                .excludeBlockTypes("terrestria:(sakura|yucca_palm)|betternether:(nether_mushroom|nether_reed)")
                .excludeBlockTypes("betternether:(nether_mushroom|nether_reed)")
                .build();
        this.addEntry(compact_stairs);

        bulk_stairs = SimpleEntrySet.builder(WoodType.class, "bulk_stairs",
                        getModBlock("oak_bulk_stairs"), () -> VanillaWoodTypes.OAK,
                        this::newBulkStairs
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures, recipes
                //TEXTURES: log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("bulk_stairs"), Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                //REASON: take a look at their textures, you'll see why.
                .excludeBlockTypes("terrestria:(sakura|yucca_palm)|betternether:(nether_mushroom|nether_reed)")
                .excludeBlockTypes("betternether:(nether_mushroom|nether_reed)")
                .build();
        this.addEntry(bulk_stairs);

        loft_stairs = SimpleEntrySet.builder(WoodType.class, "loft_stairs",
                        getModBlock("oak_loft_stairs"), () -> VanillaWoodTypes.OAK,
                        this::newLoftStairs
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures, recipes
                //TEXTURES: log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("loft_stairs"), Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                //REASON: take a look at their textures, you'll see why.
                .excludeBlockTypes("terrestria:(sakura|yucca_palm)|betternether:(nether_mushroom|nether_reed)")
                .excludeBlockTypes("betternether:(nether_mushroom|nether_reed)")
                .build();
        this.addEntry(loft_stairs);

        balconies = SimpleEntrySet.builder(WoodType.class, "balcony",
                        getModBlock("oak_balcony"), () -> VanillaWoodTypes.OAK,
                        this::newBalconyRailing
                )
                .requiresChildren(FENCE, STRIPPED_LOG) //REASON: textures, recipes
                //TEXTURES: log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("balconies"), Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .noDrops() //REASON: Has no loot_table file so the block will self-drop just fine
                .defaultRecipe()
                //REASON: take a look at their textures, you'll see why.
                .excludeBlockTypes("terrestria:(sakura|yucca_palm)|betternether:(nether_mushroom|nether_reed)")
                .excludeBlockTypes("betternether:(nether_mushroom|nether_reed)")
                .build();
        this.addEntry(balconies);

        railings = SimpleEntrySet.builder(WoodType.class, "railing",
                        getModBlock("oak_railing"), () -> VanillaWoodTypes.OAK,
                        this::newStairRailing
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                .requiresFromMap(balconies.blocks) //REASON: recipes
                //TEXTURES: log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("railings"), Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                //REASON: take a look at their textures, you'll see why.
                .excludeBlockTypes("terrestria:(sakura|yucca_palm)|betternether:(nether_mushroom|nether_reed)")
                .excludeBlockTypes("betternether:(nether_mushroom|nether_reed)")
                .build();
        this.addEntry(railings);

        platforms = SimpleEntrySet.builder(WoodType.class, "platform",
                        getModBlock("oak_platform"), () -> VanillaWoodTypes.OAK,
                        this::newStairPlatform
                )
                .requiresChildren(SLAB, STRIPPED_LOG) //REASON: textures, recipes
                //TEXTURES: log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("platforms"), Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                //REASON: take a look at their textures, you'll see why.
                .excludeBlockTypes("terrestria:(sakura|yucca_palm)|betternether:(nether_mushroom|nether_reed)")
                .excludeBlockTypes("betternether:(nether_mushroom|nether_reed)")
                .build();
        this.addEntry(platforms);

    }

    public abstract Block newTerraceStairs(WoodType woodType);
    public abstract Block newSkylineStairs(WoodType woodType);
    public abstract Block newCompactStairs(WoodType woodType);
    public abstract Block newBulkStairs(WoodType woodType);
    public abstract Block newLoftStairs(WoodType woodType);
    public abstract Block newBalconyRailing(WoodType woodType);
    public abstract Block newStairRailing(WoodType woodType);
    public abstract Block newStairPlatform(WoodType woodType);

    // METHODS
    public BlockBehaviour.Properties copyProperties() {
        return BlockBehaviour.Properties.of().mapColor(MapColor.WOOD)
                .strength(2.0F, 2.3F)
                .sound(SoundType.WOOD);
    }

}