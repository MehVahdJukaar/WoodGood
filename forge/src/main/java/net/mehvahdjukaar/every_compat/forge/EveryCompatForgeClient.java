package net.mehvahdjukaar.every_compat.forge;

import net.mehvahdjukaar.every_compat.EveryCompatClient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = EveryCompatForge.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EveryCompatForgeClient {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event){
       event.enqueueWork(EveryCompatClient::clientSetup);
    }

}
