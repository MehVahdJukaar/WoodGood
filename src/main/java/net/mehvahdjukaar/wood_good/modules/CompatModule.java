package net.mehvahdjukaar.wood_good.modules;

import net.mehvahdjukaar.selene.block_set.leaves.LeavesType;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.mehvahdjukaar.selene.resourcepack.*;
import net.mehvahdjukaar.selene.resourcepack.recipe.IRecipeTemplate;
import net.mehvahdjukaar.wood_good.WoodGood;
import net.mehvahdjukaar.wood_good.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.wood_good.dynamicpack.ServerDynamicResourcesHandler;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.commons.lang3.function.TriFunction;

import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.BiFunction;
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

    public String makeBlockId(WoodType type, String blockName) {
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
            if (!shouldRegisterEntry(name, registry) || w.isVanilla()) continue;

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

    protected final boolean shouldRegisterEntry(String name, IForgeRegistry<?> registry) {
        name = name.replace(this.shortenedId() + "/", ""); //db/quark/blossom_chair
        if (name.startsWith(modId + "/")) return false;        //discards one from this mod
        String name2 = name.replace("/", "_");
        if (registry.containsKey(new ResourceLocation(modId, name)) ||
                registry.containsKey(new ResourceLocation(modId, name2))) return false;
        for (String m : WoodGood.COMPETITOR_MODS) {
            if (registry.containsKey(new ResourceLocation(m, name)) ||
                    registry.containsKey(new ResourceLocation(m, name2))) return false;
        }
        return true;
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

    ;


    //utility functions

    @FunctionalInterface
    protected interface IdModifier extends BiFunction<String, String, String> {
        @Override
        String apply(String original, String id);
    }

    @Deprecated
    protected final void addBlockResources(ResourceManager manager, RPAwareDynamicResourceProvider<?> handler,
                                           Map<WoodType, Block> blocks,
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
    protected final void addBlockResources(ResourceManager manager, RPAwareDynamicResourceProvider<?> handler,
                                           Map<WoodType, Block> blocks, String replaceTarget, ResourceLocation... jsonsLocations) {
        addBlockResources(manager, handler, blocks,
                WoodJsonTransformation.create(modId, manager)
                        .replaceSimpleBlock(modId, replaceTarget)
                        .replaceBlockInPath(replaceTarget),
                jsonsLocations);
    }

    protected final void addBlockResources(ResourceManager manager, RPAwareDynamicResourceProvider<?> handler,
                                           Map<WoodType, Block> blocks,
                                           WoodJsonTransformation modifier, ResourceLocation... jsonsLocations) {
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


    @FunctionalInterface
    protected interface WoodTextModifier extends TriFunction<String, ResourceLocation, WoodType, String> {
        @Override
        String apply(String originalText, ResourceLocation blockId, WoodType type);
    }

    public static class WoodJsonTransformation {

        private final ResourceManager manager;
        private final String modId;

        private final List<WoodTextModifier> textModifiers = new ArrayList<>();
        private WoodTextModifier idModifiers = (s, id, w) -> s;

        private WoodJsonTransformation(String modId, ResourceManager manager) {
            this.manager = manager;
            this.modId = modId;
        }

        public static WoodJsonTransformation create(String modId, ResourceManager manager) {
            return new WoodJsonTransformation(modId, manager);
        }

        public WoodJsonTransformation withPath(WoodTextModifier modifier) {
            this.idModifiers = modifier;
            return this;
        }

        public WoodJsonTransformation replaceWoodInPath(String originalWoodName) {
            this.idModifiers = (s, id, w) -> s.replace(originalWoodName,
                    id.getPath().substring(0, id.getPath().lastIndexOf("/"))+"/"+w.getTypeName());
            return this;
        }

        public WoodJsonTransformation replaceBlockInPath(String blockName) {
            this.idModifiers = (s, id, w) -> s.replace(blockName, id.getPath());
            return this;
        }

        public WoodJsonTransformation replaceWoodBlock(String woodName) {
            this.addModifier((s, id, w) -> s.replace(modId + ":block/" + woodName,
                    id.getNamespace() + ":block/" +
                            id.getPath().substring(0, id.getPath().lastIndexOf("/"))+"/"+w.getTypeName()));
            return this;
        }

        public WoodJsonTransformation addModifier(WoodTextModifier modifier) {
            this.textModifiers.add(modifier);
            return this;
        }

        public WoodJsonTransformation addModifier(BiFunction<String, WoodType, String> genericModifier) {
            return this.addModifier((s, id, w) -> genericModifier.apply(s, w));
        }

        public WoodJsonTransformation replaceSimpleBlock(Block block) {
            ResourceLocation res = block.getRegistryName();
            return replaceSimpleBlock(res.getNamespace(), res.getPath());
        }

        public WoodJsonTransformation replaceSimpleBlock(String blockNamespace, String blockName) {
            return replaceSimpleBlock(blockNamespace, blockName, ":block/", ":block/");
        }

        public WoodJsonTransformation replaceSimpleBlock(String blockNamespace, String blockName, String inBetween, String inBetween2) {
            return this.addModifier((s, id, w) -> s.replace(blockNamespace + inBetween + blockName,
                    id.getNamespace() + inBetween2 + id.getPath()));
        }

        public WoodJsonTransformation replaceString(String from, String to) {
            return this.addModifier((s, id, w) -> s.replace(from, to));
        }

        public WoodJsonTransformation replaceOakPlanks() {
            return this.replaceWithTextureFromWoodItem("minecraft:block/oak_planks", "planks");
        }

        public WoodJsonTransformation replaceWithTextureFromWoodItem(String target, String textureFromChild) {
            return this.addModifier((s, id, w) -> {
                String r = s;
                try {
                    ItemLike woodObject = w.getChild(textureFromChild);
                    ResourceLocation newTexture = null;
                    if (woodObject instanceof Block b) {
                        newTexture = RPUtils.findFirstBlockTextureLocation(manager, b);
                    } else if (woodObject instanceof Item i) {
                        newTexture = RPUtils.findFirstItemTextureLocation(manager, i);
                    }
                    if (newTexture != null) {
                        r = s.replace(target, newTexture.toString());
                    }
                } catch (FileNotFoundException ignored) {
                }
                return r;
            });
        }

        public StaticResource transform(StaticResource resource, ResourceLocation blockId, WoodType type) {
            String newText = new String(resource.data, StandardCharsets.UTF_8);

            for (var m : textModifiers) {
                newText = m.apply(newText, blockId, type);
            }

            ResourceLocation oldPath = resource.location;
            //old code. TODO: redo
            /*
            StringBuilder builder = new StringBuilder();
            String[] partial = oldPath.getPath().split("/");
            for (int i = 0; i < partial.length; i++) {
                if (i != 0) builder.append("/");
                if (i == partial.length - 1) {
                    builder.append(idModifiers.apply(partial[i], blockId, type));
                } else builder.append(partial[i]);
            }*/
            String id = idModifiers.apply(oldPath.getPath(), blockId, type);
            ResourceLocation newLocation = new ResourceLocation(blockId.getNamespace(), id);

            return StaticResource.create(newText.getBytes(), newLocation);
        }
    }


    //creates and add new recipes based off the one at the given resource
    protected final void addBlocksRecipes(ResourceManager manager, RPAwareDynamicDataProvider handler,
                                          Map<WoodType, Block> blocks, String oakRecipe) {
        IRecipeTemplate<?> template = RPUtils.readRecipeAsTemplate(manager, ResType.RECIPES.getPath(modRes(oakRecipe)));

        blocks.forEach((w, b) -> {
            FinishedRecipe newR = template.createSimilar(WoodType.OAK_WOOD_TYPE, w, w.planks.asItem());
            handler.getPack().addRecipe(newR);
        });
    }

    protected final void addChildToOak(String category, String oakBlockName) {
        WoodType.OAK_WOOD_TYPE.addChild(category, ForgeRegistries.BLOCKS.getValue(modRes(oakBlockName)));
    }

}
