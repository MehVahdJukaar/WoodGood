package net.mehvahdjukaar.every_compat.modules.twigs;

import com.ninni.twigs.block.ColumnBlock;
import com.ninni.twigs.block.TableBlock;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.api.TabAddMode;
import net.mehvahdjukaar.every_compat.type.StoneType;
import net.mehvahdjukaar.every_compat.type.StoneTypeRegistry;
import net.mehvahdjukaar.moonlight.api.misc.Registrator;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.StairBlock;

import java.util.Collection;

public class TwigsModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> tables;
    public final SimpleEntrySet<StoneType, Block> columns;

    public TwigsModule(String modId) {
        super(modId, "tw");

        //noinspection DataFlowIssue
        columns = addEntry(SimpleEntrySet.builder(StoneType.class, "column",
                        getModBlock("stone_column"), () -> StoneTypeRegistry.getValue(new ResourceLocation("stone")),
                        stoneType -> new ColumnBlock(Utils.copyPropertySafe(
                                        (stoneType.getBlockOfThis("bricks") != null)
                                        ? stoneType.getBlockOfThis("bricks")
                                        : Blocks.STONE_BRICKS)
                                )
                        )
                .createPaletteFromChild(p -> p.changeSizeMatchingLuminanceSpan(0.3F),
                        "bricks")
                .addTexture(modRes("block/stone_column"))
                .addTexture(modRes("block/stone_column_bottom"))
                .addTexture(modRes("block/stone_column_tip"))
                .addTexture(modRes("block/stone_column_top"))
                .setTabKey(modRes("twig"))
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .addRecipe(modRes("stone_column_stonecutting"))
                .build()
        );


        tables = SimpleEntrySet.builder(WoodType.class, "table",
                        getModBlock("oak_table"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TableBlock(Utils.copyPropertySafe(w.planks).instabreak()))
                .addTag(modRes("tables"), Registries.BLOCK)
                .addTag(modRes("tables"), Registries.ITEM)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .defaultRecipe()
                .setTabKey(modRes("twig"))
                .setTabMode(TabAddMode.AFTER_SAME_TYPE)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .createPaletteFromOak((p) -> p.remove(p.getDarkest()))
                .addTexture(modRes("block/oak_table"))
                .addTexture(modRes("block/oak_table_top"))
                .addTexture(modRes("block/oak_table_bottom"))
                .build();

        this.addEntry(tables);
    }

    @Override
    public void registerStonesBlocks(Registrator<Block> registry, Collection<StoneType> leavesTypes) {
        super.registerStonesBlocks(registry, leavesTypes);
    }
}
