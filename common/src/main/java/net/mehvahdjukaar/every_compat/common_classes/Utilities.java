package net.mehvahdjukaar.every_compat.common_classes;

import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Map;
import java.util.Objects;

@SuppressWarnings("unused")
public class Utilities {

    public static boolean doChildrenExistFor(BlockType blockType, String... blockTypes) {
        for (String type : blockTypes) {
            if (Objects.isNull(blockType.getBlockOfThis(type))) return false;
        }
        return true;
    }

    public static boolean doChildrenExistFor(BlockType blockType, SimpleEntrySet<?, ?> entrySet) {
            return Objects.nonNull(entrySet.blocks.get(blockType));
    }

    public static Block getChildrenBlockSafe(String childkey, BlockType blockType) {
        Block block = blockType.getBlockOfThis(childkey);
        Block blockAlt = null;
        if (childkey.contains("_")) {
            String[] split = childkey.split("_");
            blockAlt = blockType.getBlockOfThis(split[split.length - 1]);
        }

        if (Objects.nonNull(block)) return block;
        else if (Objects.nonNull(blockAlt)) return blockAlt;
        else return (Block) blockType.mainChild();
    }

    public static BlockBehaviour.Properties copyChildrenPropertySafe(String childKey, BlockType blockType) {
        return Utils.copyPropertySafe(getChildrenBlockSafe(childKey, blockType));
    }

    /**
     * @param childkey stairs, slab, or other childkeys can be used
     * @param blockType WoodType, LeavesType, or other BlockTypes
     */
    public static BlockState copyBlockStateSafe(String childkey, BlockType blockType) {
        return getChildrenBlockSafe(childkey, blockType).defaultBlockState();
    }

    /**
     * @param blocks SimpleEntrySet.blocks
     * @param blockType WoodType, LeavesType, or other BlockTypes
     */
    public static BlockState copyBlockStateSafe(Map<?, Block> blocks, BlockType blockType) {
        if (Objects.nonNull(blocks.get(blockType))) return blocks.get(blockType).defaultBlockState();
        else return ((Block) blockType.mainChild()).defaultBlockState();
    }

}
