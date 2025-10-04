package net.mehvahdjukaar.every_compat.api;

import net.mehvahdjukaar.every_compat.EcProxy;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.example.WoodGoodAddonExample;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

import static net.mehvahdjukaar.every_compat.EveryCompat.maybeAddModule;

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
     * <p>
     * Take a look at {@link net.mehvahdjukaar.every_compat.api.example.WoodGoodModuleExample} for an example module
     */
    public static synchronized void registerModule(CompatModule module) {
        EcProxy.add(module);
    }

    public static synchronized void registerOptionalModule(String modId, Supplier<Class<? extends CompatModule>> moduleClass) {
        EcProxy.addOptional(modId, moduleClass);
    }

    //no need for this
    @Deprecated(forRemoval = true)
    public static Collection<CompatModule> getModule(String modId) {
        return EveryCompat.getModulesOfMod(modId);
    }


    //TODO: delete these 2 they arent needed. less stuff here the better
    /// Add your module to EveryCompat. The module will be loaded if the mod is installed
    @Deprecated(forRemoval = true)
    public static void addIfLoaded(String modId, Supplier<Class<? extends CompatModule>> moduleClassSupplier) {
        maybeAddModule(modId, moduleClassSupplier);
    }

    //delete
    @Deprecated(forRemoval = true)
    @SafeVarargs
    /// Same as addIfLoaded but Multiple Module can be StoneModule, MudModule, WoodModule or others for the one same mod
    public static void addMultipleIfLoaded(String modId, Supplier<Class<? extends CompatModule>>... moduleClasses) {
        for (Supplier<Class<? extends CompatModule>> moduleClassSupplier : moduleClasses) {
            maybeAddModule(modId, moduleClassSupplier);
        }
    }

    /// If you mod has compat mods that support it with Biomes O' Plenty or other Wood Mods below can make an exception
    /// so EC won't generate blocks from your mod with Biomes O' Plenty
    public static void addOtherCompatMod(String compatModId, List<String> fromModId, List<String> supportedModId) {
        EcProxy.addOtherCompatMod(compatModId, fromModId, supportedModId);
    }

    public static void addOtherCompatMod(String compatModId, String fromModId, String supportedModId) {
        addOtherCompatMod(compatModId, List.of(fromModId), List.of(supportedModId));
    }

    public static void addOtherCompatMod(String compatModId, String fromModId, String... supportedModId) {
        List<String> list = new ArrayList<>();
        Collections.addAll(list, supportedModId);
        addOtherCompatMod(compatModId, List.of(fromModId), list);
    }

    public static void addOtherCompatMod(String compatModId, List<String> fromModId, String... supportedModId) {
        List<String> list = new ArrayList<>();
        Collections.addAll(list, supportedModId);
        addOtherCompatMod(compatModId, fromModId, list);
    }

    public static void addOtherCompatMod(String compatModId, List<String> fromModId, String supportedModId) {
        addOtherCompatMod(compatModId, fromModId, List.of(supportedModId));
    }

}
