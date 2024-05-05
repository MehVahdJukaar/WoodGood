package net.mehvahdjukaar.every_compat.modules.forge.variant_crafting_tables;

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
                "abundant_atmosphere", "ad_astra", "aether", "aether_redux",
                "alexscaves", "alloyed", "architects_palette", "atum",
                "aurorasdeco", "automaticdoors", "bambooeverything", "betterarcheology",
                "betterend", "betternether", "bewitchment", "biomancy",
                "biomemakeover", "biomesoplenty", "blocksplus", "blockus",
                "byg", "caupona", "ceilands", "charm",
                "chipped", "cinderscapes", "cobblemon", "colorfulazaleas",
                "copperoverhaul", "couplings", "create_things_and_misc", "createdeco",
                "darkerdepths", "deep_aether", "deeperdarker", "desolation",
                "doubledoors", "dustrial_decor", "ecologics", "enderscape",
                "endlessbiomes", "enhanced_mushrooms", "enlightened_end", "everythingcopper",
                "extendedmushrooms", "forbidden_arcanus", "fruittrees", "gardens_of_the_dead",
                "goodending", "graveyard", "hexcasting", "hexerei",
                "horizons", "integrateddynamics", "malum", "manyideas_doors",
                "mcwdoors", "modern_glass_doors", "morecraft", "ms",
                "mysticsbiomes", "nethers_exoticism", "newworld", "phantasm",
                "pokecube", "prehistoricfauna", "premium_wood", "promenade",
                "pyromancer", "quark", "regions_unexplored", "silentgear",
                "snowyspirit", "statement", "supplementaries", "tconstruct",
                "techreborn", "terraqueous", "terrestria", "traverse",
                "twilightforest", "undergarden", "vinery", "wilderwild",
                "windswept", "woodworks", "xps_additions", "yippee"
        );
    }
}
