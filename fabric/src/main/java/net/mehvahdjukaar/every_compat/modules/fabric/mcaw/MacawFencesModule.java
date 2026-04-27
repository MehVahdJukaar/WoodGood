package net.mehvahdjukaar.every_compat.modules.fabric.mcaw;

import net.kikoz.mcwfences.objects.FenceHitbox;
import net.kikoz.mcwfences.objects.WiredFence;
import net.mehvahdjukaar.every_compat.modules.macaw.MacawFencesModuleAbstract;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;

//See MacawFencesModuleAbstract's SUPPORTED VERSION
public class MacawFencesModule extends MacawFencesModuleAbstract {

    public MacawFencesModule(String modId) {
        super(modId);
    }

    @Override
    public ResourceLocation getTabKey() {
        return modRes("fencesgroup");
    }

    @Override
    public Block newFenceBlock(WoodType woodType) {
        return new FenceBlock(Utils.copyPropertySafe(woodType.planks)
                .strength(1.4F, 2.0F).noOcclusion());
    }

    @Override
    public Block newFenceGateBlock(WoodType woodType) {
        return new FenceGateBlock(Utils.copyPropertySafe(woodType.planks)
                .strength(1.4F, 2.0F).noOcclusion(), woodType.toVanillaOrOak());
    }

    @Override
    public Block newWiredFence(WoodType woodType) {
        return new WiredFence(Utils.copyPropertySafe(woodType.planks)
                .strength(1.5F, 2.5F).noOcclusion());
    }

    @Override
    public Block newFenceHitbox(LeavesType leavesType) {
        return new FenceHitbox(Utils.copyPropertySafe(leavesType.leaves).lightLevel((s) -> 0)
                .strength(0.2F, 0.3F).noOcclusion()
                .mapColor(leavesType.leaves.defaultMapColor()));
    }

}
