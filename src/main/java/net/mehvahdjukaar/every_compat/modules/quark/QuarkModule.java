package net.mehvahdjukaar.every_compat.modules.quark;

import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.modules.CompatModule;
import net.mehvahdjukaar.selene.block_set.leaves.LeavesType;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.mehvahdjukaar.selene.block_set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.selene.client.asset_generators.LangBuilder;
import net.mehvahdjukaar.selene.client.asset_generators.textures.Palette;
import net.mehvahdjukaar.selene.client.asset_generators.textures.Respriter;
import net.mehvahdjukaar.selene.client.asset_generators.textures.TextureImage;
import net.mehvahdjukaar.selene.items.WoodBasedBlockItem;
import net.mehvahdjukaar.selene.resourcepack.BlockTypeResourceTransform;
import net.mehvahdjukaar.selene.resourcepack.DynamicLanguageManager;
import net.mehvahdjukaar.selene.resourcepack.RPUtils;
import net.mehvahdjukaar.selene.resourcepack.ResType;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;
import net.minecraftforge.registries.IForgeRegistry;
import vazkii.arl.util.RegistryHelper;
import vazkii.quark.base.block.QuarkBlock;
import vazkii.quark.base.handler.ToolInteractionHandler;
import vazkii.quark.base.module.ModuleLoader;
import vazkii.quark.content.building.block.HedgeBlock;
import vazkii.quark.content.building.block.VariantBookshelfBlock;
import vazkii.quark.content.building.block.WoodPostBlock;
import vazkii.quark.content.building.module.HedgesModule;
import vazkii.quark.content.building.module.VariantBookshelvesModule;
import vazkii.quark.content.building.module.VerticalPlanksModule;
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

    public static final String STRIPPED_POST_NAME = "stripped_post";
    public static final Map<WoodType, Block> STRIPPED_POSTS = new HashMap<>();
    public static final Map<WoodType, Item> STRIPPED_POST_ITEMS = new HashMap<>();

    public static final String BOOKSHELF_NAME = "bookshelf";
    public static final Map<WoodType, Block> BOOKSHELVES = new HashMap<>();
    public static final Map<WoodType, Item> BOOKSHELF_ITEMS = new HashMap<>();

    public static final String VERTICAL_PLANK_NAME = "vertical_planks";
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
                String strippedName = makeBlockId(w, STRIPPED_POST_NAME);
                if (w.isVanilla() || isEntryAlreadyRegistered(name, registry)) continue;

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

        WoodType.OAK_WOOD_TYPE.addChild(shortenedId() + "/bookshelf", Blocks.BOOKSHELF);
        WoodTypeRegistry.WOOD_TYPES.get(new ResourceLocation("acacia")).addChild(shortenedId() + "/bookshelf",
                ForgeRegistries.BLOCKS.getValue(modRes("acacia_bookshelf")));
        for (WoodType w : woodTypes) {

            boolean nether = !w.canBurn();
            String name = w.getVariantId(BOOKSHELF_NAME, false);

            if (w.isVanilla() || isEntryAlreadyRegistered(name, registry)) continue;

            var module = ModuleLoader.INSTANCE.getModuleInstance(VariantBookshelvesModule.class);
            String prefix = shortenedId() + "/" + w.getAppendableId();

            Block bookshelf = new VariantBookshelfBlock(prefix, module, nether);
            BOOKSHELVES.put(w, bookshelf);
            registry.register(bookshelf);
            w.addChild(shortenedId() + "/bookshelf", bookshelf);
        }

        addChildToOak(shortenedId() + "/vertical_planks", "vertical_oak_planks");
        for (WoodType w : woodTypes) {

            String name = shortenedId() + "/" + w.getVariantId("planks", "vertical");
            if (w.isVanilla() || isEntryAlreadyRegistered(name, registry)) continue;
            var module = ModuleLoader.INSTANCE.getModuleInstance(VerticalPlanksModule.class);

            Block block = new QuarkBlock(name, module, CreativeModeTab.TAB_BUILDING_BLOCKS, BlockBehaviour.Properties.copy(w.planks));
            VERTICAL_PLANKS.put(w, block);
            registry.register(block);
            w.addChild(shortenedId() + "/vertical_planks", block);
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
            if (l.isVanilla() || isEntryAlreadyRegistered(name, registry)) continue;
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
            Item i = new WoodBasedBlockItem(value, new Item.Properties().tab(tab), w);
            STRIPPED_POST_ITEMS.put(w, i);
            registry.register(i.setRegistryName(value.getRegistryName()));
        });

        BOOKSHELVES.forEach((w, value) -> {
            Item i = new WoodBasedBlockItem(value, new Item.Properties().tab(tab), w);
            BOOKSHELF_ITEMS.put(w, i);
            registry.register(i.setRegistryName(value.getRegistryName()));
        });

        VERTICAL_PLANKS.forEach((w, value) -> {
            Item i = new WoodBasedBlockItem(value, new Item.Properties().tab(tab), w);
            VERTICAL_PLANK_ITEMS.put(w, i);
            registry.register(i.setRegistryName(value.getRegistryName()));
        });

        HEDGES.forEach((key, value) -> {
            Item i = new WoodBasedBlockItem(value, new Item.Properties().tab(tab), 200);
            HEDGE_ITEMS.put(key, i);
            registry.register(i.setRegistryName(value.getRegistryName()));
        });
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
        List<ResourceLocation> bookshelves = new ArrayList<>();
        List<ResourceLocation> verticalPlanks = new ArrayList<>();
        POSTS.forEach((wood, value) -> {
            pack.addSimpleBlockLootTable(value);
            posts.add(value.getRegistryName());
        });
        STRIPPED_POSTS.forEach((wood, value) -> {
            pack.addSimpleBlockLootTable(value);
            posts.add(value.getRegistryName());
        });
        BOOKSHELVES.forEach((wood, value) -> {
            //pack.addSimpleBlockLootTable(value);
            bookshelves.add(value.getRegistryName());
        });
        VERTICAL_PLANKS.forEach((wood, value) -> {
            pack.addSimpleBlockLootTable(value);
            verticalPlanks.add(value.getRegistryName());
        });
        HEDGES.forEach((wood, value) -> {
            pack.addSimpleBlockLootTable(value);
            hedges.add(value.getRegistryName());
        });
        pack.addTag(new ResourceLocation("forge","bookshelves"), bookshelves, Registry.BLOCK_REGISTRY);
        pack.addTag(new ResourceLocation("forge","bookshelves"), bookshelves, Registry.ITEM_REGISTRY);
        pack.addTag(new ResourceLocation("planks"), verticalPlanks, Registry.BLOCK_REGISTRY);
        pack.addTag(new ResourceLocation("supplementaries", "posts"), posts, Registry.BLOCK_REGISTRY);
        pack.addTag(modRes("hedges"), hedges, Registry.BLOCK_REGISTRY);
        pack.addTag(modRes("hedges"), hedges, Registry.ITEM_REGISTRY);
        posts.addAll(hedges);
        posts.addAll(bookshelves);
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

        //bookshelves
        this.addBlockResources(manager, handler, BOOKSHELVES, "acacia_bookshelf",
                ResType.ITEM_MODELS.getPath(modRes("acacia_bookshelf")),
                ResType.BLOCKSTATES.getPath(modRes("acacia_bookshelf"))
        );
        this.addBlockResources(manager, handler, BOOKSHELVES,
                BlockTypeResourceTransform.wood(modId, manager)
                        .replaceSimpleBlock(modId, "acacia_bookshelf")
                        .replaceWithTextureFromChild("block/acacia_planks", "planks")
                        .idReplaceBlock("acacia_bookshelf"),
                ResType.BLOCK_MODELS.getPath(modRes("acacia_bookshelf"))
        );

        //vertical planks
        this.addBlockResources(manager, handler, VERTICAL_PLANKS,
                BlockTypeResourceTransform.wood(modId, manager)
                        .replaceSimpleBlock(modId, "vertical_oak_planks")
                        .replaceWithTextureFromChild("block/oak_planks", "planks")
                        .idReplaceBlock("vertical_oak_planks"),
                ResType.ITEM_MODELS.getPath(modRes("vertical_oak_planks")),
                ResType.BLOCKSTATES.getPath(modRes("vertical_oak_planks")),
                ResType.BLOCK_MODELS.getPath(modRes("vertical_oak_planks"))
        );
    }

    //recipes
    @Override
    public void addDynamicServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
        this.addBlocksRecipesL(manager, handler, HEDGES, "building/crafting/oak_hedge");
        this.addBlocksRecipes(manager, handler, BOOKSHELVES, "building/crafting/acacia_bookshelf",
                WoodTypeRegistry.WOOD_TYPES.get(new ResourceLocation("acacia")));
        this.addBlocksRecipes(manager, handler, POSTS, "building/crafting/oak_post");
        this.addBlocksRecipes(manager, handler, STRIPPED_POSTS, "building/crafting/stripped_oak_post");
        this.addBlocksRecipes(manager, handler, VERTICAL_PLANKS, "building/crafting/vertplanks/vertical_oak_planks");

        this.addBlockResources(manager, handler, BOOKSHELVES, "acacia_bookshelf",
                ResType.BLOCK_LOOT_TABLES.getPath(modRes("acacia_bookshelf"))
        );
    }

    //translations
    @Override
    public void addTranslations(ClientDynamicResourcesHandler clientDynamicResourcesHandler, DynamicLanguageManager.LanguageAccessor lang) {
        POSTS.forEach((w, v) -> LangBuilder.addDynamicEntry(lang, "block.wood_good.post", w, v));
        STRIPPED_POSTS.forEach((w, v) -> LangBuilder.addDynamicEntry(lang, "block.wood_good.stripped_post", w, v));
        HEDGES.forEach((w, v) -> LangBuilder.addDynamicEntry(lang, "block.wood_good.hedge", w, v));
        BOOKSHELVES.forEach((w, v) -> LangBuilder.addDynamicEntry(lang, "block.wood_good.bookshelf", w, v));
        VERTICAL_PLANKS.forEach((w, v) -> LangBuilder.addDynamicEntry(lang, "block.wood_good.vertical_planks", w, v));
    }

    @Override
    public void addDynamicClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        try (TextureImage main = TextureImage.open(manager, new ResourceLocation("block/bookshelf"));
             TextureImage mask = TextureImage.open(manager, WoodGood.res("block/bookshelf_mask"))) {
            Respriter respriter = Respriter.masked(main, mask);

            BOOKSHELVES.forEach((wood, table) -> {

                String id = table.getRegistryName().getPath();

                try (TextureImage plankTexture = TextureImage.open(manager,
                        RPUtils.findFirstBlockTextureLocation(manager, wood.planks))) {

                    List<Palette> palette = Palette.fromAnimatedImage(plankTexture);
                    for (var p : palette) {
                        p.increaseDown();
                        p.increaseDown();
                        p.increaseDown();
                    }

                    addWoodTexture(wood, handler, manager, "block/" + id, () ->
                            respriter.recolorWithAnimation(palette, plankTexture.getMetadata()));

                } catch (Exception ex) {
                    handler.getLogger().error("Failed to generate Bookshelf block texture for for {} : {}", table, ex);
                }
            });
        } catch (Exception ex) {
            handler.getLogger().error("Could not generate any Bookshelf block texture : ", ex);
        }
    }
}

