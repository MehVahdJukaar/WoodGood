package net.mehvahdjukaar.every_compat.modules.chipped;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.misc.HardcodedBlockType;
import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.SimpleTagBuilder;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceSink;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesTypeRegistry;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;

import static net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys.*;

///SUPPORT: v4.0.2+
public class ChippedModuleAbstract extends EveryCompatModule {

    public static String tabPath = "main";

    public ChippedModuleAbstract(String modId) {
        super(modId, "ch");
    }

    @SuppressWarnings("SameParameterValue")
    protected void addCarpenterRecipe(ResourceSink sink, String identifier) {
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


            // Checking for Vanilla Child of wood type exist
            if (woodType.getChild(identifier) != null) {
                switch (identifier) { // Adds normal or modded blockType
                    case PLANKS -> tagBuilder.addEntry(woodType.planks);
                    case DOOR -> tagBuilder.addEntry(woodType.getChild(DOOR));
                    case TRAPDOOR -> tagBuilder.addEntry(woodType.getChild(TRAPDOOR));
                    case LOG -> tagBuilder.addEntry(woodType.log);
                    case STRIPPED_LOG -> tagBuilder.addEntry(woodType.getChild(STRIPPED_LOG));
                }
            }

            JsonObject tagObject = new JsonObject();
            if (isTagCreated) {
                sink.addTag(tagBuilder, Registries.ITEM);
                sink.addTag(tagBuilder, Registries.BLOCK);
                tagObject.addProperty("tag", tagBuilder.getId().toString());
                ingredients.add(tagObject);
            }

        }
        JsonObject recipeJO = new JsonObject();
        recipeJO.addProperty("type", "chipped:" + "workbench");
        recipeJO.add("ingredients", ingredients);
        if (!ingredients.isEmpty()) sink.addJson(EveryCompat.res(shortenedId() + "/" + "carpenters_table" + "_" + identifier), recipeJO, ResType.RECIPES);

    }

    protected void addBotanistRecipe(ResourceSink sink) {
        JsonArray ingredients = new JsonArray();

        for (var leavesType : LeavesTypeRegistry.INSTANCE) {
            if (HardcodedBlockType.isKnownVanillaLeaves(leavesType)) continue;

            boolean isTagCreated = false;
            String suffixedFile = leavesType.getAppendableIdWith(LEAVES);

            SimpleTagBuilder tagBuilder = SimpleTagBuilder.of(EveryCompat.res(
                    shortenedId() + "/" + suffixedFile));

            for (var entry : this.getEntries()) {
                String name = entry.getName();

                if (name.matches(".*(_" + LEAVES + "|" + LEAVES + "_).*")) {
                    Item item = ((SimpleEntrySet<?, ?>) entry).items.get(leavesType);
                    if (item != null) {
                        tagBuilder.addEntry(item);
                        isTagCreated = true;
                    }
                }
            }

            // Checking for the Vanilla Child of leave type exist
            if (leavesType.getChild(LEAVES) != null) {
                tagBuilder.addEntry(leavesType.getChild(LEAVES));
            }

            JsonObject tagObject = new JsonObject();
            if (isTagCreated) {
                sink.addTag(tagBuilder, Registries.ITEM);
                sink.addTag(tagBuilder, Registries.BLOCK);
                tagObject.addProperty("tag", tagBuilder.getId().toString());
                ingredients.add(tagObject);
            }
        }
        JsonObject jo = new JsonObject();
        jo.addProperty("type", "chipped:" + "workbench");
        jo.add("ingredients", ingredients);
        if (!ingredients.isEmpty()) sink.addJson(EveryCompat.res(shortenedId() + "/" + "botanist_workbench" + "_" + LEAVES), jo, ResType.RECIPES);

    }
}
