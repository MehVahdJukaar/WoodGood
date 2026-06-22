package net.mehvahdjukaar.every_compat.misc;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.concurrent.Callable;

/**
 * Collects resource-generation failures during a single generation pass.
 * Logs each distinct cause once, then summarizes repeated occurrences.
 */
public final class TaskRunnerWithFaliureCollection {

    private static final ThreadLocal<TaskRunnerWithFaliureCollection> ACTIVE = new ThreadLocal<>();
    private static final TaskRunnerWithFaliureCollection DUMMY = new TaskRunnerWithFaliureCollection(true);

    private final boolean disabled;
    private final Map<String, FailureGroup> groups = new LinkedHashMap<>();

    private TaskRunnerWithFaliureCollection() {
        this(false);
    }

    private TaskRunnerWithFaliureCollection(boolean disabled) {
        this.disabled = disabled;
    }

    public static TaskRunnerWithFaliureCollection active() {
        TaskRunnerWithFaliureCollection collector = ACTIVE.get();
        return collector != null ? collector : DUMMY;
    }

    public static void run(String passName, Runnable action) {
        TaskRunnerWithFaliureCollection collector = new TaskRunnerWithFaliureCollection();
        ACTIVE.set(collector);
        try {
            action.run();
        } finally {
            collector.finish(passName);
            ACTIVE.remove();
        }
    }

    public void record(String context, Supplier<String> item, Throwable error) {
        if (disabled) {
            EveryCompat.LOGGER.error("[{}] {}: {}", context, item.get(), error.getMessage(), error);
            if (PlatHelper.isDev()) {
                throw error instanceof RuntimeException re ? re : new RuntimeException(error);
            }
            return;
        }

        String key = context + "|" + rootMessage(error);
        FailureGroup group = groups.computeIfAbsent(key, k -> new FailureGroup(context, error));
        group.count++;
        if (group.samples.size() < 3) {
            group.samples.add(item.get());
        }

        if (group.count == 1) {
            EveryCompat.LOGGER.error("[{}] {}: {}", context, item.get(), error.getMessage(), error);
            if (PlatHelper.isDev()) {
                throw error instanceof RuntimeException re ? re : new RuntimeException(error);
            }
        }
    }

    public void runSafely(String context, Supplier<String> item, Runnable action) {
        runSafely(context, item, () -> {
            action.run();
            return null;
        });
    }

    public void runSafely(String context, Supplier<String> item, Callable<Void> action) {
        try {
            action.call();
        } catch (Exception e) {
            record(context, item, e);
        }
    }

    private void finish(String passName) {
        if (disabled || groups.isEmpty()) {
            return;
        }

        int total = 0;
        for (FailureGroup group : groups.values()) {
            total += group.count;
            if (group.count > 1) {
                EveryCompat.LOGGER.warn(
                        "[{}] {} additional failures with the same cause ({}). Examples: {}",
                        group.context,
                        group.count - 1,
                        rootMessage(group.error),
                        String.join(", ", group.samples)
                );
            }
        }

        EveryCompat.LOGGER.warn(
                "{} completed with {} failure(s) across {} distinct cause(s)",
                passName,
                total,
                groups.size()
        );
    }

    private static String rootMessage(Throwable error) {
        Throwable current = error;
        while (current.getCause() != null && current.getCause() != current) {
            current = current.getCause();
        }
        String message = current.getMessage();
        return current.getClass().getSimpleName() + (message == null || message.isBlank() ? "" : ": " + message);
    }

    private static final class FailureGroup {
        private final String context;
        private final Throwable error;
        private int count;
        private final List<String> samples = new ArrayList<>();

        private FailureGroup(String context, Throwable error) {
            this.context = context;
            this.error = error;
        }
    }
}
