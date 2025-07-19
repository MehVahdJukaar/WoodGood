package net.mehvahdjukaar.every_compat;

import net.mehvahdjukaar.every_compat.api.CompatModule;
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

//        addOtherCompatMod("compatoplenty", "biomesoplenty",
//                "twigs", "farmersdelight", "quark", "woodworks", "boatload");
//        addOtherCompatMod("compat_makeover", "biomemakeover",
//                "habitat", "farmersdelight", "quark", "decorative_blocks");
//        addOtherCompatMod("decorative_compat", "biomesoplenty", "decorative_blocks");
//        addOtherCompatMod("storagedrawersunlimited", "biomesoplenty", "storagedrawers");
//        addOtherCompatMod("lolmcvbop", "biomesoplenty", "lolmcv");
//        addOtherCompatMod("lolmcvbmo", "biomemakeover", "lolmcv");
//        addOtherCompatMod("natures_delight", "natures_spirit", "farmersdelight");
//        addOtherCompatMod("arts_and_crafts_compat","arts_and_crafts",
//                "twigs", "decorative_blocks", "farmersdelight", "dramaticdoors");

//        addOtherCompatMod("ascended_quark", List.of("aether", "deep_aether"), "quark");

        // Macaw's Addon
//        addOtherCompatMod("macawsbridgesbop", "biomesoplenty", "mcwbridges");
//        addOtherCompatMod("macawbridgesbyg", "biomeswevegone", "mcwbridges");
//        addOtherCompatMod("mcwfencesbop", "biomesoplenty", "mcwfences");
//        addOtherCompatMod("mcwfencesbyg", "biomeswevegone", "mcwfences");
//        addOtherCompatMod("macawsroofsbop", "biomesoplenty", "mcwroofs");
//        addOtherCompatMod("macawsroofsbyg", "biomeswevegone", "mcwroofs");
//        addOtherCompatMod("mcwbyg", List.of("biomeswevegone", "byg"),
//                "mcwbridges", "mcwroofs", "mcwfences", "mcwfurnitures", "mcwstairs", "mcwdoors", "mcwtrpdoors", "mcwpaths", "mcwwindows");
//        addOtherCompatMod("mcwbiomesoplenty", "biomesoplenty",
//                "mcwbridges", "mcwroofs", "mcwfences", "mcwfurnitures", "mcwstairs", "mcwdoors", "mcwtrpdoors", "mcwpaths", "mcwwindows");
//        addOtherCompatMod("mcwmoddinglegacy", List.of("blue_skies", "premium_wood"),
//                "mcwbridges", "mcwroofs", "mcwfences", "mcwfurnitures", "mcwstairs", "mcwdoors", "mcwtrpdoors", "mcwpaths", "mcwwindows");
//        addOtherCompatMod("mcwabnormals", List.of("buzzier_bees", "environmental", "upgrade_aquatic", "autumnity", "endergetic", "atmospheric", "caverns_and_chasms"),
//                "mcwbridges", "mcwroofs", "mcwfences", "mcwfurnitures", "mcwstairs", "mcwdoors", "mcwtrpdoors", "mcwpaths", "mcwwindows");
//        addOtherCompatMod("mcwterraformersmc", List.of("terrestria", "traverse", "cinderscape"),
//                "mcwbridges", "mcwroofs", "mcwfences", "mcwfurnitures", "mcwstairs", "mcwdoors", "mcwtrpdoors", "mcwpaths", "mcwwindows");
//        addOtherCompatMod("mcwaurora", "enhanced_mushrooms",
//                "mcwbridges", "mcwroofs", "mcwfences", "mcwfurnitures", "mcwstairs", "mcwdoors", "mcwtrpdoors", "mcwpaths", "mcwwindows");
//        addOtherCompatMod("mcwquark", "quark",
//                "mcwbridges", "mcwroofs", "mcwfences", "mcwfurnitures", "mcwstairs", "mcwdoors", "mcwtrpdoors", "mcwpaths", "mcwwindows");

        // Abnormals Delight
//        addOtherCompatMod("abnormals_delight",
//                List.of("autumnity", "upgrade_aquatic", "environmental", "atmospheric", "endergetic", "caverns_and_chasms"),
//                "farmersdelight");
    }
}
