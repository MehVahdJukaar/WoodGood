package net.mehvahdjukaar.every_compat.modules.pokecube;

import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.Registry;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import pokecube.core.init.ItemGenerator;
import pokecube.legends.PokecubeLegends;
import pokecube.legends.init.BlockInit;


public class PokecubeLegendsModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> DISTORTIC_PLANKS;
    public final SimpleEntrySet<WoodType, Block> DISTORTIC_STAIRS;
    public final SimpleEntrySet<WoodType, Block> DISTORTIC_SLABS;

    public PokecubeLegendsModule(String modId) {
        super(modId, "pcl");
        CreativeModeTab tab = PokecubeLegends.TAB_DECORATIONS;


        DISTORTIC_PLANKS = SimpleEntrySet.builder(WoodType.class, "planks", "distortic",
                        BlockInit.DISTORTIC_OAK_PLANKS, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addRecipe(modRes("dimensions/distorted_world/distortic_planks/distortic_oak_planks"))
                .addTag(modRes("legends_planks"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("legends_planks"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.PLANKS, Registry.ITEM_REGISTRY)
                .addTexture(modRes("block/distortic_oak_planks"))
                .setTab(() -> tab)
                .build();

        this.addEntry(DISTORTIC_PLANKS);

        DISTORTIC_STAIRS = SimpleEntrySet.builder(WoodType.class, "stairs", "distortic",
                        BlockInit.DISTORTIC_OAK_STAIRS, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ItemGenerator.GenericStairs(w.planks.defaultBlockState(), Utils.copyPropertySafe(w.planks)))
                .addRecipe(modRes("dimensions/distorted_world/distortic_planks/distortic_oak_stairs"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_STAIRS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.WOODEN_STAIRS, Registry.ITEM_REGISTRY)
                .addTexture(modRes("block/distortic_oak_planks"))
                .setTab(() -> tab)
                .build();

        this.addEntry(DISTORTIC_STAIRS);

        DISTORTIC_SLABS = SimpleEntrySet.builder(WoodType.class, "slab", "distortic",
                        BlockInit.DISTORTIC_OAK_SLAB, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new SlabBlock(Utils.copyPropertySafe(w.planks)))
                .addRecipe(modRes("dimensions/distorted_world/distortic_planks/distortic_oak_slab"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_SLABS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.WOODEN_SLABS, Registry.ITEM_REGISTRY)
                .addTexture(modRes("block/distortic_oak_planks"))
                .setTab(() -> tab)
                .build();

        this.addEntry(DISTORTIC_SLABS);
    }
}
