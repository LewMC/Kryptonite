package net.lewmc.kryptonite.utils;

import net.lewmc.kryptonite.Kryptonite;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigurationUtil {
    private final MessageUtil message;
    private final LogUtil log;
    private final CommandSender user;
    private YamlConfiguration config;

    public ConfigurationUtil(Kryptonite plugin, CommandSender user) {
        this.message = new MessageUtil(user);
        this.log = new LogUtil(plugin);
        this.user = user;
    }

    public void load(String path) {
        this.config = new YamlConfiguration();
        try {
            this.config.load(path);
        } catch (IOException | InvalidConfigurationException e) {
            this.message.Error("Unable to open configuration, see console for more information.");
            this.log.severe(e.getMessage());
        }
    }

    public void set(String key, Object value) {
        this.config.set(key, value);
    }

    public void save(File path) {
        try {
            this.config.save(path);
        } catch (IOException e) {
            this.message.Error("Unable to save configuration, see console for more information.");
            this.log.severe(e.getMessage());
        }
    }
}
