package net.lewmc.kryptonite.optimiser;

import net.lewmc.kryptonite.Kryptonite;
import org.bukkit.configuration.InvalidConfigurationException;

import java.io.File;
import java.io.IOException;

public class ServerProperties {
    private final Kryptonite plugin;

    public ServerProperties(final Kryptonite plugin) {
        this.plugin = plugin;
        try {
            plugin.getConfig().load(new File("server.properties"));
        } catch (IOException | InvalidConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean networkCompressionThreshold(int value) {
        this.plugin.getConfig().set("network-compression-threshold", value);
        return true;
    }

    public boolean simulationDistance(int value) {
        this.plugin.getConfig().set("network-compression-threshold", value);
        return true;
    }
}
