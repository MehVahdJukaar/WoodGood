package net.mehvahdjukaar.every_compat.modules.neoforge.lightmans_currency;
//TODO: add back. auction stand is gone and LC moved everything around

import io.github.lightman314.lightmanscurrency.core.LCCreativeGroups;
import io.github.lightman314.lightmanscurrency.features.trader.item.blocks.specific.DoubleShelfBlock;
import io.github.lightman314.lightmanscurrency.features.trader.item.blocks.specific.SingleShelfBlock;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;

import java.util.function.Supplier;

import static net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys.SLAB;

///SUPPORT: v1.0.0.2+
public class LightmansCurrencyModule extends EveryCompatModule {

    public final SimpleEntrySet<WoodType, Block> shelves;
    public final SimpleEntrySet<WoodType, Block> shelves_2x2;

    public LightmansCurrencyModule(String modId) {
        super(modId, "lc");
        Supplier<CreativeModeTab> extraOrTraders = LCCreativeGroups.TRADERS;

        shelves = SimpleEntrySet.builder(WoodType.class, "", "single_shelf",
                        getModBlock("single_shelf_oak"), () -> VanillaWoodTypes.OAK,
                        (woodType, properties) -> new SingleShelfBlock(properties
                                .mapColor(woodType.getColor())
                                .strength(2.0F, Float.POSITIVE_INFINITY)
                                .sound(SoundType.WOOD)
                                .noOcclusion()
                        )
                )
                .requiresChildren(SLAB) //REASON: recipes
                .addTile(getModTile("item_trader"))
                //TEXTURES: planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WITHER_IMMUNE, Registries.BLOCK)
                .addTag(BlockTags.DRAGON_IMMUNE, Registries.BLOCK)
                .addTag(modRes("safe_interactable"), Registries.ITEM)
                .addTag(modRes("groups/single_shelf"), Registries.BLOCK, Registries.ITEM)
                .addTag(modRes("traders"), Registries.ITEM)
                .addTag(modRes("traders/item"), Registries.ITEM)
//                .addTag(Identifier.parse("carryon:block_blacklist"), Registries.BLOCK)
//                .addTag(Identifier.parse("ftbchunks:interact_whitelist"), Registries.BLOCK)
                .setTab(extraOrTraders)
                .addRecipe(modRes("traders/single_shelf/oak"))
                .build();
        this.addEntry(shelves);

        shelves_2x2 = SimpleEntrySet.builder(WoodType.class, "","double_shelf",
                        getModBlock("double_shelf_oak"), () -> VanillaWoodTypes.OAK,
                        (woodType, properties) -> new DoubleShelfBlock(properties
                                .mapColor(woodType.getColor())
                                .strength(2.0F, Float.POSITIVE_INFINITY)
                                .sound(SoundType.WOOD)
                                .noOcclusion()
                        )
                )
                .requiresFromMap(shelves.blocks) //REASON: recipes
                .requiresChildren(SLAB) //REASON: recipes
                .addTile(getModTile("item_trader"))
                //TEXTURES: planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WITHER_IMMUNE, Registries.BLOCK)
                .addTag(BlockTags.DRAGON_IMMUNE, Registries.BLOCK)
                .addTag(modRes("safe_interactable"), Registries.ITEM)
                .addTag(modRes("groups/single_shelf"), Registries.BLOCK, Registries.ITEM)
                .addTag(modRes("traders"), Registries.ITEM)
                .addTag(modRes("traders/item"), Registries.ITEM)
//                .addTag(Identifier.parse("carryon:block_blacklist"), Registries.BLOCK)
//                .addTag(Identifier.parse("ftbchunks:interact_whitelist"), Registries.BLOCK)
                .setTab(extraOrTraders)
                .addRecipe(modRes("traders/double_shelf/oak"))
                .build();
        this.addEntry(shelves_2x2);

    }

//    @Override
//    public List<String> getAlreadySupportedMods() {
//        return List.of("biomesoplenty", "biomeswevegone"); //, "quark"
//    }
}
