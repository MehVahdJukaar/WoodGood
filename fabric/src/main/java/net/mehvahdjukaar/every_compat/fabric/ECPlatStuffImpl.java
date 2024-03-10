package net.mehvahdjukaar.every_compat.fabric;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootParams;

import java.util.List;

public class ECPlatStuffImpl {

    public static List<ItemStack> modifyLoot(ResourceLocation id, List<ItemStack> stacks, LootParams lootContext) {
        return stacks;
    }

}
