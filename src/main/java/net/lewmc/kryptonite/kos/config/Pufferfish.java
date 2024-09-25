package net.lewmc.kryptonite.kos.config;

import net.lewmc.kryptonite.Kryptonite;
import net.lewmc.kryptonite.utils.ConfigurationUtil;
import org.bukkit.command.CommandSender;

/**
 * The Pufferfish class manages the pufferfish.yml configuration file.
 */
public class Pufferfish {
    private final Kryptonite plugin;
    private final CommandSender user;

    /**
     * Constructor for the Pufferfish class.
     * @param plugin Kryptonite - Reference to the main plugin class.
     * @param user CommandSender - The user who sent the command.
     */
    public Pufferfish(Kryptonite plugin, CommandSender user) {
        this.plugin = plugin;
        this.user = user;
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

    /**
     * Sets a requested key to a requested value.
     * @param key Key - The requested key.
     * @param value int - The requested value.
     */
    public void setInt(Key key, int value) {
        this.plugin.restartRequired = true;
        ConfigurationUtil cfg = new ConfigurationUtil(this.plugin, this.user);
        cfg.load("pufferfish.yml");
        cfg.set(key.toString(), value);
        cfg.save();
    }

    /**
     * Gets a requested key's value.
     * @param key Key - The requested key.
     */
    public int getInt(Key key) {
        ConfigurationUtil cfg = new ConfigurationUtil(this.plugin, this.user);
        cfg.load("pufferfish.yml");
        return cfg.getInt(key.toString());
    }

    /**
     * Sets a requested key to a requested value.
     * @param key Key - The requested key.
     * @param value int - The requested value.
     */
    public void setBoolean(Key key, boolean value) {
        this.plugin.restartRequired = true;
        ConfigurationUtil cfg = new ConfigurationUtil(this.plugin, this.user);
        cfg.load("pufferfish.yml");
        cfg.set(key.toString(), value);
        cfg.save();
    }

    /**
     * Gets a requested key's value.
     * @param key Key - The requested key.
     */
    public boolean getBoolean(Key key) {
        ConfigurationUtil cfg = new ConfigurationUtil(this.plugin, this.user);
        cfg.load("pufferfish.yml");
        return cfg.getBoolean(key.toString());
    }

    /**
     * Sets a requested key to a requested value.
     * @param key Key - The requested key.
     * @param value int - The requested value.
     */
    public void setString(Key key, String value) {
        this.plugin.restartRequired = true;
        ConfigurationUtil cfg = new ConfigurationUtil(this.plugin, this.user);
        cfg.load("pufferfish.yml");
        cfg.set(key.toString(), value);
        cfg.save();
    }
}
