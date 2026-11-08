package net.mehvahdjukaar.every_compat;

import net.mehvahdjukaar.every_compat.api.CompatModule;
import net.mehvahdjukaar.every_compat.api.CompatModuleClient;
import net.mehvahdjukaar.every_compat.configs.ECConfigs;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.misc.BlockCountConfigOverlay;
import net.mehvahdjukaar.every_compat.misc.ErrorMessageScreen;
import net.mehvahdjukaar.every_compat.misc.FurnitureShowcaseWidget;
import net.mehvahdjukaar.every_compat.misc.WoodTypeCycleItemModel;
import net.mehvahdjukaar.moonlight.api.misc.EventCalled;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesTypeRegistry;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.item.ItemModels;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;


public class EveryCompatClient {

    private static final Map<CompatModule, CompatModuleClient<?>> CLIENTS = new ConcurrentHashMap<>();

    public static void init() {
        ClientHelper.addClientSetup(EveryCompatClient::clientSetup);
        forAllClients(CompatModuleClient::onClientInit);
        ClientHelper.addBlockEntityRenderersRegistration(EveryCompatClient::registerBlockEntityRenderers);
        ClientHelper.addBlockColorsRegistration(EveryCompatClient::registerBlockColors);
        RegHelper.registerDynamicResourceProvider(ClientDynamicResourcesHandler.getInstance());
        ItemModels.ID_MAPPER.put(EveryCompat.res("wood_type_cycle"), WoodTypeCycleItemModel.Unbaked.MAP_CODEC);
        BlockCountConfigOverlay.register();
        FurnitureShowcaseWidget.register();
    }

    @EventCalled
    public static void onFirstScreen(Screen screen) {
        var errors = ECModules.getModulesThatErrored();
        if (!errors.isEmpty()) {
            Minecraft.getInstance().setScreen(ErrorMessageScreen.create(screen, errors));
        }
        ECModules.setCanShowErrorScreen(false);
    }

    // lazy so modules added late by addons still get one
    public static void forAllClients(Consumer<CompatModuleClient<?>> action) {
        ECModules.forAllModules(m -> action.accept(CLIENTS.computeIfAbsent(m, CompatModule::createClient)));
    }

    private static void registerBlockColors(ClientHelper.BlockColorEvent event) {
        forAllClients(c -> c.registerBlockColors(event));
    }

    private static void registerBlockEntityRenderers(ClientHelper.BlockEntityRendererEvent event) {
        forAllClients(c -> c.registerBlockEntityRenderers(event));
    }

    public static void clientSetup() {
        forAllClients(CompatModuleClient::onClientSetup);
    }

    public static void onItemTooltip(ItemStack stack, Item.TooltipContext tooltipContext, TooltipFlag tooltipFlag, List<Component> components) {
        boolean modTooltip = ECConfigs.MOD_TOOPTIP.get();
        boolean blockTooltip = ECConfigs.BLOCK_TYPE_TOOLTIP.get();

        if (modTooltip || blockTooltip && (tooltipFlag.isAdvanced() || !ECConfigs.TOOLTIPS_ADVANCED.get())) {
            Item item = stack.getItem();
            var compatModule = ECModules.getModuleOfItem(item);
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
}
