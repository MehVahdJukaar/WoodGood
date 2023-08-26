package net.mehvahdjukaar.every_compat.modules.the_new_shutters;

import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.stehschnitzel.shutter.ShutterMain;
import net.stehschnitzel.shutter.common.blocks.Shutter;
import net.stehschnitzel.shutter.init.BlockInit;


public class ShuttersModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> shutters;

    public ShuttersModule(String modId) {
        super(modId, "shu");
        CreativeModeTab tab = ShutterMain.SHUTTER_TAB;

        shutters = SimpleEntrySet.builder(WoodType.class, "shutter",
                        BlockInit.OAK_SHUTTER, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new Shutter(WoodGood.copySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/oak_shutter_lower"))
                .addTexture(modRes("block/oak_shutter_middle"))
                .addTexture(modRes("block/oak_shutter_normal"))
                .addTexture(modRes("block/oak_shutter_upper"))
                .addTexture(modRes("item/oak_shutter"))
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(shutters);
    }
}
