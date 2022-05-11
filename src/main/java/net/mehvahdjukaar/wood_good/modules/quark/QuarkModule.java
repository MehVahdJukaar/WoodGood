package net.mehvahdjukaar.wood_good.modules.quark;

import net.mehvahdjukaar.selene.block_set.leaves.LeavesType;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.mehvahdjukaar.selene.resourcepack.ResType;
import net.mehvahdjukaar.selene.resourcepack.asset_generators.LangBuilder;
import net.mehvahdjukaar.wood_good.WoodGood;
import net.mehvahdjukaar.wood_good.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.wood_good.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.wood_good.modules.CompatModule;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.IForgeRegistry;
import vazkii.arl.util.RegistryHelper;
import vazkii.quark.base.handler.ToolInteractionHandler;
import vazkii.quark.base.module.ModuleLoader;
import vazkii.quark.content.building.block.WoodPostBlock;
import vazkii.quark.content.building.module.WoodenPostsModule;

import java.lang.reflect.Field;
import java.util.*;

public class QuarkModule extends CompatModule {

    public QuarkModule(String modId) {
        super(modId);
    }

    @Override
    public String shortenedId() {
        return "q";
    }

    public static final String POST_NAME = "post";
    public static final Map<WoodType, Block> POSTS = new HashMap<>();
    public static final Map<WoodType, Item> POST_ITEMS = new HashMap<>();

    public static final String STRIPPED_POST = "stripped_post";
    public static final Map<WoodType, Block> STRIPPED_POSTS = new HashMap<>();
    public static final Map<WoodType, Item> STRIPPED_POST_ITEMS = new HashMap<>();

    public static final String VERTICAL_SLAB_NAME = "vertical_slab";
    public static final Map<WoodType, Block> VERTICAL_SLABS = new HashMap<>();
    public static final Map<WoodType, Item> SUPPORTS_ITEMS = new HashMap<>();

    public static final String VERTICAL_PLANK_NAME = "vertical_plank";
    public static final Map<WoodType, Block> VERTICAL_PLANKS = new HashMap<>();
    public static final Map<WoodType, Item> VERTICAL_PLANK_ITEMS = new HashMap<>();


    @Override
    public void registerWoodBlocks(IForgeRegistry<Block> registry, Collection<WoodType> woodTypes) {
        //HAAAACK
        //removes stuff from autoreglib since it's too late to let it register these and we are registering them manually
        Map<String, ?> ARLModData;
        try {
            Field f = ObfuscationReflectionHelper.findField(RegistryHelper.class, "modData");
            f.setAccessible(true);
            ARLModData = (Map<String, ?>) f.get(null);
        } catch (Exception e) {
            WoodGood.LOGGER.error("Failed to setup Wood Good Quark Module");
            return;
        }
        //posts
        for (WoodType w : woodTypes) {

            Block fence = w.fence;
            if (fence != null) {
                boolean nether = !w.canBurn();
                String name = w.getVariantId(POST_NAME, false);
                String strippedName = makeBlockId(w,STRIPPED_POST);
                if (w.isVanilla() || !shouldRegisterEntry(name, registry)) continue;

                var module = ModuleLoader.INSTANCE.getModuleInstance(WoodenPostsModule.class);
                String append = shortenedId()+"/"+w.getNamespace() + "/";

                Block post = new WoodPostBlock(module, fence, append, nether);
                POSTS.put(w, post);
                registry.register(post);

                //thanks twigs T_T
                if (!w.getTypeName().contains("stripped")) {
                    Block stripped = new WoodPostBlock(module, fence, append + "stripped_", nether);
                    STRIPPED_POSTS.put(w, stripped);
                    registry.register(stripped);
                    ToolInteractionHandler.registerInteraction(ToolActions.AXE_STRIP, post, stripped);
                }
            }
        }

        ARLModData.remove(WoodGood.MOD_ID);
    }


    @Override
    public void registerLeavesBlocks(IForgeRegistry<Block> registry, Collection<LeavesType> leavesTypes) {

    }

    @Override
    public void registerItems(IForgeRegistry<Item> registry) {
        CreativeModeTab tab = CreativeModeTab.TAB_DECORATIONS;

        POSTS.forEach((key, value) -> {
            Item i = new BlockItem(value, new Item.Properties().tab(tab));
            POST_ITEMS.put(key, i);
            registry.register(i.setRegistryName(value.getRegistryName()));
        });
        STRIPPED_POSTS.forEach((key, value) -> {
            Item i = new BlockItem(value, new Item.Properties().tab(tab));
            STRIPPED_POST_ITEMS.put(key, i);
            registry.register(i.setRegistryName(value.getRegistryName()));
        });
    }

    @Override
    public void onClientSetup(FMLClientSetupEvent event) {
        POSTS.values().forEach(t -> ItemBlockRenderTypes.setRenderLayer(t, RenderType.cutout()));
        STRIPPED_POSTS.values().forEach(t -> ItemBlockRenderTypes.setRenderLayer(t, RenderType.cutout()));
    }


    @Override
    public void addStaticServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
        var pack = handler.dynamicPack;
        List<ResourceLocation> posts = new ArrayList<>();
        POSTS.forEach((wood, value) -> {
            pack.addSimpleBlockLootTable(value);
            posts.add(value.getRegistryName());
        });
        STRIPPED_POSTS.forEach((wood, value) -> {
            pack.addSimpleBlockLootTable(value);
            posts.add(value.getRegistryName());
        });
        pack.addTag(new ResourceLocation("supplementaries", "posts"), posts, Registry.BLOCK_REGISTRY);
        pack.addTag(new ResourceLocation("mineable/axe"), posts, Registry.BLOCK_REGISTRY);

    }


    @Override
    public void addStaticClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager, LangBuilder langBuilder) {
        //posts
        POSTS.forEach((w, v) -> langBuilder.addEntry(v, w.getNameForTranslation(POST_NAME)));

        this.addBlockResources(manager, handler, POSTS, "oak_post",
                ResType.ITEM_MODELS.getPath(modRes("oak_post")),
                ResType.BLOCKSTATES.getPath(modRes("oak_post"))
        );
        this.addBlockResources(manager, handler, POSTS,
                (s, id) -> s.replace("minecraft:block/" + "oak_post", modId+ ":block/" + id.replace("_post","")),
                (s, id) -> s.replace("oak_post", id),
                ResType.BLOCK_MODELS.getPath(modRes("oak_post"))
        );

        STRIPPED_POSTS.forEach((w, v) -> langBuilder.addEntry(v, w.getNameForTranslation(STRIPPED_POST)));

        this.addBlockResources(manager, handler, STRIPPED_POSTS, "stripped_oak_post",
                ResType.ITEM_MODELS.getPath(modRes("stripped_oak_post")),
                ResType.BLOCKSTATES.getPath(modRes("stripped_oak_post"))
        );
        //TODO: do this properly
        this.addBlockResources(manager, handler, STRIPPED_POSTS,
                (s, id) -> s.replace("minecraft:block/" + "stripped_oak", id.split(":")[0]+ ":block/" +id.split(":")[1].replace("_post","")),
                (s, id) -> s.replace("stripped_oak_post", id),
                ResType.BLOCK_MODELS.getPath(modRes("stripped_oak_post"))
        );
    }
}

