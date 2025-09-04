package net.mehvahdjukaar.every_compat.modules.farmersdelight;

import com.google.gson.JsonObject;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.*;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceSink;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import vectorwing.farmersdelight.common.block.CabinetBlock;
import vectorwing.farmersdelight.common.item.FuelBlockItem;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.function.Consumer;

import static net.mehvahdjukaar.every_compat.api.PaletteStrategies.registerCached;
import static net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys.*;

// SUPPORT: FABRIC-v2.2.7+ | NEOFORGE-v1.2.6+
public class FarmersDelightModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> cabinets;

    public FarmersDelightModule(String modId) {
        super(modId, "fd", EveryCompat.MOD_ID);

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
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(modRes( "farmersdelight"))
                .setTabMode(TabAddMode.AFTER_SAME_TYPE)
                .defaultRecipe()
                .addCustomItem((w, block, p) -> new FuelBlockItem(block, ModItems.basicItem(), 300))
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
                }
                else {
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
            cabinets.items.forEach(((woodType, item) -> {

                createCuttingRecipe("door", woodType.getBlockOfThis("door"), woodType.planks,
                        woodType, sink, manager);
                createCuttingRecipe("hanging_sign", woodType.getBlockOfThis("hanging_sign"), woodType.planks,
                        woodType, sink, manager);
                createCuttingRecipe("sign", woodType.getBlockOfThis("sign"), woodType.planks,
                        woodType, sink, manager);
                createCuttingRecipe("trapdoor", woodType.getBlockOfThis("trapdoor"), woodType.planks,
                        woodType, sink, manager);
                createCuttingRecipe("log", woodType.log, woodType.getBlockOfThis("stripped_log"),
                        woodType, sink, manager);
                createCuttingRecipe("wood", woodType.getBlockOfThis("wood"), woodType.getBlockOfThis("stripped_wood"),
                        woodType, sink, manager);

            }));
        });
    }

    public void createCuttingRecipe(String recipeType, Block input, Block output,
                                    WoodType woodType, ResourceSink sink, ResourceManager manager) {

        if (Objects.nonNull(input) && Objects.nonNull(output)) {
            ResourceLocation recipeLocation = ResType.RECIPES.getPath(modRes("cutting/oak_"+recipeType));

            try (InputStream recipeStream = manager.getResource(recipeLocation)
                    .orElseThrow(() -> new FileNotFoundException(recipeLocation.toString())).open()) {
                JsonObject recipe = RPUtils.deserializeJson(recipeStream);

                // EDITING RECIPE
                JsonObject underIngredients = recipe.getAsJsonArray("ingredients").get(0).getAsJsonObject();
                underIngredients.addProperty("item", Utils.getID(input).toString());

                JsonObject underResult = recipe.getAsJsonArray("result").get(0).getAsJsonObject().getAsJsonObject("item");
                underResult.addProperty("id", Utils.getID(output).toString());

                // Adding to ResourceLocation
                String path = this.shortenedId() + "/cutting/" + woodType.getAppendableId() +"_"+ recipeType;

                sink.addJson(EveryCompat.res(path), recipe, ResType.RECIPES);
            } catch (IOException e) {
                EveryCompat.LOGGER.error("Failed to generate the cutting recipe for {} - {}", Utils.getID(output), e);
            }
        }
    }
}
