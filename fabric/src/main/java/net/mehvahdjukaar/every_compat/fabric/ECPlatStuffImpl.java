package net.mehvahdjukaar.every_compat.fabric;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;

import java.util.List;

@SuppressWarnings("unused")
public class ECPlatStuffImpl {
    public static List<ItemStack> modifyLoot(ResourceLocation id, List<ItemStack> stacks, LootContext lootContext) {
        return stacks;
    }

    public static String getModVersion(String modId) {
        return FabricLoader.getInstance().getModContainer(modId).map(container ->
                    container.getMetadata().getVersion().getFriendlyString()
                ).orElse("Unknown");
    }
}
