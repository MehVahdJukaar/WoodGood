package net.mehvahdjukaar.every_compat.api;

import net.mehvahdjukaar.every_compat.EveryCompat;

import java.util.Collection;
import java.util.List;

/**
 * Use this to register new wood type blocks and module
 * To register wood types that aren't detected reference net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;
 */
public class EveryCompatAPI {

    /**
     * Register a new compat module for your modded blocks
     *
     * @param module your module instance. Can be a custom implementation
     */
    public static synchronized void registerModule(CompatModule module) {
        EveryCompat.addModule(module);
    }

    public static Collection<CompatModule> getModule(String modId) {
        return EveryCompat.getModulesOfMod(modId);
    }

    /// If you mod has compat mods that support it with Biomes O' Plenty or other Wood Mods below can make an exception
    /// so EC won't generate blocks from your mod with Biomes O' Plenty
    public static void addOtherCompatMod(String compatModId, List<String> fromModId, List<String> supportedModId){
        EveryCompat.addOtherCompatMod(compatModId, fromModId, supportedModId);
    }

///      ┌──────────────────────────────────────────────────────────┐
///      │         register a custom non-detected wood type         │
///      └──────────────────────────────────────────────────────────┘
/*
    public static void init() {
        BlockSetAPI.addBlockTypeFinder(WoodType.class, WoodType.Finder
            .simple("my_mod", "cherry", "cherry_plank", "cherry_stem"));

    }
*/


}
