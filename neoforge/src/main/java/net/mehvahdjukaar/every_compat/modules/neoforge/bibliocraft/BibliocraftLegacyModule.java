package net.mehvahdjukaar.every_compat.modules.neoforge.bibliocraft;

import com.github.minecraftschurlimods.bibliocraft.content.bookcase.BookcaseBlock;
import com.github.minecraftschurlimods.bibliocraft.content.clock.FancyClockBlock;
import com.github.minecraftschurlimods.bibliocraft.content.clock.GrandfatherClockBlock;
import com.github.minecraftschurlimods.bibliocraft.content.fancyarmorstand.FancyArmorStandBlock;
import com.github.minecraftschurlimods.bibliocraft.content.fancycrafter.FancyCrafterBlock;
import com.github.minecraftschurlimods.bibliocraft.content.fancysign.FancySignBlock;
import com.github.minecraftschurlimods.bibliocraft.content.label.LabelBlock;
import com.github.minecraftschurlimods.bibliocraft.content.potionshelf.PotionShelfBlock;
import com.github.minecraftschurlimods.bibliocraft.content.shelf.ShelfBlock;
import com.github.minecraftschurlimods.bibliocraft.content.table.TableBlock;
import com.github.minecraftschurlimods.bibliocraft.content.toolrack.ToolRackBlock;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

import java.util.List;

import static net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys.SLAB;

//SUPPORT: v1.5.4+
public class BibliocraftLegacyModule extends EveryCompatModule {

    public final SimpleEntrySet<WoodType, Block> bookcase;
    public final SimpleEntrySet<WoodType, Block> fancy_armor_stand;
    public final SimpleEntrySet<WoodType, Block> fancy_clock;
    public final SimpleEntrySet<WoodType, Block> fancy_crafter;
    public final SimpleEntrySet<WoodType, Block> fancy_sign;
    public final SimpleEntrySet<WoodType, Block> grandfather_clock;
    public final SimpleEntrySet<WoodType, Block> label;
    public final SimpleEntrySet<WoodType, Block> potion_shelf;
    public final SimpleEntrySet<WoodType, Block> shelf;
    public final SimpleEntrySet<WoodType, Block> table;
    public final SimpleEntrySet<WoodType, Block> tool_rack;

    public BibliocraftLegacyModule(String modId) {
        super(modId, "bcl");
        ResourceLocation tab = modRes(modId);

        bookcase = SimpleEntrySet.builder(WoodType.class, "bookcase",
                        getModBlock("oak_bookcase"), () -> VanillaWoodTypes.OAK,
                        w -> new BookcaseBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                .requiresChildren(SLAB) //REASON: recipes
                .addTile(getModTile("bookcase"))
                //TEXTURES: planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("bookcases"), Registries.BLOCK, Registries.ITEM)
                .setTabKey(tab)
                .addRecipe(modRes("wood/oak/bookcase"))
                .build();
        this.addEntry(bookcase);

        fancy_armor_stand = SimpleEntrySet.builder(WoodType.class, "fancy_armor_stand",
                        getModBlock("oak_fancy_armor_stand"), () -> VanillaWoodTypes.OAK,
                        w -> new FancyArmorStandBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                .requiresChildren(SLAB) //REASON: recipes
                .addTile(getModTile("fancy_armor_stand"))
                //TEXTURES: planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("fancy_armor_stands"), Registries.BLOCK, Registries.ITEM)
                .addTag(modRes("fancy_armor_stands/wood"), Registries.BLOCK, Registries.ITEM)
                .setTabKey(tab)
                .addRecipe(modRes("wood/oak/fancy_armor_stand"))
                .build();
        this.addEntry(fancy_armor_stand);

        fancy_clock = SimpleEntrySet.builder(WoodType.class, "fancy_clock",
                        getModBlock("oak_fancy_clock"), () -> VanillaWoodTypes.OAK,
                        w -> new FancyClockBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                .requiresChildren(SLAB) //REASON: recipes
                .addTile(getModTile("clock"))
                //TEXTURES: planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("fancy_clocks"), Registries.BLOCK, Registries.ITEM)
                .setTabKey(tab)
                .addRecipe(modRes("wood/oak/fancy_clock"))
                .build();
        this.addEntry(fancy_clock);

        fancy_crafter = SimpleEntrySet.builder(WoodType.class, "fancy_crafter",
                        getModBlock("oak_fancy_crafter"), () -> VanillaWoodTypes.OAK,
                        w -> new FancyCrafterBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                .addTile(getModTile("fancy_crafter"))
                //TEXTURES: planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("fancy_crafters"), Registries.BLOCK, Registries.ITEM)
                .setTabKey(tab)
                .addRecipe(modRes("wood/oak/fancy_crafter"))
                .build();
        this.addEntry(fancy_crafter);

        fancy_sign = SimpleEntrySet.builder(WoodType.class, "fancy_sign",
                        getModBlock("oak_fancy_sign"), () -> VanillaWoodTypes.OAK,
                        w -> new FancySignBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                //TEXTURES: planks
                .addTile(getModTile("fancy_sign"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("fancy_signs"), Registries.BLOCK, Registries.ITEM)
                .setTabKey(tab)
                .addRecipe(modRes("wood/oak/fancy_sign"))
                .build();
        this.addEntry(fancy_sign);

        grandfather_clock = SimpleEntrySet.builder(WoodType.class, "grandfather_clock",
                        getModBlock("oak_grandfather_clock"), () -> VanillaWoodTypes.OAK,
                        w -> new GrandfatherClockBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                .requiresFromMap(fancy_clock.blocks) //REASON: recipes
                //TEXTURES: planks
                .addTile(getModTile("clock"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("grandfather_clocks"), Registries.BLOCK, Registries.ITEM)
                .setTabKey(tab)
                .addRecipe(modRes("wood/oak/grandfather_clock"))
                .build();
        this.addEntry(grandfather_clock);

        label = SimpleEntrySet.builder(WoodType.class, "label",
                        getModBlock("oak_label"), () -> VanillaWoodTypes.OAK,
                        w -> new LabelBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                .requiresChildren(SLAB) //REASON: recipes
                .addTile(getModTile("label"))
                //TEXTURES: planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("labels"), Registries.BLOCK, Registries.ITEM)
                .setTabKey(tab)
                .addRecipe(modRes("wood/oak/label"))
                .build();
        this.addEntry(label);

        potion_shelf = SimpleEntrySet.builder(WoodType.class, "potion_shelf",
                        getModBlock("oak_potion_shelf"), () -> VanillaWoodTypes.OAK,
                        w -> new PotionShelfBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                .requiresChildren(SLAB) //REASON: recipes
                .addTile(getModTile("potion_shelf"))
                //TEXTURES: planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("potion_shelves"), Registries.BLOCK, Registries.ITEM)
                .setTabKey(tab)
                .addRecipe(modRes("wood/oak/potion_shelf"))
                .build();
        this.addEntry(potion_shelf);

        shelf = SimpleEntrySet.builder(WoodType.class, "shelf",
                        getModBlock("oak_shelf"), () -> VanillaWoodTypes.OAK,
                        w -> new ShelfBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                .requiresChildren(SLAB) //REASON: recipes
                .addTile(getModTile("shelf"))
                //TEXTURES: planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("shelves"), Registries.BLOCK, Registries.ITEM)
                .setTabKey(tab)
                .addRecipe(modRes("wood/oak/shelf"))
                .build();
        this.addEntry(shelf);

        table = SimpleEntrySet.builder(WoodType.class, "table",
                        getModBlock("oak_table"), () -> VanillaWoodTypes.OAK,
                        w -> new TableBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                .addTile(getModTile("table"))
                //TEXTURES: planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("tables"), Registries.BLOCK, Registries.ITEM)
                .setTabKey(tab)
                .addRecipe(modRes("wood/oak/table"))
                .build();
        this.addEntry(table);

        tool_rack = SimpleEntrySet.builder(WoodType.class, "tool_rack",
                        getModBlock("oak_tool_rack"), () -> VanillaWoodTypes.OAK,
                        w -> new ToolRackBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                .addTile(getModTile("tool_rack"))
                //TEXTURES: planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("tool_racks"), Registries.BLOCK, Registries.ITEM)
                .setTabKey(tab)
                .addRecipe(modRes("wood/oak/tool_rack"))
                .build();
        this.addEntry(tool_rack);

    }

    @Override
    public List<String> getAlreadySupportedMods() {
        return List.of(
                "aether", "allthemodium", "ars_nouveau",
                "biomesoplenty", "butchercraft", "bwg",
                "cobblemon", "deep_aether", "deeperdarker",
                "eternal_starlight", "expandeddelight", "extradelight",
                "forbidden_arcanus", "gtceu", "integrateddynamics",
                "mynethersdelight", "occultism", "regionsunexplored",
                "silentgear", "twilightforest", "undergarden",
                "vampirism", "werewolves"
        );
    }
}