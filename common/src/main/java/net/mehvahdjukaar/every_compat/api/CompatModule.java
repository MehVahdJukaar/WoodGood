package net.mehvahdjukaar.every_compat.api;

import com.google.common.base.Suppliers;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
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
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;


public abstract class CompatModule {

    protected final String modId;
    protected final String modName;

    protected CompatModule(String modId) {
        this.modId = modId;
        this.modName = PlatHelper.getModName(modId);
    }

    public String getModId() {
        return modId;
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

    //TODO: improve
    public final boolean isEntryAlreadyRegistered(String name, BlockType woodType, Registry<?> registry) {
        //ec:twigs/bop/willow_table
        name = name.substring(name.lastIndexOf("/") + 1); //gets the base name

        String woodFrom = woodType.getNamespace();

        String n1 = woodFrom + "/" + name; //quark/blossom_chair
        String n2 = woodFrom + "_" + name; //quark_blossom_chair

        if (this.getAlreadySupportedMods().contains(woodFrom)) return true;

        // Garden-Of-The-dead's whistle must be skipped for branches from Regions-Unexplored
        // Nether's Exoticism & Snifferent already has branches, branches from Regions-Unexplored is not needed
        if ( (woodFrom.equals("gardens_of_the_dead") || woodFrom.equals("snifferent") ||
                woodType.getId().toString().equals("nethers_exoticism:jabuticaba")) && name.contains("branch"))
            return true;

        // CB: Compressed Blocks must be skipped
        if (woodFrom.equals("compressedblocks")) return true;

        // Quark & Woodworks have chest & trapped_chest
        if (woodFrom.equals("quark") && shortenedId().equals("abnww") && name.contains("chest")) return true;

        // Macaw's Fences&Walls or MrCrayFish's Furniture - hedges will be skipped because Quark already has hedges
        if (woodFrom.equals("quark") && (shortenedId().equals("mcf") || shortenedId().equals("cfm"))) return false;

        // Create's windows will be skipped blc [Let's do] Blooming Nature & Meadow already has windows
        if (( woodFrom.equals("bloomingnature") || woodFrom.equals("meadow") ) && name.contains("window")) return false;

        // ArchitectPalette's boards will be skipped blc Upgrade-Aqautic already has boards but have no recipes &
        // no item in CreativeMode
        if (woodFrom.equals("upgrade_aquatic") && ( name.equals("driftwood_boards") || name.equals("river_boards") ))
            return false;

        // Similar to above, Architect's Palette - boards will be skipped due to the existing boards in Autumnity
        if (woodFrom.equals("autumnity") && name.equals("maple_boards")) return false;

        // check if TerraFirmaCraft (tfc) mod exist, then won't discards wood types
        if (woodFrom.equals("tfc")) return false;

        //discards wood types from this mod
        if (woodFrom.equals(modId)) return true; //quark, blossom

        if (woodType.getId().toString().equals("ecologics:azalea")) {
            if (modId.equals("quark")) return false; //ecologics and quark azalea. tbh not sure why needed
        }
        if (woodType.getId().toString().equals("twilightforest:mangrove")) {
            return name.equals("mangrove_chest");//mangrove waaa so much pain
        }

        if (woodType.getId().toString().equals("architects_palette:twisted")) {
            return name.equals("vct:twisted_crafting_table");
        }
        if (woodType.getId().toString().equals("biomesoplenty:fir")) {
            return name.equals("vct:fir_crafting_table");
        }
        if (woodType.getId().toString().equals("biomesoplenty:jacaranda")) {
            return name.equals("vct:jacaranda_crafting_table");
        }
        if (woodType.getId().toString().equals("biomesoplenty:maple")) {
            return name.equals("vct:maple_crafting_table");
        }
        if (woodType.getId().toString().equals("ecologics:azalea")) {
            return name.equals("vct:azalea_crafting_table");
        }
        if (woodType.getId().toString().equals("ecologics:walnut")) {
            return name.equals("vct:walnut_crafting_table");
        }

        if (registry.containsKey(new ResourceLocation(modId, name)) || //ones from the mod they are from. usually include vanilla types
                registry.containsKey(new ResourceLocation(modId, n2))) return true;
        if (this.shortenedId().equals("af")) return false; //hardcoding
        // if (this.shortenedId().equals("ap")) return false; //hardcoding dont remember why i had this. Incase you want o
        if (this.shortenedId().equals("vs")) return false; //we always register everything for these
        if (this.shortenedId().equals("abnww") && woodFrom.equals("architects_palette"))
            return false; //we always register everything for these

        if (registry.containsKey(new ResourceLocation(woodFrom, name))) return true;

        for (var c : EveryCompat.COMPAT_MODS) {
            String compatModId = c.modId();  //bopcomp : bop->quark, twigs
            //if the wood is from the mod this adds compat for && it supports this block type
            if (c.woodsFrom().contains(woodFrom) && c.blocksFrom().contains(modId)) {
                if (registry.containsKey(new ResourceLocation(compatModId, name))) return true;
                if (registry.containsKey(new ResourceLocation(compatModId, n1))) return true;
                if (registry.containsKey(new ResourceLocation(compatModId, n2))) return true;
            }
        }
        return false;
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
        return Suppliers.memoize(() -> (T) BuiltInRegistries.BLOCK.getOptional(modRes(id)).orElse(null));
    }

    protected final Supplier<CreativeModeTab> getModTab(String id) {
        return Suppliers.memoize(() -> BuiltInRegistries.CREATIVE_MODE_TAB.getOptional(modRes(id)).orElse(null));
    }

    protected final Supplier<Block> getModBlock(String id) {
        return getModBlock(id, Block.class);
    }

    protected final Supplier<Item> getModItem(String id) {
        return Suppliers.memoize(() -> BuiltInRegistries.ITEM.getOptional(modRes(id)).orElse(null));
    }

    protected final <T extends BlockEntityType<?>> Supplier<T> getModTile(String id, Class<T> blockClass) {
        return Suppliers.memoize(() -> (T) BuiltInRegistries.BLOCK_ENTITY_TYPE.getOptional(modRes(id)).orElse(null));
    }

    protected final <T extends BlockEntityType<?>> Supplier<T> getModTile(String id) {
        return (Supplier<T>)(Object) getModTile(id, BlockEntityType.class);
    }

    //how much crap this module has registered
    public abstract int bloatAmount();

    //used for creative tabs
    public <T extends BlockType> List<Item> getAllItemsOfType(T type) {
        return List.of();
    }


}
