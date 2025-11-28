package net.mehvahdjukaar.every_compat.dynamicpack;

import com.google.common.base.Stopwatch;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.configs.ECConfigs;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.resources.SimpleTagBuilder;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynServerResourcesGenerator;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynamicDataPack;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    public void regenerateDynamicAssets(Consumer<ResourceGenTask> executor) {
        if (!ECConfigs.GENERATE_DYNAMIC_SERVER.get()) return;

        Stopwatch stopwatch = Stopwatch.createStarted();
        this.dynamicPack.setGenerateDebugResources(PlatHelper.isDev() || ECConfigs.DEBUG_RESOURCES.get());

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

        if (ECConfigs.GENERATE_BLOCKTYPE_TAGS.get()) {
            executor.accept((resourceManager, resourceSink) -> {
                for (var r : BlockSetAPI.getRegistries()) {
                    String typeName = r.typeName();
                    for (BlockType blockType : r.getValues()) {

                        ResourceLocation tagId = blockType.getId().withPrefix(typeName + "/");
                        SimpleTagBuilder itemTag = SimpleTagBuilder.of(tagId);
                        SimpleTagBuilder blockTag = SimpleTagBuilder.of(tagId);
                        boolean isItemAddedToTag = false;
                        boolean isBlockAddedToTag = false;
                        for (Map.Entry<String, Object> entrySet : blockType.getChildren()) {
                            String key = entrySet.getKey();
                            if (key.equals("diagonalfences:fence")) continue; //dumb. if these are tagged their mod crashes

                            Block block = blockType.getBlockOfThis(key);
                            if (block != null) {
                                isBlockAddedToTag = true;
                                blockTag.addEntry(block);
                            }
                            Item item = blockType.getItemOfThis(key);
                            if (item != null) {
                                isItemAddedToTag = true;
                                itemTag.addEntry(item);
                            }
                        }
                        if (isBlockAddedToTag) {
                            resourceSink.addTag(blockTag, Registries.BLOCK);
                        }
                        if (isItemAddedToTag) {
                            resourceSink.addTag(itemTag, Registries.ITEM);
                        }
                    }
                }
            });
        }
    }

}
