package net.mehvahdjukaar.every_compat.fabric;

import net.fabricmc.api.ModInitializer;
import net.mehvahdjukaar.every_compat.EveryCompatCommon;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;

public class EveryCompatFabric extends EveryCompatCommon implements ModInitializer {

    @Override
    public void onInitialize() {
        this.initialize();

        if (PlatHelper.getPhysicalSide().isClient()) {
            EveryCompatFabricClient.init();

        }
    }

    @Override
    protected void addModules() {
        super.addModules();
    }
}
