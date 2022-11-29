package net.mehvahdjukaar.every_compat.modules.mcaw;

import com.mcwroofs.kikoz.MacawsRoofs;
import com.mcwroofs.kikoz.init.BlockInit;
import com.mcwroofs.kikoz.objects.roofs.BaseRoof;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;


public class MacawRoofsModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> ROOFS;

    public MacawRoofsModule(String modId) {
        super(modId, "mcr");
        CreativeModeTab tab = MacawsRoofs.ROOFSITEMGROUP;


        ROOFS = SimpleEntrySet.builder(WoodType.class, "roof",
                        BlockInit.OAK_ROOF, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BaseRoof(Blocks.OAK_PLANKS.defaultBlockState(), Utils.copyPropertySafe(w.log).strength(2.0F, 2.3F)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(ROOFS);
    }
}
