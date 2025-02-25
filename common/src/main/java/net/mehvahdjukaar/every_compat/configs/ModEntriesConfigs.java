package net.mehvahdjukaar.every_compat.configs;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.moonlight.api.platform.configs.ConfigBuilder;
import net.mehvahdjukaar.moonlight.api.platform.configs.ConfigSpec;
import net.mehvahdjukaar.moonlight.api.platform.configs.ConfigType;
import net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

//loaded after registry
public class ModEntriesConfigs {

    private static final Map<Class<? extends BlockType>, Map<String, Supplier<Boolean>>> BLOCK_TYPE_CONFIGS = new HashMap<>();
    private static final Map<Class<? extends BlockType>, Map<String, Supplier<Boolean>>> CHILD_CONFIGS = new HashMap<>();

    public static ConfigSpec SPEC;
    private static boolean wasInit = false;

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
                if (!w.isVanilla()) {
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
            if (reg.getType() == WoodType.class || reg.getType() == LeavesType.class ||
            reg.typeName().equals("mud_type") || reg.typeName().equals("stone_type")) {
                builder.push(reg.typeName().replace(" ", "_"));
                for (var c : EveryCompat.getChildKeys(reg.getType())) {
                    String key = c.replace(":", ".");
                    var config = builder.define(key, true);
                    var map = CHILD_CONFIGS.computeIfAbsent(reg.getType(), s -> new HashMap<>());
                    map.put(c, config);
                }
                builder.pop();
            }
            else {
                builder.push(reg.typeName().replace(" ", "_"));
                for (var c : EveryCompat.getChildKeys(reg.getType())) {
                    String key = c.replace(":", ".");
                    var config = builder.define(key, true);
                    var map = CHILD_CONFIGS.computeIfAbsent(reg.getType(), s -> new HashMap<>());
                    map.put(c, config);
                }
                builder.pop();
            }
        }
        builder.pop();

        SPEC = builder.buildAndRegister();

        SPEC.loadFromFile(); //manually load later
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

    public static <T extends BlockType> boolean isTypeEnabled(T w, @Nullable String childType) {
        if (!wasInit) initEarlyButNotSuperEarly();
        try {
            if (childType != null && !CHILD_CONFIGS.get(w.getClass()).getOrDefault(childType, () -> true).get())
                return false;
            return BLOCK_TYPE_CONFIGS.get(w.getClass()).get(w.getId().toString()).get();
        } catch (Exception ignored) {
        }
        return true;
    }

}
