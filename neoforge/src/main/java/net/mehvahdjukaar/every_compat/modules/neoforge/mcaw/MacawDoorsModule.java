package net.mehvahdjukaar.every_compat.modules.neoforge.mcaw;

import com.mcwdoors.kikoz.objects.JapaneseDoors;
import com.mcwdoors.kikoz.objects.StableDoor;
import net.mehvahdjukaar.every_compat.modules.macaw.MacawDoorsModuleAbstract;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;

//See MacawDoorModuleAbstract's SUPPORTED VERSION
public class MacawDoorsModule extends MacawDoorsModuleAbstract {

    public MacawDoorsModule(String modId) {
        super(modId);
    }

    protected Block newDoor(WoodType woodType, BlockBehaviour.Properties p) {
        return new DoorBlock(woodType.toVanillaOrOak().setType(), p) {};
    }

    protected Block newJapaneseDoors(WoodType woodType, BlockBehaviour.Properties p) {
        return new JapaneseDoors(p,
                woodType.toVanillaOrOak().setType());
    }


    protected Block newStableDoor(WoodType woodType, BlockBehaviour.Properties p) {
        return new StableDoor(p, woodType.toVanillaOrOak().setType());
    }

}
