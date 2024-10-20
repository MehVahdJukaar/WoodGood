package net.mehvahdjukaar.every_compat.forge;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraftforge.common.ForgeHooks;

import java.util.List;

public class ECPlatStuffImpl {
    public static List<ItemStack> modifyLoot(ResourceLocation id, List<ItemStack> stacks, LootParams lootContext) {
        ItemStack[] array = stacks.toArray(ItemStack[]::new);
        return ForgeHooks.modifyLoot(id, ObjectArrayList.wrap(array),
                (new LootContext.Builder(lootContext)).create(id));
    }

}
