package net.mehvahdjukaar.every_compat.modules.productive_bees;

import com.mcwpaths.kikoz.MacawsPaths;
import com.mcwpaths.kikoz.init.BlockInit;
import com.mcwpaths.kikoz.objects.FacingPathBlock;
import cy.jdkdigital.productivebees.ProductiveBees;
import cy.jdkdigital.productivebees.common.block.AdvancedBeehive;
import cy.jdkdigital.productivebees.init.ModBlocks;
import cy.jdkdigital.productivebees.init.ModItemGroups;
import cy.jdkdigital.productivebees.init.ModTags;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;


public class ProductiveBeesModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> ADVANCED_BEEHIVES;

    public ProductiveBeesModule(String modId) {
        super(modId, "pb");
        CreativeModeTab tab = ModItemGroups.PRODUCTIVE_BEES;


        ADVANCED_BEEHIVES = SimpleEntrySet.builder(WoodType.class, "beehive", "advanced",
                        ModBlocks.ADVANCED_SPRUCE_BEEHIVE, () -> WoodTypeRegistry.getValue(new ResourceLocation("spruce")),
                        w -> new AdvancedBeehive(Utils.copyPropertySafe(w.planks)))
                .addTextureM(EveryCompat.res("block/advanced_beehive/horizontal/spruce_beehive_front_right_honey"), EveryCompat.res("block/pb/advanced_beehive/horizontal/oak_beehive_front_right_honey_m"))
                .addTextureM(EveryCompat.res("block/advanced_beehive/horizontal/spruce_beehive_front_left_honey"), EveryCompat.res("block/pb/advanced_beehive/horizontal/oak_beehive_front_left_honey_m"))
                .addTextureM(EveryCompat.res("block/advanced_beehive/horizontal/spruce_beehive_front_right"), EveryCompat.res("block/pb/advanced_beehive/horizontal/oak_beehive_front_right_m"))
                .addTextureM(EveryCompat.res("block/advanced_beehive/horizontal/spruce_beehive_front_left"), EveryCompat.res("block/pb/advanced_beehive/horizontal/oak_beehive_front_left_m"))
                .addTextureM(EveryCompat.res("block/advanced_beehive/horizontal/spruce_beehive_side_right"), EveryCompat.res("block/pb/advanced_beehive/horizontal/oak_beehive_side_right_m"))
                .addTextureM(EveryCompat.res("block/advanced_beehive/horizontal/spruce_beehive_side_left"), EveryCompat.res("block/pb/advanced_beehive/horizontal/oak_beehive_side_left_m"))
                .addTextureM(EveryCompat.res("block/advanced_beehive/spruce_beehive_front_top_honey"), EveryCompat.res("block/pb/advanced_beehive/oak_beehive_front_top_honey_m"))
                .addTextureM(EveryCompat.res("block/advanced_beehive/spruce_beehive_side_top_honey"), EveryCompat.res("block/pb/advanced_beehive/oak_beehive_side_top_honey_m"))
                .addTextureM(EveryCompat.res("block/advanced_beehive/spruce_beehive_front_honey"), EveryCompat.res("block/pb/advanced_beehive/oak_beehive_front_honey_m"))
                .addTextureM(EveryCompat.res("block/advanced_beehive/spruce_beehive_side_top"), EveryCompat.res("block/pb/advanced_beehive/oak_beehive_side_top_m"))
                .addTextureM(EveryCompat.res("block/advanced_beehive/spruce_beehive_front"), EveryCompat.res("block/pb/advanced_beehive/oak_beehive_front_m"))
                .addTextureM(EveryCompat.res("block/advanced_beehive/spruce_beehive_side"), EveryCompat.res("block/pb/advanced_beehive/oak_beehive_side_m"))
                .addTexture(EveryCompat.res("block/advanced_beehive/horizontal/spruce_beehive_end_right"))
                .addTexture(EveryCompat.res("block/advanced_beehive/horizontal/spruce_beehive_end_left"))
                .addTextureM(EveryCompat.res("block/spruce_beehive_front_honey"), EveryCompat.res("block/spruce_beehive_front_honey_m"))
                .addTexture(EveryCompat.res("block/spruce_beehive_front"))
                .addTexture(EveryCompat.res("block/spruce_beehive_side"))
                .addTexture(EveryCompat.res("block/spruce_beehive_end"))
//                .addModelTransform(m -> m.replaceWithTextureFromChild(WoodTypeRegistry.getValue(new ResourceLocation("buzzier_bees:block/spruce_beehive_side")).toString(), WoodTypeRegistry.getValue(new ResourceLocation("block/spruce_beehive_side")).toString()))
//                .addModelTransform(m -> m.replaceString(new ResourceLocation("buzzier_bees:block/spruce").toString(), EveryCompat.ALL_WOODS.toString()))
//                .addModelTransform(m -> m.replaceWithTextureFromChild("buzzier_bees:block/spruce_beehive_side", EveryCompat.ALL_WOODS.toString()))
                .addModelTransform(m -> m.replaceString("buzzier_bees:block/spruce", "everycomp:block/spruce"))
                .addTag(modRes("advanced_beehives"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("advanced_beehives"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.BEEHIVES, Registry.BLOCK_REGISTRY)
                .addRecipe(modRes("hives/advanced_spruce_beehive_clear"))
                .addRecipe(modRes("hives/advanced_spruce_beehive"))
                .setTab(() -> tab)
                .build();

        this.addEntry(ADVANCED_BEEHIVES);
    }
}
