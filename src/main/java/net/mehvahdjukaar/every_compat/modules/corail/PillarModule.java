package net.mehvahdjukaar.every_compat.modules.corail;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.mehvahdjukaar.selene.resourcepack.ResType;
import net.minecraft.core.Registry;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import ovh.corail.corail_pillar.block.BlockPillar;
import ovh.corail.corail_pillar.registry.ModTabs;

import java.util.Objects;

//SUPPORT: v5.4.1
public class PillarModule extends SimpleModule {
    public final SimpleEntrySet<WoodType, Block> LOG_PILLAR;
    public final SimpleEntrySet<WoodType, Block> SMALL_LOG_PILLAR;
    public final SimpleEntrySet<WoodType, Block> PLANK_PILLAR;
    public final SimpleEntrySet<WoodType, Block> SMALL_PLANK_PILLAR;
    public PillarModule(String modId) {
        super(modId, "cpr");
        CreativeModeTab tab = ModTabs.mainTab;

        LOG_PILLAR = SimpleEntrySet.builder(WoodType.class, "log", "pillar",
                () -> getModBlock("pillar_oak_log"), () -> WoodType.OAK_WOOD_TYPE,
                w -> new BlockPillar(w.getTypeName(), w.log, false)
        )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("pillar"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("wooden_pillar"), Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .build();
        this.addEntry(LOG_PILLAR);

        SMALL_LOG_PILLAR = SimpleEntrySet.builder(WoodType.class, "log", "small_pillar",
                () -> getModBlock("small_pillar_oak_log"), () -> WoodType.OAK_WOOD_TYPE,
                w -> new BlockPillar(w.getTypeName(), w.log, true)
        )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("pillar"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("wooden_pillar"), Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .build();
        this.addEntry(SMALL_LOG_PILLAR);

        PLANK_PILLAR = SimpleEntrySet.builder(WoodType.class, "planks", "pillar",
                () -> getModBlock("pillar_oak_planks"), () -> WoodType.OAK_WOOD_TYPE,
                w -> new BlockPillar(w.getTypeName(), w.log, true)
        )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("pillar"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("wooden_pillar"), Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .build();
        this.addEntry(PLANK_PILLAR);

        SMALL_PLANK_PILLAR = SimpleEntrySet.builder(WoodType.class, "planks", "small_pillar",
                () -> getModBlock("small_pillar_oak_planks"), () -> WoodType.OAK_WOOD_TYPE,
                w -> new BlockPillar(w.getTypeName(), w.log, true)
        )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("pillar"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("wooden_pillar"), Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .build();
        this.addEntry(SMALL_PLANK_PILLAR);

    }

    @Override
    public void addDynamicServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicServerResources(handler, manager);

        LOG_PILLAR.items.forEach((wood, item) -> {
            //TYPE: LOG_PILLAR
            woodcuttingRecipe(handler, wood, wood.log.asItem(), item,2);
            PILLAR_from_small_pillar(handler, wood, SMALL_LOG_PILLAR.items.get(wood), item);

            //TYPE: SMALL_LOG_PILLAR
            woodcuttingRecipe(handler, wood, wood.log.asItem(), SMALL_LOG_PILLAR.items.get(wood),4,true);
            SMALL_PILLAR_from_woods(handler, wood, wood.log.asItem(), SMALL_LOG_PILLAR.items.get(wood));
            SMALL_PILLAR_from_pillar(handler, wood, item, SMALL_LOG_PILLAR.items.get(wood));

            //TYPE: PLANK_PILLAR
            woodcuttingRecipe(handler, wood, wood.planks.asItem(), PLANK_PILLAR.items.get(wood),2);
            PILLAR_from_small_pillar(handler, wood, SMALL_PLANK_PILLAR.items.get(wood), PLANK_PILLAR.items.get(wood));

            //TYPE: SMALL_PLANK_PILLAR
            woodcuttingRecipe(handler, wood, wood.planks.asItem(), SMALL_PLANK_PILLAR.items.get(wood),4,true);
            SMALL_PILLAR_from_woods(handler, wood, wood.planks.asItem(), SMALL_PLANK_PILLAR.items.get(wood));
            SMALL_PILLAR_from_pillar(handler, wood, PLANK_PILLAR.items.get(wood), SMALL_PLANK_PILLAR.items.get(wood));

        });
    }

    // with a default parameter
    public void woodcuttingRecipe(ServerDynamicResourcesHandler handler, WoodType wood, Item input, Item output, int count) {
        woodcuttingRecipe(handler, wood, input, output, count,false);
    }

    public void woodcuttingRecipe(ServerDynamicResourcesHandler handler, WoodType wood, Item input, Item output, int count, boolean isSmall) {
        String filenameBuilder = "pillar_" + input.toString();
        if (isSmall) {
            filenameBuilder = "small_" + filenameBuilder;
        }

        JsonObject insideCondition = new JsonObject();
        insideCondition.addProperty("type", "forge:mod_loaded");
        insideCondition.addProperty("modid", "corail_woodcutter");

        JsonArray condition = new JsonArray();
        condition.add(insideCondition);

        JsonObject ingredient = new JsonObject();
        ingredient.addProperty("item", Objects.requireNonNull(input.getRegistryName()).toString());

        JsonObject recipe = new JsonObject();
        recipe.addProperty("type", "corail_woodcutter:woodcutting");
        recipe.add("ingredient", ingredient);
        recipe.addProperty("result", Objects.requireNonNull(output.getRegistryName()).toString());
        recipe.addProperty("count", count);
        recipe.add("condition", condition);

        handler.dynamicPack.addJson(WoodGood.res("cpr/" + wood.getNamespace() + "/woodcutting/" + filenameBuilder), recipe, ResType.RECIPES);
    }


    public void SMALL_PILLAR_from_woods(ServerDynamicResourcesHandler handler, WoodType wood, Item input, Item output) {

        String filenameBuilder = "pillar_wood/";
        if (input == wood.log.asItem()) {
            filenameBuilder += "log/";
        }
        else {
            filenameBuilder += "planks/";
        }
        filenameBuilder += wood.getTypeName();

        JsonObject result = new JsonObject();
        result.addProperty("item", Objects.requireNonNull(output.getRegistryName()).toString());
        result.addProperty("count", 6);

        JsonObject clay = new JsonObject();
        clay.addProperty("item", "minecraft:clay_ball");
        JsonObject log = new JsonObject();
        log.addProperty("item", Objects.requireNonNull(input.getRegistryName()).toString());


        JsonObject key = new JsonObject();
        key.add("0", clay);
        key.add("1", log);

        JsonArray pattern = new JsonArray();
        pattern.add(" 1 ");
        pattern.add(" 0 ");
        pattern.add(" 1 ");

        JsonObject recipe = new JsonObject();
        recipe.addProperty("type", "minecraft:crafting_shaped");
        recipe.add("pattern", pattern);
        recipe.add("key", key);
        recipe.add("result", result);

        handler.dynamicPack.addJson(WoodGood.res("cpr/" + wood.getNamespace() + "/woodcutting/" + filenameBuilder), recipe, ResType.RECIPES);
    }

    public void SMALL_PILLAR_from_pillar(ServerDynamicResourcesHandler handler, WoodType wood, Item input, Item output) {
        String filenameBuilder = "small_pillar_wood/";
        if (input == wood.log.asItem() || input == LOG_PILLAR.items.get(wood)) {
            filenameBuilder += "log/";
        }
        else {
            filenameBuilder += "planks/";
        }
        filenameBuilder += wood.getTypeName();

        JsonObject result = new JsonObject();
        result.addProperty("item", Objects.requireNonNull(output.getRegistryName()).toString());
        result.addProperty("count", 2);

        JsonObject insideIngredient = new JsonObject();
        insideIngredient.addProperty("item", Objects.requireNonNull(input.getRegistryName()).toString());
        JsonArray ingredients = new JsonArray();
        ingredients.add(insideIngredient);

        JsonObject recipe = new JsonObject();
        recipe.addProperty("type", "minecraft:crafting_shapeless");
        recipe.add("ingredients", ingredients);
        recipe.add("result", result);

        handler.dynamicPack.addJson(WoodGood.res("cpr/" + wood.getNamespace() + "/woodcutting/" + filenameBuilder), recipe, ResType.RECIPES);

    }

    public void PILLAR_from_small_pillar(ServerDynamicResourcesHandler handler, WoodType wood, Item input, Item output) {
        String filenameBuilder = wood.getTypeName() + "pillar_to_small_pillar";

        if (input == wood.log.asItem() || input == SMALL_LOG_PILLAR.items.get(wood)) {
            filenameBuilder += "_log";
        }
        else {
            filenameBuilder += "_planks";
        }

        JsonObject result = new JsonObject();
        result.addProperty("item", Objects.requireNonNull(output.getRegistryName()).toString());

        JsonObject ingredient = new JsonObject();
        ingredient.addProperty("item", Objects.requireNonNull(input.getRegistryName()).toString());


        JsonObject key = new JsonObject();
        key.add("0", ingredient);

        JsonArray pattern = new JsonArray();
        pattern.add("00");

        JsonObject recipe = new JsonObject();
        recipe.addProperty("type", "minecraft:crafting_shaped");
        recipe.add("pattern", pattern);
        recipe.add("key", key);
        recipe.add("result", result);

        handler.dynamicPack.addJson(WoodGood.res("cpr/" + wood.getNamespace() + "/woodcutting/" + filenameBuilder), recipe, ResType.RECIPES);
    }

}

