package net.lewmc.kryptonite.utils;

import net.lewmc.foundry.Logger;
import net.lewmc.kryptonite.Kryptonite;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.IOException;

/**
 * ConfigurationUtil is a helper class for managing YML configuration files.
 */
public class ConfigurationUtil {
    private final MessageUtil message;
    private final Logger log;
    private YamlConfiguration config;
    private String path;

    /**
     * Constructor for the ConfigurationUtil class.
     * @param plugin Kryptonite - Reference to the main plugin class.
     * @param user CommandSender - The user who sent the command.
     */
    public ConfigurationUtil(Kryptonite plugin, CommandSender user) {
        this.message = new MessageUtil(user);
        this.log = new Logger(plugin.foundryConfig);
    }

    /**
     * Loads a configuration file into memory.
     * @param path String - The path to the file.
     */
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

    /**
     * Sets a key to the requested value.
     * @param key String - The key.
     * @param value Object - The value.
     */
    public void set(String key, Object value) {
        this.config.set(key, value);
    }

    /**
     * Gets a requested value (could be anything!)
     * @param key String - The key.
     * @return Object - The value
     */
    public Object get(String key) {
        return this.config.get(key);
    }

    /**
     * Gets a requested value as an integer.
     * @param key String - The key.
     * @return int - The value
     */
    public int getInt(String key) {
        return this.config.getInt(key);
    }

    /**
     * Gets a requested value as a string.
     * @param key String - The key.
     * @return String - The value
     */
    public String getString(String key) {
        return this.config.getString(key);
    }

    /**
     * Gets a requested value as a boolean.
     * @param key String - The key.
     * @return boolean - The value
     */
    public boolean getBoolean(String key) {
        return this.config.getBoolean(key);
    }

    /**
     * Saves the configuration file.
     */
    public void save() {
        try {
            this.config.save(this.path);
        } catch (IOException e) {
            this.message.Error("Unable to save configuration, see console for more information.");
            this.log.severe(e.getMessage());
        }
    }
}
