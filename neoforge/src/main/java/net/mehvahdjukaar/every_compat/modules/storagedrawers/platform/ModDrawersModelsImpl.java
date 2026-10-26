package net.mehvahdjukaar.every_compat.modules.storagedrawers.platform;

import net.mehvahdjukaar.every_compat.modules.storagedrawers.ModDrawersModels;
import net.mehvahdjukaar.every_compat.modules.storagedrawers.StorageDrawersModule;
import net.mehvahdjukaar.every_compat.neoforge.EveryCompatForge;
import net.neoforged.neoforge.client.event.ModelEvent;

public class ModDrawersModelsImpl {

    public static void registerDrawerModelDecoration(StorageDrawersModule module) {
        EveryCompatForge.getModEventBus().addListener((ModelEvent.ModifyBakingResult event) -> {
            for (var loc : ModDrawersModels.getDrawerModelLocations(module)) {
                event.getModels().computeIfPresent(loc, (l, model) -> ModDrawersModels.makeStandardDrawerModel(model));
            }
        });
    }
}
