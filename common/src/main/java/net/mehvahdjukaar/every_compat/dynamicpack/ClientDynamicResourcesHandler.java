package net.mehvahdjukaar.every_compat.dynamicpack;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.PaletteStrategies;
import net.mehvahdjukaar.every_compat.configs.ECConfigs;
import net.mehvahdjukaar.every_compat.misc.CompatSpritesHelper;
import net.mehvahdjukaar.every_compat.misc.TaskRunnerWithFaliureCollection;
import net.mehvahdjukaar.moonlight.api.events.AfterLanguageLoadEvent;
import net.mehvahdjukaar.moonlight.api.misc.IProgressTracker;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynamicClientResourceProvider;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.minecraft.server.packs.resources.ResourceManager;

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
        super(EveryCompat.res("dynamic_resources"),
                ECConfigs.CLIENT_GENERATION_MODE.get().pickStrategy());
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
    public void reload(ResourceManager manager, IProgressTracker reporter) {
        if (!firstInit) {
            CompatSpritesHelper.addHardcodedSprites();
            firstInit = true;
        }
        super.reload(manager, reporter);
    }

    @Override
    public boolean canUseExternalResourcePacks() {
        return ECConfigs.SPEC != null && ECConfigs.USE_EXTERNAL_RESOURCE_PACK.get();
    }

    @Override
    public void regenerateDynamicAssets(Consumer<ResourceGenTask> executor) {
        TaskRunnerWithFaliureCollection.run("Client dynamic resources generation", () -> {
            PaletteStrategies.clearCache();

            List<ResourceGenTask> tasks = new ArrayList<>();
            EveryCompat.forAllModules(m -> m.addDynamicClientResources(tasks::add));

            int minBatches = Runtime.getRuntime().availableProcessors();
            int maxBatches = tasks.size() / Runtime.getRuntime().availableProcessors();
            int batchSize = Math.max(minBatches, maxBatches);

            //submit tasks in batches. to do so split that list in sizes of that batchSize then submit a task to the executor where that list is iterated and executed
            EveryCompat.LOGGER.info("Starting dynamic resources generation tasks: {} in batches of {}", tasks.size(), batchSize);
            TaskRunnerWithFaliureCollection failures = TaskRunnerWithFaliureCollection.active();
            for (int i = 0; i < tasks.size(); i += batchSize) {
                int batchStart = i;
                int end = Math.min(i + batchSize, tasks.size());
                var subList = tasks.subList(i, end);
                executor.accept((resourceManager, resourceSink) -> {
                    for (int j = 0; j < subList.size(); j++) {
                        ResourceGenTask subtask = subList.get(j);
                        int taskIndex = batchStart + j;
                        failures.runSafely("client resource task", () -> "task #" + taskIndex,
                                () -> subtask.accept(resourceManager, resourceSink));
                    }
                });
            }
        });
    }
}
