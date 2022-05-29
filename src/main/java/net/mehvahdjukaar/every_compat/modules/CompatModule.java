package net.mehvahdjukaar.every_compat.modules;

import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.selene.block_set.BlockType;
import net.mehvahdjukaar.selene.block_set.leaves.LeavesType;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.mehvahdjukaar.selene.block_set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.selene.client.asset_generators.textures.Respriter;
import net.mehvahdjukaar.selene.client.asset_generators.textures.TextureImage;
import net.mehvahdjukaar.selene.resourcepack.*;
import net.mehvahdjukaar.selene.resourcepack.recipe.IRecipeTemplate;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.Collectors;


public abstract class CompatModule {

    protected final String modId;

    public CompatModule(String modId) {
        this.modId = modId;
    }

    public String getModId() {
        return modId;
    }

    public abstract String shortenedId();

    @Override
    public String toString() {
        return "GoodWood " + modId + " Module";
    }

    public ResourceLocation modRes(String string) {
        return new ResourceLocation(modId, string);
    }

    public String makeBlockId(BlockType type, String blockName) {
        return this.shortenedId() + "/" + type.getVariantId(blockName, false);
    }

    public void onModSetup(FMLCommonSetupEvent event) {

    }


    public void onClientSetup(FMLClientSetupEvent event) {

    }

    public void registerWoodBlocks(IForgeRegistry<Block> registry, Collection<WoodType> woodTypes) {
        String test = "aa";
        for (WoodType w : woodTypes) {
            String name = w.getVariantId(test, true);
            if (isEntryAlreadyRegistered(name, registry) || w.isVanilla()) continue;

        }
    }

    public void registerLeavesBlocks(IForgeRegistry<Block> registry, Collection<LeavesType> leavesTypes) {

    }

    public void registerItems(IForgeRegistry<Item> registry) {

    }

    public void registerTiles(IForgeRegistry<BlockEntityType<?>> registry) {

    }

    public void registerEntities(IForgeRegistry<EntityType<?>> registry) {

    }

    public void registerTileRenderers(IForgeRegistry<EntityType<?>> registry) {

    }

    protected final boolean isEntryAlreadyRegistered(String name, IForgeRegistry<?> registry) {
        name = name.replace(this.shortenedId() + "/", ""); //af/quark/blossom_chair
        if (name.startsWith(modId + "/")) return true;        //discards one from this mod
        String name2 = name.replace("/", "_"); //quark_blossom_chair
        String name3 = name.substring(name.lastIndexOf("/") + 1); //blossom_chair
        if (registry.containsKey(new ResourceLocation(modId, name)) ||
                registry.containsKey(new ResourceLocation(modId, name2))) return true;
        if (this.shortenedId().equals("af")) return false; //hardcoding

        String woodFrom = name.replace("/" + name3, "");
        if (registry.containsKey(new ResourceLocation(woodFrom, name3))) return true;

        for (var c : WoodGood.COMPETITOR_MODS) {
            String compatModId = c.modId();
            for (var s : c.supportedMods()) {
                if (s.equals(woodFrom) && registry.containsKey(new ResourceLocation(compatModId, name3))) return true;
            }
            if (registry.containsKey(new ResourceLocation(compatModId, name)) ||
                    registry.containsKey(new ResourceLocation(compatModId, name2))) return true;
        }
        return false;
    }

    //resource pack stuff

    public void addStaticServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {

    }


    public void addDynamicServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {

    }

    public void addStaticClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {

    }

    public void addDynamicClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {

    }

    public void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {

    }

    public void addTranslations(ClientDynamicResourcesHandler clientDynamicResourcesHandler, DynamicLanguageManager.LanguageAccessor lang) {
    }

    public void registerColors(ColorHandlerEvent.Item event) {
    }

    //utility functions

    @FunctionalInterface
    protected interface IdModifier extends BiFunction<String, String, String> {
        @Override
        String apply(String original, String id);
    }

    @Deprecated
    protected final <T extends BlockType> void addBlockResources(ResourceManager manager, RPAwareDynamicResourceProvider<?> handler,
                                                                 Map<T, Block> blocks,
                                                                 IdModifier textTransform,
                                                                 IdModifier pathTransform,
                                                                 ResourceLocation... jsonsLocations) {
        List<StaticResource> original = Arrays.stream(jsonsLocations).map(s -> StaticResource.getOrLog(manager, s)).collect(Collectors.toList());

        blocks.forEach((wood, value) -> {
            String id = value.getRegistryName().getPath();
            for (var res : original) {
                try {

                    handler.getPack().addSimilarJsonResource(res,
                            s -> textTransform.apply(s, id),
                            s -> pathTransform.apply(s, id)
                    );
                } catch (Exception e) {
                    handler.getLogger().error("Failed to generate model from {}", res.location);
                }
            }
        });
    }

    //creates and add new jsons based off the ones at the given resources with the provided modifiers
    protected final <T extends BlockType> void addBlockResources(ResourceManager manager, RPAwareDynamicResourceProvider<?> handler,
                                                                 Map<T, Block> blocks, String replaceTarget, ResourceLocation... jsonsLocations) {
        addBlockResources(manager, handler, blocks,
                BlockTypeResourceTransform.<T>create(modId, manager)
                        .replaceSimpleBlock(modId, replaceTarget)
                        .idReplaceBlock(replaceTarget),
                jsonsLocations);
    }

    protected final <T extends BlockType> void addBlockResources(ResourceManager manager, RPAwareDynamicResourceProvider<?> handler,
                                                                 Map<T, Block> blocks,
                                                                 BlockTypeResourceTransform<T> modifier, ResourceLocation... jsonsLocations) {
        List<StaticResource> original = Arrays.stream(jsonsLocations).map(s -> StaticResource.getOrLog(manager, s)).collect(Collectors.toList());

        blocks.forEach((wood, value) -> {

            for (var res : original) {
                try {
                    StaticResource newRes = modifier.transform(res, value.getRegistryName(), wood);

                    assert newRes.location != res.location : "ids cant be the same";

                    handler.dynamicPack.addResource(newRes);
                } catch (Exception e) {
                    handler.getLogger().error("Failed to generate model from {}", res.location);
                }
            }
        });
    }


    //TODO: generalize
    //creates and add new recipes based off the one at the given resource
    protected final void addBlocksRecipes(ResourceManager manager, RPAwareDynamicDataProvider handler,
                                          Map<WoodType, Block> blocks, String oakRecipe) {
        IRecipeTemplate<?> template = RPUtils.readRecipeAsTemplate(manager, ResType.RECIPES.getPath(modRes(oakRecipe)));

        blocks.forEach((w, b) -> {
            FinishedRecipe newR = template.createSimilar(WoodType.OAK_WOOD_TYPE, w, w.planks.asItem());
            handler.getPack().addRecipe(newR);
        });
    }

    //creates and add new recipes based off the one at the given resource
    protected final void addBlocksRecipesL(ResourceManager manager, RPAwareDynamicDataProvider handler,
                                           Map<LeavesType, Block> blocks, String oakRecipe) {
        IRecipeTemplate<?> template = RPUtils.readRecipeAsTemplate(manager, ResType.RECIPES.getPath(modRes(oakRecipe)));

        blocks.forEach((w, b) -> {
            FinishedRecipe newR = template.createSimilar(LeavesType.OAK_LEAVES_TYPE, w, w.leaves.asItem());
            handler.getPack().addRecipe(newR);
        });
    }

    protected final void addChildToOak(String category, String oakBlockName) {
        WoodType.OAK_WOOD_TYPE.addChild(category, ForgeRegistries.BLOCKS.getValue(modRes(oakBlockName)));
    }


    //post process some textures. currently only ecologics azalea
    protected void addWoodTexture(WoodType wood, ClientDynamicResourcesHandler handler, ResourceManager manager,
                                  String path, Supplier<TextureImage> textureSupplier){
        handler.addTextureIfNotPresent(manager, path, ()->{
            var t = textureSupplier.get();
             maybeFlowerAzalea(t,   manager,   wood);
             return t;
        });
    }

    //for ecologics
    protected void maybeFlowerAzalea(TextureImage image, ResourceManager manager, WoodType woodType) {
        if (woodType.getId().toString().equals("ecologics:flowering_azalea")) {
            WoodType azalea = WoodTypeRegistry.WOOD_TYPES.get(new ResourceLocation("ecologics:azalea"));
            if (azalea != null) {
                try (TextureImage mask = TextureImage.open(manager,
                        WoodGood.res("blocks/ecologics_overlay"));
                     TextureImage plankTexture = TextureImage.open(manager,
                             RPUtils.findFirstBlockTextureLocation(manager, azalea.planks))) {

                    Respriter respriter = Respriter.of(image);
                    respriter.recolorWithAnimationOf(plankTexture);
                    image.applyOverlay(mask);

                } catch (Exception e) {
                    WoodGood.LOGGER.warn("failed to apply azalea overlay: ", e);
                }
            }
        }
    }
}
