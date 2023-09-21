package net.mehvahdjukaar.every_compat.modules.fabric.mcaw;

import net.kikoz.mcwwindows.MacawsWindows;
import net.kikoz.mcwwindows.init.BlockInit;
import net.kikoz.mcwwindows.objects.Blinds;
import net.kikoz.mcwwindows.objects.ConnectedWindow;
import net.kikoz.mcwwindows.objects.Parapet;
import net.kikoz.mcwwindows.objects.Shutter;
import net.kikoz.mcwwindows.objects.Window;
import net.kikoz.mcwwindows.objects.WindowBarred;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import java.util.function.Supplier;

public class MacawWindowsModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> window;
    public final SimpleEntrySet<WoodType, Block> window2;
    public final SimpleEntrySet<WoodType, Block> fourWindow;
    public final SimpleEntrySet<WoodType, Block> strippedLogWindow;
    public final SimpleEntrySet<WoodType, Block> strippedLogWindow2;
    public final SimpleEntrySet<WoodType, Block> strippedLogFourWindow;
    public final SimpleEntrySet<WoodType, Block> plankWindow;
    public final SimpleEntrySet<WoodType, Block> plankWindow2;
    public final SimpleEntrySet<WoodType, Block> plankFourWindow;
    public final SimpleEntrySet<WoodType, Block> paneWindow;
    public final SimpleEntrySet<WoodType, Block> strippedPaneWindow;
    public final SimpleEntrySet<WoodType, Block> plankPaneWindow;
    public final SimpleEntrySet<WoodType, Block> logParapet;
    public final SimpleEntrySet<WoodType, Block> plankParapet;
    public final SimpleEntrySet<WoodType, Block> blinds;
    public final SimpleEntrySet<WoodType, Block> shutter;
    public final SimpleEntrySet<WoodType, Block> louveredShutter;


    public MacawWindowsModule(String modId) {
        super(modId, "mcw");
        final Supplier<ResourceKey<CreativeModeTab>> tab = () -> MacawsWindows.WINDOWSGROUP;

        window = SimpleEntrySet.builder(WoodType.class, "window",
                        () -> BlockInit.OAK_WINDOW, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ConnectedWindow(Utils.copyPropertySafe(w.log)))
                .addTag(modRes("windows"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .build();

        this.addEntry(window);

        window2 = SimpleEntrySet.builder(WoodType.class, "window2",
                        () -> BlockInit.OAK_WINDOW2, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WindowBarred(Utils.copyPropertySafe(w.log)))
                .addTag(modRes("windows_two"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .build();

        this.addEntry(window2);

        fourWindow = SimpleEntrySet.builder(WoodType.class, "four_window",
                        () -> BlockInit.OAK_FOUR_WINDOW, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WindowBarred(Utils.copyPropertySafe(w.log)))
                .addTag(modRes("windows_four"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .build();

        this.addEntry(fourWindow);

        strippedLogWindow = SimpleEntrySet.builder(WoodType.class, "log_window", "stripped",
                        () -> BlockInit.STRIPPED_OAK_LOG_WINDOW, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ConnectedWindow(Utils.copyPropertySafe(w.log)))
                .addTag(modRes("windows"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .build();

        this.addEntry(strippedLogWindow);

        strippedLogWindow2 = SimpleEntrySet.builder(WoodType.class, "log_window2", "stripped",
                        () -> BlockInit.STRIPPED_OAK_LOG_WINDOW2, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WindowBarred(Utils.copyPropertySafe(w.log)))
                .addTag(modRes("windows_two"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .build();

        this.addEntry(strippedLogWindow2);

        strippedLogFourWindow = SimpleEntrySet.builder(WoodType.class, "log_four_window", "stripped",
                        () -> BlockInit.STRIPPED_OAK_LOG_FOUR_WINDOW, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WindowBarred(Utils.copyPropertySafe(w.log)))
                .addTag(modRes("windows_four"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .build();

        this.addEntry(strippedLogFourWindow);

        plankWindow = SimpleEntrySet.builder(WoodType.class, "plank_window",
                        () -> BlockInit.OAK_PLANK_WINDOW, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ConnectedWindow(Utils.copyPropertySafe(w.planks)))
                .addTag(modRes("windows"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .build();

        this.addEntry(plankWindow);

        plankWindow2 = SimpleEntrySet.builder(WoodType.class, "plank_window2",
                        () -> BlockInit.OAK_PLANK_WINDOW2, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WindowBarred(Utils.copyPropertySafe(w.planks)))
                .addTag(modRes("windows_two"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .build();

        this.addEntry(plankWindow2);

        plankFourWindow = SimpleEntrySet.builder(WoodType.class, "plank_four_window",
                        () -> BlockInit.OAK_PLANK_FOUR_WINDOW, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WindowBarred(Utils.copyPropertySafe(w.planks)))
                .addTag(modRes("windows_four"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .build();

        this.addEntry(plankFourWindow);

        paneWindow = SimpleEntrySet.builder(WoodType.class, "pane_window",
                        () -> BlockInit.OAK_PANE_WINDOW, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Window(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .addTag(modRes("pane_windows"), Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .build();

        this.addEntry(paneWindow);

        strippedPaneWindow = SimpleEntrySet.builder(WoodType.class, "pane_window", "stripped",
                        () -> BlockInit.STRIPPED_OAK_PANE_WINDOW, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Window(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .addTag(modRes("pane_windows"), Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .build();

        this.addEntry(strippedPaneWindow);

        plankPaneWindow = SimpleEntrySet.builder(WoodType.class, "plank_pane_window",
                        () -> BlockInit.OAK_PLANK_PANE_WINDOW, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Window(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .addTag(modRes("pane_windows"), Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .build();

        this.addEntry(plankPaneWindow);

        logParapet = SimpleEntrySet.builder(WoodType.class, "log_parapet",
                        () -> BlockInit.OAK_LOG_PARAPET, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Parapet(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();

        this.addEntry(logParapet);

        plankParapet = SimpleEntrySet.builder(WoodType.class, "plank_parapet",
                        () -> BlockInit.OAK_PLANK_PARAPET, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Parapet(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();

        this.addEntry(plankParapet);

        blinds = SimpleEntrySet.builder(WoodType.class, "blinds",
                        () -> BlockInit.OAK_BLINDS, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Blinds(Utils.copyPropertySafe(w.planks).noOcclusion()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();

        this.addEntry(blinds);

        shutter = SimpleEntrySet.builder(WoodType.class, "shutter",
                        () -> BlockInit.OAK_SHUTTER, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Shutter(Utils.copyPropertySafe(w.planks)))
                .addTag(modRes("shutters"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTexture(modRes("block/oak_shutter"))
                .setTabKey(tab)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .build();

        this.addEntry(shutter);

        louveredShutter = SimpleEntrySet.builder(WoodType.class, "louvered_shutter",
                        () -> BlockInit.OAK_LOUVERED_SHUTTER, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Shutter(Utils.copyPropertySafe(w.planks)))
                .addTag(modRes("shutters"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTexture(modRes("block/oak_louvered_shutter"))
                .setTabKey(tab)
                .createPaletteFromOak(this::shutterPalette)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .build();

        this.addEntry(louveredShutter);
    }

    private void shutterPalette(Palette p) {
        p.remove(p.getLightest());
        p.remove(p.getDarkest());
        p.remove(p.getDarkest());
    }
}
