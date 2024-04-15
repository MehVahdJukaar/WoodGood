package net.mehvahdjukaar.every_compat.modules.builders_delight;


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
import com.tynoxs.buildersdelight.content.init.BdTabs;
import com.tynoxs.buildersdelight.content.item.BdFurnitureKit;
import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.every_compat.api.EntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.mehvahdjukaar.selene.block_set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.selene.client.asset_generators.LangBuilder;
import net.mehvahdjukaar.selene.client.asset_generators.textures.Palette;
import net.mehvahdjukaar.selene.client.asset_generators.textures.Respriter;
import net.mehvahdjukaar.selene.client.asset_generators.textures.SpriteUtils;
import net.mehvahdjukaar.selene.client.asset_generators.textures.TextureImage;
import net.mehvahdjukaar.selene.resourcepack.AfterLanguageLoadEvent;
import net.mehvahdjukaar.selene.resourcepack.DynamicDataPack;
import net.mehvahdjukaar.selene.resourcepack.RPUtils;
import net.mehvahdjukaar.selene.resourcepack.ResType;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.IForgeRegistry;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;


//SUPPORT v1.0+
public class BuildersDelightModule extends SimpleModule {

    //TYPE: ITEM
    public static final Map<WoodType, Item> FURNITURE_KIT = new HashMap<>();

    //TYPE: DECORATION
    public final SimpleEntrySet<WoodType, Block> CHAIR_1, CHAIR_2;
    public final SimpleEntrySet<WoodType, Block> TABLE_1, TABLE_2;

    //TYPE: BLOCKS
    public final SimpleEntrySet<WoodType, Block> PLANKS_1,  PLANKS_2,  PLANKS_3,  PLANKS_4,  PLANKS_5,  PLANKS_6,  PLANKS_7;

    public final SimpleEntrySet<WoodType, Block> STAIRS_1, STAIRS_2, STAIRS_3, STAIRS_4, STAIRS_5, STAIRS_6, STAIRS_7;

    public final SimpleEntrySet<WoodType, Block> SLAB_1, SLAB_2, SLAB_3, SLAB_4, SLAB_5, SLAB_6, SLAB_7;

    public final SimpleEntrySet<WoodType, Block> FRAME_1, FRAME_2, FRAME_3, FRAME_4, FRAME_5, FRAME_6, FRAME_7, FRAME_8;

    public final SimpleEntrySet<WoodType, Block> GLASS_1, GLASS_2, GLASS_3, GLASS_4, GLASS_5, GLASS_6, GLASS_7, GLASS_8;

    public final SimpleEntrySet<WoodType, Block> GLASS_PANE_1, GLASS_PANE_2, GLASS_PANE_3, GLASS_PANE_4, GLASS_PANE_5, GLASS_PANE_6, GLASS_PANE_7, GLASS_PANE_8;

    @SuppressWarnings("ConstantConditions")
    public BuildersDelightModule(String modId) {
        super(modId, "bdl");
        CreativeModeTab TabBlock =  BdTabs.TabBlocks;
        CreativeModeTab TabDeco = BdTabs.TabDecoration;

        //TYPE: CHAIR
        CHAIR_1 = SimpleEntrySet.builder(WoodType.class, "chair_1",
                        BdDecoration.OAK_CHAIR_1, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new BlockChair(WoodGood.copySafe(w.planks))
                )
                .addCustomItem((w, b, p) -> new customBdBlockItem(b, p, "chair_1"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                //.addTag(modRes("chair"), Registry.ITEM_REGISTRY)
                .setTab(() -> TabDeco)
                .addTexture(modRes("block/decoration/seating/oak/oak_chair_1"))
                .createPaletteFromOak(this::neutralPalette)
                .setRenderType(() -> RenderType::cutout)
                .build();
        this.addEntry(CHAIR_1);

        CHAIR_2 = SimpleEntrySet.builder(WoodType.class, "chair_2",
                        BdDecoration.OAK_CHAIR_2, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new BlockStool(WoodGood.copySafe(w.planks))
                )
                .addCustomItem((w, b, p) -> new customBdBlockItem(b, p, "chair_2"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                //.addTag(modRes("chair"), Registry.ITEM_REGISTRY)
                .addTexture(modRes("block/decoration/seating/oak/oak_chair_2"))
                .createPaletteFromOak(this::neutralPalette)
                .setTab(() -> TabDeco)
                .setRenderType(() -> RenderType::cutout)
                .build();
        this.addEntry(CHAIR_2);


        //TYPE: TABLE
        TABLE_1 = SimpleEntrySet.builder(WoodType.class, "table_1",
                        BdDecoration.OAK_TABLE_1,
                        () -> WoodType.OAK_WOOD_TYPE,
                        w -> new BlockSmallTable(WoodGood.copySafe(w.planks))
                )
                .addCustomItem((w, b, p) -> new customBdBlockItem(b, p, "table_1"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                //.addTag(modRes("table"), Registry.ITEM_REGISTRY)
                .setTab(() -> TabDeco)
                .addTexture(modRes("block/decoration/tables/oak/oak_table_1"))
                .setRenderType(() -> RenderType::cutout)
                .build();
        this.addEntry(TABLE_1);

        TABLE_2 = SimpleEntrySet.builder(WoodType.class, "table_2",
                        BdDecoration.OAK_TABLE_2, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new BlockSmallTable(WoodGood.copySafe(w.planks))
                )
                .addCustomItem((w, b, p) -> new customBdBlockItem(b, p, "table_2"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                //.addTag(modRes("table"), Registry.ITEM_REGISTRY)
                .setTab(() -> TabDeco)
                .addTexture(modRes("block/decoration/tables/oak/oak_table_2"))
                .setRenderType(() -> RenderType::cutout)
                .build();
        this.addEntry(TABLE_2);


        //TYPE: PLANKS
        PLANKS_1 = SimpleEntrySet.builder(WoodType.class, "planks_1",
                        BdBlocks.OAK_PLANKS_1, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new BlockFlammable(WoodGood.copySafe(w.planks))
                )
                .addCustomItem((w, b, p) -> new customBdBlockItem(b, p, "planks_1"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("planks"), Registry.ITEM_REGISTRY)
                .setTab(() -> TabBlock)
                .addTexture(modRes("block/oak_planks_1"))
                .createPaletteFromOak(this::neutralPalette)
                .addRecipe(ResourceLocation.tryParse("buildersdelight:oak_planks_1"))
                .setRenderType(() -> RenderType::cutout)
                .build();
        this.addEntry(PLANKS_1);

        PLANKS_2 = SimpleEntrySet.builder(WoodType.class, "planks_2",
                        BdBlocks.OAK_PLANKS_2, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new BlockFlammable(WoodGood.copySafe(w.planks))
                )
                .addCustomItem((w, b, p) -> new customBdBlockItem(b, p, "planks_2"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("planks"), Registry.ITEM_REGISTRY)
                .setTab(() -> TabBlock)
                .addTexture(modRes("block/oak_planks_2"))
                .createPaletteFromOak(this::neutralPalette)
                .addRecipe(ResourceLocation.tryParse("buildersdelight:oak_planks_2"))
                .setRenderType(() -> RenderType::cutout)
                .build();
        this.addEntry(PLANKS_2);

        PLANKS_3 = SimpleEntrySet.builder(WoodType.class, "planks_3",
                        BdBlocks.OAK_PLANKS_3, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new BlockFlammable(WoodGood.copySafe(w.planks))
                )
                .addCustomItem((w, b, p) -> new customBdBlockItem(b, p, "planks_3"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("planks"), Registry.ITEM_REGISTRY)
                .setTab(() -> TabBlock)
                .addTexture(modRes("block/oak_planks_3"))
                .createPaletteFromOak(this::neutralPalette)
                .addRecipe(ResourceLocation.tryParse("buildersdelight:oak_planks_3"))
                .setRenderType(() -> RenderType::cutout)
                .build();
        this.addEntry(PLANKS_3);

        PLANKS_4 = SimpleEntrySet.builder(WoodType.class, "planks_4",
                        BdBlocks.OAK_PLANKS_4, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new BlockFlammable(WoodGood.copySafe(w.planks))
                )
                .addCustomItem((w, b, p) -> new customBdBlockItem(b, p, "planks_4"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("planks"), Registry.ITEM_REGISTRY)
                .setTab(() -> TabBlock)
                .addTexture(modRes("block/oak_planks_4"))
                .createPaletteFromOak(this::neutralPalette)
                .addRecipe(ResourceLocation.tryParse("buildersdelight:oak_planks_4"))
                .setRenderType(() -> RenderType::cutout)
                .build();
        this.addEntry(PLANKS_4);

        PLANKS_5 = SimpleEntrySet.builder(WoodType.class, "planks_5",
                        BdBlocks.OAK_PLANKS_5, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new BlockFlammable(WoodGood.copySafe(w.planks))
                )
                .addCustomItem((w, b, p) -> new customBdBlockItem(b, p, "planks_5"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("planks"), Registry.ITEM_REGISTRY)
                .setTab(() -> TabBlock)
                .addTexture(modRes("block/oak_planks_5"))
                .createPaletteFromOak(this::neutralPalette)
                .addRecipe(ResourceLocation.tryParse("buildersdelight:oak_planks_5"))
                .setRenderType(() -> RenderType::cutout)
                .build();
        this.addEntry(PLANKS_5);

        PLANKS_6 = SimpleEntrySet.builder(WoodType.class, "planks_6",
                        BdBlocks.OAK_PLANKS_6, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new BlockFlammable(WoodGood.copySafe(w.planks))
                )
                .addCustomItem((w, b, p) -> new customBdBlockItem(b, p, "planks_6"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("planks"), Registry.ITEM_REGISTRY)
                .setTab(() -> TabBlock)
                .addTexture(modRes("block/oak_planks_6"))
                .createPaletteFromOak(this::neutralPalette)
                .addRecipe(ResourceLocation.tryParse("buildersdelight:oak_planks_6"))
                .setRenderType(() -> RenderType::cutout)
                .build();
        this.addEntry(PLANKS_6);

        PLANKS_7 = SimpleEntrySet.builder(WoodType.class, "planks_7",
                        BdBlocks.OAK_PLANKS_7, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new BlockFlammable(WoodGood.copySafe(w.planks))
                )
                .addCustomItem((w, b, p) -> new customBdBlockItem(b, p, "planks_7"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("planks"), Registry.ITEM_REGISTRY)
                .setTab(() -> TabBlock)
                .addTexture(modRes("block/oak_planks_7"))
                .createPaletteFromOak(this::neutralPalette)
                .addRecipe(ResourceLocation.tryParse("buildersdelight:oak_planks_7"))
                .setRenderType(() -> RenderType::cutout)
                .build();
        this.addEntry(PLANKS_7);


        //TYPE: STAIRS
        STAIRS_1 = SimpleEntrySet.builder(WoodType.class, "stairs_1",
                        BdBlocks.OAK_STAIRS_1, () -> WoodType.OAK_WOOD_TYPE,
                        w -> registerIfStairs(w, () -> new StairFlammable(w.getBlockOfThis("stairs").defaultBlockState(),
                                WoodGood.copySafe(w.getBlockOfThis("stairs") )))
                )
                .addCustomItem((w, b, p) -> new customBdBlockItem(b, p, "stairs_1"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> TabBlock)
                //.addTexture() Using the same texture added from PLANK
                .addRecipe(ResourceLocation.tryParse("buildersdelight:oak_stairs_1"))
                .setRenderType(() -> RenderType::cutout)
                .build();
        this.addEntry(STAIRS_1);

        STAIRS_2 = SimpleEntrySet.builder(WoodType.class, "stairs_2",
                        BdBlocks.OAK_STAIRS_2, () -> WoodType.OAK_WOOD_TYPE,
                        w -> registerIfStairs(w, () -> new StairFlammable(w.getBlockOfThis("stairs").defaultBlockState(),
                                WoodGood.copySafe(w.getBlockOfThis("stairs") )))
                )
                .addCustomItem((w, b, p) -> new customBdBlockItem(b, p, "stairs_2"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> TabBlock)
                //.addTexture() Using the same texture added from PLANK
                .addRecipe(ResourceLocation.tryParse("buildersdelight:oak_stairs_2"))
                .setRenderType(() -> RenderType::cutout)
                .build();
        this.addEntry(STAIRS_2);

        STAIRS_3 = SimpleEntrySet.builder(WoodType.class, "stairs_3",
                        BdBlocks.OAK_STAIRS_3, () -> WoodType.OAK_WOOD_TYPE,
                        w -> registerIfStairs(w, () -> new StairFlammable(w.getBlockOfThis("stairs").defaultBlockState(),
                                WoodGood.copySafe(w.getBlockOfThis("stairs") )))
                )
                .addCustomItem((w, b, p) -> new customBdBlockItem(b, p, "stairs_3"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> TabBlock)
                //.addTexture() Using the same texture added from PLANK
                .addRecipe(ResourceLocation.tryParse("buildersdelight:oak_stairs_3"))
                .setRenderType(() -> RenderType::cutout)
                .build();
        this.addEntry(STAIRS_3);

        STAIRS_4 = SimpleEntrySet.builder(WoodType.class, "stairs_4",
                        BdBlocks.OAK_STAIRS_4, () -> WoodType.OAK_WOOD_TYPE,
                        w -> registerIfStairs(w, () -> new StairFlammable(w.getBlockOfThis("stairs").defaultBlockState(),
                                WoodGood.copySafe(w.getBlockOfThis("stairs") )))
                )
                .addCustomItem((w, b, p) -> new customBdBlockItem(b, p, "stairs_4"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> TabBlock)
                //.addTexture() Using the same texture added from PLANK
                .addRecipe(ResourceLocation.tryParse("buildersdelight:oak_stairs_4"))
                .setRenderType(() -> RenderType::cutout)
                .build();
        this.addEntry(STAIRS_4);

        STAIRS_5 = SimpleEntrySet.builder(WoodType.class, "stairs_5",
                        BdBlocks.OAK_STAIRS_5, () -> WoodType.OAK_WOOD_TYPE,
                        w -> registerIfStairs(w, () -> new StairFlammable(w.getBlockOfThis("stairs").defaultBlockState(),
                                WoodGood.copySafe(w.getBlockOfThis("stairs") )))
                )
                .addCustomItem((w, b, p) -> new customBdBlockItem(b, p, "stairs_5"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> TabBlock)
                //.addTexture() Using the same texture added from PLANK
                .addRecipe(ResourceLocation.tryParse("buildersdelight:oak_stairs_5"))
                .setRenderType(() -> RenderType::cutout)
                .build();
        this.addEntry(STAIRS_5);

        STAIRS_6 = SimpleEntrySet.builder(WoodType.class, "stairs_6",
                        BdBlocks.OAK_STAIRS_6, () -> WoodType.OAK_WOOD_TYPE,
                        w -> registerIfStairs(w, () -> new StairFlammable(w.getBlockOfThis("stairs").defaultBlockState(),
                                WoodGood.copySafe(w.getBlockOfThis("stairs") )))
                )
                .addCustomItem((w, b, p) -> new customBdBlockItem(b, p, "stairs_6"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> TabBlock)
                //.addTexture() Using the same texture added from PLANK
                .addRecipe(ResourceLocation.tryParse("buildersdelight:oak_stairs_6"))
                .setRenderType(() -> RenderType::cutout)
                .build();
        this.addEntry(STAIRS_6);

        STAIRS_7 = SimpleEntrySet.builder(WoodType.class, "stairs_7",
                        BdBlocks.OAK_STAIRS_7, () -> WoodType.OAK_WOOD_TYPE,
                        w -> registerIfStairs(w, () -> new StairFlammable(w.getBlockOfThis("stairs").defaultBlockState(),
                                WoodGood.copySafe(w.getBlockOfThis("stairs") )))
                )
                .addCustomItem((w, b, p) -> new customBdBlockItem(b, p, "stairs_7"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> TabBlock)
                //.addTexture() Using the same texture added from PLANK
                .addRecipe(ResourceLocation.tryParse("buildersdelight:oak_stairs_7"))
                .setRenderType(() -> RenderType::cutout)
                .build();
        this.addEntry(STAIRS_7);


        //TYPE: SLAB
        SLAB_1 = SimpleEntrySet.builder(WoodType.class, "slab_1",
                        BdBlocks.OAK_SLAB_1, () -> WoodType.OAK_WOOD_TYPE,
                        w -> registerIfSlab(w,()-> new SlabFlammable(WoodGood.copySafe(w.getBlockOfThis("slab"))))
                )
                .addCustomItem((w, b, p) -> new customBdBlockItem(b, p, "slab_1"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> TabBlock)
                //.addTexture() Using the same texture added from PLANK
                .addRecipe(ResourceLocation.tryParse("buildersdelight:oak_slab_1"))
                .setRenderType(() -> RenderType::cutout)
                .build();
        this.addEntry(SLAB_1);

        SLAB_2 = SimpleEntrySet.builder(WoodType.class, "slab_2",
                        BdBlocks.OAK_SLAB_2, () -> WoodType.OAK_WOOD_TYPE,
                        w ->registerIfSlab(w,()-> new SlabFlammable(WoodGood.copySafe(w.getBlockOfThis("slab"))))
                )
                .addCustomItem((w, b, p) -> new customBdBlockItem(b, p, "slab_2"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> TabBlock)
                //.addTexture() Using the same texture added from PLANK
                .addRecipe(ResourceLocation.tryParse("buildersdelight:oak_slab_2"))
                .setRenderType(() -> RenderType::cutout)
                .build();
        this.addEntry(SLAB_2);

        SLAB_3 = SimpleEntrySet.builder(WoodType.class, "slab_3",
                        BdBlocks.OAK_SLAB_3, () -> WoodType.OAK_WOOD_TYPE,
                        w ->registerIfSlab(w,()-> new SlabFlammable(WoodGood.copySafe(w.getBlockOfThis("slab"))))
                )
                .addCustomItem((w, b, p) -> new customBdBlockItem(b, p, "slab_3"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> TabBlock)
                //.addTexture() Using the same texture added from PLANK
                .addRecipe(ResourceLocation.tryParse("buildersdelight:oak_slab_3"))
                .setRenderType(() -> RenderType::cutout)
                .build();
        this.addEntry(SLAB_3);

        SLAB_4 = SimpleEntrySet.builder(WoodType.class, "slab_4",
                        BdBlocks.OAK_SLAB_4, () -> WoodType.OAK_WOOD_TYPE,
                        w -> registerIfSlab(w,()->new SlabFlammable(WoodGood.copySafe(w.getBlockOfThis("slab"))))
                )
                .addCustomItem((w, b, p) -> new customBdBlockItem(b, p, "slab_4"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> TabBlock)
                //.addTexture() Using the same texture added from PLANK
                .addRecipe(ResourceLocation.tryParse("buildersdelight:oak_slab_4"))
                .setRenderType(() -> RenderType::cutout)
                .build();
        this.addEntry(SLAB_4);

        SLAB_5 = SimpleEntrySet.builder(WoodType.class, "slab_5",
                        BdBlocks.OAK_SLAB_5, () -> WoodType.OAK_WOOD_TYPE,
                        w -> registerIfSlab(w,()->new SlabFlammable(WoodGood.copySafe(w.getBlockOfThis("slab"))))
                )
                .addCustomItem((w, b, p) -> new customBdBlockItem(b, p, "slab_5"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> TabBlock)
                //.addTexture() Using the same texture added from PLANK
                .addRecipe(ResourceLocation.tryParse("buildersdelight:oak_slab_5"))
                .setRenderType(() -> RenderType::cutout)
                .build();
        this.addEntry(SLAB_5);

        SLAB_6 = SimpleEntrySet.builder(WoodType.class, "slab_6",
                        BdBlocks.OAK_SLAB_6, () -> WoodType.OAK_WOOD_TYPE,
                        w -> registerIfSlab(w,()->new SlabFlammable(WoodGood.copySafe(w.getBlockOfThis("slab"))))
                )
                .addCustomItem((w, b, p) -> new customBdBlockItem(b, p, "slab_6"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> TabBlock)
                //.addTexture() Using the same texture added from PLANK
                .addRecipe(ResourceLocation.tryParse("buildersdelight:oak_slab_6"))
                .setRenderType(() -> RenderType::cutout)
                .build();
        this.addEntry(SLAB_6);

        SLAB_7 = SimpleEntrySet.builder(WoodType.class, "slab_7",
                        BdBlocks.OAK_SLAB_7, () -> WoodType.OAK_WOOD_TYPE,
                        w -> registerIfSlab(w,()->new SlabFlammable(WoodGood.copySafe(w.getBlockOfThis("slab"))))
                )
                .addCustomItem((w, b, p) -> new customBdBlockItem(b, p, "slab_7"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> TabBlock)
                //.addTexture() Using the same texture added from PLANK
                .addRecipe(ResourceLocation.tryParse("buildersdelight:oak_slab_7"))
                .setRenderType(() -> RenderType::cutout)
                .build();
        this.addEntry(SLAB_7);


        //TYPE: FRAME
        FRAME_1 = SimpleEntrySet.builder(WoodType.class, "frame_1",
                        BdBlocks.OAK_FRAME_1, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new BlockFlammable(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL))
                )
                .addCustomItem((w, b, p) -> new customBdBlockItem(b, p, "frame_1"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> TabBlock)
                .addTexture(modRes("block/oak_frame_1"))
                .createPaletteFromOak(this::neutralPalette)
                .addRecipe(ResourceLocation.tryParse("buildersdelight:oak_frame_1"))
                .setRenderType(() -> RenderType::cutout)
                .build();
        this.addEntry(FRAME_1);

        FRAME_2 = SimpleEntrySet.builder(WoodType.class, "frame_2",
                        BdBlocks.OAK_FRAME_2, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new BlockFlammable(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL))
                )
                .addCustomItem((w, b, p) -> new customBdBlockItem(b, p, "frame_2"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                //.addTag(modRes("frame"), Registry.ITEM_REGISTRY)
                .setTab(() -> TabBlock)
                .addTexture(modRes("block/oak_frame_2"))
                .createPaletteFromOak(this::neutralPalette)
                .setRenderType(() -> RenderType::cutout)
                .build();
        this.addEntry(FRAME_2);

        FRAME_3 = SimpleEntrySet.builder(WoodType.class, "frame_3",
                        BdBlocks.OAK_FRAME_3, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new BlockFlammable(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL))
                )
                .addCustomItem((w, b, p) -> new customBdBlockItem(b, p, "frame_3"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                //.addTag(modRes("frame"), Registry.ITEM_REGISTRY)
                .setTab(() -> TabBlock)
                .addTexture(modRes("block/oak_frame_3"))
                .createPaletteFromOak(this::neutralPalette)
                .setRenderType(() -> RenderType::cutout)
                .build();
        this.addEntry(FRAME_3);

        FRAME_4 = SimpleEntrySet.builder(WoodType.class, "frame_4",
                        BdBlocks.OAK_FRAME_4, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new BlockFlammable(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL))
                )
                .addCustomItem((w, b, p) -> new customBdBlockItem(b, p, "frame_4"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                //.addTag(modRes("frame"), Registry.ITEM_REGISTRY)
                .setTab(() -> TabBlock)
                .addTexture(modRes("block/oak_frame_4"))
                .createPaletteFromOak(this::neutralPalette)
                .setRenderType(() -> RenderType::cutout)
                .build();
        this.addEntry(FRAME_4);

        FRAME_5 = SimpleEntrySet.builder(WoodType.class, "frame_5",
                        BdBlocks.OAK_FRAME_5, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new BlockFlammable(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL))
                )
                .addCustomItem((w, b, p) -> new customBdBlockItem(b, p, "frame_5"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                //.addTag(modRes("frame"), Registry.ITEM_REGISTRY)
                .setTab(() -> TabBlock)
                .addTexture(modRes("block/oak_frame_5"))
                .createPaletteFromOak(this::neutralPalette)
                .setRenderType(() -> RenderType::cutout)
                .build();
        this.addEntry(FRAME_5);

        FRAME_6 = SimpleEntrySet.builder(WoodType.class, "frame_6",
                        BdBlocks.OAK_FRAME_6, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new BlockFlammable(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL))
                )
                .addCustomItem((w, b, p) -> new customBdBlockItem(b, p, "frame_6"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                //.addTag(modRes("frame"), Registry.ITEM_REGISTRY)
                .setTab(() -> TabBlock)
                .addTexture(modRes("block/oak_frame_6"))
                .createPaletteFromOak(this::neutralPalette)
                .setRenderType(() -> RenderType::cutout)
                .build();
        this.addEntry(FRAME_6);

        FRAME_7 = SimpleEntrySet.builder(WoodType.class, "frame_7",
                        BdBlocks.OAK_FRAME_7, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new BlockFlammable(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL))
                )
                .addCustomItem((w, b, p) -> new customBdBlockItem(b, p, "frame_7"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                //.addTag(modRes("frame"), Registry.ITEM_REGISTRY)
                .setTab(() -> TabBlock)
                .addTexture(modRes("block/oak_frame_7"))
                .createPaletteFromOak(this::neutralPalette)
                .setRenderType(() -> RenderType::cutout)
                .build();
        this.addEntry(FRAME_7);

        FRAME_8 = SimpleEntrySet.builder(WoodType.class, "frame_8",
                        BdBlocks.OAK_FRAME_8, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new BlockFlammable(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL))
                )
                .addCustomItem((w, b, p) -> new customBdBlockItem(b, p, "frame_8"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                //.addTag(modRes("frame"), Registry.ITEM_REGISTRY)
                .setTab(() -> TabBlock)
                .addTexture(modRes("block/oak_frame_8"))
                .createPaletteFromOak(this::neutralPalette)
                .setRenderType(() -> RenderType::cutout)
                .build();
        this.addEntry(FRAME_8);


        //TYPE: GLASS
        GLASS_1 = SimpleEntrySet.builder(WoodType.class, "glass_1",
                        BdBlocks.OAK_GLASS_1, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new CustomGlassCT(BlockBehaviour.Properties.copy(Blocks.GLASS),"block/bdl/"+ w.getId().getNamespace()+ "/glass/"+ w.getTypeName()+ "_glass/oak_glass_1")
                )
                .addCustomItem((w, b, p) -> new customBdBlockItem(b, p, "glass_1"))
                //.addTag(Tags.Blocks.GLASS, Registry.ITEM_REGISTRY)
                .addTag(modRes("glass"), Registry.ITEM_REGISTRY)
                .setTab(() -> TabBlock)
                .createPaletteFromOak(this::neutralPalette)
                .addTextureM(modRes("block/glass/oak_glass/oak_glass_1"), WoodGood.res("block/bdl/oak_glass_1_mask"))
                .addRecipe(ResourceLocation.tryParse("buildersdelight:oak_glass_1"))
                .setRenderType(() -> RenderType::translucent)
                .build();
        this.addEntry(GLASS_1);

        GLASS_2 = SimpleEntrySet.builder(WoodType.class, "glass_2",
                        BdBlocks.OAK_GLASS_2, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new CustomGlassCT(BlockBehaviour.Properties.copy(Blocks.GLASS),"block/bdl/"+ w.getId().getNamespace()+ "/glass/"+ w.getTypeName()+ "_glass/oak_glass_2")
                )
                .addCustomItem((w, b, p) -> new customBdBlockItem(b, p, "glass_2"))
                //.addTag(Tags.Blocks.GLASS, Registry.ITEM_REGISTRY)
                .addTag(modRes("glass"), Registry.ITEM_REGISTRY)
                .setTab(() -> TabBlock)
                .createPaletteFromOak(this::neutralPalette)
                .addTextureM(modRes("block/glass/oak_glass/oak_glass_2"), WoodGood.res("block/bdl/oak_glass_2_mask"))
                .setRenderType(() -> RenderType::translucent)
                .build();
        this.addEntry(GLASS_2);

        GLASS_3 = SimpleEntrySet.builder(WoodType.class, "glass_3",
                        BdBlocks.OAK_GLASS_3, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new CustomGlassCT(BlockBehaviour.Properties.copy(Blocks.GLASS),"block/bdl/"+ w.getId().getNamespace()+ "/glass/"+ w.getTypeName()+ "_glass/oak_glass_3")
                )
                .addCustomItem((w, b, p) -> new customBdBlockItem(b, p, "glass_3"))
                //.addTag(Tags.Blocks.GLASS, Registry.ITEM_REGISTRY)
                .addTag(modRes("glass"), Registry.ITEM_REGISTRY)
                .setTab(() -> TabBlock)
                .createPaletteFromOak(this::neutralPalette)
                .addTextureM(modRes("block/glass/oak_glass/oak_glass_3"), WoodGood.res("block/bdl/oak_glass_3_mask"))
                .setRenderType(() -> RenderType::translucent)
                .build();
        this.addEntry(GLASS_3);

        GLASS_4 = SimpleEntrySet.builder(WoodType.class, "glass_4",
                        BdBlocks.OAK_GLASS_4, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new CustomGlassCT(BlockBehaviour.Properties.copy(Blocks.GLASS),"block/bdl/"+ w.getId().getNamespace()+ "/glass/"+ w.getTypeName()+ "_glass/oak_glass_4")
                )
                .addCustomItem((w, b, p) -> new customBdBlockItem(b, p, "glass_4"))
                //.addTag(Tags.Blocks.GLASS, Registry.ITEM_REGISTRY)
                .addTag(modRes("glass"), Registry.ITEM_REGISTRY)
                .setTab(() -> TabBlock)
                .createPaletteFromOak(this::neutralPalette)
                .addTextureM(modRes("block/glass/oak_glass/oak_glass_4"), WoodGood.res("block/bdl/oak_glass_4_mask"))
                .setRenderType(() -> RenderType::translucent)
                .build();
        this.addEntry(GLASS_4);

        GLASS_5 = SimpleEntrySet.builder(WoodType.class, "glass_5",
                        BdBlocks.OAK_GLASS_5, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new CustomGlassCT(BlockBehaviour.Properties.copy(Blocks.GLASS),"block/bdl/"+ w.getId().getNamespace()+ "/glass/"+ w.getTypeName()+ "_glass/oak_glass_5")
                )
                .addCustomItem((w, b, p) -> new customBdBlockItem(b, p, "glass_5"))
                //.addTag(Tags.Blocks.GLASS, Registry.ITEM_REGISTRY)
                .addTag(modRes("glass"), Registry.ITEM_REGISTRY)
                .setTab(() -> TabBlock)
                .createPaletteFromOak(this::neutralPalette)
                .addTextureM(modRes("block/glass/oak_glass/oak_glass_5"), WoodGood.res("block/bdl/oak_glass_5_mask"))
                .setRenderType(() -> RenderType::translucent)
                .build();
        this.addEntry(GLASS_5);

        GLASS_6 = SimpleEntrySet.builder(WoodType.class, "glass_6",
                        BdBlocks.OAK_GLASS_6, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new CustomGlassCT(BlockBehaviour.Properties.copy(Blocks.GLASS),"block/bdl/"+ w.getId().getNamespace()+ "/glass/"+ w.getTypeName()+ "_glass/oak_glass_6")
                )
                .addCustomItem((w, b, p) -> new customBdBlockItem(b, p, "glass_6"))
                //.addTag(Tags.Blocks.GLASS, Registry.ITEM_REGISTRY)
                .addTag(modRes("glass"), Registry.ITEM_REGISTRY)
                .setTab(() -> TabBlock)
                .createPaletteFromOak(this::neutralPalette)
                .addTextureM(modRes("block/glass/oak_glass/oak_glass_6"), WoodGood.res("block/bdl/oak_glass_6_mask"))
                .setRenderType(() -> RenderType::translucent)
                .build();
        this.addEntry(GLASS_6);

        GLASS_7 = SimpleEntrySet.builder(WoodType.class, "glass_7",
                        BdBlocks.OAK_GLASS_7, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new CustomGlassCT(BlockBehaviour.Properties.copy(Blocks.GLASS),"block/bdl/"+ w.getId().getNamespace()+ "/glass/"+ w.getTypeName()+ "_glass/oak_glass_7")
                )
                .addCustomItem((w, b, p) -> new customBdBlockItem(b, p, "glass_7"))
                //.addTag(Tags.Blocks.GLASS, Registry.ITEM_REGISTRY)
                .addTag(modRes("glass"), Registry.ITEM_REGISTRY)
                .setTab(() -> TabBlock)
                .createPaletteFromOak(this::neutralPalette)
                .addTextureM(modRes("block/glass/oak_glass/oak_glass_7"), WoodGood.res("block/bdl/oak_glass_7_mask"))
                .setRenderType(() -> RenderType::translucent)
                .build();
        this.addEntry(GLASS_7);

        GLASS_8 = SimpleEntrySet.builder(WoodType.class, "glass_8",
                        BdBlocks.OAK_GLASS_8, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new CustomGlassCT(BlockBehaviour.Properties.copy(Blocks.GLASS),"block/bdl/"+ w.getId().getNamespace()+ "/glass/"+ w.getTypeName()+ "_glass/oak_glass_8")
                )
                .addCustomItem((w, b, p) -> new customBdBlockItem(b, p, "glass_8"))
                //.addTag(Tags.Blocks.GLASS, Registry.ITEM_REGISTRY)
                .addTag(modRes("glass"), Registry.ITEM_REGISTRY)
                .setTab(() -> TabBlock)
                .createPaletteFromOak(this::neutralPalette)
                .addTextureM(modRes("block/glass/oak_glass/oak_glass_8"), WoodGood.res("block/bdl/oak_glass_8_mask"))
                .setRenderType(() -> RenderType::translucent)
                .build();
        this.addEntry(GLASS_8);


        //TYPE: GLASS_PANE
        GLASS_PANE_1 = SimpleEntrySet.builder(WoodType.class, "glass_pane_1",
                        BdBlocks.OAK_GLASS_PANE_1, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new CustomPaneCT(BlockBehaviour.Properties.copy(Blocks.GLASS),"block/bdl/"+ w.getId().getNamespace()+ "/glass/"+ w.getTypeName()+ "_glass/oak_glass_1")
                )
                .addCustomItem((w, b, p) -> new customBdBlockItem(b, p, "glass_pane_1"))
//                .addTag(Tags.Blocks.GLASS_PANES, Registry.ITEM_REGISTRY)
                .setTab(() -> TabBlock)
                // (.addTexture) Using the same texture added from GLASS_X
                .addRecipe(ResourceLocation.tryParse("buildersdelight:oak_glass_pane_1"))
                .setRenderType(() -> RenderType::translucent)
                .build();
        this.addEntry(GLASS_PANE_1);

        GLASS_PANE_2 = SimpleEntrySet.builder(WoodType.class, "glass_pane_2",
                        BdBlocks.OAK_GLASS_PANE_2, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new CustomPaneCT(BlockBehaviour.Properties.copy(Blocks.GLASS), "block/bdl/"+ w.getId().getNamespace()+ "/glass/"+ w.getTypeName()+ "_glass/oak_glass_2")
                )
                .addCustomItem((w, b, p) -> new customBdBlockItem(b, p, "glass_pane_2"))
                //.addTag(Tags.Blocks.GLASS_PANES, Registry.ITEM_REGISTRY)
                .setTab(() -> TabBlock)
                // (.addTexture) Using the same texture added from GLASS_X
                .addRecipe(ResourceLocation.tryParse("buildersdelight:oak_glass_pane_2"))
                .setRenderType(() -> RenderType::translucent)
                .build();
        this.addEntry(GLASS_PANE_2);

        GLASS_PANE_3 = SimpleEntrySet.builder(WoodType.class, "glass_pane_3",
                        BdBlocks.OAK_GLASS_PANE_3, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new CustomPaneCT(BlockBehaviour.Properties.copy(Blocks.GLASS), "block/bdl/"+ w.getId().getNamespace()+ "/glass/"+ w.getTypeName()+ "_glass/oak_glass_3")
                )
                .addCustomItem((w, b, p) -> new customBdBlockItem(b, p, "glass_pane_3"))
                //.addTag(Tags.Blocks.GLASS_PANES, Registry.ITEM_REGISTRY)
                .setTab(() -> TabBlock)
                // (.addTexture) Using the same texture added from GLASS_X
                .addRecipe(ResourceLocation.tryParse("buildersdelight:oak_glass_pane_3"))
                .setRenderType(() -> RenderType::translucent)
                .build();
        this.addEntry(GLASS_PANE_3);

        GLASS_PANE_4 = SimpleEntrySet.builder(WoodType.class, "glass_pane_4",
                        BdBlocks.OAK_GLASS_PANE_4, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new CustomPaneCT(BlockBehaviour.Properties.copy(Blocks.GLASS),"block/bdl/"+ w.getId().getNamespace()+ "/glass/"+ w.getTypeName()+ "_glass/oak_glass_4")
                )
                .addCustomItem((w, b, p) -> new customBdBlockItem(b, p, "glass_pane_4"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(Tags.Blocks.GLASS_PANES, Registry.ITEM_REGISTRY)
                .setTab(() -> TabBlock)
                // (.addTexture) Using the same texture added from GLASS_X
                .addRecipe(ResourceLocation.tryParse("buildersdelight:oak_glass_pane_4"))
                .setRenderType(() -> RenderType::translucent)
                .build();
        this.addEntry(GLASS_PANE_4);

        GLASS_PANE_5 = SimpleEntrySet.builder(WoodType.class, "glass_pane_5",
                        BdBlocks.OAK_GLASS_PANE_5, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new CustomPaneCT(BlockBehaviour.Properties.copy(Blocks.GLASS),"block/bdl/"+ w.getId().getNamespace()+ "/glass/"+ w.getTypeName()+ "_glass/oak_glass_5")
                )
                .addCustomItem((w, b, p) -> new customBdBlockItem(b, p, "glass_pane_5"))
                //.addTag(Tags.Blocks.GLASS_PANES, Registry.ITEM_REGISTRY)
                .setTab(() -> TabBlock)
                // (.addTexture) Using the same texture added from GLASS_X
                .addRecipe(ResourceLocation.tryParse("buildersdelight:oak_glass_pane_5"))
                .setRenderType(() -> RenderType::translucent)
                .build();
        this.addEntry(GLASS_PANE_5);

        GLASS_PANE_6 = SimpleEntrySet.builder(WoodType.class, "glass_pane_6",
                        BdBlocks.OAK_GLASS_PANE_6, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new CustomPaneCT(BlockBehaviour.Properties.copy(Blocks.GLASS),"block/bdl/"+ w.getId().getNamespace()+ "/glass/"+ w.getTypeName()+ "_glass/oak_glass_6")
                )
                .addCustomItem((w, b, p) -> new customBdBlockItem(b, p, "glass_pane_6"))
                //.addTag(Tags.Blocks.GLASS_PANES, Registry.ITEM_REGISTRY)
                .setTab(() -> TabBlock)
                // (.addTexture) Using the same texture added from GLASS_X
                .addRecipe(ResourceLocation.tryParse("buildersdelight:oak_glass_pane_6"))
                .setRenderType(() -> RenderType::translucent)
                .build();
        this.addEntry(GLASS_PANE_6);

        GLASS_PANE_7 = SimpleEntrySet.builder(WoodType.class, "glass_pane_7",
                        BdBlocks.OAK_GLASS_PANE_7, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new CustomPaneCT(BlockBehaviour.Properties.copy(Blocks.GLASS),"block/bdl/"+ w.getId().getNamespace()+ "/glass/"+ w.getTypeName()+ "_glass/oak_glass_7")
                )
                .addCustomItem((w, b, p) -> new customBdBlockItem(b, p, "glass_pane_7"))
                //.addTag(Tags.Blocks.GLASS_PANES, Registry.ITEM_REGISTRY)
                .setTab(() -> TabBlock)
                // (.addTexture) Using the same texture added from GLASS_X
                .addRecipe(ResourceLocation.tryParse("buildersdelight:oak_glass_pane_7"))
                .setRenderType(() -> RenderType::translucent)
                .build();
        this.addEntry(GLASS_PANE_7);

        GLASS_PANE_8 = SimpleEntrySet.builder(WoodType.class, "glass_pane_8",
                        BdBlocks.OAK_GLASS_PANE_8, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new CustomPaneCT(BlockBehaviour.Properties.copy(Blocks.GLASS),"block/bdl/"+ w.getId().getNamespace()+ "/glass/"+ w.getTypeName()+ "_glass/oak_glass_8")
                )
                .addCustomItem((w, b, p) -> new customBdBlockItem(b, p, "glass_pane_8"))
                //.addTag(Tags.Blocks.GLASS_PANES, Registry.ITEM_REGISTRY)
                .setTab(() -> TabBlock)
                // (.addTexture) Using the same texture added from GLASS_X
                .addRecipe(ResourceLocation.tryParse("buildersdelight:oak_glass_pane_8"))
                .setRenderType(() -> RenderType::translucent)
                .build();
        this.addEntry(GLASS_PANE_8);

    }

    //TYPE: Palette
    private void neutralPalette(Palette p) {
        p.remove(p.getLightest());
        p.remove(p.getLightest());
        p.remove(p.getDarkest());
        p.remove(p.getDarkest());
    }

    //TYPE: register if not null
    private <B extends Block> B registerIfStairs(WoodType woodType, Supplier<B> supplier) {
        if (woodType.getBlockOfThis("stairs") != null) {
            return supplier.get();
        }
        return null;
    }
    private <B extends Block> B registerIfSlab(WoodType woodType, Supplier<B> supplier) {
        if (woodType.getBlockOfThis("slab") != null) {
            return supplier.get();
        }
        return null;
    }

    @Override
    // Add <type>_furniture_kit - items
    public void registerItems(IForgeRegistry<Item> registry) {
        super.registerItems(registry);
        for (Map.Entry<ResourceLocation, WoodType> entry : WoodTypeRegistry.WOOD_TYPES.entrySet()) {
            WoodType wood = entry.getValue();
            String fullName = this.shortenedId() + "/" + wood.getNamespace() + "/" + wood.getTypeName() + "_furniture_kit";
            if (wood.isVanilla()) continue;
            Item item = new CompatBdFurnitureKit(new Item.Properties().tab(BdTabs.TabMaterials).stacksTo(64), "furniture_kit");
            FURNITURE_KIT.put(wood, item);
            registry.register(item.setRegistryName(WoodGood.res(fullName)));
        }
    }

    @Override
    public void addTranslations(ClientDynamicResourcesHandler clientDynamicResourcesHandler, AfterLanguageLoadEvent lang) {
        super.addTranslations(clientDynamicResourcesHandler, lang);
        FURNITURE_KIT.forEach((w, v) -> LangBuilder.addDynamicEntry(lang, "item_type.buildersdelight.furniture_kit", w, v));
    }

    // Create a tag file with <type>_planks
    public void planksTags(WoodType wood, DynamicDataPack DDPack, EntrySet<?, ?>... entries) {
        JsonArray tagList = new JsonArray();

        var vanilla = wood.getItemOfThis("planks"); // Add Normal blocks to the list
        if (vanilla != null) tagList.add(String.valueOf(vanilla.getRegistryName()));

        for (var e : entries) { // Add blocks to the list
            Item obj = e.items.get(wood);
            if (obj != null) tagList.add(String.valueOf(obj.getRegistryName()));
        }

        if (!tagList.isEmpty()) {
            ResourceLocation resLoc = WoodGood.res("items/" + wood.getTypeName() + "_planks");
            JsonObject file = new JsonObject();
            file.addProperty("replace", "false");
            file.add("values", tagList);
            DDPack.addJson(resLoc, file, ResType.TAGS);
        }

    }

    @Override
    // Add recipes for FURNITURE_KIT & ChiselRecipe
    public void addDynamicServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicServerResources(handler, manager);
        DynamicDataPack pack = handler.getPack();


        for (WoodType wood : WoodTypeRegistry.WOOD_TYPES.values()) {
            if (!wood.isVanilla()) {
                 // Chisel Recipe
                addChiselRecipe(pack, wood, "planks", PLANKS_1, PLANKS_2, PLANKS_3, PLANKS_4, PLANKS_5, PLANKS_6, PLANKS_7);
                addChiselRecipe(pack, wood, "stairs", STAIRS_1, STAIRS_2, STAIRS_3, STAIRS_4, STAIRS_5, STAIRS_6, STAIRS_7);
                addChiselRecipe(pack, wood, "slab", SLAB_1, SLAB_2, SLAB_3, SLAB_4, SLAB_5, SLAB_6, SLAB_7);
                addChiselRecipe(pack, wood, "frame", FRAME_1, FRAME_2, FRAME_3, FRAME_4, FRAME_5, FRAME_6, FRAME_7, FRAME_8);
                addChiselRecipe(pack, wood, "glass", GLASS_1, GLASS_2, GLASS_3, GLASS_4, GLASS_5, GLASS_6, GLASS_7, GLASS_8);
                addChiselRecipe(pack, wood, "glass_pane", GLASS_PANE_1, GLASS_PANE_2, GLASS_PANE_3, GLASS_PANE_4, GLASS_PANE_5, GLASS_PANE_6, GLASS_PANE_7, GLASS_PANE_8);

                planksTags(wood, pack, PLANKS_1, PLANKS_2, PLANKS_3, PLANKS_4, PLANKS_5, PLANKS_6, PLANKS_7);

                // Glass Recipe
                craftingWithTagsRecipe("glass_1", "planks", GLASS_1.items.get(wood), wood, pack, manager);
                craftingWithTagsRecipe("frame_1", "planks", FRAME_1.items.get(wood), wood, pack, manager);
            }
        }

        // Recipe for FURNITURE_KIT
        String recipe = """
                    {
                      "group": "buildersdelight",
                      "type": "minecraft:crafting_shaped",
                      "pattern": [
                        " 0 ",
                        "010",
                        " 0 "
                      ],
                      "key": {
                        "0": {
                          "item": "minecraft:string"
                        },
                        "1": {
                          "item": "[planks]"
                        }
                      },
                      "result": {
                        "item": "[result]",
                        "count": 2
                      }
                    }
        """;
        for (var entries : FURNITURE_KIT.entrySet()) {
            WoodType wood = entries.getKey();
            Item furniture_kit = entries.getValue();

            // FURNITURE_KIT RECIPES
            String r = recipe.replace("[result]", Objects.requireNonNull(entries.getValue().getRegistryName()).toString())
                    .replace("[planks]", Objects.requireNonNull(wood.planks.getRegistryName()).toString());
            ResourceLocation res = WoodGood.res("bdl/" + wood.getAppendableId() + "_furniture_kit");
            pack.addBytes(res, r.getBytes(), ResType.RECIPES);

            // CHAIR
            stonecutterRecipe("chair_1", CHAIR_1.items.get(wood), furniture_kit, wood, handler, manager);
            stonecutterRecipe("chair_2", CHAIR_2.items.get(wood), furniture_kit, wood, handler, manager);
            // TABLE
            stonecutterRecipe("table_1", TABLE_1.items.get(wood), furniture_kit, wood, handler, manager);
            stonecutterRecipe("table_2", TABLE_2.items.get(wood), furniture_kit, wood, handler, manager);
        }
    }

    public void stonecutterRecipe(String outputName, Item output, Item input,  WoodType wood, ServerDynamicResourcesHandler handler, ResourceManager manager) {
        String ResLoc = "minecraft:" + wood.getTypeName() + "_" + outputName;
        // tfc have oak, acacia, birch which are conflicting with vanilla's
        if (Objects.equals(wood.getNamespace(), "tfc")) ResLoc += "_tfc";

        JsonObject ingredient = new JsonObject();
        ingredient.addProperty("item", String.valueOf(input.getRegistryName()));

        JsonObject recipe = new JsonObject();
        recipe.addProperty("type", "minecraft:stonecutting");
        recipe.add("ingredient", ingredient);
        recipe.addProperty("result", String.valueOf(output.getRegistryName()));
        recipe.addProperty("count", 1);

        handler.dynamicPack.addJson(new ResourceLocation(ResLoc), recipe, ResType.RECIPES);
    }

    // Recipes use tags for ingredients
    public void craftingWithTagsRecipe(String baseName, String input, Item output, WoodType wood, DynamicDataPack pack, ResourceManager manager) {
        // bdl/namespace/<type>_glass_1;
        String pathBuilder = this.shortenedId() + "/" + wood.getVariantId(baseName,false);

        ResourceLocation recipeLoc = modRes("recipes/oak_"+ baseName + ".json");
        JsonObject recipe;
        try (InputStream recipeStream = manager.getResource(recipeLoc).getInputStream()) {
            recipe = RPUtils.deserializeJson(recipeStream);
            String inputTag = WoodGood.MOD_ID + ":" + wood.getTypeName() +"_"+ input ;

            // VARIABLES for json
            JsonObject underKey;
            if (Objects.equals(baseName, "glass_1")) {
                underKey = recipe.getAsJsonObject("key").getAsJsonObject("1");
            } else {
                underKey = recipe.getAsJsonObject("key").getAsJsonObject("0");
            }
            JsonObject underResult = recipe.getAsJsonObject("result");

            // EDITING
            underKey.addProperty("tag", inputTag);
            underResult.addProperty("item", String.valueOf(output.getRegistryName()));

            pack.addJson(WoodGood.res(pathBuilder), recipe, ResType.RECIPES);
        }
        catch (IOException ex) {
            WoodGood.LOGGER.error("{BuildersDelight Module} TagsRecipe(): " + ex);
        }
    }

    private static void addChiselRecipe(DynamicDataPack pack, WoodType w, String name, EntrySet<?, ?>... entries) {
        JsonArray recipe = new JsonArray();
        for (var e : entries) {         // Add blocks from buildersdelight to JsonArray
            var o = e.items.get(w);
            if (o != null) {
                recipe.add(Objects.requireNonNull(o.getRegistryName()).toString());
            }
        }
        var vanilla = w.getItemOfThis(name); // Add Normal blocks to JsonArray
        if (vanilla != null) recipe.add(Objects.requireNonNull(vanilla.getRegistryName()).toString());

        if (!recipe.isEmpty()) {
            JsonObject jo = new JsonObject();
            ResourceLocation res = WoodGood.res("chisel/" + w.getVariantId(name) + ".json");
            jo.add("variants", recipe);
            pack.addJson(res, jo, ResType.GENERIC);
        }
    }

    @Override
    // Model for FURNITURE_KIT
    public void addStaticClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        super.addStaticClientResources(handler, manager);
        ResourceLocation resLocItem = modRes("models/item/oak_furniture_kit.json");

        FURNITURE_KIT.forEach((wood, item) -> {
            ResourceLocation itemID = item.getRegistryName();

            try (InputStream fileStream = manager.getResource(resLocItem).getInputStream()) {
                JsonObject model = RPUtils.deserializeJson(fileStream);
                JsonObject underTextures = model.getAsJsonObject("textures" );

                underTextures.addProperty("layer0", WoodGood.MOD_ID + ":item/" + Objects.requireNonNull(itemID).getPath());

                handler.dynamicPack.addJson(WoodGood.res(itemID.getPath()), model, ResType.ITEM_MODELS);
            }
            catch (IOException e) {
                WoodGood.LOGGER.error("{BuildersDelight Module} Model file: " + e);
            }

        });
    }

    @Override
    // Textures for furniture_kit
    public void addDynamicClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicClientResources(handler, manager);

        try (
                TextureImage kitTexture = TextureImage.open(manager, modRes("item/oak_furniture_kit"));
                TextureImage overlay = TextureImage.open(manager, WoodGood.res("item/bdl/string_overlay"))
            ) {

            Respriter respriterKit = Respriter.of(kitTexture);

            FURNITURE_KIT.forEach((wood, item) -> {
                ResourceLocation itemID = item.getRegistryName();

                try (TextureImage plankTexture = TextureImage.open(manager,
                        RPUtils.findFirstBlockTextureLocation(manager, wood.planks))
                    ) {
                    Palette targetPalette = SpriteUtils.extrapolateWoodItemPalette(plankTexture);

                    TextureImage newImage = respriterKit.recolor(targetPalette);
                    TextureImage newTexture  = newImage.makeCopy();
                    newTexture.applyOverlayOnExisting(overlay.makeCopy());

                    handler.dynamicPack.addAndCloseTexture(new ResourceLocation(Objects.requireNonNull(itemID).getNamespace(),
                            "item/" + itemID.getPath()), newTexture);

                } catch (Exception e) {
                    handler.getLogger().error("{BuildersDelight Module} Failed opening file via" +
                            " AddDyanmicClientResource: " + e);
                }
            });

        }
        catch (Exception ex) {
            handler.getLogger().error("{BuildersDelight Module} furniture_kit textures via AddDynamicClientResources: " + ex);
        }
    }

    // Adding tooltip to each block
    private static class customBdBlockItem extends BlockItem {

        private final Component tooltip;

        public customBdBlockItem(Block block, Properties properties, String name) {
            super(block, properties);
            this.tooltip = new TranslatableComponent("tooltip.ec.buildersdelight." + name).withStyle(ChatFormatting.GRAY);
        }

        @Override
        public void appendHoverText(@NotNull ItemStack pStack, Level pLevel, List<Component> pTooltip, @NotNull TooltipFlag pFlag) {
            pTooltip.add(tooltip);
        }
    }

    public static class CompatBdFurnitureKit extends BdFurnitureKit {
        private final Component tooltip;

        public CompatBdFurnitureKit(Properties properties, String name) {
            super(properties);
            this.tooltip = new TranslatableComponent("tooltip.ec.buildersdelight." + name).withStyle(ChatFormatting.GRAY);
        }

        @Override
        public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, @NotNull TooltipFlag pFlag) {
            pTooltip.add(tooltip);
        }
    }
}
