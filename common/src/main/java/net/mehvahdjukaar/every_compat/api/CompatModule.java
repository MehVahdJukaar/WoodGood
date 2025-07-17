package net.mehvahdjukaar.every_compat.api;

import com.google.common.base.Suppliers;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.mehvahdjukaar.every_compat.ECRegistry;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.events.AfterLanguageLoadEvent;
import net.mehvahdjukaar.moonlight.api.misc.Registrator;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.mehvahdjukaar.moonlight.api.resources.assets.LangBuilder;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;


public abstract class CompatModule {

    protected final String modId;
    protected final String modName;

    //EC or addon namespace
    private final String myNamespace;

    protected CompatModule(String modId, String myNamespace) {
        this.modId = modId;
        this.modName = PlatHelper.getModName(modId);
        this.myNamespace = myNamespace;
    }

    protected CompatModule(String modId) {
        this(modId, EveryCompat.MOD_ID);
    }

    public String getModId() {
        return modId;
    }

    public String getMyNamespace() {
        return myNamespace;
    }

    // readable name
    public String getModName() {
        return modName;
    }

    public abstract String shortenedId();

    @Override
    public String toString() {
        return "EveryCompat " + LangBuilder.getReadableName(modId) + " Module";
    }

    public ResourceLocation modRes(String string) {
        return ResourceLocation.fromNamespaceAndPath(modId, string);
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

    public <T extends BlockType> void registerBlocks(Class<T> typeClass,
                                                     Registrator<Block> registry, Collection<T> types) {
    }

    public void registerItems(Registrator<Item> registry) {
    }

    public void registerTiles(Registrator<BlockEntityType<?>> registry) {
    }

    public void registerEntities(Registrator<EntityType<?>> registry) {
    }


    //resource pack stuff

    public void addDynamicServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
    }

    @Environment(EnvType.CLIENT)
    public void addDynamicClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
    }

    @Environment(EnvType.CLIENT)
    public void registerBlockEntityRenderers(ClientHelper.BlockEntityRendererEvent event) {
    }

    public void addTranslations(ClientDynamicResourcesHandler clientDynamicResourcesHandler, AfterLanguageLoadEvent lang) {
    }

    public void registerBlockColors(ClientHelper.BlockColorEvent event) {
    }

    public void registerItemColors(ClientHelper.ItemColorEvent event) {
    }

    public void registerItemsToExistingTabs(RegHelper.ItemToTabEvent event) {
    }

    //utility functions

    protected final <T extends Block> Supplier<T> getModBlock(String id, Class<T> blockClass) {
        return memorize(id, BuiltInRegistries.BLOCK);
    }

    @Deprecated(forRemoval = true)
    protected final Supplier<CreativeModeTab> getModTab(String id) {
        return memorize(id, BuiltInRegistries.CREATIVE_MODE_TAB);
    }

    protected final Supplier<Block> getModBlock(String id) {
        return getModBlock(id, Block.class);
    }

    protected final Supplier<Item> getModItem(String id) {
        return memorize(id, BuiltInRegistries.ITEM);
    }

    protected final <B extends BlockEntity> Supplier<BlockEntityType<B>> getModTile(String id, Class<B> tileEntityClass) {
        return memorize(id, BuiltInRegistries.BLOCK_ENTITY_TYPE);
    }

    protected final Supplier<BlockEntityType<BlockEntity>> getModTile(String id) {
        return getModTile(id, BlockEntity.class);
    }

    //how much crap this module has registered
    public abstract int bloatAmount();

    //used for creative tabs
    public <T extends BlockType> List<Item> getAllItemsOfType(T type) {
        return List.of();
    }


    public <T> Supplier<T> memorize(String id, Registry<?> reg) {
        return Suppliers.memoize(() -> {
            try {
                return (T) reg.getOptional(modRes(id))
                        .orElseThrow();
            } catch (Throwable e) {
                throw new IllegalStateException("Could not find " + id + " in " + reg + ". This likely means that the reigstry entry was renamed in the original mod and EC needs updating. " +
                        "Either downgrade the mod " + this.modId + " or wait for an Every Compat update");
            }
        });
    }

    // Ec tab
    public ResourceKey<CreativeModeTab> getDedicatedTab() {
        return ECRegistry.MOD_TAB.getKey();
    }

    public abstract Collection<Class<? extends BlockType>> getAffectedTypes();

    //these have to be known in advance
    public String[] getServerResourcesNamespaces() {
        return new String[]{modId, myNamespace};
    }

    public String[] getClientResourcesNamespaces() {
        return new String[]{myNamespace};
    }
}
