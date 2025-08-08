package net.mehvahdjukaar.every_compat.forge;

import net.mehvahdjukaar.every_compat.EveryCompatClient;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EveryCompatForgeClient {

    private static boolean firstScreenShown;

    public static void init() {
        MinecraftForge.EVENT_BUS.register(EveryCompatForgeClient.class);
    }

    @SubscribeEvent
    public static void onScreenDrawPost(ScreenEvent.Init.Post event) {
        if (!firstScreenShown && event.getScreen() instanceof TitleScreen) {
            EveryCompatClient.onFirstScreen(event.getScreen());
            firstScreenShown = true;
        }
    }
}