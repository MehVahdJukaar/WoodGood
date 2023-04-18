package net.mehvahdjukaar.every_compat.modules.forge.premium_wood;

import net.mehvahdjukaar.every_compat.EveryCompat;
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
import net.minecraft.world.level.block.GlassBlock;


public class PremiumWoodModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> framedGlass;

    public PremiumWoodModule(String modId) {
        super(modId, "pw");
        CreativeModeTab tab = CreativeModeTab.TAB_BUILDING_BLOCKS;

        framedGlass = SimpleEntrySet.builder(WoodType.class, "framed_glass",
                        () -> getModBlock("magic_framed_glass"),
                        () -> WoodTypeRegistry.getValue(new ResourceLocation("premium_wood:magic")),
                        w -> new GlassBlock(Utils.copyPropertySafe(Blocks.GLASS)))
                .addTextureM(modRes("block/magic/magic_framed_glass"), EveryCompat.res("block/pw/magic_framed_glass_m"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("framed_glass"), Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(framedGlass);
    }
}
