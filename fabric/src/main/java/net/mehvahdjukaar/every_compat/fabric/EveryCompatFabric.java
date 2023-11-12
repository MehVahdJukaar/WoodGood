package net.mehvahdjukaar.every_compat.fabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.EveryCompatClient;
import net.mehvahdjukaar.every_compat.api.CompatModule;
import net.mehvahdjukaar.every_compat.modules.another_furniture.AnotherFurnitureModule;
import net.mehvahdjukaar.every_compat.modules.camp_chair.CampChairModule;
import net.mehvahdjukaar.every_compat.modules.decorative_blocks.DecorativeBlocksModule;
import net.mehvahdjukaar.every_compat.modules.exline.BarkCarpetsModule;
import net.mehvahdjukaar.every_compat.modules.fabric.architect_palette.ArchitectsPaletteModule;
import net.mehvahdjukaar.every_compat.modules.fabric.create.CreateModule;
import net.mehvahdjukaar.every_compat.modules.fabric.dramatic_doors.dramaticdoors.DramaticDoorsModule;
import net.mehvahdjukaar.every_compat.modules.fabric.farmersdelight.FarmersDelightModule;
import net.mehvahdjukaar.every_compat.modules.fabric.infinitybuttons.InfinityButtonsModule;
import net.mehvahdjukaar.every_compat.modules.fabric.mcaw.*;
import net.mehvahdjukaar.every_compat.modules.friendsandfoes.FriendsAndFoesModule;
import net.mehvahdjukaar.every_compat.modules.furnish.FurnishModule;
import net.mehvahdjukaar.every_compat.modules.handcrafted.HandcraftedModule;
import net.mehvahdjukaar.every_compat.modules.twigs.TwigsModule;

public class EveryCompatFabric extends EveryCompat implements ModInitializer {

    @Override
    public void onInitialize() {
        this.commonInit();

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
        addModule("farmersdelight", () -> FarmersDelightModule::new);
        addModule("infinitybuttons", () -> InfinityButtonsModule::new);
        addModule("dramaticdoors", () -> DramaticDoorsModule::new);

        // ========================================== WORK IN PROGRESS ============================================== \\

        // ====================================== DISABLED FOR A REASON ============================================= \\

        // ============================================= OTHERS ===================================================== \\
        forAllModules(CompatModule::onModInit);

    }
}
