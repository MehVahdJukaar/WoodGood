package net.mehvahdjukaar.every_compat.modules.forge.mcaw;

import com.mcwdoors.kikoz.objects.JapaneseDoors;
import com.mcwdoors.kikoz.objects.StableDoor;
import net.mehvahdjukaar.every_compat.modules.macaw.MacawDoorsModuleAbstract;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.SoundType;

//See MacawDoorModuleAbstract's SUPPORTED VERSION
public class MacawDoorsModule extends MacawDoorsModuleAbstract {

    public MacawDoorsModule(String modId) {
        super(modId);
    }

    protected Block newDoor(WoodType woodType) {
        return new DoorBlock(Utils.copyPropertySafe(woodType.log).noOcclusion(), woodType.toVanillaOrOak().setType()){};
    }

    protected Block newJapaneseDoors(WoodType woodType) {
        return new JapaneseDoors(Utils.copyPropertySafe(woodType.planks).noOcclusion().sound(SoundType.SCAFFOLDING),
                woodType.toVanillaOrOak().setType());
    }

    protected Block newStableDoor(WoodType woodType) {
        return new StableDoor(Utils.copyPropertySafe(woodType.planks).noOcclusion(), woodType.toVanillaOrOak().setType());
    }

}
