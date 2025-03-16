package net.mehvahdjukaar.every_compat.modules.neoforge.pokecube;

import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import pokecube.core.init.ItemGenerator;


public class PokecubeAOIModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> distorticPlanks;
    public final SimpleEntrySet<WoodType, Block> distorticStairs;
    public final SimpleEntrySet<WoodType, Block> distorticSlabs;

    public PokecubeAOIModule(String modId) {
        super(modId, "pcl");
        ResourceLocation tab = modRes("building_blocks_tab");

        distorticPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "distortic",
                        getModBlock("distortic_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addRecipe(modRes("dimensions/distorted_world/distortic_planks/distortic_oak_planks"))
                .addTag(modRes("legends_planks"), Registries.BLOCK)
                .addTag(modRes("legends_planks"), Registries.ITEM)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .addTexture(modRes("block/distortic_oak_planks"))
                .setTabKey(tab)
                .build();

        this.addEntry(distorticPlanks);

        distorticStairs = SimpleEntrySet.builder(WoodType.class, "stairs", "distortic",
                        getModBlock("distortic_oak_stairs"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ItemGenerator.GenericStairs(w.planks.defaultBlockState(), Utils.copyPropertySafe(w.planks)))
                .addRecipe(modRes("dimensions/distorted_world/distortic_planks/distortic_oak_stairs"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_STAIRS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_STAIRS, Registries.ITEM)
                .addTexture(modRes("block/distortic_oak_planks"))
                .setTabKey(tab)
                .build();

        this.addEntry(distorticStairs);

        distorticSlabs = SimpleEntrySet.builder(WoodType.class, "slab", "distortic",
                        getModBlock("distortic_oak_slab"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new SlabBlock(Utils.copyPropertySafe(w.planks)))
                .addRecipe(modRes("dimensions/distorted_world/distortic_planks/distortic_oak_slab"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_SLABS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_SLABS, Registries.ITEM)
                .addTexture(modRes("block/distortic_oak_planks"))
                .setTabKey(tab)
                .build();

        this.addEntry(distorticSlabs);
    }
}
