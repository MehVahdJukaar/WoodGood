package net.mehvahdjukaar.every_compat.modules.forge.mcaw;

import com.mcwfences.kikoz.MacawsFences;
import com.mcwfences.kikoz.init.BlockInit;
import com.mcwfences.kikoz.objects.WiredFence;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesType;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesTypeRegistry;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.WallBlock;


public class MacawFencesModule extends SimpleModule {

    public final SimpleEntrySet<LeavesType, Block> HEDGES;
    public final SimpleEntrySet<WoodType, Block> HIGHLEY_GATES;
    public final SimpleEntrySet<WoodType, Block> HORSE_FENCES;
    public final SimpleEntrySet<WoodType, Block> PICKET_FENCES;
    public final SimpleEntrySet<WoodType, Block> PYRAMID_GATES;
    public final SimpleEntrySet<WoodType, Block> STOCKADE_FENCES;
    public final SimpleEntrySet<WoodType, Block> WIRED_FENCES;

    public MacawFencesModule(String modId) {
        super(modId, "mcf");

        CreativeModeTab tab = MacawsFences.FencesItemGroup;

        PICKET_FENCES = SimpleEntrySet.builder(WoodType.class, "picket_fence",
                        BlockInit.OAK_PICKET_FENCE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new FenceBlock(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.WOODEN_FENCES, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.WOODEN_FENCES, Registry.ITEM_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(PICKET_FENCES);

        STOCKADE_FENCES = SimpleEntrySet.builder(WoodType.class, "stockade_fence",
                        BlockInit.OAK_STOCKADE_FENCE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new FenceBlock(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.WOODEN_FENCES, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.WOODEN_FENCES, Registry.ITEM_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(STOCKADE_FENCES);

        HORSE_FENCES = SimpleEntrySet.builder(WoodType.class, "horse_fence",
                        BlockInit.OAK_HORSE_FENCE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new FenceBlock(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.WOODEN_FENCES, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.WOODEN_FENCES, Registry.ITEM_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(HORSE_FENCES);

        WIRED_FENCES = SimpleEntrySet.builder(WoodType.class, "wired_fence",
                        BlockInit.OAK_WIRED_FENCE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WiredFence(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.WOODEN_FENCES, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.WOODEN_FENCES, Registry.ITEM_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(WIRED_FENCES);


        PYRAMID_GATES = SimpleEntrySet.builder(WoodType.class, "pyramid_gate",
                        BlockInit.OAK_PYRAMID_GATE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new FenceGateBlock(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.FENCE_GATES, Registry.BLOCK_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(PYRAMID_GATES);

        HIGHLEY_GATES = SimpleEntrySet.builder(WoodType.class, "highley_gate",
                        BlockInit.OAK_HIGHLEY_GATE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new FenceGateBlock(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.WOODEN_FENCES, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.WOODEN_FENCES, Registry.ITEM_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(HIGHLEY_GATES);

        HEDGES = SimpleEntrySet.builder(LeavesType.class, "hedge",
                        BlockInit.OAK_HEDGE, () -> LeavesTypeRegistry.OAK_TYPE,
                        w -> {
                            var l = w.getBlockOfThis("leaves");
                            if (l == null) return null;
                            return new WallBlock(Utils.copyPropertySafe(l).lightLevel((s) -> 0));
                        })
                .addModelTransform(m -> m.replaceWithTextureFromChild("mcwfences:block/oak_hedge",
                        "leaves", s -> !s.contains("/snow") && !s.contains("_snow")))
                .addTag(BlockTags.MINEABLE_WITH_HOE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WALLS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.WALLS, Registry.ITEM_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(HEDGES);
    }

    @Override
    public void registerBlockColors(ClientHelper.BlockColorEvent event) {
        super.registerBlockColors(event);
        HEDGES.blocks.forEach((t, b) -> {
            event.register((s, l, p, i) -> event.getColor(t.leaves.defaultBlockState(), l, p, i), b);
        });
    }

    @Override
    public void registerItemColors(ClientHelper.ItemColorEvent event) {
        super.registerItemColors(event);
        HEDGES.blocks.forEach((t, b) -> {
            event.register((stack, tintIndex) -> event.getColor(new ItemStack(t.leaves), tintIndex), b.asItem());
        });
    }
}
