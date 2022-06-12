package net.mehvahdjukaar.every_compat.modules.storage_drawers;

import com.jaquadro.minecraft.storagedrawers.block.BlockStandardDrawers;
import com.jaquadro.minecraft.storagedrawers.block.tile.TileEntityDrawers;
import com.jaquadro.minecraft.storagedrawers.block.tile.TileEntityDrawersStandard;
import com.jaquadro.minecraft.storagedrawers.client.renderer.TileEntityDrawersRenderer;
import com.jaquadro.minecraft.storagedrawers.core.ModBlocks;
import com.jaquadro.minecraft.storagedrawers.core.ModItemGroup;
import com.simibubi.create.foundation.item.ItemDescription;
import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.mehvahdjukaar.selene.client.asset_generators.textures.Palette;
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
import org.jetbrains.annotations.NotNull;


public class StorageDrawersModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, BlockStandardDrawers> DRAWERS;

    public StorageDrawersModule(String modId) {
        super(modId, "sd");

        DRAWERS = SimpleEntrySet.builder("full_drawers_1",
                        ModBlocks.OAK_FULL_DRAWERS_1, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new CompatStandardDrawers(1, false, BlockBehaviour.Properties.copy(w.planks).strength(1.5f, 2.3f)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(ModItemGroup.STORAGE_DRAWERS)
                .defaultRecipe()
                .createPaletteFromOak(this::drawersPalette)
                .setRenderType(()-> RenderType::cutout)
                .addTexture(modRes("blocks/drawers_oak_front_1"))
                .addTexture(modRes("blocks/drawers_oak_side"))
                .addTexture(modRes("blocks/drawers_oak_sort"))
                .addTexture(modRes("blocks/drawers_oak_trim"))
                .build();

        this.addEntry(DRAWERS);
    }

    private void drawersPalette(Palette p) {
        p.remove(p.getDarkest());
        p.remove(p.getDarkest());
        p.remove(p.getLightest());
    }

//    @Override
//    public void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
//        event.registerBlockEntityRenderer((BlockEntityType<TileEntityDrawersStandard>) (DRAWERS.getTileHolder().tile), TileEntityDrawersRenderer::new);
//    }

//    class CompatStandardDrawersEntity extends TileEntityDrawersStandard {
//
//        public CompatStandardDrawersEntity(BlockEntityType<?> tileEntityType, BlockPos pos, BlockState state) {
//            super(tileEntityType, pos, state);
//        }
//
//        @Override
//        public @NotNull BlockEntityType<?> getType() {
//            return DRAWERS.getTileHolder().tile;
//        }
//    }

//    private class CompatStandardDrawers extends BlockStandardDrawers {
//        public CompatStandardDrawers(int drawerCount, boolean halfDepth, Properties properties) {
//            super(drawerCount, halfDepth, properties);
//        }
//
//        public TileEntityDrawers newBlockEntity(BlockPos pos, BlockState state) {
//            return TileEntityDrawersStandard.createEntity(this.getDrawerCount(), pos, state);
//        }
//    }
//
//    @Override
//    public void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
//        event.registerBlockEntityRenderer((BlockEntityType<TileEntityDrawersStandard>) (DRAWERS.getTileHolder().tile), TileEntityDrawersRenderer::new);
//    }

    @Override
    public void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer((BlockEntityType<TileEntityDrawersStandard>) (DRAWERS.getTileHolder().tile), TileEntityDrawersRenderer::new);
    }

    class CompatStandardDrawersEntity extends TileEntityDrawersStandard {

        public CompatStandardDrawersEntity(BlockEntityType<?> tileEntityType, BlockPos pos, BlockState state) {
            super(tileEntityType, pos, state);
        }

        @Override
        public @NotNull BlockEntityType<?> getType() {
            return DRAWERS.getTileHolder().tile;
        }
    }

    private class CompatStandardDrawers extends BlockStandardDrawers {
        public CompatStandardDrawers(int drawerCount, boolean halfDepth, Properties properties) {
            super(drawerCount, halfDepth, properties);
        }

        public TileEntityDrawers newBlockEntity(BlockEntityType<?> tileEntityType, BlockPos pos, BlockState state) {
            return new CompatStandardDrawersEntity(tileEntityType, pos, state);
        }
    }
}
