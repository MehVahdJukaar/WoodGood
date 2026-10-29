package net.mehvahdjukaar.every_compat.api;

import com.mojang.datafixers.util.Pair;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.Nullable;

public record TextureInfo(Identifier texture, @Nullable Identifier mask, @Nullable Identifier extraMask, @Nullable Identifier overlay,
                          boolean keepNamespace, boolean copyTexture, boolean noAnimation,
                          String customTexturePath, Pair<String, String> replacePath,
                          boolean autoMask,
                          @Deprecated boolean onAtlas,
                          PaletteStrategy paletteStrategy,
                          @Nullable PaletteStrategy extraPaletteStrategy
) {

    public static Builder of(Identifier res) {
        return new Builder(res);
    }

    public static Builder of(Identifier res, Identifier mask) {
        return new Builder(res).mask(mask);
    }

    public static Builder of(Identifier res, Identifier mask, Identifier overlay) {
        return new Builder(res).mask(mask).overlay(overlay);
    }

    public static <T extends BlockType> Builder of(Identifier res, String customTexturePath) {
        return new Builder(res).customTexture(customTexturePath);
    }

    public static class Builder {
        private final Identifier texture;
        private Identifier mask;
        private Identifier extraMask;
        private Identifier overlay;
        private boolean keepNamespace = false;
        private boolean copyTexture = false;
        private boolean noAnimation = false;
        private boolean autoMask = false;
        private boolean onAtlas;
        private String customTexturePath;
        private Pair<String, String> replacePath;
        private PaletteStrategy palette = PaletteStrategies.MAIN_CHILD;
        private PaletteStrategy extraPalette = PaletteStrategies.LOG_SIDE_STANDARD;

        public Builder(Identifier texture) {
            this.texture = texture;
            this.onAtlas = !texture.getPath().startsWith("entity/");
        }

        public Builder mask(Identifier mask) {
            this.mask = mask;
            return this;
        }

        public Builder extraMask(Identifier extraMask) {
            this.extraMask = extraMask;
            return this;
        }

        public Builder overlay(Identifier overlay) {
            this.overlay = overlay;
            return this;
        }

        // for textures not on atlas that won't be cleared
        public Builder forEntityOrGui() {
            this.onAtlas = false;
            return this;
        }

        public Builder keepNamespace() {
            this.keepNamespace = true;
            return this;
        }

        public Builder copyTexture() {
            this.copyTexture = true;
            return this;
        }

        // Ensure that generated textures aren't animated texture
        public Builder noAnimation() {
            this.noAnimation = true;
            return this;
        }

        public Builder customTexture(String customTexturePath) {
            this.customTexturePath = customTexturePath;
            return this;
        }

        public Builder replacePath(String oldChar, String newChar) {
            this.replacePath = Pair.of(oldChar, newChar);
            return this;
        }

        // Masks with colors of the block type default block main texture
        public Builder autoMask() {
            this.autoMask = true;
            return this;
        }

        //a bit of abuse of type here, should be PaletteStrategy but i want to enforce them being cached
        public Builder setPalette(PaletteStrategy paletteProvider) {
            this.palette = paletteProvider;
            return this;
        }
        //a bit of abuse of type here, should be PaletteStrategy but i want to enforce them being cached
        public Builder setExtraPalette(PaletteStrategy extraPaletteProvider) {
            this.extraPalette = extraPaletteProvider;
            return this;
        }


        public TextureInfo build() {
            return new TextureInfo(texture, mask, extraMask, overlay, keepNamespace,
                    copyTexture, noAnimation, customTexturePath, replacePath, autoMask, onAtlas,
                    palette, extraPalette);
        }
    }

}
