package net.mehvahdjukaar.every_compat.modules.handcrafted;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import earth.terrarium.handcrafted.common.block.chair.bench.BenchBlock;
import earth.terrarium.handcrafted.common.block.chair.bench.BenchBlockEntity;
import earth.terrarium.handcrafted.common.block.chair.chair.ChairBlock;
import earth.terrarium.handcrafted.common.block.chair.chair.ChairBlockEntity;
import earth.terrarium.handcrafted.common.block.chair.couch.CouchBlock;
import earth.terrarium.handcrafted.common.block.chair.couch.CouchBlockEntity;
import earth.terrarium.handcrafted.common.block.chair.couch.ExpandableCouchBlock;
import earth.terrarium.handcrafted.common.block.property.CouchShape;
import earth.terrarium.handcrafted.common.block.property.SheetState;
import earth.terrarium.handcrafted.common.block.property.TableState;
import earth.terrarium.handcrafted.common.block.table.table.TableBlock;
import earth.terrarium.handcrafted.common.block.table.table.TableBlockEntity;
import earth.terrarium.handcrafted.common.item.RenderedBlockItem;
import earth.terrarium.handcrafted.common.registry.ModBlocks;
import earth.terrarium.handcrafted.common.registry.ModItems;
import earth.terrarium.handcrafted.common.registry.ModTags;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.client.ICustomItemRendererProvider;
import net.mehvahdjukaar.moonlight.api.client.ItemStackRenderer;
import net.mehvahdjukaar.moonlight.api.platform.ClientPlatformHelper;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;


public class HandcraftedModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> CHAIR;
    public final SimpleEntrySet<WoodType, Block> TABLE;
//    public final SimpleEntrySet<WoodType, Block> BENCH;
    public final SimpleEntrySet<WoodType, Block> COUCH;
//    public final SimpleEntrySet<WoodType, Block> FANCY_BED;
//    public final SimpleEntrySet<WoodType, Block> DINING_BENCH;
//    public final SimpleEntrySet<WoodType, Block> NIGHTSTAND;
//    public final SimpleEntrySet<WoodType, Block> DESK;
//    public final SimpleEntrySet<WoodType, Block> SIDE_TABLE;
//    public final SimpleEntrySet<WoodType, Block> COUNTER_1;
//    public final SimpleEntrySet<WoodType, Block> COUNTER_2;
//    public final SimpleEntrySet<WoodType, Block> COUNTER_3;
//    public final SimpleEntrySet<WoodType, Block> CUPBOARD_1;
//    public final SimpleEntrySet<WoodType, Block> CUPBOARD_2;
//    public final SimpleEntrySet<WoodType, Block> DRAWER_1;
//    public final SimpleEntrySet<WoodType, Block> DRAWER_2;
//    public final SimpleEntrySet<WoodType, Block> DRAWER_3;
//    public final SimpleEntrySet<WoodType, Block> DRAWER_4;
//    public final SimpleEntrySet<WoodType, Block> SHELF_1;
//    public final SimpleEntrySet<WoodType, Block> PILLAR_TRIM;
//    public final SimpleEntrySet<WoodType, Block> CORNER_TRIM;


    public HandcraftedModule(String modId) {
        super(modId, "hc");
        CreativeModeTab tab = ModItems.ITEM_GROUP;

        CHAIR = SimpleEntrySet.builder(WoodType.class, "chair",
                        ModBlocks.OAK_CHAIR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CustomChairBlock(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/chair/chair/oak_chair"))
                .addTile(CustomChairTile::new)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .addCustomItem((w, b, p) -> new CustomChairItem(b, p))
//                .defaultRecipe()
                .build();
        this.addEntry(CHAIR);

        TABLE = SimpleEntrySet.builder(WoodType.class, "table",
                        ModBlocks.OAK_TABLE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CustomTableBlock(Utils.copyPropertySafe(w.planks).noOcclusion()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(ModTags.TABLE_ATTACHMENTS, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/table/table/oak_table"))
                .addTile(CustomTableTile::new)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .addCustomItem((w, b, p) -> new CustomTableItem(b, p))
//                .defaultRecipe()
                .build();
        this.addEntry(TABLE);
/*
        BENCH = SimpleEntrySet.builder(WoodType.class, "bench",
                        ModBlocks.OAK_BENCH, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WoodenBenchBlock(Utils.copyPropertySafe(w.planks).noOcclusion()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/chair/bench/oak_bench"))
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
//                .defaultRecipe()
                .build();
        this.addEntry(BENCH);
*/
        COUCH = SimpleEntrySet.builder(WoodType.class, "couch",
                        ModBlocks.OAK_COUCH, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CustomCouchBlock(Utils.copyPropertySafe(w.planks).noOcclusion()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/chair/couch/oak_couch"))
                .addTile(CustomCouchTile::new)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .addCustomItem((w, b, p) -> new CustomCouchItem(b, p))
//                .defaultRecipe()
                .build();
        this.addEntry(COUCH);
/*
        FANCY_BED = SimpleEntrySet.builder(WoodType.class, "fancy_bed",
                        ModBlocks.OAK_FANCY_BED, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new FancyBedBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_BED)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/bed/single/oak_fancy_bed"))
                .addTexture(modRes("block/bed/double/oak_fancy_bed"))
                .addTile(FancyBedBlockEntity::new)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
//                .defaultRecipe()
                .build();
        this.addEntry(FANCY_BED);

        DINING_BENCH = SimpleEntrySet.builder(WoodType.class, "dining_bench",
                        ModBlocks.OAK_DINING_BENCH, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DiningBenchBlock(Utils.copyPropertySafe(w.planks).noOcclusion()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/chair/dining_bench/oak_dining_bench"))
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
//                .defaultRecipe()
                .build();
        this.addEntry(DINING_BENCH);

        NIGHTSTAND = SimpleEntrySet.builder(WoodType.class, "nightstand",
                        ModBlocks.OAK_NIGHTSTAND, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new NightstandBlock(Utils.copyPropertySafe(w.planks).noOcclusion()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/table/nightstand/oak_nightstand"))
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
//                .defaultRecipe()
                .build();
        this.addEntry(NIGHTSTAND);

        DESK = SimpleEntrySet.builder(WoodType.class, "desk",
                        ModBlocks.OAK_DESK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DeskBlock(Utils.copyPropertySafe(w.planks).noOcclusion()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/table/desk/oak_desk"))
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
//                .defaultRecipe()
                .build();
        this.addEntry(DESK);

        SIDE_TABLE = SimpleEntrySet.builder(WoodType.class, "side_table",
                        ModBlocks.OAK_SIDE_TABLE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new SideTableBlock(Utils.copyPropertySafe(w.planks).noOcclusion()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/table/side_table/oak_side_table"))
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
//                .defaultRecipe()
                .build();
        this.addEntry(SIDE_TABLE);


        COUNTER_1 = SimpleEntrySet.builder(WoodType.class, "counter_1",
                        ModBlocks.OAK_COUNTER_1, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CounterBlock(Utils.copyPropertySafe(w.planks).noOcclusion()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/counter/counter/oak_counter_1"))
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
//                .defaultRecipe()
                .build();
        this.addEntry(COUNTER_1);

        COUNTER_2 = SimpleEntrySet.builder(WoodType.class, "counter_2",
                        ModBlocks.OAK_COUNTER_2, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CounterBlock(Utils.copyPropertySafe(w.planks).noOcclusion()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/counter/counter/oak_counter_2"))
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
//                .defaultRecipe()
                .build();
        this.addEntry(COUNTER_2);

        COUNTER_3 = SimpleEntrySet.builder(WoodType.class, "counter_3",
                        ModBlocks.OAK_COUNTER_3, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CounterBlock(Utils.copyPropertySafe(w.planks).noOcclusion()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/counter/counter/oak_counter_3"))
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
//                .defaultRecipe()
                .build();
        this.addEntry(COUNTER_3);


        CUPBOARD_1 = SimpleEntrySet.builder(WoodType.class, "cupboard_1",
                        ModBlocks.OAK_CUPBOARD_1, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CupboardBlock(Utils.copyPropertySafe(w.planks).noOcclusion()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/counter/cupboard/oak/cupboard_1"))
                .addTexture(modRes("block/counter/cupboard/oak/cupboard_back"))
                .addTexture(modRes("block/counter/cupboard/oak/cupboard_side"))
                .addTexture(modRes("block/counter/cupboard/oak/cupboard_top"))
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
//                .defaultRecipe()
                .build();
        this.addEntry(CUPBOARD_1);

        CUPBOARD_2 = SimpleEntrySet.builder(WoodType.class, "cupboard_2",
                        ModBlocks.OAK_CUPBOARD_2, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CupboardBlock(Utils.copyPropertySafe(w.planks).noOcclusion()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/counter/cupboard/oak/cupboard_2"))
                .addTexture(modRes("block/counter/cupboard/oak/cupboard_back"))
                .addTexture(modRes("block/counter/cupboard/oak/cupboard_side"))
                .addTexture(modRes("block/counter/cupboard/oak/cupboard_top"))
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
//                .defaultRecipe()
                .build();
        this.addEntry(CUPBOARD_2);


        DRAWER_1 = SimpleEntrySet.builder(WoodType.class, "drawer_1",
                        ModBlocks.OAK_DRAWER_1, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DrawerBlock(Utils.copyPropertySafe(w.planks).noOcclusion()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/counter/drawer/oak/front_1/drawer_left"))
                .addTexture(modRes("block/counter/drawer/oak/front_1/drawer_middle"))
                .addTexture(modRes("block/counter/drawer/oak/front_1/drawer_right"))
                .addTexture(modRes("block/counter/drawer/oak/front_1/drawer_single"))
                .addTexture(modRes("block/counter/drawer/oak/drawer_back"))
                .addTexture(modRes("block/counter/drawer/oak/drawer_bottom"))
                .addTexture(modRes("block/counter/drawer/oak/drawer_side_left"))
                .addTexture(modRes("block/counter/drawer/oak/drawer_side_right"))
                .addTexture(modRes("block/counter/drawer/oak/drawer_top"))
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
//                .defaultRecipe()
                .build();
        this.addEntry(DRAWER_1);

        DRAWER_2 = SimpleEntrySet.builder(WoodType.class, "drawer_2",
                        ModBlocks.OAK_DRAWER_2, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DrawerBlock(Utils.copyPropertySafe(w.planks).noOcclusion()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/counter/drawer/oak/front_2/drawer_left"))
                .addTexture(modRes("block/counter/drawer/oak/front_2/drawer_middle"))
                .addTexture(modRes("block/counter/drawer/oak/front_2/drawer_right"))
                .addTexture(modRes("block/counter/drawer/oak/front_2/drawer_single"))
                .addTexture(modRes("block/counter/drawer/oak/drawer_back"))
                .addTexture(modRes("block/counter/drawer/oak/drawer_bottom"))
                .addTexture(modRes("block/counter/drawer/oak/drawer_side_left"))
                .addTexture(modRes("block/counter/drawer/oak/drawer_side_right"))
                .addTexture(modRes("block/counter/drawer/oak/drawer_top"))
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
//                .defaultRecipe()
                .build();
        this.addEntry(DRAWER_2);

        DRAWER_3 = SimpleEntrySet.builder(WoodType.class, "drawer_3",
                        ModBlocks.OAK_DRAWER_3, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DrawerBlock(Utils.copyPropertySafe(w.planks).noOcclusion()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/counter/drawer/oak/front_3/drawer_left"))
                .addTexture(modRes("block/counter/drawer/oak/front_3/drawer_middle"))
                .addTexture(modRes("block/counter/drawer/oak/front_3/drawer_right"))
                .addTexture(modRes("block/counter/drawer/oak/front_3/drawer_single"))
                .addTexture(modRes("block/counter/drawer/oak/drawer_back"))
                .addTexture(modRes("block/counter/drawer/oak/drawer_bottom"))
                .addTexture(modRes("block/counter/drawer/oak/drawer_side_left"))
                .addTexture(modRes("block/counter/drawer/oak/drawer_side_right"))
                .addTexture(modRes("block/counter/drawer/oak/drawer_top"))
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
//                .defaultRecipe()
                .build();
        this.addEntry(DRAWER_3);

        DRAWER_4 = SimpleEntrySet.builder(WoodType.class, "drawer_4",
                        ModBlocks.OAK_DRAWER_4, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DrawerBlock(Utils.copyPropertySafe(w.planks).noOcclusion()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/counter/drawer/oak/front_4/drawer"))
                .addTexture(modRes("block/counter/drawer/oak/drawer_back"))
                .addTexture(modRes("block/counter/drawer/oak/drawer_bottom"))
                .addTexture(modRes("block/counter/drawer/oak/drawer_side_left"))
                .addTexture(modRes("block/counter/drawer/oak/drawer_side_right"))
                .addTexture(modRes("block/counter/drawer/oak/drawer_top"))
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
//                .defaultRecipe()
                .build();
        this.addEntry(DRAWER_4);


        SHELF_1 = SimpleEntrySet.builder(WoodType.class, "shelf",
                        ModBlocks.OAK_SHELF_1, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DiningBenchBlock(Utils.copyPropertySafe(w.planks).noOcclusion()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/counter/shelf/oak/shelf_back"))
                .addTexture(modRes("block/counter/shelf/oak/shelf_left"))
                .addTexture(modRes("block/counter/shelf/oak/shelf_middle"))
                .addTexture(modRes("block/counter/shelf/oak/shelf_right"))
                .addTexture(modRes("block/counter/shelf/oak/shelf_side_left"))
                .addTexture(modRes("block/counter/shelf/oak/shelf_side_right"))
                .addTexture(modRes("block/counter/shelf/oak/shelf_single"))
                .addTexture(modRes("block/counter/shelf/oak/shelf_top"))
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
//                .defaultRecipe()
                .build();
        this.addEntry(SHELF_1);


        PILLAR_TRIM = SimpleEntrySet.builder(WoodType.class, "pillar_trim",
                        ModBlocks.OAK_PILLAR_TRIM, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrimBlock(Utils.copyPropertySafe(w.planks).noOcclusion()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/trim/pillar/oak_pillar_trim"))
                .addTexture(modRes("block/trim/pillar/oak_pillar_trim_2"))
                .addTexture(modRes("block/trim/pillar/oak_thicc_pillar_trim"))
                .addTexture(modRes("block/trim/pillar/oak_thicc_pillar_trim_2"))
                .addTexture(modRes("block/trim/pillar/oak_thin_pillar_trim"))
                .addTexture(modRes("block/trim/pillar/oak_thin_pillar_trim_2"))
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
//                .defaultRecipe()
                .build();
        this.addEntry(PILLAR_TRIM);

        CORNER_TRIM = SimpleEntrySet.builder(WoodType.class, "corner_trim",
                        ModBlocks.OAK_CORNER_TRIM, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CornerTrimBlock(Utils.copyPropertySafe(w.planks).noOcclusion()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/trim/corner/oak_corner_trim"))
                .addTexture(modRes("block/trim/corner/oak_thicc_corner_trim"))
                .addTexture(modRes("block/trim/corner/oak_thin_corner_trim"))
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
//                .defaultRecipe()
                .build();
        this.addEntry(CORNER_TRIM);
*/
    }

    @Override
    public void registerBlockEntityRenderers(ClientPlatformHelper.BlockEntityRendererEvent event) {
        event.register(((BlockEntityType) CHAIR.getTileHolder().get()), CompatChairRenderer::new);
        event.register(((BlockEntityType) TABLE.getTileHolder().get()), OptimizedTableRenderer::new);
//        event.register(((BlockEntityType) BENCH.getTileHolder().get()), CompatBenchRenderer::new);
        event.register(((BlockEntityType) COUCH.getTileHolder().get()), CompatCouchRenderer::new);
    }

    //TYPE: ================ Entity
    public class CustomChairTile extends ChairBlockEntity {
        public CustomChairTile(BlockPos blockPos, BlockState blockState) {
            super(blockPos, blockState);
        }

        @Override
        public BlockEntityType<?> getType() {
            return CHAIR.getTileHolder().get();
        }
    }

    public class CustomTableTile extends TableBlockEntity {
        public CustomTableTile(BlockPos blockPos, BlockState blockState) {
            super(blockPos, blockState);
        }

        @Override
        public BlockEntityType<?> getType() {
            return TABLE.getTileHolder().get();
        }
    }
/*
    public class CustomBenchTile extends BenchBlockEntity {
        public CustomBenchTile(BlockPos blockPos, BlockState blockState) {
            super(blockPos, blockState);
        }

        @Override
        public BlockEntityType<?> getType() {
            return Bench.getTileHolder().get();
        }
    }
*/

    public class CustomCouchTile extends CouchBlockEntity {
        public CustomCouchTile(BlockPos blockPos, BlockState blockState) {
            super(blockPos, blockState);
        }

        @Override
        public BlockEntityType<?> getType() {
            return COUCH.getTileHolder().get();
        }
    }

    //TYPE: ================ stitchAtlasTextures
    @Override
    public void stitchAtlasTextures(ClientPlatformHelper.AtlasTextureEvent event) {

        if (OptimizedTableRenderer.OBJECT_TO_TEXTURE.isEmpty()) {
            // TABLE
            for (var t : TABLE.items.values()) { //all sheets items
                var texture = OptimizedTableRenderer.OBJECT_TO_TEXTURE.computeIfAbsent(t, b -> {
                    var blockId = Registry.ITEM.getKey(t);
                    var s = blockId.getPath().split("/");
                    return new Material(TextureAtlas.LOCATION_BLOCKS,
                            EveryCompat.res("block/hc/" + s[1] + "/table/table/" + s[2]));
                });
                event.addSprite(texture.texture());
            }
            // cloths
            for (var d : DyeColor.values()) {
                Item sheetItem = Registry.ITEM.get(this.modRes(d.getName() + "_sheet"));
                if (sheetItem != Items.AIR) {
                    var texture = OptimizedTableRenderer.OBJECT_TO_TEXTURE.computeIfAbsent(sheetItem, b ->
                            new Material(TextureAtlas.LOCATION_BLOCKS,
                                    modRes("block/table/table_cloth/" + d.getName() + "_sheet")));
                    event.addSprite(texture.texture());
                }
            }

            // CHAIR
            for (var t : CHAIR.items.values()) {
                var texture = OptimizedTableRenderer.OBJECT_TO_TEXTURE.computeIfAbsent(t, b -> {
                    var blockId = Registry.ITEM.getKey(t);
                    var s = blockId.getPath().split("/");
                    return new Material(TextureAtlas.LOCATION_BLOCKS,
                            EveryCompat.res("block/hc/" + s[1] + "/chair/chair/" + s[2]));
                });
                event.addSprite(texture.texture());
            }

            // cushins
            for (var d : DyeColor.values()) {
                Item cushionItem = Registry.ITEM.get(this.modRes(d.getName() + "_cushion"));
                if (cushionItem != Items.AIR) {
                    // chair
                    var texture = OptimizedTableRenderer.OBJECT_TO_TEXTURE.computeIfAbsent(cushionItem, b ->
                            new Material(TextureAtlas.LOCATION_BLOCKS,
                                    modRes("block/chair/chair/cushion/" + d.getName() + "_cushion")));
                    event.addSprite(texture.texture());

                    // couch
                    var texture2 = CompatCouchRenderer.OBJECT_TO_TEXTURE.computeIfAbsent(cushionItem, b ->
                            new Material(TextureAtlas.LOCATION_BLOCKS,
                                    modRes("block/chair/couch/cushion/" + d.getName() + "_cushion")));
                    event.addSprite(texture2.texture());
                }
            }

            // BENCH


            // COUCH
            for (var t : COUCH.items.values()) {
                var texture = CompatCouchRenderer.OBJECT_TO_TEXTURE.computeIfAbsent(t, b -> {
                    var blockId = Registry.ITEM.getKey(t);
                    var s = blockId.getPath().split("/");
                    return new Material(TextureAtlas.LOCATION_BLOCKS,
                            EveryCompat.res("block/hc/" + s[1] + "/chair/couch/" + s[2]));
                });
                event.addSprite(texture.texture());
            }

        }
    }

    //TYPE: ================ Custom Block
    public class CustomChairBlock extends ChairBlock {
        public CustomChairBlock(Properties properties) {
            super(properties);
        }

        @Override
        public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
            return new CustomChairTile(pos, state);
        }
    }

    public class CustomTableBlock extends TableBlock {
        public CustomTableBlock(Properties properties) {
            super(properties);
        }

        @Override
        public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
            return new CustomTableTile(pos, state);
        }
    }

/*
    public class CustomBenchBlock extends BenchBlock {
        public CustomBenchBlock(Properties properties) {
            super(properties);
        }

        @Override
        public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
            return new CustomBenchTile(pos, state);
        }
    }
*/

    public class CustomCouchBlock extends CouchBlock {
        public CustomCouchBlock(Properties properties) {
            super(properties);
        }

        @Override
        public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
            return new CustomCouchTile(pos, state);
        }
    }


    //TYPE: ================ ItemRenderer
    public static class TableItemRenderer extends ItemStackRenderer {
        @Override
        public void renderByItem(ItemStack itemStack, ItemTransforms.TransformType transformType, PoseStack poseStack,
                                 MultiBufferSource multiBufferSource, int i, int i1) {
            OptimizedTableRenderer.INSTANCE.doRender(poseStack, multiBufferSource, i, i1,
                    TableState.SINGLE, SheetState.SINGLE, Items.AIR, itemStack.getItem());
        }
    }

    public static class ChairItemRenderer extends ItemStackRenderer {
        @Override
        public void renderByItem(ItemStack itemStack, ItemTransforms.TransformType transformType, PoseStack poseStack,
                                 MultiBufferSource multiBufferSource, int i, int i1) {
            CompatChairRenderer.INSTANCE.doRender(itemStack.getItem(), Items.AIR, Direction.SOUTH, poseStack,
                    multiBufferSource, i, i1, 0.75f, -0.5);
        }
    }
/*
    public static class BenchItemRenderer extends ItemStackRenderer {
        @Override
        public void renderByItem(ItemStack itemStack, ItemTransforms.TransformType transformType, PoseStack poseStack,
                                 MultiBufferSource multiBufferSource, int i, int i1) {
        }
    }
*/

    public static class CouchItemRenderer extends ItemStackRenderer {
        @Override
        public void renderByItem(ItemStack itemStack, ItemTransforms.TransformType transformType, PoseStack poseStack,
                                 MultiBufferSource multiBufferSource, int i, int i1) {
            CompatCouchRenderer.INSTANCE.doRender(itemStack.getItem(), Items.AIR, Direction.SOUTH,
                    CouchShape.SINGLE, poseStack,
                    multiBufferSource, i, i1);
        }
    }
    //TYPE: ================ Item
    public static class CustomTableItem extends BlockItem implements ICustomItemRendererProvider {

        public CustomTableItem(Block block, Properties properties) {
            super(block, properties);
        }

        @Override
        public Supplier<ItemStackRenderer> getRendererFactory() {
            return () -> new TableItemRenderer();
        }
    }

    public static class CustomChairItem extends BlockItem implements ICustomItemRendererProvider {

        public CustomChairItem(Block block, Properties properties) {
            super(block, properties);
        }

        @Override
        public Supplier<ItemStackRenderer> getRendererFactory() {
            return () -> new ChairItemRenderer();
        }
    }
/*
    public static class CustomBenchItem extends BlockItem implements ICustomItemRendererProvider {

        public CustomBenchItem(Block block, Properties properties) {
            super(block, properties);
        }

        @Override
        public Supplier<ItemStackRenderer> getRendererFactory() {
            return () -> new BenchItemRenderer();
        }
    }
*/

    public static class CustomCouchItem extends BlockItem implements ICustomItemRendererProvider {

        public CustomCouchItem(Block block, Properties properties) {
            super(block, properties);
        }

        @Override
        public Supplier<ItemStackRenderer> getRendererFactory() {
            return () -> new CouchItemRenderer();
        }
    }

}
