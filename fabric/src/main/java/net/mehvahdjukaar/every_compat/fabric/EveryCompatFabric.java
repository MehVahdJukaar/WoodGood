package net.mehvahdjukaar.every_compat.fabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.EveryCompatClient;
import net.mehvahdjukaar.every_compat.api.CompatModule;
import net.mehvahdjukaar.every_compat.api.EveryCompatAPI;
import net.mehvahdjukaar.every_compat.modules.fabric.architect_palette.ArchitectsPaletteModule;
import net.mehvahdjukaar.every_compat.modules.fabric.mrcrayfish.MightyMailModule;
import net.mehvahdjukaar.every_compat.modules.fabric.create.CreateModule;
import net.mehvahdjukaar.every_compat.modules.fabric.dramatic_doors.DramaticDoorsModule;
import net.mehvahdjukaar.every_compat.modules.fabric.farmersdelight.FarmersDelightModuleOld;
import net.mehvahdjukaar.every_compat.modules.fabric.infinitybuttons.InfinityButtonsModule;
import net.mehvahdjukaar.every_compat.modules.fabric.mcaw.*;
import net.mehvahdjukaar.every_compat.modules.fabric.lieonlion.MoreCraftingTablesModule;
import net.mehvahdjukaar.every_compat.modules.fabric.regions_unexplored.RegionsUnexploredModule;
import net.mehvahdjukaar.every_compat.modules.fabric.twilightforest.TwilightForestModule;
import net.mehvahdjukaar.every_compat.modules.fabric.variants.VariantVanillaBlocksModule;
import net.mehvahdjukaar.every_compat.modules.fabric.wilder_wild.WilderWildModule;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;

public class EveryCompatFabric extends EveryCompat implements ModInitializer {

    @Override
    public void onInitialize() {
        this.commonInit();

        if (PlatHelper.getPhysicalSide().isClient())
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
        addModule("create", () -> CreateModule::new);
        addModule("dramaticdoors", () -> DramaticDoorsModule::new);
        addModule("infinitybuttons", () -> InfinityButtonsModule::new);
        addModule("lolmct", () -> MoreCraftingTablesModule::new);
        addModule("mighty_mail", () -> MightyMailModule::new);
        addModule("regions_unexplored", () -> RegionsUnexploredModule::new);
        addModule("variantvanillablocks", () -> VariantVanillaBlocksModule::new);
        addModule("wilderwild", () -> WilderWildModule::new);

        if (EveryCompat.OLD_FD) EveryCompatAPI.registerModule(new FarmersDelightModuleOld("farmersdelight"));
        // ========================================== WORK IN PROGRESS ============================================== \\

        // ====================================== DISABLED FOR A REASON ============================================= \\
//        addModule("twilightforest", () -> TwilightForestModule::new); // Not available
//        addModule("architects_palette", () -> ArchitectsPaletteModule::new); // Not available

        // ============================================= OTHERS ===================================================== \\
        forAllModules(CompatModule::onModInit);
        if (PlatHelper.getPhysicalSide().isClient()) {
            EveryCompatClient.init();
        }
    }
}
