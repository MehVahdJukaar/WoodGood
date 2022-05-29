package net.mehvahdjukaar.every_compat.modules.quark;

import net.mehvahdjukaar.selene.block_set.leaves.LeavesType;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.mehvahdjukaar.selene.client.asset_generators.LangBuilder;
import net.mehvahdjukaar.selene.items.WoodBasedBlockItem;
import net.mehvahdjukaar.selene.resourcepack.BlockTypeResourceTransform;
import net.mehvahdjukaar.selene.resourcepack.DynamicLanguageManager;
import net.mehvahdjukaar.selene.resourcepack.ResType;
import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.modules.CompatModule;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;
import net.minecraftforge.registries.IForgeRegistry;
import vazkii.arl.util.RegistryHelper;
import vazkii.quark.base.handler.ToolInteractionHandler;
import vazkii.quark.base.module.ModuleLoader;
import vazkii.quark.content.building.block.HedgeBlock;
import vazkii.quark.content.building.block.WoodPostBlock;
import vazkii.quark.content.building.module.HedgesModule;
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

    public static final String HEDGE_NAME = "hedge";
    public static final Map<LeavesType, Block> HEDGES = new HashMap<>();
    public static final Map<LeavesType, Item> HEDGE_ITEMS = new HashMap<>();


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
        addChildToOak(shortenedId() + "/post", "oak_post");
        addChildToOak(shortenedId() + "/stripped_post", "stripped_oak_post");
        for (WoodType w : woodTypes) {

            Block fence = w.getBlockOfThis("fence");
            if (fence != null) {
                boolean nether = !w.canBurn();
                String name = w.getVariantId(POST_NAME, false);
                String strippedName = makeBlockId(w, STRIPPED_POST);
                if (w.isVanilla() || !shouldRegisterEntry(name, registry)) continue;

                var module = ModuleLoader.INSTANCE.getModuleInstance(WoodenPostsModule.class);
                String prefix = shortenedId() + "/" + w.getNamespace() + "/";

                Block post = new WoodPostBlock(module, fence, prefix, nether);
                POSTS.put(w, post);
                registry.register(post);
                w.addChild(shortenedId() + "/post", post);

                //thanks twigs T_T
                if (!w.getTypeName().contains("stripped")) {
                    Block stripped = new WoodPostBlock(module, fence, prefix + "stripped_", nether);
                    STRIPPED_POSTS.put(w, stripped);
                    registry.register(stripped);
                    ToolInteractionHandler.registerInteraction(ToolActions.AXE_STRIP, post, stripped);
                    w.addChild(shortenedId() + "/stripped_post", stripped);
                }
            }
        }

        ARLModData.remove(WoodGood.MOD_ID);
    }

    @Override
    public void registerLeavesBlocks(IForgeRegistry<Block> registry, Collection<LeavesType> leavesTypes) {
        //HAAAACK
        //removes stuff from autoreglib since it's too late to let it register these and we are registering them manually
        Field regName;
        Map<String, ?> ARLModData;
        try {
            Field f = ObfuscationReflectionHelper.findField(RegistryHelper.class, "modData");
            f.setAccessible(true);
            ARLModData = (Map<String, ?>) f.get(null);

            regName = ObfuscationReflectionHelper.findField(ForgeRegistryEntry.class, "registryName");
            regName.setAccessible(true);
        } catch (Exception e) {
            WoodGood.LOGGER.error("Failed to setup Wood Good Quark Module");
            return;
        }
        //hedges
        LeavesType.OAK_LEAVES_TYPE.addChild(shortenedId() + "/hedge", ForgeRegistries.BLOCKS.getValue(modRes("oak_hedge")));
        for (LeavesType l : leavesTypes) {
            String name = makeBlockId(l, HEDGE_NAME);
            if (l.isVanilla() || !shouldRegisterEntry(name, registry)) continue;
            if (l.woodType != null) {
                Block fence = l.woodType.getBlockOfThis("fence");
                if (fence != null) {
                    var module = ModuleLoader.INSTANCE.getModuleInstance(HedgesModule.class);

                    Block block = new HedgeBlock(module, fence, l.leaves);
                    try {
                        regName.set(block, WoodGood.res(name));
                        HEDGES.put(l, block);
                        registry.register(block);
                        l.addChild(shortenedId() + "/hedge", block);

                    } catch (Exception e) {
                        throw new UnsupportedOperationException(String.format("Failed to set registry name for %s hedge", l));
                    }
                }
            }
        }

        ARLModData.remove(WoodGood.MOD_ID);
    }

    @Override
    public void registerItems(IForgeRegistry<Item> registry) {
        CreativeModeTab tab = CreativeModeTab.TAB_DECORATIONS;

        POSTS.forEach((w, value) -> {
            Item i = new WoodBasedBlockItem(value, new Item.Properties().tab(tab), w);
            POST_ITEMS.put(w, i);
            registry.register(i.setRegistryName(value.getRegistryName()));
        });
        STRIPPED_POSTS.forEach((w, value) -> {
            Item i = new WoodBasedBlockItem(value, new Item.Properties().tab(tab),w);
            STRIPPED_POST_ITEMS.put(w, i);
            registry.register(i.setRegistryName(value.getRegistryName()));
        });

        HEDGES.forEach((key, value) -> {
            Item i = new WoodBasedBlockItem(value, new Item.Properties().tab(tab), 200);
            HEDGE_ITEMS.put(key, i);
            registry.register(i.setRegistryName(value.getRegistryName()));

        });
    }

    //render layer
    @Override
    public void onClientSetup(FMLClientSetupEvent event) {
    }

    @Override
    public void registerColors(ColorHandlerEvent.Item event) {
        HEDGES.forEach((l, h) -> {
            ItemColors colors = event.getItemColors();
            ItemStack leafStack = new ItemStack(l.leaves);
            colors.register((stack, tintIndex) -> colors.getColor(leafStack, tintIndex), h.asItem());
        });
    }

    //tags & loot tables
    @Override
    public void addStaticServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
        var pack = handler.dynamicPack;
        List<ResourceLocation> posts = new ArrayList<>();
        List<ResourceLocation> hedges = new ArrayList<>();
        POSTS.forEach((wood, value) -> {
            pack.addSimpleBlockLootTable(value);
            posts.add(value.getRegistryName());
        });
        STRIPPED_POSTS.forEach((wood, value) -> {
            pack.addSimpleBlockLootTable(value);
            posts.add(value.getRegistryName());
        });
        HEDGES.forEach((wood, value) -> {
            pack.addSimpleBlockLootTable(value);
            hedges.add(value.getRegistryName());
        });
        pack.addTag(new ResourceLocation("supplementaries", "posts"), posts, Registry.BLOCK_REGISTRY);
        pack.addTag(modRes("hedges"), hedges, Registry.BLOCK_REGISTRY);
        pack.addTag(modRes("hedges"), hedges, Registry.ITEM_REGISTRY);
        posts.addAll(hedges);
        pack.addTag(new ResourceLocation("minecraft:mineable/axe"), posts, Registry.BLOCK_REGISTRY);
    }

    //models
    @Override
    public void addStaticClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        //posts
        this.addBlockResources(manager, handler, POSTS, "oak_post",
                ResType.ITEM_MODELS.getPath(modRes("oak_post")),
                ResType.BLOCKSTATES.getPath(modRes("oak_post"))
        );
        this.addBlockResources(manager, handler, POSTS,
                BlockTypeResourceTransform.wood(modId, manager)
                        .replaceWithTextureFromChild("minecraft:block/oak_log",
                                "log", s -> !s.contains("top"))
                        .idReplaceBlock("oak_post"),
                ResType.BLOCK_MODELS.getPath(modRes("oak_post"))
        );
        //stripped posts
        this.addBlockResources(manager, handler, STRIPPED_POSTS, "stripped_oak_post",
                ResType.ITEM_MODELS.getPath(modRes("stripped_oak_post")),
                ResType.BLOCKSTATES.getPath(modRes("stripped_oak_post"))
        );
        this.addBlockResources(manager, handler, STRIPPED_POSTS,
                BlockTypeResourceTransform.wood(modId, manager)
                        .replaceWithTextureFromChild("minecraft:block/stripped_oak_log",
                                "stripped_log", s -> !s.contains("top"))
                        .idReplaceBlock("stripped_oak_post"),
                ResType.BLOCK_MODELS.getPath(modRes("stripped_oak_post"))
        );
        //hedges
        this.addBlockResources(manager, handler, HEDGES, "oak_hedge",
                ResType.ITEM_MODELS.getPath(modRes("oak_hedge")),
                ResType.BLOCKSTATES.getPath(modRes("oak_hedge"))
        );
        this.addBlockResources(manager, handler, HEDGES,
                BlockTypeResourceTransform.leaves(modId, manager)
                        .replaceWithTextureFromChild("minecraft:block/oak_log",
                                l -> l.woodType.log, s -> !s.contains("top"))
                        .replaceWithTextureFromChild("minecraft:block/oak_leaves", "leaves")
                        .idReplaceBlock("oak_hedge"),
                ResType.BLOCK_MODELS.getPath(modRes("oak_hedge_side")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_hedge_post")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_hedge_extend"))
        );
    }

    //recipes
    @Override
    public void addDynamicServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
        this.addBlocksRecipesL(manager, handler, HEDGES, "building/crafting/oak_hedge");
        this.addBlocksRecipes(manager, handler, POSTS, "building/crafting/oak_post");
        this.addBlocksRecipes(manager, handler, STRIPPED_POSTS, "building/crafting/stripped_oak_post");
    }

    //translations
    @Override
    public void addTranslations(ClientDynamicResourcesHandler clientDynamicResourcesHandler, DynamicLanguageManager.LanguageAccessor lang) {
        POSTS.forEach((w, v) -> LangBuilder.addDynamicEntry(lang, "block.wood_good.post", w, v));
        STRIPPED_POSTS.forEach((w, v) -> LangBuilder.addDynamicEntry(lang, "block.wood_good.stripped_post", w, v));
        HEDGES.forEach((w, v) -> LangBuilder.addDynamicEntry(lang, "block.wood_good.hedge", w, v));
    }
}

