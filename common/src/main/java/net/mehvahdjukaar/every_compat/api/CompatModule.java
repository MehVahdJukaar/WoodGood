package net.mehvahdjukaar.every_compat.api;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.misc.HardcodedBlockType;
import net.mehvahdjukaar.moonlight.api.events.AfterLanguageLoadEvent;
import net.mehvahdjukaar.moonlight.api.misc.Registrator;
import net.mehvahdjukaar.moonlight.api.platform.ClientPlatformHelper;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.assets.LangBuilder;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynClientResourcesProvider;
import net.mehvahdjukaar.moonlight.api.resources.textures.Respriter;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;


public abstract class CompatModule {

    protected final String modId;

    protected CompatModule(String modId) {
        this.modId = modId;
    }

    public String getModId() {
        return modId;
    }

    public abstract String shortenedId();

    @Override
    public String toString() {
        return "EveryCompat " + LangBuilder.getReadableName(modId) + " Module";
    }

    public ResourceLocation modRes(String string) {
        return new ResourceLocation(modId, string);
    }

    public List<String> getAlreadySupportedMods() {
        return List.of();
    }

    public void onModInit() {
    }

    public void onModSetup() {

    }

    public void onClientInit() {

    }

    public void onClientSetup() {

    }

    public void registerWoodBlocks(Registrator<Block> registry, Collection<WoodType> woodTypes) {

    }

    public void registerLeavesBlocks(Registrator<Block> registry, Collection<LeavesType> leavesTypes) {

    }

    public void registerItems(Registrator<Item> registry) {

    }

    public void registerTiles(Registrator<BlockEntityType<?>> registry) {

    }

    public void registerEntities(Registrator<EntityType<?>> registry) {

    }

    public final boolean isEntryAlreadyRegistered(String blockId, BlockType blockType, Registry<?> registry) {
        //!! NOTE: blockType is either: WoodType, LeavesType, or StoneTYpe
        if (blockType.isVanilla()) return true; // Exclude all of Vanilla Types

        // blockId: everycomp:twigs/biomesoplenty/willow_table | blockName: willow_table
        String blockName = blockId.substring(blockId.lastIndexOf("/") + 1);

        String blockTypeFrom = blockType.getNamespace();

        String slashConvention = blockTypeFrom + "/" + blockName; // quark/blossom_chair
        String underscoreConvention = blockTypeFrom + "_" + blockName; // quark_blossom_chair

        // ugly hardcoded stuff
        if (blockType instanceof WoodType wt) {
            Boolean hardcoded = HardcodedBlockType.isWoodBlockAlreadyRegistered(blockName, wt, modId, shortenedId());
            if (hardcoded != null) return hardcoded;
        } else if (blockType instanceof LeavesType lt) {
            Boolean hardcoded = HardcodedBlockType.isLeavesBlockAlreadyRegistered(blockName, lt, modId, shortenedId());
            if (hardcoded != null) return hardcoded;
        }

        /// ========== EXCLUDE ========== \\\
        if (this.getAlreadySupportedMods().contains(blockTypeFrom)) return true;

        // Discard the blocks that are already in the supportedModId from blockTypeFrom
        if (blockTypeFrom.equals(modId)) return true; // quark, blossom

        // Discards the supportedBlockName being already in the supportedModId & Vanilla blockType
        if (registry.containsKey(new ResourceLocation(modId, blockName)) ||
                registry.containsKey(new ResourceLocation(modId, underscoreConvention))) return true;


        // Checking if supportedBlockName exists in the blockTypeFrom
        if (registry.containsKey(new ResourceLocation(blockTypeFrom, blockName))) return true;

        for (var c : EveryCompat.COMPAT_MODS) {
            String compatModId = c.modId();  //bopcomp : bop->quark, twigs
            //if the wood is from the mod this adds compat for && it supports this block type
            if (c.woodFrom().contains(blockTypeFrom) && c.blocksFrom().contains(modId)) {
                if (registry.containsKey(new ResourceLocation(compatModId, blockName))) return true;
                if (registry.containsKey(new ResourceLocation(compatModId, slashConvention))) return true;
                if (registry.containsKey(new ResourceLocation(compatModId, underscoreConvention))) return true;
            }
        }
        return false;
    }

    //resource pack stuff

    public void addDynamicServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {

    }

    public void addDynamicClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {

    }

    public void registerBlockEntityRenderers(ClientPlatformHelper.BlockEntityRendererEvent event) {

    }

    public void addTranslations(ClientDynamicResourcesHandler clientDynamicResourcesHandler, AfterLanguageLoadEvent lang) {
    }

    public void registerBlockColors(ClientPlatformHelper.BlockColorEvent event) {
    }

    public void registerItemColors(ClientPlatformHelper.ItemColorEvent event) {
    }

    //utility functions

    @Nullable
    protected final <T extends Block> T getModBlock(String id, Class<T> blockClass) {
        return (T) Registry.BLOCK.get(modRes(id));
    }

    @Nullable
    protected final Block getModBlock(String id) {
        return Registry.BLOCK.get(modRes(id));
    }

    @Nullable
    protected final Item getModItem(String id) {
        return Registry.ITEM.get(modRes(id));
    }

    @Nullable
    protected final <T extends BlockEntityType<?>> T getModTile(String id, Class<T> blockClass) {
        return (T) Registry.BLOCK_ENTITY_TYPE.get(modRes(id));
    }

    @Nullable
    protected final BlockEntityType<?> getModTile(String id) {
        return Registry.BLOCK_ENTITY_TYPE.get(modRes(id));
    }

    //post process some textures. currently only ecologics azalea
    public void addWoodTexture(WoodType wood, DynClientResourcesProvider handler, ResourceManager manager,
                               String path, Supplier<TextureImage> textureSupplier) {
        handler.addTextureIfNotPresent(manager, path, () -> {
            var t = textureSupplier.get();
            maybeFlowerAzalea(t, manager, wood);
            return t;
        });

        handler.addTextureIfNotPresent(manager, path, () -> {
            var t = textureSupplier.get();
            maybeBrimwood(t, manager, path, wood);
            return t;
        });
    }

    //for ecologics
    protected void maybeFlowerAzalea(TextureImage image, ResourceManager manager, WoodType woodType) {
        if (woodType.getId().toString().equals("ecologics:flowering_azalea")) {
            WoodType azalea = WoodTypeRegistry.getValue(new ResourceLocation("ecologics:azalea"));
            if (azalea != null) {
                try (TextureImage mask = TextureImage.open(manager,
                        EveryCompat.res("block/ecologics_overlay"));
                     TextureImage plankTexture = TextureImage.open(manager,
                             RPUtils.findFirstBlockTextureLocation(manager, azalea.planks))) {

                    Respriter respriter = Respriter.of(image);
                    var temp = respriter.recolorWithAnimationOf(plankTexture);

                    image.applyOverlayOnExisting(temp, mask);
                    temp.close();

                } catch (Exception e) {
                    EveryCompat.LOGGER.warn("failed to apply azalea overlay: ", e);
                }
            }
        }
    }

    //for Regions-Unexplored's brimwood
    protected void maybeBrimwood(TextureImage image, ResourceManager manager, String path, WoodType woodType) {
        if (woodType.getId().toString().equals("regions_unexplored:brimwood")) {
            WoodType brimwood = WoodTypeRegistry.getValue(new ResourceLocation("regions_unexplored:brimwood"));
            if (brimwood != null && !path.contains("chest")) {
                try (TextureImage lavaOverlay = TextureImage.open(manager,
                        EveryCompat.res("block/regions_unexplored/brimwood_planks_lava"));
                     TextureImage plankTexture = TextureImage.open(manager,
                             EveryCompat.res("block/regions_unexplored/brimwood_planks"));

                ) {
                    String type = path.substring(path.lastIndexOf("brimwood_") + 9);

                    Respriter respriter = switch (type) {
                        case "barrel_side" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_barrel_side_m")
                        ));
                        case "barrel_top" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_barrel_top_m")
                        ));
                        case "beehive_front_honey" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_beehive_front_honey_m")
                        ));
                        case "beehive_side" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_beehive_side_m")
                        ));
                        case "bookshelf" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_bookshelf_m")
                        ));
                        case "cartography_table_side1" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_cartography_table_side1_m")
                        ));
                        case "cartography_table_side2" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_cartography_table_side2_m")
                        ));
                        case "cartography_table_top" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_cartography_table_top_m")
                        ));
                        case "chiseled_bookshelf_occupied" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_chiseled_bookshelf_occupied_m")
                        ));
                        case "crafting_table_front" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_crafting_table_front_m")
                        ));
                        case "crafting_table_side" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_crafting_table_side_m")
                        ));
                        case "fletching_table_front" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_fletching_table_front_m")
                        ));
                        case "fletching_table_side" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_fletching_table_side_m")
                        ));
                        case "fletching_table_top" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_fletching_table_top_m")
                        ));
                        case "lectern_base" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_lectern_base_m")
                        ));
                        case "lectern_front" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_lectern_front_m")
                        ));
                        case "smithing_table_bottom" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_smithing_table_bottom_m")
                        ));
                        case "smithing_table_front" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_smithing_table_front_m")
                        ));
                        case "smithing_table_side" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_smithing_table_side_m")
                        ));
                        case "smoker_bottom" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_smoker_bottom_m")
                        ));
                        case "smoker_front" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_smoker_front_m")
                        ));
                        case "smoker_side" -> Respriter.masked(image, TextureImage.open(manager,
                                EveryCompat.res("block/regions_unexplored/brimwood_smoker_side_m")
                        ));
                        default -> Respriter.of(image);
                    };

                    var temp = respriter.recolorWithAnimationOf(plankTexture);

                    if (path.contains("stairs") || path.contains("planks") || path.contains("slab") ||
                            path.contains("beehive") || path.contains("composter_bottom") || path.contains("composter_side")
                            || path.contains("lectern_side") || path.contains("lectern_top") || path.contains("bookshelf_side")
                            || path.contains("bookshelf_top") || path.contains("boards")
                    )
                        image.applyOverlayOnExisting(temp, lavaOverlay);
                    else
                        image.applyOverlayOnExisting(temp);

                    temp.close();

                } catch (Exception e) {
                    EveryCompat.LOGGER.error("failed to open the texture for: ", e);
                }
            }
        }
    }

    public <T extends BlockType, B extends Block> Function<T, @Nullable B> ifHasChild(Function<T, B> supplier, String... children) {
        return w -> {
            for (var v : children) {
                if (w.getChild(v) == null) return null;
            }
            return supplier.apply(w);
        };
    }

    //how much crap this module has registered
    public abstract int bloatAmount();

    public void stitchAtlasTextures(ClientPlatformHelper.AtlasTextureEvent event) {
    }
}
