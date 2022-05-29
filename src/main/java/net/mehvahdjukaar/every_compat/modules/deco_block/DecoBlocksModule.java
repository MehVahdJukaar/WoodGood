package net.mehvahdjukaar.every_compat.modules.deco_block;

import com.crispytwig.another_furniture.AnotherFurnitureMod;
import lilypuree.decorative_blocks.blocks.types.WoodDecorativeBlockTypes;
import lilypuree.decorative_blocks.core.DBBlocks;
import lilypuree.decorative_blocks.core.DBItems;
import lilypuree.decorative_blocks.items.SeatItem;
import lilypuree.decorative_blocks.items.SupportItem;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.mehvahdjukaar.selene.client.asset_generators.LangBuilder;
import net.mehvahdjukaar.selene.client.asset_generators.textures.Palette;
import net.mehvahdjukaar.selene.client.asset_generators.textures.Respriter;
import net.mehvahdjukaar.selene.client.asset_generators.textures.SpriteUtils;
import net.mehvahdjukaar.selene.client.asset_generators.textures.TextureImage;
import net.mehvahdjukaar.selene.items.WoodBasedBlockItem;
import net.mehvahdjukaar.selene.resourcepack.BlockTypeResourceTransform;
import net.mehvahdjukaar.selene.resourcepack.DynamicLanguageManager;
import net.mehvahdjukaar.selene.resourcepack.RPUtils;
import net.mehvahdjukaar.selene.resourcepack.ResType;
import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.modules.CompatModule;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FurnaceBlock;
import net.minecraft.world.level.block.entity.FurnaceBlockEntity;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.*;

public class DecoBlocksModule extends CompatModule {

    public DecoBlocksModule(String modId) {
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
            if (w.isVanilla() || !shouldRegisterEntry(name, registry)) continue;

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
            if (w.isVanilla() || !shouldRegisterEntry(name, registry)) continue;

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
            if (w.isVanilla() || !shouldRegisterEntry(name, registry)) continue;

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
            if (w.isVanilla() || !shouldRegisterEntry(name, registry)) continue;

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
        List<ResourceLocation> beams = new ArrayList<>();
        BEAMS.forEach((wood, value) -> {
            pack.addSimpleBlockLootTable(value);
            beams.add(value.getRegistryName());
        });
        pack.addTag(modRes("beams"), beams, Registry.BLOCK_REGISTRY);
        pack.addTag(modRes("beams"), beams, Registry.ITEM_REGISTRY);

        List<ResourceLocation> palisades = new ArrayList<>();
        PALISADES.forEach((wood, value) -> {
            pack.addSimpleBlockLootTable(value);
            palisades.add(value.getRegistryName());
        });
        pack.addTag(modRes("palisades"), palisades, Registry.BLOCK_REGISTRY);
        pack.addTag(modRes("palisades"), palisades, Registry.ITEM_REGISTRY);

        List<ResourceLocation> supports = new ArrayList<>();
        SUPPORTS.forEach((wood, value) -> {
            pack.addSimpleBlockLootTable(value);
            supports.add(value.getRegistryName());
        });
        pack.addTag(modRes("supports"), supports, Registry.BLOCK_REGISTRY);
        pack.addTag(modRes("supports"), supports, Registry.ITEM_REGISTRY);

        List<ResourceLocation> seats = new ArrayList<>();
        SEATS.forEach((wood, value) -> {
            pack.addSimpleBlockLootTable(value);
            seats.add(value.getRegistryName());
        });
        pack.addTag(modRes("seats"), seats, Registry.BLOCK_REGISTRY);
        pack.addTag(modRes("seats"), seats, Registry.ITEM_REGISTRY);
    }

    @Override
    public void addDynamicServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
        this.addBlocksRecipes(manager, handler, BEAMS, "oak_beam");
        this.addBlocksRecipes(manager, handler, PALISADES, "oak_palisade");
        this.addBlocksRecipes(manager, handler, SUPPORTS, "oak_support");
        this.addBlocksRecipes(manager, handler, SEATS, "oak_seat");
    }

    @Override
    public void addStaticClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        this.addBlockResources(manager, handler, BEAMS, "oak_beam",
                ResType.ITEM_MODELS.getPath(modRes("oak_beam")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_beam_y")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_beam_x")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_beam_z")),
                ResType.BLOCKSTATES.getPath(modRes("oak_beam"))
        );
        this.addBlockResources(manager, handler, PALISADES, "oak_palisade",
                ResType.ITEM_MODELS.getPath(modRes("oak_palisade")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_palisade_inventory")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_palisade_post")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_palisade_side")),
                ResType.BLOCKSTATES.getPath(modRes("oak_palisade"))
        );
        this.addBlockResources(manager, handler, SUPPORTS,
                BlockTypeResourceTransform.wood(modId, manager)
                        .idReplaceType("oak")
                        .replaceSimpleBlock(modId, "oak_support"),
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
                ResType.BLOCK_MODELS.getPath(modRes("oak_upside_down_support_vertical_big")),
                ResType.BLOCKSTATES.getPath(modRes("oak_support"))
        );
        this.addBlockResources(manager, handler, SEATS, "oak_seat",
                ResType.ITEM_MODELS.getPath(modRes("oak_seat")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_seat")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_seat_inventory")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_seat_post")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_seat_post_inventory")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_seat_top_post")),
                ResType.BLOCKSTATES.getPath(modRes("oak_seat"))
        );
    }

    @Override
    public void addTranslations(ClientDynamicResourcesHandler clientDynamicResourcesHandler, DynamicLanguageManager.LanguageAccessor lang) {
        BEAMS.forEach((w, v) -> LangBuilder.addDynamicEntry(lang, "block.wood_good.beam", w, v));
        SUPPORTS.forEach((w, v) -> LangBuilder.addDynamicEntry(lang, "block.wood_good.support", w, v));
        PALISADES.forEach((w, v) -> LangBuilder.addDynamicEntry(lang, "block.wood_good.palisade", w, v));
        SEATS.forEach((w, v) -> LangBuilder.addDynamicEntry(lang, "block.wood_good.seat", w, v));
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
