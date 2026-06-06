package net.mehvahdjukaar.every_compat.modules.farmersdelight;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.*;
import net.mehvahdjukaar.every_compat.misc.HardcodedBlockType;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceSink;
import net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import vectorwing.farmersdelight.common.block.CabinetBlock;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

import static java.util.Map.entry;
import static net.mehvahdjukaar.every_compat.api.PaletteStrategies.registerCached;
import static net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys.*;

// SUPPORT: FABRIC-v2.4.0+ | FORGE-v1.2.8+
public class FarmersDelightModule extends SimpleModule {
//NOTE: the new version has a reworked recipe system since (FB)-v3.3.0+ or (FG)-v1.3.0+

    public final SimpleEntrySet<WoodType, Block> cabinets;

    public FarmersDelightModule(String modId) {
        super(modId, "fd");

        cabinets = SimpleEntrySet.builder(WoodType.class, "cabinet",
                        getModBlock("oak_cabinet"), () -> VanillaWoodTypes.OAK,
                        w -> new CabinetBlock(Utils.copyPropertySafe(w.planks))
                )
                .requiresChildren(TRAPDOOR, SLAB) //REASON: recipes
                .addTile(getModTile("cabinet"))
                .addTextureM(modRes("block/oak_cabinet_front"),
                        EveryCompat.res("block/fd/oak_cabinet_front_m"),
                        customPalette)
                .addTexture(modRes("block/oak_cabinet_side"), customPalette)
                .addTexture(modRes("block/oak_cabinet_top"), customPalette)
                .addTexture(modRes("block/oak_cabinet_front_open"), customPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("cabinets/wooden"), Registries.ITEM)
                .setTabKey(modRes("farmersdelight"))
                .setTabMode(TabAddMode.AFTER_SAME_TYPE)
                .defaultRecipe()
                .build();
        this.addEntry(cabinets);
    }

    public static final PaletteStrategy customPalette = registerCached((blockType, manager) -> PaletteStrategies.makePaletteFromChild(
            blockType, manager, PLANKS, null, p -> {
                p.reduceDown();
                if (p.size() < 9) {
                    while (p.size() <= 9) {
                        p.increaseInner();
                    }
                } else {
                    while (p.size() >= 9) {
                        p.reduce();
                    }
                }
            })
    );

    @Override
    // RECIPES
    public void addDynamicServerResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicServerResources(executor);

        executor.accept((manager, sink) -> {
            // Creating cutting_board recipes
            for (WoodType woodType : WoodTypeRegistry.INSTANCE) {
                if (HardcodedBlockType.isKnownVanillaWood(woodType)) continue;

                // Skip if one of Farmer's-Cutting compat mods is installed
                String namespaceRegex = COMPAT_RECIPE_MODS.getOrDefault(woodType.getNamespace(), "none");
                boolean isRecipeModNotInstalled = !PlatHelper.getInstalledMods().contains(namespaceRegex);
                boolean isCollectionModNotInstalled = !PlatHelper.getInstalledMods().contains("mr_farmers_cuttingcollection")
                        && !COMPAT_RECIPE_MODS.containsKey(woodType.getNamespace());

                if (isRecipeModNotInstalled && isCollectionModNotInstalled) {
                    createCuttingRecipe(LOG, woodType, sink, manager);
                    createCuttingRecipe(WOOD, woodType, sink, manager);

                    createSalvagingRecipe("furniture", woodType, sink, manager);
                    createSalvagingRecipe(CHEST_BOAT, woodType, sink, manager);
                }
            }
        });
    }

    public void createCuttingRecipe(String recipeType, WoodType woodType, ResourceSink sink, ResourceManager manager) {

        if (Objects.isNull(woodType.getBlockOfThis(recipeType))) return;

        ResourceLocation recipeLocation = ResType.RECIPES.getPath(modRes("cutting/oak_"+recipeType));

        try (InputStream recipeStream = manager.getResource(recipeLocation)
                .orElseThrow(() -> new FileNotFoundException(recipeLocation.toString())).open()) {
            JsonObject recipe = RPUtils.deserializeJson(recipeStream);

            // EDITING RECIPE
            parseAndModifyRecipe(recipe, woodType);

            // Adding to ResourceLocation
            String newPath = woodType.createPathWith(shortenedId(), "cutting/", recipeType);

            sink.addJson(EveryCompat.res(newPath), recipe, ResType.RECIPES);
        } catch (IOException e) {
            EveryCompat.LOGGER.error("Failed to generate the cutting recipe for {} - {}", recipeLocation.toString(), e);
        }
    }

    public void createSalvagingRecipe(String recipeType, WoodType woodType, ResourceSink sink, ResourceManager manager) {

        ResourceLocation recipeLocation = ResType.RECIPES.getPath(modRes("salvaging/oak_"+recipeType));

        try (InputStream recipeStream = manager.getResource(recipeLocation)
                .orElseThrow(() -> new FileNotFoundException(recipeLocation.toString())).open()) {
            JsonObject recipe = RPUtils.deserializeJson(recipeStream);

            // EDITING RECIPE
            parseAndModifyRecipe(recipe, woodType);

            // Adding to ResourceLocation
            String newPath = woodType.createPathWith(shortenedId(), "salvaging/", recipeType);

            sink.addJson(EveryCompat.res(newPath), recipe, ResType.RECIPES);
        } catch (IOException e) {
            EveryCompat.LOGGER.error("Failed to generate the cutting recipe for {} - {}", recipeLocation.toString(), e);
        }
    }

    public static void parseAndModifyRecipe(Object object, WoodType woodType) {

        if (object instanceof JsonObject jsonObject) {
            for (String key : jsonObject.keySet()) {
                switch (key) {
                    case "ingredients" -> {
                        if (jsonObject.get(key).isJsonArray())
                            parseAndModifyRecipe(jsonObject.getAsJsonArray(key), woodType);
                        else
                            parseAndModifyRecipe(jsonObject.getAsJsonObject(key), woodType);
                    }
                    // modifying
                    case "result" -> {
                        if (jsonObject.get(key).isJsonArray()) {
                            parseAndModifyRecipe(jsonObject.getAsJsonArray(key), woodType);
                        }
                    }
                }
            }
        }
        // Modifying ingredients or results - NOTE: this is JsonArray
        else if (object instanceof JsonArray jsonArray) {
            for (int idx = 0; idx < jsonArray.size(); idx++) {
                if (jsonArray.get(idx).isJsonArray()) {
                    parseAndModifyRecipe(jsonArray.get(idx).getAsJsonArray(), woodType);
                }
                else if (jsonArray.get(idx).isJsonObject()) {
                    convertItemType(jsonArray.get(idx).getAsJsonObject(), woodType, "item");
                }
            }
        }
    }

    public static void convertItemType(JsonObject jsonObject, WoodType woodType, String jsonKey) {
        ResourceLocation baseItemId = new ResourceLocation(jsonObject.get(jsonKey).getAsString());
        Item baseItem = BuiltInRegistries.ITEM.get(baseItemId);
        WoodType originalType = WoodTypeRegistry.INSTANCE.getBlockTypeOf(baseItem);

        if (originalType == VanillaWoodTypes.OAK) {
            Item newItem = BlockSetAPI.changeItemType(baseItem, originalType, woodType);

            if (newItem != null)
                jsonObject.addProperty(jsonKey, Utils.getID(newItem).toString());
            else
                jsonObject.remove(jsonKey);
        }
    }


    // a recipe mod, not full Compat-Mod providing cutting-board recipes for other Wood-Mods
    // farmers-cutting-collection.*.jar
    private final Map<String, String> COMPAT_RECIPE_MODS = Map.ofEntries(
        entry("aether", "fcaether"),
        entry("betterend", "fcbe"),
        entry("betternether", "fcbn"),
        entry("biomesoplenty", "fcbop"),
        entry("biomeswevegone", "fcbwg"),
        entry("blue_skies", "fcbs"),
        entry("cinderscapes", "fccs"),
        entry("eternal_starlight", "fces"),
        entry("natures_spirit", "fcns"),
        entry("nethers_exoticism", "fcne"),
        entry("promenade", "fcpromenade"),
        entry("regions_unexplored", "fcru"),
        entry("terrestria", "fcterrestria"),
        entry("twilightforest", "fctf")
    );
}
