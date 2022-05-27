package net.mehvahdjukaar.wood_good.modules.twilightforest;

import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.mehvahdjukaar.selene.client.asset_generators.LangBuilder;
import net.mehvahdjukaar.selene.resourcepack.DynamicLanguageManager;
import net.mehvahdjukaar.selene.resourcepack.ResType;
import net.mehvahdjukaar.wood_good.WoodGood;
import net.mehvahdjukaar.wood_good.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.wood_good.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.wood_good.modules.CompatModule;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;
import net.moddingplayground.twigs.Twigs;
import twilightforest.TwilightForestMod;
import twilightforest.block.*;
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
        addChildToOak(shortenedId() + "/hollow_log", "hollow_oak_log");
        for (WoodType w : woodTypes) {
            String name = makeBlockId(w, "hollow")+"_log";

            ResourceLocation verticalRes = WoodGood.res(name+"_vertical");

            if (w.isVanilla() ||  !shouldRegisterEntry(verticalRes.getPath(), registry)) continue;

            ResourceLocation horizontalRes = WoodGood.res(name+"_horizontal");
            ResourceLocation climbableRes = WoodGood.res(name+"_climbable");

            Block horizontal = new HollowLogHorizontal(BlockBehaviour.Properties.copy(w.log));
            registry.register(horizontal.setRegistryName(horizontalRes));
            HOLLOW_LOGS_HORIZONTAL.put(w, horizontal);


            Block climbable = new HollowLogClimbable(BlockBehaviour.Properties.copy(w.log),
                    RegistryObject.create(verticalRes, ForgeRegistries.BLOCKS));
            registry.register(climbable.setRegistryName(climbableRes));
            HOLLOW_LOGS_CLIMBABLE.put(w, climbable);

            Block vertical = new HollowLogVertical(BlockBehaviour.Properties.copy(w.log),
                    RegistryObject.create(climbableRes, ForgeRegistries.BLOCKS));
            registry.register(vertical.setRegistryName(climbableRes));
            HOLLOW_LOGS_VERTICAL.put(w, vertical);

            w.addChild(this.shortenedId()+"/hollow_log", vertical);
        }
    }

    @Override
    public void registerItems(IForgeRegistry<Item> registry) {
        BANISTERS.forEach((key, value) -> {
            Item i = new BlockItem(value, new Item.Properties().tab(TFItems.creativeTab));
            BANISTER_ITEMS.put(key, i);
            registry.register(i.setRegistryName(value.getRegistryName()));
        });
        HOLLOW_LOGS_VERTICAL.forEach((w,b)->{
            String ss = b.getRegistryName().getPath().replace("vertical","");
            Item i = new HollowLogItem(
                    RegistryObject.create(WoodGood.res(ss+"_horizontal"), ForgeRegistries.BLOCKS),
                    RegistryObject.create(b.getRegistryName(), ForgeRegistries.BLOCKS),
                    RegistryObject.create(WoodGood.res(ss+"_climbable"), ForgeRegistries.BLOCKS),
                    new Item.Properties().tab(TFItems.creativeTab));
            HOLLOW_LOG_ITEMS.put(w, i);
            registry.register(i.setRegistryName(b.getRegistryName()));
        });
    }

    //loot table and tags
    @Override
    public void addStaticServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
        var pack = handler.dynamicPack;
        //banisters
        List<ResourceLocation> beams = new ArrayList<>();
        BANISTERS.forEach((wood, value) -> {
            pack.addSimpleBlockLootTable(value);
            beams.add(value.getRegistryName());
        });
        pack.addTag(modRes("banisters"), beams, Registry.BLOCK_REGISTRY);
        pack.addTag(modRes("banisters"), beams, Registry.ITEM_REGISTRY);
    }

    //recipes
    @Override
    public void addDynamicServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
        this.addBlocksRecipes(manager, handler, BANISTERS, "wood/oak_banister");
    }

    @Override
    public void addStaticClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        this.addBlockResources(manager, handler, BANISTERS,
                WoodJsonTransformation.create(WoodGood.MOD_ID, manager)
                        .replaceBlockInPath("oak_banister")
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

    //translations
    @Override
    public void addTranslations(ClientDynamicResourcesHandler clientDynamicResourcesHandler, DynamicLanguageManager.LanguageAccessor lang) {
        BANISTERS.forEach((w, v) -> LangBuilder.addDynamicEntry(lang, "block.wood_good.banister", w, v));
    }

}
