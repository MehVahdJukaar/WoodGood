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
                        () -> getModBlock("oak_chiseled_bookshelf"),
                        () -> WoodTypeRegistry.getValue(new ResourceLocation("oak")),
                        w -> new ChiseledBookshelfBlock(Utils.copyPropertySafe(WoodsterBlocks.BOOKSHELF)))
                .setTab(() -> WoodsterTabs.WOODSTER)
                .useLootFromBase()
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/oak_chiseled_bookshelf_0"))
                .addTextureM(modRes("block/oak_chiseled_bookshelf_1", "block/everycomp_chiseled_bookself_1"))
                .addTextureM(modRes("block/oak_chiseled_bookshelf_2", "block/everycomp_chiseled_bookself_2"))
                .addTextureM(modRes("block/oak_chiseled_bookshelf_3", "block/everycomp_chiseled_bookself_3"))
                .addTextureM(modRes("block/oak_chiseled_bookshelf_4", "block/everycomp_chiseled_bookself_4"))
                .addTextureM(modRes("block/oak_chiseled_bookshelf_5", "block/everycomp_chiseled_bookself_5"))
                .addTextureM(modRes("block/oak_chiseled_bookshelf_6", "block/everycomp_chiseled_bookself_6"))
                .addTexture(modRes("block/oak_chiseled_bookshelf_side"))
                .addTexture(modRes("block/oak_chiseled_bookshelf_top"))
                //.addTile(ChiseledBookshelfBlockEntity::new)
                .build();

        this.addEntry(chiseled_books);

        books = SimpleEntrySet.builder(WoodType.class, "bookshelf",
                        () -> getModBlock("spruce_bookshelf"),
                        () -> WoodTypeRegistry.getValue(new ResourceLocation("spruce")),
                        w -> new BookshelfBlock(Utils.copyPropertySafe(WoodsterBlocks.BOOKSHELF)))
                .setTab(() -> WoodsterTabs.WOODSTER)
                .useLootFromBase()
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTextureM(modRes("block/spruce_bookshelf", "block/everycomp_bookshelf"))
                .build();

        this.addEntry(books);

        ladders = SimpleEntrySet.builder(WoodType.class, "ladder",
                        () -> getModBlock("spruce_ladder"),
                        () -> WoodTypeRegistry.getValue(new ResourceLocation("spruce")),
                        w -> new LadderBlock(Utils.copyPropertySafe(WoodsterBlocks.LADDER)))
                .setTab(() -> WoodsterTabs.WOODSTER)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.CLIMBABLE, Registry.BLOCK_REGISTRY)
                .addTag(new ResourceLocation("minecraft:ladders"), Registry.BLOCK_REGISTRY)
                .addTag(new ResourceLocation("minecraft:ladders"), Registry.ITEM_REGISTRY)
                .defaultRecipe()
                .addTextureM(modRes("block/spruce_ladder", "block/everycomp_ladder"))
                .build();

        this.addEntry(ladders);
    }
}
