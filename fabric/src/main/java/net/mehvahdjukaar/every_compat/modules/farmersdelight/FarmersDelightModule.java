package net.mehvahdjukaar.every_compat.modules.farmersdelight;

import com.nhoryzon.mc.farmersdelight.FarmersDelightMod;
import com.nhoryzon.mc.farmersdelight.block.PantryBlock;
import com.nhoryzon.mc.farmersdelight.entity.block.PantryBlockEntity;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class FarmersDelightModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> CABINETS;

    public FarmersDelightModule(String modId) {
        super(modId, "fd");

        CABINETS = SimpleEntrySet.builder(WoodType.class, "pantry",
                        () -> getModBlock("oak_pantry"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CompatCabinetBlock())
                .addTag(modRes("cabinets"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("cabinets"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .defaultRecipe()
                .addTile(CompatCabinetBlockTile::new)
                .setTab(() -> FarmersDelightMod.ITEM_GROUP)
                .createPaletteFromOak(Palette::increaseDown)
                .addTexture(EveryCompat.res("block/oak_cabinet_front"))
                .addTexture(EveryCompat.res("block/oak_cabinet_side"))
                .addTexture(EveryCompat.res("block/oak_cabinet_top"))
                .addTextureM(EveryCompat.res("block/oak_cabinet_front_open"), EveryCompat.res("block/oak_cabinet_front_open_m"))
                .build();

        this.addEntry(CABINETS);
    }

    class CompatCabinetBlockTile extends PantryBlockEntity {

        public CompatCabinetBlockTile(BlockPos pos, BlockState state) {
            super(pos, state);
        }

        @Override
        public BlockEntityType<?> getType() {
            return CABINETS.getTileHolder().tile;
        }

    }

    private class CompatCabinetBlock extends PantryBlock {
        public CompatCabinetBlock() {
            super();
        }

        public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
            return new CompatCabinetBlockTile(pos, state);
        }
    }

}
