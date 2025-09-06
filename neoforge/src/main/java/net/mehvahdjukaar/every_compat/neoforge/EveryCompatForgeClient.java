package net.mehvahdjukaar.every_compat.neoforge;

import net.mehvahdjukaar.every_compat.EveryCompatClient;
import net.minecraft.client.gui.screens.TitleScreen;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.config.ConfigTracker;
import net.neoforged.neoforge.client.event.ScreenEvent;
import net.neoforged.neoforge.common.NeoForge;

public class EveryCompatForgeClient {

    private static boolean firstScreenShown;

    public static void init() {
        NeoForge.EVENT_BUS.register(EveryCompatForgeClient.class);
    }

    @SubscribeEvent
    public static void onScreenDrawPost(ScreenEvent.Init.Post event) {
        if (!firstScreenShown && event.getScreen() instanceof TitleScreen) {
            EveryCompatClient.onFirstScreen(event.getScreen());
            firstScreenShown = true;
        }
    }
}