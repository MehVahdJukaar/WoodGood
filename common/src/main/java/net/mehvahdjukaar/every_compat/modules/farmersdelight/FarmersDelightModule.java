package net.mehvahdjukaar.every_compat.modules.farmersdelight;

import com.google.gson.JsonObject;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.api.TabAddMode;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceSink;
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
import java.util.Objects;

// SUPPORT: v1.2.4+
// SUPPORT: FABRIC-v2.0.5+
public class FarmersDelightModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> cabinets;

    public FarmersDelightModule(String modId) {
        super(modId, "fd");

        cabinets = SimpleEntrySet.builder(WoodType.class, "cabinet",
                        getModBlock("oak_cabinet"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CabinetBlock(Utils.copyPropertySafe(w.planks))
                )
                .requiresChildren("trapdoor", "slab") //REASON: recipes
                .addTag(modRes("cabinets"), Registries.BLOCK)
                .addTag(modRes("cabinets"), Registries.ITEM)
                .addTag(modRes("cabinets/wooden"), Registries.ITEM)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .defaultRecipe()
                .addTile(getModTile("cabinet"))
                .setTabKey(modRes( "farmersdelight"))
                .setTabMode(TabAddMode.AFTER_SAME_TYPE)
                .createPaletteFromPlanks(p -> {
                    p.reduceDown();
                    if (p.size() < 9) {
                        while (p.size() <= 9) {
                            p.increaseInner();
                        }
                    }
                    else {
                        while (p.size() >= 9) {
                            p.reduce();
                        }
                    }
                })
                .addTextureM(modRes("block/oak_cabinet_front"), EveryCompat.res("block/fd/oak_cabinet_front_m"))
                .addTexture(modRes("block/oak_cabinet_side"))
                .addTexture(modRes("block/oak_cabinet_top"))
                .addTexture(modRes("block/oak_cabinet_front_open"))
                .build();
        this.addEntry(cabinets);
    }

    @Override
    // Recipes
    public void addDynamicServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager, ResourceSink sink) {
        super.addDynamicServerResources(handler, manager, sink);

        // Creating cutting_board recipes
        cabinets.items.forEach(((woodType, item) -> {

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

        }));
    }

    public void createCuttingRecipe(String recipeType, Block input, Block output,
                                    WoodType woodType, ServerDynamicResourcesHandler handler, ResourceManager manager) {

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

                handler.dynamicPack.addJson(EveryCompat.res(path), recipe, ResType.RECIPES);
            } catch (IOException e) {
                handler.getLogger().error("Failed to generate the cutting recipe for {} - {}", Utils.getID(output), e);
            }
        }
    }
}
