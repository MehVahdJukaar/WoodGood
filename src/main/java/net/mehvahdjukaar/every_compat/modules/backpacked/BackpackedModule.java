package net.mehvahdjukaar.every_compat.modules.backpacked;

import com.mrcrayfish.backpacked.Backpacked;
import com.mrcrayfish.backpacked.block.ShelfBlock;
import com.mrcrayfish.backpacked.client.renderer.entity.layers.ShelfRenderer;
import com.mrcrayfish.backpacked.core.ModBlocks;
import com.mrcrayfish.backpacked.tileentity.ShelfBlockEntity;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
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

public class BackpackedModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> SHELF;

    public BackpackedModule(String modId) {
        super(modId, "bp");

        SHELF = SimpleEntrySet.builder("backpack_shelf",
                        ModBlocks.OAK_BACKPACK_SHELF, ()-> WoodType.OAK_WOOD_TYPE,
                        w -> new CompatShelfBlock(BlockBehaviour.Properties.copy(w.planks).instabreak()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(Backpacked.TAB)
                .addRecipe(modRes("oak_backpack_shelf"))
                .addTile(CompatShelfBlockEntity::new)
                .setRenderType(()->RenderType::cutout)
                .build();

        this.addEntry(SHELF);
    }

    @Override
    public void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer((BlockEntityType<ShelfBlockEntity>) (SHELF.getTileHolder().tile), ShelfRenderer::new);
    }

    class CompatShelfBlockEntity extends ShelfBlockEntity {

        public CompatShelfBlockEntity(BlockPos pos, BlockState state) {
            super(pos, state);
        }

        @Override
        public BlockEntityType<?> getType() {
            return SHELF.getTileHolder().tile;
        }
    }

    private class CompatShelfBlock extends ShelfBlock {
        public CompatShelfBlock(Properties properties) {
            super(properties);
        }

        public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
            return new CompatShelfBlockEntity(pos, state);
        }
    }
}
