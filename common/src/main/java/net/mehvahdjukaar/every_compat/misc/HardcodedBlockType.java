package net.mehvahdjukaar.every_compat.misc;

import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import org.jetbrains.annotations.Nullable;

// ugly mess. Too coupled with wood types and too many hardcoded exceptions
public class HardcodedBlockType {
    public static String woodidentify;
    public static String woodTypeFromMod;
    public static String leavesidentify;
    public static String leavesTypeFromMod;
    public static String modId;
    public static String supportedBlockName;
    public static String shortenedIdenfity;

    @Nullable
    public static Boolean isWoodBlockAlreadyRegistered(String blockName, WoodType woodType, String ModId, String shortenedId) {
        woodTypeFromMod = woodType.getNamespace();
        woodidentify = woodType.getId().toString();
        modId = ModId;
        supportedBlockName = blockName;
        shortenedIdenfity = shortenedId;

                    /// ========== EXCLUDE ========== \\\
        // Discarding Dynamic Trees and its addons
        if (isWoodRegistryOf("", "", "dynamictrees|dt", "", "")) return true;

        // Garden-Of-The-dead's whistle & Snifferent already has branches, branches from Regions-Unexplored is not needed
        if (isWoodRegistryOf("", "", "gardens_of_the_dead|snifferent", "", "branch")) return true;

        // Nether's Exoticism already has branches, branches from Regions-Unexplored is not needed
        if (isWoodRegistryOf("", "", "", "nethers_exoticism:jabuticaba", "branch")) return true;

        // Quark & Woodworks have chest & trapped_chest. | is this needed? shouldnt it be covered by the next statements?
        if (isWoodRegistryOf("", "abnww", "quark", "", "chest")) return true;


                    /// ========== INCLUDE ========== \\\
        // Makes it so the guita's Branches block still registers if another mod adds a branch block/item
        if (isWoodRegistryOf("branches", "", "", "", "branch")) return false;

        // Minecraft has "mangrove" that caused the generation of blocks with The-Twilight-Forest's mangrove to be skipped.
        if (isWoodRegistryOf("", "", "", "twilightforest:mangrove", "")) return false;

        // chests & ladders from Quark aren't generated with Abnormal's Wood mods
        if (isWoodRegistryOf("quark", "", "upgrade_aquatic|autumnity|atmospheric|environmental", "", "")) return false;

        // Better Nether & Better End have stripped_bark as stripped_wood but bark from Bewitchment caused EC to skip
        if (isWoodRegistryOf("", "bw", "betternether|betterend", "", "")) return false;

        // Create's windows will be skipped blc [Let's do] Blooming Nature & Meadow already has windows
        if (isWoodRegistryOf("", "", "bloomingnature|meadow", "", "window")) return false;

        // ArchitectPalette's boards will be skipped blc Upgrade-Aqautic already has boards but have no recipes &
        // no item in CreativeMode
        if (isWoodRegistryOf("", "", "upgrade_aquatic", "", "driftwood_boards|river_boards")) return false;

        // Similar to above, Architect's Palette - boards will be skipped due to the existing boards in Autumnity
        if (isWoodRegistryOf("", "", "autumnity", "", "maple_boards")) return false;

        // check if TerraFirmaCraft (tfc) mod exist, then won't discards wood types
        if (isWoodRegistryOf("", "", "tfc", "", "")) return false;

        //ecologics and quark azalea. tbh not sure why needed
        if (isWoodRegistryOf("quark", "", "", "ecologics:azalea", "")) return false;

        // what's the reason for below? | hardcoding
        if (isWoodRegistryOf("", "af", "", "", "")) return false;

        // Valhelsia Structure's blocks must be not be discarded
        if (isWoodRegistryOf("", "vs", "", "", "")) return false;

        // we always register everything for these (mehvahdjukaar)
        if (isWoodRegistryOf("", "abnww", "architects_palette", "", "")) return false;

        // if (this.shortenedId().equals("ap")) return false; //hardcoding dont remember why i had this. Incase you want o
        if (isWoodRegistryOf("", "ap", "", "", "")) return false;

        return null;
    }

    @Nullable
    public static Boolean isLeavesBlockAlreadyRegistered(String blockName, LeavesType leavesType, String supportedModId, String shortenedId) {
        leavesTypeFromMod = leavesType.getNamespace();
        leavesidentify = leavesType.getId().toString();
        modId = supportedModId;
        supportedBlockName = blockName;
        shortenedIdenfity = shortenedId;

        //!! INCLUDE ==========
        // Unrelated to Quark's ancient_leaves & Alex's Cave (ancient_leaves) should be included
        if (isLeavesRegistryOf("quark", "", "", "alexscaves:ancient", "")) return false;

        // Macaw's Fences&Walls or MrCrayFish's Furniture - hedges will be skipped because Quark already has hedges
        if (isLeavesRegistryOf("", "mcf|cfm", "quark", "", "")) return false;

        //!! EXCLUDE ==========


        return null;
    }

    public static Boolean isWoodRegistryOf(String whichSupportedModId, String shortenedId, String woodtypeFromMod, String woodTypeId, String whichSupportedBlockName) {

        String[] expressions = {
                whichSupportedModId,
                shortenedId,
                woodtypeFromMod,
                woodTypeId,
                whichSupportedBlockName
        };

        String[] values = {
                modId,
                shortenedIdenfity,
                woodTypeFromMod,
                woodidentify,
                supportedBlockName
        };

        for (int idx = 0; idx < values.length; idx++ ) {

            if (!expressions[idx].isEmpty()) { // Skip the blank expressions
                boolean isNotMatched = !values[idx].matches(expressions[idx]);
                if (isNotMatched) return false;
            }
        }

        return true;
    }

    public static Boolean isLeavesRegistryOf(String whichSupportedModId, String shortenedId, String woodtypeFromMod, String leavesTypeId, String whichSupportedBlockName) {

        String[] expressions = {
                whichSupportedModId,
                shortenedId,
                woodtypeFromMod,
                leavesTypeId,
                whichSupportedBlockName
        };

        String[] values = {
                modId,
                shortenedIdenfity,
                leavesTypeFromMod,
                leavesidentify,
                supportedBlockName
        };

        for (int idx = 0; idx < values.length; idx++ ) {

            if (!expressions[idx].isEmpty()) { // Skip the blank expressions
                boolean isNotMatched = !values[idx].matches(expressions[idx]);
                if (isNotMatched) return false;
            }
        }

        return true;
    }


}