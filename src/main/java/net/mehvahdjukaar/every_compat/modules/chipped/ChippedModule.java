package net.mehvahdjukaar.every_compat.modules.chipped;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import earth.terrarium.chipped.registry.ChippedProperties;
import earth.terrarium.chipped.registry.ModBlocks;
import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.every_compat.api.EntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.mehvahdjukaar.selene.block_set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.selene.client.asset_generators.textures.Palette;
import net.mehvahdjukaar.selene.client.asset_generators.textures.PaletteColor;
import net.mehvahdjukaar.selene.client.asset_generators.textures.SpriteUtils;
import net.mehvahdjukaar.selene.resourcepack.DynamicDataPack;
import net.mehvahdjukaar.selene.resourcepack.ResType;
import net.mehvahdjukaar.selene.resourcepack.resources.TagBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.block.*;

import java.util.List;
import java.util.Objects;

import static net.mehvahdjukaar.selene.client.asset_generators.textures.SpriteUtils.extrapolateWoodItemPalette;

// TODO: Fix recipes & tags
// `chipped:oak_planks` tags should be changed to `modid:modded_planks`
// The same should be done for door & trapdoor variants
// Mcmeta files are not copied from the base block

// SUPPORT: v2.0.1
public class ChippedModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> stackedPlanks;
    public final SimpleEntrySet<WoodType, Block> hewnPlanks;
    public final SimpleEntrySet<WoodType, Block> verticallyRailedPlanks;
    public final SimpleEntrySet<WoodType, Block> framedPlanks;
    public final SimpleEntrySet<WoodType, Block> detailedPlanks;
    public final SimpleEntrySet<WoodType, Block> brickyPlanks;
    public final SimpleEntrySet<WoodType, Block> basketWovenPlanks;
    public final SimpleEntrySet<WoodType, Block> boxedPlanks;
    public final SimpleEntrySet<WoodType, Block> brickBondPlanks;
    public final SimpleEntrySet<WoodType, Block> corneredPlanks;
    public final SimpleEntrySet<WoodType, Block> cratedPlanks;
    public final SimpleEntrySet<WoodType, Block> crossLacedPlanks;
    public final SimpleEntrySet<WoodType, Block> crossedPlanks;
    public final SimpleEntrySet<WoodType, Block> diagonalPlanks;
    public final SimpleEntrySet<WoodType, Block> diamondPlanks;
    public final SimpleEntrySet<WoodType, Block> doubleHerringbonePlanks;
    public final SimpleEntrySet<WoodType, Block> enclosedPlanks;
    public final SimpleEntrySet<WoodType, Block> finePlanks;
    public final SimpleEntrySet<WoodType, Block> fineVerticalPlanks;
    public final SimpleEntrySet<WoodType, Block> herringbonePlanks;
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
    public final SimpleEntrySet<WoodType, Block> thinPlanks;
    public final SimpleEntrySet<WoodType, Block> tiledPlanks;
    public final SimpleEntrySet<WoodType, Block> versaillesPlanks;
    public final SimpleEntrySet<WoodType, Block> verticalPlanks;
    public final SimpleEntrySet<WoodType, Block> whirlwindPlanks;
    public final SimpleEntrySet<WoodType, Block> wickeredPlanks;
    public final SimpleEntrySet<WoodType, Block> zigzagPlanks;
    public final SimpleEntrySet<WoodType, Block> latticePlanks;
    public final SimpleEntrySet<WoodType, Block> framedBoardPlanks;

    public final SimpleEntrySet<WoodType, Block> beachDoor;
    public final SimpleEntrySet<WoodType, Block> slidingDoor;
    public final SimpleEntrySet<WoodType, Block> modernDoor;
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
    public final SimpleEntrySet<WoodType, Block> supportedDoor;
    public final SimpleEntrySet<WoodType, Block> tileWindowedDoor;
    public final SimpleEntrySet<WoodType, Block> tiledDoor;
    public final SimpleEntrySet<WoodType, Block> windowedDoor;

    public final SimpleEntrySet<WoodType, Block> Trapdoor_1;
    public final SimpleEntrySet<WoodType, Block> Trapdoor_2;
    public final SimpleEntrySet<WoodType, Block> Trapdoor_3;
    public final SimpleEntrySet<WoodType, Block> Trapdoor_4;
    public final SimpleEntrySet<WoodType, Block> Trapdoor_5;
    public final SimpleEntrySet<WoodType, Block> Trapdoor_6;
    public final SimpleEntrySet<WoodType, Block> Trapdoor_7;
    public final SimpleEntrySet<WoodType, Block> Trapdoor_8;
    public final SimpleEntrySet<WoodType, Block> Trapdoor_9;
    public final SimpleEntrySet<WoodType, Block> Trapdoor_10;
    public final SimpleEntrySet<WoodType, Block> Trapdoor_11;

    public final SimpleEntrySet<WoodType, Block> Log_1;
    public final SimpleEntrySet<WoodType, Block> Log_2;
    public final SimpleEntrySet<WoodType, Block> Log_3;
    public final SimpleEntrySet<WoodType, Block> Log_4;
    public final SimpleEntrySet<WoodType, Block> Log_5;
    public final SimpleEntrySet<WoodType, Block> Log_6;
    public final SimpleEntrySet<WoodType, Block> Log_7;
    public final SimpleEntrySet<WoodType, Block> Log_8;
    public final SimpleEntrySet<WoodType, Block> Log_9;
    public final SimpleEntrySet<WoodType, Block> Log_10;
    public final SimpleEntrySet<WoodType, Block> Log_11;

    public final SimpleEntrySet<WoodType, Block> Stripped_Log_1;
    public final SimpleEntrySet<WoodType, Block> Stripped_Log_2;
    public final SimpleEntrySet<WoodType, Block> Stripped_Log_3;
    public final SimpleEntrySet<WoodType, Block> Stripped_Log_4;
    public final SimpleEntrySet<WoodType, Block> Stripped_Log_5;
    public final SimpleEntrySet<WoodType, Block> Stripped_Log_6;
    public final SimpleEntrySet<WoodType, Block> Stripped_Log_7;
    public final SimpleEntrySet<WoodType, Block> Stripped_Log_8;
    public final SimpleEntrySet<WoodType, Block> Stripped_Log_9;
    public final SimpleEntrySet<WoodType, Block> Stripped_Log_10;
    public final SimpleEntrySet<WoodType, Block> Stripped_Log_11;
    public final SimpleEntrySet<WoodType, Block> Stripped_Log_12;
    public final SimpleEntrySet<WoodType, Block> Stripped_Log_13;
    public final SimpleEntrySet<WoodType, Block> Stripped_Log_14;
    public final SimpleEntrySet<WoodType, Block> Stripped_Log_15;
    public final SimpleEntrySet<WoodType, Block> Stripped_Log_16;
/*
*/

// If you are planning to add torch, wallTorch, Glass, GlassPane, Barrel
// Don't waste your time because they don't have woodtype in the name/ID,
// just "glass_pane_X" where X is integers

    public ChippedModule(String modId) {
        super(modId, "ch");
        CreativeModeTab tab = ModBlocks.ITEM_GROUP;

        //TYPE: PLANKS
        stackedPlanks = SimpleEntrySet.builder(WoodType.class, "planks_1",
                        () -> getModBlock("oak_planks_1"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new Block(WoodGood.copySafe(w.planks))
                )
                .addTexture(modRes("block/oak_planks/oak_planks_1"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::dullerPalette)
//                .createPaletteFromOak(p -> multiPalette(p, 22, p.getLightest()))
                .setTab(() -> tab)
                .build();
        this.addEntry(stackedPlanks);
        hewnPlanks = SimpleEntrySet.builder(WoodType.class, "planks_2",
                        () -> getModBlock("oak_planks_2"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new Block(WoodGood.copySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/oak_planks_2"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkerPalette)
                .setTab(() -> tab)
                .build();
        this.addEntry(hewnPlanks);

        verticallyRailedPlanks = SimpleEntrySet.builder(WoodType.class, "planks_3",
                        () -> getModBlock("oak_planks_3"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new Block(WoodGood.copySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/oak_planks_3"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkerPalette)
                .setTab(() -> tab)
                .build();
        this.addEntry(verticallyRailedPlanks);

        framedPlanks = SimpleEntrySet.builder(WoodType.class, "planks_4",
                        () -> getModBlock("oak_planks_4"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new Block(WoodGood.copySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/oak_planks_4"))
                .addTexture(modRes("block/oak_planks/oak_planks_4_ctm"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkerPalette)
                .setTab(() -> tab)
                .build();
        this.addEntry(framedPlanks);

        detailedPlanks = SimpleEntrySet.builder(WoodType.class, "planks_5",
                        () -> getModBlock("oak_planks_5"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new Block(WoodGood.copySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/oak_planks_5"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkerPalette)
                .setTab(() -> tab)
                .build();
        this.addEntry(detailedPlanks);

        brickyPlanks = SimpleEntrySet.builder(WoodType.class, "planks_6",
                        () -> getModBlock("oak_planks_6"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new Block(WoodGood.copySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/oak_planks_6"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkerPalette)
                .setTab(() -> tab)
                .build();
        this.addEntry(brickyPlanks);

        corneredPlanks = SimpleEntrySet.builder(WoodType.class, "planks_7",
                        () -> getModBlock("oak_planks_7"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new Block(WoodGood.copySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/oak_planks_7"))
                .addTexture(modRes("block/oak_planks/oak_planks_7_ctm"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkerPalette)
                .setTab(() -> tab)
                .build();
        this.addEntry(corneredPlanks);

        wickeredPlanks = SimpleEntrySet.builder(WoodType.class, "planks_8",
                        () -> getModBlock("oak_planks_8"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new Block(WoodGood.copySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/oak_planks_8"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkerPalette)
                .setTab(() -> tab)
                .build();
        this.addEntry(wickeredPlanks);

        shiftedPlanks = SimpleEntrySet.builder(WoodType.class, "planks_9",
                        () -> getModBlock("oak_planks_9"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new Block(WoodGood.copySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/oak_planks_9"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkerPalette)
                .setTab(() -> tab)
                .build();
        this.addEntry(shiftedPlanks);

        thinPlanks = SimpleEntrySet.builder(WoodType.class, "planks_10",
                        () -> getModBlock("oak_planks_10"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new Block(WoodGood.copySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/oak_planks_10"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkerPalette)
                .setTab(() -> tab)
                .build();
        this.addEntry(thinPlanks);

        enclosedPlanks = SimpleEntrySet.builder(WoodType.class, "planks_11",
                        () -> getModBlock("oak_planks_11"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new Block(WoodGood.copySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/oak_planks_11"))
                .addTexture(modRes("block/oak_planks/oak_planks_11_ctm"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkerPalette)
                .setTab(() -> tab)
                .build();
        this.addEntry(enclosedPlanks);

        boxedPlanks = SimpleEntrySet.builder(WoodType.class, "planks_12",
                        () -> getModBlock("oak_planks_12"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new Block(WoodGood.copySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/oak_planks_12"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkerPalette)
                .setTab(() -> tab)
                .build();
        this.addEntry(boxedPlanks);

        cratedPlanks = SimpleEntrySet.builder(WoodType.class, "planks_13",
                        () -> getModBlock("oak_planks_13"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new Block(WoodGood.copySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/oak_planks_13"))
                .addTexture(modRes("block/oak_planks/oak_planks_13_ctm"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkerPalette)
                .setTab(() -> tab)
                .build();
        this.addEntry(cratedPlanks);

        polishedPlanks = SimpleEntrySet.builder(WoodType.class, "planks_14",
                        () -> getModBlock("oak_planks_14"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new Block(WoodGood.copySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/oak_planks_14"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::neutralPalette)
                .setTab(() -> tab)
                .build();
        this.addEntry(polishedPlanks);

        verticalPlanks = SimpleEntrySet.builder(WoodType.class, "planks_15",
                        () -> getModBlock("oak_planks_15"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new Block(WoodGood.copySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/oak_planks_15"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.PLANKS, Registry.ITEM_REGISTRY)
                .setTab(() -> tab)
                .build();
        this.addEntry(verticalPlanks);

        smoothPlanks = SimpleEntrySet.builder(WoodType.class, "planks_16",
                        () -> getModBlock("oak_planks_16"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new Block(WoodGood.copySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/oak_planks_16"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkerPalette)
                .setTab(() -> tab)
                .build();
        this.addEntry(smoothPlanks);

        diagonalPlanks = SimpleEntrySet.builder(WoodType.class, "planks_17",
                        () -> getModBlock("oak_planks_17"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new Block(WoodGood.copySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/oak_planks_17"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkerPalette)
                .setTab(() -> tab)
                .build();
        this.addEntry(diagonalPlanks);

        basketWovenPlanks = SimpleEntrySet.builder(WoodType.class, "planks_18",
                        () -> getModBlock("oak_planks_18"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new Block(WoodGood.copySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/oak_planks_18"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkerPalette)
                .setTab(() -> tab)
                .build();
        this.addEntry(basketWovenPlanks);

        shavingsPlanks = SimpleEntrySet.builder(WoodType.class, "planks_19",
                        () -> getModBlock("oak_planks_19"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new Block(WoodGood.copySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/oak_planks_19"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.PLANKS, Registry.ITEM_REGISTRY)
                .setTab(() -> tab)
                .build();
        this.addEntry(shavingsPlanks);

        railedPlanks = SimpleEntrySet.builder(WoodType.class, "planks_20",
                        () -> getModBlock("oak_planks_20"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new Block(WoodGood.copySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/oak_planks_20"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.PLANKS, Registry.ITEM_REGISTRY)
                .setTab(() -> tab)
                .build();
        this.addEntry(railedPlanks);

        mosaicPlanks = SimpleEntrySet.builder(WoodType.class, "planks_21",
                        () -> getModBlock("oak_planks_21"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new Block(WoodGood.copySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/oak_planks_21"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::dullPalette)
                .setTab(() -> tab)
                .build();
        this.addEntry(mosaicPlanks);

        panelPlanks = SimpleEntrySet.builder(WoodType.class, "planks_22",
                        () -> getModBlock("oak_planks_22"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new Block(WoodGood.copySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/oak_planks_22"))
                .addTexture(modRes("block/oak_planks/oak_planks_22_ctm"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkPalette)
                .setTab(() -> tab)
                .build();
        this.addEntry(panelPlanks);

        brickBondPlanks = SimpleEntrySet.builder(WoodType.class, "planks_23",
                        () -> getModBlock("oak_planks_23"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new Block(WoodGood.copySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/oak_planks_23"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::dullerPalette)
                .setTab(() -> tab)
                .build();
        this.addEntry(brickBondPlanks);

        crossLacedPlanks = SimpleEntrySet.builder(WoodType.class, "planks_24",
                        () -> getModBlock("oak_planks_24"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new Block(WoodGood.copySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/oak_planks_24"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.PLANKS, Registry.ITEM_REGISTRY)
                .setTab(() -> tab)
                .build();
        this.addEntry(crossLacedPlanks);

        crossedPlanks = SimpleEntrySet.builder(WoodType.class, "planks_25",
                        () -> getModBlock("oak_planks_25"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new Block(WoodGood.copySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/oak_planks_25"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.PLANKS, Registry.ITEM_REGISTRY)
                .setTab(() -> tab)
                .build();
        this.addEntry(crossedPlanks);


        diamondPlanks = SimpleEntrySet.builder(WoodType.class, "planks_26",
                        () -> getModBlock("oak_planks_26"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new Block(WoodGood.copySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/oak_planks_26"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.PLANKS, Registry.ITEM_REGISTRY)
                .setTab(() -> tab)
                .build();
        this.addEntry(diamondPlanks);

        doubleHerringbonePlanks = SimpleEntrySet.builder(WoodType.class, "planks_27",
                        () -> getModBlock("oak_planks_27"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new Block(WoodGood.copySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/oak_planks_27"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkestPalette)
                .setTab(() -> tab)
                .build();
        this.addEntry(doubleHerringbonePlanks);

        finePlanks = SimpleEntrySet.builder(WoodType.class, "planks_28",
                        () -> getModBlock("oak_planks_28"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new Block(WoodGood.copySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/oak_planks_28"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::dullPalette)
                .setTab(() -> tab)
                .build();
        this.addEntry(finePlanks);

        fineVerticalPlanks = SimpleEntrySet.builder(WoodType.class, "planks_29",
                        () -> getModBlock("oak_planks_29"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new Block(WoodGood.copySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/oak_planks_29"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::dullPalette)
                .setTab(() -> tab)
                .build();
        this.addEntry(fineVerticalPlanks);

        herringbonePlanks = SimpleEntrySet.builder(WoodType.class, "planks_30",
                        () -> getModBlock("oak_planks_30"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new Block(WoodGood.copySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/oak_planks_30"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkestPalette)
                .setTab(() -> tab)
                .build();
        this.addEntry(herringbonePlanks);

        lacedPlanks = SimpleEntrySet.builder(WoodType.class, "planks_31",
                        () -> getModBlock("oak_planks_31"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new Block(WoodGood.copySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/oak_planks_31"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkerPalette)
                .setTab(() -> tab)
                .build();
        this.addEntry(lacedPlanks);

        nailedPlanks = SimpleEntrySet.builder(WoodType.class, "planks_32",
                        () -> getModBlock("oak_planks_32"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new Block(WoodGood.copySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/oak_planks_32"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkerPalette)
                .setTab(() -> tab)
                .build();
        this.addEntry(nailedPlanks);

        naturalPlanks = SimpleEntrySet.builder(WoodType.class, "planks_33",
                        () -> getModBlock("oak_planks_33"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new Block(WoodGood.copySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/oak_planks_33"))
                .addTexture(modRes("block/oak_planks/oak_planks_33_ctm"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkPalette)
                .setTab(() -> tab)
                .build();
        this.addEntry(naturalPlanks);

        peggedPlanks = SimpleEntrySet.builder(WoodType.class, "planks_34",
                        () -> getModBlock("oak_planks_34"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new Block(WoodGood.copySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/oak_planks_34"))
                .addTexture(modRes("block/oak_planks/oak_planks_34_pillar"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkPalette)
                .setTab(() -> tab)
                .build();
        this.addEntry(peggedPlanks);

        slantedPlanks = SimpleEntrySet.builder(WoodType.class, "planks_35",
                        () -> getModBlock("oak_planks_35"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new Block(WoodGood.copySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/oak_planks_35"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkestPalette)
                .setTab(() -> tab)
                .build();
        this.addEntry(slantedPlanks);

        tiledPlanks = SimpleEntrySet.builder(WoodType.class, "planks_36",
                        () -> getModBlock("oak_planks_36"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new Block(WoodGood.copySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/oak_planks_36"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkerPalette)
                .setTab(() -> tab)
                .build();
        this.addEntry(tiledPlanks);

        versaillesPlanks = SimpleEntrySet.builder(WoodType.class, "planks_37",
                        () -> getModBlock("oak_planks_37"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new Block(WoodGood.copySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/oak_planks_37"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkerPalette)
                .setTab(() -> tab)
                .build();
        this.addEntry(versaillesPlanks);

        whirlwindPlanks = SimpleEntrySet.builder(WoodType.class, "planks_38",
                        () -> getModBlock("oak_planks_38"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new Block(WoodGood.copySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/oak_planks_38"))
                .addTexture(modRes("block/oak_planks/oak_planks_38_ctm"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkPalette)
                .setTab(() -> tab)
                .build();
        this.addEntry(whirlwindPlanks);

        framedBoardPlanks = SimpleEntrySet.builder(WoodType.class, "planks_39",
                        () -> getModBlock("oak_planks_39"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new Block(WoodGood.copySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/oak_planks_39"))
                .addTexture(modRes("block/oak_planks/oak_planks_39_ctm"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkPalette)
                .setTab(() -> tab)
                .build();
        this.addEntry(framedBoardPlanks);

        zigzagPlanks = SimpleEntrySet.builder(WoodType.class, "planks_40",
                        () -> getModBlock("oak_planks_40"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new Block(WoodGood.copySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/oak_planks_40"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkPalette)
                .setTab(() -> tab)
                .build();
        this.addEntry(zigzagPlanks);

        latticePlanks = SimpleEntrySet.builder(WoodType.class, "planks_41",
                        () -> getModBlock("oak_planks_41"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new Block(WoodGood.copySafe(w.planks)))
                .addTexture(modRes("block/oak_planks/oak_planks_41"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.PLANKS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.PLANKS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkPalette)
                .setTab(() -> tab)
                .build();
        this.addEntry(latticePlanks);



        //TYPE: DOOR
        slidingDoor = SimpleEntrySet.builder(WoodType.class, "door_1",
                        () -> getModBlock("oak_door_1"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new DoorBlock(WoodGood.copySafe(Blocks.OAK_DOOR))
                )
                .addTextureM(modRes("block/oak_door/oak_door_1_bottom"), WoodGood.res("block/ch/door/oak_door_x_bottom_m"))
                .addTextureM(modRes("block/oak_door/oak_door_1_top"), WoodGood.res("block/ch/door/oak_door_x_top_m"))
                .addTextureM(modRes("item/oak_door/oak_door_1"), WoodGood.res("item/ch/door/oak_door_x_m"))
                .addModelTransform(m -> m.addModifier((s, resLoc, w) -> {
                    String modID = WoodGood.MOD_ID + ":item";
                    String extra = "/ch/" + w.getNamespace() + "/";
                    String blockID = w.getTypeName() + "_door";
                    return s.replace("chipped:item/oak_door/oak_door_1",
                    modID + extra + blockID + extra + blockID+"_1");
                        }
                ))
                .addCustomItem((woodType, block, properties) -> new DoubleHighBlockItem(block, properties))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.WOODEN_DOORS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkerPalette)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .useLootFromBase()
                .build();
        this.addEntry(slidingDoor);

        beachDoor = SimpleEntrySet.builder(WoodType.class, "door_2",
                        () -> getModBlock("oak_door_2"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new DoorBlock(WoodGood.copySafe(Blocks.OAK_DOOR))
                )
                .addTextureM(modRes("block/oak_door/oak_door_2_bottom"), WoodGood.res("block/ch/door/oak_door_x_bottom_m"))
                .addTextureM(modRes("block/oak_door/oak_door_2_top"), WoodGood.res("block/ch/door/oak_door_x_top_m"))
                .addTextureM(modRes("item/oak_door/oak_door_2"), WoodGood.res("item/ch/door/oak_door_x_m"))
                .addModelTransform(m -> m.addModifier((s, resLoc, w) -> {
                            String prefix = WoodGood.MOD_ID + ":item";
                            String extra = "/ch/" + w.getNamespace() + "/";
                            String blockID = w.getTypeName() + "_door";
                            return s.replace("chipped:item/oak_door/oak_door_2",
                                    prefix + extra + blockID + extra + blockID+"_2");
                        }
                ))
                .addCustomItem((woodType, block, properties) -> new DoubleHighBlockItem(block, properties))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.WOODEN_DOORS, Registry.ITEM_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .useLootFromBase()
                .build();
        this.addEntry(beachDoor);

        boardedDoor = SimpleEntrySet.builder(WoodType.class, "door_3",
                        () -> getModBlock("oak_door_3"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new DoorBlock(WoodGood.copySafe(Blocks.OAK_DOOR))
                )
                .addTextureM(modRes("block/oak_door/oak_door_3_bottom"), WoodGood.res("block/ch/door/oak_door_3_bottom_m"))
                .addTextureM(modRes("block/oak_door/oak_door_3_top"), WoodGood.res("block/ch/door/oak_door_3_top_m"))
                .addTextureM(modRes("item/oak_door/oak_door_3"), WoodGood.res("item/ch/door/oak_door_3_m"))
                .addModelTransform(m -> m.addModifier((s, resLoc, w) -> {
                            String prefix = WoodGood.MOD_ID + ":item";
                            String extra = "/ch/" + w.getNamespace() + "/";
                            String blockID = w.getTypeName() + "_door";
                            return s.replace("chipped:item/oak_door/oak_door_3",
                                    prefix + extra + blockID + extra + blockID+"_3");
                        }
                ))
                .addCustomItem((woodType, block, properties) -> new DoubleHighBlockItem(block, properties))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.WOODEN_DOORS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkPalette)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .useLootFromBase()
                .build();
        this.addEntry(boardedDoor);

        dualPaneledDoor = SimpleEntrySet.builder(WoodType.class, "door_4",
                        () -> getModBlock("oak_door_4"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new DoorBlock(WoodGood.copySafe(Blocks.OAK_DOOR))
                )
                .addTextureM(modRes("block/oak_door/oak_door_4_bottom"), WoodGood.res("block/ch/door/oak_door_x_bottom_m"))
                .addTextureM(modRes("block/oak_door/oak_door_4_top"), WoodGood.res("block/ch/door/oak_door_x_top_m"))
                .addTextureM(modRes("item/oak_door/oak_door_4"), WoodGood.res("item/ch/door/oak_door_x_m"))
                .addModelTransform(m -> m.addModifier((s, resLoc, w) -> {
                            String prefix = WoodGood.MOD_ID + ":item";
                            String extra = "/ch/" + w.getNamespace() + "/";
                            String blockID = w.getTypeName() + "_door";
                            return s.replace("chipped:item/oak_door/oak_door_4",
                                    prefix + extra + blockID + extra + blockID+"_4");
                        }
                ))
                .addCustomItem((woodType, block, properties) -> new DoubleHighBlockItem(block, properties))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.WOODEN_DOORS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkPalette)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .useLootFromBase()
                .build();
        this.addEntry(dualPaneledDoor);

        fortifiedDoor = SimpleEntrySet.builder(WoodType.class, "door_5",
                        () -> getModBlock("oak_door_5"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new DoorBlock(WoodGood.copySafe(Blocks.OAK_DOOR))
                )
                .addTextureM(modRes("block/oak_door/oak_door_5_bottom"), WoodGood.res("block/ch/door/oak_door_5_bottom_m"))
                .addTextureM(modRes("block/oak_door/oak_door_5_top"), WoodGood.res("block/ch/door/oak_door_5_top_m"))
                .addTextureM(modRes("item/oak_door/oak_door_5"), WoodGood.res("item/ch/door/oak_door_5_m"))
                .addModelTransform(m -> m.addModifier((s, resLoc, w) -> {
                            String prefix = WoodGood.MOD_ID + ":item";
                            String extra = "/ch/" + w.getNamespace() + "/";
                            String blockID = w.getTypeName() + "_door";
                            return s.replace("chipped:item/oak_door/oak_door_5",
                                    prefix + extra + blockID + extra + blockID+"_5");
                        }
                ))
                .addCustomItem((woodType, block, properties) -> new DoubleHighBlockItem(block, properties))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.WOODEN_DOORS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkPalette)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .useLootFromBase()
                .build();
        this.addEntry(fortifiedDoor);

        gatedDoor = SimpleEntrySet.builder(WoodType.class, "door_6",
                        () -> getModBlock("oak_door_6"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new DoorBlock(WoodGood.copySafe(Blocks.OAK_DOOR))
                )
                .addTextureM(modRes("block/oak_door/oak_door_6_bottom"), WoodGood.res("block/ch/door/oak_door_x_bottom_m"))
                .addTextureM(modRes("block/oak_door/oak_door_6_top"), WoodGood.res("block/ch/door/oak_door_x_top_m"))
                .addTextureM(modRes("item/oak_door/oak_door_6"), WoodGood.res("item/ch/door/oak_door_x_m"))
                .addModelTransform(m -> m.addModifier((s, resLoc, w) -> {
                            String prefix = WoodGood.MOD_ID + ":item";
                            String extra = "/ch/" + w.getNamespace() + "/";
                            String blockID = w.getTypeName() + "_door";
                            return s.replace("chipped:item/oak_door/oak_door_6",
                                    prefix + extra + blockID + extra + blockID+"_6");
                        }
                ))
                .addCustomItem((woodType, block, properties) -> new DoubleHighBlockItem(block, properties))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.WOODEN_DOORS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkPalette)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .useLootFromBase()
                .build();
        this.addEntry(gatedDoor);

        glassDoor = SimpleEntrySet.builder(WoodType.class, "door_7",
                        () -> getModBlock("oak_door_7"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new DoorBlock(WoodGood.copySafe(Blocks.OAK_DOOR))
                )
                .addTextureM(modRes("block/oak_door/oak_door_7_bottom"), WoodGood.res("block/ch/door/oak_door_7_bottom_m"))
                .addTextureM(modRes("block/oak_door/oak_door_7_top"), WoodGood.res("block/ch/door/oak_door_7_top_m"))
                .addTextureM(modRes("item/oak_door/oak_door_7"), WoodGood.res("item/ch/door/oak_door_7_m"))
                .addModelTransform(m -> m.addModifier((s, resLoc, w) -> {
                            String prefix = WoodGood.MOD_ID + ":item";
                            String extra = "/ch/" + w.getNamespace() + "/";
                            String blockID = w.getTypeName() + "_door";
                            return s.replace("chipped:item/oak_door/oak_door_7",
                                    prefix + extra + blockID + extra + blockID + "_7");
                        }
                ))
                .addCustomItem((woodType, block, properties) -> new DoubleHighBlockItem(block, properties))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.WOODEN_DOORS, Registry.ITEM_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .useLootFromBase()
                .build();
        this.addEntry(glassDoor);

        heavyDoor = SimpleEntrySet.builder(WoodType.class, "door_8",
                        () -> getModBlock("oak_door_8"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new DoorBlock(WoodGood.copySafe(Blocks.OAK_DOOR))
                )
                .addTextureM(modRes("block/oak_door/oak_door_8_bottom"), WoodGood.res("block/ch/door/oak_door_8_bottom_m"))
                .addTextureM(modRes("block/oak_door/oak_door_8_top"), WoodGood.res("block/ch/door/oak_door_8_top_m"))
                .addTextureM(modRes("item/oak_door/oak_door_8"), WoodGood.res("item/ch/door/oak_door_x_m"))
                .addModelTransform(m -> m.addModifier((s, resLoc, w) -> {
                            String prefix = WoodGood.MOD_ID + ":item";
                            String extra = "/ch/" + w.getNamespace() + "/";
                            String blockID = w.getTypeName() + "_door";
                            return s.replace("chipped:item/oak_door/oak_door_8",
                                    prefix + extra + blockID + extra + blockID+"_8");
                        }
                ))
                .addCustomItem((woodType, block, properties) -> new DoubleHighBlockItem(block, properties))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.WOODEN_DOORS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkPalette)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .useLootFromBase()
                .build();
        this.addEntry(heavyDoor);

        modernDoor = SimpleEntrySet.builder(WoodType.class, "door_9",
                        () -> getModBlock("oak_door_9"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new DoorBlock(WoodGood.copySafe(Blocks.OAK_DOOR))
                )
                .addTextureM(modRes("block/oak_door/oak_door_9_bottom"), WoodGood.res("block/ch/door/oak_door_9_bottom_m"))
                .addTextureM(modRes("block/oak_door/oak_door_9_top"), WoodGood.res("block/ch/door/oak_door_9_top_m"))
                .addTextureM(modRes("item/oak_door/oak_door_9"), WoodGood.res("item/ch/door/oak_door_9_m"))
                .addModelTransform(m -> m.addModifier((s, resLoc, w) -> {
                            String prefix = WoodGood.MOD_ID + ":item";
                            String extra = "/ch/" + w.getNamespace() + "/";
                            String blockID = w.getTypeName() + "_door";
                            return s.replace("chipped:item/oak_door/oak_door_9",
                                    prefix + extra + blockID + extra + blockID + "_9");
                        }
                ))
                .addCustomItem((woodType, block, properties) -> new DoubleHighBlockItem(block, properties))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.WOODEN_DOORS, Registry.ITEM_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .useLootFromBase()
                .build();
        this.addEntry(modernDoor);

        overgrownDoor = SimpleEntrySet.builder(WoodType.class, "door_10",
                        () -> getModBlock("oak_door_10"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new DoorBlock(WoodGood.copySafe(Blocks.OAK_DOOR))
                )
                .addTextureM(modRes("block/oak_door/oak_door_10_bottom"), WoodGood.res("block/ch/door/oak_door_10_bottom_m"))
                .addTextureM(modRes("block/oak_door/oak_door_10_top"), WoodGood.res("block/ch/door/oak_door_10_top_m"))
                .addTextureM(modRes("item/oak_door/oak_door_10"), WoodGood.res("item/ch/door/oak_door_10_m"))
                .addModelTransform(m -> m.addModifier((s, resLoc, w) -> {
                            String prefix = WoodGood.MOD_ID + ":item";
                            String extra = "/ch/" + w.getNamespace() + "/";
                            String blockID = w.getTypeName() + "_door";
                            return s.replace("chipped:item/oak_door/oak_door_10",
                                    prefix + extra + blockID + extra + blockID+"_10");
                        }
                ))
                .addCustomItem((woodType, block, properties) -> new DoubleHighBlockItem(block, properties))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.WOODEN_DOORS, Registry.ITEM_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .useLootFromBase()
                .build();
        this.addEntry(overgrownDoor);

        paneledDoor = SimpleEntrySet.builder(WoodType.class, "door_11",
                        () -> getModBlock("oak_door_11"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new DoorBlock(WoodGood.copySafe(Blocks.OAK_DOOR))
                )
                .addTextureM(modRes("block/oak_door/oak_door_11_bottom"), WoodGood.res("block/ch/door/oak_door_11_bottom_m"))
                .addTextureM(modRes("block/oak_door/oak_door_11_top"), WoodGood.res("block/ch/door/oak_door_11_top_m"))
                .addTextureM(modRes("item/oak_door/oak_door_11"), WoodGood.res("item/ch/door/oak_door_x_m"))
                .addModelTransform(m -> m.addModifier((s, resLoc, w) -> {
                            String prefix = WoodGood.MOD_ID + ":item";
                            String extra = "/ch/" + w.getNamespace() + "/";
                            String blockID = w.getTypeName() + "_door";
                            return s.replace("chipped:item/oak_door/oak_door_11",
                                    prefix + extra + blockID + extra + blockID+"_11");
                        }
                ))
                .addCustomItem((woodType, block, properties) -> new DoubleHighBlockItem(block, properties))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.WOODEN_DOORS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkPalette)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .useLootFromBase()
                .build();
        this.addEntry(paneledDoor);

        paperDoor = SimpleEntrySet.builder(WoodType.class, "door_12",
                        () -> getModBlock("oak_door_12"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new DoorBlock(WoodGood.copySafe(Blocks.OAK_DOOR))
                )
                .addTextureM(modRes("block/oak_door/oak_door_12_bottom"), WoodGood.res("block/ch/door/oak_door_12_bottom_m"))
                .addTextureM(modRes("block/oak_door/oak_door_12_top"), WoodGood.res("block/ch/door/oak_door_12_top_m"))
                .addTextureM(modRes("item/oak_door/oak_door_12"), WoodGood.res("item/ch/door/oak_door_12_m"))
                .addModelTransform(m -> m.addModifier((s, resLoc, w) -> {
                            String prefix = WoodGood.MOD_ID + ":item";
                            String extra = "/ch/" + w.getNamespace() + "/";
                            String blockID = w.getTypeName() + "_door";
                            return s.replace("chipped:item/oak_door/oak_door_12",
                                    prefix + extra + blockID + extra + blockID+"_12");
                        }
                ))
                .addCustomItem((woodType, block, properties) -> new DoubleHighBlockItem(block, properties))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.WOODEN_DOORS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkPalette)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .useLootFromBase()
                .build();
        this.addEntry(paperDoor);

        pressedDoor = SimpleEntrySet.builder(WoodType.class, "door_13",
                        () -> getModBlock("oak_door_13"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new DoorBlock(WoodGood.copySafe(Blocks.OAK_DOOR))
                )
                .addTextureM(modRes("block/oak_door/oak_door_13_bottom"), WoodGood.res("block/ch/door/oak_door_x_bottom_m"))
                .addTextureM(modRes("block/oak_door/oak_door_13_top"), WoodGood.res("block/ch/door/oak_door_x_top_m"))
                .addTextureM(modRes("item/oak_door/oak_door_13"), WoodGood.res("item/ch/door/oak_door_13_m"))
                .addModelTransform(m -> m.addModifier((s, resLoc, w) -> {
                            String prefix = WoodGood.MOD_ID + ":item";
                            String extra = "/ch/" + w.getNamespace() + "/";
                            String blockID = w.getTypeName() + "_door";
                            return s.replace("chipped:item/oak_door/oak_door_13",
                                    prefix + extra + blockID + extra + blockID+"_13");
                        }
                ))
                .addCustomItem((woodType, block, properties) -> new DoubleHighBlockItem(block, properties))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.WOODEN_DOORS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkPalette)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .useLootFromBase()
                .build();
        this.addEntry(pressedDoor);

        screenDoor = SimpleEntrySet.builder(WoodType.class, "door_14",
                        () -> getModBlock("oak_door_14"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new DoorBlock(WoodGood.copySafe(Blocks.OAK_DOOR))
                )
                .addTextureM(modRes("block/oak_door/oak_door_14_bottom"), WoodGood.res("block/ch/door/oak_door_x_bottom_m"))
                .addTextureM(modRes("block/oak_door/oak_door_14_top"), WoodGood.res("block/ch/door/oak_door_x_top_m"))
                .addTextureM(modRes("item/oak_door/oak_door_14"), WoodGood.res("item/ch/door/oak_door_14_m"))
                .addModelTransform(m -> m.addModifier((s, resLoc, w) -> {
                            String prefix = WoodGood.MOD_ID + ":item";
                            String extra = "/ch/" + w.getNamespace() + "/";
                            String blockID = w.getTypeName() + "_door";
                            return s.replace("chipped:item/oak_door/oak_door_14",
                                    prefix + extra + blockID + extra + blockID+"_14");
                        }
                ))
                .addCustomItem((woodType, block, properties) -> new DoubleHighBlockItem(block, properties))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.WOODEN_DOORS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkPalette)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .useLootFromBase()
                .build();
        this.addEntry(screenDoor);

        secretDoor = SimpleEntrySet.builder(WoodType.class, "door_15",
                        () -> getModBlock("oak_door_15"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new DoorBlock(WoodGood.copySafe(Blocks.OAK_DOOR))
                )
                .addTextureM(modRes("block/oak_door/oak_door_15_bottom"), WoodGood.res("block/ch/door/oak_door_15_x_m"))
                .addTextureM(modRes("block/oak_door/oak_door_15_top"), WoodGood.res("block/ch/door/oak_door_15_x_m"))
                .addTextureM(modRes("item/oak_door/oak_door_15"), WoodGood.res("item/ch/door/oak_door_15_m"))
                .addModelTransform(m -> m.addModifier((s, resLoc, w) -> {
                            String prefix = WoodGood.MOD_ID + ":item";
                            String extra = "/ch/" + w.getNamespace() + "/";
                            String blockID = w.getTypeName() + "_door";
                            return s.replace("chipped:item/oak_door/oak_door_15",
                                    prefix + extra + blockID + extra + blockID+"_15");
                        }
                ))
                .addCustomItem((woodType, block, properties) -> new DoubleHighBlockItem(block, properties))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.WOODEN_DOORS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkPalette)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .useLootFromBase()
                .build();
        this.addEntry(secretDoor);

        shackDoor = SimpleEntrySet.builder(WoodType.class, "door_16",
                        () -> getModBlock("oak_door_16"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new DoorBlock(WoodGood.copySafe(Blocks.OAK_DOOR))
                )
                .addTexture(modRes("block/oak_door/oak_door_16_bottom"))
                .addTextureM(modRes("block/oak_door/oak_door_16_top"), WoodGood.res("block/ch/door/oak_door_16_top_m"))
                .addTextureM(modRes("item/oak_door/oak_door_16"), WoodGood.res("item/ch/door/oak_door_x_m"))
                .addModelTransform(m -> m.addModifier((s, resLoc, w) -> {
                            String prefix = WoodGood.MOD_ID + ":item";
                            String extra = "/ch/" + w.getNamespace() + "/";
                            String blockID = w.getTypeName() + "_door";
                            return s.replace("chipped:item/oak_door/oak_door_16",
                                    prefix + extra + blockID + extra + blockID+"_16");
                        }
                ))
                .addCustomItem((woodType, block, properties) -> new DoubleHighBlockItem(block, properties))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.WOODEN_DOORS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkPalette)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .useLootFromBase()
                .build();
        this.addEntry(shackDoor);

        supportedDoor = SimpleEntrySet.builder(WoodType.class, "door_17",
                        () -> getModBlock("oak_door_17"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new DoorBlock(WoodGood.copySafe(Blocks.OAK_DOOR))
                )
                .addTextureM(modRes("block/oak_door/oak_door_17_bottom"), WoodGood.res("block/ch/door/oak_door_17_bottom_m"))
                .addTextureM(modRes("block/oak_door/oak_door_17_top"), WoodGood.res("block/ch/door/oak_door_17_top_m"))
                .addTextureM(modRes("item/oak_door/oak_door_17"), WoodGood.res("item/ch/door/oak_door_x_m"))
                .addModelTransform(m -> m.addModifier((s, resLoc, w) -> {
                            String prefix = WoodGood.MOD_ID + ":item";
                            String extra = "/ch/" + w.getNamespace() + "/";
                            String blockID = w.getTypeName() + "_door";
                            return s.replace("chipped:item/oak_door/oak_door_17",
                                    prefix + extra + blockID + extra + blockID+"_17");
                        }
                ))
                .addCustomItem((woodType, block, properties) -> new DoubleHighBlockItem(block, properties))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.WOODEN_DOORS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkerPalette)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .useLootFromBase()
                .build();
        this.addEntry(supportedDoor);

        tileWindowedDoor = SimpleEntrySet.builder(WoodType.class, "door_18",
                        () -> getModBlock("oak_door_18"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new DoorBlock(WoodGood.copySafe(Blocks.OAK_DOOR))

                )
                .addTextureM(modRes("block/oak_door/oak_door_18_bottom"), WoodGood.res("block/ch/door/oak_door_18_bottom_m"))
                .addTextureM(modRes("block/oak_door/oak_door_18_top"), WoodGood.res("block/ch/door/oak_door_18_top_m"))
                .addTextureM(modRes("item/oak_door/oak_door_18"), WoodGood.res("item/ch/door/oak_door_18_m"))
                .addModelTransform(m -> m.addModifier((s, resLoc, w) -> {
                            String prefix = WoodGood.MOD_ID + ":item";
                            String extra = "/ch/" + w.getNamespace() + "/";
                            String blockID = w.getTypeName() + "_door";
                            return s.replace("chipped:item/oak_door/oak_door_18",
                                    prefix + extra + blockID + extra + blockID+"_18");
                        }
                ))
                .addCustomItem((woodType, block, properties) -> new DoubleHighBlockItem(block, properties))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.WOODEN_DOORS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkPalette)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .useLootFromBase()
                .build();
        this.addEntry(tileWindowedDoor);

        tiledDoor = SimpleEntrySet.builder(WoodType.class, "door_19",
                        () -> getModBlock("oak_door_19"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new DoorBlock(WoodGood.copySafe(Blocks.OAK_DOOR))
                )
                .addTextureM(modRes("block/oak_door/oak_door_19_bottom"), WoodGood.res("block/ch/door/oak_door_x_bottom_m"))
                .addTextureM(modRes("block/oak_door/oak_door_19_top"), WoodGood.res("block/ch/door/oak_door_x_top_m"))
                .addTextureM(modRes("item/oak_door/oak_door_19"), WoodGood.res("item/ch/door/oak_door_19_m"))
                .addModelTransform(m -> m.addModifier((s, resLoc, w) -> {
                            String prefix = WoodGood.MOD_ID + ":item";
                            String extra = "/ch/" + w.getNamespace() + "/";
                            String blockID = w.getTypeName() + "_door";
                            return s.replace("chipped:item/oak_door/oak_door_19",
                                    prefix + extra + blockID + extra + blockID+"_19");
                        }
                ))
                .addCustomItem((woodType, block, properties) -> new DoubleHighBlockItem(block, properties))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.WOODEN_DOORS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkPalette)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .useLootFromBase()
                .build();
        this.addEntry(tiledDoor);

        windowedDoor = SimpleEntrySet.builder(WoodType.class, "door_20",
                        () -> getModBlock("oak_door_20"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new DoorBlock(WoodGood.copySafe(Blocks.OAK_DOOR))
                )
                .addTextureM(modRes("block/oak_door/oak_door_20_bottom"), WoodGood.res("block/ch/door/oak_door_x_bottom_m"))
                .addTextureM(modRes("block/oak_door/oak_door_20_top"), WoodGood.res("block/ch/door/oak_door_20_top_m"))
                .addTextureM(modRes("item/oak_door/oak_door_20"), WoodGood.res("item/ch/door/oak_door_20_m"))
                .addModelTransform(m -> m.addModifier((s, resLoc, w) -> {
                            String prefix = WoodGood.MOD_ID + ":item";
                            String extra = "/ch/" + w.getNamespace() + "/";
                            String blockID = w.getTypeName() + "_door";
                            return s.replace("chipped:item/oak_door/oak_door_20",
                                    prefix + extra + blockID + extra + blockID+"_20");
                        }
                ))
                .addCustomItem((woodType, block, properties) -> new DoubleHighBlockItem(block, properties))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_DOORS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.WOODEN_DOORS, Registry.ITEM_REGISTRY)
                .createPaletteFromOak(this::darkPalette)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .useLootFromBase()
                .build();
        this.addEntry(windowedDoor);

        // TYPE: TRAPDOOR
        Trapdoor_1 = SimpleEntrySet.builder(WoodType.class, "trapdoor_1",
                        () -> getModBlock("oak_trapdoor_1"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new TrapDoorBlock(WoodGood.copySafe(Blocks.OAK_TRAPDOOR))
                )
                .addTextureM(modRes("block/oak_trapdoor/oak_trapdoor_1"), WoodGood.res("block/ch/trapdoor/oak_trapdoor_1_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registry.ITEM_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();
        this.addEntry(Trapdoor_1);


        Trapdoor_2 = SimpleEntrySet.builder(WoodType.class, "trapdoor_2",
                        () -> getModBlock("oak_trapdoor_2"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new TrapDoorBlock(WoodGood.copySafe(Blocks.OAK_TRAPDOOR))
                )
                .addTextureM(modRes("block/oak_trapdoor/oak_trapdoor_2"), WoodGood.res("block/ch/trapdoor/oak_trapdoor_2_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registry.ITEM_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();
        this.addEntry(Trapdoor_2);

        Trapdoor_3 = SimpleEntrySet.builder(WoodType.class, "trapdoor_3",
                        () -> getModBlock("oak_trapdoor_3"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new TrapDoorBlock(WoodGood.copySafe(Blocks.OAK_TRAPDOOR))
                )
                .addTexture(modRes("block/oak_trapdoor/oak_trapdoor_3"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registry.ITEM_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();
        this.addEntry(Trapdoor_3);

        Trapdoor_4 = SimpleEntrySet.builder(WoodType.class, "trapdoor_4",
                        () -> getModBlock("oak_trapdoor_4"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new TrapDoorBlock(WoodGood.copySafe(Blocks.OAK_TRAPDOOR))
                )
                .addTextureM(modRes("block/oak_trapdoor/oak_trapdoor_4"), WoodGood.res("block/ch/trapdoor/oak_trapdoor_4_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registry.ITEM_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();
        this.addEntry(Trapdoor_4);

        Trapdoor_5 = SimpleEntrySet.builder(WoodType.class, "trapdoor_5",
                        () -> getModBlock("oak_trapdoor_5"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new TrapDoorBlock(WoodGood.copySafe(Blocks.OAK_TRAPDOOR))
                )
                .addTextureM(modRes("block/oak_trapdoor/oak_trapdoor_5"), WoodGood.res("block/ch/trapdoor/oak_trapdoor_5_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registry.ITEM_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();
        this.addEntry(Trapdoor_5);

        Trapdoor_6 = SimpleEntrySet.builder(WoodType.class, "trapdoor_6",
                        () -> getModBlock("oak_trapdoor_6"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new TrapDoorBlock(WoodGood.copySafe(Blocks.OAK_TRAPDOOR))
                )
                .addTextureM(modRes("block/oak_trapdoor/oak_trapdoor_6"), WoodGood.res("block/ch/trapdoor/oak_trapdoor_6_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registry.ITEM_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();
        this.addEntry(Trapdoor_6);

        Trapdoor_7 = SimpleEntrySet.builder(WoodType.class, "trapdoor_7",
                        () -> getModBlock("oak_trapdoor_7"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new TrapDoorBlock(WoodGood.copySafe(Blocks.OAK_TRAPDOOR))
                )
                .addTextureM(modRes("block/oak_trapdoor/oak_trapdoor_7"), WoodGood.res("block/ch/trapdoor/oak_trapdoor_7_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registry.ITEM_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();
        this.addEntry(Trapdoor_7);

        Trapdoor_8 = SimpleEntrySet.builder(WoodType.class, "trapdoor_8",
                        () -> getModBlock("oak_trapdoor_8"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new TrapDoorBlock(WoodGood.copySafe(Blocks.OAK_TRAPDOOR))
                )
                .addTextureM(modRes("block/oak_trapdoor/oak_trapdoor_8"), WoodGood.res("block/ch/trapdoor/oak_trapdoor_8_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registry.ITEM_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();
        this.addEntry(Trapdoor_8);

        Trapdoor_9 = SimpleEntrySet.builder(WoodType.class, "trapdoor_9",
                        () -> getModBlock("oak_trapdoor_9"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new TrapDoorBlock(WoodGood.copySafe(Blocks.OAK_TRAPDOOR))
                )
                .addTexture(modRes("block/oak_trapdoor/oak_trapdoor_9"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registry.ITEM_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();
        this.addEntry(Trapdoor_9);

        Trapdoor_10 = SimpleEntrySet.builder(WoodType.class, "trapdoor_10",
                        () -> getModBlock("oak_trapdoor_10"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new TrapDoorBlock(WoodGood.copySafe(Blocks.OAK_TRAPDOOR))
                )
                .addTexture(modRes("block/oak_trapdoor/oak_trapdoor_10"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registry.ITEM_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();
        this.addEntry(Trapdoor_10);

        Trapdoor_11 = SimpleEntrySet.builder(WoodType.class, "trapdoor_11",
                        () -> getModBlock("oak_trapdoor_11"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new TrapDoorBlock(WoodGood.copySafe(Blocks.OAK_TRAPDOOR))
                )
                .addTextureM(modRes("block/oak_trapdoor/oak_trapdoor_11"), WoodGood.res("block/ch/trapdoor/oak_trapdoor_11_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registry.BLOCK_REGISTRY)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registry.ITEM_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();
        this.addEntry(Trapdoor_11);

        //TYPE: LOG
        Log_1 = SimpleEntrySet.builder(WoodType.class, "log_1",
                        () -> getModBlock("oak_log_1"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new RotatedPillarBlock(WoodGood.copySafe(w.log))
                )
                .addTexture(modRes("block/oak_log/oak_log_1_side"))
                .addTexture(modRes("block/oak_log/oak_log_1_top"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();
        this.addEntry(Log_1);

        Log_2 = SimpleEntrySet.builder(WoodType.class, "log_2",
                        () -> getModBlock("oak_log_2"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new RotatedPillarBlock(WoodGood.copySafe(w.log))
                )
                .addTexture(modRes("block/oak_log/oak_log_2_side"))
                .addTexture(modRes("block/oak_log/oak_log_2_top"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();
        this.addEntry(Log_2);

        Log_3 = SimpleEntrySet.builder(WoodType.class, "log_3",
                        () -> getModBlock("oak_log_3"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new RotatedPillarBlock(WoodGood.copySafe(w.log))
                )
                .addTexture(modRes("block/oak_log/oak_log_3_side"))
                .addTexture(modRes("block/oak_log/oak_log_3_top"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();
        this.addEntry(Log_3);

        Log_4 = SimpleEntrySet.builder(WoodType.class, "log_4",
                        () -> getModBlock("oak_log_4"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new RotatedPillarBlock(WoodGood.copySafe(w.log))
                )
                .addTexture(modRes("block/oak_log/oak_log_4_side"))
                .addTexture(modRes("block/oak_log/oak_log_4_top"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();
        this.addEntry(Log_4);

        Log_5 = SimpleEntrySet.builder(WoodType.class, "log_5",
                        () -> getModBlock("oak_log_5"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new RotatedPillarBlock(WoodGood.copySafe(w.log))
                )
                .addTextureM(modRes("block/oak_log/oak_log_5_side"), WoodGood.res("block/ch/log/oak_log_5_side_m"))
                .addTextureM(modRes("block/oak_log/oak_log_5_top"), WoodGood.res("block/ch/log/oak_log_5_top_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();
        this.addEntry(Log_5);

        Log_6 = SimpleEntrySet.builder(WoodType.class, "log_6",
                        () -> getModBlock("oak_log_6"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new RotatedPillarBlock(WoodGood.copySafe(w.log))
                )
                .addTexture(modRes("block/oak_log/oak_log_6_side"))
                .addTexture(modRes("block/oak_log/oak_log_6_top"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();
        this.addEntry(Log_6);

        Log_7 = SimpleEntrySet.builder(WoodType.class, "log_7",
                        () -> getModBlock("oak_log_7"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new RotatedPillarBlock(WoodGood.copySafe(w.log))
                )
                .addTextureM(modRes("block/oak_log/oak_log_7_side"), WoodGood.res("block/ch/log/oak_log_7_side_m"))
                .addTexture(modRes("block/oak_log/oak_log_7_top"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();
        this.addEntry(Log_7);

        Log_8 = SimpleEntrySet.builder(WoodType.class, "log_8",
                        () -> getModBlock("oak_log_8"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new RotatedPillarBlock(WoodGood.copySafe(w.log))
                )
                .addTexture(modRes("block/oak_log/oak_log_8_side"))
                .addTexture(modRes("block/oak_log/oak_log_8_top"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();
        this.addEntry(Log_8);

        Log_9 = SimpleEntrySet.builder(WoodType.class, "log_9",
                        () -> getModBlock("oak_log_9"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new RotatedPillarBlock(WoodGood.copySafe(w.log))
                )
                .addTextureM(modRes("block/oak_log/oak_log_9_side"), WoodGood.res("block/ch/log/oak_log_9_side_m"))
                .addTextureM(modRes("block/oak_log/oak_log_9_top"), WoodGood.res("block/ch/log/oak_log_9_top_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();
        this.addEntry(Log_9);

        Log_10 = SimpleEntrySet.builder(WoodType.class, "log_10",
                        () -> getModBlock("oak_log_10"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new RotatedPillarBlock(WoodGood.copySafe(w.log))
                )
                .addTexture(modRes("block/oak_log/oak_log_10_side"))
                .addTexture(modRes("block/oak_log/oak_log_10_top"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();
        this.addEntry(Log_10);

        Log_11 = SimpleEntrySet.builder(WoodType.class, "log_11",
                        () -> getModBlock("oak_log_11"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new RotatedPillarBlock(WoodGood.copySafe(w.log))
                )
                .addTextureM(modRes("block/oak_log/oak_log_11_side"), WoodGood.res("block/ch/log/oak_log_11_side_m"))
                .addTexture(modRes("block/oak_log/oak_log_11_top"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();
        this.addEntry(Log_11);

        //TYPE: STRIPPED_LOG
        Stripped_Log_1 = SimpleEntrySet.builder(WoodType.class, "log_1", "stripped",
                        () -> getModBlock("stripped_oak_log_1"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new RotatedPillarBlock(WoodGood.copySafe(w.strippedLog))
                )
                .addTexture(modRes("block/stripped_oak_log/stripped_oak_log_1_side"))
                .addTexture(modRes("block/stripped_oak_log/stripped_oak_log_1_top"))
                .createPaletteFromOak(this::dullerPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();
        this.addEntry(Stripped_Log_1);

        Stripped_Log_2 = SimpleEntrySet.builder(WoodType.class, "log_2", "stripped",
                        () -> getModBlock("stripped_oak_log_2"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new RotatedPillarBlock(WoodGood.copySafe(w.strippedLog))
                )
                .addTexture(modRes("block/stripped_oak_log/stripped_oak_log_2"))
                .createPaletteFromOak(this::dullerPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();
        this.addEntry(Stripped_Log_2);

        Stripped_Log_3 = SimpleEntrySet.builder(WoodType.class, "log_3", "stripped",
                        () -> getModBlock("stripped_oak_log_3"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new RotatedPillarBlock(WoodGood.copySafe(w.strippedLog))
                )
                .addTexture(modRes("block/stripped_oak_log/stripped_oak_log_3"))
                .createPaletteFromOak(this::dullerPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();
        this.addEntry(Stripped_Log_3);

        Stripped_Log_4 = SimpleEntrySet.builder(WoodType.class, "log_4", "stripped",
                        () -> getModBlock("stripped_oak_log_4"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new RotatedPillarBlock(WoodGood.copySafe(w.strippedLog))
                )
                .addTexture(modRes("block/stripped_oak_log/stripped_oak_log_4"))
                .createPaletteFromOak(this::dullerPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();
        this.addEntry(Stripped_Log_4);

        Stripped_Log_5 = SimpleEntrySet.builder(WoodType.class, "log_5", "stripped",
                        () -> getModBlock("stripped_oak_log_5"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new RotatedPillarBlock(WoodGood.copySafe(w.strippedLog))
                )
                .addTexture(modRes("block/stripped_oak_log/stripped_oak_log_5"))
                .createPaletteFromOak(this::dullerPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();
        this.addEntry(Stripped_Log_5);

        Stripped_Log_6 = SimpleEntrySet.builder(WoodType.class, "log_6", "stripped",
                        () -> getModBlock("stripped_oak_log_6"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new RotatedPillarBlock(WoodGood.copySafe(w.strippedLog))
                )
                .addTexture(modRes("block/stripped_oak_log/stripped_oak_log_6"))
                .createPaletteFromOak(this::dullerPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();
        this.addEntry(Stripped_Log_6);

        Stripped_Log_7 = SimpleEntrySet.builder(WoodType.class, "log_7", "stripped",
                        () -> getModBlock("stripped_oak_log_7"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new RotatedPillarBlock(WoodGood.copySafe(w.strippedLog))
                )
                .addTexture(modRes("block/stripped_oak_log/stripped_oak_log_7"))
                .createPaletteFromOak(this::dullerPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();
        this.addEntry(Stripped_Log_7);

        Stripped_Log_8 = SimpleEntrySet.builder(WoodType.class, "log_8", "stripped",
                        () -> getModBlock("stripped_oak_log_8"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new RotatedPillarBlock(WoodGood.copySafe(w.strippedLog))
                )
                .addTexture(modRes("block/stripped_oak_log/stripped_oak_log_8"))
                .createPaletteFromOak(this::dullerPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();
        this.addEntry(Stripped_Log_8);

        Stripped_Log_9 = SimpleEntrySet.builder(WoodType.class, "log_9", "stripped",
                        () -> getModBlock("stripped_oak_log_9"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new RotatedPillarBlock(WoodGood.copySafe(w.strippedLog))
                )
                .addTexture(modRes("block/stripped_oak_log/stripped_oak_log_9"))
                .createPaletteFromOak(this::dullerPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();
        this.addEntry(Stripped_Log_9);

        Stripped_Log_10 = SimpleEntrySet.builder(WoodType.class, "log_10", "stripped",
                        () -> getModBlock("stripped_oak_log_10"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new RotatedPillarBlock(WoodGood.copySafe(w.strippedLog))
                )
                .addTexture(modRes("block/stripped_oak_log/stripped_oak_log_10"))
                .createPaletteFromOak(this::dullerPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();
        this.addEntry(Stripped_Log_10);

        Stripped_Log_11 = SimpleEntrySet.builder(WoodType.class, "log_11", "stripped",
                        () -> getModBlock("stripped_oak_log_11"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new RotatedPillarBlock(WoodGood.copySafe(w.strippedLog))
                )
                .addTexture(modRes("block/stripped_oak_log/stripped_oak_log_11"))
                .createPaletteFromOak(this::dullerPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();
        this.addEntry(Stripped_Log_11);

        Stripped_Log_12 = SimpleEntrySet.builder(WoodType.class, "log_12", "stripped",
                        () -> getModBlock("stripped_oak_log_12"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new RotatedPillarBlock(WoodGood.copySafe(w.strippedLog))
                )
                .addTexture(modRes("block/stripped_oak_log/stripped_oak_log_12"))
                .createPaletteFromOak(this::dullerPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();
        this.addEntry(Stripped_Log_12);

        Stripped_Log_13 = SimpleEntrySet.builder(WoodType.class, "log_13", "stripped",
                        () -> getModBlock("stripped_oak_log_13"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new RotatedPillarBlock(WoodGood.copySafe(w.strippedLog))
                )
                .addTexture(modRes("block/stripped_oak_log/stripped_oak_log_13"))
                .createPaletteFromOak(this::dullerPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();
        this.addEntry(Stripped_Log_13);

        Stripped_Log_14 = SimpleEntrySet.builder(WoodType.class, "log_14", "stripped",
                        () -> getModBlock("stripped_oak_log_14"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new RotatedPillarBlock(WoodGood.copySafe(w.strippedLog))
                )
                .addTextureM(modRes("block/stripped_oak_log/stripped_oak_log_14"),
                        WoodGood.res("block/ch/stripped_log/stripped_oak_log_14_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();
        this.addEntry(Stripped_Log_14);

        Stripped_Log_15 = SimpleEntrySet.builder(WoodType.class, "log_15", "stripped",
                        () -> getModBlock("stripped_oak_log_15"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new RotatedPillarBlock(WoodGood.copySafe(w.strippedLog))
                )
                .addTexture(modRes("block/stripped_oak_log/stripped_oak_log_15"))
                .createPaletteFromOak(this::dullerPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();
        this.addEntry(Stripped_Log_15);

        Stripped_Log_16 = SimpleEntrySet.builder(WoodType.class, "log_16", "stripped",
                        () -> getModBlock("stripped_oak_log_16"), () -> WoodType.OAK_WOOD_TYPE,
                        w -> new RotatedPillarBlock(WoodGood.copySafe(w.strippedLog))
                )
                .addTexture(modRes("block/stripped_oak_log/stripped_oak_log_16"))
                .createPaletteFromOak(this::dullerPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .build();
        this.addEntry(Stripped_Log_16);

/*
*/
    }

    private void dullPalette(Palette p) {
        p.add(p.increaseInner());
        p.remove(p.getLightest());
        p.remove(p.getDarkest());
        p.remove(p.getDarkest());
        p.remove(p.getDarkest());
    }

    private void dullerPalette(Palette p) {
        if (p.size() > 6) {
            p.remove(p.getLightest());
            p.remove(p.getLightest());
            p.remove(p.getDarkest());
            p.remove(p.getDarkest());
        }
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
        p.add(p.increaseInner());
        p.remove(p.getLightest());
        p.remove(p.getDarkest());
        p.add(p.increaseInner());
        p.remove(p.getDarkest());
    }

    private void neutralPalette(Palette p) {
        p.remove(p.getLightest());
        p.add(p.increaseInner());
        p.remove(p.getLightest());
        p.add(p.increaseInner());
        p.remove(p.getLightest());
        p.add(p.increaseInner());

        p.remove(p.getDarkest());
        p.add(p.increaseInner());
        p.remove(p.getDarkest());
        p.add(p.increaseInner());
    }

    // TYPE: METHODS
    @Override
    public void addDynamicServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicServerResources(handler, manager);

        addSpecialRecipe(handler.getPack(), "planks","carpenters_table", "ch/carpenters_table");
        addSpecialRecipe(handler.getPack(), "door","carpenters_table", "ch/carpenters_table");
        addSpecialRecipe(handler.getPack(), "trapdoor","carpenters_table", "ch/carpenters_table");
        addSpecialRecipe(handler.getPack(), "log","carpenters_table", "ch/carpenters_table");
        addSpecialRecipe(handler.getPack(), "stripped_log","carpenters_table", "ch/carpenters_table");
    }

    private JsonArray jsonArray = new JsonArray();

    private void addSpecialRecipe(DynamicDataPack pack, String identifier, String workStation, String recipeName) {

        WoodTypeRegistry.WOOD_TYPES.forEach((resLoc, w) -> {
            boolean hasSomething = false;
            var id = w.id;

            TagBuilder tagBuilder = TagBuilder.of(new ResourceLocation(id.getNamespace(),
                    w.getTypeName() + "_" + identifier));
            for (var e : this.getEntries()) {
                String name = e.getName();

                if (name.matches(".*(_" + identifier + "|" + identifier + "_).*")) {
                    if (identifier.equals("door") && name.matches(".*(_trapdoor|trapdoor_).*")) continue;
                    if (identifier.equals("log") && name.matches(".*(_stripped_log|stripped_).*")) continue;
                    Item b = e.items.get(w);
                    if (b != null) {
                        hasSomething = true;
                        tagBuilder.addEntry(b);
                    }
                }
            }

            // null check
            if (w.getChild(identifier) != null) {
                switch (identifier) {
                    case "planks" -> tagBuilder.addEntry(w.planks); // adds vanilla|modded planks
                    case "door" -> tagBuilder.addEntry(w.getBlockOfThis("door")); // adds vanilla|modded door
                    case "trapdoor" -> tagBuilder.addEntry(w.getBlockOfThis("trapdoor")); // adds vanilla|modded trapdoor
                    case "log" -> tagBuilder.addEntry(w.log); // adds vanilla|modded log
                    case "stripped_log" -> tagBuilder.addEntry(w.strippedLog); // adds vanilla|modded stripped_log
                }
            }

            if (hasSomething) {
                pack.addTag(tagBuilder, Registry.ITEM_REGISTRY);
                pack.addTag(tagBuilder, Registry.BLOCK_REGISTRY);
                jsonArray.add(tagBuilder.getId().toString());
            }

        });

        JsonObject jo = new JsonObject();
        jo.addProperty("type","chipped:" + workStation);
        jo.add("tags", jsonArray);
        pack.addJson(WoodGood.res(recipeName), jo, ResType.RECIPES);
    }

}
