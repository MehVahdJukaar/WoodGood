package net.mehvahdjukaar.every_compat.misc;

import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

import java.util.Map;
import java.util.Set;

public final class ColoringUtils {

    static final Set<ResourceLocation> HARDCODED_NO_TINT = Set.of(
            new ResourceLocation("regions_unexplored", "flowering"),
            new ResourceLocation("blue_skies", "cherry"),
            new ResourceLocation("twilightforest", "beanstalk")
    );

    public static void copyItemTint(ClientHelper.ItemColorEvent event, Map<? extends BlockType, ? extends Item> items) {
        items.forEach((type, item) -> {
            event.register((stack, tintIndex) -> {
                        ItemStack parentItem = new ItemStack(type.mainChild());
                        if (parentItem.isEmpty()) return 1;
                        return event.getColor(parentItem, tintIndex);
                    },
                    item.asItem());
        });
    }

    public static void copyBlockTint(ClientHelper.ItemColorEvent event, Map<? extends BlockType, ? extends Block> blocks) {
        blocks.forEach((type, item) -> {
            event.register((stack, tintIndex) -> {
                        ItemStack parentItem = new ItemStack(type.mainChild());
                        if (parentItem.isEmpty()) return 1;
                        return event.getColor(parentItem, tintIndex);
                    },
                    item.asItem());
        });
    }

    public static void copyBlockTint(ClientHelper.BlockColorEvent event, Map<? extends BlockType,? extends Block> blocks) {
        blocks.forEach((type, block) -> {
            if (HARDCODED_NO_TINT.contains(type.id)) return;
            event.register((bs, l, p, i) -> {
                if (type.mainChild() instanceof Block bl) {
                    return event.getColor(bl.defaultBlockState(), l, p, i);
                }
                return 1;
            }, block);
        });
    }

}
