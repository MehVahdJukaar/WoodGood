package net.mehvahdjukaar.every_compat.api;

import com.google.common.base.Suppliers;
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
import org.jetbrains.annotations.Nullable;

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

        if (this.getAlreadySupportedMods().contains(woodFrom)) return true;

        // check if TerraFirmaCraft (tfc) mod exist, then won't discards wood types
        if (woodFrom.equals("tfc")) return false;

        //discards wood types from this mod
        if (woodFrom.equals(modId)) return true; //quark, blossom

        String n1 = woodFrom + "/" + name; //quark/blossom_chair
        String n2 = woodFrom + "_" + name; //quark_blossom_chair
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
        if (woodType.getId().toString().equals("regions_unexplored:aspen")) {
            return name.equals("vct:aspen_crafting_table");
        }
        if (woodType.getId().toString().equals("regions_unexplored:cherry")) {
            return name.equals("vct:cherry_crafting_table");
        }
        if (woodType.getId().toString().equals("regions_unexplored:fir")) {
            return name.equals("vct:fir_crafting_table");
        }
        if (woodType.getId().toString().equals("regions_unexplored:jacaranda")) {
            return name.equals("vct:jacaranda_crafting_table");
        }
        if (woodType.getId().toString().equals("regions_unexplored:maple")) {
            return name.equals("vct:maple_crafting_table");
        }
        if (woodType.getId().toString().equals("regions_unexplored:willow")) {
            return name.equals("vct:willow_crafting_table");
        }

        if (registry.containsKey(new ResourceLocation(modId, name)) || //ones from the mod they are from. usually include vanilla types
                registry.containsKey(new ResourceLocation(modId, n2))) return true;
        if (this.shortenedId().equals("af")) return false; //hardcoding
        if (this.shortenedId().equals("ap")) return false; //hardcoding
        if (this.shortenedId().equals("vs")) return false; //we always register everything for these
        if (this.shortenedId().equals("abww") && woodFrom.equals("architects_palette"))
            return false; //we always register everything for these

        if (registry.containsKey(new ResourceLocation(woodFrom, name))) return true;

        for (var c : EveryCompat.COMPAT_MODS) {
            String compatModId = c.modId();  //bopcomp : bop->quark, twigs
            //if the wood is from the mod this adds compat for && it supports this block type
            if (woodFrom.equals(c.woodFrom()) && c.blocksFrom().contains(modId)) {
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

    public void addDynamicClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {

    }

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
