package net.mehvahdjukaar.every_compat.modules.neoforge.mcaw;

import com.mcwroofs.kikoz.objects.roofs.*;
import net.mehvahdjukaar.every_compat.modules.macaw.MacawRoofsModuleAbstract;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.world.level.block.Block;

//See MacawRoofsModuleAbstract's SUPPORTED VERSION
public class MacawRoofsModule extends MacawRoofsModuleAbstract {

    public MacawRoofsModule(String modId) {
        super(modId);
    }

    @Override
    public Block newRoofGlass(WoodType woodType) {
        return new RoofGlass(copyStandardProperties(woodType));
    }

    @Override
    public Block newBaseRoof(WoodType woodType) {
        return new BaseRoof(woodType.planks.defaultBlockState(), copyStandardProperties(woodType));
    }

    @Override
    public Block newSteepRoof(WoodType woodType) {
        return new SteepRoof(woodType.planks.defaultBlockState(), copyStandardProperties(woodType));
    }

    @Override
    public Block newRoofTopNew(WoodType woodType) {
        return new RoofTopNew(copyStandardProperties(woodType));
    }

    @Override
    public Block newLower(WoodType woodType) {
        return new Lower(woodType.planks.defaultBlockState(), copyStandardProperties(woodType));
    }

    @Override
    public Block newSteep(WoodType woodType) {
        return new Steep(woodType.planks.defaultBlockState(), copyStandardProperties(woodType));
    }
}
