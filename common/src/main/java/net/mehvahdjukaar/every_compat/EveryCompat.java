package net.mehvahdjukaar.every_compat;


import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import net.mehvahdjukaar.every_compat.api.CompatModule;
import net.mehvahdjukaar.every_compat.api.EveryCompatAPI;
import net.mehvahdjukaar.every_compat.configs.ECConfigs;
import net.mehvahdjukaar.every_compat.configs.WoodConfigs;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.modules.another_furniture.AnotherFurnitureModule;
import net.mehvahdjukaar.every_compat.modules.camp_chair.CampChairModule;
import net.mehvahdjukaar.every_compat.modules.chipped.ChippedModule;
import net.mehvahdjukaar.every_compat.modules.decorative_blocks.DecorativeBlocksModule;
import net.mehvahdjukaar.every_compat.modules.exline.BarkCarpetsModule;
import net.mehvahdjukaar.every_compat.modules.friendsandfoes.FriendsAndFoesModule;
import net.mehvahdjukaar.every_compat.modules.furnish.FurnishModule;
import net.mehvahdjukaar.every_compat.modules.handcrafted.HandcraftedModule;
import net.mehvahdjukaar.every_compat.modules.hearth_and_home.HearthAndHomeModule;
import net.mehvahdjukaar.every_compat.modules.mrcrayfish.RefurbishedFurnitureModule;
import net.mehvahdjukaar.every_compat.modules.table_top_craft.TableTopCraftModule;
import net.mehvahdjukaar.every_compat.modules.twigs.TwigsModule;
import net.mehvahdjukaar.moonlight.api.misc.Registrator;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

// MODULES
// LIB

/**
 * Author: MehVahdJukaar
 */

public abstract class EveryCompat {
    public static final String MOD_ID = "everycomp";
    public static final Logger LOGGER = LogManager.getLogger();

    public static final Multimap<String, CompatModule> ACTIVE_MODULES = MultimapBuilder
            .linkedHashKeys().arrayListValues().build();

    public static final List<CompatMod> COMPAT_MODS = new ArrayList<>();

    //these are the names of the block types we add wooden variants for
    public static final Map<Class<? extends BlockType>, Set<String>> ENTRY_TYPES = new HashMap<>();

    // all mod that EC directly or indirectly depends on
    private static final Set<String> DEPENDENCIES = new HashSet<>();

    public static ResourceLocation res(String name) {
        return new ResourceLocation(MOD_ID, name);
    }

    public static void forAllModules(Consumer<CompatModule> action) {
        ACTIVE_MODULES.values().forEach(action);
    }

//    public static ResourceKey<CreativeModeTab> MOD_TAB = null;

    protected void commonInit() {

        ECConfigs.init();
        ECNetworking.init();
        ECCreativeTabs.init();

        ServerDynamicResourcesHandler.INSTANCE.register();
        RegHelper.addItemsToTabsRegistration(EveryCompat::registerItemsToTabs);

        if (PlatHelper.getPhysicalSide().isClient()) {
            ClientDynamicResourcesHandler.getInstance().register();

        }

// ============================================ addOtherCompatMod =========================================== \\
        addOtherCompatMod("compatoplenty", "biomesoplenty", List.of("twigs", "farmersdelight", "quark", "woodworks"));
        addOtherCompatMod("compat_makeover", "biomemakeover", List.of("habitat", "farmersdelight", "quark", "decorative_blocks"));
        addOtherCompatMod("decorative_compat", "biomesoplenty", List.of("decorative_blocks"));
        addOtherCompatMod("abnormals_delight", "autumnity", List.of("farmersdelight"));
        addOtherCompatMod("abnormals_delight", "upgrade_aquatic", List.of("farmersdelight"));
        addOtherCompatMod("abnormals_delight", "endergetic", List.of("farmersdelight"));
        addOtherCompatMod("abnormals_delight", "atmospheric", List.of("farmersdelight"));
        addOtherCompatMod("storagedrawersunlimited", "biomesoplenty", List.of("storagedrawers"));

        // Macaw's
        addOtherCompatMod("macawsbridgesbop", "biomesoplenty", List.of("mcwbridges"));
        addOtherCompatMod("macawbridgesbyg", "byg", List.of("mcwbridges"));
        addOtherCompatMod("mcwfencesbop", "biomesoplenty", List.of("mcwfences"));
        addOtherCompatMod("mcwfencesbyg", "byg", List.of("mcwfences"));
        addOtherCompatMod("macawsroofsbop", "biomesoplenty", List.of("mcwroofs"));
        addOtherCompatMod("macawsroofsbyg", "byg", List.of("mcwroofs"));

        addOtherCompatMod("mcwbyg", List.of("biomeswevegone", "byg"),
                "mcwbridges", "mcwroofs", "mcwfences", "mcwfurnitures", "mcwstairs");
        addOtherCompatMod("mcwbiomesoplenty", "biomesoplenty",
                "mcwbridges", "mcwroofs", "mcwfences", "mcwfurnitures", "mcwstairs");
        addOtherCompatMod("mcwmoddinglegacy", List.of("blue_skies", "premium_wood"),
                "mcwbridges", "mcwroofs", "mcwfences", "mcwfurnitures", "mcwstairs");

        // Abnormals Delight
        addOtherCompatMod("abnormals_delight", "atmospheric", List.of("farmersdelight"));
        addOtherCompatMod("abnormals_delight", "autumnity", List.of("farmersdelight"));
        addOtherCompatMod("abnormals_delight", "endergetic", List.of("farmersdelight"));
        addOtherCompatMod("abnormals_delight", "environmental", List.of("farmersdelight"));
        addOtherCompatMod("abnormals_delight", "upgrade_aquatic", List.of("farmersdelight"));

// ============================================== addModule ================================================= \\
        addModule("another_furniture", () -> AnotherFurnitureModule::new);
        addModule("barkcarpets", () -> BarkCarpetsModule::new);
        addModule("campchair", () -> CampChairModule::new);
        addModule("chipped", () -> ChippedModule::new);
        addModule("decorative_blocks", () -> DecorativeBlocksModule::new);
        addModule("friendsandfoes", () -> FriendsAndFoesModule::new);
        addModule("furnish", () -> FurnishModule::new);
        addModule("handcrafted", () -> HandcraftedModule::new);
        addModule( "hnh" , () -> HearthAndHomeModule::new);
        addModule("twigs", () -> TwigsModule::new);
        addModule("table_top_craft", () -> TableTopCraftModule::new);
        addModule("refurbished_furniture", () -> RefurbishedFurnitureModule::new);


    // ========================================================================================================== \\
        forAllModules(m -> EveryCompat.LOGGER.info("Loaded {}", m.toString()));


        BlockSetAPI.addDynamicBlockRegistration(this::registerWoodStuff, WoodType.class);
        BlockSetAPI.addDynamicBlockRegistration(this::registerLeavesStuff, LeavesType.class);

        BlockSetAPI.addDynamicRegistration((r, c) -> this.registerItems(r), WoodType.class, BuiltInRegistries.ITEM);
        BlockSetAPI.addDynamicRegistration((r, c) -> this.registerTiles(r), WoodType.class, BuiltInRegistries.BLOCK_ENTITY_TYPE);
        BlockSetAPI.addDynamicRegistration((r, c) -> this.registerEntities(r), WoodType.class, BuiltInRegistries.ENTITY_TYPE);

    }

    public static <T extends BlockType> void addEntryType(Class<T> type, String childId) {
        ENTRY_TYPES.computeIfAbsent(type, t -> new HashSet<>()).add(childId);
    }

    public void addOtherCompatMod(String compatModId, String fromModId, List<String> supportedModId) {
        COMPAT_MODS.add(new CompatMod(compatModId, List.of(fromModId), supportedModId));
    }

    public static void addOtherCompatMod(String compatModId, List<String> fromModId, String... supportedModId) {
        List<String> list = new ArrayList<>();
        Collections.addAll(list, supportedModId);
        COMPAT_MODS.add(new CompatMod(compatModId, fromModId, list));
    }

    public static void addOtherCompatMod(String compatModId, String fromModId, String... supportedModId) {
        List<String> list = new ArrayList<>();
        Collections.addAll(list, supportedModId);
        COMPAT_MODS.add(new CompatMod(compatModId, List.of(fromModId), list));
    }

    public void addModule(String modId, Supplier<Function<String, CompatModule>> moduleFactory) {
        if (PlatHelper.isModLoaded(modId)) {
            DEPENDENCIES.add(modId);
            var module = moduleFactory.get().apply(modId);
            EveryCompatAPI.registerModule(module);
        }
    }

    public static Collection<String> getDependencies() {
        return DEPENDENCIES;
    }

//    public static final Supplier<AllWoodItem> ALL_WOODS = RegHelper.registerItem(res("all_woods"), AllWoodItem::new);

    public void commonSetup() {
        String activeModulesString = ACTIVE_MODULES.keySet().stream()
                .map(key -> {
                    int count = ACTIVE_MODULES.get(key).size();
                    return count > 1 ? key + "(" + count + ")" : key;
                })
                .collect(Collectors.joining(", ", "[", "]"));
        EveryCompat.LOGGER.info("Every Compat has loaded {} modules: {}", ACTIVE_MODULES.size(), activeModulesString);

        if (PlatHelper.isModLoaded("chipped")) {
            EveryCompat.LOGGER.warn("Chipped is installed. The mod on its own adds a ludicrous amount of blocks. With Every Compat this can easily explode. You have been warned");
        }
        //log registered stuff size
        int newSize = BuiltInRegistries.BLOCK.size();
        int myChildrenSize = ACTIVE_MODULES.values().stream().filter(Objects::nonNull).mapToInt(CompatModule::bloatAmount).sum();

        if (myChildrenSize == 0 && !ACTIVE_MODULES.isEmpty()) {
            String log = """
                    ##########################################################################################################
                    #                                                                                                        #
                    #  ATTENTION: EVERY COMPAT REGISTERED 0 BLOCK! No Wood mods (Biomes O' Plenty or others) are installed.  #
                    #                            You dont need EveryCompat and should remove it.                             #
                    #                                                                                                        #
                    ##########################################################################################################
                    """;
            EveryCompat.LOGGER.error("\n\n{}", log);
            return;
        }
        else if (ACTIVE_MODULES.isEmpty()) {
            String log = """
                        #######################################################
                        #                                                     #
                        #     ATTENTION: No supported mods are installed.     #
                        #   You dont need EveryCompat and should remove it.   #
                        #                                                     #
                        #######################################################
                        """;
            EveryCompat.LOGGER.error("\n\n{}", log);
        }

        float percent = (myChildrenSize / (float) newSize) * 100f;
        if (percent > 25) {
            EveryCompat.LOGGER.warn("Registered {} compat blocks making up {}% of total blocks registered", myChildrenSize, String.format("%.2f", percent));
        } else {
            EveryCompat.LOGGER.info("Registered {} compat blocks making up {}% of total blocks registered", myChildrenSize, String.format("%.2f", percent));
        }
        if (percent > 33) {
            Optional<CompatModule> compatbloated = ACTIVE_MODULES.values().stream().max(Comparator.comparing(compatModule -> compatModule != null ? compatModule.bloatAmount() : 0));
            if (compatbloated.isPresent()) {
                CompatModule bloated = compatbloated.get();
                EveryCompat.LOGGER.info("Registered {} compat blocks making up {}% of total blocks registered", myChildrenSize, String.format("%.2f", percent));
                //no freaking clue why this was returned as null once
                EveryCompat.LOGGER.error("Every Compat registered blocks make up more than one third of your registered blocks, taking up memory and load time.");
                EveryCompat.LOGGER.error("You might want to uninstall some mods, biggest offender was {} ({} blocks)", bloated.getModName().toUpperCase(Locale.ROOT), bloated.bloatAmount());
            }
        }

        forAllModules(CompatModule::onModSetup);
    }

    public void registerWoodStuff(Registrator<Block> event, Collection<WoodType> woods) {
        WoodConfigs.init(); // add wood stuff once its ready
//        if (EarlyConfigs.TAB_ENABLED.get()) this.addTab();
        LOGGER.info("Registering Compat Wood Blocks");
        forAllModules(m -> m.registerWoodBlocks(event, woods));
    }

    public void registerLeavesStuff(Registrator<Block> event, Collection<LeavesType> leaves) {
        LOGGER.info("Registering Compat Leaves Blocks");
        forAllModules(m -> m.registerLeavesBlocks(event, leaves));
    }

    protected void registerItems(Registrator<Item> event) {
        forAllModules(m -> m.registerItems(event));
    }

    protected void registerTiles(Registrator<BlockEntityType<?>> event) {
        forAllModules(m -> m.registerTiles(event));
    }

    protected void registerEntities(Registrator<EntityType<?>> event) {
        forAllModules(m -> m.registerEntities(event));
    }


    public record CompatMod(String modId, List<String> woodFrom, List<String> blocksFrom) {
    }

//    public static final ChannelHandler CHANNEL = ChannelHandler.createChannel(EveryCompat.res("network"));

//    static {
//        CHANNEL.register(NetworkDir.PLAY_TO_CLIENT, ECNetworking.S2CBlockStateCheckMessage.class, ECNetworking.S2CBlockStateCheckMessage::new);
//    }

//    public static class S2CBlockStateCheckMessage implements Message {
//
//        public S2CBlockStateCheckMessage(FriendlyByteBuf buf) {
//            int start = buf.readVarInt();
//            int size = buf.readVarInt();
//
//            boolean mismatched = false;
//            for (int i = start; i < (start + size); i++) {
//                try {
//                    CompoundTag nbt = buf.readNbt();
//                    if (nbt == null) {
//                        int aa = 1;
//                    }
//                    var blockState = NbtUtils.readBlockState(null, nbt);
//                    BlockState exp = Block.BLOCK_STATE_REGISTRY.byId(i);
//                    if (blockState != exp) {
//                        if (!mismatched) {
//                            LOGGER.error("Found blockstate id mismatch from " + blockState + "at id " + i + ". Was expecting: " + exp);
//                        }
//                        mismatched = true;
//                    } else {
//                        if (mismatched) {
//                            LOGGER.error("to" + blockState + "at id " + i);
//                        }
//                        mismatched = false;
//                    }
//                } catch (Exception e) {
//                    LOGGER.error("Failed to read blockstate in id map: ", e);
//                    break;
//                }
//            }
//            buf.release();
//        }
//
//        public S2CBlockStateCheckMessage() {
//        }
//
//        @Override
//        public void writeToBuffer(FriendlyByteBuf buf) {
//            FriendlyByteBuf dummy = new FriendlyByteBuf(Unpooled.buffer());
//            int start = lastInd;
//            for (int i = lastInd; i < Block.BLOCK_STATE_REGISTRY.size(); i++) {
//                lastInd++;
//                CompoundTag nbt = NbtUtils.writeBlockState(Block.stateById(i));
//                dummy.writeNbt(nbt);
//                if (dummy.writerIndex() > 1048576 * 0.9) {
//                    break;
//                }
//            }
//            buf.writeVarInt(start);
//            buf.writeVarInt(lastInd - start);
//            buf.writeBytes(dummy);
//            dummy.release();
//        }
//
//        @Override
//        public void handle(ChannelHandler.Context context) {}
//    }

    private static void registerItemsToTabs(RegHelper.ItemToTabEvent event) {
        if (ECConfigs.TAB_ENABLED.get() || ECConfigs.NO_MOD_CREATIVE_TAB.get()) {
            Map<CreativeModeTab, Map<BlockType, List<Item>>> typeToEntrySet = new LinkedHashMap<>();

            // Add to EC's tab
            for (var typeRegistry : BlockSetAPI.getRegistries()) {
                for (var type : typeRegistry.getValues()) {
                    forAllModules(m ->
                            typeToEntrySet.computeIfAbsent(m.getDedicatedTab(), creativeModeTab ->
                                    new LinkedHashMap<>()).computeIfAbsent(type, blockType ->
                                    new ArrayList<>()).addAll(m.getAllItemsOfType(type))
                    );
                }
            }
            for (var mapEntry : typeToEntrySet.entrySet()) {
                for (List<Item> entry : mapEntry.getValue().values()) {

                    event.add(mapEntry.getKey(), entry.toArray(ItemLike[]::new));

                }
            }
        }

        if (!ECConfigs.NO_MOD_CREATIVE_TAB.get()) {
            // Add to the Supported-Mod's tab
            forAllModules(m -> m.registerItemsToExistingTabs(event));
        }
    }

//    private static int lastInd = 0;

//    public static void sendPacket(ServerPlayer s) {
//        if (EarlyConfigs.DEBUG_PACKET.get() || PlatHelper.isDev()) {
//            lastInd = 0;
//            LOGGER.warn("Starting Blockstate Map validity check:");
//            while (lastInd < Block.BLOCK_STATE_REGISTRY.size()) {
//                EveryCompat.CHANNEL.sendToClientPlayer(s, new EveryCompat.S2CBlockStateCheckMessage());
//            }
//        }
//    }
}
