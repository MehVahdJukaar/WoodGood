package net.mehvahdjukaar.every_compat.api;

import net.mehvahdjukaar.every_compat.ECRegistry;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.moonlight.api.events.AfterLanguageLoadEvent;
import net.mehvahdjukaar.moonlight.api.misc.Registrator;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;


public abstract class CompatModule {

    protected final String modId;
    protected final String modName; //readable name
    protected final String shortId;

    /// use {@link CompatModule#extendModuleName(String)}
    @Deprecated(forRemoval = true)
    protected String blockType = "";

    private String extendedName = "";

    //EC or addon namespace
    private final String myNamespace;

    protected CompatModule(String modId, String shortId, String myNamespace) {
        this.modId = modId;
        this.modName = Objects.requireNonNull(PlatHelper.getModName(modId), "Could not find mod name for " + modId);
        this.myNamespace = myNamespace;
        this.shortId = shortId;
        if (myNamespace.equals("minecraft")){
            throw new AssertionError("Every Compat module namespace cannot be minecraft");
        }
        //yeah, bad api but doesn't matter if we use wood or other types
        //called here so we get right bus since module construction is delegated
        BlockSetAPI.addDynamicRegistration(myNamespace, (regBlock) -> {
            EveryCompat.executeOrFail(() -> {
                for (Class<? extends BlockType> typeClass : this.getAffectedTypes()) {
                    this.registerBlocks(typeClass, regBlock);
                }
            }, this);
        }, BuiltInRegistries.BLOCK);

        BlockSetAPI.addDynamicRegistration(myNamespace, (regItem) -> {
            EveryCompat.executeOrFail(() -> {
                this.registerItems((resourceLocation, item) -> {
                    regItem.register(resourceLocation, item);
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

    /// Adding extra info to {@link CompatModule#toString()}
    /// <br>
    /// return ModuleName's blockType
    /// @deprecated use {@link CompatModule#extendModuleName(String)}
    /// @param blockType "Log", "Planks", "Leaves" can be used
    @Deprecated(forRemoval = true)
    ///@deprecated use {@link CompatModule#extendModuleName(String)}
    public void setBlockType(String blockType) {
        extendModuleName("'s " + blockType);
    }

    /// Adding extra info to {@link CompatModule#toString()}
    ///<br>return "Module's Name" or "Module Name"
    ///@param name The parameter are
    ///            <br>* "'s Name"
    ///            <br>* " Name" with a space
    public void extendModuleName(String name) {
        this.extendedName = name;
    }

    @Override
    public String toString() {
        return "module[ " + getModName() + extendedName + " @ " + getMyNamespace() + " ]";
    }

    public Identifier modRes(String string) {
        return Identifier.fromNamespaceAndPath(modId, string);
    }

    public List<String> getAlreadySupportedMods() {
        return List.of();
    }

    public void onModInit() {
    }

    public void onModSetup() {
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

    public void registerItemsToExistingTabs(RegHelper.ItemToTabEvent event) {
    }

    /// Only called on client. Override to give the module its own client class
    public CompatModuleClient<?> createClient() {
        return new CompatModuleClient<>(this);
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

    //how much crap this module has registered
    public abstract int bloatAmount();

    //used for creative tabs
    public <T extends BlockType> List<Item> getAllItemsOfType(T type) {
        return List.of();
    }

}
