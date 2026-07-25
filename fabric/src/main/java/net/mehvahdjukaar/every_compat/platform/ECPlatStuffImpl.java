package net.mehvahdjukaar.every_compat.platform;

import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootParams;

import java.util.List;
import java.util.function.Consumer;

public class ECPlatStuffImpl {

    public static List<ItemStack> modifyLoot(ResourceLocation id, List<ItemStack> stacks, LootParams lootContext) {
        return stacks;
    }

    public static void registerStripping(Block block, Block stripped_block) {
        StrippableBlockRegistry.register(block, stripped_block);
    }

    public static void addPlatformServerResources(Consumer<ResourceGenTask> executor) {
    }

}
