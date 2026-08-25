package net.mehvahdjukaar.every_compat.modules.forge.mcaw;

import com.mcwbridges.kikoz.objects.*;
import net.mehvahdjukaar.every_compat.modules.macaw.MacawBridgesModuleAbstract;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.world.level.block.Block;

//See MacawBridgesModuleAbstract's SUPPORTED VERSION
public class MacawBridgesModule extends MacawBridgesModuleAbstract {

    public MacawBridgesModule(String modId) {
        super(modId);
    }

    @Override
    public Block newBridge_Support(WoodType woodType) {
        return new Bridge_Support(Utils.copyPropertySafe(woodType.planks));
    }

    @Override
    public Block newBridge_Block_Rope(WoodType woodType) {
        return new Bridge_Block_Rope(Utils.copyPropertySafe(woodType.planks));
    }

    @Override
    public Block newLog_Bridge(WoodType woodType) {
        return new Log_Bridge(Utils.copyPropertySafe(woodType.planks));
    }

    @Override
    public Block newRail_Bridge(WoodType woodType) {
        return new Rail_Bridge(Utils.copyPropertySafe(woodType.planks).noOcclusion());
    }

    @Override
    public Block newBridge_Stairs(WoodType woodType) {
        return new Bridge_Stairs(Utils.copyPropertySafe(woodType.planks));
    }

}
