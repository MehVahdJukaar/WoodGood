package net.mehvahdjukaar.every_compat;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootParams;

import java.util.List;

public class ECPlatStuff {

    @ExpectPlatform
    public static List<ItemStack> modifyLoot(ResourceLocation id, List<ItemStack> stacks, LootParams lootContext) {
        throw new AssertionError();
    }
}
