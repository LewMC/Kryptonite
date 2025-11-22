package net.lewmc.kryptonite.utils.config;

import net.lewmc.foundry.Files;
import net.lewmc.foundry.Logger;
import net.lewmc.kryptonite.Kryptonite;
import net.lewmc.kryptonite.utils.PropertiesUtil;

import java.io.File;
import java.util.List;

/**
 * A generic ConfigItem
 * @since 2.1.0
 * @param <T>
 */
public abstract class GenericConfigItem<T> {
    /**
     * Handles .yaml and .yml files.
     */
    protected Files yamlFile;

    /**
     * Handles .properties files.
     */
    protected PropertiesUtil propFile;

    /**
     * The file the config item is located in.
     */
    protected String file;

    /**
     * The key of the config item within the file.
     */
    protected String key;

    /**
     * The config item's human-readable name.
     */
    protected String name;

    /**
     * The config item's description, for the GUI each String is a new line.
     */
    protected List<String> description;

    /**
     * If the config's dependencies are enabled. If none, set to true
     */
    protected Boolean dependencyIsEnabled;

    /**
     * Reference to the main Kryptonite class
     */
    protected Kryptonite plugin;

    /**
     * The generic config item.
     * @param file String - The file the config item is located in.
     * @param key String - The key of the config item within the file.
     * @param name String - The config item's human-readable name.
     * @param description List of Strings - The config item's description, for the GUI each String is a new line.
     * @param dependencyIsEnabled Boolean - If the config's dependencies are enabled. If none, set to true.
     * @param plugin Kryptonite - Reference to the main Kryptonite class.
     */
    public GenericConfigItem(String file, String key, String name, List<String> description, Boolean dependencyIsEnabled, Kryptonite plugin) {
        this.file = file;
        this.key = key;
        this.name = name;
        this.description = description;
        this.dependencyIsEnabled = dependencyIsEnabled;
        this.plugin = plugin;
    }

    /**
     * Loads the file.
     */
    public void loadFile() {
        if (file.contains(".properties")) {
            this.propFile = new PropertiesUtil(plugin.getServer().getWorldContainer() + File.separator + file);
        } else if (file.contains(".yaml") || file.contains(".yml")) {
            this.yamlFile = new Files(plugin.foundryConfig, plugin);
            this.yamlFile.loadNoReformat(new File(this.plugin.getDataFolder(),"/../../"+file).getAbsoluteFile());
        } else {
            new Logger(plugin.foundryConfig).severe("Unable to load file '"+file+"' file extension not supported.");
            new Logger(plugin.foundryConfig).severe("Expect additional errors.");
        }
    }

    /**
     * Should fetch the current value of the config item.
     * @return T - The config's current value.
     */
    public abstract T getValue();

    /**
     * Should set the value of the config item.
     * @param value T - The config's current value.
     */
    public abstract void setValue(T value);

    /**
     * Should check if the config value is valid.
     * @return boolean - Is it valid?
     */
    public boolean isValid() {
        return this.willBeValid(this.getValue());
    }

    /**
     * Should check if the config value will be valid.
     * @return boolean - Is it valid?
     */
    public abstract boolean willBeValid(T value);

    /**
     * Should check if the config value is ideal.
     * @return boolean - Is it ideal?
     */
    public abstract boolean isIdeal();

    /**
     * Returns the ideal value in human-readable, string format.
     * @return String - The ideal value.
     */
    public abstract String getIdealValue();

    /**
     * Returns the config item's name.
     * @return String - The name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the config item's description.
     * @return List of Strings - The description.
     */
    public List<String> getDescription() {
        return this.description;
    }

    /**
     * Returns if the value's dependencies are enabled.
     * @return boolean - Are they?
     */
    public Boolean dependencyIsEnabled() {
        return this.dependencyIsEnabled;
    }
}