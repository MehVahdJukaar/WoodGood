package net.mehvahdjukaar.every_compat.misc;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.MultiLineLabel;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;

import java.util.List;

public class ErrorMessageScreen extends Screen {
    private final Screen lastScreen;
    private final Component text;
    private int ticksUntilEnable;
    private MultiLineLabel message;

    private Button exitButton;
    private Button disaleButton;

    public ErrorMessageScreen(Screen screen, int ticksUntilEnable,
                              Component title, Component text) {
        super(title);
        this.message = MultiLineLabel.EMPTY;
        this.lastScreen = screen;
        this.ticksUntilEnable = ticksUntilEnable;
        this.text = text;
    }

    @Override
    public Component getNarrationMessage() {
        return CommonComponents.joinForNarration(super.getNarrationMessage(), text);
    }

    @Override
    protected void init() {
        super.init();
        this.exitButton = this.addRenderableWidget(Button.builder(CommonComponents.GUI_PROCEED, (pressed) -> {
            Minecraft.getInstance().setScreen(this.lastScreen);
        }).bounds(this.width / 2 + 5, this.height * 5 / 6, 150, 20).build());
        this.exitButton.active = false;

        this.disaleButton = this.addRenderableWidget(Button.builder(
                Component.translatable("gui.supplementaries.welcome_screen.turn_off"), (pressed) -> {
                    throw new RuntimeException();
                }).bounds((this.width - 150) / 2, this.height * 5 / 6, 150, 20).build());
        this.disaleButton.active = false;

        this.message = MultiLineLabel.create(this.font, text, this.width - 50);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        super.render(graphics, mouseX, mouseY, partialTicks);
        graphics.drawCenteredString(this.font, this.title, this.width / 2, 30, 16777215);
        this.message.renderCentered(graphics, this.width / 2, 55);
    }

    @Override
    public void tick() {
        super.tick();
        if (--this.ticksUntilEnable <= 0) {
            this.exitButton.active = true;
            this.disaleButton.active = true;
        }
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return this.ticksUntilEnable <= 0;
    }

    @Override
    public void onClose() {
        Minecraft.getInstance().setScreen(this.lastScreen);
    }

    // static stuff
    private static final Component TEXT = Component.translatable("gui.everycomp.error_screen.message");

    private static final Component TITLE = Component.translatable("gui.everycomp.error_screen.title")
            .withStyle(ChatFormatting.RED).withStyle(ChatFormatting.BOLD);

    public static ErrorMessageScreen create(Screen screen, List<String> mods) {
        return new ErrorMessageScreen(screen, 40, TITLE, TEXT);
    }

}
