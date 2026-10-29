package net.mehvahdjukaar.every_compat.misc;

import net.mehvahdjukaar.moonlight.api.client.TextureCache;
import net.minecraft.resources.ResourceLocation;

// ┌──────────────────────────────────────────────────────────┐
// │ Used by addons like StoneZone or GemsRealms, so their    │
// │ textures can be carried over to EveryCompat to feed into │
// │ "findFirstBlockTextureLocation()"                        │
// │ See EveryCompat's SpriteHelper for details               │
// └──────────────────────────────────────────────────────────┘
@Deprecated(forRemoval = true) //use TextureCache.registerSpecialTextureForBlock with the block id
public record SpriteExtra(String blockId, String textureKey, String resLocTexture) {

    @SuppressWarnings("unused") // Being used by StoneZone & GemRealms
    public static SpriteExtra addOptional(String blockId, String textureKey, String resLocTexture) {
        return new Builder(blockId, textureKey, resLocTexture).build();
    }

    public static final class Builder {

        private final String blockId;
        private final String textureKey;
        private final String resLocTexture;

        public Builder(String blockId, String textureKey, String resLocTexture) {
            this.blockId = blockId;
            this.textureKey = textureKey;
            this.resLocTexture = resLocTexture;
        }

        public SpriteExtra build() {
            TextureCache.registerSpecialTextureForBlock(ResourceLocation.parse(blockId), textureKey, ResourceLocation.parse(resLocTexture));
            return new SpriteExtra(blockId, textureKey, resLocTexture);
        }
    }

}
