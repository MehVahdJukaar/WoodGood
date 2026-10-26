package net.mehvahdjukaar.every_compat.modules.missing_wilds;

import me.ultrusmods.missingwilds.block.FallenLogBlock;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;

///SUPPORT v4.0.0-BETA+
public class MissingWildModule extends EveryCompatModule {

    public final SimpleEntrySet<WoodType, Block> fallenLogs;

    public MissingWildModule(String modId) {
        super(modId, "msw");

        fallenLogs = SimpleEntrySet.builder(WoodType.class, "log", "fallen",
                        getModBlock("fallen_acacia_log"), () -> VanillaWoodTypes.ACACIA,
                        w -> new FallenLogBlock(Utils.copyPropertySafe(w.log).noOcclusion()))
                .addTag(modRes("fallen_logs"), Registries.BLOCK, Registries.ITEM)
                .setTab(getModTab("items"))
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .excludedWoodTypes() //REASON: look at its javadoc for the reason
                .build();
        this.addEntry(fallenLogs);
    }
}
