package net.lewmc.kryptonite.utils;

import net.lewmc.kryptonite.Kryptonite;
import org.bukkit.Bukkit;

public class LogUtil {
    private final Kryptonite plugin;
    public LogUtil(Kryptonite plugin) {
        this.plugin = plugin;
    }

    public void info(String message) {
        Bukkit.getLogger().info("[Kryptonite] " + message);
    }

    public void warn(String message) {
        Bukkit.getLogger().warning("[Kryptonite] " + message);
    }

    public void severe(String message) {
        Bukkit.getLogger().severe("[Kryptonite] " + message);
    }
}
