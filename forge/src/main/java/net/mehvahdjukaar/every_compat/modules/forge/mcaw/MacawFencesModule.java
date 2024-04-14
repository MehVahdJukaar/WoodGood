package net.mehvahdjukaar.every_compat.modules.forge.mcaw;

import com.mcwfences.kikoz.MacawsFences;
import com.mcwfences.kikoz.init.BlockInit;
import com.mcwfences.kikoz.init.TabInit;
import com.mcwfences.kikoz.objects.WiredFence;
import net.mehvahdjukaar.every_compat.EveryCompat;
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
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.WallBlock;

import java.util.Map;


//SUPPORT v1.1.1+
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

        var tab = TabInit.FENCEITEMGROUP;

        PICKET_FENCES = SimpleEntrySet.builder(WoodType.class, "picket_fence",
                        BlockInit.OAK_PICKET_FENCE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new FenceBlock(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.WOODEN_FENCES, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_FENCES, Registries.ITEM)
                .setTab(tab)
                .defaultRecipe()
                .build();
        this.addEntry(PICKET_FENCES);

        STOCKADE_FENCES = SimpleEntrySet.builder(WoodType.class, "stockade_fence",
                        BlockInit.OAK_STOCKADE_FENCE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new FenceBlock(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.WOODEN_FENCES, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_FENCES, Registries.ITEM)
                .setTab(tab)
                .defaultRecipe()
                .build();
        this.addEntry(STOCKADE_FENCES);

        HORSE_FENCES = SimpleEntrySet.builder(WoodType.class, "horse_fence",
                        BlockInit.OAK_HORSE_FENCE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new FenceBlock(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.WOODEN_FENCES, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_FENCES, Registries.ITEM)
                .setTab(tab)
                .defaultRecipe()
                .build();
        this.addEntry(HORSE_FENCES);

        WIRED_FENCES = SimpleEntrySet.builder(WoodType.class, "wired_fence",
                        BlockInit.OAK_WIRED_FENCE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WiredFence(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.WOODEN_FENCES, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_FENCES, Registries.ITEM)
                .setRenderType(() -> RenderType::cutout)
                .setTab(tab)
                .defaultRecipe()
                .build();
        this.addEntry(WIRED_FENCES);


        PYRAMID_GATES = SimpleEntrySet.builder(WoodType.class, "pyramid_gate",
                        BlockInit.OAK_PYRAMID_GATE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new FenceGateBlock(Utils.copyPropertySafe(w.planks), w.toVanillaOrOak()))
                .addTag(BlockTags.FENCE_GATES, Registries.BLOCK)
                .setTab(tab)
                .defaultRecipe()
                .build();
        this.addEntry(PYRAMID_GATES);

        HIGHLEY_GATES = SimpleEntrySet.builder(WoodType.class, "highley_gate",
                        BlockInit.OAK_HIGHLEY_GATE, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new FenceGateBlock(Utils.copyPropertySafe(w.planks),w.toVanillaOrOak()))
                .addTag(BlockTags.WOODEN_FENCES, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_FENCES, Registries.ITEM)
                .setTab(tab)
                .defaultRecipe()
                .build();
        this.addEntry(HIGHLEY_GATES);

        HEDGES = SimpleEntrySet.builder(LeavesType.class, "hedge",
                        BlockInit.OAK_HEDGE, () -> LeavesTypeRegistry.OAK_TYPE,
                        w -> new WallBlock(Utils.copyPropertySafe(w.leaves).lightLevel((s) -> 0)))
                .requiresChildren("leaves")
                .addTag(BlockTags.MINEABLE_WITH_HOE, Registries.BLOCK)
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .addTag(ItemTags.WALLS, Registries.ITEM)
                .setTab(tab)
                .defaultRecipe()
                .addModelTransform(m -> m.addModifier((s, id, l) -> {
                    /*
                    * EveryComp's code don't account for "mcwfences:block/oak_leaves" when the mod could have used
                    * "minecraft:block/oak_leaves" for texturing. idk why the dev use this way.
                    */
                    String namespace = l.getNamespace();
                    String typeName = l.getTypeName();
                    switch (namespace) {
                        case "chipped" -> {
                            return LeavesPath("","", s, l, true);
                        }
                        case "blue_skies" -> {
                            if (typeName.equals("comet"))
                                return LeavesPath("comet_leaves_grown", "leaves", s, l);

                            return LeavesPath("", "leaves", s, l);
                        }
                        case "regions_unexplored" -> {
                            switch (typeName) {
                                case "alpha" -> {
                                    return LeavesPath("alpha_oak_leaves", "", s, l);
                                }
                                case "apple_oak" -> {
                                    return LeavesPath("apple_oak_leaves_stage_0", "", s, l);
                                }
                                case "small_oak" -> {
                                    return s.replace("\"mcwfences:block/oak_leaves\"",
                                            "\"minecraft:block/oak_leaves\"");
                                }
                                case "flowering" -> {
                                    return s.replace("\"mcwfences:block/oak_leaves\"",
                                            "\"regions_unexplored:item/flowering_leaves\"");
                                }
                                case "palm" -> {
                                    return LeavesPath("palm_leaves_side", "", s, l);
                                }
                            }
                        }
                        case "endlessbiomes" -> {
                            switch (typeName) {
                                case "glowing_penumbral" -> {
                                    return LeavesPath("penumbralleavesglowing", "", s, l);
                                }
                                case "penumbral" -> {
                                    return LeavesPath("penumbralleavesnewest", "", s, l);
                                }
                            }
                        }
                        case "aether" -> {
                            return LeavesPath("", "natural", s, l);
                        }
                        case "aether_redux" -> {
                            if (typeName.equals("azure_fieldsproot")) {
                                return LeavesPath("fieldsproot_leaves", "natural", s, l);
                            }
                            return LeavesPath("", "natural", s, l);
                        }
                    }
                    return LeavesPath("", "", s, l);
                }))
                .build();
        this.addEntry(HEDGES);
    }

    public String LeavesPath(String leavesName, String folderName, String s, LeavesType l) {
        return LeavesPath(leavesName, folderName, s, l, false);
    }

    public String LeavesPath(String leavesName, String folderName, String s, LeavesType l, boolean has_CHIPPED) {
        String path = "\"" + l.getNamespace() + ":block/";
        String LeavesTypeName = l.getTypeName();
        String folder;
        if (!leavesName.isEmpty()) { // unique name for leaves texture
            if (!folderName.isEmpty()) path += folderName + "/";

            return s.replace("\"mcwfences:block/oak_leaves\"",
                    path + leavesName + "\"");
        }
        else if (!folderName.isEmpty()) { // only for blue_skies
            folder = folderName + "/";
        }
        else if (has_CHIPPED) { // only for chipped
            folder = LeavesTypeName.replaceAll(
                    "cherry_|frosted_|dead_|golden_|apple_|magenta_|flower_|red_|white_|orange_",
                    "") + "_leaves/";
        }
        else folder = "";

        return s.replace("\"mcwfences:block/oak_leaves\"",
                path + folder + LeavesTypeName + "_leaves\"");
    }

    @Override
    public void registerBlockColors(ClientHelper.BlockColorEvent event) {
        super.registerBlockColors(event);
        for (Map.Entry<LeavesType, Block> entry : HEDGES.blocks.entrySet()) {
            LeavesType t = entry.getKey();
            Block b = entry.getValue();
            if (t.getNamespace().equals("regions_unexplored") && t.getTypeName().equals("flowering")) {
                continue;
            }
            event.register((s, l, p, i) -> event.getColor(t.leaves.defaultBlockState(), l, p, i), b);
        }
    }

    @Override
    public void registerItemColors(ClientHelper.ItemColorEvent event) {
        super.registerItemColors(event);
        for (Map.Entry<LeavesType, Block> entry : HEDGES.blocks.entrySet()) {
            LeavesType t = entry.getKey();
            Block b = entry.getValue();
            if (t.getNamespace().equals("regions_unexplored") && t.getTypeName().equals("flowering")) {
                continue;
            }
            event.register((stack, tintIndex) -> event.getColor(new ItemStack(t.leaves), tintIndex), b.asItem());
        }
    }
}
