package net.mehvahdjukaar.every_compat.modules.storagedrawers;

import com.jaquadro.minecraft.storagedrawers.block.BlockDrawers;
import net.mehvahdjukaar.candlelight.api.PlatformImpl;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashSet;
import java.util.Set;

public class ModDrawersModels {

    @PlatformImpl
    public static void registerDrawerModelDecoration(StorageDrawersModule module) {
        throw new AssertionError();
    }

    public static Set<BlockState> getDrawerStates(StorageDrawersModule module) {
        Set<BlockState> states = new HashSet<>();
        module.getDrawersOfType(BlockDrawers.class).forEach(b ->
                states.addAll(b.getStateDefinition().getPossibleStates()));
        return states;
    }
}
