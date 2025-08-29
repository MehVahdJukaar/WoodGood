package net.mehvahdjukaar.every_compat.api;

import com.mojang.datafixers.util.Pair;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public record TextureInfo(ResourceLocation texture, @Nullable ResourceLocation mask,
                                               boolean keepNamespace, boolean copyTexture, String customTexturePath,
                                               Pair<String, String> replacePath,
                                               boolean autoMask, boolean onAtlas, PaletteStrategy paletteStrategy) {

    public static Builder of(ResourceLocation res) {
        return new Builder(res);
    }

    public static Builder of(ResourceLocation res, ResourceLocation mask) {
        return new Builder(res).mask(mask);
    }

    public static <T extends BlockType> Builder of(ResourceLocation res, String customTexturePath) {
        return new Builder(res).customTexture(customTexturePath);
    }

    //remove once you remove the rest of palette madness
    @Deprecated(forRemoval = true)
    public TextureInfo cloneWithPalette(PaletteStrategy newPalette) {
        return new TextureInfo(this.texture, this.mask, this.keepNamespace, this.copyTexture, this.customTexturePath,
                replacePath, this.autoMask, this.onAtlas, newPalette);
    }

    public static class Builder {
        private final ResourceLocation texture;
        private ResourceLocation mask;
        private boolean keepNamespace = false;
        private boolean copyTexture = false;
        private boolean autoMask = false;
        private boolean onAtlas;
        private String customTexturePath;
        private Pair<String, String> replacePath;
        private PaletteStrategy palette = PaletteStrategies.MAIN_CHILD;

        public Builder(ResourceLocation texture) {
            this.texture = texture;
            this.onAtlas = !texture.getPath().startsWith("entity/");
        }

        public Builder mask(ResourceLocation mask) {
            this.mask = mask;
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


        public TextureInfo build() {
            return new TextureInfo(texture, mask, keepNamespace,
                    copyTexture, customTexturePath, replacePath, autoMask, onAtlas, palette);
        }
    }

}
