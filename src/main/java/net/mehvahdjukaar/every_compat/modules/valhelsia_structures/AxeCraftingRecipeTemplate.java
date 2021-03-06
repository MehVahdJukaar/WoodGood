package net.mehvahdjukaar.every_compat.modules.valhelsia_structures;

import com.google.gson.JsonObject;
import com.stal111.valhelsia_structures.common.recipe.AxeCraftingRecipeBuilder;
import net.mehvahdjukaar.selene.block_set.BlockType;
import net.mehvahdjukaar.selene.resourcepack.recipe.IRecipeTemplate;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.ICondition;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class AxeCraftingRecipeTemplate implements IRecipeTemplate<AxeCraftingRecipeBuilder.Result> {
    public final Item output;
    public final int count;
    public final String group;
    public final Ingredient input;

    private List<ICondition> conditions = new ArrayList<>();

    public AxeCraftingRecipeTemplate(JsonObject json) {

        JsonObject output = json.get("output").getAsJsonObject();

        ResourceLocation item = new ResourceLocation(output.get("item").getAsString());
        int count = 1;
        var c = output.get("count");
        if (c != null) count = c.getAsInt();

        this.count = count;
        this.output = Registry.ITEM.get(item);

        var g = json.get("group");
        this.group = g == null ? "" : g.getAsString();

        this.input = Ingredient.fromJson(json.get("input"));
    }

    @Override
    public <T extends BlockType> AxeCraftingRecipeBuilder.Result createSimilar(
            T originalMat, T destinationMat, Item unlockItem, @Nullable String id) {
        ItemLike newOutput = BlockType.changeItemBlockType(this.output, originalMat, destinationMat);
        if (newOutput == null)
            throw new UnsupportedOperationException(String.format("Could not convert output item %s", output));

        boolean atLeastOneChanged = false;
        Ingredient newInput = input;
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
}
