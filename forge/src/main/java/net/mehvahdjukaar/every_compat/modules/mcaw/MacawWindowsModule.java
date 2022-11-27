package net.mehvahdjukaar.every_compat.modules.mcaw;

import com.mcwwindows.kikoz.MacawsWindows;
import com.mcwwindows.kikoz.init.BlockInit;
import com.mcwwindows.kikoz.objects.Blinds;
import com.mcwwindows.kikoz.objects.Parapet;
import com.mcwwindows.kikoz.objects.Window;
import com.mcwwindows.kikoz.objects.WindowBarred;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class MacawWindowsModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> BLINDS;
    public final SimpleEntrySet<WoodType, Block> LOG_PARAPETS;
    public final SimpleEntrySet<WoodType, Block> LOG_WINDOWS;
    public final SimpleEntrySet<WoodType, Block> LOG_PANE_WINDOWS;
    public final SimpleEntrySet<WoodType, Block> LOG_FOUR_WINDOWS;
    public final SimpleEntrySet<WoodType, Block> PLANKS_WINDOWS;
    public final SimpleEntrySet<WoodType, Block> PLANKS_FOUR_WINDOWS;
    public final SimpleEntrySet<WoodType, Block> PLANKS_PARAPETS;
    public final SimpleEntrySet<WoodType, Block> PLANKS_PANE_WINDOWS;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_LOG_WINDOWS;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_LOG_PANE_WINDOWS;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_LOG_FOUR_WINDOW;


    public MacawWindowsModule(String modId) {
        super(modId, "mcw");

        BLINDS = SimpleEntrySet.builder(WoodType.class, "blinds",
                        BlockInit.OAK_BLINDS, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Blinds())
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> MacawsWindows.Window2ItemGroup)
                .defaultRecipe()
                .build();

        this.addEntry(BLINDS);

        LOG_PARAPETS = SimpleEntrySet.builder(WoodType.class, "log_parapet",
                        BlockInit.OAK_LOG_PARAPET, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Parapet(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.2F, 1.0F)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> MacawsWindows.Window2ItemGroup)
                .defaultRecipe()
                .build();

        this.addEntry(LOG_PARAPETS);

        PLANKS_PARAPETS = SimpleEntrySet.builder(WoodType.class, "plank_parapet",
                        BlockInit.OAK_PLANK_PARAPET, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Parapet(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.2F, 1.0F)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> MacawsWindows.Window2ItemGroup)
                .defaultRecipe()
                .build();

        this.addEntry(PLANKS_PARAPETS);

        LOG_WINDOWS = SimpleEntrySet.builder(WoodType.class, "window",
                        BlockInit.OAK_WINDOW, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Window(BlockBehaviour.Properties.of(Material.WOOD).strength(0.6F, 1.2F)))
                .addTag(modRes("windows"), Registry.BLOCK_REGISTRY)
                .setTab(() -> MacawsWindows.WindowItemGroup)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(LOG_WINDOWS);

        PLANKS_WINDOWS = SimpleEntrySet.builder(WoodType.class, "plank_window",
                        BlockInit.OAK_PLANK_WINDOW, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Window(BlockBehaviour.Properties.of(Material.WOOD).strength(0.6F, 1.2F)))
                .addTag(modRes("windows"), Registry.BLOCK_REGISTRY)
                .setTab(() -> MacawsWindows.WindowItemGroup)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(PLANKS_WINDOWS);

        LOG_PANE_WINDOWS = SimpleEntrySet.builder(WoodType.class, "window2",
                        BlockInit.OAK_WINDOW2, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WindowBarred(BlockBehaviour.Properties.of(Material.WOOD).strength(0.6F, 1.2F)))
                .addTag(modRes("windows_two"), Registry.BLOCK_REGISTRY)
                .setTab(() -> MacawsWindows.WindowItemGroup)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(LOG_PANE_WINDOWS);

        PLANKS_PANE_WINDOWS = SimpleEntrySet.builder(WoodType.class, "plank_window2",
                        BlockInit.OAK_PLANK_WINDOW2, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WindowBarred(BlockBehaviour.Properties.of(Material.WOOD).strength(0.6F, 1.2F)))
                .addTag(modRes("windows_two"), Registry.BLOCK_REGISTRY)
                .setTab(() -> MacawsWindows.WindowItemGroup)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(PLANKS_PANE_WINDOWS);

        STRIPPED_LOG_WINDOWS = SimpleEntrySet.builder(WoodType.class, "log_window", "stripped",
                        BlockInit.STRIPPED_OAK_LOG_WINDOW, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Window(BlockBehaviour.Properties.of(Material.WOOD).strength(0.6F, 1.2F)))
                .addTag(modRes("windows"), Registry.BLOCK_REGISTRY)
                .setTab(() -> MacawsWindows.WindowItemGroup)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(STRIPPED_LOG_WINDOWS);

        STRIPPED_LOG_PANE_WINDOWS = SimpleEntrySet.builder(WoodType.class, "log_window2", "stripped",
                        BlockInit.STRIPPED_OAK_LOG_WINDOW2, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WindowBarred(BlockBehaviour.Properties.of(Material.WOOD).strength(0.6F, 1.2F)))
                .addTag(modRes("windows_two"), Registry.BLOCK_REGISTRY)
                .setTab(() -> MacawsWindows.WindowItemGroup)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(STRIPPED_LOG_PANE_WINDOWS);

        LOG_FOUR_WINDOWS = SimpleEntrySet.builder(WoodType.class, "four_window",
                        BlockInit.OAK_FOUR_WINDOW, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WindowBarred(BlockBehaviour.Properties.of(Material.WOOD).strength(0.6F, 1.2F)))
                .addTag(modRes("windows_four"), Registry.BLOCK_REGISTRY)
                .setTab(() -> MacawsWindows.WindowItemGroup)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(LOG_FOUR_WINDOWS);

        PLANKS_FOUR_WINDOWS = SimpleEntrySet.builder(WoodType.class, "plank_four_window",
                        BlockInit.OAK_PLANK_FOUR_WINDOW, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WindowBarred(BlockBehaviour.Properties.of(Material.WOOD).strength(0.6F, 1.2F)))
                .addTag(modRes("windows_four"), Registry.BLOCK_REGISTRY)
                .setTab(() -> MacawsWindows.WindowItemGroup)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(PLANKS_FOUR_WINDOWS);

        STRIPPED_LOG_FOUR_WINDOW = SimpleEntrySet.builder(WoodType.class, "log_four_window", "stripped",
                        BlockInit.STRIPPED_OAK_LOG_FOUR_WINDOW, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WindowBarred(BlockBehaviour.Properties.of(Material.WOOD).strength(0.6F, 1.2F)))
                .addTag(modRes("windows_four"), Registry.BLOCK_REGISTRY)
                .setTab(() -> MacawsWindows.WindowItemGroup)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(STRIPPED_LOG_FOUR_WINDOW);
    }
}
