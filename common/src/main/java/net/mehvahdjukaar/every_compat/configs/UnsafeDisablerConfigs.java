package net.mehvahdjukaar.every_compat.configs;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.AbstractSimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.CompatModule;
import net.mehvahdjukaar.every_compat.api.EntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.platform.configs.ConfigBuilder;
import net.mehvahdjukaar.moonlight.api.platform.configs.ConfigType;
import net.mehvahdjukaar.moonlight.api.platform.configs.ModConfigHolder;
import net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.set.BlockTypeRegistry;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesType;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesTypeRegistry;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Supplier;

public class UnsafeDisablerConfigs {

    public static final Supplier<List<String>> WOOD_TYPES_BLACKLIST;
    public static final Supplier<List<String>> LEAVES_TYPES_BLACKLIST;
    public static final Supplier<List<String>> BLOCKS_BLACKLIST;
    public static final Supplier<List<String>> ENTRY_SETS_BLACKLIST;
    public static final Supplier<List<String>> MODULES_BLACKLIST;
    public static final Supplier<Boolean> INCLUDE_ALL_WOOD_MODULES;
    public static final Supplier<Boolean> ENABLE_FRAMED_BLOCKS_BLACKLIST;

    public static ModConfigHolder CONFIG_SPEC;

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
                    Only use for personal play. 
                    If you are a modpack maker DO NOT use it!
    
                    ══════════════════════════ Detail ═══════════════════════════
                    This file allow you to exclude WoodTypes, LeavesType, EntrySet, or a Module
                    1) You can find their names for WoodTypes, LeavesType, or EntrySet in `everycomp-entries.toml`
                    2) Leave a value empty to disable that rule.
    
                    Module - is a Supported Mod, just a modId is sufficient.
                    EntrySet - is a FurnitureType or DecorativeType that Wood-Good is supporting via the mod. it is either block or item.
                    Wood-Mods - Biomes O' Plenty, The Twilight Forest, so on...
                    Supported-Mods - The mods that EveryCompat is currently supporting
    
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
        WOOD_TYPES_BLACKLIST = builder.comment("Exclude WoodType from all of Modules\n"+WoodTypeExample)
                .defineSuggestionList("blacklist", List.of(),
                        () -> typeIds(WoodTypeRegistry.INSTANCE), ConfigBuilder.REGEX_CHECK,
                        id -> typeStack(WoodTypeRegistry.INSTANCE, id));
        builder.pop();

        builder.push("leavestype");
        LEAVES_TYPES_BLACKLIST = builder.comment("Exclude LeavesType from all of Modules\n\tThe example is same as WoodType's")
                .defineSuggestionList("blacklist", List.of(),
                        () -> typeIds(LeavesTypeRegistry.INSTANCE), ConfigBuilder.REGEX_CHECK,
                        id -> typeStack(LeavesTypeRegistry.INSTANCE, id));
        builder.pop();

        builder.push("block");
        String blockExample = """
                    This is only applied to Wood-Good.
                    EXAMPLE: blacklist = [
                        "chipped/biomesoplenty/checkered_redwood_trapdoor",\tCOMMENT: excluded a checkered_trapdoor from Chipped with Biomes-O'-Plenty's redwood
                        "variantvanillablocks/biomesoplenty/fir_chest",\t\tCOMMENT: excluded a chest from Variant-Vanilla-Blocks with Biomes-O'-Plenty's fir
                        ".*/biomesoplenty/fir_chest",\t\t\t\t\t\t\tCOMMENT: .* is an RegEx, so it exclude fir_chest from any Supported-Mods with just Biomes-O'-Plenty (Wood-Mods)
                        "chipped/.*/fir_chest",\t\t\t\t\t\t\t\tCOMMENT: .* is an RegEx, so it exclude fir_chest from any Wood-Mods with just chipped (Supported-Mod)
                        ".*fir_chest",\t\t\t\t\t\t\t\t\t\t\tCOMMENT: .* is an RegEx, so it exclude fir_chest from any Supported-Mods & Wood-Mods
                    ]
                """;
        BLOCKS_BLACKLIST = builder.comment("Exclude a specific WoodType/LeavesType block\n"+blockExample)
                .defineSuggestionList("blacklist", List.of(),
                        () -> List.copyOf(blockEntries().keySet()), ConfigBuilder.REGEX_CHECK,
                        id -> blockEntries().getOrDefault(id, ItemStack.EMPTY));
        builder.pop();

        builder.push("entryset");
        String entrysetExample = """
                    This is only applied to Wood-Good.
                    EXAMPLE: blacklist = [
                        "chipped:checkered_trapdoor",\t\tCOMMENT: chipped:checkered_oak_trapdoor without "oak"
                        "variantvanillablocks:chest",\t\tCOMMENT: variantvanillablocks:oak_chest without "oak"
                        "chipped:.*"\t\t\t\t\t\tCOMMENT: .* is an regex which will exclude all of EntrySets from one Module - Wood-Good ONLY
                    ]
                    NOTE: This excluded one entryset from all WoodTypes/LeavesTypes
                """;
        ENTRY_SETS_BLACKLIST = builder.comment("Exclude EntrySet from the module for All of WoodType or LeavesType\n"+entrysetExample)
                .defineSuggestionList("blacklist", List.of(),
                        UnsafeDisablerConfigs::entrySetIds, ConfigBuilder.REGEX_CHECK,
                        UnsafeDisablerConfigs::entrySetStack);
        builder.pop();

        builder.push("module");
        String moduleExample = """
                    EXAMPLE: blacklist = [
                        "chipped",
                        "variantvanillablocks"
                    ]
                """;
        MODULES_BLACKLIST = builder.comment("Exclude Module From Wood-Good, Stone-Zone & Gems-Realm\n"+moduleExample)
                .defineSuggestionList("blacklist", List.of(),
                        EveryCompat::getActiveModuleIds, ConfigBuilder.STRING_CHECK, null);
        builder.pop();

        builder.push("other");
        INCLUDE_ALL_WOOD_MODULES = builder.comment("Disable all of Supported Mods on EveryCompat's side. This feature is same as Library-Section which do not have any Wood Modules.\nWARNING: If the config between CLIENT & SERVER are not the same, then you won't able to join a server")
                .define("include_all_wood_modules", true);

        ENABLE_FRAMED_BLOCKS_BLACKLIST = builder.comment("Blacklist all of EveryCompat's supported blocks that may have similar block to Framed Blocks.\nThis will be applied to all mods, EveryCompat, StoneZone, & GemsRealm.\nWARNING: If the config between CLIENT & SERVER are not the same, then you won't able to join a server")
                .define("enable_framed_blocks_blacklist", false);
        builder.pop();

        CONFIG_SPEC = builder.build();

        CONFIG_SPEC.forceLoad();

        // Warning Message
        if (!WOOD_TYPES_BLACKLIST.get().isEmpty() || !LEAVES_TYPES_BLACKLIST.get().isEmpty() || !BLOCKS_BLACKLIST.get().isEmpty()
                || !ENTRY_SETS_BLACKLIST.get().isEmpty() || !MODULES_BLACKLIST.get().isEmpty()) {
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

    // ─── autocomplete sources for the blacklist pickers (resolved lazily, after registration) ───

    // all type ids in a registry, e.g. "biomesoplenty:fir"
    private static List<String> typeIds(BlockTypeRegistry<?> reg) {
        return reg.getValues().stream().map(w -> w.getId().toString()).sorted().toList();
    }

    // preview icon for a type id; empty for regex patterns that don't resolve to a real type
    private static ItemStack typeStack(BlockTypeRegistry<?> reg, String id) {
        ResourceLocation rl = ResourceLocation.tryParse(id);
        BlockType type = rl == null ? null : reg.get(rl);
        return type == null ? ItemStack.EMPTY : new ItemStack(type.mainChild().asItem());
    }

    // every "modid:entry" child key across all registries
    private static List<String> entrySetIds() {
        return BlockSetAPI.getRegistries().stream()
                .flatMap(r -> EveryCompat.getChildKeys(r.getType()).stream())
                .distinct().sorted().toList();
    }

    // preview icon for an entry key: the first type that actually has that child
    private static ItemStack entrySetStack(String childKey) {
        for (var reg : BlockSetAPI.getRegistries()) {
            for (BlockType w : reg.getValues()) {
                Item item = w.getItemOfThis(childKey);
                if (item != null) return new ItemStack(item);
            }
        }
        return ItemStack.EMPTY;
    }

    // every generated wood/leaves block keyed by its "modid/woodNamespace/blockName" blacklist id -> its icon.
    // matches the id built in SimpleModule#isEntryAlreadyRegistered. Built once, after registration.
    private static Map<String, ItemStack> blockEntriesCache;

    private static Map<String, ItemStack> blockEntries() {
        if (blockEntriesCache != null) return blockEntriesCache;
        Map<String, ItemStack> map = new TreeMap<>();
        for (CompatModule module : EveryCompat.getActiveModules()) {
            if (!(module instanceof SimpleModule simpleModule)) continue;
            String modId = module.getModId();
            for (EntrySet<?> entry : simpleModule.getEntries()) {
                if (!(entry instanceof AbstractSimpleEntrySet<?, ?, ?> simpleEntry)) continue;
                simpleEntry.blocks.forEach((type, block) -> {
                    if (!(type instanceof WoodType) && !(type instanceof LeavesType)) return;
                    String path = Utils.getID((Block) block).getPath();
                    String blockName = path.substring(path.lastIndexOf('/') + 1);
                    map.put(modId + "/" + type.getNamespace() + "/" + blockName, new ItemStack(block.asItem()));
                });
            }
        }
        // don't memoize an empty map built before registration finished
        if (!map.isEmpty()) blockEntriesCache = map;
        return map;
    }

}
