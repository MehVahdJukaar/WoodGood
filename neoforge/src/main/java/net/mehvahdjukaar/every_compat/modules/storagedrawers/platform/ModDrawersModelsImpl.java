package net.mehvahdjukaar.every_compat.modules.storagedrawers.platform;

import com.jaquadro.minecraft.storagedrawers.client.model.BakedModelProvider;
import com.jaquadro.minecraft.storagedrawers.client.model.DrawerModelStore;
import com.jaquadro.minecraft.storagedrawers.client.model.ItemModelStore;
import com.jaquadro.minecraft.storagedrawers.client.model.ParentModel;
import net.mehvahdjukaar.every_compat.modules.storagedrawers.ModDrawersModels;
import net.mehvahdjukaar.every_compat.modules.storagedrawers.StorageDrawersModule;
import net.mehvahdjukaar.every_compat.neoforge.EveryCompatForge;
import net.minecraft.client.renderer.block.dispatch.BlockStateModel;
import net.neoforged.neoforge.client.event.ModelEvent;

public class ModDrawersModelsImpl {

    public static void registerDrawerModelDecoration(StorageDrawersModule module) {
        EveryCompatForge.getModEventBus().addListener((ModelEvent.ModifyBakingResult event) -> {
            var models = event.getBakingResult().blockStateModels();
            var drawerStates = ModDrawersModels.getDrawerStates(module);
            DrawerModelStore.INSTANCE.targetBlocks.addAll(drawerStates);
            for (var state : drawerStates) {
                BlockStateModel parent = models.get(state);
                if (parent == null || parent instanceof ParentModel) continue;

                BlockStateModel model = BakedModelProvider.makeStandardDrawerModel(parent);
                ItemModelStore.models.put(state, model);
                models.put(state, model);
            }
        });
    }
}
