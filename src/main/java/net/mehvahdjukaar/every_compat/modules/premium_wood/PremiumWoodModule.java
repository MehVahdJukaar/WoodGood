package net.mehvahdjukaar.every_compat.modules.premium_wood;

import com.legacy.premium_wood.block.PremiumBookshelfBlock;
import com.legacy.premium_wood.block.PremiumWorkbenchBlock;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.mehvahdjukaar.selene.block_set.wood.WoodTypeRegistry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GlassBlock;


public class PremiumWoodModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> craftingTable;
    public final SimpleEntrySet<WoodType, Block> bookshelf;
    public final SimpleEntrySet<WoodType, Block> framedGlass;

    public PremiumWoodModule(String modId) {
        super(modId, "pw");
        CreativeModeTab tab = CreativeModeTab.TAB_BUILDING_BLOCKS;

        craftingTable = SimpleEntrySet.builder(WoodType.class, "crafting_table",
                        () -> getModBlock("tiger_crafting_table"),
                        () -> WoodTypeRegistry.fromNBT("premium_wood:tiger"),
                        w -> new PremiumWorkbenchBlock())
                .addTextureM(WoodGood.res("block/tiger/tiger_crafting_table_front"), WoodGood.res("block/pw/tiger_crafting_table_front_m"))
                .addTextureM(WoodGood.res("block/tiger/tiger_crafting_table_side"), WoodGood.res("block/pw/tiger_crafting_table_side_m"))
                .addTexture(WoodGood.res("block/tiger/tiger_crafting_table_top"))
                .addModelTransform(m -> m.replaceString("premium_wood:block/tiger/tiger_planks", "block/tiger_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .useLootFromBase()
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(craftingTable);

        bookshelf = SimpleEntrySet.builder(WoodType.class, "bookshelf",
                        () -> getModBlock("tiger_bookshelf"),
                        () -> WoodTypeRegistry.fromNBT("premium_wood:tiger"),
                        w -> new PremiumBookshelfBlock())
                .addTextureM(modRes("block/tiger/tiger_bookshelf"), WoodGood.res("block/pw/tiger_bookshelf_m"))
                .addModelTransform(m -> m.replaceString("premium_wood:block/tiger/tiger_planks", "block/tiger_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .useLootFromBase()
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(bookshelf);

        framedGlass = SimpleEntrySet.builder(WoodType.class, "framed_glass",
                        () -> getModBlock("tiger_framed_glass"),
                        () -> WoodTypeRegistry.fromNBT("premium_wood:tiger"),
                        w -> new GlassBlock(WoodGood.copySafe(Blocks.GLASS)))
                .addTextureM(modRes("block/tiger/tiger_framed_glass"), WoodGood.res("block/pw/tiger_framed_glass_m"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("framed_glass"), Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::translucent)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(framedGlass);
    }
}
