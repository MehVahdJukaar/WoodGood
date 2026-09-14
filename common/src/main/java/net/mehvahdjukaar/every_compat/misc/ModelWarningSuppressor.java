package net.mehvahdjukaar.every_compat.misc;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.minecraft.resources.ResourceLocation;

import java.util.HashSet;
import java.util.Set;

public class ModelWarningSuppressor {

    public static int missingTextures;
    public static final Set<ResourceLocation> missingVariantBlockstates = new HashSet<>();
    public static final Set<ResourceLocation> unloadableModels = new HashSet<>();

    public static void reset() {
        missingTextures = 0;
        missingVariantBlockstates.clear();
        unloadableModels.clear();
    }

    public static void report() {
        if (missingTextures > 0) {
            EveryCompat.LOGGER.info("Suppressed 'missing textures in model' warnings for {} of our generated models " +
                    "(they reference textures not provided by the target mod; vanilla missing texture is used as fallback).",
                    missingTextures);
        }
        if (!missingVariantBlockstates.isEmpty()) {
            EveryCompat.LOGGER.error("{} of our blockstates have states with no model (blockstate json missing or pointing to models that were never generated). First few: {}",
                    missingVariantBlockstates.size(), missingVariantBlockstates.stream().limit(5).toList());
        }
        if (!unloadableModels.isEmpty()) {
            EveryCompat.LOGGER.error("{} of our models could not be loaded (json missing or broken). First few: {}",
                    unloadableModels.size(), unloadableModels.stream().limit(5).toList());
        }
    }
}
