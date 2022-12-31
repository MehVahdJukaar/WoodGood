package net.mehvahdjukaar.every_compat.modules.exline;

import com.exline.barkcarpets.BarkCarpets;
import com.exline.barkcarpets.block.BarkCarpetBlock;
import com.exline.barkcarpets.init.BlockInit;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;


public class BarkCarpetsModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, BarkCarpetBlock> BARK_CARPETS;

    public BarkCarpetsModule(String modId) {
        super(modId, "bc");
        CreativeModeTab tab = BarkCarpets.ITEM_GROUP;


        BARK_CARPETS = SimpleEntrySet.builder(WoodType.class, "bark_carpet",
                        () -> BlockInit.OAK_BARK_CARPET, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BarkCarpetBlock(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(BARK_CARPETS);
    }
}
