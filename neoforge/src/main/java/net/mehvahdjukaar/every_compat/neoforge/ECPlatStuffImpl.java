package net.mehvahdjukaar.every_compat.neoforge;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootParams;
import net.neoforged.neoforge.common.CommonHooks;
import net.neoforged.neoforge.event.EventHooks;

import java.util.List;
import java.util.Optional;

public class ECPlatStuffImpl {

    public static List<ItemStack> modifyLoot(ResourceLocation id, List<ItemStack> stacks, LootParams lootContext) {
        ItemStack[] array = stacks.toArray(ItemStack[]::new);
        return CommonHooks.modifyLoot(id, ObjectArrayList.wrap(array),
                (new LootContext.Builder(lootContext)).create(Optional.of(id)));
    }

}
