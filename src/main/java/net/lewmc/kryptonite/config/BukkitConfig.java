package net.lewmc.kryptonite.config;

import net.lewmc.kryptonite.Kryptonite;
import net.lewmc.kryptonite.utils.config.ConfigCollection;
import net.lewmc.kryptonite.utils.config.IntegerConfigItem;

import java.util.List;

/**
 * Configuration data for bukkit.yml
 * @since 2.1.0
 */
public class BukkitConfig extends ConfigCollection {
    /**
     * Constructs the bukkit.yml data.
     * @param plugin Kryptonite - Reference to the main Kryptonite class.
     */
    public BukkitConfig(Kryptonite plugin) {
        String file = "bukkit.yml";

        values.put(Key.SPAWN_LIMITS_MONSTERS.toString(), new IntegerConfigItem(
                file,
                Key.SPAWN_LIMITS_MONSTERS.toString(),
                "Spawn Limit (Monsters)",
                        List.of(
                        "A limit for how many mobs should spawn in the world.",
                        "The server has less work to do with lower numbers,",
                        "but survival worlds may be less realistic."),
                true,
                1,
                200,
                "20-50",
                plugin
        ));

        values.put(Key.SPAWN_LIMITS_ANIMALS.toString(), new IntegerConfigItem(
                file,
                Key.SPAWN_LIMITS_ANIMALS.toString(),
                "Spawn Limit (Animals)",
                        List.of(
                        "A limit for how many animals should spawn in the",
                        "world. The server has less work to do with lower",
                        "numbers, but survival worlds may be less realistic."),
                true,
                1,
                200,
                "5-10",
                plugin
        ));

        values.put(Key.SPAWN_LIMITS_WATER_ANIMALS.toString(), new IntegerConfigItem(
                file,
                Key.SPAWN_LIMITS_WATER_ANIMALS.toString(),
                "Spawn Limit (Water Animals)",
                        List.of(
                        "A limit for how many water animals should spawn in",
                        "the world. The server has less work to do with lower",
                        "numbers, but survival worlds may be less realistic."),
                true,
                1,
                200,
                "2-5",
                plugin
        ));

        values.put(Key.SPAWN_LIMITS_WATER_AMBIENT.toString(), new IntegerConfigItem(
                file,
                Key.SPAWN_LIMITS_WATER_AMBIENT.toString(),
                "Spawn Limit (Water Ambient)",
                        List.of(
                        "A limit for how many water ambient should spawn in",
                        "the world. The server has less work to do with lower",
                        "numbers, but survival worlds may be less realistic."),
                true,
                1,
                200,
                "2-10",
                plugin
        ));

        values.put(Key.SPAWN_LIMITS_WATER_UNDERGROUND_CREATURE.toString(), new IntegerConfigItem(
                file,
                Key.SPAWN_LIMITS_WATER_UNDERGROUND_CREATURE.toString(),
                "Spawn Limit (Water Underground Creature)",
                        List.of(
                        "A limit for how many water underground creatures",
                        "should spawn in the world. The server has less work",
                        "to do with lower numbers, but survival worlds may be",
                        "less realistic."),
                true,
                1,
                200,
                "2-5",
                plugin
        ));

        values.put(Key.SPAWN_LIMITS_AXOLOTLS.toString(), new IntegerConfigItem(
                file,
                Key.SPAWN_LIMITS_AXOLOTLS.toString(),
                "Spawn Limit (Axolotls)",
                        List.of(
                        "A limit for how many axolotls should spawn in the",
                        "world. The server has less work to do with lower",
                        "numbers, but survival worlds may be less realistic."),
                true,
                1,
                200,
                "2-4",
                plugin
        ));

        values.put(Key.TICKS_PER_MONSTER_SPAWNS.toString(), new IntegerConfigItem(
                file,
                Key.TICKS_PER_MONSTER_SPAWNS.toString(),
                "Ticks Per Monster Spawns",
                        List.of(
                        "How often (in ticks) the server attempts to spawn",
                        "monsters. The server has less work to do with lower",
                        "numbers, but survival worlds may be less realistic."),
                true,
                1,
                1000,
                "5-10",
                plugin
        ));

        values.put(Key.TICKS_PER_ANIMAL_SPAWNS.toString(), new IntegerConfigItem(
                file,
                Key.TICKS_PER_ANIMAL_SPAWNS.toString(),
                "Ticks Per Animal Spawns",
                        List.of(
                        "How often (in ticks) the server attempts to spawn",
                        "animals. The server has less work to do with lower",
                        "numbers, but survival worlds may be less realistic."),
                true,
                1,
                1000,
                "400",
                plugin
        ));

        values.put(Key.TICKS_PER_WATER_SPAWNS.toString(), new IntegerConfigItem(
                file,
                Key.TICKS_PER_WATER_SPAWNS.toString(),
                "Ticks Per Water Spawns",
                        List.of(
                        "How often (in ticks) the server attempts to spawn",
                        "water creatures. The server has less work to do with",
                        "lower numbers, but survival worlds may be less",
                        "realistic."),
                true,
                1,
                1000,
                "400",
                plugin
        ));

        values.put(Key.TICKS_PER_WATER_AMBIENT_SPAWNS.toString(), new IntegerConfigItem(
                file,
                Key.TICKS_PER_WATER_AMBIENT_SPAWNS.toString(),
                "Ticks Per Water Ambient Spawns",
                        List.of(
                        "How often (in ticks) the server attempts to spawn",
                        "water ambient creatures. The server has less work",
                        "to do with lower numbers, but survival worlds may",
                        "be less realistic."),
                true,
                1,
                1000,
                "400",
                plugin
        ));

        values.put(Key.TICKS_PER_AXOLOTL_SPAWNS.toString(), new IntegerConfigItem(
                file,
                Key.TICKS_PER_AXOLOTL_SPAWNS.toString(),
                "Ticks Per Axolotl Spawns",
                        List.of(
                        "How often (in ticks) the server attempts to spawn",
                        "axolotls. The server has less work to do with lower",
                        "numbers, but survival worlds may be less realistic."),
                true,
                1,
                1000,
                "400",
                plugin
        ));

        values.put(Key.TICKS_PER_AMBIENT_SPAWNS.toString(), new IntegerConfigItem(
                file,
                Key.TICKS_PER_AMBIENT_SPAWNS.toString(),
                "Ticks Per Ambient Spawns",
                        List.of(
                        "How often (in ticks) the server attempts to spawn",
                        "ambient creatures. The server has less work to do",
                        "with lower numbers, but survival worlds may be less.",
                        "realistic"),
                true,
                1,
                1000,
                "400",
                plugin
        ));

        values.put(Key.CHUNK_GC_PERIOD_IN_TICKS.toString(), new IntegerConfigItem(
                file,
                Key.CHUNK_GC_PERIOD_IN_TICKS.toString(),
                "Chunk GC Period in Ticks",
                        List.of(
                        "The ticks between each chunk garbage collection",
                        "consideration."),
                true,
                1,
                1000,
                "400-600",
                plugin
        ));
    }

    /**
     * Configuration values supported by this format.
     */
    public enum Key {
        SPAWN_LIMITS_MONSTERS {
            @Override
            public String toString() { return "spawn-limits.monsters"; }
        },
        SPAWN_LIMITS_ANIMALS {
            @Override
            public String toString() { return "spawn-limits.animals"; }
        },
        SPAWN_LIMITS_WATER_ANIMALS {
            @Override
            public String toString() { return "spawn-limits.water-animals"; }
        },
        SPAWN_LIMITS_WATER_AMBIENT {
            @Override
            public String toString() { return "spawn-limits.water-ambient"; }
        },
        SPAWN_LIMITS_WATER_UNDERGROUND_CREATURE {
            @Override
            public String toString() { return "spawn-limits.water-underground-creature"; }
        },
        SPAWN_LIMITS_AXOLOTLS {
            @Override
            public String toString() { return "spawn-limits.axolotls"; }
        },
        SPAWN_LIMITS_AMBIENT {
            @Override
            public String toString() { return "spawn-limits.ambient"; }
        },
        TICKS_PER_MONSTER_SPAWNS {
            @Override
            public String toString() { return "ticks-per.monster-spawns"; }
        },
        TICKS_PER_ANIMAL_SPAWNS {
            @Override
            public String toString() { return "ticks-per.animal-spawns"; }
        },
        TICKS_PER_WATER_SPAWNS {
            @Override
            public String toString() { return "ticks-per.water-spawns"; }
        },
        TICKS_PER_WATER_AMBIENT_SPAWNS {
            @Override
            public String toString() { return "ticks-per.water-ambient-spawns"; }
        },
        TICKS_PER_WATER_UNDERGROUND_CREATURE_SPAWNS {
            @Override
            public String toString() { return "ticks-per.water-underground-creature-spawns"; }
        },
        TICKS_PER_AXOLOTL_SPAWNS {
            @Override
            public String toString() { return "ticks-per.axolotl-spawns"; }
        },
        TICKS_PER_AMBIENT_SPAWNS {
            @Override
            public String toString() { return "ticks-per.ambient-spawns"; }
        },
        CHUNK_GC_PERIOD_IN_TICKS {
            @Override
            public String toString() { return "chunk-gc.period-in-ticks"; }
        }
    }
}