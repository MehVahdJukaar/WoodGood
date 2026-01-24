package net.mehvahdjukaar.every_compat.modules.neoforge.more;

import com.duart.mctb.blocks.CraftingBlock;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Block;

import java.util.List;

// SUPPORT: v7.0.2+
// NOTE: More Crafting Table for Forge! is developed by 852Duart/DaveDuart And is FORGE only
public class MoreCraftingTablesForForgeModule extends EveryCompatModule {

    public final SimpleEntrySet<WoodType, Block> craftingTable;

    public MoreCraftingTablesForForgeModule(String modId) {
        super(modId, "mctb");
        ResourceKey<CreativeModeTab> tab = CreativeModeTabs.FUNCTIONAL_BLOCKS;

        craftingTable = SimpleEntrySet.builder(WoodType.class, "crafting_table",
                        getModBlock("spruce_crafting_table"), () -> VanillaWoodTypes.SPRUCE,
                        w -> new CraftingBlock(Utils.copyPropertySafe(w.planks))
                )
                //TEXTURES: oak_craftng_table - BaseTexture
                .addTextureM(EveryCompat.res("block/spruce_crafting_table_front"), EveryCompat.res("block/mctb/spruce_crafting_table_front_m"))
                .addTextureM(EveryCompat.res("block/spruce_crafting_table_side"), EveryCompat.res("block/mctb/spruce_crafting_table_side_m"))
                .addTextureM(EveryCompat.res("block/spruce_crafting_table_top"), EveryCompat.res("block/mctb/spruce_crafting_table_top_m"))
                //TAG: forge:workbenches removed
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(ResourceLocation.parse("forge:workbench"), Registries.BLOCK)
                .addTag(ResourceLocation.parse("forge:workbenches"), Registries.BLOCK)
                .addTag(ResourceLocation.parse("charm:crafting_table"), Registries.ITEM)
                .addTag(modRes("revertable_workbench"), Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(craftingTable);
    }

    @Override
    public List<String> getAlreadySupportedMods() {
        return List.of(
                "ad_astra", "ars_nouveau", "atmospheric",
                "biomesoplenty", "blue_skies", "botania",
                "environmental", "forbidden_arcanus", "naturesaura",
                "outer_end", "quark", "regions_unexplored",
                "tconstruct", "traverse", "twilightforest",
                "undergarden"
        );

    }
}
