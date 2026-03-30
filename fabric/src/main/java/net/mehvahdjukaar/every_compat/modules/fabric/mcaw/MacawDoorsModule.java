package net.mehvahdjukaar.every_compat.modules.fabric.mcaw;

import net.kikoz.mcwdoors.objects.JapaneseDoors;
import net.kikoz.mcwdoors.objects.StableDoor;
import net.mehvahdjukaar.every_compat.modules.macaw.MacawDoorsModuleAbstract;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;

//See MacawDoorModuleAbstract's SUPPORTED VERSION
public class MacawDoorsModule extends MacawDoorsModuleAbstract {

    public MacawDoorsModule(String modId) {
        super(modId);
    }

    protected Block newDoor(String blockId, WoodType woodType) {
        return new DoorBlock(woodType.toVanillaOrOak().setType(), standardPropertiesSafe(blockId, woodType)) {};
    }

    protected Block newJapaneseDoors(String blockId, WoodType woodType) {
        return new JapaneseDoors(standardPropertiesSafe(blockId, woodType),
                woodType.toVanillaOrOak().setType());
    }

    protected Block newStableDoor(String blockId, WoodType woodType) {
        return new StableDoor(standardPropertiesSafe(blockId, woodType), woodType.toVanillaOrOak().setType());
    }

}
