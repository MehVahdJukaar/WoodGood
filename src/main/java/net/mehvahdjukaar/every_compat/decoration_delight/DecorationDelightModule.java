package net.mehvahdjukaar.every_compat.decoration_delight;

import decor.delight.block.*;
import decor.delight.block.entity.OakCounterBlockEntity;
import decor.delight.block.entity.OakCounterCornerBlockEntity;
import decor.delight.init.DecorationDelightModBlocks;
import decor.delight.init.DecorationDelightModTabs;
import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.minecraft.core.Registry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;

public class DecorationDelightModule extends SimpleModule {
    public final SimpleEntrySet<WoodType, Block> COUNTER;
    public final SimpleEntrySet<WoodType, Block> COUNTER_CORNER;
    public final SimpleEntrySet<WoodType, Block> COUNTER_BEND;
    public final SimpleEntrySet<WoodType, Block> COUNTER_TOP;
    public final SimpleEntrySet<WoodType, Block> STOOL;
    public final SimpleEntrySet<WoodType, Block> MOSAIC;

    public DecorationDelightModule(String modId) {
        super(modId, "ddl");
        CreativeModeTab tab = DecorationDelightModTabs.TAB_DECORATION_DELIGHT_TAB;

        COUNTER = SimpleEntrySet.builder(WoodType.class, "counter",
                DecorationDelightModBlocks.OAK_COUNTER, () -> WoodType.OAK_WOOD_TYPE,
                w -> new OakCounterBlock()
        )
                .addTag(BlockTags.MINEABLE_WITH_HOE, Registry.BLOCK_REGISTRY)
                .addTile(OakCounterBlockEntity::new)
                .addModelTransform(m -> m.replaceString("blocks/", "block/")
                        .replaceString("lighter_oak_counter_texture", "ddl_lighter_oak_counter")
                        .replaceString("oak_planks", "ddl_oak_planks")
                )
                .addTextureM(WoodGood.res("block/ddl_lighter_oak_counter"), WoodGood.res("block/ddl_lighter_oak_counter_m"))
                .addTexture(WoodGood.res("block/ddl_oak_planks"))
                .setTab(() -> tab)
                .addRecipe(modRes("oak_counter_recipe"))
                .addRecipe(modRes("oak_counter_recipe_2"))
                .build();
        this.addEntry(COUNTER);

        COUNTER_CORNER = SimpleEntrySet.builder(WoodType.class, "counter_corner",
                DecorationDelightModBlocks.OAK_COUNTER_CORNER, () -> WoodType.OAK_WOOD_TYPE,
                w -> new OakCounterCornerBlock()
        )
                .addTag(BlockTags.MINEABLE_WITH_HOE, Registry.BLOCK_REGISTRY)
                .addTile(OakCounterCornerBlockEntity::new)
                .addModelTransform(m -> m.replaceString("blocks/", "block/")
                        .replaceString("lighter_oak_counter_corner_texture", "ddl_lighter_oak_counter_corner")
                        .replaceString("oak_planks", "ddl_oak_planks")
                )
                .addTextureM(WoodGood.res("block/ddl_lighter_oak_counter_corner"), WoodGood.res("block/ddl_lighter_oak_counter_corner_m"))
                .addTexture(WoodGood.res("block/ddl_oak_planks"))
                .setTab(() -> tab)
                .addRecipe(modRes("oak_counter_corner_recipe"))
                .build();
        this.addEntry(COUNTER_CORNER);

        COUNTER_BEND = SimpleEntrySet.builder(WoodType.class, "counter_bend",
                DecorationDelightModBlocks.OAK_COUNTER_BEND, () -> WoodType.OAK_WOOD_TYPE,
                w -> new OakCounterBendBlock()
        )
                .addTag(BlockTags.MINEABLE_WITH_HOE, Registry.BLOCK_REGISTRY)
                .addModelTransform(m -> m.replaceString("blocks/", "block/")
                        .replaceString("lighter_oak_counter_bend_texture", "ddl_lighter_oak_counter_bend")
                        .replaceString("oak_planks", "ddl_oak_planks")
                )
                .addTexture(WoodGood.res("block/ddl_lighter_oak_counter_bend"))
                .addTexture(WoodGood.res("block/ddl_oak_planks"))
                .setTab(() -> tab)
                .addRecipe(modRes("oak_counter_bend_recipe"))
                .build();
        this.addEntry(COUNTER_BEND);

        COUNTER_TOP = SimpleEntrySet.builder(WoodType.class, "countertop",
                DecorationDelightModBlocks.OAK_COUNTERTOP, () -> WoodType.OAK_WOOD_TYPE,
                w -> new OakCountertopBlock()
        )
                .addTag(BlockTags.MINEABLE_WITH_HOE, Registry.BLOCK_REGISTRY)
                .addModelTransform(m -> m.replaceString("blocks/", "block/")
                        .replaceString("lighter_oak_countertop", "ddl_lighter_oak_countertop")
                )
                .addTexture(WoodGood.res("block/ddl_lighter_oak_countertop"))
                .setTab(() -> tab)
                .addRecipe(modRes("oak_countertop_recipe"))
                .build();
        this.addEntry(COUNTER_TOP);

        STOOL = SimpleEntrySet.builder(WoodType.class, "stool",
                DecorationDelightModBlocks.OAK_STOOL, () -> WoodType.OAK_WOOD_TYPE,
                w -> new OakStoolBlock()
        )
                .addTag(BlockTags.MINEABLE_WITH_HOE, Registry.BLOCK_REGISTRY)
                .addModelTransform(m -> m.replaceString("blocks/", "block/")
                        .replaceString("newoakstool", "ddl_new_oak_stool")
                        .replaceString("newoakstoolrealreal", "ddl_new_oak_stoolrealreal")
                        .replaceString("oak_planks", "ddl_oak_planks")
                )
                .addTexture(WoodGood.res("block/ddl_new_oak_stool"))
                .addTexture(WoodGood.res("block/ddl_oak_planks"))
                .addTexture(WoodGood.res("block/ddl_new_oak_stoolrealreal"))
                .setTab(() -> tab)
                .addRecipe(modRes("oak_stool_recipe"))
                .build();
        this.addEntry(STOOL);

        MOSAIC = SimpleEntrySet.builder(WoodType.class, "mosaic",
                DecorationDelightModBlocks.OAK_MOSAIC, () -> WoodType.OAK_WOOD_TYPE,
                w -> new OakMosaicBlock()
        )
                .addTag(BlockTags.MINEABLE_WITH_HOE, Registry.BLOCK_REGISTRY)
                .addModelTransform(m -> m.replaceString("blocks/", "block/")
                        .replaceString("oakmosaic", "ddl_oak_mosaic")
                )
                .addTexture(WoodGood.res("block/ddl_oak_mosaic"))
                .setTab(() -> tab)
                .addRecipe(modRes("oak_mosaic_recipe"))
                .build();
        this.addEntry(MOSAIC);

    }
}
