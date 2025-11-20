package net.lewmc.kryptonite.config;

import net.lewmc.kryptonite.Kryptonite;
import net.lewmc.kryptonite.utils.config.ConfigCollection;
import net.lewmc.kryptonite.utils.config.StringConfigItem;

import java.util.List;

/**
 * Configuration data for purpur.yml
 * @since 2.1.0
 */
public class SpigotConfig extends ConfigCollection {
    /**
     * Constructs the purpur.yml data.
     * @param plugin Kryptonite - Reference to the main Kryptonite class.
     */
    public SpigotConfig(Kryptonite plugin) {
        String file = "spigot.yml";

        values.put(Key.VIEW_DISTANCE.toString(), new StringConfigItem(
                file,
                Key.VIEW_DISTANCE.toString(),
                "View Distance",
                List.of(
                        "The distance the server will send to the client.",
                        "Recommended value: 'default' (click to zero)"),
                true,
                List.of("default","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32"),
                List.of("default"),
                plugin
        ));
    }

    /**
     * Configuration values supported by this format.
     */
    public enum Key {
        VIEW_DISTANCE {
            @Override
            public String toString() { return "world-settings.default.view-distance"; }
        },
        MOB_SPAWN_RANGE {
            @Override
            public String toString() { return "world-settings.default.mob-spawn-range"; }
        },
        ENTITY_ACTIVATION_RANGE_ANIMALS {
            @Override
            public String toString() { return "world-settings.default.entity-activation-range.animals"; }
        },
        ENTITY_ACTIVATION_RANGE_MONSTERS {
            @Override
            public String toString() { return "world-settings.default.entity-activation-range.monsters"; }
        },
        ENTITY_ACTIVATION_RANGE_RAIDERS {
            @Override
            public String toString() { return "world-settings.default.entity-activation-range.raiders"; }
        },
        ENTITY_ACTIVATION_RANGE_MISC {
            @Override
            public String toString() { return "world-settings.default.entity-activation-range.misc"; }
        },
        ENTITY_ACTIVATION_RANGE_WATER {
            @Override
            public String toString() { return "world-settings.default.entity-activation-range.water"; }
        },
        ENTITY_ACTIVATION_RANGE_VILLAGERS {
            @Override
            public String toString() { return "world-settings.default.entity-activation-range.villagers"; }
        },
        ENTITY_ACTIVATION_RANGE_FLYING_MONSTERS {
            @Override
            public String toString() { return "world-settings.default.entity-activation-range.flying-monsters"; }
        },
        ENTITY_TRACKING_RANGE_PLAYERS {
            @Override
            public String toString() { return "world-settings.default.entity-tracking-range.players"; }
        },
        ENTITY_TRACKING_RANGE_ANIMALS {
            @Override
            public String toString() { return "world-settings.default.entity-tracking-range.animals"; }
        },
        ENTITY_TRACKING_RANGE_MONSTERS {
            @Override
            public String toString() { return "world-settings.default.entity-tracking-range.monsters"; }
        },
        ENTITY_TRACKING_RANGE_MISC {
            @Override
            public String toString() { return "world-settings.default.entity-tracking-range.misc"; }
        },
        ENTITY_TRACKING_RANGE_OTHER {
            @Override
            public String toString() { return "world-settings.default.entity-tracking-range.other"; }
        },
        ENTITY_TRACKING_RANGE_DISPLAY {
            @Override
            public String toString() { return "world-settings.default.entity-tracking-range.display"; }
        },
        TICK_INACTIVE_VILLAGERS {
            @Override
            public String toString() { return "world-settings.default.entity-activation-range.tick-inactive-villagers"; }
        },
        NERF_SPAWNER_MOBS {
            @Override
            public String toString() { return "world-settings.default.nerf-spawner-mobs"; }
        },
        TICKS_PER_HOPPER_TRANSFER {
            @Override
            public String toString() { return "world-settings.default.ticks-per.hopper-transfer"; }
        },
        TICKS_PER_HOPPER_CHECK {
            @Override
            public String toString() { return "world-settings.default.ticks-per.hopper-check"; }
        }
    }
}