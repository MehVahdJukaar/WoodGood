package net.mehvahdjukaar.every_compat.modules.exline;

import com.exline.barkcarpets.block.BarkCarpetBlock;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;

import java.util.function.Supplier;


public class BarkCarpetsModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, BarkCarpetBlock> barkCarpets;

    public BarkCarpetsModule(String modId) {
        super(modId, "bc");
        Supplier<CreativeModeTab> tab =  getModTab("tab");


        barkCarpets = SimpleEntrySet.builder(WoodType.class, "bark_carpet",
                        ()->getModBlock("oak_bark_carpet", BarkCarpetBlock.class), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BarkCarpetBlock(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setRenderType(() -> RenderType::cutout)
                .setTab(tab)
                .defaultRecipe()
                .build();

        this.addEntry(barkCarpets);
    }
}
