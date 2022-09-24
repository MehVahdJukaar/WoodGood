package net.mehvahdjukaar.every_compat.modules.twilightforest;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
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

    public final SimpleEntrySet<WoodType, BanisterBlock> BANISTERS;
    public final SimpleEntrySet<WoodType, HollowLogVertical> HOLLOW_LOGS_VERTICAL;
    public final SimpleEntrySet<WoodType, HollowLogHorizontal> HOLLOW_LOGS_HORIZONTAL;
    public final SimpleEntrySet<WoodType, HollowLogClimbable> HOLLOW_LOGS_CLIMBABLE;

    public TwilightForestModule(String modId) {
        super(modId, "tf");

        //TODO: check face culling
        BANISTERS = SimpleEntrySet.builder(WoodType.class, "banister",
                        TFBlocks.OAK_BANISTER, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BanisterBlock(Utils.copyPropertySafe(w.planks).noOcclusion()))
                .addTag(modRes("banisters"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("banisters"), Registry.ITEM_REGISTRY)
                .addRecipe(modRes("wood/oak_banister"))
                .useLootFromBase()
                .setTab(() -> TFItems.creativeTab)
                .build();

        this.addEntry(BANISTERS);


        HOLLOW_LOGS_HORIZONTAL = SimpleEntrySet.builder(WoodType.class, "log_horizontal", "hollow",
                        TFBlocks.HOLLOW_ACACIA_LOG_HORIZONTAL, () -> WoodTypeRegistry.getValue(new ResourceLocation("acacia")),
                        w -> regIfPossible(w, () -> new HollowLogHorizontal(Utils.copyPropertySafe(w.planks))))
                .addTag(modRes("hollow_logs_horizontal"), Registry.BLOCK_REGISTRY)
                .noItem()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(HOLLOW_LOGS_HORIZONTAL);


        HOLLOW_LOGS_VERTICAL = SimpleEntrySet.builder(WoodType.class, "log_vertical", "hollow",
                        TFBlocks.HOLLOW_ACACIA_LOG_VERTICAL, () -> WoodTypeRegistry.getValue(new ResourceLocation("acacia")),
                        w -> {
                            var id = EveryCompat.res(this.shortenedId() + "/" + w.getVariantId("hollow", true) + "_log_climbable");
                            return regIfPossible(w, () -> new HollowLogVertical(Utils.copyPropertySafe(w.planks), RegistryObject.create(id, ForgeRegistries.BLOCKS)));
                        })
                .addTag(modRes("hollow_logs_vertical"), Registry.BLOCK_REGISTRY)
                .noItem()
                .addRecipe(modRes("stonecutting/acacia_log/hollow_acacia_log"))
                .build();

        this.addEntry(HOLLOW_LOGS_VERTICAL);

        HOLLOW_LOGS_CLIMBABLE = SimpleEntrySet.builder(WoodType.class, "log_climbable", "hollow",
                        TFBlocks.HOLLOW_ACACIA_LOG_CLIMBABLE, () -> WoodTypeRegistry.getValue(new ResourceLocation("acacia")),
                        w -> regIfPossible(w, () -> new HollowLogClimbable(Utils.copyPropertySafe(w.planks),
                                RegistryObject.create(Utils.getID(HOLLOW_LOGS_VERTICAL.blocks.get(w)), ForgeRegistries.BLOCKS))))
                .addTag(modRes("hollow_logs_climbable"), Registry.BLOCK_REGISTRY)
                .noItem()
                .setRenderType(() -> RenderType::cutout)
                .build();

        this.addEntry(HOLLOW_LOGS_CLIMBABLE);


    }

    @Override
    public void registerItems(Registrator<Item> registry) {
        super.registerItems(registry);
        HOLLOW_LOGS_VERTICAL.blocks.forEach((w, b) -> {
            String itemName = Utils.getID(b).getPath().replace("_vertical", "");
            Item i = new HollowLogItem(
                    RegistryObject.create(EveryCompat.res(itemName + "_horizontal"), ForgeRegistries.BLOCKS),
                    RegistryObject.create(Utils.getID(b), ForgeRegistries.BLOCKS),
                    RegistryObject.create(EveryCompat.res(itemName + "_climbable"), ForgeRegistries.BLOCKS),
                    new Item.Properties().tab(TFItems.creativeTab));
            HOLLOW_LOGS_VERTICAL.items.put(w, i);
            registry.register(EveryCompat.res(itemName), i);
        });
    }

    @Override
    public void registerBlockColors(ClientPlatformHelper.BlockColorEvent event) {
        event.register(
                (s, l, pos, i) -> l != null && pos != null ?
                        BiomeColors.getAverageFoliageColor(l, pos) : FoliageColor.getDefaultColor(),
                HOLLOW_LOGS_CLIMBABLE.blocks.values().toArray(Block[]::new));
        event.register(
                (s, l, pos, i) -> l != null && pos != null ?
                        BiomeColors.getAverageGrassColor(l, pos) : -1,
                HOLLOW_LOGS_HORIZONTAL.blocks.values().toArray(Block[]::new));
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
