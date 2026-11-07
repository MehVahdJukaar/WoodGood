package net.mehvahdjukaar.every_compat.fabric;

import net.mehvahdjukaar.every_compat.ECPlatStuff;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.ai.village.poi.PoiTypes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;

import java.util.List;
import java.util.Set;

public class ECPlatStuffImpl extends ECPlatStuff {

    public static List<ItemStack> modifyLoot(ResourceLocation id, List<ItemStack> stacks, LootParams lootContext) {
        return stacks;
    }

    public static void registerStripping(Block post, Block stripped) {
    }

    public static void registerPoiStates(Holder<PoiType> poi, Set<BlockState> states) {
        PoiTypes.registerBlockStates(poi, states);
    }

}
