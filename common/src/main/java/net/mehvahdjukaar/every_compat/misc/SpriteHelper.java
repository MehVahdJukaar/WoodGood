package net.mehvahdjukaar.every_compat.misc;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.moonlight.api.client.TextureCache;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.textures.Respriter;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;
import java.util.function.Supplier;

// Used to identify textures "types" only based off their name.
// feed into "findFirstBlockTextureLocation()"
public class SpriteHelper {

    public static final @NotNull Predicate<String> LOOKS_LIKE_TOP_LOG_TEXTURE = (s) -> {
        s = (new ResourceLocation(s)).getPath();
        if (s.contains("_overlay")) return false;
        return s.contains("_top") || s.contains("_end") || s.contains("_up");
    };
    public static final @NotNull Predicate<String> LOOKS_LIKE_SIDE_LOG_TEXTURE = (s) -> {
        s = (new ResourceLocation(s)).getPath();
        return (!LOOKS_LIKE_TOP_LOG_TEXTURE.test(s) && !(s.contains("_overlay") && !(s.contains("_leaves"))));
    };
    public static final @NotNull Predicate<String> LOOKS_LIKE_LEAF_TEXTURE = (s) -> {
        s = (new ResourceLocation(s)).getPath();
        return !s.contains("_top") && !s.contains("_bushy") && !s.contains("_snow") && !s.contains("_overlay") && !s.contains("/snow");
    };

    public static void addHardcodedSprites() {
        // Minecraft
        TextureCache.registerSpecialTextureForBlock(Blocks.CACTUS,"cactus_log", EveryCompat.res("block/cactus_side"));
        TextureCache.registerSpecialTextureForBlock(Blocks.CACTUS,"cactus_log_top", EveryCompat.res("block/cactus_top"));
//            TextureCache.registerSpecialTextureForBlock(Blocks.CACTUS"stripped_cactus_log", res("block/stripped_cactus_side"));
//            TextureCache.registerSpecialTextureForBlock(Blocks.CACTUS"stripped_cactus_log_top", res("block/stripped_cactus_top"));
        addOptional("minecraft:mushroom_stem","_side","minecraft:block/mushroom_stem");
        addOptional("minecraft:mushroom_stem","_top","minecraft:block/mushroom_stem");

        // Promenade
        addOptional("promenade:sakura_log","_side","promenade:block/sakura/log/side");
        addOptional("promenade:sakura_log","_top","promenade:block/sakura/log/top");
        addOptional("promenade:stripped_sakura_log","_side","promenade:block/sakura/stripped_log/side");
        addOptional("promenade:stripped_sakura_log","_top","promenade:block/sakura/stripped_log/top");

        addOptional("promenade:dark_amaranth_log","_side","promenade:block/dark_amaranth/log/side");
        addOptional("promenade:dark_amaranth_log","_top","promenade:block/dark_amaranth/log/top");
        addOptional("promenade:stripped_dark_amaranth_log","_side","promenade:block/dark_amaranth/stripped_log/side");
        addOptional("promenade:stripped_dark_amaranth_log","_top","promenade:block/dark_amaranth/stripped_log/top");

        addOptional("promenade:palm_log","_side","promenade:block/palm/log/side");
        addOptional("promenade:palm_log","_top","promenade:block/palm/log/top");
        addOptional("promenade:stripped_palm_log","_side","promenade:block/palm/stripped_log/side");
        addOptional("promenade:stripped_palm_log","_top","promenade:block/palm/stripped_log/top");

        addOptional("promenade:maple_log","_side","promenade:block/maple/log/side");
        addOptional("promenade:maple_log","_top","promenade:block/maple/log/top");
        addOptional("promenade:stripped_maple_log","_side","promenade:block/maple/stripped_log/side");
        addOptional("promenade:stripped_maple_log","_top","promenade:block/maple/stripped_log/top");

        addOptional("promenade:sakura_planks","_side","promenade:block/sakura/planks");
        addOptional("promenade:dark_amaranth_planks","_side","promenade:block/dark_amaranth/planks");
        addOptional("promenade:palm_planks","_side","promenade:block/palm/planks");
        addOptional("promenade:maple_planks","_side","promenade:block/maple/planks");

        // Simple Mango
        addOptional("simplemango:mango_log","_top","simplemango:block/mango_log_horizontal");

        // Rainbow Oaks Renewed
        addOptional("rainbowoaks:rainbow_leaves","_leaves","minecraft:block/oak_leaves");

        // Cultural Delights
            // Leaves
        addOptional("culturaldelights:fruiting_avocado_leaves","_leaves","culturaldelights:block/fruiting_avocado_leaves_0");

        // Meadow
            // Leaves
        addOptional("meadow:pine_leaves","_leaves","meadow:block/pine_leaves_1");

        // Autumnity
            // Leaves
        addOptional("autumnity:red_maple_leaves","_leaves","autumnity:block/maple_leaves");
        addOptional("autumnity:orange_maple_leaves","_leaves","autumnity:block/maple_leaves");
        addOptional("autumnity:yellow_maple_leaves","_leaves","autumnity:block/maple_leaves");

        // Blue Skies
            // Leaves
        addOptional("blue_skies:comet_leaves","_leaves","blue_skies:block/leaves/comet_leaves_grown");

        // Aether Redux
            // Leaves
        addOptional("aether_redux:azure_fieldsproot_leaves","_leaves","aether_redux:block/natural/fieldsproot_leaves");

        // Integrated Dynamics
            // Leaves
        addOptional("integrateddynamics:menril_leaves","_leaves","integrateddynamics:block/menril_leaves_fancy");

        // Malum
            // Leaves
        addOptional("malum:budding_soulwood_leaves","_leaves","malum:block/soulwood/budding_soulwood_leaves");
        addOptional("malum:azure_runewood_leaves","_leaves","malum:block/runewood/azure_runewood_leaves");

        // Oh The Biomes We've Gone
            // Leaves
        addOptional("biomeswevegone:aspen_leaves","_leaves","biomeswevegone:block/aspen/leaves");
        addOptional("biomeswevegone:baobab_leaves","_leaves","biomeswevegone:block/baobab/leaves");
        addOptional("biomeswevegone:blue_enchanted_leaves","_leaves","biomeswevegone:block/blue_enchanted/leaves");
        addOptional("biomeswevegone:cika_leaves","_leaves","biomeswevegone:block/cika/leaves");
        addOptional("biomeswevegone:cypress_leaves","_leaves","biomeswevegone:block/cypress/leaves");
        addOptional("biomeswevegone:ebony_leaves","_leaves","biomeswevegone:block/ebony/leaves");
        addOptional("biomeswevegone:fir_leaves","_leaves","biomeswevegone:block/fir/leaves");
        addOptional("biomeswevegone:green_enchanted_leaves","_leaves","biomeswevegone:block/green_enchanted/leaves");
        addOptional("biomeswevegone:holly_leaves","_leaves","biomeswevegone:block/holly/leaves");
        addOptional("biomeswevegone:ironwood_leaves","_leaves","biomeswevegone:block/ironwood/leaves");
        addOptional("biomeswevegone:jacaranda_leaves","_leaves","biomeswevegone:block/jacaranda/leaves");
        addOptional("biomeswevegone:mahongany_leaves","_leaves","biomeswevegone:block/mahongany/leaves");
        addOptional("biomeswevegone:maple_leaves","_leaves","biomeswevegone:block/maple/leaves");
        addOptional("biomeswevegone:palm_leaves","_leaves","biomeswevegone:block/palm/leaves");
        addOptional("biomeswevegone:palo_verde_leaves","_leaves","biomeswevegone:block/palo_verde/leaves");
        addOptional("biomeswevegone:pine_leaves","_leaves","biomeswevegone:block/pine/leaves");
        addOptional("biomeswevegone:rainbow_eucalyptus_leaves","_leaves","biomeswevegone:block/rainbow_eucalyptus/leaves");
        addOptional("biomeswevegone:redwood_leaves","_leaves","biomeswevegone:block/redwood/leaves");
        addOptional("biomeswevegone:skyris_leaves","_leaves","biomeswevegone:block/skyris/leaves");
        addOptional("biomeswevegone:white_mangrove_leaves","_leaves","biomeswevegone:block/white_mangrove/leaves");
        addOptional("biomeswevegone:willow_leaves","_leaves","biomeswevegone:block/willow/leaves");
        addOptional("biomeswevegone:witch_hazel_leaves","_leaves","biomeswevegone:block/witch_hazel/leaves");
        addOptional("biomeswevegone:zelkova_leaves","_leaves","biomeswevegone:block/zelkova/leaves");

        // Eternal Tales
        addOptional("eternal_tales:khagris_log","_side","eternal_tales:block/khagris_log_new");
        addOptional("eternal_tales:khagris_log","_top","eternal_tales:block/edemlogtop");
        addOptional("eternal_tales:stripped_khagris_log","_side","eternal_tales:block/khagris_log_stripped_new");
        addOptional("eternal_tales:stripped_khagris_log","_top","eternal_tales:block/khagris_log_stripped_top_new");
        addOptional("eternal_tales:purgatorium_log","_side","eternal_tales:block/purgatorium_log_new");
        addOptional("eternal_tales:purgatorium_log","_top","eternal_tales:block/purglogtop");
        addOptional("eternal_tales:purgatorium_stripped_log","_side","eternal_tales:block/purgatorium_stripped_log_new");
        addOptional("eternal_tales:purgatorium_stripped_log","_top","eternal_tales:block/purgatorium_log_stripped_top_new");
        addOptional("eternal_tales:petrified_log","_side","eternal_tales:block/petrifiedlogside");
        addOptional("eternal_tales:petrified_log","_top","eternal_tales:block/petrifiedlogtop");
        addOptional("eternal_tales:stripped_petrified_log","_side","eternal_tales:block/petrifiedlogsidestripped");
        addOptional("eternal_tales:stripped_petrified_log","_top","eternal_tales:block/petrifiedlogstrippedtop");
        addOptional("eternal_tales:mandarin_orange_log","_side","eternal_tales:block/mandarinlog");
        addOptional("eternal_tales:mandarin_orange_log","_top","eternal_tales:block/mandarinlogtop");
        addOptional("eternal_tales:comets_log","_side","eternal_tales:block/comets_log_new");
        addOptional("eternal_tales:comets_log","_top","eternal_tales:block/cometswoodtop");
        addOptional("eternal_tales:striped_comets_log","_side","eternal_tales:block/comets_log_stripped_new");
        addOptional("eternal_tales:striped_comets_log","_top","eternal_tales:block/comets_log_stripped_top_new");
        addOptional("eternal_tales:carved_pure_wood","_side","eternal_tales:block/pure_log_carved_side");
        addOptional("eternal_tales:carved_pure_wood","_top","eternal_tales:block/pure_log_carved_top");

        // Biomes O' Plenty
            // Leaves
        addOptional("biomesoplenty:null_leaves","_leaves","biomesoplenty:block/null_overlay");
        addOptional("biomesoplenty:snowblossom_leaves","_leaves","biomesoplenty:block/snowblossom_leaves");

        // Environmental
        addOptional("environmental:pink_wisteria_leaves","_leaves","environmental:block/pink_wisteria_leaves");
        addOptional("environmental:blue_wisteria_leaves","_leaves","environmental:block/blue_wisteria_leaves");
        addOptional("environmental:purple_wisteria_leaves","_leaves","environmental:block/purple_wisteria_leaves");
        addOptional("environmental:white_wisteria_leaves","_leaves","environmental:block/white_wisteria_leaves");

        // Dawn Of The Time: Builder Edition
            //REASON: the planks is actually being treated as a log instead of a planks
        addOptional("dawnoftimebuilder:waxed_oak_planks","_side","dawnoftimebuilder:block/waxed_oak_planks");
        addOptional("dawnoftimebuilder:waxed_oak_planks","_top","dawnoftimebuilder:block/waxed_oak_planks");
        addOptional("dawnoftimebuilder:charred_spruce_planks","_side","dawnoftimebuilder:block/charred_spruce_planks");
        addOptional("dawnoftimebuilder:charred_spruce_planks","_top","dawnoftimebuilder:block/charred_spruce_planks");

        // Shadowlands
        addOptional("shadowlands:vellium_log","_side","shadowlands:block/velliumlogside");
        addOptional("shadowlands:vellium_log","_top","shadowlands:block/log");
        addOptional("shadowlands:vellium_planks","_all","shadowlands:block/velliumplanks");
            // Leaves
        addOptional("shadowlands:vellium_leaves","_top","shadowlands:block/velliumleaves");

        // The Midnight
        addOptional("midnight:bogshroom_stem","_side","midnight:block/bogshroom_cap");
        addOptional("midnight:bogshroom_stem","_top","midnight:block/bogshroom_cap");
        addOptional("midnight:moonshroom_stem","_side","midnight:block/moonshroom_cap");
        addOptional("midnight:moonshroom_stem","_top","midnight:block/moonshroom_cap");
        addOptional("midnight:nightshroom_stem","_side","midnight:block/nightshroom_cap");
        addOptional("midnight:nightshroom_stem","_top","midnight:block/nightshroom_cap");
        addOptional("midnight:viridshroom_stem","_side","midnight:block/viridshroom_cap");
        addOptional("midnight:viridshroom_stem","_top","midnight:block/viridshroom_cap");
        addOptional("midnight:dewshroom_stem","_side","midnight:block/dewshroom_cap");
        addOptional("midnight:dewshroom_stem","_top","midnight:block/dewshroom_cap");

        // Advent Of Ascension
        addOptional("aoa3:stranglewood_log","_side","aoa3:block/stranglewood_log");
        addOptional("aoa3:stranglewood_log","_top","aoa3:block/stranglewood_log_top");

        // Better End
        addOptional("betterend:lucernia_leaves","_leaves","betterend:block/lucernia_leaves_1");

        // Better Nether
        addOptional("betternether:nether_mushroom","_side","betternether:block/nether_mushroom_stem_side");

        // My Nether's Delight
        addOptional("mynethersdelight:powdery_block","_side","mynethersdelight:block/powdery_block");
        addOptional("mynethersdelight:stripped_powdery_block","_side","mynethersdelight:block/stripped_powdery_block");

        // Piglin Ruins
        addOptional("piglin_ruins:ominous_stalk_block","_side","piglin_ruins:block/ominous_stalk_block_side");
        addOptional("piglin_ruins:ominous_stalk_block","_top","piglin_ruins:block/ominous_stalk_block_top");
        addOptional("piglin_ruins:stripped_ominous_stalk_block","_side","piglin_ruins:block/stripped_ominous_stalk_block_side");
        addOptional("piglin_ruins:stripped_ominous_stalk_block","_top","piglin_ruins:block/stripped_ominous_stalk_block_top");

        // Unusual End
        addOptional("unusualend:chorus_cane_block","_side","unusualend:block/chorus_cane_block_side");
        addOptional("unusualend:chorus_cane_block","_top","unusualend:block/chorus_cane_block_top");
        addOptional("unusualend:stripped_chorus_cane_block","_side","unusualend:block/stripped_chorus_cane_block_side");
        addOptional("unusualend:stripped_chorus_cane_block","_top","unusualend:block/stripped_chorus_cane_block_top");

        // Ad Astra
        addOptional("ad_astra:strophar_stem","_side","ad_astra:block/strophar_stem");
        addOptional("ad_astra:strophar_stem","_top","ad_astra:block/strophar_stem");
        addOptional("ad_astra:aeronos_stem","_side","ad_astra:block/aeronos_stem");
        addOptional("ad_astra:aeronos_stem","_top","ad_astra:block/aeronos_stem");

        // Terrestria
        addOptional("terrestria:sakura_log","_top","terrestria:block/sakura_log_section");
        addOptional("terrestria:yucca_palm_log","_top","terrestria:block/yucca_palm_log_section");

        // The Abyss: The Other Side
        addOptional("theabyss:rena_log","_top","theabyss:block/rena_log");
        addOptional("theabyss:stripped_rena_log","_top","theabyss:block/rena_log");
        addOptional("theabyss:luna_log","_top","theabyss:block/luna_log");
        addOptional("theabyss:stripped_luna_log","_top","theabyss:block/luna_log");

        // Dreamy Cottage
        addOptional("dreamy_cottage:strawberry_log","_side","dreamy_cottage:block/strawberrylogside");
        addOptional("dreamy_cottage:strawberry_log","_top","dreamy_cottage:block/strawberrylog");
        addOptional("dreamy_cottage:stripped_strawberry_log","_side","dreamy_cottage:block/strippedlogsidestrawberry");
        addOptional("dreamy_cottage:stripped_strawberry_log","_top","dreamy_cottage:block/strippedlogstrawberry");

        addOptional("dreamy_cottage:white_oak_log","_side","dreamy_cottage:block/untitled416_20240420160357_1");
        addOptional("dreamy_cottage:white_oak_log","_top","dreamy_cottage:block/untitled416_20240420160009_1");
        addOptional("dreamy_cottage:stripped_white_oak_log","_side","dreamy_cottage:block/strippedwhiteoakside");
        addOptional("dreamy_cottage:stripped_white_oak_log","_top","dreamy_cottage:block/strippedwhiteoak");

            // Leaves
        addOptional("dreamy_cottage:strawberry_leaves","_leaves","dreamy_cottage:block/whiteoakleaves");
        addOptional("dreamy_cottage:white_oak_leaves","_leaves","dreamy_cottage:block/whiteoakleaves");

        // Feywild
        addOptional("feywild:spring_tree_log","_side","feywild:block/spring_tree_wood");
        addOptional("feywild:spring_tree_log","_top","feywild:block/spring_tree_log");
        addOptional("feywild:summer_tree_log","_side","feywild:block/summer_tree_wood");
        addOptional("feywild:summer_tree_log","_top","feywild:block/summer_tree_log");
        addOptional("feywild:autumn_tree_log","_side","feywild:block/autumn_tree_wood");
        addOptional("feywild:autumn_tree_log","_top","feywild:block/autumn_tree_log");
        addOptional("feywild:winter_tree_log","_side","feywild:block/winter_tree_wood");
        addOptional("feywild:winter_tree_log","_top","feywild:block/winter_tree_log");
        addOptional("feywild:blossom_tree_log","_side","feywild:block/blossom_tree_wood");
        addOptional("feywild:blossom_tree_log","_top","feywild:block/blossom_tree_log");
        addOptional("feywild:hexen_tree_log","_side","feywild:block/hexen_tree_wood");
        addOptional("feywild:hexen_tree_log","_top","feywild:block/hexen_tree_log");


        // Born In Chaos
        addOptional("born_in_chaos_v1:scorched_log","_side","born_in_chaos_v1:block/brievno");
        addOptional("born_in_chaos_v1:scorched_log","_top","born_in_chaos_v1:block/brievnovierkh1");

        addOptional("born_in_chaos_v1:stripped_scorched_log","_side","born_in_chaos_v1:block/obdreve");
        addOptional("born_in_chaos_v1:stripped_scorched_log","_top","born_in_chaos_v1:block/obtes");

        addOptional("born_in_chaos_v1:scorched_planks","_planks","born_in_chaos_v1:block/opdosk");

        // Nether Update Expanded
        addOptional("nue:dragon_stem","_side","nue:block/dragonstem");
        addOptional("nue:dragon_stem","_top","nue:block/dragonstemtop");
        addOptional("nue:stripped_dragon_stem","_side","nue:block/strippeddragonstem");
        addOptional("nue:stripped_dragon_stem","_top","nue:block/strippeddragonstemtop");

        addOptional("nue:elder_stem","_side","nue:block/elderstem");
        addOptional("nue:elder_stem","_top","nue:block/elderstemtop");
        addOptional("nue:stripped_elder_stem","_side","nue:block/strippedelderstem");
        addOptional("nue:stripped_elder_stem","_top","nue:block/strippedelderstemtop");

        addOptional("nue:frosted_stem","_side","nue:block/frostedstem");
        addOptional("nue:frosted_stem","_top","nue:block/frostedstem2");
        addOptional("nue:stripped_frosted_stem","_side","nue:block/strippedfrozenstem");
        addOptional("nue:stripped_frosted_stem","_top","nue:block/strippedfrozenstemtop");

        // Fruitful Fun
            // Leaves
        addOptional("fruitfulfun:apple_leaves","_leaves","minecraft:block/oak_leaves");
        addOptional("fruitfulfun:cherry_leaves","_leaves","fruitfulfun:block/cherry_leaves_2");
        addOptional("fruitfulfun:citron_leaves","_leaves","fruitfulfun:block/citron_leaves");
        addOptional("fruitfulfun:grapefruit_leaves","_leaves","fruitfulfun:block/grapefruit_leaves");
        addOptional("fruitfulfun:lemon_leaves","_leaves","fruitfulfun:block/lemon_leaves");
        addOptional("fruitfulfun:lime_leaves","_leaves","fruitfulfun:block/lime_leaves");
        addOptional("fruitfulfun:orange_leaves","_leaves","fruitfulfun:block/orange_leaves");
        addOptional("fruitfulfun:pomegranate_leaves","_leaves","fruitfulfun:block/pomegranate_leaves");
        addOptional("fruitfulfun:pomelo_leaves","_leaves","fruitfulfun:block/pomelo_leaves");
        addOptional("fruitfulfun:redlove_leaves","_leaves","fruitfulfun:block/redlove_leaves");
        addOptional("fruitfulfun:tangerine_leaves","_leaves","fruitfulfun:block/tangerine_leaves");

        // Extended Mushrooms
        addOptional("extendedmushrooms:glowshroom_stem","_top","extendedmushrooms:block/glowshroom_stem");
        addOptional("extendedmushrooms:poisonous_mushroom_stem","_top","extendedmushrooms:block/poisonous_mushroom_stem");

        addOptional("extendedmushrooms:stripped_mushroom_stem","_side","extendedmushrooms:block/stripped_mushroom_stem");
        addOptional("extendedmushrooms:stripped_mushroom_stem","_top","extendedmushrooms:block/stripped_mushroom_stem");

        addOptional("extendedmushrooms:honey_fungus_stem","_side","extendedmushrooms:block/honey_fungus_stem");
        addOptional("extendedmushrooms:honey_fungus_stem","_top","extendedmushrooms:block/honey_fungus_stem");
        addOptional("extendedmushrooms:honey_fungus_stem_stripped","_side","extendedmushrooms:block/honey_fungus_stem_stripped");
        addOptional("extendedmushrooms:honey_fungus_stem_stripped","_top","extendedmushrooms:block/honey_fungus_stem_stripped");

        // Let's Do - Vinery
            // Leaves
        addOptional("vinery:apple_leaves","_leaves","vinery:block/apple_leaves_0");
        addOptional("vinery:dark_cherry","_leaves","vinery:block/dark_cherry_leaves");

        // The Twilight Forest
            // Leaves
        addOptional("twilightforest:beanstalk_leaves","_leaves","minecraft:block/azalea_leaves");
        addOptional("twilightforest:thorn_leaves","_leaves","minecraft:block/oak_leaves");

        // Regions Unexplored
        addOptional("regions_unexplored:eucalyptus_log","_side", EveryCompat.MOD_ID + ":block/regions_unexplored/eucalyptus_log");

            // Leaves
        addOptional("regions_unexplored:alpha_leaves","_leaves","regions_unexplored:block/alpha_oak_leaves");
        addOptional("regions_unexplored:apple_oak_leaves","_leaves","regions_unexplored:block/apple_oak_leaves_stage_0");
        addOptional("regions_unexplored:flowering_leaves","_leaves","regions_unexplored:item/flowering_leaves");
        addOptional("regions_unexplored:palm_leaves","_leaves","regions_unexplored:block/palm_leaves_side");
        addOptional("regions_unexplored:enchanted_birch_leaves","_leaves","regions_unexplored:item/enchanted_birch_leaves");
        addOptional("regions_unexplored:silver_birch_leaves","_leaves","regions_unexplored:item/silver_birch_leaves");
        addOptional("regions_unexplored:small_oak_leaves","_leaves","minecraft:block/oak_leaves");

        // Endless Biomes
        addOptional("endlessbiomes:twisted_stem","_side","endlessbiomes:block/twistedlogsidetest");
        addOptional("endlessbiomes:twisted_stem","_top","endlessbiomes:block/twistedlogtoptest");
        addOptional("endlessbiomes:stripped_twisted_stem","_side","endlessbiomes:block/twistedstrippedlogsidetest");
        addOptional("endlessbiomes:stripped_twisted_stem","_top","endlessbiomes:block/twistedstrippedlogtoptest");

        addOptional("endlessbiomes:penumbra_stem","_side","endlessbiomes:block/penumbrallogsidenewest");
        addOptional("endlessbiomes:penumbra_stem","_top","endlessbiomes:block/penumbrallogtopnewest");
        addOptional("endlessbiomes:stripped_penumbra_stem","_side","endlessbiomes:block/strippedpenumbralogsidenewest");
        addOptional("endlessbiomes:stripped_penumbra_stem","_top","endlessbiomes:block/strippedpenumbralogtopnewest");

        // Gardens Of The Dead
        addOptional("gardens_of_the_dead:whistlecane","_side","gardens_of_the_dead:block/whistlecane_block");
        addOptional("gardens_of_the_dead:whistlecane","_top","gardens_of_the_dead:block/whistlecane_block_top");
        addOptional("gardens_of_the_dead:stripped_soulblight_stem","_side","gardens_of_the_dead:block/stripped_soulblight_stem");
        addOptional("gardens_of_the_dead:stripped_soulblight_stem","_top","gardens_of_the_dead:block/stripped_soulblight_stem_top");

        // L_Ender 's Cataclysm
        addOptional("cataclysm:chorus_stem","_side","cataclysm:block/chorus_stem");
        addOptional("cataclysm:chorus_stem","_top","cataclysm:block/chorus_stem");

        // PFW Aesthetic Gems - Idk what happened because I disabled this and the texture files couldn't be found. Below is required to fix the missing textures
        addOptional("pfw_aesthetic_gems:ice_blue_topaz_log","_side","pfw_aesthetic_gems:block/ice_blue_topaz_log");
        addOptional("pfw_aesthetic_gems:ice_blue_topaz_log","_top","pfw_aesthetic_gems:block/ice_blue_topaz_log_top");
        addOptional("pfw_aesthetic_gems:stripped_ice_blue_topaz_log","_side","pfw_aesthetic_gems:block/stripped_ice_blue_topaz_log");
        addOptional("pfw_aesthetic_gems:stripped_ice_blue_topaz_log","_top","pfw_aesthetic_gems:block/stripped_ice_blue_topaz_log_top");

        addOptional("pfw_aesthetic_gems:pink_topaz_log","_side","pfw_aesthetic_gems:block/pink_topaz_log");
        addOptional("pfw_aesthetic_gems:pink_topaz_log","_top","pfw_aesthetic_gems:block/pink_topaz_log_top");
        addOptional("pfw_aesthetic_gems:stripped_pink_topaz_log","_side","pfw_aesthetic_gems:block/stripped_pink_topaz_log");
        addOptional("pfw_aesthetic_gems:stripped_pink_topaz_log","_top","pfw_aesthetic_gems:block/stripped_pink_topaz_log_top");

    }

    private static void addOptional(String blockId, String textureId, String texturePath) {
        BuiltInRegistries.BLOCK.getOptional(new ResourceLocation(blockId))
                .ifPresent(b -> TextureCache.registerSpecialTextureForBlock(b, textureId, new ResourceLocation(texturePath)));
    }

    //ugly hardcoded wood post-processing
    @Nullable
    public static Supplier<TextureImage> maybePostProcessWoodTexture(WoodType wood, String newId, ResourceManager manager, Supplier<TextureImage> textureSupplier) {
        // Ecologics
        if (wood.getNamespace().equals("ecologics")) {
            return () -> {
                var t = textureSupplier.get();
                maybeFlowerAzalea(t, manager, wood);
                return t;
            };
        }
        // Regions Unexplored
        else if (wood.getNamespace().equals("regions_unexplored")) {
            return () -> {
                var t = textureSupplier.get();
                maybeBrimwood(t, manager, newId, wood);
                return t;
            };
        }
        // Advent of Ascension
        else if (wood.getNamespace().equals("aoa3")) {
            return () -> {
                var t = textureSupplier.get();
                maybeStrangewood(t, manager, wood);
                return t;
            };
        }
        return null;
    }

    //for ecologics
    private static void maybeFlowerAzalea(TextureImage image, ResourceManager manager, WoodType woodType) {
        if (woodType.getId().toString().equals("ecologics:flowering_azalea")) {
            WoodType azalea = WoodTypeRegistry.getValue(new ResourceLocation("ecologics:azalea"));
            if (azalea != null) {
                try (TextureImage mask = TextureImage.open(manager,
                        EveryCompat.res("block/ecologics_overlay"));
                     TextureImage plankTexture = TextureImage.open(manager,
                             RPUtils.findFirstBlockTextureLocation(manager, azalea.planks))) {

                    Respriter respriter = Respriter.of(image);
                    var temp = respriter.recolorWithAnimationOf(plankTexture);

                    image.applyOverlayOnExisting(temp, mask);
                    temp.close();

                } catch (Exception e) {
                    EveryCompat.LOGGER.warn("failed to apply azalea overlay for wood type {} and image {}", woodType, image);
                }
            }
        }
    }

    //for Regions-Unexplored's brimwood
    private static void maybeBrimwood(TextureImage image, ResourceManager manager, String path, WoodType woodType) {
        if (woodType.getId().toString().equals("regions_unexplored:brimwood")) {
            WoodType brimwood = WoodTypeRegistry.getValue(new ResourceLocation("regions_unexplored:brimwood"));
            if (brimwood != null) {
                try (TextureImage lavaOverlay = TextureImage.open(manager,
                        EveryCompat.res("block/regions_unexplored/brimwood_planks_lava"));
                     TextureImage plankTexture = TextureImage.open(manager,
                             EveryCompat.res("block/regions_unexplored/brimwood_planks"));

                ) {
                    String type = path.substring(path.lastIndexOf("brimwood_") + 9);

                    Respriter respriter = switch (type) {
                        case "barrel_side" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_barrel_side_m")
                        ));
                        case "barrel_top" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_barrel_top_m")
                        ));
                        case "beehive_front_honey" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_beehive_front_honey_m")
                        ));
                        case "beehive_side" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_beehive_side_m")
                        ));
                        case "bookshelf" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_bookshelf_m")
                        ));
                        case "cartography_table_side1" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_cartography_table_side1_m")
                        ));
                        case "cartography_table_side2" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_cartography_table_side2_m")
                        ));
                        case "cartography_table_top" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_cartography_table_top_m")
                        ));
                        case "chiseled_bookshelf_occupied" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_chiseled_bookshelf_occupied_m")
                        ));
                        case "crafting_table_front" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_crafting_table_front_m")
                        ));
                        case "crafting_table_side" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_crafting_table_side_m")
                        ));
                        case "fletching_table_front" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_fletching_table_front_m")
                        ));
                        case "fletching_table_side" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_fletching_table_side_m")
                        ));
                        case "fletching_table_top" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_fletching_table_top_m")
                        ));
                        case "lectern_base" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_lectern_base_m")
                        ));
                        case "lectern_front" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_lectern_front_m")
                        ));
                        case "smithing_table_bottom" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_smithing_table_bottom_m")
                        ));
                        case "smithing_table_front" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_smithing_table_front_m")
                        ));
                        case "smithing_table_side" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_smithing_table_side_m")
                        ));
                        case "smoker_bottom" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_smoker_bottom_m")
                        ));
                        case "smoker_front" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_smoker_front_m")
                        ));
                        case "smoker_side" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_smoker_side_m")
                        ));
                        default -> Respriter.of(image);
                    };

                    var temp = respriter.recolorWithAnimationOf(plankTexture);

                    if (path.contains("stairs") || path.contains("planks") || path.contains("slab") ||
                            path.contains("beehive") || path.contains("composter_bottom") || path.contains("composter_side")
                            || path.contains("lectern_side") || path.contains("lectern_top") || path.contains("bookshelf_side")
                            || path.contains("bookshelf_top")
                    )
                        image.applyOverlayOnExisting(temp, lavaOverlay);
                    else
                        image.applyOverlayOnExisting(temp);

                    temp.close();

                } catch (Exception e) {
                    EveryCompat.LOGGER.error("Failed to process {}'s texture: {}", path, e);
                }
            }
        }
    }

    //for Advent-Of-Ascension's stranglewood
    private static void maybeStrangewood(TextureImage image, ResourceManager manager, WoodType woodType) {
        if (woodType.getId().toString().equals("aoa3:strangewood")) {
            WoodType strangewood = WoodTypeRegistry.getValue(new ResourceLocation("aoa3:strangewood"));
            if (strangewood != null) {
                try (TextureImage vineOverlay = TextureImage.open(manager,
                        new ResourceLocation("aoa3:block/stranglewood_log_vine"));
                     TextureImage logTexture = TextureImage.open(manager,
                             RPUtils.findFirstBlockTextureLocation(manager, strangewood.log, SpriteHelper.LOOKS_LIKE_SIDE_LOG_TEXTURE))) {

                    Respriter respriter = Respriter.of(image);
                    var temp = respriter.recolorWithAnimationOf(logTexture);

                    image.applyOverlayOnExisting(temp, vineOverlay);
                    temp.close();

                } catch (Exception e) {
                    EveryCompat.LOGGER.warn("Failed to apply vineOverlay for strangewood texture: {}", String.valueOf(e));
                }
            }
        }
    }

}
