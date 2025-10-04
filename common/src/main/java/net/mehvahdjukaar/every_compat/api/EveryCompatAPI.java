package net.mehvahdjukaar.every_compat.api;

import net.mehvahdjukaar.every_compat.EcTempPluginStorage;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.example.WoodGoodAddonExample;

import java.util.Collection;
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
     * <p>
     * Take a look at {@link net.mehvahdjukaar.every_compat.api.example.WoodGoodModuleExample} for an example module
     */
    public static synchronized void registerModule(CompatModule module) {
        EcTempPluginStorage.add(module);
    }

    public static synchronized void registerOptionalModule(String modId, Supplier<Class<? extends CompatModule>> moduleClass) {
        EcTempPluginStorage.addOptional(modId, moduleClass);
    }

    //no need for this
    @Deprecated(forRemoval = true)
    public static Collection<CompatModule> getModule(String modId) {
        return EveryCompat.getModulesOfMod(modId);
    }

    /// If you mod has compat mods that support it with Biomes O' Plenty or other Wood Mods below can make an exception
    /// so EC won't generate blocks from your mod with Biomes O' Plenty
    public static void addOtherCompatMod(String compatModId, List<String> fromModId, List<String> supportedModId){
        EcTempPluginStorage.addOtherCompatMod(compatModId, fromModId, supportedModId);
    }


}
