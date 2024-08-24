package net.mehvahdjukaar.every_compat.modules.missing_wilds;

import me.ultrusmods.missingwilds.block.FallenLogBlock;
import me.ultrusmods.missingwilds.item.MissingWildsItemGroup;
import me.ultrusmods.missingwilds.register.MissingWildsBlocks;
import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.mehvahdjukaar.selene.block_set.wood.WoodTypeRegistry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

//SUPPORT: v1.1.0
public class MissingWildModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> FALLEN_LOGS;

    public MissingWildModule(String modId) {
        super(modId, "msw");

        FALLEN_LOGS = SimpleEntrySet.builder(WoodType.class, "log", "fallen",
                        MissingWildsBlocks.FALLEN_ACACIA_LOG, () -> WoodTypeRegistry.WOOD_TYPES.get(new ResourceLocation("acacia")),
                        ifHasChild(w -> new FallenLogBlock(WoodGood.copySafe(w.planks).strength(2.0F).noOcclusion()),
                                "stripped_log")
                )
                .addTag(modRes("fallen_logs"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("fallen_logs"), Registry.ITEM_REGISTRY)
                .setTab(() -> MissingWildsItemGroup.MISSING_WILDS)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();
        this.addEntry(FALLEN_LOGS);
    }

}
