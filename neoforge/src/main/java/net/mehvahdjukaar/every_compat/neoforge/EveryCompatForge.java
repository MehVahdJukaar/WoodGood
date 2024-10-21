package net.mehvahdjukaar.every_compat.neoforge;

import net.mehvahdjukaar.every_compat.ECNetworking;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.EveryCompatClient;
import net.mehvahdjukaar.every_compat.api.CompatModule;
import net.mehvahdjukaar.every_compat.configs.ModConfigs;
import net.mehvahdjukaar.every_compat.modules.farmersdelight.FarmersDelightModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.abnormal.BoatLoadModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.abnormal.WoodworksModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.absent_by_design.AbsentByDesignModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.architect_palette.ArchitectsPaletteModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.beautify_decorate.BeautifyDecorateModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.builders_delight.BuildersDelightModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.buildersaddition.BuildersAdditionModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.building_but_better.BuildingButBetterModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.corail_pillar.CorailPillarModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.create.CreateModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.decoration_delight.DecorationDelightModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.dramaticdoors.DramaticDoorsMacawModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.dramaticdoors.DramaticDoorsModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.functional_storage.FunctionalStorageModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.infinitybuttons.InfinityButtonsModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.just_a_raft.JustARaftModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.lauchs.LauchsShuttersModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.lieonlion.MoreChestVariantsModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.lieonlion.MoreCraftingTablesModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.lightmans_currency.LightmansCurrencyModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.mcaw.*;
import net.mehvahdjukaar.every_compat.modules.neoforge.missing_wilds.MissingWildModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.more.MoreCraftingTablesForForgeModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.mosaic_carpentry.MosaicCarpentryModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.mrcrayfish_furniture.MightyMailModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.mrcrayfish_furniture.MrCrayfishFurnitureModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.oreberries_replanted.OreberriesReplantedModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.pokecube.PokecubeLegendsModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.premium_wood.PremiumWoodModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.redeco.ReDecoModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.regions_unexplored.RegionsUnexploredModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.storagedrawers.StorageDrawersModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.timber_frames.TimberFramesModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.tropicraft.TropicraftModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.twilightforest.TwilightForestModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.valhelsia.ValhelsiaStructuresModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.variants.VariantCraftingTablesModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.variants.VariantVanillaBlocksModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.woodster.WoodsterModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.workshop.WorkshopForHandsomeAdventurerModule;
import net.mehvahdjukaar.every_compat.modules.neoforge.xerca.XercaModule;
import net.mehvahdjukaar.every_compat.modules.stylish_stiles.StylishStilesModule;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.crafting.CraftingHelper;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.neoforge.event.entity.player.PlayerNegotiationEvent;

/**
 * Author: MehVahdJukaar
 */
@Mod(EveryCompat.MOD_ID)
public class EveryCompatForge extends EveryCompat {
    public static final String MOD_ID = EveryCompat.MOD_ID;

    public EveryCompatForge(IEventBus bus) {
        this.commonInit();

        CraftingHelper.register(new BlockTypeEnabledCondition.Serializer());

//!!================================================ Add Modules ==================================================== \\
        addModule("absentbydesign", () -> AbsentByDesignModule::new);
        addModule("architects_palette", () -> ArchitectsPaletteModule::new);
        addModule("beautify", () -> BeautifyDecorateModule::new);
        addModule("boatload", () -> BoatLoadModule::new);
        addModule("buildersaddition", () -> BuildersAdditionModule::new);
        addModule("bbb", () -> BuildingButBetterModule::new);
        addModule("buildersdelight", () -> BuildersDelightModule::new);
        addModule("cfm", () -> MrCrayfishFurnitureModule::new);
        addModule("corail_pillar", () -> CorailPillarModule::new);
        addModule("create", () -> CreateModule::new);
        addModule("decoration_delight", () -> DecorationDelightModule::new);
        addModule("dramaticdoors", () -> DramaticDoorsModule::new);
        addModule("farmersdelight", () -> FarmersDelightModule::new);
        addModule("functionalstorage", () -> FunctionalStorageModule::new);
        addModule("infinitybuttons", () -> InfinityButtonsModule::new);
        addModule("justaraftmod", () -> JustARaftModule::new);
        addModule("lolmcv", () -> MoreChestVariantsModule::new);
        addModule("mctb", () -> MoreCraftingTablesForForgeModule::new);
        addModule("mighty_mail", () -> MightyMailModule::new);
        addModule("missingwilds", () -> MissingWildModule::new);
        addModule("mosaic_carpentry", () -> MosaicCarpentryModule::new);
        addModule("oreberriesreplanted", () -> OreberriesReplantedModule::new);
        addModule("lightmanscurrency", () -> LightmansCurrencyModule::new);
        addModule("pokecube_legends", () -> PokecubeLegendsModule::new);
        addModule("premium_wood", () -> PremiumWoodModule::new);
        addModule("redeco", () -> ReDecoModule::new);
        addModule("regions_unexplored", () -> RegionsUnexploredModule::new);
        addModule("shutter", () -> LauchsShuttersModule::new);
        addModule("storagedrawers", () -> StorageDrawersModule::new);
        addModule("stylishstiles", () -> StylishStilesModule::new);
        addModule("timber_frames", () -> TimberFramesModule::new);
        addModule("tropicraft", () -> TropicraftModule::new);
        addModule("twilightforest", () -> TwilightForestModule::new);
        addModule("valhelsia_structures", () -> ValhelsiaStructuresModule::new);
        addModule("variantvanillablocks", () -> VariantVanillaBlocksModule::new);
        addModule("vct", () -> VariantCraftingTablesModule::new);
        addModule("woodster", () -> WoodsterModule::new);
        addModule("woodworks", () -> WoodworksModule::new);
        addModule("workshop_for_handsome_adventurer", () -> WorkshopForHandsomeAdventurerModule::new);
        addModule("xercamod", () -> XercaModule::new);
        if (PlatHelper.isModLoaded("mcwdoors")) {
            addModule("dramaticdoors", () -> DramaticDoorsMacawModule::new);
        }
        addModule("lolmct", () -> MoreCraftingTablesModule::new);

        // ========================================= Macaw's ======================================================== \\
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

//!!============================================= DISABLED FOR A REASON ============================================= \\

//        addModule("graveyard", () -> GraveyardModule::new); // Disabled until custom block models work
//        addModule("productivebees", () -> ProductiveBeesModule::new); //WIP: class for both beehive have major changes

//!!================================================= OTHERS ======================================================== \\
        NeoForge.EVENT_BUS.register(this);

        forAllModules(CompatModule::onModInit);

        if (PlatHelper.getPhysicalSide().isClient()) {
            EveryCompatClient.init();
        }
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
            for (var m : EveryCompat.ACTIVE_MODULES.values()) {
                if (path.startsWith(m.shortenedId() + "_")) {
                    String newPath = path.substring((m.shortenedId() + "_").length());
                    ResourceLocation newId = ResourceLocation.parse(m.getModId(), newPath);
                    Optional<BlockEntityType<?>> optional = BuiltInRegistries.BLOCK_ENTITY_TYPE.getOptional(newId);
                    optional.ifPresent(mapping::remap);
                    break;
                }
            }
        }
    }*/


    @SubscribeEvent
    public void onPlayerNegotiation(PlayerNegotiationEvent playerNegotiationEvent) {
        if (ModConfigs.CHECK_PACKET.get()) {
            //TODO: add back
            /*
            ((ChannelHandlerImpl) ECNetworking.CHANNEL).channel.sendTo(new ECNetworking.S2CModVersionCheckMessage(),
                    playerNegotiationEvent.getConnection(),
                    NetworkDirection.LOGIN_TO_CLIENT
            );*/
        }
    }
}
