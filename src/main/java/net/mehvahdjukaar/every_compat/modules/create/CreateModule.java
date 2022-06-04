package net.mehvahdjukaar.every_compat.modules.create;

import com.mojang.datafixers.util.Pair;
import com.simibubi.create.Create;
import com.simibubi.create.CreateClient;
import com.simibubi.create.content.palettes.AllPaletteBlocks;
import com.simibubi.create.content.palettes.ConnectedGlassPaneBlock;
import com.simibubi.create.content.palettes.WindowBlock;
import com.simibubi.create.foundation.block.connected.*;
import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.mehvahdjukaar.selene.client.asset_generators.textures.Palette;
import net.mehvahdjukaar.selene.client.asset_generators.textures.TextureImage;
import net.mehvahdjukaar.selene.resourcepack.RPUtils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.metadata.animation.AnimationMetadataSection;
import net.minecraft.core.Registry;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

public class CreateModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, WindowBlock> WINDOWS;
    public final SimpleEntrySet<WoodType, ConnectedGlassPaneBlock> WINDOW_PANES;


    public CreateModule(String modId) {
        super(modId, "c");

        WINDOWS = SimpleEntrySet.builder("window",
                        AllPaletteBlocks.OAK_WINDOW, () -> WoodType.OAK_WOOD_TYPE,
                        this::makeWindow)
                .addTag(BlockTags.IMPERMEABLE, Registry.BLOCK_REGISTRY)
                .setTab(Create.PALETTES_CREATIVE_TAB)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .createPaletteFromOak(p -> p.remove(p.getDarkest()))
                .addMaskedTexture(WoodGood.res("block/palettes/oak_window"), WoodGood.res("block/palettes/oak_window_m"))
                .addMaskedTexture(WoodGood.res("block/palettes/oak_window_connected"), WoodGood.res("block/palettes/oak_window_connected_m"))
                .build();

        this.addEntry(WINDOWS);


        WINDOW_PANES = SimpleEntrySet.builder("window_pane",
                        AllPaletteBlocks.OAK_WINDOW_PANE, () -> WoodType.OAK_WOOD_TYPE,
                        s -> new ConnectedGlassPaneBlock(BlockBehaviour.Properties.copy(Blocks.GLASS_PANE)))
                .addTag(Tags.Items.GLASS_PANES, Registry.BLOCK_REGISTRY)
                .setTab(Create.PALETTES_CREATIVE_TAB)
                .defaultRecipe()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(WINDOW_PANES);

    }

    private WindowBlock makeWindow(WoodType w) {
        return new WindowBlock(BlockBehaviour.Properties.copy(Blocks.GLASS)
                .isValidSpawn((s, l, ps, t) -> false).isRedstoneConductor((s, l, ps) -> false)
                .isSuffocating((s, l, ps) -> false).isViewBlocking((s, l, ps) -> false));
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void onClientSetup() {
        super.onClientSetup();
        WINDOWS.blocks.forEach((w, b) -> {
            String path = "block/" + shortenedId() + "/" + w.getNamespace() + "/palettes/" + w.getTypeName() + "_window";

            CTSpriteShiftEntry spriteShift = CTSpriteShifter.getCT(CTSpriteShifter.CTType.VERTICAL,
                    WoodGood.res(path), WoodGood.res(path + "_connected"));

            CreateClient.MODEL_SWAPPER.getCustomBlockModels().register(b.delegate,
                    (model) -> new CTModel(model, new HorizontalCTBehaviour(spriteShift)));
            CreateClient.MODEL_SWAPPER.getCustomBlockModels().register(WINDOW_PANES.blocks.get(w).delegate,
                    (model) -> new CTModel(model, new GlassPaneCTBehaviour(spriteShift)));
        });
    }

    @OnlyIn(Dist.CLIENT)
    //we could also remove this and run getCT before client setup
    @Override
    public void onTextureStitch(TextureStitchEvent.Pre event) {
        if (event.getAtlas().location().equals(TextureAtlas.LOCATION_BLOCKS)) {
            WINDOWS.blocks.forEach((w, b) -> {
                String path = "block/" + shortenedId() + "/" + w.getNamespace() + "/palettes/" + w.getTypeName() + "_window_connected";
                event.addSprite(WoodGood.res(path));
            });
        }
    }

}
