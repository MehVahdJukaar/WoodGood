package net.mehvahdjukaar.every_compat.api;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesTypeRegistry;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;

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
    public static void addUndetectedBlockTypes() {

        // ───────────────────────────────── WOODTYPE ──────────────────────────────────

        WoodTypeRegistry.INSTANCE.addSimpleFinder("mod_id", "cherry");

        /// If you have multiple WoodTypes to add, then you can do the following:
        WoodTypeRegistry woodReg = WoodTypeRegistry.INSTANCE;

        /// Simple Finder - it will use the ID by default for log: mod_id:cherry_log and planks: mod_id:cherry_planks
        woodReg.addSimpleFinder("mod_id", "cherry");
        woodReg.addSimpleFinder("mod_id:dark_cherry");

        /// Advanced Finder - Adding undetected children:
        woodReg.addSimpleFinder("mod_id", "dark_birch")
                .log("log_dark_birch") // If ID of the log is unique, then .log() can be used, without it, default: dark_birch_log
                .planks("dark_birch_plank") // if ID of the planks has no "s" or unique naming/spelling, then .planks can be used
                .childBlock(VanillaWoodChildKeys.FENCE, "dark_brich_post_fence"); // ID of the fence is unique, you can use .childBlock for other children

        // ──────────────────────────────── LEAVESTYPE ─────────────────────────────────

        /// If you have multiple LeavesTypes to add, then you can do the following:
        LeavesTypeRegistry leafReg = LeavesTypeRegistry.INSTANCE;

        /// Simple Finder
        LeavesTypeRegistry.INSTANCE.addSimpleFinder("mod_id:red_cherry");
        leafReg.addSimpleFinder("mod_id","white_cherry");

        /// Advanced Finder - Adding undetected children:
        leafReg.addSimpleFinder("mod_id", "black_cherry") // DEFAULT: will detect "black_cherry_leaves"
                .childBlock("sapling", "black_cherries_sapling");

        /// Adding LeavesType's Associated-WoodType:
        leafReg.addLeavesToWoodMapping("mod_id:white_cherry", "minecraft:cherry");
        leafReg.addLeavesToWoodMapping("mod_id", "red_cherry", "black_cherry"); // both WoodType and LeavesType are from the same mod_id

    }

    /* ─────────────────────────────────────────── ADDITIONAL DETAIL ────────────────────────────────────────────
     *
     * Simple Finder automatically find the blocktypes via IDs:
     *      log - mod_id:cherry_Log
     *      planks - mod_id:cherry_planks
     *
     * It can find WoodType with suffix for STEM or WOOD, STRIPPED_LOG, and STRIPPED_WOOD.
     *
     */
}
