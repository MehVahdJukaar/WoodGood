package net.mehvahdjukaar.every_compat.modules.fabric.farmersdelight;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.PaletteStrategies;
import net.mehvahdjukaar.every_compat.api.PaletteStrategy;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.TabAddMode;
import net.mehvahdjukaar.every_compat.misc.HardcodedBlockType;
import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceSink;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.Block;
import vectorwing.farmersdelight.common.block.CabinetBlock;
import vectorwing.farmersdelight.common.crafting.CuttingBoardRecipe;
import vectorwing.farmersdelight.common.crafting.ingredient.ChanceResult;
import vectorwing.farmersdelight.common.item.FuelBlockItem;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static java.util.Map.entry;
import static net.mehvahdjukaar.every_compat.api.PaletteStrategies.registerCached;
import static net.mehvahdjukaar.every_compat.misc.HardcodedBlockType.IsBambooLike;
import static net.mehvahdjukaar.every_compat.misc.TaskRunnerWithFailureCollection.forEachSafely;
import static net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys.*;

///SUPPORT: FABRIC-v3.6.30+
public class FarmersDelightModule extends EveryCompatModule {

    public final SimpleEntrySet<WoodType, Block> cabinets;

    public FarmersDelightModule(String modId) {
        super(modId, "fd");

        cabinets = SimpleEntrySet.builder(WoodType.class, "cabinet",
                        getModBlock("oak_cabinet"), () -> VanillaWoodTypes.OAK,
                        (w, p) -> new CabinetBlock(p)
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
                .addCustomItem((w, block, p) -> new FuelBlockItem(block, p, 300))
                .build();
        this.addEntry(cabinets);
    }

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

                    createCuttingRecipe(LOG, woodType.getBlockOfThis(LOG), woodType.getBlockOfThis(STRIPPED_LOG), woodType, sink, manager);
                    createCuttingRecipe(WOOD, woodType.getBlockOfThis(WOOD), woodType.getBlockOfThis(STRIPPED_WOOD), woodType, sink, manager);

                    createSalvagingRecipe("furniture", woodType, sink, manager);
                    createSalvagingRecipe(CHEST_BOAT, woodType, sink, manager);
                }
            });
        });
    }

    public void createCuttingRecipe(String recipeType, Block input, Block output,
                                    WoodType targetType, ResourceSink sink, ResourceManager manager) {

        if (input == null || output == null || IsBambooLike(targetType)) return;

        Recipe<?> recipe = RPUtils.readRecipe(manager, modRes("cutting/oak_" + recipeType).toString());

        if (recipe instanceof CuttingBoardRecipe cuttingRecipe) {
            List<ChanceResult> newResult = convertResults(cuttingRecipe.getRollableResults(), targetType);

            String newPath = targetType.createPathWith(shortenedId(), "cutting/", recipeType);
            CuttingBoardRecipe newRecipe = new CuttingBoardRecipe(modRes("cutting").toString(), Ingredient.of(input),
                    cuttingRecipe.getTool(), newResult, cuttingRecipe.getSoundEventHolder().orElse(null));

            sink.addRecipe(newRecipe, EveryCompat.res(newPath));
        }
    }

    public void createSalvagingRecipe(String recipeType, WoodType newWoodType, ResourceSink sink, ResourceManager manager) {

        Recipe<?> recipe = RPUtils.readRecipe(manager, modRes("salvaging/oak_" + recipeType).toString());

        if (recipe instanceof CuttingBoardRecipe cuttingRecipe) {

            List<Item> newInputs = new ArrayList<>();
            for (Holder<Item> holder : cuttingRecipe.getInput().items().toList()) {
                Item oldItem = holder.value();
                WoodType oldWoodType = WoodTypeRegistry.INSTANCE.getBlockTypeOf(oldItem);
                if (oldWoodType == null) continue;
                Item newItem = BlockType.changeItemType(oldItem, oldWoodType, newWoodType);
                if (newItem != null) newInputs.add(newItem);
            }
            if (newInputs.isEmpty()) return;

            List<ChanceResult> newResult = convertResults(cuttingRecipe.getRollableResults(), newWoodType);

            String newPath = newWoodType.createPathWith(shortenedId(), "salvaging/", recipeType);
            CuttingBoardRecipe newRecipe = new CuttingBoardRecipe(modRes("cutting").toString(), Ingredient.of(newInputs.stream()),
                    cuttingRecipe.getTool(), newResult, cuttingRecipe.getSoundEventHolder().orElse(null));

            sink.addRecipe(newRecipe, EveryCompat.res(newPath));
        }
    }

    //only oak results get swapped bark and such stay
    private static List<ChanceResult> convertResults(List<ChanceResult> oldResults, WoodType targetType) {
        List<ChanceResult> newResults = new ArrayList<>(oldResults.size());
        for (ChanceResult chanceResult : oldResults) {
            ItemStackTemplate stack = chanceResult.stack();
            Item baseItem = stack.item().value();
            WoodType originalType = WoodTypeRegistry.INSTANCE.getBlockTypeOf(baseItem);
            Item newItem = originalType == VanillaWoodTypes.OAK ? BlockType.changeItemType(baseItem, originalType, targetType) : null;
            if (newItem != null) {
                newResults.add(new ChanceResult(new ItemStackTemplate(newItem, stack.components()).withCount(stack.count()), chanceResult.chance()));
            } else newResults.add(chanceResult);
        }
        return newResults;
    }

    // a recipe mod, not full Compat-Mod providing cutting-board recipes for other Wood-Mods
    // farmers-cutting-collection.*.jar
    private final Map<String, String> COMPAT_RECIPE_MODS = Map.ofEntries(
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

