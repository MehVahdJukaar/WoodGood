package net.mehvahdjukaar.every_compat.api;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.configs.UnsafeDisablerConfigs;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.misc.HardcodedBlockType;
import net.mehvahdjukaar.moonlight.api.events.AfterLanguageLoadEvent;
import net.mehvahdjukaar.moonlight.api.misc.Registrator;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.mehvahdjukaar.moonlight.api.resources.assets.LangBuilder;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.*;
import java.util.function.Consumer;

import static net.mehvahdjukaar.every_compat.misc.HardcodedBlockType.FRAMED_BLOCKS_SUFFIX;

public class SimpleModule extends CompatModule {

    private Class<? extends BlockType> typeClazz = null;
    private final String shortId;
    protected String blockType = "";
    private final Map<String, EntrySet<?>> entries = new LinkedHashMap<>();
    private final Set<Class<? extends BlockType>> affectedTypes = new HashSet<>();

    protected int bloat = 0;

    public SimpleModule(String modId, String shortId) {
        super(modId);
        this.shortId = shortId;
    }

    public SimpleModule(String modId, String shortId, String myNamespace) {
        super(modId, myNamespace);
        this.shortId = shortId;
    }

    /// Adding extra info to {@link SimpleModule#toString()}
    public void setBlockType(String blockType) {
        this.blockType = blockType;
    }

    public String getModNameAndBlockType() {
        Optional<EntrySet<?>> entry = getEntries().stream().findFirst();
        String blockType = entry.map(entrySet -> entrySet.getTypeClass().getSimpleName()).orElse("");
        return modName + "'s " + blockType;
    }

    public final ResourceLocation makeMyRes(String name) {
        return new ResourceLocation(getMyNamespace(), name);
    }

    @Override
    public Collection<Class<? extends BlockType>> getAffectedTypes() {
        return affectedTypes;
    }

    @Override
    public int bloatAmount() {
        return bloat;
    }

    public <T extends BlockType, S extends EntrySet<T>> S addEntry(S entryHolder) {
        var old = this.entries.put(entryHolder.getName(), entryHolder);
        if (old != null) {
            throw new UnsupportedOperationException(String.format("This module already has an entry set with name %s", entryHolder.getName()));
        }
        this.affectedTypes.add(entryHolder.getTypeClass());
        //ugly
        EveryCompat.trackChildType(entryHolder.getTypeClass(), entryHolder.makeChildKey(this));
        return entryHolder;
    }

    public Collection<EntrySet<?>> getEntries() {
        return entries.values();
    }

    public EntrySet<?> getEntry(String name) {
        return entries.get(name);
    }

    @Override
    public String shortenedId() {
        return shortId;
    }

    @Override
    public void addTranslations(ClientDynamicResourcesHandler clientDynamicResourcesHandler, AfterLanguageLoadEvent lang) {
        getEntries().forEach(e -> e.addTranslations(this, lang));
    }

    public <T extends BlockType> void registerBlocks(Class<T> typeClass,
                                                     Registrator<Block> registry, Collection<T> types) {
        this.typeClazz = typeClass;
        int blockCount = 0;
        for (var e : getEntries()) {
            if (e.getTypeClass().isAssignableFrom(typeClass)) {
                registerBlocksTyped(registry, types, e);
                blockCount += e.getBlockCount();
            }
        }
        bloat += blockCount;
        if (blockCount > 0) EveryCompat.LOGGER.info("{}: registered {} {} blocks", this, blockCount, typeClass.getSimpleName());
    }

    @SuppressWarnings("unchecked")
    private <T extends BlockType> void registerBlocksTyped(Registrator<Block> registry,
                                                           Collection<?> types, EntrySet<T> e) {
        e.registerBlocks(this, registry, (Collection<T>) types);
    }

    @Override
    public void registerItems(Registrator<Item> registry) {
        int blockCount = 0;
        for (EntrySet<?> e : getEntries()) {
                e.registerItems(this, registry);
                if (e instanceof ItemOnlyEntrySet<?,?>) blockCount += e.getBlockCount();
        }
        bloat += blockCount;
        if (blockCount > 0) EveryCompat.LOGGER.info("{}: registered {} {} items", this, blockCount, typeClazz.getSimpleName());
    }

    @Override
    public void registerTiles(Registrator<BlockEntityType<?>> registry) {
        getEntries().forEach(e -> e.registerTiles(this, registry));
    }

    @Override
    public void addDynamicServerResources(Consumer<ResourceGenTask> executor) {
        getEntries().forEach(e -> executor.accept((manager, sink) -> {
            try {
                e.generateLootTables(this, manager, sink);
                e.generateRecipes(this, manager, sink);
                e.generateTags(this, manager, sink);

            } catch (Exception ex) {
                EveryCompat.LOGGER.error("Failed to generate server resources for entry set {} from module {}:", e, this, ex);
                if (PlatHelper.isDev()) throw ex;
            }
        }));
        executor.accept((manager, sink) ->
                addDynamicServerResources(ServerDynamicResourcesHandler.getInstance(), manager));

    }

    /// @deprecated -> USE {@link SimpleModule#addDynamicServerResources(Consumer)}
    @Deprecated(forRemoval = true)
    public void addDynamicServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
    }

    @Override
    public String toString() {
        String typeOrEmpty = (!blockType.isEmpty()) ? "'s " + blockType : "";
        return "[WoodGood - " + LangBuilder.getReadableName(modId) + typeOrEmpty + " Module]";
    }

    @Override
    public void addDynamicClientResources(Consumer<ResourceGenTask> executor) {
        for (var entry : getEntries()) {
            executor.accept((manager, sink) -> {
                try {
                    entry.generateTextures(this, manager, sink);
                    entry.generateModels(this, manager, sink);

                } catch (Exception ex) {
                    EveryCompat.LOGGER.error("Failed to generate client resources for EntrySet: {} from module {}:", entry, this, ex);
                    if (PlatHelper.isDev()) throw ex;
                }
            });
        }

        executor.accept((manager, sink) ->
                addDynamicClientResources(ClientDynamicResourcesHandler.getInstance(), manager)
        );
    }

    /// @deprecated -> USE {@link SimpleModule#addDynamicClientResources(Consumer)}
    @Deprecated(forRemoval = true)
    public void addDynamicClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
    }


    @Override
    public void registerBlockColors(ClientHelper.BlockColorEvent event) {
        getEntries().forEach(e -> e.registerBlockColors(event));
    }

    @Override
    public void registerItemColors(ClientHelper.ItemColorEvent event) {
        getEntries().forEach(e -> e.registerItemColors(event));
    }

    @Override
    public void onClientSetup() {
        getEntries().forEach(EntrySet::setRenderLayer);
    }

    @Override
    public void onModSetup() {
        getEntries().forEach(EntrySet::setupExistingTiles);
    }

    public static void appendTileEntityBlocks(BlockEntityType<?> be, Collection<? extends Block> blocks) {
        be.validBlocks = new HashSet<>(be.validBlocks);
        be.validBlocks.addAll(blocks);
    }

    @Override
    public void registerItemsToExistingTabs(RegHelper.ItemToTabEvent event) {
        getEntries().forEach(e -> e.registerItemsToExistingTabs(this, event));
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends BlockType> List<Item> getAllItemsOfType(T type) {
        List<Item> l = new ArrayList<>();
        for (EntrySet<?> entrySet : entries.values()) {
            if (entrySet.getTypeClass().isAssignableFrom(type.getClass())) {
                var itemOfType = ((EntrySet<T>) entrySet).getItemForECTab(type);
                if (itemOfType != null) l.add(itemOfType);
            }
        }
        return l;
    }

    @Deprecated(forRemoval = true)
    public boolean isEntryAlreadyRegistered(String entrySetId, String blockId, BlockType blockType, Registry<?> registry) {
        return isEntryAlreadyRegistered(entrySetId, new ResourceLocation(blockId), blockType, registry);
    }


    //TODO: improve
    public boolean isEntryAlreadyRegistered(String entrySetId, ResourceLocation blockId, BlockType blockType, Registry<?> registry) {

        if (registry.containsKey(blockId)) {
            EveryCompat.crashIfInDev("Attempted to register a block with id: (" + blockType.getClass().getSimpleName()
                    + ")" + blockId + " that had another one already registered in registry via " + registry.key().location() + "!"
            );
            return true;
        }

        String blockPath = blockId.getPath();
        //short block name - blockName: willow_table from the blockId: everycomp:tw/biomesoplenty/willow_table
        String blockName = blockPath.substring(blockPath.lastIndexOf("/") + 1);

        String woodTypeFrom = blockType.getNamespace();

        String slashConvention = woodTypeFrom + "/" + blockName; // quark/blossom_chair
        String underscoreConvention = woodTypeFrom + "_" + blockName; // quark_blossom_chair

        // Excude Supported-Mods' blocks that are similar to blocks from Framed-Blocks
        if (UnsafeDisablerConfigs.ENABLE_FRAMED_BLOCKS_BLACKLIST.get() && PlatHelper.isModLoaded("framedblocks") && FRAMED_BLOCKS_SUFFIX.stream().anyMatch(blockName::contains)) return true;

        // Exclude blocks from a mod that are both Supported-Mod & Wood-Mod
        if (woodTypeFrom.equals(modId)) return true; //Example: quark, blossom

        // Exclude blocks when a supported-mod have an internal support for mods that add new BlockTypes
        if (this.getAlreadySupportedMods().contains(woodTypeFrom)) return true;

        // ugly hardcoded stuff
        if (blockType instanceof WoodType woodType) {
            Boolean hardcoded = HardcodedBlockType.isWoodBlockAlreadyRegistered(entrySetId, blockName, woodType, modId);
            if (hardcoded != null) return hardcoded;
        } else if (blockType instanceof LeavesType leavesType) {
            Boolean hardcoded = HardcodedBlockType.isLeavesBlockAlreadyRegistered(entrySetId, blockName, leavesType, modId);
            if (hardcoded != null) return hardcoded;
        }

        /// ========== EXCLUDE ========== \\\

        // Discards the supportedBlockName being already in the supportedModId & Vanilla blockType (mod has builtin compat)
        if (registry.containsKey(new ResourceLocation(modId, blockName)) ||
                registry.containsKey(new ResourceLocation(modId, underscoreConvention))) {
            //check for false positives (block types with same names)
            if (WoodTypeRegistry.INSTANCE.get(modRes(blockType.getTypeName())) == null) {
                return true;
                //if my own mod adds mymod:that_wood it means that block I found its actually mine, hence a new one should be added as they are different
            }
        }

        if (registry.containsKey(new ResourceLocation(woodTypeFrom, blockName)))
            return true; //REASON: prevent duplicated blocks for now & above is WIP for now

        for (var c : EveryCompat.getCompatMods()) {
            String compatModId = c.modId();  //bopcomp : bop->quark, twigs
            //if the wood is from the mod this adds compat for && it supports this block type
            if (c.woodsFrom().contains(woodTypeFrom) && c.blocksFrom().contains(modId)) {
                if (registry.containsKey(new ResourceLocation(compatModId, blockName))) return true;
                if (registry.containsKey(new ResourceLocation(compatModId, slashConvention)))
                    return true;
                if (registry.containsKey(new ResourceLocation(compatModId, underscoreConvention)))
                    return true;
            }
        }
        return false;
    }

}
