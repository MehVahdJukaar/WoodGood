package net.mehvahdjukaar.every_compat.dynamicpack;

import com.google.common.base.Stopwatch;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.configs.ECConfigs;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynServerResourcesGenerator;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynamicDataPack;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.minecraft.server.packs.resources.ResourceManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ServerDynamicResourcesHandler extends DynServerResourcesGenerator {

    public static final ServerDynamicResourcesHandler INSTANCE = new ServerDynamicResourcesHandler();

    public ServerDynamicResourcesHandler() {
        super(new DynamicDataPack(EveryCompat.res("generated_pack")));

        /// Ensure the tags to be loaded first time into the world, not second time
        getPack().addNamespaces("minecraft");
        getPack().addNamespaces("forge");
        getPack().addNamespaces(EveryCompat.MOD_ID);

        if (PlatHelper.isModLoaded("lolmcv")) {
            getPack().addNamespaces("lieonstudio");
        }
    }

    @Override
    public Logger getLogger() {
        return EveryCompat.LOGGER;
    }

    @Override
    public boolean dependsOnLoadedPacks() {
        return ECConfigs.SPEC == null || ECConfigs.DEPEND_ON_PACKS.get();
    }

    @Override
    public void regenerateDynamicAssets(Consumer<ResourceGenTask> executor) {
        List<ResourceGenTask> tasks = new ArrayList<>();
        EveryCompat.forAllModules(m -> m.addDynamicServerResources(tasks::add));

        int batchSize = Math.max(10, tasks.size() / (Runtime.getRuntime().availableProcessors()));
        //submit tasks in batches. to do so split that list in sizes of that batchSize then submit a task to the executor where that list is iterated and executed
        EveryCompat.LOGGER.info("Dynamic server resources generation tasks: {} in batches of: {}", tasks.size(), batchSize);
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
    public void regenerateDynamicAssets(ResourceManager manager) {
        if (!ECConfigs.GENERATE_DYNAMIC_SERVER.get())return;

        Stopwatch stopwatch = Stopwatch.createStarted();
        this.dynamicPack.setGenerateDebugResources(false); //PlatHelper.isDev() || ECConfigs.DEBUG_RESOURCES.get()
        super.regenerateDynamicAssets(manager);
        EveryCompat.forAllModules(m -> m.addDynamicServerResourcesLast(manager, dynamicPack));

        EveryCompat.LOGGER.info("Dynamic server assets generation took: {}", stopwatch.stop().toString());
    }


    /// Will be added to DynamicPack if the mod is loaded
    public void addModToDynamicPack(String modId) {
        if (PlatHelper.isModLoaded(modId)) {
            getPack().addNamespaces(modId);
        }
    }

}
