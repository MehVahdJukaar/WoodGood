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
import net.neoforged.bus.EventBus;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.IModBusEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.neoforge.event.entity.player.PlayerNegotiationEvent;

import java.lang.ref.WeakReference;

import static net.mehvahdjukaar.every_compat.EveryCompat.addOptionalModule;
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
            addOptionalModule("mcwbridges", () -> MacawBridgesModule.class);
            addOptionalModule("mcwdoors", () -> MacawDoorsModule.class);
            addOptionalModule("mcwfences", () -> MacawFencesModule.class);
            addOptionalModule("mcwfurnitures", () -> MacawFurnitureModule.class);
            addOptionalModule("mcwlights", () -> MacawLightsModule.class);
            addOptionalModule("mcwpaths", () -> MacawPathsModule.class);
            addOptionalModule("mcwroofs", () -> MacawRoofsModule.class);
            addOptionalModule("mcwtrpdoors", () -> MacawTrapdoorsModule.class);
            addOptionalModule("mcwwindows", () -> MacawWindowsModule.class);
            addOptionalModule("mcwstairs", () -> MacawStairsModule.class);

            // ========================================= GENERAL ======================================================== \\
            addOptionalModule("absentbydesign", () -> AbsentByDesignModule.class);
            addOptionalModule("beautify", () -> BeautifyDecorateModule.class);
            addOptionalModule("bibliocraft", () -> BibliocraftLegacyModule.class);
            addOptionalModule("boatload", () -> BoatLoadModule.class);
            addOptionalModule("buildersaddition", () -> BuildersAdditionModule.class);
            addOptionalModule("bbb", () -> BuildingButBetterModule.class);
            addOptionalModule("buildersdelight", () -> BuildersDelightModule.class);
            addOptionalModule("corail_pillar", () -> CorailPillarModule.class);
            addOptionalModule("decoration_delight", () -> DecorationDelightModule.class);
            addOptionalModule("dramaticdoors", () -> DramaticDoorsModule.class);
            addOptionalModule("excessive_building", () -> ExcessiveBuildingModule.class);
            addOptionalModule("functionalstorage", () -> FunctionalStorageModule.class);
            addOptionalModule("infinitybuttons", () -> InfinityButtonsModule.class);
            addOptionalModule("justaraftmod", () -> JustARaftModule.class);
            addOptionalModule("mctb", () -> MoreCraftingTablesForForgeModule.class);
            addOptionalModule("mighty_mail", () -> MightyMailModule.class);
            addOptionalModule("mosaic_carpentry", () -> MosaicCarpentryModule.class);
            addOptionalModule("oreberriesreplanted", () -> OreberriesReplantedModule.class);
            addOptionalModule("lightmanscurrency", () -> LightmansCurrencyModule.class);
            addOptionalModule("pokecube_legends", () -> PokecubeAIOModule.class);
            addOptionalModule("premium_wood", () -> PremiumWoodModule.class);
            addOptionalModule("redeco", () -> ReDecoModule.class);
            addOptionalModule("regions_unexplored", () -> RegionsUnexploredModule.class);
            addOptionalModule("shutter", () -> LauchsShuttersModule.class);
            addOptionalModule("timber_frames", () -> TimberFramesModule.class);
            addOptionalModule("tropicraft", () -> TropicraftModule.class);
            addOptionalModule("twilightforest", () -> TwilightForestModule.class);
            addOptionalModule("valhelsia_structures", () -> ValhelsiaStructuresModule.class);
//        addOptionalModule("variantvanillablocks", () -> VariantVanillaBlocksModule::new); .class-AVAILABLE
            addOptionalModule("vct", () -> VariantCraftingTablesModule.class);
            addOptionalModule("woodster", () -> WoodsterModule.class);
            addOptionalModule("woodworks", () -> WoodworksModule.class);
            addOptionalModule("workshop_for_handsome_adventurer", () -> WorkshopForHandsomeAdventurerModule.class);
            addOptionalModule("xercamod", () -> XercaModule.class);

            if (PlatHelper.isModLoaded("mcwdoors")) {
                addOptionalModule("dramaticdoors", () -> DramaticDoorsMacawModule.class);
            }

            addOptionalModule("create", () -> CreateModule.class); //TEMP: DISABLED due to version difference
        }

// ============================================== DISABLED FOR A REASON ============================================= \\
//        addOptionalModule("graveyard", () -> GraveyardModule::new); .class until custom block models work
//        addOptionalModule("productivebees", () -> ProductiveBeesModule::new); .class: class for both beehive have major changes

    }

    public static IEventBus getModEventBus() {
        return BUS.get();
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void itemTooltipEvent(ItemTooltipEvent event) {
        // Remove the [Debug] strings from ItemToolTip
        if (PlatHelper.isDev()) {
            event.getToolTip().removeIf(line ->
                    line.getString().matches(".*\\[Debug\\] Item Tags:.*") || line.getString().matches(".*\\[Debug\\] Block Tags:.*")
            );
        }

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
