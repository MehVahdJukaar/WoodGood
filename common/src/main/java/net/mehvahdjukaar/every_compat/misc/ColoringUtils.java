package net.mehvahdjukaar.every_compat.misc;

import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class ColoringUtils {

    static final Set<Identifier> HARDCODED_NO_TINT = Set.of(
            Identifier.fromNamespaceAndPath("blue_skies", "cherry"),
            Identifier.fromNamespaceAndPath("twilightforest", "beanstalk")
    );

    //parent sources might not be registered yet so we look them up lazily. 3 layers covers anything we copy
    private static final int COPIED_TINT_LAYERS = 3;

    public static void copyBlockTint(ClientHelper.BlockColorEvent event, Map<? extends BlockType,? extends Block> blocks) {
        blocks.forEach((type, block) -> {
            if (HARDCODED_NO_TINT.contains(type.id)) return;
            List<BlockTintSource> layers = new ArrayList<>();
            for (int i = 0; i < COPIED_TINT_LAYERS; i++) {
                layers.add(new ParentTintSource(type, i));
            }
            event.register(layers, block);
        });
    }

    //passes the parent state along since its source might read properties we dont have
    private record ParentTintSource(BlockType type, int layer) implements BlockTintSource {

        @Override
        public int color(BlockState state) {
            if (type.mainChild() instanceof Block bl) {
                var parentState = bl.defaultBlockState();
                var source = Minecraft.getInstance().getBlockColors().getTintSource(parentState, layer);
                if (source != null) return source.color(parentState);
            }
            return -1;
        }

        @Override
        public int colorInWorld(BlockState state, BlockAndTintGetter level, BlockPos pos) {
            if (type.mainChild() instanceof Block bl) {
                var parentState = bl.defaultBlockState();
                var source = Minecraft.getInstance().getBlockColors().getTintSource(parentState, layer);
                if (source != null) return source.colorInWorld(parentState, level, pos);
            }
            return -1;
        }
    }
}
