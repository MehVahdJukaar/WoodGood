package net.mehvahdjukaar.every_compat.modules.forge.chipped;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.Registry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.BarrelBlock;
import net.minecraft.world.level.block.Block;

//TODO: Fix recipes

public class ChippedModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> barrel;
    public final SimpleEntrySet<WoodType, Block> basketWoven;
    public final SimpleEntrySet<WoodType, Block> boxed;
    public final SimpleEntrySet<WoodType, Block> brickBond;
    public final SimpleEntrySet<WoodType, Block> bricky;
    public final SimpleEntrySet<WoodType, Block> cornered;
    public final SimpleEntrySet<WoodType, Block> crate;
    public final SimpleEntrySet<WoodType, Block> crated;
    public final SimpleEntrySet<WoodType, Block> crossLaced;
    public final SimpleEntrySet<WoodType, Block> crossed;
    public final SimpleEntrySet<WoodType, Block> detailed;
    public final SimpleEntrySet<WoodType, Block> diagonal;
    public final SimpleEntrySet<WoodType, Block> diamond;
    public final SimpleEntrySet<WoodType, Block> doubleHerringbone;
    public final SimpleEntrySet<WoodType, Block> enclosed;
    public final SimpleEntrySet<WoodType, Block> fine;
    public final SimpleEntrySet<WoodType, Block> fineVertical;
    public final SimpleEntrySet<WoodType, Block> framed;
    public final SimpleEntrySet<WoodType, Block> herringbone;
    public final SimpleEntrySet<WoodType, Block> hewn;
    public final SimpleEntrySet<WoodType, Block> laced;
    public final SimpleEntrySet<WoodType, Block> mosaic;
    public final SimpleEntrySet<WoodType, Block> nailed;
    public final SimpleEntrySet<WoodType, Block> natural;
    public final SimpleEntrySet<WoodType, Block> panel;
    public final SimpleEntrySet<WoodType, Block> pegged;
    public final SimpleEntrySet<WoodType, Block> polished;
    public final SimpleEntrySet<WoodType, Block> shavings;
    public final SimpleEntrySet<WoodType, Block> railed;
    public final SimpleEntrySet<WoodType, Block> reinforcedCrate;
    public final SimpleEntrySet<WoodType, Block> shifted;
    public final SimpleEntrySet<WoodType, Block> slanted;
    public final SimpleEntrySet<WoodType, Block> smooth;
    public final SimpleEntrySet<WoodType, Block> stacked;
    public final SimpleEntrySet<WoodType, Block> thin;
    public final SimpleEntrySet<WoodType, Block> tiled;
    public final SimpleEntrySet<WoodType, Block> versailles;
    public final SimpleEntrySet<WoodType, Block> vertical;
    public final SimpleEntrySet<WoodType, Block> verticallyRailed;
    public final SimpleEntrySet<WoodType, Block> whirlwind;
    public final SimpleEntrySet<WoodType, Block> wickered;

    // TODO: Fix recipes & tags
    // `chipped:oak_planks` tags should be changed to `modid:modded_planks`
    // Create a recipe using the new tag for `chipped:recipes/carpenters_table`
    // Currently all variants are crafted with Oak Planks due to them using the `chipped:oak_planks` tag
    public ChippedModule(String modId) {
        super(modId, "ch");
        CreativeModeTab tab = CreativeModeTab.TAB_BUILDING_BLOCKS;

        mosaic = SimpleEntrySet.builder(WoodType.class, "planks_mosaic",
                        () -> getModBlock("oak_planks_mosaic"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/oak_planks_mosaic"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::dullPalette)
                .setTab(() -> tab)
                .build();

        this.addEntry(mosaic);

        panel = SimpleEntrySet.builder(WoodType.class, "planks_panel",
                        () -> getModBlock("oak_planks_panel"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/ctm/oak_planks_panel_ctm"))
                .addTexture(modRes("block/oak_planks/oak_planks_panel"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(p -> p.remove(p.getDarkest()))
                .setTab(() -> tab)
                .build();

        this.addEntry(panel);

        shavings = SimpleEntrySet.builder(WoodType.class, "planks_shavings",
                        () -> getModBlock("oak_planks_shavings"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/oak_planks_shavings"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.ITEM_REGISTRY)
                .setTab(() -> tab)
                .build();

        this.addEntry(shavings);

        basketWoven = SimpleEntrySet.builder(WoodType.class, "planks", "basket_woven",
                        () -> getModBlock("basket_woven_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/basket_woven_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkerPalette)
                .setTab(() -> tab)
                .build();

        this.addEntry(basketWoven);

        boxed = SimpleEntrySet.builder(WoodType.class, "planks", "boxed",
                        () -> getModBlock("boxed_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/boxed_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkerPalette)
                .setTab(() -> tab)
                .build();

        this.addEntry(boxed);

        brickBond = SimpleEntrySet.builder(WoodType.class, "planks", "brick_bond",
                        () -> getModBlock("brick_bond_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/brick_bond_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::neutralPalette)
                .setTab(() -> tab)
                .build();

        this.addEntry(brickBond);

        bricky = SimpleEntrySet.builder(WoodType.class, "planks", "bricky",
                        () -> getModBlock("bricky_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/bricky_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkerPalette)
                .setTab(() -> tab)
                .build();

        this.addEntry(bricky);

        cornered = SimpleEntrySet.builder(WoodType.class, "planks", "cornered",
                        () -> getModBlock("cornered_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/ctm/cornered_oak_planks_ctm"))
                .addTexture(modRes("block/oak_planks/cornered_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(p -> p.remove(p.getDarkest()))
                .setTab(() -> tab)
                .build();

        this.addEntry(cornered);

        crated = SimpleEntrySet.builder(WoodType.class, "planks", "crated",
                        () -> getModBlock("crated_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/ctm/crated_oak_planks_ctm"))
                .addTexture(modRes("block/oak_planks/crated_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(p -> p.remove(p.getDarkest()))
                .setTab(() -> tab)
                .build();

        this.addEntry(crated);

        crossLaced = SimpleEntrySet.builder(WoodType.class, "planks", "cross_laced",
                        () -> getModBlock("cross_laced_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/cross_laced_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.ITEM_REGISTRY)
                .setTab(() -> tab)
                .build();

        this.addEntry(crossLaced);

        crossed = SimpleEntrySet.builder(WoodType.class, "planks", "crossed",
                        () -> getModBlock("crossed_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/crossed_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.ITEM_REGISTRY)
                .setTab(() -> tab)
                .build();

        this.addEntry(crossed);

        detailed = SimpleEntrySet.builder(WoodType.class, "planks", "detailed",
                        () -> getModBlock("detailed_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/detailed_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkerPalette)
                .setTab(() -> tab)
                .build();

        this.addEntry(detailed);

        diagonal = SimpleEntrySet.builder(WoodType.class, "planks", "diagonal",
                        () -> getModBlock("diagonal_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/diagonal_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkerPalette)
                .setTab(() -> tab)
                .build();

        this.addEntry(diagonal);

        diamond = SimpleEntrySet.builder(WoodType.class, "planks", "diamond",
                        () -> getModBlock("diamond_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/diamond_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.ITEM_REGISTRY)
                .setTab(() -> tab)
                .build();

        this.addEntry(diamond);

        doubleHerringbone = SimpleEntrySet.builder(WoodType.class, "planks", "double_herringbone",
                        () -> getModBlock("double_herringbone_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/double_herringbone_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkestPalette)
                .setTab(() -> tab)
                .build();

        this.addEntry(doubleHerringbone);

        enclosed = SimpleEntrySet.builder(WoodType.class, "planks", "enclosed",
                        () -> getModBlock("enclosed_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/ctm/enclosed_oak_planks_ctm"))
                .addTexture(modRes("block/oak_planks/enclosed_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(p -> p.remove(p.getDarkest()))
                .setTab(() -> tab)
                .build();

        this.addEntry(enclosed);

        fine = SimpleEntrySet.builder(WoodType.class, "planks", "fine",
                        () -> getModBlock("fine_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/fine_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::dullPalette)
                .setTab(() -> tab)
                .build();

        this.addEntry(fine);

        fineVertical = SimpleEntrySet.builder(WoodType.class, "planks", "fine_vertical",
                        () -> getModBlock("fine_vertical_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/fine_vertical_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::dullPalette)
                .setTab(() -> tab)
                .build();

        this.addEntry(fineVertical);

        framed = SimpleEntrySet.builder(WoodType.class, "planks", "framed",
                        () -> getModBlock("framed_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/ctm/framed_oak_planks_ctm"))
                .addTexture(modRes("block/oak_planks/framed_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(p -> p.remove(p.getDarkest()))
                .setTab(() -> tab)
                .build();

        this.addEntry(framed);

        herringbone = SimpleEntrySet.builder(WoodType.class, "planks", "herringbone",
                        () -> getModBlock("herringbone_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/herringbone_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkestPalette)
                .setTab(() -> tab)
                .build();

        this.addEntry(herringbone);

        hewn = SimpleEntrySet.builder(WoodType.class, "planks", "hewn",
                        () -> getModBlock("hewn_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/hewn_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkerPalette)
                .setTab(() -> tab)
                .build();

        this.addEntry(hewn);

        laced = SimpleEntrySet.builder(WoodType.class, "planks", "laced",
                        () -> getModBlock("laced_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/laced_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkerPalette)
                .setTab(() -> tab)
                .build();

        this.addEntry(laced);

        nailed = SimpleEntrySet.builder(WoodType.class, "planks", "nailed",
                        () -> getModBlock("nailed_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/nailed_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkerPalette)
                .setTab(() -> tab)
                .build();

        this.addEntry(nailed);

        natural = SimpleEntrySet.builder(WoodType.class, "planks", "natural",
                        () -> getModBlock("natural_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/ctm/natural_oak_planks_ctm"))
                .addTexture(modRes("block/oak_planks/natural_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(p -> p.remove(p.getDarkest()))
                .setTab(() -> tab)
                .build();

        this.addEntry(natural);

        pegged = SimpleEntrySet.builder(WoodType.class, "planks", "pegged",
                        () -> getModBlock("pegged_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/ctm/pegged_oak_planks_ctm"))
                .addTexture(modRes("block/oak_planks/pegged_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(p -> p.remove(p.getDarkest()))
                .setTab(() -> tab)
                .build();

        this.addEntry(pegged);

        polished = SimpleEntrySet.builder(WoodType.class, "planks", "polished",
                        () -> getModBlock("polished_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/polished_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::dullerPalette)
                .setTab(() -> tab)
                .build();

        this.addEntry(polished);

        railed = SimpleEntrySet.builder(WoodType.class, "planks", "railed",
                        () -> getModBlock("railed_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/railed_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.ITEM_REGISTRY)
                .setTab(() -> tab)
                .build();

        this.addEntry(railed);

        shifted = SimpleEntrySet.builder(WoodType.class, "planks", "shifted",
                        () -> getModBlock("shifted_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/shifted_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkerPalette)
                .setTab(() -> tab)
                .build();

        this.addEntry(shifted);

        slanted = SimpleEntrySet.builder(WoodType.class, "planks", "slanted",
                        () -> getModBlock("slanted_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/slanted_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkestPalette)
                .setTab(() -> tab)
                .build();

        this.addEntry(slanted);

        smooth = SimpleEntrySet.builder(WoodType.class, "planks", "smooth",
                        () -> getModBlock("smooth_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/smooth_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(p -> p.remove(p.getDarkest()))
                .setTab(() -> tab)
                .build();

        this.addEntry(smooth);

        stacked = SimpleEntrySet.builder(WoodType.class, "planks", "stacked",
                        () -> getModBlock("stacked_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/stacked_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkerPalette)
                .setTab(() -> tab)
                .build();

        this.addEntry(stacked);

        thin = SimpleEntrySet.builder(WoodType.class, "planks", "thin",
                        () -> getModBlock("thin_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/thin_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkerPalette)
                .setTab(() -> tab)
                .build();

        this.addEntry(thin);

        tiled = SimpleEntrySet.builder(WoodType.class, "planks", "tiled",
                        () -> getModBlock("tiled_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/tiled_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkerPalette)
                .setTab(() -> tab)
                .build();

        this.addEntry(tiled);

        versailles = SimpleEntrySet.builder(WoodType.class, "planks", "versailles",
                        () -> getModBlock("versailles_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/versailles_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkerPalette)
                .setTab(() -> tab)
                .build();

        this.addEntry(versailles);

        vertical = SimpleEntrySet.builder(WoodType.class, "planks", "vertical",
                        () -> getModBlock("vertical_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/vertical_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.ITEM_REGISTRY)
                .setTab(() -> tab)
                .build();

        this.addEntry(vertical);

        verticallyRailed = SimpleEntrySet.builder(WoodType.class, "planks", "vertically_railed",
                        () -> getModBlock("vertically_railed_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/vertically_railed_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.ITEM_REGISTRY)
                .setTab(() -> tab)
                .build();

        this.addEntry(verticallyRailed);

        whirlwind = SimpleEntrySet.builder(WoodType.class, "planks", "whirlwind",
                        () -> getModBlock("whirlwind_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/whirlwind_oak_planks"))
                .addTexture(modRes("block/oak_planks/ctm/whirlwind_oak_planks_ctm"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(p -> p.remove(p.getDarkest()))
                .setTab(() -> tab)
                .build();

        this.addEntry(whirlwind);

        wickered = SimpleEntrySet.builder(WoodType.class, "planks", "wickered",
                        () -> getModBlock("wickered_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/wickered_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_planks"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkerPalette)
                .setTab(() -> tab)
                .build();

        this.addEntry(wickered);

        barrel = SimpleEntrySet.builder(WoodType.class, "barrel",
                        () -> getModBlock("oak_barrel"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BarrelBlock(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/barrel/oak_barrel_bottom"))
                .addTexture(modRes("block/barrel/oak_barrel_top_open"))
                .addTextureM(modRes("block/barrel/oak_barrel_side"), EveryCompat.res("block/ch/oak_barrel_side_m"))
                .addTextureM(modRes("block/barrel/oak_barrel_top"), EveryCompat.res("block/ch/oak_barrel_top_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("barrel"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("barrel"), Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkerPalette)
                .setTab(() -> tab)
                .build();

        this.addEntry(barrel);

        crate = SimpleEntrySet.builder(WoodType.class, "crate",
                        () -> getModBlock("oak_crate"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BarrelBlock(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/barrel/oak_crate_side"))
                .addTexture(modRes("block/barrel/oak_crate_top"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("barrel"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("barrel"), Registry.ITEM_REGISTRY)
                .createPaletteFromOak(p -> p.remove(p.getDarkest()))
                .setTab(() -> tab)
                .build();

        this.addEntry(crate);

        reinforcedCrate = SimpleEntrySet.builder(WoodType.class, "crate", "reinforced",
                        () -> getModBlock("reinforced_oak_crate"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BarrelBlock(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/barrel/reinforced_oak_crate_side"))
                .addTexture(modRes("block/barrel/reinforced_oak_crate_top"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("barrel"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("barrel"), Registry.ITEM_REGISTRY)
                .createPaletteFromOak(p -> p.remove(p.getDarkest()))
                .setTab(() -> tab)
                .build();

        this.addEntry(reinforcedCrate);
    }

    private void dullPalette(Palette p) {
        p.add(p.increaseInner());
        p.remove(p.getLightest());
        p.remove(p.getDarkest());
        p.remove(p.getDarkest());
        p.remove(p.getDarkest());
    }

    private void dullerPalette(Palette p) {
        p.remove(p.getLightest());
        p.remove(p.getLightest());
        p.remove(p.getDarkest());
        p.remove(p.getDarkest());
    }

    private void lighterPalette(Palette p) {
        p.remove(p.getDarkest());
        p.remove(p.getDarkest());
    }

    private void lightestPalette(Palette p) {
        p.remove(p.getLightest());
        p.remove(p.getDarkest());
        p.remove(p.getDarkest());
    }

    private void darkerPalette(Palette p) {
        p.remove(p.getDarkest());
        p.remove(p.getLightest());
    }

    private void darkestPalette(Palette p) {
        p.remove(p.getDarkest());
        p.remove(p.getLightest());
        p.remove(p.getLightest());
    }

    private void neutralPalette(Palette p) {
        p.remove(p.getLightest());
        p.remove(p.getLightest());
        p.remove(p.getDarkest());
        p.remove(p.getDarkest());
    }

// Disabled due to it also creating textures files instead of only tags
//    @Override
//    public void addDynamicServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
//        super.addDynamicServerResources(handler, manager);
//
//            for (var w : WoodTypeRegistry.getTypes()) {
//                SimpleTagBuilder planks_tag = SimpleTagBuilder.of(ResourceLocation.tryParse(w.id + "_planks"));
//                handler.dynamicPack.addTag(planks_tag.add(ResourceLocation.tryParse(w.id + "_planks")), Registry.BLOCK_REGISTRY);
//                handler.dynamicPack.addTag(planks_tag.add(ResourceLocation.tryParse(w.id + "_planks")), Registry.ITEM_REGISTRY);
//            }
//    }
}
