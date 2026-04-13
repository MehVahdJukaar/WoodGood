package net.mehvahdjukaar.every_compat.api;

import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.core.misc.McMetaFile;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface PaletteStrategy {

    PaletteAndAnimation getPaletteAndAnimation(BlockType t, ResourceManager manager) throws Exception;

    record PaletteAndAnimation(List<Palette> palette, @Nullable McMetaFile animation, @Nullable ResourceLocation id) {

        public static PaletteAndAnimation of(List<Palette> palette, @Nullable McMetaFile animation) {
            return new PaletteAndAnimation(palette, animation, null);
        }

        public static PaletteAndAnimation of(List<Palette> palette, @Nullable McMetaFile animation, ResourceLocation id) {
            return new PaletteAndAnimation(palette, animation, id);
        }
    }


}
