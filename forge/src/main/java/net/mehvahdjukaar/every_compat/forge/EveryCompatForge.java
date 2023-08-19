package net.mehvahdjukaar.every_compat.forge;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.EveryCompatClient;
import net.mehvahdjukaar.every_compat.api.CompatModule;

import net.mehvahdjukaar.every_compat.modules.another_furniture.AnotherFurnitureModule;
import net.mehvahdjukaar.every_compat.modules.camp_chair.CampChairModule;
import net.mehvahdjukaar.every_compat.modules.decorative_blocks.DecorativeBlocksModule;
import net.mehvahdjukaar.every_compat.modules.exline.BarkCarpetsModule;
import net.mehvahdjukaar.every_compat.modules.forge.abnormal.WoodworksModule;
import net.mehvahdjukaar.every_compat.modules.forge.architect_palette.ArchitectsPaletteModule;
import net.mehvahdjukaar.every_compat.modules.forge.backpacked.BackpackedModule;
import net.mehvahdjukaar.every_compat.modules.forge.buildersaddition.BuildersAdditionModule;
import net.mehvahdjukaar.every_compat.modules.forge.create.CreateModule;
import net.mehvahdjukaar.every_compat.modules.forge.dramaticdoors.DramaticDoorsMacawModule;
import net.mehvahdjukaar.every_compat.modules.forge.dramaticdoors.DramaticDoorsModule;
import net.mehvahdjukaar.every_compat.modules.forge.farmersdelight.FarmersDelightModule;
import net.mehvahdjukaar.every_compat.modules.forge.infinitybuttons.InfinityButtonsModule;
import net.mehvahdjukaar.every_compat.modules.forge.lauchs.LauchsShuttersModule;
import net.mehvahdjukaar.every_compat.modules.forge.mcaw.*;
import net.mehvahdjukaar.every_compat.modules.forge.missing_wilds.MissingWildModule;
import net.mehvahdjukaar.every_compat.modules.forge.mosaic_carpentry.MosaicCarpentryModule;
import net.mehvahdjukaar.every_compat.modules.forge.mrcrayfish_furniture.MrCrayfishFurnitureModule;
import net.mehvahdjukaar.every_compat.modules.forge.pokecube.PokecubeLegendsModule;
import net.mehvahdjukaar.every_compat.modules.forge.premium_wood.PremiumWoodModule;
import net.mehvahdjukaar.every_compat.modules.forge.productive_bees.ProductiveBeesModule;
import net.mehvahdjukaar.every_compat.modules.forge.quark.QuarkModule;
import net.mehvahdjukaar.every_compat.modules.forge.storagedrawers.StorageDrawersModule;
import net.mehvahdjukaar.every_compat.modules.forge.twilightforest.TwilightForestModule;
import net.mehvahdjukaar.every_compat.modules.forge.valhelsia.ValhelsiaStructuresModule;
import net.mehvahdjukaar.every_compat.modules.forge.woodster.WoodsterModule;
import net.mehvahdjukaar.every_compat.modules.forge.workshop.WorkshopForHandsomeAdventurerModule;
import net.mehvahdjukaar.every_compat.modules.forge.xerca.XercaModule;
import net.mehvahdjukaar.every_compat.modules.forge.builders_delight.BuildersDelightModule;

import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
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

        addModule("backpacked", () -> BackpackedModule::new);
        addModule("buildersaddition", () -> BuildersAdditionModule::new);
        addModule("cfm", () -> MrCrayfishFurnitureModule::new);

        addModule("create", () -> CreateModule::new);
        addModule("dramaticdoors", () -> DramaticDoorsModule::new);
        if (PlatHelper.isModLoaded("mcwdoors")) {
            addModule("dramaticdoors", () -> DramaticDoorsMacawModule::new);
        }
        addModule("farmersdelight", () -> FarmersDelightModule::new);
        addModule("infinitybuttons", () -> InfinityButtonsModule::new);
        addModule("missingwilds", () -> MissingWildModule::new);
        addModule("mosaic_carpentry", () -> MosaicCarpentryModule::new);
        addModule("pokecube_legends", () -> PokecubeLegendsModule::new);
        addModule("premium_wood", () -> PremiumWoodModule::new);
        addModule("productivebees", () -> ProductiveBeesModule::new);
        addModule("quark", () -> QuarkModule::new);
        addModule("shutter", () -> LauchsShuttersModule::new);
        addModule("storagedrawers", () -> StorageDrawersModule::new);
        addModule("twilightforest", () -> TwilightForestModule::new);
        addModule("valhelsia_structures", () -> ValhelsiaStructuresModule::new);
        addModule("woodster", () -> WoodsterModule::new);
        addModule("woodworks", () -> WoodworksModule::new);
        addModule("mcwbridges", () -> MacawBridgesModule::new);
        addModule("mcwdoors", () -> MacawDoorsModule::new);
        addModule("mcwfences", () -> MacawFencesModule::new);
        addModule("mcwfurnitures", () -> MacawFurnitureModule::new);
        addModule("mcwlights", () -> MacawLightsModule::new);
        addModule("mcwpaths", () -> MacawPathsModule::new);
        addModule("mcwroofs", () -> MacawRoofsModule::new);
        addModule("mcwtrpdoors", () -> MacawTrapdoorsModule::new);
        addModule("mcwwindows", () -> MacawWindowsModule::new);
        addModule("architects_palette", () -> ArchitectsPaletteModule::new);
		addModule("workshop_for_handsome_adventurer", () -> WorkshopForHandsomeAdventurerModule::new);

        addModule("buildersdelight", () -> BuildersDelightModule::new);

        // addModule("graveyard", () -> GraveyardModule::new);

        // Disabled due to block entity complications

        // Disabled until custom block models work
         addModule("xercamod", () -> XercaModule::new);

        if (PlatHelper.getPhysicalSide().isClient()) {
            EveryCompatClient.commonInit();
        }

        FMLJavaModLoadingContext.get().getModEventBus().register(this);
        MinecraftForge.EVENT_BUS.addListener(EveryCompatForge::onRemap);
        MinecraftForge.EVENT_BUS.addListener(EveryCompatForge::onDataSync);

        forAllModules(CompatModule::onModInit);
    }

    @SubscribeEvent
    public void onCommonSetup(FMLCommonSetupEvent event) {
        this.commonSetup();
    }

    public static void onDataSync(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer s)EveryCompat.sendPacket(s);

    }

    public static void onRemap(MissingMappingsEvent event) {
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
}
