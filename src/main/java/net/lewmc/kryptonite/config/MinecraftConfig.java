package net.lewmc.kryptonite.config;

import net.lewmc.kryptonite.Kryptonite;
import net.lewmc.kryptonite.utils.config.BooleanConfigItem;
import net.lewmc.kryptonite.utils.config.ConfigCollection;
import net.lewmc.kryptonite.utils.config.IntegerConfigItem;

import java.util.*;

/**
 * Configuration data for server.properties
 * @since 2.1.0
 */
public class MinecraftConfig extends ConfigCollection {
    /**
     * Constructs the server.properties data.
     * @param plugin Kryptonite - Reference to the main Kryptonite class.
     */
    public MinecraftConfig(Kryptonite plugin) {
        String file = "server.properties";

        values.put(Key.NETWORK_COMPRESSION_THRESHOLD.toString(), new IntegerConfigItem(
                file,
                Key.NETWORK_COMPRESSION_THRESHOLD.toString(),
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

        values.put(Key.VIEW_DISTANCE.toString(), new IntegerConfigItem(
                file,
                Key.VIEW_DISTANCE.toString(),
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

        values.put(Key.SIMULATION_DISTANCE.toString(), new IntegerConfigItem(
                file,
                Key.SIMULATION_DISTANCE.toString(),
                "Simulation Distance",
                List.of("The distance mobs will be simulated."),
                true,
                1,
                50,
                "5 - "+values.get("view-distance").getValue(),
                plugin
        ));

        values.put(Key.SYNC_CHUNK_WRITES.toString(), new BooleanConfigItem(
                file,
                Key.SYNC_CHUNK_WRITES.toString(),
                "Sync Chunk Writes",
                List.of("Forces the server to write chunks on the main",
                        "thread which impacts performance."
                ),
                true,
                false,
                plugin
        ));

        values.put(Key.ALLOW_FLIGHT.toString(), new BooleanConfigItem(
                file,
                Key.ALLOW_FLIGHT.toString(),
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

    /**
     * Configuration values supported by this format.
     */
    public enum Key {
        NETWORK_COMPRESSION_THRESHOLD {
            @Override
            public String toString() { return "network-compression-threshold";}
        },
        VIEW_DISTANCE {
            @Override
            public String toString() { return "view-distance";}
        },
        SIMULATION_DISTANCE {
            @Override
            public String toString() { return "simulation-distance";}
        },
        SYNC_CHUNK_WRITES {
            @Override
            public String toString() { return "sync-chunk-writes";}
        },
        ALLOW_FLIGHT {
            @Override
            public String toString() { return "allow-flight";}
        }
    }
}