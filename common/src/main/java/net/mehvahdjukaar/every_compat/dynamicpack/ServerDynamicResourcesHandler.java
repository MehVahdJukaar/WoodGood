package net.mehvahdjukaar.every_compat.dynamicpack;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.configs.EarlyConfigs;
import net.mehvahdjukaar.moonlight.api.platform.PlatformHelper;
import net.mehvahdjukaar.moonlight.api.resources.SimpleTagBuilder;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynServerResourcesProvider;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynamicDataPack;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.TagManager;
import net.minecraft.world.level.block.Blocks;
import org.apache.logging.log4j.Logger;

public class ServerDynamicResourcesHandler extends DynServerResourcesProvider {

    public ServerDynamicResourcesHandler() {
        super(new DynamicDataPack(EveryCompat.res("generated_pack")));
        //needed for tags
        getPack().addNamespaces("minecraft");
        getPack().addNamespaces("forge");
        this.dynamicPack.generateDebugResources = PlatformHelper.isDev() || EarlyConfigs.DEBUG_RESOURCES.get();
    }

    @Override
    public Logger getLogger() {
        return EveryCompat.LOGGER;
    }

    @Override
    public boolean dependsOnLoadedPacks() {
        return EarlyConfigs.SPEC != null && EarlyConfigs.DEPEND_ON_PACKS.get();
    }

    @Override
    public void regenerateDynamicAssets(ResourceManager manager) {
        EveryCompat.forAllModules(m -> {
            try {
                m.addDynamicServerResources(this, manager);
            } catch (Exception e) {
                getLogger().error("Failed to generate server dynamic assets for module {}: {}", m, e);
            }
        });
    }

    @Override
    public void generateStaticAssetsOnStartup(ResourceManager manager) {
        EveryCompat.forAllModules(m -> {
            try {
                m.addStaticServerResources(this, manager);
            } catch (Exception e) {
                getLogger().error("Failed to generate server static assets for module {} : {}", m, e);
            }
        });
    }


}
