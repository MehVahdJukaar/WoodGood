package net.mehvahdjukaar.every_compat.modules.fabric.lauchs;

import net.mehvahdjukaar.every_compat.modules.lauchs.LauchsShuttersModuleAbstract;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.world.level.block.Block;
import net.stehschnitzel.shutter.block.Shutter;

//SUPPORT: v2.0.2+
public class LauchsShuttersModule extends LauchsShuttersModuleAbstract {

    public LauchsShuttersModule(String modId) {
        super(modId);
    }

    @Override
    public Block newShutter(WoodType woodType) {
        return new Shutter(Utils.copyPropertySafe(woodType.planks));
    }
}
