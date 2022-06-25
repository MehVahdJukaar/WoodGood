package net.mehvahdjukaar.every_compat.configs;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.selene.block_set.BlockSetManager;
import net.mehvahdjukaar.selene.block_set.BlockType;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.loading.FMLPaths;

import java.util.HashMap;
import java.util.Map;

//loaded before registry
public class EarlyConfigs {

    public static final String FILE_NAME = WoodGood.MOD_ID + "-registry.toml";

    private static final Map<Class<? extends BlockType>, Map<String, ForgeConfigSpec.BooleanValue>> BLOCK_TYPE_CONFIGS = new HashMap<>();

    public static ForgeConfigSpec REGISTRY_CONFIG;

    public static ForgeConfigSpec.BooleanValue TAB_ENABLED;
    public static ForgeConfigSpec.BooleanValue REMAP_COMPAT;
    public static ForgeConfigSpec.BooleanValue REMAP_OWN;
    public static ForgeConfigSpec.BooleanValue DEPEND_ON_PACKS;

    public static void init() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.push("general");
        TAB_ENABLED = builder.comment("Puts all the added items into a new Every Compat tab instead of their own mod tabs. Be warned that if disabled it could cause some issue with some mods that have custom tabs")
                .define("creative_tab", true);
        REMAP_COMPAT = builder.comment("Allows the mod to try to remap and convert other blocks and items from other compat mods that have been uninstalled from one world. This was made so one can uninstall such mods seamlessly having their blocks converted into Evety Compat counterparts")
                .define("remap_other_mods", false);
        REMAP_OWN = builder.comment("Clears out and remaps all blocks registered by this mod belonging to uninstalled wood types to air or oak wood")
                .define("remap_self", true);
        DEPEND_ON_PACKS = builder.comment("Makes dynamic assets that are generated depend on loaded resource packs. Turn off to make them just use vanilla assets")
                        .define("assets_depend_on_loaded_packs", true);
        builder.pop();
        for (var reg : BlockSetManager.getRegistries()) {
            builder.push(reg.typeName().replace(" ", "_"));
            for (var w : reg.getTypes().values()) {
                String key = w.toString().replace(":", ".");
                ForgeConfigSpec.BooleanValue config = builder.define(key, true);
                var map = BLOCK_TYPE_CONFIGS.computeIfAbsent(reg.getType(), s -> new HashMap<>());
                map.put(w.toString(), config);
            }
            builder.pop();
        }
        REGISTRY_CONFIG = builder.build();

        load();
    }

    //TODO: move to lib
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

        if (TAB_ENABLED.get()) {
            WoodGood.createModTab();
        }

        ModContainer modContainer = ModLoadingContext.get().getActiveContainer();
        var REGISTRY_CONFIG_OBJECT = new ModConfig(ModConfig.Type.COMMON, REGISTRY_CONFIG, modContainer, FILE_NAME);
        modContainer.addConfig(REGISTRY_CONFIG_OBJECT);
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