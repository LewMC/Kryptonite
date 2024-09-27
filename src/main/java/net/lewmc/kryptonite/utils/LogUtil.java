package net.lewmc.kryptonite.utils;

import com.tcoded.folialib.FoliaLib;
import com.tcoded.folialib.wrapper.task.WrappedTask;
import net.lewmc.kryptonite.Kryptonite;
import org.bukkit.Bukkit;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

/**
 * LogUtil manages logging of information.
 */
public class LogUtil {
    private final Kryptonite plugin;

    private enum Type {
        LOG_INFO {
            @Override public String toString() { return "INFO"; }
        },
        LOG_WARNING {
            @Override public String toString() { return "WARN"; }
        },
        LOG_SEVERE {
            @Override public String toString() { return "SEVERE"; }
        },
    }

    /**
     * Constructor for the LogUtil class.
     * @param plugin Kryptonite - Reference to the main plugin class.
     */
    public LogUtil(Kryptonite plugin) {
        this.plugin = plugin;
    }

    /**
     * Logs informational messages if verbose mode is on.
     * @param message String - The message to log.
     */
    public void veboseInfo(String message) {
        message = "[Kryptonite] " + message;

        if (this.plugin.getConfig().getBoolean("verbose")) {
            Bukkit.getLogger().info(message);
        }

        this.logToFile(Type.LOG_INFO, message);
    }

    /**
     * Logs informational messages.
     * @param message String - The message to log.
     */
    public void info(String message) {
        message = "[Kryptonite] " + message;

        Bukkit.getLogger().info(message);
    }

    /**
     * Logs warning messages.
     * @param message String - The message to log.
     */
    public void warn(String message) {
        message = "[Kryptonite] " + message;

        Bukkit.getLogger().warning(message);
    }

    /**
     * Logs severe messages.
     * @param message String - The message to log.
     */
    public void severe(String message) {
        message = "[Kryptonite] " + message;

        Bukkit.getLogger().severe(message);
    }

    /**
     * Logs informational messages.
     * @param type Type - The type of message to log.
     * @param message String - The message to log.
     */
    private void logToFile(Type type, final String message) {
        FoliaLib foliaLib = new FoliaLib(this.plugin);

        foliaLib.getScheduler().runAsync((WrappedTask task) -> {
            String msg = "[" + type.toString() + "]" + message;
            Path p = Paths.get(this.plugin.getDataFolder().getAbsolutePath() + "/kryptonite.log");
            String s = System.lineSeparator() + msg;

            try (BufferedWriter writer = Files.newBufferedWriter(p, StandardOpenOption.APPEND)) {
                writer.write(s);
            } catch (IOException e) {
                Bukkit.getLogger().severe("[Kryptonite] Unable to write to file: " + e.getMessage());
            }
            foliaLib.getScheduler().cancelTask(task);
        });
    }
}
