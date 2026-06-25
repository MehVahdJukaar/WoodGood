package net.mehvahdjukaar.every_compat.modules.neoforge.curiosities;

import com.syndicatemc.curiosities.common.block.VerticalConnectingPillarBlock;
import com.teamabnormals.woodworks.common.item.crafting.SawmillRecipe;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.TabAddMode;
import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceSink;
import net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.Block;

import java.util.List;
import java.util.function.Consumer;

import static net.mehvahdjukaar.every_compat.misc.HardcodedBlockType.IsBambooLike;
import static net.mehvahdjukaar.every_compat.misc.UtilityTag.getATagOrCreateANew;
import static net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys.PLANKS;

//SUPPORT: v0.1.0+
public class CuriositiesModule extends EveryCompatModule {

    public final SimpleEntrySet<WoodType, Block> fancied_planks;

    public CuriositiesModule(String modId) {
        super(modId, "cur");
        ResourceKey<CreativeModeTab> tabPath = CreativeModeTabs.BUILDING_BLOCKS;

        fancied_planks = SimpleEntrySet.builder(WoodType.class, "planks", "fancied",
                        getModBlock("fancied_oak_planks"), () -> VanillaWoodTypes.OAK,
                        w -> new VerticalConnectingPillarBlock(Utils.copyPropertySafe(w.planks))
                )
                .addTexture(modRes("block/fancied_oak_planks_normal"))
                .addTexture(modRes("block/fancied_oak_planks_top"))
                .addTexture(modRes("block/fancied_oak_planks_top_connected"))
                .addTexture(modRes("block/fancied_oak_planks_both_connected"))
                .addTexture(modRes("block/fancied_oak_planks_bottom_connected"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTab(getTab(tabPath))
                .setTabMode(TabAddMode.AFTER_SAME_WOOD)
                .defaultRecipe()
                .build();
        this.addEntry(fancied_planks);
    }

    @Override
    // Recipes
    public void addDynamicServerResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicServerResources(executor);

        if (PlatHelper.isModLoaded("woodworks")) {
            executor.accept((manager, sink) -> {
                for (WoodType woodType : fancied_planks.blocks.keySet()) {

                    createSawmillRecipe(PLANKS, "logs", sink, manager, woodType);
                    createSawmillRecipe(PLANKS, PLANKS, sink, manager, woodType);
                }
            });
        }
    }

    public void createSawmillRecipe(String typeOutput, String typeInput, ResourceSink sink, ResourceManager manager, WoodType newWoodType) {
        String fromBambooType = typeInput.matches("logs?") ? "blocks" : typeInput;

        ResourceLocation recipeLocation = (IsBambooLike(newWoodType))
                ? modRes("fancied_bamboo_" + typeOutput + "_from_bamboo_" + fromBambooType + "_sawing")
                : modRes("fancied_acacia_" + typeOutput + "_from_acacia_" + typeInput + "_sawing");

        if (manager.getResource(ResType.RECIPES.getPath(recipeLocation)).isEmpty()) return;

        Recipe<?> recipe = RPUtils.readRecipe(manager, recipeLocation);

        if (recipe instanceof SawmillRecipe sawmillRecipe) {
            boolean isIngredientModified = false;
            Ingredient newIngredient = null;

            var oldIngredients = sawmillRecipe.getIngredients().getFirst().getItems();
            for (ItemStack oldItemStack : oldIngredients) {

                Item oldItem = oldItemStack.getItem();

                // if the recipe has Items.BARRIER, then it's using TAG as ingredient
                if (oldItem != Items.BARRIER) {
                    WoodType oldWoodType = WoodTypeRegistry.INSTANCE.getBlockTypeOf(oldItem);
                    Item newItem = BlockSetAPI.changeItemType(oldItem, oldWoodType, newWoodType);

                    if (newItem != null) {
                        newIngredient = Ingredient.of(new ItemStack(newItem));
                        isIngredientModified = true;
                    }
                }
                else {
                    isIngredientModified = true;
                    ResourceLocation newTag = getATagOrCreateANew("logs", "blocks", newWoodType, sink, manager);
                    newIngredient = Ingredient.of(TagKey.create(Registries.ITEM, newTag));

                }
            }

            if (isIngredientModified) {
                Item oldItem = sawmillRecipe.result.getItem();
                int count = sawmillRecipe.result.getCount();
                WoodType oldWoodType = WoodTypeRegistry.INSTANCE.getBlockTypeOf(oldItem);

                if (oldWoodType == VanillaWoodTypes.ACACIA || oldWoodType == VanillaWoodTypes.BAMBOO) {
                    Item newItem = BlockSetAPI.changeItemType(oldItem, oldWoodType, newWoodType);
                    if (newItem != null) {
                        SawmillRecipe newRecipe = new SawmillRecipe(sawmillRecipe.getGroup(), newIngredient, new ItemStack(newItem, count));

                        String appendedPath = recipeLocation.withPrefix(shortenedId() + "/" + newWoodType.getNamespace() + "/").getPath();
                        String newPath = (oldWoodType == VanillaWoodTypes.ACACIA)
                                ? appendedPath.replace("acacia", newWoodType.getTypeName())
                                : appendedPath.replace("bamboo", newWoodType.getTypeName());

                        sink.addRecipe(new RecipeHolder<>(
                                EveryCompat.res(newPath),
                                newRecipe)
                        );
                    }
                    else {
                        EveryCompat.LOGGER.warn("SawmillRecipe - Result cannot be modified for {} - {}", newWoodType.getId(), recipeLocation);
                    }
                }
            }
            else {
                EveryCompat.LOGGER.warn("SawmillRecipe - Ingredient cannot modified for {} - {}", newWoodType.getId(), recipeLocation);
            }
        }
    }

    @Override
    public List<String> getAlreadySupportedMods() {
        return List.of(
                "atmospheric",
                "autumnity",
//                "caverns_and_chasms",
                "environmental",
                "gardens_of_the_dead",
                "mynethersdelight",
                "upgrade_aquatic"
        );
    }
}