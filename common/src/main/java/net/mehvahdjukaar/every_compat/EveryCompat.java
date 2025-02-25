package net.mehvahdjukaar.every_compat;


import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.mehvahdjukaar.every_compat.api.CompatModule;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.configs.ECConfigs;
import net.mehvahdjukaar.every_compat.configs.ModEntriesConfigs;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.misc.Registrator;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class EveryCompat {

    public static final String MOD_ID = "everycomp";
    public static final Logger LOGGER = LogManager.getLogger("Every Compat");

    private static final List<CompatModule> ACTIVE_MODULES = new ArrayList<>();
    private static final List<CompatMod> COMPAT_MODS = new ArrayList<>();
    // all mod that EC directly or indirectly depends on
    private static final Set<String> DEPENDENCIES = new HashSet<>();
    private static final Set<String> ADDON_IDS = new HashSet<>();

    //these are the names of the block types we add wooden variants for
    private static final Map<Class<? extends BlockType>, Set<String>> TYPES_TO_CHILD_KEYS = new Object2ObjectOpenHashMap<>();
    private static final Map<Object, CompatModule> ITEMS_TO_MODULES = new Object2ObjectOpenHashMap<>();
    private static final Set<Class<? extends BlockType>> AFFECTED_TYPES = new HashSet<>();
    private static final UnsafeModuleDisabler MODULE_DISABLER = new UnsafeModuleDisabler();

    public static ResourceLocation res(String name) {
        return new ResourceLocation(MOD_ID, name);
    }

    public static void forAllModules(Consumer<CompatModule> action) {
        ACTIVE_MODULES.forEach(action);
    }

    public static CompatModule getModuleOfItem(Item item) {
        return ITEMS_TO_MODULES.get(item);
    }

    public static <T extends BlockType> void trackChildType(Class<T> type, String childId) {
        TYPES_TO_CHILD_KEYS.computeIfAbsent(type, t -> new HashSet<>()).add(childId);
    }

    public static Collection<String> getChildKeys(Class<? extends BlockType> type) {
        return TYPES_TO_CHILD_KEYS.getOrDefault(type, Set.of());
    }

    public static void addOtherCompatMod(String compatModId, String fromModId, String supportedModId) {
        addOtherCompatMod(compatModId, List.of(fromModId), List.of(supportedModId));
    }

    public static void addOtherCompatMod(String compatModId, String fromModId, String... supportedModId) {
        List<String> list = new ArrayList<>();
        Collections.addAll(list, supportedModId);
        addOtherCompatMod(compatModId, List.of(fromModId), list);
    }

    public static void addOtherCompatMod(String compatModId, List<String> fromModId, String... supportedModId) {
        List<String> list = new ArrayList<>();
        Collections.addAll(list, supportedModId);
        addOtherCompatMod(compatModId, fromModId, list);
    }

    public static void addOtherCompatMod(String compatModId, List<String> fromModId, String supportedModId) {
        addOtherCompatMod(compatModId, fromModId, List.of(supportedModId));
    }

    public static void addOtherCompatMod(String compatModId, List<String> fromModId, List<String> supportedModId) {
        COMPAT_MODS.add(new CompatMod(compatModId, fromModId, supportedModId));
        DEPENDENCIES.add(compatModId);
        DEPENDENCIES.addAll(fromModId);
        DEPENDENCIES.addAll(supportedModId);
    }

    public static void addModule(CompatModule module) {
        if (MODULE_DISABLER.isModuleOn(module.getModId())) { //maybe turn into supplier
            ACTIVE_MODULES.add(module);
            DEPENDENCIES.add(module.getModId());
            DEPENDENCIES.addAll(module.getAlreadySupportedMods());

            ServerDynamicResourcesHandler.INSTANCE.getPack()
                    .addNamespaces(module.getServerResourcesNamespaces());
            if (PlatHelper.getPhysicalSide().isClient()) {
                ClientDynamicResourcesHandler.getInstance().getPack()
                        .addNamespaces(module.getClientResourcesNamespaces());
            }

            for (var t : module.getAffectedTypes()) {
                addDynamicRegistrationFor(t);
            }
            ADDON_IDS.add(module.getMyNamespace());
        }
    }

    public static void addIfLoaded(String modId, Supplier<Function<String, CompatModule>> moduleFactory) {
        if (PlatHelper.isModLoaded(modId)) {
            CompatModule module = moduleFactory.get().apply(modId);
            addModule(module);
        }
    }

    public static Collection<CompatMod> getCompatMods() {
        return COMPAT_MODS;
    }

    public static Collection<String> getDependencies() {
        return DEPENDENCIES;
    }

    public static void init() {
        ECConfigs.init();
        ECNetworking.init();
        ECRegistry.init();

        ServerDynamicResourcesHandler.INSTANCE.register();
        RegHelper.addItemsToTabsRegistration(EveryCompat::registerItemsToTabs);
        PlatHelper.addCommonSetup(EveryCompat::setup);

        BlockSetAPI.addDynamicRegistration((r, c) -> registerItems(r), WoodType.class, BuiltInRegistries.ITEM);
        BlockSetAPI.addDynamicRegistration((r, c) -> registerTiles(r), WoodType.class, BuiltInRegistries.BLOCK_ENTITY_TYPE);
        BlockSetAPI.addDynamicRegistration((r, c) -> registerEntities(r), WoodType.class, BuiltInRegistries.ENTITY_TYPE);

        MODULE_DISABLER.save();
    }

    public static void setup() {
        if (PlatHelper.isModLoaded("chipped")) {
            EveryCompat.LOGGER.warn("Chipped is installed. The mod on its own adds a ludicrous amount of blocks. With Every Compat this can easily explode. You have been warned");
        }
        //log registered stuff size
        int newSize = BuiltInRegistries.BLOCK.size();
        int myBlocksSize = newSize - prevRegSize;

        float p = (myBlocksSize / (float) newSize) * 100f;
        if (myBlocksSize == 0) {
            EveryCompat.LOGGER.error("\n\nATTENTION: EVERY COMPAT REGISTERED 0 BLOCKS! No Wood mods (Biomes O' Plenty or others) are installed.\nYou dont need EveryCompat and should remove it.\n");
            return;
        }

        if (p > 25) {
            EveryCompat.LOGGER.warn("Registered {} compat blocks making up {}% of total blocks registered", myBlocksSize, String.format("%.2f", p));
        } else {
            EveryCompat.LOGGER.info("Registered {} compat blocks making up {}% of total blocks registered", myBlocksSize, String.format("%.2f", p));
        }
        if (p > 33) {
            Optional<CompatModule> compatbloated = ACTIVE_MODULES.stream().max(Comparator.comparing(CompatModule::bloatAmount));
            if (compatbloated.isPresent()) {
                CompatModule bloated = compatbloated.get();
                //no freaking clue why this was returned as null once
                EveryCompat.LOGGER.error("Every Compat registered blocks make up more than one third of your registered blocks, taking up memory and load time.");
                EveryCompat.LOGGER.error("You might want to uninstall some mods, biggest offender was {} ({} blocks)", bloated.getModName().toUpperCase(Locale.ROOT), bloated.bloatAmount());
            } else {
                EveryCompat.LOGGER.error("\n\nATTENION: No supported mods are installed. You don't need Every Compat and should remove it.\n");
            }
        }


        forAllModules(CompatModule::onModSetup);

    }

    private static int prevRegSize = 0;

    public static <T extends BlockType> void addDynamicRegistrationFor(Class<T> t) {
        if (AFFECTED_TYPES.add(t)) {
            BlockSetAPI.addDynamicBlockRegistration((r, c) -> {
                if (prevRegSize == 0) prevRegSize = BuiltInRegistries.BLOCK.size();
                ModEntriesConfigs.initEarlyButNotSuperEarly(); // assure configs are loaded since they depend on wood stuff being init
                LOGGER.info("Registering Compat {} Blocks", t.getSimpleName());
                forAllModules(m -> m.registerBlocks(t, r, c));
            }, t);
        }
    }

    protected static void registerItems(Registrator<Item> event) {
        forAllModules(m -> m.registerItems((id, o) -> {
            event.register(id, o);
            EveryCompat.ITEMS_TO_MODULES.put(o, m);
        }));
    }

    protected static void registerTiles(Registrator<BlockEntityType<?>> event) {
        forAllModules(m -> m.registerTiles(event));
    }

    protected static void registerEntities(Registrator<EntityType<?>> event) {
        forAllModules(m -> m.registerEntities(event));
    }

    public static boolean isMyIdOrAddon(String namespace) {
        return ADDON_IDS.contains(namespace);
    }


    public record CompatMod(String modId, List<String> woodsFrom, List<String> blocksFrom) {
    }

    private static void registerItemsToTabs(RegHelper.ItemToTabEvent event) {
        if (ECConfigs.TAB_ENABLED.get()) {
            Map<ResourceKey<CreativeModeTab>, Map<BlockType, List<Item>>> typeToEntrySet = new LinkedHashMap<>();
            for (var r : BlockSetAPI.getRegistries()) {
                for (var type : r.getValues()) {
                    forAllModules(m -> {
                        typeToEntrySet.computeIfAbsent(m.getDedicatedTab(), j -> new LinkedHashMap<>())
                                .computeIfAbsent(type, j -> new ArrayList<>())
                                .addAll(m.getAllItemsOfType(type));
                    });
                }
            }
            for (var e : typeToEntrySet.entrySet()) {
                for (var ee : e.getValue().values()) {
                    event.add(e.getKey(), ee.toArray(ItemLike[]::new));
                }
            }
        } else {
            forAllModules(m -> m.registerItemsToExistingTabs(event));
        }
    }
}
