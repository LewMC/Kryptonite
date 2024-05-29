package net.lewmc.kryptonite.utils;

import net.lewmc.kryptonite.Kryptonite;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.IOException;

public class ConfigurationUtil {
    private final MessageUtil message;
    private final LogUtil log;

    public ConfigurationUtil(Kryptonite plugin, CommandSender user) {
        this.message = new MessageUtil(user);
        this.log = new LogUtil(plugin);
    }
    public YamlConfiguration load(String path) {
        YamlConfiguration patches = new YamlConfiguration();
        try {
            patches.load(path);
        } catch (IOException | InvalidConfigurationException e) {
            this.message.Error("Unable to open configuration, see console for more information.");
            this.log.severe(e.getMessage());
        }

        return patches;
    }
}
