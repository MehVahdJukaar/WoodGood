package net.mehvahdjukaar.every_compat.fabric;

import net.fabricmc.api.ModInitializer;
import net.mehvahdjukaar.every_compat.EveryCompatCommon;
import net.mehvahdjukaar.every_compat.modules.fabric.beautify_decorate.BeautifyRefabricatedModule;
import net.mehvahdjukaar.every_compat.modules.fabric.bewitchment.BewitchmentModule;
import net.mehvahdjukaar.every_compat.modules.fabric.building_but_better.BuildingButBetterModule;
import net.mehvahdjukaar.every_compat.modules.fabric.clutter.ClutterModule;
import net.mehvahdjukaar.every_compat.modules.fabric.create.CreateModule;
import net.mehvahdjukaar.every_compat.modules.fabric.dramatic_doors.DramaticDoorsMacawModule;
import net.mehvahdjukaar.every_compat.modules.fabric.dramatic_doors.DramaticDoorsModule;
import net.mehvahdjukaar.every_compat.modules.fabric.excessive_building.ExcessiveBuildingModule;
import net.mehvahdjukaar.every_compat.modules.fabric.exlines.AwningModule;
import net.mehvahdjukaar.every_compat.modules.fabric.infinitybuttons.InfinityButtonsModule;
import net.mehvahdjukaar.every_compat.modules.fabric.lauchs.LauchsShuttersModule;
import net.mehvahdjukaar.every_compat.modules.fabric.lightmans_currency.LightmansCurrencyModule;
import net.mehvahdjukaar.every_compat.modules.fabric.mcaw.*;
import net.mehvahdjukaar.every_compat.modules.fabric.mrcrayfish.MightyMailModule;
import net.mehvahdjukaar.every_compat.modules.fabric.red_bits.RedBitsModule;
import net.mehvahdjukaar.every_compat.modules.fabric.regions_unexplored.RegionsUnexploredModule;
import net.mehvahdjukaar.every_compat.modules.fabric.wilder_wild.WilderWildModule;
import net.mehvahdjukaar.every_compat.modules.fabric.wooden_hoppers.WoodenHoppersModule;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;

import static net.mehvahdjukaar.every_compat.EveryCompat.maybeAddModule;
import static net.mehvahdjukaar.every_compat.configs.UnsafeDisablerConfigs.INCLUDE_ALL_WOOD_MODULES;

public class EveryCompatFabric extends EveryCompatCommon implements ModInitializer {

    @Override
    public void onInitialize() {
        this.initialize();

        if (PlatHelper.getPhysicalSide().isClient()) {
            EveryCompatFabricClient.init();
        }
    }

    @Override
    protected void addModules() {
        super.addModules();

//!!================================================ Add Modules ==================================================== \\

        if (INCLUDE_ALL_WOOD_MODULES.get()) {

            // =============================================== MACAW's ================================================== \\
            maybeAddModule("mcwbridges", () -> MacawBridgesModule.class);
            maybeAddModule("mcwdoors", () -> MacawDoorsModule.class);
            maybeAddModule("mcwfences", () -> MacawFencesModule.class);
            maybeAddModule("mcwlights", () -> MacawLightsModule.class);
            maybeAddModule("mcwpaths", () -> MacawPathsModule.class);
            maybeAddModule("mcwroofs", () -> MacawRoofsModule.class);
            maybeAddModule("mcwtrpdoors", () -> MacawTrapdoorsModule.class);
            maybeAddModule("mcwwindows", () -> MacawWindowsModule.class);
            maybeAddModule("mcwfurnitures", () -> MacawFurnitureModule.class);
            maybeAddModule("mcwstairs", () -> MacawStairsModule.class);

            // =============================================== GENERAL ================================================== \\
            maybeAddModule("bbb", () -> BuildingButBetterModule.class);
            maybeAddModule("beautify", () -> BeautifyRefabricatedModule.class);
            maybeAddModule("bewitchment", () -> BewitchmentModule.class);
            maybeAddModule("clutter", () -> ClutterModule.class);
            maybeAddModule("dramaticdoors", () -> DramaticDoorsModule.class);
            maybeAddModule("excessive_building", () -> ExcessiveBuildingModule.class);
            maybeAddModule("exlineawnings", () -> AwningModule.class);
            maybeAddModule("infinitybuttons", () -> InfinityButtonsModule.class);
            maybeAddModule("lightmanscurrency", () -> LightmansCurrencyModule.class); //!! Not maintained since 1.20.1
            maybeAddModule("mighty_mail", () -> MightyMailModule.class);
            maybeAddModule("redbits", () -> RedBitsModule.class);
            maybeAddModule("regions_unexplored", () -> RegionsUnexploredModule.class);
            maybeAddModule("shutter", () -> LauchsShuttersModule.class);
            maybeAddModule("wilderwild", () -> WilderWildModule.class);
            maybeAddModule("woodenhoppers", () -> WoodenHoppersModule.class);

            if (PlatHelper.isModLoaded("mcwdoors")) {
                maybeAddModule("dramaticdoors", () -> DramaticDoorsMacawModule.class);
            }

            maybeAddModule("create", () -> CreateModule.class); // ONLY TEMP until FABRIC v6.0.0 is out
        }

// ============================================== DISABLED FOR A REASON ============================================= \\
//        addModule("twilightforest", () -> TwilightForestModule::new); //!! NOT AVAILABLE

    }
}
