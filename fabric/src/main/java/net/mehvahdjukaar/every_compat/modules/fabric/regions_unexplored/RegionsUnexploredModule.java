package net.mehvahdjukaar.every_compat.modules.fabric.regions_unexplored;

import io.github.uhq_games.regions_unexplored.block.RuBlocks;
import io.github.uhq_games.regions_unexplored.world.level.block.plant.branch.BranchBlock;
import io.github.uhq_games.regions_unexplored.world.level.block.plant.tall.ShrubBlock;
import net.mehvahdjukaar.every_compat.modules.regions_unexplored.RegionsUnexploredModuleAbstract;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;

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
        return new BranchBlock(BlockBehaviour.Properties.copy(RuBlocks.ACACIA_BRANCH), BranchBlock.BranchType.BRANCH);
    }

}
