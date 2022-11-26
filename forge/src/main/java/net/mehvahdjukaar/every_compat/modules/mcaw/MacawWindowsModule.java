package net.mehvahdjukaar.every_compat.modules.mcaw;

import com.mcwwindows.kikoz.MacawsWindows;
import com.mcwwindows.kikoz.init.BlockInit;
import com.mcwwindows.kikoz.objects.Blinds;
import com.mcwwindows.kikoz.objects.Parapet;
import com.mcwwindows.kikoz.objects.Window;
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
    public final SimpleEntrySet<WoodType, Block> WINDOWS;
    public final SimpleEntrySet<WoodType, Block> WINDOWS2;
    public final SimpleEntrySet<WoodType, Block> PLANK_WINDOWS;
    public final SimpleEntrySet<WoodType, Block> PLANK_PARAPETS;
    public final SimpleEntrySet<WoodType, Block> PLANK_WINDOWS2;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_LOG_WINDOW;
    public final SimpleEntrySet<WoodType, Block> STRIPPED_LOG_WINDOW2;


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

        PLANK_PARAPETS = SimpleEntrySet.builder(WoodType.class, "plank_parapet",
                        BlockInit.OAK_PLANK_PARAPET, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Parapet(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.2F, 1.0F)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(() -> MacawsWindows.Window2ItemGroup)
                .defaultRecipe()
                .build();

        this.addEntry(PLANK_PARAPETS);

        WINDOWS = SimpleEntrySet.builder(WoodType.class, "window",
                        BlockInit.OAK_WINDOW, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Window(BlockBehaviour.Properties.of(Material.WOOD).strength(0.6F, 1.2F)))
                .addTag(BlockTags.WALLS, Registry.BLOCK_REGISTRY)
                .setTab(() -> MacawsWindows.WindowItemGroup)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(WINDOWS);

        PLANK_WINDOWS = SimpleEntrySet.builder(WoodType.class, "plank_window",
                        BlockInit.OAK_PLANK_WINDOW, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Window(BlockBehaviour.Properties.of(Material.WOOD).strength(0.6F, 1.2F)))
                .addTag(BlockTags.WALLS, Registry.BLOCK_REGISTRY)
                .setTab(() -> MacawsWindows.WindowItemGroup)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(PLANK_WINDOWS);

        WINDOWS2 = SimpleEntrySet.builder(WoodType.class, "window2",
                        BlockInit.OAK_WINDOW2, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Window(BlockBehaviour.Properties.of(Material.WOOD).strength(0.6F, 1.2F)))
                .addTag(BlockTags.WALLS, Registry.BLOCK_REGISTRY)
                .setTab(() -> MacawsWindows.WindowItemGroup)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(WINDOWS2);

        PLANK_WINDOWS2 = SimpleEntrySet.builder(WoodType.class, "plank_window2",
                        BlockInit.OAK_PLANK_WINDOW2, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Window(BlockBehaviour.Properties.of(Material.WOOD).strength(0.6F, 1.2F)))
                .addTag(BlockTags.WALLS, Registry.BLOCK_REGISTRY)
                .setTab(() -> MacawsWindows.WindowItemGroup)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(PLANK_WINDOWS2);

        STRIPPED_LOG_WINDOW = SimpleEntrySet.builder(WoodType.class, "log_window", "stripped",
                        BlockInit.STRIPPED_OAK_LOG_WINDOW, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Window(BlockBehaviour.Properties.of(Material.WOOD).strength(0.6F, 1.2F)))
                .addTag(BlockTags.WALLS, Registry.BLOCK_REGISTRY)
                .setTab(() -> MacawsWindows.WindowItemGroup)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(STRIPPED_LOG_WINDOW);

        STRIPPED_LOG_WINDOW2 = SimpleEntrySet.builder(WoodType.class, "log_window2", "stripped",
                        BlockInit.STRIPPED_OAK_LOG_WINDOW2, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Window(BlockBehaviour.Properties.of(Material.WOOD).strength(0.6F, 1.2F)))
                .addTag(BlockTags.WALLS, Registry.BLOCK_REGISTRY)
                .setTab(() -> MacawsWindows.WindowItemGroup)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(STRIPPED_LOG_WINDOW2);
    }
}
