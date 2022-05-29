package net.mehvahdjukaar.every_compat.misc;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.selene.Selene;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.mehvahdjukaar.selene.block_set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.selene.resourcepack.recipe.IRecipeTemplate;
import net.mehvahdjukaar.selene.resourcepack.recipe.ShapedRecipeTemplate;
import net.mehvahdjukaar.selene.resourcepack.recipe.ShapelessRecipeTemplate;
import net.mehvahdjukaar.selene.resourcepack.recipe.StoneCutterRecipeTemplate;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Map;

public class CustomRecipeLoader extends SimpleJsonResourceReloadListener {

    private CustomRecipeLoader() {
        super(new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create(), "template_recipes");
    }

    public static void register(final AddReloadListenerEvent event) {
        event.addListener(new CustomRecipeLoader());
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> jsons, ResourceManager pResourceManager, ProfilerFiller pProfiler) {
        int added = 0;
        for (var j : jsons.entrySet()) {
            try {
                if (j.getValue() instanceof JsonObject jo) {
                    IRecipeTemplate<?> template = readRecipeAsTemplate(jo);
                    boolean ad = false;
                    for (var w : WoodTypeRegistry.WOOD_TYPES.values()) {
                        FinishedRecipe newR;
                        if (w == WoodType.OAK_WOOD_TYPE) {
                            //not adding oak for now //TODO: remove
                            continue;
                        }
                        newR = template.createSimilar(WoodType.OAK_WOOD_TYPE, w, w.planks.asItem());
                        //TODO: add
                        ResourceLocation res = WoodGood.res(w.getAppendableId() + j.getKey().getPath());
                        WoodGood.SERVER_RESOURCES.getPack().addRecipe(newR);
                        if (!ad) {
                            ad = true;
                            added++;
                        }
                    }
                }
            } catch (Exception e) {
                WoodGood.LOGGER.warn("Failed to add custom recipe for wood types. Be sure it is based off oak wood:", e);
            }
        }
        if (added != 0)
            Selene.LOGGER.info("Added {} Custom Recipes for all {} wood types", added, WoodTypeRegistry.WOOD_TYPES.size());
    }


    //TODO: replace with lib
    @Deprecated
    public static IRecipeTemplate<?> readRecipeAsTemplate(JsonObject recipe) {
        String type = GsonHelper.getAsString(recipe, "type");
        RecipeSerializer<?> s = ForgeRegistries.RECIPE_SERIALIZERS.getValue(new ResourceLocation(type));
        if (s == RecipeSerializer.SHAPED_RECIPE) {
            return ShapedRecipeTemplate.fromJson(recipe);
        } else if (s == RecipeSerializer.SHAPELESS_RECIPE) {
            return ShapelessRecipeTemplate.fromJson(recipe);
        } else if (s == RecipeSerializer.STONECUTTER) {
            return StoneCutterRecipeTemplate.fromJson(recipe);
        }
        throw new UnsupportedOperationException(String.format("Invalid recipe serializer: %s. Must be either shaped, shapeless or stonecutting", s));
    }

}

