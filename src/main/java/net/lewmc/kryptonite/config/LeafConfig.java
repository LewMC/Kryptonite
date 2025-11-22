package net.lewmc.kryptonite.config;

import net.lewmc.kryptonite.Kryptonite;
import net.lewmc.kryptonite.utils.config.*;

import java.io.File;
import java.util.List;

/**
 * Configuration data for leaf-global.yml
 * @since 2.1.0
 */
public class LeafConfig extends ConfigCollection {
    /**
     * Constructs the leaf-global.yml data.
     * @param plugin Kryptonite - Reference to the main Kryptonite class.
     */
    public LeafConfig(Kryptonite plugin) {
        String file = "config" + File.separator + "leaf-global.yml";

        values.put(Key.ASYNC_PARALLEL_WORLD_TICKING_ENABLED.toString(), new BooleanConfigItem(
                file,
                Key.ASYNC_PARALLEL_WORLD_TICKING_ENABLED.toString(),
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

        values.put(Key.ASYNC_PARALLEL_WORLD_TICKING_THREADS.toString(), new IntegerConfigItem(
                file,
                Key.ASYNC_PARALLEL_WORLD_TICKING_THREADS.toString(),
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

        values.put(Key.ASYNC_PARALLEL_WORLD_TICKING_LOG_CONTAINER_CREATION_STACKTRACES.toString(), new BooleanConfigItem(
                file,
                Key.ASYNC_PARALLEL_WORLD_TICKING_LOG_CONTAINER_CREATION_STACKTRACES.toString(),
                "Parallel World Tracing Log Container Creation Stacktraces",
                List.of(
                        "Log stacktraces when containers (like Tile Entities",
                        "or Entities) are created during parallel ticking.",
                        "Useful for debugging potential concurrency issues."),
                (Boolean) values.get("async.parallel-world-ticking.enabled").getValue(),
                false,
                plugin
        ));

        values.put(Key.ASYNC_PARALLEL_WORLD_TICKING_DISABLE_HARD_THROW.toString(), new BooleanConfigItem(
                file,
                Key.ASYNC_PARALLEL_WORLD_TICKING_DISABLE_HARD_THROW.toString(),
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

        values.put(Key.ASYNC_PARALLEL_WORLD_TICKING_RUN_ASYNC_TASKS_SYNC.toString(), new BooleanConfigItem(
                file,
                Key.ASYNC_PARALLEL_WORLD_TICKING_RUN_ASYNC_TASKS_SYNC.toString(),
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

        values.put(Key.ASYNC_ENTITY_TRACKER_ENABLED.toString(), new BooleanConfigItem(
                file,
                Key.ASYNC_ENTITY_TRACKER_ENABLED.toString(),
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

        values.put(Key.ASYNC_ENTITY_TRACKER_COMPAT_MODE.toString(), new BooleanConfigItem(
                file,
                Key.ASYNC_ENTITY_TRACKER_COMPAT_MODE.toString(),
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

        values.put(Key.ASYNC_ENTITY_TRACKER_MAX_THREADS.toString(), new IntegerConfigItem(
                file,
                Key.ASYNC_ENTITY_TRACKER_MAX_THREADS.toString(),
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

        values.put(Key.ASYNC_ENTITY_TRACKER_KEEPALIVE.toString(), new IntegerConfigItem(
                file,
                Key.ASYNC_ENTITY_TRACKER_KEEPALIVE.toString(),
                "Async Entity Tracker Keepalive",
                List.of("Thread keepalive time. Threads with no tasks",
                        "will be terminated if they remain idle for",
                        "this duration. Measured in seconds."),
                (Boolean) values.get("async.async-entity-tracker.enabled").getValue(),
                1,
                120,
                "50-70",
                plugin
        ));

        values.put(Key.ASYNC_ENTITY_TRACKER_QUEUE_SIZE.toString(), new IntegerConfigItem(
                file,
                Key.ASYNC_ENTITY_TRACKER_QUEUE_SIZE.toString(),
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

        values.put(Key.ASYNC_TARGET_FINDING.toString(), new BooleanConfigItem(
                file,
                Key.ASYNC_TARGET_FINDING.toString(),
                "Async Target Finding",
                List.of(
                        "Moves the expensive entity target search",
                        "calculations (finding nearby entities to attack or",
                        "interact with) to a background thread"),
                true,
                true,
                plugin
        ));

        values.put(Key.ASYNC_PLAYERDATA_SAVE.toString(), new BooleanConfigItem(
                file,
                Key.ASYNC_PLAYERDATA_SAVE.toString(),
                "Async Playerdata Save",
                List.of(
                        "Make playerdata saving aynchronous. Warning: might",
                        "cause data loss in some circumstances - use with",
                        "extreme caution and ensure robust backups!"),
                true,
                false,
                plugin
        ));

        values.put(Key.ASYNC_PATHFINDING_ENABLED.toString(), new BooleanConfigItem(
                file,
                Key.ASYNC_PATHFINDING_ENABLED.toString(),
                "Async Pathfinding",
                List.of(
                        "Make mob pathfinding calculations asynchronous."),
                true,
                true,
                plugin
        ));

        values.put(Key.ASYNC_PATHFINDING_MAX_THREADS.toString(), new IntegerConfigItem(
                file,
                Key.ASYNC_PATHFINDING_MAX_THREADS.toString(),
                "Async Pathfinding Max Threads",
                List.of("Maximum number of threads for async entity",
                        "pathfinding to use. When set to 0, 1/4 of available",
                        "CPU cores are used. Recommended to set to 1/3, of",
                        "cores depending on server load and core count."
                ),
                (Boolean) values.get("async.async-pathfinding.enabled").getValue(),
                0,
                100,
                null,
                plugin
        ));

        values.put(Key.ASYNC_PATHFINDING_KEEPALIVE.toString(), new IntegerConfigItem(
                file,
                Key.ASYNC_PATHFINDING_KEEPALIVE.toString(),
                "Async Pathfinding Keepalive",
                List.of("Thread keepalive time. Threads with no tasks",
                        "will be terminated if they remain idle for",
                        "this duration. Measured in seconds."),
                (Boolean) values.get("async.async-pathfinding.enabled").getValue(),
                1,
                120,
                "50-70",
                plugin
        ));

        values.put(Key.ASYNC_PATHFINDING_QUEUE_SIZE.toString(), new IntegerConfigItem(
                file,
                Key.ASYNC_PATHFINDING_QUEUE_SIZE.toString(),
                "Async Entity Tracker Queue Size",
                List.of("Maximum size of the queue for pending",
                        "pathfinding tasks. If set to 0, the queue size",
                        "dynamically calculated as max-threads * 256."),
                (Boolean) values.get("async.async-pathfinding.enabled").getValue(),
                0,
                500,
                null,
                plugin
        ));

        values.put(Key.ASYNC_PATHFINDING_REJECT_POLICY.toString(), new StringConfigItem(
                file,
                Key.ASYNC_PATHFINDING_REJECT_POLICY.toString(),
                "Async Entity Tracker Queue Size",
                List.of("The policy to use when the pathfinding task queue",
                        "is full (only relevant if queue-size is > 0) and a",
                        "new task is submitted"),
                (Boolean) values.get("async.async-pathfinding.enabled").getValue(),
                List.of("FLUSH_ALL", "CALLER_RUNS"),
                List.of("CALLER_RUNS"),
                plugin
        ));

        values.put(Key.ASYNC_MOB_SPAWNING_ENABLED.toString(), new BooleanConfigItem(
                file,
                Key.ASYNC_MOB_SPAWNING_ENABLED.toString(),
                "Async Pathfinding",
                List.of(
                        "Whether asynchronous mob spawning calculations should",
                        "be enabled. On servers with many entities, this can",
                        "improve performance by offloading some expensive",
                        "calculations required for mob spawning to other threads.",
                        "You must have Paper's per-player-mob-spawns config set",
                        "to true in paper-world-defaults.yml for this to work",
                        "effectively."),
                true,
                true,
                plugin
        ));
    }

    /**
     * Configuration values supported by this format.
     */
    public enum Key {
        ASYNC_PARALLEL_WORLD_TICKING_ENABLED {
            @Override
            public String toString() { return "async.parallel-world-ticking.enabled"; }
        },
        ASYNC_PARALLEL_WORLD_TICKING_THREADS {
            @Override
            public String toString() { return "async.parallel-world-ticking.threads"; }
        },
        ASYNC_PARALLEL_WORLD_TICKING_LOG_CONTAINER_CREATION_STACKTRACES {
            @Override
            public String toString() { return "async.parallel-world-ticking.log-container-creation-stacktraces"; }
        },
        ASYNC_PARALLEL_WORLD_TICKING_DISABLE_HARD_THROW {
            @Override
            public String toString() { return "async.parallel-world-ticking.disable-hard-throw"; }
        },
        ASYNC_PARALLEL_WORLD_TICKING_RUN_ASYNC_TASKS_SYNC {
            @Override
            public String toString() { return "async.parallel-world-ticking.run-async-tasks-sync"; }
        },
        ASYNC_ENTITY_TRACKER_ENABLED {
            @Override
            public String toString() { return "async.async-entity-tracker.enabled"; }
        },
        ASYNC_ENTITY_TRACKER_COMPAT_MODE {
            @Override
            public String toString() { return "async.async-entity-tracker.compat-mode"; }
        },
        ASYNC_ENTITY_TRACKER_MAX_THREADS {
            @Override
            public String toString() { return "async.async-entity-tracker.max-threads"; }
        },
        ASYNC_ENTITY_TRACKER_KEEPALIVE {
            @Override
            public String toString() { return "async.async-entity-tracker.keepalive"; }
        },
        ASYNC_ENTITY_TRACKER_QUEUE_SIZE {
            @Override
            public String toString() { return "async.async-entity-tracker.queue-size"; }
        },
        ASYNC_TARGET_FINDING {
            @Override
            public String toString() { return "async.async-target-finding"; }
        },
        ASYNC_PLAYERDATA_SAVE {
            @Override
            public String toString() { return "async.async-playerdata-save"; }
        },
        ASYNC_PATHFINDING_ENABLED {
            @Override
            public String toString() { return "async.async-pathfinding.enabled"; }
        },
        ASYNC_PATHFINDING_MAX_THREADS {
            @Override
            public String toString() { return "async.async-pathfinding.max-threads"; }
        },
        ASYNC_PATHFINDING_KEEPALIVE {
            @Override
            public String toString() { return "async.async-pathfinding.keepalive"; }
        },
        ASYNC_PATHFINDING_QUEUE_SIZE {
            @Override
            public String toString() { return "async.async-pathfinding.queue-size"; }
        },
        ASYNC_PATHFINDING_REJECT_POLICY {
            @Override
            public String toString() { return "async.async-pathfinding.reject-policy"; }
        },
        ASYNC_MOB_SPAWNING_ENABLED {
            @Override
            public String toString() { return "async.async-mob-spawning.enabled"; }
        }
    }
}