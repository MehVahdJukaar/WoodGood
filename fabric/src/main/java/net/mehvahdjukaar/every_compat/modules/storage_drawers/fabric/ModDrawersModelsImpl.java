package net.mehvahdjukaar.every_compat.modules.storage_drawers.fabric;

import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.mehvahdjukaar.every_compat.modules.storage_drawers.ModDrawersModels;
import net.mehvahdjukaar.every_compat.modules.storage_drawers.StorageDrawersModule;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;

import java.util.Set;

public class ModDrawersModelsImpl {

    public static void registerDrawerModelDecoration(StorageDrawersModule module) {
        ModelLoadingPlugin.register(pluginContext -> {
            Set<ModelResourceLocation> drawerModels = ModDrawersModels.getDrawerModelLocations(module);
            pluginContext.modifyModelAfterBake().register((model, context) -> {
                ResourceLocation blockid = context.id();
                if (blockid != null && drawerModels.contains(blockid)) return ModDrawersModels.makeStandardDrawerModel(model);
                return model;
            });
        });
    }
}
