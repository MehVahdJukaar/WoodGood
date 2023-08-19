package net.mehvahdjukaar.every_compat.modules.forge.architect_palette;

import architectspalette.content.blocks.BoardBlock;
import architectspalette.content.blocks.RailingBlock;
import architectspalette.core.ArchitectsPalette;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.block.ModStairBlock;
import net.mehvahdjukaar.moonlight.api.resources.textures.PaletteColor;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.Registry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.WallBlock;

public class ArchitectsPaletteModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> railings;
    public final SimpleEntrySet<WoodType, Block> boards;
    public final SimpleEntrySet<WoodType, Block> boardStairs;
    public final SimpleEntrySet<WoodType, Block> boardWalls;
    public final SimpleEntrySet<WoodType, Block> boardSlabs;

    public ArchitectsPaletteModule(String modId) {
        super(modId, "ap");

        railings = SimpleEntrySet.builder(WoodType.class, "railing",
                        () -> getModBlock("oak_railing"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new RailingBlock(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .defaultRecipe()
                .setTab(() -> CreativeModeTab.TAB_DECORATIONS)
                .build();

        this.addEntry(railings);

        boardSlabs = SimpleEntrySet.builder(WoodType.class, "board_slab",
                        () -> getModBlock("oak_board_slab"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new SlabBlock(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.SLABS, Registries.BLOCK)
                .addTag(BlockTags.SLABS, Registries.ITEM)
                .defaultRecipe()
                .copyParentDrop()
                .setTab(() -> CreativeModeTab.TAB_BUILDING_BLOCKS)
                .build();

        this.addEntry(boardSlabs);


        boardWalls = SimpleEntrySet.builder(WoodType.class, "board_wall",
                        () -> getModBlock("oak_board_wall"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WallBlock(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .addTag(BlockTags.WALLS, Registries.ITEM)
                .defaultRecipe()
                .setTab(() -> CreativeModeTab.TAB_BUILDING_BLOCKS)
                .build();

        this.addEntry(boardWalls);

        boards = SimpleEntrySet.builder(WoodType.class, "boards",
                        () -> getModBlock("oak_boards"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BoardBlock(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .defaultRecipe()
                .setTab(() -> CreativeModeTab.TAB_BUILDING_BLOCKS)
                .createPaletteFromOak(p -> {

                    while (p.size() > 7) {
                        p.remove(p.getDarkest());
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

        this.addEntry(boards);

        boardStairs = SimpleEntrySet.builder(WoodType.class, "board_stairs",
                        () -> getModBlock("oak_board_stairs"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ModStairBlock(() -> boards.blocks.get(w), Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.STAIRS, Registries.BLOCK)
                .addTag(BlockTags.STAIRS, Registries.ITEM)
                .defaultRecipe()
                .setTab(() -> CreativeModeTab.TAB_BUILDING_BLOCKS)
                .build();

        this.addEntry(boardStairs);
    }

}
