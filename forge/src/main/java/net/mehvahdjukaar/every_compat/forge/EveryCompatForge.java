package net.mehvahdjukaar.every_compat.forge;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.fml.common.Mod;

/**
 * Author: MehVahdJukaar
 */
@Mod(EveryCompat.MOD_ID)
public class EveryCompatForge extends EveryCompat {
    public static final String MOD_ID = EveryCompat.MOD_ID;

    public EveryCompatForge() {
        this.commonInit();

       // MinecraftForge.EVENT_BUS.addListener(CustomRecipeLoader::onEarlyPackLoad);
       // MinecraftForge.EVENT_BUS.register(EntriesRemapper.class);
        CraftingHelper.register(new BlockTypeEnabledCondition.Serializer());

    }


}
