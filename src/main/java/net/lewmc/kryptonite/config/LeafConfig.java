package net.lewmc.kryptonite.config;

import net.lewmc.kryptonite.Kryptonite;
import net.lewmc.kryptonite.utils.config.BooleanConfigItem;
import net.lewmc.kryptonite.utils.config.ConfigCollection;
import net.lewmc.kryptonite.utils.config.IntegerConfigItem;

import java.io.File;
import java.util.List;

/**
 * Configuration data for leaf-global.yml
 * @since 2.1.0
 */
public class LeafConfig extends ConfigCollection {
    /**
     * Constructs the server.properties data.
     * @param plugin Kryptonite - Reference to the main Kryptonite class.
     */
    public LeafConfig(Kryptonite plugin) {
        String file = "config" + File.separator + "leaf-global.yml";

        values.put("async.parallel-world-ticking.enabled", new BooleanConfigItem(
                file,
                "async.parallel-world-ticking.enabled",
                "Parallel World Tracing",
                        List.of(
                        "Parallel processing different worlds in separate",
                        "threads. Experimental feature, potentially unstable",
                        "Only use if you experience specific bottlenecks and",
                        "understand the risks. Learn more at leafmc.one/docs"),
                true,
                false,
                plugin
        ));

        values.put("async.parallel-world-ticking.threads", new IntegerConfigItem(
                file,
                "async.parallel-world-ticking.threads",
                "Parallel World Tracing Threads",
                List.of("Number of threads dedicated to parallel world",
                        "ticking. Consider setting based on amount of worlds",
                        "in the server."
                ),
                (Boolean) values.get("async.parallel-world-ticking.enabled").getValue(),
                1,
                100,
                null,
                plugin
        ));

        values.put("async.parallel-world-ticking.log-container-creation-stacktraces", new BooleanConfigItem(
                file,
                "async.parallel-world-ticking.log-container-creation-stacktraces",
                "Parallel World Tracing Log Container Creation Stacktraces",
                List.of(
                        "Log stacktraces when containers (like Tile Entities",
                        "or Entities) are created during parallel ticking.",
                        "Useful for debugging potential concurrency issues."),
                (Boolean) values.get("async.parallel-world-ticking.enabled").getValue(),
                false,
                plugin
        ));

        values.put("async.parallel-world-ticking.disable-hard-throw", new BooleanConfigItem(
                file,
                "async.parallel-world-ticking.disable-hard-throw",
                "Parallel World Tracing Disable Hard Throw",
                List.of(
                        "Disable hard throws (which usually stop the server)",
                        "related to parallel ticking errors.  Might mask",
                        "underlying issues but could prevent crashes in",
                        "unstable experimental phases. Use with caution."),
                (Boolean) values.get("async.parallel-world-ticking.enabled").getValue(),
                false,
                plugin
        ));

        values.put("async.parallel-world-ticking.run-async-tasks-sync", new BooleanConfigItem(
                file,
                "async.parallel-world-ticking.run-async-tasks-sync",
                "Parallel World Tracing Run Async Tasks Sync",
                List.of(
                        "Run asynchronous tasks synchronously within the",
                        "parallel ticking system. Might be needed for",
                        "compatibility with certain plugins but largely",
                        "negates the performance benefits of parallel ticking."),
                (Boolean) values.get("async.parallel-world-ticking.enabled").getValue(),
                false,
                plugin
        ));

        values.put("async.async-entity-tracker.enabled", new BooleanConfigItem(
                file,
                "async.async-entity-tracker.enabled",
                "Async Entity Tracker",
                List.of(
                        "Make entity tracking asynchronous, can improve",
                        "performance significantly, especially in situations",
                        "with massive numbers of entities in a small area.",
                        "If using NPC plugins, enable compat-mode as well."),
                true,
                true,
                plugin
        ));

        values.put("async.async-entity-tracker.compat-mode", new BooleanConfigItem(
                file,
                "async.async-entity-tracker.compat-mode",
                "Async Entity Tracker Compat Mode",
                List.of(
                        "Enable compatibility mode for plugins like",
                        "Citizens or other NPC plugins that use real, player",
                        "type entities. You should enable compat-mode ONLY",
                        "IF you have installed Citizens or similar real",
                        "entity NPC plugins and are experiencing issues."),
                (Boolean) values.get("async.async-entity-tracker.enabled").getValue(),
                null,
                plugin
        ));

        values.put("async.async-entity-tracker.max-threads", new IntegerConfigItem(
                file,
                "async.async-entity-tracker.max-threads",
                "Async Entity Tracker Max Threads",
                List.of("Maximum number of threads for the async entity",
                        "tracker to use. When set to 0, 1/4 of available",
                        "CPU cores are used. Recommended to set to 1/2,",
                        "of cores depending on server load and core count."
                ),
                (Boolean) values.get("async.async-entity-tracker.enabled").getValue(),
                0,
                100,
                null,
                plugin
        ));

        values.put("async.async-entity-tracker.keepalive", new IntegerConfigItem(
                file,
                "async.async-entity-tracker.keepalive",
                "Async Entity Tracker Keepalive",
                List.of("Thread keepalive time. Threads with no tasks",
                        "will be terminated if they remain idle for",
                        "this duration. Measured in seconds longer than."),
                (Boolean) values.get("async.async-entity-tracker.enabled").getValue(),
                1,
                120,
                "50-70",
                plugin
        ));

        values.put("async.async-entity-tracker.queue-size", new IntegerConfigItem(
                file,
                "async.async-entity-tracker.queue-size",
                "Async Entity Tracker Queue Size",
                List.of("Maximum size of the queue for pending entity",
                        "tracking tasks. If set to 0, the queue size is",
                        "dynamically calculated as max-threads * 384.",
                        "A limit might prevent excessive memory usage",
                        "under extreme load but could potentially lead",
                        "to tasks being dropped or delayed"),
                (Boolean) values.get("async.async-entity-tracker.enabled").getValue(),
                0,
                500,
                null,
                plugin
        ));

        values.put("async.async-target-finding", new BooleanConfigItem(
                file,
                "async.async-target-finding",
                "Async Target Finding",
                List.of(
                        "Moves the expensive entity target search",
                        "calculations (finding nearby entities to attack or",
                        "interact with) to a background thread"),
                true,
                true,
                plugin
        ));

        values.put("async.async-playerdata-save", new BooleanConfigItem(
                file,
                "async.async-playerdata-save",
                "Async Playerdata Save",
                List.of(
                        "Make playerdata saving aynchronous. Warning: might",
                        "cause data loss in some circumstances - use with",
                        "extreme caution and ensure robust backups!"),
                true,
                false,
                plugin
        ));

        values.put("async.async-pathfinding.enabled", new BooleanConfigItem(
                file,
                "async.async-pathfinding.enabled",
                "Async Pathfinding",
                List.of(
                        "Make mob pathfinding calculations asynchronous."),
                true,
                true,
                plugin
        ));
    }
}