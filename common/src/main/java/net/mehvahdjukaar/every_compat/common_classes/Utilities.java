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

    public static BlockBehaviour.Properties copyChildrenPropertySafe(String childType, BlockType blockType) {
        Block block = blockType.getBlockOfThis(childType);
        Block blockAlt = null;
        if (childType.contains("_")) {
            String[] split = childType.split("_");
            blockAlt = blockType.getBlockOfThis(split[split.length - 1]);
        }

        if (Objects.nonNull(block)) return Utils.copyPropertySafe(block);
        else if (Objects.nonNull(blockAlt)) return Utils.copyPropertySafe(blockAlt);
        else return Utils.copyPropertySafe((Block) blockType.mainChild());
    }

    /**
     * @param types stairs, slab, or other blockTypes can be used
     * @param blockType WoodType, LeavesType, or StoneType
     */
    public static BlockState copyBlockStateSafe(String types, BlockType blockType) {
        Block block = blockType.getBlockOfThis(types);
        Block blockAlt = null;
        if (types.contains("_")) {
            String[] split = types.split("_");
            blockAlt = blockType.getBlockOfThis(split[1]);
        }

        if (Objects.nonNull(block)) return block.defaultBlockState();
        else if (Objects.nonNull(blockAlt)) return blockAlt.defaultBlockState();
        else return ((Block) blockType.mainChild()).defaultBlockState();
    }

    /**
     * @param blocks SimpleEntrySet.blocks
     * @param blockType WoodType, LeavesType, or StoneType
     */
    public static BlockState copyBlockStateSafe(Map<?, Block> blocks, BlockType blockType) {
        if (Objects.nonNull(blocks.get(blockType))) return blocks.get(blockType).defaultBlockState();
        else return ((Block) blockType.mainChild()).defaultBlockState();
    }

}
