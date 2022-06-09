package net.mehvahdjukaar.every_compat.dynamicpack;

import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.selene.resourcepack.DynamicDataPack;
import net.mehvahdjukaar.selene.resourcepack.RPAwareDynamicDataProvider;
import net.minecraft.server.packs.resources.ResourceManager;
import org.apache.logging.log4j.Logger;

public class ServerDynamicResourcesHandler extends RPAwareDynamicDataProvider {

    public ServerDynamicResourcesHandler() {
        super(new DynamicDataPack(WoodGood.res("generated_pack")));
        //needed for tags
        WoodGood.forAllModules(m -> getPack().addNamespaces(m.getModId()));
        getPack().addNamespaces("minecraft");
        getPack().addNamespaces("forge");
    }

    @Override
    public Logger getLogger() {
        return WoodGood.LOGGER;
    }

    @Override
    public boolean dependsOnLoadedPacks() {
        return true;
    }

    @Override
    public void regenerateDynamicAssets(ResourceManager manager) {
        WoodGood.forAllModules(m -> {
            try {
                m.addDynamicServerResources(this, manager);
            } catch (Exception e) {
                getLogger().error("Failed to generate server dynamic assets for module {}: {}", m, e);
            }
        });
    }

    @Override
    public void generateStaticAssetsOnStartup(ResourceManager manager) {
        WoodGood.forAllModules(m -> {
            try {
                m.addStaticServerResources(this, manager);
            } catch (Exception e) {
                getLogger().error("Failed to generate server static assets for module {} : {}", m, e);
            }
        });
    }


}
