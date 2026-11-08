package net.mehvahdjukaar.every_compat.api;

import net.mehvahdjukaar.every_compat.ECModules;
import net.mehvahdjukaar.every_compat.api.example.WoodGoodAddonExample;
import org.jetbrains.annotations.ApiStatus;

import java.util.List;
import java.util.function.Supplier;

/**
 * Use this class register new wood type blocks and module to Every Compat
 * To register wood types that aren't detected reference net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;
 * <p>
 * Only usable inside {@link EveryCompatPlugin#register(PluginEvent)}, calls after that throw
 * <p>
 * Take a look at {@link WoodGoodAddonExample} for examples
 */
public final class PluginEvent {
    @ApiStatus.Internal
    public PluginEvent() {
    }

    private static void checkStillLoading() {
        if (ECModules.arePluginsLoaded()) {
            throw new IllegalStateException("Every Compat plugins already loaded. Only use the registrar inside EveryCompatPlugin.register");
        }
    }

    /**
     * Register a new compat module for your modded blocks
     * <br>
     * Only added if the mod is loaded and the module isn't blacklisted or disabled
     * @param moduleClass example: WoodGoodModuleExample.class
     *                    <p>
     *                    Take a look at {@link WoodGoodAddonExample} for an example module
     */
    public void addModule(String modId, Supplier<Class<? extends CompatModule>> moduleClass) {
        checkStillLoading();
        ECModules.addOptionalModule(modId, moduleClass);
    }

    @SafeVarargs
    public final void addModules(String modId, Supplier<Class<? extends CompatModule>>... moduleClasses) {
        checkStillLoading();
        ECModules.addMultipleOptional(modId, moduleClasses);
    }

    // ┌────────────────────────────────────────────────────────────────────────────────────────────────────────┐
    // │  If your mod has compat mods that support it with Biomes O' Plenty or other mods below can make an     │
    // │  exception so EC & its addons won't generate blocks from your mod with Biomes O' Plenty or other mods  │
    // │  Example:                                                                                              │
    // │  - CompatMod: Macaw's Bridges - Biomes O' Plenty                                                       │
    // │  - fromModId: Biomes O' Plenty                                                                         │
    // │  - SupportedModId: Macaw's Bridge                                                                      │
    // └────────────────────────────────────────────────────────────────────────────────────────────────────────┘

    // Single ModiD & Multiple SupportedModId
    public void addOtherCompatMod(String compatModId, String fromModId, String... supportedModIds) {
        checkStillLoading();
        ECModules.addOtherCompatMod(compatModId, fromModId, supportedModIds);
    }

    // Multiple ModiD & Multiple SupportedModId
    public void addOtherCompatMod(String compatModId, List<String> fromModIds, String... supportedModIds) {
        checkStillLoading();
        ECModules.addOtherCompatMod(compatModId, fromModIds, supportedModIds);
    }

    /**
     * Disable a whole module so it won't register anything
     * <br>
     * Applies to Wood-Good, Stone-Zone &amp; Gems-Realm, same as the modules blacklist config
     *
     * @param modId mod id of the supported mod, example: "handcrafted"
     */
    public void disableModule(String modId) {
        checkStillLoading();
        ECModules.disableModule(modId);
    }

    /**
     * Disable a specific block (and its item) from being registered. Works for any BlockType, not only WoodType|LeavesType
     *
     * @param blockId same format as the blocks blacklist config: supportedModId/blockTypeNamespace/blockName
     *                <p>
     *                example: "quark/biomesoplenty/redwood_ladder"
     */
    public void disableBlock(String blockId) {
        checkStillLoading();
        ECModules.disableBlock(blockId);
    }

    /**
     * Same as {@link PluginEvent#disableBlock(String)} but with a regex
     *
     * @param blockIdRegex example: "handcrafted/biomesoplenty/.*_chair"
     */
    public void disableBlocksMatching(String blockIdRegex) {
        checkStillLoading();
        ECModules.disableBlocksMatching(blockIdRegex);
    }
}
