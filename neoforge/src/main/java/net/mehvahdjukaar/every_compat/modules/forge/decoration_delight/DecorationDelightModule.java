package net.mehvahdjukaar.every_compat.modules.forge.decoration_delight;

import decor.delight.block.*;
import decor.delight.block.entity.OakCounterBlockEntity;
import decor.delight.block.entity.OakCounterCornerBlockEntity;
import decor.delight.init.DecorationDelightModBlocks;
import decor.delight.init.DecorationDelightModTabs;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

//SUPPORT: v1.0.0
public class DecorationDelightModule extends SimpleModule {
    public final SimpleEntrySet<WoodType, Block> COUNTER;
    public final SimpleEntrySet<WoodType, Block> COUNTER_CORNER;
    public final SimpleEntrySet<WoodType, Block> COUNTER_BEND;
    public final SimpleEntrySet<WoodType, Block> COUNTER_TOP;
    public final SimpleEntrySet<WoodType, Block> STOOL;
    public final SimpleEntrySet<WoodType, Block> MOSAIC;

    public DecorationDelightModule(String modId) {
        super(modId, "ddr");
        RegistryObject<CreativeModeTab> tab = DecorationDelightModTabs.DECORATION_DELIGHT_TAB;

        COUNTER = SimpleEntrySet.builder(WoodType.class, "counter",
                DecorationDelightModBlocks.OAK_COUNTER, () -> WoodTypeRegistry.OAK_TYPE,
                w -> new OakCounterBlock()
        )
                .addTag(BlockTags.MINEABLE_WITH_HOE, Registries.BLOCK)
                .addTile(OakCounterBlockEntity::new)
                .addModelTransform(m -> m.replaceString("blocks/", "block/")
                        .replaceString("lighter_oak_counter_texture", "ddr/lighter_oak_counter")
                        .replaceString("oak_planks", "ddr/oak_planks")
                )
                .addTextureM(EveryCompat.res("block/ddr/lighter_oak_counter"), EveryCompat.res("block/ddr/lighter_oak_counter_m"))
                .addTexture(EveryCompat.res("block/ddr/oak_planks"))
                .setTab(tab)
                .addRecipe(modRes("oak_counter_recipe"))
                .addRecipe(modRes("oak_counter_recipe_2"))
                .build();
        this.addEntry(COUNTER);

        COUNTER_CORNER = SimpleEntrySet.builder(WoodType.class, "counter_corner",
                DecorationDelightModBlocks.OAK_COUNTER_CORNER, () -> WoodTypeRegistry.OAK_TYPE,
                w -> new OakCounterCornerBlock()
        )
                .addTag(BlockTags.MINEABLE_WITH_HOE, Registries.BLOCK)
                .addTile(OakCounterCornerBlockEntity::new)
                .addModelTransform(m -> m.replaceString("blocks/", "block/")
                        .replaceString("lighter_oak_counter_corner_texture", "ddr/lighter_oak_counter_corner")
                        .replaceString("oak_planks", "ddr/oak_planks")
                )
                .addTextureM(EveryCompat.res("block/ddr/lighter_oak_counter_corner"), EveryCompat.res("block/ddr/lighter_oak_counter_corner_m"))
                .addTexture(EveryCompat.res("block/ddr/oak_planks"))
                .setTab(tab)
                .addRecipe(modRes("oak_counter_corner_recipe"))
                .build();
        this.addEntry(COUNTER_CORNER);

        COUNTER_BEND = SimpleEntrySet.builder(WoodType.class, "counter_bend",
                DecorationDelightModBlocks.OAK_COUNTER_BEND, () -> WoodTypeRegistry.OAK_TYPE,
                w -> new OakCounterBendBlock()
        )
                .addTag(BlockTags.MINEABLE_WITH_HOE, Registries.BLOCK)
                .addModelTransform(m -> m.replaceString("blocks/", "block/")
                        .replaceString("lighter_oak_counter_bend_texture", "ddr/lighter_oak_counter_bend")
                        .replaceString("oak_planks", "ddr/oak_planks")
                )
                .addTexture(EveryCompat.res("block/ddr/lighter_oak_counter_bend"))
                .addTexture(EveryCompat.res("block/ddr/oak_planks"))
                .setTab(tab)
                .addRecipe(modRes("oak_counter_bend_recipe"))
                .build();
        this.addEntry(COUNTER_BEND);

        COUNTER_TOP = SimpleEntrySet.builder(WoodType.class, "countertop",
                DecorationDelightModBlocks.OAK_COUNTERTOP, () -> WoodTypeRegistry.OAK_TYPE,
                w -> new OakCountertopBlock()
        )
                .addTag(BlockTags.MINEABLE_WITH_HOE, Registries.BLOCK)
                .addModelTransform(m -> m.replaceString("blocks/", "block/")
                        .replaceString("lighter_oak_countertop", "ddr/lighter_oak_countertop")
                )
                .addTexture(EveryCompat.res("block/ddr/lighter_oak_countertop"))
                .setTab(tab)
                .addRecipe(modRes("oak_countertop_recipe"))
                .build();
        this.addEntry(COUNTER_TOP);

        STOOL = SimpleEntrySet.builder(WoodType.class, "stool",
                DecorationDelightModBlocks.OAK_STOOL, () -> WoodTypeRegistry.OAK_TYPE,
                w -> new OakStoolBlock()
                )
                .addTag(BlockTags.MINEABLE_WITH_HOE, Registries.BLOCK)
                .addModelTransform(m -> m.replaceString("blocks/", "block/")
                        .replaceString("newoakstool", "ddr/new_oak_stool")
                        .replaceString("newoakstoolrealreal", "ddr/new_oak_stoolrealreal")
                        .replaceString("oak_planks", "ddr/oak_planks")
                )
                .addTexture(EveryCompat.res("block/ddr/new_oak_stool"))
                .addTexture(EveryCompat.res("block/ddr/oak_planks"))
                .addTexture(EveryCompat.res("block/ddr/new_oak_stoolrealreal"))
                .setTab(tab)
                .addRecipe(modRes("oak_stool_recipe"))
                .build();
        this.addEntry(STOOL);

        MOSAIC = SimpleEntrySet.builder(WoodType.class, "mosaic",
                DecorationDelightModBlocks.OAK_MOSAIC, () -> WoodTypeRegistry.OAK_TYPE,
                w -> new OakMosaicBlock()
                )
                .addTag(BlockTags.MINEABLE_WITH_HOE, Registries.BLOCK)
                .addModelTransform(m -> m.replaceString("blocks/", "block/")
                        .replaceString("oakmosaic", "ddr/oak_mosaic")
                )
                .addTexture(EveryCompat.res("block/ddr/oak_mosaic"))
                .setTab(tab)
                .addRecipe(modRes("oak_mosaic_recipe"))
                .build();
        this.addEntry(MOSAIC);

    }
}
