package net.mehvahdjukaar.every_compat.fabric;

import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.mehvahdjukaar.every_compat.EveryCompatClient;
import net.minecraft.client.gui.screens.TitleScreen;

public class EveryCompatFabricClient {

    private static boolean firstScreenShown = false;

    public static void init() {
        ItemTooltipCallback.EVENT.register(EveryCompatClient::onItemTooltip);

        ScreenEvents.AFTER_INIT.register((client, screen, scaledWidth, scaledHeight) -> {
            if (!firstScreenShown && screen instanceof TitleScreen) {
                EveryCompatClient.onFirstScreen(screen);
                firstScreenShown = true;
            }
        });
    }

}
