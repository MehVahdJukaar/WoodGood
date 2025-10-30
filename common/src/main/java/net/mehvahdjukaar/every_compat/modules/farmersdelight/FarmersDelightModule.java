package net.mehvahdjukaar.every_compat.modules.farmersdelight;

import com.google.gson.JsonObject;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.*;
import net.mehvahdjukaar.every_compat.misc.HardcodedBlockType;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceSink;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
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

        executor.accept((manager, handler) -> {
            // Creating cutting_board recipes
            for (WoodType woodType : WoodTypeRegistry.INSTANCE) {
                if (HardcodedBlockType.isKnownVanillaWood(woodType)) continue;

                // Skip if one of Farmer's-Cutting mods is installed
                String namespaceRegex = COMPAT_RECIPE_MODS.getOrDefault(woodType.getNamespace(), "none");
                boolean isRecipeModNotInstalled = !PlatHelper.getInstalledMods().contains(namespaceRegex);

                if (isRecipeModNotInstalled) {
                    createCuttingRecipe("door", woodType.getBlockOfThis("door"), woodType.planks,
                            woodType, handler, manager);
                    createCuttingRecipe("hanging_sign", woodType.getBlockOfThis("hanging_sign"), woodType.planks,
                            woodType, handler, manager);
                    createCuttingRecipe("sign", woodType.getBlockOfThis("sign"), woodType.planks,
                            woodType, handler, manager);
                    createCuttingRecipe("trapdoor", woodType.getBlockOfThis("trapdoor"), woodType.planks,
                            woodType, handler, manager);
                    createCuttingRecipe("log", woodType.log, woodType.getBlockOfThis("stripped_log"),
                            woodType, handler, manager);
                    createCuttingRecipe("wood", woodType.getBlockOfThis("wood"), woodType.getBlockOfThis("stripped_wood"),
                            woodType, handler, manager);
                }
            }
        });
    }

    public void createCuttingRecipe(String recipeType, Block input, Block output,
                                    WoodType woodType, ResourceSink handler, ResourceManager manager) {

        if (Objects.nonNull(input) && Objects.nonNull(output)) {
        ResourceLocation recipeLocation = modRes("recipes/cutting/oak_"+recipeType+".json");

            try (InputStream recipeStream = manager.getResource(recipeLocation)
                    .orElseThrow(() -> new FileNotFoundException(recipeLocation.toString())).open()) {
                JsonObject recipe = RPUtils.deserializeJson(recipeStream);

                // EDITING RECIPE
                JsonObject getItem = recipe.getAsJsonArray("ingredients").get(0).getAsJsonObject();
                getItem.addProperty("item", Utils.getID(input).toString());

                JsonObject getResult = recipe.getAsJsonArray("result").get(0).getAsJsonObject();
                getResult.addProperty("item", Utils.getID(output).toString());

                // Adding to ResourceLocation
                String path = this.shortenedId() + "/cutting/" + woodType.getAppendableId() +"_"+recipeType;

                handler.addJson(EveryCompat.res(path), recipe, ResType.RECIPES);
            } catch (IOException e) {
                EveryCompat.LOGGER.error("Failed to generate the cutting recipe for {} - {}", Utils.getID(output), e);
            }
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
