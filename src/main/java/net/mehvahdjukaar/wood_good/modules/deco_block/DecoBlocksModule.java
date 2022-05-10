package net.mehvahdjukaar.wood_good.modules.deco_block;

import lilypuree.decorative_blocks.blocks.types.WoodDecorativeBlockTypes;
import lilypuree.decorative_blocks.core.DBBlocks;
import lilypuree.decorative_blocks.core.DBItems;
import lilypuree.decorative_blocks.items.SeatItem;
import lilypuree.decorative_blocks.items.SupportItem;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.mehvahdjukaar.selene.resourcepack.RPUtils;
import net.mehvahdjukaar.selene.resourcepack.ResType;
import net.mehvahdjukaar.selene.resourcepack.StaticResource;
import net.mehvahdjukaar.selene.resourcepack.asset_generators.LangBuilder;
import net.mehvahdjukaar.selene.resourcepack.asset_generators.textures.Palette;
import net.mehvahdjukaar.selene.resourcepack.asset_generators.textures.Respriter;
import net.mehvahdjukaar.selene.resourcepack.asset_generators.textures.SpriteUtils;
import net.mehvahdjukaar.selene.resourcepack.asset_generators.textures.TextureImage;
import net.mehvahdjukaar.wood_good.WoodGood;
import net.mehvahdjukaar.wood_good.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.wood_good.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.wood_good.modules.CompatModule;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class DecoBlocksModule extends CompatModule {

    public DecoBlocksModule(String modId) {
        super(modId);
    }

    private final Map<WoodType, DBWoodType> DB_WOOD_TYPES = new HashMap<>();

    private static final String PALISADE_NAME = "palisade";
    public Map<WoodType, Block> PALISADES = new HashMap<>();
    public Map<WoodType, Item> PALISADE_ITEMS = new HashMap<>();

    private static final String BEAM_NAME = "beam";
    public Map<WoodType, Block> BEAMS = new HashMap<>();
    public Map<WoodType, Item> BEAM_ITEMS = new HashMap<>();

    private static final String SUPPORT_NAME = "support";
    public Map<WoodType, Block> SUPPORTS = new HashMap<>();
    public Map<WoodType, Item> SUPPORTS_ITEMS = new HashMap<>();

    private static final String SEAT_NAME = "seat";
    public Map<WoodType, Block> SEATS = new HashMap<>();
    public Map<WoodType, Item> SEAT_ITEMS = new HashMap<>();

    @Override
    public void registerWoodBlocks(IForgeRegistry<Block> registry, Collection<WoodType> woodTypes) {
        woodTypes.forEach(w -> DB_WOOD_TYPES.put(w, new DBWoodType(w)));

        //beams
        for (WoodType w : woodTypes) {
            String name = w.getVariantId(BEAM_NAME, false);
            if (w.isVanilla() || !shouldRegisterEntry(name, registry)) continue;

            DBWoodType wood = DB_WOOD_TYPES.get(w);
            Block block = DBBlocks.createDecorativeBlock(wood, WoodDecorativeBlockTypes.BEAM);
            BEAMS.put(w, block);
            registry.register(block.setRegistryName(WoodGood.res(name)));
        }
        //palisades
        for (WoodType w : woodTypes) {
            String name = w.getVariantId(PALISADE_NAME, false);
            if (w.isVanilla() || !shouldRegisterEntry(name, registry)) continue;

            DBWoodType wood = DB_WOOD_TYPES.get(w);
            Block block = DBBlocks.createDecorativeBlock(wood, WoodDecorativeBlockTypes.PALISADE);
            PALISADES.put(w, block);
            registry.register(block.setRegistryName(WoodGood.res(name)));
        }
        //supports
        for (WoodType w : woodTypes) {
            String name = w.getVariantId(SUPPORT_NAME, false);
            if (w.isVanilla() || !shouldRegisterEntry(name, registry)) continue;

            DBWoodType wood = DB_WOOD_TYPES.get(w);
            Block block = DBBlocks.createDecorativeBlock(wood, WoodDecorativeBlockTypes.SUPPORT);
            SUPPORTS.put(w, block);
            registry.register(block.setRegistryName(WoodGood.res(name)));
        }
        //seats
        for (WoodType w : woodTypes) {
            String name = w.getVariantId(SEAT_NAME, false);
            if (w.isVanilla() || !shouldRegisterEntry(name, registry)) continue;

            DBWoodType wood = DB_WOOD_TYPES.get(w);
            Block block = DBBlocks.createDecorativeBlock(wood, WoodDecorativeBlockTypes.SEAT);
            SEATS.put(w, block);
            registry.register(block.setRegistryName(WoodGood.res(name)));
        }
    }

    @Override
    public void registerItems(IForgeRegistry<Item> registry) {
        BEAMS.forEach((key, value) -> {
            Item i = new BlockItem(value, DBItems.modItemProperties);
            BEAM_ITEMS.put(key, i);
            registry.register(i.setRegistryName(value.getRegistryName()));
        });
        PALISADES.forEach((key, value) -> {
            Item i = new BlockItem(value, DBItems.modItemProperties);
            PALISADE_ITEMS.put(key, i);
            registry.register(i.setRegistryName(value.getRegistryName()));
        });
        SUPPORTS.forEach((key, value) -> {
            Item i = new SupportItem(value, DBItems.modItemProperties);
            SUPPORTS_ITEMS.put(key, i);
            registry.register(i.setRegistryName(value.getRegistryName()));
        });
        SEATS.forEach((key, value) -> {
            Item i = new SeatItem(value, DBItems.modItemProperties);
            SEAT_ITEMS.put(key, i);
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
        SUPPORTS.forEach((wood, value) -> {
            pack.addSimpleBlockLootTable(value);
            seats.add(value.getRegistryName());
        });
        pack.addTag(modRes("seats"), seats, Registry.BLOCK_REGISTRY);
        pack.addTag(modRes("seats"), seats, Registry.ITEM_REGISTRY);
    }

    @Override
    public void addStaticClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager, LangBuilder langBuilder) {
        //beams
        BEAMS.forEach((w, v) -> langBuilder.addEntry(v, w.getNameForTranslation(BEAM_NAME)));

        this.addBlockResources(manager, handler, BEAMS, "oak_beam",
                ResType.ITEM_MODELS.getPath(modRes("oak_beam")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_beam_y")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_beam_x")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_beam_z")),
                ResType.BLOCKSTATES.getPath(modRes("oak_beam"))
        );

        //palisades
        PALISADES.forEach((w, v) -> langBuilder.addEntry(v, w.getNameForTranslation(PALISADE_NAME)));

        this.addBlockResources(manager, handler, PALISADES, "oak_palisade",
                ResType.ITEM_MODELS.getPath(modRes("oak_palisade")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_palisade_inventory")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_palisade_post")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_palisade_side")),
                ResType.BLOCKSTATES.getPath(modRes("oak_palisade"))
        );

        //supports
        SUPPORTS.forEach((w, v) -> langBuilder.addEntry(v, w.getNameForTranslation(SUPPORT_NAME)));

        this.addBlockResources(manager, handler, SUPPORTS,
                (s, id) -> s.replace("decorative_blocks:block/","wood_good:block/")
                        .replace("oak_support", id)
                        .replace("oak_upside_down_support",
                        id.replace("support", "upside_down_support")),
                (s, id) -> s.replace("oak_support",  id),
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

        //seats
        SEATS.forEach((w, v) -> langBuilder.addEntry(v, w.getNameForTranslation(SEAT_NAME)));

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
