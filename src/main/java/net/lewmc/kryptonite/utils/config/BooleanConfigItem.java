package net.lewmc.kryptonite.utils.config;

import net.lewmc.foundry.Files;
import net.lewmc.foundry.Logger;
import net.lewmc.kryptonite.Kryptonite;
import net.lewmc.kryptonite.utils.PropertiesUtil;

import java.util.List;

/**
 * A boolean version of GenericConfigItem
 * @since 2.1.0
 */
public class BooleanConfigItem extends GenericConfigItem<Boolean> {
    /**
     * The ideal value.
     */
    private final Boolean idealValue;

    /**
     * The Boolean config item.
     * @param file String - The file the config item is located in.
     * @param key String - The key of the config item within the file.
     * @param name String - The config item's human-readable name.
     * @param description List of Strings - The config item's description, for the GUI each String is a new line.
     * @param dependencyIsEnabled Boolean - If the config's dependencies are enabled. If none, set to true.
     * @param idealValue Boolean - String list of ideal values.
     * @param plugin Kryptonite - Reference to the main Kryptonite class.
     */
    public BooleanConfigItem(String file, String key, String name, List<String> description, Boolean dependencyIsEnabled, Boolean idealValue, Kryptonite plugin) {
        super(file, key, name, description, dependencyIsEnabled, plugin);
        this.idealValue = idealValue;
    }

    /**
     * Retrieves the current value of the config item.
     * @return Boolean - The config's current value.
     */
    @Override
    public Boolean getValue() {
        if (this.file.contains(".properties")) {
            PropertiesUtil p = new PropertiesUtil(this.file);
            return Boolean.parseBoolean(p.getProperty(key));
        } else if (this.file.contains(".yml") || file.contains(".yaml")) {
            Files f = new Files(this.plugin.foundryConfig, this.plugin);
            f.load(this.file);
            boolean value = f.getBoolean(key);
            f.close();
            return value;
        } else {
            new Logger(this.plugin.foundryConfig).severe("Unable to load file: '"+this.file+"' extension not supported.");
            return false;
        }
    }

    /**
     * Sets the current value of the config item.
     * @param value Boolean - The config's current value.
     */
    @Override
    public void setValue(Boolean value) {
        if (this.file.contains(".properties")) {
            propFile.setProperty(this.key, String.valueOf(value));
        } else if (file.contains(".yml") || file.contains(".yaml")) {
            yamlFile.load(this.file);
            yamlFile.set(this.key, value);
            yamlFile.save();
        }
    }

    /**
     * Checks if the value will be valid.
     * @param value Boolean - The value to check.
     * @return boolean - Is it valid?
     */
    @Override
    public boolean willBeValid(Boolean value) {
        return !this.dependencyIsEnabled;
    }

    /**
     * Checks if the config value is ideal.
     * @return boolean - Is it ideal?
     */
    @Override
    public boolean isIdeal() {
        if (idealValue == null) { return true; }
        return getValue().equals(idealValue);
    }

    /**
     * Returns the ideal value in human-readable, string format.
     * @return String - The ideal value.
     */
    @Override
    public String getIdealValue() {
        return idealValue == null ? "any" : Boolean.toString(idealValue);
    }
}
