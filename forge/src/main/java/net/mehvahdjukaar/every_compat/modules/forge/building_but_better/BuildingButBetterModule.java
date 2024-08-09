package net.mehvahdjukaar.every_compat.modules.forge.building_but_better;

import com.starfish_studios.bbb.block.FacingConnectingBlock;
import com.starfish_studios.bbb.block.LayerBlock;
import com.starfish_studios.bbb.item.DescriptionBlockItem;
import com.starfish_studios.bbb.registry.BBBBlocks;
import com.starfish_studios.bbb.registry.BBBTags.BBBBlockTags;
import com.starfish_studios.bbb.registry.BBBTags.BBBItemTags;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

public class BuildingButBetterModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> layers;
    public final SimpleEntrySet<WoodType, Block> trims;
//    public final SimpleEntrySet<WoodType, Block> beams;
//    public final SimpleEntrySet<WoodType, Block> beamStairs;
//    public final SimpleEntrySet<WoodType, Block> beamSlabs;
//    public final SimpleEntrySet<WoodType, Block> supports;
//    public final SimpleEntrySet<WoodType, Block> frames;
//    public final SimpleEntrySet<WoodType, Block> pallets;
//    public final SimpleEntrySet<WoodType, Block> lanterns;
//    public final SimpleEntrySet<WoodType, Block> ladders;
//    public final SimpleEntrySet<WoodType, Block> walls;

    public BuildingButBetterModule(String modId) {
        super(modId, "bbb");

        layers = SimpleEntrySet.builder(WoodType.class, "layer",
                BBBBlocks.OAK_LAYER, () -> WoodTypeRegistry.OAK_TYPE,
                w -> new LayerBlock(Utils.copyPropertySafe(w.planks)))
                .addTag(BBBItemTags.LAYERS, Registries.ITEM)
                .addTag(modRes("wooden_layers"), Registries.ITEM)
                .addTag(modRes("wooden_blocks"), Registries.BLOCK)
                .addTag(modRes("wooden_layers"), Registries.BLOCK)
                .addTag(BBBBlockTags.LAYERS, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addCustomItem((wood, block, properties) -> new DescriptionBlockItem(block, properties))
                .defaultRecipe()
                .copyParentDrop()
                .setTab(getModTab("item_group"))
                .addModelTransform(m -> m.replaceOakPlanks())
                .build();

        this.addEntry(layers);

        trims = SimpleEntrySet.builder(WoodType.class, "trim",
                BBBBlocks.OAK_TRIM, () -> WoodTypeRegistry.OAK_TYPE,
                w -> new FacingConnectingBlock(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/trim/oak_bottom"))
                .addTexture(modRes("block/trim/oak_middle"))
                .addTexture(modRes("block/trim/oak_none"))
                .addTexture(modRes("block/trim/oak_top"))
                .addTexture(modRes("block/trim/oak_top_face"))
                .addTag(modRes("wooden_blocks"), Registries.BLOCK)
                .addTag(modRes("trims"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .defaultRecipe()
                .setTab(getModTab("item_group"))
                .build();

        this.addEntry(trims);
    }
}
