package net.mehvahdjukaar.every_compat.modules.farmersdelight;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.block.CabinetBlock;
import vectorwing.farmersdelight.common.registry.ModBlockEntityTypes;
import vectorwing.farmersdelight.common.registry.ModCreativeTabs;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

//SUPPORT: v1.2.4
public class FarmersDelightModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> cabinets;

    public FarmersDelightModule(String modId) {
        super(modId, "fd");

        cabinets = SimpleEntrySet.builder(WoodType.class, "cabinet",
                        () -> getModBlock("oak_cabinet"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CabinetBlock(Utils.copyPropertySafe(w.planks)))
                .addTag(modRes("cabinets"), Registries.BLOCK)
                .addTag(modRes("cabinets"), Registries.ITEM)
                .addTag(modRes("cabinets/wooden"), Registries.ITEM)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .defaultRecipe()
                .addTile(ModBlockEntityTypes.CABINET)
                .setTab(ModCreativeTabs.TAB_FARMERS_DELIGHT::get)
                .createPaletteFromOak(Palette::increaseDown)
                .addTexture(EveryCompat.res("block/oak_cabinet_front"))
                .addTexture(EveryCompat.res("block/oak_cabinet_side"))
                .addTexture(EveryCompat.res("block/oak_cabinet_top"))
                .addTextureM(EveryCompat.res("block/oak_cabinet_front_open"), EveryCompat.res("block/oak_cabinet_front_open_m"))
                .build();

        this.addEntry(cabinets);
    }

    @Override
    // Recipes
    public void addDynamicServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicServerResources(handler, manager);

        // Creating cutting_board recipes for wood_log variants
        cabinets.items.forEach(((woodType, item) -> {
            ResourceLocation recipeLocation = modRes("recipes/cutting/oak_log.json");

            if (Objects.nonNull(woodType.getBlockOfThis("stripped_log"))) {
                try (InputStream recipeStream = manager.getResource(recipeLocation).orElseThrow().open()) {
                    JsonObject recipe = RPUtils.deserializeJson(recipeStream);

                    // EDITING RECIPE
                    JsonObject getItem = recipe.getAsJsonArray("ingredients").get(0).getAsJsonObject();
                    getItem.addProperty("item", Utils.getID(woodType.log).toString());

                    JsonObject getResult = recipe.getAsJsonArray("result").get(0).getAsJsonObject();
                    getResult.addProperty("item", Utils.getID(woodType.getBlockOfThis("stripped_log")).toString());

                    // Adding to ResourceLocation
                    String path = this.shortenedId() + "/cutting/" + woodType.getAppendableId() + "_log";

                    handler.dynamicPack.addJson(EveryCompat.res(path), recipe, ResType.RECIPES);
                }
                catch (IOException e) {
                    handler.getLogger().error("{Farmer's Delight} Failed to generate recipe via " + e);
                }
            }
        }));
    }

}
