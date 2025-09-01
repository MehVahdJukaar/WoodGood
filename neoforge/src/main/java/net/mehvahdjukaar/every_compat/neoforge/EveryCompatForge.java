package net.mehvahdjukaar.every_compat.neoforge;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.EveryCompatClient;
import net.mehvahdjukaar.every_compat.EveryCompatCommon;
import net.mehvahdjukaar.every_compat.configs.ECConfigs;
import net.mehvahdjukaar.every_compat.modules.neoforge.abnormal.BoatLoadModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.abnormal.WoodworksModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.absent_by_design.AbsentByDesignModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.architect_palette.ArchitectsPaletteModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.beautify_decorate.BeautifyDecorateModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.bibliocraft.BibliocraftLegacyModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.builders_delight.BuildersDelightModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.buildersaddition.BuildersAdditionModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.building_but_better.BuildingButBetterModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.corail_pillar.CorailPillarModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.create.CreateModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.decoration_delight.DecorationDelightModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.dramaticdoors.DramaticDoorsMacawModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.dramaticdoors.DramaticDoorsModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.excessive_building.ExcessiveBuildingModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.functional_storage.FunctionalStorageModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.infinitybuttons.InfinityButtonsModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.just_a_raft.JustARaftModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.lauchs.LauchsShuttersModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.lightmans_currency.LightmansCurrencyModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.mcaw.*;
import net.mehvahdjukaar.every_compat.modules.neoforge.more.MoreCraftingTablesForForgeModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.mosaic_carpentry.MosaicCarpentryModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.mrcrayfish.MightyMailModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.oreberries_replanted.OreberriesReplantedModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.pokecube.PokecubeAOIModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.premium_wood.PremiumWoodModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.redeco.ReDecoModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.regions_unexplored.RegionsUnexploredModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.timber_frames.TimberFramesModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.tropicraft.TropicraftModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.twilightforest.TwilightForestModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.valhelsia.ValhelsiaStructuresModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.variants.VariantCraftingTablesModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.woodster.WoodsterModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.workshop.WorkshopForHandsomeAdventurerModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.xerca.XercaModule;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.neoforge.event.entity.player.PlayerNegotiationEvent;

import java.lang.ref.WeakReference;

import static net.mehvahdjukaar.every_compat.EveryCompat.addIfLoaded;

/**
 * Author: MehVahdJukaar
 */
@Mod(EveryCompat.MOD_ID)
public class EveryCompatForge extends EveryCompatCommon {
    private static WeakReference<IEventBus> BUS = new WeakReference<>(null);

    public EveryCompatForge(IEventBus bus) {
        RegHelper.startRegisteringFor(bus);
        BUS = new WeakReference<>(bus);
        this.initialize();

        NeoForge.EVENT_BUS.register(this);

        if (PlatHelper.getPhysicalSide().isClient()) {
            EveryCompatForgeClient.init();
        }
    }

    @Override
    protected void addModules() {
        super.addModules();

//!! =============================================== Add Modules ==================================================== \\

        // ========================================= MACAW's ======================================================== \\
        addIfLoaded("mcwbridges", () -> MacawBridgesModule::new);
        addIfLoaded("mcwdoors", () -> MacawDoorsModule::new);
        addIfLoaded("mcwfences", () -> MacawFencesModule::new);
        addIfLoaded("mcwfurnitures", () -> MacawFurnitureModule::new);
        addIfLoaded("mcwlights", () -> MacawLightsModule::new);
        addIfLoaded("mcwpaths", () -> MacawPathsModule::new);
        addIfLoaded("mcwroofs", () -> MacawRoofsModule::new);
        addIfLoaded("mcwtrpdoors", () -> MacawTrapdoorsModule::new);
        addIfLoaded("mcwwindows", () -> MacawWindowsModule::new);
        addIfLoaded("mcwstairs", () -> MacawStairsModule::new);

        // ========================================= GENERAL ======================================================== \\
        addIfLoaded("absentbydesign", () -> AbsentByDesignModule::new);
        addIfLoaded("architects_palette", () -> ArchitectsPaletteModule::new);
        addIfLoaded("beautify", () -> BeautifyDecorateModule::new);
        addIfLoaded("bibliocraft", () -> BibliocraftLegacyModule::new);
        addIfLoaded("boatload", () -> BoatLoadModule::new);
        addIfLoaded("buildersaddition", () -> BuildersAdditionModule::new);
        addIfLoaded("bbb", () -> BuildingButBetterModule::new);
        addIfLoaded("buildersdelight", () -> BuildersDelightModule::new);
        addIfLoaded("corail_pillar", () -> CorailPillarModule::new);
        addIfLoaded("decoration_delight", () -> DecorationDelightModule::new);
        addIfLoaded("dramaticdoors", () -> DramaticDoorsModule::new);
        addIfLoaded("excessive_building", () -> ExcessiveBuildingModule::new);
        addIfLoaded("functionalstorage", () -> FunctionalStorageModule::new);
        addIfLoaded("infinitybuttons", () -> InfinityButtonsModule::new);
        addIfLoaded("justaraftmod", () -> JustARaftModule::new);
        addIfLoaded("mctb", () -> MoreCraftingTablesForForgeModule::new);
        addIfLoaded("mighty_mail", () -> MightyMailModule::new);
        addIfLoaded("mosaic_carpentry", () -> MosaicCarpentryModule::new);
        addIfLoaded("oreberriesreplanted", () -> OreberriesReplantedModule::new);
        addIfLoaded("lightmanscurrency", () -> LightmansCurrencyModule::new);
        addIfLoaded("pokecube_legends", () -> PokecubeAOIModule::new);
        addIfLoaded("premium_wood", () -> PremiumWoodModule::new);
        addIfLoaded("redeco", () -> ReDecoModule::new);
        addIfLoaded("regions_unexplored", () -> RegionsUnexploredModule::new);
        addIfLoaded("shutter", () -> LauchsShuttersModule::new);
        addIfLoaded("timber_frames", () -> TimberFramesModule::new);
        addIfLoaded("tropicraft", () -> TropicraftModule::new);
        addIfLoaded("twilightforest", () -> TwilightForestModule::new);
        addIfLoaded("valhelsia_structures", () -> ValhelsiaStructuresModule::new);
//        addIfLoaded("variantvanillablocks", () -> VariantVanillaBlocksModule::new); //!! NOT-AVAILABLE
        addIfLoaded("vct", () -> VariantCraftingTablesModule::new);
        addIfLoaded("woodster", () -> WoodsterModule::new);
        addIfLoaded("woodworks", () -> WoodworksModule::new);
        addIfLoaded("workshop_for_handsome_adventurer", () -> WorkshopForHandsomeAdventurerModule::new);
        addIfLoaded("xercamod", () -> XercaModule::new);

        if (PlatHelper.isModLoaded("mcwdoors")) {
            addIfLoaded("dramaticdoors", () -> DramaticDoorsMacawModule::new);
        }

        addIfLoaded("create", () -> CreateModule::new); //TEMP: DISABLED due to version difference

// ============================================== DISABLED FOR A REASON ============================================= \\
//        addIfLoaded("graveyard", () -> GraveyardModule::new); // Disabled until custom block models work
//        addIfLoaded("productivebees", () -> ProductiveBeesModule::new); //WIP: class for both beehive have major changes

    }

    public static IEventBus getModEventBus() {
        return BUS.get();
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void itemTooltipEvent(ItemTooltipEvent event) {
        EveryCompatClient.onItemTooltip(event.getItemStack(), event.getContext(), event.getFlags(), event.getToolTip());
    }
/*
    @SubscribeEvent
    public void onRemap(MissingMappingsEvent event) {
        for (var mapping : event.getMappings(Registries.BLOCK_ENTITY_TYPE, EveryCompat.MOD_ID)) {
            ResourceLocation key = mapping.getKey();
            String path = key.getPath();
            forAllModules(m -> {
                if (path.startsWith(m.shortenedId() + "_")) {
                    String newPath = path.substring((m.shortenedId() + "_").length());
                    ResourceLocation newId = ResourceLocation.fromNamespaceAndPath(m.getModId(), newPath);
                    Optional<BlockEntityType<?>> optional = BuiltInRegistries.BLOCK_ENTITY_TYPE.getOptional(newId);
                    optional.ifPresent(mapping::remap);
                }
            });
        }
    }*/


    @SubscribeEvent
    public void onPlayerNegotiation(PlayerNegotiationEvent playerNegotiationEvent) {
        if (ECConfigs.CHECK_PACKET.get()) {
            /*
            ((ChannelHandlerImpl) NetworkHelper.channel.sendTo(new ECNetworking.S2CModVersionCheckMessage(),
                    playerNegotiationEvent.getConnection(),
                    NetworkDirection.LOGIN_TO_CLIENT
            );
            */
            //TODO: add back
        }
    }
}
