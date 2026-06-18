package net.mehvahdjukaar.every_compat.configs;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.misc.HardcodedBlockType;
import net.mehvahdjukaar.moonlight.api.platform.configs.ConfigBuilder;
import net.mehvahdjukaar.moonlight.api.platform.configs.ConfigType;
import net.mehvahdjukaar.moonlight.api.platform.configs.ModConfigHolder;
import net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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

        builder.comment("Disables certain types. Note that all these configs, like in any other mod, only hide stuff from tabs and disable their recipes")
                .push("types");
        for (var reg : BlockSetAPI.getRegistries()) {
            builder.push(reg.typeName().replace(" ", "_"));
            for (var w : reg.getValues()) {
                if (!HardcodedBlockType.isKnownVanillaType(w)) {
                    String key = w.toString().replace(":", ".");
                    var config = builder.define(key, true);
                    var map = BLOCK_TYPE_CONFIGS.computeIfAbsent(reg.getType(), s -> new HashMap<>());
                    map.put(w.toString(), config);
                }
            }
            builder.pop();
        }
        builder.pop();

        builder.comment("Disables specific entries")
                .push("entries");
        for (var reg : BlockSetAPI.getRegistries()) {
            builder.push(reg.typeName().replace(" ", "_"));
            for (var c : EveryCompat.getChildKeys(reg.getType())) {
                String key = c.replace(":", ".");
                var config = builder.define(key, true);
                var map = CHILD_CONFIGS.computeIfAbsent(reg.getType(), s -> new HashMap<>());
                map.put(c, config);
            }
            builder.pop();
        }
        builder.pop();

        SPEC = builder.build();

        SPEC.forceLoad(); //manually load later
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
