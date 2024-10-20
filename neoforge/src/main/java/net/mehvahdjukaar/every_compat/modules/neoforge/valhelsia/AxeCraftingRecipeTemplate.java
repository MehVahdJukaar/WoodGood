package net.mehvahdjukaar.every_compat.modules.neoforge.valhelsia;

import com.google.gson.JsonObject;
import com.stal111.valhelsia_structures.common.recipe.AxeCraftingRecipeBuilder;
import net.mehvahdjukaar.moonlight.api.resources.recipe.IRecipeTemplate;
import net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class AxeCraftingRecipeTemplate implements IRecipeTemplate<AxeCraftingRecipeBuilder.Result> {
    public final Item output;
    public final int count;
    public final String group;
    public final Ingredient input;

    private final List<Object> conditions = new ArrayList<>();

    public AxeCraftingRecipeTemplate(JsonObject json) {

        JsonObject output = json.get("output").getAsJsonObject();

        ResourceLocation item = ResourceLocation.parse(output.get("item").getAsString());
        int count = 1;
        var c = output.get("count");
        if (c != null) count = c.getAsInt();

        this.count = count;
        this.output = BuiltInRegistries.ITEM.get(item);

        var g = json.get("group");
        this.group = g == null ? "" : g.getAsString();

        this.input = Ingredient.fromJson(json.get("input"));
    }

    @Override
    public <T extends BlockType> AxeCraftingRecipeBuilder.Result createSimilar(
            T originalMat, T destinationMat, Item unlockItem, @Nullable String id) {
        ItemLike newOutput = BlockSetAPI.changeItemType(this.output, originalMat, destinationMat);
        if (newOutput == null)
            throw new UnsupportedOperationException(String.format("Could not convert output item %s", output));

        boolean atLeastOneChanged = false;
        Ingredient newInput = input;
        for (var in : newInput.getItems()) {
            Item it = in.getItem();
            if (it != Items.BARRIER) {
                ItemLike i = BlockSetAPI.changeItemType(it, originalMat, destinationMat);
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

        AxeCraftingRecipeBuilder builder = new AxeCraftingRecipeBuilder(RecipeCategory.BUILDING_BLOCKS, newInput, newOutput.asItem(), count);
        //builder.group(group);

        builder.unlockedBy("has_log", InventoryChangeTrigger.TriggerInstance.hasItems(unlockItem));


        AtomicReference<AxeCraftingRecipeBuilder.Result> newRecipe = new AtomicReference<>();

        if (id == null) {
            builder.save(r -> newRecipe.set((AxeCraftingRecipeBuilder.Result) r));
        } else {
            builder.save(r -> newRecipe.set((AxeCraftingRecipeBuilder.Result) r), id);
        }
        return newRecipe.get();
    }

    @Override
    public void addCondition(Object condition) {
        this.conditions.add(condition);
    }

    @Override
    public List<Object> getConditions() {
        return conditions;
    }

}