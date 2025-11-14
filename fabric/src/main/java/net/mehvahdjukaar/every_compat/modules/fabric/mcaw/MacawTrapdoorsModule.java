package net.mehvahdjukaar.every_compat.modules.fabric.mcaw;

import net.mehvahdjukaar.every_compat.modules.macaw.MacawTrapdoorsModuleAbstract;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TrapDoorBlock;


//SUPPORT: v1.1.2+
public class MacawTrapdoorsModule extends MacawTrapdoorsModuleAbstract {

    public MacawTrapdoorsModule(String modId) {
        super(modId);
    }

    protected Block newTrapDoorBlock(WoodType woodType, Block block) {
        return new TrapDoorBlock(Utils.copyPropertySafe(block).noOcclusion(), woodType.toVanillaOrOak().setType()){};
    }
}
