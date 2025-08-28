package net.lewmc.kryptonite.config;

import net.lewmc.kryptonite.Kryptonite;
import net.lewmc.kryptonite.utils.config.BooleanConfigItem;
import net.lewmc.kryptonite.utils.config.ConfigCollection;
import net.lewmc.kryptonite.utils.config.GenericConfigItem;
import net.lewmc.kryptonite.utils.config.IntegerConfigItem;

import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * Configuration data for leaf-global.yml
 * @since 2.1.0
 */
public class LeafConfig extends ConfigCollection {
    /**
     * Holds configuration data for the server.properties file.
     */
    public HashMap<String, GenericConfigItem> values = new HashMap<>();

    /**
     * Constructs the server.properties data.
     * @param plugin Kryptonite - Reference to the main Kryptonite class.
     */
    public LeafConfig(Kryptonite plugin) {
        String file = File.separator + "config" + File.separator + "leaf-global.yml";

        values.put("async.parallel-world-tracing.enabled", new BooleanConfigItem(
                file,
                "async.parallel-world-tracing.enabled",
                "Parallel World Tracing",
                        List.of(
                        "Parallel processing different worlds in separate",
                        "threads. Experimental feature, potentially unstable",
                        "Only use if you experience specific bottlenecks and",
                        "understand the risks. Learn more at leafmc.one/docs"),
                false,
                plugin
        ));

        values.put("async.parallel-world-tracing.threads", new IntegerConfigItem(
                file,
                "async.parallel-world-tracing.threads",
                "Parallel World Tracing Threads",
                List.of("Number of threads dedicated to parallel world",
                        "ticking. Consider setting based on amount of worlds",
                        "in the server."
                ),
                1,
                100,
                null,
                plugin
        ));

        values.put("async.parallel-world-tracing.log-container-creation-stacktraces", new BooleanConfigItem(
                file,
                "async.parallel-world-tracing.log-container-creation-stacktraces",
                "Parallel World Tracing Log Container Creation Stacktraces",
                List.of(
                        "Log stacktraces when containers (like Tile Entities",
                        "or Entities) are created during parallel ticking.",
                        "Useful for debugging potential concurrency issues."),
                false,
                plugin
        ));

        values.put("async.parallel-world-tracing.disable-hard-throw", new BooleanConfigItem(
                file,
                "async.parallel-world-tracing.disable-hard-throw",
                "Parallel World Tracing Disable Hard Throw",
                List.of(
                        "Disable hard throws (which usually stop the server)",
                        "related to parallel ticking errors.  Might mask",
                        "underlying issues but could prevent crashes in",
                        "unstable experimental phases. Use with caution."),
                false,
                plugin
        ));

        values.put("async.parallel-world-tracing.run-async-tasks-sync", new BooleanConfigItem(
                file,
                "async.parallel-world-tracing.run-async-tasks-sync",
                "Parallel World Tracing Run Async Tasks Sync",
                List.of(
                        "Run asynchronous tasks synchronously within the",
                        "parallel ticking system. Might be needed for",
                        "compatibility with certain plugins but largely",
                        "negates the performance benefits of parallel ticking."),
                false,
                plugin
        ));
    }
}