package net.mehvahdjukaar.every_compat;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ECPlatStuff {

    @ExpectPlatform
    public static List<ItemStack> modifyLoot(ResourceLocation id, List<ItemStack> stacks, LootContext lootContext) {
        throw new AssertionError();
    }

    @Nullable
    @ExpectPlatform
    public static String getModVersion(String modId) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static CreativeModeTab.Builder searchBar(CreativeModeTab.Builder builder) {
        throw new AssertionError();
    }

}
