package net.mehvahdjukaar.every_compat.modules.missing_wilds;

import me.ultrusmods.missingwilds.block.FallenLogBlock;
import me.ultrusmods.missingwilds.item.MissingWildsItemGroup;
import me.ultrusmods.missingwilds.register.MissingWildsBlocks;
import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class MissingWildModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> FALLEN_LOGS;

    public MissingWildModule(String modId) {
        super(modId, "msw");

        FALLEN_LOGS = SimpleEntrySet.builder(WoodType.class, "log", "fallen",
                        MissingWildsBlocks.FALLEN_OAK_LOG, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new FallenLogBlock(WoodGood.copySafe(w.planks).strength(2.0F).noOcclusion()))
                .addTag(modRes("fallen_logs"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("fallen_logs"), Registry.ITEM_REGISTRY)
                .setTab(() -> MissingWildsItemGroup.MISSING_WILDS)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(FALLEN_LOGS);
    }
}
