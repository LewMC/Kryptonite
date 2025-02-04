package net.lewmc.kryptonite.kos.config;

import net.lewmc.kryptonite.Kryptonite;
import net.lewmc.kryptonite.utils.ConfigurationUtil;
import net.lewmc.kryptonite.utils.LogUtil;
import org.bukkit.command.CommandSender;

/**
 * The Bukkit class manages the bukkit.yml configuration file.
 */
public class Bukkit {
    private final Kryptonite plugin;
    private final CommandSender user;

    /**
     * Constructor for the Bukkit class.
     * @param plugin Kryptonite - Reference to the main plugin class.
     * @param user CommandSender - The user who sent the command.
     */
    public Bukkit(Kryptonite plugin, CommandSender user) {
        this.plugin = plugin;
        this.user = user;
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

    /**
     * Sets a requested key to a requested value.
     * @param key Key - The requested key.
     * @param value int - The requested value.
     */
    public void setInt(Key key, int value) {
        this.plugin.restartRequired = true;
        ConfigurationUtil cfg = new ConfigurationUtil(this.plugin, this.user);
        cfg.load("bukkit.yml");
        cfg.set(key.toString(), value);
        cfg.save();

        LogUtil log = new LogUtil(this.plugin);
        log.veboseInfo("KOS>bukkit.yml set '" + key + "' to '" + value + "'");
    }

    /**
     * Gets a requested key's value.
     * @param key Key - The requested key.
     */
    public int getInt(Key key) {
        ConfigurationUtil cfg = new ConfigurationUtil(this.plugin, this.user);
        cfg.load("bukkit.yml");
        return cfg.getInt(key.toString());
    }
}
