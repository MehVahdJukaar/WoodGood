package net.mehvahdjukaar.every_compat.forge;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.mehvahdjukaar.every_compat.ECPlatStuff;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.registries.GameData;
import org.violetmoon.zeta.util.handler.ToolInteractionHandler;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ECPlatStuffImpl extends ECPlatStuff {

    public static List<ItemStack> modifyLoot(ResourceLocation id, List<ItemStack> stacks, LootParams lootContext) {
        ItemStack[] array = stacks.toArray(ItemStack[]::new);
        return ForgeHooks.modifyLoot(id, ObjectArrayList.wrap(array),
                (new LootContext.Builder(lootContext)).create(id));
    }

    public static void registerStripping(Block block, Block stripped_block) {
        ToolInteractionHandler.registerInteraction(ToolActions.AXE_STRIP, block, stripped_block);
    }

    //forge nukes registerBlockStates. this map is rebuilt from matchingStates on every registry sync
    public static void registerPoiStates(Holder<PoiType> poi, Set<BlockState> states) {
        Map<BlockState, PoiType> map = GameData.getBlockStatePointOfInterestTypeMap();
        for (BlockState s : states) {
            map.put(s, poi.value());
        }
    }

}
