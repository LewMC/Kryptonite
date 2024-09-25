package net.lewmc.kryptonite.kos.config;

import net.lewmc.kryptonite.Kryptonite;
import net.lewmc.kryptonite.utils.ConfigurationUtil;
import org.bukkit.command.CommandSender;

/**
 * The Purpur class manages the purpur.yml configuration file.
 */
public class Purpur {
    private final Kryptonite plugin;
    private final CommandSender user;

    /**
     * Constructor for the Purpur class.
     * @param plugin Kryptonite - Reference to the main plugin class.
     * @param user CommandSender - The user who sent the command.
     */
    public Purpur(Kryptonite plugin, CommandSender user) {
        this.plugin = plugin;
        this.user = user;
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

    /**
     * Sets a requested key to a requested value.
     * @param key Key - The requested key.
     * @param value int - The requested value.
     */
    public void setInt(Key key, int value) {
        this.plugin.restartRequired = true;
        ConfigurationUtil cfg = new ConfigurationUtil(this.plugin, this.user);
        cfg.load("purpur.yml");
        cfg.set(key.toString(), value);
        cfg.save();
    }

    /**
     * Gets a requested key's value.
     * @param key Key - The requested key.
     */
    public int getInt(Key key) {
        ConfigurationUtil cfg = new ConfigurationUtil(this.plugin, this.user);
        cfg.load("purpur.yml");
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
        cfg.load("purpur.yml");
        cfg.set(key.toString(), value);
        cfg.save();
    }

    /**
     * Gets a requested key's value.
     * @param key Key - The requested key.
     */
    public boolean getBoolean(Key key) {
        ConfigurationUtil cfg = new ConfigurationUtil(this.plugin, this.user);
        cfg.load("purpur.yml");
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
        cfg.load("purpur.yml");
        cfg.set(key.toString(), value);
        cfg.save();
    }
}
