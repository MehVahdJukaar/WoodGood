package net.mehvahdjukaar.every_compat.modules.neoforge.regions_unexplored;

import net.mehvahdjukaar.every_compat.modules.regions_unexplored.RegionsUnexploredModuleAbstract;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.regions_unexplored.block.type.shrub.ShrubBlock;
import net.regions_unexplored.block.type.wood.BranchBlock;

//See RegionsUnexploredAbstractModule's SUPPORTED VERSION
public class RegionsUnexploredModule extends RegionsUnexploredModuleAbstract {

    public RegionsUnexploredModule(String modId) {
        super(modId);
    }

    @Override
    public Block newShrubBlock(BlockBehaviour.Properties properties) {
        return new ShrubBlock(properties);
    }

    @Override
    public Block newBranchBlock(BlockBehaviour.Properties properties) {
        return new BranchBlock(properties);
    }

}
