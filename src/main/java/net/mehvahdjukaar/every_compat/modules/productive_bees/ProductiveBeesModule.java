package net.mehvahdjukaar.every_compat.modules.productive_bees;

import cy.jdkdigital.productivebees.common.block.AdvancedBeehive;
import cy.jdkdigital.productivebees.common.block.ExpansionBox;
import cy.jdkdigital.productivebees.common.block.entity.AdvancedBeehiveBlockEntity;
import cy.jdkdigital.productivebees.common.block.entity.ExpansionBoxBlockEntity;
import cy.jdkdigital.productivebees.init.ModBlocks;
import cy.jdkdigital.productivebees.init.ModItemGroups;
import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.mehvahdjukaar.selene.block_set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.selene.client.asset_generators.textures.Palette;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public class ProductiveBeesModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> advancedBeehives;
    public final SimpleEntrySet<WoodType, Block> expansionBoxes;

    public ProductiveBeesModule(String modId) {
        super(modId, "pb");
        CreativeModeTab tab = ModItemGroups.PRODUCTIVE_BEES;

        expansionBoxes = SimpleEntrySet.builder(WoodType.class, "", "expansion_box",
                        ModBlocks.EXPANSION_BOX_SPRUCE, () -> WoodTypeRegistry.WOOD_TYPES.get(new ResourceLocation("spruce")),
                        w -> new ExpansionBox(Block.Properties.copy(Blocks.BEEHIVE)))
                // Textures are not generated for this, temporary until model overrides work
                .addModelTransform(m -> m.replaceString("buzzier_bees:block/spruce", "productivebees:block/spruce"))
                // END
                .addTexture(WoodGood.res("block/spruce_beehive_end"))
                .addTexture(WoodGood.res("block/advanced_beehive/horizontal/spruce_beehive_end_right"))
                .addTexture(WoodGood.res("block/advanced_beehive/horizontal/spruce_beehive_end_left"))

                // Using the same generated texture from Advanced Beehives'
                .createPaletteFromOak(this::removeDarkest)
                .addTag(modRes("expansion_boxes"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("expansion_boxes"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.BEEHIVES, Registry.BLOCK_REGISTRY)
                .addTile(ExpansionBoxBlockEntity::new)
                .addRecipe(modRes("boxes/expansion_box_spruce"))
                .setTab(() -> tab)
                .build();
        this.addEntry(expansionBoxes);

        advancedBeehives = SimpleEntrySet.builder(WoodType.class, "beehive", "advanced",
                        ModBlocks.ADVANCED_SPRUCE_BEEHIVE, () -> WoodTypeRegistry.WOOD_TYPES.get(new ResourceLocation("spruce")),
                        w -> new AdvancedBeehive(Block.Properties.copy(Blocks.BEEHIVE)))
                // Textures are not generated for this, temporary until model overrides work
                .addModelTransform(m -> m.replaceString("buzzier_bees:block/spruce", "productivebees:block/spruce"))
                // FRONT TOP
                .addTextureM(WoodGood.res("block/advanced_beehive/spruce_beehive_front"), WoodGood.res("block/advanced_beehive/mask/spruce_beehive_front_m"))
                .addTextureM(WoodGood.res("block/advanced_beehive/spruce_beehive_front_honey"), WoodGood.res("block/advanced_beehive/mask/spruce_beehive_front_honey_m"))
                .addTextureM(WoodGood.res("block/advanced_beehive/spruce_beehive_front_top_honey"), WoodGood.res("block/advanced_beehive/mask/spruce_beehive_front_top_honey_m"))
                // FRONT SIDE
                .addTextureM(WoodGood.res("block/advanced_beehive/horizontal/spruce_beehive_front_left"), WoodGood.res("block/advanced_beehive/mask/horizontal/spruce_beehive_front_left_m"))
                .addTextureM(WoodGood.res("block/advanced_beehive/horizontal/spruce_beehive_front_right"), WoodGood.res("block/advanced_beehive/mask/horizontal/spruce_beehive_front_right_m"))
                .addTextureM(WoodGood.res("block/advanced_beehive/horizontal/spruce_beehive_front_left_honey"), WoodGood.res("block/advanced_beehive/mask/horizontal/spruce_beehive_front_left_honey_m"))
                .addTextureM(WoodGood.res("block/advanced_beehive/horizontal/spruce_beehive_front_right_honey"), WoodGood.res("block/advanced_beehive/mask/horizontal/spruce_beehive_front_right_honey_m"))
                // SIDE
                .addTextureM(WoodGood.res("block/spruce_beehive_side"), WoodGood.res("block/spruce_beehive_side_m"))
                .addTextureM(WoodGood.res("block/advanced_beehive/horizontal/spruce_beehive_side_left"), WoodGood.res("block/advanced_beehive/mask/horizontal/spruce_beehive_side_left_m"))
                .addTextureM(WoodGood.res("block/advanced_beehive/horizontal/spruce_beehive_side_right"), WoodGood.res("block/advanced_beehive/mask/horizontal/spruce_beehive_side_right_m"))
                .addTextureM(WoodGood.res("block/advanced_beehive/spruce_beehive_side"), WoodGood.res("block/advanced_beehive/mask/spruce_beehive_side_m"))
                .addTextureM(WoodGood.res("block/advanced_beehive/spruce_beehive_side_top"), WoodGood.res("block/advanced_beehive/mask/spruce_beehive_side_top_m"))
                .addTextureM(WoodGood.res("block/advanced_beehive/spruce_beehive_side_top_honey"), WoodGood.res("block/advanced_beehive/mask/spruce_beehive_side_top_honey_m"))
                // FRONT
                .addTextureM(WoodGood.res("block/spruce_beehive_front"), WoodGood.res("block/spruce_beehive_front_m"))
                .addTextureM(WoodGood.res("block/spruce_beehive_front_honey"), WoodGood.res("block/spruce_beehive_front_honey_m"))

                .addTag(modRes("advanced_beehives"), Registry.ITEM_REGISTRY)
                .addTag(modRes("nests/slimy_nests"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("nests/sugarbag_nests"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("nests/soul_sands_nests"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("nests/nether_quartz_nests"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("nests/end_nests"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("nests/nether_brick_nests"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("nests/nether_gold_nests"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("nests/glowstone_nests"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("beehives"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.BEEHIVES, Registry.BLOCK_REGISTRY)
                .addTile(AdvancedBeehiveBlockEntity::new)
                // Having two recipes overwrites each other TODO: no actually it souldnt
                .addRecipe(modRes("hives/advanced_spruce_beehive_clear"))
                .addRecipe(modRes("hives/advanced_spruce_beehive"))
                .setTab(() -> tab)
                .build();
        this.addEntry(advancedBeehives);

    }

    private void removeDarkest(@NotNull Palette p) {
        p.remove(p.getDarkest());
        p.remove(p.getDarkest());
        p.remove(p.getDarkest());
    }


    @Override
    public List<String> getAlreadySupportedMods() {
        return List.of(
                "biomesoplenty", "atmospheric", "byg"
        );
    }
}
