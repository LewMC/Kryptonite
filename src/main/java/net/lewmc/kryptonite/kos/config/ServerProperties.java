package net.lewmc.kryptonite.kos.config;

import net.lewmc.kryptonite.Kryptonite;
import net.lewmc.kryptonite.utils.PropertiesUtil;

/**
 * The ServerProperties class manages the server.properties configuration file.
 */
public class ServerProperties {
    private final PropertiesUtil util;
    private final Kryptonite plugin;

    /**
     * Constructor for the ServerProperties class.
     * @param plugin Kryptonite - Reference to the main plugin class.
     */
    public ServerProperties(Kryptonite plugin) {
        this.util = new PropertiesUtil("server.properties");
        this.plugin = plugin;
    }

    /**
     * Configuration values supported by this format.
     */
    public enum Key {
        NETWORK_COMPRESSION_THRESHOLD {
            @Override
            public String toString() {
                return "network-compression-threshold";
            }
        },
        SIMULATION_DISTANCE {
            @Override
            public String toString() {
                return "simulation-distance";
            }
        },
        VIEW_DISTANCE {
            @Override
            public String toString() {
                return "view-distance";
            }
        },
        SYNC_CHUNK_WRITES {
            @Override
            public String toString() {
                return "sync-chunk-writes";
            }
        },
        ALLOW_FLIGHT {
            @Override
            public String toString() {
                return "allow-flight";
            }
        }
    }

    /**
     * Sets a value.
     * @param key Config - A valid configuration key.
     * @param value String - The value to set.
     */
    public void set(Key key, String value) {
        plugin.restartRequired = true;
        this.util.setProperty(key.toString(), value);
    }

    /**
     * Retrieves a string from the configuration.
     * @param key Config - A valid configuration key.
     * @return String - The value.
     */
    public String getString(Key key) {
        return this.util.getProperty(key.toString());
    }

    /**
     * Retrieves an integer from the configuration.
     * @param key Config - A valid configuration key.
     * @return int - The value.
     */
    public int getInt(Key key) {
        return Integer.parseInt(this.util.getProperty(key.toString()));
    }
}
