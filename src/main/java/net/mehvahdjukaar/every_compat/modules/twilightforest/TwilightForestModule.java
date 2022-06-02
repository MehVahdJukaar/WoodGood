package net.mehvahdjukaar.every_compat.modules.twilightforest;

import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.misc.Utils;
import net.mehvahdjukaar.every_compat.modules.CompatModule;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.mehvahdjukaar.selene.client.asset_generators.LangBuilder;
import net.mehvahdjukaar.selene.items.WoodBasedBlockItem;
import net.mehvahdjukaar.selene.resourcepack.AfterLanguageLoadEvent;
import net.mehvahdjukaar.selene.resourcepack.BlockTypeResTransformer;
import net.mehvahdjukaar.selene.resourcepack.DynamicLanguageManager;
import net.mehvahdjukaar.selene.resourcepack.ResType;
import net.mehvahdjukaar.selene.resourcepack.resources.TagBuilder;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
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
            if (w.isVanilla() || isEntryAlreadyRegistered(name, registry)) continue;

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

            if (w.getChild("stripped_log") == null) continue;
            if (w.isVanilla() || isEntryAlreadyRegistered(verticalRes.getPath(), registry)) continue;


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
        BANISTERS.forEach((w, value) -> {
            Item i = new WoodBasedBlockItem(value, new Item.Properties().tab(TFItems.creativeTab), w);
            BANISTER_ITEMS.put(w, i);
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
    public void onClientSetup() {
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
        BANISTERS.forEach((wood, value) -> pack.addSimpleBlockLootTable(value));
        TagBuilder banisters = TagBuilder.of(modRes("banisters")).addEntries(BANISTERS.values());
        pack.addTag(banisters, Registry.BLOCK_REGISTRY);
        pack.addTag(banisters, Registry.ITEM_REGISTRY);

        HOLLOW_LOGS_CLIMBABLE.forEach((wood, value) -> pack.addSimpleBlockLootTable(value));
        TagBuilder hollow_logs_climbable = TagBuilder.of(modRes("hollow_logs_climbable")).addEntries(HOLLOW_LOGS_CLIMBABLE.values());
        pack.addTag(hollow_logs_climbable, Registry.BLOCK_REGISTRY);

        HOLLOW_LOGS_VERTICAL.forEach((wood, value) -> pack.addSimpleBlockLootTable(value));
        TagBuilder hollow_logs_vertical = TagBuilder.of(modRes("hollow_logs_vertical")).addEntries(HOLLOW_LOGS_VERTICAL.values());
        pack.addTag(hollow_logs_vertical, Registry.BLOCK_REGISTRY);

        HOLLOW_LOGS_HORIZONTAL.forEach((wood, value) -> pack.addSimpleBlockLootTable(value));
        TagBuilder hollow_logs_horizontal = TagBuilder.of(modRes("hollow_logs_horizontal")).addEntries(HOLLOW_LOGS_HORIZONTAL.values());
        pack.addTag(hollow_logs_horizontal, Registry.BLOCK_REGISTRY);
    }

    //recipes
    @Override
    public void addDynamicServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
        Utils.addWoodRecipes(modId, manager, handler.dynamicPack, BANISTERS, "wood/oak_banister");
        Utils.addWoodRecipes(modId, manager, handler.dynamicPack, HOLLOW_LOGS_VERTICAL, "stonecutting/oak_log/hollow_oak_log");
    }

    @Override
    public void addStaticClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        Utils.addBlockResources(modId, manager, handler.dynamicPack, BANISTERS,
                BlockTypeResTransformer.wood(modId, manager)
                        .IDReplaceBlock("oak_banister")
                        .replaceOakPlanks(),
                ResType.ITEM_MODELS.getPath(modRes("oak_banister")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_banister_connected")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_banister_connected_extended")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_banister_short")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_banister_short_extended")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_banister_tall")),
                ResType.BLOCK_MODELS.getPath(modRes("oak_banister_tall_extended"))
        );
        Utils.addBlockResources(modId, manager, handler.dynamicPack, BANISTERS, "oak_banister",
                ResType.BLOCKSTATES.getPath(modRes("oak_banister"))
        );

    }

    @Override
    public void addDynamicClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        //hollow logs
        //Utils.addStandardResources(modId, manager, handler.dynamicPack);
        Utils.addBlockResources(modId, manager, handler.dynamicPack, HOLLOW_LOGS_VERTICAL,
                BlockTypeResTransformer.wood(modId, manager)
                        .replaceBlockType("oak")
                        .IDReplaceType("oak"),
                ResType.BLOCKSTATES.getPath(modRes("hollow_oak_log_horizontal")),
                ResType.BLOCKSTATES.getPath(modRes("hollow_oak_log_vertical")),
                ResType.BLOCKSTATES.getPath(modRes("hollow_oak_log_climbable")),
                ResType.ITEM_MODELS.getPath(modRes("hollow_oak_log"))
        );
        Utils.addBlockResources(modId, manager, handler.dynamicPack, HOLLOW_LOGS_VERTICAL,
                BlockTypeResTransformer.wood(modId, manager)
                        .IDReplaceType("oak")
                        .replaceOakBark()
                        .replaceOakStripped(),
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
    public void addTranslations(ClientDynamicResourcesHandler clientDynamicResourcesHandler, AfterLanguageLoadEvent lang) {
        BANISTERS.forEach((w, v) -> LangBuilder.addDynamicEntry(lang, "block.wood_good.banister", w, v));
        HOLLOW_LOGS_VERTICAL.forEach((w, v) -> LangBuilder.addDynamicEntry(lang, "block.wood_good.hollow_log", w, v));
    }

}
