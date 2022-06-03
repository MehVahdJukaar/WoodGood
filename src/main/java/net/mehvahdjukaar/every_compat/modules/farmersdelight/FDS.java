package net.mehvahdjukaar.every_compat.modules.farmersdelight;

import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.state.BlockBehaviour;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.registry.ModBlocks;

public class FDS extends SimpleModule {

    public FDS(String modId) {
        super(modId, "fd");

        SimpleEntrySet<?, ?> cabinets = SimpleEntrySet.builder("cabinet",
                        ModBlocks.OAK_CABINET, ()-> WoodType.OAK_WOOD_TYPE,
                        w ->  new CompatCabinetBlock(BlockBehaviour.Properties.copy(w.planks).strength(2.5F)))
                .addTag(modRes("cabinets"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("cabinets"), Registry.ITEM_REGISTRY)
                .defaultRecipe()
                .addTile(CompatCabinetBlockTile::new)
                .setTab(FarmersDelight.CREATIVE_TAB)
                .addTexture(modRes("block/oak_cabinet_front"))
                .addTexture(modRes("block/oak_cabinet_side"))
                .addTexture(modRes("block/oak_cabinet_top"))
                .build();

        this.addEntry(cabinets);
    }
}
