package net.mehvahdjukaar.every_compat.api;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.events.AfterLanguageLoadEvent;
import net.mehvahdjukaar.moonlight.api.misc.Registrator;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.assets.LangBuilder;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynClientResourcesGenerator;
import net.mehvahdjukaar.moonlight.api.resources.textures.Respriter;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
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
import java.util.function.Function;
import java.util.function.Supplier;


public abstract class CompatModule {

    protected final String modId;

    protected CompatModule(String modId) {
        this.modId = modId;
    }

    public String getModId() {
        return modId;
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
        if (registry.containsKey(new ResourceLocation(modId, name)) || //ones from the mod they are from. usually include vanilla types
                registry.containsKey(new ResourceLocation(modId, n2))) return true;
        if (this.shortenedId().equals("af")) return false; //hardcoding
        if (this.shortenedId().equals("ap")) return false; //hardcoding
        if (this.shortenedId().equals("vs")) return false; //we always register everything for these
        if (this.shortenedId().equals("abww") && woodFrom.equals("architects_palette")) return false; //we always register everything for these

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

    @Nullable
    protected final <T extends Block> T getModBlock(String id, Class<T> blockClass) {
        return (T) BuiltInRegistries.BLOCK.getOptional(modRes(id)).orElse(null);
    }

    @Nullable
    protected final CreativeModeTab getModTab(String id) {
        return BuiltInRegistries.CREATIVE_MODE_TAB.getOptional(modRes(id)).orElse(null);
    }

    @Nullable
    protected final Block getModBlock(String id) {
        return getModBlock(id, Block.class);
    }

    @Nullable
    protected final Item getModItem(String id) {
        return BuiltInRegistries.ITEM.getOptional(modRes(id)).orElse(null);
    }

    @Nullable
    protected final <T extends BlockEntityType<?>> T getModTile(String id, Class<T> blockClass) {
        return (T) BuiltInRegistries.BLOCK_ENTITY_TYPE.getOptional(modRes(id)).orElse(null);
    }

    @Nullable
    protected final BlockEntityType<?> getModTile(String id) {
        return getModTile(id, BlockEntityType.class);
    }

    //post process some textures. currently only ecologics azalea
    public void addWoodTexture(WoodType wood, DynClientResourcesGenerator handler, ResourceManager manager,
                               String path, Supplier<TextureImage> textureSupplier) {
        handler.addTextureIfNotPresent(manager, path, () -> {
            var t = textureSupplier.get();
            maybeFlowerAzalea(t, manager, wood);
            return t;
        });
    }

    //for ecologics
    protected void maybeFlowerAzalea(TextureImage image, ResourceManager manager, WoodType woodType) {
        if (woodType.getId().toString().equals("ecologics:flowering_azalea")) {
            WoodType azalea = WoodTypeRegistry.getValue(new ResourceLocation("ecologics:azalea"));
            if (azalea != null) {
                try (TextureImage mask = TextureImage.open(manager,
                        EveryCompat.res("block/ecologics_overlay"));
                     TextureImage plankTexture = TextureImage.open(manager,
                             RPUtils.findFirstBlockTextureLocation(manager, azalea.planks))) {

                    Respriter respriter = Respriter.of(image);
                    var temp = respriter.recolorWithAnimationOf(plankTexture);

                    image.applyOverlayOnExisting(temp, mask);
                    temp.close();

                } catch (Exception e) {
                    EveryCompat.LOGGER.warn("failed to apply azalea overlay: ", e);
                }
            }
        }
    }

    //how much crap this module has registered
    public abstract int bloatAmount();

    //used for creative tabs
    public <T extends BlockType> List<Item> getAllItemsOfType( T type){
        return List.of();
    }


}
