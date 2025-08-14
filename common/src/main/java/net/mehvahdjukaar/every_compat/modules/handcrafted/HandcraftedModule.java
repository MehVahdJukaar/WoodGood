package net.mehvahdjukaar.every_compat.modules.handcrafted;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import earth.terrarium.handcrafted.common.blocks.*;
import earth.terrarium.handcrafted.common.blocks.trims.CornerTrimBlock;
import earth.terrarium.handcrafted.common.blocks.trims.PillarTrimBlock;
import earth.terrarium.handcrafted.common.registry.ModBlocks;
import earth.terrarium.handcrafted.common.tags.ModBlockTags;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.io.FileNotFoundException;
import java.util.function.Consumer;

//SUPPORT: v3.0.6+
public class HandcraftedModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> chair;
    public final SimpleEntrySet<WoodType, Block> table;
    public final SimpleEntrySet<WoodType, Block> bench;
    public final SimpleEntrySet<WoodType, Block> couch;
    public final SimpleEntrySet<WoodType, Block> fancy_bed;
    public final SimpleEntrySet<WoodType, Block> dining_bench;
    public final SimpleEntrySet<WoodType, Block> nightstand;
    public final SimpleEntrySet<WoodType, Block> desk;
    public final SimpleEntrySet<WoodType, Block> side_table;
    public final SimpleEntrySet<WoodType, Block> counter;
    public final SimpleEntrySet<WoodType, Block> cupboard;
    public final SimpleEntrySet<WoodType, Block> drawer;
    public final SimpleEntrySet<WoodType, Block> shelf;
    public final SimpleEntrySet<WoodType, Block> pillarTrim;
    public final SimpleEntrySet<WoodType, Block> cornerTrim;

    public HandcraftedModule(String modId) {
        super(modId, "hc");
        ResourceLocation tab = modRes("main");

        chair = SimpleEntrySet.builder(WoodType.class, "chair",
                        ModBlocks.OAK_CHAIR, () -> VanillaWoodTypes.OAK,
                        w -> new ChairBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                .setRenderType(RenderLayer.CUTOUT)
                .addTexture(modRes("block/chair/oak_chair"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("chairs"), Registries.BLOCK)
                .addTag(modRes("chairs"), Registries.ITEM)
                .setTabKey(tab)
                .addCustomItem((w, b, p) -> new BlockItem(b, p))
                .defaultRecipe()
                .build();
        this.addEntry(chair);

        table = SimpleEntrySet.builder(WoodType.class, "table",
                        ModBlocks.OAK_TABLE, () -> VanillaWoodTypes.OAK,
                        w -> new TableBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                .setRenderType(RenderLayer.CUTOUT)
                .addTexture(modRes("block/table/table/oak_table"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(ModBlockTags.TABLE_CONNECTABLE, Registries.BLOCK)
                .addTag(modRes("tables"), Registries.BLOCK)
                .addTag(modRes("tables"), Registries.ITEM)
                .setTabKey(tab)
                .addCustomItem((w, b, p) -> new BlockItem(b, p))
                .defaultRecipe()
                .build();
        this.addEntry(table);

        bench = SimpleEntrySet.builder(WoodType.class, "bench",
                        ModBlocks.OAK_BENCH, () -> VanillaWoodTypes.OAK,
                        w -> new BenchBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                .setRenderType(RenderLayer.CUTOUT)
                .addTexture(modRes("block/bench/oak_bench"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("wooden_benches"), Registries.BLOCK)
                .addTag(modRes("benches"), Registries.BLOCK)
                .addTag(modRes("wooden_benches"), Registries.ITEM)
                .addTag(modRes("benches"), Registries.ITEM)
                .setTabKey(tab)
                .addCustomItem((w, b, p) -> new BlockItem(b, p))
                .defaultRecipe()
                .build();
        this.addEntry(bench);

        couch = SimpleEntrySet.builder(WoodType.class, "couch",
                        ModBlocks.OAK_COUCH, () -> VanillaWoodTypes.OAK,
                        w -> new CouchBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                .setRenderType(RenderLayer.CUTOUT)
                .addTexture(modRes("block/couch/oak_couch"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("couches"), Registries.BLOCK, Registries.ITEM)
                .setTabKey(tab)
                .addCustomItem((w, b, p) -> new BlockItem(b, p))
                .defaultRecipe()
                .build();
        this.addEntry(couch);

        fancy_bed = SimpleEntrySet.builder(WoodType.class, "fancy_bed",
                        ModBlocks.OAK_FANCY_BED, () -> VanillaWoodTypes.OAK,
                        w -> new FancyBedBlock(Utils.copyPropertySafe(Blocks.WHITE_BED))
                )
                .setRenderType(RenderLayer.CUTOUT)
                .addTexture(modRes("block/fancy_bed/single/oak_fancy_bed"))
                .addTexture(modRes("block/fancy_bed/double/oak_fancy_bed"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("fancy_beds"), Registries.BLOCK, Registries.ITEM)
                .addTag(BlockTags.BEDS, Registries.BLOCK ,  Registries.ITEM)
                .setTabKey(tab)
                .addCustomItem((w, b, p) -> new BlockItem(b, p))
                .defaultRecipe()
                .copyParentDrop()
                .build();
        this.addEntry(fancy_bed);

        dining_bench = SimpleEntrySet.builder(WoodType.class, "dining_bench",
                        ModBlocks.OAK_DINING_BENCH, () -> VanillaWoodTypes.OAK,
                        w -> new DiningBenchBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                .setRenderType(RenderLayer.CUTOUT)
                .addTexture(modRes("block/dining_bench/oak_dining_bench"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("dining_benches"), Registries.BLOCK)
                .addTag(modRes("dining_benches"), Registries.ITEM)
                .setTabKey(tab)
                .addCustomItem((w, b, p) -> new BlockItem(b, p))
                .defaultRecipe()
                .build();
        this.addEntry(dining_bench);

        nightstand = SimpleEntrySet.builder(WoodType.class, "nightstand",
                        ModBlocks.OAK_NIGHTSTAND, () -> VanillaWoodTypes.OAK,
                        w -> new NightstandBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                .addTile(getModTile("container"))
                .setRenderType(RenderLayer.CUTOUT)
                .addTextureM(modRes("block/table/nightstand/oak_nightstand"), EveryCompat.res("block/hc/table/oak_nightstand_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(ModBlockTags.TABLE_CONNECTABLE, Registries.BLOCK)
                .addTag(modRes("nightstands"), Registries.BLOCK,  Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .addCustomItem((w, b, p) -> new BlockItem(b, p))
                .build();
        this.addEntry(nightstand);

        desk = SimpleEntrySet.builder(WoodType.class, "desk",
                        ModBlocks.OAK_DESK, () -> VanillaWoodTypes.OAK,
                        w -> new DeskBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                .addTile(getModTile("container"))
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addTextureM(modRes("block/table/desk/oak_desk"), EveryCompat.res("block/hc/table/oak_desk_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(ModBlockTags.TABLE_CONNECTABLE, Registries.BLOCK)
                .addTag(modRes("desks"), Registries.BLOCK,  Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .addCustomItem((w, b, p) -> new BlockItem(b, p))
                .build();
        this.addEntry(desk);

        side_table = SimpleEntrySet.builder(WoodType.class, "side_table",
                        ModBlocks.OAK_SIDE_TABLE, () -> VanillaWoodTypes.OAK,
                        w -> new SideTableBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                .addTile(getModTile("container"))
                .setRenderType(RenderLayer.CUTOUT)
                .addTextureM(modRes("block/table/side_table/oak_side_table"), EveryCompat.res("block/hc/table/oak_side_table_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("side_tables"), Registries.BLOCK, Registries.ITEM)
                .setTabKey(tab)
                .addCustomItem((w, b, p) -> new BlockItem(b, p))
                .defaultRecipe()
                .build();
        this.addEntry(side_table);

        counter = SimpleEntrySet.builder(WoodType.class, "counter",
                        ModBlocks.ACACIA_COUNTER, () -> VanillaWoodTypes.ACACIA,
                        w -> new CounterBlock(Utils.copyPropertySafe(w.planks))
                )
                .addTile(getModTile("container"))
                .addTextureM(modRes("block/counter/acacia_counter_1"), EveryCompat.res("block/hc/counter/oak_counter_1_m"))
                .addTextureM(modRes("block/counter/acacia_counter_2"), EveryCompat.res("block/hc/counter/oak_counter_2_m"))
                .addTextureM(modRes("block/counter/acacia_counter_3"), EveryCompat.res("block/hc/counter/oak_counter_3_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("counters"), Registries.BLOCK,  Registries.ITEM)
                .setTabKey(tab)
                .addCustomItem((w, b, p) -> new BlockItem(b, p))
                .defaultRecipe()
                //NOTE: the models/block files are modified below to correct the texture for "top"
                .build();
        this.addEntry(counter);

        cupboard = SimpleEntrySet.builder(WoodType.class, "cupboard",
                        ModBlocks.OAK_CUPBOARD, () -> VanillaWoodTypes.OAK,
                        w -> new CupboardBlock(Utils.copyPropertySafe(w.planks))
                )
                .addTile(getModTile("container"))
                .addTextureM(modRes("block/cupboard/oak/cupboard_1"), EveryCompat.res("block/hc/cupboard/cupboard_1_m"))
                .addTextureM(modRes("block/cupboard/oak/cupboard_2"), EveryCompat.res("block/hc/cupboard/cupboard_2_m"))
                .addTexture(modRes("block/cupboard/oak/cupboard_back"))
                .addTexture(modRes("block/cupboard/oak/cupboard_side"))
                .addTexture(modRes("block/cupboard/oak/cupboard_top"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("cupboards"), Registries.BLOCK, Registries.ITEM)
                .setTabKey(tab)
                .addCustomItem((w, b, p) -> new BlockItem(b, p))
                .defaultRecipe()
                .build();
        this.addEntry(cupboard);

        drawer = SimpleEntrySet.builder(WoodType.class, "drawer",
                        ModBlocks.OAK_DRAWER, () -> VanillaWoodTypes.OAK,
                        w -> new DrawerBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                .addTile(getModTile("container"))
                .addTextureM(modRes("block/drawer/oak/front_1/drawer_left"), EveryCompat.res("block/hc/drawer/front_1/drawer_left_m"))
                .addTextureM(modRes("block/drawer/oak/front_1/drawer_middle"), EveryCompat.res("block/hc/drawer/front_1/drawer_middle_m"))
                .addTextureM(modRes("block/drawer/oak/front_1/drawer_right"), EveryCompat.res("block/hc/drawer/front_1/drawer_right_m"))
                .addTextureM(modRes("block/drawer/oak/front_1/drawer_single"), EveryCompat.res("block/hc/drawer/front_1/drawer_single_m"))
                .addTextureM(modRes("block/drawer/oak/front_2/drawer_left"), EveryCompat.res("block/hc/drawer/front_2/drawer_left_m"))
                .addTextureM(modRes("block/drawer/oak/front_2/drawer_middle"), EveryCompat.res("block/hc/drawer/front_2/drawer_middle_m"))
                .addTextureM(modRes("block/drawer/oak/front_2/drawer_right"), EveryCompat.res("block/hc/drawer/front_2/drawer_right_m"))
                .addTextureM(modRes("block/drawer/oak/front_2/drawer_single"), EveryCompat.res("block/hc/drawer/front_2/drawer_single_m"))
                .addTextureM(modRes("block/drawer/oak/front_3/drawer_left"), EveryCompat.res("block/hc/drawer/front_3/drawer_left_m"))
                .addTextureM(modRes("block/drawer/oak/front_3/drawer_middle"), EveryCompat.res("block/hc/drawer/front_3/drawer_middle_m"))
                .addTextureM(modRes("block/drawer/oak/front_3/drawer_right"), EveryCompat.res("block/hc/drawer/front_3/drawer_right_m"))
                .addTextureM(modRes("block/drawer/oak/front_3/drawer_single"), EveryCompat.res("block/hc/drawer/front_3/drawer_single_m"))
                .addTextureM(modRes("block/drawer/oak/front_4/drawer"), EveryCompat.res("block/hc/drawer/front_4/drawer_m"))
                .addTexture(modRes("block/drawer/oak/drawer_back"))
                .addTexture(modRes("block/drawer/oak/drawer_bottom"))
                .addTexture(modRes("block/drawer/oak/drawer_side_left"))
                .addTexture(modRes("block/drawer/oak/drawer_side_right"))
                .addTexture(modRes("block/drawer/oak/drawer_top"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("drawers"), Registries.BLOCK, Registries.ITEM)
                .setTabKey(tab)
                .addCustomItem((w, b, p) -> new BlockItem(b, p))
                .defaultRecipe()
                .build();
        this.addEntry(drawer);

        shelf = SimpleEntrySet.builder(WoodType.class, "shelf",
                        ModBlocks.OAK_SHELF, () -> VanillaWoodTypes.OAK,
                        w -> new ShelfBlock(Utils.copyPropertySafe(w.planks))
                )
                .addTile(getModTile("container"))
                .addTexture(modRes("block/shelf/oak/shelf_back"))
                .addTexture(modRes("block/shelf/oak/shelf_left"))
                .addTexture(modRes("block/shelf/oak/shelf_middle"))
                .addTexture(modRes("block/shelf/oak/shelf_right"))
                .addTexture(modRes("block/shelf/oak/shelf_side_left"))
                .addTexture(modRes("block/shelf/oak/shelf_side_right"))
                .addTexture(modRes("block/shelf/oak/shelf_single"))
                .addTexture(modRes("block/shelf/oak/shelf_top"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("shelves"), Registries.BLOCK, Registries.ITEM)
                .setTabKey(tab)
                .addCustomItem((w, b, p) -> new BlockItem(b, p))
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(shelf);

        pillarTrim = SimpleEntrySet.builder(WoodType.class, "pillar_trim",
                        ModBlocks.OAK_PILLAR_TRIM, () -> VanillaWoodTypes.OAK,
                        w -> new PillarTrimBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), true)
                )
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addTexture(modRes("block/trim/pillar/oak_pillar_trim_normal"))
                .addTexture(modRes("block/trim/pillar/oak_pillar_trim_thicc"))
                .addTexture(modRes("block/trim/pillar/oak_pillar_trim_thin"))
                .addTexture(modRes("block/trim/pillar/oak_pillar_trim_2_normal"))
                .addTexture(modRes("block/trim/pillar/oak_pillar_trim_2_thicc"))
                .addTexture(modRes("block/trim/pillar/oak_pillar_trim_2_thin"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("pillar_trims"), Registries.BLOCK, Registries.ITEM)
                .addTag(modRes("trims"), Registries.BLOCK, Registries.ITEM)
                .setTabKey(tab)
                .addCustomItem((w, b, p) -> new BlockItem(b, p))
                .defaultRecipe()
                .build();
        this.addEntry(pillarTrim);

        cornerTrim = SimpleEntrySet.builder(WoodType.class, "corner_trim",
                        ModBlocks.OAK_CORNER_TRIM, () -> VanillaWoodTypes.OAK,
                        w -> new CornerTrimBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), true)
                )
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addTexture(modRes("block/trim/corner/oak_corner_trim_normal"))
                .addTexture(modRes("block/trim/corner/oak_corner_trim_thicc"))
                .addTexture(modRes("block/trim/corner/oak_corner_trim_thin"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("corner_trims"), Registries.BLOCK, Registries.ITEM)
                .addTag(modRes("trims"), Registries.BLOCK, Registries.ITEM)
                .setTabKey(tab)
                .addCustomItem((w, b, p) -> new BlockItem(b, p))
                .defaultRecipe()
                .build();
        this.addEntry(cornerTrim);
    }

    @Override
    public void addDynamicClientResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicClientResources(executor);

        /*
         * Creating a new model file to replace the acacia_counter_acacia_planks_X because the "top" is using incorrect
         * texture. The "top" shouldn't be modified but there is no solution
        */
        String modelFile = """
                    {
                      "parent": "handcrafted:block/counter",
                      "textures": {
                        "particle": "[planks]",
                        "top": "[modTexture]",
                        "wood": "[blockTexture]"
                      }
                    }
                    """;

        executor.accept((manager, sink) -> {

            counter.blocks.forEach((woodType, block) -> {
                for (int num = 1; num < 4; num++) {

                    //ID: everycomp:block/ shortenedId / namespace / counter/ TYPE_ counter_ num
                    String texturePath = woodType.createFullIdWith(EveryCompat.MOD_ID, "block", shortenedId(), "counter/",
                            "counter_" + num);

                    //PATH: shortenedId / namespace / TYPE _counter_acacia_planks_ num
                    String path = woodType.createPathWith(shortenedId(), "",
                            "counter_acacia_planks_" + num);

                    String planksTexture = "";

                    try {
                        planksTexture = RPUtils.findFirstBlockTextureLocation(manager, woodType.planks).toString();
                        if (planksTexture.isEmpty()) planksTexture = "particlesIsMissing";
                    } catch (FileNotFoundException ignored) {}

                    String modifiedModel = modelFile
                            .replace("[planks]", planksTexture)
                            .replace("[modTexture]", modId + ":block/counter/top/acacia_planks")
                            .replace("[blockTexture]", texturePath);
                    // Replace the strings



                        // Adding to the Resources
                    JsonElement oakJson = JsonParser.parseString(modifiedModel);
                    sink.addBlockModel(EveryCompat.res(path), oakJson);
                }

            });
        });

    }
}
