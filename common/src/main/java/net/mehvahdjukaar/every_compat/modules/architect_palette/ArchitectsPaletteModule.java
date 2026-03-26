package net.mehvahdjukaar.every_compat.modules.architect_palette;

import architectspalette.content.blocks.RailingBlock;
import net.mehvahdjukaar.every_compat.api.PaletteStrategies;
import net.mehvahdjukaar.every_compat.api.PaletteStrategy;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.moonlight.api.block.ModStairBlock;
import net.mehvahdjukaar.moonlight.api.resources.textures.PaletteColor;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.WallBlock;

import static net.mehvahdjukaar.every_compat.api.PaletteStrategies.registerCached;
import static net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys.PLANKS;

//SUPPORT: v1.4.0-Beta.5+
public class ArchitectsPaletteModule extends EveryCompatModule {

    public final SimpleEntrySet<WoodType, Block> railings;
    public final SimpleEntrySet<WoodType, Block> boards;
    public final SimpleEntrySet<WoodType, Block> boardSlabs;
    public final SimpleEntrySet<WoodType, Block> boardStairs;
    public final SimpleEntrySet<WoodType, Block> boardWalls;

    public ArchitectsPaletteModule(String modId) {
        super(modId, "ap");
        ResourceLocation tab = modRes("architects_palette");

        railings = SimpleEntrySet.builder(WoodType.class, "railing",
                        getModBlock("oak_railing"), () -> VanillaWoodTypes.OAK,
                        w -> new RailingBlock(Utils.copyPropertySafe(w.planks))
                )
                //TEXTURES: planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTab(getTab(tab))
                .defaultRecipe()
                .build();
        this.addEntry(railings);

        boards = SimpleEntrySet.builder(WoodType.class, "boards",
                        getModBlock("oak_boards"), () -> VanillaWoodTypes.OAK,
                        w -> new Block(Utils.copyPropertySafe(w.planks))
                )
                .addTexture(modRes("block/oak_boards"), customPalette)
                .addTexture(modRes("block/oak_boards_odd"), customPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTab(getTab(tab))
                .defaultRecipe()
                .build();
        this.addEntry(boards);

        boardSlabs = SimpleEntrySet.builder(WoodType.class, "board_slab",
                        getModBlock("oak_board_slab"), () -> VanillaWoodTypes.OAK,
                        w -> new SlabBlock(Utils.copyPropertySafe(w.planks))
                )
                .requiresFromMap(boards.blocks) //REASONS: textures, recipes
                //TEXTURES: boards
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.SLABS, Registries.BLOCK)
                .addTag(ItemTags.SLABS, Registries.ITEM)
                .setTab(getTab(tab))
                .defaultRecipe()
                .copyParentDrop()
                .build();
        this.addEntry(boardSlabs);

        boardStairs = SimpleEntrySet.builder(WoodType.class, "board_stairs",
                        getModBlock("oak_board_stairs"), () -> VanillaWoodTypes.OAK,
                        w -> new ModStairBlock(() -> boards.blocks.get(w), Utils.copyPropertySafe(w.planks))
                )
                .requiresFromMap(boards.blocks) //REASONS: textures, recipes
                //TEXTURES: boards
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.STAIRS, Registries.BLOCK)
                .addTag(ItemTags.STAIRS, Registries.ITEM)
                .setTab(getTab(tab))
                .defaultRecipe()
                .build();
        this.addEntry(boardStairs);

        boardWalls = SimpleEntrySet.builder(WoodType.class, "board_wall",
                        getModBlock("oak_board_wall"), () -> VanillaWoodTypes.OAK,
                        w -> new WallBlock(Utils.copyPropertySafe(w.planks))
                )
                .requiresFromMap(boards.blocks) //REASONS: textures, recipes
                //TEXTURES: boards
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .addTag(ItemTags.WALLS, Registries.ITEM)
                .setTab(getTab(tab))
                .defaultRecipe()
                .build();
        this.addEntry(boardWalls);

    }

    public static final PaletteStrategy customPalette = registerCached((blockType, manager) -> PaletteStrategies.makePaletteFromChild(
            blockType, manager, PLANKS, null, p -> {

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
    );

}
