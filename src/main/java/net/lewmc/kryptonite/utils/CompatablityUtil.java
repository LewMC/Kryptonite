package net.lewmc.kryptonite.utils;

import net.lewmc.kryptonite.Kryptonite;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class CompatablityUtil {
    private final Kryptonite plugin;

    public CompatablityUtil(Kryptonite plugin) {
        this.plugin = plugin;
    }

    public List<String> badPlugins() {
        List<String> badPlugins = new java.util.ArrayList<>(List.of());

        Plugin[] plugins = this.plugin.getServer().getPluginManager().getPlugins();

        for (Plugin p : plugins) {
            if (
                    p.getName().equalsIgnoreCase("ClearLag") ||
                    p.getName().equalsIgnoreCase("EntityClearer") ||
                    p.getName().equalsIgnoreCase("StackMob") ||
                    p.getName().equalsIgnoreCase("PluginManager+") ||
                    p.getName().equalsIgnoreCase("PluginManager") ||
                    p.getName().equalsIgnoreCase("AutoPluginLoader") ||
                    p.getName().equalsIgnoreCase("PlugMan") ||
                    p.getName().equalsIgnoreCase("PlugManX")
            ) {
                badPlugins.add(p.getName());
            }
        }

        return badPlugins;
    }
}
