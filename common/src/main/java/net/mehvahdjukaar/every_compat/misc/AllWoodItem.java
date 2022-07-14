package net.mehvahdjukaar.every_compat.misc;


import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.mehvahdjukaar.moonlight.api.client.ICustomItemRendererProvider;
import net.mehvahdjukaar.moonlight.api.client.ItemStackRenderer;
import net.minecraft.world.item.Item;

public class AllWoodItem extends Item implements ICustomItemRendererProvider {

    public AllWoodItem() {
        super(new Properties().tab(null));
    }


    @Environment(EnvType.CLIENT)
    @Override
    public ItemStackRenderer createRenderer() {
        return new AllWoodItemRenderer();
    }
}
