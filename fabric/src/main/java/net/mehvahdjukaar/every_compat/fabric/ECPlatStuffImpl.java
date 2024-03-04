package net.mehvahdjukaar.every_compat.fabric;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;

import java.util.List;

public class ECPlatStuffImpl {
    public static List<ItemStack> modifyLoot(ResourceLocation id, List<ItemStack> stacks, LootContext lootContext) {
        return stacks;
    }
}
