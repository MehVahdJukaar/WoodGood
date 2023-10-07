package net.mehvahdjukaar.every_compat.modules.forge.mcaw;

import com.mcwwindows.kikoz.MacawsWindows;
import com.mcwwindows.kikoz.init.BlockInit;
import com.mcwwindows.kikoz.objects.*;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

public class MacawWindowsModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> blinds;
    public final SimpleEntrySet<WoodType, Block> logParapets;
    public final SimpleEntrySet<WoodType, Block> logWindows;
    public final SimpleEntrySet<WoodType, Block> logPaneWindows;
    public final SimpleEntrySet<WoodType, Block> logFourWindows;
    public final SimpleEntrySet<WoodType, Block> louveredShutters;
    public final SimpleEntrySet<WoodType, Block> planksWindows;
    public final SimpleEntrySet<WoodType, Block> planksFourWindows;
    public final SimpleEntrySet<WoodType, Block> planksParapets;
    public final SimpleEntrySet<WoodType, Block> planksPaneWindows;
    public final SimpleEntrySet<WoodType, Block> shutters;
    public final SimpleEntrySet<WoodType, Block> strippedLogWindows;
    public final SimpleEntrySet<WoodType, Block> strippedLogPaneWindows;
    public final SimpleEntrySet<WoodType, Block> strippedLogFourWindow;
    public final SimpleEntrySet<WoodType, Block> PaneWindow;
    public final SimpleEntrySet<WoodType, Block> strippedPaneWindow;
    public final SimpleEntrySet<WoodType, Block> plankPaneWindow;


    public MacawWindowsModule(String modId) {
        super(modId, "mcw");

        blinds = SimpleEntrySet.builder(WoodType.class, "blinds",
                        BlockInit.OAK_BLINDS, () -> WoodTypeRegistry.OAK_TYPE, w -> new Blinds())
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> MacawsWindows.WindowItemGroup)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .build();

        this.addEntry(blinds);

        logParapets = SimpleEntrySet.builder(WoodType.class, "log_parapet",
                        BlockInit.OAK_LOG_PARAPET, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Parapet(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> MacawsWindows.WindowItemGroup)
                .defaultRecipe()
                .build();

        this.addEntry(logParapets);

        planksParapets = SimpleEntrySet.builder(WoodType.class, "plank_parapet",
                        BlockInit.OAK_PLANK_PARAPET, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Parapet(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> MacawsWindows.WindowItemGroup)
                .defaultRecipe()
                .build();

        this.addEntry(planksParapets);

        logWindows = SimpleEntrySet.builder(WoodType.class, "window",
                        BlockInit.OAK_WINDOW, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Window(Utils.copyPropertySafe(w.log)))
                .addTag(modRes("windows"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> MacawsWindows.WindowItemGroup)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .build();

        this.addEntry(logWindows);

        planksWindows = SimpleEntrySet.builder(WoodType.class, "plank_window",
                        BlockInit.OAK_PLANK_WINDOW, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Window(Utils.copyPropertySafe(w.planks)))
                .addTag(modRes("windows"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> MacawsWindows.WindowItemGroup)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .build();

        this.addEntry(planksWindows);

        logPaneWindows = SimpleEntrySet.builder(WoodType.class, "window2",
                        BlockInit.OAK_WINDOW2, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WindowBarred(Utils.copyPropertySafe(w.log)))
                .addTag(modRes("windows_two"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> MacawsWindows.WindowItemGroup)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .build();

        this.addEntry(logPaneWindows);

        planksPaneWindows = SimpleEntrySet.builder(WoodType.class, "plank_window2",
                        BlockInit.OAK_PLANK_WINDOW2, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WindowBarred(Utils.copyPropertySafe(w.planks)))
                .addTag(modRes("windows_two"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> MacawsWindows.WindowItemGroup)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .build();

        this.addEntry(planksPaneWindows);

        strippedLogWindows = SimpleEntrySet.builder(WoodType.class, "log_window", "stripped",
                        BlockInit.STRIPPED_OAK_LOG_WINDOW, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Window(Utils.copyPropertySafe(w.log)))
                .addTag(modRes("windows"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> MacawsWindows.WindowItemGroup)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .build();

        this.addEntry(strippedLogWindows);

        strippedLogPaneWindows = SimpleEntrySet.builder(WoodType.class, "log_window2", "stripped",
                        BlockInit.STRIPPED_OAK_LOG_WINDOW2, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WindowBarred(Utils.copyPropertySafe(w.log)))
                .addTag(modRes("windows_two"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> MacawsWindows.WindowItemGroup)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .build();

        this.addEntry(strippedLogPaneWindows);

        logFourWindows = SimpleEntrySet.builder(WoodType.class, "four_window",
                        BlockInit.OAK_FOUR_WINDOW, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WindowBarred(Utils.copyPropertySafe(w.log)))
                .addTag(modRes("windows_four"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> MacawsWindows.WindowItemGroup)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .build();

        this.addEntry(logFourWindows);

        planksFourWindows = SimpleEntrySet.builder(WoodType.class, "plank_four_window",
                        BlockInit.OAK_PLANK_FOUR_WINDOW, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WindowBarred(Utils.copyPropertySafe(w.planks)))
                .addTag(modRes("windows_four"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> MacawsWindows.WindowItemGroup)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .build();

        this.addEntry(planksFourWindows);

        strippedLogFourWindow = SimpleEntrySet.builder(WoodType.class, "log_four_window", "stripped",
                        BlockInit.STRIPPED_OAK_LOG_FOUR_WINDOW, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WindowBarred(Utils.copyPropertySafe(w.log)))
                .addTag(modRes("windows_four"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> MacawsWindows.WindowItemGroup)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .build();

        this.addEntry(strippedLogFourWindow);

        louveredShutters = SimpleEntrySet.builder(WoodType.class, "louvered_shutter",
                        BlockInit.OAK_LOUVERED_SHUTTER, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Shutter(Utils.copyPropertySafe(w.planks)))
                .addTag(modRes("shutters"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/oak_louvered_shutter"))
                .setTab(() -> MacawsWindows.WindowItemGroup)
                .createPaletteFromOak(this::shutterPalette)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .build();

        this.addEntry(louveredShutters);

        shutters = SimpleEntrySet.builder(WoodType.class, "shutter",
                        BlockInit.OAK_SHUTTER, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Shutter(Utils.copyPropertySafe(w.planks)))
                .addTag(modRes("shutters"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/oak_shutter"))
                .setTab(() -> MacawsWindows.WindowItemGroup)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .build();

        this.addEntry(shutters);

        PaneWindow = SimpleEntrySet.builder(WoodType.class, "pane_window",
                        BlockInit.OAK_PANE_WINDOW, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Window(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WALLS, Registry.BLOCK_REGISTRY)
                .addTag(modRes("pane_windows"), Registry.BLOCK_REGISTRY)
                .setTab(() -> MacawsWindows.WindowItemGroup)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .build();

        this.addEntry(PaneWindow);

        strippedPaneWindow = SimpleEntrySet.builder(WoodType.class, "pane_window", "stripped",
                        BlockInit.STRIPPED_OAK_PANE_WINDOW, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Window(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WALLS, Registry.BLOCK_REGISTRY)
                .addTag(modRes("pane_windows"), Registry.BLOCK_REGISTRY)
                .setTab(() -> MacawsWindows.WindowItemGroup)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .build();

        this.addEntry(strippedPaneWindow);

        plankPaneWindow = SimpleEntrySet.builder(WoodType.class, "plank_pane_window",
                        BlockInit.OAK_PLANK_PANE_WINDOW, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Window(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WALLS, Registry.BLOCK_REGISTRY)
                .addTag(modRes("pane_windows"), Registry.BLOCK_REGISTRY)
                .setTab(() -> MacawsWindows.WindowItemGroup)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .build();

        this.addEntry(plankPaneWindow);

    }

    private void shutterPalette(Palette p) {
        p.remove(p.getLightest());
        p.remove(p.getDarkest());
        p.remove(p.getDarkest());
    }
}
