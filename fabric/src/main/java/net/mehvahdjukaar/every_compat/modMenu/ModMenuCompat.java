package net.mehvahdjukaar.every_compat.modMenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.configs.EarlyConfigs;
import net.mehvahdjukaar.every_compat.configs.WoodConfigs;
import net.mehvahdjukaar.moonlight.api.platform.configs.fabric.FabricConfigListScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

public class ModMenuCompat implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return p -> new FabricConfigListScreen(EveryCompat.MOD_ID, Items.OAK_PLANKS.getDefaultInstance(),
                Component.literal("\u00A76Every Compat Configs"), new ResourceLocation("textures/block/oak_planks.png"),
                p, EarlyConfigs.SPEC, WoodConfigs.SPEC);
    }
}