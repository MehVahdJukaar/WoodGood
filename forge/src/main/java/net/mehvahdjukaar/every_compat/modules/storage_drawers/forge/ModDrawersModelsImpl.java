package net.mehvahdjukaar.every_compat.modules.storage_drawers.forge;

import net.mehvahdjukaar.every_compat.forge.EveryCompatForge;
import net.mehvahdjukaar.every_compat.modules.storage_drawers.ModDrawersModels;
import net.mehvahdjukaar.every_compat.modules.storage_drawers.StorageDrawersModule;
import net.minecraftforge.client.event.ModelEvent;

public class ModDrawersModelsImpl {

    public static void registerDrawerModelDecoration(StorageDrawersModule module) {
        EveryCompatForge.getModEventBus().addListener((ModelEvent.ModifyBakingResult event) -> {
            for (var loc : ModDrawersModels.getDrawerModelLocations(module)) {
                event.getModels().computeIfPresent(loc, (l, model) -> ModDrawersModels.makeStandardDrawerModel(model));
            }
        });
    }
}
