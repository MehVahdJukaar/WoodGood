package net.mehvahdjukaar.every_compat.modules.twigs;

import com.ninni.twigs.block.TableBlock;
import net.mehvahdjukaar.every_compat.api.AbstractSimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.api.TabAddMode;
import net.mehvahdjukaar.every_compat.type.StoneType;
import net.mehvahdjukaar.every_compat.type.StoneTypeRegistry;
import net.mehvahdjukaar.moonlight.api.misc.Registrator;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.StairBlock;

import java.util.Collection;

public class TwigsModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> tables;
    public final SimpleEntrySet<StoneType, Block> columns;

    public TwigsModule(String modId) {
        super(modId, "tw");

        columns = addEntry(SimpleEntrySet.builder(StoneType.class, "column",
                        getModBlock("stone_column"), () -> StoneTypeRegistry.getValue(new ResourceLocation("stone")),
                        stoneType -> new StairBlock(stoneType.stone.defaultBlockState(), Utils.copyPropertySafe(stoneType.stone)))
                //TEXTURES: Using cut_andesite's from above
//                .setTabKey(() -> CreativeModeTabs.BUILDING_BLOCKS)
                .setRenderType(() -> RenderType::cutout)
//                .defaultRecipe()
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
                .setRenderType(() -> RenderType::cutout)
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
