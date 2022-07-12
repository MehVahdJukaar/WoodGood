package net.mehvahdjukaar.every_compat.modules.graveyard;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.blocks.SarcophagusBlock;
import com.finallion.graveyard.config.GraveyardConfig;
import com.finallion.graveyard.init.TGBlocks;
import com.finallion.graveyard.init.TGItems;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class GraveyardModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> COFFINS;

    public GraveyardModule(String modId) {
        super(modId, "gy");


        COFFINS = SimpleEntrySet.builder(WoodType.class, "coffin",
                        () -> getModBlock("oak_coffin"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new SarcophagusBlock(BlockBehaviour.Properties.of(Material.WOOD).noOcclusion().strength(1.0F), true))
                .addTag(modRes("coffins"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("coffins"), Registry.ITEM_REGISTRY)
                .defaultRecipe()
                .setTab(() -> TheGraveyard.GROUP)
                .build();

        this.addEntry(COFFINS);

    }
}
