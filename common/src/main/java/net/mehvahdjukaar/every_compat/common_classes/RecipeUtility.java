package net.mehvahdjukaar.every_compat.common_classes;

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
public class RecipeUtility {

    /**
     * Create Stonecutting Recipe that use tag as an ingredient
     */
    public static void stonecuttingWithTagRecipe(Block output, ResourceLocation recipeLoc, ResourceLocation tagResLoc,
                                                 ResourceLocation newRecipeLoc, ResourceSink sink, ResourceManager manager) {
        if (Objects.nonNull(output)) {
            try (InputStream recipeStream = manager.getResource(recipeLoc)
                    .orElseThrow(() -> new FileNotFoundException("Failed to get " + recipeLoc)).open()) {
                JsonObject recipe = RPUtils.deserializeJson(recipeStream);

                // Editing the recipe
                recipe.getAsJsonObject("ingredient").addProperty("tag", tagResLoc.toString());
                recipe.addProperty("result", Utils.getID(output).toString());

                // Adding to the resources
                sink.addJson(newRecipeLoc, recipe, ResType.RECIPES);

            } catch (IOException e) {
                EveryCompat.LOGGER.error("Failed to generate the recipe @ {} : {}", recipeLoc, e);
            }
        }
    }
}
