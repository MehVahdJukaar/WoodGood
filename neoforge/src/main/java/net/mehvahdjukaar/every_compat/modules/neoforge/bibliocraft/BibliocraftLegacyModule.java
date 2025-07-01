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
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

import java.util.List;
import java.util.Set;

//SUPPORT: v1.5.4+
public class BibliocraftLegacyModule extends SimpleModule {

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
                        getModBlock("oak_bookcase"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BookcaseBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                .requiresChildren("slab") //REASON: recipes
                //TEXTURES: planks
                .addTexture(modRes("block/oak_bookcase"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("bookcases"), Registries.BLOCK)
                .addTag(modRes("bookcases"), Registries.ITEM)
                .setTabKey(tab)
                .addRecipe(modRes("wood/oak/bookcase"))
                .build();
        this.addEntry(bookcase);

        fancy_armor_stand = SimpleEntrySet.builder(WoodType.class, "fancy_armor_stand",
                        getModBlock("oak_fancy_armor_stand"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new FancyArmorStandBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                .requiresChildren("slab") //REASON: recipes
                //TEXTURES: planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("fancy_armor_stands"), Registries.BLOCK)
                .addTag(modRes("fancy_armor_stands/wood"), Registries.BLOCK)
                .addTag(modRes("fancy_armor_stands"), Registries.ITEM)
                .addTag(modRes("fancy_armor_stands/wood"), Registries.ITEM)
                .setTabKey(tab)
                .addRecipe(modRes("wood/oak/fancy_armor_stand"))
                .build();
        this.addEntry(fancy_armor_stand);

        fancy_clock = SimpleEntrySet.builder(WoodType.class, "fancy_clock",
                        getModBlock("oak_fancy_clock"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new FancyClockBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                .requiresChildren("slab") //REASON: recipes
                //TEXTURES: planks
                .addTile(getModTile("clock"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("fancy_clocks"), Registries.BLOCK)
                .addTag(modRes("fancy_clocks"), Registries.ITEM)
                .setTabKey(tab)
                .addRecipe(modRes("wood/oak/fancy_clock"))
                .build();
        this.addEntry(fancy_clock);

        fancy_crafter = SimpleEntrySet.builder(WoodType.class, "fancy_crafter",
                        getModBlock("oak_fancy_crafter"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new FancyCrafterBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                //TEXTURES: planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("fancy_crafters"), Registries.BLOCK)
                .addTag(modRes("fancy_crafters"), Registries.ITEM)
                .setTabKey(tab)
                .addRecipe(modRes("wood/oak/fancy_crafter"))
                .build();
        this.addEntry(fancy_crafter);

        fancy_sign = SimpleEntrySet.builder(WoodType.class, "fancy_sign",
                        getModBlock("oak_fancy_sign"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new FancySignBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                //TEXTURES: planks
                .addTile(getModTile("fancy_sign"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("fancy_signs"), Registries.BLOCK)
                .addTag(modRes("fancy_signs"), Registries.ITEM)
                .setTabKey(tab)
                .addRecipe(modRes("wood/oak/fancy_sign"))
                .build();
        this.addEntry(fancy_sign);

        grandfather_clock = SimpleEntrySet.builder(WoodType.class, "grandfather_clock",
                        getModBlock("oak_grandfather_clock"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new GrandfatherClockBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                .requiresFromMap(fancy_clock.blocks) //REASON: recipes
                //TEXTURES: planks
                .addTile(getModTile("clock"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("grandfather_clocks"), Registries.BLOCK)
                .addTag(modRes("grandfather_clocks"), Registries.ITEM)
                .setTabKey(tab)
                .addRecipe(modRes("wood/oak/grandfather_clock"))
                .build();
        this.addEntry(grandfather_clock);

        label = SimpleEntrySet.builder(WoodType.class, "label",
                        getModBlock("oak_label"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new LabelBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                .requiresChildren("slab") //REASON: recipes
                //TEXTURES: planks
                .addTile(getModTile("label"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("labels"), Registries.BLOCK)
                .addTag(modRes("labels"), Registries.ITEM)
                .setTabKey(tab)
                .addRecipe(modRes("wood/oak/label"))
                .build();
        this.addEntry(label);

        potion_shelf = SimpleEntrySet.builder(WoodType.class, "potion_shelf",
                        getModBlock("oak_potion_shelf"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new PotionShelfBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                .requiresChildren("slab") //REASON: recipes
                //TEXTURES: planks
                .addTile(getModTile("potion_shelf"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("potion_shelves"), Registries.BLOCK)
                .addTag(modRes("potion_shelves"), Registries.ITEM)
                .setTabKey(tab)
                .addRecipe(modRes("wood/oak/potion_shelf"))
                .build();
        this.addEntry(potion_shelf);

        shelf = SimpleEntrySet.builder(WoodType.class, "shelf",
                        getModBlock("oak_shelf"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ShelfBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                .requiresChildren("slab") //REASON: recipes
                //TEXTURES: planks
                .addTile(getModTile("shelf"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("shelves"), Registries.BLOCK)
                .addTag(modRes("shelves"), Registries.ITEM)
                .setTabKey(tab)
                .addRecipe(modRes("wood/oak/shelf"))
                .build();
        this.addEntry(shelf);

        table = SimpleEntrySet.builder(WoodType.class, "table",
                        getModBlock("oak_table"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TableBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                //TEXTURES: planks
                .addTile(getModTile("table"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("tables"), Registries.BLOCK)
                .addTag(modRes("tables"), Registries.ITEM)
                .setTabKey(tab)
                .addRecipe(modRes("wood/oak/table"))
                .build();
        this.addEntry(table);

        tool_rack = SimpleEntrySet.builder(WoodType.class, "tool_rack",
                        getModBlock("oak_tool_rack"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ToolRackBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                //TEXTURES: planks
                .addTile(getModTile("tool_rack"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("tool_racks"), Registries.BLOCK)
                .addTag(modRes("tool_racks"), Registries.ITEM)
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