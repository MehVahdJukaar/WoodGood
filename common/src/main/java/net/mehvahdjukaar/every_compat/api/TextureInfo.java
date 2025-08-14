package net.mehvahdjukaar.every_compat.api;

import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public record TextureInfo<T extends BlockType>(ResourceLocation texture, @Nullable ResourceLocation mask,
                                               boolean keepNamespace, boolean copyTexture, String customTexturePath,
                                               boolean copyMCMETA, boolean autoMask,
                                               boolean onAtlas, PaletteStrategy paletteStrategy) {

    public static <T extends BlockType> Builder<T> of(ResourceLocation res) {
        return new Builder<T>(res);
    }

    public static <T extends BlockType> Builder<T> of(ResourceLocation res, ResourceLocation mask) {
        return new Builder<T>(res).mask(mask);
    }

    public static <T extends BlockType> Builder<T> of(ResourceLocation res, String customTexturePath) {
        return new Builder<T>(res).customTexture(customTexturePath);
    }

    //remove once you remove the rest of palette madness
    @Deprecated(forRemoval = true)
    public TextureInfo<T> cloneWithPalette(PaletteStrategy newPalette) {
        return new TextureInfo<>(this.texture, this.mask, this.keepNamespace, this.copyTexture, this.customTexturePath,
                this.copyMCMETA, this.autoMask, this.onAtlas, newPalette);
    }

    public static class Builder<T extends BlockType> {
        private final ResourceLocation texture;
        private ResourceLocation mask;
        private boolean keepNamespace = false;
        private boolean copyTexture = false;
        private boolean copyMCMETA = false;
        private boolean autoMask = false;
        private boolean onAtlas;
        private String customTexturePath;
        private PaletteStrategy palette = PaletteStrategies.MAIN_CHILD;

        public Builder(ResourceLocation texture) {
            this.texture = texture;
            this.onAtlas = !texture.getPath().startsWith("entity/");
        }

        public Builder<T> mask(ResourceLocation mask) {
            this.mask = mask;
            return this;
        }

        // for textures not on atlas that won't be cleared
        public Builder<T> forEntityOrGui() {
            this.onAtlas = false;
            return this;
        }

        public Builder<T> keepNamespace() {
            this.keepNamespace = true;
            return this;
        }

        public Builder<T> copyTexture() {
            this.copyTexture = true;
            return this;
        }

        public Builder<T> customTexture(String customTexturePath) {
            this.customTexturePath = customTexturePath;
            return this;
        }

        public Builder<T> copyMCMETA() {
            this.copyMCMETA = true;
            return this;
        }

        public Builder<T> autoMask() {
            this.autoMask = true;
            return this;
        }

        //a bit of abuse of type here, should be PaletteStrategy but i want to enforce them being cached
        public Builder<T> palette(PaletteStrategy paletteProvider) {
            this.palette = paletteProvider;
            return this;
        }


        public TextureInfo<T> build() {
            return new TextureInfo<T>(texture, mask, keepNamespace,
                    copyTexture, customTexturePath, copyMCMETA, autoMask, onAtlas, palette);
        }
    }

}
