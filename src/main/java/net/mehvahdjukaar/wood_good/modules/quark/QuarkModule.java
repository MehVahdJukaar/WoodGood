package net.mehvahdjukaar.wood_good.modules.quark;

import net.mehvahdjukaar.selene.block_set.leaves.LeavesType;
import net.mehvahdjukaar.wood_good.modules.CompatModule;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Collection;

public class QuarkModule extends CompatModule {

    public QuarkModule(String modId) {
        super(modId);
    }

    @Override
    public void registerLeavesBlocks(IForgeRegistry<Block> registry, Collection<LeavesType> leavesTypes) {

    }
}
