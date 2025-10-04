package net.mehvahdjukaar.every_compat;

import net.mehvahdjukaar.every_compat.api.CompatModule;
import net.mehvahdjukaar.every_compat.modules.another_furniture.AnotherFurnitureModule;
import net.mehvahdjukaar.every_compat.modules.architect_palette.ArchitectsPaletteModule;
import net.mehvahdjukaar.every_compat.modules.beautiful_campfires.BeautifulCampfiresModule;
import net.mehvahdjukaar.every_compat.modules.blockus.BlockusModule;
import net.mehvahdjukaar.every_compat.modules.camp_chair.CampChairModule;
import net.mehvahdjukaar.every_compat.modules.chipped.ChippedDoorModule;
import net.mehvahdjukaar.every_compat.modules.chipped.ChippedGlassModule;
import net.mehvahdjukaar.every_compat.modules.chipped.ChippedLogModule;
import net.mehvahdjukaar.every_compat.modules.chipped.ChippedMainModule;
import net.mehvahdjukaar.every_compat.modules.dawn_of_time.DawnOfTimeModule;
import net.mehvahdjukaar.every_compat.modules.decorative_blocks.DecorativeBlocksModule;
import net.mehvahdjukaar.every_compat.modules.exlines.BarkCarpetsModule;
import net.mehvahdjukaar.every_compat.modules.farmersdelight.FarmersDelightModule;
import net.mehvahdjukaar.every_compat.modules.friendsandfoes.FriendsAndFoesModule;
import net.mehvahdjukaar.every_compat.modules.furnish.FurnishModule;
import net.mehvahdjukaar.every_compat.modules.handcrafted.HandcraftedModule;
import net.mehvahdjukaar.every_compat.modules.hearth_and_home.HearthAndHomeModule;
import net.mehvahdjukaar.every_compat.modules.lieonlion.MoreChestVariantsModule;
import net.mehvahdjukaar.every_compat.modules.lieonlion.MoreCraftingTablesModule;
import net.mehvahdjukaar.every_compat.modules.missing_wilds.MissingWildModule;
import net.mehvahdjukaar.every_compat.modules.more_beautiful_torches.MoreBeautifulTorches;
import net.mehvahdjukaar.every_compat.modules.mrcrayfish.BackpackedModule;
import net.mehvahdjukaar.every_compat.modules.mrcrayfish.RefurbishedFurnitureModule;
import net.mehvahdjukaar.every_compat.modules.quark.QuarkModule;
import net.mehvahdjukaar.every_compat.modules.storagedrawers.StorageDrawersModule;
import net.mehvahdjukaar.every_compat.modules.stylish_stiles.StylishStilesModule;
import net.mehvahdjukaar.every_compat.modules.table_top_craft.TableTopCraftModule;
import net.mehvahdjukaar.every_compat.modules.twigs.TwigsModule;
import net.mehvahdjukaar.every_compat.modules.valhelsia_furniture.ValhelsiaFurnitureModule;
import net.mehvahdjukaar.every_compat.modules.variants.VariantVanillaBlocksModule;
import net.mehvahdjukaar.every_compat.modules.villagers_plus.VillagersPlusModule;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;

import java.util.List;

import static net.mehvahdjukaar.every_compat.EcProxy.addMultipleOptional;
import static net.mehvahdjukaar.every_compat.EcProxy.addOptional;
import static net.mehvahdjukaar.every_compat.EveryCompat.forAllModules;
import static net.mehvahdjukaar.every_compat.api.EveryCompatAPI.addOtherCompatMod;
import static net.mehvahdjukaar.every_compat.configs.UnsafeDisablerConfigs.INCLUDE_ALL_WOOD_MODULES;

public class EveryCompatCommon {

    protected void initialize() {
        EveryCompat.init();

        this.addModules();

        EveryCompat.forAllModules(CompatModule::onModInit);

        if (PlatHelper.getPhysicalSide().isClient()) {
            EveryCompatClient.init();
        }
    }

    protected void addModules() {

        if (INCLUDE_ALL_WOOD_MODULES.get()) {

//!! =============================================== Add Other Compat Mods ========================================== \\
            addOtherCompatMod("compatoplenty", "biomesoplenty",
                    "twigs", "farmersdelight", "quark", "woodworks", "boatload");
            addOtherCompatMod("compat_makeover", "biomemakeover",
                    "habitat", "farmersdelight", "quark", "decorative_blocks");
            addOtherCompatMod("decorative_compat", "biomesoplenty", "decorative_blocks");
            addOtherCompatMod("storagedrawersunlimited", "biomesoplenty", "storagedrawers");
            addOtherCompatMod("lolmcvbop", "biomesoplenty", "lolmcv");
            addOtherCompatMod("lolmcvbmo", "biomemakeover", "lolmcv");
            addOtherCompatMod("arts_and_crafts_compat","arts_and_crafts",
                    "twigs", "decorative_blocks", "farmersdelight", "dramaticdoors");

            addOtherCompatMod("ascended_quark", List.of("aether", "deep_aether"), "quark");

            // Farmer's Delight
            addOtherCompatMod("natures_delight", "natures_spirit", "farmersdelight");
            addOtherCompatMod("undergardendelight", "undergarden", "farmersdelight");

            // Macaw's Addon
            addOtherCompatMod("macawsbridgesbop", "biomesoplenty", "mcwbridges");
            addOtherCompatMod("macawbridgesbyg", "biomeswevegone", "mcwbridges");
            addOtherCompatMod("mcwfencesbop", "biomesoplenty", "mcwfences");
            addOtherCompatMod("mcwfencesbyg", "biomeswevegone", "mcwfences");
            addOtherCompatMod("macawsroofsbop", "biomesoplenty", "mcwroofs");
            addOtherCompatMod("macawsroofsbyg", "biomeswevegone", "mcwroofs");
            addOtherCompatMod("mcwbyg", List.of("biomeswevegone", "byg"),
                    "mcwbridges", "mcwroofs", "mcwfences", "mcwfurnitures", "mcwstairs", "mcwdoors", "mcwtrpdoors", "mcwpaths", "mcwwindows");
            addOtherCompatMod("mcwbiomesoplenty", "biomesoplenty",
                    "mcwbridges", "mcwroofs", "mcwfences", "mcwfurnitures", "mcwstairs", "mcwdoors", "mcwtrpdoors", "mcwpaths", "mcwwindows");
            addOtherCompatMod("mcwmoddinglegacy", List.of("blue_skies", "premium_wood"),
                    "mcwbridges", "mcwroofs", "mcwfences", "mcwfurnitures", "mcwstairs", "mcwdoors", "mcwtrpdoors", "mcwpaths", "mcwwindows");
            addOtherCompatMod("mcwabnormals", List.of("buzzier_bees", "environmental", "upgrade_aquatic", "autumnity", "endergetic", "atmospheric", "caverns_and_chasms"),
                    "mcwbridges", "mcwroofs", "mcwfences", "mcwfurnitures", "mcwstairs", "mcwdoors", "mcwtrpdoors", "mcwpaths", "mcwwindows");
            addOtherCompatMod("mcwterraformersmc", List.of("terrestria", "traverse", "cinderscape"),
                    "mcwbridges", "mcwroofs", "mcwfences", "mcwfurnitures", "mcwstairs", "mcwdoors", "mcwtrpdoors", "mcwpaths", "mcwwindows");
            addOtherCompatMod("mcwaurora", "enhanced_mushrooms",
                    "mcwbridges", "mcwroofs", "mcwfences", "mcwfurnitures", "mcwstairs", "mcwdoors", "mcwtrpdoors", "mcwpaths", "mcwwindows");
            addOtherCompatMod("mcwquark", "quark",
                    "mcwbridges", "mcwroofs", "mcwfences", "mcwfurnitures", "mcwstairs", "mcwdoors", "mcwtrpdoors", "mcwpaths", "mcwwindows");

            // Abnormals Delight
            addOtherCompatMod("abnormals_delight",
                    List.of("autumnity", "upgrade_aquatic", "environmental", "atmospheric", "endergetic", "caverns_and_chasms"),
                    "farmersdelight");

    //!! =========================================== Add Modules ==================================================== \\
            addOptional("architects_palette", () -> ArchitectsPaletteModule.class);
            addOptional("another_furniture", () -> AnotherFurnitureModule.class);
            addOptional("backpacked", () -> BackpackedModule.class);
            addOptional("barkcarpets", () -> BarkCarpetsModule.class); // Exline's
            addOptional("beautifulcampfires", () -> BeautifulCampfiresModule.class);
            addOptional("blockus", () -> BlockusModule.class);
            addOptional("campchair", () -> CampChairModule.class);
            addMultipleOptional("chipped", () -> ChippedMainModule.class, () -> ChippedLogModule.class, () -> ChippedDoorModule.class, () -> ChippedGlassModule.class);
            addOptional("dawnoftimebuilder", () -> DawnOfTimeModule.class);
            addOptional("decorative_blocks", () -> DecorativeBlocksModule.class);
            addOptional("farmersdelight", () -> FarmersDelightModule.class);
            addOptional("friendsandfoes", () -> FriendsAndFoesModule.class);
            addOptional("furnish", () -> FurnishModule.class);
            addOptional("handcrafted", () -> HandcraftedModule.class);
            addOptional("hearth_and_home", () -> HearthAndHomeModule.class);
            addOptional("lolmct", () -> MoreCraftingTablesModule.class);
            addOptional("lolmcv", () -> MoreChestVariantsModule.class);
            addOptional("missingwilds", () -> MissingWildModule.class);
            addOptional("more_beautiful_torches", () -> MoreBeautifulTorches.class);
            addOptional("quark", () -> QuarkModule.class);
            addOptional("refurbished_furniture", () -> RefurbishedFurnitureModule.class);
            addOptional("storagedrawers", () -> StorageDrawersModule.class);
            addOptional("stylishstiles", () -> StylishStilesModule.class);
            addOptional("table_top_craft", () -> TableTopCraftModule.class);
            addOptional("twigs", () -> TwigsModule.class);
            addOptional("valhelsia_furniture", () -> ValhelsiaFurnitureModule.class);
            addOptional("variantvanillablocks", () -> VariantVanillaBlocksModule.class);
            addOptional("villagersplus", () -> VillagersPlusModule.class);

//!! =================================================== OTHERS ===================================================== \\
            forAllModules(m -> EveryCompat.LOGGER.info("Loaded {}", m.toString()));
        }
    }
}
