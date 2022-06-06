package net.mehvahdjukaar.every_compat.modules.quark;

import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.every_compat.configs.EarlyConfigs;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.misc.Utils;
import net.mehvahdjukaar.every_compat.modules.CompatModule;
import net.mehvahdjukaar.selene.block_set.leaves.LeavesType;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.mehvahdjukaar.selene.block_set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.selene.client.asset_generators.LangBuilder;
import net.mehvahdjukaar.selene.client.asset_generators.textures.Palette;
import net.mehvahdjukaar.selene.client.asset_generators.textures.Respriter;
import net.mehvahdjukaar.selene.client.asset_generators.textures.TextureImage;
import net.mehvahdjukaar.selene.items.WoodBasedBlockItem;
import net.mehvahdjukaar.selene.resourcepack.AfterLanguageLoadEvent;
import net.mehvahdjukaar.selene.resourcepack.BlockTypeResTransformer;
import net.mehvahdjukaar.selene.resourcepack.RPUtils;
import net.mehvahdjukaar.selene.resourcepack.ResType;
import net.mehvahdjukaar.selene.resourcepack.resources.TagBuilder;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.common.Tags;
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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Deprecated
public class LegacyQM extends CompatModule {

    public LegacyQM(String modId) {
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
    public static BlockEntityType<CompatChestBlockTile> COMPAT_CHEST_TILE = null;

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
        CreativeModeTab tab = WoodGood.MOD_TAB != null ? WoodGood.MOD_TAB :
                CreativeModeTab.TAB_DECORATIONS;

        POSTS.forEach((w, value) -> {
            var t = EarlyConfigs.isTypeEnabled(w) ? tab : null;
            Item i = new WoodBasedBlockItem(value, new Item.Properties().tab(t), w);
            POST_ITEMS.put(w, i);
            registry.register(i.setRegistryName(value.getRegistryName()));
        });
        STRIPPED_POSTS.forEach((w, value) -> {
            var t = EarlyConfigs.isTypeEnabled(w) ? tab : null;
            Item i = new WoodBasedBlockItem(value, new Item.Properties().tab(t), w);
            STRIPPED_POST_ITEMS.put(w, i);
            registry.register(i.setRegistryName(value.getRegistryName()));
        });

        BOOKSHELVES.forEach((w, value) -> {
            var t = EarlyConfigs.isTypeEnabled(w) ? tab : null;
            Item i = new WoodBasedBlockItem(value, new Item.Properties().tab(t), w);
            BOOKSHELF_ITEMS.put(w, i);
            registry.register(i.setRegistryName(value.getRegistryName()));
        });

        VERTICAL_PLANKS.forEach((w, value) -> {
            var t = EarlyConfigs.isTypeEnabled(w) ? tab : null;
            Item i = new WoodBasedBlockItem(value, new Item.Properties().tab(t), w);
            VERTICAL_PLANK_ITEMS.put(w, i);
            registry.register(i.setRegistryName(value.getRegistryName()));
        });

        HEDGES.forEach((leavesType, value) -> {
            var t = EarlyConfigs.isTypeEnabled(leavesType) ? tab : null;
            Item i = new WoodBasedBlockItem(value, new Item.Properties().tab(t), 200);
            HEDGE_ITEMS.put(leavesType, i);
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

        TagBuilder bookshelves = TagBuilder.of(Tags.Items.BOOKSHELVES).addEntries(BOOKSHELVES.values());
        pack.addTag(bookshelves, Registry.BLOCK_REGISTRY);
        pack.addTag(bookshelves, Registry.ITEM_REGISTRY);

        POSTS.forEach((wood, value) -> pack.addSimpleBlockLootTable(value));
        STRIPPED_POSTS.forEach((wood, value) -> pack.addSimpleBlockLootTable(value));
        TagBuilder posts = TagBuilder.of(modRes("posts")).addEntries(POSTS.values()).addEntries(STRIPPED_POSTS.values());
        pack.addTag(posts, Registry.BLOCK_REGISTRY);

        VERTICAL_PLANKS.forEach((wood, value) -> pack.addSimpleBlockLootTable(value));
        TagBuilder verticalPlanks = TagBuilder.of(BlockTags.PLANKS).addEntries(VERTICAL_PLANKS.values());
        pack.addTag(verticalPlanks, Registry.BLOCK_REGISTRY);

        HEDGES.forEach((wood, value) -> pack.addSimpleBlockLootTable(value));
        TagBuilder hedges = TagBuilder.of(modRes("hedges")).addEntries(HEDGES.values());
        pack.addTag(hedges, Registry.BLOCK_REGISTRY);
        pack.addTag(hedges, Registry.ITEM_REGISTRY);

        pack.addTag(TagBuilder.of(BlockTags.MINEABLE_WITH_AXE).addTag(posts).addTag(hedges).addTag(bookshelves), Registry.BLOCK_REGISTRY);
    }

    //models
    @Override
    public void addStaticClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        //posts
        Utils.addBlockResources(modId, manager, handler.dynamicPack, POSTS, "oak_post",
                ResType.ITEM_MODELS.getPath(modRes("oak_post")),
                ResType.BLOCKSTATES.getPath(modRes("oak_post"))
        );
        Utils.addBlockResources(modId, manager, handler.dynamicPack, POSTS,
                BlockTypeResTransformer.wood(modId, manager)
                        .replaceWithTextureFromChild("minecraft:block/oak_log",
                                "log", s -> !s.contains("top"))
                        .IDReplaceBlock("oak_post"),
                ResType.BLOCK_MODELS.getPath(modRes("oak_post"))
        );
        //stripped posts
        Utils.addBlockResources(modId, manager, handler.dynamicPack, STRIPPED_POSTS, "stripped_oak_post",
                ResType.ITEM_MODELS.getPath(modRes("stripped_oak_post")),
                ResType.BLOCKSTATES.getPath(modRes("stripped_oak_post"))
        );
        Utils.addBlockResources(modId, manager, handler.dynamicPack, STRIPPED_POSTS,
                BlockTypeResTransformer.wood(modId, manager)
                        .replaceWithTextureFromChild("minecraft:block/stripped_oak_log",
                                "stripped_log", s -> !s.contains("top"))
                        .IDReplaceBlock("stripped_oak_post"),
                ResType.BLOCK_MODELS.getPath(modRes("stripped_oak_post"))
        );
        //hedges
        Utils.addBlockResources(modId, manager, handler.dynamicPack, HEDGES, "oak_hedge",
                ResType.ITEM_MODELS.getPath(modRes("oak_hedge")),
                ResType.BLOCKSTATES.getPath(modRes("oak_hedge"))
        );
        Utils.addBlockResources(modId, manager, handler.dynamicPack, HEDGES,
                BlockTypeResTransformer.leaves(modId, manager)
                        .replaceWithTextureFromChild("minecraft:block/oak_log",
                                l -> l.woodType.log, s -> !s.contains("top"))
                        .replaceWithTextureFromChild("minecraft:block/oak_leaves", "leaves")
                        .IDReplaceBlock("oak_hedge"),
                ResType.BLOCK_MODELS.getPath(modRes("oak_hedge_side")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_hedge_post")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_hedge_extend"))
        );

        //bookshelves
        Utils.addBlockResources(modId, manager, handler.dynamicPack, BOOKSHELVES, "acacia_bookshelf",
                ResType.ITEM_MODELS.getPath(modRes("acacia_bookshelf")),
                ResType.BLOCKSTATES.getPath(modRes("acacia_bookshelf"))
        );
        Utils.addBlockResources(modId, manager, handler.dynamicPack, BOOKSHELVES,
                BlockTypeResTransformer.wood(modId, manager)
                        .replaceSimpleBlock(modId, "acacia_bookshelf")
                        .replaceWithTextureFromChild("minecraft:block/acacia_planks", "planks")
                        .IDReplaceBlock("acacia_bookshelf"),
                ResType.BLOCK_MODELS.getPath(modRes("acacia_bookshelf"))
        );

        //vertical planks
        Utils.addBlockResources(modId, manager, handler.dynamicPack, VERTICAL_PLANKS,
                BlockTypeResTransformer.wood(modId, manager)
                        .replaceSimpleBlock(modId, "vertical_oak_planks")
                        .replaceWithTextureFromChild("minecraft:block/oak_planks", "planks")
                        .IDReplaceBlock("vertical_oak_planks"),
                ResType.ITEM_MODELS.getPath(modRes("vertical_oak_planks")),
                ResType.BLOCKSTATES.getPath(modRes("vertical_oak_planks")),
                ResType.BLOCK_MODELS.getPath(modRes("vertical_oak_planks"))
        );
    }

    //recipes
    @Override
    public void addDynamicServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
        Utils.addLeavesRecipes(modId, manager, handler.dynamicPack, HEDGE_ITEMS, "building/crafting/oak_hedge");
        Utils.addBlocksRecipes(modId, manager, handler.dynamicPack, BOOKSHELF_ITEMS, "building/crafting/acacia_bookshelf",
                WoodTypeRegistry.WOOD_TYPES.get(new ResourceLocation("acacia")));
        Utils.addWoodRecipes(modId, manager, handler.dynamicPack, POST_ITEMS, "building/crafting/oak_post");
        Utils.addWoodRecipes(modId, manager, handler.dynamicPack, STRIPPED_POST_ITEMS, "building/crafting/stripped_oak_post");
        Utils.addWoodRecipes(modId, manager, handler.dynamicPack, VERTICAL_PLANK_ITEMS, "building/crafting/vertplanks/vertical_oak_planks");

        Utils.addBlockResources(modId, manager, handler.dynamicPack, BOOKSHELVES, "acacia_bookshelf",
                ResType.BLOCK_LOOT_TABLES.getPath(modRes("acacia_bookshelf"))
        );
    }

    //translations
    @Override
    public void addTranslations(ClientDynamicResourcesHandler clientDynamicResourcesHandler, AfterLanguageLoadEvent lang) {
        POSTS.forEach((w, v) -> LangBuilder.addDynamicEntry(lang, "block.everycomp.post", w, v));
        STRIPPED_POSTS.forEach((w, v) -> LangBuilder.addDynamicEntry(lang, "block.everycomp.stripped_post", w, v));
        HEDGES.forEach((w, v) -> LangBuilder.addDynamicEntry(lang, "block.everycomp.hedge", w, v));
        BOOKSHELVES.forEach((w, v) -> LangBuilder.addDynamicEntry(lang, "block.everycomp.bookshelf", w, v));
        VERTICAL_PLANKS.forEach((w, v) -> LangBuilder.addDynamicEntry(lang, "block.everycomp.vertical_planks", w, v));
    }

    @Override
    public void addDynamicClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        try (TextureImage main = TextureImage.open(manager, WoodGood.res("block/acacia_bookshelf"));
             TextureImage mask = TextureImage.open(manager, WoodGood.res("block/acacia_bookshelf_m"))) {
            Respriter respriter = Respriter.masked(main, mask);

            BOOKSHELVES.forEach((wood, table) -> {

                String id = table.getRegistryName().getPath();

                try (TextureImage plankTexture = TextureImage.open(manager,
                        RPUtils.findFirstBlockTextureLocation(manager, wood.planks))) {

                    List<Palette> palette = Palette.fromAnimatedImage(plankTexture);
                    for (var p : palette) {
                        var l0 = p.getDarkest();
                        p.increaseDown();
                        p.increaseDown();
                        p.increaseDown();
                        p.increaseDown();
                        p.remove(l0);
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

