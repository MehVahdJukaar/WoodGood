package net.mehvahdjukaar.every_compat.misc;

import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

import static net.mehvahdjukaar.every_compat.configs.UnsafeDisablerConfigs.*;

// ugly mess. Too coupled with WoodTypes|LeavesTypes and too many hardcoded exceptions
public class HardcodedBlockType {

    public static String woodidentify;
    public static String woodTypeFromMod;
    public static String leavesidentify;
    public static String leavesTypeFromMod;
    public static String supportedMod;
    public static String supportedBlockName;

    @Nullable
    public static Boolean isWoodBlockAlreadyRegistered(String entrySetId, String blockName, WoodType woodType, String ModId) {
        woodTypeFromMod = woodType.getNamespace();
        woodidentify = woodType.getId().toString();
        supportedMod = ModId;
        supportedBlockName = blockName;

        /// ─────────────────────────── Include Vanilla Type ────────────────────────────
        // Dawn-Of-Time's fancy-fence only has birch but no other vanilla variants
        if (isWoodFrom("dawnoftimebuilder", "", "minecraft:(oak|acacia|jungle|dark_oak|spruce|mangrove|cherry)", "fancy_fence")) return false;

        /// ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━ EXCLUDE ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

        // Exclude one WoodType from a Wood Mod
        if (WOOD_TYPES_BLACKLIST.get().stream().anyMatch(woodidentify::matches)) return true;

        // Exclude one EntrySet from a module
        if (ENTRY_SETS_BLACKLIST.get().stream().anyMatch(entrySetId::matches)) return true;

        // Exclude all of Vanilla Types that we know of. Excludes other mc namespaced added by mods
        if (isKnownVanillaWood(woodType)) return true;

        // The WoodType from Cobblemon's Legendary Monuments has a 32x32 texture
        if (isWoodFrom("", "", "legendarymonuments:distortion", "")) return true;

        // Supported Mods that have supportedBlockId should be excluded due to FramedBlocks
        if (isWoodFrom("", "", "", "torch") && PlatHelper.isModLoaded("framedblocks")) return true;

        // Nature's-Spirit's joshua texture is a 8x8, it's currently excluded in Valhelaia-Structure for now - the texture generation could be improved
        if (isWoodFrom("valhelsia_structures", "", "natures_spirit:joshua", "")) return true;

        // Garden-Of-The-dead's whistle, Snifferent's globar, Nethers-Exoticism's jabuticaba already has branches, Regions-Unexplored's branches is not needed
        if (isWoodFrom("regions_unexplored", "gardens_of_the_dead|snifferent|nethers_exoticism", "", "(whistlecane|globar|jabuticaba)_branch")) return true;

        // Quark's stripped_post with Ecologics must be excluded beacuse azalea_post and stripped_azalea_post's texture are identical
        if (isWoodFrom("quark", "ecologics", "", "stripped_flowering_azalea_post")) return true;


        /// ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━ INCLUDE ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
        // Makes it so the Guita's Branches block still registers if another mod adds a branch block/item
        if (isWoodFrom("branches", "", "", "branch")) return false;

        // Minecraft's CHERRY prevent the generation of blocks with Terraqueous's CHERRY
        if (isWoodFrom("", "", "terraqueous:cherry", "")) return false;

        // Refurbished-Furniture's oak_table wasn't generated due to Dawn-Of-Time's waxed_oak_table
        if (isWoodFrom("refurbished_furniture", "", "dawnoftimebuilder:waxed_oak", "")) return false;

        // Minecraft has "mangrove" that caused the generation of blocks with The-Twilight-Forest's mangrove to be skipped.
        if (isWoodFrom("", "", "twilightforest:mangrove", "")) return false;

        // Quark's chests & ladders aren't generated with Abnormal's Wood mods | Quark's blocks with Caverns-And-Chasms' AZALEA aren't generated due to Quark's AZALEA
        if (isWoodFrom("quark", "upgrade_aquatic|autumnity|atmospheric|environmental|caverns_and_chasms", "", "")) return false;

        // Better Nether & Better End have stripped_bark as stripped_wood but bark from Bewitchment caused EC to skip
        if (isWoodFrom("bewitchment", "betternether|betterend", "", "")) return false;

        // Create's windows will be skipped blc [Let's do] Blooming Nature & Meadow already has windows
        if (isWoodFrom("", "bloomingnature|meadow", "", "window")) return false;

        // ArchitectPalette's boards will be skipped blc Upgrade-Aqautic already has boards but have no recipes & no item in CreativeMode
        if (isWoodFrom("architects_palette", "upgrade_aquatic", "", "driftwood_boards|river_boards")) return false;

        // Similar to above, Architect's Palette - boards will be skipped due to the existing boards in Autumnity
        if (isWoodFrom("architects_palette", "autumnity", "", "maple_boards")) return false;

        // Ensure blocks to be generated because TerraFirmaCraft has similar name of vanilla woodType (oak, acacia, so on)
        if (isWoodFrom("", "tfc", "", "")) return false;

        //ecologics and quark azalea. tbh not sure why needed
        if (isWoodFrom("quark", "", "ecologics:azalea", "")) return false;

        // we always register everything for these (mehvahdjukaar)
        if (isWoodFrom("woodworks", "architects_palette", "", "")) return false;

        // Ensure the Architects-Palette's boards are generated with Abnormal mods (Upgrade Aquatic, Woodworks)
        if (isWoodFrom("architects_palette", "upgrade_aquatic|autumnity|atmospheric|environmental|caverns_and_chasms", "", "")) return false;

        return null;
    }

    @Nullable
    public static Boolean isLeavesBlockAlreadyRegistered(String entrySetId, String blockName, LeavesType leavesType, String supportedModId) {
        leavesTypeFromMod = leavesType.getNamespace();
        leavesidentify = leavesType.getId().toString();
        supportedMod = supportedModId;
        supportedBlockName = blockName;

        /// ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━ EXCLUDE ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

        // Exclude one LeavesType from a Wood mod
        if (LEAVES_TYPES_BLACKLIST.get().stream().anyMatch(leavesidentify::matches)) return true;

        // Exclude one EntrySet from a module
        if (ENTRY_SETS_BLACKLIST.get().stream().anyMatch(entrySetId::matches)) return true;

        // Exclude all of Vanilla Types
        if (leavesType.isVanilla()) return true;

        // Traversable-Leaves' leaves is a testing item and should be excluded
        if (isLeavesFrom("", "", "traversable_leaves:dev_leaves", "")) return true;

        /// ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━ INCLUDE ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
        // Unrelated to Quark's ancient_leaves & Alex's Cave (ancient_leaves) should be included
        if (isLeavesFrom("quark", "", "alexscaves:ancient", "")) return false;

        // Macaw's Fences&Walls or MrCrayFish's Furniture - hedges will be skipped because Quark already has hedges
        if (isLeavesFrom("mcwfences|cfm", "quark", "", "")) return false;

        return null;
    }

    /**
     * @param SupportedModId Id of Supported Mods That EveryCompat is supporting - Can be one or more Ids
     * @param woodtypeFromMod Id of mod that WoodType is from - Can be one or more Ids
     * @param woodTypeId id of WoodType, ex: "biomesoplenty:redwood" or "biomesoplenty:(redwood|fir|blackwood)"
     * @param supportedBlockId Id of block: "chest" OR id with name of woodType: "redwood_chest"
     */
    public static Boolean isWoodFrom(String SupportedModId, String woodtypeFromMod, String woodTypeId,
                                     String supportedBlockId) {

        String[] expressions = {
                SupportedModId,
                woodtypeFromMod,
                woodTypeId,
                supportedBlockId
        };

        String[] values = {
                supportedMod,
                woodTypeFromMod,
                woodidentify,
                supportedBlockName
        };

        for (int idx = 0; idx < values.length; idx++) {

            if (!expressions[idx].isEmpty()) { // Skip the blank expressions
                boolean mismatched = !(values[idx].matches(expressions[idx]) || values[idx].contains(expressions[idx]));

                // If first expression is mismatched, then...
                if (mismatched) return false;
            }
        }

        // Matched
        return true;
    }


    /**
     * @param whichSupportedModId - Id of Supported Mods That EveryCompat is supporting
     * @param leavestypeFromMod - Id of mod that LeavesType is from
     * @param leavesTypeId - id of LeavesType, ex: "biomesoplenty:redwood"
     * @param supportedBlockId - Id of block: "hedge" OR id with name of leavesType: "redwood_hedge"
     */
    public static Boolean isLeavesFrom(String whichSupportedModId, String leavestypeFromMod, String leavesTypeId,
                                       String supportedBlockId) {

        String[] expressions = {
                whichSupportedModId,
                leavestypeFromMod,
                leavesTypeId,
                supportedBlockId
        };

        String[] values = {
                supportedMod,
                leavesTypeFromMod,
                leavesidentify,
                supportedBlockName
        };

        for (int idx = 0; idx < values.length; idx++) {

            if (!expressions[idx].isEmpty()) { // Skip the blank expressions
                boolean mismatched = !(values[idx].matches(expressions[idx]) || values[idx].contains(expressions[idx]));

                // If first expression is mismatched, then...
                if (mismatched) return false;
            }
        }

        // Matched
        return true;
    }


    //for mods that might add in vanilla namespace
    public static boolean isKnownVanillaWood(WoodType woodType){
        var id = woodType.getId();
        if (id.getNamespace().equals("minecraft")) {
            return VANILLA_WOODS.contains(id.getPath());
        }
        return false;
    }

    private static final Set<String> VANILLA_WOODS = Set.of(
            "oak", "spruce", "birch", "jungle", "acacia", "dark_oak", "mangrove", "cherry", "bamboo", "crimson", "warped"
    );

}
