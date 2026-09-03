package net.mehvahdjukaar.every_compat.modules.chipped;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.EntrySet;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;

import static net.mehvahdjukaar.every_compat.modules.chipped.ChippedMainModule.DARKER_PALETTE;
import static net.mehvahdjukaar.every_compat.modules.chipped.ChippedMainModule.DARK_PALETTE;

//See ChippedAbstractModule's SUPPORTED VERSION
public class ChippedDoorModule extends ChippedModuleAbstract {

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

    public ChippedDoorModule(String modId) {
        super(modId);
        ResourceLocation tab = modRes(tabPath);
        setBlockType("Door");

        barredDoor = SimpleEntrySet.builder(WoodType.class, "door", "barred",
                        getModBlock("barred_oak_door"), () -> VanillaWoodTypes.OAK,
                        this::makeDoor
                )
                .addTextureM(modRes("block/oak_door/barred_oak_door_bottom"),
                        EveryCompat.res("block/ch/doors/barred_oak_door_bottom_m"))
                .addTextureM(modRes("block/oak_door/barred_oak_door_top"),
                        EveryCompat.res("block/ch/doors/barred_oak_door_top_m"))
                .addTextureM(modRes("item/oak_door/barred_oak_door"),
                        EveryCompat.res("item/ch/doors/barred_oak_door_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .noDrops()
                .build();
        this.addEntry(barredDoor);

        beachDoor = SimpleEntrySet.builder(WoodType.class, "door", "beach",
                        getModBlock("beach_oak_door"), () -> VanillaWoodTypes.OAK,
                        this::makeDoor
                )
                .addTextureM(modRes("block/oak_door/beach_oak_door_bottom"),
                        EveryCompat.res("block/ch/doors/beach_oak_door_bottom_m"))
                .addTextureM(modRes("block/oak_door/beach_oak_door_top"),
                        EveryCompat.res("block/ch/doors/beach_oak_door_top_m"))
                .addTextureM(modRes("item/oak_door/beach_oak_door"),
                        EveryCompat.res("item/ch/doors/beach_oak_door_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .noDrops()
                .build();
        this.addEntry(beachDoor);

        boardedDoor = SimpleEntrySet.builder(WoodType.class, "door", "boarded",
                        getModBlock("boarded_oak_door"), () -> VanillaWoodTypes.OAK,
                        this::makeDoor
                )
                .addTextureM(modRes("block/oak_door/boarded_oak_door_bottom"),
                        EveryCompat.res("block/ch/doors/boarded_oak_door_bottom_m"),
                        DARK_PALETTE)
                .addTextureM(modRes("block/oak_door/boarded_oak_door_top"),
                        EveryCompat.res("block/ch/doors/boarded_oak_door_top_m"),
                        DARK_PALETTE)
                .addTextureM(modRes("item/oak_door/boarded_oak_door"),
                        EveryCompat.res("item/ch/doors/boarded_oak_door_m"),
                        DARK_PALETTE)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .noDrops()
                .build();
        this.addEntry(boardedDoor);

        dualPaneledDoor = SimpleEntrySet.builder(WoodType.class, "door", "dual_paneled",
                        getModBlock("dual_paneled_oak_door"), () -> VanillaWoodTypes.OAK,
                        this::makeDoor
                )
                .addTextureM(modRes("block/oak_door/dual_paneled_oak_door_bottom"),
                        EveryCompat.res("block/ch/doors/dual_paneled_oak_door_bottom_m"),
                        DARK_PALETTE)
                .addTextureM(modRes("block/oak_door/dual_paneled_oak_door_top"),
                        EveryCompat.res("block/ch/doors/dual_paneled_oak_door_top_m"),
                        DARK_PALETTE)
                .addTextureM(modRes("item/oak_door/dual_paneled_oak_door"),
                        EveryCompat.res("item/ch/doors/dual_paneled_oak_door_m"),
                        DARK_PALETTE)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .noDrops()
                .build();
        this.addEntry(dualPaneledDoor);

        fortifiedDoor = SimpleEntrySet.builder(WoodType.class, "door", "fortified",
                        getModBlock("fortified_oak_door"), () -> VanillaWoodTypes.OAK,
                        this::makeDoor
                )
                .addTextureM(modRes("block/oak_door/fortified_oak_door_bottom"),
                        EveryCompat.res("block/ch/doors/fortified_oak_door_bottom_m"),
                        DARK_PALETTE)
                .addTextureM(modRes("block/oak_door/fortified_oak_door_top"),
                        EveryCompat.res("block/ch/doors/fortified_oak_door_top_m"),
                        DARK_PALETTE)
                .addTextureM(modRes("item/oak_door/fortified_oak_door"),
                        EveryCompat.res("item/ch/doors/fortified_oak_door_m"),
                        DARK_PALETTE)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .noDrops()
                .build();
        this.addEntry(fortifiedDoor);

        gatedDoor = SimpleEntrySet.builder(WoodType.class, "door", "gated",
                        getModBlock("gated_oak_door"), () -> VanillaWoodTypes.OAK,
                        this::makeDoor
                )
                .addTextureM(modRes("block/oak_door/gated_oak_door_bottom"),
                        EveryCompat.res("block/ch/doors/gated_oak_door_bottom_m"),
                        DARK_PALETTE)
                .addTextureM(modRes("block/oak_door/gated_oak_door_top"),
                        EveryCompat.res("block/ch/doors/gated_oak_door_top_m"),
                        DARK_PALETTE)
                .addTextureM(modRes("item/oak_door/gated_oak_door"),
                        EveryCompat.res("item/ch/doors/gated_oak_door_m"),
                        DARK_PALETTE)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .noDrops()
                .build();
        this.addEntry(gatedDoor);

        glassDoor = SimpleEntrySet.builder(WoodType.class, "door", "glass",
                        getModBlock("glass_oak_door"), () -> VanillaWoodTypes.OAK,
                        this::makeDoor
                )
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
                        getModBlock("heavy_oak_door"), () -> VanillaWoodTypes.OAK,
                        this::makeDoor
                )
                .addTextureM(modRes("block/oak_door/heavy_oak_door_bottom"),
                        EveryCompat.res("block/ch/doors/heavy_oak_door_bottom_m"),
                        DARK_PALETTE)
                .addTextureM(modRes("block/oak_door/heavy_oak_door_top"),
                        EveryCompat.res("block/ch/doors/heavy_oak_door_top_m"),
                        DARK_PALETTE)
                .addTextureM(modRes("item/oak_door/heavy_oak_door"),
                        EveryCompat.res("item/ch/doors/heavy_oak_door_m"),
                        DARK_PALETTE)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .noDrops()
                .build();
        this.addEntry(heavyDoor);

        overgrownDoor = SimpleEntrySet.builder(WoodType.class, "door", "overgrown",
                        getModBlock("overgrown_oak_door"), () -> VanillaWoodTypes.OAK,
                        this::makeDoor
                )
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
                        getModBlock("paneled_oak_door"), () -> VanillaWoodTypes.OAK,
                        this::makeDoor
                )
                .addTextureM(modRes("block/oak_door/paneled_oak_door_bottom"),
                        EveryCompat.res("block/ch/doors/paneled_oak_door_bottom_m"),
                        DARK_PALETTE)
                .addTextureM(modRes("block/oak_door/paneled_oak_door_top"),
                        EveryCompat.res("block/ch/doors/paneled_oak_door_top_m"),
                        DARK_PALETTE)
                .addTextureM(modRes("item/oak_door/paneled_oak_door"),
                        EveryCompat.res("item/ch/doors/paneled_oak_door_m"),
                        DARK_PALETTE)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .noDrops()
                .build();
        this.addEntry(paneledDoor);

        paperDoor = SimpleEntrySet.builder(WoodType.class, "door", "paper",
                        getModBlock("paper_oak_door"), () -> VanillaWoodTypes.OAK,
                        this::makeDoor
                )
                .addTextureM(modRes("block/oak_door/paper_oak_door_bottom"),
                        EveryCompat.res("block/ch/doors/paper_oak_door_bottom_m"),
                        DARK_PALETTE)
                .addTextureM(modRes("block/oak_door/paper_oak_door_top"),
                        EveryCompat.res("block/ch/doors/paper_oak_door_top_m"),
                        DARK_PALETTE)
                .addTextureM(modRes("item/oak_door/paper_oak_door"),
                        EveryCompat.res("item/ch/doors/paper_oak_door_m"),
                        DARK_PALETTE)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .noDrops()
                .build();
        this.addEntry(paperDoor);

        pressedDoor = SimpleEntrySet.builder(WoodType.class, "door", "pressed",
                        getModBlock("pressed_oak_door"), () -> VanillaWoodTypes.OAK,
                        this::makeDoor
                )
                .addTextureM(modRes("block/oak_door/pressed_oak_door_bottom"),
                        EveryCompat.res("block/ch/doors/pressed_oak_door_bottom_m"),
                        DARK_PALETTE)
                .addTextureM(modRes("block/oak_door/pressed_oak_door_top"),
                        EveryCompat.res("block/ch/doors/pressed_oak_door_top_m"),
                        DARK_PALETTE)
                .addTextureM(modRes("item/oak_door/pressed_oak_door"),
                        EveryCompat.res("item/ch/doors/pressed_oak_door_m"),
                        DARK_PALETTE)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .noDrops()
                .build();
        this.addEntry(pressedDoor);

        screenDoor = SimpleEntrySet.builder(WoodType.class, "door", "screen",
                        getModBlock("screen_oak_door"), () -> VanillaWoodTypes.OAK,
                        this::makeDoor
                )
                .addTextureM(modRes("block/oak_door/screen_oak_door_bottom"),
                        EveryCompat.res("block/ch/doors/screen_oak_door_bottom_m"),
                        DARK_PALETTE)
                .addTextureM(modRes("block/oak_door/screen_oak_door_top"),
                        EveryCompat.res("block/ch/doors/screen_oak_door_top_m"),
                        DARK_PALETTE)
                .addTextureM(modRes("item/oak_door/screen_oak_door"),
                        EveryCompat.res("item/ch/doors/screen_oak_door_m"),
                        DARK_PALETTE)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .noDrops()
                .build();
        this.addEntry(screenDoor);

        secretDoor = SimpleEntrySet.builder(WoodType.class, "door", "secret",
                        getModBlock("secret_oak_door"), () -> VanillaWoodTypes.OAK,
                        this::makeDoor
                )
                .addTextureM(modRes("block/oak_door/secret_oak_door_bottom"),
                        EveryCompat.res("block/ch/doors/secret_oak_door_bottom_m"),
                        DARK_PALETTE)
                .addTextureM(modRes("block/oak_door/secret_oak_door_top"),
                        EveryCompat.res("block/ch/doors/secret_oak_door_top_m"),
                        DARK_PALETTE)
                .addTextureM(modRes("item/oak_door/secret_oak_door"),
                        EveryCompat.res("item/ch/doors/secret_oak_door_m"),
                        DARK_PALETTE)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .noDrops()
                .build();
        this.addEntry(secretDoor);

        shackDoor = SimpleEntrySet.builder(WoodType.class, "door", "shack",
                        getModBlock("shack_oak_door"), () -> VanillaWoodTypes.OAK,
                        this::makeDoor
                )
                .addTexture(modRes("block/oak_door/shack_oak_door_bottom"), DARK_PALETTE)
                .addTextureM(modRes("block/oak_door/shack_oak_door_top"),
                        EveryCompat.res("block/ch/doors/shack_oak_door_top_m"),
                        DARK_PALETTE)
                .addTextureM(modRes("item/oak_door/shack_oak_door"),
                        EveryCompat.res("item/ch/doors/shack_oak_door_m"),
                        DARK_PALETTE)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .noDrops()
                .build();
        this.addEntry(shackDoor);

        slidingDoor = SimpleEntrySet.builder(WoodType.class, "door", "sliding",
                        getModBlock("sliding_oak_door"), () -> VanillaWoodTypes.OAK,
                        this::makeDoor
                )
                .addTextureM(modRes("block/oak_door/sliding_oak_door_bottom"),
                        EveryCompat.res("block/ch/doors/sliding_oak_door_bottom_m"),
                        DARK_PALETTE)
                .addTextureM(modRes("block/oak_door/sliding_oak_door_top"),
                        EveryCompat.res("block/ch/doors/sliding_oak_door_top_m"),
                        DARK_PALETTE)
                .addTextureM(modRes("item/oak_door/sliding_oak_door"),
                        EveryCompat.res("item/ch/doors/sliding_oak_door_m"),
                        DARK_PALETTE)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .noDrops()
                .build();
        this.addEntry(slidingDoor);

        supportedDoor = SimpleEntrySet.builder(WoodType.class, "door", "supported",
                        getModBlock("supported_oak_door"), () -> VanillaWoodTypes.OAK,
                        this::makeDoor
                )
                .addTextureM(modRes("block/oak_door/supported_oak_door_bottom"),
                        EveryCompat.res("block/ch/doors/supported_oak_door_bottom_m"),
                        DARKER_PALETTE)
                .addTextureM(modRes("block/oak_door/supported_oak_door_top"),
                        EveryCompat.res("block/ch/doors/supported_oak_door_top_m"),
                        DARKER_PALETTE)
                .addTextureM(modRes("item/oak_door/supported_oak_door"),
                        EveryCompat.res("item/ch/doors/supported_oak_door_m"),
                        DARKER_PALETTE)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .noDrops()
                .build();
        this.addEntry(supportedDoor);

        tileWindowedDoor = SimpleEntrySet.builder(WoodType.class, "door", "tile_windowed",
                        getModBlock("tile_windowed_oak_door"), () -> VanillaWoodTypes.OAK,
                        this::makeDoor
                )
                .addTextureM(modRes("block/oak_door/tile_windowed_oak_door_bottom"),
                        EveryCompat.res("block/ch/doors/tile_windowed_oak_door_bottom_m"),
                        DARK_PALETTE)
                .addTextureM(modRes("block/oak_door/tile_windowed_oak_door_top"),
                        EveryCompat.res("block/ch/doors/tile_windowed_oak_door_top_m"),
                        DARK_PALETTE)
                .addTextureM(modRes("item/oak_door/tile_windowed_oak_door"),
                        EveryCompat.res("item/ch/doors/tile_windowed_oak_door_m"),
                        DARK_PALETTE)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .noDrops()
                .build();
        this.addEntry(tileWindowedDoor);

        tiledDoor = SimpleEntrySet.builder(WoodType.class, "door", "tiled",
                        getModBlock("tiled_oak_door"), () -> VanillaWoodTypes.OAK,
                        this::makeDoor
                )
                .addTextureM(modRes("block/oak_door/tiled_oak_door_bottom"),
                        EveryCompat.res("block/ch/doors/tiled_oak_door_bottom_m"),
                        DARK_PALETTE)
                .addTextureM(modRes("block/oak_door/tiled_oak_door_top"),
                        EveryCompat.res("block/ch/doors/tiled_oak_door_top_m"),
                        DARK_PALETTE)
                .addTextureM(modRes("item/oak_door/tiled_oak_door"),
                        EveryCompat.res("item/ch/doors/tiled_oak_door_m"),
                        DARK_PALETTE)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .noDrops()
                .build();
        this.addEntry(tiledDoor);

        windowedDoor = SimpleEntrySet.builder(WoodType.class, "door", "windowed",
                        getModBlock("windowed_oak_door"), () -> VanillaWoodTypes.OAK,
                        this::makeDoor
                )
                .addTextureM(modRes("block/oak_door/windowed_oak_door_bottom"),
                        EveryCompat.res("block/ch/doors/windowed_oak_door_bottom_m"),
                        DARK_PALETTE)
                .addTextureM(modRes("block/oak_door/windowed_oak_door_top"),
                        EveryCompat.res("block/ch/doors/windowed_oak_door_top_m"),
                        DARK_PALETTE)
                .addTextureM(modRes("item/oak_door/windowed_oak_door"),
                        EveryCompat.res("item/ch/doors/windowed_oak_door_m"),
                        DARK_PALETTE)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_DOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_DOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .noDrops()
                .build();
        this.addEntry(windowedDoor);

        //TYPE: trapdoor
        airyTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "airy",
                        getModBlock("airy_oak_trapdoor"), () -> VanillaWoodTypes.OAK,
                        this::makeTrapdoor
                )
                .addTextureM(modRes("block/oak_trapdoor/airy_oak_trapdoor"), EveryCompat.res("block/ch/trapdoors/airy_oak_trapdoor_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .build();
        this.addEntry(airyTrapdoor);

        barredTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "barred",
                        getModBlock("barred_oak_trapdoor"), () -> VanillaWoodTypes.OAK,
                        this::makeTrapdoor
                )
                .addTexture(modRes("block/oak_trapdoor/barred_oak_trapdoor"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .build();
        this.addEntry(barredTrapdoor);

        checkeredTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "checkered",
                        getModBlock("checkered_oak_trapdoor"), () -> VanillaWoodTypes.OAK,
                        this::makeTrapdoor
                )
                .addTexture(modRes("block/oak_trapdoor/checkered_oak_trapdoor"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .build();
        this.addEntry(checkeredTrapdoor);

        classicTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "classic",
                        getModBlock("classic_spruce_trapdoor"), () -> VanillaWoodTypes.SPRUCE,
                        this::makeTrapdoor
                )
                .addTexture(modRes("block/spruce_trapdoor/classic_spruce_trapdoor"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .build();
        this.addEntry(classicTrapdoor);

        classicWindowedTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "classic_windowed",
                        getModBlock("classic_windowed_oak_trapdoor"), () -> VanillaWoodTypes.OAK,
                        this::makeTrapdoor
                )
                .addTextureM(modRes("block/oak_trapdoor/classic_windowed_oak_trapdoor"), EveryCompat.res("block/ch/trapdoors/classic_windowed_oak_trapdoor_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .build();
        this.addEntry(classicWindowedTrapdoor);

        cobwebTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "cobweb",
                        getModBlock("cobweb_oak_trapdoor"), () -> VanillaWoodTypes.OAK,
                        this::makeTrapdoor
                )
                .addTextureM(modRes("block/oak_trapdoor/cobweb_oak_trapdoor"), EveryCompat.res("block/ch/trapdoors/cobweb_oak_trapdoor_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .build();
        this.addEntry(cobwebTrapdoor);

        distortedTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "distorted",
                        getModBlock("distorted_oak_trapdoor"), () -> VanillaWoodTypes.OAK,
                        this::makeTrapdoor
                )
                .addTexture(modRes("block/oak_trapdoor/distorted_oak_trapdoor"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .build();
        this.addEntry(distortedTrapdoor);

        fancyTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "fancy",
                        getModBlock("fancy_oak_trapdoor"), () -> VanillaWoodTypes.OAK,
                        this::makeTrapdoor
                )
                .addTextureM(modRes("block/oak_trapdoor/fancy_oak_trapdoor"), EveryCompat.res("block/ch/trapdoors/fancy_oak_trapdoor_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .build();
        this.addEntry(fancyTrapdoor);

        goldenBarredTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "golden_barred",
                        getModBlock("golden_barred_oak_trapdoor"), () -> VanillaWoodTypes.OAK,
                        this::makeTrapdoor
                )
                .addTextureM(modRes("block/oak_trapdoor/golden_barred_oak_trapdoor"),
                        EveryCompat.res("block/ch/trapdoors/golden_barred_oak_trapdoor_m"),
                        DARK_PALETTE)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .build();
        this.addEntry(goldenBarredTrapdoor);

        heavyTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "heavy",
                        getModBlock("heavy_oak_trapdoor"), () -> VanillaWoodTypes.OAK,
                        this::makeTrapdoor
                )
                .addTextureM(modRes("block/oak_trapdoor/heavy_oak_trapdoor"),
                        EveryCompat.res("block/ch/trapdoors/heavy_oak_trapdoor_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .build();
        this.addEntry(heavyTrapdoor);

        ironBarredTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "iron_barred",
                        getModBlock("iron_barred_oak_trapdoor"), () -> VanillaWoodTypes.OAK,
                        this::makeTrapdoor
                )
                .addTextureM(modRes("block/oak_trapdoor/iron_barred_oak_trapdoor"),
                        EveryCompat.res("block/ch/trapdoors/iron_barred_oak_trapdoor_m"),
                        DARK_PALETTE)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .build();
        this.addEntry(ironBarredTrapdoor);

        leafyTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "leafy",
                        getModBlock("leafy_oak_trapdoor"), () -> VanillaWoodTypes.OAK,
                        this::makeTrapdoor
                )
                .addTextureM(modRes("block/oak_trapdoor/leafy_oak_trapdoor"), EveryCompat.res("block/ch/trapdoors/leafy_oak_trapdoor_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .build();
        this.addEntry(leafyTrapdoor);

        meshedTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "meshed",
                        getModBlock("meshed_oak_trapdoor"), () -> VanillaWoodTypes.OAK,
                        this::makeTrapdoor
                )
                .addTexture(modRes("block/oak_trapdoor/meshed_oak_trapdoor"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .build();
        this.addEntry(meshedTrapdoor);

        overgrownTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "overgrown",
                        getModBlock("overgrown_oak_trapdoor"), () -> VanillaWoodTypes.OAK,
                        this::makeTrapdoor
                )
                .addTextureM(modRes("block/oak_trapdoor/overgrown_oak_trapdoor"), EveryCompat.res("block/ch/trapdoors/overgrown_oak_trapdoor_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .build();
        this.addEntry(overgrownTrapdoor);

        pointlessTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "pointless",
                        getModBlock("pointless_oak_trapdoor"), () -> VanillaWoodTypes.OAK,
                        this::makeTrapdoor
                )
                .addTexture(modRes("block/oak_trapdoor/pointless_oak_trapdoor"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .build();
        this.addEntry(pointlessTrapdoor);

        slottedTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "slotted",
                        getModBlock("slotted_oak_trapdoor"), () -> VanillaWoodTypes.OAK,
                        this::makeTrapdoor
                )
                .addTexture(modRes("block/oak_trapdoor/slotted_oak_trapdoor"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .build();
        this.addEntry(slottedTrapdoor);

        solidTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "solid",
                        getModBlock("solid_oak_trapdoor"), () -> VanillaWoodTypes.OAK,
                        this::makeTrapdoor
                )
                .addTexture(modRes("block/oak_trapdoor/solid_oak_trapdoor"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .build();
        this.addEntry(solidTrapdoor);

        suspiciousTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "suspicious",
                        getModBlock("suspicious_oak_trapdoor"), () -> VanillaWoodTypes.OAK,
                        this::makeTrapdoor
                )
                .addTexture(modRes("block/oak_trapdoor/suspicious_oak_trapdoor"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .build();
        this.addEntry(suspiciousTrapdoor);

        twistedTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "twisted",
                        getModBlock("twisted_oak_trapdoor"), () -> VanillaWoodTypes.OAK,
                        this::makeTrapdoor
                )
                .addTextureM(modRes("block/oak_trapdoor/twisted_oak_trapdoor"), EveryCompat.res("block/ch/trapdoors/twisted_oak_trapdoor_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .build();
        this.addEntry(twistedTrapdoor);

        vinedTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "vined",
                        getModBlock("vined_oak_trapdoor"), () -> VanillaWoodTypes.OAK,
                        this::makeTrapdoor
                )
                .addTextureM(modRes("block/oak_trapdoor/vined_oak_trapdoor"), EveryCompat.res("block/ch/trapdoors/vined_oak_trapdoor_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .build();
        this.addEntry(vinedTrapdoor);

        wartedTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "warted",
                        getModBlock("warted_oak_trapdoor"), () -> VanillaWoodTypes.OAK,
                        this::makeTrapdoor
                )
                .addTextureM(modRes("block/oak_trapdoor/warted_oak_trapdoor"), EveryCompat.res("block/ch/trapdoors/warted_oak_trapdoor_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .build();
        this.addEntry(wartedTrapdoor);

        windowedTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "windowed",
                        getModBlock("windowed_oak_trapdoor"), () -> VanillaWoodTypes.OAK,
                        this::makeTrapdoor
                )
                .addTextureM(modRes("block/oak_trapdoor/windowed_oak_trapdoor"), EveryCompat.res("block/ch/trapdoors/windowed_oak_trapdoor_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .build();
        this.addEntry(windowedTrapdoor);

        wovenTrapdoor = SimpleEntrySet.builder(WoodType.class, "trapdoor", "woven",
                        getModBlock("woven_oak_trapdoor"), () -> VanillaWoodTypes.OAK,
                        this::makeTrapdoor
                )
                .addTexture(modRes("block/oak_trapdoor/woven_oak_trapdoor"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_TRAPDOORS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_TRAPDOORS, Registries.ITEM)
                .setRenderType(RenderLayer.CUTOUT)
                .setTabKey(tab)
                .build();
        this.addEntry(wovenTrapdoor);

    }

    private @NotNull TrapDoorBlock makeTrapdoor(WoodType w) {
        return new TrapDoorBlock(Utils.copyPropertySafe(w.planks).noOcclusion(), w.toVanillaOrOak().setType());
    }

    private @NotNull DoorBlock makeDoor(WoodType w) {
        return new DoorBlock(Utils.copyPropertySafe(w.log).noOcclusion(), w.toVanillaOrOak().setType());
    }

    @Override
    // RECIPES, LOOT_TABLES
    public void addDynamicServerResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicServerResources(executor);

        executor.accept((manager, sink)-> {
            // use this. also set the entry to no drop so we don't have 2.
            // why do we need this instead of copy parent drop? macaw has doors too and they work
            // chipped adds their loot not via loot table. this is why we need this. no other mod should need this stuff
            // this shouldnt be needed.... why isnt copy parent loot working?
            List<EntrySet<?>> doors = this.getEntries().stream().filter(
                    e -> e.getName().contains("door") && !e.getName().contains("trapdoor")).toList();
            for (var e : doors) {
                if (e instanceof SimpleEntrySet<?, ?> se) {
                    for (var d : se.blocks.values()) {
                        sink.addLootTable(d, createDoorLoot(d));
                    }
                }
            }

            addCarpenterRecipe(sink, "door");
            addCarpenterRecipe(sink, "trapdoor");
        });
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
}