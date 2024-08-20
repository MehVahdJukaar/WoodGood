package net.mehvahdjukaar.every_compat.modules.stylish_stiles;

import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Block;
import net.weaverfever.stylishstiles.block.custom.Stile;

import java.util.function.Supplier;

//SUPPORT: v1.1.1+
public class StylishStilesModule extends SimpleModule {
    public final SimpleEntrySet<WoodType, Block> STILE;

    public StylishStilesModule(String modId) {
        super(modId, "ss");

        //noinspection DataFlowIssue
        STILE = SimpleEntrySet.builder(WoodType.class, "stile",
                        getModBlock("oak_stile"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> registerIfFence(w, () -> new Stile(Utils.copyPropertySafe(w.getBlockOfThis("fence"))))
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
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_FENCES, Registries.BLOCK)
                .addTag(BlockTags.FENCES, Registries.BLOCK)
                .setTabKey(CreativeModeTabs.BUILDING_BLOCKS)
                .defaultRecipe()
                .build();
        this.addEntry(STILE);
    }

    // Is fence available, then register. Otherwise, will not register
    private <B extends Block> B registerIfFence(WoodType woodType, Supplier<B> supplier) {
        if (woodType.getBlockOfThis("fence") != null) {
            return supplier.get();
        }
        return null;
    }

}
