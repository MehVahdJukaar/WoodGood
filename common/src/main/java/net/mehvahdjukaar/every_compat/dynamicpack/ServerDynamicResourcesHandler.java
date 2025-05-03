package net.mehvahdjukaar.every_compat.dynamicpack;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.configs.ECConfigs;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynServerResourcesGenerator;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynamicDataPack;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.minecraft.server.packs.resources.ResourceManager;
import org.apache.logging.log4j.Logger;

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
        return  ECConfigs.SPEC == null || ECConfigs.DEPEND_ON_PACKS.get();
    }

    @Override
    public void regenerateDynamicAssets(Consumer<ResourceGenTask> executor) {
        this.dynamicPack.setGenerateDebugResources(PlatHelper.isDev() || ECConfigs.DEBUG_RESOURCES.get());

        EveryCompat.forAllModules(m -> {
            try {
                m.addDynamicServerResources(executor);
            } catch (Exception e) {
                getLogger().error("Failed to generate server dynamic assets for module {}: {}", m, e);
            }
        });
    }

    /// Will be added to DynamicPack if the mod is loaded
    public void addModToDynamicPack(String modId) {
        if (PlatHelper.isModLoaded(modId)) {
            getPack().addNamespaces(modId);
        }
    }

}
