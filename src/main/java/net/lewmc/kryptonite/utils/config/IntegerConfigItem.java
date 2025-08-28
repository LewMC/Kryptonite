package net.lewmc.kryptonite.utils.config;

import net.lewmc.foundry.Logger;
import net.lewmc.kryptonite.Kryptonite;

import java.util.List;

/**
 * An integer version of GenericConfigItem
 * @since 2.1.0
 */
public class IntegerConfigItem extends GenericConfigItem<Integer> {
    /**
     * The minimum allowed value.
     */
    private final int minValue;

    /**
     * The maximum allowed value.
     */
    private final int maxValue;

    /**
     * The ideal value in String format (e.g. "7" or "1-5" meaning 1 to 5)
     */
    private final String idealValue;

    /**
     * The integer config item.
     * @param file String - The file the config item is located in.
     * @param key String - The key of the config item within the file.
     * @param name String - The config item's human-readable name.
     * @param description List of Strings - The config item's description, for the GUI each String is a new line.
     * @param dependencyIsEnabled Boolean - If the config's dependencies are enabled. If none, set to true.
     * @param minValue int - The minimum allowed value.
     * @param maxValue int - The maximum allowed value.
     * @param idealValue String - The ideal value in String format (e.g. "7" or "1-5" meaning 1 to 5)
     * @param plugin Kryptonite - Reference to the main Kryptonite class.
     */
    public IntegerConfigItem(String file, String key, String name, List<String> description, Boolean dependencyIsEnabled, int minValue, int maxValue, String idealValue, Kryptonite plugin) {
        super(file, key, name, description, dependencyIsEnabled, plugin);
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.idealValue = idealValue;
    }

    /**
     * Retrieves the current value of the config item.
     * @return int - The config's current value.
     */
    @Override
    public Integer getValue() {
        if (this.file.contains(".properties")) {
            try {
                return Integer.parseInt(propFile.getProperty(key));
            }
            catch (NumberFormatException e) {
                Logger l = new Logger(this.plugin.foundryConfig);
                l.severe("Unable to parse key '"+this.key+"' in file '"+this.file+"' NumberFormatException");
                l.severe(e.getMessage());
                return 0;
            }
        } else if (this.file.contains(".yml") || file.contains(".yaml")) {
            this.loadFile();
            Integer value = yamlFile.getInt(key);
            yamlFile.close();
            return value;
        } else {
            new Logger(this.plugin.foundryConfig).severe("Unable to load file: '"+this.file+"' extension not supported.");
            return 0;
        }
    }

    /**
     * Sets the current value of the config item.
     * @param value Integer - The config's current value.
     */
    @Override
    public void setValue(Integer value) {
        if (this.file.contains(".properties")) {
            propFile.setProperty(this.key, String.valueOf(value));
        } else if (file.contains(".yml") || file.contains(".yaml")) {
            this.loadFile();
            yamlFile.set(this.key, value);
            yamlFile.save();
        }
    }

    /**
     * Checks if the value will be valid.
     * @param value Integer - The value to check.
     * @return boolean - Is it valid?
     */
    @Override
    public boolean willBeValid(Integer value) {
        return dependencyIsEnabled && (value >= minValue && value <= maxValue);
    }

    /**
     * Checks if the config value is ideal.
     * @return boolean - Is it ideal?
     */
    @Override
    public boolean isIdeal() {
        if (idealValue == null) { return true; }
        int current = this.getValue();
        if (idealValue.contains("-")) {
            String[] parts = idealValue.split("-");
            int minIdeal = Integer.parseInt(parts[0].trim());
            int maxIdeal = Integer.parseInt(parts[1].trim());
            return current >= minIdeal && current <= maxIdeal;
        } else {
            return current == Integer.parseInt(idealValue);
        }
    }

    /**
     * Returns the ideal value in human-readable, string format.
     * @return String - The ideal value.
     */
    @Override
    public String getIdealValue() {
        return idealValue == null ? "any" : idealValue;
    }
}
