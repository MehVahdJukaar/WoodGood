package net.mehvahdjukaar.every_compat.modules.forge.builders_delight;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.tynoxs.buildersdelight.content.block.custom.BlockChair;
import com.tynoxs.buildersdelight.content.block.custom.BlockSmallTable;
import com.tynoxs.buildersdelight.content.block.custom.BlockStool;
import com.tynoxs.buildersdelight.content.block.wood.BlockFlammable;
import com.tynoxs.buildersdelight.content.block.wood.SlabFlammable;
import com.tynoxs.buildersdelight.content.block.wood.StairFlammable;
import com.tynoxs.buildersdelight.content.init.BdBlocks;
import com.tynoxs.buildersdelight.content.init.BdDecoration;
import com.tynoxs.buildersdelight.content.init.BdItems;
import com.tynoxs.buildersdelight.content.init.BdTabs;
import com.tynoxs.buildersdelight.content.item.BdFurnitureKit;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.EntrySet;
import net.mehvahdjukaar.every_compat.api.ItemOnlyEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynamicDataPack;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.resources.textures.PaletteColor;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.moonlight.api.util.math.colors.HSVColor;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;


public class BuildersDelightModule extends SimpleModule {

    //TYPE: ITEM
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


    public BuildersDelightModule(String modId) {
        super(modId, "bdl");
        CreativeModeTab tabDeco = BdTabs.TabDecoration;
        CreativeModeTab tabBlock = BdTabs.TabBlocks;
        CreativeModeTab tabMater = BdTabs.TabMaterials;

        //TYPE: ITEM
        FURNITURE_KIT = ItemOnlyEntrySet.builder(WoodType.class, "furniture_kit",
                        BdItems.OAK_FURNITURE_KIT, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new customBdFurnitureKit(new Item.Properties().tab(tabMater).stacksTo(64), "furniture_kit")
                )
                .addTextureM(modRes("item/oak_furniture_kit"), EveryCompat.res("item/bdl/furniture_kit_mask"))
               // .addRecipe(modRes("oak_furniture_kit"))
                .createPaletteFromOak(BuildersDelightModule::extrapolateWoodItemPalette)
                .build();
        this.addEntry(FURNITURE_KIT);

        //TYPE: CHAIR
        CHAIR_1 = SimpleEntrySet.builder(WoodType.class, "chair_1",
                        BdDecoration.OAK_CHAIR_1, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BlockChair(Utils.copyPropertySafe(w.planks))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("chair"), Registry.ITEM_REGISTRY)
                .setTab(() -> tabDeco)
                .addTexture(modRes("block/decoration/seating/oak/oak_chair_1"))
                .createPaletteFromOak(this::neutralPalette)
                .addRecipe(ResourceLocation.tryParse("minecraft:oak_chair_1"))
                .setRenderType(() -> RenderType::cutout)
                .addCustomItem((woodType, block, properties) -> new BDBlockItem(block, properties, "chair_1"))
                .build();

        this.addEntry(CHAIR_1);

        CHAIR_2 = SimpleEntrySet.builder(WoodType.class, "chair_2",
                        BdDecoration.OAK_CHAIR_2, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BlockStool(Utils.copyPropertySafe(w.planks))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("chair"), Registry.ITEM_REGISTRY)
                .addTexture(modRes("block/decoration/seating/oak/oak_chair_2"))
                .createPaletteFromOak(this::neutralPalette)
                .addRecipe(ResourceLocation.tryParse("minecraft:oak_chair_2"))
                .setTab(() -> tabDeco)
                .setRenderType(() -> RenderType::cutout)
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "chair_2"))
                .build();

        this.addEntry(CHAIR_2);


        //TYPE: TABLE
        TABLE_1 = SimpleEntrySet.builder(WoodType.class, "table_1",
                        BdDecoration.OAK_TABLE_1,
                        () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BlockSmallTable(Utils.copyPropertySafe(w.planks))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("table"), Registry.ITEM_REGISTRY)
                .setTab(() -> tabDeco)
                .addTexture(modRes("block/decoration/tables/oak/oak_table_1"))
                .addRecipe(ResourceLocation.tryParse("minecraft:oak_table_1"))
                .setRenderType(() -> RenderType::cutout)
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "table_1"))
                .build();

        this.addEntry(TABLE_1);

        TABLE_2 = SimpleEntrySet.builder(WoodType.class, "table_2",
                        BdDecoration.OAK_TABLE_2, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BlockSmallTable(Utils.copyPropertySafe(w.planks))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("table"), Registry.ITEM_REGISTRY)
                .setTab(() -> tabDeco)
                .addTexture(modRes("block/decoration/tables/oak/oak_table_2"))
                .addRecipe(ResourceLocation.tryParse("minecraft:oak_table_2"))
                .setRenderType(() -> RenderType::cutout)
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "table_2"))
                .build();

        this.addEntry(TABLE_2);


        //TYPE: PLANKS
        PLANKS_1 = SimpleEntrySet.builder(WoodType.class, "planks_1",
                        BdBlocks.OAK_PLANKS_1, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BlockFlammable(Utils.copyPropertySafe(w.planks))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tabBlock)
                .addTexture(modRes("block/oak_planks_1"))
                .createPaletteFromOak(this::neutralPalette)
                .addRecipe(modRes("oak_planks_1"))
                .setRenderType(() -> RenderType::solid)
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "planks_1"))
                .build();

        this.addEntry(PLANKS_1);

        PLANKS_2 = SimpleEntrySet.builder(WoodType.class, "planks_2",
                        BdBlocks.OAK_PLANKS_2, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BlockFlammable(Utils.copyPropertySafe(w.planks))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tabBlock)
                .addTexture(modRes("block/oak_planks_2"))
                .createPaletteFromOak(this::neutralPalette)
                .addRecipe(modRes("oak_planks_2"))
                .setRenderType(() -> RenderType::solid)
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "planks_2"))
                .build();

        this.addEntry(PLANKS_2);

        PLANKS_3 = SimpleEntrySet.builder(WoodType.class, "planks_3",
                        BdBlocks.OAK_PLANKS_3, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BlockFlammable(Utils.copyPropertySafe(w.planks))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tabBlock)
                .addTexture(modRes("block/oak_planks_3"))
                .createPaletteFromOak(this::neutralPalette)
                .addRecipe(modRes("oak_planks_3"))
                .setRenderType(() -> RenderType::solid)
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "planks_3"))
                .build();

        this.addEntry(PLANKS_3);

        PLANKS_4 = SimpleEntrySet.builder(WoodType.class, "planks_4",
                        BdBlocks.OAK_PLANKS_4, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BlockFlammable(Utils.copyPropertySafe(w.planks))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tabBlock)
                .addTexture(modRes("block/oak_planks_4"))
                .createPaletteFromOak(this::neutralPalette)
                .addRecipe(modRes("oak_planks_4"))
                .setRenderType(() -> RenderType::solid)
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "planks_4"))
                .build();

        this.addEntry(PLANKS_4);

        PLANKS_5 = SimpleEntrySet.builder(WoodType.class, "planks_5",
                        BdBlocks.OAK_PLANKS_5, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BlockFlammable(Utils.copyPropertySafe(w.planks))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tabBlock)
                .addTexture(modRes("block/oak_planks_5"))
                .createPaletteFromOak(this::neutralPalette)
                .addRecipe(modRes("oak_planks_5"))
                .setRenderType(() -> RenderType::solid)
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "planks_5"))
                .build();

        this.addEntry(PLANKS_5);

        PLANKS_6 = SimpleEntrySet.builder(WoodType.class, "planks_6",
                        BdBlocks.OAK_PLANKS_6, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BlockFlammable(Utils.copyPropertySafe(w.planks))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tabBlock)
                .addTexture(modRes("block/oak_planks_6"))
                .createPaletteFromOak(this::neutralPalette)
                .addRecipe(modRes("oak_planks_6"))
                .setRenderType(() -> RenderType::solid)
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "planks_6"))
                .build();

        this.addEntry(PLANKS_6);

        PLANKS_7 = SimpleEntrySet.builder(WoodType.class, "planks_7",
                        BdBlocks.OAK_PLANKS_7, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BlockFlammable(Utils.copyPropertySafe(w.planks))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tabBlock)
                .addTexture(modRes("block/oak_planks_7"))
                .createPaletteFromOak(this::neutralPalette)
                .addRecipe(modRes("oak_planks_7"))
                .setRenderType(() -> RenderType::solid)
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "planks_7"))
                .build();

        this.addEntry(PLANKS_7);


        //TYPE: STAIRS
        STAIRS_1 = SimpleEntrySet.builder(WoodType.class, "stairs_1",
                        BdBlocks.OAK_STAIRS_1, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new StairFlammable(Blocks.OAK_STAIRS.defaultBlockState(), Utils.copyPropertySafe(w.getBlockOfThis("stairs")))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tabBlock)
                .requiresChildren("stairs")
                .addTexture(modRes("block/oak_planks_1"))
                .createPaletteFromOak(this::neutralPalette)
                .addRecipe(modRes("oak_stairs_1"))
                .setRenderType(() -> RenderType::cutout)
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "stairs_1"))
                .build();

        this.addEntry(STAIRS_1);

        STAIRS_2 = SimpleEntrySet.builder(WoodType.class, "stairs_2",
                        BdBlocks.OAK_STAIRS_2, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new StairFlammable(Blocks.OAK_STAIRS.defaultBlockState(), Utils.copyPropertySafe(w.getBlockOfThis("stairs")))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tabBlock)
                .requiresChildren("stairs")
                .addTexture(modRes("block/oak_planks_2"))
                .createPaletteFromOak(this::neutralPalette)
                .addRecipe(modRes("oak_stairs_2"))
                .setRenderType(() -> RenderType::cutout)
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "stairs_2"))
                .build();

        this.addEntry(STAIRS_2);

        STAIRS_3 = SimpleEntrySet.builder(WoodType.class, "stairs_3",
                        BdBlocks.OAK_STAIRS_3, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new StairFlammable(Blocks.OAK_STAIRS.defaultBlockState(), Utils.copyPropertySafe(w.getBlockOfThis("stairs")))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tabBlock)
                .requiresChildren("stairs")
                .addTexture(modRes("block/oak_planks_3"))
                .createPaletteFromOak(this::neutralPalette)
                .addRecipe(modRes("oak_stairs_3"))
                .setRenderType(() -> RenderType::cutout)
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "stairs_3"))
                .build();

        this.addEntry(STAIRS_3);

        STAIRS_4 = SimpleEntrySet.builder(WoodType.class, "stairs_4",
                        BdBlocks.OAK_STAIRS_4, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new StairFlammable(Blocks.OAK_STAIRS.defaultBlockState(), Utils.copyPropertySafe(w.getBlockOfThis("stairs")))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tabBlock)
                .requiresChildren("stairs")
                .addTexture(modRes("block/oak_planks_4"))
                .createPaletteFromOak(this::neutralPalette)
                .addRecipe(modRes("oak_stairs_4"))
                .setRenderType(() -> RenderType::cutout)
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "stairs_4"))
                .build();

        this.addEntry(STAIRS_4);

        STAIRS_5 = SimpleEntrySet.builder(WoodType.class, "stairs_5",
                        BdBlocks.OAK_STAIRS_5, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new StairFlammable(Blocks.OAK_STAIRS.defaultBlockState(), Utils.copyPropertySafe(w.getBlockOfThis("stairs")))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tabBlock)
                .requiresChildren("stairs")
                .addTexture(modRes("block/oak_planks_5"))
                .createPaletteFromOak(this::neutralPalette)
                .addRecipe(modRes("oak_stairs_5"))
                .setRenderType(() -> RenderType::cutout)
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "stairs_5"))
                .build();

        this.addEntry(STAIRS_5);

        STAIRS_6 = SimpleEntrySet.builder(WoodType.class, "stairs_6",
                        BdBlocks.OAK_STAIRS_6, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new StairFlammable(Blocks.OAK_STAIRS.defaultBlockState(), Utils.copyPropertySafe(w.getBlockOfThis("stairs")))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tabBlock)
                .requiresChildren("stairs")
                .addTexture(modRes("block/oak_planks_6"))
                .createPaletteFromOak(this::neutralPalette)
                .addRecipe(modRes("oak_stairs_6"))
                .setRenderType(() -> RenderType::cutout)
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "stairs_6"))
                .build();

        this.addEntry(STAIRS_6);

        STAIRS_7 = SimpleEntrySet.builder(WoodType.class, "stairs_7",
                        BdBlocks.OAK_STAIRS_7, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new StairFlammable(Blocks.OAK_STAIRS.defaultBlockState(), Utils.copyPropertySafe(w.getBlockOfThis("stairs")))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tabBlock)
                .requiresChildren("stairs")
                .addTexture(modRes("block/oak_planks_7"))
                .createPaletteFromOak(this::neutralPalette)
                .addRecipe(modRes("oak_stairs_7"))
                .setRenderType(() -> RenderType::cutout)
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "stairs_7"))
                .build();

        this.addEntry(STAIRS_7);


        //TYPE: SLAB
        SLAB_1 = SimpleEntrySet.builder(WoodType.class, "slab_1",
                        BdBlocks.OAK_SLAB_1, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new SlabFlammable(Utils.copyPropertySafe(w.getBlockOfThis("slab")))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .requiresChildren("slab")
                .setTab(() -> tabBlock)
                .addTexture(modRes("block/oak_planks_1"))
                .createPaletteFromOak(this::neutralPalette)
                .addRecipe(modRes("oak_slab_1"))
                .setRenderType(() -> RenderType::cutout)
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "slab_1"))
                .build();

        this.addEntry(SLAB_1);

        SLAB_2 = SimpleEntrySet.builder(WoodType.class, "slab_2",
                        BdBlocks.OAK_SLAB_2, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new SlabFlammable(Utils.copyPropertySafe(w.getBlockOfThis("slab")))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tabBlock)
                .requiresChildren("slab")
                .addTexture(modRes("block/oak_planks_2"))
                .createPaletteFromOak(this::neutralPalette)
                .addRecipe(modRes("oak_slab_2"))
                .setRenderType(() -> RenderType::cutout)
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "slab_2"))
                .build();

        this.addEntry(SLAB_2);

        SLAB_3 = SimpleEntrySet.builder(WoodType.class, "slab_3",
                        BdBlocks.OAK_SLAB_3, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new SlabFlammable(Utils.copyPropertySafe(w.getBlockOfThis("slab")))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tabBlock)
                .requiresChildren("slab")
                .addTexture(modRes("block/oak_planks_3"))
                .createPaletteFromOak(this::neutralPalette)
                .addRecipe(modRes("oak_slab_3"))
                .setRenderType(() -> RenderType::cutout)
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "slab_3"))
                .build();

        this.addEntry(SLAB_3);

        SLAB_4 = SimpleEntrySet.builder(WoodType.class, "slab_4",
                        BdBlocks.OAK_SLAB_4, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new SlabFlammable(Utils.copyPropertySafe(w.getBlockOfThis("slab")))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tabBlock)
                .requiresChildren("slab")
                .addTexture(modRes("block/oak_planks_4"))
                .createPaletteFromOak(this::neutralPalette)
                .addRecipe(modRes("oak_slab_4"))
                .setRenderType(() -> RenderType::cutout)
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "slab_4"))
                .build();

        this.addEntry(SLAB_4);

        SLAB_5 = SimpleEntrySet.builder(WoodType.class, "slab_5",
                        BdBlocks.OAK_SLAB_5, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new SlabFlammable(Utils.copyPropertySafe(w.getBlockOfThis("slab")))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tabBlock)
                .requiresChildren("slab")
                .addTexture(modRes("block/oak_planks_5"))
                .createPaletteFromOak(this::neutralPalette)
                .addRecipe(modRes("oak_slab_5"))
                .setRenderType(() -> RenderType::cutout)
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "slab_5"))
                .build();

        this.addEntry(SLAB_5);

        SLAB_6 = SimpleEntrySet.builder(WoodType.class, "slab_6",
                        BdBlocks.OAK_SLAB_6, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new SlabFlammable(Utils.copyPropertySafe(w.getBlockOfThis("slab")))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tabBlock)
                .requiresChildren("slab")
                .addTexture(modRes("block/oak_planks_6"))
                .createPaletteFromOak(this::neutralPalette)
                .addRecipe(modRes("oak_slab_6"))
                .setRenderType(() -> RenderType::cutout)
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "slab_6"))
                .build();

        this.addEntry(SLAB_6);

        SLAB_7 = SimpleEntrySet.builder(WoodType.class, "slab_7",
                        BdBlocks.OAK_SLAB_7, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new SlabFlammable(Utils.copyPropertySafe(w.getBlockOfThis("slab")))
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tabBlock)
                .requiresChildren("slab")
                .addTexture(modRes("block/oak_planks_7"))
                .createPaletteFromOak(this::neutralPalette)
                .addRecipe(modRes("oak_slab_7"))
                .setRenderType(() -> RenderType::cutout)
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "slab_7"))
                .build();

        this.addEntry(SLAB_7);


        //TYPE: FRAME
        FRAME_1 = SimpleEntrySet.builder(WoodType.class, "frame_1",
                        BdBlocks.OAK_FRAME_1, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BlockFlammable(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL))
                )
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "frame_1"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> tabBlock)
                .addTexture(modRes("block/oak_frame_1"))
                .createPaletteFromOak(this::neutralPalette)
                .addRecipe(modRes("oak_frame_1"))
                .setRenderType(() -> RenderType::solid)
                .build();

        this.addEntry(FRAME_1);

        FRAME_2 = SimpleEntrySet.builder(WoodType.class, "frame_2",
                        BdBlocks.OAK_FRAME_2, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BlockFlammable(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL))
                )
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "frame_2"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("frame"), Registry.ITEM_REGISTRY)
                .setTab(() -> tabBlock)
                .addTexture(modRes("block/oak_frame_2"))
                .createPaletteFromOak(this::neutralPalette)
                .addRecipe(modRes("oak_frame_1"))
                .setRenderType(() -> RenderType::solid)
                .build();

        this.addEntry(FRAME_2);

        FRAME_3 = SimpleEntrySet.builder(WoodType.class, "frame_3",
                        BdBlocks.OAK_FRAME_3, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BlockFlammable(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL))
                )
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "frame_3"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("frame"), Registry.ITEM_REGISTRY)
                .setTab(() -> tabBlock)
                .addTexture(modRes("block/oak_frame_3"))
                .createPaletteFromOak(this::neutralPalette)
                .addRecipe(modRes("oak_frame_1"))
                .setRenderType(() -> RenderType::solid)
                .build();

        this.addEntry(FRAME_3);

        FRAME_4 = SimpleEntrySet.builder(WoodType.class, "frame_4",
                        BdBlocks.OAK_FRAME_4, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BlockFlammable(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL))
                )
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "frame_4"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("frame"), Registry.ITEM_REGISTRY)
                .setTab(() -> tabBlock)
                .addTexture(modRes("block/oak_frame_4"))
                .createPaletteFromOak(this::neutralPalette)
                .addRecipe(modRes("oak_frame_1"))
                .setRenderType(() -> RenderType::solid)
                .build();

        this.addEntry(FRAME_4);

        FRAME_5 = SimpleEntrySet.builder(WoodType.class, "frame_5",
                        BdBlocks.OAK_FRAME_5, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BlockFlammable(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL))
                )
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "frame_5"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("frame"), Registry.ITEM_REGISTRY)
                .setTab(() -> tabBlock)
                .addTexture(modRes("block/oak_frame_5"))
                .createPaletteFromOak(this::neutralPalette)
                .addRecipe(modRes("oak_frame_1"))
                .setRenderType(() -> RenderType::solid)
                .build();

        this.addEntry(FRAME_5);

        FRAME_6 = SimpleEntrySet.builder(WoodType.class, "frame_6",
                        BdBlocks.OAK_FRAME_6, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BlockFlammable(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL))
                )
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "frame_6"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("frame"), Registry.ITEM_REGISTRY)
                .setTab(() -> tabBlock)
                .addTexture(modRes("block/oak_frame_6"))
                .createPaletteFromOak(this::neutralPalette)
                .addRecipe(modRes("oak_frame_1"))
                .setRenderType(() -> RenderType::solid)
                .build();

        this.addEntry(FRAME_6);

        FRAME_7 = SimpleEntrySet.builder(WoodType.class, "frame_7",
                        BdBlocks.OAK_FRAME_7, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BlockFlammable(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL))
                )
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "frame_7"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("frame"), Registry.ITEM_REGISTRY)
                .setTab(() -> tabBlock)
                .addTexture(modRes("block/oak_frame_7"))
                .createPaletteFromOak(this::neutralPalette)
                .addRecipe(modRes("oak_frame_1"))
                .setRenderType(() -> RenderType::solid)
                .build();

        this.addEntry(FRAME_7);

        FRAME_8 = SimpleEntrySet.builder(WoodType.class, "frame_8",
                        BdBlocks.OAK_FRAME_8, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BlockFlammable(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL))
                )
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "frame_8"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("frame"), Registry.ITEM_REGISTRY)
                .setTab(() -> tabBlock)
                .addTexture(modRes("block/oak_frame_8"))
                .createPaletteFromOak(this::neutralPalette)
                .addRecipe(modRes("oak_frame_1"))
                .setRenderType(() -> RenderType::solid)
                .build();

        this.addEntry(FRAME_8);


        //TYPE: GLASS
        GLASS_1 = SimpleEntrySet.builder(WoodType.class, "glass_1",
                        BdBlocks.OAK_GLASS_1, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CustomGlassCT(BlockBehaviour.Properties.copy(Blocks.GLASS), "block/bdl/" + w.getId().getNamespace() + "/glass/" + w.getTypeName() + "_glass/oak_glass_1")
                )
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "glass_1"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(Tags.Blocks.GLASS, Registry.ITEM_REGISTRY)
                .setTab(() -> tabBlock)
                .createPaletteFromOak(this::neutralPalette)
                .addTextureM(modRes("block/glass/oak_glass/oak_glass_1"), EveryCompat.res("block/bdl/oak_glass_1_mask"))
                .addRecipe(modRes("oak_glass_1"))
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(GLASS_1);

        GLASS_2 = SimpleEntrySet.builder(WoodType.class, "glass_2",
                        BdBlocks.OAK_GLASS_2, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CustomGlassCT(BlockBehaviour.Properties.copy(Blocks.GLASS), "block/bdl/" + w.getId().getNamespace() + "/glass/" + w.getTypeName() + "_glass/oak_glass_2")
                )
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "glass_2"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(Tags.Blocks.GLASS, Registry.ITEM_REGISTRY)
                .setTab(() -> tabBlock)
                .createPaletteFromOak(this::neutralPalette)
                .addTextureM(modRes("block/glass/oak_glass/oak_glass_2"), EveryCompat.res("block/bdl/oak_glass_2_mask"))
                .addRecipe(modRes("oak_glass_1"))
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(GLASS_2);

        GLASS_3 = SimpleEntrySet.builder(WoodType.class, "glass_3",
                        BdBlocks.OAK_GLASS_3, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CustomGlassCT(BlockBehaviour.Properties.copy(Blocks.GLASS), "block/bdl/" + w.getId().getNamespace() + "/glass/" + w.getTypeName() + "_glass/oak_glass_3")
                )
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "glass_3"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(Tags.Blocks.GLASS, Registry.ITEM_REGISTRY)
                .setTab(() -> tabBlock)
                .createPaletteFromOak(this::neutralPalette)
                .addTextureM(modRes("block/glass/oak_glass/oak_glass_3"), EveryCompat.res("block/bdl/oak_glass_3_mask"))
                .addRecipe(modRes("oak_glass_1"))
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(GLASS_3);

        GLASS_4 = SimpleEntrySet.builder(WoodType.class, "glass_4",
                        BdBlocks.OAK_GLASS_4, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CustomGlassCT(BlockBehaviour.Properties.copy(Blocks.GLASS), "block/bdl/" + w.getId().getNamespace() + "/glass/" + w.getTypeName() + "_glass/oak_glass_4")
                )
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "glass_4"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(Tags.Blocks.GLASS, Registry.ITEM_REGISTRY)
                .setTab(() -> tabBlock)
                .createPaletteFromOak(this::neutralPalette)
                .addTextureM(modRes("block/glass/oak_glass/oak_glass_4"), EveryCompat.res("block/bdl/oak_glass_4_mask"))
                .addRecipe(modRes("oak_glass_1"))
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(GLASS_4);

        GLASS_5 = SimpleEntrySet.builder(WoodType.class, "glass_5",
                        BdBlocks.OAK_GLASS_5, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CustomGlassCT(BlockBehaviour.Properties.copy(Blocks.GLASS), "block/bdl/" + w.getId().getNamespace() + "/glass/" + w.getTypeName() + "_glass/oak_glass_5")
                )
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "glass_5"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(Tags.Blocks.GLASS, Registry.ITEM_REGISTRY)
                .setTab(() -> tabBlock)
                .createPaletteFromOak(this::neutralPalette)
                .addTextureM(modRes("block/glass/oak_glass/oak_glass_5"), EveryCompat.res("block/bdl/oak_glass_5_mask"))
                .addRecipe(modRes("oak_glass_1"))
                .setRenderType(() -> RenderType::translucent)
                .build();

        this.addEntry(GLASS_5);

        GLASS_6 = SimpleEntrySet.builder(WoodType.class, "glass_6",
                        BdBlocks.OAK_GLASS_6, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CustomGlassCT(BlockBehaviour.Properties.copy(Blocks.GLASS), "block/bdl/" + w.getId().getNamespace() + "/glass/" + w.getTypeName() + "_glass/oak_glass_6")
                )
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "glass_6"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(Tags.Blocks.GLASS, Registry.ITEM_REGISTRY)
                .setTab(() -> tabBlock)
                .createPaletteFromOak(this::neutralPalette)
                .addTextureM(modRes("block/glass/oak_glass/oak_glass_6"), EveryCompat.res("block/bdl/oak_glass_6_mask"))
                .addRecipe(modRes("oak_glass_1"))
                .setRenderType(() -> RenderType::translucent)
                .build();

        this.addEntry(GLASS_6);

        GLASS_7 = SimpleEntrySet.builder(WoodType.class, "glass_7",
                        BdBlocks.OAK_GLASS_7, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CustomGlassCT(BlockBehaviour.Properties.copy(Blocks.GLASS), "block/bdl/" + w.getId().getNamespace() + "/glass/" + w.getTypeName() + "_glass/oak_glass_7")
                )
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "glass_7"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(Tags.Blocks.GLASS, Registry.ITEM_REGISTRY)
                .setTab(() -> tabBlock)
                .createPaletteFromOak(this::neutralPalette)
                .addTextureM(modRes("block/glass/oak_glass/oak_glass_7"), EveryCompat.res("block/bdl/oak_glass_7_mask"))
                .addRecipe(modRes("oak_glass_1"))
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(GLASS_7);

        GLASS_8 = SimpleEntrySet.builder(WoodType.class, "glass_8",
                        BdBlocks.OAK_GLASS_8, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CustomGlassCT(BlockBehaviour.Properties.copy(Blocks.GLASS), "block/bdl/" + w.getId().getNamespace() + "/glass/" + w.getTypeName() + "_glass/oak_glass_8")
                )
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "glass_8"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(Tags.Blocks.GLASS, Registry.ITEM_REGISTRY)
                .setTab(() -> tabBlock)
                .createPaletteFromOak(this::neutralPalette)
                .addTextureM(modRes("block/glass/oak_glass/oak_glass_8"), EveryCompat.res("block/bdl/oak_glass_8_mask"))
                .addRecipe(modRes("oak_glass_1"))
                .setRenderType(() -> RenderType::translucent)
                .build();

        this.addEntry(GLASS_8);


        //TYPE: GLASS_PANE
        GLASS_PANE_1 = SimpleEntrySet.builder(WoodType.class, "glass_pane_1",
                        BdBlocks.OAK_GLASS_PANE_1, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CustomPaneCT(BlockBehaviour.Properties.copy(Blocks.GLASS), "block/bdl/" + w.getId().getNamespace() + "/glass/" + w.getTypeName() + "_glass/oak_glass_1")
                )
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "glass_pane_1"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(Tags.Blocks.GLASS_PANES, Registry.ITEM_REGISTRY)
                .setTab(() -> tabBlock)
                // (.addTexture) Using the same texture added from GLASS_X
                .addRecipe(modRes("oak_glass_pane_1"))
                .setRenderType(() -> RenderType::translucent)
                .build();

        this.addEntry(GLASS_PANE_1);

        GLASS_PANE_2 = SimpleEntrySet.builder(WoodType.class, "glass_pane_2",
                        BdBlocks.OAK_GLASS_PANE_2, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CustomPaneCT(BlockBehaviour.Properties.copy(Blocks.GLASS), "block/bdl/" + w.getId().getNamespace() + "/glass/" + w.getTypeName() + "_glass/oak_glass_2")
                )
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "glass_pane_2"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(Tags.Blocks.GLASS_PANES, Registry.ITEM_REGISTRY)
                .setTab(() -> tabBlock)
                // (.addTexture) Using the same texture added from GLASS_X
                .addRecipe(modRes("oak_glass_pane_2"))
                .setRenderType(() -> RenderType::translucent)
                .build();

        this.addEntry(GLASS_PANE_2);

        GLASS_PANE_3 = SimpleEntrySet.builder(WoodType.class, "glass_pane_3",
                        BdBlocks.OAK_GLASS_PANE_3, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CustomPaneCT(BlockBehaviour.Properties.copy(Blocks.GLASS), "block/bdl/" + w.getId().getNamespace() + "/glass/" + w.getTypeName() + "_glass/oak_glass_3")
                )
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "glass_pane_3"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(Tags.Blocks.GLASS_PANES, Registry.ITEM_REGISTRY)
                .setTab(() -> tabBlock)
                // (.addTexture) Using the same texture added from GLASS_X
                .addRecipe(modRes("oak_glass_pane_3"))
                .setRenderType(() -> RenderType::translucent)
                .build();

        this.addEntry(GLASS_PANE_3);

        GLASS_PANE_4 = SimpleEntrySet.builder(WoodType.class, "glass_pane_4",
                        BdBlocks.OAK_GLASS_PANE_4, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CustomPaneCT(BlockBehaviour.Properties.copy(Blocks.GLASS), "block/bdl/" + w.getId().getNamespace() + "/glass/" + w.getTypeName() + "_glass/oak_glass_4")
                )
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "glass_pane_4"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(Tags.Blocks.GLASS_PANES, Registry.ITEM_REGISTRY)
                .setTab(() -> tabBlock)
                // (.addTexture) Using the same texture added from GLASS_X
                .addRecipe(modRes("oak_glass_pane_4"))
                .setRenderType(() -> RenderType::translucent)
                .build();

        this.addEntry(GLASS_PANE_4);

        GLASS_PANE_5 = SimpleEntrySet.builder(WoodType.class, "glass_pane_5",
                        BdBlocks.OAK_GLASS_PANE_5, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CustomPaneCT(BlockBehaviour.Properties.copy(Blocks.GLASS), "block/bdl/" + w.getId().getNamespace() + "/glass/" + w.getTypeName() + "_glass/oak_glass_5")
                )
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "glass_pane_5"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(Tags.Blocks.GLASS_PANES, Registry.ITEM_REGISTRY)
                .setTab(() -> tabBlock)
                // (.addTexture) Using the same texture added from GLASS_X
                .addRecipe(modRes("oak_glass_pane_5"))
                .setRenderType(() -> RenderType::translucent)
                .build();

        this.addEntry(GLASS_PANE_5);

        GLASS_PANE_6 = SimpleEntrySet.builder(WoodType.class, "glass_pane_6",
                        BdBlocks.OAK_GLASS_PANE_6, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CustomPaneCT(BlockBehaviour.Properties.copy(Blocks.GLASS), "block/bdl/" + w.getId().getNamespace() + "/glass/" + w.getTypeName() + "_glass/oak_glass_6")
                )
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "glass_pane_6"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(Tags.Blocks.GLASS_PANES, Registry.ITEM_REGISTRY)
                .setTab(() -> tabBlock)
                // (.addTexture) Using the same texture added from GLASS_X
                .addRecipe(modRes("oak_glass_pane_6"))
                .setRenderType(() -> RenderType::translucent)
                .build();

        this.addEntry(GLASS_PANE_6);

        GLASS_PANE_7 = SimpleEntrySet.builder(WoodType.class, "glass_pane_7",
                        BdBlocks.OAK_GLASS_PANE_7, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CustomPaneCT(BlockBehaviour.Properties.copy(Blocks.GLASS), "block/bdl/" + w.getId().getNamespace() + "/glass/" + w.getTypeName() + "_glass/oak_glass_7")
                )
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "glass_pane_7"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(Tags.Blocks.GLASS_PANES, Registry.ITEM_REGISTRY)
                .setTab(() -> tabBlock)
                // (.addTexture) Using the same texture added from GLASS_X
                .addRecipe(modRes("oak_glass_pane_7"))
                .setRenderType(() -> RenderType::translucent)
                .build();

        this.addEntry(GLASS_PANE_7);

        GLASS_PANE_8 = SimpleEntrySet.builder(WoodType.class, "glass_pane_8",
                        BdBlocks.OAK_GLASS_PANE_8, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CustomPaneCT(BlockBehaviour.Properties.copy(Blocks.GLASS), "block/bdl/" + w.getId().getNamespace() + "/glass/" + w.getTypeName() + "_glass/oak_glass_8")
                )
                .addCustomItem((w, b, p) -> new BDBlockItem(b, p, "glass_pane_8"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(Tags.Blocks.GLASS_PANES, Registry.ITEM_REGISTRY)
                .setTab(() -> tabBlock)
                // (.addTexture) Using the same texture added from GLASS_X
                .addRecipe(modRes("oak_glass_pane_8"))
                .setRenderType(() -> RenderType::translucent)
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

    private static class BDBlockItem extends BlockItem {

        private final Component tooltip;

        public BDBlockItem(Block block, Properties properties, String name) {
            super(block, properties);
            this.tooltip = Component.translatable("tooltip.everycomp.buildersdelight." + name).withStyle(ChatFormatting.GRAY);
        }

        @Override
        public void appendHoverText(ItemStack pStack, Level pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
            pTooltip.add(tooltip);
        }
    }

    public static class customBdFurnitureKit extends BdFurnitureKit {
        private final Component tooltip;

        public customBdFurnitureKit(Properties properties, String name) {
            super(properties);
            this.tooltip = Component.translatable("tooltip.everycomp.buildersdelight." + name).withStyle(ChatFormatting.GRAY);
        }

        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, @NotNull TooltipFlag pFlag) {
            pTooltip.add(tooltip);
        }
    }

    @Override
    public void addDynamicServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicServerResources(handler, manager);
        var pack = handler.getPack();
        for (var w : WoodTypeRegistry.getTypes()) {
            if (!w.isVanilla()) {
                addChiselRecipe(pack, w, "planks", PLANKS_1, PLANKS_2, PLANKS_3, PLANKS_4, PLANKS_5, PLANKS_6, PLANKS_7);
                addChiselRecipe(pack, w, "stairs", STAIRS_1, STAIRS_2, STAIRS_3, STAIRS_4, STAIRS_5, STAIRS_6, STAIRS_7);
                addChiselRecipe(pack, w, "slab", SLAB_1, SLAB_2, SLAB_3, SLAB_4, SLAB_5, SLAB_6, SLAB_7);
                addChiselRecipe(pack, w, "frame", FRAME_1, FRAME_2, FRAME_3, FRAME_4, FRAME_5, FRAME_6, FRAME_7, FRAME_8);
                addChiselRecipe(pack, w, "glass", GLASS_1, GLASS_2, GLASS_3, GLASS_4, GLASS_5, GLASS_6, GLASS_7, GLASS_8);
                addChiselRecipe(pack, w, "glass_pane", GLASS_PANE_1, GLASS_PANE_2, GLASS_PANE_3, GLASS_PANE_4, GLASS_PANE_5, GLASS_PANE_6, GLASS_PANE_7, GLASS_PANE_8);
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
                          "item": "[planks]"
                        }
                      },
                      "result": {
                        "item": "[result]",
                        "count": 2
                      }
                    }""";
        for (var v : this.FURNITURE_KIT.items.entrySet()) {

            WoodType wood = v.getKey();
            String r = recipe.replace("[result]", Utils.getID(v.getValue()).toString())
                    .replace("[planks]", Utils.getID(wood.planks).toString());

            ResourceLocation res = EveryCompat.res("bdl/" + wood.getAppendableId() + "_furniture_kit");
            pack.addBytes(res, r.getBytes(), ResType.RECIPES);
        }
    }


    private static void addChiselRecipe(DynamicDataPack pack, WoodType w, String name, EntrySet<?, ?, ?>... entries) {
        JsonArray arr = new JsonArray();
        for (var e : entries) {         // Add blocks from buildersdelight to JsonArray
            var o = e.items.get(w);
            if (o != null) {
                arr.add(Utils.getID(o).toString());
            }
        }
        var vanilla = w.getChild(name); // Add Normal blocks to JsonArray
        if (vanilla != null) arr.add(Utils.getID(vanilla).toString());

        if (!arr.isEmpty()) {
            JsonObject jo = new JsonObject();
            ResourceLocation res = EveryCompat.res("chisel/" + w.getVariantId(name) + ".json");
            jo.add("variants", arr);
            pack.addJson(res, jo, ResType.GENERIC);
        }
    }

    // Improved the texture of FURNITURE_KIT
    @Deprecated(forRemoval = true, since = "1.20")
    public static Palette extrapolateWoodItemPalette(Palette palette) {
        PaletteColor color = palette.get(0);
        HSVColor hsv = color.rgb().asHSV();
        float satMult = 1.11F;
        float brightnessMult = 0.94F;
        HSVColor newCol = new HSVColor(hsv.hue(), Mth.clamp(hsv.saturation() * satMult, 0.0F, 1.0F), Mth.clamp(hsv.value() * brightnessMult, 0.0F, 1.0F), hsv.alpha());
        PaletteColor newP = new PaletteColor(newCol);
        newP.occurrence = color.occurrence;
        palette.set(0, newP);
        return palette;
    }
}
