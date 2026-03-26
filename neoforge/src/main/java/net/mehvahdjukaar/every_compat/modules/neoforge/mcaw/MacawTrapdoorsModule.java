package net.mehvahdjukaar.every_compat.modules.neoforge.mcaw;

import net.mehvahdjukaar.every_compat.modules.macaw.MacawTrapdoorsModuleAbstract;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TrapDoorBlock;

//See MacawTrapdoorsModuleAbstract's SUPPORTED VERSION
public class MacawTrapdoorsModule extends MacawTrapdoorsModuleAbstract {

    public MacawTrapdoorsModule(String modId) {
        super(modId);
    }

    protected Block newTrapDoorBlock(WoodType woodType, Block block) {
        return new TrapDoorBlock(woodType.toVanillaOrOak().setType(), Utils.copyPropertySafe(block).noOcclusion()) {};
    }
}
