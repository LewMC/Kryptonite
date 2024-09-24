package net.lewmc.kryptonite.utils;

import net.lewmc.kryptonite.Kryptonite;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.IOException;

public class ConfigurationUtil {
    private final MessageUtil message;
    private final LogUtil log;
    private YamlConfiguration config;
    private String path;

    public ConfigurationUtil(Kryptonite plugin, CommandSender user) {
        this.message = new MessageUtil(user);
        this.log = new LogUtil(plugin);
    }

    public void load(String path) {
        this.config = new YamlConfiguration();
        try {
            this.config.load(path);
            this.path = path;
        } catch (IOException | InvalidConfigurationException e) {
            this.message.Error("Unable to open configuration, see console for more information.");
            this.log.severe(e.getMessage());
        }
    }

    public void set(String key, Object value) {
        this.config.set(key, value);
    }

    public int getInt(String key) {
        return this.config.getInt(key);
    }

    public String getString(String key) {
        return this.config.getString(key);
    }

    public boolean getBoolean(String key) {
        return this.config.getBoolean(key);
    }

    public void save() {
        try {
            this.config.save(this.path);
        } catch (IOException e) {
            this.message.Error("Unable to save configuration, see console for more information.");
            this.log.severe(e.getMessage());
        }
    }
}
