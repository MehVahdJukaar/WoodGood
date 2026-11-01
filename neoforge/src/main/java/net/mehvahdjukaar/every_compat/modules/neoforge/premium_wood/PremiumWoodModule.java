package net.mehvahdjukaar.every_compat.modules.neoforge.premium_wood;

import com.legacy.premium_wood.block.PremiumBookshelfBlock;
import com.legacy.premium_wood.block.PremiumWorkbenchBlock;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TransparentBlock;

public class PremiumWoodModule extends EveryCompatModule {

    public final SimpleEntrySet<WoodType, Block> craftingTable;
    public final SimpleEntrySet<WoodType, Block> bookshelf;
    public final SimpleEntrySet<WoodType, Block> framedGlass;

    public PremiumWoodModule(String modId) {
        super(modId, "pw");
        ResourceKey<CreativeModeTab> tab = CreativeModeTabs.BUILDING_BLOCKS;
        var tiger = WoodTypeRegistry.INSTANCE.makeFutureHolder(Identifier.parse("premium_wood:tiger"));

        craftingTable = SimpleEntrySet.builder(WoodType.class, "crafting_table",
                        getModBlock("tiger_crafting_table"), tiger,
                        (w, p) -> new PremiumWorkbenchBlock(p)
                )
                .addTextureM(EveryCompat.res("block/tiger/tiger_crafting_table_front"), EveryCompat.res("block/pw/tiger_crafting_table_front_m"))
                .addTextureM(EveryCompat.res("block/tiger/tiger_crafting_table_side"), EveryCompat.res("block/pw/tiger_crafting_table_side_m"))
                .addTexture(EveryCompat.res("block/tiger/tiger_crafting_table_top"))
                .addModelTransform(m -> m.replaceString("premium_wood:block/tiger_planks", "block/tiger_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTab(getTab(tab))
                .defaultRecipe()
                .copyParentDrop()
                .build();
        this.addEntry(craftingTable);

        bookshelf = SimpleEntrySet.builder(WoodType.class, "bookshelf",
                        getModBlock("tiger_bookshelf"), tiger,
                        (w, p) -> new PremiumBookshelfBlock(p)
                )
                .setProperties(w -> Utils.copyPropertySafe(Blocks.BOOKSHELF))
                .addTextureM(modRes("block/tiger_bookshelf"), EveryCompat.res("block/pw/tiger_bookshelf_m"))
                .addModelTransform(m -> m.replaceString("premium_wood:block/tiger_planks", "block/tiger_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTab(getTab(tab))
                .defaultRecipe()
                .copyParentDrop()
                .build();
        this.addEntry(bookshelf);

        framedGlass = SimpleEntrySet.builder(WoodType.class, "framed_glass",
                        getModBlock("tiger_framed_glass"), tiger,
                        (w, p) -> new TransparentBlock(p)
                )
                .setProperties(w -> Utils.copyPropertySafe(Blocks.GLASS))
                .addTextureM(modRes("block/tiger/tiger_framed_glass"), EveryCompat.res("block/pw/tiger_framed_glass_m"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("framed_glass"), Registries.BLOCK)
                .setTab(getTab(tab))
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .build();
        this.addEntry(framedGlass);
    }
}
