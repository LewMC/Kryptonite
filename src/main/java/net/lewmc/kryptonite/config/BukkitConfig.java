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

        values.put("spawn-limits.monsters", new IntegerConfigItem(
                file,
                "spawn-limits.monsters",
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

        values.put("spawn-limits.animals", new IntegerConfigItem(
                file,
                "spawn-limits.animals",
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

        values.put("spawn-limits.water-animals", new IntegerConfigItem(
                file,
                "spawn-limits.water-animals",
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

        values.put("spawn-limits.water-ambient", new IntegerConfigItem(
                file,
                "spawn-limits.water-ambient",
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

        values.put("spawn-limits.water-underground-creature", new IntegerConfigItem(
                file,
                "spawn-limits.water-underground-creature",
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

        values.put("spawn-limits.axolotls", new IntegerConfigItem(
                file,
                "spawn-limits.axolotls",
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

        values.put("ticks-per.monster-spawns", new IntegerConfigItem(
                file,
                "ticks-per.monster-spawns",
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

        values.put("ticks-per.animal-spawns", new IntegerConfigItem(
                file,
                "ticks-per.animal-spawns",
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

        values.put("ticks-per.water-spawns", new IntegerConfigItem(
                file,
                "ticks-per.water-spawns",
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

        values.put("ticks-per.water-ambient-spawns", new IntegerConfigItem(
                file,
                "ticks-per.water-ambient-spawns",
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

        values.put("ticks-per.axolotl-spawns", new IntegerConfigItem(
                file,
                "ticks-per.axolotl-spawns",
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

        values.put("ticks-per.ambient-spawns", new IntegerConfigItem(
                file,
                "ticks-per.ambient-spawns",
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

        values.put("chunk-gc.period-in-ticks", new IntegerConfigItem(
                file,
                "chunk-gc.period-in-ticks",
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