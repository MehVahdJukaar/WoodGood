package net.mehvahdjukaar.every_compat;

import net.mehvahdjukaar.candlelight.api.PlatformImpl;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootParams;

import java.util.List;

public class ECPlatStuff {

    @PlatformImpl
    public static List<ItemStack> modifyLoot(ResourceLocation id, List<ItemStack> stacks, LootParams lootContext) {
        throw new AssertionError();
    }

    @PlatformImpl
    public static void registerStripping(Block post, Block stripped) {
        throw new AssertionError();
    }
}
