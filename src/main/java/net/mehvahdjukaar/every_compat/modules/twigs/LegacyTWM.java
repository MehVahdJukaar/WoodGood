package net.mehvahdjukaar.every_compat.modules.twigs;

import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.misc.Utils;
import net.mehvahdjukaar.every_compat.modules.CompatModule;
import net.mehvahdjukaar.selene.block_set.BlockType;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.mehvahdjukaar.selene.client.asset_generators.LangBuilder;
import net.mehvahdjukaar.selene.client.asset_generators.textures.Palette;
import net.mehvahdjukaar.selene.client.asset_generators.textures.Respriter;
import net.mehvahdjukaar.selene.client.asset_generators.textures.TextureImage;
import net.mehvahdjukaar.selene.items.WoodBasedBlockItem;
import net.mehvahdjukaar.selene.resourcepack.AfterLanguageLoadEvent;
import net.mehvahdjukaar.selene.resourcepack.RPUtils;
import net.mehvahdjukaar.selene.resourcepack.resources.TagBuilder;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.IForgeRegistry;
import net.moddingplayground.twigs.Twigs;
import net.moddingplayground.twigs.block.TableBlock;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Deprecated
public class LegacyTWM extends CompatModule {

    public LegacyTWM(String modId) {
        super(modId);
    }

    @Override
    public String shortenedId() {
        return "tw";
    }

    public static final String TABLE_NAME = "table";
    public static final Map<WoodType, Block> TABLES = new HashMap<>();
    public static final Map<WoodType, Item> TABLE_ITEMS = new HashMap<>();

    @Override
    public void registerWoodBlocks(IForgeRegistry<Block> registry, Collection<WoodType> woodTypes) {
        addChildToOak(shortenedId() + "/table", "oak_table");
        for (WoodType w : woodTypes) {
            String name = makeBlockId(w, TABLE_NAME);
            if (w.isVanilla() || isEntryAlreadyRegistered(name, registry)) continue;

            Block block = new TableBlock(BlockBehaviour.Properties.copy(w.planks).instabreak());
            TABLES.put(w, block);
            registry.register(block.setRegistryName(WoodGood.res(name)));
            w.addChild(shortenedId() + "/table", block);
        }
    }

    @Override
    public void registerItems(IForgeRegistry<Item> registry) {
        TABLES.forEach((w, value) -> {
            Item i = new WoodBasedBlockItem(value, new Item.Properties().tab(Twigs.ITEM_GROUP),w);
            TABLE_ITEMS.put(w, i);
            registry.register(i.setRegistryName(value.getRegistryName()));
        });
    }

    @Override
    public void onClientSetup() {
        TABLES.values().forEach(t -> ItemBlockRenderTypes.setRenderLayer(t, RenderType.cutout()));
    }

    //loot table and tags
    @Override
    public void addStaticServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
        var pack = handler.dynamicPack;
        TABLES.forEach((wood, value) -> pack.addSimpleBlockLootTable(value));
        TagBuilder tables = TagBuilder.of(modRes("tables")).addEntries(TABLES.values());
        pack.addTag(tables, Registry.BLOCK_REGISTRY);
        pack.addTag(tables, Registry.ITEM_REGISTRY);
    }

    @Override
    public String getModId() {
        return super.getModId();
    }

    //recipes
    @Override
    public void addDynamicServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
        Utils.addWoodRecipes(modId, manager, handler.dynamicPack, TABLE_ITEMS, "table/oak_table");
    }

    //models
    @Override
    public void addStaticClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {

        Utils.addStandardResources(modId, manager, handler.dynamicPack, TABLES,WoodType.OAK_WOOD_TYPE);
        /*
        Utils.addBlockResources(modId, manager, handler.dynamicPack, TABLES, "oak_table",
                ResType.ITEM_MODELS.getPath(modRes("oak_table")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_table")),
                ResType.BLOCKSTATES.getPath(modRes("oak_table"))
        );
        */
    }

    //translations
    @Override
    public void addTranslations(ClientDynamicResourcesHandler clientDynamicResourcesHandler, AfterLanguageLoadEvent lang) {
        TABLES.forEach((w, v) -> LangBuilder.addDynamicEntry(lang, "block.wood_good.table",(BlockType) w, v));
    }

    //textures
    @Override
    public void addDynamicClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {


        try (TextureImage main = TextureImage.open(manager,
                modRes("block/oak_table"));
             TextureImage top = TextureImage.open(manager,
                     modRes("block/oak_table_top"));
             TextureImage bottom = TextureImage.open(manager,
                     modRes("block/oak_table_bottom"))) {

            Respriter respriterMain = Respriter.of(main);
            Respriter respriterTop = Respriter.of(top);
            Respriter respriterBottom = Respriter.of(bottom);

            TABLES.forEach((wood, table) -> {

                String id = table.getRegistryName().getPath();

                try (TextureImage plankTexture = TextureImage.open(manager,
                        RPUtils.findFirstBlockTextureLocation(manager, wood.planks))) {

                    List<Palette> targetPalette = Palette.fromAnimatedImage(plankTexture);
                    targetPalette.forEach(p -> p.remove(p.getDarkest()));

                    addWoodTexture(wood, handler, manager, "block/" + id, () ->
                            respriterMain.recolorWithAnimation(targetPalette, plankTexture.getMetadata()));

                    addWoodTexture(wood, handler, manager, "block/" + id + "_top", () ->
                            respriterTop.recolorWithAnimation(targetPalette, plankTexture.getMetadata()));

                    addWoodTexture(wood, handler, manager, "block/" + id + "_bottom", () ->
                            respriterBottom.recolorWithAnimation(targetPalette, plankTexture.getMetadata()));

                } catch (Exception ex) {
                    handler.getLogger().error("Failed to generate Table block texture for for {} : {}", table, ex);
                }
            });
        } catch (Exception ex) {
            handler.getLogger().error("Could not generate any Table block texture : ", ex);
        }
    }


}
