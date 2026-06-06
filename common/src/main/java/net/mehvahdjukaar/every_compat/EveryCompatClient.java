package net.mehvahdjukaar.every_compat;

import net.mehvahdjukaar.every_compat.api.CompatModule;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.configs.ECConfigs;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.misc.ErrorMessageScreen;
import net.mehvahdjukaar.every_compat.misc.WoodTypeCycleItemRenderer;
import net.mehvahdjukaar.moonlight.api.misc.EventCalled;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesTypeRegistry;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;

import java.util.List;
import java.util.function.Supplier;


public class EveryCompatClient {

    public static void init() {
        ClientHelper.addClientSetup(EveryCompatClient::clientSetup);
        EveryCompat.forAllModules(CompatModule::onClientInit);
        ClientHelper.addBlockEntityRenderersRegistration(EveryCompatClient::registerBlockEntityRenderers);
        ClientHelper.addBlockColorsRegistration(EveryCompatClient::registerBlockColors);
        ClientHelper.addItemColorsRegistration(EveryCompatClient::registerItemColors);
        RegHelper.registerDynamicResourceProvider(ClientDynamicResourcesHandler.getInstance());
        ClientHelper.addItemRenderersRegistration(event -> {
            event.register(ECRegistry.ALL_WOODS.get(), new WoodTypeCycleItemRenderer());
        });
    }

    @EventCalled
    public static void onFirstScreen(Screen screen) {
        var errors = EveryCompat.getModulesThatErrored();
        if (!errors.isEmpty()) {
            Minecraft.getInstance().setScreen(ErrorMessageScreen.create(screen, errors));
        }
        EveryCompat.canShowErrorScreen = false;
    }

    private static void registerBlockColors(ClientHelper.BlockColorEvent event) {
        EveryCompat.forAllModules(m -> m.registerBlockColors(event));
    }

    private static void registerItemColors(ClientHelper.ItemColorEvent event) {
        EveryCompat.forAllModules(m -> m.registerItemColors(event));
    }

    private static void registerBlockEntityRenderers(ClientHelper.BlockEntityRendererEvent event) {
        EveryCompat.forAllModules(m -> m.registerBlockEntityRenderers(event));
    }

    public static void clientSetup() {
        EveryCompat.forAllModules(CompatModule::onClientSetup);
    }

    public static void onItemTooltip(ItemStack stack, Item.TooltipContext tooltipContext, TooltipFlag tooltipFlag, List<Component> components) {
        boolean modTooltip = ECConfigs.MOD_TOOPTIP.get();
        boolean blockTooltip = ECConfigs.BLOCK_TYPE_TOOLTIP.get();

        if (modTooltip || blockTooltip && (tooltipFlag.isAdvanced() || !ECConfigs.TOOLTIPS_ADVANCED.get())) {
            Item item = stack.getItem();
            var compatModule = EveryCompat.getModuleOfItem(item);
            if (compatModule != null) {
                if (blockTooltip) {
                    BlockType woodType = WoodTypeRegistry.INSTANCE.getBlockTypeOf(item);
                    if (woodType != null) {
                        components.add(Component.translatable("tooltip.everycomp.wood_type", woodType.toString()).withStyle(ChatFormatting.DARK_GREEN));
                    }

                    BlockType leavesType = LeavesTypeRegistry.INSTANCE.getBlockTypeOf(item);
                    if (leavesType != null) {
                        components.add(Component.translatable("tooltip.everycomp.leaf_type", leavesType.toString()).withStyle(ChatFormatting.DARK_GREEN));
                    }
                }
                if (modTooltip)
                    components.add(Component.translatable("tooltip.everycomp.mod", compatModule.getModName()).withStyle(ChatFormatting.BLUE));
            }
        }
        //tags stuff moved to ML, under the config
    }

    public static <B extends Block> void registerRenderType(B b, BlockType bt, Object type) {
        if (bt.id.equals(ResourceLocation.tryParse("rats:pirat"))) {
            type = RenderLayer.TRANSLUCENT;
        }
        if (type == null) return;
        if (type instanceof RenderLayer rl) {
            switch (rl) {
                case CUTOUT -> ClientHelper.registerRenderType(b, RenderType.cutout());
                case CUTOUT_MIPPED -> ClientHelper.registerRenderType(b, RenderType.cutoutMipped());
                case TRANSLUCENT -> ClientHelper.registerRenderType(b, RenderType.translucent());
                case SOLID -> ClientHelper.registerRenderType(b, RenderType.solid());
            }
        } else if (type instanceof Supplier<?> s) {
            RenderType renderType = ((Supplier<Supplier<RenderType>>) s).get().get();
            ClientHelper.registerRenderType(b, renderType);
        } else if (PlatHelper.isDev()) {
            throw new IllegalArgumentException("Invalid render type: " + type);
        }

    }
}
