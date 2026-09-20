package net.mehvahdjukaar.every_compat.fabric;

import net.mehvahdjukaar.every_compat.ECPlatStuff;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootParams;

import java.util.List;

public class ECPlatStuffImpl extends ECPlatStuff {

    public static List<ItemStack> modifyLoot(ResourceLocation id, List<ItemStack> stacks, LootParams lootContext) {
        return stacks;
    }

    public static void registerStripping(Block post, Block stripped) {
    }

}
