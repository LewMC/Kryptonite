package net.lewmc.kryptonite.kos.config;

import net.lewmc.kryptonite.utils.PropertiesUtil;

public class ServerProperties {
    private final PropertiesUtil util;

    public ServerProperties() {
        this.util = new PropertiesUtil("server.properties");
    }


    public enum Config {
        NETWORK_COMPRESSION_THRESHOLD {
            public String toString() {
                return "network-compression-threshold";
            }
        },
        SIMULATION_DISTANCE {
            public String toString() {
                return "simulation-distance";
            }
        },
        VIEW_DISTANCE {
            public String toString() {
                return "view-distance";
            }
        },
        SYNC_CHUNK_WRITES {
            public String toString() {
                return "sync-chunk-writes";
            }
        }
    }

    public void set(Config key, String value) {
        this.util.setProperty(key.toString(), value);
    }

    public String getString(Config key) {
        return this.util.getProperty(key.toString());
    }

    public int getInt(Config key) {
        return Integer.parseInt(this.util.getProperty(key.toString()));
    }

    @Deprecated
    public void setNetworkCompressionThreshold(String value) {
        this.util.setProperty("network-compression-threshold", value);
    }

    @Deprecated
    public void setSimulationDistance(String value) {
        this.util.setProperty("simulation-distance", value);
    }

    @Deprecated
    public void setViewDistance(String value) {
        this.util.setProperty("view-distance", value);
    }

    @Deprecated
    public void setSyncChunkWrites(boolean value) {
        if (value) {
            this.util.setProperty("sync-chunk-writes", "true");
        } else {
            this.util.setProperty("sync-chunk-writes", "false");
        }
    }

    public void save() {
        // Doesn't require saving.
    }
}
