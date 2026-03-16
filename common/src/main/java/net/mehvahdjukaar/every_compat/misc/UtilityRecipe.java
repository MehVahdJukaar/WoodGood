package net.mehvahdjukaar.every_compat.misc;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceSink;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.level.block.Block;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@SuppressWarnings("unused")
public class UtilityRecipe {

    /**
     * Create Stonecutting Recipe that use tag as an ingredient
     */
    public static void stonecuttingWithTagRecipe(Block output, ResourceLocation recipeLoc, ResourceLocation tagResLoc,
                                                 ResourceLocation newRecipeLoc, ResourceSink sink, ResourceManager manager) {
        if (Objects.nonNull(output)) {
            try (InputStream recipeStream = manager.getResource(recipeLoc)
                    .orElseThrow(() -> new FileNotFoundException(recipeLoc.toString())).open()) {
                JsonObject recipe = RPUtils.deserializeJson(recipeStream);

                // Editing the recipe
                recipe.getAsJsonObject("ingredient").addProperty("tag", tagResLoc.toString());
                recipe.getAsJsonObject("result").addProperty("id", Utils.getID(output).toString());

                // Adding to the resources
                sink.addJson(newRecipeLoc, recipe, ResType.RECIPES);

            } catch (IOException e) {
                EveryCompat.LOGGER.error("Failed to generate the recipe @ {} : {}", recipeLoc, e);
            }
        }
    }

    /**
     * Create Recipe that use tag as an ingredient. Note the recipe has more than 1 tag
     */
    public static void createRecipeWithTag(ResourceLocation recipeLoc, ResourceLocation newRecipeLoc,
                                           String oldTagIngredient, String newTagIngredient, Object newResult,
                                           ResourceSink sink, ResourceManager manager) {
        if (Objects.nonNull(newResult)) {
            try (InputStream recipeStream = manager.getResource(ResType.RECIPES.getPath(recipeLoc))
                    .orElseThrow(() -> new FileNotFoundException(recipeLoc.toString())).open()) {
                JsonObject recipeJson = RPUtils.deserializeJson(recipeStream);

                // Editing the recipe
                parseAndModifyRecipe(recipeJson, oldTagIngredient, newTagIngredient, Utils.getID(newResult).toString(), recipeLoc);

                // Adding to the resources
                sink.addJson(newRecipeLoc, recipeJson, ResType.RECIPES);

            } catch (IOException e) {
                EveryCompat.LOGGER.error("Failed to generate the recipe @ {} : {}", recipeLoc, e);
            }
        }
    }

    /// Parsing the recipe and modifying elements
    public static void parseAndModifyRecipe(Object object, String oldIngredient, String newIngredien, String newResult, ResourceLocation recipeId) {

        if (object instanceof JsonObject jsonObject) {
            for (String key : jsonObject.keySet()) {
                switch (key) {
                    case "ingredients", "ingredient", "results" -> {
                        if (jsonObject.get(key).isJsonArray())
                            parseAndModifyRecipe(jsonObject.getAsJsonArray(key), oldIngredient, newIngredien, newResult, recipeId);
                        else if (jsonObject.get(key).isJsonObject())
                            parseAndModifyRecipe(jsonObject.getAsJsonObject(key), oldIngredient, newIngredien, newResult, recipeId);
                        else
                            EveryCompat.LOGGER.error("Failed to modify [ingredients, ingredient, or results] via recipe: {}", recipeId);
                    }
                    // modifying
                    case "result" -> {
                        if (jsonObject.getAsJsonObject(key).has("id"))
                            jsonObject.getAsJsonObject(key).addProperty("id", newResult);
                        else
                            EveryCompat.LOGGER.error("Failed to modify 'result' via recipe: {}", recipeId);
                    }
                    case "tag" -> {
                        if (jsonObject.get("tag").getAsString().equals(oldIngredient))
                            jsonObject.addProperty("tag", newIngredien);
                        else
                            EveryCompat.LOGGER.error("Failed to modify 'tag' via recipe: {}", recipeId);
                    }
                    case "item" -> {
                        if (jsonObject.get("item").getAsString().equals(oldIngredient))
                            jsonObject.addProperty("item", newIngredien);
                        else
                            EveryCompat.LOGGER.error("Failed to modify 'item' via recipe: {}", recipeId);
                    }
                }
            }
        }
        // Modifying ingredient or result
        else if (object instanceof JsonArray jsonArray) {
            for (int idx = 0; idx < jsonArray.size(); idx++) {
                JsonObject jsonObject = jsonArray.get(idx).getAsJsonObject();
                if (jsonObject.has("tag") && jsonObject.get("tag").getAsString().equals(oldIngredient)) {
                    jsonObject.addProperty("tag", newIngredien);
                }
                else if (jsonObject.has("item") && jsonObject.get("item").getAsString().equals(oldIngredient)) {
                    jsonObject.addProperty("item", newIngredien);
                }
                else if (jsonObject.has("id")) {
                    jsonObject.addProperty("id", newResult);
                }
            }
        }
    }


}
