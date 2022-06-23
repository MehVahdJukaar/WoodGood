package net.mehvahdjukaar.every_compat.dynamicpack;

import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.every_compat.configs.EarlyConfigs;
import net.mehvahdjukaar.selene.client.asset_generators.textures.Palette;
import net.mehvahdjukaar.selene.client.asset_generators.textures.Respriter;
import net.mehvahdjukaar.selene.client.asset_generators.textures.TextureImage;
import net.mehvahdjukaar.selene.resourcepack.AfterLanguageLoadEvent;
import net.mehvahdjukaar.selene.resourcepack.DynamicTexturePack;
import net.mehvahdjukaar.selene.resourcepack.RPAwareDynamicTextureProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraftforge.fml.ModList;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;


public class ClientDynamicResourcesHandler extends RPAwareDynamicTextureProvider {

    public ClientDynamicResourcesHandler() {
        super(new DynamicTexturePack(WoodGood.res("generated_pack")));
        //since we place chests textures in its namespace to use its renderer
        if (ModList.get().isLoaded("quark")) getPack().addNamespaces("quark");
        //this.dynamicPack.generateDebugResources = false;
    }

    @Override
    public Logger getLogger() {
        return WoodGood.LOGGER;
    }

    @Override
    public boolean dependsOnLoadedPacks() {
        return EarlyConfigs.REGISTRY_CONFIG != null && EarlyConfigs.DEPEND_ON_PACKS.get();
    }

    @Override
    public void generateStaticAssetsOnStartup(ResourceManager manager) {
        WoodGood.forAllModules(m -> {
            try {
                m.addStaticClientResources(this, manager);
            } catch (Exception e) {
                getLogger().error("Failed to generate client static assets for module {}: {}", m, e);
            }
        });
    }

    @Override
    public void addDynamicTranslations(AfterLanguageLoadEvent lang) {
        WoodGood.forAllModules(m -> {
            m.addTranslations(this, lang);
        });
        int a = 1;
    }

    @Override
    public void regenerateDynamicAssets(ResourceManager manager) {
        WoodGood.forAllModules(m -> {
            try {
                m.addDynamicClientResources(this, manager);
            } catch (Exception e) {
                getLogger().error("Failed to generate client dynamic assets for module {}:", m, e);
            }
        });
        int a = 1;
    }

}
