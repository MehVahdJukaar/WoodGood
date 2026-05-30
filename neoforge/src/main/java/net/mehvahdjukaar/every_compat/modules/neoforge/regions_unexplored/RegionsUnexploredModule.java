package net.mehvahdjukaar.every_compat.modules.neoforge.regions_unexplored;

import net.mehvahdjukaar.every_compat.modules.regions_unexplored.RegionsUnexploredModuleAbstract;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.world.level.block.Block;
import net.regions_unexplored.block.type.wood.BranchBlock;
import net.regions_unexplored.world.level.block.plant.tall.ShrubBlock;

//See MacawFurnitureAbstractModule's SUPPORTED VERSION
public class RegionsUnexploredModule extends RegionsUnexploredModuleAbstract {

    public RegionsUnexploredModule(String modId) {
        super(modId);
    }

    @Override
    public Block newShrubBlock(LeavesType l) {
        return new ShrubBlock(SHRUB_PROPERTIES);
    }

    @Override
    public Block newBranchBlock(WoodType w) {
        return new BranchBlock(BRANCH_PROPERTIES);
    }

}
