package net.mehvahdjukaar.every_compat.modules.fabric.clutter;

import net.emilsg.clutter.block.custom.*;
import net.emilsg.clutter.util.ModBlockTags;
import net.emilsg.clutter.util.ModItemGroup;
import net.emilsg.clutter.util.ModItemTags;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
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

import java.util.HashMap;
import java.util.function.ToIntFunction;

//SUPPORT: v0.2-1-FINAL
public class ClutterModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> wall_bookshelves;
    public final SimpleEntrySet<WoodType, Block> window_sills;
    public final SimpleEntrySet<WoodType, Block> tables;
    public final SimpleEntrySet<WoodType, Block> stripped_tables;
    public final SimpleEntrySet<WoodType, Block> chairs;
    public final SimpleEntrySet<WoodType, Block> stripped_chairs;
    public final SimpleEntrySet<WoodType, Block> wall_cupboards;
    public final SimpleEntrySet<WoodType, Block> cupboards;
    public final SimpleEntrySet<WoodType, Block> trellises;
    public final SimpleEntrySet<WoodType, Block> benches;
    public final SimpleEntrySet<WoodType, Block> stripped_benches;

    public final HashMap<Block, Block> mapTables = new HashMap<>();
    public final HashMap<Block, Block> mapChairs = new HashMap<>();
//    public final HashMap<Block, Block> mapBenches = new HashMap<>();

    public ClutterModule(String modId) {
        super(modId, "clu");
        var tab = ModItemGroup.CLUTTER;

        wall_bookshelves = SimpleEntrySet.builder(WoodType.class, "wall_bookshelf",
                        () -> getModBlock("oak_wall_bookshelf"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WallBookshelfBlock(FabricBlockSettings.copy(w.planks)
                                .lightLevel(WallBookshelfBlock.createLightLevelFromLitBlockState(8))
                        )
                )
                .addTile(() -> net.emilsg.clutter.block.entity.ModBlockEntities.WALL_BOOKSHELF)
                //TEXTURE: Using planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModBlockTags.FLAMMABLE, Registry.BLOCK_REGISTRY)
                .addTag(ModBlockTags.BOOKSHELVES, Registry.BLOCK_REGISTRY)
                .addTag(new ResourceLocation("c:bokoshelves"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("bookshelves"), Registry.ITEM_REGISTRY)
                .addTag(modRes("flammable"), Registry.ITEM_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();
        this.addEntry(wall_bookshelves);

        window_sills = SimpleEntrySet.builder(WoodType.class, "window_sill",
                        () -> getModBlock("oak_window_sill"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WindowSillBlock(Utils.copyPropertySafe(w.planks))
                )
                .setRenderType(() -> RenderType::cutout)
                //TEXTURE: Using planks
                .addTag(ModBlockTags.FLAMMABLE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("window_sills"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("window_sills"), Registry.ITEM_REGISTRY)
                .addTag(modRes("flammable"), Registry.ITEM_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();
        this.addEntry(window_sills);

        tables = SimpleEntrySet.builder(WoodType.class, "table",
                        () -> getModBlock("oak_table"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CompatTableBlock(Utils.copyPropertySafe(w.planks))
                )
                //TEXTURE: Using log & planks
                .addTag(ModBlockTags.FLAMMABLE, Registry.BLOCK_REGISTRY)
                .addTag(ModBlockTags.TABLES, Registry.BLOCK_REGISTRY)
                .addTag(ModBlockTags.STRIPPABLE_TABLES, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("tables"), Registry.ITEM_REGISTRY)
                .addTag(modRes("flammable"), Registry.ITEM_REGISTRY)
                .addTag(modRes("strippable_tables"), Registry.ITEM_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                //REASON: Take a look @ terrestria's logs|stripped_logs' non-standard 16x16 texture, you'll get why
                //EXCLUDED: sakura & yucca_palm
                .addCondition(w -> !w.getId().toString().matches("terrestria:(sakura|yucca_palm)"))
                .build();
        this.addEntry(tables);

        stripped_tables = SimpleEntrySet.builder(WoodType.class, "table", "stripped",
                        () -> getModBlock("stripped_oak_table"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CompatTableBlock(Utils.copyPropertySafe(w.planks))
                )
                .requiresChildren("stripped_log") //REASON: recipes & textures
                //TEXTURE: Using stripped_log & planks
                .addTag(ModBlockTags.FLAMMABLE, Registry.BLOCK_REGISTRY)
                .addTag(ModBlockTags.TABLES, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("flammable"), Registry.ITEM_REGISTRY)
                .addTag(modRes("tables"), Registry.ITEM_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                //REASON: Take a look @ terrestria's logs|stripped_logs' non-standard 16x16 texture, you'll get why
                //EXCLUDED: sakura & yucca_palm
                .addCondition(w -> !w.getId().toString().matches("terrestria:(sakura|yucca_palm)"))
                .build();
        this.addEntry(stripped_tables);


        chairs = SimpleEntrySet.builder(WoodType.class, "chair",
                        () -> getModBlock("oak_chair"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CompatChairBlock(Utils.copyPropertySafe(w.planks))
                )
                //TEXTURE: Using log & planks
                .addTag(ModBlockTags.FLAMMABLE, Registry.BLOCK_REGISTRY)
                .addTag(ModBlockTags.STRIPPABLE_CHAIRS, Registry.BLOCK_REGISTRY)
                .addTag(ModBlockTags.WOODEN_CHAIRS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("wooden_chairs"), Registry.ITEM_REGISTRY)
                .addTag(modRes("strippable_chairs"), Registry.ITEM_REGISTRY)
                .addTag(modRes("flammable"), Registry.ITEM_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                //REASON: Take a look @ terrestria's logs|stripped_logs' non-standard 16x16 texture, you'll get why
                //EXCLUDED: sakura & yucca_palm
                .addCondition(w -> !w.getId().toString().matches("terrestria:(sakura|yucca_palm)"))
                .build();
        this.addEntry(chairs);

        stripped_chairs = SimpleEntrySet.builder(WoodType.class, "chair", "stripped",
                        () -> getModBlock("stripped_oak_chair"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CompatChairBlock(Utils.copyPropertySafe(w.planks))
                )
                .requiresChildren("stripped_log") //REASON: recipes & textures
                //TEXTURE: Using stripped_log & planks
                .addTag(ModBlockTags.FLAMMABLE, Registry.BLOCK_REGISTRY)
                .addTag(ModBlockTags.WOODEN_CHAIRS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("flammable"), Registry.ITEM_REGISTRY)
                .addTag(modRes("wooden_chairs"), Registry.ITEM_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                //REASON: Take a look @ terrestria's logs|stripped_logs' non-standard 16x16 texture, you'll get why
                //EXCLUDED: sakura & yucca_palm
                .addCondition(w -> !w.getId().toString().matches("terrestria:(sakura|yucca_palm)"))
                .build();
        this.addEntry(stripped_chairs);


        cupboards = SimpleEntrySet.builder(WoodType.class, "cupboard",
                        () -> getModBlock("oak_cupboard"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CupboardBlock(FabricBlockSettings.copy(w.planks).noOcclusion())
                )
                .addTile(() -> net.emilsg.clutter.block.entity.ModBlockEntities.CUPBOARD)
                //TEXTURE: Using planks
                .addTag(ModBlockTags.FLAMMABLE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("cupboards"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("flammable"), Registry.ITEM_REGISTRY)
                .addTag(modRes("cupboards"), Registry.ITEM_REGISTRY)
                .addTexture(modRes("block/oak_cupboard_inside"))
                .addTexture(modRes("block/oak_cupboard_doors"))
                .setTab(() -> tab)
                .defaultRecipe()
                .build();
        this.addEntry(cupboards);

        wall_cupboards = SimpleEntrySet.builder(WoodType.class, "wall_cupboard",
                        () -> getModBlock("oak_wall_cupboard"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WallCupboardBlock(FabricBlockSettings.copy(w.planks).noOcclusion())
                )
                .addTile(() -> net.emilsg.clutter.block.entity.ModBlockEntities.WALL_CUPBOARD)
                //TEXTURE: using cupboards' above
                .addTag(ModBlockTags.FLAMMABLE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("cupboards"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("flammable"), Registry.ITEM_REGISTRY)
                .addTag(modRes("cupboards"), Registry.ITEM_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();
        this.addEntry(wall_cupboards);

        trellises = SimpleEntrySet.builder(WoodType.class, "trellis",
                        () -> getModBlock("oak_trellis"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrellisBlock(FabricBlockSettings.copy(w.planks)
                                .lightLevel(TrellisBlock.createLightLevelFromLitBlockState()))
                )
                .setRenderType(() -> RenderType::cutoutMipped)
                //TEXTURE: Using log
                .addTag(ModBlockTags.FLAMMABLE, Registry.BLOCK_REGISTRY)
                .addTag(ModBlockTags.TRELLISES, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.CLIMBABLE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.FALL_DAMAGE_RESETTING, Registry.BLOCK_REGISTRY)
                .addTag(modRes("flammable"), Registry.ITEM_REGISTRY)
                .addTag(modRes("trellises"), Registry.ITEM_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();
        this.addEntry(trellises);

        benches = SimpleEntrySet.builder(WoodType.class, "bench",
                        () -> getModBlock("oak_bench"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WoodenBenchBlock(Utils.copyPropertySafe(w.planks))
                )
                //TEXTURE: Using log & planks
                .addTag(ModBlockTags.FLAMMABLE, Registry.BLOCK_REGISTRY)
                .addTag(ModBlockTags.BENCHES, Registry.BLOCK_REGISTRY)
                .addTag(ModBlockTags.STRIPPABLE_BENCHES, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("flammable"), Registry.ITEM_REGISTRY)
                .addTag(modRes("benches"), Registry.ITEM_REGISTRY)
                .addTag(modRes("strippable_benches"), Registry.ITEM_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                //REASON: Take a look @ terrestria's logs|stripped_logs' non-standard 16x16 texture, you'll get why
                //EXCLUDED: sakura & yucca_palm
                .addCondition(w -> !w.getId().toString().matches("terrestria:(sakura|yucca_palm)"))
                .build();
        this.addEntry(benches);

        stripped_benches = SimpleEntrySet.builder(WoodType.class, "bench", "stripped",
                        () -> getModBlock("stripped_oak_bench"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WoodenBenchBlock(Utils.copyPropertySafe(w.planks))
                )
                .requiresChildren("stripped_log") //REASON: recipes & textures
                //TEXTURE: Using stripped_log & planks
                .addTag(ModBlockTags.FLAMMABLE, Registry.BLOCK_REGISTRY)
                .addTag(ModBlockTags.BENCHES, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("flammable"), Registry.ITEM_REGISTRY)
                .addTag(modRes("benches"), Registry.ITEM_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                //REASON: Take a look @ terrestria's logs|stripped_logs' non-standard 16x16 texture, you'll get why
                //EXCLUDED: sakura & yucca_palm
                .addCondition(w -> !w.getId().toString().matches("terrestria:(sakura|yucca_palm)"))
                .build();
        this.addEntry(stripped_benches);

    }

// METHODS

    // Making these blocks be strippable
    @Override
    public void onModSetup() {
        super.onModSetup();

        tables.blocks.forEach((wood, block) -> {
            putBlocksIn(mapTables, block, stripped_tables.blocks.get(wood));

            //CHAIRS
            putBlocksIn(mapChairs, chairs.blocks.get(wood), stripped_chairs.blocks.get(wood));

            //BENCHES
//            putBlocksIn(mapBenches, benches.blocks.get(wood), stripped_benches.blocks.get(wood));
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
        public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
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
        public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
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


//    public class CompatBenchBlock extends WoodenBenchBlock {
//
//        public CompatBenchBlock(Properties properties) {
//            super(properties);
//        }
//
//        private BlockState getStrippedState(BlockState state) {
//            return mapBenches.get(state.getBlock()).defaultBlockState().setValue(FACING, state.getValue(FACING))
//                    .setValue(LEGPOSITIONS, state.getValue(LEGPOSITIONS));
//        }
//
//        @Override
//        public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
//            ItemStack itemStack = player.getItemInHand(hand);
//            if (itemStack.getItem() instanceof AxeItem && state.is(ModBlockTags.STRIPPABLE_BENCHES)) {
//                BlockState strippedState = this.getStrippedState(state);
//                world.setBlockAndUpdate(pos, strippedState);
//                world.playSound(null, pos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
//                if (!player.isCreative()) {
//                    itemStack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(hand));
//                }
//
//                return InteractionResult.SUCCESS;
//            } else {
//                return InteractionResult.PASS;
//            }
//        }
//
//    }
}