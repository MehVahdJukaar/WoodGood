package net.mehvahdjukaar.every_compat.modules.neoforge.more;

import com.duart.mctb.blocks.CraftingBlock;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.misc.VanillaWoods;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Block;

import java.util.List;

// SUPPORT: v5.1.2+
// NOTE: More Crafting Table for Forge! is developed by Duart And is FORGE only
public class MoreCraftingTablesForForgeModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> craftingTable;

    public MoreCraftingTablesForForgeModule(String modId) {
        super(modId, "mctb");
        var tab = CreativeModeTabs.FUNCTIONAL_BLOCKS;

        craftingTable = SimpleEntrySet.builder(WoodType.class, "crafting_table",
                        getModBlock("spruce_crafting_table"),
                        () -> WoodTypeRegistry.getValue(VanillaWoods.SPRUCE),
                        w -> new CraftingBlock(Utils.copyPropertySafe(w.planks)))
                //TEXTURES: oak_craftng_table - BaseTexture
                .addTextureM(EveryCompat.res("block/spruce_crafting_table_front"), EveryCompat.res("block/mctb/spruce_crafting_table_front_m"))
                .addTextureM(EveryCompat.res("block/spruce_crafting_table_side"), EveryCompat.res("block/mctb/spruce_crafting_table_side_m"))
                .addTextureM(EveryCompat.res("block/spruce_crafting_table_top"), EveryCompat.res("block/mctb/spruce_crafting_table_top_m"))
                //TAG: forge:workbenches removed
                .addTag(ResourceLocation.parse("charm:crafting_table"), Registries.ITEM)
                .addTag(modRes("revertable_workbench"), Registries.ITEM)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();

        this.addEntry(craftingTable);
    }

    @Override
    public List<String> getAlreadySupportedMods() {
        return List.of(
                "biomesoplenty", "quark", "ad_astra",
                "naturesaura", "undergarden", "twilightforest",
                "regions_unexplored"
        );

    }
}
