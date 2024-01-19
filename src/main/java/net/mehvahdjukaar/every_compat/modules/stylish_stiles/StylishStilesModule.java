package net.mehvahdjukaar.every_compat.modules.stylish_stiles;

import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.minecraft.core.Registry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.weaverfever.stylishstiles.block.ModBlocks;
import net.weaverfever.stylishstiles.block.custom.Stile;

import java.util.function.Supplier;

public class StylishStilesModule extends SimpleModule {
    public final SimpleEntrySet<WoodType, Block> STILE;

    public StylishStilesModule(String modId) {
        super(modId, "ss");

        STILE = SimpleEntrySet.builder(WoodType.class, "stile",
                ModBlocks.OAK_STILE, () -> WoodType.OAK_WOOD_TYPE,
                w -> registerIfFence(w, () -> new Stile(WoodGood.copySafe(w.getBlockOfThis("fence"))))
        )
                .addModelTransform(m -> m.addModifier((s, id, w) -> {
                    if (w.getNamespace().equals("tfc")) {
                        return s.replace("\"model\": \"minecraft:block/oak_fence_side\"",
                                "\"model\": \"" + w.getNamespace() + ":block/wood/planks/" + w.getTypeName() + "_fence_side\"");
                    }
                    else {
                        return s.replace("\"model\": \"minecraft:block/oak_fence_side\"",
                                "\"model\": \"" + w.getNamespace() + ":block/" + w.getTypeName() + "_fence_side\"");
                    }
                }))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_FENCES, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.FENCES, Registry.BLOCK_REGISTRY)
                .setTab(() -> CreativeModeTab.TAB_BUILDING_BLOCKS)
                .defaultRecipe()
                .build();
        this.addEntry(STILE);
    }

    private <B extends Block> B registerIfFence(WoodType woodType, Supplier<B> supplier) {
        if (woodType.getBlockOfThis("fence") != null) {
            return supplier.get();
        }
        return null;
    }

}
