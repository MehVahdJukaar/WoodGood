package net.mehvahdjukaar.every_compat.modules;

import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.selene.block_set.BlockType;
import net.mehvahdjukaar.selene.block_set.leaves.LeavesType;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.mehvahdjukaar.selene.block_set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.selene.client.asset_generators.LangBuilder;
import net.mehvahdjukaar.selene.client.asset_generators.textures.Respriter;
import net.mehvahdjukaar.selene.client.asset_generators.textures.TextureImage;
import net.mehvahdjukaar.selene.resourcepack.AfterLanguageLoadEvent;
import net.mehvahdjukaar.selene.resourcepack.RPAwareDynamicTextureProvider;
import net.mehvahdjukaar.selene.resourcepack.RPUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.function.Function;
import java.util.function.Supplier;


public abstract class CompatModule {

    protected final String modId;

    public CompatModule(String modId) {
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

    public String makeBlockId(BlockType type, String blockName) {
        return this.shortenedId() + "/" + type.getVariantId(blockName, false);
    }

    public void onModSetup() {

    }

    public void onClientSetup() {

    }

    public void registerWoodBlocks(IForgeRegistry<Block> registry, Collection<WoodType> woodTypes) {

    }

    public void registerLeavesBlocks(IForgeRegistry<Block> registry, Collection<LeavesType> leavesTypes) {

    }

    public void registerItems(IForgeRegistry<Item> registry) {

    }

    public void registerTiles(IForgeRegistry<BlockEntityType<?>> registry) {

    }

    public void registerEntities(IForgeRegistry<EntityType<?>> registry) {

    }

    //TODO: improve
    public final boolean isEntryAlreadyRegistered(String name, BlockType woodType, IForgeRegistry<?> registry) {
        //ec:twigs/bop/willow_table
        name = name.substring(name.lastIndexOf("/")+1); //gets the base name

        String woodFrom = woodType.getNamespace();
        //discards wood types from this mod
        if (woodFrom.equals(modId)) return true; //quark, blossom

        String n1 = woodFrom + "/" + name; //quark/blossom_chair
        String n2 = woodFrom + "_" + name; //quark_blossom_chair

        if (registry.containsKey(new ResourceLocation(modId, name)) || //ones from the mod they are from. usually include vanilla types
                registry.containsKey(new ResourceLocation(modId, n2))) return true;
        if (this.shortenedId().equals("af")) return false; //hardcoding
        if (this.shortenedId().equals("vs")) return false; //we always register everything for these


        if (registry.containsKey(new ResourceLocation(woodFrom, name))) return true;

        for (var c : WoodGood.COMPAT_MODS) {
            String compatModId = c.modId();  //bopcomp : bop->quark, twigs
            //if the wood is from the mod this adds compat for && it supports this block type
            if(woodFrom.equals(c.woodFrom()) && c.blocksFrom().contains(modId)){
                if (registry.containsKey(new ResourceLocation(compatModId, name))) return true;
                if (registry.containsKey(new ResourceLocation(compatModId, n2))) return true;
                if (registry.containsKey(new ResourceLocation(compatModId, n2))) return true;
            }
        }
        return false;
    }

    //resource pack stuff

    public void addStaticServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {

    }


    public void addDynamicServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {

    }

    public void addStaticClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {

    }

    public void addDynamicClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {

    }

    public void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {

    }

    public void addTranslations(ClientDynamicResourcesHandler clientDynamicResourcesHandler, AfterLanguageLoadEvent lang) {
    }

    public void registerColors(ColorHandlerEvent.Item event) {
    }

    @OnlyIn(Dist.CLIENT)
    public void onTextureStitch(TextureStitchEvent.Pre event) {
    }


    protected final Block getModBlock(String id) {
        return ForgeRegistries.BLOCKS.getValue(modRes(id));
    }
    //utility functions

    protected final void addChildToOak(String category, String oakBlockName) {
        WoodType.OAK_WOOD_TYPE.addChild(category, ForgeRegistries.BLOCKS.getValue(modRes(oakBlockName)));
    }


    //post process some textures. currently only ecologics azalea
    public void addWoodTexture(WoodType wood, RPAwareDynamicTextureProvider handler, ResourceManager manager,
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
            WoodType azalea = WoodTypeRegistry.WOOD_TYPES.get(new ResourceLocation("ecologics:azalea"));
            if (azalea != null) {
                try (TextureImage mask = TextureImage.open(manager,
                        WoodGood.res("block/ecologics_overlay"));
                     TextureImage plankTexture = TextureImage.open(manager,
                             RPUtils.findFirstBlockTextureLocation(manager, azalea.planks))) {

                    Respriter respriter = Respriter.of(image);
                    var temp = respriter.recolorWithAnimationOf(plankTexture);

                    image.applyOverlayOnExisting(temp, mask);
                    temp.close();

                } catch (Exception e) {
                    WoodGood.LOGGER.warn("failed to apply azalea overlay: ", e);
                }
            }
        }
    }

    public <T extends BlockType, B extends Block> Function<T, @Nullable B> ifHasChild(Function<T, B> supplier, String... children) {
        return w -> {
            for (var v : children) {
                if (w.getChild(v) == null) return null;
            }
            return supplier.apply(w);
        };
    }


    public void onFirstClientTick(){};
}
