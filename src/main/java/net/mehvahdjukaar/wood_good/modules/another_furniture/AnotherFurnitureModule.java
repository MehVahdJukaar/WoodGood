package net.mehvahdjukaar.wood_good.modules.another_furniture;

import com.crispytwig.another_furniture.AnotherFurnitureMod;
import com.crispytwig.another_furniture.block.ChairBlock;
import com.crispytwig.another_furniture.block.TableBlock;
import com.crispytwig.another_furniture.block.entity.ShelfBlockEntity;
import com.crispytwig.another_furniture.init.ModBlockEntities;
import com.crispytwig.another_furniture.init.ModBlocks;
import com.crispytwig.another_furniture.init.ModItems;
import com.crispytwig.another_furniture.render.ShelfRenderer;
import com.mojang.datafixers.types.Type;
import lilypuree.decorative_blocks.core.DBItems;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.mehvahdjukaar.selene.resourcepack.RPUtils;
import net.mehvahdjukaar.selene.resourcepack.ResType;
import net.mehvahdjukaar.selene.resourcepack.asset_generators.LangBuilder;
import net.mehvahdjukaar.selene.resourcepack.asset_generators.textures.Palette;
import net.mehvahdjukaar.selene.resourcepack.asset_generators.textures.Respriter;
import net.mehvahdjukaar.selene.resourcepack.asset_generators.textures.TextureImage;
import net.mehvahdjukaar.wood_good.WoodGood;
import net.mehvahdjukaar.wood_good.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.wood_good.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.wood_good.modules.CompatModule;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
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
        for (WoodType w : woodTypes) {
            String name = makeBlockId(w, TABLE_NAME);
            if (w.isVanilla() || !shouldRegisterEntry(name, registry)) continue;

            Block block = new TableBlock(BlockBehaviour.Properties.copy(w.planks).strength(2.0F, 3.0F));
            TABLES.put(w, block);
            registry.register(block.setRegistryName(WoodGood.res(name)));
        }
        //chair
        for (WoodType w : woodTypes) {
            String name = makeBlockId(w, CHAIR_NAME);
            if (w.isVanilla() || !shouldRegisterEntry(name, registry)) continue;

            Block block = new ChairBlock(BlockBehaviour.Properties.copy(w.planks).strength(2.0F, 3.0F));
            CHAIRS.put(w, block);
            registry.register(block.setRegistryName(WoodGood.res(name)));
        }
        //shelf
        for (WoodType w : woodTypes) {
            String name = makeBlockId(w, SHELF_NAME);
            if (w.isVanilla() || !shouldRegisterEntry(name, registry)) continue;

            Block block = new CompatShelfBlock(BlockBehaviour.Properties.copy(w.planks).strength(2.0F, 3.0F));
            SHELVES.put(w, block);
            registry.register(block.setRegistryName(WoodGood.res(name)));
        }
    }

    @Override
    public void registerItems(IForgeRegistry<Item> registry) {
        TABLES.forEach((key, value) -> {
            Item i = new BlockItem(value, new Item.Properties().tab(AnotherFurnitureMod.TAB));
            TABLE_TIMES.put(key, i);
            registry.register(i.setRegistryName(value.getRegistryName()));
        });
        CHAIRS.forEach((key, value) -> {
            Item i = new BlockItem(value, new Item.Properties().tab(AnotherFurnitureMod.TAB));
            CHAIR_ITEMS.put(key, i);
            registry.register(i.setRegistryName(value.getRegistryName()));
        });
        SHELVES.forEach((key, value) -> {
            Item i = new BlockItem(value, new Item.Properties().tab(AnotherFurnitureMod.TAB));
            SHELF_ITEMS.put(key, i);
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
    public void onClientSetup(FMLClientSetupEvent event) {
         CHAIRS.values().forEach(t-> ItemBlockRenderTypes.setRenderLayer(t, RenderType.cutout()));
    }

    @Override
    public void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(COMPAT_SHELF_TILE, ShelfRenderer::new);
    }

    @Override
    public void addStaticServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
        var pack = handler.dynamicPack;
        List<ResourceLocation> tables = new ArrayList<>();
        TABLES.forEach((wood, value) -> {
            pack.addSimpleBlockLootTable(value);
            tables.add(value.getRegistryName());
        });
        pack.addTag(modRes("tables"), tables, Registry.BLOCK_REGISTRY);
        pack.addTag(modRes("tables"), tables, Registry.ITEM_REGISTRY);

        List<ResourceLocation> chairs = new ArrayList<>();
        CHAIRS.forEach((wood, value) -> {
            pack.addSimpleBlockLootTable(value);
            chairs.add(value.getRegistryName());
        });
        pack.addTag(modRes("chairs"), chairs, Registry.BLOCK_REGISTRY);
        pack.addTag(modRes("chairs"), chairs, Registry.ITEM_REGISTRY);

        List<ResourceLocation> shelves = new ArrayList<>();
        SHELVES.forEach((wood, value) -> {
            pack.addSimpleBlockLootTable(value);
            shelves.add(value.getRegistryName());
        });
        pack.addTag(modRes("shelves"), shelves, Registry.BLOCK_REGISTRY);
        pack.addTag(modRes("shelves"), shelves, Registry.ITEM_REGISTRY);
    }

    @Override
    public void addStaticClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager, LangBuilder langBuilder) {
        //beams
        TABLES.forEach((w, v) -> langBuilder.addEntry(v, w.getNameForTranslation(TABLE_NAME)));
        CHAIRS.forEach((w, v) -> langBuilder.addEntry(v, w.getNameForTranslation(CHAIR_NAME)));
        SHELVES.forEach((w, v) -> langBuilder.addEntry(v, w.getNameForTranslation(SHELF_NAME)));


        this.addBlockResources(manager, handler, TABLES, (s, id) ->
                        s.replace("another_furniture:block/table", "wood_good:block/table")
                                .replace("another_furniture:textures", "wood_good:textures")
                                .replace("table/oak", id.replace("_table", "")),
                (s, id) -> s.replace("oak", id.replace("_table", "")),
                ResType.BLOCK_MODELS.getPath(modRes("table/oak_leg")),
                ResType.BLOCK_MODELS.getPath(modRes("table/oak_top")),
                ResType.ITEM_MODELS.getPath(modRes("oak_table"))

        );

        this.addBlockResources(manager, handler, TABLES, (s, id) ->
                        s.replace("another_furniture", "wood_good")
                                .replace("table/oak", "table/"+id.replace("_table", "")),
                (s, id) -> s.replace("oak", id.replace("_table", "")),
                ResType.BLOCKSTATES.getPath(modRes("oak_table"))
        );

        //chairs
        this.addBlockResources(manager, handler, CHAIRS, (s, id) ->
                        s.replace("another_furniture:block/chair", "wood_good:block/chair")
                                .replace("another_furniture:textures", "wood_good:textures")
                                .replace("chair/oak", id.replace("_chair", "")),
                (s, id) -> s.replace("oak", id.replace("_chair", "")),
                ResType.BLOCK_MODELS.getPath(modRes("chair/oak")),
                ResType.ITEM_MODELS.getPath(modRes("oak_chair"))

        );

        this.addBlockResources(manager, handler, CHAIRS, (s, id) ->
                        s.replace("another_furniture", "wood_good")
                                .replace("chair/oak", "chair/"+id.replace("_chair", "")),
                (s, id) -> s.replace("oak", id.replace("_chair", "")),
                ResType.BLOCKSTATES.getPath(modRes("oak_chair"))
        );


        //shelves
        this.addBlockResources(manager, handler, CHAIRS, (s, id) ->
                        s.replace("another_furniture:block/shelf", "wood_good:block/chair")
                                .replace("another_furniture:textures", "wood_good:textures")
                                .replace("shelf/oak", id.replace("_shelf", "")),
                (s, id) -> s.replace("oak", id.replace("_chair", "")),
                ResType.BLOCK_MODELS.getPath(modRes("shelf/oak_full")),
                ResType.BLOCK_MODELS.getPath(modRes("shelf/oak_f")),
                ResType.BLOCK_MODELS.getPath(modRes("shelf/oak_l")),
                ResType.ITEM_MODELS.getPath(modRes("oak_shelf"))

        );

        this.addBlockResources(manager, handler, CHAIRS, (s, id) ->
                        s.replace("another_furniture", "wood_good")
                                .replace("shelf/oak", "shelf/"+id.replace("_shelf", "")),
                (s, id) -> s.replace("oak", id.replace("_shelf", "")),
                ResType.BLOCKSTATES.getPath(modRes("oak_shelf"))
        );


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

                    handler.addTextureIfNotPresent(manager, "block/" + id.replace("table", "sides"), () ->
                            respriterSides.recolorWithAnimation(targetPalette, plankTexture.getMetadata()));

                    handler.addTextureIfNotPresent(manager, "block/" + id.replace("table", "top"), () ->
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

                    handler.addTextureIfNotPresent(manager, "block/" + id.replace("chair", "back"), () ->
                            respriterBack.recolorWithAnimation(targetPalette, plankTexture.getMetadata()));

                    handler.addTextureIfNotPresent(manager, "block/" + id.replace("chair", "bottom"), () ->
                            respriterBottom.recolorWithAnimation(targetPalette, plankTexture.getMetadata()));

                    handler.addTextureIfNotPresent(manager, "block/" + id.replace("chair", "seat"), () ->
                            respriterSeat.recolorWithAnimation(targetPalette, plankTexture.getMetadata()));

                } catch (Exception ex) {
                    handler.getLogger().error("Failed to generate Chair block texture for for {} : {}", table, ex);
                }
            });
        } catch (Exception ex) {
            handler.getLogger().error("Could not generate any Chair block texture : ", ex);
        }
    }
}
