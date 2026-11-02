package net.mehvahdjukaar.every_compat;

import net.mehvahdjukaar.every_compat.api.CompatModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.List;


public class EveryCompatClient {

    public static void commonInit() {
        EveryCompat.forAllModules(CompatModule::onClientInit);
        ClientHelper.addBlockEntityRenderersRegistration(EveryCompatClient::registerBlockEntityRenderers);
        ClientHelper.addBlockColorsRegistration(EveryCompatClient::registerBlockColors);
        ClientHelper.addItemColorsRegistration(EveryCompatClient::registerItemColors);
        ClientDynamicResourcesHandler.getInstance().register();
    }

//    private static void registerTextures(ClientHelper.AtlasTextureEvent event){
//        EveryCompat.forAllModules(m -> m.stitchAtlasTextures(event));
//    }

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
        if (PlatHelper.isDev()) {
            Block blocked = Block.byItem(stack.getItem());
            // BLOCK TAGS
            if (blocked != Blocks.AIR) {
                components.add(Component.literal("BlockTag:").withStyle(ChatFormatting.GREEN));
                blocked.defaultBlockState().getTags().forEach((k) -> components.add(Component.literal("-" + k.location()).withStyle(Style.EMPTY.withColor(0xc8ffc8))));
            }

            // ITEM TAGS
            components.add(Component.literal("ItemTag:").withStyle(ChatFormatting.LIGHT_PURPLE));
            stack.getTags().forEach((k) -> components.add(Component.literal("-" + k.location()).withStyle(Style.EMPTY.withColor(0xffc8ff))));
        }
    }

}
