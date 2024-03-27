package net.mehvahdjukaar.every_compat.fabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.EveryCompatClient;
import net.mehvahdjukaar.every_compat.api.CompatModule;
import net.mehvahdjukaar.every_compat.modules.fabric.architect_palette.ArchitectsPaletteModule;
import net.mehvahdjukaar.every_compat.modules.fabric.create.CreateModule;
import net.mehvahdjukaar.every_compat.modules.fabric.dramatic_doors.DramaticDoorsModule;
import net.mehvahdjukaar.every_compat.modules.fabric.farmersdelight.FarmersDelightModule;
import net.mehvahdjukaar.every_compat.modules.fabric.infinitybuttons.InfinityButtonsModule;
import net.mehvahdjukaar.every_compat.modules.fabric.mcaw.*;
import net.mehvahdjukaar.every_compat.modules.fabric.twilightforest.TwilightForestModule;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;

public class EveryCompatFabric extends EveryCompat implements ModInitializer {

    @Override
    public void onInitialize() {
        this.commonInit();

        if(PlatHelper.getPhysicalSide().isClient())
            ItemTooltipCallback.EVENT.register(EveryCompatClient::onItemTooltip);

        // ========================================= Macaw's ======================================================== \\
        addModule("mcwbridges", () -> MacawBridgesModule::new);
        addModule("mcwdoors", () -> MacawDoorsModule::new);
        addModule("mcwfences", () -> MacawFencesModule::new);
        addModule("mcwlights", () -> MacawLightsModule::new);
        addModule("mcwpaths", () -> MacawPathsModule::new);
        addModule("mcwroofs", () -> MacawRoofsModule::new);
        addModule("mcwtrpdoors", () -> MacawTrapdoorsModule::new);
        addModule("mcwwindows", () -> MacawWindowsModule::new);
        addModule("mcwfurnitures", () -> MacawFurnitureModule::new);

        // ========================================= Add Modules ==================================================== \\
        addModule("architects_palette", () -> ArchitectsPaletteModule::new);
        addModule("create", () -> CreateModule::new);
        addModule("infinitybuttons", () -> InfinityButtonsModule::new);
        addModule("dramaticdoors", () -> DramaticDoorsModule::new);
        addModule("twilightforest", () -> TwilightForestModule::new);

        // ========================================== WORK IN PROGRESS ============================================== \\

        // ====================================== DISABLED FOR A REASON ============================================= \\

        // ============================================= OTHERS ===================================================== \\
        forAllModules(CompatModule::onModInit);
        if (PlatHelper.getPhysicalSide().isClient()) {
            EveryCompatClient.init();
        }
    }
}
