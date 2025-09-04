package net.mehvahdjukaar.every_compat.misc;

import com.google.common.base.Stopwatch;

public class TimeTest {

    private static final Stopwatch STOPWATCH = Stopwatch.createUnstarted();

    public static void start(){
        STOPWATCH.start();
    }

    public static void stopAndPrint(){
        STOPWATCH.stop();
        System.out.println("GAME LOADED IN: " + STOPWATCH);
        STOPWATCH.reset();
    }
}
