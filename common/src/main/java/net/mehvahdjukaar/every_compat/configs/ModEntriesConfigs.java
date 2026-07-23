package net.mehvahdjukaar.every_compat.configs;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.misc.HardcodedBlockType;
import net.mehvahdjukaar.moonlight.api.platform.configs.ConfigBuilder;
import net.mehvahdjukaar.moonlight.api.platform.configs.ConfigType;
import net.mehvahdjukaar.moonlight.api.platform.configs.ModConfigHolder;
import net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.set.BlockTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Supplier;

//loaded after registry
public class ModEntriesConfigs {

    private static final Map<Class<? extends BlockType>, Map<String, Supplier<Boolean>>> BLOCK_TYPE_CONFIGS = new HashMap<>();
    private static final Map<Class<? extends BlockType>, Map<String, Supplier<Boolean>>> CHILD_CONFIGS = new HashMap<>();

    public static ModConfigHolder SPEC;
    private static boolean wasInit = false;
    private static boolean logOnce = false;
    private static final ArrayList<ResourceLocation> loggedBlockType = new ArrayList<>();
    private static final ArrayList<String> loggedChildType = new ArrayList<>();

    // default as we are initializing it late

    public static void initEarlyButNotSuperEarly() {
        if (wasInit) return;
        wasInit = true;
        ConfigBuilder builder = ConfigBuilder.create(EveryCompat.res("entries"), ConfigType.COMMON);

        builder.comment("Disables whole block types. Note that all these configs, like in any other mod, only hide stuff from tabs and disable their recipes")
                .push("types");
        for (var reg : BlockSetAPI.getRegistries()) {
            builder.icon(defaultIcon(reg)).push(reg.typeName().replace(" ", "_"));
            // group by namespace so each mod gets its own sub-section instead of a flat dotted list
            Map<String, List<BlockType>> byNamespace = new TreeMap<>();
            for (var w : reg.getValues()) {
                if (!HardcodedBlockType.isKnownVanillaType(w)) {
                    byNamespace.computeIfAbsent(w.getNamespace(), k -> new ArrayList<>()).add(w);
                }
            }
            var map = BLOCK_TYPE_CONFIGS.computeIfAbsent(reg.getType(), s -> new HashMap<>());
            byNamespace.forEach((namespace, types) -> {
                builder.push(namespace); // mod id sub-category, left without an icon on purpose
                for (var w : types) {
                    ResourceLocation icon = typeIcon(w);
                    if (icon != null) builder.icon(icon);
                    map.put(w.toString(), builder.feature(w.getId().getPath(), true));
                }
                builder.pop();
            });
            builder.pop();
        }
        builder.pop();

        builder.comment("Disables specific entries")
                .push("entries");
        for (var reg : BlockSetAPI.getRegistries()) {
            builder.icon(defaultIcon(reg)).push(reg.typeName().replace(" ", "_"));
            // child keys are "modid:name" - group by that mod id
            Map<String, List<String>> byNamespace = new TreeMap<>();
            for (var c : EveryCompat.getChildKeys(reg.getType())) {
                int i = c.indexOf(':');
                byNamespace.computeIfAbsent(i < 0 ? "minecraft" : c.substring(0, i), k -> new ArrayList<>()).add(c);
            }
            var map = CHILD_CONFIGS.computeIfAbsent(reg.getType(), s -> new HashMap<>());
            byNamespace.forEach((namespace, childKeys) -> {
                builder.push(namespace); // mod id sub-category, left without an icon on purpose
                for (var c : childKeys) {
                    ResourceLocation icon = entryIcon(reg, c);
                    if (icon != null) builder.icon(icon);
                    int i = c.indexOf(':');
                    map.put(c, builder.feature(i < 0 ? c : c.substring(i + 1), true));
                }
                builder.pop();
            });
            builder.pop();
        }
        builder.pop();

        SPEC = builder.build();

        SPEC.forceLoad(); //manually load later
    }

    // icon for a registry category: the default type's main item (e.g. oak planks for wood types)
    private static ResourceLocation defaultIcon(BlockTypeRegistry<?> reg) {
        return Utils.getID(reg.getDefaultType().mainChild().asItem());
    }

    // icon for a single block type: its own main item (e.g. fir planks)
    @Nullable
    private static ResourceLocation typeIcon(BlockType w) {
        Item item = w.mainChild().asItem();
        return item == null ? null : Utils.getID(item);
    }

    // icon for a child entry: the first block type that actually has that child
    @Nullable
    private static ResourceLocation entryIcon(BlockTypeRegistry<?> reg, String childKey) {
        for (BlockType w : reg.getValues()) {
            Item item = w.getItemOfThis(childKey);
            if (item != null) return Utils.getID(item);
        }
        return null;
    }

    public static <T extends BlockType> boolean isEntryEnabled(T blockType, Object o) {
        if (o instanceof BlockItem bi) o = bi.getBlock();
        return isTypeEnabled(blockType, blockType.getChildKey(o));
    }

    public static <T extends BlockType> boolean isEntryEnabled(Class<T> typeClass, Object o) {
        if (o instanceof BlockItem bi) o = bi.getBlock();
        var blockType = BlockSetAPI.getBlockTypeOf((ItemLike) o, typeClass);
        return isTypeEnabled(blockType, blockType.getChildKey(o));
    }

    // currently not being used
    public static <T extends BlockType> boolean isTypeEnabled(T w) {
        return isTypeEnabled(w, null);
    }

    public static <T extends BlockType> boolean isTypeEnabled(T blockType, @Nullable String childType) {
        if (!wasInit) initEarlyButNotSuperEarly();
        Class<? extends BlockType> typeClass = blockType.getClass();
        Map<String, Supplier<Boolean>> childConfigs = CHILD_CONFIGS.get(typeClass);
        if (childConfigs == null) {
            if (!logOnce) {
                EveryCompat.LOGGER.warn("==> This meant you have no Supported Mod installed. <==");
                logOnce = true;
            }
            if (!loggedChildType.contains(childType)) {
                EveryCompat.LOGGER.warn("No ChildType config map found for: {}", childType);
                loggedChildType.add(childType);
            }
            return true;
        }
        if (childType != null && !childConfigs.getOrDefault(childType, () -> true).get()) {
            return false;
        }
        Map<String, Supplier<Boolean>> blocktypeConfigs = BLOCK_TYPE_CONFIGS.get(typeClass);
        if (blocktypeConfigs == null) {
            if (!logOnce) {
                EveryCompat.LOGGER.warn("==> This meant you have no BlockType Mod (Wood, Stone, & Others) installed. <==");
                logOnce = true;
            }
            if (!loggedBlockType.contains(blockType.getId())) {
                EveryCompat.LOGGER.warn("No BlockType config map found for {} - {}", typeClass.getName().substring(typeClass.getName().lastIndexOf(".") + 1), blockType.getId());
                loggedBlockType.add(blockType.getId());
            }
            return true;
        }

        Supplier<Boolean> booleanSupplier = blocktypeConfigs.get(blockType.getId().toString());
        if (booleanSupplier != null) return booleanSupplier.get();

        return true; // Vanilla BlockTypes that will have null value in booleanSupplier
    }

    public static Map<String, Supplier<Boolean>> getChildConfigs(Class<? extends BlockType> blockType) {
        return CHILD_CONFIGS.get(blockType);
    }

    public static Map<String, Supplier<Boolean>> getBlockTypeConfigs(Class<? extends BlockType> blockType) {
        return BLOCK_TYPE_CONFIGS.get(blockType);
    }
}
