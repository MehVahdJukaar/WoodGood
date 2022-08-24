package net.mehvahdjukaar.every_compat.fabric;

import net.fabricmc.api.ModInitializer;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.EveryCompatClient;
import net.mehvahdjukaar.every_compat.modules.another_furniture.AnotherFurnitureModule;
import net.mehvahdjukaar.every_compat.modules.architect_palette.ArchitectsPaletteModule;
import net.mehvahdjukaar.every_compat.modules.camp_chair.CampChairModule;
import net.mehvahdjukaar.every_compat.modules.mcaw.*;
import net.mehvahdjukaar.every_compat.modules.twigs.TwigsModule;
import net.mehvahdjukaar.moonlight.fabric.FabricSetupCallbacks;
import net.minecraft.core.Registry;

public class EveryCompatFabric extends EveryCompat implements ModInitializer {

    @Override
    public void onInitialize() {
        addModule("twigs", () -> TwigsModule::new);
        addModule("mcwdoors", () -> MacawDoorsModule::new);
        addModule("mcwlights", () -> MacawLightsModule::new);
        addModule("mcwpaths", () -> MacawPathsModule::new);
        addModule("mcwtrpdoors", () -> MacawTrapdoorsModule::new);
        addModule("mcwwindows", () -> MacawWindowsModule::new);
        addModule("mcwfences", () -> MacawFencesModule::new);
        addModule("mcwbridges", () -> MacawBridgesModule::new);


        addModule("another_furniture", () -> AnotherFurnitureModule::new);
        addModule("architects_palette", () -> ArchitectsPaletteModule::new);

        addModule("campchair", () -> CampChairModule::new);
        //addModule("farmersdelight", () -> FarmersDelightModule::new);

        this.commonInit();

        FabricSetupCallbacks.CLIENT_SETUP.add(this::onClientSetup);
        FabricSetupCallbacks.COMMON_SETUP.add(this::onCommonSetup);

    }

    //equivalent of forge common onCommonSetup
    public void onCommonSetup() {
        this.registerItems((a, b) -> Registry.register(Registry.ITEM, a, b));
        this.registerTiles((a, b) -> Registry.register(Registry.BLOCK_ENTITY_TYPE, a, b));
        this.registerEntities((a, b) -> Registry.register(Registry.ENTITY_TYPE, a, b));
        this.commonSetup();
    }

    public void onClientSetup() {
        EveryCompatClient.commonInit();
        EveryCompatClient.commonSetup();
        EveryCompat.ALL_WOODS.get().registerFabricRenderer();
    }
}
