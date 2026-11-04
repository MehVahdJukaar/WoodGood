package net.mehvahdjukaar.every_compat.forge;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.forgespi.language.IModInfo;

import java.util.List;

public class ECPlatStuffImpl {
    public static List<ItemStack> modifyLoot(ResourceLocation id, List<ItemStack> stacks, LootContext lootContext) {
        ItemStack[] array = stacks.toArray(ItemStack[]::new);
        return ForgeHooks.modifyLoot(id, ObjectArrayList.wrap(array), lootContext);
    }

    public static String getModVersion(String modId) {
        IModInfo modInfo = ModList.get().getModContainerById(modId).map(ModContainer::getModInfo).orElse(null);
        return modInfo != null ? modInfo.getVersion().toString() : "unknown";
    }

    public static CreativeModeTab.Builder searchBar(CreativeModeTab.Builder builder) {
        return builder.withSearchBar();
    }

}
