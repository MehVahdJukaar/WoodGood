package net.mehvahdjukaar.every_compat.dynamicpack;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.configs.ECConfigs;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynamicServerResourceProvider;
import net.mehvahdjukaar.moonlight.api.resources.pack.PackGenerationStrategy;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.minecraft.server.packs.repository.PackRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class ServerDynamicResourcesHandler extends DynamicServerResourceProvider {

    private Collection<String> dynamicDataList;
    public static ServerDynamicResourcesHandler INSTANCE;

    public static ServerDynamicResourcesHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ServerDynamicResourcesHandler();
        }
        return INSTANCE;
    }

    public ServerDynamicResourcesHandler() {
        super(EveryCompat.res("dynamic_resources"), PackGenerationStrategy.CACHED);
    }

    @Override
    protected Collection<String> gatherSupportedNamespaces() {
        dynamicDataList = new ArrayList<>();
        dynamicDataList.add("minecraft");
        dynamicDataList.add(EveryCompat.MOD_ID);
        /// Ensure the tags to be loaded first time into the world, not second time
        addIfLoaded("lolmcv", "lieonstudio");

        return dynamicDataList;
    }

    public void addIfLoaded(String modId, String namespace) {
        if (PlatHelper.isModLoaded(modId)) dynamicDataList.add(namespace);
    }

    @Override
    public void regenerateDynamicAssets(Consumer<ResourceGenTask> executor) {
        if (!ECConfigs.GENERATE_DYNAMIC_SERVER.get()) return;

        List<ResourceGenTask> tasks = new ArrayList<>();
        EveryCompat.forAllModules(m -> m.addDynamicServerResources(tasks::add));

        int minBatches = Runtime.getRuntime().availableProcessors();
        int maxBatches = tasks.size() / Runtime.getRuntime().availableProcessors();
        int batchSize = Math.max(minBatches, maxBatches);

        //submit tasks in batches. to do so split that list in sizes of that batchSize then submit a task to the executor where that list is iterated and executed
        EveryCompat.LOGGER.info("Every Compat is starting dynamic server resources generation tasks: {} in batches of {}", tasks.size(), batchSize);
        for (int i = 0; i < tasks.size(); i += batchSize) {
            int end = Math.min(i + batchSize, tasks.size());
            var subList = tasks.subList(i, end);
            executor.accept((resourceManager, resourceSink) -> {
                for (ResourceGenTask task : subList) {
                    task.accept(resourceManager, resourceSink);
                }
            });
        }
    }

    @Override
    protected PackRepository getPackRepository() {
        return Objects.nonNull(PlatHelper.getCurrentServer()) ? PlatHelper.getCurrentServer().getPackRepository() : null;
    }

}
