package net.mehvahdjukaar.every_compat.modules.farmersdelight;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.stal111.valhelsia_structures.common.recipe.AxeCraftingRecipeBuilder;
import net.mehvahdjukaar.selene.block_set.BlockType;
import net.mehvahdjukaar.selene.resourcepack.recipe.IRecipeTemplate;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.crafting.ingredient.ChanceResult;
import vectorwing.farmersdelight.data.builder.CuttingBoardRecipeBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
/*
public class CuttingBoardCraftingRecipeTemplate implements IRecipeTemplate<CuttingBoardRecipeBuilder.Result> {

    public final Ingredient tool;
    private final String soundEventID;

    public final Ingredient ingredient;
    private final List<ChanceResult> results;

    private List<ICondition> conditions = new ArrayList<>();

    public CuttingBoardCraftingRecipeTemplate(JsonObject json) {


        JsonArray arrayIngredients = json.get("ingredients").getAsJsonArray();
        this.ingredient = Ingredient.fromJson(arrayIngredients.get(0));
        this.tool = Ingredient.fromJson(json.get("tool"));

        this.results = new ArrayList<>();
        JsonArray arrayResults = json.get("result").getAsJsonArray();
        for (var r : arrayResults) {
            var j = r.getAsJsonObject();
            Item i = ForgeRegistries.ITEMS.getValue(new ResourceLocation(j.get("item").getAsString()));
            int count = 0;
            if (j.has("count")) {
                count = j.get("count").getAsInt();
            }
            float chance = 1;
            if (j.has("chance")) {
                chance = j.get("chance").getAsFloat();
            }
            results.add(new ChanceResult(new ItemStack(i, count), chance));
        }

        if (json.has("sound")) {
            this.soundEventID = json.get("sound").getAsString();
        } else this.soundEventID = "";
    }

    @Override
    public <T extends BlockType> CuttingBoardRecipeBuilder.Result createSimilar(
            T originalMat, T destinationMat, Item unlockItem, @Nullable String id) {
        ItemLike newOutput = BlockType.changeItemBlockType(this.ingredient., originalMat, destinationMat);
        if (newOutput == null)
            throw new UnsupportedOperationException(String.format("Could not convert output item %s", output));

        {
            boolean atLeastOneChanged = false;
            Ingredient newInput = ingredient;
            for (var in : newInput.getItems()) {
                Item it = in.getItem();
                if (it != Items.BARRIER) {
                    ItemLike i = BlockType.changeItemBlockType(it, originalMat, destinationMat);
                    if (i != null) {
                        atLeastOneChanged = true;
                        //converts first ingredient it finds
                        newInput = Ingredient.of(i);
                        break;
                    }
                }
            }
            //if recipe fails
            if (!atLeastOneChanged) return null;
        }

        boolean atLeastOneChanged = false;
        Ingredient newInput = ingredient;
        for (var in : newInput.getItems()) {
            Item it = in.getItem();
            if (it != Items.BARRIER) {
                ItemLike i = BlockType.changeItemBlockType(it, originalMat, destinationMat);
                if (i != null) {
                    atLeastOneChanged = true;
                    //converts first ingredient it finds
                    newInput = Ingredient.of(i);
                    break;
                }
            }
        }
        //if recipe fails
        if (!atLeastOneChanged) return null;

        AxeCraftingRecipeBuilder builder = new AxeCraftingRecipeBuilder(newInput, newOutput.asItem(), count);
        //builder.group(group);

        builder.unlocks("has_log", InventoryChangeTrigger.TriggerInstance.hasItems(unlockItem));


        AtomicReference<AxeCraftingRecipeBuilder.Result> newRecipe = new AtomicReference<>();

        if (id == null) {
            builder.save(r -> newRecipe.set((AxeCraftingRecipeBuilder.Result) r));
        } else {
            builder.save(r -> newRecipe.set((AxeCraftingRecipeBuilder.Result) r), id);
        }
        return newRecipe.get();
    }

    @Override
    public List<ICondition> getConditions() {
        return conditions;
    }

    @Override
    public void addCondition(ICondition condition) {
        this.conditions.add(condition);
    }
}*/
