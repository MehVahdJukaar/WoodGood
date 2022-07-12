package net.mehvahdjukaar.every_compat.modules.twilightforest;

import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.mehvahdjukaar.selene.block_set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.selene.resourcepack.AfterLanguageLoadEvent;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;
import twilightforest.block.*;
import twilightforest.item.HollowLogItem;
import twilightforest.item.TFItems;

import java.util.function.Supplier;

public class TwilightForestModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, BanisterBlock> BANISTERS;
    public final SimpleEntrySet<WoodType, HollowLogVertical> HOLLOW_LOGS_VERTICAL;
    public final SimpleEntrySet<WoodType, HollowLogHorizontal> HOLLOW_LOGS_HORIZONTAL;
    public final SimpleEntrySet<WoodType, HollowLogClimbable> HOLLOW_LOGS_CLIMBABLE;

    public TwilightForestModule(String modId) {
        super(modId, "tf");

        BANISTERS = SimpleEntrySet.builder(WoodType.class,"banister",
                        TFBlocks.OAK_BANISTER, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new BanisterBlock(WoodGood.copySafe(w.planks)))
                .addTag(modRes("banisters"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("banisters"), Registry.ITEM_REGISTRY)
                .addRecipe(modRes("wood/oak_banister"))
                .useLootFromBase()
                .setTab(()->TFItems.creativeTab)
                .build();

        this.addEntry(BANISTERS);


        HOLLOW_LOGS_HORIZONTAL = SimpleEntrySet.builder(WoodType.class,"log_horizontal", "hollow",
                        TFBlocks.HOLLOW_ACACIA_LOG_HORIZONTAL, () -> WoodTypeRegistry.WOOD_TYPES.get(new ResourceLocation("acacia")),
                        w -> regIfPossible(w, () -> new HollowLogHorizontal(WoodGood.copySafe(w.planks))))
                .addTag(modRes("hollow_logs_horizontal"), Registry.BLOCK_REGISTRY)
                .noItem()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(HOLLOW_LOGS_HORIZONTAL);


        HOLLOW_LOGS_VERTICAL = SimpleEntrySet.builder(WoodType.class,"log_vertical", "hollow",
                        TFBlocks.HOLLOW_ACACIA_LOG_VERTICAL, () -> WoodTypeRegistry.WOOD_TYPES.get(new ResourceLocation("acacia")),
                        w -> {
                            var id = WoodGood.res(this.shortenedId() + "/" + w.getVariantId("hollow", true) + "_log_climbable");
                            return regIfPossible(w, () -> new HollowLogVertical(WoodGood.copySafe(w.planks), RegistryObject.create(id, ForgeRegistries.BLOCKS)));
                        })
                .addTag(modRes("hollow_logs_vertical"), Registry.BLOCK_REGISTRY)
                .noItem()
                .addRecipe(modRes("stonecutting/acacia_log/hollow_acacia_log"))
                .build();

        this.addEntry(HOLLOW_LOGS_VERTICAL);

        HOLLOW_LOGS_CLIMBABLE = SimpleEntrySet.builder(WoodType.class,"log_climbable", "hollow",
                        TFBlocks.HOLLOW_ACACIA_LOG_CLIMBABLE, () -> WoodTypeRegistry.WOOD_TYPES.get(new ResourceLocation("acacia")),
                        w -> regIfPossible(w, () -> new HollowLogClimbable(WoodGood.copySafe(w.planks),
                                RegistryObject.create(HOLLOW_LOGS_VERTICAL.blocks.get(w).getRegistryName(), ForgeRegistries.BLOCKS))))
                .addTag(modRes("hollow_logs_climbable"), Registry.BLOCK_REGISTRY)
                .noItem()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(HOLLOW_LOGS_CLIMBABLE);


    }

    @Override
    public void registerItems(IForgeRegistry<Item> registry) {
        super.registerItems(registry);
        HOLLOW_LOGS_VERTICAL.blocks.forEach((w, b) -> {
            String itemName = b.getRegistryName().getPath().replace("_vertical", "");
            Item i = new HollowLogItem(
                    RegistryObject.create(WoodGood.res(itemName + "_horizontal"), ForgeRegistries.BLOCKS),
                    RegistryObject.create(b.getRegistryName(), ForgeRegistries.BLOCKS),
                    RegistryObject.create(WoodGood.res(itemName + "_climbable"), ForgeRegistries.BLOCKS),
                    new Item.Properties().tab(TFItems.creativeTab));
            HOLLOW_LOGS_VERTICAL.items.put(w, i);
            registry.register(i.setRegistryName(WoodGood.res(itemName)));
        });
    }

    @Override
    public void registerColors(ColorHandlerEvent.Item event) {
        BlockColors colors = event.getBlockColors();
        colors.register(
                (s, l, pos, i) -> l != null && pos != null ?
                        BiomeColors.getAverageFoliageColor(l, pos) : FoliageColor.getDefaultColor(),
                HOLLOW_LOGS_CLIMBABLE.blocks.values().toArray(Block[]::new));
        colors.register(
                (s, l, pos, i) -> l != null && pos != null ?
                        BiomeColors.getAverageGrassColor(l, pos) : -1,
                HOLLOW_LOGS_HORIZONTAL.blocks.values().toArray(Block[]::new));
    }

    @Override
    public void addTranslations(ClientDynamicResourcesHandler clientDynamicResourcesHandler, AfterLanguageLoadEvent lang) {
        super.addTranslations(clientDynamicResourcesHandler, lang);
       // HOLLOW_LOGS_VERTICAL.items.forEach((w, v) -> LangBuilder.addDynamicEntry(lang, "block_type.twilightforest.hollow_log", (BlockType) w, v));
    }

    @Override
    public void addDynamicClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicClientResources(handler, manager);
    }

    @Nullable
    private <B extends Block> B regIfPossible(WoodType woodType, Supplier<B> supplier) {
        if (woodType.getChild("stripped_log") != null) {
            return supplier.get();
        }
        return null;
    }
}
