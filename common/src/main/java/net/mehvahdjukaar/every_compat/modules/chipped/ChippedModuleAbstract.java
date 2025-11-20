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

//SUPPORT: v3.0.7+
public class ChippedModuleAbstract extends SimpleModule {

    public static String tabPath = "main";

    public ChippedModuleAbstract(String modId) {
        super(modId, "ch");
    }

    protected void addCarpenterRecipe(ResourceSink sink, String identifier) {
        JsonArray arrayTags = new JsonArray();

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

            if (isTagCreated) {
                sink.addTag(tagBuilder, Registries.ITEM);
                sink.addTag(tagBuilder, Registries.BLOCK);
                arrayTags.add(tagBuilder.getId().toString());
            }
        }
        JsonObject jo = new JsonObject();
        jo.addProperty("type", "chipped:" + "carpenters_table");
        jo.add("tags", arrayTags);
        sink.addJson(EveryCompat.res(shortenedId() + "/" + "carpenters_table" + "_" + identifier), jo, ResType.RECIPES);

    }
}
