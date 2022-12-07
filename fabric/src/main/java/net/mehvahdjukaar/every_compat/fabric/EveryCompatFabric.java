package net.mehvahdjukaar.every_compat.fabric;

import net.fabricmc.api.ModInitializer;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.EveryCompatClient;
import net.mehvahdjukaar.every_compat.modules.another_furniture.AnotherFurnitureModule;
import net.mehvahdjukaar.every_compat.modules.architect_palette.ArchitectsPaletteModule;
import net.mehvahdjukaar.every_compat.modules.camp_chair.CampChairModule;
import net.mehvahdjukaar.every_compat.modules.decorative_blocks.DecorativeBlocksModule;
import net.mehvahdjukaar.every_compat.modules.mcaw.*;
import net.mehvahdjukaar.every_compat.modules.twigs.TwigsModule;
import net.mehvahdjukaar.moonlight.fabric.FabricSetupCallbacks;
import net.minecraft.core.Registry;

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
        //addModule("mcwwindows", () -> MacawWindowsModule::new);

        addModule("another_furniture", () -> AnotherFurnitureModule::new);
        addModule("architects_palette", () -> ArchitectsPaletteModule::new);
        addModule("campchair", () -> CampChairModule::new);
        addModule("decorative_blocks", () -> DecorativeBlocksModule::new);
        addModule("twigs", () -> TwigsModule::new);

        //addModule("farmersdelight", () -> FarmersDelightModule::new);

        FabricSetupCallbacks.CLIENT_SETUP.add(this::onClientSetup);
        FabricSetupCallbacks.COMMON_SETUP.add(this::commonSetup);

    }

    public void onClientSetup() {
        EveryCompatClient.commonInit();
        EveryCompatClient.commonSetup();
        EveryCompat.ALL_WOODS.get().registerFabricRenderer();
    }
}
