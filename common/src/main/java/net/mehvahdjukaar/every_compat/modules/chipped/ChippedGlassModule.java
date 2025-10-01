package net.mehvahdjukaar.every_compat.modules.chipped;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.TextureInfo;
import net.mehvahdjukaar.every_compat.common_classes.TagUtility;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TransparentBlock;

//SUPPORT: v4.0.2+
public class ChippedGlassModule extends ChippedAbstractModule {

    public final SimpleEntrySet<WoodType, Block> circleGlass,
            barredGlass,
            borderedGlass,
            diamondBorderedGlass,
            horizontalLinedGlass,
            largeDiamondGlass,
            lineBarredGlass,
            ornateBarredGlass,
            snowflakeGlass,
            squareGlass,
            wovenGlass;

    public final SimpleEntrySet<WoodType, Block> circleGlassPane,
            barredGlassPane,
            borderedGlassPane,
            diamondBorderedGlassPane,
            horizontalLinedGlassPane,
            largeDiamondGlassPane,
            lineBarredGlassPane,
            ornateBarredGlassPane,
            snowflakeGlassPane,
            squareGlassPane,
            wovenGlassPane;

    public ChippedGlassModule(String modId) {
        super(modId);
        //        super(modId, shortenedId, EveryCompat.MOD_ID);
        ResourceLocation tab = modRes(tabPath);

        circleGlass = SimpleEntrySet.builder(WoodType.class, "glass", "circle",
                        getModBlock("circle_oak_glass"), () -> VanillaWoodTypes.OAK,
                        w -> new TransparentBlock(Utils.copyPropertySafe(w.planks).strength(0.3F).sound(SoundType.GLASS)
                                .noOcclusion().isValidSpawn((s, l, ps, t) -> false).isRedstoneConductor((s, l, ps) -> false)
                                .isSuffocating((s, l, ps) -> false).isViewBlocking((s, l, ps) -> false))
                )
                .addTextureM(modRes("block/glass/circle_oak_glass"), EveryCompat.res("block/ch/glass/circle_oak_glass_m"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.IMPERMEABLE, Registries.BLOCK)
                .addTag(modRes("glass"), Registries.BLOCK)
                .addTag(modRes("glass"), Registries.ITEM)
                .addTag(TagUtility.GLASS_TAG, Registries.BLOCK)
                .addTag(TagUtility.GLASS_TAG, Registries.ITEM)
                .setRenderType(RenderLayer.TRANSLUCENT)
                .setTabKey(tab)
                .build();
        this.addEntry(circleGlass);

        barredGlass = SimpleEntrySet.builder(WoodType.class, "bared_glass",
                        getModBlock("oak_bared_glass"), () -> VanillaWoodTypes.OAK,
                        w -> new TransparentBlock(Utils.copyPropertySafe(w.planks).strength(0.3F).sound(SoundType.GLASS)
                                .noOcclusion().isValidSpawn((s, l, ps, t) -> false).isRedstoneConductor((s, l, ps) -> false)
                                .isSuffocating((s, l, ps) -> false).isViewBlocking((s, l, ps) -> false))
                )
                .addTextureM(modRes("block/glass/oak_bared_glass"), EveryCompat.res("block/ch/glass/oak_bared_glass_m"))
                .addTexture(TextureInfo.of(modRes("block/glass/ctm/oak_bared_glass_ctm/0")).copyTexture())
                .addTextureM(modRes("block/glass/ctm/oak_bared_glass_ctm/1"), EveryCompat.res("block/ch/glass/ctm/oak_bared_glass/1_mask"))
                .addTextureM(modRes("block/glass/ctm/oak_bared_glass_ctm/2"), EveryCompat.res("block/ch/glass/ctm/oak_bared_glass/2_mask"))
                .addTextureM(modRes("block/glass/ctm/oak_bared_glass_ctm/3"), EveryCompat.res("block/ch/glass/ctm/oak_bared_glass/3_mask"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.IMPERMEABLE, Registries.BLOCK)
                .addTag(modRes("glass"), Registries.BLOCK)
                .addTag(modRes("glass"), Registries.ITEM)
                .addTag(TagUtility.GLASS_TAG, Registries.BLOCK) //common tags
                .addTag(TagUtility.GLASS_TAG, Registries.ITEM)
                .setRenderType(RenderLayer.TRANSLUCENT)
                .setTabKey(tab)
                .build();
        this.addEntry(barredGlass);

        borderedGlass = SimpleEntrySet.builder(WoodType.class, "bordered_glass",
                        getModBlock("oak_bordered_glass"), () -> VanillaWoodTypes.OAK,
                        w -> new TransparentBlock(Utils.copyPropertySafe(w.planks).strength(0.3F).sound(SoundType.GLASS)
                                .noOcclusion().isValidSpawn((s, l, ps, t) -> false).isRedstoneConductor((s, l, ps) -> false)
                                .isSuffocating((s, l, ps) -> false).isViewBlocking((s, l, ps) -> false))
                )
                .addTextureM(modRes("block/glass/oak_bordered_glass"), EveryCompat.res("block/ch/glass/oak_bordered_glass_m"))
                .addTextureM(modRes("block/glass/ctm/oak_bordered_glass_ctm/0"), EveryCompat.res("block/ch/glass/ctm/oak_bordered_glass/0_mask"))
                .addTextureM(modRes("block/glass/ctm/oak_bordered_glass_ctm/1"), EveryCompat.res("block/ch/glass/ctm/oak_bordered_glass/1_mask"))
                .addTextureM(modRes("block/glass/ctm/oak_bordered_glass_ctm/2"), EveryCompat.res("block/ch/glass/ctm/oak_bordered_glass/2_mask"))
                .addTextureM(modRes("block/glass/ctm/oak_bordered_glass_ctm/3"), EveryCompat.res("block/ch/glass/ctm/oak_bordered_glass/3_mask"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.IMPERMEABLE, Registries.BLOCK)
                .addTag(modRes("glass"), Registries.BLOCK)
                .addTag(modRes("glass"), Registries.ITEM)
                .addTag(TagUtility.GLASS_TAG, Registries.BLOCK)
                .addTag(TagUtility.GLASS_TAG, Registries.ITEM)
                .setRenderType(RenderLayer.TRANSLUCENT)
                .setTabKey(tab)
                .build();
        this.addEntry(borderedGlass);

        diamondBorderedGlass = SimpleEntrySet.builder(WoodType.class, "diamond_bordered_glass",
                        getModBlock("oak_diamond_bordered_glass"), () -> VanillaWoodTypes.OAK,
                        w -> new TransparentBlock(Utils.copyPropertySafe(w.planks).strength(0.3F).sound(SoundType.GLASS)
                                .noOcclusion().isValidSpawn((s, l, ps, t) -> false).isRedstoneConductor((s, l, ps) -> false)
                                .isSuffocating((s, l, ps) -> false).isViewBlocking((s, l, ps) -> false))
                )
                .addTextureM(modRes("block/glass/oak_diamond_bordered_glass"), EveryCompat.res("block/ch/glass/oak_diamond_bordered_glass_m"))
                .addTextureM(modRes("block/glass/ctm/oak_diamond_bordered_glass_ctm/0"), EveryCompat.res("block/ch/glass/ctm/oak_diamond_bordered_glass/0_mask"))
                .addTextureM(modRes("block/glass/ctm/oak_diamond_bordered_glass_ctm/1"), EveryCompat.res("block/ch/glass/ctm/oak_diamond_bordered_glass/1_mask"))
                .addTextureM(modRes("block/glass/ctm/oak_diamond_bordered_glass_ctm/2"), EveryCompat.res("block/ch/glass/ctm/oak_diamond_bordered_glass/2_mask"))
                .addTextureM(modRes("block/glass/ctm/oak_diamond_bordered_glass_ctm/3"), EveryCompat.res("block/ch/glass/ctm/oak_diamond_bordered_glass/3_mask"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.IMPERMEABLE, Registries.BLOCK)
                .addTag(modRes("glass"), Registries.BLOCK)
                .addTag(modRes("glass"), Registries.ITEM)
                .addTag(TagUtility.GLASS_TAG, Registries.BLOCK)
                .addTag(TagUtility.GLASS_TAG, Registries.ITEM)
                .setRenderType(RenderLayer.TRANSLUCENT)
                .setTabKey(tab)
                .build();
        this.addEntry(diamondBorderedGlass);

        horizontalLinedGlass = SimpleEntrySet.builder(WoodType.class, "horizontal_lined_glass",
                        getModBlock("oak_horizontal_lined_glass"), () -> VanillaWoodTypes.OAK,
                        w -> new TransparentBlock(Utils.copyPropertySafe(w.planks).strength(0.3F).sound(SoundType.GLASS)
                                .noOcclusion().isValidSpawn((s, l, ps, t) -> false).isRedstoneConductor((s, l, ps) -> false)
                                .isSuffocating((s, l, ps) -> false).isViewBlocking((s, l, ps) -> false))
                )
                .addTextureM(modRes("block/glass/oak_horizontal_lined_glass"), EveryCompat.res("block/ch/glass/oak_horizontal_lined_glass_m"))
                .addTextureM(modRes("block/glass/ctm/oak_horizontal_lined_glass_ctm/0"), EveryCompat.res("block/ch/glass/ctm/oak_horizontal_lined_glass/0_mask"))
                .addTextureM(modRes("block/glass/ctm/oak_horizontal_lined_glass_ctm/1"), EveryCompat.res("block/ch/glass/ctm/oak_horizontal_lined_glass/1_mask"))
                .addTextureM(modRes("block/glass/ctm/oak_horizontal_lined_glass_ctm/2"), EveryCompat.res("block/ch/glass/ctm/oak_horizontal_lined_glass/2_mask"))
                .addTextureM(modRes("block/glass/ctm/oak_horizontal_lined_glass_ctm/3"), EveryCompat.res("block/ch/glass/ctm/oak_horizontal_lined_glass/3_mask"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.IMPERMEABLE, Registries.BLOCK)
                .addTag(modRes("glass"), Registries.BLOCK)
                .addTag(modRes("glass"), Registries.ITEM)
                .addTag(TagUtility.GLASS_TAG, Registries.BLOCK)
                .addTag(TagUtility.GLASS_TAG, Registries.ITEM)
                .setRenderType(RenderLayer.TRANSLUCENT)
                .setTabKey(tab)
                .build();
        this.addEntry(horizontalLinedGlass);

        largeDiamondGlass = SimpleEntrySet.builder(WoodType.class, "large_diamond_glass",
                        getModBlock("oak_large_diamond_glass"), () -> VanillaWoodTypes.OAK,
                        w -> new TransparentBlock(Utils.copyPropertySafe(w.planks).strength(0.3F).sound(SoundType.GLASS)
                                .noOcclusion().isValidSpawn((s, l, ps, t) -> false).isRedstoneConductor((s, l, ps) -> false)
                                .isSuffocating((s, l, ps) -> false).isViewBlocking((s, l, ps) -> false))
                )
                .addTextureM(modRes("block/glass/oak_large_diamond_glass"), EveryCompat.res("block/ch/glass/oak_large_diamond_glass_m"))
                .addTextureM(modRes("block/glass/ctm/oak_large_diamond_glass_ctm/0"), EveryCompat.res("block/ch/glass/ctm/oak_large_diamond_glass/0_mask"))
                .addTextureM(modRes("block/glass/ctm/oak_large_diamond_glass_ctm/1"), EveryCompat.res("block/ch/glass/ctm/oak_large_diamond_glass/1_mask"))
                .addTextureM(modRes("block/glass/ctm/oak_large_diamond_glass_ctm/2"), EveryCompat.res("block/ch/glass/ctm/oak_large_diamond_glass/2_mask"))
                .addTextureM(modRes("block/glass/ctm/oak_large_diamond_glass_ctm/3"), EveryCompat.res("block/ch/glass/ctm/oak_large_diamond_glass/3_mask"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.IMPERMEABLE, Registries.BLOCK)
                .addTag(modRes("glass"), Registries.BLOCK)
                .addTag(modRes("glass"), Registries.ITEM)
                .addTag(TagUtility.GLASS_TAG, Registries.BLOCK)
                .addTag(TagUtility.GLASS_TAG, Registries.ITEM)
                .setRenderType(RenderLayer.TRANSLUCENT)
                .setTabKey(tab)
                .build();
        this.addEntry(largeDiamondGlass);

        lineBarredGlass = SimpleEntrySet.builder(WoodType.class, "line_bared_glass",
                        getModBlock("oak_line_bared_glass"), () -> VanillaWoodTypes.OAK,
                        w -> new TransparentBlock(Utils.copyPropertySafe(w.planks).strength(0.3F).sound(SoundType.GLASS)
                                .noOcclusion().isValidSpawn((s, l, ps, t) -> false).isRedstoneConductor((s, l, ps) -> false)
                                .isSuffocating((s, l, ps) -> false).isViewBlocking((s, l, ps) -> false))
                )
                .addTextureM(modRes("block/glass/oak_line_bared_glass"), EveryCompat.res("block/ch/glass/oak_line_bared_glass_m"))
                .addTexture(TextureInfo.of(modRes("block/glass/ctm/oak_line_bared_glass_ctm/0")).copyTexture())
                .addTextureM(modRes("block/glass/ctm/oak_line_bared_glass_ctm/1"), EveryCompat.res("block/ch/glass/ctm/oak_line_bared_glass/1_mask"))
                .addTextureM(modRes("block/glass/ctm/oak_line_bared_glass_ctm/2"), EveryCompat.res("block/ch/glass/ctm/oak_line_bared_glass/2_mask"))
                .addTextureM(modRes("block/glass/ctm/oak_line_bared_glass_ctm/3"), EveryCompat.res("block/ch/glass/ctm/oak_line_bared_glass/3_mask"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.IMPERMEABLE, Registries.BLOCK)
                .addTag(modRes("glass"), Registries.BLOCK)
                .addTag(modRes("glass"), Registries.ITEM)
                .addTag(TagUtility.GLASS_TAG, Registries.BLOCK)
                .addTag(TagUtility.GLASS_TAG, Registries.ITEM)
                .setRenderType(RenderLayer.TRANSLUCENT)
                .setTabKey(tab)
                .build();
        this.addEntry(lineBarredGlass);

        ornateBarredGlass = SimpleEntrySet.builder(WoodType.class, "ornate_bared_glass",
                        getModBlock("oak_ornate_bared_glass"), () -> VanillaWoodTypes.OAK,
                        w -> new TransparentBlock(Utils.copyPropertySafe(w.planks).strength(0.3F).sound(SoundType.GLASS)
                                .noOcclusion().isValidSpawn((s, l, ps, t) -> false).isRedstoneConductor((s, l, ps) -> false)
                                .isSuffocating((s, l, ps) -> false).isViewBlocking((s, l, ps) -> false))
                )
                .addTextureM(modRes("block/glass/oak_ornate_bared_glass"), EveryCompat.res("block/ch/glass/oak_ornate_bared_glass_m"))
                .addTexture(TextureInfo.of(modRes("block/glass/ctm/oak_ornate_bared_glass_ctm/0")).copyTexture())
                .addTextureM(modRes("block/glass/ctm/oak_ornate_bared_glass_ctm/1"), EveryCompat.res("block/ch/glass/ctm/oak_ornate_bared_glass/1_mask"))
                .addTextureM(modRes("block/glass/ctm/oak_ornate_bared_glass_ctm/2"), EveryCompat.res("block/ch/glass/ctm/oak_ornate_bared_glass/2_mask"))
                .addTextureM(modRes("block/glass/ctm/oak_ornate_bared_glass_ctm/3"), EveryCompat.res("block/ch/glass/ctm/oak_ornate_bared_glass/3_mask"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.IMPERMEABLE, Registries.BLOCK)
                .addTag(modRes("glass"), Registries.BLOCK)
                .addTag(modRes("glass"), Registries.ITEM)
                .addTag(TagUtility.GLASS_TAG, Registries.BLOCK)
                .addTag(TagUtility.GLASS_TAG, Registries.ITEM)
                .setRenderType(RenderLayer.TRANSLUCENT)
                .setTabKey(tab)
                .build();
        this.addEntry(ornateBarredGlass);

        snowflakeGlass = SimpleEntrySet.builder(WoodType.class, "snowflake_glass",
                        getModBlock("oak_snowflake_glass"), () -> VanillaWoodTypes.OAK,
                        w -> new TransparentBlock(Utils.copyPropertySafe(w.planks).strength(0.3F).sound(SoundType.GLASS)
                                .noOcclusion().isValidSpawn((s, l, ps, t) -> false).isRedstoneConductor((s, l, ps) -> false)
                                .isSuffocating((s, l, ps) -> false).isViewBlocking((s, l, ps) -> false))
                )
                .addTextureM(modRes("block/glass/oak_snowflake_glass"), EveryCompat.res("block/ch/glass/oak_snowflake_glass_m"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.IMPERMEABLE, Registries.BLOCK)
                .addTag(modRes("glass"), Registries.BLOCK)
                .addTag(modRes("glass"), Registries.ITEM)
                .addTag(TagUtility.GLASS_TAG, Registries.BLOCK)
                .addTag(TagUtility.GLASS_TAG, Registries.ITEM)
                .setRenderType(RenderLayer.TRANSLUCENT)
                .setTabKey(tab)
                .build();
        this.addEntry(snowflakeGlass);

        wovenGlass = SimpleEntrySet.builder(WoodType.class, "woven_glass",
                        getModBlock("oak_woven_glass"), () -> VanillaWoodTypes.OAK,
                        w -> new TransparentBlock(Utils.copyPropertySafe(w.planks).strength(0.3F).sound(SoundType.GLASS)
                                .noOcclusion().isValidSpawn((s, l, ps, t) -> false).isRedstoneConductor((s, l, ps) -> false)
                                .isSuffocating((s, l, ps) -> false).isViewBlocking((s, l, ps) -> false))
                )
                .addTextureM(modRes("block/glass/oak_woven_glass"), EveryCompat.res("block/ch/glass/oak_woven_glass_m"))
                .addTextureM(modRes("block/glass/ctm/oak_woven_glass_ctm/0"), EveryCompat.res("block/ch/glass/ctm/oak_woven_glass/0_mask"))
                .addTextureM(modRes("block/glass/ctm/oak_woven_glass_ctm/1"), EveryCompat.res("block/ch/glass/ctm/oak_woven_glass/1_mask"))
                .addTextureM(modRes("block/glass/ctm/oak_woven_glass_ctm/2"), EveryCompat.res("block/ch/glass/ctm/oak_woven_glass/2_mask"))
                .addTextureM(modRes("block/glass/ctm/oak_woven_glass_ctm/3"), EveryCompat.res("block/ch/glass/ctm/oak_woven_glass/3_mask"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.IMPERMEABLE, Registries.BLOCK)
                .addTag(modRes("glass"), Registries.BLOCK)
                .addTag(modRes("glass"), Registries.ITEM)
                .addTag(TagUtility.GLASS_TAG, Registries.BLOCK)
                .addTag(TagUtility.GLASS_TAG, Registries.ITEM)
                .setRenderType(RenderLayer.TRANSLUCENT)
                .setTabKey(tab)
                .build();
        this.addEntry(wovenGlass);

        squareGlass = SimpleEntrySet.builder(WoodType.class, "glass", "square",
                        getModBlock("square_oak_glass"), () -> VanillaWoodTypes.OAK,
                        w -> new TransparentBlock(Utils.copyPropertySafe(w.planks).strength(0.3F).sound(SoundType.GLASS)
                                .noOcclusion().isValidSpawn((s, l, ps, t) -> false).isRedstoneConductor((s, l, ps) -> false)
                                .isSuffocating((s, l, ps) -> false).isViewBlocking((s, l, ps) -> false))
                )
                .addTextureM(modRes("block/glass/square_oak_glass"), EveryCompat.res("block/ch/glass/square_oak_glass_m"))
                .addTextureM(modRes("block/glass/ctm/square_oak_glass_ctm/0"), EveryCompat.res("block/ch/glass/ctm/square_oak_glass/0_mask"))
                .addTextureM(modRes("block/glass/ctm/square_oak_glass_ctm/1"), EveryCompat.res("block/ch/glass/ctm/square_oak_glass/1_mask"))
                .addTextureM(modRes("block/glass/ctm/square_oak_glass_ctm/2"), EveryCompat.res("block/ch/glass/ctm/square_oak_glass/2_mask"))
                .addTextureM(modRes("block/glass/ctm/square_oak_glass_ctm/3"), EveryCompat.res("block/ch/glass/ctm/square_oak_glass/3_mask"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.IMPERMEABLE, Registries.BLOCK)
                .addTag(modRes("glass"), Registries.BLOCK)
                .addTag(modRes("glass"), Registries.ITEM)
                .addTag(TagUtility.GLASS_TAG, Registries.BLOCK)
                .addTag(TagUtility.GLASS_TAG, Registries.ITEM)
                .setRenderType(RenderLayer.TRANSLUCENT)
                .setTabKey(tab)
                .build();
        this.addEntry(squareGlass);

        //TYPE glass_pane
        circleGlassPane = SimpleEntrySet.builder(WoodType.class, "glass_pane", "circle",
                        getModBlock("circle_oak_glass_pane"), () -> VanillaWoodTypes.OAK,
                        w -> new IronBarsBlock(Utils.copyPropertySafe(w.planks).strength(0.3F).sound(SoundType.GLASS).noOcclusion())
                )
                .requiresFromMap(circleGlass.blocks) //REASON: textures
                .addModelTransform(m -> m.replaceString("minecraft:block/glass_pane_top", "chipped:block/glass_pane/circle_oak_glass_pane_top"))
                // using the same glass texture
                .addTexture(modRes("block/glass_pane/circle_oak_glass_pane_top"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(ResourceLocation.fromNamespaceAndPath("diagonalwindows","non_diagonal_windows"), Registries.BLOCK) //REASON: Due to incompatible model
                .addTag(modRes("glass_pane"), Registries.BLOCK)
                .addTag(modRes("glass_pane"), Registries.ITEM)
                .addTag(TagUtility.GLASS_PANE_TAG, Registries.BLOCK)
                .addTag(TagUtility.GLASS_PANE_TAG, Registries.ITEM)
                .setRenderType(RenderLayer.TRANSLUCENT)
                .setTabKey(tab)
                .build();
        this.addEntry(circleGlassPane);

        barredGlassPane = SimpleEntrySet.builder(WoodType.class, "bared_glass_pane",
                        getModBlock("oak_bared_glass_pane"), () -> VanillaWoodTypes.OAK,
                        w -> new IronBarsBlock(Utils.copyPropertySafe(w.planks).strength(0.3F).sound(SoundType.GLASS).noOcclusion())
                )
                .requiresFromMap(barredGlass.blocks) //REASON: textures
                .addModelTransform(m -> m.replaceString("minecraft:block/glass_pane_top", "chipped:block/glass_pane/oak_bared_glass_pane_top"))
                // using the same glass texture
                .addTexture(modRes("block/glass_pane/oak_bared_glass_pane_top"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(ResourceLocation.fromNamespaceAndPath("diagonalwindows","non_diagonal_windows"), Registries.BLOCK) //REASON: Due to incompatible model
                .addTag(modRes("glass_pane"), Registries.BLOCK)
                .addTag(modRes("glass_pane"), Registries.ITEM)
                .addTag(TagUtility.GLASS_PANE_TAG, Registries.BLOCK)
                .addTag(TagUtility.GLASS_PANE_TAG, Registries.ITEM)
                .setRenderType(RenderLayer.TRANSLUCENT)
                .setTabKey(tab)
                .build();
        this.addEntry(barredGlassPane);

        borderedGlassPane = SimpleEntrySet.builder(WoodType.class, "bordered_glass_pane",
                        getModBlock("oak_bordered_glass_pane"), () -> VanillaWoodTypes.OAK,
                        w -> new IronBarsBlock(Utils.copyPropertySafe(w.planks).strength(0.3F).sound(SoundType.GLASS).noOcclusion())
                )
                .requiresFromMap(borderedGlass.blocks) //REASON: textures
                .addModelTransform(m -> m.replaceString("minecraft:block/glass_pane_top", "chipped:block/glass_pane/oak_bordered_glass_pane_top"))
                // using the same glass texture
                .addTexture(modRes("block/glass_pane/oak_bordered_glass_pane_top"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(ResourceLocation.fromNamespaceAndPath("diagonalwindows","non_diagonal_windows"), Registries.BLOCK) //REASON: Due to incompatible model
                .addTag(modRes("glass_pane"), Registries.BLOCK)
                .addTag(modRes("glass_pane"), Registries.ITEM)
                .addTag(TagUtility.GLASS_PANE_TAG, Registries.BLOCK)
                .addTag(TagUtility.GLASS_PANE_TAG, Registries.ITEM)
                .setRenderType(RenderLayer.TRANSLUCENT)
                .setTabKey(tab)
                .build();
        this.addEntry(borderedGlassPane);

        diamondBorderedGlassPane = SimpleEntrySet.builder(WoodType.class, "diamond_bordered_glass_pane",
                        getModBlock("oak_diamond_bordered_glass_pane"), () -> VanillaWoodTypes.OAK,
                        w -> new IronBarsBlock(Utils.copyPropertySafe(w.planks).strength(0.3F).sound(SoundType.GLASS).noOcclusion())
                )
                .requiresFromMap(diamondBorderedGlass.blocks) //REASON: textures
                .addModelTransform(m -> m.replaceString("minecraft:block/glass_pane_top", "chipped:block/glass_pane/oak_diamond_bordered_glass_pane_top"))
                // using the same glass texture
                .addTexture(modRes("block/glass_pane/oak_diamond_bordered_glass_pane_top"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(ResourceLocation.fromNamespaceAndPath("diagonalwindows","non_diagonal_windows"), Registries.BLOCK) //REASON: Due to incompatible model
                .addTag(modRes("glass_pane"), Registries.BLOCK)
                .addTag(modRes("glass_pane"), Registries.ITEM)
                .addTag(TagUtility.GLASS_PANE_TAG, Registries.BLOCK)
                .addTag(TagUtility.GLASS_PANE_TAG, Registries.ITEM)
                .setRenderType(RenderLayer.TRANSLUCENT)
                .setTabKey(tab)
                .build();
        this.addEntry(diamondBorderedGlassPane);

        horizontalLinedGlassPane = SimpleEntrySet.builder(WoodType.class, "horizontal_lined_glass_pane",
                        getModBlock("oak_horizontal_lined_glass_pane"), () -> VanillaWoodTypes.OAK,
                        w -> new IronBarsBlock(Utils.copyPropertySafe(w.planks).strength(0.3F).sound(SoundType.GLASS).noOcclusion())
                )
                .requiresFromMap(horizontalLinedGlass.blocks) //REASON: textures
                .addModelTransform(m -> m.replaceString("minecraft:block/glass_pane_top", "chipped:block/glass_pane/oak_horizontal_lined_glass_pane_top"))
                // using the same glass texture
                .addTexture(modRes("block/glass_pane/oak_horizontal_lined_glass_pane_top"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(  ResourceLocation.fromNamespaceAndPath("diagonalwindows","non_diagonal_windows"), Registries.BLOCK) //REASON: Due to incompatible model
                .addTag(modRes("glass_pane"), Registries.BLOCK)
                .addTag(modRes("glass_pane"), Registries.ITEM)
                .addTag(TagUtility.GLASS_PANE_TAG, Registries.BLOCK)
                .addTag(TagUtility.GLASS_PANE_TAG, Registries.ITEM)
                .setRenderType(RenderLayer.TRANSLUCENT)
                .setTabKey(tab)
                .build();
        this.addEntry(horizontalLinedGlassPane);

        largeDiamondGlassPane = SimpleEntrySet.builder(WoodType.class, "large_diamond_glass_pane",
                        getModBlock("oak_large_diamond_glass_pane"), () -> VanillaWoodTypes.OAK,
                        w -> new IronBarsBlock(Utils.copyPropertySafe(w.planks).strength(0.3F).sound(SoundType.GLASS).noOcclusion())
                )
                .requiresFromMap(largeDiamondGlass.blocks) //REASON: textures
                .addModelTransform(m -> m.replaceString("minecraft:block/glass_pane_top", "chipped:block/glass_pane/oak_large_diamond_glass_pane_top"))
                // using the same glass texture
                .addTexture(modRes("block/glass_pane/oak_large_diamond_glass_pane_top"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(  ResourceLocation.fromNamespaceAndPath("diagonalwindows","non_diagonal_windows"), Registries.BLOCK) //REASON: Due to incompatible model
                .addTag(modRes("glass_pane"), Registries.BLOCK)
                .addTag(modRes("glass_pane"), Registries.ITEM)
                .addTag(TagUtility.GLASS_PANE_TAG, Registries.BLOCK)
                .addTag(TagUtility.GLASS_PANE_TAG, Registries.ITEM)
                .setRenderType(RenderLayer.TRANSLUCENT)
                .setTabKey(tab)
                .build();
        this.addEntry(largeDiamondGlassPane);

        lineBarredGlassPane = SimpleEntrySet.builder(WoodType.class, "line_bared_glass_pane",
                        getModBlock("oak_line_bared_glass_pane"), () -> VanillaWoodTypes.OAK,
                        w -> new IronBarsBlock(Utils.copyPropertySafe(w.planks).strength(0.3F).sound(SoundType.GLASS).noOcclusion())
                )
                .requiresFromMap(lineBarredGlass.blocks) //REASON: textures
                .addModelTransform(m -> m.replaceString("minecraft:block/glass_pane_top", "chipped:block/glass_pane/oak_line_bared_glass_pane_top"))
                // using the same glass texture
                .addTexture(modRes("block/glass_pane/oak_line_bared_glass_pane_top"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(ResourceLocation.fromNamespaceAndPath("diagonalwindows","non_diagonal_windows"), Registries.BLOCK) //REASON: Due to incompatible model
                .addTag(modRes("glass_pane"), Registries.BLOCK)
                .addTag(modRes("glass_pane"), Registries.ITEM)
                .addTag(TagUtility.GLASS_PANE_TAG, Registries.BLOCK)
                .addTag(TagUtility.GLASS_PANE_TAG, Registries.ITEM)
                .setRenderType(RenderLayer.TRANSLUCENT)
                .setTabKey(tab)
                .build();
        this.addEntry(lineBarredGlassPane);

        ornateBarredGlassPane = SimpleEntrySet.builder(WoodType.class, "ornate_bared_glass_pane",
                        getModBlock("oak_ornate_bared_glass_pane"), () -> VanillaWoodTypes.OAK,
                        w -> new IronBarsBlock(Utils.copyPropertySafe(w.planks).strength(0.3F).sound(SoundType.GLASS).noOcclusion())
                )
                .requiresFromMap(ornateBarredGlass.blocks) //REASON: textures
                .addModelTransform(m -> m.replaceString("minecraft:block/glass_pane_top", "chipped:block/glass_pane/oak_ornate_bared_glass_pane_top"))
                // using the same glass texture
                .addTexture(modRes("block/glass_pane/oak_ornate_bared_glass_pane_top"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(ResourceLocation.fromNamespaceAndPath("diagonalwindows","non_diagonal_windows"), Registries.BLOCK) //REASON: Due to incompatible model
                .addTag(modRes("glass_pane"), Registries.BLOCK)
                .addTag(modRes("glass_pane"), Registries.ITEM)
                .addTag(TagUtility.GLASS_PANE_TAG, Registries.BLOCK)
                .addTag(TagUtility.GLASS_PANE_TAG, Registries.ITEM)
                .setRenderType(RenderLayer.TRANSLUCENT)
                .setTabKey(tab)
                .build();
        this.addEntry(ornateBarredGlassPane);

        snowflakeGlassPane = SimpleEntrySet.builder(WoodType.class, "snowflake_glass_pane",
                        getModBlock("oak_snowflake_glass_pane"), () -> VanillaWoodTypes.OAK,
                        w -> new IronBarsBlock(Utils.copyPropertySafe(w.planks).strength(0.3F).sound(SoundType.GLASS).noOcclusion())
                )
                .requiresFromMap(snowflakeGlass.blocks) //REASON: textures
                .addModelTransform(m -> m.replaceString("minecraft:block/glass_pane_top", "chipped:block/glass_pane/oak_snowflake_glass_pane_top"))
                // using the same glass texture
                .addTexture(modRes("block/glass_pane/oak_snowflake_glass_pane_top"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(ResourceLocation.fromNamespaceAndPath("diagonalwindows","non_diagonal_windows"), Registries.BLOCK) //REASON: Due to incompatible model
                .addTag(modRes("glass_pane"), Registries.BLOCK)
                .addTag(modRes("glass_pane"), Registries.ITEM)
                .addTag(TagUtility.GLASS_PANE_TAG, Registries.BLOCK)
                .addTag(TagUtility.GLASS_PANE_TAG, Registries.ITEM)
                .setRenderType(RenderLayer.TRANSLUCENT)
                .setTabKey(tab)
                .build();
        this.addEntry(snowflakeGlassPane);

        wovenGlassPane = SimpleEntrySet.builder(WoodType.class, "woven_glass_pane",
                        getModBlock("oak_woven_glass_pane"), () -> VanillaWoodTypes.OAK,
                        w -> new IronBarsBlock(Utils.copyPropertySafe(w.planks).strength(0.3F).sound(SoundType.GLASS).noOcclusion())
                )
                .requiresFromMap(wovenGlass.blocks) //REASON: textures
                .addModelTransform(m -> m.replaceString("minecraft:block/glass_pane_top", "chipped:block/glass_pane/oak_woven_glass_pane_top"))
                // using the same glass texture
                .addTexture(modRes("block/glass_pane/oak_woven_glass_pane_top"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(ResourceLocation.fromNamespaceAndPath("diagonalwindows","non_diagonal_windows"), Registries.BLOCK) //REASON: Due to incompatible model
                .addTag(modRes("glass_pane"), Registries.BLOCK)
                .addTag(modRes("glass_pane"), Registries.ITEM)
                .addTag(TagUtility.GLASS_PANE_TAG, Registries.BLOCK)
                .addTag(TagUtility.GLASS_PANE_TAG, Registries.ITEM)
                .setRenderType(RenderLayer.TRANSLUCENT)
                .setTabKey(tab)
                .build();
        this.addEntry(wovenGlassPane);

        squareGlassPane = SimpleEntrySet.builder(WoodType.class, "glass_pane", "square",
                        getModBlock("square_oak_glass_pane"), () -> VanillaWoodTypes.OAK,
                        w -> new IronBarsBlock(Utils.copyPropertySafe(w.planks).strength(0.3F).sound(SoundType.GLASS).noOcclusion())
                )
                .requiresFromMap(squareGlass.blocks) //REASON: textures
                .addModelTransform(m -> m.replaceString("minecraft:block/glass_pane_top", "chipped:block/glass_pane/square_oak_glass_pane_top"))
                // using the same glass texture
                .addTexture(modRes("block/glass_pane/square_oak_glass_pane_top"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(ResourceLocation.fromNamespaceAndPath("diagonalwindows","non_diagonal_windows"), Registries.BLOCK) //REASON: Due to incompatible model
                .addTag(modRes("glass_pane"), Registries.BLOCK)
                .addTag(modRes("glass_pane"), Registries.ITEM)
                .addTag(TagUtility.GLASS_PANE_TAG, Registries.BLOCK)
                .addTag(TagUtility.GLASS_PANE_TAG, Registries.ITEM)
                .setRenderType(RenderLayer.TRANSLUCENT)
                .setTabKey(tab)
                .build();
        this.addEntry(squareGlassPane);

    }
}