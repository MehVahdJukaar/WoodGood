package net.mehvahdjukaar.every_compat.modules.fabric.missing_wilds;

import me.ultrusmods.missingwilds.block.FallenLogBlock;
import me.ultrusmods.missingwilds.register.MissingWildsBlocks;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

//SUPPORT v1.3.3+
public class MissingWildModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> fallenLogs;

    public MissingWildModule(String modId) {
        super(modId, "msw");

        fallenLogs = SimpleEntrySet.builder(WoodType.class, "log", "fallen",
                        getModBlock("fallen_acacia_log"), () -> WoodTypeRegistry.getValue(ResourceLocation.parse("acacia")),
                        w -> new FallenLogBlock(Utils.copyPropertySafe(w.log).noOcclusion()))
                .addTag(modRes("fallen_logs"), Registries.BLOCK)
                .addTag(modRes("fallen_logs"), Registries.ITEM)
                .setTabKey(modRes("items"))
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                //REASON: Take a look @ their's logs|stripped_logs' non-standard 16x16 texture, you'll get why
                .excludeBlockTypes("deeperdarker", "bloom")
                .excludeBlockTypes("terrestria", "(yucca_palm|sakura)")
                .build();
        this.addEntry(fallenLogs);
    }
}
