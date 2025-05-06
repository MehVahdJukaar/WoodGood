package net.mehvahdjukaar.every_compat.dynamicpack;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.configs.ECConfigs;
import net.mehvahdjukaar.every_compat.misc.SpriteHelper;
import net.mehvahdjukaar.moonlight.api.events.AfterLanguageLoadEvent;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynClientResourcesGenerator;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynamicTexturePack;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.level.block.Block;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;


public class ClientDynamicResourcesHandler extends DynClientResourcesGenerator {

    private static ClientDynamicResourcesHandler INSTANCE;

    public static ClientDynamicResourcesHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ClientDynamicResourcesHandler();
        }
        return INSTANCE;
    }

    private boolean firstInit = false;

    public ClientDynamicResourcesHandler() {
        super(new DynamicTexturePack(EveryCompat.res("generated_pack")));
        //since we place chests textures in its namespace to use its renderer
        if (PlatHelper.isModLoaded("quark")) getPack().addNamespaces("quark");

        this.dynamicPack.setGenerateDebugResources(PlatHelper.isDev() || ECConfigs.DEBUG_RESOURCES.get());
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
    public void addDynamicTranslations(AfterLanguageLoadEvent lang) {
        EveryCompat.forAllModules(m -> {
            m.addTranslations(this, lang);
        });
    }

    @Override
    public void regenerateDynamicAssets(Consumer<ResourceGenTask> executor) {
        List<ResourceGenTask> tasks = new ArrayList<>();
        EveryCompat.forAllModules(m -> m.addDynamicClientResources(tasks::add));
        int maxBatches = tasks.size() / Runtime.getRuntime().availableProcessors();
        int batchSize =  Math.max(10, maxBatches);

//        EveryCompat.LOGGER.info("Dynamic resources generation tasks: {} in batches of: {}", tasks.size(), batchSize);
//        EveryCompat.LOGGER.info("Dynamic resources generation threads: {} ", tasks.size() / batchSize);

        //submit tasks in batches. to do so split that list in sizes of that batchSize then submit a task to the executor where that list is iterated and executed
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

//    private static final ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool();
//
//    @Override
//    protected @NotNull ExecutorService getExecutors() {
//        return EXECUTOR_SERVICE;
//    }

    @Override
    public void regenerateDynamicAssets(ResourceManager manager) {
        if (!firstInit) {
            SpriteHelper.addHardcodedSprites();
            firstInit = true;
        }
//        Stopwatch stopwatch = Stopwatch.createStarted();
        this.dynamicPack.setGenerateDebugResources(PlatHelper.isDev() || ECConfigs.DEBUG_RESOURCES.get());
        super.regenerateDynamicAssets(manager);
//        EveryCompat.LOGGER.info("Dynamic assets generation took: " + stopwatch.stop().toString());
        this.paletteCache.clear();
    }

    //needs to be thread safe
    private final Map<BlockType, Palette> paletteCache = new ConcurrentHashMap<>();

    public Palette getCachedBaseBlockTexturePalette(ResourceManager manager, BlockType baseType) {
        return paletteCache.computeIfAbsent(baseType, k -> {
            try (TextureImage oakPlanksTexture = TextureImage.open(manager,
                    RPUtils.findFirstBlockTextureLocation(manager, (Block) baseType.mainChild()))) {
                return Palette.fromImage(oakPlanksTexture);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

    }

}
