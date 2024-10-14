package net.mehvahdjukaar.every_compat.fabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.EveryCompatClient;
import net.mehvahdjukaar.every_compat.api.CompatModule;
import net.mehvahdjukaar.every_compat.modules.fabric.MacawStairsModule;
import net.mehvahdjukaar.every_compat.modules.fabric.architect_palette.ArchitectsPaletteModule;
import net.mehvahdjukaar.every_compat.modules.fabric.beautify_decorate.BeautifyRefabricatedModule;
import net.mehvahdjukaar.every_compat.modules.fabric.bewitchment.BewitchmentModule;
import net.mehvahdjukaar.every_compat.modules.fabric.clutter.ClutterModule;
import net.mehvahdjukaar.every_compat.modules.fabric.create.CreateModule;
import net.mehvahdjukaar.every_compat.modules.fabric.dramaticdoors.DramaticDoorsMacawModule;
import net.mehvahdjukaar.every_compat.modules.fabric.dramaticdoors.DramaticDoorsModule;
import net.mehvahdjukaar.every_compat.modules.fabric.farmersdelight.FarmersDelightModule;
import net.mehvahdjukaar.every_compat.modules.fabric.infinitybuttons.InfinityButtonsModule;
import net.mehvahdjukaar.every_compat.modules.fabric.lightmans_currency.LightmansCurrencyModule;
import net.mehvahdjukaar.every_compat.modules.fabric.mcaw.*;
import net.mehvahdjukaar.every_compat.modules.fabric.mrcrayfish.BackpackedModule;
import net.mehvahdjukaar.every_compat.modules.fabric.mrcrayfish.MightyMailModule;
import net.mehvahdjukaar.every_compat.modules.fabric.red_bits.RedBitsModule;
import net.mehvahdjukaar.every_compat.modules.fabric.regions_unexplored.RegionsUnexploredModule;
import net.mehvahdjukaar.every_compat.modules.fabric.villagers_plus.VillagersPlusModule;
import net.mehvahdjukaar.every_compat.modules.fabric.wilder_wild.WilderWildModule;
import net.mehvahdjukaar.every_compat.modules.fabric.wooden_hoppers.WoodenHoppersModule;
import net.mehvahdjukaar.moonlight.api.platform.PlatformHelper;
import net.mehvahdjukaar.moonlight.fabric.FabricSetupCallbacks;

public class EveryCompatFabric extends EveryCompat implements ModInitializer {

    @Override
    public void onInitialize() {
        this.commonInit();

        if (PlatformHelper.getEnv().isClient())
            ItemTooltipCallback.EVENT.register(EveryCompatClient::onItemTooltip);

// ========================================= Add Modules ==================================================== \\
            // Macaw's
        addModule("mcwbridges", () -> MacawBridgesModule::new);
        addModule("mcwdoors", () -> MacawDoorsModule::new);
        addModule("mcwfences", () -> MacawFencesModule::new);
        addModule("mcwlights", () -> MacawLightsModule::new);
        addModule("mcwpaths", () -> MacawPathsModule::new);
        addModule("mcwroofs", () -> MacawRoofsModule::new);
        addModule("mcwtrpdoors", () -> MacawTrapdoorsModule::new);
        addModule("mcwwindows", () -> MacawWindowsModule::new);
        addModule("mcwfurnitures", () -> MacawFurnitureModule::new);
        addModule("mcwstairs", () -> MacawStairsModule::new);

            // OTHERS
        addModule("architects_palette", () -> ArchitectsPaletteModule::new);
        addModule("backpacked", () -> BackpackedModule::new);
        addModule("beautify", () -> BeautifyRefabricatedModule::new);
        addModule("bewitchment", () -> BewitchmentModule::new);
        addModule("clutter", () -> ClutterModule::new);
        addModule("create", () -> CreateModule::new);
        addModule("farmersdelight", () -> FarmersDelightModule::new);
        addModule("infinitybuttons", () -> InfinityButtonsModule::new);
        addModule("dramaticdoors", () -> DramaticDoorsModule::new);
        addModule("mighty_mail", () -> MightyMailModule::new);
        if (PlatformHelper.isModLoaded("mcwdoors")) {
            addModule("dramaticdoors", () -> DramaticDoorsMacawModule::new);
        }
        addModule("redbits", () -> RedBitsModule::new);
        addModule("regions_unexplored", () -> RegionsUnexploredModule::new);
        addModule("villagersplus", () -> VillagersPlusModule::new);
        addModule("wilderwild", () -> WilderWildModule::new);
        addModule("woodenhoppers", () -> WoodenHoppersModule::new);
        addModule("lightmanscurrency", () -> LightmansCurrencyModule::new);

        // ============================================= OTHERS ===================================================== \\
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
