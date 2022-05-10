package net.mehvahdjukaar.wood_good.dynamicpack;

import net.mehvahdjukaar.selene.resourcepack.DynamicTexturePack;
import net.mehvahdjukaar.selene.resourcepack.RPAwareDynamicTextureProvider;
import net.mehvahdjukaar.selene.resourcepack.asset_generators.LangBuilder;
import net.mehvahdjukaar.selene.resourcepack.asset_generators.textures.Palette;
import net.mehvahdjukaar.selene.resourcepack.asset_generators.textures.Respriter;
import net.mehvahdjukaar.selene.resourcepack.asset_generators.textures.TextureImage;
import net.mehvahdjukaar.wood_good.WoodGood;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;


public class ClientDynamicResourcesHandler extends RPAwareDynamicTextureProvider {

    public ClientDynamicResourcesHandler() {
        super(new DynamicTexturePack(WoodGood.res("generated_pack")));
        this.dynamicPack.generateDebugResources = true;
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
        LangBuilder langBuilder = new LangBuilder();

        WoodGood.forAllModules(m -> {
            try {
                m.addStaticClientResources(this, manager, langBuilder);
            } catch (Exception e) {
                getLogger().error("Failed to generate client static assets for module: {}", m);
            }
        });

        dynamicPack.addLang(WoodGood.res("en_us"), langBuilder.build());

    }


    @Override
    public void regenerateDynamicAssets(ResourceManager manager) {
        WoodGood.forAllModules(m -> {
            try {
                m.addDynamicClientResources(this, manager);
            } catch (Exception e) {
                getLogger().error("Failed to generate client dynamic assets for module: {}", m);
            }
        });
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
