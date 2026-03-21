package net.mehvahdjukaar.every_compat.modules.macaw;

import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.misc.CompatSpritesHelper;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesType;
import net.mehvahdjukaar.moonlight.api.set.leaves.VanillaLeavesTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;

///SUPPORT v1.2.1+
public abstract class MacawFencesModuleAbstract extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> picketFences,
            stockadeFences,
            horseFences,
            wiredFences,
            pyramidGates,
            highleyGates;
    public final SimpleEntrySet<LeavesType, Block> hedges;

    public MacawFencesModuleAbstract(String modId) {
        super(modId, "mcf");
        ResourceLocation tab = getTabKey();

        picketFences = SimpleEntrySet.builder(WoodType.class, "picket_fence",
                        getModBlock("oak_picket_fence"), () -> VanillaWoodTypes.OAK,
                        this::newFenceBlock
                )
                //TEXTURES: log, planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.FENCES, Registries.BLOCK)
                .addTag(BlockTags.FENCES, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .copyParentDrop() //REASON: ensure blocks's dropping when Diagonal Fences is installed
                //REASON: take a look at their //TEXTURES, you'll see why. Excluded!
                .excludeBlockTypes("terrestria", "sakura", "yucca_palm")
                .excludeBlockTypes("betternether", "nether_mushroom", "nether_reed")
                .build();
        this.addEntry(picketFences);

        stockadeFences = SimpleEntrySet.builder(WoodType.class, "stockade_fence",
                        getModBlock("oak_stockade_fence"), () -> VanillaWoodTypes.OAK,
                        this::newFenceBlock
                )
                //TEXTURES: log, planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.FENCES, Registries.BLOCK)
                .addTag(BlockTags.FENCES, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .copyParentDrop() //REASON: ensure blocks's dropping when Diagonal Fences is installed
                //REASON: take a look at their //TEXTURES, you'll see why. Excluded!
                .excludeBlockTypes("terrestria", "sakura", "yucca_palm")
                .excludeBlockTypes("betternether", "nether_mushroom", "nether_reed")
                .build();
        this.addEntry(stockadeFences);

        horseFences = SimpleEntrySet.builder(WoodType.class, "horse_fence",
                        getModBlock("oak_horse_fence"), () -> VanillaWoodTypes.OAK,
                        this::newFenceBlock
                )
                .requiresChildren("stripped_log") //REASON: textures
                //TEXTURES: log, stripped_log (inventory), planks (inventory)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.FENCES, Registries.BLOCK)
                .addTag(BlockTags.FENCES, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .copyParentDrop() //REASON: ensure blocks's dropping when Diagonal Fences is installed
                //REASON: take a look at their //TEXTURES, you'll see why. Excluded!
                .excludeBlockTypes("terrestria", "sakura", "yucca_palm")
                .excludeBlockTypes("betternether", "nether_mushroom", "nether_reed")
                .build();
        this.addEntry(horseFences);

        wiredFences = SimpleEntrySet.builder(WoodType.class, "wired_fence",
                        getModBlock("oak_wired_fence"), () -> VanillaWoodTypes.OAK,
                        this::newWiredFence
                )
                //TEXTURES: log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.FENCES, Registries.BLOCK)
                .addTag(BlockTags.FENCES, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT)
                .copyParentDrop() //REASON: ensure blocks's dropping when Diagonal Fences is installed
                //REASON: take a look at their //TEXTURES, you'll see why. Excluded!
                .excludeBlockTypes("terrestria", "sakura", "yucca_palm")
                .excludeBlockTypes("betternether", "nether_mushroom", "nether_reed")
                .build();
        this.addEntry(wiredFences);

        pyramidGates = SimpleEntrySet.builder(WoodType.class, "pyramid_gate",
                        getModBlock("oak_pyramid_gate"), () -> VanillaWoodTypes.OAK,
                        this::newFenceGateBlock
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
                        getModBlock("oak_highley_gate"), () -> VanillaWoodTypes.OAK,
                        this::newFenceGateBlock
                )
                //TEXTURES: log
                .addTag(BlockTags.FENCE_GATES, Registries.BLOCK)
                .addTag(BlockTags.UNSTABLE_BOTTOM_CENTER, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .copyParentDrop() //REASON: ensure blocks's dropping when Diagonal Fences is installed
                //REASON: take a look at their //TEXTURES, you'll see why. Excluded!
                .excludeBlockTypes("terrestria", "sakura", "yucca_palm")
                .excludeBlockTypes("betternether", "nether_mushroom", "nether_reed")
                .build();
        this.addEntry(highleyGates);

        hedges = SimpleEntrySet.builder(LeavesType.class, "hedge",
                        getModBlock("oak_hedge"), () -> VanillaLeavesTypes.OAK,
                        this::newFenceHitbox
                )
                //TEXTURES: leaves
//                .addCondition(l -> !l.getId().toString().equals("regions_unexplored:apple_oak")) // there should be a way to fix the color of the leave being grey
                .addTag(BlockTags.MINEABLE_WITH_HOE, Registries.BLOCK)
                .addTag(BlockTags.FENCES, Registries.BLOCK)
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .addTag(ItemTags.WALLS, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .copyParentTint()
                .addModelTransform(m -> m.replaceWithTextureFromChild("mcwfences:block/oak_leaves",
                        "leaves", CompatSpritesHelper.LOOKS_LIKE_LEAF_TEXTURE))
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .copyParentDrop() //REASON: ensure blocks's dropping when Diagonal Fences is installed
                //REASON: Below have no leave texture
                .excludeBlockTypes("regions_unexplored", "flowering")
                .build();
        this.addEntry(hedges);
    }

    public abstract ResourceLocation getTabKey();

    public abstract Block newFenceBlock(WoodType woodType);
    public abstract Block newFenceGateBlock(WoodType woodType);
    public abstract Block newWiredFence(WoodType woodType);
    public abstract Block newFenceHitbox(LeavesType leavesType);

}
