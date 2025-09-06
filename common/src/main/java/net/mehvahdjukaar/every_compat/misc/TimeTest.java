package net.mehvahdjukaar.every_compat.misc;

import com.google.common.base.Stopwatch;
import net.mehvahdjukaar.every_compat.configs.ECConfigs;

public class TimeTest {

    private static final Stopwatch STOPWATCH = Stopwatch.createUnstarted();

    public static void start(){
        STOPWATCH.start();
    }

    public static void stopAndPrint(){
        System.out.println("Config: "+ECConfigs.CLIENT_GENERATION_MODE.get());
        System.out.println("Lookup time: "+ ECConfigs.watch);

        STOPWATCH.stop();
        System.out.println("GAME LOADED IN: " + STOPWATCH);
        STOPWATCH.reset();
    }
}
