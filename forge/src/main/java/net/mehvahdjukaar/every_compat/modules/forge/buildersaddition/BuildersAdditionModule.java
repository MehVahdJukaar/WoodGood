package net.mehvahdjukaar.every_compat.modules.forge.buildersaddition;

import com.google.gson.JsonObject;
import com.mrh0.buildersaddition.Index;
import com.mrh0.buildersaddition.blocks.*;
import com.mrh0.buildersaddition.event.CreativeModeTabRegistry;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesType;
import net.mehvahdjukaar.moonlight.api.set.leaves.VanillaLeavesTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;

import java.io.IOException;
import java.io.InputStream;
import java.util.function.Consumer;

//SUPPORT: v20230928a+
public class BuildersAdditionModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> verticalSlab,
            tables,
            stools,
            chairs,
            countersAndesite,
            countersDiorite,
            countersGranite,
            countersBlackstone,
            countersDeepslate,
            bookshelves,
            shelves,
            cabinets,
            cupboards,
            smallCupboards,
            benches,
            supportsBracket,
            bedsideTables,
            arcades;
    public final SimpleEntrySet<LeavesType, Block> hedges;

    public BuildersAdditionModule(String modId) {
        super(modId, "bca");
        ResourceLocation tab = CreativeModeTabRegistry.MAIN_TAB.getId();


        verticalSlab = SimpleEntrySet.builder(WoodType.class, "vertical_slab",
                        Index.OAK_VERTICAL_SLAB, () -> VanillaWoodTypes.OAK,
                        w -> new VerticalSlab(shortenedId() + "/" + w.getAppendableId(), w.planks)
                )
                .addCondition(w -> !PlatHelper.isModLoaded("v_slab_compat"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addRecipe(modRes("vertical_slab/oak_vertical_slab"))
//              Recipe added by a manual code below
                .setTabKey(tab)
                .build();
        this.addEntry(verticalSlab);

        tables = SimpleEntrySet.builder(WoodType.class, "", "table",
                        Index.TABLE_OAK, () -> VanillaWoodTypes.OAK,
                        w -> new Table(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addRecipe(modRes("table/table_oak"))
                .setTabKey(tab)
                .build();
        this.addEntry(tables);

        stools = SimpleEntrySet.builder(WoodType.class, "", "stool",
                        Index.STOOL_OAK, () -> VanillaWoodTypes.OAK,
                        w -> new Stool(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addRecipe(modRes("stool/stool_oak"))
                .setTabKey(tab)
                .build();
        this.addEntry(stools);

        chairs = SimpleEntrySet.builder(WoodType.class, "", "chair",
                        Index.CHAIR_OAK, () -> VanillaWoodTypes.OAK,
                        w -> new Chair(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addRecipe(modRes("chair/chair_oak"))
                .setTabKey(tab)
                .build();
        this.addEntry(chairs);

        countersAndesite = SimpleEntrySet.builder(WoodType.class, "andesite", "counter",
                        getModBlock("counter_oak_andesite"), () -> VanillaWoodTypes.OAK,
                        w -> new Counter(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addRecipe(modRes("counter/counter_oak_andesite"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(countersAndesite);

        countersDiorite = SimpleEntrySet.builder(WoodType.class, "diorite", "counter",
                        getModBlock("counter_oak_diorite"), () -> VanillaWoodTypes.OAK,
                        w -> new Counter(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addRecipe(modRes("counter/counter_oak_diorite"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(countersDiorite);

        countersGranite = SimpleEntrySet.builder(WoodType.class, "granite", "counter",
                        getModBlock("counter_oak_granite"), () -> VanillaWoodTypes.OAK,
                        w -> new Counter(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addRecipe(modRes("counter/counter_oak_granite"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(countersGranite);

        countersBlackstone = SimpleEntrySet.builder(WoodType.class, "blackstone", "counter",
                        getModBlock("counter_oak_blackstone"), () -> VanillaWoodTypes.OAK,
                        w -> new Counter(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addRecipe(modRes("counter/counter_oak_blackstone"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(countersBlackstone);

        countersDeepslate = SimpleEntrySet.builder(WoodType.class, "deepslate", "counter",
                        getModBlock("counter_oak_deepslate"), () -> VanillaWoodTypes.OAK,
                        w -> new Counter(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addRecipe(modRes("counter/counter_oak_deepslate"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(countersDeepslate);

        bookshelves = SimpleEntrySet.builder(WoodType.class, "", "bookshelf",
                        Index.BOOKSHELF_OAK, () -> VanillaWoodTypes.OAK,
                        w -> new CompatBookshelf(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addRecipe(modRes("bookshelf/bookshelf_oak"))
                .setTabKey(tab)
                .build();
        this.addEntry(bookshelves);

        shelves = SimpleEntrySet.builder(WoodType.class, "", "shelf",
                        Index.SHELF_OAK, () -> VanillaWoodTypes.OAK,
                        w -> new Shelf(shortenedId() + "/" + w.getAppendableId()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addRecipe(modRes("shelf/shelf_oak"))
                .addTile(Index.SHELF_TILE_ENTITY_TYPE)
                .setTabKey(tab)
                .build();
        this.addEntry(shelves);

        cabinets = SimpleEntrySet.builder(WoodType.class, "", "cabinet",
                        Index.CABINET_OAK, () -> VanillaWoodTypes.OAK,
                        w -> new CompatCabinet(shortenedId() + "/" + w.getAppendableId()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addRecipe(modRes("cabinet/cabinet_oak"))
                .setTabKey(tab)
                .build();
        this.addEntry(cabinets);

        cupboards = SimpleEntrySet.builder(WoodType.class, "", "cupboard",
                        Index.CUPBOARD_OAK, () -> VanillaWoodTypes.OAK,
                        w -> new Cupboard(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addRecipe(modRes("cupboard/cupboard_oak_left"))
                .addRecipe(modRes("cupboard/cupboard_oak_right"))
                .setTabKey(tab)
                .build();
        this.addEntry(cupboards);

        smallCupboards = SimpleEntrySet.builder(WoodType.class, "", "small_cupboard",
                        Index.SMALL_CUPBOARD_OAK, () -> VanillaWoodTypes.OAK,
                        w -> new SmallCupboard(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addRecipe(modRes("small_cupboard/small_cupboard_oak_left"))
                .addRecipe(modRes("small_cupboard/small_cupboard_oak_right"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(smallCupboards);

        benches = SimpleEntrySet.builder(WoodType.class, "", "bench",
                        Index.BENCH_OAK, () -> VanillaWoodTypes.OAK,
                        w -> new Bench(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addRecipe(modRes("bench/bench_oak"))
                .setTabKey(tab)
                .build();
        this.addEntry(benches);

        supportsBracket = SimpleEntrySet.builder(WoodType.class, "", "support_bracket",
                        Index.SUPPORT_BRACKET_OAK, () -> VanillaWoodTypes.OAK,
                        w -> new SupportBracket(shortenedId() + "/" + w.getAppendableId(), w.planks)).requiresChildren("stripped_log")
                .addRecipe(modRes("support_bracket/support_bracket_oak_left"))
                .addRecipe(modRes("support_bracket/support_bracket_oak_right"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(supportsBracket);

        bedsideTables = SimpleEntrySet.builder(WoodType.class, "", "bedside_table",
                        getModBlock("bedside_table_oak"), () -> VanillaWoodTypes.OAK,
                        w -> new BedsideTable(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addRecipe(modRes("bedside_table/bedside_table_oak"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(bedsideTables);

        arcades = SimpleEntrySet.builder(WoodType.class, "", "arcade",
                        Index.ARCADE_OAK, () -> VanillaWoodTypes.OAK,
                        w -> new CompatArcade(shortenedId() + "/" + w.getAppendableId()))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addRecipe(modRes("arcade/arcade_oak"))
                .setTabKey(tab)
                .build();
        this.addEntry(arcades);

        hedges = SimpleEntrySet.builder(LeavesType.class, "", "hedge",
                        getModBlock("hedge_oak"), () -> VanillaLeavesTypes.OAK,
                        leavesType -> new CompatHedge(leavesType.createPathWith(shortenedId(), ""), leavesType.leaves)
                )
                //RECIPES: leaves
                //TEXTURES: leaves
                .addModelTransform(m -> m.replaceLeavesTextures(VanillaLeavesTypes.OAK))
                .addTag(BlockTags.MINEABLE_WITH_HOE, Registries.BLOCK)
                .addTag(BlockTags.LEAVES, Registries.BLOCK)
                .addTag(ItemTags.LEAVES, Registries.ITEM)
                .copyParentTint()
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
//                .excludeBlockTypes("", "")
                .build();
        this.addEntry(hedges);
    }

    private static class CompatBookshelf extends Bookshelf {
        public CompatBookshelf(String name, Block source) {
            super("bookshelf_" + name);
        }
    }

    private static class CompatCabinet extends Cabinet {
        public CompatCabinet(String name) {
            super("cabinet_" + name);
        }
    }

    private static class CompatArcade extends Arcade {
        public CompatArcade(String name) {
            super("arcade_" + name);
        }
    }

    @Override
    // RECIPES
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public void addDynamicServerResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicServerResources(executor);

        ResourceLocation recipeLoc = modRes("recipes/vertical_slab/reverse/oak_vertical_slab.json");

        // 2 vertical-slab to plank recipes
        executor.accept((manager, sink) -> {
            try (InputStream recipeStream = manager.getResource(recipeLoc).get().open()) {
                JsonObject recipe = RPUtils.deserializeJson(recipeStream);

                verticalSlab.items.forEach((woodType, item) -> {
                    // Editing JSON
                    JsonObject underIngredient = recipe.getAsJsonObject("key").getAsJsonObject("P");
                    underIngredient.addProperty("item", Utils.getID(item).toString());

                    JsonObject underResult = recipe.getAsJsonObject("result");
                    underResult.addProperty("item", Utils.getID(woodType.planks).toString());
                    // Adding finished recipe
                   // sink.addJson(EveryCompat.res(woodType.createPathWith(shortenedId(), "_vertical_slab_reversed")), recipe, ResType.RECIPES);
                });

            } catch (IOException e) {
                EveryCompat.LOGGER.error("BuilderAdditional - failed to open the reverse/vertical_slab recipe: {0}", e);
            }

        });
    }
}
