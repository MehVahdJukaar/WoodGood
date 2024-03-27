package net.mehvahdjukaar.every_compat.modules.forge.missing_wilds;

import me.ultrusmods.missingwilds.block.FallenLogBlock;
import me.ultrusmods.missingwilds.register.MissingWildsBlocks;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

//SUPPORT v1.2.0+
public class MissingWildModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> fallenLogs;

    public MissingWildModule(String modId) {
        super(modId, "msw");

        fallenLogs = SimpleEntrySet.builder(WoodType.class, "log", "fallen",
                        MissingWildsBlocks.FALLEN_ACACIA_LOG, () -> WoodTypeRegistry.getValue(new ResourceLocation("acacia")),
                        w -> new FallenLogBlock(Utils.copyPropertySafe(w.log).noOcclusion()))
                .addTag(modRes("fallen_logs"), Registries.BLOCK)
                .addTag(modRes("fallen_logs"), Registries.ITEM)
                .setTab(getModTab("items"))
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();
        this.addEntry(fallenLogs);
    }
}
