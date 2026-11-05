package net.mehvahdjukaar.every_compat.api;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.example.WoodGoodAddonExample;
import net.mehvahdjukaar.every_compat.misc.OtherCompatMod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

/**
 * Use this class register new wood type blocks and module to Every Compat
 * To register wood types that aren't detected reference net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;
 * <p>
 * Take a look at {@link WoodGoodAddonExample} for examples
 */
public class EveryCompatAPI {

    /**
     * @deprecated Must change to {@link EveryCompatAPI#registerOptionalModule(String, Supplier)}
     * <br>
     * REASON: registerModule doesn't follow the blacklist rule but registerOptionalModule follows blacklist rule
     * <br><br>
     * Register a new compat module for your modded blocks
     *
     * @param module your module instance. Can be a custom implementation
     *               <p>
     *               Take a look at {@link WoodGoodAddonExample} for an example module
     */
    @Deprecated
    public static synchronized void registerModule(CompatModule module) {
        EveryCompat.addModule(module);
    }

    /**
     * Register a new compat module for your modded blocks
     * @param moduleClass example: WoodGoodModuleExample.class
     *                    <p>
     *                    Take a look at {@link WoodGoodAddonExample} for an example module
     */
    public static synchronized void registerOptionalModule(String modId, Supplier<Class<? extends CompatModule>> moduleClass) {
        EveryCompat.addOptionalModule(modId, moduleClass);
    }

    public static void addIfLoaded(String modId, Supplier<Class<? extends CompatModule>> moduleClassSupplier) {
        EveryCompat.addOptionalModule(modId, moduleClassSupplier);
    }

    @SafeVarargs
    public static void addMultipleIfLoaded(String modId, Supplier<Class<? extends CompatModule>>... moduleClasses) {
        EveryCompat.addMultipleOptional(modId, moduleClasses);
    }

    /**
     * Stops every module for that mod id from registering anything, addons included. Call it during mod init
     */
    public static void disableModule(String modId) {
        EveryCompat.disableModule(modId);
    }

    /**
     * Skips that block (and its item). Works on any block type, call it during mod init
     * ids look like the config blacklist ones: supportedModId/blockTypeNamespace/blockName, e.g. handcrafted/biomesoplenty/fir_chair
     */
    public static void disableBlock(String blockId) {
        EveryCompat.disableBlock(blockId);
    }

    /**
     * Same as disableBlock but takes a regex on the id, e.g. handcrafted/biomesoplenty/.*_chair
     */
    public static void disableBlocksMatching(String blockIdRegex) {
        EveryCompat.disableBlocksMatching(blockIdRegex);
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
