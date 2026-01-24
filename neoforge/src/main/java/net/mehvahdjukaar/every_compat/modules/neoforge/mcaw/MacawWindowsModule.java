package net.mehvahdjukaar.every_compat.modules.neoforge.mcaw;

import com.mcwwindows.kikoz.objects.*;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.*;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

import static net.mehvahdjukaar.every_compat.api.PaletteStrategies.registerCached;
import static net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys.PLANKS;
import static net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys.STRIPPED_LOG;

//SUPPORT: v2.2.1+
public class MacawWindowsModule extends EveryCompatModule {

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
        ResourceLocation tab = modRes(modId);

        window = SimpleEntrySet.builder(WoodType.class, "window",
                        getModBlock("oak_window"), () -> VanillaWoodTypes.OAK,
                        w -> new ConnectedWindow(Utils.copyPropertySafe(w.log))
                )
                //TEXTURES: log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .addTag(modRes("windows"), Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .build();
        this.addEntry(window);

        window2 = SimpleEntrySet.builder(WoodType.class, "window2",
                        getModBlock("oak_window2"), () -> VanillaWoodTypes.OAK,
                        w -> new WindowBarred(Utils.copyPropertySafe(w.log))
                )
                //TEXTURES: log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .addTag(modRes("windows_two"), Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .build();
        this.addEntry(window2);

        fourWindow = SimpleEntrySet.builder(WoodType.class, "four_window",
                        getModBlock("oak_four_window"), () -> VanillaWoodTypes.OAK,
                        w -> new WindowBarred(Utils.copyPropertySafe(w.log))
                )
                //TEXTURES: log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .addTag(modRes("windows_four"), Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .build();
        this.addEntry(fourWindow);

        strippedLogWindow = SimpleEntrySet.builder(WoodType.class, "log_window", "stripped",
                        getModBlock("stripped_oak_log_window"), () -> VanillaWoodTypes.OAK,
                        w -> new ConnectedWindow(Utils.copyPropertySafe(w.log))
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                //TEXTURES: stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .addTag(modRes("windows"), Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .build();
        this.addEntry(strippedLogWindow);

        strippedLogWindow2 = SimpleEntrySet.builder(WoodType.class, "log_window2", "stripped",
                        getModBlock("stripped_oak_log_window2"), () -> VanillaWoodTypes.OAK,
                        w -> new WindowBarred(Utils.copyPropertySafe(w.log))
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                //TEXTURES: stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .addTag(modRes("windows_two"), Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .build();
        this.addEntry(strippedLogWindow2);

        strippedLogFourWindow = SimpleEntrySet.builder(WoodType.class, "log_four_window", "stripped",
                        getModBlock("stripped_oak_log_four_window"), () -> VanillaWoodTypes.OAK,
                        w -> new WindowBarred(Utils.copyPropertySafe(w.log))
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                //TEXTURES: stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .addTag(modRes("windows_four"), Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .build();
        this.addEntry(strippedLogFourWindow);

        plankWindow = SimpleEntrySet.builder(WoodType.class, "plank_window",
                        getModBlock("oak_plank_window"), () -> VanillaWoodTypes.OAK,
                        w -> new ConnectedWindow(Utils.copyPropertySafe(w.planks))
                )
                //TEXTURES: planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .addTag(modRes("windows"), Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .build();
        this.addEntry(plankWindow);

        plankWindow2 = SimpleEntrySet.builder(WoodType.class, "plank_window2",
                        getModBlock("oak_plank_window2"), () -> VanillaWoodTypes.OAK,
                        w -> new WindowBarred(Utils.copyPropertySafe(w.planks))
                )
                //TEXTURES: planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .addTag(modRes("windows_two"), Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .build();
        this.addEntry(plankWindow2);

        plankFourWindow = SimpleEntrySet.builder(WoodType.class, "plank_four_window",
                        getModBlock("oak_plank_four_window"), () -> VanillaWoodTypes.OAK,
                        w -> new WindowBarred(Utils.copyPropertySafe(w.planks))
                )
                //TEXTURES: planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .addTag(modRes("windows_four"), Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .build();
        this.addEntry(plankFourWindow);

        paneWindow = SimpleEntrySet.builder(WoodType.class, "pane_window",
                        getModBlock("oak_pane_window"), () -> VanillaWoodTypes.OAK,
                        w -> new Window(Utils.copyPropertySafe(w.log))
                )
                //TEXTURES: log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .addTag(modRes("pane_windows"), Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .build();
        this.addEntry(paneWindow);

        strippedPaneWindow = SimpleEntrySet.builder(WoodType.class, "pane_window", "stripped",
                        getModBlock("stripped_oak_pane_window"), () -> VanillaWoodTypes.OAK,
                        w -> new Window(Utils.copyPropertySafe(w.log))
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                //TEXTURES: stripped_log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .addTag(modRes("pane_windows"), Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .build();
        this.addEntry(strippedPaneWindow);

        plankPaneWindow = SimpleEntrySet.builder(WoodType.class, "plank_pane_window",
                        getModBlock("oak_plank_pane_window"), () -> VanillaWoodTypes.OAK,
                        w -> new Window(Utils.copyPropertySafe(w.planks))
                )
                //TEXTURES: planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WALLS, Registries.BLOCK)
                .addTag(modRes("pane_windows"), Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .build();
        this.addEntry(plankPaneWindow);

        logParapet = SimpleEntrySet.builder(WoodType.class, "log_parapet",
                        getModBlock("oak_log_parapet"), () -> VanillaWoodTypes.OAK,
                        w -> new Parapet(Utils.copyPropertySafe(w.log))
                )
                //TEXTURES: log
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("parapets"), Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .build();
        this.addEntry(logParapet);

        plankParapet = SimpleEntrySet.builder(WoodType.class, "plank_parapet",
                        getModBlock("oak_plank_parapet"), () -> VanillaWoodTypes.OAK,
                        w -> new Parapet(Utils.copyPropertySafe(w.planks))
                )
                //TEXTURES: planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("parapets"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(plankParapet);

        blinds = SimpleEntrySet.builder(WoodType.class, "blinds",
                        getModBlock("oak_blinds"), () -> VanillaWoodTypes.OAK,
                        w -> new Blinds(Utils.copyPropertySafe(w.planks))
                )
                .requiresChildren(STRIPPED_LOG) //REASON: textures
                //TEXTURES: log, stripped_log, planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("blinds"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(blinds);

        shutter = SimpleEntrySet.builder(WoodType.class, "shutter",
                        getModBlock("oak_shutter"), () -> VanillaWoodTypes.OAK,
                        w -> new Shutter(Utils.copyPropertySafe(w.planks))
                )
                .addTexture(modRes("block/oak_shutter"), shutterPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("shutters"), Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT)
                .defaultRecipe()
                .build();
        this.addEntry(shutter);

        louveredShutter = SimpleEntrySet.builder(WoodType.class, "louvered_shutter",
                        getModBlock("oak_louvered_shutter"), () -> VanillaWoodTypes.OAK,
                        w -> new Shutter(Utils.copyPropertySafe(w.planks))
                )
                .addTexture(modRes("block/oak_louvered_shutter"), shutterPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("shutters"), Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT)
                .defaultRecipe()
                .build();
        this.addEntry(louveredShutter);
    }

    public static final PaletteStrategy shutterPalette = registerCached((blockType, manager) -> PaletteStrategies.makePaletteFromChild(
            blockType, manager, PLANKS, null, p -> {
                int leftover = p.size() - 3;
                if (leftover > 6) {
                    p.remove(p.getLightest());
                    p.remove(p.getDarkest());
                    p.remove(p.getDarkest());
                }
            })
    );
}
