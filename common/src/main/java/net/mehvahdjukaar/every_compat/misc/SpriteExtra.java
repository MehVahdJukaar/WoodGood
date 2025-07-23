package net.mehvahdjukaar.every_compat.misc;

import static net.mehvahdjukaar.every_compat.misc.SpriteHelper.spriteExtraSet;

// ┌──────────────────────────────────────────────────────────┐
// │ Used by addons like StoneZone or GemsRealms, so their    │
// │ textures can be carried over to EveryCompat to feed into │
// │ "findFirstBlockTextureLocation()"                        │
// │ See EveryCompat's SpriteHelper for details               │
// └──────────────────────────────────────────────────────────┘
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
            var info = new SpriteExtra(blockId, textureKey, resLocTexture);
            spriteExtraSet.add(info);
            return info;
        }
    }

}
