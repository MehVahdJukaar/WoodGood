package net.mehvahdjukaar.every_compat.dynamicpack;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.PaletteStrategies;
import net.mehvahdjukaar.every_compat.configs.ECConfigs;
import net.mehvahdjukaar.every_compat.misc.CompatSpritesHelper;
import net.mehvahdjukaar.moonlight.api.events.AfterLanguageLoadEvent;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynamicClientResourceProvider;
import net.mehvahdjukaar.moonlight.api.resources.pack.PackGenerationStrategy;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;


public class ClientDynamicResourcesHandler extends DynamicClientResourceProvider {

    private static ClientDynamicResourcesHandler INSTANCE;

    public static ClientDynamicResourcesHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ClientDynamicResourcesHandler();
        }
        return INSTANCE;
    }

    private boolean firstInit = false;

    public ClientDynamicResourcesHandler() {
        super(EveryCompat.res("dynamic_resources"), PackGenerationStrategy.CACHED);
    }

    @Override
    public void addDynamicTranslations(AfterLanguageLoadEvent lang) {
        EveryCompat.forAllModules(m -> m.addTranslations(this, lang));
    }

    @Override
    protected Collection<String> gatherSupportedNamespaces() {
        return List.of(
                "minecraft",
                "quark" //REASON: since we place chests textures in its namespace to use its renderer
        );
    }

    @Override
    public void regenerateDynamicAssets(Consumer<ResourceGenTask> executor) {
        if (!firstInit) {
            CompatSpritesHelper.addHardcodedSprites();
            firstInit = true;
        }
        if (!ECConfigs.GENERATE_DYNAMIC_CLIENT.get()) return;

        PaletteStrategies.clearCache();

        List<ResourceGenTask> tasks = new ArrayList<>();
        EveryCompat.forAllModules(m -> m.addDynamicClientResources(tasks::add));

        int minBatches = Runtime.getRuntime().availableProcessors();
        int maxBatches = tasks.size() / Runtime.getRuntime().availableProcessors();
        int batchSize = Math.max(minBatches, maxBatches);

        //submit tasks in batches. to do so split that list in sizes of that batchSize then submit a task to the executor where that list is iterated and executed
        EveryCompat.LOGGER.info("Starting dynamic resources generation tasks: {} in batches of {}", tasks.size(), batchSize);
        for (int i = 0; i < tasks.size(); i += batchSize) {
            int end = Math.min(i + batchSize, tasks.size());
            var subList = tasks.subList(i, end);
            executor.accept((resourceManager, resourceSink) -> {
                for (ResourceGenTask subtask : subList) {
                    subtask.accept(resourceManager, resourceSink);
                }
            });
        }
    }
}
