package net.mehvahdjukaar.every_compat.misc;

import net.mehvahdjukaar.every_compat.ECModules;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.moonlight.api.client.gui.ConfigScreenExtensions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;

public class BlockCountConfigOverlay implements ConfigScreenExtensions.Overlay {

    private static final int COLOR = 0xFFA0A0A0;
    private static final int BLOATED_COLOR = 0xFFFF5555;

    public static void register() {
        ConfigScreenExtensions.registerOverlay(EveryCompat.MOD_ID, new BlockCountConfigOverlay());
    }

    @Override
    public void render(GuiGraphicsExtractor graphics, ConfigScreenExtensions.Panel panel, int mouseX, int mouseY, float partialTick) {
        var font = Minecraft.getInstance().font;
        float percent = ECModules.getRegisteredBlocksPercentage();
        Component text = Component.translatable("gui.everycomp.blocks_added",
                ECModules.getRegisteredBlocksCount(), ECModules.getActiveModuleIds().size(),
                String.format("%.1f", percent));
        int x = (panel.left() + panel.right()) / 2;
        int y = panel.bottom() - font.lineHeight - 4;
        graphics.centeredText(font, text, x, y, percent > ECModules.BLOAT_WARN_PERCENT ? BLOATED_COLOR : COLOR);
    }
}
