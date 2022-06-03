package net.mehvahdjukaar.every_compat;


import architectspalette.core.ArchitectsPalette;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.api.WoodGoodAPI;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.misc.CustomRecipeLoader;
import net.mehvahdjukaar.every_compat.modules.CompatModule;
import net.mehvahdjukaar.every_compat.modules.another_furniture.AFMS;
import net.mehvahdjukaar.every_compat.modules.architect_palette.ArchitectsPaletteModule;
import net.mehvahdjukaar.every_compat.modules.create.CrS;
import net.mehvahdjukaar.every_compat.modules.deco_block.DBS;
import net.mehvahdjukaar.every_compat.modules.deco_block.DecoBlocksModule;
import net.mehvahdjukaar.every_compat.modules.farmersdelight.FDS;
import net.mehvahdjukaar.every_compat.modules.twigs.TwigsModuleS;
import net.mehvahdjukaar.selene.block_set.BlockSetManager;
import net.mehvahdjukaar.selene.block_set.leaves.LeavesType;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.mehvahdjukaar.selene.resourcepack.RPUtils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.moddingplayground.twigs.Twigs;
import net.moddingplayground.twigs.block.TableBlock;
import net.moddingplayground.twigs.init.TwigsBlocks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Author: MehVahdJukaar
 */
@Mod(WoodGood.MOD_ID)
public class WoodGood {
    public static final String MOD_ID = "everycomp";

    public static ResourceLocation res(String name) {
        return new ResourceLocation(MOD_ID, name);
    }

    public static final Logger LOGGER = LogManager.getLogger();

    public static final List<CompatModule> ACTIVE_MODULES = new ArrayList<>();

    public static final List<CompatMod> COMPETITOR_MODS = new ArrayList<>();

    public static void forAllModules(Consumer<CompatModule> action) {
        ACTIVE_MODULES.forEach(action);
    }

    public static ServerDynamicResourcesHandler SERVER_RESOURCES = null;
    public static ClientDynamicResourcesHandler CLIENT_RESOURCES = null;

    public WoodGood() {

        //addCompetitorMod("much_more_mod_compat");
        addCompetitorMod("compatoplenty", List.of("biomesoplenty"));
        addCompetitorMod("decorative_compat", List.of("biomesoplenty"));
        addCompetitorMod("compat_makeover", List.of("biomemakeover"));

        //addModule("decorative_blocks", () -> DecoBlocksModule::new);
        // addModule("twigs", () -> TwigsModuleS::new);
         addModule("another_furniture", () -> AFMS::new);
         addModule("farmersdelight", () -> FDS::new);
         addModule("decorative_blocks", () -> DBS::new);
         addModule("architects_palette", () -> ArchitectsPaletteModule::new);
         addModule("create", () -> CrS::new);


        //  addModule("quark", () -> QuarkModule::new);
        //  addModule("another_furniture", () -> AnotherFurnitureModule::new);
        //   addModule("twilightforest", () -> TwilightForestModule::new);
        //  addModule("farmersdelight", () -> FarmersDelightModule::new);
        //   addModule("create", () -> CreateModule::new);

        ACTIVE_MODULES.forEach(m -> WoodGood.LOGGER.info("Loaded {}", m.toString()));

        BlockSetManager.addBlockSetRegistrationCallback(this::registerWoodStuff, Block.class, WoodType.class);
        BlockSetManager.addBlockSetRegistrationCallback(this::registerLeavesStuff, Block.class, LeavesType.class);

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        //bus.addListener(WoodGood::init);
        bus.register(this);

        MinecraftForge.EVENT_BUS.addListener(CustomRecipeLoader::onEarlyPackLoad);

        SERVER_RESOURCES = new ServerDynamicResourcesHandler();
        SERVER_RESOURCES.register(bus);

        if (FMLEnvironment.dist == Dist.CLIENT) {
            CLIENT_RESOURCES = new ClientDynamicResourcesHandler();
            CLIENT_RESOURCES.register(bus);
        } else CLIENT_RESOURCES = null;

    }


    private void addCompetitorMod(String modId, List<String> supportedMods) {
        if (ModList.get().isLoaded(modId)) COMPETITOR_MODS.add(new CompatMod(modId, supportedMods));
    }

    private void addModule(String modId, Supplier<Function<String, CompatModule>> moduleFactory) {
        if (ModList.get().isLoaded(modId)) ACTIVE_MODULES.add(moduleFactory.get().apply(modId));
    }

    @SubscribeEvent
    public void init(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ACTIVE_MODULES.forEach(CompatModule::onModSetup);
        });
    }

    public void registerWoodStuff(RegistryEvent.Register<Block> event, Collection<WoodType> woods) {
        var reg = event.getRegistry();
        ACTIVE_MODULES.forEach(m -> m.registerWoodBlocks(reg, woods));
    }

    public void registerLeavesStuff(RegistryEvent.Register<Block> event, Collection<LeavesType> leaves) {
        var reg = event.getRegistry();
        ACTIVE_MODULES.forEach(m -> m.registerLeavesBlocks(reg, leaves));
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        ACTIVE_MODULES.forEach(m -> m.registerItems(event.getRegistry()));
    }

    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event) {

    }

    @SubscribeEvent
    public void registerTiles(RegistryEvent.Register<BlockEntityType<?>> event) {
        ACTIVE_MODULES.forEach(m -> m.registerTiles(event.getRegistry()));
    }

    @SubscribeEvent
    public void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
        ACTIVE_MODULES.forEach(m -> m.registerEntities(event.getRegistry()));
    }

    public record CompatMod(String modId, List<String> supportedMods) {
    }


}
