package net.mehvahdjukaar.every_compat.modules.chipped;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.EntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.misc.HardcodedBlockType;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.SimpleTagBuilder;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceSink;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.List;
import java.util.function.Consumer;

public class ChippedAbstractModule extends SimpleModule {

    public static String tabPath = "main";

    public ChippedAbstractModule(String modId) {
        super(modId, "ch");
    }

    @Override
    // RECIPES, LOOT_TABLES
    public void addDynamicServerResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicServerResources(executor);

        executor.accept((manager, handler)-> {
            // use this. also set the entry to no drop so we don't have 2.
            // why do we need this instead of copy parent drop? macaw has doors too and they work
            // chipped adds their loot not via loot table. this is why we need this. no other mod should need this stuff
            // this shouldnt be needed.... why isnt copy parent loot working?
            List<EntrySet<?>> doors = this.getEntries().stream().filter(
                    e -> e.getName().contains("door") && !e.getName().contains("trapdoor")).toList();
            for (var e : doors) {
                if (e instanceof SimpleEntrySet<?, ?> se) {
                    for (var d : se.blocks.values()) {
                        handler.addLootTable(d, createDoorLoot(d));
                    }
                }
            }

            addCarpenterRecipe(handler, "planks");
            addCarpenterRecipe(handler, "door");
            addCarpenterRecipe(handler, "trapdoor");
            addCarpenterRecipe(handler, "log");
            addCarpenterRecipe(handler, "stripped_log");
        });
    }

    public static LootTable.Builder createDoorLoot(Block block) {
        return LootTable.lootTable().withPool(
                LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(block)
                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                        .setProperties(StatePropertiesPredicate.Builder.properties()
                                                .hasProperty(DoorBlock.HALF, DoubleBlockHalf.LOWER)))));
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
