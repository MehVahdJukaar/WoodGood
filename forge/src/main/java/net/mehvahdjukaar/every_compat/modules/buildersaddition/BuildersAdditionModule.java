package net.mehvahdjukaar.every_compat.modules.buildersaddition;

import com.mcwpaths.kikoz.objects.FacingPathBlock;
import com.mrh0.buildersaddition.Index;
import com.mrh0.buildersaddition.blocks.VerticalSlab;
import com.mrh0.buildersaddition.itemgroup.ModGroup;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import vazkii.quark.base.Quark;


public class BuildersAdditionModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> PANELS;

    public BuildersAdditionModule(String modId) {
        super(modId, "bca");
        CreativeModeTab tab = ModGroup.MAIN;


        PANELS = SimpleEntrySet.builder(WoodType.class, "vertical_slab",
                        Index.OAK_VERTICAL_SLAB, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new VerticalSlab(shortenedId() + "/" + w.getAppendableId(), w.planks))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(new ResourceLocation(Quark.MOD_ID + ":wooden_vertical_slabs"), Registry.BLOCK_REGISTRY)
                .addTag(new ResourceLocation(Quark.MOD_ID + ":wooden_vertical_slabs"), Registry.ITEM_REGISTRY)
                .addTag(new ResourceLocation(Quark.MOD_ID + ":vertical_slab"), Registry.BLOCK_REGISTRY)
                .addTag(new ResourceLocation(Quark.MOD_ID + ":vertical_slab"), Registry.ITEM_REGISTRY)
                .addRecipe(modRes("block/vertical_slab/oak_vertical_slab"))
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();

        this.addEntry(PANELS);
    }
}
