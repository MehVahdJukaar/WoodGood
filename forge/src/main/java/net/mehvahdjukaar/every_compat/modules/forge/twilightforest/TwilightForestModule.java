package net.mehvahdjukaar.every_compat.modules.forge.twilightforest;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.configs.WoodConfigs;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.misc.Registrator;
import net.mehvahdjukaar.moonlight.api.platform.ClientPlatformHelper;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;
import twilightforest.block.BanisterBlock;
import twilightforest.block.HollowLogClimbable;
import twilightforest.block.HollowLogHorizontal;
import twilightforest.block.HollowLogVertical;
import twilightforest.init.TFBlocks;
import twilightforest.init.TFItems;
import twilightforest.item.HollowLogItem;

import java.util.function.Supplier;

public class TwilightForestModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, BanisterBlock> banisters;
    public final SimpleEntrySet<WoodType, HollowLogVertical> hollowLogsVertical;
    public final SimpleEntrySet<WoodType, HollowLogHorizontal> hollowLogsHorizontal;
    public final SimpleEntrySet<WoodType, HollowLogClimbable> hollowLogsClimbable;

    public TwilightForestModule(String modId) {
        super(modId, "tf");

        //TODO: check face culling
        banisters = SimpleEntrySet.builder(WoodType.class, "banister",
                        TFBlocks.OAK_BANISTER, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BanisterBlock(Utils.copyPropertySafe(w.planks).noOcclusion()))
                .addTag(modRes("banisters"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("banisters"), Registry.ITEM_REGISTRY)
                .addRecipe(modRes("wood/oak_banister"))
                .useLootFromBase()
                .setTab(() -> TFItems.creativeTab)
                .build();

        this.addEntry(banisters);


        hollowLogsHorizontal = SimpleEntrySet.builder(WoodType.class, "log_horizontal", "hollow",
                        TFBlocks.HOLLOW_ACACIA_LOG_HORIZONTAL, () -> WoodTypeRegistry.getValue(new ResourceLocation("acacia")),
                        w -> regIfPossible(w, () -> new HollowLogHorizontal(Utils.copyPropertySafe(w.log))))
                .addTag(modRes("hollow_logs_horizontal"), Registry.BLOCK_REGISTRY)
                .noItem()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(hollowLogsHorizontal);


        hollowLogsVertical = SimpleEntrySet.builder(WoodType.class, "log_vertical", "hollow",
                        TFBlocks.HOLLOW_ACACIA_LOG_VERTICAL, () -> WoodTypeRegistry.getValue(new ResourceLocation("acacia")),
                        w -> {
                            var id = EveryCompat.res(this.shortenedId() + "/" + w.getVariantId("hollow", true) + "_log_climbable");
                            return regIfPossible(w, () -> new HollowLogVertical(Utils.copyPropertySafe(w.log), RegistryObject.create(id, ForgeRegistries.BLOCKS)));
                        })
                .addTag(modRes("hollow_logs_vertical"), Registry.BLOCK_REGISTRY)
                .noItem()
                .addRecipe(modRes("stonecutting/acacia_log/hollow_acacia_log_vertical"))
                .build();

        this.addEntry(hollowLogsVertical);

        hollowLogsClimbable = SimpleEntrySet.builder(WoodType.class, "log_climbable", "hollow",
                        TFBlocks.HOLLOW_ACACIA_LOG_CLIMBABLE, () -> WoodTypeRegistry.getValue(new ResourceLocation("acacia")),
                        w -> regIfPossible(w, () -> new HollowLogClimbable(Utils.copyPropertySafe(w.log),
                                RegistryObject.create(Utils.getID(hollowLogsVertical.blocks.get(w)), ForgeRegistries.BLOCKS))))
                .addTag(modRes("hollow_logs_climbable"), Registry.BLOCK_REGISTRY)
                .noItem()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(hollowLogsClimbable);


    }

    @Override
    public void registerItems(Registrator<Item> registry) {
        super.registerItems(registry);
        hollowLogsVertical.blocks.forEach((w, b) -> {
            String itemName = Utils.getID(b).getPath().replace("_vertical", "");
            String childKey = this.getModId() + ":hollow_log";
            Item i = new HollowLogItem(
                    RegistryObject.create(EveryCompat.res(itemName + "_horizontal"), ForgeRegistries.BLOCKS),
                    RegistryObject.create(Utils.getID(b), ForgeRegistries.BLOCKS),
                    RegistryObject.create(EveryCompat.res(itemName + "_climbable"), ForgeRegistries.BLOCKS),
                    new Item.Properties().tab(getTab(w, childKey + "_vertical")));
            hollowLogsVertical.items.put(w, i);
            w.addChild(childKey, (Object) i);
            registry.register(EveryCompat.res(itemName + "_vertical"), i);
        });
    }

    private static CreativeModeTab getTab(WoodType w, String type) {
        return WoodConfigs.isTypeEnabled(w, type) ?
                (EveryCompat.MOD_TAB != null ? EveryCompat.MOD_TAB : TFItems.creativeTab) : null;
    }

    @Override
    public void registerBlockColors(ClientPlatformHelper.BlockColorEvent event) {
        event.register(
                (s, l, pos, i) -> l != null && pos != null ?
                        BiomeColors.getAverageFoliageColor(l, pos) : FoliageColor.getDefaultColor(),
                hollowLogsClimbable.blocks.values().toArray(Block[]::new));
        event.register(
                (s, l, pos, i) -> l != null && pos != null ?
                        BiomeColors.getAverageGrassColor(l, pos) : -1,
                hollowLogsHorizontal.blocks.values().toArray(Block[]::new));
    }

    @Override
    public void addStaticClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        super.addStaticClientResources(handler, manager);
    }

    @Nullable
    private <B extends Block> B regIfPossible(WoodType woodType, Supplier<B> supplier) {
        if (woodType.getChild("stripped_log") != null) {
            return supplier.get();
        }
        return null;
    }
}
