package net.mehvahdjukaar.every_compat.modules.forge.woodster;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChiseledBookShelfBlock;
import net.minecraft.world.level.block.LadderBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.salju.woodster.block.BookshelfBlock;
import net.salju.woodster.init.WoodsterBlocks;

//SUPPORT: v1.4.4
public class WoodsterModule extends SimpleModule {
    public final SimpleEntrySet<WoodType, Block> chiseled_books;
    public final SimpleEntrySet<WoodType, Block> books;
    public final SimpleEntrySet<WoodType, Block> ladders;

    public WoodsterModule(String modId) {
        super(modId, "wdst");
        var tab = modRes(modId);

        chiseled_books = SimpleEntrySet.builder(WoodType.class, "chiseled_bookshelf",
                        WoodsterBlocks.DARK_OAK_CHISELED_BOOKSHELF, () -> WoodTypeRegistry.getValue(new ResourceLocation("dark_oak")),
                        w -> new ChiseledBookShelfBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(1.5F).sound(SoundType.CHISELED_BOOKSHELF))
                )
                .addTile(() -> BlockEntityType.CHISELED_BOOKSHELF)
                .addTexture(modRes("block/dark_oak_chiseled_bookshelf_side"))
                .addTexture(modRes("block/dark_oak_chiseled_bookshelf_top"))
                .addTexture(modRes("block/dark_oak_chiseled_bookshelf_0"))
                .addTextureM(modRes("block/dark_oak_chiseled_bookshelf_6"),modRes("block/everycomp_chiseled_bookshelf_6"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .copyParentDrop()
                .build();
        this.addEntry(chiseled_books);

        books = SimpleEntrySet.builder(WoodType.class, "bookshelf",
                        WoodsterBlocks.ACACIA_BOOKSHELF, () -> WoodTypeRegistry.getValue(new ResourceLocation("acacia")),
                        w -> new BookshelfBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(1.5F).sound(SoundType.WOOD))
                )
                .addTextureM(EveryCompat.res("block/acacia_bookshelf"), EveryCompat.res("block/acacia_bookshelf_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .copyParentDrop()
                .build();
        this.addEntry(books);

        ladders = SimpleEntrySet.builder(WoodType.class, "ladder",
                        WoodsterBlocks.SPRUCE_LADDER, () -> WoodTypeRegistry.getValue(new ResourceLocation("spruce")),
                        w -> new LadderBlock(BlockBehaviour.Properties.of().strength(0.4F).sound(SoundType.LADDER).noOcclusion())
                )
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addTexture(EveryCompat.res("block/spruce_ladder"))
                .addTag(new ResourceLocation("minecraft:ladders"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.CLIMBABLE, Registries.BLOCK)
                .addTag(BlockTags.FALL_DAMAGE_RESETTING, Registries.BLOCK)
                .addTag(new ResourceLocation("minecraft:ladders"), Registries.ITEM)
                .setTabKey( tab)
                .defaultRecipe()
                .build();
        this.addEntry(ladders);
    }
}
