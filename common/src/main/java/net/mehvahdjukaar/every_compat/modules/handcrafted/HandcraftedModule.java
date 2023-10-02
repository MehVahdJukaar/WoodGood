package net.mehvahdjukaar.every_compat.modules.handcrafted;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import earth.terrarium.handcrafted.client.HandcraftedClient;
import earth.terrarium.handcrafted.client.block.chair.chair.ChairRenderer;
import earth.terrarium.handcrafted.client.block.table.table.TableModel;
import earth.terrarium.handcrafted.client.block.table.table.TableRenderer;
import earth.terrarium.handcrafted.client.fabric.HandcraftedClientFabric;
import earth.terrarium.handcrafted.common.block.chair.chair.ChairBlock;
import earth.terrarium.handcrafted.common.block.property.SheetState;
import earth.terrarium.handcrafted.common.block.property.TableState;
import earth.terrarium.handcrafted.common.block.table.table.TableBlock;
import earth.terrarium.handcrafted.common.block.table.table.TableBlockEntity;
import earth.terrarium.handcrafted.common.registry.ModBlockEntityTypes;
import earth.terrarium.handcrafted.common.registry.ModBlocks;
import earth.terrarium.handcrafted.common.registry.ModItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.client.ICustomItemRendererProvider;
import net.mehvahdjukaar.moonlight.api.client.ItemStackRenderer;
import net.mehvahdjukaar.moonlight.api.platform.ClientPlatformHelper;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.IdentityHashMap;
import java.util.Map;
import java.util.function.Supplier;


public class HandcraftedModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> CHAIR;
    public final SimpleEntrySet<WoodType, Block> TABLE;
//    public final SimpleEntrySet<WoodType, Block> BENCH;
//    public final SimpleEntrySet<WoodType, Block> COUCH;
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
                        w -> new ChairBlock(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/chair/chair/oak_chair"))
                .addTile(ModBlockEntityTypes.TABLE)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
//                .defaultRecipe()
                .build();
        this.addEntry(CHAIR);

        TABLE = SimpleEntrySet.builder(WoodType.class, "table",
                        ModBlocks.OAK_TABLE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CustomTableBlock(Utils.copyPropertySafe(w.planks).noOcclusion()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/table/table/oak_table"))
                .addTile(CustomTableTile::new)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .addCustomItem((woodType, block, properties) -> new CustomTableItem(block, properties))
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

        COUCH = SimpleEntrySet.builder(WoodType.class, "couch",
                        ModBlocks.OAK_COUCH, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CouchBlock(Utils.copyPropertySafe(w.planks).noOcclusion()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/chair/couch/oak_couch"))
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
//                .defaultRecipe()
                .build();
        this.addEntry(COUCH);

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
        event.register(((BlockEntityType) CHAIR.getTileHolder().get()), ChairRenderer::new);
        event.register(((BlockEntityType) TABLE.getTileHolder().get()), OptimizedTableRenderer::new);
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

    @Override
    public void stitchAtlasTextures(ClientPlatformHelper.AtlasTextureEvent event) {

        if (OBJECT_TO_TEXTURE.isEmpty()) {
            for (var t : TABLE.items.values()) { //all sheets items
                var texture = OBJECT_TO_TEXTURE.computeIfAbsent(t, b -> {
                    var blockId = Registry.ITEM.getKey(t);
                    var s = blockId.getPath().split("/");
                    return new Material(TextureAtlas.LOCATION_BLOCKS,
                            EveryCompat.res("block/hc/" + s[1] + "/table/table/" + s[2]));
                });
                event.addSprite(texture.texture());
            }


            //...
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

    //might be worth moving this stuff to a dedicated client class
    @Environment(EnvType.CLIENT)
    private static final Map<Item, Material> OBJECT_TO_TEXTURE = new IdentityHashMap<>();

    @Environment(EnvType.CLIENT)
    //this is bad. you should use custom baked models instead...
    public static class OptimizedTableRenderer implements BlockEntityRenderer<TableBlockEntity> {
        private static OptimizedTableRenderer INSTANCE = null;
        private final TableModel model;
        private final ModelPart northeastLeg;
        private final ModelPart northwestLeg;
        private final ModelPart southeastLeg;
        private final ModelPart northOverlay;
        private final ModelPart southwestLeg;
        private final ModelPart eastOverlay;
        private final ModelPart southOverlay;
        private final ModelPart westOverlay;

        public OptimizedTableRenderer(BlockEntityRendererProvider.Context ctx) {
            this.model = new TableModel(ctx.getModelSet().bakeLayer(TableModel.LAYER_LOCATION));
            this.northeastLeg = model.getMain().getChild("table").getChild("northeast_leg");
            this.northwestLeg = model.getMain().getChild("table").getChild("northwest_leg");
            this.southeastLeg = model.getMain().getChild("table").getChild("southeast_leg");
            this.southwestLeg = model.getMain().getChild("table").getChild("southwest_leg");
            this.northOverlay = model.getMain().getChild("overlay").getChild("overlay_side_north");
            this.eastOverlay = model.getMain().getChild("overlay").getChild("overlay_side_east");
            this.southOverlay = model.getMain().getChild("overlay").getChild("overlay_side_south");
            this.westOverlay = model.getMain().getChild("overlay").getChild("overlay_side_west");
            INSTANCE = this;
        }

        public void render(TableBlockEntity entity, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {


            var tableState = entity.getBlockState().getValue(TableBlock.TABLE_BLOCK_SHAPE);
            var sheetState = entity.getBlockState().getValue(TableBlock.TABLE_SHEET_SHAPE);
            Item b = entity.getBlockState().getBlock().asItem();
            Item sheet = entity.getStack().getItem();
            doRender( poseStack, buffer, packedLight, packedOverlay, tableState, sheetState, sheet, b);
        }

        public void doRender(PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay,
                             TableState tableState, SheetState sheetState, Item sheet, Item block) {
            poseStack.pushPose();

            poseStack.translate(0.5, 1.5, 0.5);
            poseStack.mulPose(Vector3f.XP.rotationDegrees(180.0F));

            switch (tableState) {
                case CENTER -> {
                    northeastLeg.visible = false;
                    northwestLeg.visible = false;
                    southeastLeg.visible = false;
                    southwestLeg.visible = false;
                }
                case NORTH_EAST_CORNER -> {
                    northeastLeg.visible = false;
                    northwestLeg.visible = false;
                    southwestLeg.visible = false;
                }
                case NORTH_WEST_CORNER -> {
                    northeastLeg.visible = false;
                    northwestLeg.visible = false;
                    southeastLeg.visible = false;
                }
                case SOUTH_EAST_CORNER -> {
                    northwestLeg.visible = false;
                    southeastLeg.visible = false;
                    southwestLeg.visible = false;
                }
                case SOUTH_WEST_CORNER -> {
                    northeastLeg.visible = false;
                    southeastLeg.visible = false;
                    southwestLeg.visible = false;
                }
                case NORTH_SIDE -> {
                    northeastLeg.visible = false;
                    northwestLeg.visible = false;
                }
                case EAST_SIDE -> {
                    northwestLeg.visible = false;
                    southwestLeg.visible = false;
                }
                case SOUTH_SIDE -> {
                    southeastLeg.visible = false;
                    southwestLeg.visible = false;
                }
                case WEST_SIDE -> {
                    northeastLeg.visible = false;
                    southeastLeg.visible = false;
                }
            }

            switch (sheetState) {
                case CENTER -> {
                    northOverlay.visible = false;
                    eastOverlay.visible = false;
                    southOverlay.visible = false;
                    westOverlay.visible = false;
                }
                case NORTH_SIDE -> {
                    northOverlay.visible = false;
                    eastOverlay.visible = false;
                    westOverlay.visible = false;
                }
                case EAST_SIDE -> {
                    northOverlay.visible = false;
                    eastOverlay.visible = false;
                    southOverlay.visible = false;
                }
                case SOUTH_SIDE -> {
                    eastOverlay.visible = false;
                    southOverlay.visible = false;
                    westOverlay.visible = false;
                }
                case WEST_SIDE -> {
                    northOverlay.visible = false;
                    southOverlay.visible = false;
                    westOverlay.visible = false;
                }
                case NORTH_EAST_CORNER -> {
                    northOverlay.visible = false;
                    eastOverlay.visible = false;
                }
                case NORTH_WEST_CORNER -> {
                    northOverlay.visible = false;
                    westOverlay.visible = false;
                }
                case SOUTH_EAST_CORNER -> {
                    southOverlay.visible = false;
                    eastOverlay.visible = false;
                }
                case SOUTH_WEST_CORNER -> {
                    southOverlay.visible = false;
                    westOverlay.visible = false;
                }
                case NORTH_COVER -> northOverlay.visible = false;
                case EAST_COVER -> eastOverlay.visible = false;
                case SOUTH_COVER -> southOverlay.visible = false;
                case WEST_COVER -> westOverlay.visible = false;
            }

            var texture = OBJECT_TO_TEXTURE.get(block);

            model.renderToBuffer(poseStack, texture.buffer(buffer, RenderType::entityCutout), packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
            if (sheet != Items.AIR) {
                var s = OBJECT_TO_TEXTURE.computeIfAbsent(sheet, b -> {

                    var itemId = Registry.ITEM.getKey(sheet);
                    return new Material(
                            TextureAtlas.LOCATION_BLOCKS,
                            new ResourceLocation("handcrafted:block/table/table_cloth/hc/" + itemId.getPath()));
                });
                model.renderToBuffer(poseStack, s.buffer(buffer, RenderType::entityCutout), packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
            }
            poseStack.popPose();
        }
    }

    public static class TableItemRenderer extends ItemStackRenderer{

        @Override
        public void renderByItem(ItemStack itemStack, ItemTransforms.TransformType transformType, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int i1) {
            OptimizedTableRenderer.INSTANCE.doRender(poseStack, multiBufferSource, i, i1,
                    TableState.CENTER, SheetState.CENTER, Items.AIR, itemStack.getItem());

        }
    }

    public static class CustomTableItem extends BlockItem implements ICustomItemRendererProvider {

        public CustomTableItem(Block block, Properties properties) {
            super(block, properties);
        }

        @Override
        public Supplier<ItemStackRenderer> getRendererFactory() {
            return ()->new TableItemRenderer();
        }
    }
}
