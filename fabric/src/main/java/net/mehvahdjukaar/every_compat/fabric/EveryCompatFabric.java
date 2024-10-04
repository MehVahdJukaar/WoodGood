package net.mehvahdjukaar.every_compat.fabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.EveryCompatClient;
import net.mehvahdjukaar.every_compat.api.CompatModule;
import net.mehvahdjukaar.every_compat.api.EveryCompatAPI;
import net.mehvahdjukaar.every_compat.modules.fabric.beautify_decorate.BeautifyRefabricatedModule;
import net.mehvahdjukaar.every_compat.modules.fabric.bewitchment.BewitchmentModule;
import net.mehvahdjukaar.every_compat.modules.fabric.clutter.ClutterModule;
import net.mehvahdjukaar.every_compat.modules.fabric.exlines.AwningModule;
import net.mehvahdjukaar.every_compat.modules.fabric.lauchs.LauchsShuttersModule;
import net.mehvahdjukaar.every_compat.modules.fabric.lightmans_currency.LightmansCurrencyModule;
import net.mehvahdjukaar.every_compat.modules.fabric.missing_wilds.MissingWildModule;
import net.mehvahdjukaar.every_compat.modules.fabric.mrcrayfish.MightyMailModule;
import net.mehvahdjukaar.every_compat.modules.fabric.create.CreateModule;
import net.mehvahdjukaar.every_compat.modules.fabric.dramatic_doors.DramaticDoorsModule;
import net.mehvahdjukaar.every_compat.modules.fabric.farmersdelight.FarmersDelightModuleOld;
import net.mehvahdjukaar.every_compat.modules.fabric.infinitybuttons.InfinityButtonsModule;
import net.mehvahdjukaar.every_compat.modules.fabric.mcaw.*;
import net.mehvahdjukaar.every_compat.modules.fabric.lieonlion.MoreCraftingTablesModule;
import net.mehvahdjukaar.every_compat.modules.fabric.red_bits.RedBitsModule;
import net.mehvahdjukaar.every_compat.modules.fabric.regions_unexplored.RegionsUnexploredModule;
import net.mehvahdjukaar.every_compat.modules.fabric.variants.VariantVanillaBlocksModule;
import net.mehvahdjukaar.every_compat.modules.fabric.wilder_wild.WilderWildModule;
import net.mehvahdjukaar.every_compat.modules.fabric.wooden_hoppers.WoodenHoppersModule;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;

public class EveryCompatFabric extends EveryCompat implements ModInitializer {

    @Override
    public void onInitialize() {
        this.commonInit();

        if (PlatHelper.getPhysicalSide().isClient())
            ItemTooltipCallback.EVENT.register(EveryCompatClient::onItemTooltip);

// ================================================= Macaw's ======================================================== \\
        addModule("mcwbridges", () -> MacawBridgesModule::new);
        addModule("mcwdoors", () -> MacawDoorsModule::new);
        addModule("mcwfences", () -> MacawFencesModule::new);
        addModule("mcwlights", () -> MacawLightsModule::new);
        addModule("mcwpaths", () -> MacawPathsModule::new);
        addModule("mcwroofs", () -> MacawRoofsModule::new);
        addModule("mcwtrpdoors", () -> MacawTrapdoorsModule::new);
        addModule("mcwwindows", () -> MacawWindowsModule::new);
        addModule("mcwfurnitures", () -> MacawFurnitureModule::new);

// ================================================= Add Modules ==================================================== \\
        addModule("beautify", () -> BeautifyRefabricatedModule::new);
        addModule("bewitchment", () -> BewitchmentModule::new);
        addModule("clutter", () -> ClutterModule::new);
        addModule("create", () -> CreateModule::new);
        addModule("dramaticdoors", () -> DramaticDoorsModule::new);
        addModule("exlineawnings", () -> AwningModule::new);
        addModule("infinitybuttons", () -> InfinityButtonsModule::new);
        addModule("lightmanscurrency", () -> LightmansCurrencyModule::new);
        addModule("lolmct", () -> MoreCraftingTablesModule::new);
        addModule("mighty_mail", () -> MightyMailModule::new);
        addModule("missingwilds", () -> MissingWildModule::new);
        addModule("redbits", () -> RedBitsModule::new);
        addModule("regions_unexplored", () -> RegionsUnexploredModule::new);
        addModule("shutter", () -> LauchsShuttersModule::new);
        addModule("variantvanillablocks", () -> VariantVanillaBlocksModule::new);
        addModule("wilderwild", () -> WilderWildModule::new);
        addModule("woodenhoppers", () -> WoodenHoppersModule::new);

// ============================================== DISABLED FOR A REASON ============================================= \\
//        addModule("twilightforest", () -> TwilightForestModule::new); // Not available
//        addModule("architects_palette", () -> ArchitectsPaletteModule::new); // Not available

// ===================================================== OTHERS ===================================================== \\
        if (EveryCompat.OLD_FD) EveryCompatAPI.registerModule(new FarmersDelightModuleOld("farmersdelight"));

        forAllModules(CompatModule::onModInit);
        if (PlatHelper.getPhysicalSide().isClient()) {
            EveryCompatClient.init();
        }
    }
}
