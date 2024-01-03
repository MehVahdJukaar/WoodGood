package net.mehvahdjukaar.every_compat.modules.forge.xerca;

import com.google.gson.JsonObject;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
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

import java.util.Objects;

//SUPPORT: v1.0.0
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
        CreativeModeTab tab = DecoCreativeTab.TAB_BUILDING_BLOCKS;

        carved1 = SimpleEntrySet.builder(WoodType.class, "1", "carved",
                        Blocks.CARVED_DARK_OAK_1, () -> WoodTypeRegistry.getValue(new ResourceLocation("dark_oak")),
                        w -> new BlockCarvedLog(Utils.copyPropertySafe(w.log)))
                .addCustomItem((w, b, p) -> new CarvedWoodItem(b, p, 1))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addModelTransform(m -> m.replaceString("\"xercamod:block/carved_wood/carved_dark_oak\"", "\"xercamod:block/carved_wood/carved_spruce\""))
                .addTexture(EveryCompat.res("block/carved_wood/carved_dark_oak_1"))
                .addTexture(EveryCompat.res("block/carved_wood/carved_dark_oak_1_top"))
                .setTab(() -> tab)
                .build();

        this.addEntry(carved1);

        carved2 = SimpleEntrySet.builder(WoodType.class, "2", "carved",
                        Blocks.CARVED_DARK_OAK_2, () -> WoodTypeRegistry.getValue(new ResourceLocation("dark_oak")),
                        w -> new BlockCarvedLog(Utils.copyPropertySafe(w.log)))
                .addCustomItem((w, b, p) -> new CarvedWoodItem(b, p, 2))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addModelTransform(m -> m.replaceString("\"xercamod:block/carved_wood/carved_dark_oak\"", "\"xercamod:block/carved_wood/carved_spruce\""))
                .addTexture(EveryCompat.res("block/carved_wood/carved_dark_oak_2"))
                .addTexture(EveryCompat.res("block/carved_wood/carved_dark_oak_2_top"))
                .setTab(() -> tab)
                .build();

        this.addEntry(carved2);

        carved3 = SimpleEntrySet.builder(WoodType.class, "3", "carved",
                        Blocks.CARVED_DARK_OAK_3, () -> WoodTypeRegistry.getValue(new ResourceLocation("dark_oak")),
                        w -> new BlockCarvedLog(Utils.copyPropertySafe(w.log)))
                .addCustomItem((w, b, p) -> new CarvedWoodItem(b, p, 3))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addModelTransform(m -> m.replaceString("\"xercamod:block/carved_wood/carved_dark_oak\"", "\"xercamod:block/carved_wood/carved_spruce\""))
                .addTexture(EveryCompat.res("block/carved_wood/carved_dark_oak_3"))
                .addTexture(EveryCompat.res("block/carved_wood/carved_dark_oak_3_top"))
                .setTab(() -> tab)
                .build();

        this.addEntry(carved3);

        carved4 = SimpleEntrySet.builder(WoodType.class, "4", "carved",
                        Blocks.CARVED_DARK_OAK_4, () -> WoodTypeRegistry.getValue(new ResourceLocation("dark_oak")),
                        w -> new BlockCarvedLog(Utils.copyPropertySafe(w.log)))
                .addCustomItem((w, b, p) -> new CarvedWoodItem(b, p, 4))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addModelTransform(m -> m.replaceString("\"xercamod:block/carved_wood/carved_dark_oak\"", "\"xercamod:block/carved_wood/carved_spruce\""))
                .addTexture(EveryCompat.res("block/carved_wood/carved_dark_oak_4a"))
                .addTexture(EveryCompat.res("block/carved_wood/carved_dark_oak_4b"))
                .addTexture(EveryCompat.res("block/carved_wood/carved_dark_oak_4_top"))
                .setTab(() -> tab)
                .build();

        this.addEntry(carved4);

        carved5 = SimpleEntrySet.builder(WoodType.class, "5", "carved",
                        Blocks.CARVED_DARK_OAK_5, () -> WoodTypeRegistry.getValue(new ResourceLocation("dark_oak")),
                        w -> new BlockCarvedLog(Utils.copyPropertySafe(w.log)))
                .addCustomItem((w, b, p) -> new CarvedWoodItem(b, p, 5))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addModelTransform(m -> m.replaceString("\"xercamod:block/carved_wood/carved_dark_oak\"", "\"xercamod:block/carved_wood/carved_spruce\""))
                .addTexture(EveryCompat.res("block/carved_wood/carved_dark_oak_5"))
                .addTexture(EveryCompat.res("block/carved_wood/carved_dark_oak_5_top"))
                .setTab(() -> tab)
                .build();

        this.addEntry(carved5);

        carved6 = SimpleEntrySet.builder(WoodType.class, "6", "carved",
                        Blocks.CARVED_DARK_OAK_6, () -> WoodTypeRegistry.getValue(new ResourceLocation("dark_oak")),
                        w -> new BlockCarvedLog(Utils.copyPropertySafe(w.log)))
                .addCustomItem((w, b, p) -> new CarvedWoodItem(b, p, 6))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addModelTransform(m -> m.replaceString("\"xercamod:block/carved_wood/carved_dark_oak\"", "\"xercamod:block/carved_wood/carved_spruce\""))
                .addTexture(EveryCompat.res("block/carved_wood/carved_dark_oak_6a"))
                .addTexture(EveryCompat.res("block/carved_wood/carved_dark_oak_6b"))
                .addTexture(EveryCompat.res("block/carved_wood/carved_dark_oak_6c"))
                .addTexture(EveryCompat.res("block/carved_wood/carved_dark_oak_6d"))
                .addTexture(EveryCompat.res("block/carved_wood/carved_dark_oak_6_top"))
                .setTab(() -> tab)
                .build();

        this.addEntry(carved6);

        carved7 = SimpleEntrySet.builder(WoodType.class, "7", "carved",
                        Blocks.CARVED_DARK_OAK_7, () -> WoodTypeRegistry.getValue(new ResourceLocation("dark_oak")),
                        w -> new BlockCarvedLog(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addCustomItem((w, b, p) -> new CarvedWoodItem(b, p, 7))
                .addModelTransform(m -> m.replaceString("\"xercamod:block/carved_wood/carved_dark_oak\"", "\"xercamod:block/carved_wood/carved_spruce\""))
                .addTexture(EveryCompat.res("block/carved_wood/carved_dark_oak_7a"))
                .addTexture(EveryCompat.res("block/carved_wood/carved_dark_oak_7b"))
                .addTexture(EveryCompat.res("block/carved_wood/carved_dark_oak_7c"))
                .addTexture(EveryCompat.res("block/carved_wood/carved_dark_oak_7d"))
                .addTexture(EveryCompat.res("block/carved_wood/carved_dark_oak_7_top"))
                .setTab(() -> tab)
                .build();

        this.addEntry(carved7);

        carved8 = SimpleEntrySet.builder(WoodType.class, "8", "carved",
                        Blocks.CARVED_DARK_OAK_8, () -> WoodTypeRegistry.getValue(new ResourceLocation("dark_oak")),
                        w -> new BlockCarvedLog(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addCustomItem((w, b, p) -> new CarvedWoodItem(b, p, 8))
                .addModelTransform(m -> m.replaceString("\"xercamod:block/carved_wood/carved_dark_oak\"", "\"xercamod:block/carved_wood/carved_spruce\""))
                .addTexture(EveryCompat.res("block/carved_wood/carved_dark_oak_8"))
                .addTexture(EveryCompat.res("block/carved_wood/carved_dark_oak_8_top"))
                .setTab(() -> tab)
                .build();

        this.addEntry(carved8);
    }

    private void neutralPalette(Palette p) {
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
    public void addDynamicServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicServerResources(handler, manager);

        carved1.items.forEach((wood, item) -> {
            recipeCreator(handler, wood.log.asItem(), item,1, wood);
            recipeCreator(handler, Objects.requireNonNull(wood.getBlockOfThis("stripped_log")).asItem(), item, 1, wood);

            recipeCreator(handler, wood.log.asItem(), carved2.items.get(wood), 2, wood);
            recipeCreator(handler, Objects.requireNonNull(wood.getBlockOfThis("stripped_log")).asItem(), carved2.items.get(wood), 2, wood);

            recipeCreator(handler, wood.log.asItem(), carved3.items.get(wood), 3, wood);
            recipeCreator(handler, Objects.requireNonNull(wood.getBlockOfThis("stripped_log")).asItem(), carved3.items.get(wood), 3, wood);

            recipeCreator(handler, wood.log.asItem(), carved4.items.get(wood), 4, wood);
            recipeCreator(handler, Objects.requireNonNull(wood.getBlockOfThis("stripped_log")).asItem(), carved4.items.get(wood), 4, wood);

            recipeCreator(handler, wood.log.asItem(), carved5.items.get(wood), 5, wood);
            recipeCreator(handler, Objects.requireNonNull(wood.getBlockOfThis("stripped_log")).asItem(), carved5.items.get(wood), 5, wood);

            recipeCreator(handler, wood.log.asItem(), carved6.items.get(wood), 6, wood);
            recipeCreator(handler, Objects.requireNonNull(wood.getBlockOfThis("stripped_log")).asItem(), carved6.items.get(wood), 6, wood);

            recipeCreator(handler, wood.log.asItem(), carved7.items.get(wood), 7, wood);
            recipeCreator(handler, Objects.requireNonNull(wood.getBlockOfThis("stripped_log")).asItem(), carved7.items.get(wood), 7, wood);

            recipeCreator(handler, wood.log.asItem(), carved8.items.get(wood), 8, wood);
            recipeCreator(handler, Objects.requireNonNull(wood.getBlockOfThis("stripped_log")).asItem(), carved8.items.get(wood), 8, wood);
        });

    }


    public void recipeCreator(ServerDynamicResourcesHandler handler, Item input, Item output, int X, WoodType wood) {
        String pathBuilder = XercaModule.this.shortenedId() + "/" + wood.getNamespace() + "/";
        String recipeName = "carved_" + wood.getTypeName() + "_" + X + "_from_";
        if (input == wood.log.asItem()) {
            recipeName += wood.getTypeName() + "_log_carving";
        }
        else {
            recipeName += "stripped_"  + wood.getTypeName() + "_log_carving";
        }
        // pathBuilder: carving/x/namespace/
        // recipeName: carved_<type>_X_from_<type>_log_carving | carved_<type>_X_from_stripped_<type>_log_carving

        JsonObject ingredient = new JsonObject();
        ingredient.addProperty("item", Utils.getID(input).toString());

        JsonObject json = new JsonObject();
        json.addProperty("type","xercamod:carving");
        json.add("ingredient", ingredient);
        json.addProperty("result", Utils.getID(output).toString());
        json.addProperty("count", 1);

        handler.dynamicPack.addJson(EveryCompat.res("carving/" + pathBuilder + recipeName), json, ResType.RECIPES);
    }

}
