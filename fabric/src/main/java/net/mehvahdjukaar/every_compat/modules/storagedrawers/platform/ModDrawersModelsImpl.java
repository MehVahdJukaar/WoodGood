package net.mehvahdjukaar.every_compat.modules.storagedrawers.platform;

import com.jaquadro.minecraft.storagedrawers.client.model.DrawerModelStore;
import com.jaquadro.minecraft.storagedrawers.client.model.ModelLoadPlugin;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.mehvahdjukaar.every_compat.modules.storagedrawers.ModDrawersModels;
import net.mehvahdjukaar.every_compat.modules.storagedrawers.StorageDrawersModule;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Set;

public class ModDrawersModelsImpl {

    public static void registerDrawerModelDecoration(StorageDrawersModule module) {
        ModelLoadingPlugin.register(pluginContext -> {
            Set<BlockState> drawerStates = ModDrawersModels.getDrawerStates(module);

            DrawerModelStore.INSTANCE.targetBlocks.addAll(drawerStates);
            ModelLoadPlugin sdPlugin = new ModelLoadPlugin();
            pluginContext.modifyBlockModelOnLoad().register((original, context) -> {
                BlockState state = context.state();
                if (state != null && drawerStates.contains(state) && !(original instanceof ModelLoadPlugin.UnbakedProxyModel)) {
                    return sdPlugin.new UnbakedProxyModel(original, state);
                }
                return original;
            });
        });
    }
}
