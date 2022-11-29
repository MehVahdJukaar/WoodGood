package net.mehvahdjukaar.every_compat.modules.mcaw;

import net.kikoz.mcwroofs.init.BlockInit;
import net.kikoz.mcwroofs.objects.roofs.BaseRoof;
import net.kikoz.mcwroofs.util.RoofGroup;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;


public class MacawRoofsModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> ROOFS;

    public MacawRoofsModule(String modId) {
        super(modId, "mcr");

        ROOFS = SimpleEntrySet.builder(WoodType.class, "planks_path",
                        () -> BlockInit.OAK_ROOF, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BaseRoof(Blocks.OAK_PLANKS.defaultBlockState(), Utils.copyPropertySafe(w.log).strength(2.0F, 2.3F)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutoutMipped)
                .setTab(() -> RoofGroup.ROOFGROUP)
                .defaultRecipe()
                .build();

        this.addEntry(ROOFS);
    }
}
