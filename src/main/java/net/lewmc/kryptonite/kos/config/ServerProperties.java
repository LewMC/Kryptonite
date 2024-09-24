package net.lewmc.kryptonite.kos.config;

import net.lewmc.kryptonite.utils.PropertiesUtil;

/**
 * Manages the server.properties file.
 */
public class ServerProperties {
    private final PropertiesUtil util;

    /**
     * Constructor for the ServerProperties class.
     */
    public ServerProperties() {
        this.util = new PropertiesUtil("server.properties");
    }

    /**
     * Configuration values supported by this format.
     */
    public enum Config {
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
        }
    }

    /**
     * Sets a value.
     * @param key Config - A valid configuration key.
     * @param value String - The value to set.
     */
    public void set(Config key, String value) {
        this.util.setProperty(key.toString(), value);
    }

    /**
     * Retrieves a string from the configuration.
     * @param key Config - A valid configuration key.
     * @return String - The value.
     */
    public String getString(Config key) {
        return this.util.getProperty(key.toString());
    }

    /**
     * Retrieves an integer from the configuration.
     * @param key Config - A valid configuration key.
     * @return int - The value.
     */
    public int getInt(Config key) {
        return Integer.parseInt(this.util.getProperty(key.toString()));
    }
}
