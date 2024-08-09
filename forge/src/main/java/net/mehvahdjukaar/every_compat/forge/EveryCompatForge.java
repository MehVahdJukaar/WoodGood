package net.mehvahdjukaar.every_compat.forge;

import net.mehvahdjukaar.every_compat.ECNetworking;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.EveryCompatClient;
import net.mehvahdjukaar.every_compat.api.CompatModule;
import net.mehvahdjukaar.every_compat.configs.ModConfigs;
import net.mehvahdjukaar.every_compat.modules.forge.abnormal.BoatLoadModule;
import net.mehvahdjukaar.every_compat.modules.forge.abnormal.WoodworksModule;
import net.mehvahdjukaar.every_compat.modules.forge.architect_palette.ArchitectsPaletteModule;
import net.mehvahdjukaar.every_compat.modules.forge.backpacked.BackpackedModule;
import net.mehvahdjukaar.every_compat.modules.forge.builders_delight.BuildersDelightModule;
import net.mehvahdjukaar.every_compat.modules.forge.buildersaddition.BuildersAdditionModule;
import net.mehvahdjukaar.every_compat.modules.forge.building_but_better.BuildingButBetterModule;
import net.mehvahdjukaar.every_compat.modules.forge.create.CreateModule;
import net.mehvahdjukaar.every_compat.modules.forge.decoration_delight.DecorationDelightModule;
import net.mehvahdjukaar.every_compat.modules.forge.dramaticdoors.DramaticDoorsMacawModule;
import net.mehvahdjukaar.every_compat.modules.forge.dramaticdoors.DramaticDoorsModule;
import net.mehvahdjukaar.every_compat.modules.farmersdelight.FarmersDelightModule;
import net.mehvahdjukaar.every_compat.modules.forge.infinitybuttons.InfinityButtonsModule;
import net.mehvahdjukaar.every_compat.modules.forge.lauchs.LauchsShuttersModule;
import net.mehvahdjukaar.every_compat.modules.forge.dawn_of_time.DawnOfTimeModule;
import net.mehvahdjukaar.every_compat.modules.forge.lieonlion.MoreChestVariantsModule;
import net.mehvahdjukaar.every_compat.modules.forge.lieonlion.MoreCraftingTablesModule;
import net.mehvahdjukaar.every_compat.modules.forge.mcaw.*;
import net.mehvahdjukaar.every_compat.modules.forge.missing_wilds.MissingWildModule;
import net.mehvahdjukaar.every_compat.modules.forge.more.MoreCraftingTablesForForgeModule;
import net.mehvahdjukaar.every_compat.modules.forge.mosaic_carpentry.MosaicCarpentryModule;
import net.mehvahdjukaar.every_compat.modules.forge.mrcrayfish_furniture.MightyMailModule;
import net.mehvahdjukaar.every_compat.modules.forge.mrcrayfish_furniture.MrCrayfishFurnitureModule;
import net.mehvahdjukaar.every_compat.modules.forge.pokecube.PokecubeLegendsModule;
import net.mehvahdjukaar.every_compat.modules.forge.premium_wood.PremiumWoodModule;
import net.mehvahdjukaar.every_compat.modules.forge.regions_unexplored.RegionsUnexploredModule;
import net.mehvahdjukaar.every_compat.modules.forge.storagedrawers.StorageDrawersModule;
import net.mehvahdjukaar.every_compat.modules.forge.twilightforest.TwilightForestModule;
import net.mehvahdjukaar.every_compat.modules.forge.valhelsia.ValhelsiaStructuresModule;
import net.mehvahdjukaar.every_compat.modules.forge.variants.VariantCraftingTablesModule;
import net.mehvahdjukaar.every_compat.modules.forge.variants.VariantVanillaBlocksModule;
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
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.NetworkDirection;
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
        addModule("architects_palette", () -> ArchitectsPaletteModule::new);
        addModule("backpacked", () -> BackpackedModule::new);
        addModule("bbb", () -> BuildingButBetterModule::new);
        addModule("boatload", () -> BoatLoadModule::new);
        addModule("buildersaddition", () -> BuildersAdditionModule::new);
        addModule("buildersdelight", () -> BuildersDelightModule::new);
        addModule("cfm", () -> MrCrayfishFurnitureModule::new);
        addModule("create", () -> CreateModule::new);
        addModule("dawnoftimebuilder", () -> DawnOfTimeModule::new);
        addModule("decoration_delight", () -> DecorationDelightModule::new);
        addModule("dramaticdoors", () -> DramaticDoorsModule::new);
        addModule("farmersdelight", () -> FarmersDelightModule::new);
        addModule("infinitybuttons", () -> InfinityButtonsModule::new);
        addModule("lolmcv", () -> MoreChestVariantsModule::new);
        addModule("mctb", () -> MoreCraftingTablesForForgeModule::new);
        addModule("mighty_mail", () -> MightyMailModule::new);
        addModule("missingwilds", () -> MissingWildModule::new);
        addModule("mosaic_carpentry", () -> MosaicCarpentryModule::new);
        addModule("pokecube_legends", () -> PokecubeLegendsModule::new);
        addModule("premium_wood", () -> PremiumWoodModule::new);
        addModule("regions_unexplored", () -> RegionsUnexploredModule::new);
        addModule("shutter", () -> LauchsShuttersModule::new);
        addModule("storagedrawers", () -> StorageDrawersModule::new);
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


        // ========================================== WORK IN PROGRESS ============================================== \\
//        addModule("productivebees", () -> ProductiveBeesModule::new); // class for both beehive have major changes

        // ====================================== DISABLED FOR A REASON ============================================= \\
//        addModule("graveyard", () -> GraveyardModule::new); // Disabled until custom block models work


        MinecraftForge.EVENT_BUS.register(this);

        forAllModules(CompatModule::onModInit);

        if (PlatHelper.getPhysicalSide().isClient()) {
            EveryCompatClient.init();
        }
    }


    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void itemTooltipEvent(ItemTooltipEvent event) {
        EveryCompatClient.onItemTooltip(event.getItemStack(),event.getFlags(), event.getToolTip());

    }

    //public static void onDataSync(PlayerEvent.PlayerLoggedInEvent event) {
    //    if (event.getEntity() instanceof ServerPlayer s)EveryCompat.sendPacket(s);
//
    //  }

    @SubscribeEvent
    public void onRemap(MissingMappingsEvent event) {
        for (var mapping : event.getMappings(Registries.BLOCK_ENTITY_TYPE, EveryCompat.MOD_ID)) {
            ResourceLocation key = mapping.getKey();
            String path = key.getPath();
            for (var m : EveryCompat.ACTIVE_MODULES.values()) {
                if (path.startsWith(m.shortenedId() + "_")) {
                    String newPath = path.substring((m.shortenedId() + "_").length());
                    ResourceLocation newId = new ResourceLocation(m.getModId(), newPath);
                    Optional<BlockEntityType<?>> optional = BuiltInRegistries.BLOCK_ENTITY_TYPE.getOptional(newId);
                    optional.ifPresent(mapping::remap);
                    break;
                }
            }
        }
    }


    @SubscribeEvent
    public void onPlayerNegotiation(PlayerNegotiationEvent playerNegotiationEvent) {
        if(ModConfigs.CHECK_PACKET.get()) {
            ((ChannelHandlerImpl) ECNetworking.CHANNEL).channel.sendTo(new ECNetworking.S2CModVersionCheckMessage(),
                    playerNegotiationEvent.getConnection(),
                    NetworkDirection.LOGIN_TO_CLIENT
            );
        }
    }
}
