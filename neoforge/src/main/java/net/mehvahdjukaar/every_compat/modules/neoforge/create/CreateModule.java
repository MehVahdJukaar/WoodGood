package net.mehvahdjukaar.every_compat.modules.neoforge.create;

import com.simibubi.create.content.decoration.palettes.ConnectedGlassPaneBlock;
import com.simibubi.create.content.decoration.palettes.WindowBlock;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.PaletteStrategies;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.misc.UtilityTag;
import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Function;

// SUPPORT: v6.0.2+
public class CreateModule extends EveryCompatModule {

    public final SimpleEntrySet<WoodType, Block> windows;
    public final SimpleEntrySet<WoodType, Block> windowPanes;

    public CreateModule(String modId) {
        super(modId, "c");
        ResourceLocation tab = modRes("palettes");

        windows = SimpleEntrySet.builder(WoodType.class, "window",
                        getModBlock("oak_window"), () -> VanillaWoodTypes.OAK,
                        (Function<WoodType, Block>) this::makeWindow
                )
                .addTextureM(modRes("block/palettes/oak_window"), EveryCompat.res("block/c/palettes/oak_window_m"), PaletteStrategies.PLANKS_REMOVE_DARKEST)
                .addTextureM(modRes("block/palettes/oak_window_connected"), EveryCompat.res("block/c/palettes/oak_window_connected_m"), PaletteStrategies.PLANKS_REMOVE_DARKEST)
                .addTag(BlockTags.IMPERMEABLE, Registries.BLOCK)
                .addTag(UtilityTag.GLASS_TAG, Registries.BLOCK, Registries.ITEM)
                .setTab(getModTab(tab))
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .build();
        this.addEntry(windows);

        windowPanes = SimpleEntrySet.builder(WoodType.class, "window_pane",
                        getModBlock("oak_window_pane"), () -> VanillaWoodTypes.OAK,
                        s -> new ConnectedGlassPaneBlock(Utils.copyPropertySafe(Blocks.GLASS_PANE))
                )
                .requiresFromMap(windows.blocks) //REASON: textures
                .addTag(UtilityTag.GLASS_PANE_TAG, Registries.BLOCK, Registries.ITEM)
                .setTab(getModTab(tab))
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .copyParentDrop() //REASON: ensure blocks' dropping when Diagonal Fences is installed
                .build();
        this.addEntry(windowPanes);

    }

    private WindowBlock makeWindow(WoodType w) {
        return new WindowBlock(Utils.copyPropertySafe(Blocks.GLASS)
                .isValidSpawn((s, l, ps, t) -> false).isRedstoneConductor((s, l, ps) -> false)
                .isSuffocating((s, l, ps) -> false).isViewBlocking((s, l, ps) -> false), false);
    }

    @Override
    public void onClientSetup() {
        super.onClientSetup();
        CreateClientModule.onClientSetup(this);
    }

}