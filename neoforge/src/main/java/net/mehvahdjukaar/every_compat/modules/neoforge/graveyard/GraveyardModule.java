
package net.mehvahdjukaar.every_compat.modules.neoforge.graveyard;

import com.finallion.graveyard.blockentities.SarcophagusBlockEntity;
import com.finallion.graveyard.blocks.SarcophagusBlock;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.moonlight.api.misc.Registrator;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

public class GraveyardModule extends EveryCompatModule {

    public final SimpleEntrySet<WoodType, Block> COFFINS;

    public static BlockEntityType<? extends SarcophagusBlockEntity> COFFIN_TILE;

    public GraveyardModule(String modId) {
        super(modId, "gy");

        COFFINS = SimpleEntrySet.builder(WoodType.class, "coffin",
                        getModBlock("oak_coffin"), () -> VanillaWoodTypes.OAK,
                        w -> new CompatCoffinfBlock(Utils.copyPropertySafe(Blocks.OAK_PLANKS).noOcclusion(), w)
                )
                .addTile(CompatCoffinBlockTile::new)
                .addTextureM(modRes("block/oak_coffin"), EveryCompat.res("model/oak_coffin_m"))
                .addTag(modRes("coffins"), Registries.BLOCK, Registries.ITEM)
                .setTabKey(modRes("graveyard_group"))
                .defaultRecipe()
                .build();
        this.addEntry(COFFINS);

    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void registerBlockEntityRenderers(ClientHelper.BlockEntityRendererEvent event) {
        event.register(COFFINS.getTile(CompatCoffinBlockTile.class), CompatCoffinRenderer::new);
    }

    @Override
    public void registerTiles(Registrator<BlockEntityType<?>> registry) {
        super.registerTiles(registry);
        COFFIN_TILE = COFFINS.getTile(CompatCoffinBlockTile.class);
    }

    //idk why but object holder class loader thingie keeps trying to load this if its not inner private like this
    class CompatCoffinBlockTile extends SarcophagusBlockEntity {

        private final WoodType woodType;

        public CompatCoffinBlockTile(BlockPos pos, BlockState state) {
            super(pos, state);
            this.woodType = ((CompatCoffinfBlock) state.getBlock()).getWoodType();
        }

        @Override
        public @NotNull BlockEntityType<?> getType() {
            return COFFINS.getTile();
        }

        public WoodType getWoodType() {
            return this.woodType;
        }
    }

    private class CompatCoffinfBlock extends SarcophagusBlock {
        private final WoodType woodType;

        public CompatCoffinfBlock(Properties properties, WoodType woodType) {
            super(properties, true, "lid", "base");
            this.woodType = woodType;
        }

        public WoodType getWoodType() {
            return woodType;
        }

        public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
            return new CompatCoffinBlockTile(pos, state);
        }
    }
}