package net.mehvahdjukaar.every_compat.modules.forge.chipped;

import earth.terrarium.chipped.common.registry.ModItems;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.block.BarrelBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.WallTorchBlock;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;

// TODO: Fix recipes & tags
// `chipped:oak_planks` tags should be changed to `modid:modded_planks`
// Create a recipe using the new tag for `chipped:recipes/carpenters_table`
// Currently all variants are crafted with Oak Planks due to them using the `chipped:oak_planks` tag
// The same should be done for door variants
// Fix doors dropping two items

public class ChippedModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> barrel;
    public final SimpleEntrySet<WoodType, Block> crate;
    public final SimpleEntrySet<WoodType, Block> reinforcedCrate;
    public final SimpleEntrySet<WoodType, Block> basketWoven;
    public final SimpleEntrySet<WoodType, Block> boxed;
    public final SimpleEntrySet<WoodType, Block> brickBond;
    public final SimpleEntrySet<WoodType, Block> bricky;
    public final SimpleEntrySet<WoodType, Block> cornered;
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
    public final SimpleEntrySet<WoodType, Block> barredDoor;
    public final SimpleEntrySet<WoodType, Block> beachDoor;
    public final SimpleEntrySet<WoodType, Block> boardedDoor;
    public final SimpleEntrySet<WoodType, Block> dualPaneledDoor;
    public final SimpleEntrySet<WoodType, Block> fortifiedDoor;
    public final SimpleEntrySet<WoodType, Block> gatedDoor;
    public final SimpleEntrySet<WoodType, Block> glassDoor;
    public final SimpleEntrySet<WoodType, Block> heavyDoor;
    public final SimpleEntrySet<WoodType, Block> overgrownDoor;
    public final SimpleEntrySet<WoodType, Block> paneledDoor;
    public final SimpleEntrySet<WoodType, Block> paperDoor;
    public final SimpleEntrySet<WoodType, Block> pressedDoor;
    public final SimpleEntrySet<WoodType, Block> screenDoor;
    public final SimpleEntrySet<WoodType, Block> secretDoor;
    public final SimpleEntrySet<WoodType, Block> shackDoor;
    public final SimpleEntrySet<WoodType, Block> slidingDoor;
    public final SimpleEntrySet<WoodType, Block> supportedDoor;
    public final SimpleEntrySet<WoodType, Block> tileWindowedDoor;
    public final SimpleEntrySet<WoodType, Block> tiledDoor;
    public final SimpleEntrySet<WoodType, Block> windowedDoor;
    public final SimpleEntrySet<WoodType, Block> torch;
    public final SimpleEntrySet<WoodType, Block> wallTorch;

    public ChippedModule(String modId) {
        super(modId, "ch");
        CreativeModeTab tab = ModItems.ITEM_GROUP;

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

        barredDoor = SimpleEntrySet.builder(WoodType.class, "door", "barred",
                        () -> getModBlock("barred_oak_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.planks)))
                .addTextureM(modRes("block/oak_door/barred_oak_door_bottom"), EveryCompat.res("block/ch/doors/barred_oak_door_bottom_m"))
                .addTextureM(modRes("block/oak_door/barred_oak_door_top"), EveryCompat.res("block/ch/doors/barred_oak_door_top_m"))
                .addTextureM(EveryCompat.res("item/ch/doors/barred_oak_door"), EveryCompat.res("item/ch/doors/barred_oak_door_m"))
                .addModelTransform(m -> m.replaceString("chipped:item/oak_door", "chipped:item/ch/doors")
                        .replaceGenericType("oak", "item/ch/doors"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_door"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_door"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.ITEM_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();

        this.addEntry(barredDoor);

        beachDoor = SimpleEntrySet.builder(WoodType.class, "door", "beach",
                        () -> getModBlock("beach_oak_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.planks)))
                .addTextureM(modRes("block/oak_door/beach_oak_door_bottom"), EveryCompat.res("block/ch/doors/beach_oak_door_bottom_m"))
                .addTextureM(modRes("block/oak_door/beach_oak_door_top"), EveryCompat.res("block/ch/doors/beach_oak_door_top_m"))
                .addTextureM(EveryCompat.res("item/ch/doors/beach_oak_door"), EveryCompat.res("item/ch/doors/beach_oak_door_m"))
                .addModelTransform(m -> m.replaceString("chipped:item/oak_door", "chipped:item/ch/doors")
                        .replaceGenericType("oak", "item/ch/doors"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_door"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_door"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.ITEM_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();

        this.addEntry(beachDoor);

        boardedDoor = SimpleEntrySet.builder(WoodType.class, "door", "boarded",
                        () -> getModBlock("boarded_oak_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.planks)))
                .addTextureM(modRes("block/oak_door/boarded_oak_door_bottom"), EveryCompat.res("block/ch/doors/boarded_oak_door_bottom_m"))
                .addTextureM(modRes("block/oak_door/boarded_oak_door_top"), EveryCompat.res("block/ch/doors/boarded_oak_door_top_m"))
                .addTextureM(EveryCompat.res("item/ch/doors/boarded_oak_door"), EveryCompat.res("item/ch/doors/boarded_oak_door_m"))
                .addModelTransform(m -> m.replaceString("chipped:item/oak_door", "chipped:item/ch/doors")
                        .replaceGenericType("oak", "item/ch/doors"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_door"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_door"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(p -> p.remove(p.getLightest()))
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();

        this.addEntry(boardedDoor);

        dualPaneledDoor = SimpleEntrySet.builder(WoodType.class, "door", "dual_paneled",
                        () -> getModBlock("dual_paneled_oak_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.planks)))
                .addTextureM(modRes("block/oak_door/dual_paneled_oak_door_bottom"), EveryCompat.res("block/ch/doors/dual_paneled_oak_door_bottom_m"))
                .addTextureM(modRes("block/oak_door/dual_paneled_oak_door_top"), EveryCompat.res("block/ch/doors/dual_paneled_oak_door_top_m"))
                .addTextureM(EveryCompat.res("item/ch/doors/dual_paneled_oak_door"), EveryCompat.res("item/ch/doors/dual_paneled_oak_door_m"))
                .addModelTransform(m -> m.replaceString("chipped:item/oak_door", "chipped:item/ch/doors")
                        .replaceGenericType("oak", "item/ch/doors"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_door"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_door"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(p -> p.remove(p.getLightest()))
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();

        this.addEntry(dualPaneledDoor);

        fortifiedDoor = SimpleEntrySet.builder(WoodType.class, "door", "fortified",
                        () -> getModBlock("fortified_oak_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.planks)))
                .addTextureM(modRes("block/oak_door/fortified_oak_door_bottom"), EveryCompat.res("block/ch/doors/fortified_oak_door_bottom_m"))
                .addTextureM(modRes("block/oak_door/fortified_oak_door_top"), EveryCompat.res("block/ch/doors/fortified_oak_door_top_m"))
                .addTextureM(EveryCompat.res("item/ch/doors/fortified_oak_door"), EveryCompat.res("item/ch/doors/fortified_oak_door_m"))
                .addModelTransform(m -> m.replaceString("chipped:item/oak_door", "chipped:item/ch/doors")
                        .replaceGenericType("oak", "item/ch/doors"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_door"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_door"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(p -> p.remove(p.getLightest()))
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();

        this.addEntry(fortifiedDoor);

        gatedDoor = SimpleEntrySet.builder(WoodType.class, "door", "gated",
                        () -> getModBlock("gated_oak_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.planks)))
                .addTextureM(modRes("block/oak_door/gated_oak_door_bottom"), EveryCompat.res("block/ch/doors/gated_oak_door_bottom_m"))
                .addTextureM(modRes("block/oak_door/gated_oak_door_top"), EveryCompat.res("block/ch/doors/gated_oak_door_top_m"))
                .addTextureM(EveryCompat.res("item/ch/doors/gated_oak_door"), EveryCompat.res("item/ch/doors/gated_oak_door_m"))
                .addModelTransform(m -> m.replaceString("chipped:item/oak_door", "chipped:item/ch/doors")
                        .replaceGenericType("oak", "item/ch/doors"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_door"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_door"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(p -> p.remove(p.getLightest()))
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();

        this.addEntry(gatedDoor);

        glassDoor = SimpleEntrySet.builder(WoodType.class, "door", "glass",
                        () -> getModBlock("glass_oak_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.planks)))
                .addTextureM(modRes("block/oak_door/glass_oak_door_bottom"), EveryCompat.res("block/ch/doors/glass_oak_door_bottom_m"))
                .addTextureM(modRes("block/oak_door/glass_oak_door_top"), EveryCompat.res("block/ch/doors/glass_oak_door_top_m"))
                .addTextureM(EveryCompat.res("item/ch/doors/glass_oak_door"), EveryCompat.res("item/ch/doors/glass_oak_door_m"))
                .addModelTransform(m -> m.replaceString("chipped:item/oak_door", "chipped:item/ch/doors")
                        .replaceGenericType("oak", "item/ch/doors"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_door"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_door"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.ITEM_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();

        this.addEntry(glassDoor);

        heavyDoor = SimpleEntrySet.builder(WoodType.class, "door", "heavy",
                        () -> getModBlock("heavy_oak_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.planks)))
                .addTextureM(modRes("block/oak_door/heavy_oak_door_bottom"), EveryCompat.res("block/ch/doors/heavy_oak_door_bottom_m"))
                .addTextureM(modRes("block/oak_door/heavy_oak_door_top"), EveryCompat.res("block/ch/doors/heavy_oak_door_top_m"))
                .addTextureM(EveryCompat.res("item/ch/doors/heavy_oak_door"), EveryCompat.res("item/ch/doors/heavy_oak_door_m"))
                .addModelTransform(m -> m.replaceString("chipped:item/oak_door", "chipped:item/ch/doors")
                        .replaceGenericType("oak", "item/ch/doors"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_door"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_door"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(p -> p.remove(p.getLightest()))
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();

        this.addEntry(heavyDoor);

        overgrownDoor = SimpleEntrySet.builder(WoodType.class, "door", "overgrown",
                        () -> getModBlock("overgrown_oak_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.planks)))
                .addTextureM(modRes("block/oak_door/overgrown_oak_door_bottom"), EveryCompat.res("block/ch/doors/overgrown_oak_door_bottom_m"))
                .addTextureM(modRes("block/oak_door/overgrown_oak_door_top"), EveryCompat.res("block/ch/doors/overgrown_oak_door_top_m"))
                .addTextureM(EveryCompat.res("item/ch/doors/overgrown_oak_door"), EveryCompat.res("item/ch/doors/overgrown_oak_door_m"))
                .addModelTransform(m -> m.replaceString("chipped:item/oak_door", "chipped:item/ch/doors")
                        .replaceGenericType("oak", "item/ch/doors"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_door"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_door"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.ITEM_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();

        this.addEntry(overgrownDoor);

        paneledDoor = SimpleEntrySet.builder(WoodType.class, "door", "paneled",
                        () -> getModBlock("paneled_oak_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.planks)))
                .addTextureM(modRes("block/oak_door/paneled_oak_door_bottom"), EveryCompat.res("block/ch/doors/paneled_oak_door_bottom_m"))
                .addTextureM(modRes("block/oak_door/paneled_oak_door_top"), EveryCompat.res("block/ch/doors/paneled_oak_door_top_m"))
                .addTextureM(EveryCompat.res("item/ch/doors/paneled_oak_door"), EveryCompat.res("item/ch/doors/paneled_oak_door_m"))
                .addModelTransform(m -> m.replaceString("chipped:item/oak_door", "chipped:item/ch/doors")
                        .replaceGenericType("oak", "item/ch/doors"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_door"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_door"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(p -> p.remove(p.getLightest()))
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();

        this.addEntry(paneledDoor);

        paperDoor = SimpleEntrySet.builder(WoodType.class, "door", "paper",
                        () -> getModBlock("paper_oak_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.planks)))
                .addTextureM(modRes("block/oak_door/paper_oak_door_bottom"), EveryCompat.res("block/ch/doors/paper_oak_door_bottom_m"))
                .addTextureM(modRes("block/oak_door/paper_oak_door_top"), EveryCompat.res("block/ch/doors/paper_oak_door_top_m"))
                .addTextureM(EveryCompat.res("item/ch/doors/paper_oak_door"), EveryCompat.res("item/ch/doors/paper_oak_door_m"))
                .addModelTransform(m -> m.replaceString("chipped:item/oak_door", "chipped:item/ch/doors")
                        .replaceGenericType("oak", "item/ch/doors"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_door"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_door"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(p -> p.remove(p.getLightest()))
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();

        this.addEntry(paperDoor);

        pressedDoor = SimpleEntrySet.builder(WoodType.class, "door", "pressed",
                        () -> getModBlock("pressed_oak_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.planks)))
                .addTextureM(modRes("block/oak_door/pressed_oak_door_bottom"), EveryCompat.res("block/ch/doors/pressed_oak_door_bottom_m"))
                .addTextureM(modRes("block/oak_door/pressed_oak_door_top"), EveryCompat.res("block/ch/doors/pressed_oak_door_top_m"))
                .addTextureM(EveryCompat.res("item/ch/doors/pressed_oak_door"), EveryCompat.res("item/ch/doors/pressed_oak_door_m"))
                .addModelTransform(m -> m.replaceString("chipped:item/oak_door", "chipped:item/ch/doors")
                        .replaceGenericType("oak", "item/ch/doors"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_door"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_door"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(p -> p.remove(p.getLightest()))
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();

        this.addEntry(pressedDoor);

        screenDoor = SimpleEntrySet.builder(WoodType.class, "door", "screen",
                        () -> getModBlock("screen_oak_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.planks)))
                .addTextureM(modRes("block/oak_door/screen_oak_door_bottom"), EveryCompat.res("block/ch/doors/screen_oak_door_bottom_m"))
                .addTextureM(modRes("block/oak_door/screen_oak_door_top"), EveryCompat.res("block/ch/doors/screen_oak_door_top_m"))
                .addTextureM(EveryCompat.res("item/ch/doors/screen_oak_door"), EveryCompat.res("item/ch/doors/screen_oak_door_m"))
                .addModelTransform(m -> m.replaceString("chipped:item/oak_door", "chipped:item/ch/doors")
                        .replaceGenericType("oak", "item/ch/doors"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_door"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_door"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(p -> p.remove(p.getLightest()))
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();

        this.addEntry(screenDoor);

        secretDoor = SimpleEntrySet.builder(WoodType.class, "door", "secret",
                        () -> getModBlock("secret_oak_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.planks)))
                .addTextureM(modRes("block/oak_door/secret_oak_door_bottom"), EveryCompat.res("block/ch/doors/secret_oak_door_bottom_m"))
                .addTextureM(modRes("block/oak_door/secret_oak_door_top"), EveryCompat.res("block/ch/doors/secret_oak_door_top_m"))
                .addTextureM(EveryCompat.res("item/ch/doors/secret_oak_door"), EveryCompat.res("item/ch/doors/secret_oak_door_m"))
                .addModelTransform(m -> m.replaceString("chipped:item/oak_door", "chipped:item/ch/doors")
                        .replaceGenericType("oak", "item/ch/doors"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_door"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_door"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(p -> p.remove(p.getLightest()))
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();

        this.addEntry(secretDoor);

        shackDoor = SimpleEntrySet.builder(WoodType.class, "door", "shack",
                        () -> getModBlock("shack_oak_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_door/shack_oak_door_bottom"))
                .addTextureM(modRes("block/oak_door/shack_oak_door_top"), EveryCompat.res("block/ch/doors/shack_oak_door_top_m"))
                .addTextureM(EveryCompat.res("item/ch/doors/shack_oak_door"), EveryCompat.res("item/ch/doors/shack_oak_door_m"))
                .addModelTransform(m -> m.replaceString("chipped:item/oak_door", "chipped:item/ch/doors")
                        .replaceGenericType("oak", "item/ch/doors"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_door"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_door"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(p -> p.remove(p.getLightest()))
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();

        this.addEntry(shackDoor);

        slidingDoor = SimpleEntrySet.builder(WoodType.class, "door", "sliding",
                        () -> getModBlock("sliding_oak_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.planks)))
                .addTextureM(modRes("block/oak_door/sliding_oak_door_bottom"), EveryCompat.res("block/ch/doors/sliding_oak_door_bottom_m"))
                .addTextureM(modRes("block/oak_door/sliding_oak_door_top"), EveryCompat.res("block/ch/doors/sliding_oak_door_top_m"))
                .addTextureM(EveryCompat.res("item/ch/doors/sliding_oak_door"), EveryCompat.res("item/ch/doors/sliding_oak_door_m"))
                .addModelTransform(m -> m.replaceString("chipped:item/oak_door", "chipped:item/ch/doors")
                        .replaceGenericType("oak", "item/ch/doors"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_door"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_door"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(p -> p.remove(p.getLightest()))
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();

        this.addEntry(slidingDoor);

        supportedDoor = SimpleEntrySet.builder(WoodType.class, "door", "supported",
                        () -> getModBlock("supported_oak_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.planks)))
                .addTextureM(modRes("block/oak_door/supported_oak_door_bottom"), EveryCompat.res("block/ch/doors/supported_oak_door_bottom_m"))
                .addTextureM(modRes("block/oak_door/supported_oak_door_top"), EveryCompat.res("block/ch/doors/supported_oak_door_top_m"))
                .addTextureM(EveryCompat.res("item/ch/doors/supported_oak_door"), EveryCompat.res("item/ch/doors/supported_oak_door_m"))
                .addModelTransform(m -> m.replaceString("chipped:item/oak_door", "chipped:item/ch/doors")
                        .replaceGenericType("oak", "item/ch/doors"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_door"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_door"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::lightestPalette)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();

        this.addEntry(supportedDoor);

        tileWindowedDoor = SimpleEntrySet.builder(WoodType.class, "door", "tile_windowed",
                        () -> getModBlock("tile_windowed_oak_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.planks)))
                .addTextureM(EveryCompat.res("block/oak_door/tile_windowed_oak_door_bottom"), EveryCompat.res("block/ch/doors/tile_windowed_oak_door_bottom_m"))
                .addTextureM(EveryCompat.res("block/oak_door/tile_windowed_oak_door_top"), EveryCompat.res("block/ch/doors/tile_windowed_oak_door_top_m"))
                .addTextureM(EveryCompat.res("item/ch/doors/tile_windowed_oak_door"), EveryCompat.res("item/ch/doors/tile_windowed_oak_door_m"))
                .addModelTransform(m -> m.replaceString("chipped:item/oak_door", "chipped:item/ch/doors")
                        .replaceGenericType("oak", "item/ch/doors"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_door"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_door"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(p -> p.remove(p.getLightest()))
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();

        this.addEntry(tileWindowedDoor);

        tiledDoor = SimpleEntrySet.builder(WoodType.class, "door", "tiled",
                        () -> getModBlock("tiled_oak_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_door/tiled_oak_door_bottom"))
                .addTextureM(modRes("block/oak_door/tiled_oak_door_top"), EveryCompat.res("block/ch/doors/tiled_oak_door_top_m"))
                .addTextureM(EveryCompat.res("item/ch/doors/tiled_oak_door"), EveryCompat.res("item/ch/doors/tiled_oak_door_m"))
                .addModelTransform(m -> m.replaceString("chipped:item/oak_door", "chipped:item/ch/doors")
                        .replaceGenericType("oak", "item/ch/doors"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_door"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_door"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(p -> p.remove(p.getLightest()))
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();

        this.addEntry(tiledDoor);

        windowedDoor = SimpleEntrySet.builder(WoodType.class, "door", "windowed",
                        () -> getModBlock("windowed_oak_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_door/windowed_oak_door_bottom"))
                .addTextureM(modRes("block/oak_door/windowed_oak_door_top"), EveryCompat.res("block/ch/doors/windowed_oak_door_top_m"))
                .addTextureM(EveryCompat.res("item/ch/doors/windowed_oak_door"), EveryCompat.res("item/ch/doors/windowed_oak_door_m"))
                .addModelTransform(m -> m.replaceString("chipped:item/oak_door", "chipped:item/ch/doors")
                        .replaceGenericType("oak", "item/ch/doors"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_door"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("oak_door"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(p -> p.remove(p.getLightest()))
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();

        this.addEntry(windowedDoor);

        wallTorch = SimpleEntrySet.builder(WoodType.class, "wall_torch",
                        () -> getModBlock("spruce_wall_torch"), () -> WoodTypeRegistry.getValue(new ResourceLocation("spruce")),
                        w -> new WallTorchBlock(Utils.copyPropertySafe(w.planks).noCollission().instabreak()
                                .lightLevel((arg) -> { return 14; }), ParticleTypes.FLAME))
                .addTextureM(EveryCompat.res("block/torch/spruce_torch"), EveryCompat.res("block/ch/spruce_torch_m"))
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .noItem()
                .build();

        this.addEntry(wallTorch);

        torch = SimpleEntrySet.builder(WoodType.class, "torch",
                        () -> getModBlock("spruce_torch"), () -> WoodTypeRegistry.getValue(new ResourceLocation("spruce")),
                        w -> new TorchBlock(Utils.copyPropertySafe(w.planks).noCollission().instabreak()
                                .lightLevel((arg) -> { return 14; }), ParticleTypes.FLAME))
                .addTextureM(EveryCompat.res("block/torch/spruce_torch"), EveryCompat.res("block/ch/spruce_torch_m"))
                .addCustomItem((w, b, p) -> new StandingAndWallBlockItem(b, wallTorch.blocks.get(w), p))
                .addTag(BlockTags.WALL_POST_OVERRIDE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("torch"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("torch"), Registry.ITEM_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();

        this.addEntry(torch);
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

    @Override
    public void addDynamicServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicServerResources(handler, manager);
        getEntries().forEach(e -> {
//            String itemName = Utils.getID(e).getPath();
//            SimpleTagBuilder planksTag = SimpleTagBuilder.of(modRes(basketWoven.getBaseBlock().toString()));
//            SimpleTagBuilder planksTag2 = SimpleTagBuilder.of(modRes(itemId + "_planks"));

            handler.dynamicPack.addJson(torch.getBaseBlock().getLootTable(),
                    LootTables.serialize(new LootTable.Builder().build()),
                    ResType.LOOT_TABLES);
            handler.dynamicPack.addJson(wallTorch.getBaseBlock().getLootTable(),
                    LootTables.serialize(new LootTable.Builder().build()),
                    ResType.LOOT_TABLES);
//            handler.dynamicPack.addTag(planksTag, Registry.BLOCK_REGISTRY);
//            handler.dynamicPack.addTag(planksTag, Registry.ITEM_REGISTRY);
        });
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
