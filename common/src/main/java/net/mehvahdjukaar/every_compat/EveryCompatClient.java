package net.mehvahdjukaar.every_compat;

import net.mehvahdjukaar.every_compat.api.CompatModule;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.configs.ECConfigs;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesTypeRegistry;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;


public class EveryCompatClient {

    public static void init() {
        ClientHelper.addClientSetup(EveryCompatClient::clientSetup);
        EveryCompat.forAllModules(CompatModule::onClientInit);
        ClientHelper.addBlockEntityRenderersRegistration(EveryCompatClient::registerBlockEntityRenderers);
        ClientHelper.addBlockColorsRegistration(EveryCompatClient::registerBlockColors);
        ClientHelper.addItemColorsRegistration(EveryCompatClient::registerItemColors);
        ClientDynamicResourcesHandler.getInstance().register();
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
        boolean mod = ECConfigs.MOD_TOOPTIP.get();
        boolean block = ECConfigs.BLOCK_TYPE_TOOLTIP.get();

        if (mod || block && (tooltipFlag.isAdvanced() || !ECConfigs.TOOLTIPS_ADVANCED.get())) {
            Item item = stack.getItem();
            var m = EveryCompat.getModuleOfItem(item);
            if (m != null) {
                if (mod)
                    components.add(Component.translatable("tooltip.everycomp.mod", m.getModName()).withStyle(ChatFormatting.BLUE));
                if (block) {
                    BlockType w = WoodTypeRegistry.INSTANCE.getBlockTypeOf(item);
                    if (w == null) w = LeavesTypeRegistry.INSTANCE.getBlockTypeOf(item);
                    if (w != null) {
                        components.add(Component.translatable("tooltip.everycomp.wood_type", w.toString()).withStyle(ChatFormatting.BLUE));
                    }
                }
            }
        }
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

    public static <B extends Block> void registerRenderType(B b, BlockType bt, @Nullable Object type) {
        if(bt.id.equals(ResourceLocation.parse("rats:pirat"))){
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
