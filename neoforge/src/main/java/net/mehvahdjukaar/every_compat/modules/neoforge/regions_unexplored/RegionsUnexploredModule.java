package net.mehvahdjukaar.every_compat.modules.neoforge.regions_unexplored;

import net.mehvahdjukaar.every_compat.modules.regions_unexplored.RegionsUnexploredModuleAbstract;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import net.regions_unexplored.block.RuBlocks;
import net.regions_unexplored.world.level.block.plant.branch.BranchBlock;
import net.regions_unexplored.world.level.block.plant.tall.ShrubBlock;

// SUPPORT: v0.5.6+
public class RegionsUnexploredModule extends RegionsUnexploredModuleAbstract {

    public RegionsUnexploredModule(String modId) {
        super(modId);
    }

    @Override
    public Block newShrubBlock(LeavesType l) {
        return new ShrubBlock(Utils.copyPropertySafe(l.leaves).pushReaction(PushReaction.DESTROY)
                .ignitedByLava().noCollission().instabreak().sound(SoundType.AZALEA)
                .offsetType(BlockBehaviour.OffsetType.XZ));
    }

    @Override
    public Block newBranchBlock(WoodType w) {
        return new BranchBlock(BlockBehaviour.Properties.ofFullCopy(RuBlocks.ACACIA_BRANCH.get()), BranchBlock.BranchType.BRANCH);
    }

}
