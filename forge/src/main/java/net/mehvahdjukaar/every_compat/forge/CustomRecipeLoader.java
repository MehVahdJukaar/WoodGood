package net.mehvahdjukaar.every_compat.forge;

import com.google.common.collect.Maps;
import com.google.gson.*;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.resources.recipe.IRecipeTemplate;
import net.mehvahdjukaar.moonlight.api.resources.recipe.TemplateRecipeManager;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.profiling.ProfilerFiller;

import java.io.*;
import java.nio.charset.StandardCharsets;
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

    /*
    public static void onEarlyPackLoad(final IEarlyPackReloadEvent event) {
        var manager = event.getManager();
        //code from simple reload listener
        Map<ResourceLocation, JsonElement> map = Maps.newHashMap();
        String directory = "template_recipes";
        int i = directory.length() + 1;

        for (ResourceLocation resourcelocation : manager.listResources(directory, (s) -> s.endsWith(".json"))) {
            String s = resourcelocation.getPath();
            ResourceLocation location = new ResourceLocation(resourcelocation.getNamespace(), s.substring(i, s.length() - PATH_SUFFIX_LENGTH));
            try (Resource resource = manager.getResource(resourcelocation)) {
                try (InputStream inputstream = resource.getInputStream()) {
                    try (Reader reader = new BufferedReader(new InputStreamReader(inputstream, StandardCharsets.UTF_8))) {
                        JsonElement jsonelement = GsonHelper.fromJson(GSON, reader, JsonElement.class);
                        if (jsonelement != null) {
                            JsonElement put = map.put(location, jsonelement);
                            if (put != null) {
                                throw new IllegalStateException("Duplicate data file ignored with ID " + location);
                            }
                        } else {
                            EveryCompat.LOGGER.error("Couldn't load data file {} from {} as it's null or empty", location, resourcelocation);
                        }
                    }
                }
            } catch (IllegalArgumentException | IOException | JsonParseException exception) {
                EveryCompat.LOGGER.error("Couldn't parse data file {} from {}", location, resourcelocation, exception);
            }
        }
        loadRecipes(map);
    }
*/
    @Override
    protected void apply(Map<ResourceLocation, JsonElement> jsons, ResourceManager pResourceManager, ProfilerFiller pProfiler) {
        loadRecipes(jsons);
    }

    //TODO:fix, this isnt loading anymore
    private static void loadRecipes(Map<ResourceLocation, JsonElement> jsons) {
        int added = 0;
        boolean oldStatus = ServerDynamicResourcesHandler.INSTANCE.getPack().generateDebugResources;
        ServerDynamicResourcesHandler.INSTANCE.getPack().generateDebugResources = true;
        for (var j : jsons.entrySet()) {
            try {
                if (j.getValue() instanceof JsonObject jo) {
                    IRecipeTemplate<?> template = TemplateRecipeManager.read(jo);
                    boolean ad = false;
                    for (var w : WoodTypeRegistry.getTypes()) {
                        FinishedRecipe newR;
                        if (w == WoodTypeRegistry.OAK_TYPE) {
                            //not adding oak for now //TODO: remove
                            continue;
                        }
                        ResourceLocation res = EveryCompat.res(w.getAppendableId() + j.getKey().getPath());

                        newR = template.createSimilar(WoodTypeRegistry.OAK_TYPE, w, w.planks.asItem(), res.toString());

                        ServerDynamicResourcesHandler.INSTANCE.getPack().addRecipe(newR);
                        if (!ad) {
                            ad = true;
                            added++;
                        }
                    }
                }
            } catch (Exception e) {
                EveryCompat.LOGGER.warn("Failed to add custom recipe for wood types. Be sure it is based off oak wood:", e);
            }
        }
        ServerDynamicResourcesHandler.INSTANCE.getPack().generateDebugResources = oldStatus;
        if (added != 0)
            EveryCompat.LOGGER.info("Added {} Custom Recipes for all {} wood types", added, WoodTypeRegistry.getTypes().size());
    }


}

