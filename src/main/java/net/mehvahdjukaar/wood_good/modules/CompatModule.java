package net.mehvahdjukaar.wood_good.modules;

import net.mehvahdjukaar.selene.block_set.leaves.LeavesType;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.mehvahdjukaar.selene.resourcepack.StaticResource;
import net.mehvahdjukaar.selene.resourcepack.asset_generators.LangBuilder;
import net.mehvahdjukaar.wood_good.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.wood_good.dynamicpack.ServerDynamicResourcesHandler;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
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

    @Override
    public String toString() {
        return "GoodWood " + modId + " Module";
    }

    public ResourceLocation modRes(String string) {
        return new ResourceLocation(modId, string);
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

    protected final boolean shouldRegisterEntry(String name, IForgeRegistry<?> registry) {
        //discards one from this mod
        if (name.startsWith(modId + "/")) return false;
        return !registry.containsKey(new ResourceLocation(modId, name));
    }

    //resource pack stuff

    public void addStaticServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {

    }


    public void addDynamicServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {

    }

    public void addStaticClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager, LangBuilder langBuilder) {

    }

    public void addDynamicClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {

    }


    //utility functions

    @FunctionalInterface
    protected interface IdModifier extends BiFunction<String, String, String> {
        @Override
        String apply(String original, String id);
    }

    protected final void addBlockResources(ResourceManager manager, ClientDynamicResourcesHandler handler,
                                           Map<WoodType, Block> blocks,
                                           IdModifier textTransform,
                                           IdModifier pathTransform,
                                           ResourceLocation... jsonsLocations) {
        List<StaticResource> original = Arrays.stream(jsonsLocations).map(s -> StaticResource.getOrLog(manager, s)).collect(Collectors.toList());

        blocks.forEach((wood, value) -> {
            String id = value.getRegistryName().getPath();
            for (var res : original) {
                try {

                    handler.dynamicPack.addSimilarJsonResource(res,
                            s -> textTransform.apply(s, id),
                            s -> pathTransform.apply(s, id)
                    );
                } catch (Exception e) {
                    handler.getLogger().error("Failed to generate model from {}", res.location);
                }
            }
        });
    }

    protected final void addBlockResources(ResourceManager manager, ClientDynamicResourcesHandler handler,
                                           Map<WoodType, Block> blocks, String defaultName, ResourceLocation... jsonsLocations) {
        addBlockResources(manager, handler, blocks,

                (s, id) -> s.replace(modId + ":block/" + defaultName, "wood_good:block/" + id),
                (s, id) -> s.replace(defaultName, id),
                jsonsLocations);
    }

}
