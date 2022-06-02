package net.mehvahdjukaar.every_compat.modules.create;

import com.simibubi.create.Create;
import com.simibubi.create.CreateClient;
import com.simibubi.create.content.palettes.ConnectedGlassPaneBlock;
import com.simibubi.create.content.palettes.WindowBlock;
import com.simibubi.create.foundation.block.connected.*;
import com.simibubi.create.foundation.data.WindowGen;
import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.misc.Utils;
import net.mehvahdjukaar.every_compat.modules.CompatModule;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.mehvahdjukaar.selene.client.asset_generators.LangBuilder;
import net.mehvahdjukaar.selene.client.asset_generators.textures.Palette;
import net.mehvahdjukaar.selene.client.asset_generators.textures.Respriter;
import net.mehvahdjukaar.selene.client.asset_generators.textures.TextureImage;
import net.mehvahdjukaar.selene.items.WoodBasedBlockItem;
import net.mehvahdjukaar.selene.resourcepack.AfterLanguageLoadEvent;
import net.mehvahdjukaar.selene.resourcepack.BlockTypeResTransformer;
import net.mehvahdjukaar.selene.resourcepack.RPUtils;
import net.mehvahdjukaar.selene.resourcepack.ResType;
import net.mehvahdjukaar.selene.resourcepack.resources.TagBuilder;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlas;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateModule extends CompatModule {

    public CreateModule(String modId) {
        super(modId);
    }

    @Override
    public String shortenedId() {
        return "c";
    }

    public static final String WINDOW_NAME = "window";
    public static final Map<WoodType, Block> WINDOWS = new HashMap<>();
    public static final Map<WoodType, Item> WINDOW_ITEMS = new HashMap<>();

    public static final String WINDOW_PANE_NAME = "window_pane";
    public static final Map<WoodType, Block> WINDOW_PANES = new HashMap<>();
    public static final Map<WoodType, Item> WINDOW_PANE_ITEMS = new HashMap<>();

    @Override
    public void registerWoodBlocks(IForgeRegistry<Block> registry, Collection<WoodType> woodTypes) {
        addChildToOak(shortenedId() + "/window", "oak_window");
        addChildToOak(shortenedId() + "/window_pane", "oak_window_pane");
        for (WoodType w : woodTypes) {
            String name = makeBlockId(w, WINDOW_NAME);
            if (w.isVanilla() || isEntryAlreadyRegistered(name, registry)) continue;

            WindowBlock block = new WindowBlock(BlockBehaviour.Properties.copy(Blocks.GLASS)
                    .isValidSpawn((s, l, ps, t) -> false).isRedstoneConductor((s, l, ps) -> false)
                    .isSuffocating((s, l, ps) -> false).isViewBlocking((s, l, ps) -> false));
            WINDOWS.put(w, block);
            registry.register(block.setRegistryName(WoodGood.res(name)));
            w.addChild(shortenedId() + "/window", block);

            ConnectedGlassPaneBlock pane = new ConnectedGlassPaneBlock(BlockBehaviour.Properties.copy(Blocks.GLASS_PANE));
            WINDOW_PANES.put(w, pane);
            registry.register(pane.setRegistryName(WoodGood.res(name + "_pane")));
            w.addChild(shortenedId() + "/window_pane", pane);
        }
    }

    @Override
    public void registerItems(IForgeRegistry<Item> registry) {
        WINDOWS.forEach((w, value) -> {
            Item i = new WoodBasedBlockItem(value, new Item.Properties().tab(Create.PALETTES_CREATIVE_TAB), w, 0);
            WINDOW_ITEMS.put(w, i);
            registry.register(i.setRegistryName(value.getRegistryName()));
        });
        WINDOW_PANES.forEach((w, value) -> {
            Item i = new WoodBasedBlockItem(value, new Item.Properties().tab(Create.PALETTES_CREATIVE_TAB), w, 0);
            WINDOW_PANE_ITEMS.put(w, i);
            registry.register(i.setRegistryName(value.getRegistryName()));
        });
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void onClientSetup() {
        WINDOWS.forEach((w, b) -> {
            String path = "block/" + b.getRegistryName().getPath();

            CTSpriteShiftEntry spriteShift = CTSpriteShifter.getCT(CTSpriteShifter.CTType.VERTICAL,
                    WoodGood.res(path), WoodGood.res(path + "_connected"));

            CreateClient.MODEL_SWAPPER.getCustomBlockModels().register(b.delegate,
                    (model) -> new CTModel(model, new HorizontalCTBehaviour(spriteShift)));
            CreateClient.MODEL_SWAPPER.getCustomBlockModels().register(WINDOW_PANES.get(w).delegate,
                    (model) -> new CTModel(model, new GlassPaneCTBehaviour(spriteShift)));
            ItemBlockRenderTypes.setRenderLayer(b, RenderType.cutoutMipped());
        });
        WINDOW_PANES.forEach((w, b) -> {
            ItemBlockRenderTypes.setRenderLayer(b, RenderType.cutoutMipped());
        });
    }

    @OnlyIn(Dist.CLIENT)
    //we could also remove this and run getCT before client setup
    @Override
    public void onTextureStitch(TextureStitchEvent.Pre event) {
        if (event.getAtlas().location().equals(TextureAtlas.LOCATION_BLOCKS)) {
            WINDOWS.forEach((w, b) -> {
                String path = "block/" + b.getRegistryName().getPath() + "_connected";
                event.addSprite(WoodGood.res(path));
            });
        }
    }

    @Override
    public void addStaticServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
        var pack = handler.dynamicPack;
        pack.addTag(TagBuilder.of(BlockTags.IMPERMEABLE).addEntries(WINDOWS.values()), Registry.BLOCK_REGISTRY);
        pack.addTag(TagBuilder.of(Tags.Items.GLASS_PANES).addEntries(WINDOW_PANES.values()), Registry.BLOCK_REGISTRY);
    }

    @Override
    public void addDynamicServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
        Utils.addBlockResources(modId, manager, handler.dynamicPack, WINDOWS, "oak_window",
                ResType.BLOCK_LOOT_TABLES.getPath(modRes("oak_window"))
        );
        Utils.addBlockResources(modId, manager, handler.dynamicPack, WINDOW_PANES, "oak_window_pane",
                ResType.BLOCK_LOOT_TABLES.getPath(modRes("oak_window_pane"))
        );
        Utils.addWoodRecipes(modId, manager, handler.dynamicPack, WINDOWS, "oak_window");
        Utils.addWoodRecipes(modId, manager, handler.dynamicPack, WINDOW_PANES, "oak_window_pane");
    }

    @Override
    public void addStaticClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        Utils.addBlockResources(modId, manager, handler.dynamicPack, WINDOWS,
                BlockTypeResTransformer.wood(modId, manager)
                        .IDReplaceBlock("oak_window")
                        .replaceSimpleBlock(modId, "oak_window")
                        .replaceBlockType("palettes/oak")
                        .replaceOakPlanks(),
                ResType.ITEM_MODELS.getPath(modRes("oak_window")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_window")),
                ResType.BLOCKSTATES.getPath(modRes("oak_window"))
        );
        Utils.addBlockResources(modId, manager, handler.dynamicPack, WINDOW_PANES,
                BlockTypeResTransformer.wood(modId, manager)
                        .IDReplaceBlock("oak_window_pane")
                        .replaceSimpleBlock(modId, "oak_window_pane")
                        .replaceBlockType("palettes/oak")
                        .replaceOakPlanks(),
                ResType.ITEM_MODELS.getPath(modRes("oak_window_pane")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_window_pane_noside")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_window_pane_noside_alt")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_window_pane_post")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_window_pane_side")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_window_pane_side_alt")),
                ResType.BLOCKSTATES.getPath(modRes("oak_window_pane"))
        );
    }

    @Override
    public void addDynamicClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {

        try (TextureImage window = TextureImage.open(manager, WoodGood.res("block/window"));
             TextureImage window_m = TextureImage.open(manager, WoodGood.res("block/window_mask"));
             TextureImage connected = TextureImage.open(manager, WoodGood.res("block/window_connected"));
             TextureImage connected_m = TextureImage.open(manager, WoodGood.res("block/window_connected_mask"))) {

            Respriter respriterWindow = Respriter.masked(window, window_m);
            Respriter respriterConnected = Respriter.masked(connected, connected_m);

            WINDOWS.forEach((wood, table) -> {

                String id = table.getRegistryName().getPath();

                try (TextureImage plankTexture = TextureImage.open(manager,
                        RPUtils.findFirstBlockTextureLocation(manager, wood.planks))) {

                    List<Palette> targetPalette = Palette.fromAnimatedImage(plankTexture);
                    targetPalette.forEach(p -> p.remove(p.getDarkest()));

                    addWoodTexture(wood, handler, manager, "block/" + id, () ->
                            respriterWindow.recolorWithAnimation(targetPalette, plankTexture.getMetadata()));

                    addWoodTexture(wood, handler, manager, "block/" + id + "_connected", () ->
                            respriterConnected.recolorWithAnimation(targetPalette, plankTexture.getMetadata()));

                } catch (Exception ex) {
                    handler.getLogger().error("Failed to generate Table block texture for for {} : {}", table, ex);
                }
            });
        } catch (Exception ex) {
            handler.getLogger().error("Could not generate any Table block texture : ", ex);
        }
    }

    @Override
    public void addTranslations(ClientDynamicResourcesHandler clientDynamicResourcesHandler, AfterLanguageLoadEvent lang) {
        WINDOW_PANES.forEach((w, v) -> LangBuilder.addDynamicEntry(lang, "block.wood_good.window_pane", w, v));
        WINDOWS.forEach((w, v) -> LangBuilder.addDynamicEntry(lang, "block.wood_good.window", w, v));
    }
}
