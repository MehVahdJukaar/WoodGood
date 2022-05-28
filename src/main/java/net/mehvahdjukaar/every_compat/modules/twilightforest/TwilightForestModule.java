package net.mehvahdjukaar.every_compat.modules.twilightforest;

import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.mehvahdjukaar.selene.client.asset_generators.LangBuilder;
import net.mehvahdjukaar.selene.resourcepack.BlockTypeResourceTransform;
import net.mehvahdjukaar.selene.resourcepack.DynamicLanguageManager;
import net.mehvahdjukaar.selene.resourcepack.ResType;
import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.modules.CompatModule;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;
import twilightforest.block.BanisterBlock;
import twilightforest.block.HollowLogClimbable;
import twilightforest.block.HollowLogHorizontal;
import twilightforest.block.HollowLogVertical;
import twilightforest.item.HollowLogItem;
import twilightforest.item.TFItems;

import java.util.*;

public class TwilightForestModule extends CompatModule {

    public TwilightForestModule(String modId) {
        super(modId);
    }

    @Override
    public String shortenedId() {
        return "tf";
    }

    public static final String BANISTER_NAME = "banister";
    public static final Map<WoodType, Block> BANISTERS = new HashMap<>();
    public static final Map<WoodType, Item> BANISTER_ITEMS = new HashMap<>();

    public static final String HOLLOW_LOG_NAME = "hollow_%s_log";
    public static final Map<WoodType, Block> HOLLOW_LOGS_VERTICAL = new HashMap<>();
    public static final Map<WoodType, Block> HOLLOW_LOGS_HORIZONTAL = new HashMap<>();
    public static final Map<WoodType, Block> HOLLOW_LOGS_CLIMBABLE = new HashMap<>();
    public static final Map<WoodType, Item> HOLLOW_LOG_ITEMS = new HashMap<>();

    @Override
    public void registerWoodBlocks(IForgeRegistry<Block> registry, Collection<WoodType> woodTypes) {
        //banisters
        addChildToOak(shortenedId() + "/" + BANISTER_NAME, "oak_banister");
        for (WoodType w : woodTypes) {
            String name = makeBlockId(w, BANISTER_NAME);
            if (w.isVanilla() || !shouldRegisterEntry(name, registry)) continue;

            Block block = new BanisterBlock(BlockBehaviour.Properties.copy(w.planks));
            BANISTERS.put(w, block);
            registry.register(block.setRegistryName(WoodGood.res(name)));
            w.addChild(shortenedId() + "/" + BANISTER_NAME, block);
        }
        //hollow logs
        addChildToOak(shortenedId() + "/hollow_log", "hollow_oak_log_vertical");
        for (WoodType w : woodTypes) {
            //TODO: improve
            String name = this.shortenedId() + "/" + w.getVariantId("hollow", true) + "_log";

            ResourceLocation verticalRes = WoodGood.res(name + "_vertical");

            if (w.isVanilla() || !shouldRegisterEntry(verticalRes.getPath(), registry)) continue;

            ResourceLocation horizontalRes = WoodGood.res(name + "_horizontal");
            ResourceLocation climbableRes = WoodGood.res(name + "_climbable");

            Block horizontal = new HollowLogHorizontal(BlockBehaviour.Properties.copy(w.planks));
            registry.register(horizontal.setRegistryName(horizontalRes));
            HOLLOW_LOGS_HORIZONTAL.put(w, horizontal);


            Block climbable = new HollowLogClimbable(BlockBehaviour.Properties.copy(w.planks),
                    RegistryObject.create(verticalRes, ForgeRegistries.BLOCKS));
            registry.register(climbable.setRegistryName(climbableRes));
            HOLLOW_LOGS_CLIMBABLE.put(w, climbable);

            Block vertical = new HollowLogVertical(BlockBehaviour.Properties.copy(w.planks),
                    RegistryObject.create(climbableRes, ForgeRegistries.BLOCKS));
            registry.register(vertical.setRegistryName(verticalRes));
            HOLLOW_LOGS_VERTICAL.put(w, vertical);
            w.addChild(this.shortenedId() + "/hollow_log", vertical);


        }
    }

    @Override
    public void registerItems(IForgeRegistry<Item> registry) {
        BANISTERS.forEach((key, value) -> {
            Item i = new BlockItem(value, new Item.Properties().tab(TFItems.creativeTab));
            BANISTER_ITEMS.put(key, i);
            registry.register(i.setRegistryName(value.getRegistryName()));
        });
        HOLLOW_LOGS_VERTICAL.forEach((w, b) -> {
            String itemName = b.getRegistryName().getPath().replace("_vertical", "");
            Item i = new HollowLogItem(
                    RegistryObject.create(WoodGood.res(itemName + "_horizontal"), ForgeRegistries.BLOCKS),
                    RegistryObject.create(b.getRegistryName(), ForgeRegistries.BLOCKS),
                    RegistryObject.create(WoodGood.res(itemName + "_climbable"), ForgeRegistries.BLOCKS),
                    new Item.Properties().tab(TFItems.creativeTab));
            HOLLOW_LOG_ITEMS.put(w, i);
            registry.register(i.setRegistryName(WoodGood.res(itemName)));
        });

    }

    @Override
    public void onClientSetup(FMLClientSetupEvent event) {
        HOLLOW_LOGS_CLIMBABLE.values().forEach(t -> ItemBlockRenderTypes.setRenderLayer(t, RenderType.cutout()));
        HOLLOW_LOGS_HORIZONTAL.values().forEach(t -> ItemBlockRenderTypes.setRenderLayer(t, RenderType.cutout()));
    }

    @Override
    public void registerColors(ColorHandlerEvent.Item event) {
        BlockColors colors = event.getBlockColors();
        colors.register(
                (s, l, pos, i) -> l != null && pos != null ?
                        BiomeColors.getAverageFoliageColor(l, pos) : FoliageColor.getDefaultColor(),
                HOLLOW_LOGS_CLIMBABLE.values().toArray(Block[]::new));
        colors.register(
                (s, l, pos, i) -> l != null && pos != null ?
                        BiomeColors.getAverageGrassColor(l, pos) : -1,
                HOLLOW_LOGS_HORIZONTAL.values().toArray(Block[]::new));
    }

    //loot table and tags
    @Override
    public void addStaticServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
        var pack = handler.dynamicPack;
        //banisters
        List<ResourceLocation> beams = new ArrayList<>();
        List<ResourceLocation> hollow_logs_vertical = new ArrayList<>();
        List<ResourceLocation> hollow_logs_horizontal = new ArrayList<>();
        List<ResourceLocation> hollow_logs_climbable = new ArrayList<>();
        BANISTERS.forEach((wood, value) -> {
            pack.addSimpleBlockLootTable(value);
            beams.add(value.getRegistryName());
        });
        HOLLOW_LOGS_CLIMBABLE.forEach((wood, value) -> {
            pack.addSimpleBlockLootTable(value);
            hollow_logs_climbable.add(value.getRegistryName());
        });
        HOLLOW_LOGS_VERTICAL.forEach((wood, value) -> {
            pack.addSimpleBlockLootTable(value);
            hollow_logs_vertical.add(value.getRegistryName());
        });
        HOLLOW_LOGS_HORIZONTAL.forEach((wood, value) -> {
            pack.addSimpleBlockLootTable(value);
            hollow_logs_horizontal.add(value.getRegistryName());
        });
        pack.addTag(modRes("banisters"), beams, Registry.BLOCK_REGISTRY);
        pack.addTag(modRes("banisters"), beams, Registry.ITEM_REGISTRY);
        pack.addTag(modRes("hollow_logs_vertical"), hollow_logs_vertical, Registry.BLOCK_REGISTRY);
        pack.addTag(modRes("hollow_logs_climbable"), hollow_logs_climbable, Registry.BLOCK_REGISTRY);
        pack.addTag(modRes("hollow_logs_horizontal"), hollow_logs_horizontal, Registry.BLOCK_REGISTRY);
        //pack.addTag(modRes("hollow_logs"), hollow_logs_vertical, Registry.ITEM_REGISTRY);
    }

    //recipes
    @Override
    public void addDynamicServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
        this.addBlocksRecipes(manager, handler, BANISTERS, "wood/oak_banister");
        this.addBlocksRecipes(manager, handler, HOLLOW_LOGS_VERTICAL, "stonecutting/oak_log/hollow_oak_log");
    }

    @Override
    public void addStaticClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        this.addBlockResources(manager, handler, BANISTERS,
                BlockTypeResourceTransform.wood(modId, manager)
                        .idReplaceBlock("oak_banister")
                        .replaceOakPlanks(),
                ResType.ITEM_MODELS.getPath(modRes("oak_banister")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_banister_connected")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_banister_connected_extended")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_banister_short")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_banister_short_extended")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_banister_tall")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_banister_tall_extended"))
        );
        this.addBlockResources(manager, handler, BANISTERS, "oak_banister",
                ResType.BLOCKSTATES.getPath(modRes("oak_banister"))
        );

    }

    @Override
    public void addDynamicClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        //hollow logs
        this.addBlockResources(manager, handler, HOLLOW_LOGS_VERTICAL,
                BlockTypeResourceTransform.wood(modId, manager)
                        .replaceBlockType("hollow_oak")
                        .idReplaceType("hollow_oak"),
                ResType.BLOCKSTATES.getPath(modRes("hollow_oak_log_horizontal")),
                ResType.BLOCKSTATES.getPath(modRes("hollow_oak_log_vertical")),
                ResType.BLOCKSTATES.getPath(modRes("hollow_oak_log_climbable")),
                ResType.ITEM_MODELS.getPath(modRes("hollow_oak_log"))
        );
        this.addBlockResources(manager, handler, HOLLOW_LOGS_VERTICAL,
                BlockTypeResourceTransform.wood(modId, manager)
                        .idReplaceType("hollow_oak")
                        .replaceOakBark()
                        .replaceWithTextureFromChild("minecraft:block/stripped_oak_log",
                                "stripped_log", s -> !s.contains("top")),
                ResType.BLOCK_MODELS.getPath(modRes("hollow_oak_log_climbable_ladder")),
                ResType.BLOCK_MODELS.getPath(modRes("hollow_oak_log_climbable_vine")),
                ResType.BLOCK_MODELS.getPath(modRes("hollow_oak_log_horizontal")),
                ResType.BLOCK_MODELS.getPath(modRes("hollow_oak_log_horizontal_moss")),
                ResType.BLOCK_MODELS.getPath(modRes("hollow_oak_log_horizontal_moss_grass")),
                ResType.BLOCK_MODELS.getPath(modRes("hollow_oak_log_horizontal_snow")),
                ResType.BLOCK_MODELS.getPath(modRes("hollow_oak_log_vertical"))
        );
    }

    //translations
    @Override
    public void addTranslations(ClientDynamicResourcesHandler clientDynamicResourcesHandler, DynamicLanguageManager.LanguageAccessor lang) {
        BANISTERS.forEach((w, v) -> LangBuilder.addDynamicEntry(lang, "block.wood_good.banister", w, v));
        HOLLOW_LOGS_VERTICAL.forEach((w, v) -> LangBuilder.addDynamicEntry(lang, "block.wood_good.hollow_log", w, v));
    }

}
