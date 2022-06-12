package net.mehvahdjukaar.every_compat.misc;

import com.google.common.collect.Maps;
import com.google.gson.*;
import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.mehvahdjukaar.selene.block_set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.selene.resourcepack.EarlyPackReloadEvent;
import net.mehvahdjukaar.selene.resourcepack.recipe.IRecipeTemplate;
import net.mehvahdjukaar.selene.resourcepack.recipe.TemplateRecipeManager;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.server.packs.resources.SimpleReloadInstance;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.profiling.ProfilerFiller;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;

public class CustomRecipeLoader extends SimpleJsonResourceReloadListener {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    private CustomRecipeLoader() {
        super(GSON, "template_recipes");
    }

    // public static void register(final AddReloadListenerEvent event) {
    //     event.addListener(new CustomRecipeLoader());
    // }
    private static final int PATH_SUFFIX_LENGTH = ".json".length();

    public static void onEarlyPackLoad(final EarlyPackReloadEvent event) {
        var manager = event.getManager();
        //code from simple reload listener
        Map<ResourceLocation, JsonElement> map = Maps.newHashMap();
        String directory = "template_recipes";
        int i = directory.length() + 1;

        for (ResourceLocation resourcelocation : manager.listResources(directory, (s) -> s.endsWith(".json"))) {
            String s = resourcelocation.getPath();
            ResourceLocation location = new ResourceLocation(resourcelocation.getNamespace(), s.substring(i, s.length() - PATH_SUFFIX_LENGTH));
            try(Resource resource = manager.getResource(resourcelocation)) {
                try(InputStream inputstream = resource.getInputStream()) {
                    try(Reader reader = new BufferedReader(new InputStreamReader(inputstream, StandardCharsets.UTF_8))) {
                        JsonElement jsonelement = GsonHelper.fromJson(GSON, reader, JsonElement.class);
                        if (jsonelement != null) {
                            JsonElement put = map.put(location, jsonelement);
                            if (put != null) {
                                throw new IllegalStateException("Duplicate data file ignored with ID " + location);
                            }
                        } else {
                            WoodGood.LOGGER.error("Couldn't load data file {} from {} as it's null or empty", location, resourcelocation);
                        }
                    }
                }
            } catch (IllegalArgumentException | IOException | JsonParseException exception) {
                WoodGood.LOGGER.error("Couldn't parse data file {} from {}", location, resourcelocation, exception);
            }
        }
        loadRecipes(map);
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> jsons, ResourceManager pResourceManager, ProfilerFiller pProfiler) {
        loadRecipes(jsons);
    }
//TODO:fix, this isnt loading anymore
    private static void loadRecipes(Map<ResourceLocation, JsonElement> jsons) {
        int added = 0;
        boolean oldStatus = WoodGood.SERVER_RESOURCES.getPack().generateDebugResources;
        WoodGood.SERVER_RESOURCES.getPack().generateDebugResources = true;
        for (var j : jsons.entrySet()) {
            try {
                if (j.getValue() instanceof JsonObject jo) {
                    IRecipeTemplate<?> template = TemplateRecipeManager.read(jo);
                    boolean ad = false;
                    for (var w : WoodTypeRegistry.WOOD_TYPES.values()) {
                        FinishedRecipe newR;
                        if (w == WoodType.OAK_WOOD_TYPE) {
                            //not adding oak for now //TODO: remove
                            continue;
                        }
                        ResourceLocation res = WoodGood.res(w.getAppendableId() + j.getKey().getPath());

                        newR = template.createSimilar(WoodType.OAK_WOOD_TYPE, w, w.planks.asItem(), res.toString());

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
        WoodGood.SERVER_RESOURCES.getPack().generateDebugResources = oldStatus;
        if (added != 0)
            WoodGood.LOGGER.info("Added {} Custom Recipes for all {} wood types", added, WoodTypeRegistry.WOOD_TYPES.size());
    }


}

