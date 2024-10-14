package net.mehvahdjukaar.every_compat.forge;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.EveryCompatClient;
import net.mehvahdjukaar.every_compat.api.CompatModule;

import net.mehvahdjukaar.every_compat.modules.forge.abnormal.WoodworksModule;
import net.mehvahdjukaar.every_compat.modules.forge.absent_by_design.AbsentByDesignModule;
import net.mehvahdjukaar.every_compat.modules.forge.architect_palette.ArchitectsPaletteModule;
import net.mehvahdjukaar.every_compat.modules.forge.backpacked.BackpackedModule;
import net.mehvahdjukaar.every_compat.modules.forge.beautify_decorate.BeautifyDecorateModule;
import net.mehvahdjukaar.every_compat.modules.forge.builders_delight.BuildersDelightModule;
import net.mehvahdjukaar.every_compat.modules.forge.buildersaddition.BuildersAdditionModule;
import net.mehvahdjukaar.every_compat.modules.forge.corail.CorailPillarModule;
import net.mehvahdjukaar.every_compat.modules.forge.create.CreateModule;
import net.mehvahdjukaar.every_compat.modules.forge.dawn_of_time.DawnOfTimeModule;
import net.mehvahdjukaar.every_compat.modules.forge.decoration_delight.DecorationDelightModule;
import net.mehvahdjukaar.every_compat.modules.forge.dramaticdoors.DramaticDoorsMacawModule;
import net.mehvahdjukaar.every_compat.modules.forge.dramaticdoors.DramaticDoorsModule;
import net.mehvahdjukaar.every_compat.modules.forge.exnihilo.ExNihiloSequentiaModule;
import net.mehvahdjukaar.every_compat.modules.forge.farmersdelight.FarmersDelightModule;
import net.mehvahdjukaar.every_compat.modules.forge.infinitybuttons.InfinityButtonsModule;
import net.mehvahdjukaar.every_compat.modules.forge.lauchs.LauchsShuttersModule;
import net.mehvahdjukaar.every_compat.modules.forge.lightmans_currency.LightmansCurrencyModule;
import net.mehvahdjukaar.every_compat.modules.forge.mcaw.*;
import net.mehvahdjukaar.every_compat.modules.forge.missing_wilds.MissingWildModule;
import net.mehvahdjukaar.every_compat.modules.forge.mosaic_carpentry.MosaicCarpentryModule;
import net.mehvahdjukaar.every_compat.modules.forge.mrcrayfish.MightyMailModule;
import net.mehvahdjukaar.every_compat.modules.forge.mrcrayfish.MrCrayfishFurnitureModule;
import net.mehvahdjukaar.every_compat.modules.forge.oreberries_replanted.OreberriesReplantedModule;
import net.mehvahdjukaar.every_compat.modules.forge.pokecube.PokecubeLegendsModule;
import net.mehvahdjukaar.every_compat.modules.forge.premium_wood.PremiumWoodModule;
import net.mehvahdjukaar.every_compat.modules.forge.productive_bees.ProductiveBeesModule;
import net.mehvahdjukaar.every_compat.modules.forge.quark.QuarkModule;
import net.mehvahdjukaar.every_compat.modules.forge.regions_unexplored.RegionsUnexploredModule;
import net.mehvahdjukaar.every_compat.modules.forge.storagedrawers.StorageDrawersModule;
import net.mehvahdjukaar.every_compat.modules.forge.tropicraft.TropicraftModule;
import net.mehvahdjukaar.every_compat.modules.forge.twilightforest.TwilightForestModule;
import net.mehvahdjukaar.every_compat.modules.forge.valhelsia.ValhelsiaFurnitureModule;
import net.mehvahdjukaar.every_compat.modules.forge.valhelsia.ValhelsiaStructuresModule;
import net.mehvahdjukaar.every_compat.modules.forge.villagers_plus.VillagersPlusModule;
import net.mehvahdjukaar.every_compat.modules.forge.woodster.WoodsterModule;
import net.mehvahdjukaar.every_compat.modules.forge.workshop.WorkshopForHandsomeAdventurerModule;
import net.mehvahdjukaar.every_compat.modules.forge.xerca.XercaModule;
import net.mehvahdjukaar.every_compat.modules.mrcrayfish.RefurbishedFurnitureModule;

import net.mehvahdjukaar.moonlight.api.platform.PlatformHelper;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.MissingMappingsEvent;

import java.util.Optional;

/**
 * Author: MehVahdJukaar
 */
@Mod(EveryCompat.MOD_ID)
public class EveryCompatForge extends EveryCompat {
    public static final String MOD_ID = EveryCompat.MOD_ID;

    public EveryCompatForge() {
        this.commonInit();

        CraftingHelper.register(new BlockTypeEnabledCondition.Serializer());

        // ========================================= Add Modules ==================================================== \\
            // Macaw's
        addModule("mcwbridges", () -> MacawBridgesModule::new);
        addModule("mcwdoors", () -> MacawDoorsModule::new);
        addModule("mcwfences", () -> MacawFencesModule::new);
        addModule("mcwfurnitures", () -> MacawFurnitureModule::new);
        addModule("mcwlights", () -> MacawLightsModule::new);
        addModule("mcwpaths", () -> MacawPathsModule::new);
        addModule("mcwroofs", () -> MacawRoofsModule::new);
        addModule("mcwtrpdoors", () -> MacawTrapdoorsModule::new);
        addModule("mcwwindows", () -> MacawWindowsModule::new);
        addModule("mcwstairs", () -> MacawStairsModule::new);

            // OTHERS
        addModule("absentbydesign", () -> AbsentByDesignModule::new);
        addModule("architects_palette", () -> ArchitectsPaletteModule::new);
        addModule("backpacked", () -> BackpackedModule::new);
        addModule("beautify", () -> BeautifyDecorateModule::new);
        addModule("buildersaddition", () -> BuildersAdditionModule::new);
        addModule("buildersdelight", () -> BuildersDelightModule::new);
        addModule("cfm", () -> MrCrayfishFurnitureModule::new);
        addModule("corail_pillar", () -> CorailPillarModule::new);
        addModule("create", () -> CreateModule::new);
        addModule("dawnoftimebuilder", () -> DawnOfTimeModule::new);
        addModule("decoration_delight", () -> DecorationDelightModule::new);
        if (PlatformHelper.isModLoaded("mcwdoors")) {
            addModule("dramaticdoors", () -> DramaticDoorsMacawModule::new);
        }
        addModule("dramaticdoors", () -> DramaticDoorsModule::new);
        addModule("exnihilosequentia", () -> ExNihiloSequentiaModule::new);
        addModule("farmersdelight", () -> FarmersDelightModule::new);
        addModule("infinitybuttons", () -> InfinityButtonsModule::new);
        addModule("lightmanscurrency", () -> LightmansCurrencyModule::new);
        addModule("mighty_mail", () -> MightyMailModule::new);
        addModule("missingwilds", () -> MissingWildModule::new);
        addModule("mosaic_carpentry", () -> MosaicCarpentryModule::new);
        addModule("oreberriesreplanted", () -> OreberriesReplantedModule::new);
        addModule("pokecube_legends", () -> PokecubeLegendsModule::new);
        addModule("premium_wood", () -> PremiumWoodModule::new);
        addModule("productivebees", () -> ProductiveBeesModule::new);
        addModule("quark", () -> QuarkModule::new);
        addModule("refurbished_furniture", () -> RefurbishedFurnitureModule::new);
        addModule("regions_unexplored", () -> RegionsUnexploredModule::new);
        addModule("shutter", () -> LauchsShuttersModule::new);
        addModule("storagedrawers", () -> StorageDrawersModule::new);
        addModule("tropicraft", () -> TropicraftModule::new);
        addModule("twilightforest", () -> TwilightForestModule::new);
        addModule("valhelsia_furniture", () -> ValhelsiaFurnitureModule::new);
        addModule("valhelsia_structures", () -> ValhelsiaStructuresModule::new);
        addModule("villagersplus", () -> VillagersPlusModule::new);
        addModule("woodster", () -> WoodsterModule::new);
        addModule("woodworks", () -> WoodworksModule::new);
        if (ModList.get().getModContainerById("workshop_for_handsome_adventurer") // Won't enabled if version is 1.14.7
                .map(v->v.getModInfo().getVersion()).toString().compareTo("1.14.7") < 0) {
            addModule("workshop_for_handsome_adventurer", () -> WorkshopForHandsomeAdventurerModule::new);
        }
        addModule("xercamod", () -> XercaModule::new);

        // ========================================== WORK IN PROGRESS ============================================== \\
//        addModule("carpenter", () -> CarpenterModule::new);

        // ======================================== DISABLED FOR A REASON =========================================== \\

        // =============================== DISABLED UNTIL CUSTOM BLOCK MODELS WORK ================================== \\
            // addModule("graveyard", () -> GraveyardModule::new);


// ===================================================== OTHERS ===================================================== \\
        if (PlatformHelper.getEnv().isClient()) {
            EveryCompatClient.commonInit();
        }

        FMLJavaModLoadingContext.get().getModEventBus().register(this);
        MinecraftForge.EVENT_BUS.addListener(EveryCompatForge::onRemap);
        MinecraftForge.EVENT_BUS.addListener(EveryCompatForge::onDataSync);
        if (PlatformHelper.isDev()) {
            MinecraftForge.EVENT_BUS.addListener(EveryCompatForge::itemTooltipEvent);
        }

        forAllModules(CompatModule::onModInit);
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void itemTooltipEvent(ItemTooltipEvent event) {
        EveryCompatClient.onItemTooltip(event.getItemStack(),event.getFlags(), event.getToolTip());

    }

    @SubscribeEvent
    public void onCommonSetup(FMLCommonSetupEvent event) {
        this.commonSetup();
    }

    public static void onDataSync(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer s)EveryCompat.sendPacket(s);

    }

    public static void onRemap(MissingMappingsEvent event) {
        for (var mapping : event.getMappings(Registry.BLOCK_ENTITY_TYPE_REGISTRY, EveryCompat.MOD_ID)) {
            ResourceLocation key = mapping.getKey();
            String path = key.getPath();
            for (var m : EveryCompat.ACTIVE_MODULES.values()) {
                if (path.startsWith(m.shortenedId() + "_")) {
                    String newPath = path.substring((m.shortenedId() + "_").length());
                    ResourceLocation newId = new ResourceLocation(m.getModId(), newPath);
                    Optional<BlockEntityType<?>> optional = Registry.BLOCK_ENTITY_TYPE.getOptional(newId);
                    optional.ifPresent(mapping::remap);
                    break;
                }
            }
        }
    }
}
