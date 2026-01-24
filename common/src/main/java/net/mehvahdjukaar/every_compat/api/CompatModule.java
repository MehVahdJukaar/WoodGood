package net.mehvahdjukaar.every_compat.api;

import com.google.common.base.Suppliers;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.mehvahdjukaar.every_compat.ECRegistry;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.events.AfterLanguageLoadEvent;
import net.mehvahdjukaar.moonlight.api.misc.Registrator;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GravelBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.jetbrains.annotations.ApiStatus;

import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;


public abstract class CompatModule {

    protected final String modId;
    protected final String modName; //redable name
    protected final String shortId;

    //EC or addon namespace
    private final String myNamespace;

    @Deprecated(forRemoval = true)
    protected CompatModule(String modId, String myNamespace) {
        this(modId, modId, myNamespace);
    }

    protected CompatModule(String modId, String shortId, String myNamespace) {
        this.modId = modId;
        this.modName = PlatHelper.getModName(modId);
        this.myNamespace = myNamespace;
        this.shortId = shortId;
        if (myNamespace.equals("minecraft")){
            throw new AssertionError("Every Compat module namespace cannot be minecraft");
        }
        //yeah, bad api but doesn't matter if we use wood or other types
        //called here so we get right bus since module construction is delegated
        BlockSetAPI.addDynamicRegistration(myNamespace, (r) -> {
            EveryCompat.executeOrFail(() -> {
                for (var t : this.getAffectedTypes()) {
                    this.registerBlocks(t, r);
                }
            }, this);
        }, BuiltInRegistries.BLOCK);

        BlockSetAPI.addDynamicRegistration(myNamespace, (r) -> {
            EveryCompat.executeOrFail(() -> {
                this.registerItems((resourceLocation, item) -> {
                    r.register(resourceLocation, item);
                    EveryCompat.addItemToModuleMapping(item, this);
                });
            }, this);
        }, BuiltInRegistries.ITEM);

        BlockSetAPI.addDynamicRegistration(myNamespace, r -> {
            EveryCompat.executeOrFail(() -> {
                this.registerTiles(r);
            }, this);
        }, BuiltInRegistries.BLOCK_ENTITY_TYPE);

        BlockSetAPI.addDynamicRegistration(myNamespace, r -> {
            EveryCompat.executeOrFail(() -> {
                this.registerEntities(r);
            }, this);
        }, BuiltInRegistries.ENTITY_TYPE);


    }

    public final String getModId() {
        return modId;
    }

    public final String getMyNamespace() {
        return myNamespace;
    }

    // readable name
    public final String getModName() {
        return modName;
    }

    public String shortenedId() {
        return shortId;
    }

    @Override
    public String toString() {
        return "module[ " + getModName() + " @ " + getMyNamespace() + " ]";
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

    private <T extends BlockType> void registerBlocks(Class<T> type,
                                                      Registrator<Block> registry) {
        this.registerBlocks(type, registry, BlockSetAPI.getBlockSet(type).getValues());
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
    public void addDynamicServerResources(Consumer<ResourceGenTask> executor) {
    }

    @Environment(EnvType.CLIENT)
    public void addDynamicClientResources(Consumer<ResourceGenTask> executor) {
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


    // Ec tab
    @SuppressWarnings("unchecked")
    public ResourceKey<CreativeModeTab> getDedicatedTab() {
        return (ResourceKey<CreativeModeTab>) ECRegistry.MOD_TAB.getKey();
    }

    public abstract Collection<Class<? extends BlockType>> getAffectedTypes();

    //these have to be known in advance
    public String[] getServerResourcesNamespaces() {
        return new String[]{modId, myNamespace};
    }

    public String[] getClientResourcesNamespaces() {
        return new String[]{myNamespace};
    }


    //utility functions. TODO: remove all these
    //Don't use these! Use by reference instead! that's the whole point of making a non EC-owned module!

    @Deprecated(forRemoval = true)
    protected <T extends Block> Supplier<T> getModBlock(String id, Class<T> blockClass) {
        return memorize(id, BuiltInRegistries.BLOCK);
    }

    @Deprecated(forRemoval = true)
    protected Supplier<CreativeModeTab> getModTab(String id) {
        return memorize(id, BuiltInRegistries.CREATIVE_MODE_TAB);
    }

    //internal use only. If you are a mod adding a module in your mod use by reference
    @Deprecated(forRemoval = true)
    protected Supplier<Block> getModBlock(String id) {
        return getModBlock(id, Block.class);
    }

    @Deprecated(forRemoval = true)
    protected Supplier<Item> getModItem(String id) {
        return memorize(id, BuiltInRegistries.ITEM);
    }

    @Deprecated(forRemoval = true)
    protected <B extends BlockEntity> Supplier<BlockEntityType<B>> getModTile(String id, Class<B> tileEntityClass) {
        return memorize(id, BuiltInRegistries.BLOCK_ENTITY_TYPE);
    }

    @Deprecated(forRemoval = true)
    protected Supplier<BlockEntityType<BlockEntity>> getModTile(String id) {
        return getModTile(id, BlockEntity.class);
    }

    //how much crap this module has registered
    public abstract int bloatAmount();

    //used for creative tabs
    public <T extends BlockType> List<Item> getAllItemsOfType(T type) {
        return List.of();
    }


    @SuppressWarnings("unchecked")
    @Deprecated(forRemoval = true)
    public <T> Supplier<T> memorize(String id, Registry<?> reg) {
        return Suppliers.memoize(() -> {
            try {
                return (T) reg.getOptional(modRes(id))
                        .orElseThrow();
            } catch (Throwable e) {
                throw new IllegalStateException("Could not find \"" + id + "\" in " + reg + ". This likely means that the reigstry entry was renamed in the original mod and EC needs updating. " +
                        "Is the mod, " + this.getModName().toUpperCase() + " up to date, if yes, then downgrade to the previous version & wait for an Every Compat update. Otherwise, update the mod to the latest version.");
            }
        });
    }

}
