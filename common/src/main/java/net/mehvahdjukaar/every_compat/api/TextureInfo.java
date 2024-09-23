package net.mehvahdjukaar.every_compat.api;

import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public record TextureInfo(ResourceLocation texture, @Nullable ResourceLocation mask,
                          boolean keepNamespace, boolean copyTexture, boolean autoMask,
                          boolean onAtlas) {
    public static Builder of(ResourceLocation res) {
        return new Builder(res);
    }

    public static final class Builder {
        private final ResourceLocation texture;
        private ResourceLocation mask;
        private boolean keepNamespace = false;
        private boolean copyTexture = false;
        private boolean autoMask = false;
        private boolean onAtlas = true;

        public Builder(ResourceLocation texture) {
            this.texture = texture;
            this.onAtlas = !texture.getPath().startsWith("entity/");
        }

        public Builder mask(ResourceLocation mask) {
            this.mask = mask;
            return this;
        }

        // for textures not on atlas that won't be cleared
        public Builder forEntityOrGui(){
            this.onAtlas = false;
            return this;
        }

        public Builder keepNamespace() {
            this.keepNamespace = true;
            return this;
        }

        public Builder copyTexture() {
            this.copyTexture = false;
            return this;
        }

        public Builder autoMask() {
            this.autoMask = true;
            return this;
        }

        public TextureInfo build() {
            return new TextureInfo(texture, mask, keepNamespace,
                    copyTexture, autoMask, onAtlas);
        }
    }
}
