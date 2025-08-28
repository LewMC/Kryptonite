package net.lewmc.kryptonite.kos.config;

import net.lewmc.kryptonite.Kryptonite;
import net.lewmc.kryptonite.utils.ConfigurationUtil;
import net.lewmc.kryptonite.utils.LogUtil;
import org.bukkit.command.CommandSender;

/**
 * The leaf class manages the leaf.yml configuration file.
 * @deprecated
 */
@Deprecated
public class Leaf {
    private final Kryptonite plugin;
    private final CommandSender user;

    /**
     * Constructor for the leaf class.
     * @param plugin Kryptonite - Reference to the main plugin class.
     * @param user CommandSender - The user who sent the command.
     */
    public Leaf(Kryptonite plugin, CommandSender user) {
        this.plugin = plugin;
        this.user = user;
    }

    /**
     * Configuration values supported by this format.
     */
    public enum Key {
        PARALLEL_WORLD_TRACING_ENABLED {
            @Override
            public String toString() { return "async.parallel-world-tracing.enabled"; }
        },
        PARALLEL_WORLD_TRACING_THREADS {
            @Override
            public String toString() { return "async.parallel-world-tracing.threads"; }
        },
        PARALLEL_WORLD_TRACING_LOG_CONTAINER_CREATION_STACKTRACES {
            @Override
            public String toString() { return "async.parallel-world-tracing.log-container-creation-stacktraces"; }
        },
        PARALLEL_WORLD_TRACING_DISABLE_HARD_THROW {
            @Override
            public String toString() { return "async.parallel-world-tracing.disable-hard-throw"; }
        },
        PARALLEL_WORLD_TRACING_RUN_ASYNC_TASKS_SYNC {
            @Override
            public String toString() { return "async.parallel-world-tracing.run-async-tasks-sync"; }
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
        cfg.load("leaf.yml");
        cfg.set(key.toString(), value);
        cfg.save();

        LogUtil log = new LogUtil(this.plugin);
        log.veboseInfo("KOS>leaf.yml set '" + key + "' to '" + value + "'");
    }

    /**
     * Gets a requested key's value.
     * @param key Key - The requested key.
     */
    public int getInt(Key key) {
        ConfigurationUtil cfg = new ConfigurationUtil(this.plugin, this.user);
        cfg.load("leaf.yml");
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
        cfg.load("leaf.yml");
        cfg.set(key.toString(), value);
        cfg.save();

        LogUtil log = new LogUtil(this.plugin);
        log.veboseInfo("KOS>leaf.yml set '" + key + "' to '" + value + "'");
    }

    /**
     * Gets a requested key's value.
     * @param key Key - The requested key.
     */
    public boolean getBoolean(Key key) {
        ConfigurationUtil cfg = new ConfigurationUtil(this.plugin, this.user);
        cfg.load("leaf.yml");
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
        cfg.load("leaf.yml");
        cfg.set(key.toString(), value);
        cfg.save();

        LogUtil log = new LogUtil(this.plugin);
        log.veboseInfo("KOS>leaf.yml set '" + key + "' to '" + value + "'");
    }
}
