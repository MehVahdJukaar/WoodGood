package net.mehvahdjukaar.every_compat.api;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.events.AfterLanguageLoadEvent;
import net.mehvahdjukaar.moonlight.api.misc.Registrator;
import net.mehvahdjukaar.moonlight.api.platform.ClientPlatformHelper;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class SimpleModule extends CompatModule {

    private final String shortId;
    private final Map<String, EntrySet<?, ?>> entries = new LinkedHashMap<>();

    public SimpleModule(String modId, String shortId) {
        super(modId);
        this.shortId = shortId;
    }

    public void addEntry(EntrySet<?, ?> entryHolder) {
        this.entries.put(entryHolder.getName(), entryHolder);

        EveryCompat.addEntryType(entryHolder.getTypeClass(), entryHolder.getChildKey(this));
    }

    public Collection<EntrySet<?, ?>> getEntries() {
        return entries.values();
    }

    public EntrySet<?, ?> getEntry(String name) {
        var e = entries.get(name);
        if (e == null)
            throw new UnsupportedOperationException(String.format("This module does not have entries of type %s", name));
        return e;
    }

    @Override
    public String shortenedId() {
        return shortId;
    }

    @Override
    public void addTranslations(ClientDynamicResourcesHandler clientDynamicResourcesHandler, AfterLanguageLoadEvent lang) {
        getEntries().forEach(e -> e.addTranslations(this, lang));
    }

    @Override
    public void registerWoodBlocks(Registrator<Block> registry, Collection<WoodType> woodTypes) {
        getEntries().forEach(e -> e.registerWoodBlocks(this, registry, woodTypes));
    }

    @Override
    public void registerLeavesBlocks(Registrator<Block> registry, Collection<LeavesType> leavesTypes) {
        getEntries().forEach(e -> e.registerLeavesBlocks(this, registry, leavesTypes));
    }

    @Override
    public void registerItems(Registrator<Item> registry) {
        getEntries().forEach(e -> e.registerItems(this, registry));
    }

    @Override
    public void registerTiles(Registrator<BlockEntityType<?>> registry) {
        getEntries().forEach(e -> e.registerTiles(this, registry));
    }

    @Override
    public void addStaticServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
        getEntries().forEach(e -> e.generateTags(this, handler.dynamicPack, manager));
    }

    @Override
    public void addDynamicServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
        getEntries().forEach(e -> {
            e.generateLootTables(this, handler.dynamicPack, manager);
            e.generateRecipes(this, handler.dynamicPack, manager);
        });
    }

    @Override
    public void addStaticClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        getEntries().forEach(e -> e.generateModels(this, handler, manager));
    }

    @Override
    public void addDynamicClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        getEntries().forEach(e -> e.generateTextures(this, handler, manager));
    }

    @Override
    public void onClientSetup() {
        getEntries().forEach(EntrySet::setRenderLayer);
    }


    @Override
    public void registerBlockEntityRenderers(ClientPlatformHelper.BlockEntityRendererEvent event) {
        getEntries().forEach(e -> e.registerEntityRenderers(this, event));
    }
}
