package net.mehvahdjukaar.every_compat.modules.exnihilo;

import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.registries.IForgeRegistry;
import novamachina.exnihilosequentia.client.render.BarrelRender;
import novamachina.exnihilosequentia.client.render.CrucibleRender;
import novamachina.exnihilosequentia.client.render.SieveRender;
import novamachina.exnihilosequentia.common.blockentity.barrel.AbstractBarrelEntity;
import novamachina.exnihilosequentia.common.blockentity.crucible.BaseCrucibleEntity;
import novamachina.exnihilosequentia.common.builder.BlockBuilder;
import novamachina.exnihilosequentia.common.init.ExNihiloBlockEntities;
import novamachina.exnihilosequentia.common.init.ExNihiloInitialization;
import novamachina.exnihilosequentia.common.block.barrels.NetherBarrelBlock;
import novamachina.exnihilosequentia.common.block.barrels.WoodBarrelBlock;
import novamachina.exnihilosequentia.common.block.crucibles.NetherCrucibleBlock;
import novamachina.exnihilosequentia.common.block.crucibles.WoodCrucibleBlock;
import novamachina.exnihilosequentia.common.block.sieves.NetherSieveBlock;
import novamachina.exnihilosequentia.common.block.sieves.WoodSieveBlock;
import novamachina.exnihilosequentia.common.blockentity.SieveEntity;
import novamachina.exnihilosequentia.common.blockentity.barrel.WoodBarrelEntity;
import novamachina.exnihilosequentia.common.blockentity.crucible.WoodCrucibleEntity;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class ExNihiloSequentiaModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> SIEVES;
    public final SimpleEntrySet<WoodType, Block> BARRELS;
    public final SimpleEntrySet<WoodType, Block> CRUCIBLE;

    public ExNihiloSequentiaModule(String modId) {
        super(modId, "ens");

        SIEVES = SimpleEntrySet.builder(WoodType.class, "sieve",
                        () -> getModBlock("oak_sieve"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> w.canBurn() ? new CompatWoodSieveBlock() : new NetherSieveBlock()
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/oak_sieve"))
                .setTab(() -> ExNihiloInitialization.ITEM_GROUP)
                .addRecipe(modRes("ens_oak_sieve"))
                .addTile(SieveEntity::new)
                .setRenderType(()-> RenderType::cutout)
                .build();

        this.addEntry(SIEVES);

        BARRELS = SimpleEntrySet.builder(WoodType.class, "barrel",
                        () -> getModBlock("oak_barrel"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> w.canBurn() ? new CompatWoodBarrelBlock() : new NetherBarrelBlock()
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/oak_barrel"))
                .setTab(() -> ExNihiloInitialization.ITEM_GROUP)
                .addRecipe(modRes("ens_oak_barrel"))
                .addTile(CompatWoodBarrelEntity::new)
                .build();

        this.addEntry(BARRELS);

        CRUCIBLE = SimpleEntrySet.builder(WoodType.class, "crucible",
                        () -> getModBlock("oak_crucible"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> w.canBurn() ? new CompatWoodCrucibleBlock() : new NetherCrucibleBlock()
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/oak_crucible"))
                .setTab(() -> ExNihiloInitialization.ITEM_GROUP)
                .addRecipe(modRes("ens_oak_crucible"))
                .addTile(CompatWoodCrucibleEntity::new)
                .build();

        this.addEntry(CRUCIBLE);

    }

    @Override
    public void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer((BlockEntityType<? extends WoodCrucibleEntity>) CRUCIBLE.getTileHolder().tile,
                CrucibleRender::new);
        event.registerBlockEntityRenderer((BlockEntityType<? extends SieveEntity>) SIEVES.getTileHolder().tile,
                SieveRender::new);
        event.registerBlockEntityRenderer((BlockEntityType<? extends AbstractBarrelEntity>) BARRELS.getTileHolder().tile,
                BarrelRender::new);
    }

    //TYPE: Sieve
    class CompatWoodSieveBlock extends WoodSieveBlock {
        public CompatWoodSieveBlock() {
            super();
        }

        @Override
        public BlockEntity newBlockEntity(@Nonnull BlockPos pos, @Nonnull BlockState state) {
            return new CompatSieveEntity(pos, state);
        }
    }

    class CompatSieveEntity extends SieveEntity {
        public CompatSieveEntity(BlockPos pos, BlockState state) {
            super(pos, state);
        }

        @Override
        public BlockEntityType<?> getType() {
            return SIEVES.getTileHolder().tile;
        }
    }

    //TYPE: Barrels
    class CompatWoodBarrelBlock extends WoodBarrelBlock {
        public CompatWoodBarrelBlock() {
            super();
        }

        @Override
        public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
            return new CompatWoodBarrelEntity(pos, state);
        }
    }

    class CompatWoodBarrelEntity extends WoodBarrelEntity {
        public CompatWoodBarrelEntity(BlockPos pos, BlockState state) {
            super(pos, state);
        }

        @Override
        public BlockEntityType<?> getType() {
            return BARRELS.getTileHolder().tile;
        }
    }

    //TYPE: Crucible
    class CompatWoodCrucibleBlock extends WoodCrucibleBlock {
        public CompatWoodCrucibleBlock() {
            super();
        }

        @Override
        public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
            return new CompatWoodCrucibleEntity(pos, state);
        }
    }

    class CompatWoodCrucibleEntity extends WoodCrucibleEntity {
        public CompatWoodCrucibleEntity(BlockPos pos, BlockState state) {
            super(pos, state);
        }

        @Override
        public BlockEntityType<?> getType() {
            return CRUCIBLE.getTileHolder().tile;
        }
    }
}
