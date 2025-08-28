package net.lewmc.kryptonite.kos.config;

import net.lewmc.kryptonite.Kryptonite;
import net.lewmc.kryptonite.utils.ConfigurationUtil;
import net.lewmc.kryptonite.utils.LogUtil;
import org.bukkit.command.CommandSender;

/**
 * The Spigot class manages the spigot.yml configuration file.
 * @deprecated
 */
@Deprecated
public class Spigot {
    private final Kryptonite plugin;
    private final CommandSender user;

    /**
     * Constructor for the Spigot class.
     * @param plugin Kryptonite - Reference to the main plugin class.
     * @param user CommandSender - The user who sent the command.
     */
    public Spigot(Kryptonite plugin, CommandSender user) {
        this.plugin = plugin;
        this.user = user;
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

    /**
     * Sets a requested key to a requested value.
     * @param key Key - The requested key.
     * @param value int - The requested value.
     */
    public void setInt(Key key, int value) {
        this.plugin.restartRequired = true;
        ConfigurationUtil cfg = new ConfigurationUtil(this.plugin, this.user);
        cfg.load("spigot.yml");
        cfg.set(key.toString(), value);
        cfg.save();

        LogUtil log = new LogUtil(this.plugin);
        log.veboseInfo("KOS>spigot.yml set '" + key + "' to '" + value + "'");
    }

    /**
     * Gets a requested key's value.
     * @param key Key - The requested key.
     */
    public int getInt(Key key) {
        ConfigurationUtil cfg = new ConfigurationUtil(this.plugin, this.user);
        cfg.load("spigot.yml");
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
        cfg.load("spigot.yml");
        cfg.set(key.toString(), value);
        cfg.save();

        LogUtil log = new LogUtil(this.plugin);
        log.veboseInfo("KOS>spigot.yml set '" + key + "' to '" + value + "'");
    }

    /**
     * Gets a requested key's value.
     * @param key Key - The requested key.
     */
    public boolean getBoolean(Key key) {
        ConfigurationUtil cfg = new ConfigurationUtil(this.plugin, this.user);
        cfg.load("spigot.yml");
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
        cfg.load("spigot.yml");
        cfg.set(key.toString(), value);
        cfg.save();

        LogUtil log = new LogUtil(this.plugin);
        log.veboseInfo("KOS>spigot.yml set '" + key + "' to '" + value + "'");
    }

    /**
     * Gets a requested key's value.
     * @param key Key - The requested key.
     */
    public Object getObject(Key key) {
        ConfigurationUtil cfg = new ConfigurationUtil(this.plugin, this.user);
        cfg.load("spigot.yml");
        return cfg.get(key.toString());
    }
}
