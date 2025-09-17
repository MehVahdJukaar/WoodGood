package net.mehvahdjukaar.every_compat.misc;


import net.mehvahdjukaar.moonlight.api.resources.textures.TextureOps;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class AllWoodItem extends Item {

    public AllWoodItem() {
        super(new Properties());
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        super.inventoryTick(stack, level, entity, slotId, isSelected);
        stack.setCount(0);

    }
}
