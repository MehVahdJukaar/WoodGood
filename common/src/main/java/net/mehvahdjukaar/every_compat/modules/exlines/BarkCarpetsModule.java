package net.mehvahdjukaar.every_compat.modules.exlines;
// DISABLED: Exlines Bark Carpets has no 1.21.1 NeoForge release on CurseForge
/*

import com.exline.barkcarpets.block.BarkCarpetBlock;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;

///SUPPORT: v1.3.0+
public class BarkCarpetsModule extends EveryCompatModule {

    public final SimpleEntrySet<WoodType, BarkCarpetBlock> barkCarpets;

    public BarkCarpetsModule(String modId) {
        super(modId, "bc");

        barkCarpets = SimpleEntrySet.builder(WoodType.class, "bark_carpet",
                        getModBlock("oak_bark_carpet", BarkCarpetBlock.class), () -> VanillaWoodTypes.OAK,
                        w -> new BarkCarpetBlock(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .setTab(getModTab("tab"))
                .defaultRecipe()
                .build();
        this.addEntry(barkCarpets);
    }
}
*/
