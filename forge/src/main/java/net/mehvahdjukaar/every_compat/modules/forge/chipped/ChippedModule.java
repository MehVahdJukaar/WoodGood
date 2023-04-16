package net.mehvahdjukaar.every_compat.modules.forge.chipped;

import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.Registry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;


public class ChippedModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> mosaic;

    public ChippedModule(String modId) {
        super(modId, "ch");
        CreativeModeTab tab = CreativeModeTab.TAB_BUILDING_BLOCKS;

        mosaic = SimpleEntrySet.builder(WoodType.class, "planks_mosaic",
                        () -> getModBlock("oak_planks_mosaic"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/oak_planks_mosaic"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.ITEM_REGISTRY)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(mosaic);
    }

    private void shutterPalette(Palette p) {
        p.remove(p.getDarkest());
        p.remove(p.getDarkest());
    }
}
