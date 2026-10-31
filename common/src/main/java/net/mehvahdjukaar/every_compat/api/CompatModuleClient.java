package net.mehvahdjukaar.every_compat.api;

import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.events.AfterLanguageLoadEvent;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;

import java.util.function.Consumer;

public class CompatModuleClient<M extends CompatModule> {

    protected final M module;

    public CompatModuleClient(M module) {
        this.module = module;
    }

    public M getModule() {
        return this.module;
    }

    public void onClientInit() {
    }

    public void onClientSetup() {
    }

    public void addDynamicClientResources(Consumer<ResourceGenTask> executor) {
    }

    public void addTranslations(ClientDynamicResourcesHandler clientDynamicResourcesHandler, AfterLanguageLoadEvent lang) {
    }

    public void registerBlockEntityRenderers(ClientHelper.BlockEntityRendererEvent event) {
    }

    public void registerBlockColors(ClientHelper.BlockColorEvent event) {
    }
}
