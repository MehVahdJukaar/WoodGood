package net.mehvahdjukaar.every_compat.modules.storage_drawers;

import com.jaquadro.minecraft.storagedrawers.block.BlockStandardDrawers;
import com.jaquadro.minecraft.storagedrawers.block.tile.TileEntityDrawers;
import com.jaquadro.minecraft.storagedrawers.block.tile.TileEntityDrawersStandard;
import com.jaquadro.minecraft.storagedrawers.client.renderer.TileEntityDrawersRenderer;
import com.jaquadro.minecraft.storagedrawers.core.ModBlocks;
import com.jaquadro.minecraft.storagedrawers.core.ModItemGroup;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.mehvahdjukaar.selene.client.asset_generators.textures.Palette;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.tags.BlockTags;
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
                .setTab(ModItemGroup.STORAGE_DRAWERS)
                .defaultRecipe()
                .addTile(CompatStandardDrawersEntity1::new)
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
      //  p.remove(p.getDarkest());
     //   p.remove(p.getDarkest());
     //   p.remove(p.getLightest());
//        p.remove(p.getLightest());
    }

    @Override
    public void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer((BlockEntityType<TileEntityDrawersStandard>) (DRAWERS.getTileHolder().tile), TileEntityDrawersRenderer::new);
    }

    private class CompatStandardDrawers extends BlockStandardDrawers {
        public CompatStandardDrawers(int drawerCount, boolean halfDepth, Properties properties) {
            super(drawerCount, halfDepth, properties);
        }

        public TileEntityDrawers newBlockEntity(BlockPos pos, BlockState state) {
            return switch (this.getDrawerCount()) {
                case 1 -> new CompatStandardDrawersEntity1(pos, state);
               // case 2 -> new CompatStandardDrawersEntity2(pos, state);
                default -> null;
             //   case 4 -> new CompatStandardDrawersEntity4(pos, state);
                //add these^
            };
        }
    }

    class CompatStandardDrawersEntity1 extends TileEntityDrawersStandard.Slot1 {

        public CompatStandardDrawersEntity1(BlockPos pos, BlockState state) {
            super(pos, state);
        }

        @Override
        public @NotNull BlockEntityType<?> getType() {
            return DRAWERS.getTileHolder().tile;
        }
    }
}
