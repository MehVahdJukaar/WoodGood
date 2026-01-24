package net.mehvahdjukaar.every_compat.modules.fabric.mcaw;

import net.kikoz.mcwfences.MacawsFences;
import net.kikoz.mcwfences.init.BlockInit;
import net.kikoz.mcwfences.objects.FenceHitbox;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.misc.CompatSpritesHelper;
import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesType;
import net.mehvahdjukaar.moonlight.api.set.leaves.VanillaLeavesTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

//SUPPORT v1.1.1+
public class MacawFencesModule extends EveryCompatModule {

    public final SimpleEntrySet<WoodType, Block> highleyGates,
            horseFences,
            picketFences,
            pyramidGates,
            stockadeFences,
            wiredFences;
    public final SimpleEntrySet<LeavesType, Block> hedges;

    public MacawFencesModule(String modId) {
        super(modId, "mcf");
        ResourceKey<CreativeModeTab> tab = MacawsFences.FENCESGROUP;

        picketFences = SimpleEntrySet.builder(WoodType.class, "picket_fence",
                        () -> BlockInit.OAK_PICKET_FENCE, () -> VanillaWoodTypes.OAK,
                        w -> new FenceBlock(copyStandardProperties(w))
                )
                //TEXTURES: log, planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.FENCES, Registries.BLOCK)
                .addTag(ItemTags.FENCES, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .copyParentDrop() //REASON: ensure blocks's dropping when Diagonal Fences is installed
                //REASON: take a look at their //TEXTURES, you'll see why. Excluded!
                .excludeBlockTypes("terrestria", "sakura", "yucca_palm")
                .excludeBlockTypes("betternether", "nether_mushroom", "nether_reed")
                .build();
        this.addEntry(picketFences);

        stockadeFences = SimpleEntrySet.builder(WoodType.class, "stockade_fence",
                        () -> BlockInit.OAK_STOCKADE_FENCE, () -> VanillaWoodTypes.OAK,
                        w -> new FenceBlock(copyStandardProperties(w))
                )
                //TEXTURES: log, planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.FENCES, Registries.BLOCK)
                .addTag(ItemTags.FENCES, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .copyParentDrop() //REASON: ensure blocks's dropping when Diagonal Fences is installed
                //REASON: take a look at their //TEXTURES, you'll see why. Excluded!
                .excludeBlockTypes("terrestria", "sakura", "yucca_palm")
                .excludeBlockTypes("betternether", "nether_mushroom", "nether_reed")
                .build();
        this.addEntry(stockadeFences);

        horseFences = SimpleEntrySet.builder(WoodType.class, "horse_fence",
                        () -> BlockInit.OAK_HORSE_FENCE, () -> VanillaWoodTypes.OAK,
                        w -> new FenceBlock(copyStandardProperties(w))
                )
                .requiresChildren("stripped_log") //REASON: textures
                //TEXTURES: log, stripped_log (inventory), planks (inventory)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.FENCES, Registries.BLOCK)
                .addTag(ItemTags.FENCES, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .copyParentDrop() //REASON: ensure blocks's dropping when Diagonal Fences is installed
                //REASON: take a look at their //TEXTURES, you'll see why. Excluded!
                .excludeBlockTypes("terrestria", "sakura", "yucca_palm")
                .excludeBlockTypes("betternether", "nether_mushroom", "nether_reed")
                .build();
        this.addEntry(horseFences);

        wiredFences = SimpleEntrySet.builder(WoodType.class, "wired_fence",
                        () -> BlockInit.OAK_WIRED_FENCE, () -> VanillaWoodTypes.OAK,
                        w -> new WiredFence(copyStandardProperties(w))
                )
                //TEXTURES: log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.FENCES, Registries.BLOCK)
                .addTag(ItemTags.FENCES, Registries.ITEM)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT)
                .defaultRecipe()
                .copyParentDrop() //REASON: ensure blocks's dropping when Diagonal Fences is installed
                //REASON: take a look at their //TEXTURES, you'll see why. Excluded!
                .excludeBlockTypes("terrestria", "sakura", "yucca_palm")
                .excludeBlockTypes("betternether", "nether_mushroom", "nether_reed")
                .build();
        this.addEntry(wiredFences);

        pyramidGates = SimpleEntrySet.builder(WoodType.class, "pyramid_gate",
                        () -> BlockInit.OAK_PYRAMID_GATE, () -> VanillaWoodTypes.OAK,
                        w -> new FenceGateBlock(w.toVanillaOrOak(), copyStandardProperties(w))
                )
                //TEXTURES: log, planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.UNSTABLE_BOTTOM_CENTER, Registries.BLOCK)
                .addTag(BlockTags.FENCE_GATES, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .copyParentDrop() //REASON: ensure blocks's dropping when Diagonal Fences is installed
                //REASON: take a look at their //TEXTURES, you'll see why. Excluded!
                .excludeBlockTypes("terrestria", "sakura", "yucca_palm")
                .excludeBlockTypes("betternether", "nether_mushroom", "nether_reed")
                .build();
        this.addEntry(pyramidGates);

        highleyGates = SimpleEntrySet.builder(WoodType.class, "highley_gate",
                        () -> BlockInit.OAK_HIGHLEY_GATE, () -> VanillaWoodTypes.OAK,
                        w -> new FenceGateBlock(w.toVanillaOrOak(), copyStandardProperties(w))
                )
                //TEXTURES: log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.UNSTABLE_BOTTOM_CENTER, Registries.BLOCK)
                .addTag(BlockTags.FENCE_GATES, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .copyParentDrop() //REASON: ensure blocks's dropping when Diagonal Fences is installed
                //REASON: take a look at their //TEXTURES, you'll see why. Excluded!
                .excludeBlockTypes("terrestria", "sakura", "yucca_palm")
                .excludeBlockTypes("betternether", "nether_mushroom", "nether_reed")
                .build();
        this.addEntry(highleyGates);

        hedges = SimpleEntrySet.builder(LeavesType.class, "hedge",
                        () -> BlockInit.OAK_HEDGE, () -> VanillaLeavesTypes.OAK,
                        l -> new FenceHitbox(Utils.copyPropertySafe(l.leaves).lightLevel((s) -> 0)
                                .strength(0.2F, 0.3F).noOcclusion()
                                .mapColor(l.leaves.defaultMapColor()))
                )
                //TEXTURES: leaves
                .addModelTransform(m -> m.replaceWithTextureFromChild("mcwfences:block/oak_leaves",
                        "leaves", CompatSpritesHelper.LOOKS_LIKE_LEAF_TEXTURE))
                .addTag(BlockTags.MINEABLE_WITH_HOE, Registries.BLOCK)
                .addTag(BlockTags.FENCES, Registries.BLOCK)
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .addTag(ItemTags.WALLS, Registries.ITEM)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .copyParentDrop() //REASON: ensure blocks's dropping when Diagonal Fences is installed
                .copyParentTint()
                //REASON: Below have no leave texture
                .excludeBlockTypes("regions_unexplored", "flowering")
                .build();
        this.addEntry(hedges);
    }

    private BlockBehaviour.Properties copyStandardProperties(WoodType woodType) {
        return Utils.copyPropertySafe(woodType.planks).strength(1.4F, 2.0F).noOcclusion();
    }

    public static class WiredFence extends FenceBlock {

        public WiredFence(Properties properties) {
            super(properties);
        }

        //changed. I couldn't help myself
        @Override
        public void entityInside(BlockState state, @NotNull Level level, @NotNull BlockPos blockPos, @NotNull Entity entityIn) {
            if (state.getValue(NORTH) || state.getValue(SOUTH) || state.getValue(EAST) || state.getValue(WEST))
                entityIn.hurt(level.damageSources().generic(), 2.0F);
        }
    }

}
