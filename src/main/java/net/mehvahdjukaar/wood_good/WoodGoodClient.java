package net.mehvahdjukaar.wood_good;

import net.mehvahdjukaar.selene.client.texture_renderer.RenderedTexturesManager;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Mod.EventBusSubscriber(modid = WoodGood.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WoodGoodClient {

    @SubscribeEvent
    public static void entityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        WoodGood.forAllModules(m -> m.registerEntityRenderers(event));
    }

    @SubscribeEvent
    public static void init(final FMLClientSetupEvent event) {
        WoodGood.forAllModules(m -> m.onClientSetup(event));
    }

    @SubscribeEvent
    public static void registerBlockColors(ColorHandlerEvent.Block event) {

    }

    @Mod.EventBusSubscriber(modid = WoodGood.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class AA {

        private static int aa = 0;

        @SubscribeEvent
        public static void renderTickEvent(TickEvent.RenderTickEvent event) {
            if (aa == 0) {
                aa++;
                for (var v : ForgeRegistries.ITEMS.getValues()) {
                    ResourceLocation res = v.getRegistryName();
                    if (res.getNamespace().equals(WoodGood.MOD_ID)) {
                        RenderedTexturesManager.getFlatItemTexture(v, 256);
                    }
                }
            } else if (aa == 1) {
                try {
                    Path outputFolder = Paths.get("icon_textures");
                    outputFolder = Files.createDirectories(outputFolder);
                    for (var v : ForgeRegistries.ITEMS.getValues()) {
                        ResourceLocation res = v.getRegistryName();
                        if (res.getNamespace().equals(WoodGood.MOD_ID)) {
                            var t = RenderedTexturesManager.getFlatItemTexture(v, 256);

                            t.download();
                            t.getPixels().flipY();
                            t.upload();
                            t.saveTextureToFile(outputFolder);
                        }
                    }
                } catch (Exception ignored) {
                }
                aa++;
            }
        }
    }
}
