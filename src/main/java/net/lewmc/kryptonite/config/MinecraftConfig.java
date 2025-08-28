package net.lewmc.kryptonite.config;

import net.lewmc.kryptonite.Kryptonite;
import net.lewmc.kryptonite.utils.config.BooleanConfigItem;
import net.lewmc.kryptonite.utils.config.ConfigCollection;
import net.lewmc.kryptonite.utils.config.GenericConfigItem;
import net.lewmc.kryptonite.utils.config.IntegerConfigItem;

import java.util.*;

/**
 * Configuration data for server.properties
 * @since 2.1.0
 */
public class MinecraftConfig extends ConfigCollection {
    /**
     * Holds configuration data for the server.properties file.
     */
    public HashMap<String, GenericConfigItem> values = new HashMap<>();

    /**
     * Constructs the server.properties data.
     * @param plugin Kryptonite - Reference to the main Kryptonite class.
     */
    public MinecraftConfig(Kryptonite plugin) {
        String file = "server.properties";

        values.put("network-compression-threshold", new IntegerConfigItem(
                file,
                "network-compression-threshold",
                "Network Compression Threshold",
                List.of(
                        "The cap for the size of a packet before the",
                        "server attempts to compress it."),
                true,
                -1,
                1000,
                "256",
                plugin
        ));

        values.put("view-distance", new IntegerConfigItem(
                file,
                "view-distance",
                "View Distance",
                List.of("The distance players can see. Using client mods",
                        "such as Distant Horizons or Bobby allow players",
                        "to see further without any performance impact",
                        "for the server."
                ),
                true,
                1,
                50,
                "5 - 10",
                plugin
        ));

        values.put("simulation-distance", new IntegerConfigItem(
                file,
                "simulation-distance",
                "Simulation Distance",
                List.of("The distance mobs will be simulated."),
                true,
                1,
                50,
                "5 - "+values.get("view-distance").getValue(),
                plugin
        ));

        values.put("sync-chunk-writes", new BooleanConfigItem(
                file,
                "sync-chunk-writes",
                "Sync Chunk Writes",
                List.of("Forces the server to write chunks on the main",
                        "thread which impacts performance."
                ),
                true,
                false,
                plugin
        ));

        values.put("allow-flight", new BooleanConfigItem(
                file,
                "allow-flight",
                "Allow Flight",
                List.of("This prevents players from getting kicked by the",
                        "server for 'flying' while riding a horse or",
                        "climbing on scaffolding. Doesn't actually allow",
                        "players to fly."
                ),
                true,
                null,
                plugin
        ));
    }
}