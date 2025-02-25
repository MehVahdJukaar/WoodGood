package net.mehvahdjukaar.every_compat.modules.forge.architect_palette;

import architectspalette.content.blocks.RailingBlock;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.block.ModStairBlock;
import net.mehvahdjukaar.moonlight.api.resources.textures.PaletteColor;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.WallBlock;

//SUPPORT v1.3.6+
public class ArchitectsPaletteModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> railings;
    public final SimpleEntrySet<WoodType, Block> boards;
    public final SimpleEntrySet<WoodType, Block> boardSlabs;
    public final SimpleEntrySet<WoodType, Block> boardStairs;
    public final SimpleEntrySet<WoodType, Block> boardWalls;

    public ArchitectsPaletteModule(String modId) {
        super(modId, "ap");

        railings = SimpleEntrySet.builder(WoodType.class, "railing",
                        getModBlock("oak_railing"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new RailingBlock(Utils.copyPropertySafe(w.planks))
                )
                //TEXTURES: planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .defaultRecipe()
                .setTabKey( CreativeModeTabs.BUILDING_BLOCKS)
                .build();
        this.addEntry(railings);

        boards = SimpleEntrySet.builder(WoodType.class, "boards",
                        getModBlock("oak_boards"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks))
                )
                .createPaletteFromPlanks(p -> {

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
                    //lighten the other main plank mask if its too dark
                    if (newC.luminance() - before.luminance() > dl * 1.5) {
                        PaletteColor newBefore = new PaletteColor(before.lab().withLuminance(
                                (before.luminance() * 0.6f + (newC.luminance() + dl) * 0.4f)));
                        p.set(ind - 1, newBefore);
                    }

                })
                .addTexture(modRes("block/oak_boards"))
                .addTexture(modRes("block/oak_boards_odd"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .defaultRecipe()
                .setTabKey(CreativeModeTabs.BUILDING_BLOCKS)
                .build();
        this.addEntry(boards);

        boardSlabs = SimpleEntrySet.builder(WoodType.class, "board_slab",
                        getModBlock("oak_board_slab"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new SlabBlock(Utils.copyPropertySafe(w.planks))
                )
                .requiresFromMap(boards.blocks) //REASONS: textures, recipes
                //TEXTURES: boards
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.SLABS, Registries.BLOCK)
                .addTag(BlockTags.SLABS, Registries.ITEM)
                .defaultRecipe()
                .copyParentDrop()
                .setTabKey(CreativeModeTabs.BUILDING_BLOCKS)
                .build();
        this.addEntry(boardSlabs);

        boardStairs = SimpleEntrySet.builder(WoodType.class, "board_stairs",
                        getModBlock("oak_board_stairs"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ModStairBlock(() -> boards.blocks.get(w), Utils.copyPropertySafe(w.planks))
                )
                .requiresFromMap(boards.blocks) //REASONS: textures, recipes
                //TEXTURES: boards
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.STAIRS, Registries.BLOCK)
                .addTag(BlockTags.STAIRS, Registries.ITEM)
                .defaultRecipe()
                .setTabKey(CreativeModeTabs.BUILDING_BLOCKS)
                .addCondition(boards.blocks::containsKey)
                .build();
        this.addEntry(boardStairs);

        boardWalls = SimpleEntrySet.builder(WoodType.class, "board_wall",
                        getModBlock("oak_board_wall"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WallBlock(Utils.copyPropertySafe(w.planks))
                )
                .requiresFromMap(boards.blocks) //REASONS: textures, recipes
                //TEXTURES: boards
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .addTag(BlockTags.WALLS, Registries.ITEM)
                .defaultRecipe()
                .setTabKey(CreativeModeTabs.BUILDING_BLOCKS)
                .build();
        this.addEntry(boardWalls);

    }

}
