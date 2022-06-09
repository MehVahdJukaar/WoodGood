package net.mehvahdjukaar.every_compat.dynamicpack;

import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.selene.client.asset_generators.textures.Palette;
import net.mehvahdjukaar.selene.client.asset_generators.textures.Respriter;
import net.mehvahdjukaar.selene.client.asset_generators.textures.TextureImage;
import net.mehvahdjukaar.selene.resourcepack.AfterLanguageLoadEvent;
import net.mehvahdjukaar.selene.resourcepack.DynamicTexturePack;
import net.mehvahdjukaar.selene.resourcepack.RPAwareDynamicTextureProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;


public class ClientDynamicResourcesHandler extends RPAwareDynamicTextureProvider {

    public ClientDynamicResourcesHandler() {
        super(new DynamicTexturePack(WoodGood.res("generated_pack")));
        //this.dynamicPack.generateDebugResources = false;
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
        WoodGood.forAllModules(m->{
            m.addTranslations(this,lang);
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


    /**
     * helper method.
     * recolors the template image with the color grabbed from the given image restrained to its mask, if possible
     */
    @Nullable
    public static TextureImage recolorFromVanilla(ResourceManager manager, TextureImage vanillaTexture, ResourceLocation vanillaMask,
                                                  ResourceLocation templateTexture) {
        try (TextureImage scribbleMask = TextureImage.open(manager, vanillaMask);
             TextureImage template = TextureImage.open(manager, templateTexture)) {
            Respriter respriter = Respriter.of(template);
            Palette palette = Palette.fromImage(vanillaTexture, scribbleMask);
            return respriter.recolor(palette);
        } catch (Exception ignored) {
        }
        return null;
    }

}
