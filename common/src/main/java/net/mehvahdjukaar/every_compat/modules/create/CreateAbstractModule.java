package net.mehvahdjukaar.every_compat.modules.create;

import com.simibubi.create.content.decoration.palettes.WindowBlock;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.PaletteStrategies;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

// SUPPORT: check for their supported version in FABRIC or FORGE
public abstract class CreateAbstractModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> windows;
    public final SimpleEntrySet<WoodType, Block> windowPanes;

    public CreateAbstractModule(String modId) {
        super(modId, "c");
        ResourceLocation tab = modRes("palettes");

        windows = SimpleEntrySet.builder(WoodType.class, "window",
                        getModBlock("oak_window"), () -> VanillaWoodTypes.OAK,
                        this::makeWindow
                )
                .addTextureM(modRes("block/palettes/oak_window"),
                        EveryCompat.res("block/c/palettes/oak_window_m"),
                        PaletteStrategies.PLANKS_REMOVE_DARKEST)
                .addTextureM(modRes("block/palettes/oak_window_connected"),
                        EveryCompat.res("block/c/palettes/oak_window_connected_m"),
                        PaletteStrategies.PLANKS_REMOVE_DARKEST)
                .addTag(BlockTags.IMPERMEABLE, Registries.BLOCK)
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .build();
        this.addEntry(windows);

        windowPanes = SimpleEntrySet.builder(WoodType.class, "window_pane",
                        getModBlock("oak_window_pane"), () -> VanillaWoodTypes.OAK,
                        this::makeConnectedGlassPane
                )
                .requiresFromMap(windows.blocks) //REASON: textures
                .setTabKey(tab)
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .defaultRecipe()
                .copyParentDrop() //REASON: ensure blocks' dropping when Diagonal Fences is installed
                .build();
        this.addEntry(windowPanes);

    }

    protected abstract WindowBlock makeWindow(WoodType woodType);
    protected abstract Block makeConnectedGlassPane(WoodType woodType);

}