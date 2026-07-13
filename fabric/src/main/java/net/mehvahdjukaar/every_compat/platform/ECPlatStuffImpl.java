package net.mehvahdjukaar.every_compat.platform;

import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootParams;

import java.util.List;

public class ECPlatStuffImpl {

    public static List<ItemStack> modifyLoot(ResourceLocation id, List<ItemStack> stacks, LootParams lootContext) {
        return stacks;
    }

    public static void registerStripping(Block log, Block stripped_log) {
        StrippableBlockRegistry.register(log, stripped_log);
    }

}
