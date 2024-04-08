package net.mehvahdjukaar.every_compat.modules.fabric.mcaw;

import net.kikoz.mcwfences.init.BlockInit;
import net.kikoz.mcwfences.util.FencesGroup;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.platform.ClientPlatformHelper;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesType;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesTypeRegistry;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Map;

//SUPPORT v1.0.4
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

        PICKET_FENCES = SimpleEntrySet.builder(WoodType.class, "picket_fence",
                        () -> BlockInit.OAK_PICKET_FENCE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new FenceBlock(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.WOODEN_FENCES, Registry.BLOCK_REGISTRY)
                .setTab(() -> FencesGroup.FENCESGROUP)
                .defaultRecipe()
                .build();
        this.addEntry(PICKET_FENCES);

        STOCKADE_FENCES = SimpleEntrySet.builder(WoodType.class, "stockade_fence",
                        () -> BlockInit.OAK_STOCKADE_FENCE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new FenceBlock(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.WOODEN_FENCES, Registry.BLOCK_REGISTRY)
                .setTab(() -> FencesGroup.FENCESGROUP)
                .defaultRecipe()
                .build();
        this.addEntry(STOCKADE_FENCES);

        HORSE_FENCES = SimpleEntrySet.builder(WoodType.class, "horse_fence",
                        () -> BlockInit.OAK_HORSE_FENCE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new FenceBlock(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.WOODEN_FENCES, Registry.BLOCK_REGISTRY)
                .setTab(() -> FencesGroup.FENCESGROUP)
                .defaultRecipe()
                .addModelTransform(m -> m.addModifier((s, resLoc, w) -> {
                    if (w.getNamespace().equals("betternether") && w.getTypeName().equals("nether_reed"))
                        return s.replace("\"minecraft:block/stripped_oak_log\"",
                                "\"betternether:block/nether_reed_planks_side\"");
                    if (w.getNamespace().equals("regions_unexplored") && w.getTypeName().equals("sculkwood"))
                        return s.replace("\"minecraft:block/stripped_oak_log\"",
                                "\"regions_unexplored:block/sculkwood_log_dark\"");
                    return s;
                }))
                .build();
        this.addEntry(HORSE_FENCES);

        WIRED_FENCES = SimpleEntrySet.builder(WoodType.class, "wired_fence",
                        () -> BlockInit.OAK_WIRED_FENCE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WiredFence(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.WOODEN_FENCES, Registry.BLOCK_REGISTRY)
                .setTab(() -> FencesGroup.FENCESGROUP)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();
        this.addEntry(WIRED_FENCES);


        PYRAMID_GATES = SimpleEntrySet.builder(WoodType.class, "pyramid_gate",
                        () -> BlockInit.OAK_PYRAMID_GATE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new FenceGateBlock(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.FENCE_GATES, Registry.BLOCK_REGISTRY)
                .setTab(() -> FencesGroup.FENCESGROUP)
                .defaultRecipe()
                .build();
        this.addEntry(PYRAMID_GATES);

        HIGHLEY_GATES = SimpleEntrySet.builder(WoodType.class, "highley_gate",
                        () -> BlockInit.OAK_HIGHLEY_GATE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new FenceGateBlock(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.WOODEN_FENCES, Registry.BLOCK_REGISTRY)
                .setTab(() -> FencesGroup.FENCESGROUP)
                .defaultRecipe()
                .build();
        this.addEntry(HIGHLEY_GATES);

        HEDGES = SimpleEntrySet.builder(LeavesType.class, "hedge",
                        () -> BlockInit.OAK_HEDGE, () -> LeavesTypeRegistry.OAK_TYPE,
                        w -> {
                            var l = w.getBlockOfThis("leaves");
                            if (l == null || (w.getNamespace().equals("regions_unexplored")
                                && w.getTypeName().equals("flowering"))) return null;
                            return new WallBlock(Utils.copyPropertySafe(l).lightLevel((s) -> 0));
                        })
                .addModelTransform(m -> m.addModifier((s, id, l) -> {
                    /*
                     * EveryCompat's code don't account for "mcwfences:block/oak_leaves" when the mod could have used
                     * "minecraft:block/oak_leaves" for texturing. idk why the dev use this way.
                     */
                    String namespace = l.getNamespace();
                    String typeName = l.getTypeName();
                    switch (namespace) {
                        case "chipped" -> {
                            return LeavesPath("", s, l, "", true);
                        }
                        case "betterend" -> {
                            if (typeName.equals("lucernia")) return s.replace("\"mcwfences:block/oak_leaves\"",
                                                        "\"betterend:block/lucernia_leaves_1\"");
                        }
                        case "regions_unexplored" -> {
                            switch (typeName) {
                                case "alpha" -> {
                                    return LeavesPath("alpha_oak_leaves", s, l);
                                }
                                case "apple_oak" -> {
                                    return LeavesPath("apple_oak_leaves_stage_0", s, l);
                                }
                                case "palm" -> {
                                    return LeavesPath("palm_leaves_side", s, l);
                                }
                            }
                        }
                    }
                    return LeavesPath("", s, l);
                }))
                .addTag(BlockTags.MINEABLE_WITH_HOE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WALLS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.WALLS, Registry.ITEM_REGISTRY)
                .setTab(() -> FencesGroup.FENCESGROUP)
                .defaultRecipe()
                .build();
        this.addEntry(HEDGES);
    }


    public String LeavesPath(String leavesName, String s, LeavesType l) {
        return LeavesPath(leavesName, s, l,  "", false, false);
    }
    public String LeavesPath(String leavesName, String append, String s, LeavesType l) {
        return LeavesPath(leavesName, s, l,  "", false, false);
    }
    public String LeavesPath(String leavesName, String s, LeavesType l, String folderName, boolean useBLOCKS) {
        return LeavesPath(leavesName, s, l,  folderName, useBLOCKS, false);
    }

    public String LeavesPath(String leavesName, String s, LeavesType l, String folderName, boolean useBLOCKS, boolean has_CHIPPED) {
        String path =  "\"" + l.getNamespace();
        path += (useBLOCKS) ? ":blocks/" : ":block/";
        String TypeName = l.getTypeName();
        String folder;
        if (!leavesName.isEmpty()) {
            if (!folderName.isEmpty()) path += folderName + "/";

            return s.replace("\"mcwfences:block/oak_leaves\"",
                    path + leavesName + "\"");
        }
        else if (!folderName.isEmpty()) { // only for blue_skies
            folder = folderName + "/";
        }
        else if (has_CHIPPED) { // only for chipped
            folder = TypeName.replaceAll("cherry_|frosted_|dead_|golden_|apple_|magenta_|flower_|red_|white_",
                    "") + "/";
        }
        else folder = "";

        return s.replace("\"mcwfences:block/oak_leaves\"",
                path + folder + l.getTypeName() + "_leaves" + "\"");
    }

    @Override
    public void registerBlockColors(ClientPlatformHelper.BlockColorEvent event) {
        super.registerBlockColors(event);
        for (Map.Entry<LeavesType, Block> entry : HEDGES.blocks.entrySet()) {
            LeavesType t = entry.getKey();
            Block b = entry.getValue();
            String namespace = t.getNamespace();
            String typeName = t.getTypeName();
            if (namespace.equals("regions_unexplored")) {
                if (typeName.equals("flowering")) continue;
            }
            else if (namespace.equals("blue_skies")) {
                if (typeName.equals("cherry")) continue;
            }
            event.register((s, l, p, i) -> event.getColor(t.leaves.defaultBlockState(), l, p, i), b);
        }
    }

    @Override
    public void registerItemColors(ClientPlatformHelper.ItemColorEvent event) {
        super.registerItemColors(event);
        for (Map.Entry<LeavesType, Block> entry : HEDGES.blocks.entrySet()) {
            LeavesType t = entry.getKey();
            Block b = entry.getValue();
            String namespace = t.getNamespace();
            String typeName = t.getTypeName();
            if (namespace.equals("regions_unexplored")) {
                if (typeName.equals("flowering")) continue;
            }
            else if (namespace.equals("blue_skies")) {
                if (typeName.equals("cherry")) continue;
            }
            event.register((stack, tintIndex) -> event.getColor(new ItemStack(t.leaves), tintIndex), b.asItem());
        }
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
