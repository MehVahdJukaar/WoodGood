package net.mehvahdjukaar.every_compat;


import net.mehvahdjukaar.every_compat.api.CompatModule;
import net.mehvahdjukaar.every_compat.configs.EarlyConfigs;
import net.mehvahdjukaar.every_compat.configs.WoodConfigs;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.misc.AllWoodItem;
import net.mehvahdjukaar.moonlight.api.misc.EventCalled;
import net.mehvahdjukaar.moonlight.api.misc.Registrator;
import net.mehvahdjukaar.moonlight.api.platform.PlatformHelper;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Author: MehVahdJukaar
 */
public abstract class EveryCompat {
    public static final String MOD_ID = "everycomp";

    public static ResourceLocation res(String name) {
        return new ResourceLocation(MOD_ID, name);
    }

    public static final Logger LOGGER = LogManager.getLogger();

    public static final Map<String, CompatModule> ACTIVE_MODULES = new LinkedHashMap<>();

    public static final List<CompatMod> COMPAT_MODS = new ArrayList<>();

    //these are the names of the block types we add wooden variants for
    public static final Map<Class<? extends BlockType>, Set<String>> ENTRY_TYPES = new HashMap<>();

    public static void forAllModules(Consumer<CompatModule> action) {
        ACTIVE_MODULES.values().forEach(action);
    }

    public static ServerDynamicResourcesHandler SERVER_RESOURCES = null;
    public static ClientDynamicResourcesHandler CLIENT_RESOURCES = null;

    public static CreativeModeTab MOD_TAB = null;


    protected void commonInit() {

        EarlyConfigs.init();

        SERVER_RESOURCES = new ServerDynamicResourcesHandler();
        SERVER_RESOURCES.register();

        if (PlatformHelper.getEnv().isClient()) {
            CLIENT_RESOURCES = new ClientDynamicResourcesHandler();
            CLIENT_RESOURCES.register();
        } else CLIENT_RESOURCES = null;


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

        //addModule("graveyard", () -> GraveyardModule::new);
        //addModule("benched", () -> BenchedModule::new);
        //addModule("projectbrazier", () -> ProjectBrazierModule::new);
        //addModule("storagedrawers", () -> StorageDrawersModule::new);

        forAllModules(m -> EveryCompat.LOGGER.info("Loaded {}", m.toString()));


        BlockSetAPI.addDynamicBlockRegistration(this::registerWoodStuff, WoodType.class);
        BlockSetAPI.addDynamicBlockRegistration(this::registerLeavesStuff, LeavesType.class);
    }

    public static <T extends BlockType> void addEntryType(Class<T> type, String childId) {
        ENTRY_TYPES.computeIfAbsent(type, t -> new HashSet<>()).add(childId);
    }

    private void addOtherCompatMod(String modId, String woodFrom, List<String> blocksFrom) {
        COMPAT_MODS.add(new CompatMod(modId, woodFrom, blocksFrom));
    }

    protected void addModule(String modId, Supplier<Function<String, CompatModule>> moduleFactory) {
        if (PlatformHelper.isModLoaded(modId)) {
            ACTIVE_MODULES.put(modId, moduleFactory.get().apply(modId));
            SERVER_RESOURCES.getPack().addNamespaces(modId);
        }
    }

    private void addTab() {
        MOD_TAB = PlatformHelper.createModTab(res(MOD_ID), () -> ALL_WOODS.get().getDefaultInstance(), true).setBackgroundSuffix("item_search.png");
    }

    public static final Supplier<AllWoodItem> ALL_WOODS = RegHelper.registerItem(res("all_woods"), AllWoodItem::new);


    public void commonSetup() {
        forAllModules(CompatModule::onModSetup);
    }

    private int prevRegSize;

    public void registerWoodStuff(Registrator<Block> event, Collection<WoodType> woods) {
        WoodConfigs.init(); // add wood stuff once its ready
        if (EarlyConfigs.TAB_ENABLED.get()) this.addTab();
        this.prevRegSize = Registry.BLOCK.size();
        LOGGER.info("Registering Compat Wood Blocks");
        forAllModules(m -> m.registerWoodBlocks(event, woods));

    }


    public void registerLeavesStuff(Registrator<Block> event, Collection<LeavesType> leaves) {
        LOGGER.info("Registering Compat Leaves Blocks");
        forAllModules(m -> m.registerLeavesBlocks(event, leaves));
        int newSize = Registry.BLOCK.size();
        int am = newSize - prevRegSize;
        float p = (am / (float) newSize) * 100f;
        EveryCompat.LOGGER.info("Registered {} compat blocks making up {}% of total blocks registered", am, p);
    }


    @EventCalled
    protected void registerItems(Registrator<Item> event) {
        forAllModules(m -> m.registerItems(event));
    }

    @EventCalled
    protected void registerTiles(Registrator<BlockEntityType<?>> event) {
        forAllModules(m -> m.registerTiles(event));
    }

    @EventCalled
    protected void registerEntities(Registrator<EntityType<?>> event) {
        forAllModules(m -> m.registerEntities(event));
    }


    public record CompatMod(String modId, String woodFrom, List<String> blocksFrom) {
    }


    //TODO: replace oak based with acacia based

}
