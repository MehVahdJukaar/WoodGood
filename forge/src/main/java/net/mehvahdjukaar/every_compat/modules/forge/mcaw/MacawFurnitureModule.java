package net.mehvahdjukaar.every_compat.modules.forge.mcaw;

import com.mcwfurnitures.kikoz.objects.*;
import com.mcwfurnitures.kikoz.objects.cabinets.Cabinet;
import com.mcwfurnitures.kikoz.objects.cabinets.CabinetHinge;
import com.mcwfurnitures.kikoz.objects.chairs.ModernChair;
import com.mcwfurnitures.kikoz.objects.chairs.StripedChair;
import com.mcwfurnitures.kikoz.objects.counters.Counter;
import com.mcwfurnitures.kikoz.objects.counters.CupboardCounter;
import com.mcwfurnitures.kikoz.objects.counters.StorageCounter;
import net.mehvahdjukaar.every_compat.modules.macaw.MacawFurnitureAbstractModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.world.level.block.Block;

//See MacawFurnitureAbstractModule's SUPPORTED VERSION
public class MacawFurnitureModule extends MacawFurnitureAbstractModule {

    public MacawFurnitureModule(String modId) {
        super(modId);
    }

    @Override
    public Block newCabinet(WoodType woodType) {
        return new Cabinet(woodType.planks.defaultBlockState(), copyStandardProperties());
    }

    @Override
    public Block newCabinetHinge(WoodType woodType) {
        return new CabinetHinge(copyStandardProperties());
    }

    @Override
    public Block newWideFurniture(WoodType woodType) {
        return new WideFurniture(copyStandardProperties());
    }

    @Override
    public Block newTallFurniture(WoodType woodType) {
        return new TallFurniture(copyStandardProperties());
    }

    @Override
    public Block newTallFurnitureHinge(WoodType woodType) {
        return new TallFurnitureHinge(copyStandardProperties());
    }

    @Override
    public Block newDesk(WoodType woodType) {
        return new Desk(copyStandardProperties());
    }

    @Override
    public Block newTable(WoodType woodType) {
        return new Table(copyStandardProperties());
    }

    @Override
    public Block newTableHitbox(WoodType woodType) {
        return new TableHitbox(copyStandardProperties());
    }

    @Override
    public Block newStripedChair(WoodType woodType) {
        return new StripedChair(copyStandardProperties());
    }

    @Override
    public Block newModernChair(WoodType woodType) {
        return new ModernChair(copyStandardProperties());
    }

    @Override
    public Block newChair(WoodType woodType) {
        return new Chair(copyStandardProperties());
    }

    @Override
    public Block newCounter(WoodType woodType) {
        return new Counter(copyStandardProperties());
    }

    @Override
    public Block newStorageCounter(WoodType woodType) {
        return new StorageCounter(woodType.planks.defaultBlockState(), copyStandardProperties());
    }

    @Override
    public Block newCupboardCounter(WoodType woodType) {
        return new CupboardCounter(copyStandardProperties());
    }
}

