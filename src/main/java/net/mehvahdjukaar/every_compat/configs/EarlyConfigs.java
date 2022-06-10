package net.mehvahdjukaar.every_compat.configs;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.selene.block_set.BlockSetManager;
import net.mehvahdjukaar.selene.block_set.BlockType;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.ForgeConfigSpec;
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

    public static void init() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.push("general");
        TAB_ENABLED = builder.comment("Puts all the added items into a new Every Compat tab instead of their own mod tabs. Be warned that if disabled it could cause some issue with some mods that have custom tabs")
                .define("creative_tab", true);
        REMAP_COMPAT = builder.comment("Allows the mod to try to remap and convert other blocks and items from other compat mods that have been uninstalled from one world. This was made so one can uninstall such mods seamlessly having their blocks converted into Evety Compat counterparts")
                .define("remap_other_mods", false);
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
            WoodGood.MOD_TAB = new CreativeModeTab(WoodGood.MOD_ID) {
                public ItemStack makeIcon() {
                    return Items.OAK_PLANKS.getDefaultInstance();
                }
            };
        }

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