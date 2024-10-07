package net.mehvahdjukaar.every_compat;

import net.mehvahdjukaar.every_compat.api.CompatModule;
import net.mehvahdjukaar.moonlight.api.platform.ClientPlatformHelper;
import net.mehvahdjukaar.moonlight.api.platform.PlatformHelper;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesTypeRegistry;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.List;


public class EveryCompatClient {

    public static void commonInit() {
        EveryCompat.forAllModules(CompatModule::onClientInit);
        ClientPlatformHelper.addBlockEntityRenderersRegistration(EveryCompatClient::registerBlockEntityRenderers);
        ClientPlatformHelper.addBlockColorsRegistration(EveryCompatClient::registerBlockColors);
        ClientPlatformHelper.addItemColorsRegistration(EveryCompatClient::registerItemColors);
        ClientPlatformHelper.addAtlasTextureCallback(TextureAtlas.LOCATION_BLOCKS, EveryCompatClient::registerTextures);
    }

    private static void registerTextures(ClientPlatformHelper.AtlasTextureEvent event){
        EveryCompat.forAllModules(m -> m.stitchAtlasTextures(event));
    }

    private static void registerBlockColors(ClientPlatformHelper.BlockColorEvent event) {
        EveryCompat.forAllModules(m -> m.registerBlockColors(event));
    }

    private static void registerItemColors(ClientPlatformHelper.ItemColorEvent event) {
        EveryCompat.forAllModules(m -> m.registerItemColors(event));
    }

    private static void registerBlockEntityRenderers(ClientPlatformHelper.BlockEntityRendererEvent event) {
        EveryCompat.forAllModules(m -> m.registerBlockEntityRenderers(event));
    }

    public static void clientSetup() {
        EveryCompat.forAllModules(CompatModule::onClientSetup);
    }

    public static void onItemTooltip(ItemStack stack, TooltipFlag tooltipFlag, List<Component> components) {
        if (PlatformHelper.isDev()) {
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
