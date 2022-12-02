package net.mehvahdjukaar.every_compat.modules.furnish;

import io.github.wouink.furnish.block.Bench;
import io.github.wouink.furnish.block.Cabinet;
import io.github.wouink.furnish.block.Chair;
import io.github.wouink.furnish.block.Coffin;
import io.github.wouink.furnish.block.Crate;
import io.github.wouink.furnish.block.InventoryFurniture;
import io.github.wouink.furnish.block.Ladder;
import io.github.wouink.furnish.block.LogBench;
import io.github.wouink.furnish.block.Shelf;
import io.github.wouink.furnish.block.Shutter;
import io.github.wouink.furnish.block.SimpleFurniture;
import io.github.wouink.furnish.block.Table;
import io.github.wouink.furnish.block.Wardrobe;
import io.github.wouink.furnish.block.util.VoxelShapeHelper;
import io.github.wouink.furnish.setup.FurnishBlocks;
import io.github.wouink.furnish.setup.FurnishData;
import io.github.wouink.furnish.setup.FurnishItems;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.resources.BlockTypeResTransformer;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.textures.SpriteUtils;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.VoxelShape;


public class FurnishModule extends SimpleModule {

    public static final VoxelShape[] BACK_LEFT_LEG = VoxelShapeHelper.getRotatedShapes(Block.box(3D, 0D, 11D, 5D, 16D, 13D));
    public static final VoxelShape[] BACK_RIGHT_LEG = VoxelShapeHelper.getRotatedShapes(Block.box(3D, 0D, 3D, 5D, 16D, 5D));
    public static final VoxelShape[] CUSHION = VoxelShapeHelper.getRotatedShapes(Block.box(5D, 8D, 3D, 15D, 9D, 13D));
    public static final VoxelShape[] FRONT_LEFT_LEG = VoxelShapeHelper.getRotatedShapes(Block.box(12D, 0D, 11D, 14D, 8D, 13D));
    public static final VoxelShape[] FRONT_RIGHT_LEG = VoxelShapeHelper.getRotatedShapes(Block.box(12D, 0D, 3D, 14D, 8D, 5D));
    public static final VoxelShape[] SEAT = VoxelShapeHelper.getRotatedShapes(Block.box(5D, 11D, 3D, 6D, 17D, 13D));

    public final SimpleEntrySet<WoodType, Block> BEDSIDE_TABLE;
    public final SimpleEntrySet<WoodType, Block> BENCH;
    public final SimpleEntrySet<WoodType, Block> CABINET;
    public final SimpleEntrySet<WoodType, Block> CHAIR;
    public final SimpleEntrySet<WoodType, Block> COFFIN;
    public final SimpleEntrySet<WoodType, Block> CRATE;
    public final SimpleEntrySet<WoodType, Block> KITCHEN_CABINET;
    public final SimpleEntrySet<WoodType, Block> LADDER;
    public final SimpleEntrySet<WoodType, Block> LOG_BENCHES;
    public final SimpleEntrySet<WoodType, Block> PEDESTAL_TABLE;
    public final SimpleEntrySet<WoodType, Block> SHELF;
    public final SimpleEntrySet<WoodType, Block> SHUTTER;
    public final SimpleEntrySet<WoodType, Block> SQUARE_TABLE;
    public final SimpleEntrySet<WoodType, Block> STOOL;
    public final SimpleEntrySet<WoodType, Block> TABLE;
    public final SimpleEntrySet<WoodType, Block> WARDROBE;

    public FurnishModule(String modId) {
        super(modId, "fur");
        CreativeModeTab tab = FurnishItems.Furnish_ItemGroup;


        TABLE = SimpleEntrySet.builder(WoodType.class, "table",
                        FurnishBlocks.Oak_Table, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new Table(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(modRes("" + "_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("wooden_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addRecipe(modRes("furniture_making/oak_table"))
                .setTab(() -> tab)
                .build();

        this.addEntry(TABLE);

        SQUARE_TABLE = SimpleEntrySet.builder(WoodType.class, "square_table",
                        FurnishBlocks.Oak_Square_Table, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new SimpleFurniture(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(modRes("" + "_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("wooden_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addRecipe(modRes("furniture_making/oak_square_table"))
                .setTab(() -> tab)
                .build();

        this.addEntry(SQUARE_TABLE);

        PEDESTAL_TABLE = SimpleEntrySet.builder(WoodType.class, "pedestal_table",
                        FurnishBlocks.Oak_Pedestal_Table, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new SimpleFurniture(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(modRes("" + "_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("wooden_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addRecipe(modRes("furniture_making/oak_pedestal_table"))
                .setTab(() -> tab)
                .build();

        this.addEntry(PEDESTAL_TABLE);

        BEDSIDE_TABLE = SimpleEntrySet.builder(WoodType.class, "bedside_table",
                        FurnishBlocks.Oak_Bedside_Table, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new InventoryFurniture(Utils.copyPropertySafe(w.log), FurnishData.Sounds.Drawers_Open, FurnishData.Sounds.Drawers_Close), "stripped_log"))
                .addTag(modRes("" + "_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("wooden_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addRecipe(modRes("furniture_making/oak_bedside_table"))
                .setTab(() -> tab)
                .build();

        this.addEntry(BEDSIDE_TABLE);

        KITCHEN_CABINET = SimpleEntrySet.builder(WoodType.class, "kitchen_cabinet",
                        FurnishBlocks.Oak_Kitchen_Cabinet, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new InventoryFurniture(Utils.copyPropertySafe(w.planks), FurnishData.Sounds.Drawers_Open, FurnishData.Sounds.Drawers_Close))
                .addTag(modRes("" + "_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("wooden_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addRecipe(modRes("furniture_making/oak_kitchen_cabinet"))
                .setTab(() -> tab)
                .build();

        this.addEntry(KITCHEN_CABINET);

        CABINET = SimpleEntrySet.builder(WoodType.class, "cabinet",
                        FurnishBlocks.Birch_Cabinet, () -> WoodTypeRegistry.getValue(new ResourceLocation("birch")),
                        ifHasChild(w -> new Cabinet(Utils.copyPropertySafe(w.log), FurnishData.Sounds.Cabinet_Open, FurnishData.Sounds.Cabinet_Close), "stripped_log"))
                .addTag(modRes("" + "_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("wooden_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/birch_cabinet_door_right"))
                .addTexture(modRes("block/birch_cabinet_door_left"))
                .addRecipe(modRes("furniture_making/birch_cabinet"))
                .setTab(() -> tab)
                .build();

        this.addEntry(CABINET);

        WARDROBE = SimpleEntrySet.builder(WoodType.class, "wardrobe",
                        FurnishBlocks.Birch_Wardrobe, () -> WoodTypeRegistry.getValue(new ResourceLocation("birch")),
                        ifHasChild(w -> new Wardrobe(Utils.copyPropertySafe(w.log), FurnishData.Sounds.Cabinet_Open, FurnishData.Sounds.Cabinet_Close), "stripped_log"))
                .addTag(modRes("" + "_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("wooden_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/birch_wardrobe_door_bottom_right"))
                .addTexture(modRes("block/birch_wardrobe_door_bottom_left"))
                .addTexture(modRes("block/birch_wardrobe_door_top_right"))
                .addTexture(modRes("block/birch_wardrobe_door_top_left"))
                .addRecipe(modRes("furniture_making/birch_wardrobe"))
                .setTab(() -> tab)
                .build();

        this.addEntry(WARDROBE);

        STOOL = SimpleEntrySet.builder(WoodType.class, "stool",
                        FurnishBlocks.Oak_Stool, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new Chair(Utils.copyPropertySafe(w.log), Chair.BASE_SHAPES), "stripped_log"))
                .addTag(modRes("" + "_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("wooden_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addRecipe(modRes("furniture_making/oak_stool"))
                .setTab(() -> tab)
                .build();

        this.addEntry(STOOL);

        CHAIR = SimpleEntrySet.builder(WoodType.class, "chair",
                        FurnishBlocks.Oak_Chair, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new Chair(Utils.copyPropertySafe(w.log),
                                VoxelShapeHelper.getMergedShapes(FRONT_LEFT_LEG, FRONT_RIGHT_LEG, BACK_LEFT_LEG, BACK_RIGHT_LEG, CUSHION, SEAT)), "stripped_log"))
                .addTag(modRes("" + "_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("wooden_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addRecipe(modRes("furniture_making/oak_chair"))
                .setTab(() -> tab)
                .build();

        this.addEntry(CHAIR);

        SHUTTER = SimpleEntrySet.builder(WoodType.class, "shutter",
                        FurnishBlocks.Oak_Shutter, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Shutter(Utils.copyPropertySafe(w.planks)))
                .addTag(modRes("" + "_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("wooden_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addRecipe(modRes("furniture_making/oak_shutter"))
                .addTexture(modRes("block/oak_shutter"))
                .setTab(() -> tab)
                .build();

        this.addEntry(SHUTTER);

        CRATE = SimpleEntrySet.builder(WoodType.class, "crate",
                        FurnishBlocks.Oak_Crate, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Crate(Utils.copyPropertySafe(w.planks)))
                .addTag(modRes("" + "_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("wooden_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addRecipe(modRes("furniture_making/oak_crate"))
                .addTexture(modRes("block/oak_crate_side"))
                .addTexture(modRes("block/oak_crate_top"))
                .setTab(() -> tab)
                .build();

        this.addEntry(CRATE);

        SHELF = SimpleEntrySet.builder(WoodType.class, "shelf",
                        FurnishBlocks.Oak_Shelf, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Shelf(Utils.copyPropertySafe(w.planks)))
                .addTag(modRes("" + "_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("wooden_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addRecipe(modRes("furniture_making/oak_shelf"))
                .setTab(() -> tab)
                .build();

        this.addEntry(SHELF);

        BENCH = SimpleEntrySet.builder(WoodType.class, "bench",
                        FurnishBlocks.Oak_Bench, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Bench(Utils.copyPropertySafe(w.planks)))
                .addTag(modRes("" + "_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("wooden_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addRecipe(modRes("furniture_making/oak_bench"))
                .setTab(() -> tab)
                .build();

        this.addEntry(BENCH);

        LOG_BENCHES = SimpleEntrySet.builder(WoodType.class, "log_bench",
                        FurnishBlocks.Oak_Log_Bench, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new LogBench(Utils.copyPropertySafe(w.log)))
                .addTag(modRes("" + "_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("wooden_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addRecipe(modRes("furniture_making/oak_log_bench"))
                .addTexture(modRes("block/oak_log_bench_top"))
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();

        this.addEntry(LOG_BENCHES);

        LADDER = SimpleEntrySet.builder(WoodType.class, "ladder",
                        FurnishBlocks.Oak_Ladder, () -> WoodTypeRegistry.OAK_TYPE,
                        ifHasChild(w -> new Ladder(Utils.copyPropertySafe(w.log)), "stripped_log"))
                .addTag(modRes("" + "_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("wooden_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.CLIMBABLE, Registry.BLOCK_REGISTRY)
                .addRecipe(modRes("furniture_making/oak_ladder"))
                .setTab(() -> tab)
                .build();

        this.addEntry(LADDER);

        COFFIN = SimpleEntrySet.builder(WoodType.class, "coffin",
                        FurnishBlocks.Jungle_Coffin, () -> WoodTypeRegistry.getValue(new ResourceLocation("jungle")),
                        w -> new Coffin(Utils.copyPropertySafe(w.planks)))
                .addTag(modRes("" + "_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("wooden_furniture"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addRecipe(modRes("furniture_making/jungle_coffin"))
                .addTexture(modRes("block/jungle_coffin_sides"))
                .setTab(() -> tab)
                .build();

        this.addEntry(COFFIN);
    }

    @Override
    public void addDynamicClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicClientResources(handler, manager);
        try {
            LOG_BENCHES.blocks.forEach((w, block) -> {
                var id = Utils.getID(block);

                try (TextureImage topTexture = TextureImage.open(manager,
                        RPUtils.findFirstBlockTextureLocation(manager, w.log, SpriteUtils.LOOKS_LIKE_TOP_LOG_TEXTURE))) {

                    String newId = BlockTypeResTransformer.replaceTypeNoNamespace("block/oak_log_bench_top", w, id, "oak");

                    var newTexture = topTexture.makeCopy();

                    handler.addTextureIfNotPresent(manager, newId, () -> newTexture);

                    var newTop = topTexture.makeCopy();
                    createTopTexture(topTexture, newTop);

                    handler.addTextureIfNotPresent(manager, newId + "_top", () -> newTop);

                } catch (Exception e) {
                    handler.getLogger().error("Failed to generate Log Bench block texture for for {} : {}", block, e);

                }

            });
            COFFIN.blocks.forEach((w, block) -> {
                var id = Utils.getID(block);

                try (TextureImage topTexture = TextureImage.open(manager,
                        RPUtils.findFirstBlockTextureLocation(manager, w.log, SpriteUtils.LOOKS_LIKE_TOP_LOG_TEXTURE))) {

                    String newId = BlockTypeResTransformer.replaceTypeNoNamespace("block/jungle_coffin_sides", w, id, "jungle");

                    var newTexture = topTexture.makeCopy();

                    handler.addTextureIfNotPresent(manager, newId, () -> newTexture);

                    var newTop = topTexture.makeCopy();
                    createTopTexture(topTexture, newTop);

                    handler.addTextureIfNotPresent(manager, newId + "_top", () -> newTop);

                } catch (Exception e) {
                    handler.getLogger().error("Failed to generate Log Bench block texture for for {} : {}", block, e);

                }

            });
        } catch (Exception ex) {
            handler.getLogger().error("Could not generate any Log Bench block textures : ", ex);
        }
    }

    private void createTopTexture(TextureImage original, TextureImage newImage) {
        original.forEachFrame((i, x, y) -> {
            int localX = x - original.getFrameX(i);
            int localY = y - original.getFrameY(i);
            if (localX >= 5 && localX <= 10 && localY >= 5 && localY <= 10) {
                newImage.getImage().setPixelRGBA(x - 3, y - 3, original.getImage().getPixelRGBA(x, y));
            } else if (localX >= 10 && localY > 0 && localY <= 7) {
                newImage.getImage().setPixelRGBA(x - 6, y, original.getImage().getPixelRGBA(x, y));
                newImage.getImage().setPixelRGBA(x, y, 0);
            } else if (localY >= 10 && localX > 0 && localX <= 7) {
                newImage.getImage().setPixelRGBA(x, y - 6, original.getImage().getPixelRGBA(x, y));
                newImage.getImage().setPixelRGBA(x, y, 0);
            } else if (localX >= 10 && localY >= 10) {
                newImage.getImage().setPixelRGBA(x - 6, y - 6, original.getImage().getPixelRGBA(x, y));
            } else if (localX >= 10 || localY >= 10) {
                newImage.getImage().setPixelRGBA(x, y, 0);
            }
        });
    }
}
