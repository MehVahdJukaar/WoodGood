package net.mehvahdjukaar.every_compat.modules.mcaw;

import net.kikoz.mcwfences.init.BlockInit;
import net.kikoz.mcwfences.objects.FencesGroup;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.state.BlockState;


public class MacawFencesModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> PICKET_FENCES;
    public final SimpleEntrySet<WoodType, Block> HORSE_FENCES;
    public final SimpleEntrySet<WoodType, Block> STOCKADE_FENCES;
    public final SimpleEntrySet<WoodType, Block> WIRED_FENCES;


    public final SimpleEntrySet<WoodType, Block> PYRAMID_GATES;
    public final SimpleEntrySet<WoodType, Block> HIGHLEY_GATES;

    public MacawFencesModule(String modId) {
        super(modId, "mcf");

        PICKET_FENCES = SimpleEntrySet.builder(WoodType.class, "picket_fence",
                        () -> BlockInit.OAK_PICKET_FENCE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new FenceBlock(Utils.copyPropertySafe(w.planks).strength(2.0F, 3)))
                .addTag(BlockTags.WOODEN_FENCES, Registry.BLOCK_REGISTRY)
                .setTab(() -> FencesGroup.FENCESGROUP)
                .defaultRecipe()
                .build();

        this.addEntry(PICKET_FENCES);

        STOCKADE_FENCES = SimpleEntrySet.builder(WoodType.class, "stockade_fence",
                        () -> BlockInit.OAK_STOCKADE_FENCE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new FenceBlock(Utils.copyPropertySafe(w.planks).strength(2.0F, 3)))
                .addTag(BlockTags.WOODEN_FENCES, Registry.BLOCK_REGISTRY)
                .setTab(() -> FencesGroup.FENCESGROUP)
                .defaultRecipe()
                .build();

        this.addEntry(STOCKADE_FENCES);

        HORSE_FENCES = SimpleEntrySet.builder(WoodType.class, "horse_fence",
                        () -> BlockInit.OAK_HORSE_FENCE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new FenceBlock(Utils.copyPropertySafe(w.planks).strength(2.0F, 3)))
                .addTag(BlockTags.WOODEN_FENCES, Registry.BLOCK_REGISTRY)
                .setTab(() -> FencesGroup.FENCESGROUP)
                .defaultRecipe()
                .build();

        this.addEntry(HORSE_FENCES);

        WIRED_FENCES = SimpleEntrySet.builder(WoodType.class, "wired_fence",
                        () -> BlockInit.OAK_WIRED_FENCE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WiredFence(Utils.copyPropertySafe(w.planks).strength(1.5f, 2.5f)))
                .addTag(BlockTags.WOODEN_FENCES, Registry.BLOCK_REGISTRY)
                .setTab(() -> FencesGroup.FENCESGROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(WIRED_FENCES);


        PYRAMID_GATES = SimpleEntrySet.builder(WoodType.class, "pyramid_gate",
                        () -> BlockInit.OAK_PYRAMID_GATE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new FenceGateBlock(Utils.copyPropertySafe(w.planks).strength(2.0F, 3)))
                .addTag(BlockTags.FENCE_GATES, Registry.BLOCK_REGISTRY)
                .setTab(() -> FencesGroup.FENCESGROUP)
                .defaultRecipe()
                .build();

        this.addEntry(PYRAMID_GATES);

        HIGHLEY_GATES = SimpleEntrySet.builder(WoodType.class, "highley_gate",
                        () -> BlockInit.OAK_HIGHLEY_GATE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new FenceGateBlock(Utils.copyPropertySafe(w.planks).strength(2.0F, 3)))
                .addTag(BlockTags.WOODEN_FENCES, Registry.BLOCK_REGISTRY)
                .setTab(() -> FencesGroup.FENCESGROUP)
                .defaultRecipe()
                .build();

        this.addEntry(HIGHLEY_GATES);
    }


    public static class WiredFence extends FenceBlock {


        public WiredFence(Properties properties) {
            super(properties);
        }

        //changed. I couldn't help myself
        @Override
        public void entityInside(BlockState state, Level level, BlockPos blockPos, Entity entityIn) {
            if (state.getValue(NORTH) || state.getValue(SOUTH) || state.getValue(EAST) || state.getValue(WEST))
                entityIn.hurt(DamageSource.GENERIC, 2.0F);
        }
    }

}
