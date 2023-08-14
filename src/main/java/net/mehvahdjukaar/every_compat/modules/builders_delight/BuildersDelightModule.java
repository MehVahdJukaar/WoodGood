package net.mehvahdjukaar.every_compat.modules.builders_delight;

// buildersdelight
import com.tynoxs.buildersdelight.content.block.custom.BlockSmallTable;
import com.tynoxs.buildersdelight.content.block.custom.BlockStool;
import com.tynoxs.buildersdelight.content.block.wood.BlockFlammable;
import com.tynoxs.buildersdelight.content.block.wood.SlabFlammable;
import com.tynoxs.buildersdelight.content.block.wood.StairFlammable;
import com.tynoxs.buildersdelight.content.init.BdBlocks;
import com.tynoxs.buildersdelight.content.init.BdDecoration;
import com.tynoxs.buildersdelight.content.block.custom.BlockChair;
import com.tynoxs.buildersdelight.content.init.BdTabs;

// net.mehvahdjukaar
import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.mehvahdjukaar.selene.client.asset_generators.textures.Palette;

// custom
import net.mehvahdjukaar.every_compat.modules.builders_delight.CustomGlassCT;
import net.mehvahdjukaar.every_compat.modules.builders_delight.CustomPaneCT;

// minecraft
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.common.Tags;

import java.util.function.Supplier;


public class BuildersDelightModule extends SimpleModule {

    //TYPE: DECORATION
    public final SimpleEntrySet<WoodType, Block> CHAIR_1;
    public final SimpleEntrySet<WoodType, Block> CHAIR_2;
    public final SimpleEntrySet<WoodType, Block> TABLE_1;
    public final SimpleEntrySet<WoodType, Block> TABLE_2;

    //TYPE: BLOCKS
    public final SimpleEntrySet<WoodType, Block> PLANKS_1;
    public final SimpleEntrySet<WoodType, Block> PLANKS_2;
    public final SimpleEntrySet<WoodType, Block> PLANKS_3;
    public final SimpleEntrySet<WoodType, Block> PLANKS_4;
    public final SimpleEntrySet<WoodType, Block> PLANKS_5;
    public final SimpleEntrySet<WoodType, Block> PLANKS_6;
    public final SimpleEntrySet<WoodType, Block> PLANKS_7;

    public final SimpleEntrySet<WoodType, Block> STAIRS_1;
    public final SimpleEntrySet<WoodType, Block> STAIRS_2;
    public final SimpleEntrySet<WoodType, Block> STAIRS_3;
    public final SimpleEntrySet<WoodType, Block> STAIRS_4;
    public final SimpleEntrySet<WoodType, Block> STAIRS_5;
    public final SimpleEntrySet<WoodType, Block> STAIRS_6;
    public final SimpleEntrySet<WoodType, Block> STAIRS_7;

    public final SimpleEntrySet<WoodType, Block> SLAB_1;
    public final SimpleEntrySet<WoodType, Block> SLAB_2;
    public final SimpleEntrySet<WoodType, Block> SLAB_3;
    public final SimpleEntrySet<WoodType, Block> SLAB_4;
    public final SimpleEntrySet<WoodType, Block> SLAB_5;
    public final SimpleEntrySet<WoodType, Block> SLAB_6;
    public final SimpleEntrySet<WoodType, Block> SLAB_7;

    public final SimpleEntrySet<WoodType, Block> FRAME_1;
    public final SimpleEntrySet<WoodType, Block> FRAME_2;
    public final SimpleEntrySet<WoodType, Block> FRAME_3;
    public final SimpleEntrySet<WoodType, Block> FRAME_4;
    public final SimpleEntrySet<WoodType, Block> FRAME_5;
    public final SimpleEntrySet<WoodType, Block> FRAME_6;
    public final SimpleEntrySet<WoodType, Block> FRAME_7;
    public final SimpleEntrySet<WoodType, Block> FRAME_8;

    public final SimpleEntrySet<WoodType, Block> GLASS_1;
    public final SimpleEntrySet<WoodType, Block> GLASS_2;
    public final SimpleEntrySet<WoodType, Block> GLASS_3;
    public final SimpleEntrySet<WoodType, Block> GLASS_4;
    public final SimpleEntrySet<WoodType, Block> GLASS_5;
    public final SimpleEntrySet<WoodType, Block> GLASS_6;
    public final SimpleEntrySet<WoodType, Block> GLASS_7;
    public final SimpleEntrySet<WoodType, Block> GLASS_8;

    public final SimpleEntrySet<WoodType, Block> GLASS_PANE_1;
    public final SimpleEntrySet<WoodType, Block> GLASS_PANE_2;
    public final SimpleEntrySet<WoodType, Block> GLASS_PANE_3;
    public final SimpleEntrySet<WoodType, Block> GLASS_PANE_4;
    public final SimpleEntrySet<WoodType, Block> GLASS_PANE_5;
    public final SimpleEntrySet<WoodType, Block> GLASS_PANE_6;
    public final SimpleEntrySet<WoodType, Block> GLASS_PANE_7;
    public final SimpleEntrySet<WoodType, Block> GLASS_PANE_8;


    public BuildersDelightModule(String modId) {
        super(modId, "bdl");
        CreativeModeTab TabDeco = BdTabs.TabDecoration;
        CreativeModeTab TabBlock =  BdTabs.TabBlocks;

        //TYPE: CHAIR
        CHAIR_1 = SimpleEntrySet.builder(WoodType.class, "chair_1",
                        BdDecoration.OAK_CHAIR_1, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new BlockChair(WoodGood.copySafe(w.planks))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("chair"), Registry.ITEM_REGISTRY)
                .setTab(() -> TabDeco)
                .addTexture(modRes("block/decoration/seating/oak/oak_chair_1"))
                .createPaletteFromOak(this::neutralPalette)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(CHAIR_1);

        CHAIR_2 = SimpleEntrySet.builder(WoodType.class, "chair_2",
                        BdDecoration.OAK_CHAIR_2, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new BlockStool(WoodGood.copySafe(w.planks))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("chair"), Registry.ITEM_REGISTRY)
                .addTexture(modRes("block/decoration/seating/oak/oak_chair_2"))
                .createPaletteFromOak(this::neutralPalette)
                .setTab(() -> TabDeco)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(CHAIR_2);


        //TYPE: TABLE
        TABLE_1 = SimpleEntrySet.builder(WoodType.class, "table_1",
                        BdDecoration.OAK_TABLE_1,
                        () -> WoodType.OAK_WOOD_TYPE,
                        w -> new BlockSmallTable(WoodGood.copySafe(w.planks))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("table"), Registry.ITEM_REGISTRY)
                .setTab(() -> TabDeco)
                .addTexture(modRes("block/decoration/tables/oak/oak_table_1"))
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(TABLE_1);

        TABLE_2 = SimpleEntrySet.builder(WoodType.class, "table_2",
                        BdDecoration.OAK_TABLE_2, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new BlockSmallTable(WoodGood.copySafe(w.planks))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("table"), Registry.ITEM_REGISTRY)
                .setTab(() -> TabDeco)
                .addTexture(modRes("block/decoration/tables/oak/oak_table_2"))
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(TABLE_2);


        //TYPE: PLANKS
        PLANKS_1 = SimpleEntrySet.builder(WoodType.class, "planks_1",
                        BdBlocks.OAK_PLANKS_1, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new BlockFlammable(WoodGood.copySafe(w.planks))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> TabBlock)
                .addTexture(modRes("block/oak_planks_1"))
                .createPaletteFromOak(this::neutralPalette)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(PLANKS_1);

        PLANKS_2 = SimpleEntrySet.builder(WoodType.class, "planks_2",
                        BdBlocks.OAK_PLANKS_2, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new BlockFlammable(WoodGood.copySafe(w.planks))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> TabBlock)
                .addTexture(modRes("block/oak_planks_2"))
                .createPaletteFromOak(this::neutralPalette)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(PLANKS_2);

        PLANKS_3 = SimpleEntrySet.builder(WoodType.class, "planks_3",
                        BdBlocks.OAK_PLANKS_3, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new BlockFlammable(WoodGood.copySafe(w.planks))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> TabBlock)
                .addTexture(modRes("block/oak_planks_3"))
                .createPaletteFromOak(this::neutralPalette)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(PLANKS_3);

        PLANKS_4 = SimpleEntrySet.builder(WoodType.class, "planks_4",
                        BdBlocks.OAK_PLANKS_4, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new BlockFlammable(WoodGood.copySafe(w.planks))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> TabBlock)
                .addTexture(modRes("block/oak_planks_4"))
                .createPaletteFromOak(this::neutralPalette)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(PLANKS_4);

        PLANKS_5 = SimpleEntrySet.builder(WoodType.class, "planks_5",
                        BdBlocks.OAK_PLANKS_5, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new BlockFlammable(WoodGood.copySafe(w.planks))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> TabBlock)
                .addTexture(modRes("block/oak_planks_5"))
                .createPaletteFromOak(this::neutralPalette)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(PLANKS_5);

        PLANKS_6 = SimpleEntrySet.builder(WoodType.class, "planks_6",
                        BdBlocks.OAK_PLANKS_6, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new BlockFlammable(WoodGood.copySafe(w.planks))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> TabBlock)
                .addTexture(modRes("block/oak_planks_6"))
                .createPaletteFromOak(this::neutralPalette)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(PLANKS_6);

        PLANKS_7 = SimpleEntrySet.builder(WoodType.class, "planks_7",
                        BdBlocks.OAK_PLANKS_7, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new BlockFlammable(WoodGood.copySafe(w.planks))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> TabBlock)
                .addTexture(modRes("block/oak_planks_7"))
                .createPaletteFromOak(this::neutralPalette)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(PLANKS_7);


        //TYPE: STAIRS
        STAIRS_1 = SimpleEntrySet.builder(WoodType.class, "stairs_1",
                        BdBlocks.OAK_STAIRS_1, () -> WoodType.OAK_WOOD_TYPE,
                        w -> regIfPossible(w, () -> new StairFlammable(w.getBlockOfThis("stairs").defaultBlockState(),WoodGood.copySafe(w.getBlockOfThis("stairs") )))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> TabBlock)
                .addTexture(modRes("block/oak_planks_1"))
                .createPaletteFromOak(this::neutralPalette)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(STAIRS_1);

        STAIRS_2 = SimpleEntrySet.builder(WoodType.class, "stairs_2",
                        BdBlocks.OAK_STAIRS_2, () -> WoodType.OAK_WOOD_TYPE,
                        w -> regIfPossible(w, () -> new StairFlammable(w.getBlockOfThis("stairs").defaultBlockState(),WoodGood.copySafe(w.getBlockOfThis("stairs") )))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> TabBlock)
                .addTexture(modRes("block/oak_planks_2"))
                .createPaletteFromOak(this::neutralPalette)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(STAIRS_2);

        STAIRS_3 = SimpleEntrySet.builder(WoodType.class, "stairs_3",
                        BdBlocks.OAK_STAIRS_3, () -> WoodType.OAK_WOOD_TYPE,
                        w -> regIfPossible(w, () -> new StairFlammable(w.getBlockOfThis("stairs").defaultBlockState(),WoodGood.copySafe(w.getBlockOfThis("stairs") )))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> TabBlock)
                .addTexture(modRes("block/oak_planks_3"))
                .createPaletteFromOak(this::neutralPalette)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(STAIRS_3);

        STAIRS_4 = SimpleEntrySet.builder(WoodType.class, "stairs_4",
                        BdBlocks.OAK_STAIRS_4, () -> WoodType.OAK_WOOD_TYPE,
                        w -> regIfPossible(w, () -> new StairFlammable(w.getBlockOfThis("stairs").defaultBlockState(),WoodGood.copySafe(w.getBlockOfThis("stairs") )))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> TabBlock)
                .addTexture(modRes("block/oak_planks_4"))
                .createPaletteFromOak(this::neutralPalette)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(STAIRS_4);

        STAIRS_5 = SimpleEntrySet.builder(WoodType.class, "stairs_5",
                        BdBlocks.OAK_STAIRS_5, () -> WoodType.OAK_WOOD_TYPE,
                        w -> regIfPossible(w, () -> new StairFlammable(w.getBlockOfThis("stairs").defaultBlockState(),WoodGood.copySafe(w.getBlockOfThis("stairs") )))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> TabBlock)
                .addTexture(modRes("block/oak_planks_5"))
                .createPaletteFromOak(this::neutralPalette)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(STAIRS_5);

        STAIRS_6 = SimpleEntrySet.builder(WoodType.class, "stairs_6",
                        BdBlocks.OAK_STAIRS_6, () -> WoodType.OAK_WOOD_TYPE,
                        w -> regIfPossible(w, () -> new StairFlammable(w.getBlockOfThis("stairs").defaultBlockState(),WoodGood.copySafe(w.getBlockOfThis("stairs") )))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> TabBlock)
                .addTexture(modRes("block/oak_planks_6"))
                .createPaletteFromOak(this::neutralPalette)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(STAIRS_6);

        STAIRS_7 = SimpleEntrySet.builder(WoodType.class, "stairs_7",
                        BdBlocks.OAK_STAIRS_7, () -> WoodType.OAK_WOOD_TYPE,
                        w -> regIfPossible(w, () -> new StairFlammable(w.getBlockOfThis("stairs").defaultBlockState(),WoodGood.copySafe(w.getBlockOfThis("stairs") )))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> TabBlock)
                .addTexture(modRes("block/oak_planks_7"))
                .createPaletteFromOak(this::neutralPalette)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(STAIRS_7);


        //TYPE: SLAB
        SLAB_1 = SimpleEntrySet.builder(WoodType.class, "slab_1",
                        BdBlocks.OAK_SLAB_1, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new SlabFlammable(WoodGood.copySafe(w.getBlockOfThis("slab")))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> TabBlock)
                .addTexture(modRes("block/oak_planks_1"))
                .createPaletteFromOak(this::neutralPalette)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(SLAB_1);

        SLAB_2 = SimpleEntrySet.builder(WoodType.class, "slab_2",
                        BdBlocks.OAK_SLAB_2, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new SlabFlammable(WoodGood.copySafe(w.getBlockOfThis("slab")))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> TabBlock)
                .addTexture(modRes("block/oak_planks_2"))
                .createPaletteFromOak(this::neutralPalette)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(SLAB_2);

        SLAB_3 = SimpleEntrySet.builder(WoodType.class, "slab_3",
                        BdBlocks.OAK_SLAB_3, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new SlabFlammable(WoodGood.copySafe(w.getBlockOfThis("slab")))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> TabBlock)
                .addTexture(modRes("block/oak_planks_3"))
                .createPaletteFromOak(this::neutralPalette)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(SLAB_3);

        SLAB_4 = SimpleEntrySet.builder(WoodType.class, "slab_4",
                        BdBlocks.OAK_SLAB_4, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new SlabFlammable(WoodGood.copySafe(w.getBlockOfThis("slab")))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> TabBlock)
                .addTexture(modRes("block/oak_planks_4"))
                .createPaletteFromOak(this::neutralPalette)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(SLAB_4);

        SLAB_5 = SimpleEntrySet.builder(WoodType.class, "slab_5",
                        BdBlocks.OAK_SLAB_5, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new SlabFlammable(WoodGood.copySafe(w.getBlockOfThis("slab")))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> TabBlock)
                .addTexture(modRes("block/oak_planks_5"))
                .createPaletteFromOak(this::neutralPalette)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(SLAB_5);

        SLAB_6 = SimpleEntrySet.builder(WoodType.class, "slab_6",
                        BdBlocks.OAK_SLAB_6, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new SlabFlammable(WoodGood.copySafe(w.getBlockOfThis("slab")))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> TabBlock)
                .addTexture(modRes("block/oak_planks_6"))
                .createPaletteFromOak(this::neutralPalette)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(SLAB_6);

        SLAB_7 = SimpleEntrySet.builder(WoodType.class, "slab_7",
                        BdBlocks.OAK_SLAB_7, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new SlabFlammable(WoodGood.copySafe(w.getBlockOfThis("slab")))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> TabBlock)
                .addTexture(modRes("block/oak_planks_7"))
                .createPaletteFromOak(this::neutralPalette)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(SLAB_7);


        //TYPE: FRAME
        FRAME_1 = SimpleEntrySet.builder(WoodType.class, "frame_1",
                        BdBlocks.OAK_FRAME_1, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new BlockFlammable(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> TabBlock)
                .addTexture(modRes("block/oak_frame_1"))
                .createPaletteFromOak(this::neutralPalette)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(FRAME_1);

        FRAME_2 = SimpleEntrySet.builder(WoodType.class, "frame_2",
                        BdBlocks.OAK_FRAME_2, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new BlockFlammable(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("frame"), Registry.ITEM_REGISTRY)
                .setTab(() -> TabBlock)
                .addTexture(modRes("block/oak_frame_2"))
                .createPaletteFromOak(this::neutralPalette)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(FRAME_2);

        FRAME_3 = SimpleEntrySet.builder(WoodType.class, "frame_3",
                        BdBlocks.OAK_FRAME_3, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new BlockFlammable(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("frame"), Registry.ITEM_REGISTRY)
                .setTab(() -> TabBlock)
                .addTexture(modRes("block/oak_frame_3"))
                .createPaletteFromOak(this::neutralPalette)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(FRAME_3);

        FRAME_4 = SimpleEntrySet.builder(WoodType.class, "frame_4",
                        BdBlocks.OAK_FRAME_4, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new BlockFlammable(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("frame"), Registry.ITEM_REGISTRY)
                .setTab(() -> TabBlock)
                .addTexture(modRes("block/oak_frame_4"))
                .createPaletteFromOak(this::neutralPalette)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(FRAME_4);

        FRAME_5 = SimpleEntrySet.builder(WoodType.class, "frame_5",
                        BdBlocks.OAK_FRAME_5, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new BlockFlammable(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("frame"), Registry.ITEM_REGISTRY)
                .setTab(() -> TabBlock)
                .addTexture(modRes("block/oak_frame_5"))
                .createPaletteFromOak(this::neutralPalette)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(FRAME_5);

        FRAME_6 = SimpleEntrySet.builder(WoodType.class, "frame_6",
                        BdBlocks.OAK_FRAME_6, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new BlockFlammable(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("frame"), Registry.ITEM_REGISTRY)
                .setTab(() -> TabBlock)
                .addTexture(modRes("block/oak_frame_6"))
                .createPaletteFromOak(this::neutralPalette)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(FRAME_6);

        FRAME_7 = SimpleEntrySet.builder(WoodType.class, "frame_7",
                        BdBlocks.OAK_FRAME_7, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new BlockFlammable(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("frame"), Registry.ITEM_REGISTRY)
                .setTab(() -> TabBlock)
                .addTexture(modRes("block/oak_frame_7"))
                .createPaletteFromOak(this::neutralPalette)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(FRAME_7);

        FRAME_8 = SimpleEntrySet.builder(WoodType.class, "frame_8",
                        BdBlocks.OAK_FRAME_8, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new BlockFlammable(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("frame"), Registry.ITEM_REGISTRY)
                .setTab(() -> TabBlock)
                .addTexture(modRes("block/oak_frame_8"))
                .createPaletteFromOak(this::neutralPalette)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(FRAME_8);


        //TYPE: GLASS
        GLASS_1 = SimpleEntrySet.builder(WoodType.class, "glass_1",
                        BdBlocks.OAK_GLASS_1, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new CustomGlassCT(BlockBehaviour.Properties.copy(Blocks.GLASS),"block/bdl/"+ w.getId().getNamespace()+ "/glass/"+ w.getTypeName()+ "_glass/oak_glass_1")
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(Tags.Blocks.GLASS, Registry.ITEM_REGISTRY)
                .setTab(() -> TabBlock)
                .createPaletteFromOak(this::neutralPalette)
                .addTextureM(modRes("block/glass/oak_glass/oak_glass_1"), WoodGood.res("block/bdl/oak_glass_1_mask"))
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(GLASS_1);

        GLASS_2 = SimpleEntrySet.builder(WoodType.class, "glass_2",
                        BdBlocks.OAK_GLASS_2, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new CustomGlassCT(BlockBehaviour.Properties.copy(Blocks.GLASS),"block/bdl/"+ w.getId().getNamespace()+ "/glass/"+ w.getTypeName()+ "_glass/oak_glass_2")
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(Tags.Blocks.GLASS, Registry.ITEM_REGISTRY)
                .setTab(() -> TabBlock)
                .createPaletteFromOak(this::neutralPalette)
                .addTextureM(modRes("block/glass/oak_glass/oak_glass_2"), WoodGood.res("block/bdl/oak_glass_2_mask"))
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(GLASS_2);

        GLASS_3 = SimpleEntrySet.builder(WoodType.class, "glass_3",
                        BdBlocks.OAK_GLASS_3, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new CustomGlassCT(BlockBehaviour.Properties.copy(Blocks.GLASS),"block/bdl/"+ w.getId().getNamespace()+ "/glass/"+ w.getTypeName()+ "_glass/oak_glass_3")
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(Tags.Blocks.GLASS, Registry.ITEM_REGISTRY)
                .setTab(() -> TabBlock)
                .createPaletteFromOak(this::neutralPalette)
                .addTextureM(modRes("block/glass/oak_glass/oak_glass_3"), WoodGood.res("block/bdl/oak_glass_3_mask"))
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(GLASS_3);

        GLASS_4 = SimpleEntrySet.builder(WoodType.class, "glass_4",
                        BdBlocks.OAK_GLASS_4, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new CustomGlassCT(BlockBehaviour.Properties.copy(Blocks.GLASS),"block/bdl/"+ w.getId().getNamespace()+ "/glass/"+ w.getTypeName()+ "_glass/oak_glass_4")
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(Tags.Blocks.GLASS, Registry.ITEM_REGISTRY)
                .setTab(() -> TabBlock)
                .createPaletteFromOak(this::neutralPalette)
                .addTextureM(modRes("block/glass/oak_glass/oak_glass_4"), WoodGood.res("block/bdl/oak_glass_4_mask"))
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(GLASS_4);

        GLASS_5 = SimpleEntrySet.builder(WoodType.class, "glass_5",
                        BdBlocks.OAK_GLASS_5, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new CustomGlassCT(BlockBehaviour.Properties.copy(Blocks.GLASS),"block/bdl/"+ w.getId().getNamespace()+ "/glass/"+ w.getTypeName()+ "_glass/oak_glass_5")
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(Tags.Blocks.GLASS, Registry.ITEM_REGISTRY)
                .setTab(() -> TabBlock)
                .createPaletteFromOak(this::neutralPalette)
                .addTextureM(modRes("block/glass/oak_glass/oak_glass_5"), WoodGood.res("block/bdl/oak_glass_5_mask"))
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(GLASS_5);

        GLASS_6 = SimpleEntrySet.builder(WoodType.class, "glass_6",
                        BdBlocks.OAK_GLASS_6, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new CustomGlassCT(BlockBehaviour.Properties.copy(Blocks.GLASS),"block/bdl/"+ w.getId().getNamespace()+ "/glass/"+ w.getTypeName()+ "_glass/oak_glass_6")
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(Tags.Blocks.GLASS, Registry.ITEM_REGISTRY)
                .setTab(() -> TabBlock)
                .createPaletteFromOak(this::neutralPalette)
                .addTextureM(modRes("block/glass/oak_glass/oak_glass_6"), WoodGood.res("block/bdl/oak_glass_6_mask"))
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(GLASS_6);

        GLASS_7 = SimpleEntrySet.builder(WoodType.class, "glass_7",
                        BdBlocks.OAK_GLASS_7, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new CustomGlassCT(BlockBehaviour.Properties.copy(Blocks.GLASS),"block/bdl/"+ w.getId().getNamespace()+ "/glass/"+ w.getTypeName()+ "_glass/oak_glass_7")
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(Tags.Blocks.GLASS, Registry.ITEM_REGISTRY)
                .setTab(() -> TabBlock)
                .createPaletteFromOak(this::neutralPalette)
                .addTextureM(modRes("block/glass/oak_glass/oak_glass_7"), WoodGood.res("block/bdl/oak_glass_7_mask"))
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(GLASS_7);

        GLASS_8 = SimpleEntrySet.builder(WoodType.class, "glass_8",
                        BdBlocks.OAK_GLASS_8, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new CustomGlassCT(BlockBehaviour.Properties.copy(Blocks.GLASS),"block/bdl/"+ w.getId().getNamespace()+ "/glass/"+ w.getTypeName()+ "_glass/oak_glass_8")
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(Tags.Blocks.GLASS, Registry.ITEM_REGISTRY)
                .setTab(() -> TabBlock)
                .createPaletteFromOak(this::neutralPalette)
                .addTextureM(modRes("block/glass/oak_glass/oak_glass_8"), WoodGood.res("block/bdl/oak_glass_8_mask"))
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(GLASS_8);


        //TYPE: GLASS_PANE
        GLASS_PANE_1 = SimpleEntrySet.builder(WoodType.class, "glass_pane_1",
                        BdBlocks.OAK_GLASS_PANE_1, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new CustomPaneCT(BlockBehaviour.Properties.copy(Blocks.GLASS),"block/bdl/"+ w.getId().getNamespace()+ "/glass/"+ w.getTypeName()+ "_glass/oak_glass_1")
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(Tags.Blocks.GLASS_PANES, Registry.ITEM_REGISTRY)
                .setTab(() -> TabBlock)
                // (.addTexture) Using the same texture added from GLASS_X
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(GLASS_PANE_1);

        GLASS_PANE_2 = SimpleEntrySet.builder(WoodType.class, "glass_pane_2",
                        BdBlocks.OAK_GLASS_PANE_2, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new CustomPaneCT(BlockBehaviour.Properties.copy(Blocks.GLASS), "block/bdl/"+ w.getId().getNamespace()+ "/glass/"+ w.getTypeName()+ "_glass/oak_glass_2")
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(Tags.Blocks.GLASS_PANES, Registry.ITEM_REGISTRY)
                .setTab(() -> TabBlock)
                // (.addTexture) Using the same texture added from GLASS_X
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(GLASS_PANE_2);

        GLASS_PANE_3 = SimpleEntrySet.builder(WoodType.class, "glass_pane_3",
                        BdBlocks.OAK_GLASS_PANE_3, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new CustomPaneCT(BlockBehaviour.Properties.copy(Blocks.GLASS), "block/bdl/"+ w.getId().getNamespace()+ "/glass/"+ w.getTypeName()+ "_glass/oak_glass_3")
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(Tags.Blocks.GLASS_PANES, Registry.ITEM_REGISTRY)
                .setTab(() -> TabBlock)
                // (.addTexture) Using the same texture added from GLASS_X
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(GLASS_PANE_3);

        GLASS_PANE_4 = SimpleEntrySet.builder(WoodType.class, "glass_pane_4",
                        BdBlocks.OAK_GLASS_PANE_4, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new CustomPaneCT(BlockBehaviour.Properties.copy(Blocks.GLASS),"block/bdl/"+ w.getId().getNamespace()+ "/glass/"+ w.getTypeName()+ "_glass/oak_glass_4")
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(Tags.Blocks.GLASS_PANES, Registry.ITEM_REGISTRY)
                .setTab(() -> TabBlock)
                // (.addTexture) Using the same texture added from GLASS_X
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(GLASS_PANE_4);

        GLASS_PANE_5 = SimpleEntrySet.builder(WoodType.class, "glass_pane_5",
                        BdBlocks.OAK_GLASS_PANE_5, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new CustomPaneCT(BlockBehaviour.Properties.copy(Blocks.GLASS),"block/bdl/"+ w.getId().getNamespace()+ "/glass/"+ w.getTypeName()+ "_glass/oak_glass_5")
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(Tags.Blocks.GLASS_PANES, Registry.ITEM_REGISTRY)
                .setTab(() -> TabBlock)
                // (.addTexture) Using the same texture added from GLASS_X
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(GLASS_PANE_5);

        GLASS_PANE_6 = SimpleEntrySet.builder(WoodType.class, "glass_pane_6",
                        BdBlocks.OAK_GLASS_PANE_6, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new CustomPaneCT(BlockBehaviour.Properties.copy(Blocks.GLASS),"block/bdl/"+ w.getId().getNamespace()+ "/glass/"+ w.getTypeName()+ "_glass/oak_glass_6")
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(Tags.Blocks.GLASS_PANES, Registry.ITEM_REGISTRY)
                .setTab(() -> TabBlock)
                // (.addTexture) Using the same texture added from GLASS_X
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(GLASS_PANE_6);

        GLASS_PANE_7 = SimpleEntrySet.builder(WoodType.class, "glass_pane_7",
                        BdBlocks.OAK_GLASS_PANE_7, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new CustomPaneCT(BlockBehaviour.Properties.copy(Blocks.GLASS),"block/bdl/"+ w.getId().getNamespace()+ "/glass/"+ w.getTypeName()+ "_glass/oak_glass_7")
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(Tags.Blocks.GLASS_PANES, Registry.ITEM_REGISTRY)
                .setTab(() -> TabBlock)
                // (.addTexture) Using the same texture added from GLASS_X
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(GLASS_PANE_7);

        GLASS_PANE_8 = SimpleEntrySet.builder(WoodType.class, "glass_pane_8",
                        BdBlocks.OAK_GLASS_PANE_8, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new CustomPaneCT(BlockBehaviour.Properties.copy(Blocks.GLASS),"block/bdl/"+ w.getId().getNamespace()+ "/glass/"+ w.getTypeName()+ "_glass/oak_glass_8")
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(Tags.Blocks.GLASS_PANES, Registry.ITEM_REGISTRY)
                .setTab(() -> TabBlock)
                // (.addTexture) Using the same texture added from GLASS_X
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(GLASS_PANE_8);

    }

    //TYPE: FUNCTIONS
    private void neutralPalette(Palette p) {
        p.remove(p.getLightest());
        p.remove(p.getLightest());
        p.remove(p.getDarkest());
        p.remove(p.getDarkest());
    }

    private <B extends Block> B regIfPossible(WoodType woodType, Supplier<B> supplier) {
        if (woodType.getBlockOfThis("stairs") != null) {
            return supplier.get();
        }
        return null;
    }
}
