package net.mehvahdjukaar.every_compat.modules.create;

import com.simibubi.create.CreateClient;
import com.simibubi.create.content.palettes.ConnectedGlassPaneBlock;
import com.simibubi.create.content.palettes.WindowBlock;
import com.simibubi.create.foundation.block.connected.*;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.core.Registry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.Tags;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class CreateModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> WINDOWS;
    public final SimpleEntrySet<WoodType, Block> WINDOW_PANES;


    public CreateModule(String modId) {
        super(modId, "c");

        WINDOWS = SimpleEntrySet.builder(WoodType.class, "window",
                        () -> getModBlock("oak_window"), () -> WoodTypeRegistry.OAK_TYPE, //AllPaletteBlocks.OAK_WINDOW
                        this::makeWindow)
                .addTag(BlockTags.IMPERMEABLE, Registry.BLOCK_REGISTRY)
                .setTab(() -> CreativeModeTab.TAB_DECORATIONS)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .createPaletteFromOak(p -> p.remove(p.getDarkest()))
                .addTextureM(EveryCompat.res("block/palettes/oak_window"), EveryCompat.res("block/palettes/oak_window_m"))
                .addTextureM(EveryCompat.res("block/palettes/oak_window_connected"), EveryCompat.res("block/palettes/oak_window_connected_m"))
                .build();

        this.addEntry(WINDOWS);


        WINDOW_PANES = SimpleEntrySet.builder(WoodType.class, "window_pane",
                        () -> getModBlock("oak_window_pane"), () -> WoodTypeRegistry.OAK_TYPE, //AllPaletteBlocks.OAK_WINDOW_PANE
                        s -> new ConnectedGlassPaneBlock(Utils.copyPropertySafe(Blocks.GLASS_PANE)))
                .addTag(Tags.Items.GLASS_PANES, Registry.BLOCK_REGISTRY)
                .setTab(() -> CreativeModeTab.TAB_DECORATIONS)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(WINDOW_PANES);

    }

    private WindowBlock makeWindow(WoodType w) {
        return new WindowBlock(Utils.copyPropertySafe(Blocks.GLASS)
                .isValidSpawn((s, l, ps, t) -> false).isRedstoneConductor((s, l, ps) -> false)
                .isSuffocating((s, l, ps) -> false).isViewBlocking((s, l, ps) -> false), false);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void onClientSetup() {
        super.onClientSetup();
        WINDOWS.blocks.forEach((w, b) -> {
            String path = "block/" + shortenedId() + "/" + w.getNamespace() + "/palettes/" + w.getTypeName() + "_window";

            CTSpriteShiftEntry spriteShift = CTSpriteShifter.getCT(AllCTTypes.VERTICAL,
                    EveryCompat.res(path), EveryCompat.res(path + "_connected"));

            CreateClient.MODEL_SWAPPER.getCustomBlockModels().register(Utils.getID(b),
                    (model) -> new CTModel(model, new HorizontalCTBehaviour(spriteShift)));
            CreateClient.MODEL_SWAPPER.getCustomBlockModels().register(Utils.getID(WINDOW_PANES.blocks.get(w)),
                    (model) -> new CTModel(model, new GlassPaneCTBehaviour(spriteShift)));
        });
    }

    public void onClientInit() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onTextureStitch);
    }


    @OnlyIn(Dist.CLIENT)
    //we could also remove this and run getCT before client setup
    public void onTextureStitch(TextureStitchEvent.Pre event) {
        if (event.getAtlas().location().equals(TextureAtlas.LOCATION_BLOCKS)) {
            WINDOWS.blocks.forEach((w, b) -> {
                String path = "block/" + shortenedId() + "/" + w.getNamespace() + "/palettes/" + w.getTypeName() + "_window_connected";
                event.addSprite(EveryCompat.res(path));
            });
        }
    }

}