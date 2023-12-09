package net.mehvahdjukaar.every_compat;

import net.mehvahdjukaar.every_compat.api.CompatModule;
import net.mehvahdjukaar.moonlight.api.platform.ClientPlatformHelper;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.world.inventory.InventoryMenu;


public class EveryCompatClient {

    public static void commonInit() {
        EveryCompat.forAllModules(CompatModule::onClientInit);
        ClientPlatformHelper.addBlockEntityRenderersRegistration(EveryCompatClient::registerBlockEntityRenderers);
        ClientPlatformHelper.addBlockColorsRegistration(EveryCompatClient::registerBlockColors);
        ClientPlatformHelper.addItemColorsRegistration(EveryCompatClient::registerItemColors);
        ClientPlatformHelper.addAtlasTextureCallback(TextureAtlas.LOCATION_BLOCKS, EveryCompatClient::registerTextures);
    }

    private static void registerTextures(ClientPlatformHelper.AtlasTextureEvent event){
        EveryCompat.forAllModules(m -> m.stitchAtlasTextures(event));
    }

    private static void registerBlockColors(ClientPlatformHelper.BlockColorEvent event) {
        EveryCompat.forAllModules(m -> m.registerBlockColors(event));
    }

    private static void registerItemColors(ClientPlatformHelper.ItemColorEvent event) {
        EveryCompat.forAllModules(m -> m.registerItemColors(event));
    }

    private static void registerBlockEntityRenderers(ClientPlatformHelper.BlockEntityRendererEvent event) {
        EveryCompat.forAllModules(m -> m.registerBlockEntityRenderers(event));
    }

    public static void clientSetup() {
        EveryCompat.forAllModules(CompatModule::onClientSetup);
    }

}
