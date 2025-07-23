package net.mehvahdjukaar.every_compat.configs;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.platform.configs.ConfigBuilder;
import net.mehvahdjukaar.moonlight.api.platform.configs.ConfigSpec;
import net.mehvahdjukaar.moonlight.api.platform.configs.ConfigType;

import java.util.function.Supplier;

//loaded after registry
public class ECConfigs {

    public static ConfigSpec SPEC;
    public static ConfigSpec CLIENT_SPED;

    public static final Supplier<Boolean> TAB_ENABLED;
    public static final Supplier<Boolean> TAB_ITEM_SEARCH_ENABLED;
    public static final Supplier<Boolean> DEPEND_ON_PACKS;
    public static final Supplier<Boolean> CHECK_PACKET;
    public static final Supplier<Boolean> DEBUG_RESOURCES;
    public static final Supplier<Boolean> DEBUG_PACKET;
    public static final Supplier<Boolean> BLOCK_TYPE_TOOLTIP;
    public static final Supplier<Boolean> MOD_TOOPTIP;
    public static final Supplier<Boolean> TOOLTIPS_ADVANCED;
    public static final Supplier<Boolean> GENERATE_DYNAMIC_SERVER;
    public static final Supplier<Boolean> GENERATE_DYNAMIC_CLIENT;


    static {

        if(PlatHelper.getPhysicalSide().isClient()) {
            ConfigBuilder builder = ConfigBuilder.create(EveryCompat.MOD_ID, ConfigType.CLIENT);
            builder.push("general");
            GENERATE_DYNAMIC_CLIENT = builder.comment("Enables the generation of dynamic assets. This is required for the mod to work properly. Turn off if you chose to add all the generated assets via datapack manually. This can speedup boot times for modpacks. Note that the generated assets will depend on loaded datapacks")
                    .define("generate_dynamic_assets", true);
            builder.pop();
            CLIENT_SPED = builder.buildAndRegister();
            CLIENT_SPED.loadFromFile(); //manually load early
        }else{
            GENERATE_DYNAMIC_CLIENT = () -> false;
        }

        ConfigBuilder builder = ConfigBuilder.create(EveryCompat.MOD_ID, ConfigType.COMMON);

        builder.push("general");
        GENERATE_DYNAMIC_SERVER = builder.comment("Enables the generation of dynamic assets. This is required for the mod to work properly. Turn off if you chose to add all the generated assets via datapack manually. This can speedup boot times for modpacks. Note that the generated assets will depend on loaded datapacks")
                .define("generate_dynamic_assets", true);
        TAB_ENABLED = builder.comment("Puts all the added items into a new Every Compat tab instead of their own mod tabs. Be warned that if disabled it could cause some issue with some mods that have custom tabs")
                .define("creative_tab", true);
        TAB_ITEM_SEARCH_ENABLED = builder.comment("Allow the item_search or searchBar to be visible.")
                .define("tab_item_search", true);
        // REMAP_COMPAT = builder.comment("Allows the mod to try to remap and convert other blocks and items from other compat mods that have been uninstalled from one world. This was made so one can uninstall such mods seamlessly having their blocks converted into Evety Compat counterparts")
        //        .define("remap_other_mods", false);
        // REMAP_OWN = builder.comment("Clears out and remaps all blocks registered by this mod belonging to uninstalled wood types to air or oak wood")
        //         .define("remap_self", true);
        DEPEND_ON_PACKS = builder.comment("Makes dynamic assets that are generated depend on loaded resource packs. Turn off to make them just use vanilla assets")
                .define("assets_depend_on_loaded_packs", true);
        DEBUG_RESOURCES = builder.comment("Creates a debug folder inside your instance directory where all the dynamically generated resources will be saved")
                .define("save_debug_resources", false);
        CHECK_PACKET = builder.comment("Sends a packet to verify all dependencies mod versions are the same on connect. DIsable if it causes issues")
                .define("mod_version_check_packet", true);
        DEBUG_PACKET = builder.comment("Don't touch unless you are told to").define("debug_packet", false);

        builder.push("tooltips");
        MOD_TOOPTIP = builder.comment("Enabled tooltips showing which mod an EC item is from")
                .define("mod_origin_enabled", true);
        BLOCK_TYPE_TOOLTIP = builder.comment("Enabled tooltips showing which block type an EC item is made from")
                .define("block_type_enabled", true);
        TOOLTIPS_ADVANCED = builder.comment("Only show on advanced settings")
                .define("show_on_advanced_tooltips", false);

        builder.pop();


        SPEC = builder.buildAndRegister();

        SPEC.loadFromFile(); //manually load early
    }

    public static void init() {

    }
}
