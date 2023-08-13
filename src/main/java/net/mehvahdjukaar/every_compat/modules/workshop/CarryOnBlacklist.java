package net.mehvahdjukaar.every_compat.modules.workshop;

import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;

import java.util.ArrayList;

public class CarryOnBlacklist {
    public static void addBlockToBlackList(String blockWithNamespace) {
        queue.add(blockWithNamespace);
    }

    public static void sendIMC(final InterModEnqueueEvent event) {
        for (String block : queue) {
            InterModComms.sendTo("carryon", "blacklistBlock", () -> block);
        }
        queue.clear();
    }

    private static final ArrayList<String> queue = new ArrayList<>();
}
