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

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

//loaded after registry
public class ModConfigs {

    private static final Map<Class<? extends BlockType>, Map<String, Supplier<Boolean>>> BLOCK_TYPE_CONFIGS = new HashMap<>();
    private static final Map<Class<? extends BlockType>, Map<String, Supplier<Boolean>>> CHILD_CONFIGS = new HashMap<>();

    public static ConfigSpec SPEC;

    public static Supplier<Boolean> TAB_ENABLED;
    //  public static Supplier<Boolean> REMAP_COMPAT;
    //  public static Supplier<Boolean> REMAP_OWN;
    public static Supplier<Boolean> DEPEND_ON_PACKS;
    public static Supplier<Boolean> DEBUG_RESOURCES;
    public static Supplier<Boolean> DEBUG_PACKET;
    public static Supplier<Boolean> TOOLTIPS;


    public static void init() {

        ConfigBuilder builder = ConfigBuilder.create(EveryCompat.MOD_ID, ConfigType.COMMON);


        builder.push("general");
        TAB_ENABLED = builder.comment("Puts all the added items into a new Every Compat tab instead of their own mod tabs. Be warned that if disabled it could cause some issue with some mods that have custom tabs")
                .define("creative_tab", true);
        // REMAP_COMPAT = builder.comment("Allows the mod to try to remap and convert other blocks and items from other compat mods that have been uninstalled from one world. This was made so one can uninstall such mods seamlessly having their blocks converted into Evety Compat counterparts")
        //        .define("remap_other_mods", false);
        // REMAP_OWN = builder.comment("Clears out and remaps all blocks registered by this mod belonging to uninstalled wood types to air or oak wood")
        //         .define("remap_self", true);
        DEPEND_ON_PACKS = builder.comment("Makes dynamic assets that are generated depend on loaded resource packs. Turn off to make them just use vanilla assets")
                .define("assets_depend_on_loaded_packs", true);
        DEBUG_RESOURCES = builder.comment("Creates a debug folder inside your instance directory where all the dynamically generated resources will be saved")
                .define("save_debug_resources", false);
        DEBUG_PACKET = builder.comment("Don't touch unless you are told to").define("debug_packet", false);
        TOOLTIPS = builder.comment("Enabled tooltips showing which mod an EC item is from")
                        .define("mod_origin_tooltips", true);
        builder.pop();


        builder.push("types");
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

        builder.push("entries");
        for (var reg : BlockSetAPI.getRegistries()) {
            if (reg.getType() == WoodType.class || reg.getType() == LeavesType.class) {
                builder.push(reg.typeName().replace(" ", "_"));
                for (var c : EveryCompat.ENTRY_TYPES.getOrDefault(reg.getType(), Set.of())) {
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

        SPEC.loadFromFile(); //manually load late
    }

    public static <T extends BlockType> boolean isEntryEnabled(T w, Object o) {
        if (o instanceof BlockItem bi) o = bi.getBlock();
        return isTypeEnabled(w, w.getChildKey(o));
    }

    public static <T extends BlockType> boolean isEntryEnabled(Class<T> typeClass, Object o) {
        if (o instanceof BlockItem bi) o = bi.getBlock();
        var w = BlockSetAPI.getBlockTypeOf((ItemLike) o, typeClass);
        return isTypeEnabled(w, w.getChildKey(o));
    }

    public static <T extends BlockType> boolean isTypeEnabled(T w) {
        return isTypeEnabled(w, null);
    }

    public static <T extends BlockType> boolean isTypeEnabled(T w, @Nullable String childType) {
        try {
            if (childType != null && !CHILD_CONFIGS.get(w.getClass()).getOrDefault(childType, () -> true).get())
                return false;
            return BLOCK_TYPE_CONFIGS.get(w.getClass()).get(w.getId().toString()).get();
        } catch (Exception ignored) {
        }
        return true;
    }

}