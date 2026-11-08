package net.mehvahdjukaar.every_compat.fabric;

import net.fabricmc.api.ModInitializer;
import net.mehvahdjukaar.every_compat.EveryCompatCommon;
import net.mehvahdjukaar.every_compat.modules.fabric.blockus.BlockusModule;
import net.mehvahdjukaar.every_compat.modules.fabric.create.CreateModule;
import net.mehvahdjukaar.every_compat.modules.fabric.farmersdelight.FarmersDelightModule;
import net.mehvahdjukaar.every_compat.modules.fabric.mcaw.*;
import net.mehvahdjukaar.every_compat.modules.fabric.regions_unexplored.RegionsUnexploredModule;
import net.mehvahdjukaar.every_compat.modules.fabric.wilder_wild.WilderWildModule;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;

import static net.mehvahdjukaar.every_compat.ECModules.addOptionalModule;

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
//        addOptionalModule("bbb", () -> BuildingButBetterModule.class);
//        addOptionalModule("bewitchment", () -> BewitchmentModule.class);
        addOptionalModule("blockus", () -> BlockusModule.class);
        addOptionalModule("farmersdelight", () -> FarmersDelightModule.class);
//        addOptionalModule("clutter", () -> ClutterModule.class);
//        addOptionalModule("dramaticdoors", () -> DramaticDoorsModule.class);
//        addOptionalModule("exlineawnings", () -> AwningModule.class);
//        addOptionalModule("furnish", () -> FurnishModule.class);
//        addOptionalModule("lightmanscurrency", () -> LightmansCurrencyModule.class); //!! Not maintained since 1.20.1
//        addOptionalModule("stylishstiles", () -> StylishStilesModule.class);
//        addOptionalModule("redbits", () -> RedBitsModule.class);
        addOptionalModule("regions_unexplored", () -> RegionsUnexploredModule.class);
        addOptionalModule("wilderwild", () -> WilderWildModule.class);
//        addOptionalModule("woodenhoppers", () -> WoodenHoppersModule.class);

//        if (PlatHelper.isModLoaded("mcwdoors")) {
//            addOptionalModule("dramaticdoors", () -> DramaticDoorsMacawModule.class);
//        }

        addOptionalModule("create", () -> CreateModule.class);

        // Load if The-New-Shutters is loaded, not Vanilla-Shutters
//        if (PlatHelper.isModLoaded("shutter")) {
//            Class<?> modClass = null;
//            try {
//                modClass = Class.forName("net.stehschnitzel.shutter.ShutterMain");
//            } catch (Exception ignored) {}
//
//            if (modClass != null) addOptionalModule("shutter", () -> LauchsShuttersModule.class);
//        }

        //REASON: v4.0.0+ is no longer supported because it no longer has variant blocks
//        if (PlatHelper.isModLoaded("excessive_building")) {
//            if (!PlatHelper.getModVersion("excessive_building").matches("4[.\\d]{4}"))
//                addOptionalModule("excessive_building", () -> ExcessiveBuildingModule.class);
//        }

// ============================================== DISABLED FOR A REASON ============================================= \\
//        addModule("twilightforest", () -> TwilightForestModule::new); //!! NOT AVAILABLE

    }
}
