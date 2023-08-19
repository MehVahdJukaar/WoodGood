package net.mehvahdjukaar.every_compat;

import net.mehvahdjukaar.every_compat.api.CompatModule;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;


public class EveryCompatClient {

    public static void commonInit() {
        EveryCompat.forAllModules(CompatModule::onClientInit);
        ClientHelper.addBlockEntityRenderersRegistration(ClientHelper::registerBlockEntityRenderers);
        ClientHelper.addBlockColorsRegistration(ClientHelper::registerBlockColors);
        ClientHelper.addItemColorsRegistration(ClientHelper::registerItemColors);
    }

    private static void registerBlockColors(ClientHelper.BlockColorEvent event) {
        EveryCompat.forAllModules(m -> m.registerBlockColors(event));
    }

    private static void registerItemColors(ClientHelper.ItemColorEvent event) {
        EveryCompat.forAllModules(m -> m.registerItemColors(event));
    }

    private static void registerBlockEntityRenderers(ClientHelper.BlockEntityRendererEvent event) {
        EveryCompat.forAllModules(m -> m.registerBlockEntityRenderers(event));
    }

    private static void registerItemsToTabs(ClientHelper.ItemToTabsEvent event) {
        EveryCompat.forAllModules(m -> m.registerItemsToTabs(event));
    }

    public static void clientSetup() {
        EveryCompat.forAllModules(CompatModule::onClientSetup);
    }

}
