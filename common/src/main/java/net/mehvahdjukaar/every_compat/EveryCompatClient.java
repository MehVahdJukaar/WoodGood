package net.mehvahdjukaar.every_compat;

import net.mehvahdjukaar.every_compat.api.CompatModule;
import net.mehvahdjukaar.every_compat.configs.ModConfigs;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesTypeRegistry;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;


public class EveryCompatClient {

    public static void init() {
        ClientHelper.addClientSetup(EveryCompatClient::clientSetup);
        EveryCompat.forAllModules(CompatModule::onClientInit);
        ClientHelper.addBlockEntityRenderersRegistration(EveryCompatClient::registerBlockEntityRenderers);
        ClientHelper.addBlockColorsRegistration(EveryCompatClient::registerBlockColors);
        ClientHelper.addItemColorsRegistration(EveryCompatClient::registerItemColors);
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

    public static void onItemTooltip(ItemStack stack, TooltipFlag tooltipFlag, List<Component> components) {
        boolean mod = ModConfigs.MOD_TOOPTIP.get();
        boolean block = ModConfigs.BLOCK_TYPE_TOOLTIP.get();

        if (mod || block && (tooltipFlag.isAdvanced() || !ModConfigs.TOOLTIPS_ADVANCED.get())) {
            Item item = stack.getItem();
            var m = EveryCompat.ITEMS_TO_MODULES.get(item);
            if (m != null) {
                if (mod)
                    components.add(Component.translatable("tooltip.everycomp.mod", m.getModId()).withStyle(ChatFormatting.BLUE));
                if (block) {
                    BlockType w = WoodTypeRegistry.INSTANCE.getBlockTypeOf(item);
                    if (w == null) w = LeavesTypeRegistry.INSTANCE.getBlockTypeOf(item);
                    if (w != null) {
                        components.add(Component.translatable("tooltip.everycomp.wood_type", w.toString()).withStyle(ChatFormatting.BLUE));
                    }
                }
            }
        }
    }
}
