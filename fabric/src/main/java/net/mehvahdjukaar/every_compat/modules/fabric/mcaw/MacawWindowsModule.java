package net.mehvahdjukaar.every_compat.modules.fabric.mcaw;

import net.kikoz.mcwwindows.init.BlockInit;
import net.kikoz.mcwwindows.objects.Blinds;
import net.kikoz.mcwwindows.objects.Parapet;
import net.kikoz.mcwwindows.objects.Shutter;
import net.kikoz.mcwwindows.objects.Window;
import net.kikoz.mcwwindows.objects.WindowBarred;
import net.kikoz.mcwwindows.util.BlindsGroup;
import net.kikoz.mcwwindows.util.WindowsGroup;
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

    public final SimpleEntrySet<WoodType, Block> BLINDS;
    public final SimpleEntrySet<WoodType, Block> LOG_PARAPETS;
    public final SimpleEntrySet<WoodType, Block> LOG_WINDOWS;
    public final SimpleEntrySet<WoodType, Block> LOG_PANE_WINDOWS;
    public final SimpleEntrySet<WoodType, Block> LOG_FOUR_WINDOWS;
    public final SimpleEntrySet<WoodType, Block> LOUVERED_SHUTTERS;
    public final SimpleEntrySet<WoodType, Block> PLANKS_WINDOWS;
    public final SimpleEntrySet<WoodType, Block> PLANKS_FOUR_WINDOWS;
    public final SimpleEntrySet<WoodType, Block> PLANKS_PARAPETS;
    public final SimpleEntrySet<WoodType, Block> PLANKS_PANE_WINDOWS;
    public final SimpleEntrySet<WoodType, Block> SHUTTERS;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_LOG_WINDOWS;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_LOG_PANE_WINDOWS;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_LOG_FOUR_WINDOW;
    public final SimpleEntrySet<WoodType, Block> PANE_WINDOW;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_PANE_WINDOW;
    public final SimpleEntrySet<WoodType, Block> PLANK_PANE_WINDOW;


    public MacawWindowsModule(String modId) {
        super(modId, "mcw");

        BLINDS = SimpleEntrySet.builder(WoodType.class, "blinds",
                        () -> BlockInit.OAK_BLINDS, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Blinds(Utils.copyPropertySafe(w.log).noOcclusion()))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> BlindsGroup.BLINDSGROUP)
                .defaultRecipe()
                .build();

        this.addEntry(BLINDS);

        LOG_PARAPETS = SimpleEntrySet.builder(WoodType.class, "log_parapet",
                        () -> BlockInit.OAK_LOG_PARAPET, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Parapet(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> BlindsGroup.BLINDSGROUP)
                .defaultRecipe()
                .build();

        this.addEntry(LOG_PARAPETS);

        PLANKS_PARAPETS = SimpleEntrySet.builder(WoodType.class, "plank_parapet",
                        () -> BlockInit.OAK_PLANK_PARAPET, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Parapet(Utils.copyPropertySafe(w.planks)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> BlindsGroup.BLINDSGROUP)
                .defaultRecipe()
                .build();

        this.addEntry(PLANKS_PARAPETS);

        LOG_WINDOWS = SimpleEntrySet.builder(WoodType.class, "window",
                        () -> BlockInit.OAK_WINDOW, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Window(Utils.copyPropertySafe(w.log)))
                .addTag(modRes("windows"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> WindowsGroup.WINDOWSGROUP)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .build();

        this.addEntry(LOG_WINDOWS);

        PLANKS_WINDOWS = SimpleEntrySet.builder(WoodType.class, "plank_window",
                        () -> BlockInit.OAK_PLANK_WINDOW, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Window(Utils.copyPropertySafe(w.planks)))
                .addTag(modRes("windows"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> WindowsGroup.WINDOWSGROUP)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .build();

        this.addEntry(PLANKS_WINDOWS);

        LOG_PANE_WINDOWS = SimpleEntrySet.builder(WoodType.class, "window2",
                        () -> BlockInit.OAK_WINDOW2, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WindowBarred(Utils.copyPropertySafe(w.log)))
                .addTag(modRes("windows_two"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> WindowsGroup.WINDOWSGROUP)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .build();

        this.addEntry(LOG_PANE_WINDOWS);

        PLANKS_PANE_WINDOWS = SimpleEntrySet.builder(WoodType.class, "plank_window2",
                        () -> BlockInit.OAK_PLANK_WINDOW2, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WindowBarred(Utils.copyPropertySafe(w.planks)))
                .addTag(modRes("windows_two"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> WindowsGroup.WINDOWSGROUP)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .build();

        this.addEntry(PLANKS_PANE_WINDOWS);

        STRIPPED_LOG_WINDOWS = SimpleEntrySet.builder(WoodType.class, "log_window", "stripped",
                        () -> BlockInit.STRIPPED_OAK_LOG_WINDOW, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Window(Utils.copyPropertySafe(w.log)))
                .addTag(modRes("windows"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> WindowsGroup.WINDOWSGROUP)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .build();

        this.addEntry(STRIPPED_LOG_WINDOWS);

        STRIPPED_LOG_PANE_WINDOWS = SimpleEntrySet.builder(WoodType.class, "log_window2", "stripped",
                        () -> BlockInit.STRIPPED_OAK_LOG_WINDOW2, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WindowBarred(Utils.copyPropertySafe(w.log)))
                .addTag(modRes("windows_two"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> WindowsGroup.WINDOWSGROUP)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .build();

        this.addEntry(STRIPPED_LOG_PANE_WINDOWS);

        LOG_FOUR_WINDOWS = SimpleEntrySet.builder(WoodType.class, "four_window",
                        () -> BlockInit.OAK_FOUR_WINDOW, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WindowBarred(Utils.copyPropertySafe(w.log)))
                .addTag(modRes("windows_four"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> WindowsGroup.WINDOWSGROUP)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .build();

        this.addEntry(LOG_FOUR_WINDOWS);

        PLANKS_FOUR_WINDOWS = SimpleEntrySet.builder(WoodType.class, "plank_four_window",
                        () -> BlockInit.OAK_PLANK_FOUR_WINDOW, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WindowBarred(Utils.copyPropertySafe(w.planks)))
                .addTag(modRes("windows_four"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> WindowsGroup.WINDOWSGROUP)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .build();

        this.addEntry(PLANKS_FOUR_WINDOWS);

        STRIPPED_LOG_FOUR_WINDOW = SimpleEntrySet.builder(WoodType.class, "log_four_window", "stripped",
                        () -> BlockInit.STRIPPED_OAK_LOG_FOUR_WINDOW, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WindowBarred(Utils.copyPropertySafe(w.log)))
                .addTag(modRes("windows_four"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> WindowsGroup.WINDOWSGROUP)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .build();

        this.addEntry(STRIPPED_LOG_FOUR_WINDOW);

        LOUVERED_SHUTTERS = SimpleEntrySet.builder(WoodType.class, "louvered_shutter",
                        () -> BlockInit.OAK_LOUVERED_SHUTTER, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Shutter(Utils.copyPropertySafe(w.planks)))
                .addTag(modRes("shutters"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/oak_louvered_shutter"))
                .setTab(() -> WindowsGroup.WINDOWSGROUP)
                .createPaletteFromOak(this::shutterPalette)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .build();

        this.addEntry(LOUVERED_SHUTTERS);

        SHUTTERS = SimpleEntrySet.builder(WoodType.class, "shutter",
                        () -> BlockInit.OAK_SHUTTER, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Shutter(Utils.copyPropertySafe(w.planks)))
                .addTag(modRes("shutters"), Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTexture(modRes("block/oak_shutter"))
                .setTab(() -> WindowsGroup.WINDOWSGROUP)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .build();

        this.addEntry(SHUTTERS);

        PANE_WINDOW = SimpleEntrySet.builder(WoodType.class, "pane_window",
                        () -> BlockInit.OAK_PANE_WINDOW, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Window(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WALLS, Registry.BLOCK_REGISTRY)
                .addTag(modRes("pane_windows"), Registry.BLOCK_REGISTRY)
                .setTab(() -> WindowsGroup.WINDOWSGROUP)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .build();

        this.addEntry(PANE_WINDOW);

        STRIPPED_PANE_WINDOW = SimpleEntrySet.builder(WoodType.class, "pane_window", "stripped",
                        () -> BlockInit.STRIPPED_OAK_PANE_WINDOW, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Window(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WALLS, Registry.BLOCK_REGISTRY)
                .addTag(modRes("pane_windows"), Registry.BLOCK_REGISTRY)
                .setTab(() -> WindowsGroup.WINDOWSGROUP)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .build();

        this.addEntry(STRIPPED_PANE_WINDOW);

        PLANK_PANE_WINDOW = SimpleEntrySet.builder(WoodType.class, "plank_pane_window",
                        () -> BlockInit.OAK_PLANK_PANE_WINDOW, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Window(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WALLS, Registry.BLOCK_REGISTRY)
                .addTag(modRes("pane_windows"), Registry.BLOCK_REGISTRY)
                .setTab(() -> WindowsGroup.WINDOWSGROUP)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .build();

        this.addEntry(PLANK_PANE_WINDOW);
    }

    private void shutterPalette(Palette p) {
        p.remove(p.getLightest());
        p.remove(p.getDarkest());
        p.remove(p.getDarkest());
    }
}
