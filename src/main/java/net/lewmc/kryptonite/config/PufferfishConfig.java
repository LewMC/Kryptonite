package net.lewmc.kryptonite.config;

import net.lewmc.kryptonite.Kryptonite;
import net.lewmc.kryptonite.utils.config.BooleanConfigItem;
import net.lewmc.kryptonite.utils.config.ConfigCollection;
import net.lewmc.kryptonite.utils.config.IntegerConfigItem;

import java.util.List;

/**
 * Configuration data for pufferfish.yml
 * @since 2.1.0
 */
public class PufferfishConfig extends ConfigCollection {
    /**
     * Constructs the pufferfish.yml data.
     * @param plugin Kryptonite - Reference to the main Kryptonite class.
     */
    public PufferfishConfig(Kryptonite plugin) {
        String file = "pufferfish.yml";

        values.put(Key.MAX_LOADS_PER_PROJECTILE.toString(), new IntegerConfigItem(
                file,
                Key.MAX_LOADS_PER_PROJECTILE.toString(),
                "Max Loads per Projectile",
                List.of("Specifies the maximum amount of chunks a projectile",
                        "can load in its lifetime. Decreasing will reduce chunk",
                        "loads caused by entity projectiles, but could cause",
                        "issues with tridents, enderpearls, etc."),
                true,
                1,
                100,
                "8-12",
                plugin
        ));

        values.put(Key.DAB_ENABLED.toString(), new BooleanConfigItem(
                file,
                Key.DAB_ENABLED.toString(),
                "DAB Enabled",
                List.of("Dynamic Activation of Brain - decreases how frequently",
                        "complex AI ticks. May impact mob farms, you may want to",
                        "consider increasing the activation-dist-mod parameter",
                        "or disabling DAB altogether if it is causing issues."),
                true,
                true,
                plugin
        ));

        values.put(Key.DAB_MAX_TICK_FREQ.toString(), new IntegerConfigItem(
                file,
                Key.DAB_MAX_TICK_FREQ.toString(),
                "DAB Max Tick Frequency",
                List.of("No matter what the result of the Activation Distance Modifier",
                        "calculation is, entities will never be ticked less frequently",
                        "than this often (in ticks)."),
                true,
                0,
                100,
                "20",
                plugin
        ));

        values.put(Key.DAB_ACTIVATION_DIST_MOD.toString(), new IntegerConfigItem(
                file,
                Key.DAB_ACTIVATION_DIST_MOD.toString(),
                "DAB Activation Distance Modifier",
                List.of("Controls how quickly the effects of DAB wear off with distance.",
                        "The default value of 8 is sufficient for most servers. Servers",
                        "with large amounts of villagers may benefit from decreasing",
                        "this value to 7, but the value should never be decreased below",
                        "6. If you have a small server, you may want to either increase",
                        "this value to 10, or simply disable DAB."),
                true,
                6,
                50,
                "8",
                plugin
        ));

        values.put(Key.ENABLE_ASYNC_MOB_SPAWNING.toString(), new BooleanConfigItem(
                file,
                Key.ENABLE_ASYNC_MOB_SPAWNING.toString(),
                "Enable Async Mob Spawning",
                List.of("Enables asynchronous mob spawning."),
                true,
                true,
                plugin
        ));

        values.put(Key.ENABLE_SUFFOCATION_OPTIMIZATION.toString(), new BooleanConfigItem(
                file,
                Key.ENABLE_SUFFOCATION_OPTIMIZATION.toString(),
                "Enable Suffocation Optimization",
                List.of("Enables suffocation optimization."),
                true,
                true,
                plugin
        ));

        values.put(Key.INACTIVE_GOAL_SELECTOR_THROTTLE.toString(), new BooleanConfigItem(
                file,
                Key.INACTIVE_GOAL_SELECTOR_THROTTLE.toString(),
                "Inactive Goal Selector Throttle",
                List.of("Improves performance. May have minor gameplay implications"),
                true,
                true,
                plugin
        ));

        values.put(Key.DISABLE_METHOD_PROFILER.toString(), new BooleanConfigItem(
                file,
                Key.DISABLE_METHOD_PROFILER.toString(),
                "Disable Method Profiler",
                List.of("Disables some additional profiling done by the game. This",
                        "profiling is not necessary to run in production and can cause",
                        "additional lag."),
                true,
                true,
                plugin
        ));
    }

    /**
     * Configuration values supported by this format.
     */
    public enum Key {
        MAX_LOADS_PER_PROJECTILE {
            @Override
            public String toString() { return "projectile.max-loads-per-projectile"; }
        },
        DAB_ENABLED {
            @Override
            public String toString() { return "dab.enabled"; }
        },
        DAB_MAX_TICK_FREQ {
            @Override
            public String toString() { return "dab.max-tick-freq"; }
        },
        DAB_ACTIVATION_DIST_MOD {
            @Override
            public String toString() { return "dab.activation-dist-mod"; }
        },
        ENABLE_ASYNC_MOB_SPAWNING {
            @Override
            public String toString() { return "enable-async-mob-spawning"; }
        },
        ENABLE_SUFFOCATION_OPTIMIZATION {
            @Override
            public String toString() { return "enable-suffocation-optimization"; }
        },
        INACTIVE_GOAL_SELECTOR_THROTTLE {
            @Override
            public String toString() { return "inactive-goal-selector-throttle"; }
        },
        DISABLE_METHOD_PROFILER {
            @Override
            public String toString() { return "misc.disable-method-profiler"; }
        }
    }
}