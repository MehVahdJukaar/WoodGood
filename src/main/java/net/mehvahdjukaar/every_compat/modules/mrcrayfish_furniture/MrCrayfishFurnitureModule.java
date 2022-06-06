package net.mehvahdjukaar.every_compat.modules.mrcrayfish_furniture;

import com.mrcrayfish.furniture.FurnitureMod;
import com.mrcrayfish.furniture.block.*;
import com.mrcrayfish.furniture.client.renderer.tileentity.KitchenSinkBlockEntityRenderer;
import com.mrcrayfish.furniture.common.ModTags;
import com.mrcrayfish.furniture.core.ModBlocks;
import com.mrcrayfish.furniture.tileentity.KitchenSinkBlockEntity;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.selene.block_set.BlockType;
import net.mehvahdjukaar.selene.block_set.leaves.LeavesType;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.mehvahdjukaar.selene.client.asset_generators.LangBuilder;
import net.mehvahdjukaar.selene.resourcepack.AfterLanguageLoadEvent;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.event.EntityRenderersEvent;

public class MrCrayfishFurnitureModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> BENCHES;
    public final SimpleEntrySet<WoodType, Block> CABINETS;
    public final SimpleEntrySet<WoodType, Block> COFFEE_TABLES;
    public final SimpleEntrySet<WoodType, Block> DESKS;
    public final SimpleEntrySet<WoodType, Block> KITCHEN_COUNTERS;
    public final SimpleEntrySet<WoodType, Block> KITCHEN_SINK_DARK;
    public final SimpleEntrySet<WoodType, Block> KITCHEN_SINK_LIGHT;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_BENCHES;
    public final SimpleEntrySet<WoodType, Block> TABLES;
    public final SimpleEntrySet<WoodType, Block> UPGRADED_FENCES;
    public final SimpleEntrySet<WoodType, Block> UPGRADED_GATES;
//    public final SimpleEntrySet<LeavesType, Block> HEDGES;

    public MrCrayfishFurnitureModule(String modId) {
        super(modId, "cfm");

        BENCHES = SimpleEntrySet.builder("park_bench",
                        ModBlocks.PARK_BENCH_OAK, ()-> WoodType.OAK_WOOD_TYPE,
                        w -> new ParkBenchBlock(BlockBehaviour.Properties.copy(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.OUTDOORS, Registry.ITEM_REGISTRY)
                .addTag(ModTags.Items.STORAGE, Registry.ITEM_REGISTRY)
                .setTab(FurnitureMod.GROUP)
                .addRecipe(modRes("oak_park_bench"))
                .setRenderType(()->RenderType::cutout)
                .build();

        this.addEntry(BENCHES);

        STRIPPED_BENCHES = SimpleEntrySet.builder("park_bench", "stripped",
                        ModBlocks.PARK_BENCH_STRIPPED_OAK, ()-> WoodType.OAK_WOOD_TYPE,
                        w -> new ParkBenchBlock(BlockBehaviour.Properties.copy(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.OUTDOORS, Registry.ITEM_REGISTRY)
                .addTag(ModTags.Items.STORAGE, Registry.ITEM_REGISTRY)
                .setTab(FurnitureMod.GROUP)
                .addRecipe(modRes("stripped_oak_park_bench"))
                .setRenderType(()->RenderType::cutout)
                .build();

        this.addEntry(STRIPPED_BENCHES);

        CABINETS = SimpleEntrySet.builder("cabinet",
                        ModBlocks.CABINET_OAK, ()-> WoodType.OAK_WOOD_TYPE,
                        w -> new CabinetBlock(BlockBehaviour.Properties.copy(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.STORAGE, Registry.ITEM_REGISTRY)
                .setTab(FurnitureMod.GROUP)
                .addRecipe(modRes("oak_cabinet"))
                .setRenderType(()->RenderType::cutout)
                .build();

        this.addEntry(CABINETS);

        COFFEE_TABLES = SimpleEntrySet.builder("coffee_table",
                        ModBlocks.COFFEE_TABLE_OAK, ()-> WoodType.OAK_WOOD_TYPE,
                        w -> new CoffeeTableBlock(BlockBehaviour.Properties.copy(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.GENERAL, Registry.ITEM_REGISTRY)
                .setTab(FurnitureMod.GROUP)
                .addRecipe(modRes("oak_coffee_table"))
                .setRenderType(()->RenderType::cutout)
                .build();

        this.addEntry(COFFEE_TABLES);

        DESKS = SimpleEntrySet.builder("desk",
                        ModBlocks.DESK_OAK, ()-> WoodType.OAK_WOOD_TYPE,
                        w -> new DeskBlock(BlockBehaviour.Properties.copy(w.planks), DeskBlock.MaterialType.OAK))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.BEDROOM, Registry.ITEM_REGISTRY)
                .setTab(FurnitureMod.GROUP)
                .addRecipe(modRes("oak_desk"))
                .setRenderType(()->RenderType::cutout)
                .build();

        this.addEntry(DESKS);

        KITCHEN_COUNTERS = SimpleEntrySet.builder("kitchen_counter",
                        ModBlocks.KITCHEN_COUNTER_OAK, ()-> WoodType.OAK_WOOD_TYPE,
                        w -> new KitchenCounterBlock(BlockBehaviour.Properties.copy(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.KITCHEN, Registry.ITEM_REGISTRY)
                .setTab(FurnitureMod.GROUP)
                .addRecipe(modRes("oak_kitchen_counter"))
                .setRenderType(()->RenderType::cutout)
                .build();

        this.addEntry(KITCHEN_COUNTERS);

        KITCHEN_SINK_DARK = SimpleEntrySet.builder("kitchen_sink_dark",
                        ModBlocks.KITCHEN_SINK_DARK_OAK, ()-> WoodType.OAK_WOOD_TYPE,
                        w -> new CompatKitchenSinkDarkBlock(BlockBehaviour.Properties.copy(w.planks), true))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.KITCHEN, Registry.ITEM_REGISTRY)
                .setTab(FurnitureMod.GROUP)
                .addRecipe(modRes("oak_kitchen_sink_dark"))
                .addTile(CompatKitchenSinkDarkBlockEntity::new)
                .setRenderType(()->RenderType::cutout)
                .build();

        this.addEntry(KITCHEN_SINK_DARK);

        KITCHEN_SINK_LIGHT = SimpleEntrySet.builder("kitchen_sink_light",
                        ModBlocks.KITCHEN_SINK_LIGHT_OAK, ()-> WoodType.OAK_WOOD_TYPE,
                        w -> new CompatKitchenSinkLightBlock(BlockBehaviour.Properties.copy(w.planks), true))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.KITCHEN, Registry.ITEM_REGISTRY)
                .setTab(FurnitureMod.GROUP)
                .addRecipe(modRes("oak_kitchen_sink_light"))
                .addTile(CompatKitchenSinkLightBlockEntity::new)
                .setRenderType(()->RenderType::cutout)
                .build();

        this.addEntry(KITCHEN_SINK_LIGHT);

        TABLES = SimpleEntrySet.builder("table",
                        ModBlocks.TABLE_OAK, ()-> WoodType.OAK_WOOD_TYPE,
                        w -> new TableBlock(BlockBehaviour.Properties.copy(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.GENERAL, Registry.ITEM_REGISTRY)
                .setTab(FurnitureMod.GROUP)
                .addRecipe(modRes("oak_table"))
                .setRenderType(()->RenderType::cutout)
                .build();

        this.addEntry(TABLES);

        UPGRADED_FENCES = SimpleEntrySet.builder("upgraded_fence",
                        ModBlocks.UPGRADED_FENCE_OAK, ()-> WoodType.OAK_WOOD_TYPE,
                        w -> new UpgradedFenceBlock(BlockBehaviour.Properties.copy(w.planks)))
                .addTag(BlockTags.FENCES, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Blocks.UPGRADED_FENCES, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.OUTDOORS, Registry.ITEM_REGISTRY)
                .addTag(ModTags.Items.UPGRADED_FENCES, Registry.ITEM_REGISTRY)
                .setTab(FurnitureMod.GROUP)
                .addRecipe(modRes("oak_upgraded_fence"))
                .setRenderType(()->RenderType::cutout)
                .build();

        this.addEntry(UPGRADED_FENCES);

        UPGRADED_GATES = SimpleEntrySet.builder("upgraded_gate",
                        ModBlocks.UPGRADED_GATE_OAK, ()-> WoodType.OAK_WOOD_TYPE,
                        w -> new UpgradedGateBlock(BlockBehaviour.Properties.copy(w.planks)))
                .addTag(BlockTags.FENCE_GATES, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.UNSTABLE_BOTTOM_CENTER, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Blocks.UPGRADED_FENCE_GATES, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.Items.OUTDOORS, Registry.ITEM_REGISTRY)
                .addTag(ModTags.Items.UPGRADED_FENCE_GATES, Registry.ITEM_REGISTRY)
                .setTab(FurnitureMod.GROUP)
                .addRecipe(modRes("oak_upgraded_gate"))
                .setRenderType(()->RenderType::cutout)
                .build();

        this.addEntry(UPGRADED_GATES);

//        HEDGES = SimpleEntrySet.builder("hedge",
//                        ModBlocks.HEDGE_OAK, ()-> LeavesType.OAK_LEAVES_TYPE,
//                        w -> new HedgeBlock(BlockBehaviour.Properties.copy(w.leaves)))
//                .addTag(ModTags.Blocks.HEDGES, Registry.BLOCK_REGISTRY)
//                .addTag(ModTags.Items.HEDGES, Registry.ITEM_REGISTRY)
//                .addTag(ModTags.Items.OUTDOORS, Registry.ITEM_REGISTRY)
//                .setTab(FurnitureMod.GROUP)
//                .addRecipe(modRes("oak_hedge"))
//                .setRenderType(()->RenderType::cutout)
//                .build();

//        this.addEntry(HEDGES);
    }

    @Override
    public void addTranslations(ClientDynamicResourcesHandler clientDynamicResourcesHandler, AfterLanguageLoadEvent lang) {
        super.addTranslations(clientDynamicResourcesHandler, lang);
        STRIPPED_BENCHES.items.forEach((w, v) -> LangBuilder.addDynamicEntry(lang, "block.everycomp.stripped_bench",(BlockType) w, v));
    }

    @Override
    public void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer((BlockEntityType<KitchenSinkBlockEntity>) (KITCHEN_SINK_DARK.getTileHolder().tile),
                KitchenSinkBlockEntityRenderer::new);
        event.registerBlockEntityRenderer((BlockEntityType<KitchenSinkBlockEntity>) (KITCHEN_SINK_LIGHT.getTileHolder().tile),
                KitchenSinkBlockEntityRenderer::new);
    }

    class CompatKitchenSinkDarkBlockEntity extends KitchenSinkBlockEntity {

        public CompatKitchenSinkDarkBlockEntity(BlockPos pos, BlockState state) {
            super(pos, state);
        }

        @Override
        public BlockEntityType<?> getType() {
            return KITCHEN_SINK_DARK.getTileHolder().tile;
        }
    }

    private class CompatKitchenSinkDarkBlock extends KitchenSinkBlock {
        private boolean bigSink;
        public CompatKitchenSinkDarkBlock(Properties properties, boolean bigSink) {
            super(properties, bigSink);
            this.bigSink = bigSink;
        }

        public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
            return new CompatKitchenSinkDarkBlockEntity(pos, state);
        }
    }

    class CompatKitchenSinkLightBlockEntity extends KitchenSinkBlockEntity {

        public CompatKitchenSinkLightBlockEntity(BlockPos pos, BlockState state) {
            super(pos, state);
        }

        @Override
        public BlockEntityType<?> getType() {
            return KITCHEN_SINK_LIGHT.getTileHolder().tile;
        }
    }

    private class CompatKitchenSinkLightBlock extends KitchenSinkBlock {
        private boolean bigSink;
        public CompatKitchenSinkLightBlock(Properties properties, boolean bigSink) {
            super(properties, bigSink);
            this.bigSink = bigSink;
        }

        public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
            return new CompatKitchenSinkLightBlockEntity(pos, state);
        }
    }
}
