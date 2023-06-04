package net.mehvahdjukaar.every_compat.modules.forge.woodster;

import net.salju.woodster.init.WoodsterTabs;
//import net.salju.woodster.block.entity.*;
import net.salju.woodster.block.*;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.mehvahdjukaar.moonlight.api.resources.SimpleTagBuilder;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;

import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.LadderBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Registry;

public class WoodsterModule extends SimpleModule {
	public final SimpleEntrySet<WoodType, Block> chiseled_books;
	public final SimpleEntrySet<WoodType, Block> books;
	public final SimpleEntrySet<WoodType, Block> ladders;

	public WoodsterModule(String modId) {
		super(modId, "salwood");

		chiseled_books = SimpleEntrySet.builder(WoodType.class, "chiseled_bookshelf",
                        () -> getModBlock("oak_chiseled_bookshelf"),
                        () -> WoodTypeRegistry.getValue(new ResourceLocation("oak")),
                        w -> new ChiseledBookshelfBlock(Utils.copyPropertySafe(w.planks)))
                .setTab(() -> WoodsterTabs.WOODSTER)
                .useLootFromBase()
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTextureM(EveryCompat.res("block/oak_chiseled_bookshelf_0"))
                .addTextureM(EveryCompat.res("block/oak_chiseled_bookshelf_1"))
                .addTextureM(EveryCompat.res("block/oak_chiseled_bookshelf_2"))
                .addTextureM(EveryCompat.res("block/oak_chiseled_bookshelf_3"))
                .addTextureM(EveryCompat.res("block/oak_chiseled_bookshelf_4"))
                .addTextureM(EveryCompat.res("block/oak_chiseled_bookshelf_5"))
                .addTextureM(EveryCompat.res("block/oak_chiseled_bookshelf_6"))
                .addTextureM(EveryCompat.res("block/oak_chiseled_bookshelf_side"))
                .addTextureM(EveryCompat.res("block/oak_chiseled_bookshelf_top"))
                //.addTile(ChiseledBookshelfBlockEntity::new)
                .build();

        this.addEntry(chiseled_books);

		books = SimpleEntrySet.builder(WoodType.class, "bookshelf",
                        () -> getModBlock("spruce_bookshelf"),
                        () -> WoodTypeRegistry.getValue(new ResourceLocation("spruce")),
                        w -> new BookshelfBlock(Utils.copyPropertySafe(w.planks)))
                .setTab(() -> WoodsterTabs.WOODSTER)
                .useLootFromBase()
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTextureM(EveryCompat.res("block/spruce_bookshelf"))
                .build();

        this.addEntry(books);

        ladders = SimpleEntrySet.builder(WoodType.class, "ladder",
                        () -> getModBlock("spruce_ladder"),
                        () -> WoodTypeRegistry.getValue(new ResourceLocation("spruce")),
                        w -> new LadderBlock(Utils.copyPropertySafe(w.planks)))
                .setTab(() -> WoodsterTabs.WOODSTER)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.CLIMBABLE, Registry.BLOCK_REGISTRY)
                .addTag(new ResourceLocation("minecraft:ladders"), Registry.BLOCK_REGISTRY)
                .addTag(new ResourceLocation("minecraft:ladders"), Registry.ITEM_REGISTRY)
                .defaultRecipe()
                .addTextureM(EveryCompat.res("block/spruce_ladder"))
                .build();

        this.addEntry(ladders);
	}
}
