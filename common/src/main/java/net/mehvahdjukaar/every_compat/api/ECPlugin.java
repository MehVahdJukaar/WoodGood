package net.mehvahdjukaar.every_compat.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks an {@link EveryCompatPlugin} on NeoForge
 * <br>
 * Fabric doesn't use this, use the "everycomp" entrypoint instead
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ECPlugin {

}
