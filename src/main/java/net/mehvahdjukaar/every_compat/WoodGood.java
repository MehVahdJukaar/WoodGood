package net.mehvahdjukaar.every_compat;


import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.configs.BlockTypeEnabledCondition;
import net.mehvahdjukaar.every_compat.configs.EarlyConfigs;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.misc.CustomRecipeLoader;
import net.mehvahdjukaar.every_compat.modules.CompatModule;
import net.mehvahdjukaar.every_compat.modules.another_furniture.AnotherFurnitureModule;
import net.mehvahdjukaar.every_compat.modules.architect_palette.ArchitectsPaletteModule;
import net.mehvahdjukaar.every_compat.modules.backpacked.BackpackedModule;
import net.mehvahdjukaar.every_compat.modules.create.CreateModule;
import net.mehvahdjukaar.every_compat.modules.deco_block.DecorativeBlocksModule;
import net.mehvahdjukaar.every_compat.modules.farmersdelight.FarmersDelightModule;
import net.mehvahdjukaar.every_compat.modules.mcaw.*;
import net.mehvahdjukaar.every_compat.modules.mrcrayfish_furniture.MrCrayfishFurnitureModule;
import net.mehvahdjukaar.every_compat.modules.quark.QuarkModule;
import net.mehvahdjukaar.every_compat.modules.twigs.TwigsModule;
import net.mehvahdjukaar.every_compat.modules.twilightforest.TwilightForestModule;
import net.mehvahdjukaar.every_compat.modules.valhelsia_structures.ValhelsiaStructuresModule;
import net.mehvahdjukaar.selene.block_set.BlockSetManager;
import net.mehvahdjukaar.selene.block_set.leaves.LeavesType;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
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

    public static final Map<String, CompatModule> ACTIVE_MODULES = new LinkedHashMap<>();

    public static final List<CompatMod> COMPAT_MODS = new ArrayList<>();

    public static void forAllModules(Consumer<CompatModule> action) {
        ACTIVE_MODULES.values().forEach(action);
    }

    public static ServerDynamicResourcesHandler SERVER_RESOURCES = null;
    public static ClientDynamicResourcesHandler CLIENT_RESOURCES = null;

    public static CreativeModeTab MOD_TAB = null;

    public WoodGood() {

        //addCompetitorMod("much_more_mod_compat");
        addCompetitorMod("compatoplenty", "biomesoplenty", List.of("twigs", "farmersdelight", "quark"));
        addCompetitorMod("compat_makeover", "biomemakeover", List.of("habitat", "farmersdelight", "quark", "decorative_blocks"));
        addCompetitorMod("decorative_compat", "biomesoplenty", List.of("decorative_blocks"));
        addCompetitorMod("macawsbridgesbop", "mcwbridges", List.of("biomesoplenty"));
        addCompetitorMod("macawbridgesbyg", "mcwbridges", List.of("byg"));

        addCompetitorMod("mcwfencesbop", "mcwfences", List.of("biomesoplenty"));
        addCompetitorMod("mcwfencesbyg", "mcwfences", List.of("byg"));


        /*
        addModule("another_furniture", () -> AnotherFurnitureModule::new);
        addModule("backpacked", () -> BackpackedModule::new);
        addModule("farmersdelight", () -> FarmersDelightModule::new);
        addModule("decorative_blocks", () -> DecorativeBlocksModule::new);
        addModule("architects_palette", () -> ArchitectsPaletteModule::new);
        addModule("twigs", () -> TwigsModule::new);
        addModule("create", () -> CreateModule::new);
        addModule("twilightforest", () -> TwilightForestModule::new);
        addModule("valhelsia_structures", () -> ValhelsiaStructuresModule::new);
        addModule("cfm", () -> MrCrayfishFurnitureModule::new);
        */
        addModule("quark", () -> QuarkModule::new);
        // addModule("quark", () -> LegacyQM::new);
        addModule("mcwwindows", () -> MacawWindowsModule::new);
        addModule("mcwfences", () -> MacawFencesModule::new);
        addModule("mcwbridges", () -> MacawBridgesModule::new);

        addModule("mcwdoors", () -> MacawDoorsModule::new);
        addModule("mcwlights", () -> MacawLightsModule::new);
        addModule("mcwpaths", () -> MacawPathsModule::new);
        addModule("mcwtrpdoors", () -> MacawTrapdoorsModule::new);


        forAllModules(m -> WoodGood.LOGGER.info("Loaded {}", m.toString()));

        BlockSetManager.addBlockSetRegistrationCallback(this::registerWoodStuff, Block.class, WoodType.class);
        BlockSetManager.addBlockSetRegistrationCallback(this::registerLeavesStuff, Block.class, LeavesType.class);

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        //bus.addListener(WoodGood::init);
        bus.register(this);

        MinecraftForge.EVENT_BUS.addListener(CustomRecipeLoader::onEarlyPackLoad);
        MinecraftForge.EVENT_BUS.addGenericListener(Block.class, WoodGood::remapBlocks);
        MinecraftForge.EVENT_BUS.addGenericListener(Item.class, WoodGood::remapItems);
        // MinecraftForge.EVENT_BUS.addGenericListener(WoodGood::remapBlocks);

        SERVER_RESOURCES = new ServerDynamicResourcesHandler();
        SERVER_RESOURCES.register(bus);

        if (FMLEnvironment.dist == Dist.CLIENT) {
            CLIENT_RESOURCES = new ClientDynamicResourcesHandler();
            CLIENT_RESOURCES.register(bus);
        } else CLIENT_RESOURCES = null;

        CraftingHelper.register(new BlockTypeEnabledCondition.Serializer());

    }

    private void addCompetitorMod(String modId, String woodFrom, List<String> blocksFrom) {
        COMPAT_MODS.add(new CompatMod(modId, woodFrom, blocksFrom));
    }

    private void addModule(String modId, Supplier<Function<String, CompatModule>> moduleFactory) {
        if (ModList.get().isLoaded(modId)) ACTIVE_MODULES.put(modId, moduleFactory.get().apply(modId));
    }

    @SubscribeEvent
    public void init(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            forAllModules(CompatModule::onModSetup);
        });
    }

    public void registerWoodStuff(RegistryEvent.Register<Block> event, Collection<WoodType> woods) {
        EarlyConfigs.init();

        LOGGER.info("Registering Compat Wood Blocks");
        var reg = event.getRegistry();
        forAllModules(m -> m.registerWoodBlocks(reg, woods));
    }

    public void registerLeavesStuff(RegistryEvent.Register<Block> event, Collection<LeavesType> leaves) {
        LOGGER.info("Registering Compat Leaves Blocks");
        var reg = event.getRegistry();
        forAllModules(m -> m.registerLeavesBlocks(reg, leaves));
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        forAllModules(m -> m.registerItems(event.getRegistry()));
    }

    @SubscribeEvent
    public void registerTiles(RegistryEvent.Register<BlockEntityType<?>> event) {
        forAllModules(m -> m.registerTiles(event.getRegistry()));
    }

    @SubscribeEvent
    public void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
        forAllModules(m -> m.registerEntities(event.getRegistry()));
    }

    public record CompatMod(String modId, String woodFrom, List<String> blocksFrom) {

    }

    //this can be slow. doesn't matter since it only happens once on boot
    public static void remapBlocks(RegistryEvent.MissingMappings<Block> event) {
        remapEntries(event, ForgeRegistries.BLOCKS);
    }

    //this can be slow. doesn't matter since it only happens once on boot
    public static void remapItems(RegistryEvent.MissingMappings<Item> event) {
        remapEntries(event, ForgeRegistries.ITEMS);
    }

    private static <T extends IForgeRegistryEntry<T>> void remapEntries(RegistryEvent.MissingMappings<T> event, IForgeRegistry<T> blockReg) {
        if (!EarlyConfigs.REMAP_COMPAT.get()) return;
        for (var compatMod : COMPAT_MODS) {
            String woodFrom = compatMod.woodFrom; //ie bop
            label:
            for (var mapping : event.getMappings(compatMod.modId)) {
                //ie: bopcomp:willow_table

                for (String blockFrom : compatMod.blocksFrom) {
                    CompatModule module = ACTIVE_MODULES.get(blockFrom); //if we have target mod
                    // only works for simple modules
                    if (module instanceof SimpleModule simpleModule) {
                        //only works on simple entries
                        for (var entry : simpleModule.getEntries()) {
                            if (entry instanceof SimpleEntrySet se) {
                                //find wood types from that mod id

                                String s = se.getEquivalentBlock(module, mapping.key.getPath(), woodFrom);
                                if (s != null) {
                                    if (blockReg.containsKey(WoodGood.res(s))) {
                                        var b = blockReg.getValue(WoodGood.res(s));
                                        mapping.remap(b);
                                        WoodGood.LOGGER.info("Remapping block '{}' to '{}'", mapping.key, b);
                                        continue label;
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }
    }


}
