package net.mehvahdjukaar.every_compat.modules.missing_wilds;

import me.ultrusmods.missingwilds.MissingWildsFabric;
import me.ultrusmods.missingwilds.block.FallenLogBlock;
import me.ultrusmods.missingwilds.register.MissingWildsBlocks;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

public class MissingWildModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> FALLEN_LOGS;

    public MissingWildModule(String modId) {
        super(modId, "msw");

        FALLEN_LOGS = SimpleEntrySet.builder(WoodType.class, "log", "fallen",
                        MissingWildsBlocks.FALLEN_ACACIA_LOG, () -> WoodTypeRegistry.getValue(new ResourceLocation("acacia")),
                        w -> new FallenLogBlock(Utils.copyPropertySafe(w.log).noOcclusion()))
                .addTag(modRes("fallen_logs"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("fallen_logs"), Registry.ITEM_REGISTRY)
                .setTab(() -> MissingWildsFabric.MISSING_WILD_ITEMS)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(FALLEN_LOGS);
    }
}
