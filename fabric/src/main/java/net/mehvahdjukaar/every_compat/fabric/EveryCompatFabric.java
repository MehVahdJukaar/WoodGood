package net.mehvahdjukaar.every_compat.fabric;

import net.fabricmc.api.ModInitializer;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.EveryCompatClient;
import net.mehvahdjukaar.every_compat.api.CompatModule;
import net.mehvahdjukaar.every_compat.modules.another_furniture.AnotherFurnitureModule;
import net.mehvahdjukaar.every_compat.modules.camp_chair.CampChairModule;
import net.mehvahdjukaar.every_compat.modules.decorative_blocks.DecorativeBlocksModule;
import net.mehvahdjukaar.every_compat.modules.exline.BarkCarpetsModule;
import net.mehvahdjukaar.every_compat.modules.fabric.architect_palette.ArchitectsPaletteModule;
import net.mehvahdjukaar.every_compat.modules.fabric.create.CreateModule;
import net.mehvahdjukaar.every_compat.modules.fabric.farmersdelight.FarmersDelightModule;
import net.mehvahdjukaar.every_compat.modules.fabric.infinitybuttons.InfinityButtonsModule;
import net.mehvahdjukaar.every_compat.modules.fabric.mcaw.*;
import net.mehvahdjukaar.moonlight.fabric.FabricSetupCallbacks;

public class EveryCompatFabric extends EveryCompat implements ModInitializer {

    @Override
    public void onInitialize() {
        this.commonInit();

        addModule("mcwbridges", () -> MacawBridgesModule::new);
        addModule("mcwdoors", () -> MacawDoorsModule::new);
        addModule("mcwfences", () -> MacawFencesModule::new);
        addModule("mcwlights", () -> MacawLightsModule::new);
        addModule("mcwpaths", () -> MacawPathsModule::new);
        addModule("mcwroofs", () -> MacawRoofsModule::new);
        addModule("mcwtrpdoors", () -> MacawTrapdoorsModule::new);
        addModule("mcwwindows", () -> MacawWindowsModule::new);

        addModule("architects_palette", () -> ArchitectsPaletteModule::new);
        addModule("create", () -> CreateModule::new);
        addModule("farmersdelight", () -> FarmersDelightModule::new);
        addModule("infinitybuttons", () -> InfinityButtonsModule::new);

        FabricSetupCallbacks.CLIENT_SETUP.add(this::onClientSetup);
        FabricSetupCallbacks.COMMON_SETUP.add(this::commonSetup);

        forAllModules(CompatModule::onModInit);
    }

    public void onClientSetup() {
        EveryCompatClient.commonInit();
        EveryCompatClient.clientSetup();
        EveryCompat.ALL_WOODS.get().registerFabricRenderer();
    }
}
