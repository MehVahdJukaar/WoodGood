package net.mehvahdjukaar.every_compat.modules.forge.lightmans_currency;

import io.github.lightman314.lightmanscurrency.LCTags;
import io.github.lightman314.lightmanscurrency.common.blocks.traderblocks.BookTraderBlock;
import io.github.lightman314.lightmanscurrency.common.blocks.traderblocks.ShelfBlock;
import io.github.lightman314.lightmanscurrency.common.blocks.traderblocks.reference.AuctionStandBlock;
import io.github.lightman314.lightmanscurrency.common.core.ModBlockEntities;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

import java.util.List;

//SUPPORT: v2.2.1.3f-FINAL
@SuppressWarnings("removal")
public class LightmansCurrencyModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> auction_stands;
    public final SimpleEntrySet<WoodType, Block> shelves;
    public final SimpleEntrySet<WoodType, Block> shelves_2x2;
    public final SimpleEntrySet<WoodType, Block> bookshelf_traders;

    public LightmansCurrencyModule(String modId) {
        super(modId, "lc");
        ResourceLocation tab = modRes("traders");

        auction_stands = SimpleEntrySet.builder(WoodType.class, "", "auction_stand",
                        () -> getModBlock("auction_stand_oak"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new AuctionStandBlock(BlockBehaviour.Properties.of(Material.WOOD).color(w.getColor())
                                .strength(2.0F)
                        )
                )
                .addTile(ModBlockEntities.AUCTION_STAND)
                //TEXTURE: Using log & log_top
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(LCTags.Blocks.SAFE_INTERACTABLE, Registries.BLOCK)
                .addTag(LCTags.Blocks.AUCTION_STAND, Registries.BLOCK) //IMPORTANT: Allows the item to be rendered
                .addTag(new ResourceLocation("ftbchunks:interact_whitelist"), Registries.BLOCK)
                .setTabKey(tab)
                .addRecipe(modRes("auction_stand/oak"))
                .build();
        this.addEntry(auction_stands);

        shelves = SimpleEntrySet.builder(WoodType.class, "", "shelf",
                        () -> getModBlock("shelf_oak"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ShelfBlock(BlockBehaviour.Properties.of(Material.WOOD)
                                .color(w.getColor()).strength(2.0F, Float.POSITIVE_INFINITY)
                                .sound(SoundType.WOOD),
                                1
                        )
                )
                .requiresChildren("slab") //REASON: Recipes
                .addTile(ModBlockEntities.ITEM_TRADER)
                //TEXTURE: Using planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WITHER_IMMUNE, Registries.BLOCK)
                .addTag(BlockTags.DRAGON_IMMUNE, Registries.BLOCK)
                .addTag(LCTags.Blocks.SAFE_INTERACTABLE, Registries.BLOCK)
                .addTag(LCTags.Blocks.OWNER_PROTECTED, Registries.BLOCK)
                .addTag(LCTags.Blocks.SHELF, Registries.BLOCK) //IMPORTANT: Allows the item to be rendered
                .addTag(new ResourceLocation("ftbchunks:interact_whitelist"), Registries.BLOCK)
                .addTag(LCTags.Items.TRADER_SHELF, Registries.ITEM)
                .addTag(LCTags.Items.TRADER, Registries.ITEM)
                .addTag(LCTags.Items.TRADER_NORMAL, Registries.ITEM)
                .setTabKey(tab)
                .addRecipe(modRes("traders/shelf/oak"))
                .build();
        this.addEntry(shelves);

        shelves_2x2 = SimpleEntrySet.builder(WoodType.class, "","shelf_2x2",
                        () -> getModBlock("shelf_2x2_oak"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ShelfBlock(BlockBehaviour.Properties.of(Material.WOOD)
                                .color(w.getColor()).strength(2.0F, Float.POSITIVE_INFINITY)
                                .sound(SoundType.WOOD),
                                4
                        )
                )
                .requiresChildren("slab") //REASON: Recipes
                .addTile(ModBlockEntities.ITEM_TRADER)
                //TEXTURE: Using planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WITHER_IMMUNE, Registries.BLOCK)
                .addTag(BlockTags.DRAGON_IMMUNE, Registries.BLOCK)
                .addTag(LCTags.Blocks.SAFE_INTERACTABLE, Registries.BLOCK)
                .addTag(LCTags.Blocks.OWNER_PROTECTED, Registries.BLOCK)
                .addTag(LCTags.Blocks.SHELF_2x2, Registries.BLOCK) //IMPORTANT: Allows the item to be rendered
                .addTag(new ResourceLocation("ftbchunks:interact_whitelist"), Registries.BLOCK)
                .addTag(LCTags.Items.TRADER_SHELF_2x2, Registries.ITEM)
                .addTag(LCTags.Items.TRADER, Registries.ITEM)
                .addTag(LCTags.Items.TRADER_NORMAL, Registries.ITEM)
                .setTabKey(tab)
                .addRecipe(modRes("traders/shelf2/oak"))
                .build();
        this.addEntry(shelves_2x2);

        bookshelf_traders = SimpleEntrySet.builder(WoodType.class, "", "bookshelf_trader",
                        () -> getModBlock("bookshelf_trader_oak"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BookTraderBlock(BlockBehaviour.Properties.of(Material.WOOD).color(w.getColor())
                                .strength(3.0F, Float.POSITIVE_INFINITY).sound(SoundType.WOOD)
                        )
                )
                .addTile(ModBlockEntities.BOOK_TRADER)
                //TEXTURE: Using planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WITHER_IMMUNE, Registries.BLOCK)
                .addTag(BlockTags.DRAGON_IMMUNE, Registries.BLOCK)
                .addTag(LCTags.Blocks.SAFE_INTERACTABLE, Registries.BLOCK)
                .addTag(LCTags.Blocks.OWNER_PROTECTED, Registries.BLOCK)
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
    public List<String> getAlreadySupportedMods() {
        return List.of("biomesoplenty", "byg", "quark");
    }


}