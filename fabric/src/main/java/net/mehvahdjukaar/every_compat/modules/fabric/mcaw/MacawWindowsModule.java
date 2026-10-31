package net.mehvahdjukaar.every_compat.modules.fabric.mcaw;

import net.kikoz.mcwwindows.objects.*;
import net.mehvahdjukaar.every_compat.modules.macaw.MacawWindowsModuleAbstract;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

//See MacawWindowsModuleAbstract's SUPPORTED VERSION
public class MacawWindowsModule extends MacawWindowsModuleAbstract {

    public MacawWindowsModule(String modId) {
        super(modId);
    }

    @Override
    public Block newConnectedWindow(WoodType woodType, BlockBehaviour.Properties p) {
        return new ConnectedWindow(p.mapColor(MapColor.WOOD).sound(SoundType.WOOD).strength(0.6F, 1.2F));
    }

    @Override
    public Block newWindowBarred(WoodType woodType, BlockBehaviour.Properties p) {
        return new WindowBarred(p.mapColor(MapColor.WOOD).sound(SoundType.WOOD).strength(0.6f, 1.2F));
    }

    @Override
    public Block newWindow(WoodType woodType, BlockBehaviour.Properties p) {
        return new Window(p.mapColor(MapColor.WOOD).sound(SoundType.WOOD).strength(0.6F, 1.2F));
    }

    @Override
    public Block newParapet(WoodType woodType, BlockBehaviour.Properties p) {
        return new Parapet(p.mapColor(MapColor.WOOD).sound(SoundType.WOOD).strength(0.2F, 1.0F));
    }

    @Override
    public Block newBlinds(WoodType woodType, BlockBehaviour.Properties p) {
        return new Blinds(p.mapColor(MapColor.WOOD).sound(SoundType.WOOD).strength(0.3F, 1.0F));
    }

    @Override
    public Block newShutter(WoodType woodType, BlockBehaviour.Properties p) {
        return new Shutter(p.mapColor(MapColor.WOOD).sound(SoundType.WOOD).strength(0.5F, 2.0F));
    }

    @Override
    public Block newCurtainRod(WoodType woodType, BlockBehaviour.Properties p) {
        return new CurtainRod(p.mapColor(MapColor.WOOD).sound(SoundType.WOOD).strength(0.3F, 0.7F));
    }

}
