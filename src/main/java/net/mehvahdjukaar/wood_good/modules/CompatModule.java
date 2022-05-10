package net.mehvahdjukaar.wood_good.modules;

import net.mehvahdjukaar.selene.block_set.leaves.LeavesType;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
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

import java.util.Collection;


public abstract class CompatModule {

    protected final String modId;

    public CompatModule(String modId) {
        this.modId = modId;
    }

    @Override
    public String toString() {
        return "GoodWood " + modId + " Module";
    }

    public ResourceLocation modRes(String string){
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


}
