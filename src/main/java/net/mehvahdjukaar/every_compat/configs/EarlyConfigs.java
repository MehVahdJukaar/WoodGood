package net.mehvahdjukaar.every_compat.configs;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.selene.block_set.BlockType;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.mehvahdjukaar.selene.block_set.wood.WoodTypeRegistry;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.loading.FMLPaths;

import java.util.HashMap;
import java.util.Map;

//loaded before registry
public class EarlyConfigs {

    public static final String FILE_NAME = WoodGood.MOD_ID + "-registry.toml";

    private static final Map<Class<? extends BlockType>, Map<String, ForgeConfigSpec.BooleanValue>> BLOCK_TYPE_CONFIGS = new HashMap<>();

    public static ForgeConfigSpec REGISTRY_CONFIG;

    public static void init() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.push("wood_types");
        for (var w : WoodTypeRegistry.WOOD_TYPES.keySet()) {
            String key = w.toString().replace(":", ".");
            ForgeConfigSpec.BooleanValue config = builder.define(key, true);
            var map = BLOCK_TYPE_CONFIGS.computeIfAbsent(WoodType.class, s -> new HashMap<>());
            map.put(w.toString(), config);
        }
        builder.pop();
        REGISTRY_CONFIG = builder.build();

        load();
    }

    private static void load() {
        CommentedFileConfig replacementConfig = CommentedFileConfig
                .builder(FMLPaths.CONFIGDIR.get().resolve(FILE_NAME))
                .sync()
                .preserveInsertionOrder()
                .writingMode(WritingMode.REPLACE)
                .build();
        replacementConfig.load();
        replacementConfig.save();
        REGISTRY_CONFIG.setConfig(replacementConfig);
    }


    public static boolean isWoodEnabled(String wood) {
        return true;
        //return BLOCK_TYPE_CONFIGS.get(WoodType.class).get(wood).get();
    }


    public static <T extends BlockType> boolean isTypeEnabled(T w) {
        return BLOCK_TYPE_CONFIGS.get(w.getClass()).get(w.getId().toString()).get();
    }
}