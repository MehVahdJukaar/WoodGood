package net.mehvahdjukaar.every_compat.modules.create;

import com.simibubi.create.Create;
import com.simibubi.create.CreateClient;
import com.simibubi.create.content.palettes.AllPaletteBlocks;
import com.simibubi.create.content.palettes.ConnectedGlassPaneBlock;
import com.simibubi.create.content.palettes.WindowBlock;
import com.simibubi.create.foundation.block.connected.*;
import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.core.Registry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.Tags;

public class CrS extends SimpleModule {

    public final SimpleEntrySet<WoodType, WindowBlock> WINDOWS;
 //   public final SimpleEntrySet<WoodType, ConnectedGlassPaneBlock> WINDOW_PANES;


    public CrS(String modId) {
        super(modId, "c");

        WINDOWS = SimpleEntrySet.builder("window",
                        AllPaletteBlocks.OAK_WINDOW, () -> WoodType.OAK_WOOD_TYPE,
                        this::makeWindow)
                .addTag(BlockTags.IMPERMEABLE, Registry.BLOCK_REGISTRY)
                .setTab(Create.PALETTES_CREATIVE_TAB)
                .setRenderType(() -> RenderType::cutout)
                .addMaskedTexture(WoodGood.res("block/window"), WoodGood.res("block/window_mask"))
                .addMaskedTexture(WoodGood.res("block/window_connected"), WoodGood.res("block/window_connected_mask"))
                .build();

        this.addEntry(WINDOWS);

        /*
        WINDOW_PANES = SimpleEntrySet.builder("window_pane",
                        AllPaletteBlocks.OAK_WINDOW_PANE, () -> WoodType.OAK_WOOD_TYPE,
                        s -> new ConnectedGlassPaneBlock(BlockBehaviour.Properties.copy(Blocks.GLASS_PANE)))
                .addTag(Tags.Items.GLASS_PANES, Registry.BLOCK_REGISTRY)
                .setTab(Create.PALETTES_CREATIVE_TAB)
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(WINDOW_PANES);
*/
    }

    public WindowBlock makeWindow(WoodType w) {
        return new WindowBlock(BlockBehaviour.Properties.copy(Blocks.GLASS)
                .isValidSpawn((s, l, ps, t) -> false).isRedstoneConductor((s, l, ps) -> false)
                .isSuffocating((s, l, ps) -> false).isViewBlocking((s, l, ps) -> false));
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void onClientSetup() {

    }


    @OnlyIn(Dist.CLIENT)
    //we could also remove this and run getCT before client setup
    @Override
    public void onTextureStitch(TextureStitchEvent.Pre event) {
        /*
        if (event.getAtlas().location().equals(TextureAtlas.LOCATION_BLOCKS)) {
            WINDOWS.blocks.forEach((w, b) -> {
                String path = "block/" + b.getRegistryName().getPath() + "_connected";
                event.addSprite(WoodGood.res(path));
            });
        }*/
        /*
        WINDOWS.blocks.forEach((w, b) -> {
            String path = "block/" + b.getRegistryName().getPath();

            CTSpriteShiftEntry spriteShift = CTSpriteShifter.getCT(CTSpriteShifter.CTType.VERTICAL,
                    WoodGood.res(path), WoodGood.res(path + "_connected"));

            CreateClient.MODEL_SWAPPER.getCustomBlockModels().register(b.delegate,
                    (model) -> new CTModel(model, new HorizontalCTBehaviour(spriteShift)));
            CreateClient.MODEL_SWAPPER.getCustomBlockModels().register(WINDOW_PANES.blocks.get(w).delegate,
                    (model) -> new CTModel(model, new GlassPaneCTBehaviour(spriteShift)));
        });

         */
    }
}
