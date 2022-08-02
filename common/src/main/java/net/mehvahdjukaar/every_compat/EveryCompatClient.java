package net.mehvahdjukaar.every_compat;

import net.mehvahdjukaar.every_compat.api.CompatModule;
import net.mehvahdjukaar.moonlight.api.platform.ClientPlatformHelper;


public class EveryCompatClient {

    public static void commonInit() {
        EveryCompat.forAllModules(CompatModule::onClientInit);
        ClientPlatformHelper.addBlockEntityRenderersRegistration(EveryCompatClient::registerBlockEntityRenderers);
        ClientPlatformHelper.addBlockColorsRegistration(EveryCompatClient::registerBlockColors);
        ClientPlatformHelper.addItemColorsRegistration(EveryCompatClient::registerItemColors);
    }

    private static void registerBlockColors(ClientPlatformHelper.BlockColorEvent event) {
        EveryCompat.forAllModules(m -> m.registerBlockColors(event));
    }

    private static void registerItemColors(ClientPlatformHelper.ItemColorEvent event) {
        EveryCompat.forAllModules(m -> m.registerItemColors(event));
    }

    private static void registerBlockEntityRenderers(ClientPlatformHelper.BlockEntityRendererEvent event) {
        EveryCompat.forAllModules(m -> m.registerEntityRenderers(event));
    }

    public static void commonSetup() {
        EveryCompat.forAllModules(CompatModule::onClientSetup);
    }

}
