package net.lewmc.kryptonite.optimiser;

import net.lewmc.kryptonite.utils.PropertiesUtil;

public class ServerProperties {
    private final PropertiesUtil util;

    public ServerProperties() {
        this.util = new PropertiesUtil("server.properties");
    }

    public void networkCompressionThreshold(String value) {
        this.util.setProperty("network-compression-threshold", value);
    }

    public void simulationDistance(String value) {
        this.util.setProperty("simulation-distance", value);
    }

    public void viewDistance(String value) {
        this.util.setProperty("view-distance", value);
    }

    public void syncChunkWrites(String value) {
        this.util.setProperty("sync-chunk-writes", value);
    }

    public void save() {
        // Doesn't require saving.
    }
}
