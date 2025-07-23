package net.mehvahdjukaar.every_compat.modules.fabric.create;

import com.simibubi.create.CreateClient;
import com.simibubi.create.content.decoration.palettes.ConnectedGlassPaneBlock;
import com.simibubi.create.content.decoration.palettes.WindowBlock;
import com.simibubi.create.foundation.block.connected.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.common_classes.TagUtility;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

// SUPPORT: v0.5.1+
public class CreateModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> windows;
    public final SimpleEntrySet<WoodType, Block> windowPanes;

    public CreateModule(String modId) {
        super(modId, "c");
        var tab = modRes("palettes");

        windows = SimpleEntrySet.builder(WoodType.class, "window",
                        getModBlock("oak_window"), () -> WoodTypeRegistry.OAK_TYPE,
                        this::makeWindow
                )
                .createPaletteFromPlanks(p -> p.remove(p.getDarkest()))
                .addTextureM(modRes("block/palettes/oak_window"), EveryCompat.res("block/c/palettes/oak_window_m"))
                .addTextureM(modRes("block/palettes/oak_window_connected"), EveryCompat.res("block/c/palettes/oak_window_connected_m"))
                .addTag(BlockTags.IMPERMEABLE, Registries.BLOCK)
                .addTag(TagUtility.SILICA_TAG, Registries.BLOCK)
                .addTag(TagUtility.GLASS_TAG, Registries.BLOCK)
                .addTag(TagUtility.GLASS_COLORLESS_TAG, Registries.BLOCK)
                .addTag(TagUtility.SILICA_TAG, Registries.ITEM)
                .addTag(TagUtility.GLASS_TAG, Registries.ITEM)
                .addTag(TagUtility.GLASS_COLORLESS_TAG, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .setRenderType(RenderLayer.TRANSLUCENT)
                .build();
        this.addEntry(windows);

        windowPanes = SimpleEntrySet.builder(WoodType.class, "window_pane",
                        getModBlock("oak_window_pane"), () -> WoodTypeRegistry.OAK_TYPE,
                        s -> new ConnectedGlassPaneBlock(Utils.copyPropertySafe(Blocks.GLASS_PANE))
                )
                .requiresFromMap(windows.blocks) //REASON: textures
                .addTag(TagUtility.GLASS_PANE_TAG, Registries.BLOCK)
                .addTag(TagUtility.GLASS_PANE_COLORLESS_TAG, Registries.BLOCK)
                .addTag(TagUtility.GLASS_PANE_TAG, Registries.ITEM)
                .addTag(TagUtility.GLASS_PANE_COLORLESS_TAG, Registries.ITEM)
                .setTabKey(tab)
                .defaultRecipe()
                .setRenderType(RenderLayer.TRANSLUCENT)
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
    @Environment(EnvType.CLIENT)
    public void onClientSetup() {
        super.onClientSetup();
        CreateClientModule.clientStuff(this);
    }

    @Environment(EnvType.CLIENT)
    private static class CreateClientModule {
        private static void clientStuff(CreateModule module) {
            module.windows.blocks.forEach((w, b) -> {
                String path = "block/" + module.shortenedId() + "/" + w.getNamespace() + "/palettes/" + w.getTypeName() + "_window";

                CTSpriteShiftEntry spriteShift = CTSpriteShifter.getCT(AllCTTypes.VERTICAL,
                        EveryCompat.res(path), EveryCompat.res(path + "_connected"));

                CreateClient.MODEL_SWAPPER.getCustomBlockModels().register(Utils.getID(b),
                        (model) -> new CTModel(model, new HorizontalCTBehaviour(spriteShift)));
                CreateClient.MODEL_SWAPPER.getCustomBlockModels().register(Utils.getID(module.windowPanes.blocks.get(w)),
                        (model) -> new CTModel(model, new GlassPaneCTBehaviour(spriteShift)));
            });
        }
    }

}