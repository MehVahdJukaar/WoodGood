package net.mehvahdjukaar.every_compat.modules.forge.more;

import com.duart.mctb.blocks.CraftingBlock;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
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
public class MoreCraftingTablesModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> craftingTable;

    public MoreCraftingTablesModule(String modId) {
        super(modId, "mctb");
        var tab = CreativeModeTabs.FUNCTIONAL_BLOCKS;

        craftingTable = SimpleEntrySet.builder(WoodType.class, "crafting_table",
                        getModBlock("spruce_crafting_table"),
                        () -> WoodTypeRegistry.getValue(new ResourceLocation("spruce")),
                        w -> new CraftingBlock(Utils.copyPropertySafe(w.planks)))
                .addTextureM(EveryCompat.res("block/spruce_crafting_table_front"), EveryCompat.res("block/mctb/spruce_crafting_table_front_m"))
                .addTextureM(EveryCompat.res("block/spruce_crafting_table_side"), EveryCompat.res("block/mctb/spruce_crafting_table_side_m"))
                .addTextureM(EveryCompat.res("block/spruce_crafting_table_top"), EveryCompat.res("block/mctb/spruce_crafting_table_top_m"))
                .addTag(new ResourceLocation("forge:workbenches"), Registries.BLOCK)
                .addTag(new ResourceLocation("forge:workbench"), Registries.BLOCK)
                .addTag(new ResourceLocation("forge:workbenches"), Registries.ITEM)
                .addTag(new ResourceLocation("forge:workbench"), Registries.ITEM)
                .addTag(new ResourceLocation("charm:crafting_table"), Registries.ITEM)
                .addTag(modRes("revertable_workbench"), Registries.ITEM)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(() -> tab)
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
