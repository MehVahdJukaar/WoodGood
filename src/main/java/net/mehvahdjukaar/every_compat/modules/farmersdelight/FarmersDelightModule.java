package net.mehvahdjukaar.every_compat.modules.farmersdelight;

import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.misc.Utils;
import net.mehvahdjukaar.every_compat.modules.CompatModule;
import net.mehvahdjukaar.every_compat.modules.another_furniture.CompatShelfBlockTile;
import net.mehvahdjukaar.selene.block_set.BlockType;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.mehvahdjukaar.selene.client.asset_generators.LangBuilder;
import net.mehvahdjukaar.selene.client.asset_generators.textures.Palette;
import net.mehvahdjukaar.selene.client.asset_generators.textures.Respriter;
import net.mehvahdjukaar.selene.client.asset_generators.textures.TextureImage;
import net.mehvahdjukaar.selene.items.WoodBasedBlockItem;
import net.mehvahdjukaar.selene.resourcepack.AfterLanguageLoadEvent;
import net.mehvahdjukaar.selene.resourcepack.DynamicLanguageManager;
import net.mehvahdjukaar.selene.resourcepack.RPUtils;
import net.mehvahdjukaar.selene.resourcepack.ResType;
import net.mehvahdjukaar.selene.resourcepack.resources.TagBuilder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.IForgeRegistry;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.block.CabinetBlock;
import vectorwing.farmersdelight.common.block.entity.CabinetBlockEntity;

import java.util.*;

public class FarmersDelightModule extends CompatModule {

    public FarmersDelightModule(String modId) {
        super(modId);
    }

    @Override
    public String shortenedId() {
        return "fd";
    }

    public static final String CABINET_NAME = "cabinet";
    public static final Map<WoodType, Block> CABINETS = new HashMap<>();
    public static final Map<WoodType, Item> CABINET_ITEMS = new HashMap<>();
    public static BlockEntityType<CompatCabinetBlockTile> COMPAT_CABINET_TILE = null;

    @Override
    public void registerWoodBlocks(IForgeRegistry<Block> registry, Collection<WoodType> woodTypes) {
        addChildToOak(shortenedId() + "/cabinet", "oak_cabinet");
        for (WoodType w : woodTypes) {
            String name = makeBlockId(w, CABINET_NAME);
            if (w.isVanilla() || isEntryAlreadyRegistered(name, registry)) continue;
            Block block = new CompatCabinetBlock(BlockBehaviour.Properties.copy(w.planks).strength(2.5F));
            CABINETS.put(w, block);
            registry.register(block.setRegistryName(WoodGood.res(name)));
            w.addChild(shortenedId() + "/cabinet", block);
        }
    }

    @Override
    public void registerItems(IForgeRegistry<Item> registry) {
        CABINETS.forEach((w, value) -> {
            Item i = new WoodBasedBlockItem(value, new Item.Properties().tab(FarmersDelight.CREATIVE_TAB), w);
            CABINET_ITEMS.put(w, i);
            registry.register(i.setRegistryName(value.getRegistryName()));
        });
    }

    @Override
    public void registerTiles(IForgeRegistry<BlockEntityType<?>> registry) {
        COMPAT_CABINET_TILE = BlockEntityType.Builder.of(CompatCabinetBlockTile::new,
                CABINETS.values().toArray(Block[]::new)).build(null);
        registry.register(COMPAT_CABINET_TILE.setRegistryName("fd_cabinet"));
    }

    //loot table and tags
    @Override
    public void addStaticServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
        var pack = handler.dynamicPack;
        CABINETS.forEach((wood, value) -> pack.addSimpleBlockLootTable(value));
        TagBuilder cabinets = TagBuilder.of(modRes("cabinets")).addEntries(CABINETS.values());
        pack.addTag(cabinets, Registry.BLOCK_REGISTRY);
        pack.addTag(cabinets, Registry.ITEM_REGISTRY);
    }

    //recipes
    @Override
    public void addDynamicServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
        Utils.addWoodRecipes(modId, manager, handler.dynamicPack, CABINETS, "oak_cabinet");
    }

    //models
    @Override
    public void addStaticClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        Utils.addBlockResources(modId, manager, handler.dynamicPack, CABINETS, "oak_cabinet",
                ResType.ITEM_MODELS.getPath(modRes("oak_cabinet")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_cabinet")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_cabinet_open")),
                ResType.BLOCKSTATES.getPath(modRes("oak_cabinet"))
        );
    }

    //translations
    @Override
    public void addTranslations(ClientDynamicResourcesHandler clientDynamicResourcesHandler, AfterLanguageLoadEvent lang) {
        CABINETS.forEach((w, v) -> LangBuilder.addDynamicEntry(lang, "block.wood_good.cabinet",(BlockType) w, v));
    }

    //textures
    @Override
    public void addDynamicClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {

        try (TextureImage front = TextureImage.open(manager, WoodGood.res("block/cabinet_front"));
             TextureImage front_m = TextureImage.open(manager, WoodGood.res("block/cabinet_front_mask"));
             TextureImage open = TextureImage.open(manager, WoodGood.res("block/cabinet_front_open"));
             TextureImage open_m = TextureImage.open(manager, WoodGood.res("block/cabinet_front_open_mask"));
             TextureImage side = TextureImage.open(manager, WoodGood.res("block/cabinet_side"));
             TextureImage top = TextureImage.open(manager, WoodGood.res("block/cabinet_top"))) {

            Respriter respriterFront = Respriter.masked(front, front_m);
            Respriter respriterOpen = Respriter.masked(open, open_m);
            Respriter respriterSide = Respriter.of(side);
            Respriter respriterTop = Respriter.of(top);

            CABINETS.forEach((wood, table) -> {

                String id = table.getRegistryName().getPath();

                try (TextureImage plankTexture = TextureImage.open(manager,
                        RPUtils.findFirstBlockTextureLocation(manager, wood.planks))) {

                    List<Palette> targetPalette = Palette.fromAnimatedImage(plankTexture);

                    addWoodTexture(wood, handler, manager, "block/" + id + "_front", () ->
                            respriterFront.recolorWithAnimation(targetPalette, plankTexture.getMetadata()));

                    addWoodTexture(wood, handler, manager, "block/" + id + "_top", () ->
                            respriterTop.recolorWithAnimation(targetPalette, plankTexture.getMetadata()));

                    addWoodTexture(wood, handler, manager, "block/" + id + "_side", () ->
                            respriterSide.recolorWithAnimation(targetPalette, plankTexture.getMetadata()));

                    targetPalette.forEach(Palette::increaseDown);

                    addWoodTexture(wood, handler, manager, "block/" + id + "_front_open", () ->
                            respriterOpen.recolorWithAnimation(targetPalette, plankTexture.getMetadata()));

                } catch (Exception ex) {
                    handler.getLogger().error("Failed to generate Cabinet block texture for for {} : {}", table, ex);
                }
            });
        } catch (Exception ex) {
            handler.getLogger().error("Could not generate any Cabinet block texture : ", ex);
        }
    }


}

