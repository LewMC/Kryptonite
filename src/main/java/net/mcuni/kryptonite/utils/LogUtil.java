package net.mcuni.kryptonite.utils;

import net.mcuni.kryptonite.Kryptonite;
import org.bukkit.Bukkit;

public class LogUtil {
    private final Kryptonite plugin;
    public LogUtil(Kryptonite plugin) {
        this.plugin = plugin;
    }

    public void info(String message) {
        Bukkit.getLogger().info("[" + this.plugin.getConfig().get("console-prefix") + "] " + message);
    }

    public void warn(String message) {
        Bukkit.getLogger().warning("[" + this.plugin.getConfig().get("console-prefix") + "] " + message);
    }

    public void severe(String message) {
        Bukkit.getLogger().severe("[" + this.plugin.getConfig().get("console-prefix") + "] " + message);
    }
}
