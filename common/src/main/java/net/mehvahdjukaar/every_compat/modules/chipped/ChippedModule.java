package net.mehvahdjukaar.every_compat.modules.chipped;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.*;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.SimpleTagBuilder;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynamicDataPack;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.resources.textures.PaletteColor;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.List;

//TODO:
// Mcmeta files are not copied from the base block

//SUPPORT: v3.0.1+
public class ChippedModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> barrel,
            crate,
            reinforcedCrate,
            basketWovenPlanks,
            boxedPlanks,
            brickBondPlanks,
            brickyPlanks,
            corneredPlanks,
            cratedPlanks,
            crossLacedPlanks,
            crossedPlanks,
            detailedPlanks,
            diagonalPlanks,
            diamondPlanks,
            doubleHerringbonePlanks,
            enclosedPlanks,
            finePlanks,
            fineVerticalPlanks,
            framedPlanks,
            herringbonePlanks,
            hewnPlanks,
            lacedPlanks,
            mosaicPlanks,
            nailedPlanks,
            naturalPlanks,
            panelPlanks,
            peggedPlanks,
            polishedPlanks,
            shavingsPlanks,
            railedPlanks,
            shiftedPlanks,
            slantedPlanks,
            smoothPlanks,
            stackedPlanks,
            thinPlanks,
            tiledPlanks,
            versaillesPlanks,
            verticalPlanks,
            verticallyRailedPlanks,
            whirlwindPlanks,
            wickeredPlanks;

    public final SimpleEntrySet<WoodType, Block> barredDoor,
            beachDoor,
            boardedDoor,
            dualPaneledDoor,
            fortifiedDoor,
            gatedDoor,
            glassDoor,
            heavyDoor,
            overgrownDoor,
            paneledDoor,
            paperDoor,
            pressedDoor,
            screenDoor,
            secretDoor,
            shackDoor,
            slidingDoor,
            supportedDoor,
            tileWindowedDoor,
            tiledDoor,
            windowedDoor;

    public final SimpleEntrySet<WoodType, Block> airyTrapdoor,
            barredTrapdoor,
            checkeredTrapdoor,
            classicTrapdoor,
            classicWindowedTrapdoor,
            cobwebTrapdoor,
            distortedTrapdoor,
            fancyTrapdoor,
            goldenBarredTrapdoor,
            heavyTrapdoor,
            ironBarredTrapdoor,
            leafyTrapdoor,
            meshedTrapdoor,
            overgrownTrapdoor,
            pointlessTrapdoor,
            slottedTrapdoor,
            solidTrapdoor,
            suspiciousTrapdoor,
            twistedTrapdoor,
            vinedTrapdoor,
            wartedTrapdoor,
            windowedTrapdoor,
            wovenTrapdoor;

    public final SimpleEntrySet<WoodType, Block> torch;
    public final SimpleEntrySet<WoodType, Block> wallTorch;

    public final SimpleEntrySet<WoodType, Block> circleGlass,
            barredGlass,
            borderedGlass,
            diamondBorderedGlass,
            horizontalLinedGlass,
            largeDiamondGlass,
            lineBarredGlass,
            ornateBarredGlass,
            snowflakeGlass,
            squareGlass,
            wovenGlass;

    public final SimpleEntrySet<WoodType, Block> circleGlassPane,
            barredGlassPane,
            borderedGlassPane,
            diamondBorderedGlassPane,
            horizontalLinedGlassPane,
            largeDiamondGlassPane,
            lineBarredGlassPane,
            ornateBarredGlassPane,
            snowflakeGlassPane,
            squareGlassPane,
            wovenGlassPane;

    public final SimpleEntrySet<WoodType, Block> BundledLog,
            CenterCutLog,
            DamagedLog,
            EdgeCutLog,
            FirewoodLog,
            FloweringLog,
            MixedLog,
            NailedLog,
            OvergrownLog,
            PlankedLog,
            ReinforcedLog;


    public ChippedModule(String modId) {
        super(modId, "ch");
        ResourceLocation tab = modRes("main");

        mosaicPlanks = SimpleEntrySet.builder(WoodType.class, "planks_mosaic",
                        getModBlock("oak_planks_mosaic"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/oak_planks_mosaic"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .createPaletteFromPlanks(this::dullPalette)
                .setTabKey(tab)
                .build();
        this.addEntry(mosaicPlanks);

        panelPlanks = SimpleEntrySet.builder(WoodType.class, "planks_panel",
                        getModBlock("oak_planks_panel"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/ctm/oak_planks_panel_ctm/0"))
                .addTexture(modRes("block/oak_planks/ctm/oak_planks_panel_ctm/1"))
                .addTexture(modRes("block/oak_planks/ctm/oak_planks_panel_ctm/2"))
                .addTexture(modRes("block/oak_planks/ctm/oak_planks_panel_ctm/3"))
                .addTexture(modRes("block/oak_planks/oak_planks_panel"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .createPaletteFromPlanks(this::panelPalette)
                .setTabKey(tab)
                .build();
        this.addEntry(panelPlanks);

        shavingsPlanks = SimpleEntrySet.builder(WoodType.class, "planks_shavings",
                        getModBlock("oak_planks_shavings"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/oak_planks_shavings"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .setTabKey(tab)
                .build();
        this.addEntry(shavingsPlanks);

        basketWovenPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "basket_woven",
                        getModBlock("basket_woven_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/basket_woven_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .createPaletteFromPlanks(this::darkerPalette)
                .setTabKey(tab)
                .build();
        this.addEntry(basketWovenPlanks);

        boxedPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "boxed",
                        getModBlock("boxed_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/boxed_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .createPaletteFromPlanks(this::darkerPalette)
                .setTabKey(tab)
                .build();
        this.addEntry(boxedPlanks);

        brickBondPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "brick_bond",
                        getModBlock("brick_bond_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/brick_bond_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .createPaletteFromPlanks(this::dullerPalette)
                .setTabKey(tab)
                .build();
        this.addEntry(brickBondPlanks);

        brickyPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "bricky",
                        getModBlock("bricky_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/bricky_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .createPaletteFromPlanks(this::darkerPalette)
                .setTabKey(tab)
                .build();
        this.addEntry(brickyPlanks);

        corneredPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "cornered",
                        getModBlock("cornered_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/ctm/cornered_oak_planks_ctm/0"))
                .addTexture(modRes("block/oak_planks/ctm/cornered_oak_planks_ctm/1"))
                .addTexture(modRes("block/oak_planks/ctm/cornered_oak_planks_ctm/2"))
                .addTexture(modRes("block/oak_planks/ctm/cornered_oak_planks_ctm/3"))
                .addTexture(modRes("block/oak_planks/cornered_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .createPaletteFromPlanks(this::darkerPalette)
                .setTabKey(tab)
                .build();
        this.addEntry(corneredPlanks);

        cratedPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "crated",
                        getModBlock("crated_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/ctm/common_textures/0"))
                .addTexture(modRes("block/oak_planks/ctm/crated_oak_planks_ctm/1"))
                .addTexture(modRes("block/oak_planks/ctm/crated_oak_planks_ctm/2"))
                .addTexture(modRes("block/oak_planks/ctm/crated_oak_planks_ctm/3"))
                .addTexture(modRes("block/oak_planks/crated_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .createPaletteFromPlanks(this::dullerPalette)
                .setTabKey(tab)
                .build();
        this.addEntry(cratedPlanks);

        crossLacedPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "cross_laced",
                        getModBlock("cross_laced_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/cross_laced_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .setTabKey(tab)
                .build();
        this.addEntry(crossLacedPlanks);

        crossedPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "crossed",
                        getModBlock("crossed_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/crossed_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .setTabKey(tab)
                .build();
        this.addEntry(crossedPlanks);

        detailedPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "detailed",
                        getModBlock("detailed_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/detailed_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .createPaletteFromPlanks(this::darkerPalette)
                .setTabKey(tab)
                .build();
        this.addEntry(detailedPlanks);

        diagonalPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "diagonal",
                        getModBlock("diagonal_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/diagonal_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .createPaletteFromPlanks(this::darkerPalette)
                .setTabKey(tab)
                .build();
        this.addEntry(diagonalPlanks);

        diamondPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "diamond",
                        getModBlock("diamond_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/diamond_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .setTabKey(tab)
                .build();
        this.addEntry(diamondPlanks);

        doubleHerringbonePlanks = SimpleEntrySet.builder(WoodType.class, "planks", "double_herringbone",
                        getModBlock("double_herringbone_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/double_herringbone_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .createPaletteFromPlanks(this::dullLuminance)
                .setTabKey(tab)
                .build();
        this.addEntry(doubleHerringbonePlanks);

        enclosedPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "enclosed",
                        getModBlock("enclosed_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/ctm/common_textures/0"))
                .addTexture(modRes("block/oak_planks/ctm/enclosed_oak_planks_ctm/1"))
                .addTexture(modRes("block/oak_planks/ctm/enclosed_oak_planks_ctm/2"))
                .addTexture(modRes("block/oak_planks/ctm/enclosed_oak_planks_ctm/3"))
                .addTexture(modRes("block/oak_planks/enclosed_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .createPaletteFromPlanks(this::dullerPalette)
                .setTabKey(tab)
                .build();
        this.addEntry(enclosedPlanks);

        finePlanks = SimpleEntrySet.builder(WoodType.class, "planks", "fine",
                        getModBlock("fine_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/fine_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .createPaletteFromPlanks(this::dullPalette)
                .setTabKey(tab)
                .build();
        this.addEntry(finePlanks);

        fineVerticalPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "fine_vertical",
                        getModBlock("fine_vertical_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/fine_vertical_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .createPaletteFromPlanks(this::dullPalette)
                .setTabKey(tab)
                .build();
        this.addEntry(fineVerticalPlanks);

        framedPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "framed",
                        getModBlock("framed_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/ctm/framed_oak_planks_ctm/0"))
                .addTexture(modRes("block/oak_planks/ctm/framed_oak_planks_ctm/1"))
                .addTexture(modRes("block/oak_planks/ctm/framed_oak_planks_ctm/2"))
                .addTexture(modRes("block/oak_planks/ctm/framed_oak_planks_ctm/3"))
                .addTexture(modRes("block/oak_planks/framed_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .createPaletteFromPlanks(this::darkerPalette)
                .setTabKey(tab)
                .build();
        this.addEntry(framedPlanks);

        herringbonePlanks = SimpleEntrySet.builder(WoodType.class, "planks", "herringbone",
                        getModBlock("herringbone_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/herringbone_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
//                .createPaletteFromPlanks(this::dullLuminance
                .setTabKey(tab)
                .build();
        this.addEntry(herringbonePlanks);

        hewnPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "hewn",
                        getModBlock("hewn_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/hewn_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .createPaletteFromPlanks(this::darkerPalette)
                .setTabKey(tab)
                .build();
        this.addEntry(hewnPlanks);

        lacedPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "laced",
                        getModBlock("laced_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/laced_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .createPaletteFromPlanks(this::darkerPalette)
                .setTabKey(tab)
                .build();
        this.addEntry(lacedPlanks);

        nailedPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "nailed",
                        getModBlock("nailed_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/nailed_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .createPaletteFromPlanks(this::darkerPalette)
                .setTabKey(tab)
                .build();
        this.addEntry(nailedPlanks);

        naturalPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "natural",
                        getModBlock("natural_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/ctm/natural_oak_planks_ctm/0"))
                .addTexture(modRes("block/oak_planks/ctm/natural_oak_planks_ctm/1"))
                .addTexture(modRes("block/oak_planks/ctm/natural_oak_planks_ctm/2"))
                .addTexture(modRes("block/oak_planks/ctm/natural_oak_planks_ctm/3"))
                .addTexture(modRes("block/oak_planks/natural_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .createPaletteFromPlanks(this::darkPalette)
                .setTabKey(tab)
                .build();
        this.addEntry(naturalPlanks);

        peggedPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "pegged",
                        getModBlock("pegged_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/ctm/pegged_oak_planks_ctm/0"))
                .addTexture(modRes("block/oak_planks/ctm/pegged_oak_planks_ctm/1"))
                .addTexture(modRes("block/oak_planks/ctm/pegged_oak_planks_ctm/2"))
                .addTexture(modRes("block/oak_planks/ctm/pegged_oak_planks_ctm/3"))
                .addTexture(modRes("block/oak_planks/pegged_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .createPaletteFromPlanks(this::dullPalette)
                .setTabKey(tab)
                .build();
        this.addEntry(peggedPlanks);

        polishedPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "polished",
                        getModBlock("polished_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/polished_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .createPaletteFromPlanks(this::polishedPalette)
                .setTabKey(tab)
                .build();
        this.addEntry(polishedPlanks);

        railedPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "railed",
                        getModBlock("railed_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/railed_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .setTabKey(tab)
                .build();
        this.addEntry(railedPlanks);

        shiftedPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "shifted",
                        getModBlock("shifted_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/shifted_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .createPaletteFromPlanks(this::darkerPalette)
                .setTabKey(tab)
                .build();
        this.addEntry(shiftedPlanks);

        slantedPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "slanted",
                        getModBlock("slanted_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/slanted_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .createPaletteFromPlanks(this::dullLuminance)
                .setTabKey(tab)
                .build();
        this.addEntry(slantedPlanks);

        smoothPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "smooth",
                        getModBlock("smooth_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/smooth_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .createPaletteFromPlanks(this::darkerPalette)
                .setTabKey(tab)
                .build();
        this.addEntry(smoothPlanks);

        stackedPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "stacked",
                        getModBlock("stacked_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/stacked_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .createPaletteFromPlanks(this::darkerPalette)
                .setTabKey(tab)
                .build();
        this.addEntry(stackedPlanks);

        thinPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "thin",
                        getModBlock("thin_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/thin_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .createPaletteFromPlanks(this::darkerPalette)
                .setTabKey(tab)
                .build();
        this.addEntry(thinPlanks);

        tiledPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "tiled",
                        getModBlock("tiled_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/tiled_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .createPaletteFromPlanks(this::darkerPalette)
                .setTabKey(tab)
                .build();
        this.addEntry(tiledPlanks);

        versaillesPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "versailles",
                        getModBlock("versailles_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/versailles_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .createPaletteFromPlanks(this::darkerPalette)
                .setTabKey(tab)
                .build();
        this.addEntry(versaillesPlanks);

        verticalPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "vertical",
                        getModBlock("vertical_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/vertical_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .setTabKey(tab)
                .build();
        this.addEntry(verticalPlanks);

        verticallyRailedPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "vertically_railed",
                        getModBlock("vertically_railed_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/vertically_railed_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .setTabKey(tab)
                .build();
        this.addEntry(verticallyRailedPlanks);

        whirlwindPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "whirlwind",
                        getModBlock("whirlwind_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/ctm/whirlwind_oak_planks_ctm/0"))
                .addTexture(modRes("block/oak_planks/ctm/whirlwind_oak_planks_ctm/1"))
                .addTexture(modRes("block/oak_planks/ctm/whirlwind_oak_planks_ctm/2"))
                .addTexture(modRes("block/oak_planks/ctm/whirlwind_oak_planks_ctm/3"))
                .addTexture(modRes("block/oak_planks/whirlwind_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .createPaletteFromPlanks(this::darkPalette)
                .setTabKey(tab)
                .build();
        this.addEntry(whirlwindPlanks);

        wickeredPlanks = SimpleEntrySet.builder(WoodType.class, "planks", "wickered",
                        getModBlock("wickered_oak_planks"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Block(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/wickered_oak_planks"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .createPaletteFromPlanks(this::darkerPalette)
                .setTabKey(tab)
                .build();
        this.addEntry(wickeredPlanks);

        barrel = SimpleEntrySet.builder(WoodType.class, "barrel",
                        getModBlock("oak_barrel"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BarrelBlock(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/barrel/oak_barrel_bottom"))
                .addTexture(modRes("block/barrel/oak_barrel_top_open"))
                .addTextureM(modRes("block/barrel/oak_barrel_side"), EveryCompat.res("block/ch/oak_barrel_side_m"))
                .addTextureM(modRes("block/barrel/oak_barrel_top"), EveryCompat.res("block/ch/oak_barrel_top_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("barrel"), Registries.BLOCK)
                .addTag(modRes("barrel"), Registries.ITEM)
                .addTile(() -> BlockEntityType.BARREL)
                .createPaletteFromPlanks(this::darkerPalette)
                .setTabKey(tab)
                .build();
        this.addEntry(barrel);

        crate = SimpleEntrySet.builder(WoodType.class, "crate",
                        getModBlock("oak_crate"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BarrelBlock(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/barrel/oak_crate_side"))
                .addTexture(modRes("block/barrel/oak_crate_top"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("barrel"), Registries.BLOCK)
                .addTag(modRes("barrel"), Registries.ITEM)
                .createPaletteFromPlanks(this::darkPalette)
                .addTile(() -> BlockEntityType.BARREL)
                .setTabKey(tab)
                .build();
        this.addEntry(crate);

        reinforcedCrate = SimpleEntrySet.builder(WoodType.class, "crate", "reinforced",
                        getModBlock("reinforced_oak_crate"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BarrelBlock(Utils.copyPropertySafe(w.planks)))
                .addTexture(modRes("block/barrel/reinforced_oak_crate_side"))
                .addTexture(modRes("block/barrel/reinforced_oak_crate_top"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("barrel"), Registries.BLOCK)
                .addTag(modRes("barrel"), Registries.ITEM)
                .createPaletteFromPlanks(this::darkPalette)
                .addTile(() -> BlockEntityType.BARREL)
                .setTabKey(tab)
                .build();
        this.addEntry(reinforcedCrate);

        //TYPE: door
        barredDoor = SimpleEntrySet.builder(WoodType.class, "door", "barred",
                        getModBlock("barred_oak_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.log).noOcclusion(), w.toVanillaOrOak().setType()) {
                        })
                .addTextureM(modRes("block/oak_door/barred_oak_door_bottom"), EveryCompat.res("block/ch/doors/barred_oak_door_bottom_m"))
                .addTextureM(modRes("block/oak_door/barred_oak_door_top"), EveryCompat.res("block/ch/doors/barred_oak_door_top_m"))
                .addTextureM(modRes("item/oak_door/barred_oak_door"), EveryCompat.res("item/ch/doors/barred_oak_door_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .noDrops()
                .build();
        this.addEntry(barredDoor);

        beachDoor = SimpleEntrySet.builder(WoodType.class, "door", "beach",
                        getModBlock("beach_oak_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.log).noOcclusion(), w.toVanillaOrOak().setType()) {
                        })
                .addTextureM(modRes("block/oak_door/beach_oak_door_bottom"), EveryCompat.res("block/ch/doors/beach_oak_door_bottom_m"))
                .addTextureM(modRes("block/oak_door/beach_oak_door_top"), EveryCompat.res("block/ch/doors/beach_oak_door_top_m"))
                .addTextureM(modRes("item/oak_door/beach_oak_door"), EveryCompat.res("item/ch/doors/beach_oak_door_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .noDrops()
                .build();
        this.addEntry(beachDoor);

        boardedDoor = SimpleEntrySet.builder(WoodType.class, "door", "boarded",
                        getModBlock("boarded_oak_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.log).noOcclusion(), w.toVanillaOrOak().setType()) {
                        })
                .addTextureM(modRes("block/oak_door/boarded_oak_door_bottom"), EveryCompat.res("block/ch/doors/boarded_oak_door_bottom_m"))
                .addTextureM(modRes("block/oak_door/boarded_oak_door_top"), EveryCompat.res("block/ch/doors/boarded_oak_door_top_m"))
                .addTextureM(modRes("item/oak_door/boarded_oak_door"), EveryCompat.res("item/ch/doors/boarded_oak_door_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .createPaletteFromPlanks(this::darkPalette)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .noDrops()
                .build();
        this.addEntry(boardedDoor);

        dualPaneledDoor = SimpleEntrySet.builder(WoodType.class, "door", "dual_paneled",
                        getModBlock("dual_paneled_oak_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.log).noOcclusion(), w.toVanillaOrOak().setType()) {
                        })
                .addTextureM(modRes("block/oak_door/dual_paneled_oak_door_bottom"), EveryCompat.res("block/ch/doors/dual_paneled_oak_door_bottom_m"))
                .addTextureM(modRes("block/oak_door/dual_paneled_oak_door_top"), EveryCompat.res("block/ch/doors/dual_paneled_oak_door_top_m"))
                .addTextureM(modRes("item/oak_door/dual_paneled_oak_door"), EveryCompat.res("item/ch/doors/dual_paneled_oak_door_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .createPaletteFromPlanks(this::darkPalette)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .noDrops()
                .build();
        this.addEntry(dualPaneledDoor);

        fortifiedDoor = SimpleEntrySet.builder(WoodType.class, "door", "fortified",
                        getModBlock("fortified_oak_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.log).noOcclusion(), w.toVanillaOrOak().setType()) {
                        })
                .addTextureM(modRes("block/oak_door/fortified_oak_door_bottom"), EveryCompat.res("block/ch/doors/fortified_oak_door_bottom_m"))
                .addTextureM(modRes("block/oak_door/fortified_oak_door_top"), EveryCompat.res("block/ch/doors/fortified_oak_door_top_m"))
                .addTextureM(modRes("item/oak_door/fortified_oak_door"), EveryCompat.res("item/ch/doors/fortified_oak_door_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .createPaletteFromPlanks(this::darkPalette)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .noDrops()
                .build();
        this.addEntry(fortifiedDoor);

        gatedDoor = SimpleEntrySet.builder(WoodType.class, "door", "gated",
                        getModBlock("gated_oak_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.log).noOcclusion(), w.toVanillaOrOak().setType()) {
                        })
                .addTextureM(modRes("block/oak_door/gated_oak_door_bottom"), EveryCompat.res("block/ch/doors/gated_oak_door_bottom_m"))
                .addTextureM(modRes("block/oak_door/gated_oak_door_top"), EveryCompat.res("block/ch/doors/gated_oak_door_top_m"))
                .addTextureM(modRes("item/oak_door/gated_oak_door"), EveryCompat.res("item/ch/doors/gated_oak_door_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .createPaletteFromPlanks(this::darkPalette)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .noDrops()
                .build();
        this.addEntry(gatedDoor);

        glassDoor = SimpleEntrySet.builder(WoodType.class, "door", "glass",
                        getModBlock("glass_oak_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.log).noOcclusion(), w.toVanillaOrOak().setType()) {
                        })
                .addTextureM(modRes("block/oak_door/glass_oak_door_bottom"), EveryCompat.res("block/ch/doors/glass_oak_door_bottom_m"))
                .addTextureM(modRes("block/oak_door/glass_oak_door_top"), EveryCompat.res("block/ch/doors/glass_oak_door_top_m"))
                .addTextureM(modRes("item/oak_door/glass_oak_door"), EveryCompat.res("item/ch/doors/glass_oak_door_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .noDrops()
                .build();
        this.addEntry(glassDoor);

        heavyDoor = SimpleEntrySet.builder(WoodType.class, "door", "heavy",
                        getModBlock("heavy_oak_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.log).noOcclusion(), w.toVanillaOrOak().setType()) {
                        })
                .addTextureM(modRes("block/oak_door/heavy_oak_door_bottom"), EveryCompat.res("block/ch/doors/heavy_oak_door_bottom_m"))
                .addTextureM(modRes("block/oak_door/heavy_oak_door_top"), EveryCompat.res("block/ch/doors/heavy_oak_door_top_m"))
                .addTextureM(modRes("item/oak_door/heavy_oak_door"), EveryCompat.res("item/ch/doors/heavy_oak_door_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .createPaletteFromPlanks(this::darkPalette)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .noDrops()
                .build();
        this.addEntry(heavyDoor);

        overgrownDoor = SimpleEntrySet.builder(WoodType.class, "door", "overgrown",
                        getModBlock("overgrown_oak_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.log).noOcclusion(), w.toVanillaOrOak().setType()) {
                })
                .addTextureM(modRes("block/oak_door/overgrown_oak_door_bottom"), EveryCompat.res("block/ch/doors/overgrown_oak_door_bottom_m"))
                .addTextureM(modRes("block/oak_door/overgrown_oak_door_top"), EveryCompat.res("block/ch/doors/overgrown_oak_door_top_m"))
                .addTextureM(modRes("item/oak_door/overgrown_oak_door"), EveryCompat.res("item/ch/doors/overgrown_oak_door_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .noDrops()
                .build();
        this.addEntry(overgrownDoor);

        paneledDoor = SimpleEntrySet.builder(WoodType.class, "door", "paneled",
                        getModBlock("paneled_oak_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.log).noOcclusion(), w.toVanillaOrOak().setType()) {
                        })
                .addTextureM(modRes("block/oak_door/paneled_oak_door_bottom"), EveryCompat.res("block/ch/doors/paneled_oak_door_bottom_m"))
                .addTextureM(modRes("block/oak_door/paneled_oak_door_top"), EveryCompat.res("block/ch/doors/paneled_oak_door_top_m"))
                .addTextureM(modRes("item/oak_door/paneled_oak_door"), EveryCompat.res("item/ch/doors/paneled_oak_door_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .createPaletteFromPlanks(this::darkPalette)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .noDrops()
                .build();
        this.addEntry(paneledDoor);

        paperDoor = SimpleEntrySet.builder(WoodType.class, "door", "paper",
                        getModBlock("paper_oak_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.log).noOcclusion(), w.toVanillaOrOak().setType()) {
                })
                .addTextureM(modRes("block/oak_door/paper_oak_door_bottom"), EveryCompat.res("block/ch/doors/paper_oak_door_bottom_m"))
                .addTextureM(modRes("block/oak_door/paper_oak_door_top"), EveryCompat.res("block/ch/doors/paper_oak_door_top_m"))
                .addTextureM(modRes("item/oak_door/paper_oak_door"), EveryCompat.res("item/ch/doors/paper_oak_door_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .createPaletteFromPlanks(this::darkPalette)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .noDrops()
                .build();
        this.addEntry(paperDoor);

        pressedDoor = SimpleEntrySet.builder(WoodType.class, "door", "pressed",
                        getModBlock("pressed_oak_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.log).noOcclusion(), w.toVanillaOrOak().setType()) {
                        })
                .addTextureM(modRes("block/oak_door/pressed_oak_door_bottom"), EveryCompat.res("block/ch/doors/pressed_oak_door_bottom_m"))
                .addTextureM(modRes("block/oak_door/pressed_oak_door_top"), EveryCompat.res("block/ch/doors/pressed_oak_door_top_m"))
                .addTextureM(modRes("item/oak_door/pressed_oak_door"), EveryCompat.res("item/ch/doors/pressed_oak_door_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .createPaletteFromPlanks(this::darkPalette)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .noDrops()
                .build();
        this.addEntry(pressedDoor);

        screenDoor = SimpleEntrySet.builder(WoodType.class, "door", "screen",
                        getModBlock("screen_oak_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.log).noOcclusion(), w.toVanillaOrOak().setType()) {
                        })
                .addTextureM(modRes("block/oak_door/screen_oak_door_bottom"), EveryCompat.res("block/ch/doors/screen_oak_door_bottom_m"))
                .addTextureM(modRes("block/oak_door/screen_oak_door_top"), EveryCompat.res("block/ch/doors/screen_oak_door_top_m"))
                .addTextureM(modRes("item/oak_door/screen_oak_door"), EveryCompat.res("item/ch/doors/screen_oak_door_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .createPaletteFromPlanks(this::darkPalette)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .noDrops()
                .build();
        this.addEntry(screenDoor);

        secretDoor = SimpleEntrySet.builder(WoodType.class, "door", "secret",
                        getModBlock("secret_oak_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.log).noOcclusion(), w.toVanillaOrOak().setType()) {
                })
                .addTextureM(modRes("block/oak_door/secret_oak_door_bottom"), EveryCompat.res("block/ch/doors/secret_oak_door_bottom_m"))
                .addTextureM(modRes("block/oak_door/secret_oak_door_top"), EveryCompat.res("block/ch/doors/secret_oak_door_top_m"))
                .addTextureM(modRes("item/oak_door/secret_oak_door"), EveryCompat.res("item/ch/doors/secret_oak_door_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .createPaletteFromPlanks(this::darkPalette)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .noDrops()
                .build();
        this.addEntry(secretDoor);

        shackDoor = SimpleEntrySet.builder(WoodType.class, "door", "shack",
                        getModBlock("shack_oak_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.log).noOcclusion(), w.toVanillaOrOak().setType()) {
                })
                .addTexture(modRes("block/oak_door/shack_oak_door_bottom"))
                .addTextureM(modRes("block/oak_door/shack_oak_door_top"), EveryCompat.res("block/ch/doors/shack_oak_door_top_m"))
                .addTextureM(modRes("item/oak_door/shack_oak_door"), EveryCompat.res("item/ch/doors/shack_oak_door_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .createPaletteFromPlanks(this::darkPalette)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .noDrops()
                .build();
        this.addEntry(shackDoor);

        slidingDoor = SimpleEntrySet.builder(WoodType.class, "door", "sliding",
                        getModBlock("sliding_oak_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.log).noOcclusion(), w.toVanillaOrOak().setType()) {
                })
                .addTextureM(modRes("block/oak_door/sliding_oak_door_bottom"), EveryCompat.res("block/ch/doors/sliding_oak_door_bottom_m"))
                .addTextureM(modRes("block/oak_door/sliding_oak_door_top"), EveryCompat.res("block/ch/doors/sliding_oak_door_top_m"))
                .addTextureM(modRes("item/oak_door/sliding_oak_door"), EveryCompat.res("item/ch/doors/sliding_oak_door_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .createPaletteFromPlanks(this::darkPalette)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .noDrops()
                .build();
        this.addEntry(slidingDoor);

        supportedDoor = SimpleEntrySet.builder(WoodType.class, "door", "supported",
                        getModBlock("supported_oak_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.log).noOcclusion(), w.toVanillaOrOak().setType()) {
                        })
                .addTextureM(modRes("block/oak_door/supported_oak_door_bottom"), EveryCompat.res("block/ch/doors/supported_oak_door_bottom_m"))
                .addTextureM(modRes("block/oak_door/supported_oak_door_top"), EveryCompat.res("block/ch/doors/supported_oak_door_top_m"))
                .addTextureM(modRes("item/oak_door/supported_oak_door"), EveryCompat.res("item/ch/doors/supported_oak_door_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .createPaletteFromPlanks(this::darkerPalette)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .noDrops()
                .build();
        this.addEntry(supportedDoor);

        tileWindowedDoor = SimpleEntrySet.builder(WoodType.class, "door", "tile_windowed",
                        getModBlock("tile_windowed_oak_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.log).noOcclusion(), w.toVanillaOrOak().setType()) {
                        })
                .addTextureM(EveryCompat.res("block/oak_door/tile_windowed_oak_door_bottom"), EveryCompat.res("block/ch/doors/tile_windowed_oak_door_bottom_m"))
                .addTextureM(EveryCompat.res("block/oak_door/tile_windowed_oak_door_top"), EveryCompat.res("block/ch/doors/tile_windowed_oak_door_top_m"))
                .addTextureM(modRes("item/oak_door/tile_windowed_oak_door"), EveryCompat.res("item/ch/doors/tile_windowed_oak_door_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .createPaletteFromPlanks(this::darkPalette)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .noDrops()
                .build();
        this.addEntry(tileWindowedDoor);

        tiledDoor = SimpleEntrySet.builder(WoodType.class, "door", "tiled",
                        getModBlock("tiled_oak_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.log).noOcclusion(), w.toVanillaOrOak().setType()) {
                        })
                .addTextureM(modRes("block/oak_door/tiled_oak_door_bottom"), EveryCompat.res("block/ch/doors/tiled_oak_door_bottom_m"))
                .addTextureM(modRes("block/oak_door/tiled_oak_door_top"), EveryCompat.res("block/ch/doors/tiled_oak_door_top_m"))
                .addTextureM(modRes("item/oak_door/tiled_oak_door"), EveryCompat.res("item/ch/doors/tiled_oak_door_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .createPaletteFromPlanks(this::darkPalette)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .noDrops()
                .build();
        this.addEntry(tiledDoor);

        windowedDoor = SimpleEntrySet.builder(WoodType.class, "door", "windowed",
                        getModBlock("windowed_oak_door"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DoorBlock(Utils.copyPropertySafe(w.log).noOcclusion(), w.toVanillaOrOak().setType()) {
                })
                .addTextureM(modRes("block/oak_door/windowed_oak_door_bottom"), EveryCompat.res("block/ch/doors/windowed_oak_door_bottom_m"))
                .addTextureM(modRes("block/oak_door/windowed_oak_door_top"), EveryCompat.res("block/ch/doors/windowed_oak_door_top_m"))
                .addTextureM(modRes("item/oak_door/windowed_oak_door"), EveryCompat.res("item/ch/doors/windowed_oak_door_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .createPaletteFromPlanks(this::darkPalette)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .noDrops()
                .build();
        this.addEntry(windowedDoor);

        //TYPE: trapdoor
        airyTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "airy",
                        getModBlock("airy_oak_trapdoor"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrapDoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()) {
                        })
                .addTextureM(modRes("block/oak_trapdoor/airy_oak_trapdoor"), EveryCompat.res("block/ch/trapdoors/airy_oak_trapdoor_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .build();
        this.addEntry(airyTrapdoor);

        barredTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "barred",
                        getModBlock("barred_oak_trapdoor"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrapDoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()) {
                        })
                .addTexture(modRes("block/oak_trapdoor/barred_oak_trapdoor"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .build();
        this.addEntry(barredTrapdoor);

        checkeredTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "checkered",
                        getModBlock("checkered_oak_trapdoor"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrapDoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()) {
                        })
                .addTexture(modRes("block/oak_trapdoor/checkered_oak_trapdoor"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .build();
        this.addEntry(checkeredTrapdoor);

        classicTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "classic",
                        getModBlock("classic_spruce_trapdoor"), () -> WoodTypeRegistry.getValue(new ResourceLocation("spruce")),
                        w -> new TrapDoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()) {
                        })
                .addTexture(modRes("block/spruce_trapdoor/classic_spruce_trapdoor"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .build();
        this.addEntry(classicTrapdoor);

        classicWindowedTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "classic_windowed",
                        getModBlock("classic_windowed_oak_trapdoor"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrapDoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()) {
                        })
                .addTextureM(modRes("block/oak_trapdoor/classic_windowed_oak_trapdoor"), EveryCompat.res("block/ch/trapdoors/classic_windowed_oak_trapdoor_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .build();
        this.addEntry(classicWindowedTrapdoor);

        cobwebTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "cobweb",
                        getModBlock("cobweb_oak_trapdoor"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrapDoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()) {
                        })
                .addTextureM(modRes("block/oak_trapdoor/cobweb_oak_trapdoor"), EveryCompat.res("block/ch/trapdoors/cobweb_oak_trapdoor_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .build();
        this.addEntry(cobwebTrapdoor);

        distortedTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "distorted",
                        getModBlock("distorted_oak_trapdoor"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrapDoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()) {
                        })
                .addTexture(modRes("block/oak_trapdoor/distorted_oak_trapdoor"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .build();
        this.addEntry(distortedTrapdoor);

        fancyTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "fancy",
                        getModBlock("fancy_oak_trapdoor"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrapDoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()) {
                        })
                .addTextureM(modRes("block/oak_trapdoor/fancy_oak_trapdoor"), EveryCompat.res("block/ch/trapdoors/fancy_oak_trapdoor_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .build();
        this.addEntry(fancyTrapdoor);

        goldenBarredTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "golden_barred",
                        getModBlock("golden_barred_oak_trapdoor"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrapDoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()) {
                        })
                .addTextureM(modRes("block/oak_trapdoor/golden_barred_oak_trapdoor"), EveryCompat.res("block/ch/trapdoors/golden_barred_oak_trapdoor_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .createPaletteFromPlanks(this::darkPalette)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .build();
        this.addEntry(goldenBarredTrapdoor);

        heavyTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "heavy",
                        getModBlock("heavy_oak_trapdoor"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrapDoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()) {
                        })
                .addTextureM(modRes("block/oak_trapdoor/heavy_oak_trapdoor"), EveryCompat.res("block/ch/trapdoors/heavy_oak_trapdoor_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .build();
        this.addEntry(heavyTrapdoor);

        ironBarredTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "iron_barred",
                        getModBlock("iron_barred_oak_trapdoor"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrapDoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()) {
                })
                .addTextureM(modRes("block/oak_trapdoor/iron_barred_oak_trapdoor"), EveryCompat.res("block/ch/trapdoors/iron_barred_oak_trapdoor_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .createPaletteFromPlanks(this::darkPalette)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .build();
        this.addEntry(ironBarredTrapdoor);

        leafyTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "leafy",
                        getModBlock("leafy_oak_trapdoor"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrapDoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()) {
                        })
                .addTextureM(modRes("block/oak_trapdoor/leafy_oak_trapdoor"), EveryCompat.res("block/ch/trapdoors/leafy_oak_trapdoor_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .build();
        this.addEntry(leafyTrapdoor);

        meshedTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "meshed",
                        getModBlock("meshed_oak_trapdoor"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrapDoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()) {
                })
                .addTexture(modRes("block/oak_trapdoor/meshed_oak_trapdoor"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .build();
        this.addEntry(meshedTrapdoor);

        overgrownTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "overgrown",
                        getModBlock("overgrown_oak_trapdoor"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrapDoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()) {
                })
                .addTextureM(modRes("block/oak_trapdoor/overgrown_oak_trapdoor"), EveryCompat.res("block/ch/trapdoors/overgrown_oak_trapdoor_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .build();
        this.addEntry(overgrownTrapdoor);

        pointlessTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "pointless",
                        getModBlock("pointless_oak_trapdoor"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrapDoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()) {
                })
                .addTexture(modRes("block/oak_trapdoor/pointless_oak_trapdoor"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .build();
        this.addEntry(pointlessTrapdoor);

        slottedTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "slotted",
                        getModBlock("slotted_oak_trapdoor"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrapDoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()) {
                })
                .addTexture(modRes("block/oak_trapdoor/slotted_oak_trapdoor"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .build();
        this.addEntry(slottedTrapdoor);

        solidTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "solid",
                        getModBlock("solid_oak_trapdoor"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrapDoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()) {
                        })
                .addTexture(modRes("block/oak_trapdoor/solid_oak_trapdoor"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .build();
        this.addEntry(solidTrapdoor);

        suspiciousTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "suspicious",
                        getModBlock("suspicious_oak_trapdoor"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrapDoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()) {
                })
                .addTexture(modRes("block/oak_trapdoor/suspicious_oak_trapdoor"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .build();
        this.addEntry(suspiciousTrapdoor);

        twistedTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "twisted",
                        getModBlock("twisted_oak_trapdoor"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrapDoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()) {
                        })
                .addTextureM(modRes("block/oak_trapdoor/twisted_oak_trapdoor"), EveryCompat.res("block/ch/trapdoors/twisted_oak_trapdoor_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .build();
        this.addEntry(twistedTrapdoor);

        vinedTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "vined",
                        getModBlock("vined_oak_trapdoor"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrapDoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()) {
                })
                .addTextureM(modRes("block/oak_trapdoor/vined_oak_trapdoor"), EveryCompat.res("block/ch/trapdoors/vined_oak_trapdoor_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .build();
        this.addEntry(vinedTrapdoor);

        wartedTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "warted",
                        getModBlock("warted_oak_trapdoor"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrapDoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()) {
                })
                .addTextureM(modRes("block/oak_trapdoor/warted_oak_trapdoor"), EveryCompat.res("block/ch/trapdoors/warted_oak_trapdoor_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .build();
        this.addEntry(wartedTrapdoor);

        windowedTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "windowed",
                        getModBlock("windowed_oak_trapdoor"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrapDoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()) {
                })
                .addTextureM(modRes("block/oak_trapdoor/windowed_oak_trapdoor"), EveryCompat.res("block/ch/trapdoors/windowed_oak_trapdoor_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .build();
        this.addEntry(windowedTrapdoor);

        wovenTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "woven",
                        getModBlock("woven_oak_trapdoor"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TrapDoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType()) {
                })
                .addTexture(modRes("block/oak_trapdoor/woven_oak_trapdoor"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .build();
        this.addEntry(wovenTrapdoor);

        wallTorch = SimpleEntrySet.builder(WoodType.class, "wall_torch",
                        getModBlock("spruce_wall_torch"), () -> WoodTypeRegistry.getValue(new ResourceLocation("spruce")),
                        w -> new WallTorchBlock(Utils.copyPropertySafe(w.planks).noCollission().instabreak().lightLevel(l -> 14), ParticleTypes.FLAME))
                .addTextureM(EveryCompat.res("block/torch/spruce_torch"), EveryCompat.res("block/ch/spruce_torch_m"))
                .addTag(modRes("wall_torch"), Registries.BLOCK)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .noItem()
                .build();
        this.addEntry(wallTorch);

        torch = SimpleEntrySet.builder(WoodType.class, "torch",
                        getModBlock("spruce_torch"), () -> WoodTypeRegistry.getValue(new ResourceLocation("spruce")),
                        w -> new TorchBlock( Utils.copyPropertySafe(w.planks).noCollission().instabreak().lightLevel(l -> 14), ParticleTypes.FLAME))
                .addTextureM(EveryCompat.res("block/torch/spruce_torch"), EveryCompat.res("block/ch/spruce_torch_m"))
                .addCustomItem((w, b, p) -> new StandingAndWallBlockItem(b, wallTorch.blocks.get(w), p, Direction.DOWN))
                .addTag(BlockTags.WALL_POST_OVERRIDE, Registries.BLOCK)
                .addTag(modRes("torch"), Registries.BLOCK)
                .addTag(modRes("torch"), Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .build();
        this.addEntry(torch);

        //TYPE: glass
        circleGlass = SimpleEntrySet.builder(WoodType.class, "glass", "circle",
                        getModBlock("circle_oak_glass"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new GlassBlock(Utils.copyPropertySafe(w.planks).strength(0.3F).sound(SoundType.GLASS)
                                .noOcclusion().isValidSpawn((s, l, ps, t) -> false).isRedstoneConductor((s, l, ps) -> false)
                                .isSuffocating((s, l, ps) -> false).isViewBlocking((s, l, ps) -> false)))
                .addTextureM(modRes("block/glass/circle_oak_glass"), EveryCompat.res("block/ch/glass/circle_oak_glass_m"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.IMPERMEABLE, Registries.BLOCK)
                .addTag(modRes("glass"), Registries.BLOCK)
                .addTag(modRes("glass"), Registries.ITEM)
                .setRenderType(RenderLayer.TRANSLUCENT)
                .setTabKey(tab)
                .build();
        this.addEntry(circleGlass);

        barredGlass = SimpleEntrySet.builder(WoodType.class, "bared_glass",
                        getModBlock("oak_bared_glass"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new GlassBlock(Utils.copyPropertySafe(w.planks).strength(0.3F).sound(SoundType.GLASS)
                                .noOcclusion().isValidSpawn((s, l, ps, t) -> false).isRedstoneConductor((s, l, ps) -> false)
                                .isSuffocating((s, l, ps) -> false).isViewBlocking((s, l, ps) -> false)))
                .addTextureM(modRes("block/glass/oak_bared_glass"), EveryCompat.res("block/ch/glass/oak_bared_glass_m"))
                .addTexture(TextureInfo.of(modRes("block/glass/ctm/oak_bared_glass_ctm/0")).copyTexture())
                .addTextureM(modRes("block/glass/ctm/oak_bared_glass_ctm/1"), EveryCompat.res("block/ch/glass/ctm/oak_bared_glass/1_mask"))
                .addTextureM(modRes("block/glass/ctm/oak_bared_glass_ctm/2"), EveryCompat.res("block/ch/glass/ctm/oak_bared_glass/2_mask"))
                .addTextureM(modRes("block/glass/ctm/oak_bared_glass_ctm/3"), EveryCompat.res("block/ch/glass/ctm/oak_bared_glass/3_mask"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.IMPERMEABLE, Registries.BLOCK)
                .addTag(modRes("glass"), Registries.BLOCK)
                .addTag(modRes("glass"), Registries.ITEM)
                .setRenderType(RenderLayer.TRANSLUCENT)
                .setTabKey(tab)
                .build();
        this.addEntry(barredGlass);

        borderedGlass = SimpleEntrySet.builder(WoodType.class, "bordered_glass",
                        getModBlock("oak_bordered_glass"), () -> WoodTypeRegistry.OAK_TYPE,
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
                .setRenderType(RenderLayer.TRANSLUCENT)
                .setTabKey(tab)
                .build();
        this.addEntry(borderedGlass);

        diamondBorderedGlass = SimpleEntrySet.builder(WoodType.class, "diamond_bordered_glass",
                        getModBlock("oak_diamond_bordered_glass"), () -> WoodTypeRegistry.OAK_TYPE,
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
                .setRenderType(RenderLayer.TRANSLUCENT)
                .setTabKey(tab)
                .build();
        this.addEntry(diamondBorderedGlass);

        horizontalLinedGlass = SimpleEntrySet.builder(WoodType.class, "horizontal_lined_glass",
                        getModBlock("oak_horizontal_lined_glass"), () -> WoodTypeRegistry.OAK_TYPE,
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
                .setRenderType(RenderLayer.TRANSLUCENT)
                .setTabKey(tab)
                .build();
        this.addEntry(horizontalLinedGlass);

        largeDiamondGlass = SimpleEntrySet.builder(WoodType.class, "large_diamond_glass",
                        getModBlock("oak_large_diamond_glass"), () -> WoodTypeRegistry.OAK_TYPE,
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
                .setRenderType(RenderLayer.TRANSLUCENT)
                .setTabKey(tab)
                .build();
        this.addEntry(largeDiamondGlass);

        lineBarredGlass = SimpleEntrySet.builder(WoodType.class, "line_bared_glass",
                        getModBlock("oak_line_bared_glass"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new GlassBlock(Utils.copyPropertySafe(w.planks).strength(0.3F).sound(SoundType.GLASS)
                                .noOcclusion().isValidSpawn((s, l, ps, t) -> false).isRedstoneConductor((s, l, ps) -> false)
                                .isSuffocating((s, l, ps) -> false).isViewBlocking((s, l, ps) -> false)))
                .addTextureM(modRes("block/glass/oak_line_bared_glass"), EveryCompat.res("block/ch/glass/oak_line_bared_glass_m"))
                .addTexture(TextureInfo.of(modRes("block/glass/ctm/oak_line_bared_glass_ctm/0")).copyTexture())
                .addTextureM(modRes("block/glass/ctm/oak_line_bared_glass_ctm/1"), EveryCompat.res("block/ch/glass/ctm/oak_line_bared_glass/1_mask"))
                .addTextureM(modRes("block/glass/ctm/oak_line_bared_glass_ctm/2"), EveryCompat.res("block/ch/glass/ctm/oak_line_bared_glass/2_mask"))
                .addTextureM(modRes("block/glass/ctm/oak_line_bared_glass_ctm/3"), EveryCompat.res("block/ch/glass/ctm/oak_line_bared_glass/3_mask"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.IMPERMEABLE, Registries.BLOCK)
                .addTag(modRes("glass"), Registries.BLOCK)
                .addTag(modRes("glass"), Registries.ITEM)
                .setRenderType(RenderLayer.TRANSLUCENT)
                .setTabKey(tab)
                .build();
        this.addEntry(lineBarredGlass);

        ornateBarredGlass = SimpleEntrySet.builder(WoodType.class, "ornate_bared_glass",
                        getModBlock("oak_ornate_bared_glass"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new GlassBlock(Utils.copyPropertySafe(w.planks).strength(0.3F).sound(SoundType.GLASS)
                                .noOcclusion().isValidSpawn((s, l, ps, t) -> false).isRedstoneConductor((s, l, ps) -> false)
                                .isSuffocating((s, l, ps) -> false).isViewBlocking((s, l, ps) -> false)))
                .addTextureM(modRes("block/glass/oak_ornate_bared_glass"), EveryCompat.res("block/ch/glass/oak_ornate_bared_glass_m"))
                .addTexture(TextureInfo.of(modRes("block/glass/ctm/oak_ornate_bared_glass_ctm/0")).copyTexture())
                .addTextureM(modRes("block/glass/ctm/oak_ornate_bared_glass_ctm/1"), EveryCompat.res("block/ch/glass/ctm/oak_ornate_bared_glass/1_mask"))
                .addTextureM(modRes("block/glass/ctm/oak_ornate_bared_glass_ctm/2"), EveryCompat.res("block/ch/glass/ctm/oak_ornate_bared_glass/2_mask"))
                .addTextureM(modRes("block/glass/ctm/oak_ornate_bared_glass_ctm/3"), EveryCompat.res("block/ch/glass/ctm/oak_ornate_bared_glass/3_mask"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.IMPERMEABLE, Registries.BLOCK)
                .addTag(modRes("glass"), Registries.BLOCK)
                .addTag(modRes("glass"), Registries.ITEM)
                .setRenderType(RenderLayer.TRANSLUCENT)
                .setTabKey(tab)
                .build();
        this.addEntry(ornateBarredGlass);

        snowflakeGlass = SimpleEntrySet.builder(WoodType.class, "snowflake_glass",
                        getModBlock("oak_snowflake_glass"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new GlassBlock(Utils.copyPropertySafe(w.planks).strength(0.3F).sound(SoundType.GLASS)
                                .noOcclusion().isValidSpawn((s, l, ps, t) -> false).isRedstoneConductor((s, l, ps) -> false)
                                .isSuffocating((s, l, ps) -> false).isViewBlocking((s, l, ps) -> false)))
                .addTextureM(modRes("block/glass/oak_snowflake_glass"), EveryCompat.res("block/ch/glass/oak_snowflake_glass_m"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(BlockTags.IMPERMEABLE, Registries.BLOCK)
                .addTag(modRes("glass"), Registries.BLOCK)
                .addTag(modRes("glass"), Registries.ITEM)
                .setRenderType(RenderLayer.TRANSLUCENT)
                .setTabKey(tab)
                .build();
        this.addEntry(snowflakeGlass);

        wovenGlass = SimpleEntrySet.builder(WoodType.class, "woven_glass",
                        getModBlock("oak_woven_glass"), () -> WoodTypeRegistry.OAK_TYPE,
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
                .setRenderType(RenderLayer.TRANSLUCENT)
                .setTabKey(tab)
                .build();
        this.addEntry(wovenGlass);

        squareGlass = SimpleEntrySet.builder(WoodType.class, "glass", "square",
                        getModBlock("square_oak_glass"), () -> WoodTypeRegistry.OAK_TYPE,
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
                .setRenderType(RenderLayer.TRANSLUCENT)
                .setTabKey(tab)
                .build();
        this.addEntry(squareGlass);

        //TYPE glass_pane
        circleGlassPane = SimpleEntrySet.builder(WoodType.class, "glass_pane", "circle",
                        getModBlock("circle_oak_glass_pane"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new IronBarsBlock(Utils.copyPropertySafe(w.planks).strength(0.3F).sound(SoundType.GLASS).noOcclusion()))
                .addModelTransform(m -> m.replaceString("minecraft:block/glass_pane_top", "chipped:block/glass_pane/circle_oak_glass_pane_top"))
                // using the same glass texture
                .addTexture(modRes("block/glass_pane/circle_oak_glass_pane_top"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("glass_pane"), Registries.BLOCK)
                .addTag(modRes("glass_pane"), Registries.ITEM)
                .setRenderType(RenderLayer.TRANSLUCENT)
                .setTabKey(tab)
                .build();
        this.addEntry(circleGlassPane);

        barredGlassPane = SimpleEntrySet.builder(WoodType.class, "bared_glass_pane",
                        getModBlock("oak_bared_glass_pane"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new IronBarsBlock(Utils.copyPropertySafe(w.planks).strength(0.3F).sound(SoundType.GLASS).noOcclusion()))
                .addModelTransform(m -> m.replaceString("minecraft:block/glass_pane_top", "chipped:block/glass_pane/oak_bared_glass_pane_top"))
                // using the same glass texture
                .addTexture(modRes("block/glass_pane/oak_bared_glass_pane_top"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("glass_pane"), Registries.BLOCK)
                .addTag(modRes("glass_pane"), Registries.ITEM)
                .setRenderType(RenderLayer.TRANSLUCENT)
                .setTabKey(tab)
                .build();
        this.addEntry(barredGlassPane);

        borderedGlassPane = SimpleEntrySet.builder(WoodType.class, "bordered_glass_pane",
                        getModBlock("oak_bordered_glass_pane"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new IronBarsBlock(Utils.copyPropertySafe(w.planks).strength(0.3F).sound(SoundType.GLASS).noOcclusion()))
                .addModelTransform(m -> m.replaceString("minecraft:block/glass_pane_top", "chipped:block/glass_pane/oak_bordered_glass_pane_top"))
                // using the same glass texture
                .addTexture(modRes("block/glass_pane/oak_bordered_glass_pane_top"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("glass_pane"), Registries.BLOCK)
                .addTag(modRes("glass_pane"), Registries.ITEM)
                .setRenderType(RenderLayer.TRANSLUCENT)
                .setTabKey(tab)
                .build();
        this.addEntry(borderedGlassPane);

        diamondBorderedGlassPane = SimpleEntrySet.builder(WoodType.class, "diamond_bordered_glass_pane",
                        getModBlock("oak_diamond_bordered_glass_pane"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new IronBarsBlock(Utils.copyPropertySafe(w.planks).strength(0.3F).sound(SoundType.GLASS).noOcclusion()))
                .addModelTransform(m -> m.replaceString("minecraft:block/glass_pane_top", "chipped:block/glass_pane/oak_diamond_bordered_glass_pane_top"))
                // using the same glass texture
                .addTexture(modRes("block/glass_pane/oak_diamond_bordered_glass_pane_top"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("glass_pane"), Registries.BLOCK)
                .addTag(modRes("glass_pane"), Registries.ITEM)
                .setRenderType(RenderLayer.TRANSLUCENT)
                .setTabKey(tab)
                .build();
        this.addEntry(diamondBorderedGlassPane);

        horizontalLinedGlassPane = SimpleEntrySet.builder(WoodType.class, "horizontal_lined_glass_pane",
                        getModBlock("oak_horizontal_lined_glass_pane"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new IronBarsBlock(Utils.copyPropertySafe(w.planks).strength(0.3F).sound(SoundType.GLASS).noOcclusion()))
                .addModelTransform(m -> m.replaceString("minecraft:block/glass_pane_top", "chipped:block/glass_pane/oak_horizontal_lined_glass_pane_top"))
                // using the same glass texture
                .addTexture(modRes("block/glass_pane/oak_horizontal_lined_glass_pane_top"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("glass_pane"), Registries.BLOCK)
                .addTag(modRes("glass_pane"), Registries.ITEM)
                .setRenderType(RenderLayer.TRANSLUCENT)
                .setTabKey(tab)
                .build();
        this.addEntry(horizontalLinedGlassPane);

        largeDiamondGlassPane = SimpleEntrySet.builder(WoodType.class, "large_diamond_glass_pane",
                        getModBlock("oak_large_diamond_glass_pane"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new IronBarsBlock(Utils.copyPropertySafe(w.planks).strength(0.3F).sound(SoundType.GLASS).noOcclusion()))
                .addModelTransform(m -> m.replaceString("minecraft:block/glass_pane_top", "chipped:block/glass_pane/oak_large_diamond_glass_pane_top"))
                // using the same glass texture
                .addTexture(modRes("block/glass_pane/oak_large_diamond_glass_pane_top"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("glass_pane"), Registries.BLOCK)
                .addTag(modRes("glass_pane"), Registries.ITEM)
                .setRenderType(RenderLayer.TRANSLUCENT)
                .setTabKey(tab)
                .build();
        this.addEntry(largeDiamondGlassPane);

        lineBarredGlassPane = SimpleEntrySet.builder(WoodType.class, "line_bared_glass_pane",
                        getModBlock("oak_line_bared_glass_pane"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new IronBarsBlock(Utils.copyPropertySafe(w.planks).strength(0.3F).sound(SoundType.GLASS).noOcclusion()))
                .addModelTransform(m -> m.replaceString("minecraft:block/glass_pane_top", "chipped:block/glass_pane/oak_line_bared_glass_pane_top"))
                // using the same glass texture
                .addTexture(modRes("block/glass_pane/oak_line_bared_glass_pane_top"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("glass_pane"), Registries.BLOCK)
                .addTag(modRes("glass_pane"), Registries.ITEM)
                .setRenderType(RenderLayer.TRANSLUCENT)
                .setTabKey(tab)
                .build();
        this.addEntry(lineBarredGlassPane);

        ornateBarredGlassPane = SimpleEntrySet.builder(WoodType.class, "ornate_bared_glass_pane",
                        getModBlock("oak_ornate_bared_glass_pane"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new IronBarsBlock(Utils.copyPropertySafe(w.planks).strength(0.3F).sound(SoundType.GLASS).noOcclusion()))
                .addModelTransform(m -> m.replaceString("minecraft:block/glass_pane_top", "chipped:block/glass_pane/oak_ornate_bared_glass_pane_top"))
                // using the same glass texture
                .addTexture(modRes("block/glass_pane/oak_ornate_bared_glass_pane_top"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("glass_pane"), Registries.BLOCK)
                .addTag(modRes("glass_pane"), Registries.ITEM)
                .setRenderType(RenderLayer.TRANSLUCENT)
                .setTabKey(tab)
                .build();
        this.addEntry(ornateBarredGlassPane);

        snowflakeGlassPane = SimpleEntrySet.builder(WoodType.class, "snowflake_glass_pane",
                        getModBlock("oak_snowflake_glass_pane"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new IronBarsBlock(Utils.copyPropertySafe(w.planks).strength(0.3F).sound(SoundType.GLASS).noOcclusion()))
                .addModelTransform(m -> m.replaceString("minecraft:block/glass_pane_top", "chipped:block/glass_pane/oak_snowflake_glass_pane_top"))
                // using the same glass texture
                .addTexture(modRes("block/glass_pane/oak_snowflake_glass_pane_top"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("glass_pane"), Registries.BLOCK)
                .addTag(modRes("glass_pane"), Registries.ITEM)
                .setRenderType(RenderLayer.TRANSLUCENT)
                .setTabKey(tab)
                .build();
        this.addEntry(snowflakeGlassPane);

        wovenGlassPane = SimpleEntrySet.builder(WoodType.class, "woven_glass_pane",
                        getModBlock("oak_woven_glass_pane"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new IronBarsBlock(Utils.copyPropertySafe(w.planks).strength(0.3F).sound(SoundType.GLASS).noOcclusion()))
                .addModelTransform(m -> m.replaceString("minecraft:block/glass_pane_top", "chipped:block/glass_pane/oak_woven_glass_pane_top"))
                // using the same glass texture
                .addTexture(modRes("block/glass_pane/oak_woven_glass_pane_top"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("glass_pane"), Registries.BLOCK)
                .addTag(modRes("glass_pane"), Registries.ITEM)
                .setRenderType(RenderLayer.TRANSLUCENT)
                .setTabKey(tab)
                .build();
        this.addEntry(wovenGlassPane);

        squareGlassPane = SimpleEntrySet.builder(WoodType.class, "glass_pane", "square",
                        getModBlock("square_oak_glass_pane"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new IronBarsBlock(Utils.copyPropertySafe(w.planks).strength(0.3F).sound(SoundType.GLASS).noOcclusion()))
                .addModelTransform(m -> m.replaceString("minecraft:block/glass_pane_top", "chipped:block/glass_pane/square_oak_glass_pane_top"))
                // using the same glass texture
                .addTexture(modRes("block/glass_pane/square_oak_glass_pane_top"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .addTag(modRes("glass_pane"), Registries.BLOCK)
                .addTag(modRes("glass_pane"), Registries.ITEM)
                .setRenderType(RenderLayer.TRANSLUCENT)
                .setTabKey(tab)
                .build();
        this.addEntry(squareGlassPane);

        //TYPE: log
        BundledLog = SimpleEntrySet.builder(WoodType.class, "log", "bundled",
                        getModBlock("bundled_oak_log"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new RotatedPillarBlock(Utils.copyPropertySafe(w.log))
                )
                .addTexture(modRes("block/oak_log/bundled_oak_log"))
                .addTexture(modRes("block/oak_log/bundled_oak_log_top"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(BundledLog);

        CenterCutLog = SimpleEntrySet.builder(WoodType.class, "log", "center_cut",
                        getModBlock("center_cut_oak_log"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new RotatedPillarBlock(Utils.copyPropertySafe(w.log))
                )
                .addTexture(modRes("block/oak_log/center_cut_oak_log"))
                .addTexture(modRes("block/oak_log/center_cut_oak_log_top"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(CenterCutLog);

        DamagedLog = SimpleEntrySet.builder(WoodType.class, "log", "damaged",
                        getModBlock("damaged_oak_log"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new RotatedPillarBlock(Utils.copyPropertySafe(w.log))
                )
                .addTexture(modRes("block/oak_log/damaged_oak_log"))
                .addTexture(modRes("block/oak_log/damaged_oak_log_top"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(DamagedLog);

        EdgeCutLog = SimpleEntrySet.builder(WoodType.class, "log", "edge_cut",
                        getModBlock("edge_cut_oak_log"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new RotatedPillarBlock(Utils.copyPropertySafe(w.log))
                )
                .addTexture(modRes("block/oak_log/edge_cut_oak_log"))
                .addTexture(modRes("block/oak_log/edge_cut_oak_log_top"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(EdgeCutLog);

        FirewoodLog = SimpleEntrySet.builder(WoodType.class, "log", "firewood",
                        getModBlock("firewood_oak_log"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new RotatedPillarBlock(Utils.copyPropertySafe(w.log))
                )
                .addTexture(modRes("block/oak_log/firewood_oak_log"))
                .addTexture(modRes("block/oak_log/firewood_oak_log_top"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(FirewoodLog);

        FloweringLog = SimpleEntrySet.builder(WoodType.class, "log", "flowering",
                        getModBlock("flowering_oak_log"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new RotatedPillarBlock(Utils.copyPropertySafe(w.log))
                )
                .addTextureM(modRes("block/oak_log/flowering_oak_log"),
                        EveryCompat.res("block/ch/logs/flowering_oak_log_m"))
                .addTextureM(modRes("block/oak_log/flowering_oak_log_top"),
                        EveryCompat.res("block/ch/logs/flowering_oak_log_top_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(FloweringLog);

        MixedLog = SimpleEntrySet.builder(WoodType.class, "log", "mixed",
                        getModBlock("mixed_oak_log"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new RotatedPillarBlock(Utils.copyPropertySafe(w.log))
                )
                .addTexture(modRes("block/oak_log/mixed_oak_log"))
                .addTexture(modRes("block/oak_log/mixed_oak_log_top"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(MixedLog);

        NailedLog = SimpleEntrySet.builder(WoodType.class, "log", "nailed",
                        getModBlock("nailed_oak_log"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new RotatedPillarBlock(Utils.copyPropertySafe(w.log))
                )
                .addTextureM(modRes("block/oak_log/nailed_oak_log"),
                        EveryCompat.res("block/ch/logs/nailed_oak_log_m"))
                .addTexture(modRes("block/oak_log/nailed_oak_log_top"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(NailedLog);

        OvergrownLog = SimpleEntrySet.builder(WoodType.class, "log", "overgrown",
                        getModBlock("overgrown_oak_log"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new RotatedPillarBlock(Utils.copyPropertySafe(w.log))
                )
                .addTextureM(modRes("block/oak_log/overgrown_oak_log"),
                        EveryCompat.res("block/ch/logs/overgrown_oak_log_m"))
                .addTextureM(modRes("block/oak_log/overgrown_oak_log_top"),
                        EveryCompat.res("block/ch/logs/overgrown_oak_log_top_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(OvergrownLog);

        PlankedLog = SimpleEntrySet.builder(WoodType.class, "log", "planked",
                        getModBlock("planked_oak_log"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new RotatedPillarBlock(Utils.copyPropertySafe(w.log))
                )
                .addTexture(modRes("block/oak_log/planked_oak_log"))
                .addTexture(modRes("block/oak_log/planked_oak_log_top"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(PlankedLog);

        ReinforcedLog = SimpleEntrySet.builder(WoodType.class, "log", "reinforced",
                        getModBlock("reinforced_oak_log"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new RotatedPillarBlock(Utils.copyPropertySafe(w.log))
                )
                .addTextureM(modRes("block/oak_log/reinforced_oak_log"),
                        EveryCompat.res("block/ch/logs/reinforced_oak_log_m"))
                .addTexture(modRes("block/oak_log/reinforced_oak_log_top"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(ReinforcedLog);


    }

    // TYPE: methods
    private void dullPalette(Palette p) {
        int leftover = p.size() - 3;

        if (leftover > 6) {
            p.increaseInner();
            p.remove(p.getLightest());
            p.remove(p.getDarkest());
            p.remove(p.getDarkest());
            p.remove(p.getDarkest());
        }
    }

    private void dullerPalette(Palette p) {
        int leftover = p.size() - 4;

        if (leftover > 6) {
            p.remove(p.getLightest());
            p.remove(p.getLightest());
            p.remove(p.getDarkest());
            p.remove(p.getDarkest());
        }
    }

    private void dullLuminance(Palette p) {
        while (p.size() < 8) p.increaseInner(); // necessary due to fewer than 7 paletteColors
        if (p.size() < 17) { // Not necessary for more than 16
            for (int i = 0; i < 8; i++) {
                p.increaseInner();
            }
            for (int i = 0; i < 4; i++) {
                p.reduceUp();
            }
            p.reduceDown();
            p.reduceDown();
        }
    }

    private void polishedPalette(Palette p) {
        if (6 < p.size() && p.size() < 33) {
            p.reduceDown();
            PaletteColor darker = p.getDarkest().getLightened();
            p.reduceDown();
            PaletteColor dark = p.getDarkest().getLightened();
            p.reduceDown();
            if (p.size() < 10) {
                while (p.size() < 10) {
                    p.increaseInner();
                }
            }
            else {
                while (p.size() > 8) {
                    p.reduce();
                }
            }
            p.add(dark);
            p.add(darker);
        }
    }

    private void darkerPalette(Palette p) {
        p.reduceDown();
        p.reduceUp();
    }

    private void darkPalette(Palette p) {
        if (p.size() > 25) {
            while (p.size() > 6) {
                p.reduce();
            }
        }
        p.increaseInner();
        p.increaseInner();
        p.increaseInner();
        p.reduceUp();
        p.reduceUp();
        p.reduceDown();
    }

    private void panelPalette(Palette p) {
        p.reduceDown();
        p.increaseInner();
        p.reduceDown();
        p.increaseInner();
        p.reduceDown();
        p.increaseInner();
        p.reduceUp();
    }

    @Override
    public void addDynamicServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicServerResources(handler, manager);

        // use this. also set the entry to no drop so we don't have 2.
        // why do we need this instead of copy parent drop? macaw has doors too and they work
        // chipped adds their loot not via loot table. this is why we need this. no other mod should need this stuff
        // this shouldnt be needed.... why isnt copy parent loot working?
        List<EntrySet<?>> doors = this.getEntries().stream().filter(
                e -> e.getName().contains("door") && !e.getName().contains("trapdoor")).toList();
        for (var e : doors) {
            if (e instanceof SimpleEntrySet<?, ?> se) {
                for (var d : se.blocks.values()) {
                    handler.dynamicPack.addLootTable(d, createDoorLoot(d));
                }
            }
        }

        addChippedRecipe(handler.getPack(), "planks", "carpenters_table");
        addChippedRecipe(handler.getPack(), "door", "carpenters_table");
        addChippedRecipe(handler.getPack(), "trapdoor", "carpenters_table");
        addChippedRecipe(handler.getPack(), "log", "carpenters_table");

    }

    public static LootTable.Builder createDoorLoot(Block block) {
        return LootTable.lootTable().withPool(
                LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(block)
                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                        .setProperties(StatePropertiesPredicate.Builder.properties()
                                                .hasProperty(DoorBlock.HALF, DoubleBlockHalf.LOWER)))));
    }


    @SuppressWarnings("SameParameterValue")
    private void addChippedRecipe(DynamicDataPack pack, String identifier, String workStation) {
        JsonArray jsonArray = new JsonArray();

        for (var woodType : WoodTypeRegistry.getTypes()) {
            if (woodType.isVanilla()) continue;
            boolean hasSomething = false;
            SimpleTagBuilder tagBuilder = SimpleTagBuilder.of(EveryCompat.res(
                    shortenedId() + "/" + woodType.getAppendableId() + "_" + identifier));

            for (var entry : this.getEntries()) {
                String name = entry.getName();
                if (name.matches(".*(_" + identifier + "|" + identifier + "_).*")) {
                    if (identifier.equals("door") && name.matches(".*(_trapdoor|trapdoor_).*")) continue;
                    if (identifier.equals("log") && name.matches(".*(_stripped_log|stripped_).*")) continue;
                    Item item = ((SimpleEntrySet<?, ?>) entry).items.get(woodType);
                    if (item != null) {
                        hasSomething = true;
                        tagBuilder.addEntry(item);
                    }
                }
            }

            // Checking for Child of wood type exist
            if (woodType.getChild(identifier) != null) {
                switch (identifier) { // Adds normal or modded blockType
                    case "planks" -> tagBuilder.addEntry(woodType.planks);
                    case "door" -> tagBuilder.addEntry(woodType.getChild("door"));
                    case "trapdoor" -> tagBuilder.addEntry(woodType.getChild("trapdoor"));
                    case "log" -> tagBuilder.addEntry(woodType.log);
                }
            }

            if (hasSomething) {
                pack.addTag(tagBuilder, Registries.ITEM);
                pack.addTag(tagBuilder, Registries.BLOCK);
                jsonArray.add(tagBuilder.getId().toString());
            }
        }
        JsonObject jo = new JsonObject();
        jo.addProperty("type", "chipped:" + workStation);
        jo.add("tags", jsonArray);
        pack.addJson(EveryCompat.res(shortenedId() + "/" + workStation + "_" + identifier), jo, ResType.RECIPES);

    }

}
