package net.mehvahdjukaar.every_compat.configs;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.platform.configs.ConfigBuilder;
import net.mehvahdjukaar.moonlight.api.platform.configs.ConfigType;
import net.mehvahdjukaar.moonlight.api.platform.configs.ModConfigHolder;
import net.mehvahdjukaar.moonlight.api.resources.pack.PackGenerationStrategy;

import java.util.function.Supplier;

//loaded after registry
public class ECConfigs {

    public enum GenMode {
        NEVER,
        RUN_ONCE,
        CACHED,
        CACHED_ZIPPED,
        ALWAYS;

        public PackGenerationStrategy pickStrategy() {
            return switch (this) {
                case NEVER -> PackGenerationStrategy.NO_OP;
                case RUN_ONCE -> PackGenerationStrategy.runOnce();
                case CACHED -> PackGenerationStrategy.CACHED;
                case CACHED_ZIPPED -> PackGenerationStrategy.CACHED_ZIPPED;
                case ALWAYS -> PackGenerationStrategy.REGEN_ON_EVERY_RELOAD;
            };
        }
    }

    public static ModConfigHolder SPEC;
    public static ModConfigHolder CLIENT_SPEC;

    public static final Supplier<Boolean> TAB_ENABLED;
    public static final Supplier<Boolean> TAB_ITEM_SEARCH_ENABLED;
    public static final Supplier<Boolean> NO_MOD_CREATIVE_TAB;
    public static final Supplier<Boolean> CHECK_PACKET;
    public static final Supplier<Boolean> DEBUG_PACKET;
    public static final Supplier<Boolean> BLOCK_TYPE_TOOLTIP;
    public static final Supplier<Boolean> MOD_TOOPTIP;
    public static final Supplier<Boolean> TOOLTIPS_ADVANCED;
    public static final Supplier<GenMode> SERVER_GENERATION_MODE;
    public static final Supplier<GenMode> CLIENT_GENERATION_MODE;
    public static final Supplier<Boolean> GENERATE_BLOCKTYPE_TAGS;


    static {

        if (PlatHelper.getPhysicalSide().isClient()) {
            ConfigBuilder builder = ConfigBuilder.create(EveryCompat.MOD_ID, ConfigType.CLIENT);

            builder.push("general");
            CLIENT_GENERATION_MODE = builder.comment("""
                            \nHow dynamic assets are generated. If cached the cache will regenerate once any mod or pack changes
                            - NEVER: This mod will never attempt to generate the cache folder. The assets will be put in memory
                            - RUN_ONCE: Will generate once & the assets will be stored in memory every time you launched.
                            - CACHED: create a CACHE folder via .minecraft/dynamic-resource-pack-cache
                            - CACHED_ZIPPED: create a ZIP folder via .minecraft/dynamic-resource-pack-cache
                            - ALWAYS: Will always generate the assets & will be stored in memory. There will be no cache folder""")
                    .define("dynamic_assets_generation_mode", GenMode.ALWAYS);
            builder.pop();

            CLIENT_SPEC = builder.build();
            CLIENT_SPEC.forceLoad(); //manually load early
        } else {
            CLIENT_GENERATION_MODE = () -> GenMode.ALWAYS;
        }

        ConfigBuilder builder = ConfigBuilder.create(EveryCompat.MOD_ID, ConfigType.COMMON);

        builder.push("general");
        SERVER_GENERATION_MODE = builder.comment("""
                        \nHow dynamic assets are generated.
                        - NEVER: No asset will be generated. Use this if you have an external pack that adds assets for the block, otherwise you'll get missing assets everywhere
                        - RUN_ONCE: Assets will be generated once every session. Subsequent reloads of resource/data pack will not regenerate them even if assets might have changed as a consequence
                        - CACHED: Generate the assets on first boot and saves them to a cache folder in .minecraft/dynamic-data-pack-cache. If mods or packs change it will regenerate the cache. If not, subsequent reload won't generate anything and just read the cached ones as a normal pack.
                        - CACHED_ZIPPED: Generate the assets on first boot and saves them to a cache zip file in .minecraft/dynamic-data-pack-cache. If mods or packs change it will regenerate the cache. If not, subsequent reload won't generate anything and just read the cached ones as a normal pack.
                        - ALWAYS: Will always generate the assets & will be stored in memory. No cache is used. Unintuitively, this is often the fastest method as any disk access will be slow. \nTry and see what works best for you.""")
                .worldReload()
                .define("server_assets_generation_mode", GenMode.ALWAYS);
        TAB_ENABLED = builder.comment("Puts all the added items into a new Every Compat tab instead of their own mod tabs. Be warned that if disabled it could cause some issue with some mods that have custom tabs")
                .gameRestart()
                .define("creative_tab", true);
        TAB_ITEM_SEARCH_ENABLED = builder.comment("Allow the item_search or searchBar to be visible.")
                .gameRestart()
                .define("tab_item_search", true);
        NO_MOD_CREATIVE_TAB = builder.comment("If set to true, then all of the generated items will not be put into the mod's tab.")
                .gameRestart()
                .define("no_mod_creative_tab", false);
        GENERATE_BLOCKTYPE_TAGS = builder.comment("Generate blocktype tags for every block type. This will be applied to StoneZone & GemsRealm, too. Useful for datapack makers & Iris since v1.8 that can use tags for shaders.")
                .gameRestart()
                .define("generate_blocktype_tags", true);
        // REMAP_COMPAT = builder.comment("Allows the mod to try to remap and convert other blocks and items from other compat mods that have been uninstalled from one world. This was made so one can uninstall such mods seamlessly having their blocks converted into Evety Compat counterparts")
        //        .define("remap_other_mods", false);
        // REMAP_OWN = builder.comment("Clears out and remaps all blocks registered by this mod belonging to uninstalled wood types to air or oak wood")
        //         .define("remap_self", true);
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


        SPEC = builder.build();

        SPEC.forceLoad();
    }

    public static void init() {
    }
}
