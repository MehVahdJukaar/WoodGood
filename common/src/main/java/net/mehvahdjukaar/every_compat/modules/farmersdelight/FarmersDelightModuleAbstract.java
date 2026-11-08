package net.mehvahdjukaar.every_compat.modules.farmersdelight;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.PaletteStrategies;
import net.mehvahdjukaar.every_compat.api.PaletteStrategy;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.TabAddMode;
import net.mehvahdjukaar.every_compat.misc.HardcodedBlockType;
import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceSink;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static java.util.Map.entry;
import static net.mehvahdjukaar.every_compat.api.PaletteStrategies.registerCached;
import static net.mehvahdjukaar.every_compat.misc.TaskRunnerWithFailureCollection.forEachSafely;
import static net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys.*;

///SUPPORT: FABRIC-v3.6.30+ | NEOFORGE- NOT AVAILABLE YET
public abstract class FarmersDelightModuleAbstract extends EveryCompatModule {

    public final SimpleEntrySet<WoodType, Block> cabinets;

    public FarmersDelightModuleAbstract(String modId) {
        super(modId, "fd");

        cabinets = SimpleEntrySet.builder(WoodType.class, "cabinet",
                        getModBlock("oak_cabinet"), () -> VanillaWoodTypes.OAK,
                        this::newCabinetBlock
                )
                .requiresChildren(TRAPDOOR, SLAB) //REASON: recipes
                .addTile(getModTile("cabinet"))
                .addTextureM(modRes("block/oak_cabinet_front"),
                        EveryCompat.res("block/fd/oak_cabinet_front_m"),
                        CUSTOM_PALETTE)
                .addTexture(modRes("block/oak_cabinet_side"), CUSTOM_PALETTE)
                .addTexture(modRes("block/oak_cabinet_top"), CUSTOM_PALETTE)
                .addTexture(modRes("block/oak_cabinet_front_open"), CUSTOM_PALETTE)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("cabinets"), Registries.BLOCK, Registries.ITEM)
                .addTag(modRes("cabinets/wooden"), Registries.BLOCK, Registries.ITEM)
                .setTab(getModTab("farmersdelight"))
                .setTabMode(TabAddMode.AFTER_SAME_TYPE)
                .defaultRecipe()
                .addCustomItem(this::newBlockItem)
                .build();
        this.addEntry(cabinets);
    }

    public abstract Block newCabinetBlock(WoodType wOodType, BlockBehaviour.Properties properties);
    public abstract Item newBlockItem(WoodType wOodType, Block block, Item.Properties properties);

    public static final PaletteStrategy CUSTOM_PALETTE = registerCached((blockType, manager) -> PaletteStrategies.makePaletteFromChild(
            blockType, manager, PLANKS, null, p -> {
                p.reduceDown();
                if (p.size() < 9) {
                    while (p.size() <= 9) {
                        p.increaseInner();
                    }
                } else {
                    while (p.size() >= 9) {
                        p.reduce();
                    }
                }
            })
    );

    @Override
    // RECIPES
    public void addDynamicServerResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicServerResources(executor);

        executor.accept((manager, sink) -> {
            // Creating cutting_board recipes
            forEachSafely("cutting recipe", WoodTypeRegistry.INSTANCE, woodType -> {
                if (HardcodedBlockType.isKnownVanillaWood(woodType)) return;

                // Skip if one of Farmer's-Cutting compat mods is installed
                String shortenedRecipeId = COMPAT_RECIPE_MODS.getOrDefault(woodType.getNamespace(), "none");
                // Check for Individual Recipe Compat Mods
                boolean isRecipeModNotInstalled = !PlatHelper.getInstalledMods().contains(shortenedRecipeId);
                // CHeck for one mod with all Recipe Compat Mods
                boolean isCollectionModNotInstalled = !(PlatHelper.getInstalledMods().contains("mr_farmers_cuttingcollection")
                        && COMPAT_RECIPE_MODS.containsKey(woodType.getNamespace()));
                boolean isNotAlreadySupportedMods = !getAlreadySupportedMods().contains(woodType.getNamespace());

                if (isRecipeModNotInstalled && isCollectionModNotInstalled && isNotAlreadySupportedMods) {

                    createCuttingRecipe(LOG, woodType.getBlockOfThis(LOG), woodType.getBlockOfThis(STRIPPED_LOG), woodType, manager, sink);
                    createCuttingRecipe(WOOD, woodType.getBlockOfThis(WOOD), woodType.getBlockOfThis(STRIPPED_WOOD), woodType, manager, sink);

                    createSalvagingRecipe("furniture", woodType, manager, sink);
                    createSalvagingRecipe(CHEST_BOAT, woodType, manager, sink);
                }
            });
        });
    }

    public abstract void createCuttingRecipe(String recipeType, Block input, Block output,
                                             WoodType targetType, ResourceManager manager, ResourceSink sink);

    public abstract void createSalvagingRecipe(String recipeType, WoodType newWoodType, ResourceManager manager, ResourceSink sink);

    // a recipe mod, not full Compat-Mod providing cutting-board recipes for other Wood-Mods
    // farmers-cutting-collection.*.jar
    protected final Map<String, String> COMPAT_RECIPE_MODS = Map.ofEntries(
        entry("aether", "fcaether"),
        entry("betterend", "fcbe"),
        entry("betternether", "fcbn"),
        entry("biomesoplenty", "fcbop"),
        entry("biomeswevegone", "fcbwg"),
        entry("blue_skies", "fcbs"),
        entry("cinderscapes", "fccs"),
        entry("eternal_starlight", "fces"),
        entry("natures_spirit", "fcns"),
        entry("nethers_exoticism", "fcne"),
        entry("promenade", "fcpromenade"),
        entry("regions_unexplored", "fcru"),
        entry("terrestria", "fcterrestria"),
        entry("twilightforest", "fctf")
    );

    @Override
    public List<String> getAlreadySupportedMods() {
        return List.of("abundant_atmosphere", "mynethersdelight", "newworld", "verdance");
    }
}

