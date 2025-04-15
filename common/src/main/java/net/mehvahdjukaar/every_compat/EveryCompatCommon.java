package net.mehvahdjukaar.every_compat;

import net.mehvahdjukaar.every_compat.api.CompatModule;
import net.mehvahdjukaar.every_compat.modules.another_furniture.AnotherFurnitureModule;
import net.mehvahdjukaar.every_compat.modules.beautiful_campfires.BeautifulCampfiresModule;
import net.mehvahdjukaar.every_compat.modules.camp_chair.CampChairModule;
import net.mehvahdjukaar.every_compat.modules.chipped.ChippedModule;
import net.mehvahdjukaar.every_compat.modules.dawn_of_time.DawnOfTimeModule;
import net.mehvahdjukaar.every_compat.modules.decorative_blocks.DecorativeBlocksModule;
import net.mehvahdjukaar.every_compat.modules.excessive_building.ExcessiveBuildingModule;
import net.mehvahdjukaar.every_compat.modules.exlines.BarkCarpetsModule;
import net.mehvahdjukaar.every_compat.modules.farmersdelight.FarmersDelightModule;
import net.mehvahdjukaar.every_compat.modules.friendsandfoes.FriendsAndFoesModule;
import net.mehvahdjukaar.every_compat.modules.furnish.FurnishModule;
import net.mehvahdjukaar.every_compat.modules.handcrafted.HandcraftedModule;
import net.mehvahdjukaar.every_compat.modules.hearth_and_home.HearthAndHomeModule;
import net.mehvahdjukaar.every_compat.modules.lieonlion.MoreCraftingTablesModule;
import net.mehvahdjukaar.every_compat.modules.more_beautiful_torches.MoreBeautifulTorches;
import net.mehvahdjukaar.every_compat.modules.mrcrayfish.BackpackedModule;
import net.mehvahdjukaar.every_compat.modules.quark.QuarkModule;
import net.mehvahdjukaar.every_compat.modules.storagedrawers.StorageDrawersModule;
import net.mehvahdjukaar.every_compat.modules.stylish_stiles.StylishStilesModule;
import net.mehvahdjukaar.every_compat.modules.table_top_craft.TableTopCraftModule;
import net.mehvahdjukaar.every_compat.modules.twigs.TwigsModule;
import net.mehvahdjukaar.every_compat.modules.valhelsia_furniture.ValhelsiaFurnitureModule;
import net.mehvahdjukaar.every_compat.modules.villagers_plus.VillagersPlusModule;
import net.mehvahdjukaar.every_compat.modules.wilder_wild.WilderWildModule;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;

import java.util.List;

import static net.mehvahdjukaar.every_compat.EveryCompat.*;

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

//!! ============================================ Add Other Compat Mods ============================================= \\
        addOtherCompatMod("compatoplenty", "biomesoplenty", List.of("twigs", "farmersdelight", "quark", "woodworks"));
        addOtherCompatMod("compat_makeover", "biomemakeover", List.of("habitat", "farmersdelight", "quark", "decorative_blocks"));
        addOtherCompatMod("decorative_compat", "biomesoplenty", List.of("decorative_blocks"));
        addOtherCompatMod("storagedrawersunlimited", "biomesoplenty", List.of("storagedrawers"));
        addOtherCompatMod("lolmcvbop", "biomesoplenty", List.of("lolmcv"));
        addOtherCompatMod("lolmcvbmo", "biomemakeover", List.of("lolmcv"));
        addOtherCompatMod("natures_delight", "natures_spirit", List.of("farmersdelight"));
        addOtherCompatMod("arts_and_crafts_compat", "arts_and_crafts", List.of("twigs", "decorative_blocks", "farmersdelight", "dramaticdoors"));

        // Macaw's Addon
        addOtherCompatMod("macawsbridgesbop", "biomesoplenty", List.of("mcwbridges"));
        addOtherCompatMod("macawbridgesbyg", "biomeswevegone", List.of("mcwbridges"));
        addOtherCompatMod("mcwfencesbop", "biomesoplenty", List.of("mcwfences"));
        addOtherCompatMod("mcwfencesbyg", "biomeswevegone", List.of("mcwfences"));
        addOtherCompatMod("macawsroofsbop", "biomesoplenty", List.of("mcwroofs"));
        addOtherCompatMod("macawsroofsbyg", "biomeswevegone", List.of("mcwroofs"));

        // Abnormals Delight
        addOtherCompatMod("abnormals_delight", List.of("autumnity", "upgrade_aquatic",
                "environmental", "atmospheric", "endergetic", "caves_and_chasms"), List.of("farmersdelight"));

//!! =============================================== Add Modules ==================================================== \\
        addIfLoaded("another_furniture", () -> AnotherFurnitureModule::new);
        addIfLoaded("backpacked", () -> BackpackedModule::new);
        addIfLoaded("barkcarpets", () -> BarkCarpetsModule::new); // Exline's
        addIfLoaded("bc", () -> BeautifulCampfiresModule::new);
        addIfLoaded("campchair", () -> CampChairModule::new);
        addIfLoaded("chipped", () -> ChippedModule::new);
        addIfLoaded("dawnoftimebuilder", () -> DawnOfTimeModule::new);
        addIfLoaded("decorative_blocks", () -> DecorativeBlocksModule::new);
        addIfLoaded("excessive_building", () -> ExcessiveBuildingModule::new);
        addIfLoaded("farmersdelight", () -> FarmersDelightModule::new);
        addIfLoaded("friendsandfoes", () -> FriendsAndFoesModule::new);
        addIfLoaded("furnish", () -> FurnishModule::new);
        addIfLoaded("handcrafted", () -> HandcraftedModule::new);
        addIfLoaded("hearth_and_home", () -> HearthAndHomeModule::new);
        addIfLoaded("more_beautiful_torches", () -> MoreBeautifulTorches::new);
        addIfLoaded("lolmct", () -> MoreCraftingTablesModule::new);
        addIfLoaded("quark", () -> QuarkModule::new);
        addIfLoaded("storagedrawers", () -> StorageDrawersModule::new);
        addIfLoaded("stylishstiles", () -> StylishStilesModule::new);
        addIfLoaded("table_top_craft", () -> TableTopCraftModule::new);
        addIfLoaded("twigs", () -> TwigsModule::new);
        addIfLoaded("valhelsia_furniture", () -> ValhelsiaFurnitureModule::new);
        addIfLoaded("villagersplus", () -> VillagersPlusModule::new);
        addIfLoaded("wilderwild", () -> WilderWildModule::new);


//!! =================================================== OTHERS ===================================================== \\
        forAllModules(m -> EveryCompat.LOGGER.info("Loaded {}", m.toString()));
    }
}
