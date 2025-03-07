package net.mehvahdjukaar.every_compat.modules.neoforge.create;

import com.simibubi.create.content.decoration.palettes.ConnectedGlassPaneBlock;
import com.simibubi.create.content.decoration.palettes.WindowBlock;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;

import java.util.function.Function;

// SUPPORT: v6.0.2+
public class CreateModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> windows;
    public final SimpleEntrySet<WoodType, Block> windowPanes;

    public CreateModule(String modId) {
        super(modId, "c");
        ResourceKey<CreativeModeTab> tab = CreativeModeTabs.BUILDING_BLOCKS;

        windows = SimpleEntrySet.builder(WoodType.class, "window",
                        getModBlock("oak_window"), () -> WoodTypeRegistry.OAK_TYPE, //AllPaletteBlocks.OAK_WINDOW
                        (Function<WoodType, Block>) this::makeWindow
                )
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .createPaletteFromPlanks(p -> p.remove(p.getDarkest()))
                .addTextureM(modRes("block/palettes/oak_window"), EveryCompat.res("block/palettes/oak_window_m"))
                .addTextureM(modRes("block/palettes/oak_window_connected"), EveryCompat.res("block/palettes/oak_window_connected_m"))
                .addTag(BlockTags.IMPERMEABLE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(windows);

        windowPanes = SimpleEntrySet.builder(WoodType.class, "window_pane",
                        getModBlock("oak_window_pane"), () -> WoodTypeRegistry.OAK_TYPE, //AllPaletteBlocks.OAK_WINDOW_PANE
                        s -> new ConnectedGlassPaneBlock(Utils.copyPropertySafe(Blocks.GLASS_PANE))
                )
                .requiresFromMap(windows.blocks) //REASON: textures
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .addTag(Tags.Items.GLASS_PANES, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
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