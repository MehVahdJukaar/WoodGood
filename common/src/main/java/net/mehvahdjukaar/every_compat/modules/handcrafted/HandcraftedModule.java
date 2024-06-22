package net.mehvahdjukaar.every_compat.modules.handcrafted;

import com.google.gson.JsonObject;
import earth.terrarium.handcrafted.Handcrafted;
import earth.terrarium.handcrafted.common.blockentities.ContainerBlockEntity;
import earth.terrarium.handcrafted.common.blocks.*;
import earth.terrarium.handcrafted.common.blocks.trims.CornerTrimBlock;
import earth.terrarium.handcrafted.common.blocks.trims.PillarTrimBlock;
import earth.terrarium.handcrafted.common.registry.ModBlocks;
import earth.terrarium.handcrafted.common.registry.ModItems;
import earth.terrarium.handcrafted.common.tags.ModBlockTags;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Supplier;

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
        Supplier<CreativeModeTab> tab = ModItems.TAB;

        chair = SimpleEntrySet.builder(WoodType.class, "chair",
                        ModBlocks.OAK_CHAIR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ChairBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                .setRenderType(() -> RenderType::cutout)
                .addTexture(modRes("block/chair/oak_chair"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("chairs"), Registries.BLOCK)
                .addTag(modRes("chairs"), Registries.ITEM)
                .setTab(tab)
                .addCustomItem((w, b, p) -> new BlockItem(b, p))
                .defaultRecipe()
                .build();
        this.addEntry(chair);

        table = SimpleEntrySet.builder(WoodType.class, "table",
                        ModBlocks.OAK_TABLE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TableBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                .addTile(ContainerBlockEntity::new)
                .setRenderType(() -> RenderType::cutout)
                .addTexture(modRes("block/table/table/oak_table"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(ModBlockTags.TABLE_CONNECTABLE, Registries.BLOCK)
                .addTag(modRes("tables"), Registries.BLOCK)
                .addTag(modRes("tables"), Registries.ITEM)
                .setTab(tab)
                .addCustomItem((w, b, p) -> new BlockItem(b, p))
                .defaultRecipe()
                .build();
        this.addEntry(table);

        bench = SimpleEntrySet.builder(WoodType.class, "bench",
                        ModBlocks.OAK_BENCH, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BenchBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                .addTile(ContainerBlockEntity::new)
                .setRenderType(() -> RenderType::cutout)
                .addTexture(modRes("block/bench/oak_bench"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("wooden_benches"), Registries.BLOCK)
                .addTag(modRes("benches"), Registries.BLOCK)
                .addTag(modRes("wooden_benches"), Registries.ITEM)
                .addTag(modRes("benches"), Registries.ITEM)
                .setTab(tab)
                .addCustomItem((w, b, p) -> new BlockItem(b, p))
                .defaultRecipe()
                .build();
        this.addEntry(bench);

        couch = SimpleEntrySet.builder(WoodType.class, "couch",
                        ModBlocks.OAK_COUCH, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CouchBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                .addTile(ContainerBlockEntity::new)
                .setRenderType(() -> RenderType::cutout)
                .addTexture(modRes("block/couch/oak_couch"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("couches"), Registries.BLOCK)
                .addTag(modRes("couches"), Registries.ITEM)
                .setTab(tab)
                .addCustomItem((w, b, p) -> new BlockItem(b, p))
                .defaultRecipe()
                .build();
        this.addEntry(couch);

        fancy_bed = SimpleEntrySet.builder(WoodType.class, "fancy_bed",
                        ModBlocks.OAK_FANCY_BED, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new FancyBedBlock(Utils.copyPropertySafe(Blocks.WHITE_BED))
                )
                .setRenderType(() -> RenderType::cutout)
                .addTexture(modRes("block/fancy_bed/single/oak_fancy_bed"))
                .addTexture(modRes("block/fancy_bed/double/oak_fancy_bed"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("fancy_beds"), Registries.BLOCK)
                .addTag(BlockTags.BEDS, Registries.BLOCK)
                .addTag(modRes("fancy_beds"), Registries.ITEM)
                .addTag(BlockTags.BEDS, Registries.ITEM)
                .setTab(tab)
                .addCustomItem((w, b, p) -> new BlockItem(b, p))
                .defaultRecipe()
                .build();
        this.addEntry(fancy_bed);

        dining_bench = SimpleEntrySet.builder(WoodType.class, "dining_bench",
                        ModBlocks.OAK_DINING_BENCH, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DiningBenchBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                .setRenderType(() -> RenderType::cutout)
                .addTexture(modRes("block/dining_bench/oak_dining_bench"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("dining_benches"), Registries.BLOCK)
                .addTag(modRes("dining_benches"), Registries.ITEM)
                .setTab(tab)
                .addCustomItem((w, b, p) -> new BlockItem(b, p))
                .defaultRecipe()
                .build();
        this.addEntry(dining_bench);

        nightstand = SimpleEntrySet.builder(WoodType.class, "nightstand",
                        ModBlocks.OAK_NIGHTSTAND, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new NightstandBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                .addTile(ContainerBlockEntity::new)
                .setRenderType(() -> RenderType::cutout)
                .addTextureM(modRes("block/table/nightstand/oak_nightstand"), EveryCompat.res("block/hc/table/oak_nightstand_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(ModBlockTags.TABLE_CONNECTABLE, Registries.BLOCK)
                .addTag(modRes("nightstands"), Registries.BLOCK)
                .addTag(modRes("nightstands"), Registries.ITEM)
                .setTab(tab)
                .defaultRecipe()
                .addCustomItem((w, b, p) -> new BlockItem(b, p))
                .build();
        this.addEntry(nightstand);

        desk = SimpleEntrySet.builder(WoodType.class, "desk",
                        ModBlocks.OAK_DESK, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DeskBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                .addTile(ContainerBlockEntity::new)
                .setRenderType(() -> RenderType::cutout)
                .addTextureM(modRes("block/table/desk/oak_desk"), EveryCompat.res("block/hc/table/oak_desk_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(ModBlockTags.TABLE_CONNECTABLE, Registries.BLOCK)
                .addTag(modRes("desks"), Registries.BLOCK)
                .addTag(modRes("desks"), Registries.ITEM)
                .setTab(tab)
                .defaultRecipe()
                .addCustomItem((w, b, p) -> new BlockItem(b, p))
                .build();
        this.addEntry(desk);

        side_table = SimpleEntrySet.builder(WoodType.class, "side_table",
                        ModBlocks.OAK_SIDE_TABLE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new SideTableBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                .addTile(ContainerBlockEntity::new)
                .setRenderType(() -> RenderType::cutout)
                .addTextureM(modRes("block/table/side_table/oak_side_table"), EveryCompat.res("block/hc/table/oak_side_table_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("side_tables"), Registries.BLOCK)
                .addTag(modRes("side_tables"), Registries.ITEM)
                .setTab(tab)
                .addCustomItem((w, b, p) -> new BlockItem(b, p))
                .defaultRecipe()
                .build();
        this.addEntry(side_table);

        counter = SimpleEntrySet.builder(WoodType.class, "counter",
                        ModBlocks.OAK_COUNTER, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CounterBlock(Utils.copyPropertySafe(w.planks))
                )
                .addTile(ContainerBlockEntity::new)
                .addTextureM(modRes("block/counter/oak_counter_1"), EveryCompat.res("block/hc/counter/oak_counter_1_m"))
                .addTextureM(modRes("block/counter/oak_counter_2"), EveryCompat.res("block/hc/counter/oak_counter_2_m"))
                .addTextureM(modRes("block/counter/oak_counter_3"), EveryCompat.res("block/hc/counter/oak_counter_3_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("counters"), Registries.BLOCK)
                .addTag(modRes("counters"), Registries.ITEM)
                .setTab(tab)
                .addCustomItem((w, b, p) -> new BlockItem(b, p))
                .defaultRecipe()
                .addModelTransform( // to prevent the changing of "oak_planks"
                        m -> m.addModifier((s, resLoc, w) -> s.replace(
                                "\"top\": \"everycomp:block/hc/"+w.getNamespace()+"/counter/top/"+w.getTypeName()+"_planks\"",
                                "\"top\": \"handcrafted:block/counter/top/oak_planks\""))
                )
                .build();
        this.addEntry(counter);

        cupboard = SimpleEntrySet.builder(WoodType.class, "cupboard",
                        ModBlocks.OAK_CUPBOARD, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CupboardBlock(Utils.copyPropertySafe(w.planks))
                )
                .addTile(ContainerBlockEntity::new)
                .addTextureM(modRes("block/cupboard/oak/cupboard_1"), EveryCompat.res("block/hc/cupboard/cupboard_1_m"))
                .addTextureM(modRes("block/cupboard/oak/cupboard_2"), EveryCompat.res("block/hc/cupboard/cupboard_2_m"))
                .addTexture(modRes("block/cupboard/oak/cupboard_back"))
                .addTexture(modRes("block/cupboard/oak/cupboard_side"))
                .addTexture(modRes("block/cupboard/oak/cupboard_top"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("cupboards"), Registries.BLOCK)
                .addTag(modRes("cupboards"), Registries.ITEM)
                .setTab(tab)
                .addCustomItem((w, b, p) -> new BlockItem(b, p))
                .defaultRecipe()
                .build();
        this.addEntry(cupboard);

        drawer = SimpleEntrySet.builder(WoodType.class, "drawer",
                        ModBlocks.OAK_DRAWER, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DrawerBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                .addTile(ContainerBlockEntity::new)
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
                .addTag(modRes("drawers"), Registries.BLOCK)
                .addTag(modRes("drawers"), Registries.ITEM)
                .setTab(tab)
                .addCustomItem((w, b, p) -> new BlockItem(b, p))
                .defaultRecipe()
                .build();
        this.addEntry(drawer);

        shelf = SimpleEntrySet.builder(WoodType.class, "shelf",
                        ModBlocks.OAK_SHELF, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ShelfBlock(Utils.copyPropertySafe(w.planks))
                )
                .addTile(ContainerBlockEntity::new)
                .addTexture(modRes("block/shelf/oak/shelf_back"))
                .addTexture(modRes("block/shelf/oak/shelf_left"))
                .addTexture(modRes("block/shelf/oak/shelf_middle"))
                .addTexture(modRes("block/shelf/oak/shelf_right"))
                .addTexture(modRes("block/shelf/oak/shelf_side_left"))
                .addTexture(modRes("block/shelf/oak/shelf_side_right"))
                .addTexture(modRes("block/shelf/oak/shelf_single"))
                .addTexture(modRes("block/shelf/oak/shelf_top"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("shelves"), Registries.BLOCK)
                .addTag(modRes("shelves"), Registries.ITEM)
                .setTab(tab)
                .addCustomItem((w, b, p) -> new BlockItem(b, p))
                .defaultRecipe()
                .build();
        this.addEntry(shelf);

        pillarTrim = SimpleEntrySet.builder(WoodType.class, "pillar_trim",
                        ModBlocks.OAK_PILLAR_TRIM, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new PillarTrimBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), true)
                )
                .setRenderType(() -> RenderType::cutout)
                .addTexture(modRes("block/trim/pillar/oak_pillar_trim_normal"))
                .addTexture(modRes("block/trim/pillar/oak_pillar_trim_thicc"))
                .addTexture(modRes("block/trim/pillar/oak_pillar_trim_thin"))
                .addTexture(modRes("block/trim/pillar/oak_pillar_trim_2_normal"))
                .addTexture(modRes("block/trim/pillar/oak_pillar_trim_2_thicc"))
                .addTexture(modRes("block/trim/pillar/oak_pillar_trim_2_thin"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("pillar_trims"), Registries.BLOCK)
                                .addTag(modRes("trims"), Registries.BLOCK)
                .addTag(modRes("pillar_trims"), Registries.ITEM)
                                .addTag(modRes("trims"), Registries.ITEM)
                .setTab(tab)
                .addCustomItem((w, b, p) -> new BlockItem(b, p))
                .defaultRecipe()
                .build();
        this.addEntry(pillarTrim);

        cornerTrim = SimpleEntrySet.builder(WoodType.class, "corner_trim",
                        ModBlocks.OAK_CORNER_TRIM, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CornerTrimBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), true)
                )
                .setRenderType(() -> RenderType::cutout)
                .addTexture(modRes("block/trim/corner/oak_corner_trim_normal"))
                .addTexture(modRes("block/trim/corner/oak_corner_trim_thicc"))
                .addTexture(modRes("block/trim/corner/oak_corner_trim_thin"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("corner_trims"), Registries.BLOCK)
                                .addTag(modRes("trims"), Registries.BLOCK)
                .addTag(modRes("corner_trims"), Registries.ITEM)
                                .addTag(modRes("trims"), Registries.ITEM)
                .setTab(tab)
                .addCustomItem((w, b, p) -> new BlockItem(b, p))
                .defaultRecipe()
                .build();
        this.addEntry(cornerTrim);
    }

    @Override
    // Models
    public void addDynamicClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicClientResources(handler, manager);

        /*
        * Correcting the texture for "top" in counters' model to use for counter's top/surface
        * There is no way to prevent oak_planks or dark_oak_planks from being changed to other woodtypes
        */
        counter.blocks.forEach((w, block) -> {

            for (int num = 1; num<4; num++) {

                String path = "hc/" + w.getAppendableId() + "_counter_oak_planks_" + num;
                String darkPath = "hc/" + w.getAppendableId() + "_counter_dark_oak_planks_" + num;
                ResourceLocation oakModelFile = EveryCompat.res("models/block/" + path + ".json");
                ResourceLocation darkModelFile = EveryCompat.res("models/block/" + darkPath + ".json");

                try (InputStream oakStream = manager.getResource(oakModelFile)
                        .orElseThrow(FileNotFoundException::new).open();
                     InputStream darkStream = manager.getResource(darkModelFile)
                        .orElseThrow(FileNotFoundException::new).open()
                ) {
                    JsonObject oakModel = RPUtils.deserializeJson(oakStream);
                    JsonObject darkModel = RPUtils.deserializeJson(darkStream);

                    oakModel.getAsJsonObject("textures").addProperty("top",
                            Handcrafted.MOD_ID + ":block/counter/top/oak_planks");
                    darkModel.getAsJsonObject("textures").addProperty("top",
                            Handcrafted.MOD_ID + ":block/counter/top/dark_oak_planks");

                    // Adding to the resources
                    handler.dynamicPack.addJson(EveryCompat.res(path), oakModel, ResType.BLOCK_MODELS);
                    handler.dynamicPack.addJson(EveryCompat.res(darkPath), darkModel, ResType.BLOCK_MODELS);
                }
                catch (IOException e) {
                    handler.getLogger().error("Failed to read the model file for: {}", block);
                }
            }


        });
    }
}
