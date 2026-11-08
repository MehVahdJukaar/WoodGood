package net.mehvahdjukaar.every_compat;

import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.mehvahdjukaar.every_compat.api.CompatModule;
import net.mehvahdjukaar.every_compat.api.ECPlugin;
import net.mehvahdjukaar.every_compat.api.EveryCompatPlugin;
import net.mehvahdjukaar.every_compat.api.PluginEvent;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.misc.OtherCompatMod;
import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.ApiStatus;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static net.mehvahdjukaar.every_compat.configs.UnsafeDisablerConfigs.ENTRY_SETS_BLACKLIST;
import static net.mehvahdjukaar.every_compat.configs.UnsafeDisablerConfigs.MODULES_BLACKLIST;

//Internal storage of all every compat modules and modules accessories
@ApiStatus.Internal
public abstract class ECModules {

    /// Share of the block registry above which we start telling people they added too much
    public static final float BLOAT_WARN_PERCENT = 25;
    public static final float BLOAT_ERROR_PERCENT = 33;

    private static final Multimap<String, CompatModule> ACTIVE_MODULES = MultimapBuilder
            .linkedHashKeys().arrayListValues().build();
    private static final List<OtherCompatMod> COMPAT_MODS = new ArrayList<>();

    //these are the names of the block types we add wooden variants for
    private static final Map<Class<? extends BlockType>, Set<String>> TYPES_TO_CHILD_KEYS = new Object2ObjectOpenHashMap<>();
    private static final Map<Item, CompatModule> ITEMS_TO_MODULES = new Object2ObjectOpenHashMap<>();
    private static final Set<Block> SIMPLE_DROPS = new HashSet<>();

    // all mod that EC directly or indirectly depends on
    private static final Set<String> DEPENDENCIES = new HashSet<>();
    private static final Set<String> ADDON_IDS = new HashSet<>();

    private static final Set<String> API_DISABLED_MODULES = new HashSet<>();
    private static final Set<String> API_DISABLED_BLOCKS = new HashSet<>();
    private static final List<Pattern> API_DISABLED_BLOCK_PATTERNS = new ArrayList<>();
    private static boolean pluginsLoaded = false;

    private static boolean canShowErrorScreen = PlatHelper.getPhysicalSide().isClient();
    private static final Map<CompatModule, Throwable> ERRORED = new LinkedHashMap<>();

    public static void loadPlugins() {
        PluginEvent registrar = new PluginEvent();
        for (EveryCompatPlugin plugin : PlatHelper.loadPlugins(EveryCompatPlugin.class, EveryCompat.MOD_ID, ECPlugin.class)) {
            plugin.register(registrar);
        }
        pluginsLoaded = true;
    }

    public static boolean arePluginsLoaded() {
        return pluginsLoaded;
    }

    public synchronized static void addOptionalModule(String modId, Supplier<Class<? extends CompatModule>> moduleClass) {
        if (PlatHelper.isModLoaded(modId) && !isModuleBlacklisted(modId)) {
            try {
                Class<? extends CompatModule> klazz = moduleClass.get();

                CompatModule module = instantiateModuleClass(modId, klazz);

                addModule(module);
            } catch (Throwable t) {
                EveryCompat.LOGGER.error("Failed to create module for mod {}", modId, t);
                addError(createDummyModule(modId), t);
            }
        }
    }

    @SafeVarargs
    public static void addMultipleOptional(String modId, Supplier<Class<? extends CompatModule>>... klazzes) {
        for (var klazz : klazzes) {
            addOptionalModule(modId, klazz);
        }
    }

    private static void addModule(CompatModule module) {
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

    private static boolean isModuleBlacklisted(String modId) {
        return MODULES_BLACKLIST.get().contains(modId) || ENTRY_SETS_BLACKLIST.get().contains(modId + ":.*")
                || API_DISABLED_MODULES.contains(modId);
    }

    private static CompatModule instantiateModuleClass(String modId, Class<? extends CompatModule> klazz)
            throws ReflectiveOperationException {
        try {
            // Prefer (String modId) ctor if present
            var ctor = klazz.getDeclaredConstructor(String.class);
            ctor.setAccessible(true);
            return ctor.newInstance(modId);
        } catch (NoSuchMethodException e) {
            var noArg = klazz.getDeclaredConstructor(); // Fallback to no-arg ctor
            noArg.setAccessible(true);
            return noArg.newInstance();
        }
    }

    private static CompatModule createDummyModule(String modId) {
        return new EveryCompatModule(modId, modId) {
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

    public static synchronized void addOtherCompatMod(String compatModId, String fromModId, String... supportedModIds) {
        addOtherCompatMod(compatModId, List.of(fromModId), supportedModIds);
    }

    public static synchronized void addOtherCompatMod(String compatModId, List<String> fromModIds, String... supportedModIds) {
        OtherCompatMod oc = new OtherCompatMod(compatModId, fromModIds, List.of(supportedModIds));
        COMPAT_MODS.add(oc);
        DEPENDENCIES.add(oc.modId());
        DEPENDENCIES.addAll(oc.woodsFrom());
        DEPENDENCIES.addAll(oc.blocksFrom());
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

    public static synchronized void disableModule(String modId) {
        API_DISABLED_MODULES.add(modId);
    }

    public static synchronized void disableBlock(String blockId) {
        API_DISABLED_BLOCKS.add(blockId);
    }

    public static synchronized void disableBlocksMatching(String blockIdRegex) {
        API_DISABLED_BLOCK_PATTERNS.add(Pattern.compile(blockIdRegex));
    }

    public static boolean isBlockDisabled(String blockId) {
        if (API_DISABLED_BLOCKS.contains(blockId)) return true;
        for (Pattern p : API_DISABLED_BLOCK_PATTERNS) {
            if (p.matcher(blockId).matches()) return true;
        }
        return false;
    }

    public static void forAllModules(Consumer<CompatModule> action) {
        for (var m : ACTIVE_MODULES.values()) {
            executeOrFail(() -> action.accept(m), m);
        }
    }

    public static Collection<CompatModule> getActiveModules() {
        return ACTIVE_MODULES.values();
    }

    // mod ids of every registered module, for the hazardous-config blacklist picker
    public static List<String> getActiveModuleIds() {
        return ACTIVE_MODULES.keySet().stream().sorted().toList();
    }

    public static synchronized void addItemToModuleMapping(Item item, CompatModule module) {
        ITEMS_TO_MODULES.put(item, module);
    }

    public static CompatModule getModuleOfItem(Item item) {
        return ITEMS_TO_MODULES.get(item);
    }

    public static synchronized void addSimpleDrop(Block block) {
        SIMPLE_DROPS.add(block);
    }

    public static boolean isSimpleDrop(Block block) {
        return SIMPLE_DROPS.contains(block);
    }

    public static synchronized <T extends BlockType> void trackChildType(Class<T> type, String childId) {
        TYPES_TO_CHILD_KEYS.computeIfAbsent(type, t -> new HashSet<>()).add(childId);
    }

    public static Collection<String> getChildKeys(Class<? extends BlockType> type) {
        return TYPES_TO_CHILD_KEYS.getOrDefault(type, Set.of());
    }

    public static void executeOrFail(Runnable r, CompatModule module) {
        try {
            r.run();
        } catch (Throwable e) {
            EveryCompat.LOGGER.error("Module for mod {} contains errors. This could mean that the mod has been recently updated and Every Compat needs updating (try downgrading the mod) or that you are using an older version.", module.getModName(), e);
            if (canShowErrorScreen) {
                //if before first screen we can display an error screen
                addError(module, e);
            } else {
                throw e;
            }
        }
    }

    private static synchronized void addError(CompatModule module, Throwable t) {
        ERRORED.put(module, t);
    }

    public static void setCanShowErrorScreen(boolean canShow) {
        canShowErrorScreen = canShow;
    }

    public static Map<String, String> getModulesThatErrored() {
        return ERRORED.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> getModuleWithProvider(entry.getKey()),
                        entry -> {
                            var message = entry.getValue().getMessage();
                            var cause = entry.getValue().getCause();

                            if (message != null) return message;
                            else if (cause != null) return cause.toString();
                            else return "Failed to get error message";
                        },
                        (a, b) -> a + "\n" + b,
                        LinkedHashMap::new
                ));
    }

    /// This help anyone to know which mod provide the module. This is useful for logs and crash-reports
    /// @return Rechiseled @-Every Compat or Rechiseled @-Gems Realm
    public static String getModuleWithProvider(CompatModule module) {
        String packagePath = module.getClass().toString();
        String moduleName = module.getModName();

        if (!module.getProviderId().isEmpty()) return moduleName + " @ " + module.getProviderId();

        if (packagePath.contains("mehvahdjukaar") && packagePath.contains("every_compat")) return moduleName + " @ Wood Good";

        return moduleName + " @ " + PlatHelper.getModName(module.getModId());
    }

    /// Total amount of blocks all active modules have registered
    public static int getRegisteredBlocksCount() {
        return ACTIVE_MODULES.values().stream().filter(Objects::nonNull)
                .mapToInt(CompatModule::bloatAmount).sum();
    }

    /// How much of the block registry we take up, in percent
    public static float getRegisteredBlocksPercentage() {
        return (getRegisteredBlocksCount() / (float) BuiltInRegistries.BLOCK.size()) * 100f;
    }

    public static void logLoadSummary() {
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
        int myChildrenSize = getRegisteredBlocksCount();

        if (ACTIVE_MODULES.isEmpty()) {
            String log = """
                    #######################################################
                    #                                                     #
                    #     ATTENTION: No supported mods are installed.     #
                    #   You dont need EveryCompat and should remove it.   #
                    #                                                     #
                    #######################################################
                    """;
            EveryCompat.LOGGER.error("\n\n{}", log);
        } else if (myChildrenSize == 0) {
            String log = """
                    ##########################################################################################################
                    #                                                                                                        #
                    #  ATTENTION: EVERY COMPAT REGISTERED 0 BLOCK! No Wood mods (Biomes O' Plenty or others) are installed.  #
                    #                            You dont need EveryCompat and should remove it.                             #
                    #                                                                                                        #
                    ##########################################################################################################
                    """;
            EveryCompat.LOGGER.error("\n\n{}", log);
        }

        float percent = getRegisteredBlocksPercentage();
        String percentString = String.format("%.2f", percent);
        if (percent > BLOAT_WARN_PERCENT) {
            EveryCompat.LOGGER.warn("Registered {} compat blocks making up {}% of total blocks registered", myChildrenSize, percentString);
        } else {
            EveryCompat.LOGGER.info("Registered {} compat blocks making up {}% of total blocks registered", myChildrenSize, percentString);
        }
        if (percent > BLOAT_ERROR_PERCENT) {
            //no freaking clue why this was returned as null once
            ACTIVE_MODULES.values().stream().filter(Objects::nonNull)
                    .max(Comparator.comparingInt(CompatModule::bloatAmount))
                    .ifPresent(bloated -> {
                        EveryCompat.LOGGER.error("Every Compat registered blocks make up more than one third of your registered blocks, taking up memory and load time.");
                        EveryCompat.LOGGER.error("You might want to uninstall some mods, biggest offender was {} ({} blocks)", bloated.getModName().toUpperCase(Locale.ROOT), bloated.bloatAmount());
                    });
        }
    }

}