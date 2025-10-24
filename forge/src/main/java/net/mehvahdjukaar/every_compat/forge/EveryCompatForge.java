package net.mehvahdjukaar.every_compat.forge;

import net.mehvahdjukaar.every_compat.ECNetworking;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.EveryCompatClient;
import net.mehvahdjukaar.every_compat.EveryCompatCommon;
import net.mehvahdjukaar.every_compat.configs.ECConfigs;
import net.mehvahdjukaar.every_compat.modules.forge.abnormal.BoatLoadModule;
import net.mehvahdjukaar.every_compat.modules.forge.abnormal.WoodworksModule;
import net.mehvahdjukaar.every_compat.modules.forge.absent_by_design.AbsentByDesignModule;
import net.mehvahdjukaar.every_compat.modules.forge.architect_palette.ArchitectsPaletteModule;
import net.mehvahdjukaar.every_compat.modules.forge.beautify_decorate.BeautifyDecorateModule;
import net.mehvahdjukaar.every_compat.modules.forge.blocks_plus.BlocksPlusModule;
import net.mehvahdjukaar.every_compat.modules.forge.builders_delight.BuildersDelightModule;
import net.mehvahdjukaar.every_compat.modules.forge.buildersaddition.BuildersAdditionModule;
import net.mehvahdjukaar.every_compat.modules.forge.building_but_better.BuildingButBetterModule;
import net.mehvahdjukaar.every_compat.modules.forge.corail_pillar.CorailPillarModule;
import net.mehvahdjukaar.every_compat.modules.forge.create.CreateModule;
import net.mehvahdjukaar.every_compat.modules.forge.decoration_delight.DecorationDelightModule;
import net.mehvahdjukaar.every_compat.modules.forge.dramaticdoors.DramaticDoorsMacawModule;
import net.mehvahdjukaar.every_compat.modules.forge.dramaticdoors.DramaticDoorsModule;
import net.mehvahdjukaar.every_compat.modules.forge.excessive_building.ExcessiveBuildingModule;
import net.mehvahdjukaar.every_compat.modules.forge.functional_storage.FunctionalStorageModule;
import net.mehvahdjukaar.every_compat.modules.forge.gensokyo_delight.YoukaisHomecomingModule;
import net.mehvahdjukaar.every_compat.modules.forge.infinitybuttons.InfinityButtonsModule;
import net.mehvahdjukaar.every_compat.modules.forge.just_a_raft.JustARaftModule;
import net.mehvahdjukaar.every_compat.modules.forge.lauchs.LauchsShuttersModule;
import net.mehvahdjukaar.every_compat.modules.forge.lightmans_currency.LightmansCurrencyModule;
import net.mehvahdjukaar.every_compat.modules.forge.mcaw.*;
import net.mehvahdjukaar.every_compat.modules.forge.more.MoreCraftingTablesForForgeModule;
import net.mehvahdjukaar.every_compat.modules.forge.mosaic_carpentry.MosaicCarpentryModule;
import net.mehvahdjukaar.every_compat.modules.forge.mrcrayfish.MightyMailModule;
import net.mehvahdjukaar.every_compat.modules.forge.mrcrayfish.MrCrayfishFurnitureModule;
import net.mehvahdjukaar.every_compat.modules.forge.nosiphus.NosiphusFurnitureModule;
import net.mehvahdjukaar.every_compat.modules.forge.oreberries_replanted.OreberriesReplantedModule;
import net.mehvahdjukaar.every_compat.modules.forge.pokecube.PokecubeLegendsModule;
import net.mehvahdjukaar.every_compat.modules.forge.premium_wood.PremiumWoodModule;
import net.mehvahdjukaar.every_compat.modules.forge.redeco.ReDecoModule;
import net.mehvahdjukaar.every_compat.modules.forge.regions_unexplored.RegionsUnexploredModule;
import net.mehvahdjukaar.every_compat.modules.forge.smidgeon_o_bliss.SmidgeonOBlissModule;
import net.mehvahdjukaar.every_compat.modules.forge.storage_delight.StorageDelightModule;
import net.mehvahdjukaar.every_compat.modules.forge.timber_frames.TimberFramesModule;
import net.mehvahdjukaar.every_compat.modules.forge.tropicraft.TropicraftModule;
import net.mehvahdjukaar.every_compat.modules.forge.twilightforest.TwilightForestModule;
import net.mehvahdjukaar.every_compat.modules.forge.ultimate_car.UltimateCarModule;
import net.mehvahdjukaar.every_compat.modules.forge.unusual_furniture.UnusualFurnitureModule;
import net.mehvahdjukaar.every_compat.modules.forge.valhelsia.ValhelsiaStructuresModule;
import net.mehvahdjukaar.every_compat.modules.forge.variants.VariantCraftingTablesModule;
import net.mehvahdjukaar.every_compat.modules.forge.woodster.WoodsterModule;
import net.mehvahdjukaar.every_compat.modules.forge.workshop.WorkshopForHandsomeAdventurerModule;
import net.mehvahdjukaar.every_compat.modules.forge.xerca.XercaModule;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.platform.network.forge.ChannelHandlerImpl;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerNegotiationEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.registries.MissingMappingsEvent;

import java.util.Optional;

import static net.mehvahdjukaar.every_compat.EveryCompat.addIfLoaded;
import static net.mehvahdjukaar.every_compat.EveryCompat.forAllModules;
import static net.mehvahdjukaar.every_compat.configs.UnsafeDisablerConfigs.INCLUDE_ALL_WOOD_MODULES;

/**
 * Author: MehVahdJukaar
 */
@Mod(EveryCompat.MOD_ID)
public class EveryCompatForge extends EveryCompatCommon {

    public EveryCompatForge() {
        this.initialize();

        CraftingHelper.register(new BlockTypeEnabledCondition.Serializer());
        MinecraftForge.EVENT_BUS.register(this);

        if (PlatHelper.getPhysicalSide().isClient()) {
            EveryCompatForgeClient.init();
        }

        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        LegacyRemapper.init(modBus);
    }

    @Override
    protected void addModules() {
        super.addModules();

//!! =============================================== Add Modules ==================================================== \\

        if (INCLUDE_ALL_WOOD_MODULES.get()) {

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
            if (PlatHelper.isModLoaded("bbb")) {
                if (PlatHelper.getModVersion("bbb").contains("1.0.1") || PlatHelper.getModVersion("bbb").contains("1.1.1"))
                    addIfLoaded("bbb", () -> BuildingButBetterModule::new);
            }
            addIfLoaded("beautify", () -> BeautifyDecorateModule::new);
            addIfLoaded("blocksplus", () -> BlocksPlusModule::new);
            addIfLoaded("boatload", () -> BoatLoadModule::new);
            addIfLoaded("buildersaddition", () -> BuildersAdditionModule::new);
            addIfLoaded("buildersdelight", () -> BuildersDelightModule::new);
            addIfLoaded("car", () -> UltimateCarModule::new);
            addIfLoaded("cfm", () -> MrCrayfishFurnitureModule::new);
            addIfLoaded("corail_pillar", () -> CorailPillarModule::new);
            addIfLoaded("create", () -> CreateModule::new);
            addIfLoaded("decoration_delight", () -> DecorationDelightModule::new);
            addIfLoaded("dramaticdoors", () -> DramaticDoorsModule::new);
            addIfLoaded("excessive_building", () -> ExcessiveBuildingModule::new);
            addIfLoaded("functionalstorage", () -> FunctionalStorageModule::new);
            addIfLoaded("infinitybuttons", () -> InfinityButtonsModule::new);
            addIfLoaded("justaraftmod", () -> JustARaftModule::new);
            addIfLoaded("lightmanscurrency", () -> LightmansCurrencyModule::new);
            addIfLoaded("mctb", () -> MoreCraftingTablesForForgeModule::new);
            addIfLoaded("mighty_mail", () -> MightyMailModule::new);
            addIfLoaded("mosaic_carpentry", () -> MosaicCarpentryModule::new);
            addIfLoaded("nfm", () -> NosiphusFurnitureModule::new);
            addIfLoaded("oreberriesreplanted", () -> OreberriesReplantedModule::new);
            addIfLoaded("pokecube_legends", () -> PokecubeLegendsModule::new);
            addIfLoaded("premium_wood", () -> PremiumWoodModule::new);
            addIfLoaded("redeco", () -> ReDecoModule::new);
            addIfLoaded("regions_unexplored", () -> RegionsUnexploredModule::new);
            addIfLoaded("shutter", () -> LauchsShuttersModule::new);
            addIfLoaded("sob", () -> SmidgeonOBlissModule::new);
            addIfLoaded("storagedelight", () -> StorageDelightModule::new);
            addIfLoaded("timber_frames", () -> TimberFramesModule::new);
            addIfLoaded("tropicraft", () -> TropicraftModule::new);
            addIfLoaded("twilightforest", () -> TwilightForestModule::new);
            addIfLoaded("unusual_furniture", () -> UnusualFurnitureModule::new);
            addIfLoaded("valhelsia_structures", () -> ValhelsiaStructuresModule::new);
            addIfLoaded("vct", () -> VariantCraftingTablesModule::new);
            addIfLoaded("woodster", () -> WoodsterModule::new);
            addIfLoaded("woodworks", () -> WoodworksModule::new);
            addIfLoaded("workshop_for_handsome_adventurer", () -> WorkshopForHandsomeAdventurerModule::new);
            addIfLoaded("xercamod", () -> XercaModule::new);
            addIfLoaded("youkaishomecoming", () -> YoukaisHomecomingModule::new);

            if (PlatHelper.isModLoaded("mcwdoors")) addIfLoaded("dramaticdoors", () -> DramaticDoorsMacawModule::new);

        }

// ============================================== DISABLED FOR A REASON ============================================= \\
//        addIfLoaded("graveyard", () -> GraveyardModule::new); // Disabled until custom block models work
//        addIfLoaded("productivebees", () -> ProductiveBeesModule::new); //WIP: class for both beehive have major changes

    }


    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void itemTooltipEvent(ItemTooltipEvent event) {
        // Remove the [Debug] strings from ItemToolTip
        if (PlatHelper.isDev()) {
            event.getToolTip().removeIf(line ->
                    line.getString().matches(".*\\[Debug\\] Item Tags:.*") || line.getString().matches(".*\\[Debug\\] Block Tags:.*")
            );
        }

        EveryCompatClient.onItemTooltip(event.getItemStack(), event.getFlags(), event.getToolTip());
    }

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
    }


    @SubscribeEvent
    public void onPlayerNegotiation(PlayerNegotiationEvent playerNegotiationEvent) {
        if (ECConfigs.CHECK_PACKET.get()) {
            ((ChannelHandlerImpl) ECNetworking.CHANNEL).channel.sendTo(new ECNetworking.S2CModVersionCheckMessage(),
                    playerNegotiationEvent.getConnection(),
                    NetworkDirection.LOGIN_TO_CLIENT
            );
        }
    }
}
