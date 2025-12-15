package net.mehvahdjukaar.every_compat.modules.chipped;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.misc.HardcodedBlockType;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.SimpleTagBuilder;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceSink;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;

//SUPPORT: v4.0.2+
public class ChippedModuleAbstract extends SimpleModule {

    public static String tabPath = "main";

    public ChippedModuleAbstract(String modId) {
        super(modId, "ch", EveryCompat.MOD_ID);
    }

    @SuppressWarnings("SameParameterValue")
    protected void addCarpenterRecipe(ResourceSink pack, String identifier) {
        JsonArray ingredients = new JsonArray();

        for (var woodType : WoodTypeRegistry.INSTANCE) {
            if (HardcodedBlockType.isKnownVanillaWood(woodType)) continue;

            boolean isTagCreated = false;
            String suffixedFile = (identifier.equals("stripped_log"))
                    ? woodType.getAppendableIdWith("stripped", "log")
                    : woodType.getAppendableIdWith(identifier);

            SimpleTagBuilder tagBuilder = SimpleTagBuilder.of(EveryCompat.res(
                    shortenedId() + "/" + suffixedFile));

            for (var entry : this.getEntries()) {
                String name = entry.getName();

                boolean isStrippedLog = identifier.equals("stripped_log") && name.contains("stripped");

                if (name.matches(".*(_" + identifier + "|" + identifier + "_).*") || isStrippedLog) {
                    if (identifier.equals("door") && name.matches(".*(_trapdoor|trapdoor_).*")) continue;
                    if (identifier.equals("log") && name.matches(".*(_stripped_log|stripped_).*")) continue;
                    if (identifier.equals("stripped_log") && !name.contains("stripped")) continue;
                    Item item = ((SimpleEntrySet<?, ?>) entry).items.get(woodType);
                    if (item != null) {
                        tagBuilder.addEntry(item);
                        isTagCreated = true;
                    }
                }
            }


            // Checking for Child of wood type exist
            if (woodType.getChild(identifier) != null) {
                switch (identifier) { // Adds normal or modded blockType
                    case "planks" -> tagBuilder.addEntry(woodType.planks);
                    case "door" -> tagBuilder.addEntry(woodType.getChild("door"));
                    case "trapdoor" -> tagBuilder.addEntry(woodType.getChild("trapdoor"));
                    case "log" -> tagBuilder.addEntry(woodType.log);
                    case "stripped_log" -> tagBuilder.addEntry(woodType.getChild("stripped_log"));
                }
            }

            JsonObject tagObject = new JsonObject();
            if (isTagCreated) {
                pack.addTag(tagBuilder, Registries.ITEM);
                pack.addTag(tagBuilder, Registries.BLOCK);
                tagObject.addProperty("tag", tagBuilder.getId().toString());
                ingredients.add(tagObject);
            }

        }
        JsonObject recipeJO = new JsonObject();
        recipeJO.addProperty("type", "chipped:" + "workbench");
        recipeJO.add("ingredients", ingredients);
        if (!ingredients.isEmpty()) pack.addJson(EveryCompat.res(shortenedId() + "/" + "carpenters_table" + "_" + identifier), recipeJO, ResType.RECIPES);

    }
}
