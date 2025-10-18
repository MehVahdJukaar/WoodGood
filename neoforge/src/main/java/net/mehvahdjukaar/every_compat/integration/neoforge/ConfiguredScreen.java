package net.mehvahdjukaar.every_compat.integration.neoforge;


import com.mrcrayfish.configured.api.IModConfig;
import net.mehvahdjukaar.moonlight.api.integration.configured.CustomConfigScreen;
import net.mehvahdjukaar.moonlight.api.integration.configured.CustomConfigSelectScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import java.util.HashMap;
import java.util.Map;

//fancy config screen, extending configured's CustomConfigScreen

//credits to MrCrayfish's Configured Mod
public class ConfiguredScreen extends CustomConfigScreen {

    private static final Map<String, ItemStack> CUSTOM_ICONS = new HashMap<>();

    static {
        //TODO: here is a list of config category (what gets done with a .push, to icons)
        addIcon("general", Items.BOOKSHELF);
        addIcon("tooltips", Items.NAME_TAG);
        addIcon("types", Items.OAK_PLANKS);
        addIcon("entries", Items.OAK_FENCE);
    }

    private static void addIcon(String s, ItemLike i) {
        CUSTOM_ICONS.put(s, i.asItem().getDefaultInstance());
    }

    public ConfiguredScreen(CustomConfigSelectScreen parent, IModConfig config) {
        super(parent, config);
        this.icons.putAll(CUSTOM_ICONS);
    }

    public ConfiguredScreen(String modId, ItemStack mainIcon, Component title,
                            Screen parent, IModConfig config) {
        super(modId, mainIcon, title, parent, config);
        this.icons.putAll(CUSTOM_ICONS);
    }

    @Override
    public void onSave() {
    }

    @Override
    public Factory getSubScreenFactory() {
        return ConfiguredScreen::new;
    }

}