package net.mehvahdjukaar.every_compat.modules.fabric.mcaw;

import net.kikoz.mcwroofs.objects.roofs.*;
import net.mehvahdjukaar.every_compat.modules.macaw.MacawRoofsModuleAbstract;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

//See MacawRoofsModuleAbstract's SUPPORTED VERSION
public class MacawRoofsModule extends MacawRoofsModuleAbstract {

    public MacawRoofsModule(String modId) {
        super(modId);
    }

    @Override
    public Block newRoofGlass(WoodType woodType, BlockBehaviour.Properties p) {
        return new RoofGlass(p);
    }

    @Override
    public Block newBaseRoof(WoodType woodType, BlockBehaviour.Properties p) {
        return new BaseRoof(woodType.planks.defaultBlockState(), p);
    }

    @Override
    public Block newSteepRoof(WoodType woodType, BlockBehaviour.Properties p) {
        return new SteepRoof(woodType.planks.defaultBlockState(), p);
    }

    @Override
    public Block newRoofTopNew(WoodType woodType, BlockBehaviour.Properties p) {
        return new RoofTopNew(p);
    }

    @Override
    public Block newLower(WoodType woodType, BlockBehaviour.Properties p) {
        return new Lower(woodType.planks.defaultBlockState(), p);
    }

    @Override
    public Block newSteep(WoodType woodType, BlockBehaviour.Properties p) {
        return new Steep(woodType.planks.defaultBlockState(), p);
    }
}
