package net.mehvahdjukaar.every_compat.modules.farmersdelight;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
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

public class FarmersDelightModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> CABINETS;

    public FarmersDelightModule(String modId) {
        super(modId, "fd");

        CABINETS = SimpleEntrySet.builder(WoodType.class, "cabinet",
                        () -> getModBlock("oak_cabinet"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CompatCabinetBlock(Utils.copyPropertySafe(w.planks)))
                .addTag(modRes("cabinets"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("cabinets"), Registry.ITEM_REGISTRY)
                .addTag(modRes("cabinets/wooden"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .defaultRecipe()
                .addTile(CompatCabinetBlockTile::new)
                .setTab(() -> FarmersDelight.CREATIVE_TAB)
                .createPaletteFromOak(Palette::increaseDown)
                .addTexture(EveryCompat.res("block/oak_cabinet_front"))
                .addTexture(EveryCompat.res("block/oak_cabinet_side"))
                .addTexture(EveryCompat.res("block/oak_cabinet_top"))
                .addTextureM(EveryCompat.res("block/oak_cabinet_front_open"), EveryCompat.res("block/oak_cabinet_front_open_m"))
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
