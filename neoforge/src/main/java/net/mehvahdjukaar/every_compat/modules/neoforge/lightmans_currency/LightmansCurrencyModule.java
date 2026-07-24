package net.mehvahdjukaar.every_compat.modules.neoforge.lightmans_currency;

import io.github.lightman314.lightmanscurrency.LCTags;
import io.github.lightman314.lightmanscurrency.ModCreativeGroups;
import io.github.lightman314.lightmanscurrency.common.blocks.traderblocks.BookTraderBlock;
import io.github.lightman314.lightmanscurrency.common.blocks.traderblocks.ShelfBlock;
import io.github.lightman314.lightmanscurrency.common.blocks.traderblocks.reference.AuctionStandBlock;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;

import java.util.List;
import java.util.function.Supplier;

///SUPPORT: v2.3.0.5+
public class LightmansCurrencyModule extends EveryCompatModule {

    public final SimpleEntrySet<WoodType, Block> auction_stands;
    public final SimpleEntrySet<WoodType, Block> shelves;
    public final SimpleEntrySet<WoodType, Block> shelves_2x2;
    public final SimpleEntrySet<WoodType, Block> bookshelf_traders;

    public LightmansCurrencyModule(String modId) {
        super(modId, "lc");
        Supplier<CreativeModeTab> extraOrTraders = (io.github.lightman314.lightmanscurrency.common.core.variants.WoodType.hasModdedValues())
                ? ModCreativeGroups.EXTRA_GROUP
                : ModCreativeGroups.TRADER_GROUP;

        Supplier<CreativeModeTab> extraOrMachine = (io.github.lightman314.lightmanscurrency.common.core.variants.WoodType.hasModdedValues())
                ? ModCreativeGroups.EXTRA_GROUP
                : ModCreativeGroups.MACHINE_GROUP;

        auction_stands = SimpleEntrySet.builder(WoodType.class, "", "auction_stand",
                        getModBlock("auction_stand_oak"), () -> VanillaWoodTypes.OAK,
                        w -> new AuctionStandBlock(Utils.copyPropertySafe(w.planks)
                                .mapColor(w.getColor()).strength(2.0F)
                        )
                )
                .addTile(getModTile("auction_stand"))
                //TEXTURES: log, log_top
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(LCTags.Blocks.SAFE_INTERACTABLE, Registries.BLOCK)
                .addTag(LCTags.Blocks.AUCTION_STAND, Registries.BLOCK)
                .addTag(ResourceLocation.parse("ftbchunks:interact_whitelist"), Registries.BLOCK)
                .addTag(LCTags.Items.AUCTION_STAND, Registries.ITEM)
                .setTab(extraOrMachine)
                .addRecipe(modRes("auction_stand/oak"))
                .build();
        this.addEntry(auction_stands);

        shelves = SimpleEntrySet.builder(WoodType.class, "", "shelf",
                        getModBlock("shelf_oak"), () -> VanillaWoodTypes.OAK,
                        w -> new ShelfBlock(Utils.copyPropertySafe(w.planks)
                                .mapColor(w.getColor()).strength(2.0F, Float.POSITIVE_INFINITY),
                                1
                        )
                )
                .addTile(getModTile("item_trader"))
                //TEXTURES: planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WITHER_IMMUNE, Registries.BLOCK)
                .addTag(BlockTags.DRAGON_IMMUNE, Registries.BLOCK)
                .addTag(LCTags.Blocks.SAFE_INTERACTABLE, Registries.BLOCK)
                .addTag(LCTags.Blocks.OWNER_PROTECTED, Registries.BLOCK)
                .addTag(LCTags.Blocks.SHELF, Registries.BLOCK)
                .addTag(ResourceLocation.parse("carryon:block_blacklist"), Registries.BLOCK)
                .addTag(ResourceLocation.parse("ftbchunks:interact_whitelist"), Registries.BLOCK)
                .addTag(LCTags.Items.TRADER_SHELF, Registries.ITEM)
                .addTag(LCTags.Items.TRADER, Registries.ITEM)
                .addTag(LCTags.Items.TRADER_NORMAL, Registries.ITEM)
                .setTab(extraOrTraders)
                .addRecipe(modRes("traders/shelf/oak"))
                .build();
        this.addEntry(shelves);

        shelves_2x2 = SimpleEntrySet.builder(WoodType.class, "","shelf_2x2",
                        getModBlock("shelf_2x2_oak"), () -> VanillaWoodTypes.OAK,
                        w -> new ShelfBlock(Utils.copyPropertySafe(w.planks)
                                .mapColor(w.getColor()).strength(2.0F, Float.POSITIVE_INFINITY),
                                4
                        )
                )
                .addTile(getModTile("item_trader"))
                //TEXTURES: planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WITHER_IMMUNE, Registries.BLOCK)
                .addTag(BlockTags.DRAGON_IMMUNE, Registries.BLOCK)
                .addTag(LCTags.Blocks.SAFE_INTERACTABLE, Registries.BLOCK)
                .addTag(LCTags.Blocks.OWNER_PROTECTED, Registries.BLOCK)
                .addTag(LCTags.Blocks.SHELF_2x2, Registries.BLOCK)
                .addTag(ResourceLocation.parse("carryon:block_blacklist"), Registries.BLOCK)
                .addTag(ResourceLocation.parse("ftbchunks:interact_whitelist"), Registries.BLOCK)
                .addTag(LCTags.Items.TRADER_SHELF_2x2, Registries.ITEM)
                .addTag(LCTags.Items.TRADER, Registries.ITEM)
                .addTag(LCTags.Items.TRADER_NORMAL, Registries.ITEM)
                .setTab(extraOrTraders)
                .addRecipe(modRes("traders/shelf2/oak"))
                .build();
        this.addEntry(shelves_2x2);

        bookshelf_traders = SimpleEntrySet.builder(WoodType.class, "", "bookshelf_trader",
                        getModBlock("bookshelf_trader_oak"), () -> VanillaWoodTypes.OAK,
                        w -> new BookTraderBlock(Utils.copyPropertySafe(w.planks)
                                .mapColor(w.getColor()).strength(3.0F, Float.POSITIVE_INFINITY)
                                .sound(SoundType.WOOD)
                        )
                )
                .addTile(getModTile("book_trader"))
                //TEXTURES: planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WITHER_IMMUNE, Registries.BLOCK)
                .addTag(BlockTags.DRAGON_IMMUNE, Registries.BLOCK)
                .addTag(LCTags.Blocks.SAFE_INTERACTABLE, Registries.BLOCK)
                .addTag(LCTags.Blocks.OWNER_PROTECTED, Registries.BLOCK)
                .addTag(ResourceLocation.parse("carryon:block_blacklist"), Registries.BLOCK)
                .addTag(ResourceLocation.parse("ftbchunks:interact_whitelist"), Registries.BLOCK)
                .addTag(LCTags.Items.TRADER_SPECIALTY, Registries.ITEM)
                .addTag(LCTags.Items.TRADER, Registries.ITEM)
                .addTag(LCTags.Items.TRADER_SPECIALTY_BOOKSHELF, Registries.ITEM)
                .setTab(extraOrTraders)
                .addRecipe(modRes("traders/bookshelf/oak"))
                .build();
        this.addEntry(bookshelf_traders);

    }

    @Override
    public List<String> getAlreadySupportedMods() {
        return List.of("biomesoplenty", "biomeswevegone"/*, "quark"*/);
    }
}