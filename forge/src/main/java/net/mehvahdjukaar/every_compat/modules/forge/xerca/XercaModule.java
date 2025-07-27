package net.mehvahdjukaar.every_compat.modules.forge.xerca;

import com.google.gson.JsonObject;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceSink;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import xerca.xercamod.common.block.BlockCarvedLog;
import xerca.xercamod.common.block.Blocks;

import java.util.Objects;
import java.util.function.Consumer;

//SUPPORT: v1.0.0+
public class XercaModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> carved1;
    public final SimpleEntrySet<WoodType, Block> carved2;
    public final SimpleEntrySet<WoodType, Block> carved3;
    public final SimpleEntrySet<WoodType, Block> carved4;
    public final SimpleEntrySet<WoodType, Block> carved5;
    public final SimpleEntrySet<WoodType, Block> carved6;
    public final SimpleEntrySet<WoodType, Block> carved7;
    public final SimpleEntrySet<WoodType, Block> carved8;

    public XercaModule(String modId) {
        super(modId, "x");
        ResourceKey<CreativeModeTab> tab = CreativeModeTabs.BUILDING_BLOCKS;

        carved1 = SimpleEntrySet.builder(WoodType.class, "1", "carved",
                        Blocks.CARVED_WARPED_1, () -> WoodTypeRegistry.getValue("warped"),
                        w -> new BlockCarvedLog(Utils.copyPropertySafe(w.log))
                )
                .addModelTransform(m -> m.replaceString("\"xercamod:block/carved_wood/carved_warped\"", "\"xercamod:block/carved_wood/carved_oak\""))
                .createPaletteFromPlanks(this::darkestPalette)
                .addTexture(modRes("block/carved_wood/carved_warped_1_top"))
                .addTexture(modRes("block/carved_wood/carved_warped_1_side_abcd"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                //RECIPES: manually created via addDynamicServerResource()
                .setTabKey(tab)
                .build();
        this.addEntry(carved1);

        carved2 = SimpleEntrySet.builder(WoodType.class, "2", "carved",
                        Blocks.CARVED_WARPED_2, () -> WoodTypeRegistry.getValue("warped"),
                        w -> new BlockCarvedLog(Utils.copyPropertySafe(w.log))
                )
                .addModelTransform(m -> m.replaceString("\"xercamod:block/carved_wood/carved_warped\"", "\"xercamod:block/carved_wood/carved_oak\""))
                .createPaletteFromPlanks(this::darkestestPalette)
                .addTexture(modRes("block/carved_wood/carved_warped_2_top"))
                .addTexture(modRes("block/carved_wood/carved_warped_2_side_abcd"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                //RECIPES: manually created via addDynamicServerResource()
                .setTabKey(tab)
                .build();
        this.addEntry(carved2);

        carved3 = SimpleEntrySet.builder(WoodType.class, "3", "carved",
                        Blocks.CARVED_WARPED_3, () -> WoodTypeRegistry.getValue("warped"),
                        w -> new BlockCarvedLog(Utils.copyPropertySafe(w.log))
                )
                .addModelTransform(m -> m.replaceString("\"xercamod:block/carved_wood/carved_warped\"", "\"xercamod:block/carved_wood/carved_oak\""))
                .createPaletteFromPlanks(this::darkestestPalette)
                .addTexture(modRes("block/carved_wood/carved_warped_3_top"))
                .addTexture(modRes("block/carved_wood/carved_warped_3_side_abcd"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                //RECIPES: manually created via addDynamicServerResource()
                .setTabKey(tab)
                .build();
        this.addEntry(carved3);

        carved4 = SimpleEntrySet.builder(WoodType.class, "4", "carved",
                        Blocks.CARVED_WARPED_4, () -> WoodTypeRegistry.getValue("warped"),
                        w -> new BlockCarvedLog(Utils.copyPropertySafe(w.log))
                )
                .addModelTransform(m -> m.replaceString("\"xercamod:block/carved_wood/carved_warped\"", "\"xercamod:block/carved_wood/carved_oak\""))
                .createPaletteFromPlanks(this::darkestestPalette)
                .addTexture(modRes("block/carved_wood/carved_warped_4_top"))
                .addTexture(modRes("block/carved_wood/carved_warped_4_side_abcd"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                //RECIPES: manually created via addDynamicServerResource()
                .setTabKey(tab)
                .build();
        this.addEntry(carved4);

        carved5 = SimpleEntrySet.builder(WoodType.class, "5", "carved",
                        Blocks.CARVED_WARPED_5, () -> WoodTypeRegistry.getValue("warped"),
                        w -> new BlockCarvedLog(Utils.copyPropertySafe(w.log))
                )
                .addModelTransform(m -> m.replaceString("\"xercamod:block/carved_wood/carved_warped\"", "\"xercamod:block/carved_wood/carved_oak\""))
                .createPaletteFromPlanks(this::darkestestPalette)
                .addTexture(modRes("block/carved_wood/carved_warped_5_top"))
                .addTexture(modRes("block/carved_wood/carved_warped_5_side_ab"))
                .addTexture(modRes("block/carved_wood/carved_warped_5_side_cd"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                //RECIPES: manually created via addDynamicServerResource()
                .setTabKey(tab)
                .build();
        this.addEntry(carved5);

        carved6 = SimpleEntrySet.builder(WoodType.class, "6", "carved",
                        Blocks.CARVED_WARPED_6, () -> WoodTypeRegistry.getValue("warped"),
                        w -> new BlockCarvedLog(Utils.copyPropertySafe(w.log))
                )
                .addModelTransform(m -> m.replaceString("\"xercamod:block/carved_wood/carved_warped\"", "\"xercamod:block/carved_wood/carved_oak\""))
                .createPaletteFromPlanks(this::darkerPalette)
                .addTexture(modRes("block/carved_wood/carved_warped_6_top"))
                .addTexture(modRes("block/carved_wood/carved_warped_6_bottom"))
                .addTexture(modRes("block/carved_wood/carved_warped_6_side_a"))
                .addTexture(modRes("block/carved_wood/carved_warped_6_side_b"))
                .addTexture(modRes("block/carved_wood/carved_warped_6_side_c"))
                .addTexture(modRes("block/carved_wood/carved_warped_6_side_d"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                //RECIPES: manually created via addDynamicServerResource()
                .setTabKey(tab)
                .build();
        this.addEntry(carved6);

        carved7 = SimpleEntrySet.builder(WoodType.class, "7", "carved",
                        Blocks.CARVED_WARPED_7, () -> WoodTypeRegistry.getValue("warped"),
                        w -> new BlockCarvedLog(Utils.copyPropertySafe(w.log))
                )
                .addModelTransform(m -> m.replaceString("\"xercamod:block/carved_wood/carved_warped\"", "\"xercamod:block/carved_wood/carved_oak\""))
                .createPaletteFromPlanks(this::nuetralPalette)
                .addTexture(modRes("block/carved_wood/carved_warped_7_top"))
                .addTexture(modRes("block/carved_wood/carved_warped_7_side_a"))
                .addTexture(modRes("block/carved_wood/carved_warped_7_side_bcd"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                //RECIPES: manually created via addDynamicServerResource()
                .setTabKey(tab)
                .build();
        this.addEntry(carved7);

        carved8 = SimpleEntrySet.builder(WoodType.class, "8", "carved",
                        Blocks.CARVED_WARPED_8, () -> WoodTypeRegistry.getValue("warped"),
                        w -> new BlockCarvedLog(Utils.copyPropertySafe(w.log))
                )
                .addModelTransform(m -> m.replaceString("\"xercamod:block/carved_wood/carved_warped\"", "\"xercamod:block/carved_wood/carved_oak\""))
                .createPaletteFromPlanks(this::nuetralPalette)
                .addTexture(modRes("block/carved_wood/carved_warped_8_top"))
                .addTexture(modRes("block/carved_wood/carved_warped_8_side_abcd"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                //RECIPES: manually created via addDynamicServerResource()
                .setTabKey(tab)
                .build();
        this.addEntry(carved8);
    }

    private void nuetralPalette(Palette p) {
        p.add(p.increaseInner());
        p.remove(p.getDarkest());
        p.remove(p.getLightest());
    }

    private void darkerPalette(Palette p) {
        p.add(p.increaseInner());
        p.remove(p.getDarkest());
        p.remove(p.getLightest());
        p.remove(p.getLightest());
    }

    private void darkestPalette(Palette p) {
        p.add(p.increaseInner());
        p.remove(p.getDarkest());
        p.remove(p.getLightest());
        p.remove(p.getLightest());
        p.remove(p.getLightest());
    }

    private void darkestestPalette(Palette p) {
        p.add(p.increaseInner());
        p.remove(p.getDarkest());
        p.remove(p.getDarkest());
        p.remove(p.getLightest());
        p.remove(p.getLightest());
        p.remove(p.getLightest());
    }

    @Override
    // RECIPES
    public void addDynamicServerResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicServerResources(executor);

        executor.accept((manager, sink) -> {

            carved1.items.forEach((wood, item) -> {
                Block strippedLog = wood.getBlockOfThis("stripped_log");

                if (Objects.nonNull(strippedLog))
                    recipeCreator(wood.log.asItem(), Objects.requireNonNull(strippedLog).asItem(), 1, wood, sink);

                recipeCreator(wood.log.asItem(), item,1, wood, sink);
                createRecipeIfNotNull("stripped_log", item, 1, wood, sink);

                recipeCreator(wood.log.asItem(), carved2.items.get(wood), 2, wood, sink);
                createRecipeIfNotNull("stripped_log", carved2.items.get(wood), 2, wood, sink);

                recipeCreator(wood.log.asItem(), carved3.items.get(wood), 3, wood, sink);
                createRecipeIfNotNull("stripped_log", carved3.items.get(wood), 3, wood, sink);

                recipeCreator(wood.log.asItem(), carved4.items.get(wood), 4, wood, sink);
                createRecipeIfNotNull("stripped_log", carved4.items.get(wood), 4, wood, sink);

                recipeCreator(wood.log.asItem(), carved5.items.get(wood), 5, wood, sink);
                createRecipeIfNotNull("stripped_log", carved5.items.get(wood), 5, wood, sink);

                recipeCreator(wood.log.asItem(), carved6.items.get(wood), 6, wood, sink);
                createRecipeIfNotNull("stripped_log", carved6.items.get(wood), 6, wood, sink);

                recipeCreator(wood.log.asItem(), carved7.items.get(wood), 7, wood, sink);
                createRecipeIfNotNull("stripped_log", carved7.items.get(wood), 7, wood, sink);

                recipeCreator(wood.log.asItem(), carved8.items.get(wood), 8, wood, sink);
                createRecipeIfNotNull("stripped_log", carved8.items.get(wood), 8, wood, sink);

            });

        });
    }

    public void recipeCreator(Item input, Item output, int num, WoodType wood, ResourceSink sink) {
        // pathBuilder: carving/x/namespace/
        String pathBuilder = this.shortenedId() + "/" + wood.getNamespace() + "/";
        String recipeName = wood.getTypeName() + "_log_from_" + wood.getTypeName() + "_log_carving";

        if (output == Objects.requireNonNull(wood.getBlockOfThis("stripped_log")).asItem()) {
            recipeName += "stripped_" + recipeName;
        }
        else {
            recipeName = recipeName.replaceAll(".*", "");
            recipeName += "carved_" + wood.getTypeName() + "_" + num;

            // IF statement
            recipeName += (input == Objects.requireNonNull(wood.getBlockOfThis("stripped_log")).asItem())
                    ? "_from_stripped_" + wood.getTypeName() + "_log_carving"
                    : "_from_" + wood.getTypeName() + "_log_carving";
        }

        // Creating a new recipe
        JsonObject ingredient = new JsonObject();
        ingredient.addProperty("item", Utils.getID(input).toString());

        JsonObject json = new JsonObject();
        json.addProperty("type","xercamod:carving");
        json.add("ingredient", ingredient);
        json.addProperty("result", Utils.getID(output).toString());
        json.addProperty("count", 1);

        // Adding to the resources
        sink.addJson(EveryCompat.res(pathBuilder + recipeName), json, ResType.RECIPES);
    }

    // Null check for stripped_log
    public void createRecipeIfNotNull(String input, Item output, int num, WoodType wood, ResourceSink sink) {
        if (Objects.nonNull(wood.getItemOfThis(input))) {
            recipeCreator(wood.getItemOfThis(input), output, num, wood, sink);
        }
    }
}
