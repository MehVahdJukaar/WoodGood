package net.mehvahdjukaar.wood_good;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = WoodGood.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WoodGoodClient {

    @SubscribeEvent
    public static void entityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        WoodGood.forAllModules(m->m.registerEntityRenderers(event));
    }

    @SubscribeEvent
    public static void init(final FMLClientSetupEvent event) {
        WoodGood.forAllModules(m->m.onClientSetup(event));
    }

    @SubscribeEvent
    public static void registerBlockColors(ColorHandlerEvent.Block event) {

    }

}
