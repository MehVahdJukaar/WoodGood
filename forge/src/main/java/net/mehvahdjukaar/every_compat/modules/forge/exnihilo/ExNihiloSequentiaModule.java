package net.mehvahdjukaar.every_compat.modules.forge.exnihilo;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import novamachina.exnihilosequentia.common.block.barrels.NetherBarrelBlock;
import novamachina.exnihilosequentia.common.block.barrels.WoodBarrelBlock;
import novamachina.exnihilosequentia.common.block.crucibles.NetherCrucibleBlock;
import novamachina.exnihilosequentia.common.block.crucibles.WoodCrucibleBlock;
import novamachina.exnihilosequentia.common.block.sieves.NetherSieveBlock;
import novamachina.exnihilosequentia.common.block.sieves.WoodSieveBlock;
import novamachina.exnihilosequentia.common.init.ExNihiloBlockEntities;
import novamachina.exnihilosequentia.common.init.ExNihiloInitialization;

public class ExNihiloSequentiaModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> sieves;
    public final SimpleEntrySet<WoodType, Block> barrels;
    public final SimpleEntrySet<WoodType, Block> crucibles;

    public ExNihiloSequentiaModule(String modId) {
        super(modId, "ens");

        sieves = SimpleEntrySet.builder(WoodType.class, "sieve",
                        () -> getModBlock("oak_sieve"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> w.canBurn() ? new WoodSieveBlock() : new NetherSieveBlock()
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/oak_sieve"))
                .setTab(() -> ExNihiloInitialization.ITEM_GROUP)
                .addRecipe(modRes("ens_oak_sieve"))
                .addTile(ExNihiloBlockEntities.SIEVE_ENTITY)
                .setRenderType(()-> RenderType::cutout)
                .build();

        this.addEntry(sieves);

        barrels = SimpleEntrySet.builder(WoodType.class, "barrel",
                        () -> getModBlock("oak_barrel"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> w.canBurn() ? new WoodBarrelBlock() : new NetherBarrelBlock()
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTextureM(modRes("block/oak_barrel"), EveryCompat.res("block/ens_barrel_m"))
                .setTab(() -> ExNihiloInitialization.ITEM_GROUP)
                .addRecipe(modRes("ens_oak_barrel"))
                .addTile(() -> getModTile("barrels"))
                .build();

        this.addEntry(barrels);

        crucibles = SimpleEntrySet.builder(WoodType.class, "crucible",
                        () -> getModBlock("oak_crucible"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> w.canBurn() ? new WoodCrucibleBlock() : new NetherCrucibleBlock()
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/oak_crucible"))
                .setTab(() -> ExNihiloInitialization.ITEM_GROUP)
                .addRecipe(modRes("ens_oak_crucible"))
                .addTile(ExNihiloBlockEntities.WOODEN_CRUCIBLE_ENTITY)
                .build();

        this.addEntry(crucibles);

    }
}
