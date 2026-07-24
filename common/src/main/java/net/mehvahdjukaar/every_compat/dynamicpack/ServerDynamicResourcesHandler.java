package net.mehvahdjukaar.every_compat.dynamicpack;

import net.mehvahdjukaar.every_compat.ECPlatStuff;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.configs.ECConfigs;
import net.mehvahdjukaar.every_compat.misc.TaskRunnerWithFailureCollection;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.resources.SimpleTagBuilder;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynamicServerResourceProvider;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ServerDynamicResourcesHandler extends DynamicServerResourceProvider {

    private static ServerDynamicResourcesHandler INSTANCE;
    private static ArrayList<String> namespaces = new ArrayList<>();

    public static ServerDynamicResourcesHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ServerDynamicResourcesHandler();
        }
        return INSTANCE;
    }

    public ServerDynamicResourcesHandler() {
        super(EveryCompat.res("dynamic_resources"),
                ECConfigs.SERVER_GENERATION_MODE.get().pickStrategy());
    }

    //needs to be ready when constructor is called. dont call EC stuff from here
    @Override
    protected Collection<String> gatherSupportedNamespaces() {
        var namespaces = new ArrayList<String>();
        namespaces.add("minecraft");
        namespaces.add(EveryCompat.MOD_ID);
        namespaces.add("c");
        /// Ensure the tags to be loaded first time into the world, not second time
        if (PlatHelper.isModLoaded("lolmcv")) {
            namespaces.add("lieonstudio");
            namespaces.add("quad");
        }
        if (PlatHelper.isModLoaded("toughasnails")) namespaces.add("toughasnails");
        namespaces.add("blueprint"); // it doesn't show up in modlist, so isModLoaded() can't be used & too many dependents to count

        return namespaces;
    }

    @Override
    public boolean canUseExternalResourcePacks() {
        return ECConfigs.SPEC != null && ECConfigs.USE_EXTERNAL_RESOURCE_PACK.get();
    }

    @Override
    public void regenerateDynamicAssets(Consumer<ResourceGenTask> executor) {
        TaskRunnerWithFailureCollection.run("Server dynamic resources generation", () -> {
            List<ResourceGenTask> tasks = new ArrayList<>();
            EveryCompat.forAllModules(m -> m.addDynamicServerResources(tasks::add));
            ECPlatStuff.addPlatformServerResources(tasks::add);

            int minBatches = Runtime.getRuntime().availableProcessors();
            int maxBatches = tasks.size() / Runtime.getRuntime().availableProcessors();
            int batchSize = Math.max(minBatches, maxBatches);

            //submit tasks in batches. to do so split that list in sizes of that batchSize then submit a task to the executor where that list is iterated and executed
            EveryCompat.LOGGER.info("Every Compat is starting dynamic server resources generation tasks: {} in batches of {}", tasks.size(), batchSize);
            TaskRunnerWithFailureCollection failures = TaskRunnerWithFailureCollection.active();
            for (int i = 0; i < tasks.size(); i += batchSize) {
                int batchStart = i;
                int end = Math.min(i + batchSize, tasks.size());
                var subList = tasks.subList(i, end);
                executor.accept((resourceManager, resourceSink) -> {
                    for (int j = 0; j < subList.size(); j++) {
                        ResourceGenTask task = subList.get(j);
                        int taskIndex = batchStart + j;
                        failures.runSafely("server resource task", () -> "task #" + taskIndex,
                                () -> task.accept(resourceManager, resourceSink));
                    }
                });
            }

            if (ECConfigs.GENERATE_BLOCKTYPE_TAGS.get()) {
                executor.accept((resourceManager, resourceSink) -> {
                    for (var r : BlockSetAPI.getRegistries()) {
                        String typeName = r.typeName();
                        for (BlockType blockType : r.getValues()) {
                            failures.runSafely("block type tag", blockType.getId()::toString, () -> {
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
                            });
                        }
                    }
                });
            }
        });
    }

}
