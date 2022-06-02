package net.mehvahdjukaar.every_compat.modules.another_furniture;

import com.crispytwig.another_furniture.AnotherFurnitureMod;
import com.crispytwig.another_furniture.block.ChairBlock;
import com.crispytwig.another_furniture.block.TableBlock;
import com.crispytwig.another_furniture.render.ShelfRenderer;
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
import net.mehvahdjukaar.selene.resourcepack.*;
import net.mehvahdjukaar.selene.resourcepack.resources.TagBuilder;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.*;

public class AnotherFurnitureModule extends CompatModule {

    public AnotherFurnitureModule(String modId) {
        super(modId);
    }

    @Override
    public String shortenedId() {
        return "af";
    }

    public static final String TABLE_NAME = "table";
    public static final Map<WoodType, Block> TABLES = new HashMap<>();
    public static final Map<WoodType, Item> TABLE_TIMES = new HashMap<>();

    public static final String CHAIR_NAME = "chair";
    public static final Map<WoodType, Block> CHAIRS = new HashMap<>();
    public static final Map<WoodType, Item> CHAIR_ITEMS = new HashMap<>();

    public static final String SHELF_NAME = "shelf";
    public static final Map<WoodType, Block> SHELVES = new HashMap<>();
    public static final Map<WoodType, Item> SHELF_ITEMS = new HashMap<>();

    public static BlockEntityType<CompatShelfBlockTile> COMPAT_SHELF_TILE = null;

    @Override
    public void registerWoodBlocks(IForgeRegistry<Block> registry, Collection<WoodType> woodTypes) {

        //tables
        addChildToOak(shortenedId() + "/table", "oak_table");
        for (WoodType w : woodTypes) {
            String name = makeBlockId(w, TABLE_NAME);
            if (w.isVanilla() || isEntryAlreadyRegistered(name, registry)) continue;

            Block block = new TableBlock(BlockBehaviour.Properties.copy(w.planks).strength(2.0F, 3.0F));
            TABLES.put(w, block);
            registry.register(block.setRegistryName(WoodGood.res(name)));
            w.addChild(shortenedId() + "/table", block);
        }
        //chair
        addChildToOak(shortenedId() + "/chair", "oak_chair");
        for (WoodType w : woodTypes) {
            String name = makeBlockId(w, CHAIR_NAME);
            if (w.isVanilla() || isEntryAlreadyRegistered(name, registry)) continue;

            Block block = new ChairBlock(BlockBehaviour.Properties.copy(w.planks).strength(2.0F, 3.0F));
            CHAIRS.put(w, block);
            registry.register(block.setRegistryName(WoodGood.res(name)));
            w.addChild(shortenedId() + "/chair", block);
        }
        //shelf
        addChildToOak(shortenedId() + "/shelf", "oak_shelf");
        for (WoodType w : woodTypes) {
            String name = makeBlockId(w, SHELF_NAME);
            if (w.isVanilla() || isEntryAlreadyRegistered(name, registry)) continue;

            Block block = new CompatShelfBlock(BlockBehaviour.Properties.copy(w.planks).strength(2.0F, 3.0F));
            SHELVES.put(w, block);
            registry.register(block.setRegistryName(WoodGood.res(name)));
            w.addChild(shortenedId() + "/shelf", block);
        }
    }

    @Override
    public void registerItems(IForgeRegistry<Item> registry) {
        TABLES.forEach((w, value) -> {
            Item i = new WoodBasedBlockItem(value, new Item.Properties().tab(AnotherFurnitureMod.TAB), w);
            TABLE_TIMES.put(w, i);
            registry.register(i.setRegistryName(value.getRegistryName()));
        });
        CHAIRS.forEach((w, value) -> {
            Item i = new WoodBasedBlockItem(value, new Item.Properties().tab(AnotherFurnitureMod.TAB), w);
            CHAIR_ITEMS.put(w, i);
            registry.register(i.setRegistryName(value.getRegistryName()));
        });
        SHELVES.forEach((w, value) -> {
            Item i = new WoodBasedBlockItem(value, new Item.Properties().tab(AnotherFurnitureMod.TAB), w);
            SHELF_ITEMS.put(w, i);
            registry.register(i.setRegistryName(value.getRegistryName()));
        });
    }

    @Override
    public void registerTiles(IForgeRegistry<BlockEntityType<?>> registry) {
        COMPAT_SHELF_TILE = BlockEntityType.Builder.of(CompatShelfBlockTile::new,
                SHELVES.values().toArray(Block[]::new)).build(null);
        registry.register(COMPAT_SHELF_TILE.setRegistryName("af_shelf"));
    }

    @Override
    public void onClientSetup() {
        CHAIRS.values().forEach(t -> ItemBlockRenderTypes.setRenderLayer(t, RenderType.cutout()));
    }

    @Override
    public void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(COMPAT_SHELF_TILE, ShelfRenderer::new);
    }

    @Override
    public void addStaticServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
        var pack = handler.dynamicPack;

        TABLES.forEach((wood, value) -> pack.addSimpleBlockLootTable(value));
        TagBuilder tables = TagBuilder.of(modRes("tables")).addEntries(TABLES.values());
        pack.addTag(tables, Registry.BLOCK_REGISTRY);
        pack.addTag(tables, Registry.ITEM_REGISTRY);

        CHAIRS.forEach((wood, value) -> pack.addSimpleBlockLootTable(value));
        TagBuilder chairs = TagBuilder.of(modRes("chairs")).addEntries(CHAIRS.values());
        pack.addTag(chairs, Registry.BLOCK_REGISTRY);
        pack.addTag(chairs, Registry.ITEM_REGISTRY);

        SHELVES.forEach((wood, value) -> pack.addSimpleBlockLootTable(value));
        TagBuilder shelves = TagBuilder.of(modRes("shelves")).addEntries(SHELVES.values());
        pack.addTag(shelves, Registry.BLOCK_REGISTRY);
        pack.addTag(shelves, Registry.ITEM_REGISTRY);
    }

    @Override
    public void addDynamicServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
        Utils.addWoodRecipes(modId, manager, handler.dynamicPack, TABLES, "oak_table");
        Utils.addWoodRecipes(modId, manager, handler.dynamicPack, TABLES, "oak_shelf");
        Utils.addWoodRecipes(modId, manager, handler.dynamicPack, TABLES, "oak_chair");
    }

    @Override
    public void addStaticClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {

        Utils.addBlockResources(modId, manager, handler.dynamicPack, TABLES,
                BlockTypeResTransformer.wood(modId, manager)
                        .IDReplaceType("table/oak")
                        .replaceBlockType("table/oak"),
                ResType.BLOCK_MODELS.getPath(modRes("table/oak_leg")),
                ResType.BLOCK_MODELS.getPath(modRes("table/oak_top"))
        );
        Utils.addBlockResources(modId, manager, handler.dynamicPack, TABLES,
                BlockTypeResTransformer.wood(modId, manager)
                        .IDReplaceType("oak")
                        .replaceBlockType("table/oak"),
                ResType.ITEM_MODELS.getPath(modRes("oak_table")),
                ResType.BLOCKSTATES.getPath(modRes("oak_table"))
        );


        Utils.addBlockResources(modId, manager, handler.dynamicPack, CHAIRS,
                BlockTypeResTransformer.wood(modId, manager)
                        .IDReplaceType("chair/oak")
                        .replaceBlockType("chair/oak"),
                ResType.BLOCK_MODELS.getPath(modRes("chair/oak"))
        );
        Utils.addBlockResources(modId, manager, handler.dynamicPack, CHAIRS,
                BlockTypeResTransformer.wood(modId, manager)
                        .IDReplaceType("oak")
                        .replaceBlockType("chair/oak"),
                ResType.BLOCKSTATES.getPath(modRes("oak_chair")),
                ResType.ITEM_MODELS.getPath(modRes("oak_chair"))
        );

        Utils.addBlockResources(modId, manager, handler.dynamicPack, SHELVES,
                BlockTypeResTransformer.wood(modId, manager)
                        .IDReplaceType("shelf/oak")
                        .replaceBlockType("shelf/oak"),
                ResType.BLOCK_MODELS.getPath(modRes("shelf/oak_full")),
                ResType.BLOCK_MODELS.getPath(modRes("shelf/oak_r")),
                ResType.BLOCK_MODELS.getPath(modRes("shelf/oak_l")),
                ResType.BLOCK_MODELS.getPath(modRes("shelf/oak_top"))
        );
        Utils.addBlockResources(modId, manager, handler.dynamicPack, SHELVES,
                BlockTypeResTransformer.wood(modId, manager)
                        .IDReplaceType("oak")
                        .replaceBlockType("shelf/oak"),
                ResType.BLOCKSTATES.getPath(modRes("oak_shelf")),
                ResType.ITEM_MODELS.getPath(modRes("oak_shelf"))
        );
    }

    @Override
    public void addTranslations(ClientDynamicResourcesHandler clientDynamicResourcesHandler, AfterLanguageLoadEvent lang) {
        TABLES.forEach((w, v) -> LangBuilder.addDynamicEntry(lang, "block.wood_good.table", w, v));
        CHAIRS.forEach((w, v) -> LangBuilder.addDynamicEntry(lang, "block.wood_good.chair", w, v));
        SHELVES.forEach((w, v) -> LangBuilder.addDynamicEntry(lang, "block.wood_good.shelf", w, v));
    }


    @Override
    public void addDynamicClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        var pack = handler.dynamicPack;

        //tables
        try (TextureImage sides = TextureImage.open(manager,
                modRes("block/table/oak_sides"));
             TextureImage top = TextureImage.open(manager,
                     modRes("block/table/oak_top"))) {

            Respriter respriterSides = Respriter.of(sides);
            Respriter respriterTop = Respriter.of(top);

            TABLES.forEach((wood, table) -> {

                String id = table.getRegistryName().getPath();

                try (TextureImage plankTexture = TextureImage.open(manager,
                        RPUtils.findFirstBlockTextureLocation(manager, wood.planks))) {

                    List<Palette> targetPalette = Palette.fromAnimatedImage(plankTexture);

                    addWoodTexture(wood, handler, manager, "block/" + id.replace("table", "sides"), () ->
                            respriterSides.recolorWithAnimation(targetPalette, plankTexture.getMetadata()));

                    addWoodTexture(wood, handler, manager, "block/" + id.replace("table", "top"), () ->
                            respriterTop.recolorWithAnimation(targetPalette, plankTexture.getMetadata()));


                } catch (Exception ex) {
                    handler.getLogger().error("Failed to generate Table block texture for for {} : {}", table, ex);
                }
            });
        } catch (Exception ex) {
            handler.getLogger().error("Could not generate any Table block texture : ", ex);
        }
        //TODO: write smething that grabs the blockstate and models automatically
        //chairs
        try (TextureImage back = TextureImage.open(manager,
                modRes("block/chair/oak_back"));
             TextureImage bottom = TextureImage.open(manager,
                     modRes("block/chair/oak_bottom"));
             TextureImage seat = TextureImage.open(manager,
                     modRes("block/chair/oak_seat"))) {

            Respriter respriterBack = Respriter.of(back);
            Respriter respriterBottom = Respriter.of(bottom);
            Respriter respriterSeat = Respriter.of(seat);

            CHAIRS.forEach((wood, table) -> {

                String id = table.getRegistryName().getPath();

                try (TextureImage plankTexture = TextureImage.open(manager,
                        RPUtils.findFirstBlockTextureLocation(manager, wood.planks))) {

                    List<Palette> targetPalette = Palette.fromAnimatedImage(plankTexture);

                    addWoodTexture(wood, handler, manager, "block/" + id.replace("chair", "back"), () ->
                            respriterBack.recolorWithAnimation(targetPalette, plankTexture.getMetadata()));

                    addWoodTexture(wood, handler, manager, "block/" + id.replace("chair", "bottom"), () ->
                            respriterBottom.recolorWithAnimation(targetPalette, plankTexture.getMetadata()));

                    addWoodTexture(wood, handler, manager, "block/" + id.replace("chair", "seat"), () ->
                            respriterSeat.recolorWithAnimation(targetPalette, plankTexture.getMetadata())
                    );

                } catch (Exception ex) {
                    handler.getLogger().error("Failed to generate Chair block texture for for {} : {}", table, ex);
                }
            });
        } catch (Exception ex) {
            handler.getLogger().error("Could not generate any Chair block texture : ", ex);
        }
        //shelves
        try (TextureImage supports = TextureImage.open(manager,
                modRes("block/shelf/oak_supports"))) {

            Respriter respriter = Respriter.of(supports);

            SHELVES.forEach((wood, table) -> {

                String id = table.getRegistryName().getPath();

                try (TextureImage plankTexture = TextureImage.open(manager,
                        RPUtils.findFirstBlockTextureLocation(manager, wood.planks))) {

                    List<Palette> targetPalette = Palette.fromAnimatedImage(plankTexture);


                    addWoodTexture(wood, handler, manager, "block/" + id.replace("shelf", "supports"), () ->
                            respriter.recolorWithAnimation(targetPalette, plankTexture.getMetadata())
                    );


                } catch (Exception ex) {
                    handler.getLogger().error("Failed to generate Shelf block texture for for {} : {}", table, ex);
                }
            });
        } catch (Exception ex) {
            handler.getLogger().error("Could not generate any Shelf block texture : ", ex);
        }
    }
}
