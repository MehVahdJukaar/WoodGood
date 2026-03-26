package net.mehvahdjukaar.every_compat;


import com.google.common.base.Preconditions;
import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.mehvahdjukaar.every_compat.api.AbstractSimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.CompatModule;
import net.mehvahdjukaar.every_compat.api.EveryCompatAPI;
import net.mehvahdjukaar.every_compat.configs.ECConfigs;
import net.mehvahdjukaar.every_compat.configs.ModEntriesConfigs;
import net.mehvahdjukaar.every_compat.configs.UnsafeDisablerConfigs;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.misc.OtherCompatMod;
import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static net.mehvahdjukaar.every_compat.configs.UnsafeDisablerConfigs.ENTRY_SETS_BLACKLIST;
import static net.mehvahdjukaar.every_compat.configs.UnsafeDisablerConfigs.MODULES_BLACKLIST;

@ApiStatus.Internal
//Keep this class safe to be loaded at any time
public abstract class EveryCompat {
    //TODO: figure out pack overlays to remove unneded textures when mods arent loaded
    public static final String MOD_ID = "everycomp";
    public static final Logger LOGGER = LogManager.getLogger("Every Compat");

    private static final Multimap<String, CompatModule> ACTIVE_MODULES = MultimapBuilder
            .linkedHashKeys().arrayListValues().build();
    private static final List<OtherCompatMod> COMPAT_MODS = new ArrayList<>();

    //these are the names of the block types we add wooden variants for
    private static final Map<Class<? extends BlockType>, Set<String>> TYPES_TO_CHILD_KEYS = new Object2ObjectOpenHashMap<>();
    private static final Map<Object, CompatModule> ITEMS_TO_MODULES = new Object2ObjectOpenHashMap<>();

    // all mod that EC directly or indirectly depends on
    private static final Set<String> DEPENDENCIES = new HashSet<>();
    private static final Set<String> ADDON_IDS = new HashSet<>();

    static boolean canShowErrorScreen = PlatHelper.getPhysicalSide().isClient();
    private static final Map<CompatModule, Throwable> ERRORED = new HashMap<>();

    /// @return everycomp:path
    public static ResourceLocation res(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    public static void init() {
        ECConfigs.init();
        UnsafeDisablerConfigs.init();
        ECNetworking.init();
        ECRegistry.init();

        RegHelper.registerDynamicResourceProvider(ServerDynamicResourcesHandler.getInstance());
        RegHelper.addItemsToTabsRegistration(EveryCompat::registerItemsToTabs);
        PlatHelper.addCommonSetup(EveryCompat::setup);

        BlockSetAPI.addDynamicRegistration(MOD_ID, (r) -> {
            ModEntriesConfigs.initEarlyButNotSuperEarly(); // assure configs are loaded since they depend on wood stuff being init
        }, BuiltInRegistries.BLOCK);
    }

    public static void setup() {


        String activeModulesString = ACTIVE_MODULES.keySet().stream()
                .map(key -> {
                    int count = ACTIVE_MODULES.get(key).size();
                    return count > 1 ? key + "(" + count + ")" : key;
                })
                .collect(Collectors.joining(", ", "[", "]"));
        EveryCompat.LOGGER.info("Every Compat has loaded {} modules: {}", ACTIVE_MODULES.size(), activeModulesString);

        if (PlatHelper.isModLoaded("chipped")) {
            EveryCompat.LOGGER.warn("Chipped is installed. The mod on its own adds a ludicrous amount of blocks. With Every Compat this can easily explode. You have been warned");
        }
        //log registered stuff size
        int newSize = BuiltInRegistries.BLOCK.size();
        //cal
        int myChildrenSize = ACTIVE_MODULES.values().stream().filter(Objects::nonNull).mapToInt(CompatModule::bloatAmount).sum();

        float p = (myChildrenSize / (float) newSize) * 100f;
        if (myChildrenSize == 0) {
            String log = """
                    \n##########################################################################################################
                    #                                                                                                        #
                    #  ATTENTION: EVERY COMPAT REGISTERED 0 BLOCK! No Wood mods (Biomes O' Plenty or others) are installed.  #
                    #                            You dont need EveryCompat and should remove it.                             #
                    #                                                                                                        #
                    ##########################################################################################################
                    """;
            EveryCompat.LOGGER.error("\n{}", log);
            return;
        }

        if (p > 25) {
            EveryCompat.LOGGER.warn("Registered {} compat blocks making up {}% of total blocks registered", myChildrenSize, String.format("%.2f", p));
        } else {
            EveryCompat.LOGGER.info("Registered {} compat blocks making up {}% of total blocks registered", myChildrenSize, String.format("%.2f", p));
        }
        if (p > 33) {
            Optional<CompatModule> compatbloated = ACTIVE_MODULES.values().stream().max(Comparator.comparing(compatModule -> compatModule != null ? compatModule.bloatAmount() : 0));
            if (compatbloated.isPresent()) {
                CompatModule bloated = compatbloated.get();
                EveryCompat.LOGGER.info("Registered {} compat blocks making up {}% of total blocks registered", myChildrenSize, String.format("%.2f", p));
                //no freaking clue why this was returned as null once
                EveryCompat.LOGGER.error("Every Compat registered blocks make up more than one third of your registered blocks, taking up memory and load time.");
                EveryCompat.LOGGER.error("You might want to uninstall some mods, biggest offender was {} ({} blocks)", bloated.getModName().toUpperCase(Locale.ROOT), bloated.bloatAmount());
            } else {
                String log = """
                        \n#######################################################
                        #                                                     #
                        #     ATTENTION: No supported mods are installed.     #
                        #   You dont need EveryCompat and should remove it.   #
                        #                                                     #
                        #######################################################
                        """;
                EveryCompat.LOGGER.error("\n{}", log);
            }
        }

        forAllModules(m -> {
            if (m instanceof SimpleModule sm) {
                for (EntrySet e : sm.getEntries()) {
                    //verify tabs existence and crash early if they aren't there
                    if (e instanceof AbstractSimpleEntrySet<?, ?, ?> ae) ae.getTab();
                }
            }
        });

        forAllModules(CompatModule::onModSetup);
        canShowErrorScreen = true;

        //add all namespaces. hack since the pack needs to know about those before hand if we generate the tags
        if (ECConfigs.GENERATE_BLOCKTYPE_TAGS.get()) {
            Set<String> modIdsThatHaveBlockSets = new HashSet<>();
            for (var typeRegistry : BlockSetAPI.getRegistries()) {
                for (BlockType blockType : typeRegistry.getValues()) {
                    modIdsThatHaveBlockSets.add(blockType.getNamespace());
                }
            }
            ServerDynamicResourcesHandler.getInstance()
                    .addSupportedNamespaces(modIdsThatHaveBlockSets.toArray(String[]::new));
        }
    }

    public static void forAllModules(Consumer<CompatModule> action) {
        for (var m : ACTIVE_MODULES.values()) {
            try {
                action.accept(m);
            } catch (Throwable e) {
                EveryCompat.LOGGER.error("Module for the supported mod contains errors. This could mean that the mod has been recently updated & Every Compat needs updating (try downgrading the mod) or that you are using an older version. MAIN CAUSE: {} - {}", m.getModName(), e);
                if (canShowErrorScreen) {
                    //if before first screen we can display an error screen
                    addError(m, e);
                } else {
                    throw e;
                }
            }
        }
    }

    public static void executeOrFail(Runnable r, CompatModule module) {
        try {
            r.run();
        } catch (Throwable e) {
            EveryCompat.LOGGER.error("Module for mod {} contains errors. This could mean that the mod has been recently updated and Every Compat needs updating (try downgrading the mod) or that you are using an older version.", Objects.requireNonNull(module).getModName(), e);
            if (canShowErrorScreen) {
                //if before first screen we can display an error screen
                addError(module, e);
            } else {
                throw e;
            }
        }
    }

    public static void addItemToModuleMapping(Item item, CompatModule module) {
        ITEMS_TO_MODULES.put(item, module);
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


    public static void addCompatMod(String compatModId, List<String> fromModId, List<String> supportedModId) {
        OtherCompatMod oc = new OtherCompatMod(compatModId, fromModId, supportedModId);
        addOtherCompatMod(oc);
    }

    public static synchronized void addOtherCompatMod(OtherCompatMod oc) {
        COMPAT_MODS.add(oc);
        DEPENDENCIES.add(oc.modId());
        DEPENDENCIES.addAll(oc.woodsFrom());
        DEPENDENCIES.addAll(oc.blocksFrom());
    }

    public static synchronized void addModule(CompatModule module) {
        if (!MODULES_BLACKLIST.get().contains(module.getModId())) {
            ACTIVE_MODULES.put(module.getModId(), module);
            DEPENDENCIES.add(module.getModId());
            DEPENDENCIES.addAll(module.getAlreadySupportedMods());
            ADDON_IDS.add(module.getMyNamespace());

            //this will initialize the config. should be fine if loaded from another mod i hope
            ServerDynamicResourcesHandler.getInstance()
                    .addSupportedNamespaces(module.getServerResourcesNamespaces());

            if (PlatHelper.getPhysicalSide().isClient()) {
                ClientDynamicResourcesHandler.getInstance().addSupportedNamespaces(
                        module.getClientResourcesNamespaces());
            }
        }
    }

    public synchronized static void addOptionalModule(String modId, Supplier<Class<? extends CompatModule>> moduleClass) {
        if (PlatHelper.isModLoaded(modId)
                && !MODULES_BLACKLIST.get().contains(modId)
                && !ENTRY_SETS_BLACKLIST.get().contains(modId + ":.*")) {
            try {
                Class<? extends CompatModule> klazz = moduleClass.get();

                CompatModule module = instantiateModuleClass(modId, klazz);

                addModule(module);
            } catch (Throwable t) {
                addError(new EveryCompatModule(modId, modId) {
                    @Override
                    public int bloatAmount() {
                        return 0;
                    }

                    @Override
                    public Collection<Class<? extends BlockType>> getAffectedTypes() {
                        return List.of();
                    }
                }, t);
            }
        }
    }

    private static void addError(CompatModule module, Throwable t) {
        if (module == null) {
            EveryCompat.LOGGER.error("Tried to log an error for a null module", t);
            //add dummy module instead. idk how this could even happen but if it does i want a nice error screen still
            module = new EveryCompatModule(EveryCompat.MOD_ID, "ec") {
                @Override
                public int bloatAmount() {
                    return 0;
                }

                @Override
                public Collection<Class<? extends BlockType>> getAffectedTypes() {
                    return List.of();
                }
            };
        }
        ERRORED.put(Preconditions.checkNotNull(module, "Module cannot be null"),
                Preconditions.checkNotNull(t, "Throwable cannot be null"));
    }

    @SafeVarargs
    public static void addMultipleOptional(String modId, Supplier<Class<? extends CompatModule>>... klazzes) {
        for (var klazz : klazzes) {
            addOptionalModule(modId, klazz);
        }
    }

    private static @NotNull CompatModule instantiateModuleClass(String modId, Class<? extends CompatModule> klazz)
            throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        CompatModule module;
        try {
            // Prefer (String modId) ctor if present
            var ctor = klazz.getDeclaredConstructor(String.class);
            ctor.setAccessible(true);
            module = ctor.newInstance(modId);
        } catch (NoSuchMethodException e) {
            // Fallback to no-arg ctor
            var noArg = klazz.getDeclaredConstructor();
            noArg.setAccessible(true);
            module = noArg.newInstance();
        }
        return module;
    }


    public static Collection<OtherCompatMod> getCompatMods() {
        return COMPAT_MODS;
    }

    public static Collection<String> getDependencies() {
        return DEPENDENCIES;
    }

    public static boolean isMyIdOrAddon(String namespace) {
        return ADDON_IDS.contains(namespace);
    }

    public static Collection<CompatModule> getModulesOfMod(String modId) {
        return ACTIVE_MODULES.get(modId);
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


    public static Map<String, String> getModulesThatErrored() {
        return ERRORED.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> {
                            var modName = entry.getKey().getModName();
                            return modName != null ? modName : "UNKNOWN MOD";
                        },
                        entry -> {
                            var message = entry.getValue().getMessage();
                            return message != null ? message : "Failed to get error message");
                        }
                ));
    }


    public static void addOtherCompatMod(String compatModId, String fromModId, String supportedModId) {
        addCompatMod(compatModId, List.of(fromModId), List.of(supportedModId));
    }

    public static void addOtherCompatMod(String compatModId, String fromModId, String... supportedModId) {
        List<String> list = new ArrayList<>();
        Collections.addAll(list, supportedModId);
        addCompatMod(compatModId, List.of(fromModId), list);
    }

    public static void addOtherCompatMod(String compatModId, List<String> fromModId, String supportedModId) {
        addCompatMod(compatModId, fromModId, List.of(supportedModId));
    }

    public static void addOtherCompatMod(String compatModId, List<String> fromModId, String... supportedModId) {
        List<String> list = new ArrayList<>();
        Collections.addAll(list, supportedModId);
        addCompatMod(compatModId, fromModId, list);
    }

// ─────────────────────────────────────── Deprecated & Marked-for-removal ───────────────────────────────────────

    @Deprecated(forRemoval = true)
    /// @deprecated USE {@link EveryCompatAPI#addIfLoaded(String, Supplier)}
    public static void addIfLoaded(String modId, Supplier<Function<String, CompatModule>> moduleFactory) {
        if (PlatHelper.isModLoaded(modId)) {
            try {
                CompatModule module = moduleFactory.get().apply(modId);
                addModule(module);
            } catch (Throwable e) {
                addError(new CompatModule(modId, modId, EveryCompat.MOD_ID) {

                    @Override
                    public int bloatAmount() {
                        return 0;
                    }

                    @Override
                    public Collection<Class<? extends BlockType>> getAffectedTypes() {
                        return List.of();
                    }
                }, e);
            }
        }
    }

    public static void crashIfInDev(String s) {
        if (PlatHelper.isDev()) {
            throw new AssertionError(s);
        } else {
            LOGGER.error(s);
        }
    }
}
