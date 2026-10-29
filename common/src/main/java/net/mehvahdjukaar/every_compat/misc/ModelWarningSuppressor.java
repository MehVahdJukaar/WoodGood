package net.mehvahdjukaar.every_compat.misc;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.minecraft.resources.ResourceLocation;

import java.util.HashSet;
import java.util.Set;

public class ModelWarningSuppressor {

    public static int missingTextures;
    public static final Set<ResourceLocation> MISSING_VARIANT_BLOCKSTATES = new HashSet<>();
    public static final Set<ResourceLocation> UNLOADABLE_MODELS = new HashSet<>();

    public static void reset() {
        missingTextures = 0;
        MISSING_VARIANT_BLOCKSTATES.clear();
        UNLOADABLE_MODELS.clear();
    }

    public static void report() {
        if (missingTextures > 0 && !PlatHelper.isDev()) {
            EveryCompat.LOGGER.warn("Suppressed 'missing textures in model' warnings for {} of our generated models " +
                    "(they reference textures not provided by the target mod; vanilla texture is used as fallback).",
                    missingTextures);
        }
        if (!MISSING_VARIANT_BLOCKSTATES.isEmpty()) {
            EveryCompat.LOGGER.error("{} of our blockstates have states with no model (blockstate json missing or pointing to models that were never generated). First few: {}",
                    MISSING_VARIANT_BLOCKSTATES.size(), MISSING_VARIANT_BLOCKSTATES.stream().limit(8).toList());
        }
        if (!UNLOADABLE_MODELS.isEmpty()) {
            EveryCompat.LOGGER.error("{} of our models could not be loaded (json missing or broken). First few: {}",
                    UNLOADABLE_MODELS.size(), UNLOADABLE_MODELS.stream().limit(8).toList());
        }
    }
}
