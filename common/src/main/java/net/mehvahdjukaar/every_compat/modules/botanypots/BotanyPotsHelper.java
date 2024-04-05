package net.mehvahdjukaar.every_compat.modules.botanypots;

import com.google.gson.JsonObject;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.modules.quark.QuarkModule;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.Item;

import java.io.InputStream;

public class BotanyPotsHelper {

    public static void crop_quarkhedge_recipe(QuarkModule module, Item input, Item output, ServerDynamicResourcesHandler handler, ResourceManager manager, LeavesType leaves) {

        ResourceLocation recipeLocation = EveryCompat.res("template_recipes/botanypots/crop/leaves_from_quark_hedge_crop.json"); // get Recipe JSON
        JsonObject recipe = null;

        try (InputStream recipeStream = manager.getResource(recipeLocation).orElseThrow().open()) {
            recipe = RPUtils.deserializeJson(recipeStream);

            JsonObject getSeed = recipe.getAsJsonObject("seed");
            getSeed.addProperty("item", Utils.getID(input).toString());

            JsonObject getDisplay = recipe.getAsJsonObject("display");
            getDisplay.addProperty("block", Utils.getID(input).toString());

            JsonObject getDrops = recipe.getAsJsonArray("drops").get(0).getAsJsonObject().getAsJsonObject("output");
            getDrops.addProperty("item", Utils.getID(output).toString());

        } catch (Exception e) {
            EveryCompat.LOGGER.error("{BotanyPots Helper} crop_quarkhedge_recipe(): " + e);
        }

        String filenameBuilder = "_from_quark_hedge_crop";
        handler.dynamicPack.addJson(EveryCompat.res(module.shortenedId() + "/" + leaves.getAppendableId() + filenameBuilder), recipe, ResType.RECIPES);
    }
}
