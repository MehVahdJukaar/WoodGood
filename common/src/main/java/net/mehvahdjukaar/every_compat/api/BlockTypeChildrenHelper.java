package net.mehvahdjukaar.every_compat.api;

import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

//helper utility class
public class BlockTypeChildrenHelper {

    public static boolean doChildrenExist(BlockType type, String... childKeys) {
        for (String key : childKeys) {
            if (type.getBlockOfThis(key) == null) return false;
        }
        return true;
    }

    /// for "polished_slab" it tries "slab" next, then falls back to the main child
    public static Block getChildOrMain(BlockType type, String childKey) {
        Block child = type.getBlockOfThis(childKey);
        if (child == null && childKey.contains("_")) {
            child = type.getBlockOfThis(childKey.substring(childKey.lastIndexOf('_') + 1));
        }
        if (child != null) return child;
        if (type.mainChild() instanceof Block main) return main;
        throw new UnsupportedOperationException("Main child of " + type + " is not a block, entry set needs setProperties");
    }

    public static BlockBehaviour.Properties copyChildProperties(BlockType type, String childKey) {
        return Utils.copyPropertySafe(getChildOrMain(type, childKey));
    }

    public static BlockBehaviour.Properties copyMainChildProperties(BlockType type) {
        if (type.mainChild() instanceof Block main) return Utils.copyPropertySafe(main);
        throw new UnsupportedOperationException("Main child of " + type + " is not a block, entry set needs setProperties");
    }
}
