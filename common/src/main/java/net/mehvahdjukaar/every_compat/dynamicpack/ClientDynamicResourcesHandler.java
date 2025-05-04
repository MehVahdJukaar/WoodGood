package net.mehvahdjukaar.every_compat.dynamicpack;

import com.google.common.base.Stopwatch;
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
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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

        this.dynamicPack.setGenerateDebugResources(false);
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
        boolean singleThread = false;
        if(singleThread) {
            List<ResourceGenTask> tasks = new ArrayList<>();
            var fakeExec = new Consumer<ResourceGenTask>() {
                @Override
                public void accept(ResourceGenTask resourceGenTask) {
                    tasks.add(resourceGenTask);
                }
            };
            EveryCompat.forAllModules(m -> m.addDynamicClientResources(fakeExec));

            //submit 2 tasks that wait each 1 sec
            executor.accept((manager, sink) -> {
                for (ResourceGenTask task : tasks) {
                    task.accept(manager, sink);
                }
            });
        }else{
            EveryCompat.forAllModules(m -> m.addDynamicClientResources(executor));
        }
    }

    private static final ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool();

    @Override
    protected @NotNull ExecutorService getExecutors() {
        return EXECUTOR_SERVICE;
    }

    @Override
    public void regenerateDynamicAssets(ResourceManager manager) {
        if (!firstInit) {
            SpriteHelper.addHardcodedSprites();
            firstInit = true;
        }
        Stopwatch stopwatch = Stopwatch.createStarted();
        this.dynamicPack.setGenerateDebugResources(false); //PlatHelper.isDev() || ECConfigs.DEBUG_RESOURCES.get()
        super.regenerateDynamicAssets(manager);
        EveryCompat.LOGGER.info("Dynamic assets generation took: " + stopwatch.stop().toString());
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
