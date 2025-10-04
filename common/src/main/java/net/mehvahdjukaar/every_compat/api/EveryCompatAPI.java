package net.mehvahdjukaar.every_compat.api;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.example.WoodGoodAddonExample;
import net.mehvahdjukaar.every_compat.misc.OtherCompatMod;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

/**
 * Use this class register new wood type blocks and module to Every Compat
 * To register wood types that aren't detected reference net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;
 * <p>
 * Take a look at {@link WoodGoodAddonExample} and {@link net.mehvahdjukaar.every_compat.api.example.WoodGoodModuleExample} for examples
 */
public class EveryCompatAPI {

    /**
     * Register a new compat module for your modded blocks
     *
     * @param module your module instance. Can be a custom implementation
     *               <p>
     *               Take a look at {@link net.mehvahdjukaar.every_compat.api.example.WoodGoodModuleExample} for an example module
     */
    public static synchronized void registerModule(CompatModule module) {
        EveryCompat.addModule(module);
    }

    public static synchronized void registerOptionalModule(String modId, Supplier<Class<? extends CompatModule>> moduleClass) {
        EveryCompat.addOptionalModule(modId, moduleClass);
    }

    //no need for this
    @Deprecated(forRemoval = true)
    public static Collection<CompatModule> getModule(String modId) {
        return EveryCompat.getModulesOfMod(modId);
    }

    public static void addIfLoaded(String modId, Supplier<Class<? extends CompatModule>> moduleClassSupplier) {
        EveryCompat.addOptionalModule(modId, moduleClassSupplier);
    }

    @SafeVarargs
    public static void addMultipleIfLoaded(String modId, Supplier<Class<? extends CompatModule>>... moduleClasses) {
        EveryCompat.addMultipleOptional(modId, moduleClasses);
    }

    // ┌────────────────────────────────────────────────────────────────────────────────────────────────────────┐
    // │  If your mod has compat mods that support it with Biomes O' Plenty or other mods below can make an     │
    // │  exception so EC & its addons won't generate blocks from your mod with Biomes O' Plenty or other mods  │
    // │  Example:                                                                                              │
    // │  - CompatMod: Macaw's Bridges - Biomes O' Plenty                                                       │
    // │  - fromModId: Biomes O' Plenty                                                                         │
    // │  - SupportedModId: Macaw's Bridge                                                                      │
    // └────────────────────────────────────────────────────────────────────────────────────────────────────────┘

    // Multiple ModId & Multiple SupportedModId
    public static void addOtherCompatMod(String compatModId, List<String> fromModId, List<String> supportedModId) {
        EveryCompat.addOtherCompatMod(new OtherCompatMod(compatModId, fromModId, supportedModId));
    }

    // Single ModiD & Multiple SupportedModId
    public static void addOtherCompatMod(String compatModId, String fromModId, String... supportedModId) {
        List<String> list = new ArrayList<>();
        Collections.addAll(list, supportedModId);
        EveryCompat.addOtherCompatMod(new OtherCompatMod(compatModId, List.of(fromModId), list));
    }

    // Multiple ModiD & Single SupportedModId
    public static void addOtherCompatMod(String compatModId, List<String> fromModId, String supportedModId) {
        EveryCompat.addOtherCompatMod(new OtherCompatMod(compatModId, fromModId, List.of(supportedModId)));
    }

    // Single ModiD & Single SupportedModId
    public static void addOtherCompatMod(String compatModId, String fromModId, String supportedModId) {
        EveryCompat.addOtherCompatMod(new OtherCompatMod(compatModId, List.of(fromModId), List.of(supportedModId)));
    }
}
