package net.mehvahdjukaar.every_compat.misc;

import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;

public class extendedWoodType {

    public static net.minecraft.world.level.block.state.properties.WoodType toVanillaOrOak(WoodType woodType) {
        var v = woodType.toVanilla();
        if (v != null) return v;
        return net.minecraft.world.level.block.state.properties.WoodType.OAK;
    }
}
