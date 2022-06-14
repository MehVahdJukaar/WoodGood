package net.mehvahdjukaar.every_compat.modules.architect_palette;

import architectspalette.content.blocks.BoardBlock;
import architectspalette.content.blocks.RailingBlock;
import architectspalette.core.registry.APBlocks;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.mehvahdjukaar.selene.client.asset_generators.textures.PaletteColor;
import net.minecraft.core.Registry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class ArchitectsPaletteModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> RAILINGS;
    public final SimpleEntrySet<WoodType, Block> BOARDS;

    public ArchitectsPaletteModule(String modId) {
        super(modId, "ap");

        RAILINGS = SimpleEntrySet.builder(WoodType.class,"railing",
                        APBlocks.OAK_RAILING, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new RailingBlock(BlockBehaviour.Properties.copy(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .defaultRecipe()
                .setTab(CreativeModeTab.TAB_DECORATIONS)
                .build();

        this.addEntry(RAILINGS);

        BOARDS = SimpleEntrySet.builder(WoodType.class,"boards",
                        APBlocks.OAK_BOARDS, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new BoardBlock(BlockBehaviour.Properties.copy(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .defaultRecipe()
                .setTab(CreativeModeTab.TAB_DECORATIONS)
                .createPaletteFromOak(p -> {

                    while (p.size() > 7) {
                        p.remove(0);
                    }

                    var col = p.getColorAtSlope(0.5f);
                    int ind = p.indexOf(col);
                    var lab = col.lab();
                    PaletteColor newC = new PaletteColor(lab.withLuminance(lab.luminance() * 1.03f));
                    float dl = p.get(ind + 1).luminance() - newC.luminance();
                    p.set(ind, newC);
                    PaletteColor before = p.get(ind - 1);
                    //lighten the other main plank color if its too dark
                    if (newC.luminance() - before.luminance() > dl * 1.5) {
                        PaletteColor newBefore = new PaletteColor(before.lab().withLuminance(
                                (before.luminance() * 0.6f + (newC.luminance() + dl) * 0.4f)));
                        p.set(ind - 1, newBefore);
                    }

                })
                .addTexture(modRes("block/oak_boards"))
                .addTexture(modRes("block/oak_boards_odd"))
                .build();

        this.addEntry(BOARDS);
    }

}
