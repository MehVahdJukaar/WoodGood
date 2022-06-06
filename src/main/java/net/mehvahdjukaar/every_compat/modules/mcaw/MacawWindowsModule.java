package net.mehvahdjukaar.every_compat.modules.mcaw;

import com.mcwwindows.kikoz.MacawsWindows;
import com.mcwwindows.kikoz.init.BlockInit;
import com.mcwwindows.kikoz.objects.Blinds;
import com.mcwwindows.kikoz.objects.Parapet;
import com.mcwwindows.kikoz.objects.Window;
import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.mehvahdjukaar.selene.resourcepack.AfterLanguageLoadEvent;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

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

        BLINDS = SimpleEntrySet.builder("blinds",
                        BlockInit.OAK_BLINDS, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new Blinds())
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(MacawsWindows.Window2ItemGroup)
                .defaultRecipe()
                .build();

        this.addEntry(BLINDS);

        LOG_PARAPETS = SimpleEntrySet.builder("log_parapet",
                        BlockInit.OAK_LOG_PARAPET, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new Parapet())
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(MacawsWindows.Window2ItemGroup)
                .defaultRecipe()
                .build();

        this.addEntry(LOG_PARAPETS);

        PLANK_PARAPETS = SimpleEntrySet.builder("plank_parapet",
                        BlockInit.OAK_PLANK_PARAPET, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new Parapet())
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(MacawsWindows.Window2ItemGroup)
                .defaultRecipe()
                .build();

        this.addEntry(PLANK_PARAPETS);

        WINDOWS = SimpleEntrySet.builder("window",
                        BlockInit.OAK_WINDOW, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new Window())
                .addTag(BlockTags.WALLS, Registry.BLOCK_REGISTRY)
                .setTab(MacawsWindows.WindowItemGroup)
                .defaultRecipe()
                .setRenderType(()-> RenderType::cutout)
                .build();

        this.addEntry(WINDOWS);

        PLANK_WINDOWS = SimpleEntrySet.builder("plank_window",
                        BlockInit.OAK_PLANK_WINDOW, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new Window())
                .addTag(BlockTags.WALLS, Registry.BLOCK_REGISTRY)
                .setTab(MacawsWindows.WindowItemGroup)
                .defaultRecipe()
                .setRenderType(()-> RenderType::cutout)
                .build();

        this.addEntry(PLANK_WINDOWS);

        WINDOWS2 = SimpleEntrySet.builder("window2",
                        BlockInit.OAK_WINDOW2, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new Window())
                .addTag(BlockTags.WALLS, Registry.BLOCK_REGISTRY)
                .setTab(MacawsWindows.WindowItemGroup)
                .defaultRecipe()
                .setRenderType(()-> RenderType::cutout)
                .build();

        this.addEntry(WINDOWS2);

        PLANK_WINDOWS2 = SimpleEntrySet.builder("plank_window2",
                        BlockInit.OAK_PLANK_WINDOW2, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new Window())
                .addTag(BlockTags.WALLS, Registry.BLOCK_REGISTRY)
                .setTab(MacawsWindows.WindowItemGroup)
                .defaultRecipe()
                .setRenderType(()-> RenderType::cutout)
                .build();

        this.addEntry(PLANK_WINDOWS2);

        STRIPPED_LOG_WINDOW = SimpleEntrySet.builder("log_window", "stripped",
                        BlockInit.STRIPPED_OAK_LOG_WINDOW, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new Window())
                .addTag(BlockTags.WALLS, Registry.BLOCK_REGISTRY)
                .setTab(MacawsWindows.WindowItemGroup)
                .defaultRecipe()
                .setRenderType(()-> RenderType::cutout)
                .build();

        this.addEntry(STRIPPED_LOG_WINDOW);

        STRIPPED_LOG_WINDOW2 = SimpleEntrySet.builder("log_window2", "stripped",
                        BlockInit.STRIPPED_OAK_LOG_WINDOW2, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new Window())
                .addTag(BlockTags.WALLS, Registry.BLOCK_REGISTRY)
                .setTab(MacawsWindows.WindowItemGroup)
                .defaultRecipe()
                .setRenderType(()-> RenderType::cutout)
                .build();

        this.addEntry(STRIPPED_LOG_WINDOW2);
    }
}
