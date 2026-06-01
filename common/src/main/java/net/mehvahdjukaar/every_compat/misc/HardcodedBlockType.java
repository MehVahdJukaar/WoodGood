package net.mehvahdjukaar.every_compat.misc;

import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import org.jetbrains.annotations.Nullable;

import java.util.List;
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

    public static final List<String> FRAMED_BLOCKS_SUFFIX = List.of(

            // Slabs
            "slab", "slab_edge", "slab_corner",
            "divided_slab", "adj_double_slab", "adj_double_copycat_slab",
            "centered_slab", "pyramid_slab", "checkered_slab",

            // Stairs
            "stairs", "double_stairs", "half_stairs",
            "divided_stairs", "double_half_stairs", "sliced_stairs_panel",
            "vertical_stairs", "vertical_double_stairs", "vertical_half_stairs",
            "vertical_divided_stairs", "vertical_double_half_stairs", "vertical_sliced_stairs",
            "sliced_stairs_slab", "vertical_sloped_stairs",

            // Walls
            "wall", "floor_board", "wall_board",

            // Fences & Gates
            "fence", "fence_gate", "gate", "iron_gate",

            // Panels
            "panel", "divided_panel_horizontal", "divided_panel_vertical", "centered_panel",

            // Pillars & Posts
            "pillar", "half_pillar", "post",
            "corner_pillar", "threeway_corner_pillar", "double_threeway_corner_pillar",

            // Buttons, Levers & Plates
            "pressure_plate", "large_button", "lever",

            // Torches
            "torch", "soul_torch", "redstone_torch",
            "wall_torch", "soul_wall_torch", "redstone_wall_torch",

            // Chests & Storage
            "chest", "secret_storage",

            // Misc blocks
            "cube", "bouncy_cube", "glowing_cube",
            "pyramid", "bookshelf", "chiseled_bookshelf",
            "flower_pot", "item_frame", "glowing_item_frame",
            "ladder", "bars", "pane",
            "horizontal_pane"
    );

    @Nullable
    public static Boolean isWoodBlockAlreadyRegistered(String entrySetId, String blockName, WoodType woodType, String supportedModId) {
        woodTypeFromMod = woodType.getNamespace();
        woodidentify = woodType.getId().toString();
        supportedMod = supportedModId;
        supportedBlockName = blockName;

        String blockId = supportedModId +"/"+ woodTypeFromMod +"/"+ blockName;

        /// ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━ SPECIAL EXCLUSION ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

        // Exclude blocks from a mod that are both Supported-Mod & Wood-Mod
        if (woodType.getNamespace().equals(supportedModId)) return false;

        // Exclude one WoodType from a Wood Mod
        if (WOOD_TYPES_BLACKLIST.get().stream().anyMatch(woodidentify::matches)) return true;

        // Exclude a specific WoodType Block
        if (BLOCKS_BLACKLIST.get().stream().anyMatch(blockId::matches)) return true;

        // Exclude one EntrySet from a module
        if (ENTRY_SETS_BLACKLIST.get().stream().anyMatch(entrySetId::matches)) return true;

        /// ─────────────────────────── Include Vanilla Type ────────────────────────────

        // Dawn-Of-Time's fancy-fence only has birch but no other vanilla variants
        if (isWoodFrom("dawnoftimebuilder", "", "minecraft:(oak|acacia|jungle|dark_oak|spruce|mangrove|cherry)", "fancy_fence")) return false;

        // Chipped's glass & glass_panes has no Vanilla WoodTypes except OAK
        if (isWoodFrom("chipped", "", "minecraft:(acacia|birch|jungle|dark_oak|spruce|mangrove|cherry)", "\\w+_glass(_pane)?")) return false;

        /// ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━ EXCLUDE ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

        // Exclude all of Vanilla Types that we know of. Excludes other mc namespaced added by mods
        if (isKnownVanillaWood(woodType)) return true;

        // Marvelous Menagerie Paradoxical's calamites_log is a 8x8 log and its texture won't work with supported-mod that directly use the textures
        if (isWoodFrom("mcwfences|mcwstairs", "", "marvelous_menagerie:calamites", "")) return true;
        if (isWoodFrom("quark", "", "marvelous_menagerie:calamites", "hollow_calamites_log")) return true;

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

        // Valhelsia-Structures' POST, STRIPPED_POST are not generated because Quark has POST and STRIPPED_POST
        if (isWoodFrom("valhelsia_structures", "quark", "", "")) return false;

        // MURUBLIGHT_SHELF from Enderscape is preventing SHELF from Copper-Age-Backport or Another-Furniture to be generated
        if (isWoodFrom("copperagebackport|another_furniture", "", "enderscape:murublight", "")) return false;

        // Curiosities' FANCIED_PLANKS is not getting generated with No-Man's-Land, PINE from Biomes-O'-Plenty & Windswept
        if (isWoodFrom("curiosities", "biomesoplenty|nomansland|windswept", "", "")) return false;

        // More-Crafting-Table-For-Forge supported Biomes-O'-Plenty's 3 WoodTypes: MAPLE, PINE, & WILLOW that prevented No-Man's-Land's 3 similar WoodTypes from generated
        if (isWoodFrom("mctb", "nomansland", "", "")) return false;

        // Minecraft's CHERRY prevent the generation of blocks with Terraqueous's CHERRY
        if (isWoodFrom("", "", "terraqueous:cherry", "")) return false;

        // Refurbished-Furniture's oak_table wasn't generated due to Dawn-Of-Time's waxed_oak_table
        if (isWoodFrom("refurbished_furniture", "", "dawnoftimebuilder:waxed_oak", "")) return false;

        // Minecraft has "mangrove" that caused the generation of blocks with The-Twilight-Forest's mangrove to be skipped.
        if (isWoodFrom("\\b(?!twilightforest).*", "", "twilightforest:mangrove", "")) return false;

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

        String blockId = supportedModId +"/"+ leavesTypeFromMod +"/"+ blockName;

        /// ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━ SPECIAL EXCLUSION ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

        // Exclude blocks from a mod that are both Supported-Mod & Wood-Mod
        if (leavesType.getNamespace().equals(supportedModId)) return false;

        // Exclude one LeavesType from a Wood mod
        if (LEAVES_TYPES_BLACKLIST.get().stream().anyMatch(leavesidentify::matches)) return true;

        // Exclude a specific LeavesType Block
        if (BLOCKS_BLACKLIST.get().stream().anyMatch(blockId::matches)) return true;

        // Exclude one EntrySet from a module
        if (ENTRY_SETS_BLACKLIST.get().stream().anyMatch(entrySetId::matches)) return true;

        /// ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━ EXCLUDE ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

        // Exclude all of Vanilla Types
        if (isKnownVanillaLeaves(leavesType)) return true;

        // Productive-Trees' CHERRY_PLUM shouldn't be supported
        // REASON: Productive Trees' CHERRY_PLUM & PLUM can caused crash & it's very tricky to fix, not worth it.
        if (isLeavesFrom("chipped", "", "productivetrees:cherry_plum", "")) return true;

        // Chipped's LeavesType and its supported Block shouldn't be generated
        if (isLeavesFrom("chipped", "chipped", "", "")) return true;

        // Traversable-Leaves' leaves is a testing item and should be excluded
        if (isLeavesFrom("", "", "traversable_leaves:dev_leaves", "")) return true;

        /// ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━ INCLUDE ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

        // Prevent Productive-Trees' CHERRY_PLUM_LEAVES from being added to PLUM's children
        if (isLeavesFrom("chipped", "", "productivetrees:plum", "cherry_plum_leaves")) return false;

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
     * @param whichSupportedModId Id of Supported Mods That EveryCompat is supporting
     * @param leavestypeFromMod Id of mod that LeavesType is from
     * @param leavesTypeId id of LeavesType, ex: "biomesoplenty:redwood"
     * @param supportedBlockId Id of block: "hedge" OR id with name of leavesType: "redwood_hedge"
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

    public static boolean isKnownVanillaLeaves(LeavesType leavesType){
        var id = leavesType.getId();
        if (id.getNamespace().equals("minecraft")) {
            return VANILLA_LEAVES.contains(id.getPath());
        }
        return false;
    }

    private static final Set<String> VANILLA_WOODS = Set.of(
            "oak", "spruce", "birch", "jungle", "acacia", "dark_oak", "mangrove", "cherry", "bamboo", "crimson", "warped"
    );

    private static final Set<String> VANILLA_LEAVES = Set.of(
            "oak", "spruce", "birch", "jungle", "acacia", "dark_oak", "mangrove", "cherry", "bamboo", "crimson", "warped", "azalea", "flowering_azalea"
    );

}
