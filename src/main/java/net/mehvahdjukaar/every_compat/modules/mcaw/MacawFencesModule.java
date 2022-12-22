package net.mehvahdjukaar.every_compat.modules.mcaw;

import com.mcwfences.kikoz.MacawsFences;
import com.mcwfences.kikoz.init.BlockInit;
import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;


public class MacawFencesModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> PICKET_FENCES;
    public final SimpleEntrySet<WoodType, Block> HORSE_FENCES;
    public final SimpleEntrySet<WoodType, Block> STOCKADE_FENCES;
    public final SimpleEntrySet<WoodType, Block> WIRED_FENCES;


    public final SimpleEntrySet<WoodType, Block> PYRAMID_GATES;
    public final SimpleEntrySet<WoodType, Block> HIGHLEY_GATES;

    public MacawFencesModule(String modId) {
        super(modId, "mcf");
        CreativeModeTab tab = null;

        PICKET_FENCES = SimpleEntrySet.builder(WoodType.class,"picket_fence",
                        BlockInit.OAK_PICKET_FENCE, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new FenceBlock(WoodGood.copySafe(w.planks).strength(2.0F, 3)))
                .addTag(BlockTags.WOODEN_FENCES, Registry.BLOCK_REGISTRY)
                .setTab(()->tab)
                .defaultRecipe()
                .build();

        this.addEntry(PICKET_FENCES);

        STOCKADE_FENCES = SimpleEntrySet.builder(WoodType.class,"stockade_fence",
                        BlockInit.OAK_STOCKADE_FENCE, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new FenceBlock(WoodGood.copySafe(w.planks).strength(2.0F, 3)))
                .addTag(BlockTags.WOODEN_FENCES, Registry.BLOCK_REGISTRY)
                .setTab(()->tab)
                .defaultRecipe()
                .build();

        this.addEntry(STOCKADE_FENCES);

        HORSE_FENCES = SimpleEntrySet.builder(WoodType.class,"horse_fence",
                        BlockInit.OAK_HORSE_FENCE, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new FenceBlock(WoodGood.copySafe(w.planks).strength(2.0F, 3)))
                .addTag(BlockTags.WOODEN_FENCES, Registry.BLOCK_REGISTRY)
                .setTab(()->tab)
                .defaultRecipe()
                .build();

        this.addEntry(HORSE_FENCES);

        WIRED_FENCES = SimpleEntrySet.builder(WoodType.class,"wired_fence",
                        BlockInit.OAK_WIRED_FENCE, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new WiredFence(WoodGood.copySafe(w.planks).strength(1.5f, 2.5f)))
                .addTag(BlockTags.WOODEN_FENCES, Registry.BLOCK_REGISTRY)
                .setTab(()->tab)
                .defaultRecipe()
                .setRenderType(()-> RenderType::cutout)
                .build();

        this.addEntry(WIRED_FENCES);


        PYRAMID_GATES = SimpleEntrySet.builder(WoodType.class,"pyramid_gate",
                        BlockInit.OAK_PYRAMID_GATE, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new FenceGateBlock(WoodGood.copySafe(w.planks).strength(2.0F, 3)))
                .addTag(BlockTags.FENCE_GATES, Registry.BLOCK_REGISTRY)
                .setTab(()->tab)
                .defaultRecipe()
                .build();

        this.addEntry(PYRAMID_GATES);

        HIGHLEY_GATES = SimpleEntrySet.builder(WoodType.class,"highley_gate",
                        BlockInit.OAK_HIGHLEY_GATE, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new FenceGateBlock(WoodGood.copySafe(w.planks).strength(2.0F, 3)))
                .addTag(BlockTags.WOODEN_FENCES, Registry.BLOCK_REGISTRY)
                .setTab(()->tab)
                .defaultRecipe()
                .build();

        this.addEntry(HIGHLEY_GATES);
    }


    public static class WiredFence extends FenceBlock {


        public WiredFence(Properties p_53302_) {
            super(p_53302_);
        }

        //changed. I couldn't help myself
        @Override
        public void entityInside(BlockState state, Level worldIn, BlockPos pos, Entity entityIn) {
            if(state.getValue(NORTH) || state.getValue(SOUTH) || state.getValue(EAST) || state.getValue(WEST))
            entityIn.hurt(DamageSource.GENERIC, 2.0F);
        }
    }

}
