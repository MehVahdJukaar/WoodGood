package net.mehvahdjukaar.every_compat;

import net.mehvahdjukaar.selene.block_set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.selene.client.texture_renderer.RenderedTexturesManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

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
    public static void registerBlockColors(ColorHandlerEvent.Item event) {
        WoodGood.forAllModules(m -> m.registerColors(event));
    }

    /*
    @Mod.EventBusSubscriber(modid = WoodGood.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class AA {

        private static int aa = 0;
        private static boolean b = false;

        @SubscribeEvent
        public static void renderTickEvent(TickEvent.RenderTickEvent event) {
            if (aa % 200 == 0) {
                try {
                    b = true;
                    Path outputFolder = Paths.get("icon_textures2");
                    outputFolder = Files.createDirectories(outputFolder);
                    for (var w : WoodTypeRegistry.WOOD_TYPES.values()) {
                        Block v = w.planks;
                        ResourceLocation res = v.getRegistryName();
                        //if (res.getNamespace().equals(WoodGood.MOD_ID)) {
                            var t = RenderedTexturesManager.getFlatItemTexture(v.asItem(), 512);
                            RenderedTexturesManager.drawItem(t,v.asItem().getDefaultInstance());
                            t.download();
                            t.getPixels().flipY();
                            t.upload();
                            t.saveTextureToFile(outputFolder);
                        //}
                    }
                } catch (Exception ignored) {
                }
            }
            aa++;
        }
    }*/
}
