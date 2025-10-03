package net.mehvahdjukaar.every_compat.neoforge;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.EveryCompatClient;
import net.mehvahdjukaar.every_compat.EveryCompatCommon;
import net.mehvahdjukaar.every_compat.configs.ECConfigs;
import net.mehvahdjukaar.every_compat.modules.neoforge.abnormal.BoatLoadModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.abnormal.WoodworksModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.absent_by_design.AbsentByDesignModule;
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
import net.mehvahdjukaar.every_compat.modules.neoforge.pokecube.PokecubeAIOModule;
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

import static net.mehvahdjukaar.every_compat.api.EveryCompatAPI.addIfLoaded;
import static net.mehvahdjukaar.every_compat.configs.UnsafeDisablerConfigs.INCLUDE_ALL_WOOD_MODULES;

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

        if (INCLUDE_ALL_WOOD_MODULES.get()) {

            // ========================================= MACAW's ======================================================== \\
            addIfLoaded("mcwbridges", () -> MacawBridgesModule.class);
            addIfLoaded("mcwdoors", () -> MacawDoorsModule.class);
            addIfLoaded("mcwfences", () -> MacawFencesModule.class);
            addIfLoaded("mcwfurnitures", () -> MacawFurnitureModule.class);
            addIfLoaded("mcwlights", () -> MacawLightsModule.class);
            addIfLoaded("mcwpaths", () -> MacawPathsModule.class);
            addIfLoaded("mcwroofs", () -> MacawRoofsModule.class);
            addIfLoaded("mcwtrpdoors", () -> MacawTrapdoorsModule.class);
            addIfLoaded("mcwwindows", () -> MacawWindowsModule.class);
            addIfLoaded("mcwstairs", () -> MacawStairsModule.class);

            // ========================================= GENERAL ======================================================== \\
            addIfLoaded("absentbydesign", () -> AbsentByDesignModule.class);
            addIfLoaded("beautify", () -> BeautifyDecorateModule.class);
            addIfLoaded("bibliocraft", () -> BibliocraftLegacyModule.class);
            addIfLoaded("boatload", () -> BoatLoadModule.class);
            addIfLoaded("buildersaddition", () -> BuildersAdditionModule.class);
            addIfLoaded("bbb", () -> BuildingButBetterModule.class);
            addIfLoaded("buildersdelight", () -> BuildersDelightModule.class);
            addIfLoaded("corail_pillar", () -> CorailPillarModule.class);
            addIfLoaded("decoration_delight", () -> DecorationDelightModule.class);
            addIfLoaded("dramaticdoors", () -> DramaticDoorsModule.class);
            addIfLoaded("excessive_building", () -> ExcessiveBuildingModule.class);
            addIfLoaded("functionalstorage", () -> FunctionalStorageModule.class);
            addIfLoaded("infinitybuttons", () -> InfinityButtonsModule.class);
            addIfLoaded("justaraftmod", () -> JustARaftModule.class);
            addIfLoaded("mctb", () -> MoreCraftingTablesForForgeModule.class);
            addIfLoaded("mighty_mail", () -> MightyMailModule.class);
            addIfLoaded("mosaic_carpentry", () -> MosaicCarpentryModule.class);
            addIfLoaded("oreberriesreplanted", () -> OreberriesReplantedModule.class);
            addIfLoaded("lightmanscurrency", () -> LightmansCurrencyModule.class);
            addIfLoaded("pokecube_legends", () -> PokecubeAIOModule.class);
            addIfLoaded("premium_wood", () -> PremiumWoodModule.class);
            addIfLoaded("redeco", () -> ReDecoModule.class);
            addIfLoaded("regions_unexplored", () -> RegionsUnexploredModule.class);
            addIfLoaded("shutter", () -> LauchsShuttersModule.class);
            addIfLoaded("timber_frames", () -> TimberFramesModule.class);
            addIfLoaded("tropicraft", () -> TropicraftModule.class);
            addIfLoaded("twilightforest", () -> TwilightForestModule.class);
            addIfLoaded("valhelsia_structures", () -> ValhelsiaStructuresModule.class);
//        addIfLoaded("variantvanillablocks", () -> VariantVanillaBlocksModule::new); .class-AVAILABLE
            addIfLoaded("vct", () -> VariantCraftingTablesModule.class);
            addIfLoaded("woodster", () -> WoodsterModule.class);
            addIfLoaded("woodworks", () -> WoodworksModule.class);
            addIfLoaded("workshop_for_handsome_adventurer", () -> WorkshopForHandsomeAdventurerModule.class);
            addIfLoaded("xercamod", () -> XercaModule.class);

            if (PlatHelper.isModLoaded("mcwdoors")) {
                addIfLoaded("dramaticdoors", () -> DramaticDoorsMacawModule.class);
            }

            addIfLoaded("create", () -> CreateModule.class); //TEMP: DISABLED due to version difference
        }

// ============================================== DISABLED FOR A REASON ============================================= \\
//        addIfLoaded("graveyard", () -> GraveyardModule::new); .class until custom block models work
//        addIfLoaded("productivebees", () -> ProductiveBeesModule::new); .class: class for both beehive have major changes

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
