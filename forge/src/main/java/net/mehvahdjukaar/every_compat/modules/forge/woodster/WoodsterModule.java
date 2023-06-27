package net.mehvahdjukaar.every_compat.modules.forge.woodster;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LadderBlock;
import net.salju.woodster.block.BookshelfBlock;
import net.salju.woodster.block.ChiseledBookshelfBlock;
import net.salju.woodster.init.WoodsterBlocks;
import net.salju.woodster.init.WoodsterTabs;

public class WoodsterModule extends SimpleModule {
    public final SimpleEntrySet<WoodType, Block> chiseled_books;
    public final SimpleEntrySet<WoodType, Block> books;
    public final SimpleEntrySet<WoodType, Block> ladders;

    public WoodsterModule(String modId) {
        super(modId, "wdst");

        chiseled_books = SimpleEntrySet.builder(WoodType.class, "chiseled_bookshelf",
                        WoodsterBlocks.OAK_CHISELED_BOOKSHELF, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ChiseledBookshelfBlock(Utils.copyPropertySafe(w.planks)))
                .addTextureM(modRes("block/oak_chiseled_bookshelf_1"),modRes( "block/everycomp_chiseled_bookshelf_1"))
                .addTextureM(modRes("block/oak_chiseled_bookshelf_2"),modRes( "block/everycomp_chiseled_bookshelf_2"))
                .addTextureM(modRes("block/oak_chiseled_bookshelf_3"),modRes( "block/everycomp_chiseled_bookshelf_3"))
                .addTextureM(modRes("block/oak_chiseled_bookshelf_4"),modRes( "block/everycomp_chiseled_bookshelf_4"))
                .addTextureM(modRes("block/oak_chiseled_bookshelf_5"),modRes( "block/everycomp_chiseled_bookshelf_5"))
                .addTextureM(modRes("block/oak_chiseled_bookshelf_6"),modRes( "block/everycomp_chiseled_bookshelf_6"))
                .addTexture(modRes("block/oak_chiseled_bookshelf_side"))
                .addTexture(modRes("block/oak_chiseled_bookshelf_top"))
                .addTexture(modRes("block/oak_chiseled_bookshelf_0"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> WoodsterTabs.WOODSTER)
                .copyParentDrop()
                .build();

        this.addEntry(chiseled_books);

        books = SimpleEntrySet.builder(WoodType.class, "bookshelf",
                        WoodsterBlocks.ACACIA_CHISELED_BOOKSHELF, () -> WoodTypeRegistry.getValue(new ResourceLocation("acacia")),
                        w -> new BookshelfBlock(Utils.copyPropertySafe(w.planks)))
                .addTextureM(EveryCompat.res("block/acacia_bookshelf"), EveryCompat.res("block/acacia_bookshelf_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> WoodsterTabs.WOODSTER)
                .copyParentDrop()
                .build();

        this.addEntry(books);

        ladders = SimpleEntrySet.builder(WoodType.class, "ladder",
                        WoodsterBlocks.SPRUCE_LADDER, () -> WoodTypeRegistry.getValue(new ResourceLocation("spruce")),
                        w -> new LadderBlock(Utils.copyPropertySafe(getModBlock("spruce_ladder"))))
                .addTag(new ResourceLocation("minecraft:ladders"), Registry.BLOCK_REGISTRY)
                .addTag(new ResourceLocation("minecraft:ladders"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.CLIMBABLE, Registry.BLOCK_REGISTRY)
                .addTexture(EveryCompat.res("block/spruce_ladder"))
                .setTab(() -> WoodsterTabs.WOODSTER)
                .defaultRecipe()
                .build();

        this.addEntry(ladders);
    }
}
