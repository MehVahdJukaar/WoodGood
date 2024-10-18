package net.mehvahdjukaar.every_compat.modules.forge.lightmans_currency;

import io.github.lightman314.lightmanscurrency.LCTags;
import io.github.lightman314.lightmanscurrency.ModCreativeGroups;
import io.github.lightman314.lightmanscurrency.client.renderer.blockentity.AuctionStandBlockEntityRenderer;
import io.github.lightman314.lightmanscurrency.client.renderer.blockentity.BookTraderBlockEntityRenderer;
import io.github.lightman314.lightmanscurrency.client.renderer.blockentity.ItemTraderBlockEntityRenderer;
import io.github.lightman314.lightmanscurrency.common.blockentity.AuctionStandBlockEntity;
import io.github.lightman314.lightmanscurrency.common.blockentity.trader.BookTraderBlockEntity;
import io.github.lightman314.lightmanscurrency.common.blockentity.trader.ItemTraderBlockEntity;
import io.github.lightman314.lightmanscurrency.common.blocks.traderblocks.BookTraderBlock;
import io.github.lightman314.lightmanscurrency.common.blocks.traderblocks.ShelfBlock;
import io.github.lightman314.lightmanscurrency.common.blocks.traderblocks.reference.AuctionStandBlock;
import io.github.lightman314.lightmanscurrency.common.core.ModBlockEntities;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;

import java.util.List;

//SUPPORT: v2.2.3.3+
public class LightmansCurrencyModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> auction_stands;
    public final SimpleEntrySet<WoodType, Block> shelves;
    public final SimpleEntrySet<WoodType, Block> shelves_2x2;
    public final SimpleEntrySet<WoodType, Block> bookshelf_traders;

    public LightmansCurrencyModule(String modId) {
        super(modId, "lc");
        var tab = modRes("extra");

        auction_stands = SimpleEntrySet.builder(WoodType.class, "", "auction_stand",
                        getModBlock("auction_stand_oak"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new AuctionStandBlock(Utils.copyPropertySafe(w.planks)
                                .mapColor(w.getColor()).strength(2.0F)
                        )
                )
                .addTile(ModBlockEntities.AUCTION_STAND)
                //TEXTURE: Using log & log_top
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(LCTags.Blocks.SAFE_INTERACTABLE, Registries.BLOCK)
                .addTag(LCTags.Blocks.AUCTION_STAND, Registries.BLOCK)
                .addTag(new ResourceLocation("ftbchunks:interact_whitelist"), Registries.BLOCK)
                .addTag(LCTags.Items.AUCTION_STAND, Registries.ITEM)
                .setTabKey(tab)
                .addRecipe(modRes("auction_stand/oak"))
                .build();
        this.addEntry(auction_stands);

        shelves = SimpleEntrySet.builder(WoodType.class, "", "shelf",
                        getModBlock("shelf_oak"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ShelfBlock(Utils.copyPropertySafe(w.planks)
                                .mapColor(w.getColor()).strength(2.0F, Float.POSITIVE_INFINITY),
                                1
                        )
                )
                .addTile(ModBlockEntities.ITEM_TRADER)
                //TEXTURE: Using planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WITHER_IMMUNE, Registries.BLOCK)
                .addTag(BlockTags.DRAGON_IMMUNE, Registries.BLOCK)
                .addTag(LCTags.Blocks.SAFE_INTERACTABLE, Registries.BLOCK)
                .addTag(LCTags.Blocks.OWNER_PROTECTED, Registries.BLOCK)
                .addTag(LCTags.Blocks.SHELF, Registries.BLOCK)
                .addTag(new ResourceLocation("carryon:block_blacklist"), Registries.BLOCK)
                .addTag(new ResourceLocation("ftbchunks:interact_whitelist"), Registries.BLOCK)
                .addTag(LCTags.Items.TRADER_SHELF, Registries.ITEM)
                .addTag(LCTags.Items.TRADER, Registries.ITEM)
                .addTag(LCTags.Items.TRADER_NORMAL, Registries.ITEM)
                .setTabKey(tab)
                .addRecipe(modRes("traders/shelf/oak"))
                .build();
        this.addEntry(shelves);

        shelves_2x2 = SimpleEntrySet.builder(WoodType.class, "","shelf_2x2",
                        getModBlock("shelf_2x2_oak"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ShelfBlock(Utils.copyPropertySafe(w.planks)
                                .mapColor(w.getColor()).strength(2.0F, Float.POSITIVE_INFINITY),
                                4
                        )
                )
                .addTile(ModBlockEntities.ITEM_TRADER)
                //TEXTURE: Using planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WITHER_IMMUNE, Registries.BLOCK)
                .addTag(BlockTags.DRAGON_IMMUNE, Registries.BLOCK)
                .addTag(LCTags.Blocks.SAFE_INTERACTABLE, Registries.BLOCK)
                .addTag(LCTags.Blocks.OWNER_PROTECTED, Registries.BLOCK)
                .addTag(LCTags.Blocks.SHELF_2x2, Registries.BLOCK)
                .addTag(new ResourceLocation("carryon:block_blacklist"), Registries.BLOCK)
                .addTag(new ResourceLocation("ftbchunks:interact_whitelist"), Registries.BLOCK)
                .addTag(LCTags.Items.TRADER_SHELF_2x2, Registries.ITEM)
                .addTag(LCTags.Items.TRADER, Registries.ITEM)
                .addTag(LCTags.Items.TRADER_NORMAL, Registries.ITEM)
                .setTabKey(tab)
                .addRecipe(modRes("traders/shelf2/oak"))
                .build();
        this.addEntry(shelves_2x2);

        bookshelf_traders = SimpleEntrySet.builder(WoodType.class, "", "bookshelf_trader",
                        getModBlock("bookshelf_trader_oak"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BookTraderBlock(Utils.copyPropertySafe(w.planks)
                                .mapColor(w.getColor()).strength(3.0F, Float.POSITIVE_INFINITY)
                                .sound(SoundType.WOOD)
                        )
                )
                .addTile(ModBlockEntities.BOOK_TRADER)
                //TEXTURE: Using planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WITHER_IMMUNE, Registries.BLOCK)
                .addTag(BlockTags.DRAGON_IMMUNE, Registries.BLOCK)
                .addTag(LCTags.Blocks.SAFE_INTERACTABLE, Registries.BLOCK)
                .addTag(LCTags.Blocks.OWNER_PROTECTED, Registries.BLOCK)
                .addTag(new ResourceLocation("carryon:block_blacklist"), Registries.BLOCK)
                .addTag(new ResourceLocation("ftbchunks:interact_whitelist"), Registries.BLOCK)
                .addTag(LCTags.Items.TRADER_SPECIALTY, Registries.ITEM)
                .addTag(LCTags.Items.TRADER, Registries.ITEM)
                .addTag(LCTags.Items.TRADER_SPECIALTY_BOOKSHELF, Registries.ITEM)
                .setTabKey(tab)
                .addRecipe(modRes("traders/bookshelf/oak"))
                .build();
        this.addEntry(bookshelf_traders);


    }

    @Override
    public void registerBlockEntityRenderers(ClientHelper.BlockEntityRendererEvent event) {
        super.registerBlockEntityRenderers(event);
        event.register(auction_stands.getTile(AuctionStandBlockEntity.class), AuctionStandBlockEntityRenderer::new);
        event.register(shelves.getTile(ItemTraderBlockEntity.class), ItemTraderBlockEntityRenderer::new);
        event.register(shelves_2x2.getTile(ItemTraderBlockEntity.class), ItemTraderBlockEntityRenderer::new);
        event.register(bookshelf_traders.getTile(BookTraderBlockEntity.class), BookTraderBlockEntityRenderer::new);
    }


    @Override
    public List<String> getAlreadySupportedMods() {
        return List.of("biomesoplenty", "biomeswevegone", "quark");
    }
}