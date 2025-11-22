package net.lewmc.kryptonite.config;

import net.lewmc.kryptonite.Kryptonite;
import net.lewmc.kryptonite.utils.config.BooleanConfigItem;
import net.lewmc.kryptonite.utils.config.ConfigCollection;
import net.lewmc.kryptonite.utils.config.IntegerConfigItem;

import java.util.List;

/**
 * Configuration data for purpur.yml
 * @since 2.1.0
 */
public class PurpurConfig extends ConfigCollection {
    /**
     * Constructs the purpur.yml data.
     * @param plugin Kryptonite - Reference to the main Kryptonite class.
     */
    public PurpurConfig(Kryptonite plugin) {
        String file = "purpur.yml";

        values.put(Key.USE_ALTERNATE_KEEPALIVE.toString(), new BooleanConfigItem(
                file,
                Key.USE_ALTERNATE_KEEPALIVE.toString(),
                "Use Alternate Keepalive",
                List.of(
                        "Uses a different approach to keepalive ping timeouts.",
                        "Enabling this sends a keepalive packet once per",
                        "second to a player, and only kicks for timeout if",
                        "none of them were responded to in 30 seconds. Cannot",
                        "enable if using TCPShield."),
                !plugin.getConfig().getBoolean("using-tcpshield"),
                true,
                plugin
        ));

        values.put(Key.ZOMBIE_AGGRESSIVE_TOWARDS_VILLAGER_WHEN_LAGGING.toString(), new BooleanConfigItem(
                file,
                Key.ZOMBIE_AGGRESSIVE_TOWARDS_VILLAGER_WHEN_LAGGING.toString(),
                "Zombie Aggressive Towards Villager when Lagging",
                List.of(
                        "Set to false to stop zombie aggressiveness towards",
                        "villagers when lagging. May impact vanilla behaviour",
                        "when lagging, but may reduce overall lag."),
                true,
                true,
                plugin
        ));

        values.put(Key.ENTITIES_CAN_USE_PORTALS.toString(), new BooleanConfigItem(
                file,
                Key.ENTITIES_CAN_USE_PORTALS.toString(),
                "Entities can use Portals",
                List.of(
                        "Set to false to stop entities from being able to use",
                        "portals reduces lag but impacts vanilla behaviour."),
                true,
                false,
                plugin
        ));

        values.put(Key.VILLAGER_IS_LOBOTOMIZED.toString(), new BooleanConfigItem(
                file,
                Key.VILLAGER_IS_LOBOTOMIZED.toString(),
                "Villagers Lobotomize",
                List.of(
                        "Lobotomizes the villager if it cannot move (Does not",
                        "disable trading, but some trades may not refill). May",
                        "impact vanilla behaviour, only enable if you have done",
                        "ABSOLUTELY EVERYTHING ELSE to try and reduce lag. May",
                        "break iron golem farms."),
                true,
                false,
                plugin
        ));

        values.put(Key.VILLAGER_SEARCH_RADIUS_ACQUIRE_POI.toString(), new IntegerConfigItem(
                file,
                Key.VILLAGER_SEARCH_RADIUS_ACQUIRE_POI.toString(),
                "Villager Search Radius (Acquire POI)",
                List.of(
                        "Radius within which villagers search to acquire POI.",
                        "Below 48 may break iron golem farms."),
                true,
                1,
                100,
                "16-32",
                plugin
        ));

        values.put(Key.VILLAGER_SEARCH_RADIUS_NEAREST_BED_SENSOR.toString(), new IntegerConfigItem(
                file,
                Key.VILLAGER_SEARCH_RADIUS_NEAREST_BED_SENSOR.toString(),
                "Villager Search Radius (Nearest Bed Sensor)",
                List.of(
                        "Radius within which villagers search to detect the",
                        "nearest bed. Below 48 may break iron golem farms."),
                true,
                1,
                100,
                "16-32",
                plugin
        ));

        values.put(Key.DOLPHIN_DISABLE_TREASURE_SEARCHING.toString(), new BooleanConfigItem(
                file,
                Key.DOLPHIN_DISABLE_TREASURE_SEARCHING.toString(),
                "Villager Search Radius (Nearest Bed Sensor)",
                List.of(
                        "Stops the dolphin from treasure hunting. Will impact",
                        "vanilla behaviour."),
                true,
                true,
                plugin
        ));

        values.put(Key.TELEPORT_IF_OUTSIDE_BORDER.toString(), new BooleanConfigItem(
                file,
                Key.TELEPORT_IF_OUTSIDE_BORDER.toString(),
                "Teleport if Outside Border",
                List.of(
                        "Teleports you to spawn if you somehow get outside the",
                        "world border."),
                true,
                true,
                plugin
        ));

        values.put(Key.LAGGING_THRESHOLD.toString(), new IntegerConfigItem(
                file,
                Key.LAGGING_THRESHOLD.toString(),
                "Lagging Threshold",
                List.of(
                        "Purpur keeps track of when it is lagging in order to",
                        "have the ability to change behaviors accordingly. If",
                        "vanilla behaviour is being impacted too much, consider",
                        "reducing this. 19 is a good starting point."),
                true,
                1,
                19,
                "15-19",
                plugin
        ));
    }

    /**
     * Configuration values supported by this format.
     */
    public enum Key {
        USE_ALTERNATE_KEEPALIVE {
            @Override
            public String toString() { return "settings.use-alternate-keepalive"; }
        },
        ZOMBIE_AGGRESSIVE_TOWARDS_VILLAGER_WHEN_LAGGING {
            @Override
            public String toString() { return "world-settings.default.mobs.zombie.aggressive-towards-villager-when-lagging"; }
        },
        ENTITIES_CAN_USE_PORTALS {
            @Override
            public String toString() { return "world-settings.default.gameplay-mechanics.entities-can-use-portals"; }
        },
        VILLAGER_IS_LOBOTOMIZED {
            @Override
            public String toString() { return "world-settings.default.mobs.villager.lobotomize.enabled"; }
        },
        VILLAGER_SEARCH_RADIUS_ACQUIRE_POI {
            @Override
            public String toString() { return "world-settings.default.mobs.villager.search-radius.acquire-poi"; }
        },
        VILLAGER_SEARCH_RADIUS_NEAREST_BED_SENSOR {
            @Override
            public String toString() { return "world-settings.default.mobs.villager.search-radius.nearest-bed-sensor"; }
        },
        DOLPHIN_DISABLE_TREASURE_SEARCHING {
            @Override
            public String toString() { return "world-settings.default.mobs.dolphin.disable-treasure-searching"; }
        },
        TELEPORT_IF_OUTSIDE_BORDER {
            @Override
            public String toString() { return "world-settings.default.gameplay-mechanics.player.teleport-if-outside-border"; }
        },
        LAGGING_THRESHOLD {
            @Override
            public String toString() { return "settings.lagging-threshold"; }
        }
    }
}