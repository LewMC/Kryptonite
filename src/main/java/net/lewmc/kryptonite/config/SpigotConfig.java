package net.lewmc.kryptonite.config;

import net.lewmc.kryptonite.Kryptonite;
import net.lewmc.kryptonite.utils.config.BooleanConfigItem;
import net.lewmc.kryptonite.utils.config.ConfigCollection;
import net.lewmc.kryptonite.utils.config.IntegerConfigItem;
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

        int simulationDistance = ((int) new MinecraftConfig(plugin).values.get("simulation-distance").getValue() -1)*16;

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

        values.put(Key.MOB_SPAWN_RANGE.toString(), new IntegerConfigItem(
                file,
                Key.MOB_SPAWN_RANGE.toString(),
                "Mob Spawn Range",
                List.of("The distance the server will spawn mobs."),
                true,
                1,
                100,
                "3-8",
                plugin
        ));

        values.put(Key.MOB_SPAWN_RANGE.toString(), new IntegerConfigItem(
                file,
                Key.MOB_SPAWN_RANGE.toString(),
                "Entity Activation Range (Mobs)",
                List.of("The distance the server will 'activate' mob",
                        "entities."),
                true,
                1,
                100,
                "16-"+simulationDistance,
                plugin
        ));

        values.put(Key.ENTITY_ACTIVATION_RANGE_ANIMALS.toString(), new IntegerConfigItem(
                file,
                Key.ENTITY_ACTIVATION_RANGE_ANIMALS.toString(),
                "Entity Activation Range (Animals)",
                List.of("The distance the server will 'activate'",
                        "animal entities."),
                true,
                1,
                100,
                "16-"+simulationDistance,
                plugin
        ));

        values.put(Key.ENTITY_ACTIVATION_RANGE_MONSTERS.toString(), new IntegerConfigItem(
                file,
                Key.ENTITY_ACTIVATION_RANGE_MONSTERS.toString(),
                "Entity Activation Range (Monsters)",
                List.of("The distance the server will 'activate'",
                        "monster entities."),
                true,
                1,
                100,
                "16-"+simulationDistance,
                plugin
        ));

        values.put(Key.ENTITY_ACTIVATION_RANGE_RAIDERS.toString(), new IntegerConfigItem(
                file,
                Key.ENTITY_ACTIVATION_RANGE_RAIDERS.toString(),
                "Entity Activation Range (Raiders)",
                List.of("The distance the server will 'activate'",
                        "raider entities."),
                true,
                1,
                100,
                "16-"+simulationDistance,
                plugin
        ));

        values.put(Key.ENTITY_ACTIVATION_RANGE_MISC.toString(), new IntegerConfigItem(
                file,
                Key.ENTITY_ACTIVATION_RANGE_MISC.toString(),
                "Entity Activation Range (Misc)",
                List.of("The distance the server will 'activate'",
                        "miscellaneous entities."),
                true,
                1,
                100,
                "16-"+simulationDistance,
                plugin
        ));

        values.put(Key.ENTITY_ACTIVATION_RANGE_WATER.toString(), new IntegerConfigItem(
                file,
                Key.ENTITY_ACTIVATION_RANGE_WATER.toString(),
                "Entity Activation Range (Water)",
                List.of("The distance the server will 'activate'",
                        "water entities."),
                true,
                1,
                100,
                "16-"+simulationDistance,
                plugin
        ));

        values.put(Key.ENTITY_ACTIVATION_RANGE_VILLAGERS.toString(), new IntegerConfigItem(
                file,
                Key.ENTITY_ACTIVATION_RANGE_VILLAGERS.toString(),
                "Entity Activation Range (Villagers)",
                List.of("The distance the server will 'activate'",
                        "villager entities."),
                true,
                1,
                100,
                "16-"+simulationDistance,
                plugin
        ));

        values.put(Key.ENTITY_ACTIVATION_RANGE_FLYING_MONSTERS.toString(), new IntegerConfigItem(
                file,
                Key.ENTITY_ACTIVATION_RANGE_FLYING_MONSTERS.toString(),
                "Entity Activation Range (Flying Monsters)",
                List.of("The distance the server will 'activate' flying",
                        "monster entities."),
                true,
                1,
                100,
                "16-"+simulationDistance,
                plugin
        ));

        values.put(Key.ENTITY_TRACKING_RANGE_PLAYERS.toString(), new IntegerConfigItem(
                file,
                Key.ENTITY_TRACKING_RANGE_PLAYERS.toString(),
                "Entity Tracking Range (Players)",
                List.of("Controls how far in blocks players are tracked",
                        "(sent to) the player."),
                true,
                1,
                100,
                "6-"+simulationDistance,
                plugin
        ));

        values.put(Key.ENTITY_TRACKING_RANGE_ANIMALS.toString(), new IntegerConfigItem(
                file,
                Key.ENTITY_TRACKING_RANGE_ANIMALS.toString(),
                "Entity Tracking Range (Animals)",
                List.of("Controls how far in blocks animals are tracked",
                        "(sent to) the player."),
                true,
                1,
                100,
                "6-"+simulationDistance,
                plugin
        ));

        values.put(Key.ENTITY_TRACKING_RANGE_MONSTERS.toString(), new IntegerConfigItem(
                file,
                Key.ENTITY_TRACKING_RANGE_MONSTERS.toString(),
                "Entity Tracking Range (Monsters)",
                List.of("Controls how far in blocks monsters are tracked",
                        "(sent to) the player."),
                true,
                1,
                100,
                "6-"+simulationDistance,
                plugin
        ));

        values.put(Key.ENTITY_TRACKING_RANGE_MISC.toString(), new IntegerConfigItem(
                file,
                Key.ENTITY_TRACKING_RANGE_MISC.toString(),
                "Entity Tracking Range (Miscellaneous)",
                List.of("Controls how far in blocks miscellaneous entities",
                        "are tracked (sent to) the player."),
                true,
                1,
                100,
                "6-"+simulationDistance,
                plugin
        ));

        values.put(Key.ENTITY_TRACKING_RANGE_OTHER.toString(), new IntegerConfigItem(
                file,
                Key.ENTITY_TRACKING_RANGE_OTHER.toString(),
                "Entity Tracking Range (Other)",
                List.of("Controls how far in blocks other entities are",
                        "tracked (sent to) the player."),
                true,
                1,
                100,
                "6-"+simulationDistance,
                plugin
        ));

        values.put(Key.ENTITY_TRACKING_RANGE_DISPLAY.toString(), new IntegerConfigItem(
                file,
                Key.ENTITY_TRACKING_RANGE_DISPLAY.toString(),
                "Entity Tracking Range (Display)",
                List.of("Controls how far in blocks display entities are",
                        "tracked (sent to) the player."),
                true,
                1,
                100,
                "6-"+simulationDistance,
                plugin
        ));

        values.put(Key.TICK_INACTIVE_VILLAGERS.toString(), new BooleanConfigItem(
                file,
                Key.TICK_INACTIVE_VILLAGERS.toString(),
                "Tick Inactive Villagers",
                List.of("This allows you to control whether villagers should",
                        "be ticked outside of the activation range. This will",
                        "make villagers proceed as normal and ignore the",
                        "activation range. Disabling this will help performance,",
                        "but might be confusing for players in certain situations.",
                        "May cause issues with iron farms and trade restocking."),
                true,
                false,
                plugin
        ));

        values.put(Key.NERF_SPAWNER_MOBS.toString(), new BooleanConfigItem(
                file,
                Key.NERF_SPAWNER_MOBS.toString(),
                "Nerf Spawner Mobs",
                List.of("Removes AI from mobs created by spawners. Helps server",
                        "performance but greatly impacts vanilla experience."),
                true,
                true,
                plugin
        ));

        values.put(Key.TICKS_PER_HOPPER_TRANSFER.toString(), new IntegerConfigItem(
                file,
                Key.TICKS_PER_HOPPER_TRANSFER.toString(),
                "Ticks per Hopper Transfer",
                List.of("Time in ticks that hoppers will wait to move an item.",
                        "Increasing this improves performance if there are a lot",
                        "of hoppers on your server, but will break hopper-based",
                        "clocks and possibly item sorting systems if set too high."),
                true,
                1,
                32,
                "8",
                plugin
        ));

        values.put(Key.TICKS_PER_HOPPER_CHECK.toString(), new IntegerConfigItem(
                file,
                Key.TICKS_PER_HOPPER_CHECK.toString(),
                "Ticks per Hopper Check",
                List.of("Time in ticks between hoppers checking for an item above",
                        "them or in the inventory above them. Increasing this improves",
                        "performance if there are a lot of hoppers on your server, but",
                        "will break hopper-based clocks and possibly sorting systems",
                        "if set too high."),
                true,
                1,
                32,
                "8",
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