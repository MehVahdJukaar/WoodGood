package net.mehvahdjukaar.every_compat;

import net.mehvahdjukaar.every_compat.api.CompatModule;
import net.mehvahdjukaar.every_compat.modules.another_furniture.AnotherFurnitureModule;
import net.mehvahdjukaar.every_compat.modules.beautiful_campfires.BeautifulCampfiresModule;
import net.mehvahdjukaar.every_compat.modules.camp_chair.CampChairModule;
import net.mehvahdjukaar.every_compat.modules.chipped.ChippedModule;
import net.mehvahdjukaar.every_compat.modules.create.CreateModule;
import net.mehvahdjukaar.every_compat.modules.dawn_of_time.DawnOfTimeModule;
import net.mehvahdjukaar.every_compat.modules.decorative_blocks.DecorativeBlocksModule;
import net.mehvahdjukaar.every_compat.modules.exlines.BarkCarpetsModule;
import net.mehvahdjukaar.every_compat.modules.farmersdelight.FarmersDelightModule;
import net.mehvahdjukaar.every_compat.modules.friendsandfoes.FriendsAndFoesModule;
import net.mehvahdjukaar.every_compat.modules.furnish.FurnishModule;
import net.mehvahdjukaar.every_compat.modules.handcrafted.HandcraftedModule;
import net.mehvahdjukaar.every_compat.modules.hearth_and_home.HearthAndHomeModule;
import net.mehvahdjukaar.every_compat.modules.missing_wilds.MissingWildModule;
import net.mehvahdjukaar.every_compat.modules.mrcrayfish.BackpackedModule;
import net.mehvahdjukaar.every_compat.modules.mrcrayfish.RefurbishedFurnitureModule;
import net.mehvahdjukaar.every_compat.modules.quark.QuarkModule;
import net.mehvahdjukaar.every_compat.modules.storagedrawers.StorageDrawersModule;
import net.mehvahdjukaar.every_compat.modules.stylish_stiles.StylishStilesModule;
import net.mehvahdjukaar.every_compat.modules.table_top_craft.TableTopCraftModule;
import net.mehvahdjukaar.every_compat.modules.twigs.TwigsModule;
import net.mehvahdjukaar.every_compat.modules.valhelsia_furniture.ValhelsiaFurnitureModule;
import net.mehvahdjukaar.every_compat.modules.villagers_plus.VillagersPlusModule;
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

//!! =============================================== Add Other Compat Mods ========================================== \\
        addOtherCompatMod("compatoplenty", "biomesoplenty",
                "twigs", "farmersdelight", "quark", "woodworks");
        addOtherCompatMod("compat_makeover", "biomemakeover",
                "habitat", "farmersdelight", "quark", "decorative_blocks");
        addOtherCompatMod("decorative_compat", "biomesoplenty", "decorative_blocks");
        addOtherCompatMod("storagedrawersunlimited", "biomesoplenty", "storagedrawers");
        addOtherCompatMod("lolmcvbop", "biomesoplenty", "lolmcv");
        addOtherCompatMod("lolmcvbmo", "biomemakeover", "lolmcv");
        addOtherCompatMod("natures_delight", "natures_spirit", "farmersdelight");
        addOtherCompatMod("arts_and_crafts_compat","arts_and_crafts",
                "twigs", "decorative_blocks", "farmersdelight", "dramaticdoors");

        // Macaw's Addon
        addOtherCompatMod("macawsbridgesbop", "biomesoplenty", "mcwbridges");
        addOtherCompatMod("macawbridgesbyg", "biomeswevegone", "mcwbridges");
        addOtherCompatMod("mcwfencesbop", "biomesoplenty", "mcwfences");
        addOtherCompatMod("mcwfencesbyg", "biomeswevegone", "mcwfences");
        addOtherCompatMod("macawsroofsbop", "biomesoplenty", "mcwroofs");
        addOtherCompatMod("macawsroofsbyg", "biomeswevegone", "mcwroofs");
        addOtherCompatMod("mcwbyg", List.of("biomeswevegone", "byg"),
                "mcwbridges", "mcwroofs", "mcwfences", "mcwfurnitures", "mcwstairs");
        addOtherCompatMod("mcwbiomesoplenty", "biomesoplenty",
                "mcwbridges", "mcwroofs", "mcwfences", "mcwfurnitures", "mcwstairs");
        addOtherCompatMod("mcwmoddinglegacy", List.of("blue_skies", "premium_wood"),
                "mcwbridges", "mcwroofs", "mcwfences", "mcwfurnitures", "mcwstairs");

        // Abnormals Delight
        addOtherCompatMod("abnormals_delight",
                List.of("autumnity", "upgrade_aquatic", "environmental", "atmospheric", "endergetic", "caves_and_chasms"),
                "farmersdelight");

    //!! =========================================== Add Modules ==================================================== \\
        addIfLoaded("another_furniture", () -> AnotherFurnitureModule::new);
        addIfLoaded("dawnoftimebuilder", () -> DawnOfTimeModule::new);
        addIfLoaded("backpacked", () -> BackpackedModule::new);
        addIfLoaded("barkcarpets", () -> BarkCarpetsModule::new); // Exline's
        addIfLoaded("bc", () -> BeautifulCampfiresModule::new);
        addIfLoaded("campchair", () -> CampChairModule::new);
        addIfLoaded("chipped", () -> ChippedModule::new);
        addIfLoaded("create", () -> CreateModule::new);
        addIfLoaded("decorative_blocks", () -> DecorativeBlocksModule::new);
        addIfLoaded("friendsandfoes", () -> FriendsAndFoesModule::new);
        addIfLoaded("furnish", () -> FurnishModule::new);
        addIfLoaded("hearth_and_home", () -> HearthAndHomeModule::new);
        addIfLoaded("quark", () -> QuarkModule::new);
        addIfLoaded("twigs", () -> TwigsModule::new);
        addIfLoaded("refurbished_furniture", () -> RefurbishedFurnitureModule::new);
        addIfLoaded("farmersdelight", () -> FarmersDelightModule::new);
        addIfLoaded("handcrafted", () -> HandcraftedModule::new);
        addIfLoaded("valhelsia_furniture", () -> ValhelsiaFurnitureModule::new);
        addIfLoaded("villagersplus", () -> VillagersPlusModule::new);
        addIfLoaded("table_top_craft", () -> TableTopCraftModule::new);
        addIfLoaded("storagedrawers", () -> StorageDrawersModule::new);
        addIfLoaded("stylishstiles", () -> StylishStilesModule::new);
        addIfLoaded("missingwilds", () -> MissingWildModule::new);

//!! =================================================== OTHERS ===================================================== \\
        forAllModules(m -> EveryCompat.LOGGER.info("Loaded {}", m.toString()));
    }
}
