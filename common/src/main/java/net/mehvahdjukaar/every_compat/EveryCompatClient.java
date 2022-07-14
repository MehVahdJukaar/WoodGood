package net.mehvahdjukaar.every_compat;

import net.mehvahdjukaar.every_compat.api.CompatModule;
import net.mehvahdjukaar.moonlight.api.platform.ClientPlatformHelper;


public class EveryCompatClient {

    public static void commonInit() {
        ClientPlatformHelper.addBlockEntityRenderersRegistration(EveryCompatClient::registerBlockEntityRenderers);
        ClientPlatformHelper.addBlockColorsRegistration(EveryCompatClient::registerBlockColors);
    }

    private static void registerBlockColors(ClientPlatformHelper.BlockColorEvent event) {
        EveryCompat.forAllModules(m -> m.registerColors(event));
    }

    private static void registerBlockEntityRenderers(ClientPlatformHelper.BlockEntityRendererEvent event) {
        EveryCompat.forAllModules(m -> m.registerEntityRenderers(event));
    }

    public static void commonSetup() {
        EveryCompat.forAllModules(CompatModule::onClientSetup);
    }

}
