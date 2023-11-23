package net.mehvahdjukaar.every_compat.modules.chipped;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.SimpleTagBuilder;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynamicDataPack;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.block.*;

import java.util.function.Supplier;

// TODO: Fix recipes & tags
// `chipped:oak_planks` tags should be changed to `modid:modded_planks`
// Create a recipe using the new tag for `chipped:recipes/carpenters_table`
// Currently all variants are crafted with Oak Planks due to them using the `chipped:oak_planks` tag
// The same should be done for door & trapdoor variants
// Fix doors dropping two items
// Mcmeta files are not copied from the base block

public class ChippedModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> barrel;
    public final SimpleEntrySet<WoodType, Block> crate;
    public final SimpleEntrySet<WoodType, Block> reinforcedCrate;
    public final SimpleEntrySet<WoodType, Block> basketWovenPlanks;
    public final SimpleEntrySet<WoodType, Block> boxedPlanks;
    public final SimpleEntrySet<WoodType, Block> brickBondPlanks;
    public final SimpleEntrySet<WoodType, Block> brickyPlanks;
    public final SimpleEntrySet<WoodType, Block> corneredPlanks;
    public final SimpleEntrySet<WoodType, Block> cratedPlanks;
    public final SimpleEntrySet<WoodType, Block> crossLacedPlanks;
    public final SimpleEntrySet<WoodType, Block> crossedPlanks;
    public final SimpleEntrySet<WoodType, Block> detailedPlanks;
    public final SimpleEntrySet<WoodType, Block> diagonalPlanks;
    public final SimpleEntrySet<WoodType, Block> diamondPlanks;
    public final SimpleEntrySet<WoodType, Block> doubleHerringbonePlanks;
    public final SimpleEntrySet<WoodType, Block> enclosedPlanks;
    public final SimpleEntrySet<WoodType, Block> finePlanks;
    public final SimpleEntrySet<WoodType, Block> fineVerticalPlanks;
    public final SimpleEntrySet<WoodType, Block> framedPlanks;
    public final SimpleEntrySet<WoodType, Block> herringbonePlanks;
    public final SimpleEntrySet<WoodType, Block> hewnPlanks;
    public final SimpleEntrySet<WoodType, Block> lacedPlanks;
    public final SimpleEntrySet<WoodType, Block> mosaicPlanks;
    public final SimpleEntrySet<WoodType, Block> nailedPlanks;
    public final SimpleEntrySet<WoodType, Block> naturalPlanks;
    public final SimpleEntrySet<WoodType, Block> panelPlanks;
    public final SimpleEntrySet<WoodType, Block> peggedPlanks;
    public final SimpleEntrySet<WoodType, Block> polishedPlanks;
    public final SimpleEntrySet<WoodType, Block> shavingsPlanks;
    public final SimpleEntrySet<WoodType, Block> railedPlanks;
    public final SimpleEntrySet<WoodType, Block> shiftedPlanks;
    public final SimpleEntrySet<WoodType, Block> slantedPlanks;
    public final SimpleEntrySet<WoodType, Block> smoothPlanks;
    public final SimpleEntrySet<WoodType, Block> stackedPlanks;
    public final SimpleEntrySet<WoodType, Block> thinPlanks;
    public final SimpleEntrySet<WoodType, Block> tiledPlanks;
    public final SimpleEntrySet<WoodType, Block> versaillesPlanks;
    public final SimpleEntrySet<WoodType, Block> verticalPlanks;
    public final SimpleEntrySet<WoodType, Block> verticallyRailedPlanks;
    public final SimpleEntrySet<WoodType, Block> whirlwindPlanks;
    public final SimpleEntrySet<WoodType, Block> wickeredPlanks;
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
    public final SimpleEntrySet<WoodType, Block> airyTrapdoor;
    public final SimpleEntrySet<WoodType, Block> barredTrapdoor;
    public final SimpleEntrySet<WoodType, Block> checkeredTrapdoor;
    public final SimpleEntrySet<WoodType, Block> classicTrapdoor;
    public final SimpleEntrySet<WoodType, Block> classicWindowedTrapdoor;
    public final SimpleEntrySet<WoodType, Block> cobwebTrapdoor;
    public final SimpleEntrySet<WoodType, Block> distortedTrapdoor;
    public final SimpleEntrySet<WoodType, Block> fancyTrapdoor;
    public final SimpleEntrySet<WoodType, Block> goldenBarredTrapdoor;
    public final SimpleEntrySet<WoodType, Block> heavyTrapdoor;
    public final SimpleEntrySet<WoodType, Block> ironBarredTrapdoor;
    public final SimpleEntrySet<WoodType, Block> leafyTrapdoor;
    public final SimpleEntrySet<WoodType, Block> meshedTrapdoor;
    public final SimpleEntrySet<WoodType, Block> overgrownTrapdoor;
    public final SimpleEntrySet<WoodType, Block> pointlessTrapdoor;
    public final SimpleEntrySet<WoodType, Block> slottedTrapdoor;
    public final SimpleEntrySet<WoodType, Block> solidTrapdoor;
    public final SimpleEntrySet<WoodType, Block> suspiciousTrapdoor;
    public final SimpleEntrySet<WoodType, Block> twistedTrapdoor;
    public final SimpleEntrySet<WoodType, Block> vinedTrapdoor;
    public final SimpleEntrySet<WoodType, Block> wartedTrapdoor;
    public final SimpleEntrySet<WoodType, Block> windowedTrapdoor;
    public final SimpleEntrySet<WoodType, Block> wovenTrapdoor;
    public final SimpleEntrySet<WoodType, Block> torch;
    public final SimpleEntrySet<WoodType, Block> wallTorch;
    public final SimpleEntrySet<WoodType, Block> circleGlass;
    public final SimpleEntrySet<WoodType, Block> barredGlass;
    public final SimpleEntrySet<WoodType, Block> borderedGlass;
    public final SimpleEntrySet<WoodType, Block> diamondBorderedGlass;
    public final SimpleEntrySet<WoodType, Block> horizontalLinedGlass;
    public final SimpleEntrySet<WoodType, Block> largeDiamondGlass;
    public final SimpleEntrySet<WoodType, Block> lineBarredGlass;
    public final SimpleEntrySet<WoodType, Block> ornateBarredGlass;
    public final SimpleEntrySet<WoodType, Block> snowflakeGlass;
    public final SimpleEntrySet<WoodType, Block> squareGlass;
    public final SimpleEntrySet<WoodType, Block> wovenGlass;
    public final SimpleEntrySet<WoodType, Block> circleGlassPane;
    public final SimpleEntrySet<WoodType, Block> barredGlassPane;
    public final SimpleEntrySet<WoodType, Block> borderedGlassPane;
    public final SimpleEntrySet<WoodType, Block> diamondBorderedGlassPane;
    public final SimpleEntrySet<WoodType, Block> horizontalLinedGlassPane;
    public final SimpleEntrySet<WoodType, Block> largeDiamondGlassPane;
    public final SimpleEntrySet<WoodType, Block> lineBarredGlassPane;
    public final SimpleEntrySet<WoodType, Block> ornateBarredGlassPane;
    public final SimpleEntrySet<WoodType, Block> snowflakeGlassPane;
    public final SimpleEntrySet<WoodType, Block> squareGlassPane;
    public final SimpleEntrySet<WoodType, Block> wovenGlassPane;

    public ChippedModule(String modId) {
        super(modId, "ch");
        Supplier<CreativeModeTab> tab = () -> getModTab("main");

        mosaicPlanks = SimpleEntrySet.builder(WoodType.class, "planks_mosaic",
                        () -> getModBlock("oak_planks_mosaic"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/oak_planks_mosaic"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .createPaletteFromOak(this::dullPalette)
                .setTab(tab)
                .build();

        this.addEntry(mosaicPlanks);

        panelPlanks = SimpleEntrySet.builder(WoodType.class, "planks_panel",
                        () -> getModBlock("oak_planks_panel"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/ctm/oak_planks_panel_ctm/0"))
                .addTexture(modRes("block/oak_planks/ctm/oak_planks_panel_ctm/1"))
                .addTexture(modRes("block/oak_planks/ctm/oak_planks_panel_ctm/2"))
                .addTexture(modRes("block/oak_planks/ctm/oak_planks_panel_ctm/3"))
                .addTexture(modRes("block/oak_planks/oak_planks_panel"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .createPaletteFromOak(this::panelPalette)
                .setTab(tab)
                .build();

        this.addEntry(panelPlanks);

        shavingsPlanks = SimpleEntrySet.builder(WoodType.class, "planks_shavings",
                        () -> getModBlock("oak_planks_shavings"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/oak_planks_shavings"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .setTab(tab)
                .build();

        this.addEntry(shavingsPlanks);

        basketWovenPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "basket_woven",
                        () -> getModBlock("basket_woven_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/basket_woven_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .createPaletteFromOak(this::darkerPalette)
                .setTab(tab)
                .build();

        this.addEntry(basketWovenPlanks);

        boxedPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "boxed",
                        () -> getModBlock("boxed_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/boxed_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .createPaletteFromOak(this::darkerPalette)
                .setTab(tab)
                .build();

        this.addEntry(boxedPlanks);

        brickBondPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "brick_bond",
                        () -> getModBlock("brick_bond_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/brick_bond_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .createPaletteFromOak(this::dullerPalette)
                .setTab(tab)
                .build();

        this.addEntry(brickBondPlanks);

        brickyPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "bricky",
                        () -> getModBlock("bricky_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/bricky_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .createPaletteFromOak(this::darkerPalette)
                .setTab(tab)
                .build();

        this.addEntry(brickyPlanks);

        corneredPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "cornered",
                        () -> getModBlock("cornered_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/ctm/cornered_oak_planks_ctm/0"))
                .addTexture(modRes("block/oak_planks/ctm/cornered_oak_planks_ctm/1"))
                .addTexture(modRes("block/oak_planks/ctm/cornered_oak_planks_ctm/2"))
                .addTexture(modRes("block/oak_planks/ctm/cornered_oak_planks_ctm/3"))
                .addTexture(modRes("block/oak_planks/cornered_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .createPaletteFromOak(this::darkerPalette)
                .setTab(tab)
                .build();

        this.addEntry(corneredPlanks);

        cratedPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "crated",
                        () -> getModBlock("crated_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/ctm/common_textures/0"))
                .addTexture(modRes("block/oak_planks/ctm/crated_oak_planks_ctm/1"))
                .addTexture(modRes("block/oak_planks/ctm/crated_oak_planks_ctm/2"))
                .addTexture(modRes("block/oak_planks/ctm/crated_oak_planks_ctm/3"))
                .addTexture(modRes("block/oak_planks/crated_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .createPaletteFromOak(this::dullerPalette)
                .setTab(tab)
                .build();

        this.addEntry(cratedPlanks);

        crossLacedPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "cross_laced",
                        () -> getModBlock("cross_laced_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/cross_laced_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .setTab(tab)
                .build();

        this.addEntry(crossLacedPlanks);

        crossedPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "crossed",
                        () -> getModBlock("crossed_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/crossed_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .setTab(tab)
                .build();

        this.addEntry(crossedPlanks);

        detailedPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "detailed",
                        () -> getModBlock("detailed_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/detailed_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .createPaletteFromOak(this::darkerPalette)
                .setTab(tab)
                .build();

        this.addEntry(detailedPlanks);

        diagonalPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "diagonal",
                        () -> getModBlock("diagonal_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/diagonal_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .createPaletteFromOak(this::darkerPalette)
                .setTab(tab)
                .build();

        this.addEntry(diagonalPlanks);

        diamondPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "diamond",
                        () -> getModBlock("diamond_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/diamond_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .setTab(tab)
                .build();

        this.addEntry(diamondPlanks);

        doubleHerringbonePlanks = SimpleEntrySet.builder(WoodType.class, "planks", "double_herringbone",
                        () -> getModBlock("double_herringbone_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/double_herringbone_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .createPaletteFromOak(this::darkestPalette)
                .setTab(tab)
                .build();

        this.addEntry(doubleHerringbonePlanks);

        enclosedPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "enclosed",
                        () -> getModBlock("enclosed_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/ctm/common_textures/0"))
                .addTexture(modRes("block/oak_planks/ctm/enclosed_oak_planks_ctm/1"))
                .addTexture(modRes("block/oak_planks/ctm/enclosed_oak_planks_ctm/2"))
                .addTexture(modRes("block/oak_planks/ctm/enclosed_oak_planks_ctm/3"))
                .addTexture(modRes("block/oak_planks/enclosed_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .createPaletteFromOak(this::dullerPalette)
                .setTab(tab)
                .build();

        this.addEntry(enclosedPlanks);

        finePlanks = SimpleEntrySet.builder(WoodType.class, "planks", "fine",
                        () -> getModBlock("fine_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/fine_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .createPaletteFromOak(this::dullPalette)
                .setTab(tab)
                .build();

        this.addEntry(finePlanks);

        fineVerticalPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "fine_vertical",
                        () -> getModBlock("fine_vertical_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/fine_vertical_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .createPaletteFromOak(this::dullPalette)
                .setTab(tab)
                .build();

        this.addEntry(fineVerticalPlanks);

        framedPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "framed",
                        () -> getModBlock("framed_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/ctm/framed_oak_planks_ctm/0"))
                .addTexture(modRes("block/oak_planks/ctm/framed_oak_planks_ctm/1"))
                .addTexture(modRes("block/oak_planks/ctm/framed_oak_planks_ctm/2"))
                .addTexture(modRes("block/oak_planks/ctm/framed_oak_planks_ctm/3"))
                .addTexture(modRes("block/oak_planks/framed_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .createPaletteFromOak(this::darkerPalette)
                .setTab(tab)
                .build();

        this.addEntry(framedPlanks);

        herringbonePlanks = SimpleEntrySet.builder(WoodType.class, "planks", "herringbone",
                        () -> getModBlock("herringbone_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/herringbone_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .createPaletteFromOak(this::darkestPalette)
                .setTab(tab)
                .build();

        this.addEntry(herringbonePlanks);

        hewnPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "hewn",
                        () -> getModBlock("hewn_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/hewn_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .createPaletteFromOak(this::darkerPalette)
                .setTab(tab)
                .build();

        this.addEntry(hewnPlanks);

        lacedPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "laced",
                        () -> getModBlock("laced_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/laced_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .createPaletteFromOak(this::darkerPalette)
                .setTab(tab)
                .build();

        this.addEntry(lacedPlanks);

        nailedPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "nailed",
                        () -> getModBlock("nailed_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/nailed_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .createPaletteFromOak(this::darkerPalette)
                .setTab(tab)
                .build();

        this.addEntry(nailedPlanks);

        naturalPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "natural",
                        () -> getModBlock("natural_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/ctm/natural_oak_planks_ctm/0"))
                .addTexture(modRes("block/oak_planks/ctm/natural_oak_planks_ctm/1"))
                .addTexture(modRes("block/oak_planks/ctm/natural_oak_planks_ctm/2"))
                .addTexture(modRes("block/oak_planks/ctm/natural_oak_planks_ctm/3"))
                .addTexture(modRes("block/oak_planks/natural_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .createPaletteFromOak(this::darkPalette)
                .setTab(tab)
                .build();

        this.addEntry(naturalPlanks);

        peggedPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "pegged",
                        () -> getModBlock("pegged_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/ctm/pegged_oak_planks_ctm/0"))
                .addTexture(modRes("block/oak_planks/ctm/pegged_oak_planks_ctm/1"))
                .addTexture(modRes("block/oak_planks/ctm/pegged_oak_planks_ctm/2"))
                .addTexture(modRes("block/oak_planks/ctm/pegged_oak_planks_ctm/3"))
                .addTexture(modRes("block/oak_planks/pegged_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .createPaletteFromOak(this::dullPalette)
                .setTab(tab)
                .build();

        this.addEntry(peggedPlanks);

        polishedPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "polished",
                        () -> getModBlock("polished_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/polished_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .createPaletteFromOak(this::dullerPalette)
                .setTab(tab)
                .build();

        this.addEntry(polishedPlanks);

        railedPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "railed",
                        () -> getModBlock("railed_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/railed_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .setTab(tab)
                .build();

        this.addEntry(railedPlanks);

        shiftedPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "shifted",
                        () -> getModBlock("shifted_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/shifted_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .createPaletteFromOak(this::darkerPalette)
                .setTab(tab)
                .build();

        this.addEntry(shiftedPlanks);

        slantedPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "slanted",
                        () -> getModBlock("slanted_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/slanted_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .createPaletteFromOak(this::darkestPalette)
                .setTab(tab)
                .build();

        this.addEntry(slantedPlanks);

        smoothPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "smooth",
                        () -> getModBlock("smooth_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/smooth_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .createPaletteFromOak(this::darkerPalette)
                .setTab(tab)
                .build();

        this.addEntry(smoothPlanks);

        stackedPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "stacked",
                        () -> getModBlock("stacked_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/stacked_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .createPaletteFromOak(this::darkerPalette)
                .setTab(tab)
                .build();

        this.addEntry(stackedPlanks);

        thinPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "thin",
                        () -> getModBlock("thin_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/thin_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .createPaletteFromOak(this::darkerPalette)
                .setTab(tab)
                .build();

        this.addEntry(thinPlanks);

        tiledPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "tiled",
                        () -> getModBlock("tiled_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/tiled_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .createPaletteFromOak(this::darkerPalette)
                .setTab(tab)
                .build();

        this.addEntry(tiledPlanks);

        versaillesPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "versailles",
                        () -> getModBlock("versailles_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/versailles_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .createPaletteFromOak(this::darkerPalette)
                .setTab(tab)
                .build();

        this.addEntry(versaillesPlanks);

        verticalPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "vertical",
                        () -> getModBlock("vertical_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/vertical_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .setTab(tab)
                .build();

        this.addEntry(verticalPlanks);

        verticallyRailedPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "vertically_railed",
                        () -> getModBlock("vertically_railed_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/vertically_railed_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .setTab(tab)
                .build();

        this.addEntry(verticallyRailedPlanks);

        whirlwindPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "whirlwind",
                        () -> getModBlock("whirlwind_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/ctm/whirlwind_oak_planks_ctm/0"))
                .addTexture(modRes("block/oak_planks/ctm/whirlwind_oak_planks_ctm/1"))
                .addTexture(modRes("block/oak_planks/ctm/whirlwind_oak_planks_ctm/2"))
                .addTexture(modRes("block/oak_planks/ctm/whirlwind_oak_planks_ctm/3"))
                .addTexture(modRes("block/oak_planks/whirlwind_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .createPaletteFromOak(this::darkPalette)
                .setTab(tab)
                .build();

        this.addEntry(whirlwindPlanks);

        wickeredPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "wickered",
                        () -> getModBlock("wickered_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/wickered_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .createPaletteFromOak(this::darkerPalette)
                .setTab(tab)
                .build();

        this.addEntry(wickeredPlanks);

        barrel = SimpleEntrySet.builder(WoodType.class, "barrel",
                        () -> getModBlock("oak_barrel"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BarrelBlock(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/barrel/oak_barrel_bottom"))
                .addTexture(modRes("block/barrel/oak_barrel_top_open"))
                .addTextureM(modRes("block/barrel/oak_barrel_side"), EveryCompat.res("block/ch/oak_barrel_side_m"))
                .addTextureM(modRes("block/barrel/oak_barrel_top"), EveryCompat.res("block/ch/oak_barrel_top_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("barrel"), Registries.BLOCK)
                .addTag(modRes("barrel"), Registries.ITEM)
                .createPaletteFromOak(this::darkerPalette)
                .setTab(tab)
                .build();

        this.addEntry(barrel);

        crate = SimpleEntrySet.builder(WoodType.class, "crate",
                        () -> getModBlock("oak_crate"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BarrelBlock(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/barrel/oak_crate_side"))
                .addTexture(modRes("block/barrel/oak_crate_top"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("barrel"), Registries.BLOCK)
                .addTag(modRes("barrel"), Registries.ITEM)
                .createPaletteFromOak(this::darkPalette)
                .setTab(tab)
                .build();

        this.addEntry(crate);

        reinforcedCrate = SimpleEntrySet.builder(WoodType.class, "crate", "reinforced",
                        () -> getModBlock("reinforced_oak_crate"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BarrelBlock(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/barrel/reinforced_oak_crate_side"))
                .addTexture(modRes("block/barrel/reinforced_oak_crate_top"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("barrel"), Registries.BLOCK)
                .addTag(modRes("barrel"), Registries.ITEM)
                .createPaletteFromOak(this::darkPalette)
                .setTab(tab)
                .build();

        this.addEntry(reinforcedCrate);

        barredDoor = SimpleEntrySet.builder(WoodType.class, "door", "barred",
                        () -> getModBlock("barred_oak_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()))
                .addTextureM(modRes("block/oak_door/barred_oak_door_bottom"), EveryCompat.res("block/ch/doors/barred_oak_door_bottom_m"))
                .addTextureM(modRes("block/oak_door/barred_oak_door_top"), EveryCompat.res("block/ch/doors/barred_oak_door_top_m"))
                .addTextureM(EveryCompat.res("item/ch/doors/barred_oak_door"), EveryCompat.res("item/ch/doors/barred_oak_door_m"))
                .addModelTransform(m -> m.replaceString("chipped:item/oak_door", "chipped:item/ch/doors")
                        .replaceGenericType("oak", "item/ch/doors"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .setRenderType(() -> RenderType::cutout)
                .setTab(tab)
                .noDrops()
                .build();

        this.addEntry(barredDoor);

        beachDoor = SimpleEntrySet.builder(WoodType.class, "door", "beach",
                        () -> getModBlock("beach_oak_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()))
                .addTextureM(modRes("block/oak_door/beach_oak_door_bottom"), EveryCompat.res("block/ch/doors/beach_oak_door_bottom_m"))
                .addTextureM(modRes("block/oak_door/beach_oak_door_top"), EveryCompat.res("block/ch/doors/beach_oak_door_top_m"))
                .addTextureM(EveryCompat.res("item/ch/doors/beach_oak_door"), EveryCompat.res("item/ch/doors/beach_oak_door_m"))
                .addModelTransform(m -> m.replaceString("chipped:item/oak_door", "chipped:item/ch/doors")
                        .replaceGenericType("oak", "item/ch/doors"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .setRenderType(() -> RenderType::cutout)
                .setTab(tab)
                .noDrops()
                .build();

        this.addEntry(beachDoor);

        boardedDoor = SimpleEntrySet.builder(WoodType.class, "door", "boarded",
                        () -> getModBlock("boarded_oak_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()))
                .addTextureM(modRes("block/oak_door/boarded_oak_door_bottom"), EveryCompat.res("block/ch/doors/boarded_oak_door_bottom_m"))
                .addTextureM(modRes("block/oak_door/boarded_oak_door_top"), EveryCompat.res("block/ch/doors/boarded_oak_door_top_m"))
                .addTextureM(EveryCompat.res("item/ch/doors/boarded_oak_door"), EveryCompat.res("item/ch/doors/boarded_oak_door_m"))
                .addModelTransform(m -> m.replaceString("chipped:item/oak_door", "chipped:item/ch/doors")
                        .replaceGenericType("oak", "item/ch/doors"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .createPaletteFromOak(this::darkPalette)
                .setRenderType(() -> RenderType::cutout)
                .setTab(tab)
                .noDrops()
                .build();

        this.addEntry(boardedDoor);

        dualPaneledDoor = SimpleEntrySet.builder(WoodType.class, "door", "dual_paneled",
                        () -> getModBlock("dual_paneled_oak_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()))
                .addTextureM(modRes("block/oak_door/dual_paneled_oak_door_bottom"), EveryCompat.res("block/ch/doors/dual_paneled_oak_door_bottom_m"))
                .addTextureM(modRes("block/oak_door/dual_paneled_oak_door_top"), EveryCompat.res("block/ch/doors/dual_paneled_oak_door_top_m"))
                .addTextureM(EveryCompat.res("item/ch/doors/dual_paneled_oak_door"), EveryCompat.res("item/ch/doors/dual_paneled_oak_door_m"))
                .addModelTransform(m -> m.replaceString("chipped:item/oak_door", "chipped:item/ch/doors")
                        .replaceGenericType("oak", "item/ch/doors"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .createPaletteFromOak(this::darkPalette)
                .setRenderType(() -> RenderType::cutout)
                .setTab(tab)
                .noDrops()
                .build();

        this.addEntry(dualPaneledDoor);

        fortifiedDoor = SimpleEntrySet.builder(WoodType.class, "door", "fortified",
                        () -> getModBlock("fortified_oak_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()))
                .addTextureM(modRes("block/oak_door/fortified_oak_door_bottom"), EveryCompat.res("block/ch/doors/fortified_oak_door_bottom_m"))
                .addTextureM(modRes("block/oak_door/fortified_oak_door_top"), EveryCompat.res("block/ch/doors/fortified_oak_door_top_m"))
                .addTextureM(EveryCompat.res("item/ch/doors/fortified_oak_door"), EveryCompat.res("item/ch/doors/fortified_oak_door_m"))
                .addModelTransform(m -> m.replaceString("chipped:item/oak_door", "chipped:item/ch/doors")
                        .replaceGenericType("oak", "item/ch/doors"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .createPaletteFromOak(this::darkPalette)
                .setRenderType(() -> RenderType::cutout)
                .setTab(tab)
                .noDrops()
                .build();

        this.addEntry(fortifiedDoor);

        gatedDoor = SimpleEntrySet.builder(WoodType.class, "door", "gated",
                        () -> getModBlock("gated_oak_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()))
                .addTextureM(modRes("block/oak_door/gated_oak_door_bottom"), EveryCompat.res("block/ch/doors/gated_oak_door_bottom_m"))
                .addTextureM(modRes("block/oak_door/gated_oak_door_top"), EveryCompat.res("block/ch/doors/gated_oak_door_top_m"))
                .addTextureM(EveryCompat.res("item/ch/doors/gated_oak_door"), EveryCompat.res("item/ch/doors/gated_oak_door_m"))
                .addModelTransform(m -> m.replaceString("chipped:item/oak_door", "chipped:item/ch/doors")
                        .replaceGenericType("oak", "item/ch/doors"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .createPaletteFromOak(this::darkPalette)
                .setRenderType(() -> RenderType::cutout)
                .setTab(tab)
                .noDrops()
                .build();

        this.addEntry(gatedDoor);

        glassDoor = SimpleEntrySet.builder(WoodType.class, "door", "glass",
                        () -> getModBlock("glass_oak_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()))
                .addTextureM(modRes("block/oak_door/glass_oak_door_bottom"), EveryCompat.res("block/ch/doors/glass_oak_door_bottom_m"))
                .addTextureM(modRes("block/oak_door/glass_oak_door_top"), EveryCompat.res("block/ch/doors/glass_oak_door_top_m"))
                .addTextureM(EveryCompat.res("item/ch/doors/glass_oak_door"), EveryCompat.res("item/ch/doors/glass_oak_door_m"))
                .addModelTransform(m -> m.replaceString("chipped:item/oak_door", "chipped:item/ch/doors")
                        .replaceGenericType("oak", "item/ch/doors"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .setRenderType(() -> RenderType::cutout)
                .setTab(tab)
                .noDrops()
                .build();

        this.addEntry(glassDoor);

        heavyDoor = SimpleEntrySet.builder(WoodType.class, "door", "heavy",
                        () -> getModBlock("heavy_oak_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()))
                .addTextureM(modRes("block/oak_door/heavy_oak_door_bottom"), EveryCompat.res("block/ch/doors/heavy_oak_door_bottom_m"))
                .addTextureM(modRes("block/oak_door/heavy_oak_door_top"), EveryCompat.res("block/ch/doors/heavy_oak_door_top_m"))
                .addTextureM(EveryCompat.res("item/ch/doors/heavy_oak_door"), EveryCompat.res("item/ch/doors/heavy_oak_door_m"))
                .addModelTransform(m -> m.replaceString("chipped:item/oak_door", "chipped:item/ch/doors")
                        .replaceGenericType("oak", "item/ch/doors"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .createPaletteFromOak(this::darkPalette)
                .setRenderType(() -> RenderType::cutout)
                .setTab(tab)
                .noDrops()
                .build();

        this.addEntry(heavyDoor);

        overgrownDoor = SimpleEntrySet.builder(WoodType.class, "door", "overgrown",
                        () -> getModBlock("overgrown_oak_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()))
                .addTextureM(modRes("block/oak_door/overgrown_oak_door_bottom"), EveryCompat.res("block/ch/doors/overgrown_oak_door_bottom_m"))
                .addTextureM(modRes("block/oak_door/overgrown_oak_door_top"), EveryCompat.res("block/ch/doors/overgrown_oak_door_top_m"))
                .addTextureM(EveryCompat.res("item/ch/doors/overgrown_oak_door"), EveryCompat.res("item/ch/doors/overgrown_oak_door_m"))
                .addModelTransform(m -> m.replaceString("chipped:item/oak_door", "chipped:item/ch/doors")
                        .replaceGenericType("oak", "item/ch/doors"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .setRenderType(() -> RenderType::cutout)
                .setTab(tab)
                .noDrops()
                .build();

        this.addEntry(overgrownDoor);

        paneledDoor = SimpleEntrySet.builder(WoodType.class, "door", "paneled",
                        () -> getModBlock("paneled_oak_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()))
                .addTextureM(modRes("block/oak_door/paneled_oak_door_bottom"), EveryCompat.res("block/ch/doors/paneled_oak_door_bottom_m"))
                .addTextureM(modRes("block/oak_door/paneled_oak_door_top"), EveryCompat.res("block/ch/doors/paneled_oak_door_top_m"))
                .addTextureM(EveryCompat.res("item/ch/doors/paneled_oak_door"), EveryCompat.res("item/ch/doors/paneled_oak_door_m"))
                .addModelTransform(m -> m.replaceString("chipped:item/oak_door", "chipped:item/ch/doors")
                        .replaceGenericType("oak", "item/ch/doors"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .createPaletteFromOak(this::darkPalette)
                .setRenderType(() -> RenderType::cutout)
                .setTab(tab)
                .noDrops()
                .build();

        this.addEntry(paneledDoor);

        paperDoor = SimpleEntrySet.builder(WoodType.class, "door", "paper",
                        () -> getModBlock("paper_oak_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()))
                .addTextureM(modRes("block/oak_door/paper_oak_door_bottom"), EveryCompat.res("block/ch/doors/paper_oak_door_bottom_m"))
                .addTextureM(modRes("block/oak_door/paper_oak_door_top"), EveryCompat.res("block/ch/doors/paper_oak_door_top_m"))
                .addTextureM(EveryCompat.res("item/ch/doors/paper_oak_door"), EveryCompat.res("item/ch/doors/paper_oak_door_m"))
                .addModelTransform(m -> m.replaceString("chipped:item/oak_door", "chipped:item/ch/doors")
                        .replaceGenericType("oak", "item/ch/doors"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .createPaletteFromOak(this::darkPalette)
                .setRenderType(() -> RenderType::cutout)
                .setTab(tab)
                .noDrops()
                .build();

        this.addEntry(paperDoor);

        pressedDoor = SimpleEntrySet.builder(WoodType.class, "door", "pressed",
                        () -> getModBlock("pressed_oak_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()))
                .addTextureM(modRes("block/oak_door/pressed_oak_door_bottom"), EveryCompat.res("block/ch/doors/pressed_oak_door_bottom_m"))
                .addTextureM(modRes("block/oak_door/pressed_oak_door_top"), EveryCompat.res("block/ch/doors/pressed_oak_door_top_m"))
                .addTextureM(EveryCompat.res("item/ch/doors/pressed_oak_door"), EveryCompat.res("item/ch/doors/pressed_oak_door_m"))
                .addModelTransform(m -> m.replaceString("chipped:item/oak_door", "chipped:item/ch/doors")
                        .replaceGenericType("oak", "item/ch/doors"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .createPaletteFromOak(this::darkPalette)
                .setRenderType(() -> RenderType::cutout)
                .setTab(tab)
                .noDrops()
                .build();

        this.addEntry(pressedDoor);

        screenDoor = SimpleEntrySet.builder(WoodType.class, "door", "screen",
                        () -> getModBlock("screen_oak_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()))
                .addTextureM(modRes("block/oak_door/screen_oak_door_bottom"), EveryCompat.res("block/ch/doors/screen_oak_door_bottom_m"))
                .addTextureM(modRes("block/oak_door/screen_oak_door_top"), EveryCompat.res("block/ch/doors/screen_oak_door_top_m"))
                .addTextureM(EveryCompat.res("item/ch/doors/screen_oak_door"), EveryCompat.res("item/ch/doors/screen_oak_door_m"))
                .addModelTransform(m -> m.replaceString("chipped:item/oak_door", "chipped:item/ch/doors")
                        .replaceGenericType("oak", "item/ch/doors"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .createPaletteFromOak(this::darkPalette)
                .setRenderType(() -> RenderType::cutout)
                .setTab(tab)
                .noDrops()
                .build();

        this.addEntry(screenDoor);

        secretDoor = SimpleEntrySet.builder(WoodType.class, "door", "secret",
                        () -> getModBlock("secret_oak_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()))
                .addTextureM(modRes("block/oak_door/secret_oak_door_bottom"), EveryCompat.res("block/ch/doors/secret_oak_door_bottom_m"))
                .addTextureM(modRes("block/oak_door/secret_oak_door_top"), EveryCompat.res("block/ch/doors/secret_oak_door_top_m"))
                .addTextureM(EveryCompat.res("item/ch/doors/secret_oak_door"), EveryCompat.res("item/ch/doors/secret_oak_door_m"))
                .addModelTransform(m -> m.replaceString("chipped:item/oak_door", "chipped:item/ch/doors")
                        .replaceGenericType("oak", "item/ch/doors"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .createPaletteFromOak(this::darkPalette)
                .setRenderType(() -> RenderType::cutout)
                .setTab(tab)
                .noDrops()
                .build();

        this.addEntry(secretDoor);

        shackDoor = SimpleEntrySet.builder(WoodType.class, "door", "shack",
                        () -> getModBlock("shack_oak_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()))
                .addTexture(modRes("block/oak_door/shack_oak_door_bottom"))
                .addTextureM(modRes("block/oak_door/shack_oak_door_top"), EveryCompat.res("block/ch/doors/shack_oak_door_top_m"))
                .addTextureM(EveryCompat.res("item/ch/doors/shack_oak_door"), EveryCompat.res("item/ch/doors/shack_oak_door_m"))
                .addModelTransform(m -> m.replaceString("chipped:item/oak_door", "chipped:item/ch/doors")
                        .replaceGenericType("oak", "item/ch/doors"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .createPaletteFromOak(this::darkPalette)
                .setRenderType(() -> RenderType::cutout)
                .setTab(tab)
                .noDrops()
                .build();

        this.addEntry(shackDoor);

        slidingDoor = SimpleEntrySet.builder(WoodType.class, "door", "sliding",
                        () -> getModBlock("sliding_oak_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()))
                .addTextureM(modRes("block/oak_door/sliding_oak_door_bottom"), EveryCompat.res("block/ch/doors/sliding_oak_door_bottom_m"))
                .addTextureM(modRes("block/oak_door/sliding_oak_door_top"), EveryCompat.res("block/ch/doors/sliding_oak_door_top_m"))
                .addTextureM(EveryCompat.res("item/ch/doors/sliding_oak_door"), EveryCompat.res("item/ch/doors/sliding_oak_door_m"))
                .addModelTransform(m -> m.replaceString("chipped:item/oak_door", "chipped:item/ch/doors")
                        .replaceGenericType("oak", "item/ch/doors"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .createPaletteFromOak(this::darkPalette)
                .setRenderType(() -> RenderType::cutout)
                .setTab(tab)
                .noDrops()
                .build();

        this.addEntry(slidingDoor);

        supportedDoor = SimpleEntrySet.builder(WoodType.class, "door", "supported",
                        () -> getModBlock("supported_oak_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()))
                .addTextureM(modRes("block/oak_door/supported_oak_door_bottom"), EveryCompat.res("block/ch/doors/supported_oak_door_bottom_m"))
                .addTextureM(modRes("block/oak_door/supported_oak_door_top"), EveryCompat.res("block/ch/doors/supported_oak_door_top_m"))
                .addTextureM(EveryCompat.res("item/ch/doors/supported_oak_door"), EveryCompat.res("item/ch/doors/supported_oak_door_m"))
                .addModelTransform(m -> m.replaceString("chipped:item/oak_door", "chipped:item/ch/doors")
                        .replaceGenericType("oak", "item/ch/doors"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .createPaletteFromOak(this::darkerPalette)
                .setRenderType(() -> RenderType::cutout)
                .setTab(tab)
                .noDrops()
                .build();

        this.addEntry(supportedDoor);

        tileWindowedDoor = SimpleEntrySet.builder(WoodType.class, "door", "tile_windowed",
                        () -> getModBlock("tile_windowed_oak_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()))
                .addTextureM(EveryCompat.res("block/oak_door/tile_windowed_oak_door_bottom"), EveryCompat.res("block/ch/doors/tile_windowed_oak_door_bottom_m"))
                .addTextureM(EveryCompat.res("block/oak_door/tile_windowed_oak_door_top"), EveryCompat.res("block/ch/doors/tile_windowed_oak_door_top_m"))
                .addTextureM(EveryCompat.res("item/ch/doors/tile_windowed_oak_door"), EveryCompat.res("item/ch/doors/tile_windowed_oak_door_m"))
                .addModelTransform(m -> m.replaceString("chipped:item/oak_door", "chipped:item/ch/doors")
                        .replaceGenericType("oak", "item/ch/doors"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .createPaletteFromOak(this::darkPalette)
                .setRenderType(() -> RenderType::cutout)
                .setTab(tab)
                .noDrops()
                .build();

        this.addEntry(tileWindowedDoor);

        tiledDoor = SimpleEntrySet.builder(WoodType.class, "door", "tiled",
                        () -> getModBlock("tiled_oak_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()))
                .addTextureM(modRes("block/oak_door/tiled_oak_door_bottom"), EveryCompat.res("block/ch/doors/tiled_oak_door_bottom_m"))
                .addTextureM(modRes("block/oak_door/tiled_oak_door_top"), EveryCompat.res("block/ch/doors/tiled_oak_door_top_m"))
                .addTextureM(EveryCompat.res("item/ch/doors/tiled_oak_door"), EveryCompat.res("item/ch/doors/tiled_oak_door_m"))
                .addModelTransform(m -> m.replaceString("chipped:item/oak_door", "chipped:item/ch/doors")
                        .replaceGenericType("oak", "item/ch/doors"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .createPaletteFromOak(this::darkPalette)
                .setRenderType(() -> RenderType::cutout)
                .setTab(tab)
                .noDrops()
                .build();

        this.addEntry(tiledDoor);

        windowedDoor = SimpleEntrySet.builder(WoodType.class, "door", "windowed",
                        () -> getModBlock("windowed_oak_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()))
                .addTextureM(modRes("block/oak_door/windowed_oak_door_bottom"), EveryCompat.res("block/ch/doors/windowed_oak_door_bottom_m"))
                .addTextureM(modRes("block/oak_door/windowed_oak_door_top"), EveryCompat.res("block/ch/doors/windowed_oak_door_top_m"))
                .addTextureM(EveryCompat.res("item/ch/doors/windowed_oak_door"), EveryCompat.res("item/ch/doors/windowed_oak_door_m"))
                .addModelTransform(m -> m.replaceString("chipped:item/oak_door", "chipped:item/ch/doors")
                        .replaceGenericType("oak", "item/ch/doors"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .createPaletteFromOak(this::darkPalette)
                .setRenderType(() -> RenderType::cutout)
                .setTab(tab)
                .noDrops()
                .build();

        this.addEntry(windowedDoor);

        airyTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "airy",
                        () -> getModBlock("airy_oak_trapdoor"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrapDoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()))
                .addTextureM(modRes("block/oak_trapdoor/airy_oak_trapdoor"), EveryCompat.res("block/ch/trapdoors/airy_oak_trapdoor_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(() -> RenderType::cutout)
                .setTab(tab)
                .build();

        this.addEntry(airyTrapdoor);

        barredTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "barred",
                        () -> getModBlock("barred_oak_trapdoor"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrapDoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()))
                .addTexture(modRes("block/oak_trapdoor/barred_oak_trapdoor"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(() -> RenderType::cutout)
                .setTab(tab)
                .build();

        this.addEntry(barredTrapdoor);

        checkeredTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "checkered",
                        () -> getModBlock("checkered_oak_trapdoor"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrapDoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()))
                .addTexture(modRes("block/oak_trapdoor/checkered_oak_trapdoor"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(() -> RenderType::cutout)
                .setTab(tab)
                .build();

        this.addEntry(checkeredTrapdoor);

        classicTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "classic",
                        () -> getModBlock("classic_spruce_trapdoor"), () -> WoodTypeRegistry.getValue(new ResourceLocation("spruce")),
                        w -> new TrapDoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()))
                .addTexture(EveryCompat.res("block/spruce_trapdoor/classic_spruce_trapdoor"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(() -> RenderType::cutout)
                .setTab(tab)
                .build();

        this.addEntry(classicTrapdoor);

        classicWindowedTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "classic_windowed",
                        () -> getModBlock("classic_windowed_oak_trapdoor"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrapDoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()))
                .addTextureM(modRes("block/oak_trapdoor/classic_windowed_oak_trapdoor"), EveryCompat.res("block/ch/trapdoors/classic_windowed_oak_trapdoor_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(() -> RenderType::cutout)
                .setTab(tab)
                .build();

        this.addEntry(classicWindowedTrapdoor);

        cobwebTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "cobweb",
                        () -> getModBlock("cobweb_oak_trapdoor"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrapDoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()))
                .addTextureM(modRes("block/oak_trapdoor/cobweb_oak_trapdoor"), EveryCompat.res("block/ch/trapdoors/cobweb_oak_trapdoor_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(() -> RenderType::cutout)
                .setTab(tab)
                .build();

        this.addEntry(cobwebTrapdoor);

        distortedTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "distorted",
                        () -> getModBlock("distorted_oak_trapdoor"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrapDoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()))
                .addTexture(modRes("block/oak_trapdoor/distorted_oak_trapdoor"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(() -> RenderType::cutout)
                .setTab(tab)
                .build();

        this.addEntry(distortedTrapdoor);

        fancyTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "fancy",
                        () -> getModBlock("fancy_oak_trapdoor"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrapDoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()))
                .addTextureM(modRes("block/oak_trapdoor/fancy_oak_trapdoor"), EveryCompat.res("block/ch/trapdoors/fancy_oak_trapdoor_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(() -> RenderType::cutout)
                .setTab(tab)
                .build();

        this.addEntry(fancyTrapdoor);

        goldenBarredTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "golden_barred",
                        () -> getModBlock("golden_barred_oak_trapdoor"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrapDoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()))
                .addTextureM(modRes("block/oak_trapdoor/golden_barred_oak_trapdoor"), EveryCompat.res("block/ch/trapdoors/golden_barred_oak_trapdoor_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .createPaletteFromOak(this::darkPalette)
                .setRenderType(() -> RenderType::cutout)
                .setTab(tab)
                .build();

        this.addEntry(goldenBarredTrapdoor);

        heavyTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "heavy",
                        () -> getModBlock("heavy_oak_trapdoor"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrapDoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()))
                .addTextureM(modRes("block/oak_trapdoor/heavy_oak_trapdoor"), EveryCompat.res("block/ch/trapdoors/heavy_oak_trapdoor_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(() -> RenderType::cutout)
                .setTab(tab)
                .build();

        this.addEntry(heavyTrapdoor);

        ironBarredTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "iron_barred",
                        () -> getModBlock("iron_barred_oak_trapdoor"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrapDoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()))
                .addTextureM(modRes("block/oak_trapdoor/iron_barred_oak_trapdoor"), EveryCompat.res("block/ch/trapdoors/iron_barred_oak_trapdoor_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .createPaletteFromOak(this::darkPalette)
                .setRenderType(() -> RenderType::cutout)
                .setTab(tab)
                .build();

        this.addEntry(ironBarredTrapdoor);

        leafyTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "leafy",
                        () -> getModBlock("leafy_oak_trapdoor"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrapDoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()))
                .addTextureM(modRes("block/oak_trapdoor/leafy_oak_trapdoor"), EveryCompat.res("block/ch/trapdoors/leafy_oak_trapdoor_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(() -> RenderType::cutout)
                .setTab(tab)
                .build();

        this.addEntry(leafyTrapdoor);

        meshedTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "meshed",
                        () -> getModBlock("meshed_oak_trapdoor"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrapDoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()))
                .addTexture(modRes("block/oak_trapdoor/meshed_oak_trapdoor"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(() -> RenderType::cutout)
                .setTab(tab)
                .build();

        this.addEntry(meshedTrapdoor);

        overgrownTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "overgrown",
                        () -> getModBlock("overgrown_oak_trapdoor"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrapDoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()))
                .addTextureM(modRes("block/oak_trapdoor/overgrown_oak_trapdoor"), EveryCompat.res("block/ch/trapdoors/overgrown_oak_trapdoor_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(() -> RenderType::cutout)
                .setTab(tab)
                .build();

        this.addEntry(overgrownTrapdoor);

        pointlessTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "pointless",
                        () -> getModBlock("pointless_oak_trapdoor"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrapDoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()))
                .addTexture(modRes("block/oak_trapdoor/pointless_oak_trapdoor"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(() -> RenderType::cutout)
                .setTab(tab)
                .build();

        this.addEntry(pointlessTrapdoor);

        slottedTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "slotted",
                        () -> getModBlock("slotted_oak_trapdoor"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrapDoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()))
                .addTexture(modRes("block/oak_trapdoor/slotted_oak_trapdoor"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(() -> RenderType::cutout)
                .setTab(tab)
                .build();

        this.addEntry(slottedTrapdoor);

        solidTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "solid",
                        () -> getModBlock("solid_oak_trapdoor"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrapDoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()))
                .addTexture(modRes("block/oak_trapdoor/solid_oak_trapdoor"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(() -> RenderType::cutout)
                .setTab(tab)
                .build();

        this.addEntry(solidTrapdoor);

        suspiciousTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "suspicious",
                        () -> getModBlock("suspicious_oak_trapdoor"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrapDoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()))
                .addTexture(modRes("block/oak_trapdoor/suspicious_oak_trapdoor"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(() -> RenderType::cutout)
                .setTab(tab)
                .build();

        this.addEntry(suspiciousTrapdoor);

        twistedTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "twisted",
                        () -> getModBlock("twisted_oak_trapdoor"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrapDoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()))
                .addTextureM(modRes("block/oak_trapdoor/twisted_oak_trapdoor"), EveryCompat.res("block/ch/trapdoors/twisted_oak_trapdoor_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(() -> RenderType::cutout)
                .setTab(tab)
                .build();

        this.addEntry(twistedTrapdoor);

        vinedTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "vined",
                        () -> getModBlock("vined_oak_trapdoor"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrapDoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()))
                .addTextureM(modRes("block/oak_trapdoor/vined_oak_trapdoor"), EveryCompat.res("block/ch/trapdoors/vined_oak_trapdoor_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(() -> RenderType::cutout)
                .setTab(tab)
                .build();

        this.addEntry(vinedTrapdoor);

        wartedTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "warted",
                        () -> getModBlock("warted_oak_trapdoor"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrapDoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()))
                .addTextureM(modRes("block/oak_trapdoor/warted_oak_trapdoor"), EveryCompat.res("block/ch/trapdoors/warted_oak_trapdoor_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(() -> RenderType::cutout)
                .setTab(tab)
                .build();

        this.addEntry(wartedTrapdoor);

        windowedTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "windowed",
                        () -> getModBlock("windowed_oak_trapdoor"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrapDoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()))
                .addTextureM(modRes("block/oak_trapdoor/windowed_oak_trapdoor"), EveryCompat.res("block/ch/trapdoors/windowed_oak_trapdoor_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(() -> RenderType::cutout)
                .setTab(tab)
                .build();

        this.addEntry(windowedTrapdoor);

        wovenTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "woven",
                        () -> getModBlock("woven_oak_trapdoor"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrapDoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()))
                .addTexture(modRes("block/oak_trapdoor/woven_oak_trapdoor"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(() -> RenderType::cutout)
                .setTab(tab)
                .build();

        this.addEntry(wovenTrapdoor);

        wallTorch = SimpleEntrySet.builder(WoodType.class, "wall_torch",
                        () -> getModBlock("spruce_wall_torch"), () -> WoodTypeRegistry.getValue(new ResourceLocation("spruce")),
                        w -> new WallTorchBlock(Utils.copyPropertySafe(w.planks).noCollission().instabreak().lightLevel(l -> 14), ParticleTypes.FLAME))
                .addTextureM(EveryCompat.res("block/torch/spruce_torch"), EveryCompat.res("block/ch/spruce_torch_m"))
                .addTag(modRes("wall_torch"), Registries.BLOCK)
                .setRenderType(() -> RenderType::cutout)
                .setTab(tab)
                .noItem()
                .build();

        this.addEntry(wallTorch);

        torch = SimpleEntrySet.builder(WoodType.class, "torch",
                        () -> getModBlock("spruce_torch"), () -> WoodTypeRegistry.getValue(new ResourceLocation("spruce")),
                        w -> new TorchBlock(Utils.copyPropertySafe(w.planks).noCollission().instabreak().lightLevel(l -> 14), ParticleTypes.FLAME))
                .addTextureM(EveryCompat.res("block/torch/spruce_torch"), EveryCompat.res("block/ch/spruce_torch_m"))
                .addCustomItem((w, b, p) -> new StandingAndWallBlockItem(b, wallTorch.blocks.get(w), p, Direction.DOWN))
                .addTag(BlockTags.WALL_POST_OVERRIDE, Registries.BLOCK)
                .addTag(modRes("torch"), Registries.BLOCK)
                .addTag(modRes("torch"), Registries.ITEM)
                .setRenderType(() -> RenderType::cutout)
                .setTab(tab)
                .build();

        this.addEntry(torch);

        circleGlass = SimpleEntrySet.builder(WoodType.class, "glass", "circle",
                        () -> getModBlock("circle_oak_glass"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new GlassBlock(Utils.copyPropertySafe(w.planks).strength(0.3F).sound(SoundType.GLASS)
                                .noOcclusion().isValidSpawn((s, l, ps, t) -> false).isRedstoneConductor((s, l, ps) -> false)
                                .isSuffocating((s, l, ps) -> false).isViewBlocking((s, l, ps) -> false)))
                .addTextureM(modRes("block/glass/circle_oak_glass"), EveryCompat.res("block/ch/glass/circle_oak_glass_m"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.IMPERMEABLE, Registries.BLOCK)
                .addTag(modRes("glass"), Registries.BLOCK)
                .addTag(modRes("glass"), Registries.ITEM)
                .setRenderType(() -> RenderType::translucent)
                .setTab(tab)
                .build();

        this.addEntry(circleGlass);

        barredGlass = SimpleEntrySet.builder(WoodType.class, "bared_glass",
                        () -> getModBlock("oak_bared_glass"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new GlassBlock(Utils.copyPropertySafe(w.planks).strength(0.3F).sound(SoundType.GLASS)
                                .noOcclusion().isValidSpawn((s, l, ps, t) -> false).isRedstoneConductor((s, l, ps) -> false)
                                .isSuffocating((s, l, ps) -> false).isViewBlocking((s, l, ps) -> false)))
                .addTextureM(modRes("block/glass/oak_bared_glass"), EveryCompat.res("block/ch/glass/oak_bared_glass_m"))
                .copyTexture(modRes("block/glass/ctm/oak_bared_glass_ctm/0"))
                .addTextureM(modRes("block/glass/ctm/oak_bared_glass_ctm/1"), EveryCompat.res("block/ch/glass/ctm/oak_bared_glass/1_mask"))
                .addTextureM(modRes("block/glass/ctm/oak_bared_glass_ctm/2"), EveryCompat.res("block/ch/glass/ctm/oak_bared_glass/2_mask"))
                .addTextureM(modRes("block/glass/ctm/oak_bared_glass_ctm/3"), EveryCompat.res("block/ch/glass/ctm/oak_bared_glass/3_mask"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.IMPERMEABLE, Registries.BLOCK)
                .addTag(modRes("glass"), Registries.BLOCK)
                .addTag(modRes("glass"), Registries.ITEM)
                .setRenderType(() -> RenderType::translucent)
                .setTab(tab)
                .build();

        this.addEntry(barredGlass);

        borderedGlass = SimpleEntrySet.builder(WoodType.class, "bordered_glass",
                        () -> getModBlock("oak_bordered_glass"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new GlassBlock(Utils.copyPropertySafe(w.planks).strength(0.3F).sound(SoundType.GLASS)
                                .noOcclusion().isValidSpawn((s, l, ps, t) -> false).isRedstoneConductor((s, l, ps) -> false)
                                .isSuffocating((s, l, ps) -> false).isViewBlocking((s, l, ps) -> false)))
                .addTextureM(modRes("block/glass/oak_bordered_glass"), EveryCompat.res("block/ch/glass/oak_bordered_glass_m"))
                .addTextureM(modRes("block/glass/ctm/oak_bordered_glass_ctm/0"), EveryCompat.res("block/ch/glass/ctm/oak_bordered_glass/0_mask"))
                .addTextureM(modRes("block/glass/ctm/oak_bordered_glass_ctm/1"), EveryCompat.res("block/ch/glass/ctm/oak_bordered_glass/1_mask"))
                .addTextureM(modRes("block/glass/ctm/oak_bordered_glass_ctm/2"), EveryCompat.res("block/ch/glass/ctm/oak_bordered_glass/2_mask"))
                .addTextureM(modRes("block/glass/ctm/oak_bordered_glass_ctm/3"), EveryCompat.res("block/ch/glass/ctm/oak_bordered_glass/3_mask"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.IMPERMEABLE, Registries.BLOCK)
                .addTag(modRes("glass"), Registries.BLOCK)
                .addTag(modRes("glass"), Registries.ITEM)
                .setRenderType(() -> RenderType::translucent)
                .setTab(tab)
                .build();

        this.addEntry(borderedGlass);

        diamondBorderedGlass = SimpleEntrySet.builder(WoodType.class, "diamond_bordered_glass",
                        () -> getModBlock("oak_diamond_bordered_glass"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new GlassBlock(Utils.copyPropertySafe(w.planks).strength(0.3F).sound(SoundType.GLASS)
                                .noOcclusion().isValidSpawn((s, l, ps, t) -> false).isRedstoneConductor((s, l, ps) -> false)
                                .isSuffocating((s, l, ps) -> false).isViewBlocking((s, l, ps) -> false)))
                .addTextureM(modRes("block/glass/oak_diamond_bordered_glass"), EveryCompat.res("block/ch/glass/oak_diamond_bordered_glass_m"))
                .addTextureM(modRes("block/glass/ctm/oak_diamond_bordered_glass_ctm/0"), EveryCompat.res("block/ch/glass/ctm/oak_diamond_bordered_glass/0_mask"))
                .addTextureM(modRes("block/glass/ctm/oak_diamond_bordered_glass_ctm/1"), EveryCompat.res("block/ch/glass/ctm/oak_diamond_bordered_glass/1_mask"))
                .addTextureM(modRes("block/glass/ctm/oak_diamond_bordered_glass_ctm/2"), EveryCompat.res("block/ch/glass/ctm/oak_diamond_bordered_glass/2_mask"))
                .addTextureM(modRes("block/glass/ctm/oak_diamond_bordered_glass_ctm/3"), EveryCompat.res("block/ch/glass/ctm/oak_diamond_bordered_glass/3_mask"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.IMPERMEABLE, Registries.BLOCK)
                .addTag(modRes("glass"), Registries.BLOCK)
                .addTag(modRes("glass"), Registries.ITEM)
                .setRenderType(() -> RenderType::translucent)
                .setTab(tab)
                .build();

        this.addEntry(diamondBorderedGlass);

        horizontalLinedGlass = SimpleEntrySet.builder(WoodType.class, "horizontal_lined_glass",
                        () -> getModBlock("oak_horizontal_lined_glass"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new GlassBlock(Utils.copyPropertySafe(w.planks).strength(0.3F).sound(SoundType.GLASS)
                                .noOcclusion().isValidSpawn((s, l, ps, t) -> false).isRedstoneConductor((s, l, ps) -> false)
                                .isSuffocating((s, l, ps) -> false).isViewBlocking((s, l, ps) -> false)))
                .addTextureM(modRes("block/glass/oak_horizontal_lined_glass"), EveryCompat.res("block/ch/glass/oak_horizontal_lined_glass_m"))
                .addTextureM(modRes("block/glass/ctm/oak_horizontal_lined_glass_ctm/0"), EveryCompat.res("block/ch/glass/ctm/oak_horizontal_lined_glass/0_mask"))
                .addTextureM(modRes("block/glass/ctm/oak_horizontal_lined_glass_ctm/1"), EveryCompat.res("block/ch/glass/ctm/oak_horizontal_lined_glass/1_mask"))
                .addTextureM(modRes("block/glass/ctm/oak_horizontal_lined_glass_ctm/2"), EveryCompat.res("block/ch/glass/ctm/oak_horizontal_lined_glass/2_mask"))
                .addTextureM(modRes("block/glass/ctm/oak_horizontal_lined_glass_ctm/3"), EveryCompat.res("block/ch/glass/ctm/oak_horizontal_lined_glass/3_mask"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.IMPERMEABLE, Registries.BLOCK)
                .addTag(modRes("glass"), Registries.BLOCK)
                .addTag(modRes("glass"), Registries.ITEM)
                .setRenderType(() -> RenderType::translucent)
                .setTab(tab)
                .build();

        this.addEntry(horizontalLinedGlass);

        largeDiamondGlass = SimpleEntrySet.builder(WoodType.class, "large_diamond_glass",
                        () -> getModBlock("oak_large_diamond_glass"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new GlassBlock(Utils.copyPropertySafe(w.planks).strength(0.3F).sound(SoundType.GLASS)
                                .noOcclusion().isValidSpawn((s, l, ps, t) -> false).isRedstoneConductor((s, l, ps) -> false)
                                .isSuffocating((s, l, ps) -> false).isViewBlocking((s, l, ps) -> false)))
                .addTextureM(modRes("block/glass/oak_large_diamond_glass"), EveryCompat.res("block/ch/glass/oak_large_diamond_glass_m"))
                .addTextureM(modRes("block/glass/ctm/oak_large_diamond_glass_ctm/0"), EveryCompat.res("block/ch/glass/ctm/oak_large_diamond_glass/0_mask"))
                .addTextureM(modRes("block/glass/ctm/oak_large_diamond_glass_ctm/1"), EveryCompat.res("block/ch/glass/ctm/oak_large_diamond_glass/1_mask"))
                .addTextureM(modRes("block/glass/ctm/oak_large_diamond_glass_ctm/2"), EveryCompat.res("block/ch/glass/ctm/oak_large_diamond_glass/2_mask"))
                .addTextureM(modRes("block/glass/ctm/oak_large_diamond_glass_ctm/3"), EveryCompat.res("block/ch/glass/ctm/oak_large_diamond_glass/3_mask"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.IMPERMEABLE, Registries.BLOCK)
                .addTag(modRes("glass"), Registries.BLOCK)
                .addTag(modRes("glass"), Registries.ITEM)
                .setRenderType(() -> RenderType::translucent)
                .setTab(tab)
                .build();

        this.addEntry(largeDiamondGlass);

        lineBarredGlass = SimpleEntrySet.builder(WoodType.class, "line_bared_glass",
                        () -> getModBlock("oak_line_bared_glass"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new GlassBlock(Utils.copyPropertySafe(w.planks).strength(0.3F).sound(SoundType.GLASS)
                                .noOcclusion().isValidSpawn((s, l, ps, t) -> false).isRedstoneConductor((s, l, ps) -> false)
                                .isSuffocating((s, l, ps) -> false).isViewBlocking((s, l, ps) -> false)))
                .addTextureM(modRes("block/glass/oak_line_bared_glass"), EveryCompat.res("block/ch/glass/oak_line_bared_glass_m"))
                .copyTexture(modRes("block/glass/ctm/oak_line_bared_glass_ctm/0"))
                .addTextureM(modRes("block/glass/ctm/oak_line_bared_glass_ctm/1"), EveryCompat.res("block/ch/glass/ctm/oak_line_bared_glass/1_mask"))
                .addTextureM(modRes("block/glass/ctm/oak_line_bared_glass_ctm/2"), EveryCompat.res("block/ch/glass/ctm/oak_line_bared_glass/2_mask"))
                .addTextureM(modRes("block/glass/ctm/oak_line_bared_glass_ctm/3"), EveryCompat.res("block/ch/glass/ctm/oak_line_bared_glass/3_mask"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.IMPERMEABLE, Registries.BLOCK)
                .addTag(modRes("glass"), Registries.BLOCK)
                .addTag(modRes("glass"), Registries.ITEM)
                .setRenderType(() -> RenderType::translucent)
                .setTab(tab)
                .build();

        this.addEntry(lineBarredGlass);

        ornateBarredGlass = SimpleEntrySet.builder(WoodType.class, "ornate_bared_glass",
                        () -> getModBlock("oak_ornate_bared_glass"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new GlassBlock(Utils.copyPropertySafe(w.planks).strength(0.3F).sound(SoundType.GLASS)
                                .noOcclusion().isValidSpawn((s, l, ps, t) -> false).isRedstoneConductor((s, l, ps) -> false)
                                .isSuffocating((s, l, ps) -> false).isViewBlocking((s, l, ps) -> false)))
                .addTextureM(modRes("block/glass/oak_ornate_bared_glass"), EveryCompat.res("block/ch/glass/oak_ornate_bared_glass_m"))
                .copyTexture(modRes("block/glass/ctm/oak_ornate_bared_glass_ctm/0"))
                .addTextureM(modRes("block/glass/ctm/oak_ornate_bared_glass_ctm/1"), EveryCompat.res("block/ch/glass/ctm/oak_ornate_bared_glass/1_mask"))
                .addTextureM(modRes("block/glass/ctm/oak_ornate_bared_glass_ctm/2"), EveryCompat.res("block/ch/glass/ctm/oak_ornate_bared_glass/2_mask"))
                .addTextureM(modRes("block/glass/ctm/oak_ornate_bared_glass_ctm/3"), EveryCompat.res("block/ch/glass/ctm/oak_ornate_bared_glass/3_mask"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.IMPERMEABLE, Registries.BLOCK)
                .addTag(modRes("glass"), Registries.BLOCK)
                .addTag(modRes("glass"), Registries.ITEM)
                .setRenderType(() -> RenderType::translucent)
                .setTab(tab)
                .build();

        this.addEntry(ornateBarredGlass);

        snowflakeGlass = SimpleEntrySet.builder(WoodType.class, "snowflake_glass",
                        () -> getModBlock("oak_snowflake_glass"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new GlassBlock(Utils.copyPropertySafe(w.planks).strength(0.3F).sound(SoundType.GLASS)
                                .noOcclusion().isValidSpawn((s, l, ps, t) -> false).isRedstoneConductor((s, l, ps) -> false)
                                .isSuffocating((s, l, ps) -> false).isViewBlocking((s, l, ps) -> false)))
                .addTextureM(modRes("block/glass/oak_snowflake_glass"), EveryCompat.res("block/ch/glass/oak_snowflake_glass_m"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.IMPERMEABLE, Registries.BLOCK)
                .addTag(modRes("glass"), Registries.BLOCK)
                .addTag(modRes("glass"), Registries.ITEM)
                .setRenderType(() -> RenderType::translucent)
                .setTab(tab)
                .build();

        this.addEntry(snowflakeGlass);

        wovenGlass = SimpleEntrySet.builder(WoodType.class, "woven_glass",
                        () -> getModBlock("oak_woven_glass"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new GlassBlock(Utils.copyPropertySafe(w.planks).strength(0.3F).sound(SoundType.GLASS)
                                .noOcclusion().isValidSpawn((s, l, ps, t) -> false).isRedstoneConductor((s, l, ps) -> false)
                                .isSuffocating((s, l, ps) -> false).isViewBlocking((s, l, ps) -> false)))
                .addTextureM(modRes("block/glass/oak_woven_glass"), EveryCompat.res("block/ch/glass/oak_woven_glass_m"))
                .addTextureM(modRes("block/glass/ctm/oak_woven_glass_ctm/0"), EveryCompat.res("block/ch/glass/ctm/oak_woven_glass/0_mask"))
                .addTextureM(modRes("block/glass/ctm/oak_woven_glass_ctm/1"), EveryCompat.res("block/ch/glass/ctm/oak_woven_glass/1_mask"))
                .addTextureM(modRes("block/glass/ctm/oak_woven_glass_ctm/2"), EveryCompat.res("block/ch/glass/ctm/oak_woven_glass/2_mask"))
                .addTextureM(modRes("block/glass/ctm/oak_woven_glass_ctm/3"), EveryCompat.res("block/ch/glass/ctm/oak_woven_glass/3_mask"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.IMPERMEABLE, Registries.BLOCK)
                .addTag(modRes("glass"), Registries.BLOCK)
                .addTag(modRes("glass"), Registries.ITEM)
                .setRenderType(() -> RenderType::translucent)
                .setTab(tab)
                .build();

        this.addEntry(wovenGlass);

        squareGlass = SimpleEntrySet.builder(WoodType.class, "glass", "square",
                        () -> getModBlock("square_oak_glass"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new GlassBlock(Utils.copyPropertySafe(w.planks).strength(0.3F).sound(SoundType.GLASS)
                                .noOcclusion().isValidSpawn((s, l, ps, t) -> false).isRedstoneConductor((s, l, ps) -> false)
                                .isSuffocating((s, l, ps) -> false).isViewBlocking((s, l, ps) -> false)))
                .addTextureM(modRes("block/glass/square_oak_glass"), EveryCompat.res("block/ch/glass/square_oak_glass_m"))
                .addTextureM(modRes("block/glass/ctm/square_oak_glass_ctm/0"), EveryCompat.res("block/ch/glass/ctm/square_oak_glass/0_mask"))
                .addTextureM(modRes("block/glass/ctm/square_oak_glass_ctm/1"), EveryCompat.res("block/ch/glass/ctm/square_oak_glass/1_mask"))
                .addTextureM(modRes("block/glass/ctm/square_oak_glass_ctm/2"), EveryCompat.res("block/ch/glass/ctm/square_oak_glass/2_mask"))
                .addTextureM(modRes("block/glass/ctm/square_oak_glass_ctm/3"), EveryCompat.res("block/ch/glass/ctm/square_oak_glass/3_mask"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.IMPERMEABLE, Registries.BLOCK)
                .addTag(modRes("glass"), Registries.BLOCK)
                .addTag(modRes("glass"), Registries.ITEM)
                .setRenderType(() -> RenderType::translucent)
                .setTab(tab)
                .build();

        this.addEntry(squareGlass);

        circleGlassPane = SimpleEntrySet.builder(WoodType.class, "glass_pane", "circle",
                        () -> getModBlock("circle_oak_glass_pane"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new IronBarsBlock(Utils.copyPropertySafe(w.planks).strength(0.3F).sound(SoundType.GLASS).noOcclusion()))
                .addModelTransform(m -> m.replaceString("minecraft:block/glass_pane_top", "chipped:block/glass_pane/circle_oak_glass_pane_top"))
                .addTextureM(modRes("block/glass/circle_oak_glass"), EveryCompat.res("block/ch/glass/circle_oak_glass_m"))
                .addTexture(modRes("block/glass_pane/circle_oak_glass_pane_top"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("glass_pane"), Registries.BLOCK)
                .addTag(modRes("glass_pane"), Registries.ITEM)
                .setRenderType(() -> RenderType::translucent)
                .setTab(tab)
                .build();

        this.addEntry(circleGlassPane);

        barredGlassPane = SimpleEntrySet.builder(WoodType.class, "bared_glass_pane",
                        () -> getModBlock("oak_bared_glass_pane"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new IronBarsBlock(Utils.copyPropertySafe(w.planks).strength(0.3F).sound(SoundType.GLASS).noOcclusion()))
                .addModelTransform(m -> m.replaceString("minecraft:block/glass_pane_top", "chipped:block/glass_pane/oak_bared_glass_pane_top"))
                .addTextureM(modRes("block/glass/oak_bared_glass"), EveryCompat.res("block/ch/glass/oak_bared_glass_m"))
                .addTexture(modRes("block/glass_pane/oak_bared_glass_pane_top"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("glass_pane"), Registries.BLOCK)
                .addTag(modRes("glass_pane"), Registries.ITEM)
                .setRenderType(() -> RenderType::translucent)
                .setTab(tab)
                .build();

        this.addEntry(barredGlassPane);

        borderedGlassPane = SimpleEntrySet.builder(WoodType.class, "bordered_glass_pane",
                        () -> getModBlock("oak_bordered_glass_pane"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new IronBarsBlock(Utils.copyPropertySafe(w.planks).strength(0.3F).sound(SoundType.GLASS).noOcclusion()))
                .addModelTransform(m -> m.replaceString("minecraft:block/glass_pane_top", "chipped:block/glass_pane/oak_bordered_glass_pane_top"))
                .addTextureM(modRes("block/glass/oak_bordered_glass"), EveryCompat.res("block/ch/glass/oak_bordered_glass_m"))
                .addTexture(modRes("block/glass_pane/oak_bordered_glass_pane_top"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("glass_pane"), Registries.BLOCK)
                .addTag(modRes("glass_pane"), Registries.ITEM)
                .setRenderType(() -> RenderType::translucent)
                .setTab(tab)
                .build();

        this.addEntry(borderedGlassPane);

        diamondBorderedGlassPane = SimpleEntrySet.builder(WoodType.class, "diamond_bordered_glass_pane",
                        () -> getModBlock("oak_diamond_bordered_glass_pane"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new IronBarsBlock(Utils.copyPropertySafe(w.planks).strength(0.3F).sound(SoundType.GLASS).noOcclusion()))
                .addModelTransform(m -> m.replaceString("minecraft:block/glass_pane_top", "chipped:block/glass_pane/oak_diamond_bordered_glass_pane_top"))
                .addTextureM(modRes("block/glass/oak_diamond_bordered_glass"), EveryCompat.res("block/ch/glass/oak_diamond_bordered_glass_m"))
                .addTexture(modRes("block/glass_pane/oak_diamond_bordered_glass_pane_top"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("glass_pane"), Registries.BLOCK)
                .addTag(modRes("glass_pane"), Registries.ITEM)
                .setRenderType(() -> RenderType::translucent)
                .setTab(tab)
                .build();

        this.addEntry(diamondBorderedGlassPane);

        horizontalLinedGlassPane = SimpleEntrySet.builder(WoodType.class, "horizontal_lined_glass_pane",
                        () -> getModBlock("oak_horizontal_lined_glass_pane"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new IronBarsBlock(Utils.copyPropertySafe(w.planks).strength(0.3F).sound(SoundType.GLASS).noOcclusion()))
                .addModelTransform(m -> m.replaceString("minecraft:block/glass_pane_top", "chipped:block/glass_pane/oak_horizontal_lined_glass_pane_top"))
                .addTextureM(modRes("block/glass/oak_horizontal_lined_glass"), EveryCompat.res("block/ch/glass/oak_horizontal_lined_glass_m"))
                .addTexture(modRes("block/glass_pane/oak_horizontal_lined_glass_pane_top"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("glass_pane"), Registries.BLOCK)
                .addTag(modRes("glass_pane"), Registries.ITEM)
                .setRenderType(() -> RenderType::translucent)
                .setTab(tab)
                .build();

        this.addEntry(horizontalLinedGlassPane);

        largeDiamondGlassPane = SimpleEntrySet.builder(WoodType.class, "large_diamond_glass_pane",
                        () -> getModBlock("oak_large_diamond_glass_pane"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new IronBarsBlock(Utils.copyPropertySafe(w.planks).strength(0.3F).sound(SoundType.GLASS).noOcclusion()))
                .addModelTransform(m -> m.replaceString("minecraft:block/glass_pane_top", "chipped:block/glass_pane/oak_large_diamond_glass_pane_top"))
                .addTextureM(modRes("block/glass/oak_large_diamond_glass"), EveryCompat.res("block/ch/glass/oak_large_diamond_glass_m"))
                .addTexture(modRes("block/glass_pane/oak_large_diamond_glass_pane_top"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("glass_pane"), Registries.BLOCK)
                .addTag(modRes("glass_pane"), Registries.ITEM)
                .setRenderType(() -> RenderType::translucent)
                .setTab(tab)
                .build();

        this.addEntry(largeDiamondGlassPane);

        lineBarredGlassPane = SimpleEntrySet.builder(WoodType.class, "line_bared_glass_pane",
                        () -> getModBlock("oak_line_bared_glass_pane"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new IronBarsBlock(Utils.copyPropertySafe(w.planks).strength(0.3F).sound(SoundType.GLASS).noOcclusion()))
                .addModelTransform(m -> m.replaceString("minecraft:block/glass_pane_top", "chipped:block/glass_pane/oak_line_bared_glass_pane_top"))
                .addTextureM(modRes("block/glass/oak_line_bared_glass"), EveryCompat.res("block/ch/glass/oak_line_bared_glass_m"))
                .addTexture(modRes("block/glass_pane/oak_line_bared_glass_pane_top"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("glass_pane"), Registries.BLOCK)
                .addTag(modRes("glass_pane"), Registries.ITEM)
                .setRenderType(() -> RenderType::translucent)
                .setTab(tab)
                .build();

        this.addEntry(lineBarredGlassPane);

        ornateBarredGlassPane = SimpleEntrySet.builder(WoodType.class, "ornate_bared_glass_pane",
                        () -> getModBlock("oak_ornate_bared_glass_pane"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new IronBarsBlock(Utils.copyPropertySafe(w.planks).strength(0.3F).sound(SoundType.GLASS).noOcclusion()))
                .addModelTransform(m -> m.replaceString("minecraft:block/glass_pane_top", "chipped:block/glass_pane/oak_ornate_bared_glass_pane_top"))
                .addTextureM(modRes("block/glass/oak_ornate_bared_glass"), EveryCompat.res("block/ch/glass/oak_ornate_bared_glass_m"))
                .addTexture(modRes("block/glass_pane/oak_ornate_bared_glass_pane_top"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("glass_pane"), Registries.BLOCK)
                .addTag(modRes("glass_pane"), Registries.ITEM)
                .setRenderType(() -> RenderType::translucent)
                .setTab(tab)
                .build();

        this.addEntry(ornateBarredGlassPane);

        snowflakeGlassPane = SimpleEntrySet.builder(WoodType.class, "snowflake_glass_pane",
                        () -> getModBlock("oak_snowflake_glass_pane"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new IronBarsBlock(Utils.copyPropertySafe(w.planks).strength(0.3F).sound(SoundType.GLASS).noOcclusion()))
                .addModelTransform(m -> m.replaceString("minecraft:block/glass_pane_top", "chipped:block/glass_pane/oak_snowflake_glass_pane_top"))
                .addTextureM(modRes("block/glass/oak_snowflake_glass"), EveryCompat.res("block/ch/glass/oak_snowflake_glass_m"))
                .addTexture(modRes("block/glass_pane/oak_snowflake_glass_pane_top"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("glass_pane"), Registries.BLOCK)
                .addTag(modRes("glass_pane"), Registries.ITEM)
                .setRenderType(() -> RenderType::translucent)
                .setTab(tab)
                .build();

        this.addEntry(snowflakeGlassPane);

        wovenGlassPane = SimpleEntrySet.builder(WoodType.class, "woven_glass_pane",
                        () -> getModBlock("oak_woven_glass_pane"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new IronBarsBlock(Utils.copyPropertySafe(w.planks).strength(0.3F).sound(SoundType.GLASS).noOcclusion()))
                .addModelTransform(m -> m.replaceString("minecraft:block/glass_pane_top", "chipped:block/glass_pane/oak_woven_glass_pane_top"))
                .addTextureM(modRes("block/glass/oak_woven_glass"), EveryCompat.res("block/ch/glass/oak_woven_glass_m"))
                .addTexture(modRes("block/glass_pane/oak_woven_glass_pane_top"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("glass_pane"), Registries.BLOCK)
                .addTag(modRes("glass_pane"), Registries.ITEM)
                .setRenderType(() -> RenderType::translucent)
                .setTab(tab)
                .build();

        this.addEntry(wovenGlassPane);

        squareGlassPane = SimpleEntrySet.builder(WoodType.class, "glass_pane", "square",
                        () -> getModBlock("square_oak_glass_pane"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new IronBarsBlock(Utils.copyPropertySafe(w.planks).strength(0.3F).sound(SoundType.GLASS).noOcclusion()))
                .addModelTransform(m -> m.replaceString("minecraft:block/glass_pane_top", "chipped:block/glass_pane/square_oak_glass_pane_top"))
                .addTextureM(modRes("block/glass/square_oak_glass"), EveryCompat.res("block/ch/glass/square_oak_glass_m"))
                .addTexture(modRes("block/glass_pane/square_oak_glass_pane_top"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("glass_pane"), Registries.BLOCK)
                .addTag(modRes("glass_pane"), Registries.ITEM)
                .setRenderType(() -> RenderType::translucent)
                .setTab(tab)
                .build();

        this.addEntry(squareGlassPane);
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

    private void lightPalette(Palette p) {
        p.remove(p.getDarkest());
        p.remove(p.getDarkest());
    }

    private void lighterPalette(Palette p) {
        p.remove(p.getLightest());
        p.remove(p.getDarkest());
        p.remove(p.getDarkest());
    }

    private void darkerPalette(Palette p) {
        p.remove(p.getDarkest());
        p.remove(p.getLightest());
    }

    private void darkPalette(Palette p) {
        p.add(p.increaseInner());
        p.add(p.increaseInner());
        p.add(p.increaseInner());
        p.remove(p.getLightest());
        p.remove(p.getLightest());
        p.remove(p.getDarkest());
    }

    private void darkestPalette(Palette p) {
        p.remove(p.getLightest());
        p.remove(p.getLightest());
        p.remove(p.getDarkest());
    }

    private void panelPalette(Palette p) {
        p.remove(p.getDarkest());
        p.remove(p.getDarkest());
        p.remove(p.getDarkest());
        p.remove(p.getLightest());
    }

    @Override
    public void addDynamicServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicServerResources(handler, manager);


        //use this. also set the entry to no drop so we don't have 2
        // List<EntrySet<?>> doors = this.getEntries().stream().filter(e -> e.typeName.contains("door")).toList();
        //for (var e : doors) {
        ///for (var d : e.blocks.values()) {
        //TODO:
        //handler.dynamicPack.addLootTable(d, VanillaBlockLoot.createDoorTable(d));
        //}
        // }

        addChippedRecipe(handler.getPack(), "planks", "carpenters_table", "ch/carpenters_table");
        addChippedRecipe(handler.getPack(), "door", "carpenters_table", "ch/carpenters_table");
        addChippedRecipe(handler.getPack(), "trapdoor", "carpenters_table", "ch/carpenters_table");

    }

    private void addChippedRecipe(DynamicDataPack pack, String identifier, String workStation, String recipeName) {
        JsonArray ja = new JsonArray();
        for (var w : WoodTypeRegistry.getTypes()) {
            boolean hasSomething = false;
            var id = w.id;
            SimpleTagBuilder tagBuilder = SimpleTagBuilder.of(EveryCompat.res(
                    id.getNamespace() + "_" + id.getPath() + "_" + identifier));
            for (var e : this.getEntries()) {
                String name = e.getName();
                if (name.matches(".*(_" + identifier + "|" + identifier + "_).*")) {
                    Item b = ((SimpleEntrySet<?, ?>) e).items.get(w);
                    if (b != null) {
                        hasSomething = true;
                        tagBuilder.addEntry(b);
                    }
                }
            }

            // Checking for Child of wood type exist
            if (w.getChild(identifier) != null) {
                switch (identifier) {
                    case "planks" -> tagBuilder.addEntry(w.planks); // adds normal planks
                    case "door" -> tagBuilder.addEntry(w.getChild("door")); // adds normal door
                    case "trapdoor" -> tagBuilder.addEntry(w.getChild("trapdoor")); // adds normal trapdoor
                }
            }

            if (hasSomething) {
                pack.addTag(tagBuilder, Registries.ITEM);
                ja.add(tagBuilder.getId().toString());
            }
        }
        JsonObject jo = new JsonObject();
        jo.addProperty("type", "chipped:" + workStation);
        jo.add("tags", ja);
        pack.addJson(EveryCompat.res(recipeName + "_" + identifier), jo, ResType.RECIPES);

    }

}
