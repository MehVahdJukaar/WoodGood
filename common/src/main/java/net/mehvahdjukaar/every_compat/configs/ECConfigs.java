package net.mehvahdjukaar.every_compat.configs;

import com.google.common.base.Stopwatch;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.platform.configs.ConfigBuilder;
import net.mehvahdjukaar.moonlight.api.platform.configs.ConfigType;
import net.mehvahdjukaar.moonlight.api.platform.configs.ModConfigHolder;
import net.mehvahdjukaar.moonlight.api.resources.pack.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.IoSupplier;
import org.jetbrains.annotations.Nullable;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Supplier;

//loaded after registry
public class ECConfigs {

    public enum GenMode {
        NEVER,
        CACHED,
        CACHED_ZIPPED,
        ALWAYS;

        public PackGenerationStrategy pickStrategy() {
            return switch (this) {
                case NEVER -> PackGenerationStrategy.NO_OP;
                case CACHED -> PackGenerationStrategy.CACHED;
                case CACHED_ZIPPED -> new GlobalCachedStrategy(){
                    @Override
                    public IEditablePackResources createPackResources(PackLocationInfo info, PackType type) {
                        return new Cached(info, type, this.getPath(type).resolve(info.id().replace(":", "-")));
                    }
                };
                case ALWAYS -> new PackGenerationStrategy() {
                    @Override
                    public boolean needsRegeneration(PackType packType) {
                        return true;
                    }

                    @Override
                    public IEditablePackResources createPackResources(PackLocationInfo packLocationInfo, PackType packType) {
                        return new InMem(packLocationInfo, packType);
                    }
                };
            };
        }
    }

    public static ModConfigHolder SPEC;
    public static ModConfigHolder CLIENT_SPEC;

    public static final Supplier<Boolean> TAB_ENABLED;
    public static final Supplier<Boolean> TAB_ITEM_SEARCH_ENABLED;
    public static final Supplier<Boolean> CHECK_PACKET;
    public static final Supplier<Boolean> DEBUG_PACKET;
    public static final Supplier<Boolean> BLOCK_TYPE_TOOLTIP;
    public static final Supplier<Boolean> MOD_TOOPTIP;
    public static final Supplier<Boolean> TOOLTIPS_ADVANCED;
    public static final Supplier<GenMode> SERVER_GENERATION_MODE;
    public static final Supplier<GenMode> CLIENT_GENERATION_MODE;


    static {

        if (PlatHelper.getPhysicalSide().isClient()) {
            ConfigBuilder builder = ConfigBuilder.create(EveryCompat.MOD_ID, ConfigType.CLIENT);

            builder.push("general");
            CLIENT_GENERATION_MODE = builder.comment("How assets are generated. If cached the cache will regenerate once any mod or pack changes")
                    .define("dynamic_assets_generation_mode", GenMode.CACHED_ZIPPED);
            builder.pop();

            CLIENT_SPEC = builder.build();
            CLIENT_SPEC.forceLoad(); //manually load early
        } else {
            CLIENT_GENERATION_MODE = () -> GenMode.ALWAYS;
        }

        ConfigBuilder builder = ConfigBuilder.create(EveryCompat.MOD_ID, ConfigType.COMMON);

        builder.push("general");
        SERVER_GENERATION_MODE = builder.comment("How assets are generated. If cached the cache will regenerate once any mod or pack changes")
                .define("dynamic_assets_generation_mode", GenMode.CACHED_ZIPPED);
        TAB_ENABLED = builder.comment("Puts all the added items into a new Every Compat tab instead of their own mod tabs. Be warned that if disabled it could cause some issue with some mods that have custom tabs")
                .define("creative_tab", true);
        TAB_ITEM_SEARCH_ENABLED = builder.comment("Allow the item_search or searchBar to be visible.")
                .define("tab_item_search", true);
        // REMAP_COMPAT = builder.comment("Allows the mod to try to remap and convert other blocks and items from other compat mods that have been uninstalled from one world. This was made so one can uninstall such mods seamlessly having their blocks converted into Evety Compat counterparts")
        //        .define("remap_other_mods", false);
        // REMAP_OWN = builder.comment("Clears out and remaps all blocks registered by this mod belonging to uninstalled wood types to air or oak wood")
        //         .define("remap_self", true);
        CHECK_PACKET = builder.comment("Sends a packet to verify all dependencies mod versions are the same on connect. DIsable if it causes issues")
                .define("mod_version_check_packet", true);
        DEBUG_PACKET = builder.comment("Don't touch unless you are told to").define("debug_packet", false);

        builder.push("tooltips");
        MOD_TOOPTIP = builder.comment("Enabled tooltips showing which mod an EC item is from")
                .define("mod_origin_enabled", true);
        BLOCK_TYPE_TOOLTIP = builder.comment("Enabled tooltips showing which block type an EC item is made from")
                .define("block_type_enabled", true);
        TOOLTIPS_ADVANCED = builder.comment("Only show on advanced settings")
                .define("show_on_advanced_tooltips", false);

        builder.pop();


        SPEC = builder.build();

        SPEC.forceLoad();
    }

    public static void init() {
    }

    public static final ConcurrentTimer watch = new ConcurrentTimer();

    private static final class Cached extends CacheZipPackResources {
        public Cached(PackLocationInfo location, PackType type, Path path) {
            super(location, type, path);
        }

        @Override
        public IoSupplier<InputStream> getResource(PackType type, ResourceLocation id) {
            try (var t = watch.time()) { // time is added even if an exception occurs
                return super.getResource(type, id);
            }
        }

        @Override
        public void listResources(PackType packType, String namespace, String id, ResourceOutput output) {
            try (var t = watch.time()) {
                super.listResources(packType, namespace, id, output);
            }
        }

        @Override
        public @Nullable IoSupplier<InputStream> getRootResource(String... strings) {
            try (var t = watch.time()) {
                return super.getRootResource(strings);
            }
        }
    }

    private static final class InMem extends InMemoryPackResources {

        protected InMem(PackLocationInfo info, PackType type) {
            super(info, type);
        }

        protected InMem(PackLocationInfo info, PackType type, boolean hidden) {
            super(info, type, hidden);
        }

        @Override
        public IoSupplier<InputStream> getResource(PackType type, ResourceLocation id) {
            try (var t = watch.time()) { // time is added even if an exception occurs
                return super.getResource(type, id);
            }
        }

        @Override
        public void listResources(PackType packType, String namespace, String id, ResourceOutput output) {
            try (var t = watch.time()) {
                super.listResources(packType, namespace, id, output);
            }
        }

        @Override
        public @Nullable IoSupplier<InputStream> getRootResource(String... strings) {
            try (var t = watch.time()) {
                return super.getRootResource(strings);
            }
        }
    }


    public static final class ConcurrentTimer {
        private final LongAdder nanos = new LongAdder();

        /**
         * Start a scoped timing; call close() (prefer try-with-resources).
         */
        public Timing time() {
            return new Timing(this);
        }

        public long elapsedNanos() {
            return nanos.sum();
        }

        public double elapsedMillis() {
            return nanos.sum() / 1_000_000.0;
        }

        private void add(long deltaNanos) {
            nanos.add(deltaNanos);
        }

        public double elapsedSeconds() {
            return nanos.sum() / 1_000_000_000.0;
        }

        @Override
        public String toString() {
            return String.format("Elapsed: %.3f ms (%.6f s)", elapsedMillis(), elapsedSeconds());
        }

        public static final class Timing implements AutoCloseable {
            private final ConcurrentTimer owner;
            private final long start = System.nanoTime();
            private boolean closed;

            private Timing(ConcurrentTimer owner) {
                this.owner = owner;
            }

            @Override
            public void close() {
                if (!closed) {
                    owner.add(System.nanoTime() - start);
                    closed = true;
                }
            }
        }
    }
}
