package net.mehvahdjukaar.every_compat.modules.farmersdelight;

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
import net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
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
import java.util.Objects;
import java.util.function.Consumer;

import static java.util.Map.entry;
import static net.mehvahdjukaar.every_compat.api.PaletteStrategies.registerCached;
import static net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys.*;

///SUPPORT: FABRIC-v3.3.3+ | NEOFORGE-v1.3.2+
public class FarmersDelightModule extends EveryCompatModule {
//NOTE: the new version has a reworked recipe system since v3.3.0+ or v1.3.0+

    public final SimpleEntrySet<WoodType, Block> cabinets;

    public FarmersDelightModule(String modId) {
        super(modId, "fd");

        cabinets = SimpleEntrySet.builder(WoodType.class, "cabinet",
                        getModBlock("oak_cabinet"), () -> VanillaWoodTypes.OAK,
                        w -> new CabinetBlock(Utils.copyPropertySafe(w.planks))
                )
                .requiresChildren(TRAPDOOR, SLAB) //REASON: recipes
                .addTile(getModTile("cabinet"))
                .addTextureM(modRes("block/oak_cabinet_front"),
                        EveryCompat.res("block/fd/oak_cabinet_front_m"),
                        customPalette)
                .addTexture(modRes("block/oak_cabinet_side"), customPalette)
                .addTexture(modRes("block/oak_cabinet_top"), customPalette)
                .addTexture(modRes("block/oak_cabinet_front_open"), customPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("cabinets"), Registries.BLOCK, Registries.ITEM)
                .addTag(modRes("cabinets/wooden"), Registries.BLOCK, Registries.ITEM)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTab(getModTab("farmersdelight"))
                .setTabMode(TabAddMode.AFTER_SAME_TYPE)
                .defaultRecipe()
                .addCustomItem((w, block, p) -> new FuelBlockItem(block, ModItems.basicItem(), 300))
                .build();
        this.addEntry(cabinets);
    }

    public static final PaletteStrategy customPalette = registerCached((blockType, manager) -> PaletteStrategies.makePaletteFromChild(
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
            for (WoodType woodType : WoodTypeRegistry.INSTANCE) {
                if (HardcodedBlockType.isKnownVanillaWood(woodType)) continue;

                // Skip if one of Farmer's-Cutting compat mods is installed
                String namespaceRegex = COMPAT_RECIPE_MODS.getOrDefault(woodType.getNamespace(), "none");
                boolean isRecipeModNotInstalled = !PlatHelper.getInstalledMods().contains(namespaceRegex);
                boolean isCollectionModNotInstalled = !PlatHelper.getInstalledMods().contains("mr_farmers_cuttingcollection")
                        && !COMPAT_RECIPE_MODS.containsKey(woodType.getNamespace());

                if (isRecipeModNotInstalled && isCollectionModNotInstalled) {
                    createCuttingRecipe(LOG, woodType.getBlockOfThis(LOG),
                            woodType, sink, manager);
                    createCuttingRecipe(WOOD, woodType.getBlockOfThis(WOOD),
                            woodType, sink, manager);

                    createSalvagingRecipe("furniture", woodType, sink, manager);
                    createSalvagingRecipe(CHEST_BOAT, woodType, sink, manager);
                }
            }
        });
    }

    public void createCuttingRecipe(String recipeType, Block input,
                                    WoodType targetType, ResourceSink sink, ResourceManager manager) {

        if (Objects.isNull(input)) return;

        String recipeLocation = modRes("cutting/oak_" + recipeType).toString();
        Recipe<?> recipe = RPUtils.readRecipe(manager, recipeLocation);

        if (recipe instanceof CuttingBoardRecipe cuttingRecipe) {


            NonNullList<ChanceResult> oldResult = cuttingRecipe.getRollableResults();
            NonNullList<ChanceResult> newResult = NonNullList.withSize(oldResult.size(), ChanceResult.EMPTY);
            for (int idx = 0; idx < oldResult.size(); idx++) {
                ChanceResult chanceResult = oldResult.get(idx);
                Item baseItem = chanceResult.stack().getItem();
                WoodType originalType = WoodTypeRegistry.INSTANCE.getBlockTypeOf(baseItem);
                if (originalType == VanillaWoodTypes.OAK) {
                    Item newItem = BlockSetAPI.changeItemType(baseItem, originalType, targetType);
                    if (newItem != null) {
                        newResult.set(idx, new ChanceResult(chanceResult.stack().transmuteCopy(newItem), chanceResult.chance()));
                        continue;
                    }
                }
                newResult.set(idx, chanceResult);
            }
            String newPath = targetType.createPathWith(shortenedId(), "cutting/", recipeType);
            CuttingBoardRecipe newRecipe = new CuttingBoardRecipe(modRes("cutting").toString(),
                    Ingredient.of(input), cuttingRecipe.getTool(), newResult, cuttingRecipe.getSoundEvent());

            sink.addRecipe(new RecipeHolder<>(EveryCompat.res(newPath), newRecipe));
        }
    }

    public void createSalvagingRecipe(String recipeType, WoodType newWoodType, ResourceSink sink, ResourceManager manager) {

        String recipeLocation = modRes("salvaging/oak_" + recipeType).toString();
        Recipe<?> recipe = RPUtils.readRecipe(manager, recipeLocation);

        if (recipe instanceof CuttingBoardRecipe cuttingRecipe) {

            boolean isIngredientModified = false;
            NonNullList<ChanceResult> oldResult = cuttingRecipe.getRollableResults();
            NonNullList<ChanceResult> newResult = NonNullList.withSize(oldResult.size(), ChanceResult.EMPTY);

            var oldIngredients = cuttingRecipe.getIngredients();
            var oldItemStacks = oldIngredients.getFirst().getItems();

            List<ItemStack> itemStackList = new ArrayList<>();

            /// Modifying Ingredients
            for (ItemStack oldItemStack : oldItemStacks) {

                Item oldItem = oldItemStack.getItem();
                WoodType oldWoodType = WoodTypeRegistry.INSTANCE.getBlockTypeOf(oldItem);
                Item newItem = BlockSetAPI.changeItemType(oldItem, oldWoodType, newWoodType);

                if (newItem != null) {
                    itemStackList.add(new ItemStack(newItem));
                    isIngredientModified = true;
                }
            }

            if (isIngredientModified) {
                Ingredient newIngredient = Ingredient.of(itemStackList.stream());

                /// Modifying result
                for (int idx = 0; idx < oldResult.size(); idx++) {
                    ChanceResult chanceResult = oldResult.get(idx);
                    Item baseItem = chanceResult.stack().getItem();
                    WoodType originalType = WoodTypeRegistry.INSTANCE.getBlockTypeOf(baseItem);
                    if (originalType == VanillaWoodTypes.OAK) {
                        Item newItem = BlockSetAPI.changeItemType(baseItem, originalType, newWoodType);
                        if (newItem != null) {
                            newResult.set(idx, new ChanceResult(chanceResult.stack().transmuteCopy(newItem), chanceResult.chance()));
                            continue;
                        }
                    }
                    newResult.set(idx, chanceResult);
                }

                String newPath = newWoodType.createPathWith(shortenedId(), "salvaging/", recipeType);
                CuttingBoardRecipe newRecipe = new CuttingBoardRecipe(modRes("cutting").toString(),
                        newIngredient, cuttingRecipe.getTool(), newResult, cuttingRecipe.getSoundEvent());

                sink.addRecipe(new RecipeHolder<>(EveryCompat.res(newPath), newRecipe));
            }
            else
                EveryCompat.LOGGER.warn("SalvagingRecipe - Skipping due to no ingredients being modified for {}", newWoodType.getId());
        }
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
}
