package net.mehvahdjukaar.every_compat.api;


import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.misc.TaskRunnerWithFailureCollection;
import net.mehvahdjukaar.moonlight.api.events.AfterLanguageLoadEvent;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;

import java.util.function.Consumer;

public class SimpleModuleClient<M extends SimpleModule> extends CompatModuleClient<M> {
    public SimpleModuleClient(M module) {
        super(module);
    }

    @Override
    public void addDynamicClientResources(Consumer<ResourceGenTask> executor) {
        for (var entry : module.getEntries()) {
            executor.accept((manager, sink) -> {
                try {
                    entry.generateTextures(module, manager, sink);

                    entry.generateModels(module, manager, sink);

                } catch (Exception ex) {
                    TaskRunnerWithFailureCollection.active().record("client entry set", () -> String.valueOf(entry), ex);
                    if (PlatHelper.isDev()) {
                        throw ex;
                    }
                }
            });
        }
    }

    @Override
    public void addTranslations(ClientDynamicResourcesHandler clientDynamicResourcesHandler, AfterLanguageLoadEvent lang) {
        module.getEntries().forEach(e -> e.addTranslations(module, lang));
    }

    @Override
    public void registerBlockColors(ClientHelper.BlockColorEvent event) {
        module.getEntries().forEach(e -> e.registerBlockColors(event));
    }

}
