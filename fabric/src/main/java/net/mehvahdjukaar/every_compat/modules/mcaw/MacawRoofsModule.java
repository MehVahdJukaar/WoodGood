package net.mehvahdjukaar.every_compat.modules.mcaw;

import net.kikoz.mcwroofs.init.BlockInit;
import net.kikoz.mcwroofs.objects.roofs.BaseRoof;
import net.kikoz.mcwroofs.objects.roofs.RoofGlass;
import net.kikoz.mcwroofs.objects.roofs.RoofTopNew;
import net.kikoz.mcwroofs.util.RoofGroup;
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

    public final SimpleEntrySet<WoodType, Block> ATTIC_ROOFS;
    public final SimpleEntrySet<WoodType, Block> ROOFS;
    public final SimpleEntrySet<WoodType, Block> TOP_ROOFS;

    public MacawRoofsModule(String modId) {
        super(modId, "mcr");
        CreativeModeTab tab = RoofGroup.ROOFGROUP;

        ATTIC_ROOFS = SimpleEntrySet.builder(WoodType.class, "attic_roof",
                        () -> BlockInit.OAK_ATTIC_ROOF, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new RoofGlass(Utils.copyPropertySafe(w.log).strength(2.0F, 2.3F)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutoutMipped)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(ATTIC_ROOFS);

        ROOFS = SimpleEntrySet.builder(WoodType.class, "roof",
                        () -> BlockInit.OAK_ROOF, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BaseRoof(Blocks.OAK_PLANKS.defaultBlockState(), Utils.copyPropertySafe(w.log).strength(2.0F, 2.3F)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutoutMipped)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(ROOFS);

        TOP_ROOFS = SimpleEntrySet.builder(WoodType.class, "top_roof",
                        () -> BlockInit.OAK_TOP_ROOF, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new RoofTopNew(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutoutMipped)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(TOP_ROOFS);
    }
}
