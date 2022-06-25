package net.mehvahdjukaar.every_compat.modules.farmersdelight;

import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.mehvahdjukaar.selene.client.asset_generators.textures.Palette;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.block.CabinetBlock;
import vectorwing.farmersdelight.common.block.entity.CabinetBlockEntity;
import vectorwing.farmersdelight.common.registry.ModBlocks;

public class FarmersDelightModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> CABINETS;

    public FarmersDelightModule(String modId) {
        super(modId, "fd");

        CABINETS = SimpleEntrySet.builder(WoodType.class,"cabinet",
                        ModBlocks.OAK_CABINET, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new CompatCabinetBlock(WoodGood.copySafe(w.planks).strength(2.5F)))
                .addTag(modRes("cabinets"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("cabinets"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .defaultRecipe()
                .addTile(CompatCabinetBlockTile::new)
                .setTab(()->FarmersDelight.CREATIVE_TAB)
                .createPaletteFromOak(Palette::increaseDown)
                .addTexture(WoodGood.res("block/oak_cabinet_front"))
                .addTexture(WoodGood.res("block/oak_cabinet_side"))
                .addTexture(WoodGood.res("block/oak_cabinet_top"))
                .addTextureM(WoodGood.res("block/oak_cabinet_front_open"), WoodGood.res("block/oak_cabinet_front_open_m"))
                .build();

        this.addEntry(CABINETS);
    }

    class CompatCabinetBlockTile extends CabinetBlockEntity {

        public CompatCabinetBlockTile(BlockPos pos, BlockState state) {
            super(pos, state);
        }

        @Override
        public BlockEntityType<?> getType() {
            return CABINETS.getTileHolder().tile;
        }

    }

    private class CompatCabinetBlock extends CabinetBlock {
        public CompatCabinetBlock(Properties properties) {
            super(properties);
        }

        public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
            return new CompatCabinetBlockTile(pos, state);
        }
    }

}
