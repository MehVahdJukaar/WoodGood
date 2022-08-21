package net.mehvahdjukaar.every_compat.modMenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.mehvahdjukaar.every_compat.configs.EarlyConfigs;
import net.mehvahdjukaar.moonlight.api.integration.cloth_config.ClothConfigCompat;
import net.mehvahdjukaar.moonlight.api.platform.configs.fabric.FabricConfigSpec;

public class ModMenuCompat implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> ClothConfigCompat.makeScreen(parent, (FabricConfigSpec) EarlyConfigs.SPEC);
    }
}