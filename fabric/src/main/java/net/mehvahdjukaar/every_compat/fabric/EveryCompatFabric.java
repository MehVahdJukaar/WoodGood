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

import static net.mehvahdjukaar.every_compat.EveryCompat.addOptionalModule;
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
            addOptionalModule("mcwbridges", () -> MacawBridgesModule.class);
            addOptionalModule("mcwdoors", () -> MacawDoorsModule.class);
            addOptionalModule("mcwfences", () -> MacawFencesModule.class);
            addOptionalModule("mcwlights", () -> MacawLightsModule.class);
            addOptionalModule("mcwpaths", () -> MacawPathsModule.class);
            addOptionalModule("mcwroofs", () -> MacawRoofsModule.class);
            addOptionalModule("mcwtrpdoors", () -> MacawTrapdoorsModule.class);
            addOptionalModule("mcwwindows", () -> MacawWindowsModule.class);
            addOptionalModule("mcwfurnitures", () -> MacawFurnitureModule.class);
            addOptionalModule("mcwstairs", () -> MacawStairsModule.class);

            // =============================================== GENERAL ================================================== \\
            addOptionalModule("bbb", () -> BuildingButBetterModule.class);
            addOptionalModule("bewitchment", () -> BewitchmentModule.class);
            addOptionalModule("clutter", () -> ClutterModule.class);
            addOptionalModule("dramaticdoors", () -> DramaticDoorsModule.class);
            addOptionalModule("exlineawnings", () -> AwningModule.class);
            addOptionalModule("infinitybuttons", () -> InfinityButtonsModule.class);
            addOptionalModule("lightmanscurrency", () -> LightmansCurrencyModule.class); //!! Not maintained since 1.20.1
            addOptionalModule("mighty_mail", () -> MightyMailModule.class);
            addOptionalModule("redbits", () -> RedBitsModule.class);
            addOptionalModule("regions_unexplored", () -> RegionsUnexploredModule.class);
            addOptionalModule("shutter", () -> LauchsShuttersModule.class);
            addOptionalModule("wilderwild", () -> WilderWildModule.class);
            addOptionalModule("woodenhoppers", () -> WoodenHoppersModule.class);

            /// Remove it in the next version: v2.11.27
            if (PlatHelper.isModLoaded("beautify")) {
                if (!PlatHelper.getModVersion("beautify").matches("2.0.\\d\\+1.21.1"))
                    addOptionalModule("beautify", () -> BeautifyRefabricatedModule.class);
            }

            if (PlatHelper.isModLoaded("mcwdoors")) {
                addOptionalModule("dramaticdoors", () -> DramaticDoorsMacawModule.class);
            }

            addOptionalModule("create", () -> CreateModule.class); // ONLY TEMP until FABRIC v6.0.0 is out
        }

// ============================================== DISABLED FOR A REASON ============================================= \\
//        addModule("twilightforest", () -> TwilightForestModule::new); //!! NOT AVAILABLE

    }
}
