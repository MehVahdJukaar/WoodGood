package net.mehvahdjukaar.every_compat.misc;

import net.mehvahdjukaar.every_compat.configs.ECConfigs;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.Objects;

public class WoodTypeCycleItemRenderer extends BlockTypeCycleItemRenderer<WoodType> {
    public WoodTypeCycleItemRenderer() {
        super(WoodType.class);
    }

    @Override
    @SuppressWarnings("ConstantValue")
    public ItemStack getItemIcon() {
        var itemId = ECConfigs.CREATIVE_TAB_ICON.get();
        ItemStack item = BuiltInRegistries.ITEM.get(ResourceLocation.parse(itemId)).getDefaultInstance();

        if (Objects.nonNull(item)) return item;
        else return Items.BARRIER.getDefaultInstance();
    }

    @Override
    public boolean getDisableCycleItemRenderer() {
        return ECConfigs.DISABLE_CYCLE_ITEM_RENDERER.get();
    }
}
