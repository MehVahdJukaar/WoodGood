package net.mehvahdjukaar.every_compat.configs;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.moonlight.api.platform.configs.ConfigBuilder;
import net.mehvahdjukaar.moonlight.api.platform.configs.ConfigSpec;
import net.mehvahdjukaar.moonlight.api.platform.configs.ConfigType;

import java.util.List;
import java.util.function.Supplier;

public class UnsafeDisablerConfigs {

    public static final Supplier<List<String>> WOOD_TYPES_BLACKLIST;
    public static final Supplier<List<String>> LEAVES_TYPES_BLACKLIST;
    public static final Supplier<List<String>> ENTRY_SETS_BLACKLIST;
    public static final Supplier<List<String>> MODULES_BLACKLIST;
    public static final Supplier<Boolean> INCLUDE_ALL_WOOD_MODULES;

    public static ConfigSpec CONFIG_SPEC;

    public static void init() {}

    //loads by whoever calls it first
    static {

        ConfigBuilder builder = ConfigBuilder.create(EveryCompat.res("hazardous"), ConfigType.COMMON);

        String comment = """
                    ═════════════════════════ Attention ═════════════════════════
                    Don't use this if you don't know what you are doing
                        REASON:
                    This file is a conditional registration. This is harmless in Singleplayer World,
                    but harmful in SERVER because you won't able to join.
    
                    ══════════════════════════ Detail ═══════════════════════════
                    This file allow you to exclude WoodTypes, LeavesType, EntrySet, or a Module
                    1) You can find their names for WoodTypes, LeavesType, or EntrySet in `everycomp-entries.toml`
                    2) Leave a value empty to disable that rule.
    
                    Module - is a Supported Mod, just a modId is sufficient.
                    EntrySet - is a FurnitureType or DecorativeType that Wood-Good is supporting via the mod. it is either block or item.
    
                    NOTE: blacklisting a Module will be applied to Wood-Good, Stone-Zone, Gems-Realm
                """;
        builder.comment(comment);

        builder.push("woodtype");
        String WoodTypeExample = """
                    EXAMPLE: blacklist = [
                        "forestry:.*fireproof.*",\t\tCOMMENT: .* is an RegEx, it exclude all of WoodType containing "fireproof" from Forestry
                        "biomesoplenty:.*",\t\t\tCOMMENT: .* is an RegEx, it exclude all of WoodType from Wood Mod for any Module
                        "biomesoplenty:redwood"\t\tCOMMENT: exclude redwood from Wood Mod for any module
                    ]
                """;
        WOOD_TYPES_BLACKLIST = builder.comment("Exclude WoodType from all of Modules\n"+WoodTypeExample).define("blacklist", List.of());
        builder.pop();

        builder.push("leavestype");
        LEAVES_TYPES_BLACKLIST = builder.comment("Exclude LeavesType from all of Modules\n\tThe example is same as WoodType's").define("blacklist", List.of());
        builder.pop();

        builder.push("entryset");
        String entrysetExample = """
                    This is only applied to Wood-Good.
                    EXAMPLE: blacklist = [
                        "chipped:checkered_trapdoor",\t\tCOMMENT: chipped:checkered_oak_trapdoor without "oak"
                        "variantvanillablocks:chest",\t\tCOMMENT: variantvanillablocks:oak_chest without "oak"
                        "chipped:.*"\t\t\t\t\t\tCOMMENT: .* is an regex which will exclude all of EntrySets from one Module - Wood-Good ONLY
                    ]
                """;
        ENTRY_SETS_BLACKLIST = builder.comment("Exclude EntrySet from the module for All of WoodType or LeavesType\n"+entrysetExample).define("blacklist", List.of());
        builder.pop();

        builder.push("module");
        String moduleExample = """
                    EXAMPLE: blacklist = [
                        "chipped",
                        "variantvanillablocks"
                    ]
                """;
        MODULES_BLACKLIST = builder.comment("Exclude Module From Wood-Good, Stone-Zone & Gems-Realm\n"+moduleExample).define("blacklist", List.of());
        builder.pop();

        builder.push("other");
        INCLUDE_ALL_WOOD_MODULES = builder.comment("Disable all of Supported Mods on EveryCompat's side. This feature is same as Library-Section which do not have any Wood Modules.\nWARNING: If the config between CLIENT & SERVER are not the same, then you won't able to join a server")
                .define("include_all_wood_modules", true);
        builder.pop();

        builder.setSynced();

        CONFIG_SPEC = builder.buildAndRegister();

        CONFIG_SPEC.loadFromFile();

        // Warning Message
        if (!WOOD_TYPES_BLACKLIST.get().isEmpty() || !LEAVES_TYPES_BLACKLIST.get().isEmpty() || !ENTRY_SETS_BLACKLIST.get().isEmpty() || !MODULES_BLACKLIST.get().isEmpty()) {
            EveryCompat.LOGGER.warn("""
                            \n
                            ===========================================================
                            |                                                         |
                            |                        ATTENTION                        |
                            |  You are using conditional registration via Wood-Good.  |
                            |  Proceed at your own risk and do not complain if you    |
                            |  CANNOT connect to servers                              |
                            |                                                         |
                            ===========================================================
                    """
            );
        }

    }

}
