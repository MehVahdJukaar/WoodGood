package net.mehvahdjukaar.wood_good.dynamicpack;

import net.mehvahdjukaar.selene.resourcepack.DynamicDataPack;
import net.mehvahdjukaar.selene.resourcepack.RPAwareDynamicDataProvider;
import net.mehvahdjukaar.selene.resourcepack.StaticResource;
import net.mehvahdjukaar.wood_good.WoodGood;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class ServerDynamicResourcesHandler extends RPAwareDynamicDataProvider {

    public ServerDynamicResourcesHandler() {
        super(new DynamicDataPack(WoodGood.res("generated_pack")));
        this.dynamicPack.generateDebugResources = true;
        //needed for tags
        WoodGood.forAllModules(m->getPack().addNamespaces(m.getModId()));
    }

    @Override
    public Logger getLogger() {
        return WoodGood.LOGGER;
    }

    @Override
    public boolean dependsOnLoadedPacks() {
        return false;
    }

    @Override
    public void regenerateDynamicAssets(ResourceManager manager) {

        WoodGood.forAllModules(m -> {
            try {
                m.addStaticServerResources(this, manager);
            } catch (Exception e) {
                getLogger().error("Failed to generate server static assets for module: {}", m);
            }
        });
    }

    @Override
    public void generateStaticAssetsOnStartup(ResourceManager manager) {

        WoodGood.forAllModules(m -> {
            try {
                m.addStaticServerResources(this, manager);
            } catch (Exception e) {
                getLogger().error("Failed to generate server static assets for module: {}", m);
            }
        });
    }



}
