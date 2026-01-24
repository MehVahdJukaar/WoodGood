package net.mehvahdjukaar.every_compat.modules.neoforge.builders_delight;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.AbstractSimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceSink;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.Item;

import java.io.IOException;
import java.io.InputStream;

//SUPPORT: v1.3+
public class BuildersDelightModule extends EveryCompatModule {
    //TYPE: ITEM
/*
    public final ItemOnlyEntrySet<WoodType, Item> FURNITURE_KIT;

    //TYPE: DECORATION
    public final SimpleEntrySet<WoodType, Block> CHAIR_1, CHAIR_2;
    public final SimpleEntrySet<WoodType, Block> TABLE_1, TABLE_2;

    //TYPE: BLOCKS
    public final SimpleEntrySet<WoodType, Block> PLANKS_1, PLANKS_2, PLANKS_3, PLANKS_4, PLANKS_5, PLANKS_6, PLANKS_7;

    public final SimpleEntrySet<WoodType, Block> STAIRS_1, STAIRS_2, STAIRS_3, STAIRS_4, STAIRS_5, STAIRS_6, STAIRS_7;

    public final SimpleEntrySet<WoodType, Block> SLAB_1, SLAB_2, SLAB_3, SLAB_4, SLAB_5, SLAB_6, SLAB_7;

    public final SimpleEntrySet<WoodType, Block> FRAME_1, FRAME_2, FRAME_3, FRAME_4, FRAME_5, FRAME_6, FRAME_7, FRAME_8;

    //TYPE: GLASS
    public final SimpleEntrySet<WoodType, Block> GLASS_1, GLASS_2, GLASS_3, GLASS_4, GLASS_5, GLASS_6, GLASS_7, GLASS_8;

    public final SimpleEntrySet<WoodType, Block> GLASS_PANE_1, GLASS_PANE_2, GLASS_PANE_3, GLASS_PANE_4, GLASS_PANE_5, GLASS_PANE_6, GLASS_PANE_7, GLASS_PANE_8;
*/


    @SuppressWarnings("DataFlowIssue") // <- already has null check
    public BuildersDelightModule(String modId) {
        super(modId, "bdl");
        ResourceLocation tabDeco = modRes("decoration");
        ResourceLocation tabBlock = modRes("blocks");
        ResourceLocation tabMater = modRes("materials");
        //TYPE: ITEM
/*        FURNITURE_KIT = ItemOnlyEntrySet.builder(WoodType.class, "furniture_kit",
                        getModItem("oak_furniture_kit"), () -> VanillaWoodTypes.OAK,
                        w -> new CustomBdFurnitureKit(new Item.Properties().stacksTo(64),"furniture_kit")
                )
                .setTabKey(tabMater)
                .addTextureM(modRes("item/oak_furniture_kit"), EveryCompat.res("item/bdl/furniture_kit_mask"),
                        PaletteStrategies.WOOD_ITEM)
                // manual recipe below
                .build();
        this.addEntry(FURNITURE_KIT);

        //TYPE: CHAIR
        CHAIR_1 = SimpleEntrySet.builder(WoodType.class, "chair_1",
                        BdDecoration.OAK_CHAIR_1, () -> VanillaWoodTypes.OAK,
                        w -> new BlockChair(Utils.copyPropertySafe(w.planks))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("chair"), Registries.ITEM)
                .setTabKey(tabDeco)
                .addTexture(modRes("block/decoration/seating/oak/oak_chair_1"))
                .createPaletteFromPlanks(this::lessContrastPalette)
                .addRecipe(ResourceLocation.tryParse("minecraft:oak_chair_1"))
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addCustomItem((woodType, block, properties) -> new BDBlockItem(block, properties, "chair_1"))
                .build();
        this.addEntry(CHAIR_1);

        CHAIR_2 = SimpleEntrySet.builder(WoodType.class, "chair_2",
                        BdDecoration.OAK_CHAIR_2, () -> VanillaWoodTypes.OAK,
                        w -> new BlockStool(Utils.copyPropertySafe(w.planks))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("chair"), Registries.ITEM)
                .addTexture(modRes("block/decoration/seating/oak/oak_chair_2"))
                .createPaletteFromPlanks(this::lessContrastPalette)
                .addRecipe(ResourceLocation.tryParse("minecraft:oak_chair_2"))
                .setTabKey(tabDeco)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "chair_2"))
                .build();
        this.addEntry(CHAIR_2);


        //TYPE: TABLE
        TABLE_1 = SimpleEntrySet.builder(WoodType.class, "table_1",
                        BdDecoration.OAK_TABLE_1,
                        () -> VanillaWoodTypes.OAK,
                        w -> new BlockSmallTable(Utils.copyPropertySafe(w.planks))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("table"), Registries.ITEM)
                .setTabKey(tabDeco)
                .addTexture(modRes("block/decoration/tables/oak/oak_table_1"))
                .addRecipe(ResourceLocation.tryParse("minecraft:oak_table_1"))
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "table_1"))
                .build();
        this.addEntry(TABLE_1);

        TABLE_2 = SimpleEntrySet.builder(WoodType.class, "table_2",
                        BdDecoration.OAK_TABLE_2, () -> VanillaWoodTypes.OAK,
                        w -> new BlockSmallTable(Utils.copyPropertySafe(w.planks))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("table"), Registries.ITEM)
                .setTabKey(tabDeco)
                .addTexture(modRes("block/decoration/tables/oak/oak_table_2"))
                .addRecipe(ResourceLocation.tryParse("minecraft:oak_table_2"))
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "table_2"))
                .build();
        this.addEntry(TABLE_2);


        //TYPE: PLANKS
        PLANKS_1 = SimpleEntrySet.builder(WoodType.class, "planks_1",
                        BdBlocks.OAK_PLANKS_1, () -> VanillaWoodTypes.OAK,
                        w -> new BlockFlammable(Utils.copyPropertySafe(w.planks))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tabBlock)
                .addTexture(modRes("block/oak_planks_1"))
                .createPaletteFromPlanks(this::lessContrastPalette)
                .addRecipe(modRes("oak_planks_1"))
                .setRenderType(RenderLayer.SOLID)
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "planks_1"))
                .build();
        this.addEntry(PLANKS_1);

        PLANKS_2 = SimpleEntrySet.builder(WoodType.class, "planks_2",
                        BdBlocks.OAK_PLANKS_2, () -> VanillaWoodTypes.OAK,
                        w -> new BlockFlammable(Utils.copyPropertySafe(w.planks))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tabBlock)
                .addTexture(modRes("block/oak_planks_2"))
                .createPaletteFromPlanks(this::lessContrastPalette)
                .addRecipe(modRes("oak_planks_2"))
                .setRenderType(RenderLayer.SOLID)
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "planks_2"))
                .build();
        this.addEntry(PLANKS_2);

        PLANKS_3 = SimpleEntrySet.builder(WoodType.class, "planks_3",
                        BdBlocks.OAK_PLANKS_3, () -> VanillaWoodTypes.OAK,
                        w -> new BlockFlammable(Utils.copyPropertySafe(w.planks))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tabBlock)
                .addTexture(modRes("block/oak_planks_3"))
                .createPaletteFromPlanks(this::lessContrastPalette)
                .addRecipe(modRes("oak_planks_3"))
                .setRenderType(RenderLayer.SOLID)
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "planks_3"))
                .build();
        this.addEntry(PLANKS_3);

        PLANKS_4 = SimpleEntrySet.builder(WoodType.class, "planks_4",
                        BdBlocks.OAK_PLANKS_4, () -> VanillaWoodTypes.OAK,
                        w -> new BlockFlammable(Utils.copyPropertySafe(w.planks))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tabBlock)
                .addTexture(modRes("block/oak_planks_4"))
                .createPaletteFromPlanks(this::lessContrastPalette)
                .addRecipe(modRes("oak_planks_4"))
                .setRenderType(RenderLayer.SOLID)
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "planks_4"))
                .build();
        this.addEntry(PLANKS_4);

        PLANKS_5 = SimpleEntrySet.builder(WoodType.class, "planks_5",
                        BdBlocks.OAK_PLANKS_5, () -> VanillaWoodTypes.OAK,
                        w -> new BlockFlammable(Utils.copyPropertySafe(w.planks))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tabBlock)
                .addTexture(modRes("block/oak_planks_5"))
                .createPaletteFromPlanks(this::lessContrastPalette)
                .addRecipe(modRes("oak_planks_5"))
                .setRenderType(RenderLayer.SOLID)
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "planks_5"))
                .build();
        this.addEntry(PLANKS_5);

        PLANKS_6 = SimpleEntrySet.builder(WoodType.class, "planks_6",
                        BdBlocks.OAK_PLANKS_6, () -> VanillaWoodTypes.OAK,
                        w -> new BlockFlammable(Utils.copyPropertySafe(w.planks))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tabBlock)
                .addTexture(modRes("block/oak_planks_6"))
                .createPaletteFromPlanks(this::lessContrastPalette)
                .addRecipe(modRes("oak_planks_6"))
                .setRenderType(RenderLayer.SOLID)
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "planks_6"))
                .build();
        this.addEntry(PLANKS_6);

        PLANKS_7 = SimpleEntrySet.builder(WoodType.class, "planks_7",
                        BdBlocks.OAK_PLANKS_7, () -> VanillaWoodTypes.OAK,
                        w -> new BlockFlammable(Utils.copyPropertySafe(w.planks))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tabBlock)
                .addTexture(modRes("block/oak_planks_7"))
                .createPaletteFromPlanks(this::lessContrastPalette)
                .addRecipe(modRes("oak_planks_7"))
                .setRenderType(RenderLayer.SOLID)
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "planks_7"))
                .build();
        this.addEntry(PLANKS_7);


        //TYPE: STAIRS
        STAIRS_1 = SimpleEntrySet.builder(WoodType.class, "stairs_1",
                        BdBlocks.OAK_STAIRS_1, () -> VanillaWoodTypes.OAK,
                        w -> new StairFlammable(Blocks.OAK_STAIRS.defaultBlockState(), Utils.copyPropertySafe(w.getBlockOfThis("stairs")))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tabBlock)
                .requiresChildren("stairs")
                // using the same textures from planks
                .addRecipe(modRes("oak_stairs_1"))
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "stairs_1"))
                .build();
        this.addEntry(STAIRS_1);

        STAIRS_2 = SimpleEntrySet.builder(WoodType.class, "stairs_2",
                        BdBlocks.OAK_STAIRS_2, () -> VanillaWoodTypes.OAK,
                        w -> new StairFlammable(Blocks.OAK_STAIRS.defaultBlockState(), Utils.copyPropertySafe(w.getBlockOfThis("stairs")))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tabBlock)
                .requiresChildren("stairs")
                // using the same textures from planks
                .addRecipe(modRes("oak_stairs_2"))
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "stairs_2"))
                .build();
        this.addEntry(STAIRS_2);

        STAIRS_3 = SimpleEntrySet.builder(WoodType.class, "stairs_3",
                        BdBlocks.OAK_STAIRS_3, () -> VanillaWoodTypes.OAK,
                        w -> new StairFlammable(Blocks.OAK_STAIRS.defaultBlockState(), Utils.copyPropertySafe(w.getBlockOfThis("stairs")))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tabBlock)
                .requiresChildren("stairs")
                // using the same textures from planks
                .addRecipe(modRes("oak_stairs_3"))
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "stairs_3"))
                .build();
        this.addEntry(STAIRS_3);

        STAIRS_4 = SimpleEntrySet.builder(WoodType.class, "stairs_4",
                        BdBlocks.OAK_STAIRS_4, () -> VanillaWoodTypes.OAK,
                        w -> new StairFlammable(Blocks.OAK_STAIRS.defaultBlockState(), Utils.copyPropertySafe(w.getBlockOfThis("stairs")))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tabBlock)
                .requiresChildren("stairs")
                // using the same textures from planks
                .addRecipe(modRes("oak_stairs_4"))
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "stairs_4"))
                .build();
        this.addEntry(STAIRS_4);

        STAIRS_5 = SimpleEntrySet.builder(WoodType.class, "stairs_5",
                        BdBlocks.OAK_STAIRS_5, () -> VanillaWoodTypes.OAK,
                        w -> new StairFlammable(Blocks.OAK_STAIRS.defaultBlockState(), Utils.copyPropertySafe(w.getBlockOfThis("stairs")))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tabBlock)
                .requiresChildren("stairs")
                // using the same textures from planks
                .addRecipe(modRes("oak_stairs_5"))
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "stairs_5"))
                .build();
        this.addEntry(STAIRS_5);

        STAIRS_6 = SimpleEntrySet.builder(WoodType.class, "stairs_6",
                        BdBlocks.OAK_STAIRS_6, () -> VanillaWoodTypes.OAK,
                        w -> new StairFlammable(Blocks.OAK_STAIRS.defaultBlockState(), Utils.copyPropertySafe(w.getBlockOfThis("stairs")))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tabBlock)
                .requiresChildren("stairs")
                // using the same textures from planks
                .addRecipe(modRes("oak_stairs_6"))
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "stairs_6"))
                .build();
        this.addEntry(STAIRS_6);

        STAIRS_7 = SimpleEntrySet.builder(WoodType.class, "stairs_7",
                        BdBlocks.OAK_STAIRS_7, () -> VanillaWoodTypes.OAK,
                        w -> new StairFlammable(Blocks.OAK_STAIRS.defaultBlockState(), Utils.copyPropertySafe(w.getBlockOfThis("stairs")))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tabBlock)
                .requiresChildren("stairs")
                // using the same textures from planks
                .addRecipe(modRes("oak_stairs_7"))
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "stairs_7"))
                .build();
        this.addEntry(STAIRS_7);


        //TYPE: SLAB
        SLAB_1 = SimpleEntrySet.builder(WoodType.class, "slab_1",
                        BdBlocks.OAK_SLAB_1, () -> VanillaWoodTypes.OAK,
                        w -> new SlabFlammable(Utils.copyPropertySafe(w.getBlockOfThis("slab")))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .requiresChildren("slab")
                .setTabKey(tabBlock)
                // using the same textures from planks
                .addRecipe(modRes("oak_slab_1"))
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "slab_1"))
                .build();
        this.addEntry(SLAB_1);

        SLAB_2 = SimpleEntrySet.builder(WoodType.class, "slab_2",
                        BdBlocks.OAK_SLAB_2, () -> VanillaWoodTypes.OAK,
                        w -> new SlabFlammable(Utils.copyPropertySafe(w.getBlockOfThis("slab")))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tabBlock)
                .requiresChildren("slab")
                // using the same textures from planks
                .addRecipe(modRes("oak_slab_2"))
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "slab_2"))
                .build();
        this.addEntry(SLAB_2);

        SLAB_3 = SimpleEntrySet.builder(WoodType.class, "slab_3",
                        BdBlocks.OAK_SLAB_3, () -> VanillaWoodTypes.OAK,
                        w -> new SlabFlammable(Utils.copyPropertySafe(w.getBlockOfThis("slab")))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tabBlock)
                .requiresChildren("slab")
                // using the same textures from planks
                .addRecipe(modRes("oak_slab_3"))
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "slab_3"))
                .build();
        this.addEntry(SLAB_3);

        SLAB_4 = SimpleEntrySet.builder(WoodType.class, "slab_4",
                        BdBlocks.OAK_SLAB_4, () -> VanillaWoodTypes.OAK,
                        w -> new SlabFlammable(Utils.copyPropertySafe(w.getBlockOfThis("slab")))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tabBlock)
                .requiresChildren("slab")
                // using the same textures from planks
                .addRecipe(modRes("oak_slab_4"))
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "slab_4"))
                .build();
        this.addEntry(SLAB_4);

        SLAB_5 = SimpleEntrySet.builder(WoodType.class, "slab_5",
                        BdBlocks.OAK_SLAB_5, () -> VanillaWoodTypes.OAK,
                        w -> new SlabFlammable(Utils.copyPropertySafe(w.getBlockOfThis("slab")))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tabBlock)
                .requiresChildren("slab")
                // using the same textures from planks
                .addRecipe(modRes("oak_slab_5"))
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "slab_5"))
                .build();
        this.addEntry(SLAB_5);

        SLAB_6 = SimpleEntrySet.builder(WoodType.class, "slab_6",
                        BdBlocks.OAK_SLAB_6, () -> VanillaWoodTypes.OAK,
                        w -> new SlabFlammable(Utils.copyPropertySafe(w.getBlockOfThis("slab")))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tabBlock)
                .requiresChildren("slab")
                // using the same textures from planks
                .addRecipe(modRes("oak_slab_6"))
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "slab_6"))
                .build();
        this.addEntry(SLAB_6);

        SLAB_7 = SimpleEntrySet.builder(WoodType.class, "slab_7",
                        BdBlocks.OAK_SLAB_7, () -> VanillaWoodTypes.OAK,
                        w -> new SlabFlammable(Utils.copyPropertySafe(w.getBlockOfThis("slab")))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tabBlock)
                .requiresChildren("slab")
                // using the same textures from planks
                .addRecipe(modRes("oak_slab_7"))
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "slab_7"))
                .build();
        this.addEntry(SLAB_7);


        //TYPE: FRAME
        FRAME_1 = SimpleEntrySet.builder(WoodType.class, "frame_1",
                        BdBlocks.OAK_FRAME_1, () -> VanillaWoodTypes.OAK,
                        w -> new BlockFlammable(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL))
                )
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "frame_1"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tabBlock)
                .addTexture(modRes("block/oak_frame_1"),
                        PaletteStrategies.PLANKS_LOW_CONTRAST)
                // custom recipe below
                .setRenderType(RenderLayer.SOLID)
                .build();
        this.addEntry(FRAME_1);

        FRAME_2 = SimpleEntrySet.builder(WoodType.class, "frame_2",
                        BdBlocks.OAK_FRAME_2, () -> VanillaWoodTypes.OAK,
                        w -> new BlockFlammable(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL))
                )
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "frame_2"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("frame"), Registries.ITEM)
                .setTabKey(tabBlock)
                .addTexture(modRes("block/oak_frame_2"),
                        PaletteStrategies.PLANKS_LOW_CONTRAST)
                // ChiselRecipe
                .setRenderType(RenderLayer.SOLID)
                .build();
        this.addEntry(FRAME_2);

        FRAME_3 = SimpleEntrySet.builder(WoodType.class, "frame_3",
                        BdBlocks.OAK_FRAME_3, () -> VanillaWoodTypes.OAK,
                        w -> new BlockFlammable(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL))
                )
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "frame_3"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("frame"), Registries.ITEM)
                .setTabKey(tabBlock)
                .addTexture(modRes("block/oak_frame_3"),
                        PaletteStrategies.PLANKS_LOW_CONTRAST)
                // ChiselRecipe
                .setRenderType(RenderLayer.SOLID)
                .build();
        this.addEntry(FRAME_3);

        FRAME_4 = SimpleEntrySet.builder(WoodType.class, "frame_4",
                        BdBlocks.OAK_FRAME_4, () -> VanillaWoodTypes.OAK,
                        w -> new BlockFlammable(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL))
                )
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "frame_4"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("frame"), Registries.ITEM)
                .setTabKey(tabBlock)
                .addTexture(modRes("block/oak_frame_4"),
                        PaletteStrategies.PLANKS_LOW_CONTRAST)
                // ChiselRecipe
                .setRenderType(RenderLayer.SOLID)
                .build();
        this.addEntry(FRAME_4);

        FRAME_5 = SimpleEntrySet.builder(WoodType.class, "frame_5",
                        BdBlocks.OAK_FRAME_5, () -> VanillaWoodTypes.OAK,
                        w -> new BlockFlammable(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL))
                )
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "frame_5"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("frame"), Registries.ITEM)
                .setTabKey(tabBlock)
                .addTexture(modRes("block/oak_frame_5"),
                        PaletteStrategies.PLANKS_LOW_CONTRAST)
                // ChiselRecipe
                .setRenderType(RenderLayer.SOLID)
                .build();
        this.addEntry(FRAME_5);

        FRAME_6 = SimpleEntrySet.builder(WoodType.class, "frame_6",
                        BdBlocks.OAK_FRAME_6, () -> VanillaWoodTypes.OAK,
                        w -> new BlockFlammable(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL))
                )
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "frame_6"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("frame"), Registries.ITEM)
                .setTabKey(tabBlock)
                .addTexture(modRes("block/oak_frame_6"),
                        PaletteStrategies.PLANKS_LOW_CONTRAST)
                // ChiselRecipe
                .setRenderType(RenderLayer.SOLID)
                .build();
        this.addEntry(FRAME_6);

        FRAME_7 = SimpleEntrySet.builder(WoodType.class, "frame_7",
                        BdBlocks.OAK_FRAME_7, () -> VanillaWoodTypes.OAK,
                        w -> new BlockFlammable(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL))
                )
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "frame_7"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("frame"), Registries.ITEM)
                .setTabKey(tabBlock)
                .addTexture(modRes("block/oak_frame_7"),
                        PaletteStrategies.PLANKS_LOW_CONTRAST)
                // ChiselRecipe
                .setRenderType(RenderLayer.SOLID)
                .build();
        this.addEntry(FRAME_7);

        FRAME_8 = SimpleEntrySet.builder(WoodType.class, "frame_8",
                        BdBlocks.OAK_FRAME_8, () -> VanillaWoodTypes.OAK,
                        w -> new BlockFlammable(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL))
                )
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "frame_8"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("frame"), Registries.ITEM)
                .setTabKey(tabBlock)
                .addTexture(modRes("block/oak_frame_8"),
                        PaletteStrategies.PLANKS_LOW_CONTRAST)
                // ChiselRecipe
                .setRenderType(RenderLayer.SOLID)
                .build();
        this.addEntry(FRAME_8);


        //TYPE: GLASS
        GLASS_1 = SimpleEntrySet.builder(WoodType.class, "glass_1",
                        BdBlocks.OAK_GLASS_1, () -> VanillaWoodTypes.OAK,
                        w -> new BlockGlassBlock(Utils.copyPropertySafe(Blocks.GLASS))
                )
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "glass_1"))
                .addTag(Tags.Blocks.GLASS_BLOCKS_BLOCKS, Registries.BLOCK)
                .setTabKey(tabBlock)
                .addTextureM(modRes("block/oak_glass_1"), EveryCompat.res("block/bdl/oak_glass_x_l_mask"),
                        PaletteStrategies.PLANKS_LOW_CONTRAST)
                // custom recipe below
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .build();
        this.addEntry(GLASS_1);

        GLASS_2 = SimpleEntrySet.builder(WoodType.class, "glass_2",
                        BdBlocks.OAK_GLASS_2, () -> VanillaWoodTypes.OAK,
                        w -> new BlockGlassBlock(Utils.copyPropertySafe(Blocks.GLASS))
                )
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "glass_2"))
                .addTag(Tags.Blocks.GLASS_BLOCKS, Registries.BLOCK)
                .setTabKey(tabBlock)
                .addTextureM(modRes("block/oak_glass_2"), EveryCompat.res("block/bdl/oak_glass_2_mask"),
                        PaletteStrategies.PLANKS_LOW_CONTRAST)
                .addTextureM(modRes("block/oak_glass_2_top"), EveryCompat.res("block/bdl/oak_glass_x_l_mask"),
                        PaletteStrategies.PLANKS_LOW_CONTRAST)
                // ChiselRecipe
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .build();
        this.addEntry(GLASS_2);

        GLASS_3 = SimpleEntrySet.builder(WoodType.class, "glass_3",
                        BdBlocks.OAK_GLASS_3, () -> VanillaWoodTypes.OAK,
                        w -> new BlockGlassBlock(Utils.copyPropertySafe(Blocks.GLASS))
                )
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "glass_3"))
                .addTag(Tags.Blocks.GLASS_BLOCKS, Registries.BLOCK)
                .setTabKey(tabBlock)
                .addTexture(modRes("block/oak_glass_3"),
                        PaletteStrategies.PLANKS_LOW_CONTRAST)
                .addTexture(modRes("block/oak_glass_3_top"),
                        PaletteStrategies.PLANKS_LOW_CONTRAST)
                // ChiselRecipe
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .build();
        this.addEntry(GLASS_3);

        GLASS_4 = SimpleEntrySet.builder(WoodType.class, "glass_4",
                        BdBlocks.OAK_GLASS_4, () -> VanillaWoodTypes.OAK,
                        w -> new BlockGlassBlock(Utils.copyPropertySafe(Blocks.GLASS))
                )
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "glass_4"))
                .addTag(Tags.Blocks.GLASS_BLOCKS, Registries.BLOCK)
                .setTabKey(tabBlock)
                .addTextureM(modRes("block/oak_glass_4"), EveryCompat.res("block/bdl/oak_glass_4_mask"),
                        PaletteStrategies.PLANKS_LOW_CONTRAST)
                // ChiselRecipe
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .build();
        this.addEntry(GLASS_4);

        GLASS_5 = SimpleEntrySet.builder(WoodType.class, "glass_5",
                        BdBlocks.OAK_GLASS_5, () -> VanillaWoodTypes.OAK,
                        w -> new BlockGlassBlock(Utils.copyPropertySafe(Blocks.GLASS))
                )
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "glass_5"))
                .addTag(Tags.Blocks.GLASS_BLOCKS, Registries.BLOCK)
                .setTabKey(tabBlock)
                .addTextureM(modRes("block/oak_glass_5"), EveryCompat.res("block/bdl/oak_glass_5_mask"),
                        PaletteStrategies.PLANKS_LOW_CONTRAST)
                .addTextureM(modRes("block/oak_glass_5_top"), EveryCompat.res("block/bdl/oak_glass_x_s_mask"),
                        PaletteStrategies.PLANKS_LOW_CONTRAST)
                // ChiselRecipe
                .setRenderType(RenderLayer.TRANSLUCENT)
                .build();
        this.addEntry(GLASS_5);

        GLASS_6 = SimpleEntrySet.builder(WoodType.class, "glass_6",
                        BdBlocks.OAK_GLASS_6, () -> VanillaWoodTypes.OAK,
                        w -> new BlockGlassBlock(Utils.copyPropertySafe(Blocks.GLASS))
                )
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "glass_6"))
                .addTag(Tags.Blocks.GLASS_BLOCKS, Registries.BLOCK)
                .setTabKey(tabBlock)
                .addTextureM(modRes("block/oak_glass_6"), EveryCompat.res("block/bdl/oak_glass_x_s_mask"),
                        PaletteStrategies.PLANKS_LOW_CONTRAST)
                .addTextureM(modRes("block/oak_glass_6_top"), EveryCompat.res("block/bdl/oak_glass_x_s_mask"),
                        PaletteStrategies.PLANKS_LOW_CONTRAST)
                // ChiselRecipe
                .setRenderType(RenderLayer.TRANSLUCENT)
                .build();
        this.addEntry(GLASS_6);

        GLASS_7 = SimpleEntrySet.builder(WoodType.class, "glass_7",
                        BdBlocks.OAK_GLASS_7, () -> VanillaWoodTypes.OAK,
                        w -> new BlockGlassBlock(Utils.copyPropertySafe(Blocks.GLASS))
                )
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "glass_7"))
                .addTag(Tags.Blocks.GLASS_BLOCKS, Registries.BLOCK)
                .setTabKey(tabBlock)
                .addTextureM(modRes("block/oak_glass_7"), EveryCompat.res("block/bdl/oak_glass_x_s_mask"),
                        PaletteStrategies.PLANKS_LOW_CONTRAST)
                .addTextureM(modRes("block/oak_glass_7_top"), EveryCompat.res("block/bdl/oak_glass_x_s_mask"),
                        PaletteStrategies.PLANKS_LOW_CONTRAST)
                // ChiselRecipe
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .build();
        this.addEntry(GLASS_7);

        GLASS_8 = SimpleEntrySet.builder(WoodType.class, "glass_8",
                        BdBlocks.OAK_GLASS_8, () -> VanillaWoodTypes.OAK,
                        w -> new BlockGlassBlock(Utils.copyPropertySafe(Blocks.GLASS))
                )
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "glass_8"))
                .addTag(Tags.Blocks.GLASS_BLOCKS, Registries.BLOCK)
                .setTabKey(tabBlock)
                .addTextureM(modRes("block/oak_glass_8"), EveryCompat.res("block/bdl/oak_glass_x_s_mask"),
                        PaletteStrategies.PLANKS_LOW_CONTRAST)
                // ChiselRecipe
                .setRenderType(RenderLayer.TRANSLUCENT)
                .build();
        this.addEntry(GLASS_8);


        //TYPE: GLASS_PANE
        GLASS_PANE_1 = SimpleEntrySet.builder(WoodType.class, "glass_pane_1",
                        BdBlocks.OAK_GLASS_PANE_1, () -> VanillaWoodTypes.OAK,
                        w -> new IronBarsBlock(Utils.copyPropertySafe(Blocks.GLASS))
                )
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "glass_pane_1"))
                .setTabKey(tabBlock)
                .addTexture(modRes("block/oak_glass_pane_1_top"))
                // Using the same texture added from GLASS_X
                .addRecipe(modRes("oak_glass_pane_1"))
                .setRenderType(RenderLayer.TRANSLUCENT)
                .build();
        this.addEntry(GLASS_PANE_1);

        GLASS_PANE_2 = SimpleEntrySet.builder(WoodType.class, "glass_pane_2",
                        BdBlocks.OAK_GLASS_PANE_2, () -> VanillaWoodTypes.OAK,
                        w -> new IronBarsBlock(Utils.copyPropertySafe(Blocks.GLASS))
                )
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "glass_pane_2"))
                .setTabKey(tabBlock)
                .addTexture(modRes("block/oak_glass_pane_2_top"))
                // Using the same texture added from GLASS_X
                .addRecipe(modRes("oak_glass_pane_2"))
                .setRenderType(RenderLayer.TRANSLUCENT)
                .build();
        this.addEntry(GLASS_PANE_2);

        GLASS_PANE_3 = SimpleEntrySet.builder(WoodType.class, "glass_pane_3",
                        BdBlocks.OAK_GLASS_PANE_3, () -> VanillaWoodTypes.OAK,
                        w -> new IronBarsBlock(Utils.copyPropertySafe(Blocks.GLASS))
                )
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "glass_pane_3"))
                .setTabKey(tabBlock)
                .addTexture(modRes("block/oak_glass_pane_3_top"))
                // Using the same texture added from GLASS_X
                .addRecipe(modRes("oak_glass_pane_3"))
                .setRenderType(RenderLayer.TRANSLUCENT)
                .build();
        this.addEntry(GLASS_PANE_3);

        GLASS_PANE_4 = SimpleEntrySet.builder(WoodType.class, "glass_pane_4",
                        BdBlocks.OAK_GLASS_PANE_4, () -> VanillaWoodTypes.OAK,
                        w -> new IronBarsBlock(Utils.copyPropertySafe(Blocks.GLASS))
                )
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "glass_pane_4"))
                .setTabKey(tabBlock)
                .addTexture(modRes("block/oak_glass_pane_4_top"))
                // Using the same texture added from GLASS_X
                .addRecipe(modRes("oak_glass_pane_4"))
                .setRenderType(RenderLayer.TRANSLUCENT)
                .build();
        this.addEntry(GLASS_PANE_4);

        GLASS_PANE_5 = SimpleEntrySet.builder(WoodType.class, "glass_pane_5",
                        BdBlocks.OAK_GLASS_PANE_5, () -> VanillaWoodTypes.OAK,
                        w -> new IronBarsBlock(Utils.copyPropertySafe(Blocks.GLASS))
                )
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "glass_pane_5"))
                .setTabKey(tabBlock)
                .addTexture(modRes("block/oak_glass_pane_5_top"))
                // Using the same texture added from GLASS_X
                .addRecipe(modRes("oak_glass_pane_5"))
                .setRenderType(RenderLayer.TRANSLUCENT)
                .build();
        this.addEntry(GLASS_PANE_5);

        GLASS_PANE_6 = SimpleEntrySet.builder(WoodType.class, "glass_pane_6",
                        BdBlocks.OAK_GLASS_PANE_6, () -> VanillaWoodTypes.OAK,
                        w -> new IronBarsBlock(Utils.copyPropertySafe(Blocks.GLASS))
                )
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "glass_pane_6"))
                .setTabKey(tabBlock)
                .addTexture(modRes("block/oak_glass_pane_6_top"))
                // Using the same texture added from GLASS_X
                .addRecipe(modRes("oak_glass_pane_6"))
                .setRenderType(RenderLayer.TRANSLUCENT)
                .build();
        this.addEntry(GLASS_PANE_6);

        GLASS_PANE_7 = SimpleEntrySet.builder(WoodType.class, "glass_pane_7",
                        BdBlocks.OAK_GLASS_PANE_7, () -> VanillaWoodTypes.OAK,
                        w -> new IronBarsBlock(Utils.copyPropertySafe(Blocks.GLASS))
                )
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "glass_pane_7"))
                .setTabKey(tabBlock)
                .addTexture(modRes("block/oak_glass_pane_7_top"))
                // Using the same texture added from GLASS_X
                .addRecipe(modRes("oak_glass_pane_7"))
                .setRenderType(RenderLayer.TRANSLUCENT)
                .build();
        this.addEntry(GLASS_PANE_7);

        GLASS_PANE_8 = SimpleEntrySet.builder(WoodType.class, "glass_pane_8",
                        BdBlocks.OAK_GLASS_PANE_8, () -> VanillaWoodTypes.OAK,
                        w -> new IronBarsBlock(Utils.copyPropertySafe(Blocks.GLASS))
                )
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "glass_pane_8"))
                .setTabKey(tabBlock)
                .addTexture(modRes("block/oak_glass_pane_8_top"))
                // Using the same texture added from GLASS_X
                .addRecipe(modRes("oak_glass_pane_8"))
                .setRenderType(RenderLayer.TRANSLUCENT)
                .build();
        this.addEntry(GLASS_PANE_8);
*/
    }

    /*private static class BDBlockItem extends BlockItem {

        private final Component tooltip;

        public BDBlockItem(Block block, Properties properties, String name) {
            super(block, properties);
            this.tooltip = Component.translatable("tooltip.everycomp.buildersdelight." + name).withStyle(ChatFormatting.GRAY);
        }

        @Override
        public void appendHoverText(@NotNull ItemStack pStack, Level pLevel, List<Component> pTooltip, @NotNull TooltipFlag pFlag) {
            pTooltip.add(tooltip);
        }
    }*/

/*    public static class CustomBdFurnitureKit extends Item {
        private final Component tooltip;

        public CustomBdFurnitureKit(Properties properties, String name) {
            super(properties);
            this.tooltip = Component.translatable("tooltip.everycomp.buildersdelight." + name).withStyle(ChatFormatting.GRAY);
        }

        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, @NotNull TooltipFlag pFlag) {
            pTooltip.add(tooltip);
        }
    }*/

/*    @Override
    public void addDynamicServerResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicServerResources(executor);

        executor.accept((manager, sink) -> {
            for (var w : WoodTypeRegistry.INSTANCE) {
                if (!HardcodedBlockType.isKnownVanillaWood(w)) {
                    addChiselRecipe(sink, w, "planks", PLANKS_1, PLANKS_2, PLANKS_3, PLANKS_4, PLANKS_5, PLANKS_6, PLANKS_7);
                    addChiselRecipe(sink, w, "stairs", STAIRS_1, STAIRS_2, STAIRS_3, STAIRS_4, STAIRS_5, STAIRS_6, STAIRS_7);
                    addChiselRecipe(sink, w, "slab", SLAB_1, SLAB_2, SLAB_3, SLAB_4, SLAB_5, SLAB_6, SLAB_7);
                    addChiselRecipe(sink, w, "frame", FRAME_1, FRAME_2, FRAME_3, FRAME_4, FRAME_5, FRAME_6, FRAME_7, FRAME_8);
                    addChiselRecipe(sink, w, "glass", GLASS_1, GLASS_2, GLASS_3, GLASS_4, GLASS_5, GLASS_6, GLASS_7, GLASS_8);
                    addChiselRecipe(sink, w, "glass_pane", GLASS_PANE_1, GLASS_PANE_2, GLASS_PANE_3, GLASS_PANE_4, GLASS_PANE_5, GLASS_PANE_6, GLASS_PANE_7, GLASS_PANE_8);

                    // Used in recipe of glass_1, frame_1, & furniture_kit
//                    planksTags(w, sink, PLANKS_1, PLANKS_2, PLANKS_3, PLANKS_4, PLANKS_5, PLANKS_6, PLANKS_7);

                    ResourceLocation resLoc = EveryCompat.res("items/" + w.getTypeName() + "_planks");
                    createAndAddCustomTags(resLoc, sink,
                            w.planks,
                            PLANKS_1.blocks.get(w),
                            PLANKS_2.blocks.get(w),
                            PLANKS_3.blocks.get(w),
                            PLANKS_4.blocks.get(w),
                            PLANKS_5.blocks.get(w),
                            PLANKS_6.blocks.get(w),
                            PLANKS_7.blocks.get(w)
                    );

                // crafting Recipe
                craftingWithTagsRecipe("glass_1", "planks", GLASS_1.items.get(w), w, sink, manager);
                craftingWithTagsRecipe("frame_1", "planks", FRAME_1.items.get(w), w, sink, manager);
            }
        }

        String recipe = """
            {
                "group": "buildersdelight",
                "type": "minecraft:crafting_shaped",
                "pattern": [
                    " 0 ",
                    \t"010",
                    \t" 0 "
                ],
                "key": {
                    "0": {
                        "item": "minecraft:string"
                    },
                    \t"1": {
                        "tag": "[planks]"
                    }
                },
                "result": {
                    "item": "[result]",
                    "count": 2
                }
            }
        """;
        for (var v : this.FURNITURE_KIT.items.entrySet()) {
            WoodType wood = v.getKey();
            String r = recipe.replace("[result]", Utils.getID(v.getValue()).toString())
                    .replace("[planks]", EveryCompat.MOD_ID + ":" + wood.getTypeName() +"_planks");

                ResourceLocation res = EveryCompat.res("bdl/" + wood.getAppendableId() + "_furniture_kit");
                sink.addBytes(res, r.getBytes(), ResType.RECIPES);
            }

        });

    }*/

    public void craftingWithTagsRecipe(String baseName, String input, Item output, WoodType wood, ResourceSink sink, ResourceManager manager) {
        // bdl/namespace/<type>_glass_1;
        String pathBuilder = this.shortenedId() + "/" + wood.getVariantId(baseName,false);

        ResourceLocation recipeLoc = modRes("recipes/oak_"+ baseName + ".json");
        JsonObject recipe;
        try (InputStream recipeStream = manager.getResource(recipeLoc).orElseThrow().open()) {
            recipe = RPUtils.deserializeJson(recipeStream);
            String inputTag = EveryCompat.MOD_ID + ":" + wood.getTypeName() +"_"+ input ;

            // VARIABLES for json
            JsonObject underKey;
            if (baseName.equals("glass_1")) {
                underKey = recipe.getAsJsonObject("key").getAsJsonObject("1");
            } else {
                underKey = recipe.getAsJsonObject("key").getAsJsonObject("0");
            }
            JsonObject underResult = recipe.getAsJsonObject("result");

            // EDITING
            underKey.addProperty("tag", inputTag);
            underResult.addProperty("item", Utils.getID(output).toString());

            sink.addJson(EveryCompat.res(pathBuilder), recipe, ResType.RECIPES);
        }
        catch (IOException ex) {
            EveryCompat.LOGGER.error("{BuildersDelight Module} TagsRecipe(): ", ex);
        }
    }

    private static void addChiselRecipe(ResourceSink sink, WoodType w, String name, AbstractSimpleEntrySet<?, ?, ?>... entries) {
        JsonArray arr = new JsonArray();
        for (var e : entries) {         // Add blocks from buildersdelight to JsonArray
            var o = e.items.get(w);
            if (o != null) {
                arr.add(Utils.getID(o).toString());
            }
        }
        var vanilla = w.getChild(name); // Add Normal blocks to JsonArray
        if(vanilla != null) arr.add(Utils.getID(vanilla).toString());
        if (!arr.isEmpty()) {
            JsonObject jo = new JsonObject();
            ResourceLocation res = EveryCompat.res("chisel/" + w.getVariantId(name) + ".json");
            jo.add("variants", arr);
            sink.addJson(res, jo, ResType.GENERIC);
        }
    }

}