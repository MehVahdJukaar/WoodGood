package net.mehvahdjukaar.every_compat.modules.forge.buildersaddition;

import com.google.gson.JsonObject;
import com.mrh0.buildersaddition.Index;
import com.mrh0.buildersaddition.blocks.*;
import com.mrh0.buildersaddition.event.CreativeModeTabRegistry;
import io.netty.util.ResourceLeakDetector;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesType;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesTypeRegistry;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

//SUPPORT: v20230928a+
public class BuildersAdditionModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> arcades;
    public final SimpleEntrySet<WoodType, Block> bedsideTables;
    public final SimpleEntrySet<WoodType, Block> benches;
    public final SimpleEntrySet<WoodType, Block> bookshelves;
    public final SimpleEntrySet<WoodType, Block> cabinets;
    public final SimpleEntrySet<WoodType, Block> chairs;
    public final SimpleEntrySet<WoodType, Block> countersAndesite;
    public final SimpleEntrySet<WoodType, Block> countersBlackstone;
    public final SimpleEntrySet<WoodType, Block> countersDeepslate;
    public final SimpleEntrySet<WoodType, Block> countersDiorite;
    public final SimpleEntrySet<WoodType, Block> countersGranite;
    public final SimpleEntrySet<WoodType, Block> cupboards;
    public final SimpleEntrySet<LeavesType, Block> hedges;
    public final SimpleEntrySet<WoodType, Block> shelves;
    public final SimpleEntrySet<WoodType, Block> smallCupboards;
    public final SimpleEntrySet<WoodType, Block> stools;
    public final SimpleEntrySet<WoodType, Block> supportsBracket;
    public final SimpleEntrySet<WoodType, Block> tables;
    public final SimpleEntrySet<WoodType, Block> verticalSlab;

    public BuildersAdditionModule(String modId) {
        super(modId, "bca");
        var tab = CreativeModeTabRegistry.MAIN_TAB;


        verticalSlab = SimpleEntrySet.builder(WoodType.class, "vertical_slab",
                        Index.OAK_VERTICAL_SLAB, () -> WoodTypeRegistry.OAK_TYPE,
                        (w) -> {
                            if (PlatHelper.isModLoaded("v_slab_compat")) return null;
                            return new VerticalSlab(shortenedId() + "/" + w.getAppendableId(), w.planks);
                        })
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addRecipe(modRes("vertical_slab/oak_vertical_slab"))
//              Recipe added by a manual code below
                .setTab(tab)
                .build();

        this.addEntry(verticalSlab);

        tables = SimpleEntrySet.builder(WoodType.class, "", "table",
                        Index.TABLE_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Table(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addRecipe(modRes("table/table_oak"))
                .setTab(tab)
                .build();

        this.addEntry(tables);

        stools = SimpleEntrySet.builder(WoodType.class, "", "stool",
                        Index.STOOL_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Stool(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addRecipe(modRes("stool/stool_oak"))
                .setTab(tab)
                .build();

        this.addEntry(stools);

        chairs = SimpleEntrySet.builder(WoodType.class, "", "chair",
                        Index.CHAIR_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Chair(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addRecipe(modRes("chair/chair_oak"))
                .setTab(tab)
                .build();

        this.addEntry(chairs);

        hedges = SimpleEntrySet.builder(LeavesType.class, "", "hedge",
                        Index.HEDGE_OAK, () -> LeavesTypeRegistry.OAK_TYPE,
                        w -> {
                            var l = w.getBlockOfThis("leaves");
                            if (l == null) return null;
                            return new Hedge(shortenedId() + "/" + w.getAppendableId(), l);
                        })
                .addModelTransform(m -> m.replaceWithTextureFromChild("minecraft:block/oak_leaves",
                        "leaves", s -> !s.contains("/snow") && !s.contains("_snow")))
                .addModelTransform(m -> m.replaceLeavesTextures(LeavesTypeRegistry.OAK_TYPE))
                .addTag(BlockTags.MINEABLE_WITH_HOE, Registries.BLOCK)
                .addTag(BlockTags.LEAVES, Registries.BLOCK)
                .addTag(ItemTags.LEAVES, Registries.ITEM)
                .addRecipe(modRes("hedge/hedge_oak"))
                .setRenderType(() -> RenderType::cutout)
                .setTab(tab)
                .build();

        this.addEntry(hedges);

        countersAndesite = SimpleEntrySet.builder(WoodType.class, "andesite", "counter",
                        getModBlock("counter_oak_andesite"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Counter(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addRecipe(modRes("counter/counter_oak_andesite"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTab(tab)
                .build();

        this.addEntry(countersAndesite);

        countersDiorite = SimpleEntrySet.builder(WoodType.class, "diorite", "counter",
                        getModBlock("counter_oak_diorite"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Counter(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addRecipe(modRes("counter/counter_oak_diorite"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTab(tab)
                .build();

        this.addEntry(countersDiorite);

        countersGranite = SimpleEntrySet.builder(WoodType.class, "granite", "counter",
                        getModBlock("counter_oak_granite"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Counter(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addRecipe(modRes("counter/counter_oak_granite"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTab(tab)
                .build();

        this.addEntry(countersGranite);

        countersBlackstone = SimpleEntrySet.builder(WoodType.class, "blackstone", "counter",
                        getModBlock("counter_oak_blackstone"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Counter(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addRecipe(modRes("counter/counter_oak_blackstone"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTab(tab)
                .build();

        this.addEntry(countersBlackstone);

        countersDeepslate = SimpleEntrySet.builder(WoodType.class, "deepslate", "counter",
                        getModBlock("counter_oak_deepslate"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Counter(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addRecipe(modRes("counter/counter_oak_deepslate"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTab(tab)
                .build();

        this.addEntry(countersDeepslate);

        bookshelves = SimpleEntrySet.builder(WoodType.class, "", "bookshelf",
                        Index.BOOKSHELF_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CompatBookshelf(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addRecipe(modRes("bookshelf/bookshelf_oak"))
                .setTab(tab)
                .build();

        this.addEntry(bookshelves);

        shelves = SimpleEntrySet.builder(WoodType.class, "", "shelf",
                        Index.SHELF_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Shelf(shortenedId() + "/" + w.getAppendableId()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addRecipe(modRes("shelf/shelf_oak"))
                .addTile(Index.SHELF_TILE_ENTITY_TYPE)
                .setTab(tab)
                .build();

        this.addEntry(shelves);

        cabinets = SimpleEntrySet.builder(WoodType.class, "", "cabinet",
                        Index.CABINET_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CompatCabinet(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addRecipe(modRes("cabinet/cabinet_oak"))
                .setTab(tab)
                .build();

        this.addEntry(cabinets);

        cupboards = SimpleEntrySet.builder(WoodType.class, "", "cupboard",
                        Index.CUPBOARD_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Cupboard(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addRecipe(modRes("cupboard/cupboard_oak_left"))
                .addRecipe(modRes("cupboard/cupboard_oak_right"))
                .setTab(tab)
                .build();

        this.addEntry(cupboards);

        smallCupboards = SimpleEntrySet.builder(WoodType.class, "", "small_cupboard",
                        Index.SMALL_CUPBOARD_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new SmallCupboard(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addRecipe(modRes("small_cupboard/small_cupboard_oak_left"))
                .addRecipe(modRes("small_cupboard/small_cupboard_oak_right"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTab(tab)
                .build();

        this.addEntry(smallCupboards);

        benches = SimpleEntrySet.builder(WoodType.class, "", "bench",
                        Index.BENCH_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Bench(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addRecipe(modRes("bench/bench_oak"))
                .setTab(tab)
                .build();

        this.addEntry(benches);

        supportsBracket = SimpleEntrySet.builder(WoodType.class, "", "support_bracket",
                        Index.SUPPORT_BRACKET_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new SupportBracket(shortenedId() + "/" + w.getAppendableId(), w.planks)).requiresChildren("stripped_log")
                .addRecipe(modRes("support_bracket/support_bracket_oak_left"))
                .addRecipe(modRes("support_bracket/support_bracket_oak_right"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTab(tab)
                .build();

        this.addEntry(supportsBracket);

        bedsideTables = SimpleEntrySet.builder(WoodType.class, "", "bedside_table",
                        getModBlock("bedside_table_oak"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BedsideTable(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addRecipe(modRes("bedside_table/bedside_table_oak"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTab(tab)
                .build();

        this.addEntry(bedsideTables);

        arcades = SimpleEntrySet.builder(WoodType.class, "", "arcade",
                        Index.ARCADE_OAK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CompatArcade(shortenedId() + "/" + w.getAppendableId(), w.log))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addRecipe(modRes("arcade/arcade_oak"))
                .setTab(tab)
                .build();

        this.addEntry(arcades);
    }

    private static class CompatBookshelf extends Bookshelf {
        public CompatBookshelf(String name, Block source) {
            super("bookshelf_" + name);
        }
    }

//    @Override
//    public void registerBlockEntityRenderers(ClientHelper.BlockEntityRendererEvent event) {
//        event.register((BlockEntityType<CompatShelfTileEntity>) (SHELVES.getTileHolder().tile), ShelfRenderer::new);
//    }

    private static class CompatCabinet extends Cabinet {
        public CompatCabinet(String name, Block source) {
            super("cabinet_" + name);
        }
    }

    private static class CompatArcade extends Arcade {
        public CompatArcade(String name, Block source) {
            super("arcade_" + name);

        }
    }

    @Override
    public void registerBlockColors(ClientHelper.BlockColorEvent event) {
        super.registerBlockColors(event);
        for (Map.Entry<LeavesType, Block> entry : hedges.blocks.entrySet()) {
            LeavesType t = entry.getKey();
            Block b = entry.getValue();
            if (t.getNamespace().equals("twilightforest") && t.getTypeName().equals("beanstalk")) continue;
            event.register((s, l, p, i) -> event.getColor(t.leaves.defaultBlockState(), l, p, i), b);
        }
    }

    @Override
    public void registerItemColors(ClientHelper.ItemColorEvent event) {
        super.registerItemColors(event);
        for (Map.Entry<LeavesType, Block> entry : hedges.blocks.entrySet()) {
            LeavesType t = entry.getKey();
            Block b = entry.getValue();
            if (t.getNamespace().equals("twilightforest") && t.getTypeName().equals("beanstalk")) continue;
            event.register((stack, tintIndex) -> event.getColor(new ItemStack(t.leaves), tintIndex), b.asItem());
        }
    }

    @Override
    // Recipes for vertical_slab (from 2 v-slab to plank)
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public void addDynamicServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicServerResources(handler, manager);

        ResourceLocation recipeLoc = modRes("recipes/vertical_slab/reverse/oak_vertical_slab.json");
        JsonObject recipe;

        try (InputStream recipeStream = manager.getResource(recipeLoc).get().open()) {
            recipe = RPUtils.deserializeJson(recipeStream);

            verticalSlab.items.forEach((woodType, item) -> {
                // Editing JSON
                JsonObject underIngredient = recipe.getAsJsonObject("key").getAsJsonObject("P");
                underIngredient.addProperty("item", Utils.getID(item).toString());

                JsonObject underResult = recipe.getAsJsonObject("result");
                underResult.addProperty("item", Utils.getID(woodType.planks).toString());
                // Adding finished recipe
                handler.dynamicPack.addJson(EveryCompat.res(this.shortenedId() +"/" + woodType.getAppendableId() + "_vertical_slab_reversed"), recipe, ResType.RECIPES);
            });

        } catch (IOException e) {
            handler.getLogger().error("BuilderAdditional - failed to open the reverse/vertical_slab recipe: {0}", e );
        }
    }
}
