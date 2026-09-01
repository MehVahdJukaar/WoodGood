package net.mehvahdjukaar.every_compat.api.example;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.EveryCompatAPI;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesTypeRegistry;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;

public final class WoodGoodAddonExample {

    private WoodGoodAddonExample() {
    }
    ///      ┌──────────────────────────────────────────────────────────┐
    ///      │   register a custom module and non-detected wood type    │
    ///      └──────────────────────────────────────────────────────────┘
    /// Call this method from your mod's init method
    private static void onModInit() {

        // Register out module
        EveryCompatAPI.registerOptionalModule(EveryCompat.MOD_ID, () -> WoodGoodModuleExample.class);


        // Only add if you need to add non-detected WoodTypes or LeavesTypes
        // ───────────────────────────────── WOODTYPE ──────────────────────────────────

        /// If you have multiple WoodTypes to add, then you can do the following:
        WoodTypeRegistry woodReg = WoodTypeRegistry.INSTANCE;

        woodReg.addSimpleFinder("mod_id", "cherry");

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
        leafReg.addSimpleFinder("mod_id:red_cherry");
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
