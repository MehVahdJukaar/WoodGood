package net.mehvahdjukaar.every_compat.modules.forge.variants;

import kittehmod.vct.blocks.VCTCraftingTableBlock;
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
import net.minecraft.world.level.block.SoundType;

import java.util.List;


public class VariantCraftingTablesModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> craftingTable;

    public VariantCraftingTablesModule(String modId) {
        super(modId, "vct");
        var tab = CreativeModeTabs.FUNCTIONAL_BLOCKS;

        craftingTable = SimpleEntrySet.builder(WoodType.class, "crafting_table",
                        getModBlock("spruce_crafting_table"),
                        () -> WoodTypeRegistry.getValue(new ResourceLocation("spruce")),
                        w -> new VCTCraftingTableBlock(Utils.copyPropertySafe(w.planks).strength(2.5F).sound(SoundType.WOOD)))
                .addTextureM(EveryCompat.res("block/spruce_crafting_table_front"), EveryCompat.res("block/vct/spruce_crafting_table_front_m"))
                .addTextureM(EveryCompat.res("block/spruce_crafting_table_side"), EveryCompat.res("block/vct/spruce_crafting_table_side_m"))
                .addTexture(EveryCompat.res("block/spruce_crafting_table_top"))
                .addTag(new ResourceLocation("forge:workbenches"), Registries.BLOCK)
                .addTag(new ResourceLocation("forge:workbench"), Registries.BLOCK)
                .addTag(new ResourceLocation("forge:workbenches"), Registries.ITEM)
                .addTag(new ResourceLocation("forge:workbench"), Registries.ITEM)
                .addTag(new ResourceLocation("charm:crafting_table"), Registries.ITEM)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(craftingTable);
    }

    @Override
    public List<String> getAlreadySupportedMods() {
        return List.of(
                "abundant_atmosphere", "ad_astra", "aether", "architects_palette", "ars_nouveau",
                "atmospheric", "autumnity", "biomemakeover", "biomesoplenty", "caupona",
                "caverns_and_chasms", "ceilands", "cobblemon", "colorfulazaleas", "darkerdepths",
                "deeperdarker", "ecologics", "endergetic", "endlessbiomes", "enhanced_mushrooms",
                "enlightened_end", "environmental", "extendedmushrooms", "forbidden_arcanus", "fruittrees",
                "gardens_of_the_dead", "goodending", "habitat", "hexcasting", "hexerei",
                "integrateddynamics", "malum", "morecraft", "nethers_exoticism", "newworld",
                "outer_end", "phantasm", "prehistoricfauna", "quark", "regions_unexplored",
                "silentgear", "snowyspirit", "tconstruct", "twigs", "twilightforest",
                "undergarden", "upgrade_aquatic", "windswept"
        );
    }
}
