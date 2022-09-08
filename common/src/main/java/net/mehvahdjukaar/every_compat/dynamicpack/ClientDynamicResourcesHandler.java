package net.mehvahdjukaar.every_compat.dynamicpack;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.configs.EarlyConfigs;
import net.mehvahdjukaar.moonlight.api.events.AfterLanguageLoadEvent;
import net.mehvahdjukaar.moonlight.api.platform.PlatformHelper;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynClientResourcesProvider;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynamicTexturePack;
import net.minecraft.server.packs.resources.ResourceManager;
import org.apache.logging.log4j.Logger;


public class ClientDynamicResourcesHandler extends DynClientResourcesProvider {

    public ClientDynamicResourcesHandler() {
        super(new DynamicTexturePack(EveryCompat.res("generated_pack")));
        //since we place chests textures in its namespace to use its renderer
        if (PlatformHelper.isModLoaded("quark")) getPack().addNamespaces("quark");
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
    public void generateStaticAssetsOnStartup(ResourceManager manager) {
        EveryCompat.forAllModules(m -> {
            try {
                m.addStaticClientResources(this, manager);
            } catch (Exception e) {
                getLogger().error("Failed to generate client static assets for module {}: {}", m, e);
            }
        });
    }

    @Override
    public void addDynamicTranslations(AfterLanguageLoadEvent lang) {
        EveryCompat.forAllModules(m -> {
            m.addTranslations(this, lang);
        });
    }

    @Override
    public void regenerateDynamicAssets(ResourceManager manager) {
        EveryCompat.forAllModules(m -> {
            try {
                m.addDynamicClientResources(this, manager);
            } catch (Exception e) {
                getLogger().error("Failed to generate client dynamic assets for module {}:", m, e);
            }
        });
    }

}
