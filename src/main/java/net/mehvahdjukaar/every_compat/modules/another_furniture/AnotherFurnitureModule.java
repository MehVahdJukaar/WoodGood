package net.mehvahdjukaar.every_compat.modules.another_furniture;

import com.crispytwig.another_furniture.AnotherFurnitureMod;
import com.crispytwig.another_furniture.block.ChairBlock;
import com.crispytwig.another_furniture.block.ShelfBlock;
import com.crispytwig.another_furniture.block.TableBlock;
import com.crispytwig.another_furniture.block.entity.ShelfBlockEntity;
import com.crispytwig.another_furniture.init.ModBlocks;
import com.crispytwig.another_furniture.render.ShelfRenderer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
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

    public AnotherFurnitureModule(String modId) {
        super(modId, "af");

        TABLES = SimpleEntrySet.builder("table",
                        ModBlocks.OAK_TABLE, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new TableBlock(BlockBehaviour.Properties.copy(w.planks)))
                .addTag(modRes("tables"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("tables"), Registry.ITEM_REGISTRY)
                .useLootFromBase()
                .defaultRecipe()
                .setTab(AnotherFurnitureMod.TAB)
                .addTexture(modRes("block/table/oak_sides"))
                .addTexture(modRes("block/table/oak_top"))
                .build();

        this.addEntry(TABLES);

        CHAIRS = SimpleEntrySet.builder("chair",
                        ModBlocks.OAK_CHAIR, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new ChairBlock(BlockBehaviour.Properties.copy(w.planks)))
                .addTag(modRes("chairs"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("chairs"), Registry.ITEM_REGISTRY)
                .defaultRecipe()
                .setTab(AnotherFurnitureMod.TAB)
                .setRenderType(() -> RenderType::cutout)
                .addTexture(modRes("block/chair/oak_back"))
                .addTexture(modRes("block/chair/oak_bottom"))
                .addTexture(modRes("block/chair/oak_seat"))
                .build();

        this.addEntry(CHAIRS);

        SHELVES = SimpleEntrySet.builder("shelf",
                        ModBlocks.OAK_SHELF, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new CompatShelfBlock(BlockBehaviour.Properties.copy(w.planks)))
                .addTag(modRes("shelves"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("shelves"), Registry.ITEM_REGISTRY)
                .addTile(CompatShelfBlockTile::new)
                .defaultRecipe()
                .setTab(AnotherFurnitureMod.TAB)
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

}
