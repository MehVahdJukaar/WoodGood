package net.mehvahdjukaar.every_compat.modules.storagedrawers.platform;

import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.mehvahdjukaar.every_compat.modules.storagedrawers.ModDrawersModels;
import net.mehvahdjukaar.every_compat.modules.storagedrawers.StorageDrawersModule;
import net.minecraft.client.resources.model.ModelResourceLocation;

import java.util.Set;

public class ModDrawersModelsImpl {

    public static void registerDrawerModelDecoration(StorageDrawersModule module) {
        ModelLoadingPlugin.register(pluginContext -> {
            Set<ModelResourceLocation> drawerModels = ModDrawersModels.getDrawerModelLocations(module);
            pluginContext.modifyModelAfterBake().register((model, context) -> {
                ModelResourceLocation id = context.topLevelId();
                if (id != null && drawerModels.contains(id)) return ModDrawersModels.makeStandardDrawerModel(model);
                return model;
            });
        });
    }
}
