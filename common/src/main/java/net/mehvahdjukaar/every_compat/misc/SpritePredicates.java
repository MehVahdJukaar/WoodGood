package net.mehvahdjukaar.every_compat.misc;

import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

// Used to identify textures "types" only based off their name.
// feed into "findFirstBlockTextureLocation()"
public class SpritePredicates {

    public static final @NotNull Predicate<String> LOOKS_LIKE_TOP_LOG_TEXTURE = (s) -> {
        s = (new ResourceLocation(s)).getPath();
        return s.contains("_top") || s.contains("_end") || s.contains("_up");
    };
    public static final @NotNull Predicate<String> LOOKS_LIKE_SIDE_LOG_TEXTURE = (s) -> {
        return !LOOKS_LIKE_TOP_LOG_TEXTURE.test(s);
    };
    public static final @NotNull Predicate<String> LOOKS_LIKE_LEAF_TEXTURE = (s) -> {
        s = (new ResourceLocation(s)).getPath();
        return !s.contains("_bushy") && !s.contains("_snow") && !s.contains("_overlay");
    };

}
