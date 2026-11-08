package net.mehvahdjukaar.every_compat.modules.fabric.create;

import com.zurrtum.create.content.decoration.palettes.ConnectedGlassPaneBlock;
import com.zurrtum.create.content.decoration.palettes.WindowBlock;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.CompatModuleClient;
import net.mehvahdjukaar.every_compat.api.PaletteStrategies;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.misc.UtilityTag;
import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

// SUPPORT: Create Fly v6.0.9+
public class CreateModule extends EveryCompatModule {

    public final SimpleEntrySet<WoodType, Block> windows;
    public final SimpleEntrySet<WoodType, Block> windowPanes;

    public CreateModule(String modId) {
        super(modId, "c");
        Identifier tab = modRes("palettes");

        windows = SimpleEntrySet.builder(WoodType.class, "window",
                        getModBlock("oak_window"), () -> VanillaWoodTypes.OAK,
                        (w, p) -> new WindowBlock(p, false)
                )
                .addTextureM(modRes("block/palettes/oak_window"), EveryCompat.res("block/c/palettes/oak_window_m"), PaletteStrategies.PLANKS_REMOVE_DARKEST)
                .addTextureM(modRes("block/palettes/oak_window_connected/1"), EveryCompat.res("block/c/palettes/oak_window_connected/1_m"), PaletteStrategies.PLANKS_REMOVE_DARKEST)
                .addTextureM(modRes("block/palettes/oak_window_connected/2"), EveryCompat.res("block/c/palettes/oak_window_connected/2_m"), PaletteStrategies.PLANKS_REMOVE_DARKEST)
                .addTextureM(modRes("block/palettes/oak_window_connected/3"), EveryCompat.res("block/c/palettes/oak_window_connected/3_m"), PaletteStrategies.PLANKS_REMOVE_DARKEST)
                .addTag(BlockTags.IMPERMEABLE, Registries.BLOCK)
                .addTag(UtilityTag.GLASS_TAG, Registries.BLOCK, Registries.ITEM)
                .setTab(getTab(tab))
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .build();
        this.addEntry(windows);

        windowPanes = SimpleEntrySet.builder(WoodType.class, "window_pane",
                        getModBlock("oak_window_pane"), () -> VanillaWoodTypes.OAK,
                        (w, p) -> new ConnectedGlassPaneBlock(p)
                )
                .requiresFromMap(windows.blocks) //REASON: textures
                .addTag(UtilityTag.GLASS_PANE_TAG, Registries.BLOCK, Registries.ITEM)
                .setTab(getTab(tab))
                .defaultRecipe()
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .copyParentDrop() //REASON: ensure blocks' dropping when Diagonal Fences is installed
                .build();
        this.addEntry(windowPanes);
    }

    @Override
    public CompatModuleClient<?> createClient() {
        return new CreateClientModule(this);
    }
}
