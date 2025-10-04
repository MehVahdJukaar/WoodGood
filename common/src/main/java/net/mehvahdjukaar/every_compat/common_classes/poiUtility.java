package net.mehvahdjukaar.every_compat.common_classes;

import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.block.Block;

public class poiUtility {

    /// Must be called in onModInit(), not onModSetup()
    public static void simpleAddBlocksToPOI(SimpleEntrySet<WoodType, Block> entrySet, ResourceKey<PoiType> poiType) {
        RegHelper.addExtraPOIStatesRegistration(event ->
                entrySet.blocks.values().forEach(block -> event.addBlockToPoi(poiType, block))
        );
    }
}
