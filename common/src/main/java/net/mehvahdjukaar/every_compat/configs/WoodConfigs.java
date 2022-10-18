package net.mehvahdjukaar.every_compat.configs;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.moonlight.api.platform.configs.ConfigBuilder;
import net.mehvahdjukaar.moonlight.api.platform.configs.ConfigSpec;
import net.mehvahdjukaar.moonlight.api.platform.configs.ConfigType;
import net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

//loaded before registry
public class WoodConfigs {

    private static final Map<Class<? extends BlockType>, Map<String, Supplier<Boolean>>> BLOCK_TYPE_CONFIGS = new HashMap<>();

    public static ConfigSpec SPEC;

    public static void init() {
        ConfigBuilder builder = ConfigBuilder.create(EveryCompat.res("wood_types"), ConfigType.COMMON);

        for (var reg : BlockSetAPI.getRegistries()) {
            builder.push(reg.typeName().replace(" ", "_"));
            for (var w : reg.getValues()) {
                String key = w.toString().replace(":", ".");
                var config = builder.define(key, true);
                var map = BLOCK_TYPE_CONFIGS.computeIfAbsent(reg.getType(), s -> new HashMap<>());
                map.put(w.toString(), config);
            }
            builder.pop();
        }
        SPEC = builder.buildAndRegister();

        SPEC.loadFromFile(); //manually load early
    }

    public static boolean isWoodEnabled(String wood) {
        return BLOCK_TYPE_CONFIGS.get(WoodType.class).get(wood).get();
    }

    public static <T extends BlockType> boolean isTypeEnabled(T w) {
        try {
            return BLOCK_TYPE_CONFIGS.get(w.getClass()).get(w.getId().toString()).get();
        } catch (Exception e) {
            int a = 1;
        }
        return true;
    }
}