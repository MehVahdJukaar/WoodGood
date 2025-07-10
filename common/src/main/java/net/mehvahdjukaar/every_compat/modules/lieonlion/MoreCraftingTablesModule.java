package net.mehvahdjukaar.every_compat.modules.lieonlion;

import io.github.lieonlion.lolmct.block.MoreCraftingTableBlock;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.misc.VanillaWoods;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Block;

//SUPPORT: FABRIC-v1.2.8+ | NEOFORGE-v1.2.9+
public class MoreCraftingTablesModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> craftingTable;

    public MoreCraftingTablesModule(String modId) {
        super(modId, "lolmct");
        ResourceKey<CreativeModeTab> tab = CreativeModeTabs.FUNCTIONAL_BLOCKS;

        craftingTable = SimpleEntrySet.builder(WoodType.class, "crafting_table",
                        getModBlock("spruce_crafting_table"),
                        () -> WoodTypeRegistry.getValue(VanillaWoods.SPRUCE),
                        w -> new MoreCraftingTableBlock(w.planks.defaultMapColor()))
                //TEXTUREs: oak_craftng_table as BaseTexture
                .addTextureM(EveryCompat.res("block/spruce_crafting_table_front"), EveryCompat.res("block/lolmct/spruce_crafting_table_front_m"))
                .addTextureM(EveryCompat.res("block/spruce_crafting_table_side"), EveryCompat.res("block/lolmct/spruce_crafting_table_side_m"))
                .addTextureM(EveryCompat.res("block/spruce_crafting_table_top"), EveryCompat.res("block/lolmct/spruce_crafting_table_top_m"))
                .addTag(ResourceLocation.parse("c:workbench"), Registries.BLOCK)
                .addTag(ResourceLocation.parse("lieonstudio:crafting_tables"), Registries.BLOCK)
                .addTag(ResourceLocation.parse("lieonstudio:crafting_tables"), Registries.ITEM)
                .addTag(ResourceLocation.parse("c:workbench"), Registries.ITEM)
                .addTag(ResourceLocation.parse("quad:fuel/wood"), Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(craftingTable);
    }
}
