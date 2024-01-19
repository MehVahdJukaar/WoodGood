package net.mehvahdjukaar.every_compat.modules.market_crates;

import com.lfaoanl.marketcrates.common.items.CrateItem;
import com.lfaoanl.marketcrates.common.render.CrateBlockEntityRenderer;
import com.lfaoanl.marketcrates.forge.blocks.CrateBlock;
import com.lfaoanl.marketcrates.forge.blocks.CrateBlockEntity;
import com.lfaoanl.marketcrates.forge.core.CrateRegistry;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

public class MarketCratesModule extends SimpleModule {
    public final SimpleEntrySet<WoodType, Block> CRATES;

    public MarketCratesModule(String modId) {
        super(modId, "mcs");

        CRATES = SimpleEntrySet.builder(WoodType.class, "crate",
                        CrateRegistry.blocks.get("oak"), () -> WoodType.OAK_WOOD_TYPE,
                w -> new CompatCrateBlock()

        )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addCustomItem((w, b, p) -> new CrateItem(b, p))
                .addTile(CompatCrateBlockEntity::new)
                .setTab(() -> CreativeModeTab.TAB_DECORATIONS)
                .defaultRecipe()
                .build();
        this.addEntry(CRATES);
    }

    //TYPE: COMPAT MEMBERS
    public class CompatCrateBlock extends CrateBlock {

        @Override
        public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
            return new CompatCrateBlockEntity(pos, state);
        }

        @Override
        protected void openGui(BlockState state, Level world, Player player, BlockPos pos) {
            // FORGE open inventory
            BlockEntity tile = world.getBlockEntity(pos);
            if (tile instanceof CompatCrateBlockEntity) {
                NetworkHooks.openGui((ServerPlayer) player, (CompatCrateBlockEntity) tile, tile.getBlockPos());
            }

        }
    }

    public class CompatCrateBlockEntity extends CrateBlockEntity {

        CompatCrateBlockEntity(BlockPos pos, BlockState state) {
            super(pos, state);
        }

        @Override
        @SuppressWarnings("ConstantConditions")
        public @NotNull BlockEntityType<?> getType() {
            return CRATES.getTileHolder().tile;
        }

    }

    @Override
    @SuppressWarnings("ConstantConditions")
    public void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        //noinspection unchecked
        event.registerBlockEntityRenderer((BlockEntityType<? extends CompatCrateBlockEntity>) CRATES.getTileHolder().tile, CrateBlockEntityRenderer::new);
    }
}
