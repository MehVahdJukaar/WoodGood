package net.mehvahdjukaar.every_compat;


import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.mehvahdjukaar.every_compat.api.CompatModule;
import net.mehvahdjukaar.every_compat.api.EveryCompatAPI;
import net.mehvahdjukaar.every_compat.configs.ModConfigs;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.misc.AllWoodItem;
import net.mehvahdjukaar.every_compat.modules.another_furniture.AnotherFurnitureModule;
import net.mehvahdjukaar.every_compat.modules.camp_chair.CampChairModule;
import net.mehvahdjukaar.every_compat.modules.chipped.ChippedModule;
import net.mehvahdjukaar.every_compat.modules.decorative_blocks.DecorativeBlocksModule;
import net.mehvahdjukaar.every_compat.modules.exline.BarkCarpetsModule;
import net.mehvahdjukaar.every_compat.modules.friendsandfoes.FriendsAndFoesModule;
import net.mehvahdjukaar.every_compat.modules.furnish.FurnishModule;
import net.mehvahdjukaar.every_compat.modules.hearth_and_home.HearthAndHomeModule;
import net.mehvahdjukaar.every_compat.modules.twigs.TwigsModule;
import net.mehvahdjukaar.moonlight.api.client.TextureCache;
import net.mehvahdjukaar.moonlight.api.misc.RegSupplier;
import net.mehvahdjukaar.moonlight.api.misc.Registrator;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Author: MehVahdJukaar
 */
public abstract class EveryCompat {
    public static final String MOD_ID = "everycomp";

    public static ResourceLocation res(String name) {
        return new ResourceLocation(MOD_ID, name);
    }

    public static final Logger LOGGER = LogManager.getLogger();

    public static final Map<String, CompatModule> ACTIVE_MODULES = new TreeMap<>();

    public static final List<CompatMod> COMPAT_MODS = new ArrayList<>();
    // all mod that EC directly or indirectly depends on
    public static final Set<String> DEPENDENCIES = new HashSet<>();

    //these are the names of the block types we add wooden variants for
    public static final Map<Class<? extends BlockType>, Set<String>> ENTRY_TYPES = new Object2ObjectOpenHashMap<>();
    public static final Map<Object, CompatModule> ITEMS_TO_MODULES = new Object2ObjectOpenHashMap<>();

    public static void forAllModules(Consumer<CompatModule> action) {
        ACTIVE_MODULES.values().forEach(action);
    }


    protected void commonInit() {

        ECNetworking.init();

        ServerDynamicResourcesHandler.INSTANCE.register();

        PlatHelper.addCommonSetup(EveryCompat::commonSetup);
        if (PlatHelper.getPhysicalSide().isClient()) {
            EveryCompatClient.init();
            ClientDynamicResourcesHandler.INSTANCE.register();

            TextureCache.registerSpecialTextureForBlock(Blocks.CACTUS, "cactus_log", res("block/cactus_side"));
            TextureCache.registerSpecialTextureForBlock(Blocks.CACTUS, "cactus_log_top", res("block/cactus_top"));
//            TextureCache.registerSpecialTextureForBlock(Blocks.CACTUS, "stripped_cactus_log", res("block/stripped_cactus_side"));
//            TextureCache.registerSpecialTextureForBlock(Blocks.CACTUS, "stripped_cactus_log_top", res("block/stripped_cactus_top"));
        }

        // ========================================= Add Other Compat Mods ========================================== \\
        addOtherCompatMod("compatoplenty", "biomesoplenty", List.of("twigs", "farmersdelight", "quark", "woodworks"));
        addOtherCompatMod("compat_makeover", "biomemakeover", List.of("habitat", "farmersdelight", "quark", "decorative_blocks"));
        addOtherCompatMod("decorative_compat", "biomesoplenty", List.of("decorative_blocks"));
        addOtherCompatMod("storagedrawersunlimited", "biomesoplenty", List.of("storagedrawers"));

        // Macaw's Addon
        addOtherCompatMod("macawsbridgesbop", "biomesoplenty", List.of("mcwbridges"));
        addOtherCompatMod("macawbridgesbyg", "byg", List.of("mcwbridges"));
        addOtherCompatMod("mcwfencesbop", "biomesoplenty", List.of("mcwfences"));
        addOtherCompatMod("mcwfencesbyg", "byg", List.of("mcwfences"));
        addOtherCompatMod("macawsroofsbop", "biomesoplenty", List.of("mcwroofs"));
        addOtherCompatMod("macawsroofsbyg", "byg", List.of("mcwroofs"));

        // Abnormals Delight
        addOtherCompatMod("abnormals_delight", "autumnity", List.of("farmersdelight"));
        addOtherCompatMod("abnormals_delight", "upgrade_aquatic", List.of("farmersdelight"));
        addOtherCompatMod("abnormals_delight", "endergetic", List.of("farmersdelight"));
        addOtherCompatMod("abnormals_delight", "atmospheric", List.of("farmersdelight"));
        addOtherCompatMod("abnormals_delight", "atmospheric", List.of("farmersdelight"));
        addOtherCompatMod("abnormals_delight", "autumnity", List.of("farmersdelight"));
        addOtherCompatMod("abnormals_delight", "endergetic", List.of("farmersdelight"));
        addOtherCompatMod("abnormals_delight", "environmental", List.of("farmersdelight"));
        addOtherCompatMod("abnormals_delight", "upgrade_aquatic", List.of("farmersdelight"));

        // ========================================= Add Modules ==================================================== \\
        addModule("another_furniture", () -> AnotherFurnitureModule::new);
        addModule("barkcarpets", () -> BarkCarpetsModule::new);
        addModule("campchair", () -> CampChairModule::new);
        addModule("chipped", () -> ChippedModule::new);
        addModule("decorative_blocks", () -> DecorativeBlocksModule::new);
        addModule("friendsandfoes", () -> FriendsAndFoesModule::new);
        addModule("furnish", () -> FurnishModule::new);
        addModule("hearth_and_home", () -> HearthAndHomeModule::new);
        addModule("twigs", () -> TwigsModule::new);

        // ========================================== WORK IN PROGRESS ============================================== \\
       // addModule("handcrafted", () -> HandcraftedModule::new);

        // ============================================= OTHERS ===================================================== \\
        forAllModules(m -> EveryCompat.LOGGER.info("Loaded {}", m.toString()));


        BlockSetAPI.addDynamicBlockRegistration(this::registerWoodStuff, WoodType.class);
        BlockSetAPI.addDynamicBlockRegistration(this::registerLeavesStuff, LeavesType.class);

        BlockSetAPI.addDynamicRegistration((r, c) -> this.registerItems(r), WoodType.class, BuiltInRegistries.ITEM);
        BlockSetAPI.addDynamicRegistration((r, c) -> this.registerTiles(r), WoodType.class, BuiltInRegistries.BLOCK_ENTITY_TYPE);
        BlockSetAPI.addDynamicRegistration((r, c) -> this.registerEntities(r), WoodType.class, BuiltInRegistries.ENTITY_TYPE);

        RegHelper.addItemsToTabsRegistration(EveryCompat::registerItemsToTabs);

    }

    public static <T extends BlockType> void addEntryType(Class<T> type, String childId) {
        ENTRY_TYPES.computeIfAbsent(type, t -> new HashSet<>()).add(childId);
    }

    private void addOtherCompatMod(String modId, String woodFrom, List<String> blocksFrom) {
        COMPAT_MODS.add(new CompatMod(modId, woodFrom, blocksFrom));
        DEPENDENCIES.add(modId);
        DEPENDENCIES.add(woodFrom);
        DEPENDENCIES.addAll(blocksFrom);
    }

    protected void addModule(String modId, Supplier<Function<String, CompatModule>> moduleFactory) {
        if (PlatHelper.isModLoaded(modId)) {
            CompatModule module = moduleFactory.get().apply(modId);
            try {
                EveryCompatAPI.registerModule(module);
            } catch (Exception e) {
                if (PlatHelper.isDev()) throw e;
                else EveryCompat.LOGGER.error("Failed to register module for mod " + module, e);
            }
            DEPENDENCIES.add(modId);
            DEPENDENCIES.addAll(module.getAlreadySupportedMods());
        }
    }


    public static final Supplier<AllWoodItem> ALL_WOODS = RegHelper.registerItem(res("all_woods"), AllWoodItem::new);

    public static final RegSupplier<CreativeModeTab> MOD_TAB = RegHelper.registerCreativeModeTab(res(MOD_ID),
            true,
            builder -> builder.icon(() -> ALL_WOODS.get().getDefaultInstance())
                    .backgroundSuffix("item_search.png")
                    .title(Component.translatable("itemGroup.everycomp.everycomp"))
                    .build());


    public static void commonSetup() {
        if (PlatHelper.isModLoaded("chipped")) {
            EveryCompat.LOGGER.warn("Chipped is installed. The mod on its own adds a ludicrous amount of blocks. With Every Compat this can easily explode. You have been warned");
        }
        //log registered stuff size
        int newSize = BuiltInRegistries.BLOCK.size();
        int am = newSize - prevRegSize;
        float p = (am / (float) newSize) * 100f;
        EveryCompat.LOGGER.info("Registered {} compat blocks making up {}% of total blocks registered", am, String.format("%.2f", p));
        if (p > 33) {
            CompatModule bloated = ACTIVE_MODULES.values().stream()
                    .max(Comparator.comparing(CompatModule::bloatAmount)).get();
            EveryCompat.LOGGER.error("Every Compat registered blocks make up more than one third of your registered blocks, taking up memory and load time.");
            EveryCompat.LOGGER.error("You might want to uninstall some mods, biggest offender was {} ({} blocks)", bloated.getModId().toUpperCase(Locale.ROOT), bloated.bloatAmount());
        }

        forAllModules(CompatModule::onModSetup);
    }

    private static int prevRegSize;

    public void registerWoodStuff(Registrator<Block> event, Collection<WoodType> woods) {
        ModConfigs.init(); // add wood stuff once its ready
        prevRegSize = BuiltInRegistries.BLOCK.size();
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


    public record CompatMod(String modId, String woodFrom, List<String> blocksFrom) {
    }


    //TODO: replace oak based with acacia based


    private static void registerItemsToTabs(RegHelper.ItemToTabEvent event) {
        if (ModConfigs.TAB_ENABLED.get()) {
            Map<BlockType, List<Item>> typeToEntrySet = new LinkedHashMap<>();
            for (var r : BlockSetAPI.getRegistries()) {
                for (var type : r.getValues()) {
                    forAllModules(m -> {
                        typeToEntrySet.computeIfAbsent(type, j -> new ArrayList<>())
                                .addAll(m.getAllItemsOfType(type));
                    });
                }
            }
            for (var e : typeToEntrySet.values()) {
                event.add(EveryCompat.MOD_TAB.getHolder().unwrapKey().get(), e.toArray(ItemLike[]::new));
            }
        } else {
            forAllModules(m -> m.registerItemsToExistingTabs(event));
        }
    }


    protected static void sendPacket(ServerPlayer s) {
    }


}
