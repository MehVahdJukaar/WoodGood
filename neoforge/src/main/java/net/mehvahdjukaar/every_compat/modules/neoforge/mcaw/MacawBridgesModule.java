package net.mehvahdjukaar.every_compat.modules.neoforge.mcaw;

import com.mcwbridges.kikoz.objects.*;
import net.mehvahdjukaar.every_compat.modules.macaw.MacawBridgesModuleAbstract;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

//See MacawBridgesModuleAbstract's SUPPORTED VERSION
public class MacawBridgesModule extends MacawBridgesModuleAbstract {

    public MacawBridgesModule(String modId) {
        super(modId);
    }

    @Override
    public Block newBridge_Support(WoodType woodType, BlockBehaviour.Properties p) {
        return new Bridge_Support(p);
    }

    @Override
    public Block newBridge_Block_Rope(WoodType woodType, BlockBehaviour.Properties p) {
        return new Bridge_Block_Rope(p);
    }

    @Override
    public Block newLog_Bridge(WoodType woodType, BlockBehaviour.Properties p) {
        return new Log_Bridge(p);
    }

    @Override
    public Block newRail_Bridge(WoodType woodType, BlockBehaviour.Properties p) {
        return new Rail_Bridge(p.noOcclusion());
    }

    @Override
    public Block newBridge_Stairs(WoodType woodType, BlockBehaviour.Properties p) {
        return new Bridge_Stairs(p);
    }

}
