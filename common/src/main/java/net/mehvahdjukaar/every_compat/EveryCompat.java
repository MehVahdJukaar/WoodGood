package net.mehvahdjukaar.every_compat;


import net.mehvahdjukaar.every_compat.api.*;
import net.mehvahdjukaar.every_compat.configs.ECConfigs;
import net.mehvahdjukaar.every_compat.configs.ModEntriesConfigs;
import net.mehvahdjukaar.every_compat.configs.UnsafeDisablerConfigs;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.*;

@ApiStatus.Internal
//Keep this class safe to be loaded at any time
public abstract class EveryCompat {

    public static final String MOD_ID = "everycomp";
    public static final Logger LOGGER = LogManager.getLogger("Every Compat");

    /// @return everycomp:path
    public static Identifier res(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
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
        ECModules.logLoadSummary();
        ECModules.forAllModules(EveryCompat::validateEntryTabs);

        ECModules.forAllModules(CompatModule::onModSetup);
        ECModules.setCanShowErrorScreen(true);

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

    //verify tabs existence and crash early if they aren't there
    private static void validateEntryTabs(CompatModule module) {
        if (!(module instanceof SimpleModule simpleModule)) return;
        for (EntrySet<?> entrySet : simpleModule.getEntries()) {
            if (entrySet instanceof AbstractSimpleEntrySet<?, ?, ?> abstractEntrySet) {
                Holder<CreativeModeTab> tab = abstractEntrySet.getTab();
                if (tab != null) {
                    tab.unwrapKey().orElseThrow(() -> new RuntimeException("The Creative Tab's Identifier is outdated"));
                }
                else {
                    if (PlatHelper.isDev()) {
                        String prefix_ = abstractEntrySet.prefix == null ? "" : abstractEntrySet.prefix + "_";
                        String baseBlockId = prefix_ +"x_"+ abstractEntrySet.postfix;
                        EveryCompat.LOGGER.error("Module[ {} ] had an EntrySet[ {} ] without a tab. Are you sure about this?", simpleModule.getModName(), baseBlockId);
                    }
                }
            }
        }
    }

    private static void registerItemsToTabs(RegHelper.ItemToTabEvent event) {
        if (ECConfigs.TAB_MODE.get().hasECTab()) {
            Map<ResourceKey<CreativeModeTab>, Map<BlockType, List<Item>>> typeToEntrySet = new LinkedHashMap<>();
            ECModules.forAllModules(m -> {
                var tabItemsByType = typeToEntrySet.computeIfAbsent(m.getDedicatedTab(), j -> new LinkedHashMap<>());
                for (var r : BlockSetAPI.getRegistries()) {
                    for (var type : r.getValues()) {
                        tabItemsByType.computeIfAbsent(type, j -> new ArrayList<>())
                                .addAll(m.getAllItemsOfType(type));
                    }
                }
            });
            for (var e : typeToEntrySet.entrySet()) {
                for (var ee : e.getValue().values()) {
                    event.add(e.getKey(), ee.toArray(ItemLike[]::new));
                }
            }
        }

        if (ECConfigs.TAB_MODE.get().addsToModTabs()) {
            ECModules.forAllModules(m -> m.registerItemsToExistingTabs(event));
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
