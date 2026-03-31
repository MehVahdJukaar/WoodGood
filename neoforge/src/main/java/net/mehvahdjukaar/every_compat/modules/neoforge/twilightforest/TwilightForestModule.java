package net.mehvahdjukaar.every_compat.modules.neoforge.twilightforest;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.ItemOnlyEntrySet;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jetbrains.annotations.NotNull;
import twilightforest.block.BanisterBlock;
import twilightforest.block.ClimbableHollowLogBlock;
import twilightforest.block.HorizontalHollowLogBlock;
import twilightforest.block.VerticalHollowLogBlock;
import twilightforest.enums.HollowLogVariants;
import twilightforest.init.TFBlocks;
import twilightforest.item.HollowLogItem;

import java.util.function.Supplier;

import static net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys.STRIPPED_LOG;

//SUPPORT: v4.8.3345+
public class TwilightForestModule extends EveryCompatModule {

    public final SimpleEntrySet<WoodType, BanisterBlock> banisters;
    public final SimpleEntrySet<WoodType, VerticalHollowLogBlock> hollow_log_vertical;
    public final SimpleEntrySet<WoodType, HorizontalHollowLogBlock> hollow_log_horizontal;
    public final SimpleEntrySet<WoodType, ClimbableHollowLogBlock> hollow_log_climbable;
    public final ItemOnlyEntrySet<WoodType, Item> hollow_log;

    public TwilightForestModule(String modId) {
        super(modId, "tf");
        Supplier<CreativeModeTab> tab = getTab(modRes("blocks"));

        banisters = SimpleEntrySet.builder(WoodType.class, "banister",
                        TFBlocks.OAK_BANISTER, () -> VanillaWoodTypes.OAK,
                        w -> new BanisterBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                //TEXTURES: planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("mineable_with_block_and_chain"), Registries.BLOCK)
                .addTag(modRes("banisters"), Registries.BLOCK, Registries.ITEM)
                .addRecipe(modRes("wood/oak_banister"))
                .copyParentDrop()
                .setTab(tab)
                .build();
        this.addEntry(banisters);

        hollow_log_horizontal = SimpleEntrySet.builder(WoodType.class, "log_horizontal", "hollow",
                        TFBlocks.HOLLOW_BIRCH_LOG_HORIZONTAL, () -> VanillaWoodTypes.BIRCH,
                        w -> new HorizontalHollowLogBlock(Utils.copyPropertySafe(w.log))
                )
                .requiresChildren(STRIPPED_LOG) //REASON: Textures
                //TEXTURES: stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("mineable_with_block_and_chain"), Registries.BLOCK)
                .addTag(modRes("hollow_logs_horizontal"), Registries.BLOCK)
                .addTag(modRes("hollow_logs"), Registries.BLOCK)
                .noItem().noTab() //REASON: it's using the hollow_log's tab/item as the main
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .build();
        this.addEntry(hollow_log_horizontal);

        hollow_log_vertical = SimpleEntrySet.builder(WoodType.class, "log_vertical", "hollow",
                        TFBlocks.HOLLOW_BIRCH_LOG_VERTICAL, () -> VanillaWoodTypes.BIRCH,
                        w -> {
                            var id = EveryCompat.res(this.shortenedId() + "/" + w.getVariantId("hollow", true) + "_log_climbable");
                            return new VerticalHollowLogBlock(Utils.copyPropertySafe(w.log), DeferredHolder.create(Registries.BLOCK, id));
                        })
                .requiresChildren(STRIPPED_LOG) //REASON: Textures
                //TEXTURES: stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("mineable_with_block_and_chain"), Registries.BLOCK)
                .addTag(modRes("hollow_logs_vertical"), Registries.BLOCK)
                .addTag(modRes("hollow_logs"), Registries.BLOCK)
                .noItem().noTab() //REASON: it's using the hollow_log's tab/item as the main
                .addRecipe(modRes("stonecutting/birch_log/hollow_birch_log"))
                .build();
        this.addEntry(hollow_log_vertical);

        hollow_log_climbable = SimpleEntrySet.builder(WoodType.class, "log_climbable", "hollow",
                        TFBlocks.HOLLOW_BIRCH_LOG_CLIMBABLE, () -> VanillaWoodTypes.BIRCH,
                        w -> new ClimbableHollowLogBlock(
                                DeferredHolder.create(Registries.BLOCK, Utils.getID(hollow_log_vertical.blocks.get(w))),
                                Utils.copyPropertySafe(w.log))
                )
                .requiresChildren(STRIPPED_LOG) //REASON: Textures
                //TEXTURES: stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.CLIMBABLE, Registries.BLOCK)
                .addTag(BlockTags.FALL_DAMAGE_RESETTING, Registries.BLOCK)
                .addTag(modRes("mineable_with_block_and_chain"), Registries.BLOCK)
                .addTag(modRes("hollow_logs_climbable"), Registries.BLOCK)
                .addTag(modRes("hollow_logs"), Registries.BLOCK)
                .noItem().noTab() //REASON: it's using the hollow_log's tab/item as the main
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .build();
        this.addEntry(hollow_log_climbable);

        hollow_log = ItemOnlyEntrySet.builder(WoodType.class, "log", "hollow",
                        getModItem("hollow_birch_log"), () -> VanillaWoodTypes.BIRCH,
                        w -> new HollowLogItem(
                                DeferredHolder.create(Registries.BLOCK, Utils.getID(hollow_log_horizontal.blocks.get(w))),
                                DeferredHolder.create(Registries.BLOCK, Utils.getID(hollow_log_vertical.blocks.get(w))),
                                DeferredHolder.create(Registries.BLOCK, Utils.getID(hollow_log_climbable.blocks.get(w))),
                                new Item.Properties())
                )
                .requiresFromMap(hollow_log_vertical.blocks)
                .requiresFromMap(hollow_log_horizontal.blocks)
                .requiresFromMap(hollow_log_climbable.blocks)
                .setTab(tab)
                //TEXTURES: stripped_log
                .addTag(modRes("hollow_logs_climbable"), Registries.BLOCK)
                .build();
        this.addEntry(hollow_log);

    }

    @Override
    public void registerBlockColors(ClientHelper.@NotNull BlockColorEvent event) {
        event.register(
                (s, l, pos, i) -> s.getValue(ClimbableHollowLogBlock.VARIANT) != HollowLogVariants.Climbable.VINE ? -1 :
                        l != null && pos != null ?
                                BiomeColors.getAverageFoliageColor(l, pos) : FoliageColor.getDefaultColor(),
                hollow_log_climbable.blocks.values().toArray(Block[]::new));
        event.register(
                (s, l, pos, i) -> l != null && pos != null ?
                        BiomeColors.getAverageGrassColor(l, pos) : -1,
                hollow_log_horizontal.blocks.values().toArray(Block[]::new));
    }

}
