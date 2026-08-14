package net.mehvahdjukaar.every_compat.modules.fabric.mcaw;

import net.kikoz.mcwwindows.MacawsWindows;
import net.kikoz.mcwwindows.objects.*;
import net.mehvahdjukaar.every_compat.modules.macaw.MacawWindowsModuleAbstract;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

//SUPPORT: v2.2.1+
public class MacawWindowsModule extends MacawWindowsModuleAbstract {

    public MacawWindowsModule(String modId) {
        super(modId);
        ResourceKey<CreativeModeTab> tab = MacawsWindows.WINDOWSGROUP;
    }

    @Override
    public Block newConnectedWindow(WoodType woodType) {
        return new ConnectedWindow(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).strength(0.6F, 1.2F));
    }

    @Override
    public Block newWindowBarred(WoodType woodType) {
        return new WindowBarred(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).strength(0.6F, 1.2F));
    }

    @Override
    public Block newWindow(WoodType woodType) {
        return new Window(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).strength(0.6F, 1.2F));
    }

    @Override
    public Block newParapet(WoodType woodType) {
        return new Parapet(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).strength(0.2F, 1.0F));
    }

    @Override
    public Block newBlinds(WoodType woodType) {
        return new Blinds(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).strength(0.3F, 1.0F));
    }

    @Override
    public Block newShutter(WoodType woodType) {
        return new Shutter(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).strength(0.5F, 2.0F));
    }

    @Override
    public Block newCurtainRod(WoodType woodType) {
        return new CurtainRod(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).strength(0.3F, 0.7F));
    }

}
