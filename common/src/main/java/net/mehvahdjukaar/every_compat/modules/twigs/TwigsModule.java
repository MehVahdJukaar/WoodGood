package net.mehvahdjukaar.every_compat.modules.twigs;

import com.ninni.twigs.block.TableBlock;
import net.mehvahdjukaar.every_compat.api.*;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

//SUPPORT: v3.1.1+
public class TwigsModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> tables;

    public TwigsModule(String modId) {
        super(modId, "tw");

        tables = SimpleEntrySet.builder(WoodType.class, "table",
                        getModBlock("oak_table"), () -> VanillaWoodTypes.OAK,
                        w -> new TableBlock(Utils.copyPropertySafe(w.planks).instabreak()))
                .addTag(modRes("tables"), Registries.BLOCK)
                .addTag(modRes("tables"), Registries.ITEM)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .defaultRecipe()
                .setTabKey(modRes("twig"))
                .setTabMode(TabAddMode.AFTER_SAME_TYPE)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addTexture(modRes("block/oak_table"), PaletteStrategies.PLANKS_REMOVE_DARKEST)
                .addTexture(modRes("block/oak_table_top"), PaletteStrategies.PLANKS_REMOVE_DARKEST)
                .addTexture(modRes("block/oak_table_bottom"), PaletteStrategies.PLANKS_REMOVE_DARKEST)
                .build();

        this.addEntry(tables);
    }

}
