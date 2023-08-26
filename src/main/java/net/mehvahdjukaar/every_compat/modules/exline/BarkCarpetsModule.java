package net.mehvahdjukaar.every_compat.modules.exline;

import com.exline.barkcarpets.BarkCarpets;
import com.exline.barkcarpets.BlockInit;
import com.exline.barkcarpets.block.BarkCarpetBlock;
import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.mehvahdjukaar.selene.block_set.wood.WoodTypeRegistry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;


public class BarkCarpetsModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, BarkCarpetBlock> barkCarpets;

    public BarkCarpetsModule(String modId) {
        super(modId, "bc");
        CreativeModeTab tab = BarkCarpets.ITEM_GROUP;


        barkCarpets = SimpleEntrySet.builder(WoodType.class, "bark_carpet",
                        ()->getModBlock("oak_bark_carpet", BarkCarpetBlock.class), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new BarkCarpetBlock(WoodGood.copySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(barkCarpets);
    }
}
