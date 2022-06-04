package net.mehvahdjukaar.every_compat.modules.deco_block;

import com.google.common.base.Stopwatch;
import lilypuree.decorative_blocks.blocks.types.WoodDecorativeBlockTypes;
import lilypuree.decorative_blocks.core.DBBlocks;
import lilypuree.decorative_blocks.core.DBItems;
import lilypuree.decorative_blocks.items.SeatItem;
import lilypuree.decorative_blocks.items.SupportItem;
import net.mehvahdjukaar.every_compat.misc.Utils;
import net.mehvahdjukaar.selene.block_set.BlockType;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.mehvahdjukaar.selene.client.asset_generators.LangBuilder;
import net.mehvahdjukaar.selene.client.asset_generators.textures.Palette;
import net.mehvahdjukaar.selene.client.asset_generators.textures.Respriter;
import net.mehvahdjukaar.selene.client.asset_generators.textures.SpriteUtils;
import net.mehvahdjukaar.selene.client.asset_generators.textures.TextureImage;
import net.mehvahdjukaar.selene.items.WoodBasedBlockItem;
import net.mehvahdjukaar.selene.resourcepack.*;
import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.modules.CompatModule;

import net.mehvahdjukaar.selene.resourcepack.resources.TagBuilder;
import net.minecraft.client.renderer.block.model.BlockModelDefinition;
import net.minecraft.core.Registry;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.*;

@Deprecated
public class LegacyDBM extends CompatModule {

    public LegacyDBM(String modId) {
        super(modId);
    }

    @Override
    public String shortenedId() {
        return "db";
    }

    private final Map<WoodType, DBWoodType> DB_WOOD_TYPES = new HashMap<>();

    public static final String PALISADE_NAME = "palisade";
    public static final Map<WoodType, Block> PALISADES = new HashMap<>();
    public static final Map<WoodType, Item> PALISADE_ITEMS = new HashMap<>();

    public static final String BEAM_NAME = "beam";
    public static final Map<WoodType, Block> BEAMS = new HashMap<>();
    public static final Map<WoodType, Item> BEAM_ITEMS = new HashMap<>();

    public static final String SUPPORT_NAME = "support";
    public static final Map<WoodType, Block> SUPPORTS = new HashMap<>();
    public static final Map<WoodType, Item> SUPPORTS_ITEMS = new HashMap<>();

    public static final String SEAT_NAME = "seat";
    public static final Map<WoodType, Block> SEATS = new HashMap<>();
    public static final Map<WoodType, Item> SEAT_ITEMS = new HashMap<>();

    @Override
    public void registerWoodBlocks(IForgeRegistry<Block> registry, Collection<WoodType> woodTypes) {
        woodTypes.forEach(w -> DB_WOOD_TYPES.put(w, new DBWoodType(w)));

        //beams
        addChildToOak(shortenedId() + "/beam", "oak_beam");
        for (WoodType w : woodTypes) {
            String name = makeBlockId(w, BEAM_NAME);
            if (w.isVanilla() || isEntryAlreadyRegistered(name, registry)) continue;

            DBWoodType wood = DB_WOOD_TYPES.get(w);
            Block block = DBBlocks.createDecorativeBlock(wood, WoodDecorativeBlockTypes.BEAM);
            BEAMS.put(w, block);
            registry.register(block.setRegistryName(WoodGood.res(name)));
            w.addChild(this.shortenedId() + "/beam", block);
        }
        //palisades
        addChildToOak(shortenedId() + "/palisade", "oak_palisade");
        for (WoodType w : woodTypes) {
            String name = makeBlockId(w, PALISADE_NAME);
            if (w.isVanilla() || isEntryAlreadyRegistered(name, registry)) continue;

            DBWoodType wood = DB_WOOD_TYPES.get(w);
            Block block = DBBlocks.createDecorativeBlock(wood, WoodDecorativeBlockTypes.PALISADE);
            PALISADES.put(w, block);
            registry.register(block.setRegistryName(WoodGood.res(name)));
            w.addChild(this.shortenedId() + "/palisade", block);
        }
        //supports
        addChildToOak(shortenedId() + "/support", "oak_support");
        for (WoodType w : woodTypes) {
            String name = makeBlockId(w, SUPPORT_NAME);
            if (w.isVanilla() || isEntryAlreadyRegistered(name, registry)) continue;

            DBWoodType wood = DB_WOOD_TYPES.get(w);
            Block block = DBBlocks.createDecorativeBlock(wood, WoodDecorativeBlockTypes.SUPPORT);
            SUPPORTS.put(w, block);
            registry.register(block.setRegistryName(WoodGood.res(name)));
            w.addChild(this.shortenedId() + "/support", block);
        }
        //seats
        addChildToOak(shortenedId() + "/seat", "oak_seat");
        for (WoodType w : woodTypes) {
            String name = makeBlockId(w, SEAT_NAME);
            if (w.isVanilla() || isEntryAlreadyRegistered(name, registry)) continue;

            DBWoodType wood = DB_WOOD_TYPES.get(w);
            Block block = DBBlocks.createDecorativeBlock(wood, WoodDecorativeBlockTypes.SEAT);
            SEATS.put(w, block);
            registry.register(block.setRegistryName(WoodGood.res(name)));
            w.addChild(this.shortenedId() + "/seat", block);
        }
    }

    @Override
    public void registerItems(IForgeRegistry<Item> registry) {
        BEAMS.forEach((w, value) -> {
            Item i = new WoodBasedBlockItem(value, DBItems.modItemProperties, w);
            BEAM_ITEMS.put(w, i);
            registry.register(i.setRegistryName(value.getRegistryName()));
        });
        PALISADES.forEach((w, value) -> {
            Item i = new WoodBasedBlockItem(value, DBItems.modItemProperties, w);
            PALISADE_ITEMS.put(w, i);
            registry.register(i.setRegistryName(value.getRegistryName()));
        });
        SUPPORTS.forEach((w, value) -> {
            Item i = new SupportItem(value, DBItems.modItemProperties);
            SUPPORTS_ITEMS.put(w, i);
            registry.register(i.setRegistryName(value.getRegistryName()));
        });
        SEATS.forEach((w, value) -> {
            Item i = new SeatItem(value, DBItems.modItemProperties);
            SEAT_ITEMS.put(w, i);
            registry.register(i.setRegistryName(value.getRegistryName()));
        });
    }

    @Override
    public void addStaticServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
        var pack = handler.dynamicPack;
        BEAMS.forEach((wood, value) -> pack.addSimpleBlockLootTable(value));
        TagBuilder beams = TagBuilder.of(modRes("beams")).addEntries(BEAMS.values());
        pack.addTag(beams, Registry.BLOCK_REGISTRY);
        pack.addTag(beams, Registry.ITEM_REGISTRY);

        PALISADES.forEach((wood, value) -> pack.addSimpleBlockLootTable(value));
        TagBuilder palisades = TagBuilder.of(modRes("palisades")).addEntries(PALISADES.values());
        pack.addTag(palisades, Registry.BLOCK_REGISTRY);
        pack.addTag(palisades, Registry.ITEM_REGISTRY);

        SUPPORTS.forEach((wood, value) -> pack.addSimpleBlockLootTable(value));
        TagBuilder supports = TagBuilder.of(modRes("supports")).addEntries(SUPPORTS.values());
        pack.addTag(supports, Registry.BLOCK_REGISTRY);
        pack.addTag(supports, Registry.ITEM_REGISTRY);

        SEATS.forEach((wood, value) -> pack.addSimpleBlockLootTable(value));
        TagBuilder seats = TagBuilder.of(modRes("seats")).addEntries(SEATS.values());
        pack.addTag(seats, Registry.BLOCK_REGISTRY);
        pack.addTag(seats, Registry.ITEM_REGISTRY);
    }

    @Override
    public void addDynamicServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
        Utils.addWoodRecipes(modId, manager, handler.dynamicPack, BEAMS, "oak_beam");
        Utils.addWoodRecipes(modId, manager, handler.dynamicPack, PALISADES, "oak_palisade");
        Utils.addWoodRecipes(modId, manager, handler.dynamicPack, SUPPORTS, "oak_support");
        Utils.addWoodRecipes(modId, manager, handler.dynamicPack, SEATS, "oak_seat");
    }

    @Override
    public void addStaticClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        Stopwatch wa = Stopwatch.createStarted();
        /*
        Utils.addBlockResources(modId, manager, handler.dynamicPack, BEAMS, "oak_beam",
                ResType.ITEM_MODELS.getPath(modRes("oak_beam")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_beam_y")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_beam_x")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_beam_z")),
                ResType.BLOCKSTATES.getPath(modRes("oak_beam"))
        );
        Utils.addBlockResources(modId, manager, handler.dynamicPack, PALISADES, "oak_palisade",
                ResType.ITEM_MODELS.getPath(modRes("oak_palisade")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_palisade_inventory")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_palisade_post")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_palisade_side")),
                ResType.BLOCKSTATES.getPath(modRes("oak_palisade"))
        );

        Utils.addBlockResources(modId, manager, handler.dynamicPack, SEATS, "oak_seat",
                ResType.ITEM_MODELS.getPath(modRes("oak_seat")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_seat")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_seat_inventory")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_seat_post")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_seat_post_inventory")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_seat_top_post")),
                ResType.BLOCKSTATES.getPath(modRes("oak_seat"))
        );

        Utils.addBlockResources(modId, manager, handler.dynamicPack, SUPPORTS,
                BlockTypeResTransformer.wood(modId, manager)
                        .IDReplaceType("oak")
                        .replaceBlockType("oak")
                        .addModifier((s, id, w) -> s),
                ResType.ITEM_MODELS.getPath(modRes("oak_support")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_support_horizontal_big")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_support_horizontal_small")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_support_inventory")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_support_post")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_support_vertical_big")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_support_vertical_small")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_upside_down_support_horizontal_big")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_upside_down_support_horizontal_small")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_upside_down_support_inventory")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_upside_down_support_post")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_upside_down_support_vertical_small")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_upside_down_support_vertical_big"))
        );*/
        long l = wa.elapsed().toMillis();
        Stopwatch wa2 = Stopwatch.createStarted();

        Utils.addStandardResources(modId, manager, handler.dynamicPack, SUPPORTS,WoodType.OAK_WOOD_TYPE);
        Utils.addStandardResources(modId, manager, handler.dynamicPack, SEATS,WoodType.OAK_WOOD_TYPE);
        Utils.addStandardResources(modId, manager, handler.dynamicPack, PALISADES,WoodType.OAK_WOOD_TYPE);
        Utils.addStandardResources(modId, manager, handler.dynamicPack, BEAMS,WoodType.OAK_WOOD_TYPE);

        long l1 = wa2.elapsed().toMillis();

        int a = 1;
    }

    @Override
    public void addTranslations(ClientDynamicResourcesHandler clientDynamicResourcesHandler, AfterLanguageLoadEvent lang) {
        BEAMS.forEach((w, v) -> LangBuilder.addDynamicEntry(lang, "block.wood_good.beam", (BlockType)w, v));
        SUPPORTS.forEach((w, v) -> LangBuilder.addDynamicEntry(lang, "block.wood_good.support",(BlockType) w, v));
        PALISADES.forEach((w, v) -> LangBuilder.addDynamicEntry(lang, "block.wood_good.palisade",(BlockType) w, v));
        SEATS.forEach((w, v) -> LangBuilder.addDynamicEntry(lang, "block.wood_good.seat",(BlockType) w, v));
    }

    @Override
    public void addDynamicClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        var pack = handler.dynamicPack;

        //beam
        try (TextureImage side = TextureImage.open(manager,
                modRes("block/oak_beam_side"));
             TextureImage end = TextureImage.open(manager,
                     modRes("block/oak_beam_end"))) {

            Respriter respriterSide = Respriter.of(side);
            Respriter respriterEnd = Respriter.of(end);

            BEAM_ITEMS.forEach((wood, beam) -> {

                String id = beam.getRegistryName().getPath();

                try (TextureImage plankTexture = TextureImage.open(manager,
                        RPUtils.findFirstBlockTextureLocation(manager, wood.planks))) {

                    List<Palette> targetPalette = SpriteUtils.extrapolateSignBlockPalette(plankTexture);

                    handler.addTextureIfNotPresent(manager, "block/" + id + "_side", () ->
                            respriterSide.recolorWithAnimation(targetPalette, plankTexture.getMetadata()));

                    handler.addTextureIfNotPresent(manager, "block/" + id + "_end", () ->
                            respriterEnd.recolorWithAnimation(targetPalette, plankTexture.getMetadata()));

                } catch (Exception ex) {
                    handler.getLogger().error("Failed to generate Beam block texture for for {} : {}", side, ex);
                }
            });
        } catch (Exception ex) {
            handler.getLogger().error("Could not generate any Beam block texture : ", ex);
        }

        //palisade
        try (TextureImage side = TextureImage.open(manager,
                modRes("block/oak_palisade_side"));
             TextureImage end = TextureImage.open(manager,
                     modRes("block/oak_palisade_end"))) {

            Respriter respriterSide = Respriter.of(side);
            Respriter respriterEnd = Respriter.of(end);

            PALISADES.forEach((wood, beam) -> {

                String id = beam.getRegistryName().getPath();

                try (TextureImage plankTexture = TextureImage.open(manager,
                        RPUtils.findFirstBlockTextureLocation(manager, wood.planks))) {

                    List<Palette> targetPalette = SpriteUtils.extrapolateSignBlockPalette(plankTexture);

                    handler.addTextureIfNotPresent(manager, "block/" + id + "_side", () ->
                            respriterSide.recolorWithAnimation(targetPalette, plankTexture.getMetadata()));

                    handler.addTextureIfNotPresent(manager, "block/" + id + "_end", () ->
                            respriterEnd.recolorWithAnimation(targetPalette, plankTexture.getMetadata()));

                } catch (Exception ex) {
                    handler.getLogger().error("Failed to generate Palisade block texture for for {} : {}", side, ex);
                }
            });
        } catch (Exception ex) {
            handler.getLogger().error("Could not generate any Beam block texture : ", ex);
        }

        //supports
        try (TextureImage side = TextureImage.open(manager,
                modRes("block/oak_support_side"));
             TextureImage end = TextureImage.open(manager,
                     modRes("block/oak_support_end"))) {

            Respriter respriterSide = Respriter.of(side);
            Respriter respriterEnd = Respriter.of(end);

            SUPPORTS.forEach((wood, beam) -> {

                String id = beam.getRegistryName().getPath();

                try (TextureImage plankTexture = TextureImage.open(manager,
                        RPUtils.findFirstBlockTextureLocation(manager, wood.planks))) {

                    List<Palette> targetPalette = SpriteUtils.extrapolateSignBlockPalette(plankTexture);

                    handler.addTextureIfNotPresent(manager, "block/" + id + "_side", () ->
                            respriterSide.recolorWithAnimation(targetPalette, plankTexture.getMetadata()));

                    handler.addTextureIfNotPresent(manager, "block/" + id + "_end", () ->
                            respriterEnd.recolorWithAnimation(targetPalette, plankTexture.getMetadata()));

                } catch (Exception ex) {
                    handler.getLogger().error("Failed to generate Support block texture for for {} : {}", side, ex);
                }
            });
        } catch (Exception ex) {
            handler.getLogger().error("Could not generate any Support block texture : ", ex);
        }

        //seats
        try (TextureImage textureImage = TextureImage.open(manager,
                modRes("block/oak_seat"))) {

            Respriter respriterSide = Respriter.of(textureImage);

            SEATS.forEach((wood, beam) -> {

                String id = beam.getRegistryName().getPath();

                try (TextureImage plankTexture = TextureImage.open(manager,
                        RPUtils.findFirstBlockTextureLocation(manager, wood.planks))) {

                    List<Palette> targetPalette = SpriteUtils.extrapolateSignBlockPalette(plankTexture);

                    handler.addTextureIfNotPresent(manager, "block/" + id, () ->
                            respriterSide.recolorWithAnimation(targetPalette, plankTexture.getMetadata()));

                } catch (Exception ex) {
                    handler.getLogger().error("Failed to generate Seat block texture for for {} : {}", textureImage, ex);
                }
            });
        } catch (Exception ex) {
            handler.getLogger().error("Could not generate any Seat block texture : ", ex);
        }
    }


}
