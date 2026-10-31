package net.mehvahdjukaar.every_compat.modules.fabric.mcaw;

import net.kikoz.mcwfurnitures.objects.*;
import net.kikoz.mcwfurnitures.objects.cabinets.Cabinet;
import net.kikoz.mcwfurnitures.objects.cabinets.CabinetHinge;
import net.kikoz.mcwfurnitures.objects.chairs.ModernChair;
import net.kikoz.mcwfurnitures.objects.chairs.StripedChair;
import net.kikoz.mcwfurnitures.objects.counters.Counter;
import net.kikoz.mcwfurnitures.objects.counters.CupboardCounter;
import net.kikoz.mcwfurnitures.objects.counters.SinkCounter;
import net.kikoz.mcwfurnitures.objects.counters.StorageCounter;
import net.mehvahdjukaar.every_compat.modules.macaw.MacawFurnitureModuleAbstract;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

//See MacawFurnitureAbstractModule's SUPPORTED VERSION
public class MacawFurnitureModule extends MacawFurnitureModuleAbstract {

    public MacawFurnitureModule(String modId) {
        super(modId);
    }

    @Override
    public Block newCabinet(WoodType woodType, BlockBehaviour.Properties p) {
        return new Cabinet(p);
    }

    @Override
    public Block newCabinetHinge(WoodType woodType, BlockBehaviour.Properties p) {
        return new CabinetHinge(p);
    }

    @Override
    public Block newWideFurniture(WoodType woodType, BlockBehaviour.Properties p) {
        return new WideFurniture(p);
    }

    @Override
    public Block newTallFurniture(WoodType woodType, BlockBehaviour.Properties p) {
        return new TallFurniture(p);
    }

    @Override
    public Block newTallFurnitureHinge(WoodType woodType, BlockBehaviour.Properties p) {
        return new TallFurnitureHinge(p);
    }

    @Override
    public Block newDesk(WoodType woodType, BlockBehaviour.Properties p) {
        return new Desk(p);
    }

    @Override
    public Block newTable(WoodType woodType, BlockBehaviour.Properties p) {
        return new Table(p);
    }

    @Override
    public Block newTableHitbox(WoodType woodType, BlockBehaviour.Properties p) {
        return new TableHitbox(p);
    }

    @Override
    public Block newStripedChair(WoodType woodType, BlockBehaviour.Properties p) {
        return new StripedChair(p);
    }

    @Override
    public Block newModernChair(WoodType woodType, BlockBehaviour.Properties p) {
        return new ModernChair(p);
    }

    @Override
    public Block newChair(WoodType woodType, BlockBehaviour.Properties p) {
        return new Chair(p);
    }

    @Override
    public Block newCounter(WoodType woodType, BlockBehaviour.Properties p) {
        return new Counter(p);
    }

    @Override
    public Block newStorageCounter(WoodType woodType, BlockBehaviour.Properties p) {
        return new StorageCounter(p);
    }

    @Override
    public Block newCupboardCounter(WoodType woodType, BlockBehaviour.Properties p) {
        return new CupboardCounter(p);
    }

    @Override
    public Block newSinkCounter(WoodType woodType, BlockBehaviour.Properties p) {
        return new SinkCounter(p);
    }
}
