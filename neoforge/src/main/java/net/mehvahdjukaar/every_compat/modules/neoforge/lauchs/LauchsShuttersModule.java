package net.mehvahdjukaar.every_compat.modules.neoforge.lauchs;

import net.mehvahdjukaar.every_compat.modules.lauchs.LauchsShuttersModuleAbstract;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.world.level.block.Block;
import net.stehschnitzel.shutter.common.blocks.Shutter;

import java.util.List;

//See LauchsShuttersModuleAbstract's SUPPORTED VERSION
public class LauchsShuttersModule extends LauchsShuttersModuleAbstract {

    public LauchsShuttersModule(String modId) {
        super(modId);
    }

    @Override
    public Block newShutter(WoodType woodType) {
        return new Shutter(Utils.copyPropertySafe(woodType.planks));
    }

    @Override
    public List<String> getAlreadySupportedMods() {
        return List.of(
                "autumnity",
                "beachparty",
                "caverns_and_chasms",
                "ecologics",
                "environments",
                "goodending",
                "natures_spirit",
                "quark",
                "snowyspirit",
                "twigs",
                "upgrade_aquatic"
        );
    }
}
