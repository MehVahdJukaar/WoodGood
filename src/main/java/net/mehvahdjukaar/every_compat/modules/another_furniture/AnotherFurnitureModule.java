package net.mehvahdjukaar.every_compat.modules.another_furniture;

import com.starfish_studios.another_furniture.AnotherFurniture;
import com.starfish_studios.another_furniture.block.*;
import com.starfish_studios.another_furniture.block.entity.PlanterBoxBlockEntity;
import com.starfish_studios.another_furniture.block.entity.ShelfBlockEntity;
import com.starfish_studios.another_furniture.client.renderer.blockentity.PlanterBoxRenderer;
import com.starfish_studios.another_furniture.client.renderer.blockentity.ShelfRenderer;
import com.starfish_studios.another_furniture.registry.AFBlocks;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.mehvahdjukaar.selene.client.asset_generators.textures.PaletteColor;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.event.EntityRenderersEvent;

public class AnotherFurnitureModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> TABLES;
    public final SimpleEntrySet<WoodType, Block> CHAIRS;
    public final SimpleEntrySet<WoodType, Block> SHELVES;
    public final SimpleEntrySet<WoodType, Block> PLANTER_BOXES;
    public final SimpleEntrySet<WoodType, Block> SHUTTERS;

    public AnotherFurnitureModule(String modId) {
        super(modId, "af");

        PLANTER_BOXES = SimpleEntrySet.builder("planter_box",
                        AFBlocks.OAK_PLANTER_BOX, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new CompatPlanterBoxBlock(BlockBehaviour.Properties.copy(w.planks).strength(2.0F, 3.0F)))
                .addTag(modRes("planter_boxes"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("planter_boxes"), Registry.ITEM_REGISTRY)
                .useLootFromBase()
                .defaultRecipe()
                .addTile(CompatPlanterBoxTile::new)
                .setTab(AnotherFurniture.TAB)
                .addTexture(modRes("block/planter_box/oak"))
                .build();

        this.addEntry(PLANTER_BOXES);

        SHUTTERS = SimpleEntrySet.builder("shutter",
                        AFBlocks.OAK_SHUTTER, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new ShutterBlock(BlockBehaviour.Properties.copy(w.planks).strength(2.0F, 3.0F).noOcclusion()))
                .addTag(modRes("shutters"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("shutters"), Registry.ITEM_REGISTRY)
                .useLootFromBase()
                .defaultRecipe()
                .setTab(AnotherFurniture.TAB)
                .addTexture(modRes("block/shutter/oak_bottom"))
                .addTexture(modRes("block/shutter/oak_middle"))
                .addTexture(modRes("block/shutter/oak_none"))
                .addTexture(modRes("block/shutter/oak_top"))
                .createPaletteFromOak(p -> {
                    float dl = p.getAverageLuminanceStep();
                    {
                        var c0 = p.get(0);
                        var nc0 = new PaletteColor(c0.hcl().withLuminance(c0.hcl().luminance() - (dl * 0.3f)));
                        nc0.occurrence = c0.occurrence;
                        p.set(0, nc0);
                    }
                    {
                        var c1 = p.get(1);
                        var nc1 = new PaletteColor(c1.hcl().withLuminance(c1.hcl().luminance() - (dl * 0.2f)));
                        nc1.occurrence = c1.occurrence;
                        p.set(1, nc1);
                    }
                    {
                        var c2 = p.get(2);
                        var nc2 = new PaletteColor(c2.hcl().withLuminance(c2.hcl().luminance() - (dl * 0.1f)));
                        nc2.occurrence = c2.occurrence;
                        p.set(+2, nc2);
                    }
                })
                .build();

        this.addEntry(SHUTTERS);

        TABLES = SimpleEntrySet.builder("table",
                        AFBlocks.OAK_TABLE, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new TableBlock(BlockBehaviour.Properties.copy(w.planks)))
                .addTag(modRes("tables"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("tables"), Registry.ITEM_REGISTRY)
                .useLootFromBase()
                .defaultRecipe()
                .setTab(AnotherFurniture.TAB)
                .addTexture(modRes("block/table/oak_sides"))
                .addTexture(modRes("block/table/oak_top"))
                .build();

        this.addEntry(TABLES);

        CHAIRS = SimpleEntrySet.builder("chair",
                        AFBlocks.OAK_CHAIR, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new ChairBlock(BlockBehaviour.Properties.copy(w.planks)))
                .addTag(modRes("chairs"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("chairs"), Registry.ITEM_REGISTRY)
                .defaultRecipe()
                .setTab(AnotherFurniture.TAB)
                .setRenderType(() -> RenderType::cutout)
                .addTexture(modRes("block/chair/oak_back"))
                .addTexture(modRes("block/chair/oak_bottom"))
                .addTexture(modRes("block/chair/oak_seat"))
                .build();

        this.addEntry(CHAIRS);

        SHELVES = SimpleEntrySet.builder("shelf",
                        AFBlocks.OAK_SHELF, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new CompatShelfBlock(BlockBehaviour.Properties.copy(w.planks)))
                .addTag(modRes("shelves"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("shelves"), Registry.ITEM_REGISTRY)
                .addTile(CompatShelfBlockTile::new)
                .defaultRecipe()
                .setTab(AnotherFurniture.TAB)
                .addTexture(modRes("block/shelf/oak_sides"))
                .addTexture(modRes("block/shelf/oak_top"))
                .addTexture(modRes("block/shelf/oak_supports"))
                .build();

        this.addEntry(SHELVES);

    }

    //TODO: fix renderer
    @Override
    public void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer((BlockEntityType<CompatShelfBlockTile>) (SHELVES.getTileHolder().tile), ShelfRenderer::new);
        event.registerBlockEntityRenderer((BlockEntityType<CompatPlanterBoxTile>) (PLANTER_BOXES.getTileHolder().tile), PlanterBoxRenderer::new);

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

}
