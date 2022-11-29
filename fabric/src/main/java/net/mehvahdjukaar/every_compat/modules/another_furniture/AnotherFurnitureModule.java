package net.mehvahdjukaar.every_compat.modules.another_furniture;

import com.starfish_studios.another_furniture.AnotherFurniture;
import com.starfish_studios.another_furniture.block.*;
import com.starfish_studios.another_furniture.block.entity.DrawerBlockEntity;
import com.starfish_studios.another_furniture.block.entity.PlanterBoxBlockEntity;
import com.starfish_studios.another_furniture.block.entity.ShelfBlockEntity;
import com.starfish_studios.another_furniture.client.renderer.blockentity.PlanterBoxRenderer;
import com.starfish_studios.another_furniture.client.renderer.blockentity.ShelfRenderer;
import com.starfish_studios.another_furniture.registry.AFBlocks;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.platform.ClientPlatformHelper;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.resources.textures.PaletteColor;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class AnotherFurnitureModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> TABLES;
    public final SimpleEntrySet<WoodType, Block> CHAIRS;
    public final SimpleEntrySet<WoodType, Block> SHELVES;
    public final SimpleEntrySet<WoodType, Block> PLANTER_BOXES;
    public final SimpleEntrySet<WoodType, Block> SHUTTERS;
    public final SimpleEntrySet<WoodType, Block> DRAWERS;
    public final SimpleEntrySet<WoodType, Block> BENCHES;

    public AnotherFurnitureModule(String modId) {
        super(modId, "af");

        PLANTER_BOXES = SimpleEntrySet.builder(WoodType.class, "planter_box",
                        AFBlocks.OAK_PLANTER_BOX, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CompatPlanterBoxBlock(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("planter_boxes"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("planter_boxes"), Registry.ITEM_REGISTRY)
                .useLootFromBase()
                .defaultRecipe()
                .addTile(CompatPlanterBoxTile::new)
                .setTab(() -> AnotherFurniture.TAB)
                .addTexture(modRes("block/planter_box/oak_bottom"))
                .addTexture(modRes("block/planter_box/oak_supports"))
                .addTextureM(modRes("block/planter_box/oak_top_sides"), EveryCompat.res("block/af/planter_box_top_sides_mask"))
                .build();

        this.addEntry(PLANTER_BOXES);

        SHUTTERS = SimpleEntrySet.builder(WoodType.class, "shutter",
                        AFBlocks.OAK_SHUTTER, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ShutterBlock(Utils.copyPropertySafe(w.planks).noOcclusion()))
                .addTag(modRes("shutters"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("shutters"), Registry.ITEM_REGISTRY)
                .useLootFromBase()
                .defaultRecipe()
                .setTab(() -> AnotherFurniture.TAB)
                .addTexture(modRes("block/shutter/oak_bottom"))
                .addTexture(modRes("block/shutter/oak_middle"))
                .addTexture(modRes("block/shutter/oak_none"))
                .addTexture(modRes("block/shutter/oak_top"))
                .createPaletteFromOak(this::shuttersPalette)
                .build();

        this.addEntry(SHUTTERS);

        TABLES = SimpleEntrySet.builder(WoodType.class, "table",
                        AFBlocks.OAK_TABLE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TableBlock(Utils.copyPropertySafe(w.planks)))
                .addTag(modRes("tables"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("tables"), Registry.ITEM_REGISTRY)
                .useLootFromBase()
                .defaultRecipe()
                .setTab(() -> AnotherFurniture.TAB)
                .setRenderType(() -> RenderType::cutout)
                .addTexture(modRes("block/table/oak_bottom"))
                .addTexture(modRes("block/table/oak_sides"))
                .addTexture(modRes("block/table/oak_supports"))
                .addTexture(modRes("block/table/oak_top"))
                .build();

        this.addEntry(TABLES);

        CHAIRS = SimpleEntrySet.builder(WoodType.class, "chair",
                        AFBlocks.OAK_CHAIR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ChairBlock(Utils.copyPropertySafe(w.planks)))
                .addTag(modRes("chairs"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("chairs"), Registry.ITEM_REGISTRY)
                .defaultRecipe()
                .setTab(() -> AnotherFurniture.TAB)
                .setRenderType(() -> RenderType::cutout)
                .addTexture(modRes("block/chair/oak_back"))
                .addTexture(modRes("block/chair/oak_bottom"))
                .addTexture(modRes("block/chair/oak_seat"))
                .build();

        this.addEntry(CHAIRS);

        SHELVES = SimpleEntrySet.builder(WoodType.class, "shelf",
                        AFBlocks.OAK_SHELF, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CompatShelfBlock(Utils.copyPropertySafe(w.planks)))
                .addTag(modRes("shelves"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("shelves"), Registry.ITEM_REGISTRY)
                .addTile(CompatShelfBlockTile::new)
                .defaultRecipe()
                .setTab(() -> AnotherFurniture.TAB)
                .addTexture(modRes("block/shelf/oak_sides"))
                .addTexture(modRes("block/shelf/oak_top"))
                .addTexture(modRes("block/shelf/oak_bottom"))
                .addTexture(modRes("block/shelf/oak_supports"))
                .build();

        this.addEntry(SHELVES);

        DRAWERS = SimpleEntrySet.builder(WoodType.class, "drawer",
                        AFBlocks.OAK_DRAWER, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DrawerBlock(Utils.copyPropertySafe(w.planks)))
                .addTag(modRes("drawers"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("drawers"), Registry.ITEM_REGISTRY)
                .addTile(CompatDrawerBlockTile::new)
                .defaultRecipe()
                .setTab(() -> AnotherFurniture.TAB)
                .setRenderType(() -> RenderType::cutout)
                .addTexture(modRes("block/drawer/oak_front"))
                .addTexture(modRes("block/drawer/oak_front_open"))
                .addTexture(modRes("block/drawer/oak_side"))
                .addTexture(modRes("block/drawer/oak_top"))
                .build();

        this.addEntry(DRAWERS);

        BENCHES = SimpleEntrySet.builder(WoodType.class, "bench",
                        AFBlocks.OAK_BENCH, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BenchBlock(Utils.copyPropertySafe(w.planks)))
                .addTag(modRes("benches"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("benches"), Registry.ITEM_REGISTRY)
                .defaultRecipe()
                .setTab(() -> AnotherFurniture.TAB)
                .setRenderType(() -> RenderType::cutout)
                .addTexture(modRes("block/bench/oak"))
                .build();

        this.addEntry(BENCHES);

    }

    private void shuttersPalette(Palette p) {
        float dl = p.getAverageLuminanceStep();
        {
            var c0 = p.get(0);
            var nc0 = new PaletteColor(c0.hcl().withLuminance(c0.hcl().luminance() - (dl * 0.35f)));
            nc0.occurrence = c0.occurrence;
            p.set(0, nc0);
        }
        {
            var c1 = p.get(1);
            var nc1 = new PaletteColor(c1.hcl().withLuminance(c1.hcl().luminance() - (dl * 0.18f)));
            nc1.occurrence = c1.occurrence;
            p.set(1, nc1);
        }
        {
            var c2 = p.get(2);
            var nc2 = new PaletteColor(c2.hcl().withLuminance(c2.hcl().luminance() - (dl * 0.05f)));
            nc2.occurrence = c2.occurrence;
            p.set(+2, nc2);
        }
    }

    //TODO: fix renderer
    @Override
    public void registerBlockEntityRenderers(ClientPlatformHelper.BlockEntityRendererEvent event) {
        event.register((BlockEntityType<CompatShelfBlockTile>) (SHELVES.getTileHolder().tile), ShelfRenderer::new);
        event.register((BlockEntityType<CompatPlanterBoxTile>) (PLANTER_BOXES.getTileHolder().tile), PlanterBoxRenderer::new);

    }


    //idk why but object holder class loader thingie keeps trying to load this if its not inner private like this
    class CompatShelfBlockTile extends ShelfBlockEntity {

        public CompatShelfBlockTile(BlockPos pos, BlockState state) {
            super(pos, state);
        }

        @Override
        public BlockEntityType<?> getType() {
            return SHELVES.getTileHolder().tile;
        }
    }

    private class CompatShelfBlock extends ShelfBlock {
        public CompatShelfBlock(Properties properties) {
            super(properties);
        }

        public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
            return new CompatShelfBlockTile(pos, state);
        }
    }

    class CompatPlanterBoxTile extends PlanterBoxBlockEntity {

        public CompatPlanterBoxTile(BlockPos pos, BlockState state) {
            super(pos, state);
        }

        @Override
        public BlockEntityType<?> getType() {
            return PLANTER_BOXES.getTileHolder().tile;
        }
    }

    private class CompatPlanterBoxBlock extends PlanterBoxBlock {
        public CompatPlanterBoxBlock(Properties properties) {
            super(properties);
        }

        public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
            return new CompatPlanterBoxTile(pos, state);
        }
    }

    class CompatDrawerBlockTile extends DrawerBlockEntity {

        public CompatDrawerBlockTile(BlockPos pos, BlockState state) {
            super(pos, state);
        }

        @Override
        public BlockEntityType<?> getType() {
            return DRAWERS.getTileHolder().tile;
        }
    }

}
