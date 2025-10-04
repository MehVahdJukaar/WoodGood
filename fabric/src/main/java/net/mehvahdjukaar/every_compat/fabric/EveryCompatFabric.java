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

import static net.mehvahdjukaar.every_compat.api.EveryCompatAPI.addIfLoaded;
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
            addIfLoaded("mcwbridges", () -> MacawBridgesModule.class);
            addIfLoaded("mcwdoors", () -> MacawDoorsModule.class);
            addIfLoaded("mcwfences", () -> MacawFencesModule.class);
            addIfLoaded("mcwlights", () -> MacawLightsModule.class);
            addIfLoaded("mcwpaths", () -> MacawPathsModule.class);
            addIfLoaded("mcwroofs", () -> MacawRoofsModule.class);
            addIfLoaded("mcwtrpdoors", () -> MacawTrapdoorsModule.class);
            addIfLoaded("mcwwindows", () -> MacawWindowsModule.class);
            addIfLoaded("mcwfurnitures", () -> MacawFurnitureModule.class);
            addIfLoaded("mcwstairs", () -> MacawStairsModule.class);

            // =============================================== GENERAL ================================================== \\
            addIfLoaded("bbb", () -> BuildingButBetterModule.class);
            addIfLoaded("beautify", () -> BeautifyRefabricatedModule.class);
            addIfLoaded("bewitchment", () -> BewitchmentModule.class);
            addIfLoaded("clutter", () -> ClutterModule.class);
            addIfLoaded("dramaticdoors", () -> DramaticDoorsModule.class);
            addIfLoaded("excessive_building", () -> ExcessiveBuildingModule.class);
            addIfLoaded("exlineawnings", () -> AwningModule.class);
            addIfLoaded("infinitybuttons", () -> InfinityButtonsModule.class);
            addIfLoaded("lightmanscurrency", () -> LightmansCurrencyModule.class); //!! Not maintained since 1.20.1
            addIfLoaded("mighty_mail", () -> MightyMailModule.class);
            addIfLoaded("redbits", () -> RedBitsModule.class);
            addIfLoaded("regions_unexplored", () -> RegionsUnexploredModule.class);
            addIfLoaded("shutter", () -> LauchsShuttersModule.class);
            addIfLoaded("wilderwild", () -> WilderWildModule.class);
            addIfLoaded("woodenhoppers", () -> WoodenHoppersModule.class);

            if (PlatHelper.isModLoaded("mcwdoors")) {
                addIfLoaded("dramaticdoors", () -> DramaticDoorsMacawModule.class);
            }

            addIfLoaded("create", () -> CreateModule.class); // ONLY TEMP until FABRIC v6.0.0 is out
        }

// ============================================== DISABLED FOR A REASON ============================================= \\
//        addModule("twilightforest", () -> TwilightForestModule::new); //!! NOT AVAILABLE

    }
}
