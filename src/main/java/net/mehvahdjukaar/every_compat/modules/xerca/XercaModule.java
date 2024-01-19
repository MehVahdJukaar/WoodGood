package net.mehvahdjukaar.every_compat.modules.xerca;

import com.google.gson.JsonObject;
import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.mehvahdjukaar.selene.block_set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.selene.client.asset_generators.textures.Palette;
import net.mehvahdjukaar.selene.resourcepack.RPUtils;
import net.mehvahdjukaar.selene.resourcepack.ResType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import xerca.xercamod.common.DecoCreativeTab;
import xerca.xercamod.common.block.BlockCarvedLog;
import xerca.xercamod.common.block.Blocks;
import xerca.xercamod.common.item.CarvedWoodItem;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

//SUPPORT: v1.0.1
public class XercaModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> carved1;
    public final SimpleEntrySet<WoodType, Block> carved2;
    public final SimpleEntrySet<WoodType, Block> carved3;
    public final SimpleEntrySet<WoodType, Block> carved4;
    public final SimpleEntrySet<WoodType, Block> carved5;
    public final SimpleEntrySet<WoodType, Block> carved6;
    public final SimpleEntrySet<WoodType, Block> carved7;
    public final SimpleEntrySet<WoodType, Block> carved8;

    public ArrayList<String> modelPath = new ArrayList<>();
    public final String Path = this.shortenedId() + "/";


    public XercaModule(String modId) {
        super(modId, "x");
        CreativeModeTab tab = DecoCreativeTab.TAB_BUILDING_BLOCKS;

        carved1 = SimpleEntrySet.builder(WoodType.class, "1", "carved",
                        () ->Blocks.CARVED_DARK_OAK_1, () -> WoodTypeRegistry.getValue(new ResourceLocation("dark_oak")),
                        w -> new BlockCarvedLog(Path + w.getNamespace() + "/carved_" + w.getTypeName() + "_1"))
                .addCustomItem((w, b, p) -> new CarvedWoodItem(b, p, 1))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(WoodGood.res("block/carved_wood/carved_dark_oak_1"))
                .addTexture(WoodGood.res("block/carved_wood/carved_dark_oak_1_top"))
                .setTab(() -> tab)
                .build();
        this.addEntry(carved1);

        carved2 = SimpleEntrySet.builder(WoodType.class, "2", "carved",
                        () -> Blocks.CARVED_DARK_OAK_2, () -> WoodTypeRegistry.getValue(new ResourceLocation("dark_oak")),
                        w -> new BlockCarvedLog(Path + w.getNamespace() + "/carved_" + w.getTypeName() + "_2"))
                .addCustomItem((w, b, p) -> new CarvedWoodItem(b, p, 2))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(WoodGood.res("block/carved_wood/carved_dark_oak_2"))
                .addTexture(WoodGood.res("block/carved_wood/carved_dark_oak_2_top"))
                .setTab(() -> tab)
                .build();
        this.addEntry(carved2);

        carved3 = SimpleEntrySet.builder(WoodType.class, "3", "carved",
                        () -> Blocks.CARVED_DARK_OAK_3, () -> WoodTypeRegistry.getValue(new ResourceLocation("dark_oak")),
                        w -> new BlockCarvedLog(Path + w.getNamespace() + "/carved_" + w.getTypeName() + "_3"))
                .addCustomItem((w, b, p) -> new CarvedWoodItem(b, p, 3))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(WoodGood.res("block/carved_wood/carved_dark_oak_3"))
                .addTexture(WoodGood.res("block/carved_wood/carved_dark_oak_3_top"))
                .setTab(() -> tab)
                .build();
        this.addEntry(carved3);

        carved4 = SimpleEntrySet.builder(WoodType.class, "4", "carved",
                        () -> Blocks.CARVED_DARK_OAK_4, () -> WoodTypeRegistry.getValue(new ResourceLocation("dark_oak")),
                        w -> new BlockCarvedLog(Path + w.getNamespace() + "/carved_" + w.getTypeName() + "_4"))
                .addCustomItem((w, b, p) -> new CarvedWoodItem(b, p, 4))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(WoodGood.res("block/carved_wood/carved_dark_oak_4a"))
                .addTexture(WoodGood.res("block/carved_wood/carved_dark_oak_4b"))
                .addTexture(WoodGood.res("block/carved_wood/carved_dark_oak_4_top"))
                .setTab(() -> tab)
                .build();
        this.addEntry(carved4);

        carved5 = SimpleEntrySet.builder(WoodType.class, "5", "carved",
                        () -> Blocks.CARVED_DARK_OAK_5, () -> WoodTypeRegistry.getValue(new ResourceLocation("dark_oak")),
                        w -> new BlockCarvedLog(Path + w.getNamespace() + "/carved_" + w.getTypeName() + "_5"))
                .addCustomItem((w, b, p) -> new CarvedWoodItem(b, p, 5))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(WoodGood.res("block/carved_wood/carved_dark_oak_5"))
                .addTexture(WoodGood.res("block/carved_wood/carved_dark_oak_5_top"))
                .setTab(() -> tab)
                .build();
        this.addEntry(carved5);

        carved6 = SimpleEntrySet.builder(WoodType.class, "6", "carved",
                        () -> Blocks.CARVED_DARK_OAK_6, () -> WoodTypeRegistry.getValue(new ResourceLocation("dark_oak")),
                        w -> new BlockCarvedLog(Path + w.getNamespace() + "/carved_" + w.getTypeName() + "_6"))
                .addCustomItem((w, b, p) -> new CarvedWoodItem(b, p, 6))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(WoodGood.res("block/carved_wood/carved_dark_oak_6a"))
                .addTexture(WoodGood.res("block/carved_wood/carved_dark_oak_6b"))
                .addTexture(WoodGood.res("block/carved_wood/carved_dark_oak_6c"))
                .addTexture(WoodGood.res("block/carved_wood/carved_dark_oak_6d"))
                .addTexture(WoodGood.res("block/carved_wood/carved_dark_oak_6_top"))
                .setTab(() -> tab)
                .build();
        this.addEntry(carved6);

        carved7 = SimpleEntrySet.builder(WoodType.class, "7", "carved",
                        () -> Blocks.CARVED_DARK_OAK_7, () -> WoodTypeRegistry.getValue(new ResourceLocation("dark_oak")),
                        w -> new BlockCarvedLog(Path + w.getNamespace() + "/carved_" + w.getTypeName() + "_7"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addCustomItem((w, b, p) -> new CarvedWoodItem(b, p, 7))
                .addTexture(WoodGood.res("block/carved_wood/carved_dark_oak_7a"))
                .addTexture(WoodGood.res("block/carved_wood/carved_dark_oak_7b"))
                .addTexture(WoodGood.res("block/carved_wood/carved_dark_oak_7c"))
                .addTexture(WoodGood.res("block/carved_wood/carved_dark_oak_7d"))
                .addTexture(WoodGood.res("block/carved_wood/carved_dark_oak_7_top"))
                .setTab(() -> tab)
                .build();
        this.addEntry(carved7);

        carved8 = SimpleEntrySet.builder(WoodType.class, "8", "carved",
                        () -> Blocks.CARVED_DARK_OAK_8, () -> WoodTypeRegistry.getValue(new ResourceLocation("dark_oak")),
                        w -> new BlockCarvedLog(Path + w.getNamespace() + "/carved_" + w.getTypeName() + "_8"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addCustomItem((w, b, p) -> new CarvedWoodItem(b, p, 8))
                .addTexture(WoodGood.res("block/carved_wood/carved_dark_oak_8"))
                .addTexture(WoodGood.res("block/carved_wood/carved_dark_oak_8_top"))
                .setTab(() -> tab)
                .build();
        this.addEntry(carved8);
    }

    @Override
    // Recipes
    public void addDynamicServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicServerResources(handler, manager);

        carved1.items.forEach((wood, item) -> {
            // LOG -> STRIPPED_LOG
            recipeCreator(handler, wood.log.asItem(), Objects.requireNonNull(wood.getBlockOfThis("stripped_log")).asItem(), "", wood);

            // Carved 1 to Carved 8
            recipeCreator(handler, wood.log.asItem(), item,"1", wood);
            createRecipeIfNotNull("stripped_log", item, "1", wood, handler);

            recipeCreator(handler, wood.log.asItem(), carved2.items.get(wood), "2", wood);
            createRecipeIfNotNull("stripped_log", carved2.items.get(wood), "2", wood, handler);

            recipeCreator(handler, wood.log.asItem(), carved3.items.get(wood), "3", wood);
            createRecipeIfNotNull("stripped_log", carved3.items.get(wood), "3", wood, handler);

            recipeCreator(handler, wood.log.asItem(), carved4.items.get(wood), "4", wood);
            createRecipeIfNotNull("stripped_log", carved4.items.get(wood), "4", wood, handler);

            recipeCreator(handler, wood.log.asItem(), carved5.items.get(wood), "5", wood);
            createRecipeIfNotNull("stripped_log", carved5.items.get(wood), "5", wood, handler);

            recipeCreator(handler, wood.log.asItem(), carved6.items.get(wood), "6", wood);
            createRecipeIfNotNull("stripped_log", carved6.items.get(wood), "6", wood, handler);

            recipeCreator(handler, wood.log.asItem(), carved7.items.get(wood), "7", wood);
            createRecipeIfNotNull("stripped_log", carved7.items.get(wood), "7", wood, handler);

            recipeCreator(handler, wood.log.asItem(), carved8.items.get(wood), "8", wood);
            createRecipeIfNotNull("stripped_log", carved8.items.get(wood), "8", wood, handler);
        });
    }


    public void recipeCreator(ServerDynamicResourcesHandler handler, Item input, Item output, String X, WoodType wood) {
        // pathBuilder: carving/x/namespace/
        String pathBuilder = this.shortenedId() + "/" + wood.getNamespace() + "/";
        // recipeName: carved_<type>_X_from_<type>_log_carving | carved_<type>_X_from_stripped_<type>_log_carving
        String recipeName = (X != null) ? "carved_" + wood.getTypeName() + "_" + X + "_from_" : "carved_" + wood.getTypeName() + "_from_" ;

        if (input == wood.log.asItem()) {
            recipeName += wood.getTypeName() + "_log_carving";
        }
        else {
            recipeName += "stripped_"  + wood.getTypeName() + "_log_carving";
        }

        JsonObject ingredient = new JsonObject();
        ingredient.addProperty("item", String.valueOf(input.getRegistryName()));

        JsonObject json = new JsonObject();
        json.addProperty("type","xercamod:carving");
        json.add("ingredient", ingredient);
        json.addProperty("result", String.valueOf(output.getRegistryName()));
        json.addProperty("count", 1);

        handler.dynamicPack.addJson(WoodGood.res("carving/" + pathBuilder + recipeName), json, ResType.RECIPES);
    }


    // Execute recipeCreator if nonNull stripped_log
    public void createRecipeIfNotNull(String input, Item output, String X, WoodType wood, ServerDynamicResourcesHandler handler) {
        if (Objects.nonNull(wood.getItemOfThis(input))) {
            recipeCreator(handler, wood.getItemOfThis(input), output, X, wood);
        }
    }

    @Override
    // Models
    public void addStaticClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        super.addStaticClientResources(handler, manager);

        for (Map.Entry<ResourceLocation, WoodType> entry : WoodTypeRegistry.WOOD_TYPES.entrySet()) {
            if (entry.getValue().isVanilla()) continue;
            // Creating carved_<type>.json
            creatingModel(handler, manager, entry);
        }
    }

    public void creatingModel(ClientDynamicResourcesHandler handler, ResourceManager manager, Map.Entry<ResourceLocation, WoodType> entry) {
        WoodType woodType = entry.getValue();
        String filenameBuilder = "carved_" + woodType.getTypeName();

        ResourceLocation resLocBlock = modRes("models/block/carved_wood/carved_dark_oak.json");
        try (InputStream fileStream = manager.getResource(resLocBlock).getInputStream()) {
            JsonObject model = RPUtils.deserializeJson(fileStream);
            JsonObject underTextures = model.getAsJsonObject("textures");

            String log_topPath; // tfc's texture ResourceLocation
            if (woodType.getNamespace().equals("tfc") || woodType.getNamespace().equals("afc")) {
                log_topPath = ":block/wood/log_top/" + woodType.getTypeName();
            }
            else { // normal ResourceLocation
                log_topPath = ":block/" + woodType.getTypeName() + "_log_top";
            }

            // EDITING
            underTextures.addProperty("up",  woodType.getNamespace() + log_topPath);
            underTextures.addProperty("down",  woodType.getNamespace() + log_topPath);
            underTextures.addProperty("particle",  woodType.getNamespace() + log_topPath);

            handler.dynamicPack.addJson(WoodGood.res(Path + woodType.getNamespace() + "/carved_wood/" + filenameBuilder), model, ResType.BLOCK_MODELS);
        }
        catch (IOException e) {
            WoodGood.LOGGER.error("{Xerca Module} MODEL file: " + e);
        }
    }

}
