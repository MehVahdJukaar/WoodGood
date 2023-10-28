package net.mehvahdjukaar.every_compat.modules.handcrafted;

import net.mehvahdjukaar.every_compat.api.SimpleModule;


public class HandcraftedModule extends SimpleModule {

    //public final SimpleEntrySet<WoodType, ChairBlock> chair;

    public HandcraftedModule(String modId) {
        super(modId, "hc");
        // var tab = ModItems.TAB;
/*
        chair = SimpleEntrySet.builder(WoodType.class, "chair",
                        ModBlocks.OAK_CHAIR, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ChairBlock(Utils.copyPropertySafe(w.planks).noOcclusion()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTexture(modRes("block/chair/oak_chair"))
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(chair);*/
    }
}
