package net.mehvahdjukaar.every_compat;


import net.mehvahdjukaar.every_compat.configs.BlockTypeEnabledCondition;
import net.mehvahdjukaar.every_compat.configs.EarlyConfigs;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.misc.AllWoodItem;
import net.mehvahdjukaar.every_compat.misc.CustomRecipeLoader;
import net.mehvahdjukaar.every_compat.misc.EntriesRemapper;
import net.mehvahdjukaar.every_compat.modules.CompatModule;
import net.mehvahdjukaar.every_compat.modules.another_furniture.AnotherFurnitureModule;
import net.mehvahdjukaar.every_compat.modules.architect_palette.ArchitectsPaletteModule;
import net.mehvahdjukaar.every_compat.modules.backpacked.BackpackedModule;
import net.mehvahdjukaar.every_compat.modules.camp_chair.CampChairModule;
import net.mehvahdjukaar.every_compat.modules.create.CreateModule;
import net.mehvahdjukaar.every_compat.modules.deco_block.DecorativeBlocksModule;
import net.mehvahdjukaar.every_compat.modules.exline.BarkCarpetsModule;
import net.mehvahdjukaar.every_compat.modules.farmersdelight.FarmersDelightModule;
import net.mehvahdjukaar.every_compat.modules.infinitybuttons.InfinityButtonsModule;
import net.mehvahdjukaar.every_compat.modules.mcaw.*;
import net.mehvahdjukaar.every_compat.modules.missing_wilds.MissingWildModule;
import net.mehvahdjukaar.every_compat.modules.mrcrayfish_furniture.MrCrayfishFurnitureModule;
import net.mehvahdjukaar.every_compat.modules.quark.QuarkModule;
import net.mehvahdjukaar.every_compat.modules.twigs.TwigsModule;
import net.mehvahdjukaar.every_compat.modules.twilightforest.TwilightForestModule;
import net.mehvahdjukaar.every_compat.modules.valhelsia_structures.ValhelsiaStructuresModule;
import net.mehvahdjukaar.every_compat.modules.the_new_shutters.ShuttersModule;
import net.mehvahdjukaar.every_compat.modules.mosaic_carpentry.MosaicCarpentryModule;
import net.mehvahdjukaar.every_compat.modules.dramaticdoors.DramaticDoorsModule;
import net.mehvahdjukaar.every_compat.modules.dramaticdoors.DramaticDoorsMacawModule;
import net.mehvahdjukaar.every_compat.modules.productive_bees.ProductiveBeesModule;
import net.mehvahdjukaar.every_compat.modules.builders_delight.BuildersDelightModule;
import net.mehvahdjukaar.every_compat.modules.builders_addition.BuildersAdditionModule;
import net.mehvahdjukaar.every_compat.modules.premium_wood.PremiumWoodModule;
//import net.mehvahdjukaar.every_compat.modules.iron_age_furniture.IronAgeFurnitureModule;
//import net.mehvahdjukaar.every_compat.modules.just_a_raft.JustARaftModule;
//import net.mehvahdjukaar.every_compat.modules.xerca.XercaModule;

import net.mehvahdjukaar.selene.block_set.BlockSetManager;
import net.mehvahdjukaar.selene.block_set.leaves.LeavesType;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
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

        addOtherCompatMod("compatoplenty", "biomesoplenty", List.of("twigs", "farmersdelight", "quark"));
        addOtherCompatMod("compat_makeover", "biomemakeover", List.of("habitat", "farmersdelight", "quark", "decorative_blocks"));
        addOtherCompatMod("decorative_compat", "biomesoplenty", List.of("decorative_blocks"));

        addOtherCompatMod("macawsbridgesbop", "biomesoplenty", List.of("mcwbridges"));
        addOtherCompatMod("macawbridgesbyg", "byg", List.of("mcwbridges"));
        addOtherCompatMod("mcwfencesbop", "biomesoplenty", List.of("mcwfences"));
        addOtherCompatMod("mcwfencesbyg", "byg", List.of("mcwfences"));
        addOtherCompatMod("macawsroofsbop", "biomesoplenty", List.of("mcwroofs"));
        addOtherCompatMod("macawsroofsbyg", "byg", List.of("mcwroofs"));
        addOtherCompatMod("storagedrawersunlimited", "biomesoplenty", List.of("storagedrawers"));

        //TODO: add folwering azalea special textures, fix vertical planks not generating & add leaves to it. same for quark
        //also fix that one crash that idk what was about

        addModule("mcwlights", () -> MacawLightsModule::new);
        addModule("mcwdoors", () -> MacawDoorsModule::new);
        addModule("mcwfurnitures", () -> MacawFurnitureModule::new);
        addModule("mcwroofs", () -> MacawRoofsModule::new);
        addModule("mcwpaths", () -> MacawPathsModule::new);
        addModule("mcwtrpdoors", () -> MacawTrapdoorsModule::new);
        addModule("mcwwindows", () -> MacawWindowsModule::new);
        addModule("mcwfences", () -> MacawFencesModule::new);
        addModule("mcwbridges", () -> MacawBridgesModule::new);

        addModule("cfm", () -> MrCrayfishFurnitureModule::new);

        addModule("decorative_blocks", () -> DecorativeBlocksModule::new);
        addModule("twigs", () -> TwigsModule::new);
        addModule("another_furniture", () -> AnotherFurnitureModule::new);
        addModule("backpacked", () -> BackpackedModule::new);
        addModule("farmersdelight", () -> FarmersDelightModule::new);
        addModule("architects_palette", () -> ArchitectsPaletteModule::new);

        addModule("create", () -> CreateModule::new);
        addModule("campchair", () -> CampChairModule::new);
        addModule("missingwilds", () -> MissingWildModule::new);

        addModule("twilightforest", () -> TwilightForestModule::new);
        addModule("valhelsia_structures", () -> ValhelsiaStructuresModule::new);
        addModule("quark", () -> QuarkModule::new);
        addModule("infinitybuttons", () -> InfinityButtonsModule::new);

        addModule("shutter", () -> ShuttersModule::new);
        addModule("barkcarpets", () -> BarkCarpetsModule::new);
        addModule("mosaic_carpentry", () -> MosaicCarpentryModule::new);
        addModule("dramaticdoors", () -> DramaticDoorsModule::new);
        if (ModList.get().isLoaded("mcwdoors")) {
            addModule("dramaticdoors", () -> DramaticDoorsMacawModule::new);
        }
        addModule("premium_wood", () -> PremiumWoodModule::new);

        // CURRENTLY TESTING \\
//        addModule("xerca", () -> XercaModule::new);

        // NOT FINISHED \\
//        addModule("buildersaddition", () -> BuildersAdditionModule::new);
//        addModule("productivebees", () -> ProductiveBeesModule::new);
//        addModule("buildersdelight", () -> BuildersDelightModule::new);

        // CURRENTLY DISABLED \\
//        addModule("justaraft", () -> JustARaftModule::new);
//        addModule("market_crates", () -> MarketCratesModule::new);
//        addModule("storagedrawers", () -> StorageDrawersModule::new); required fixing for rendering properly
//        addModule("graveyard", () -> GraveyardModule::new);
//        addModule("benched", () -> BenchedModule::new); Issue with model format, OBJ -> improperly rendering
//        addModule("projectbrazier", () -> ProjectBrazierModule::new);

        forAllModules(m -> WoodGood.LOGGER.info("Loaded {}", m.toString()));

        BlockSetManager.addBlockSetRegistrationCallback(this::registerWoodStuff, Block.class, WoodType.class);
        BlockSetManager.addBlockSetRegistrationCallback(this::registerLeavesStuff, Block.class, LeavesType.class);

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        bus.register(this);

        MinecraftForge.EVENT_BUS.addListener(CustomRecipeLoader::onEarlyPackLoad);
        MinecraftForge.EVENT_BUS.register(EntriesRemapper.class);

        SERVER_RESOURCES = new ServerDynamicResourcesHandler();
        SERVER_RESOURCES.register(bus);

        if (FMLEnvironment.dist == Dist.CLIENT) {
            CLIENT_RESOURCES = new ClientDynamicResourcesHandler();
            CLIENT_RESOURCES.register(bus);
        } else CLIENT_RESOURCES = null;

        CraftingHelper.register(new BlockTypeEnabledCondition.Serializer());

        BlockSetManager.addBlockTypeFinder(WoodType.class, WoodType.Finder.simple(new ResourceLocation("domum_ornamentum:cactus"),
                new ResourceLocation("domum_ornamentum:cactus_extra"), new ResourceLocation("cactus")));
        BlockSetManager.addBlockTypeFinder(WoodType.class, WoodType.Finder.simple(new ResourceLocation("domum_ornamentum:green_cactus"),
                new ResourceLocation("domum_ornamentum:green_cactus_extra"), new ResourceLocation("cactus")));
        BlockSetManager.addBlockTypeFinder(WoodType.class, WoodType.Finder.simple(
                "darkerdepths", "petrified", "petrified_planks", "petrified_log"));
//        BlockSetManager.addBlockTypeFinder(WoodType.class, WoodType.Finder.simple(
//                "darkerdepths", "porous_petrified", "petrified_planks", "porous_petrified_log"));

    }

    public static void createModTab() {
        MOD_TAB = new CreativeModeTab(WoodGood.MOD_ID) {

            public ItemStack makeIcon() {
                return ForgeRegistries.ITEMS.getValue(WoodGood.res("all_woods")).getDefaultInstance();
            }

            @Override
            public boolean hasSearchBar() {
                return true;
            }
        }.setBackgroundSuffix("item_search.png");
    }

    private void addOtherCompatMod(String modId, String woodFrom, List<String> blocksFrom) {
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

    private int prevRegSize;

    public void registerWoodStuff(RegistryEvent.Register<Block> event, Collection<WoodType> woods) {
        EarlyConfigs.init();
        this.prevRegSize = ForgeRegistries.BLOCKS.getValues().size();
        LOGGER.info("Registering Compat Wood Blocks");
        var reg = event.getRegistry();
        forAllModules(m -> m.registerWoodBlocks(reg, woods));
    }

    public void registerLeavesStuff(RegistryEvent.Register<Block> event, Collection<LeavesType> leaves) {
        LOGGER.info("Registering Compat Leaves Blocks");
        var reg = event.getRegistry();
        forAllModules(m -> m.registerLeavesBlocks(reg, leaves));
        int newSize = ForgeRegistries.BLOCKS.getValues().size();
        int am = newSize - prevRegSize;
        float p = (am / (float) newSize) * 100f;
        WoodGood.LOGGER.info("Registered {} compat blocks making up {}% of total blocks registered", am, p);
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        forAllModules(m -> m.registerItems(event.getRegistry()));
        event.getRegistry().register(new AllWoodItem().setRegistryName(res("all_woods")));
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


    //TODO: replace oak based with acacia based

    /**
     * Copies block properties without keeping stupid lambdas that could include references to the wrong blockstate properties
     */
    @Deprecated(since = "1.19")
    public static BlockBehaviour.Properties copySafe(BlockBehaviour blockBehaviour) {
        var p = BlockBehaviour.Properties.copy(blockBehaviour);
        p.lightLevel(s -> 0);
        p.color(blockBehaviour.defaultMaterialColor());
        return p;
    }


}
