package net.mehvahdjukaar.every_compat.modules.fabric.clutter;

import net.emilsg.clutter.block.ModBlockEntities;
import net.emilsg.clutter.block.custom.*;
import net.emilsg.clutter.util.ModBlockTags;
import net.emilsg.clutter.util.ModItemGroups;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.function.ToIntFunction;

//SUPPORT: v0.6.0+
public class ClutterModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> wall_bookshelves;
    public final SimpleEntrySet<WoodType, Block> window_sills;
    public final SimpleEntrySet<WoodType, Block> tables;
    public final SimpleEntrySet<WoodType, Block> stripped_tables;
    public final SimpleEntrySet<WoodType, Block> chairs;
    public final SimpleEntrySet<WoodType, Block> stripped_chairs;
    public final SimpleEntrySet<WoodType, Block> wall_cupboards;
    public final SimpleEntrySet<WoodType, Block> shelves;
    public final SimpleEntrySet<WoodType, Block> cupboards;
    public final SimpleEntrySet<WoodType, Block> trellises;
    public final SimpleEntrySet<WoodType, Block> benches;
    public final SimpleEntrySet<WoodType, Block> stripped_benches;
    public final SimpleEntrySet<WoodType, Block> mosaic_planks;
    public final SimpleEntrySet<WoodType, Block> mosaic_stairs;
    public final SimpleEntrySet<WoodType, Block> mosaic_slabs;

    public final HashMap<Block, Block> mapTables = new HashMap<>();
    public final HashMap<Block, Block> mapChairs = new HashMap<>();
    public final HashMap<Block, Block> mapBenches = new HashMap<>();

    public ClutterModule(String modId) {
        super(modId, "clu");
        var tab = ModItemGroups.CLUTTER_BLOCKS;

        wall_bookshelves = SimpleEntrySet.builder(WoodType.class, "wall_bookshelf",
                        getModBlock("oak_wall_bookshelf"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WallBookshelfBlock(FabricBlockSettings.copyOf(w.planks)
                                .luminance(createLightLevelFromLitBlockState())
                        )
                )
                .addTile(() -> ModBlockEntities.WALL_BOOKSHELF)
                //TEXTURES: planks
                .addTag(ModBlockTags.FLAMMABLE, Registries.BLOCK)
                .addTag(ModBlockTags.BOOKSHELVES, Registries.BLOCK)
                .addTag(BlockTags.ENCHANTMENT_POWER_PROVIDER, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(ModBlockTags.C_BOOKSHELVES, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(wall_bookshelves);

        window_sills = SimpleEntrySet.builder(WoodType.class, "window_sill",
                        getModBlock("oak_window_sill"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WindowSillBlock(Utils.copyPropertySafe(w.planks))
                )
                .setRenderType(RenderLayer.CUTOUT)
                //TEXTURES: planks
                .addTag(ModBlockTags.FLAMMABLE, Registries.BLOCK)
                .addTag(ModBlockTags.WINDOW_SILLS, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(window_sills);

        tables = SimpleEntrySet.builder(WoodType.class, "table",
                        getModBlock("oak_table"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CompatTableBlock(Utils.copyPropertySafe(w.planks))
                )
                //TEXTURES: log & planks
                .addTag(ModBlockTags.FLAMMABLE, Registries.BLOCK)
                .addTag(ModBlockTags.TABLES, Registries.BLOCK)
                .addTag(ModBlockTags.STRIPPABLE_TABLES, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                //REASON: Take a look at their //TEXTURES, you'll see why. Excluded!
                .excludeBlockTypes("terrestria:(sakura|yucca_palm)|betternether:(nether_mushroom|nether_reed)")
                .build();
        this.addEntry(tables);

        stripped_tables = SimpleEntrySet.builder(WoodType.class, "table", "stripped",
                        getModBlock("stripped_oak_table"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CompatTableBlock(Utils.copyPropertySafe(w.planks))
                )
                .requiresChildren("stripped_log") //REASON: recipes & textures
                //TEXTURES: stripped_log & planks
                .addTag(ModBlockTags.FLAMMABLE, Registries.BLOCK)
                .addTag(ModBlockTags.TABLES, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                //REASON: Take a look at their //TEXTURES, you'll see why. Excluded!
                .excludeBlockTypes("terrestria:(sakura|yucca_palm)|betternether:(nether_mushroom|nether_reed)")
                .build();
        this.addEntry(stripped_tables);


        chairs = SimpleEntrySet.builder(WoodType.class, "chair",
                        getModBlock("oak_chair"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CompatChairBlock(Utils.copyPropertySafe(w.planks))
                )
                //TEXTURES: log & planks
                .addTag(ModBlockTags.FLAMMABLE, Registries.BLOCK)
                .addTag(ModBlockTags.STRIPPABLE_CHAIRS, Registries.BLOCK)
                .addTag(ModBlockTags.WOODEN_CHAIRS, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                //REASON: Take a look at their //TEXTURES, you'll see why. Excluded!
                .excludeBlockTypes("terrestria:(sakura|yucca_palm)|betternether:(nether_mushroom|nether_reed)")
                .build();
        this.addEntry(chairs);

        stripped_chairs = SimpleEntrySet.builder(WoodType.class, "chair", "stripped",
                        getModBlock("stripped_oak_chair"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CompatChairBlock(Utils.copyPropertySafe(w.planks))
                )
                .requiresChildren("stripped_log") //REASON: recipes & textures
                //TEXTURES: stripped_log & planks
                .addTag(ModBlockTags.FLAMMABLE, Registries.BLOCK)
                .addTag(ModBlockTags.WOODEN_CHAIRS, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                //REASON: Take a look at their //TEXTURES, you'll see why. Excluded!
                .excludeBlockTypes("terrestria:(sakura|yucca_palm)|betternether:(nether_mushroom|nether_reed)")
                .build();
        this.addEntry(stripped_chairs);


        cupboards = SimpleEntrySet.builder(WoodType.class, "cupboard",
                        getModBlock("oak_cupboard"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CupboardBlock(FabricBlockSettings.copyOf(w.planks).nonOpaque())
                )
                .addTile(() -> ModBlockEntities.CUPBOARD)
                //TEXTURES: planks
                .addTag(ModBlockTags.FLAMMABLE, Registries.BLOCK)
                .addTag(ModBlockTags.CUPBOARDS, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTexture(modRes("block/oak_cupboard_inside"))
                .addTexture(modRes("block/oak_cupboard_door"))
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(cupboards);

        wall_cupboards = SimpleEntrySet.builder(WoodType.class, "wall_cupboard",
                        getModBlock("oak_wall_cupboard"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WallCupboardBlock(FabricBlockSettings.copyOf(w.planks).nonOpaque())
                )
                .addTile(() -> ModBlockEntities.WALL_CUPBOARD)
                //TEXTURES: using cupboards' above
                .addTag(ModBlockTags.FLAMMABLE, Registries.BLOCK)
                .addTag(ModBlockTags.CUPBOARDS, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(wall_cupboards);

        shelves = SimpleEntrySet.builder(WoodType.class, "shelf",
                        getModBlock("oak_shelf"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ShelfBlock(FabricBlockSettings.copyOf(w.planks).nonOpaque())
                )
                .addTile(() -> ModBlockEntities.SHELF)
                //TEXTURES: log & planks
                .addTag(ModBlockTags.SHELVES, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                //REASON: Take a look at their //TEXTURES, you'll see why. Excluded!
                .excludeBlockTypes("terrestria:(sakura|yucca_palm)")
                .build();
        this.addEntry(shelves);

        trellises = SimpleEntrySet.builder(WoodType.class, "trellis",
                        getModBlock("oak_trellis"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrellisBlock(FabricBlockSettings.copyOf(w.planks)
                                .luminance(TrellisBlock.createLightLevelFromLitBlockState()))
                )
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //TEXTURES: log
                .addTag(ModBlockTags.FLAMMABLE, Registries.BLOCK)
                .addTag(ModBlockTags.TRELLISES, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.CLIMBABLE, Registries.BLOCK)
                .addTag(BlockTags.FALL_DAMAGE_RESETTING, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(trellises);

        benches = SimpleEntrySet.builder(WoodType.class, "bench",
                        getModBlock("oak_bench"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CompatBenchBlock(Utils.copyPropertySafe(w.planks))
                )
                //TEXTURES: log & planks
                .addTag(ModBlockTags.FLAMMABLE, Registries.BLOCK)
                .addTag(ModBlockTags.BENCHES, Registries.BLOCK)
                .addTag(ModBlockTags.STRIPPABLE_BENCHES, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                //REASON: Take a look at their //TEXTURES, you'll see why. Excluded!
                .excludeBlockTypes("terrestria:(sakura|yucca_palm)")
                .build();
        this.addEntry(benches);

        stripped_benches = SimpleEntrySet.builder(WoodType.class, "bench", "stripped",
                        getModBlock("stripped_oak_bench"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CompatBenchBlock(Utils.copyPropertySafe(w.planks))
                )
                .requiresChildren("stripped_log") //REASON: recipes & textures
                //TEXTURES: stripped_log & planks
                .addTag(ModBlockTags.FLAMMABLE, Registries.BLOCK)
                .addTag(ModBlockTags.BENCHES, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                //REASON: Take a look at their //TEXTURES, you'll see why. Excluded!
                .excludeBlockTypes("terrestria:(sakura|yucca_palm)")
                .build();
        this.addEntry(stripped_benches);

        mosaic_planks = SimpleEntrySet.builder(WoodType.class, "mosaic",
                        getModBlock("oak_mosaic"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks))
                )
                .addTexture(modRes("block/oak_mosaic"))
                .addTag(ModBlockTags.FLAMMABLE, Registries.BLOCK)
                .addTag(ModBlockTags.WOODEN_MOSAICS, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(mosaic_planks);

        mosaic_stairs = SimpleEntrySet.builder(WoodType.class, "mosaic_stairs",
                        getModBlock("oak_mosaic_stairs"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new StairBlock(copyStairs(w),
                                Utils.copyPropertySafe(w.planks))
                )
                .setRenderType(RenderLayer.CUTOUT)
                //TEXTURES: using mosaic_planks' above
                .addTag(ModBlockTags.FLAMMABLE, Registries.BLOCK)
                .addTag(ModBlockTags.WOODEN_MOSAICS, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.STAIRS, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(mosaic_stairs);

        mosaic_slabs = SimpleEntrySet.builder(WoodType.class, "mosaic_slab",
                        getModBlock("oak_mosaic_slab"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new SlabBlock(copySlabs(w))
                )
                .setRenderType(RenderLayer.CUTOUT)
                //TEXTURES: using mosaic_planks' above
                .addTag(ModBlockTags.FLAMMABLE, Registries.BLOCK)
                .addTag(ModBlockTags.WOODEN_MOSAICS, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.SLABS, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_SLABS, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(mosaic_slabs);


    }

// METHODS
    private static ToIntFunction<BlockState> createLightLevelFromLitBlockState() {
        return (state) -> (Boolean)state.getValue(BlockStateProperties.LIT) ? 8 : 0;
    }

    public BlockState copyStairs(WoodType woodType) {
        Block stairs = woodType.getBlockOfThis("stairs");
        return (stairs != null) ? stairs.defaultBlockState() : Blocks.OAK_STAIRS.defaultBlockState();
    }

    public BlockBehaviour.Properties copySlabs(WoodType woodType) {
        Block slab = woodType.getBlockOfThis("slab");
        return (slab != null) ? Utils.copyPropertySafe(slab): Utils.copyPropertySafe(Blocks.OAK_SLAB);
    }

    // Making these blocks be strippable
    @Override
    public void onModSetup() {
        super.onModSetup();

        tables.blocks.forEach((wood, block) -> {
            putBlocksIn(mapTables, block, stripped_tables.blocks.get(wood));

            //CHAIRS
            putBlocksIn(mapChairs, chairs.blocks.get(wood), stripped_chairs.blocks.get(wood));

            //BENCHES
            putBlocksIn(mapBenches, benches.blocks.get(wood), stripped_benches.blocks.get(wood));
        });
    }

    private void putBlocksIn(HashMap<Block, Block> hashmap, Block block, Block strippedBlock) {
            hashmap.computeIfAbsent(block, key -> strippedBlock);
    }


// COMPAT CLASSES
    public class CompatTableBlock extends TableBlock {

        public CompatTableBlock(Properties properties) {
            super(properties);
        }

        private BlockState getStrippedState(BlockState state) {
            return mapTables.get(state.getBlock()).defaultBlockState().setValue(LEGS, state.getValue(LEGS)).setValue(LEG_POSITIONS, state.getValue(LEG_POSITIONS));
        }

        @Override
        public @NotNull InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
            ItemStack itemStack = player.getItemInHand(hand);

            if (itemStack.getItem() instanceof AxeItem && state.is(ModBlockTags.STRIPPABLE_TABLES)) {
                BlockState strippedState = this.getStrippedState(state);

                world.setBlockAndUpdate(pos, strippedState);
                world.playSound(null, pos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);

                if (!player.isCreative()) {
                    itemStack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(hand));
                }

                return InteractionResult.SUCCESS;
            } else {
                return InteractionResult.PASS;
            }
        }
    }

    public class CompatChairBlock extends WoodenChairBlock {
        public CompatChairBlock(Properties properties) {
            super(properties);
        }

        private BlockState getStrippedState(BlockState state) {
            return mapChairs.get(state.getBlock()).defaultBlockState().setValue(FACING, state.getValue(FACING));
        }

        @Override
        public @NotNull InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
            ItemStack itemStack = player.getItemInHand(hand);
            if (itemStack.getItem() instanceof AxeItem && state.is(ModBlockTags.STRIPPABLE_CHAIRS)) {
                BlockState strippedState = this.getStrippedState(state);
                world.setBlockAndUpdate(pos, strippedState);
                world.playSound(null, pos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
                if (!player.isCreative()) {
                    itemStack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(hand));
                }

                return InteractionResult.SUCCESS;
            } else {
                return InteractionResult.PASS;
            }
        }
    }

    public class CompatBenchBlock extends WoodenBenchBlock {

        public CompatBenchBlock(Properties properties) {
            super(properties);
        }

        private BlockState getStrippedState(BlockState state) {
            return mapBenches.get(state.getBlock()).defaultBlockState().setValue(FACING, state.getValue(FACING))
                    .setValue(LEG_POSITIONS, state.getValue(LEG_POSITIONS));
        }

        @Override
        public @NotNull InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
            ItemStack itemStack = player.getItemInHand(hand);
            if (itemStack.getItem() instanceof AxeItem && state.is(ModBlockTags.STRIPPABLE_BENCHES)) {
                BlockState strippedState = this.getStrippedState(state);
                world.setBlockAndUpdate(pos, strippedState);
                world.playSound(null, pos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
                if (!player.isCreative()) {
                    itemStack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(hand));
                }

                return InteractionResult.SUCCESS;
            } else {
                return InteractionResult.PASS;
            }
        }

    }
}