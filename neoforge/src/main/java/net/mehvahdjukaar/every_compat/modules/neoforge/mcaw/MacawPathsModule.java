package net.mehvahdjukaar.every_compat.modules.neoforge.mcaw;

import com.mcwpaths.kikoz.objects.FacingPathBlock;
import net.mehvahdjukaar.every_compat.modules.macaw.MacawPathsModuleAbstract;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.world.level.block.Block;

//See MacawPathModuleAbstract's SUPPORTED VERSION
public class MacawPathsModule extends MacawPathsModuleAbstract {

    public MacawPathsModule(String modId) {
        super(modId);
    }

    @Override
    public Block newFacingPathBlock(WoodType woodType) {
        return new FacingPathBlock(Utils.copyPropertySafe(woodType.planks));
    }
}
