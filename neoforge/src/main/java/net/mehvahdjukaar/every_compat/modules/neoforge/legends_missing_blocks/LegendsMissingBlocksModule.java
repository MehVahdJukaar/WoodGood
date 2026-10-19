package net.mehvahdjukaar.every_compat.modules.neoforge.legends_missing_blocks;

import net.legendstudios.missingblocks.block.CarvedAcaciaBlock;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

import static net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys.SLAB;

///SUPPORT: v2.0.0
public class LegendsMissingBlocksModule extends EveryCompatModule {

    public final SimpleEntrySet<WoodType, Block> carved;

    public LegendsMissingBlocksModule(String modId) {
        super(modId, "lmb");
        Supplier<CreativeModeTab> tab = getModTab(modId);

        carved = SimpleEntrySet.builder(WoodType.class, "", "carved",
                        getModBlock("carved_acacia"), () -> VanillaWoodTypes.ACACIA,
                        w -> new CarvedAcaciaBlock()
                )
                .requiresChildren(SLAB) //REASON: recipes
                .addTexture(modRes("block/carved_acacia"))
                .addTexture(modRes("block/carved_acacia_top"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTab(tab)
                .addRecipe(modRes("carved_acacia_craft"))
                .build();
        this.addEntry(carved);


    }
}