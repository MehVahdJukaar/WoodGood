package net.mehvahdjukaar.every_compat.common_classes;

import com.google.gson.JsonObject;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
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
                                                 ResourceLocation newRecipeLoc, ServerDynamicResourcesHandler handler, ResourceManager manager) {
        if (Objects.nonNull(output)) {
            try (InputStream recipeStream = manager.getResource(recipeLoc)
                    .orElseThrow(() -> new FileNotFoundException("Failed to get " + recipeLoc)).open()) {
                JsonObject recipe = RPUtils.deserializeJson(recipeStream);

                // Editing the recipe
                recipe.getAsJsonObject("ingredient").addProperty("tag", tagResLoc.toString());
                recipe.getAsJsonObject("result").addProperty("id", Utils.getID(output).toString());

                // Adding to the resources
                handler.dynamicPack.addJson(newRecipeLoc, recipe, ResType.RECIPES);

            } catch (IOException e) {
                handler.getLogger().error("Failed to generate the recipe @ {} : {}", recipeLoc, e);
            }
        }
    }
}
