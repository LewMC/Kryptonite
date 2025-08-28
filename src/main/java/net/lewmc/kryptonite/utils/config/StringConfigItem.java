package net.lewmc.kryptonite.utils.config;

import net.lewmc.foundry.Logger;
import net.lewmc.kryptonite.Kryptonite;

import java.util.List;

/**
 * A string version of GenericConfigItem
 * @since 2.1.0
 */
public class StringConfigItem extends GenericConfigItem<String> {
    /**
     * String list of allowed values.
     */
    private final List<String> allowedValues;

    /**
     * String list of ideal values.
     */
    private final List<String> idealValues;

    /**
     * The String config item.
     * @param file String - The file the config item is located in.
     * @param key String - The key of the config item within the file.
     * @param name String - The config item's human-readable name.
     * @param description List of Strings - The config item's description, for the GUI each String is a new line.
     * @param dependencyIsEnabled Boolean - If the config's dependencies are enabled. If none, set to true.
     * @param allowedValues List of Strings - String list of allowed values.
     * @param idealValues List of Strings - String list of ideal values.
     * @param plugin Kryptonite - Reference to the main Kryptonite class.
     */
    public StringConfigItem(String file, String key, String name, List<String> description, Boolean dependencyIsEnabled, List<String> allowedValues, List<String> idealValues, Kryptonite plugin) {
        super(file, key, name, description, dependencyIsEnabled, plugin);
        this.allowedValues = allowedValues;
        this.idealValues = idealValues;
    }

    /**
     * Retrieves the current value of the config item.
     * @return String - The config's current value.
     */
    @Override
    public String getValue() {
        if (this.file.contains(".properties")) {
            return propFile.getProperty(key);
        } else if (this.file.contains(".yml") || file.contains(".yaml")) {
            String value = yamlFile.getString(key);
            yamlFile.close();
            return value;
        } else {
            new Logger(this.plugin.foundryConfig).severe("Unable to load file: '"+this.file+"' extension not supported.");
            return null;
        }
    }

    /**
     * Sets the current value of the config item.
     * @param value Integer - The config's current value.
     */
    @Override
    public void setValue(String value) {
        if (this.file.contains(".properties")) {
            propFile.setProperty(this.key, value);
        } else if (file.contains(".yml") || file.contains(".yaml")) {
            yamlFile.set(this.key, value);
            yamlFile.save();
        }
    }

    /**
     * Checks if the value will be valid.
     * @param value String - The value to check.
     * @return boolean - Is it valid?
     */
    @Override
    public boolean willBeValid(String value) {
        return dependencyIsEnabled && this.allowedValues.contains(value);

    }

    /**
     * Checks if the config value is ideal.
     * @return boolean - Is it ideal?
     */
    @Override
    public boolean isIdeal() {
        if (idealValues == null) { return true; }
        return idealValues.contains(this.getValue());
    }

    /**
     * Returns the ideal value in human-readable, string format.
     * @return String - The ideal value.
     */
    @Override
    public String getIdealValue() {
        return idealValues == null ? "any" : idealValues.toString();
    }
}
