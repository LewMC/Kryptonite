package net.lewmc.kryptonite.utils.config;

import net.lewmc.foundry.Logger;
import net.lewmc.kryptonite.Kryptonite;

import java.util.List;

/**
 * A double version of GenericConfigItem
 * @since 2.1.0
 */
public class DoubleConfigItem extends GenericConfigItem<Double> {
    /**
     * The minimum allowed value.
     */
    private final Double minValue;

    /**
     * The maximum allowed value.
     */
    private final Double maxValue;

    /**
     * The ideal value in String format (e.g. "7" or "1-5" meaning 1 to 5)
     */
    private final String idealValue;

    /**
     * The double config item.
     * @param file String - The file the config item is located in.
     * @param key String - The key of the config item within the file.
     * @param name String - The config item's human-readable name.
     * @param description List of Strings - The config item's description, for the GUI each String is a new line.
     * @param dependencyIsEnabled Boolean - If the config's dependencies are enabled. If none, set to true.
     * @param minValue Double - The minimum allowed value.
     * @param maxValue Double - The maximum allowed value.
     * @param idealValue String - The ideal value in String format (e.g. "7" or "1-5" meaning 1 to 5)
     * @param plugin Kryptonite - Reference to the main Kryptonite class.
     */
    public DoubleConfigItem(String file, String key, String name, List<String> description, Boolean dependencyIsEnabled, Double minValue, Double maxValue, String idealValue, Kryptonite plugin) {
        super(file, key, name, description, dependencyIsEnabled, plugin);
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.idealValue = idealValue;
    }

    /**
     * Retrieves the current value of the config item.
     * @return Double - The config's current value.
     */
    @Override
    public Double getValue() {
        if (this.file.contains(".properties")) {
            try {
                return Double.parseDouble(propFile.getProperty(key));
            }
            catch (NumberFormatException e) {
                Logger l = new Logger(this.plugin.foundryConfig);
                l.severe("Unable to parse key '"+this.key+"' in file '"+this.file+"' NumberFormatException");
                l.severe(e.getMessage());
                return 0.0;
            }
        } else if (this.file.contains(".yml") || file.contains(".yaml")) {
            this.loadFile();
            Double value = yamlFile.getDouble(key);
            yamlFile.close();
            return value;
        } else {
            new Logger(this.plugin.foundryConfig).severe("Unable to load file: '"+this.file+"' extension not supported.");
            return 0.0;
        }
    }

    /**
     * Sets the current value of the config item.
     * @param value Double - The config's current value.
     */
    @Override
    public void setValue(Double value) {
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
     * @param value Double - The value to check.
     * @return boolean - Is it valid?
     */
    @Override
    public boolean willBeValid(Double value) {
        return dependencyIsEnabled && (value >= minValue && value <= maxValue);
    }

    /**
     * Checks if the config value is ideal.
     * @return boolean - Is it ideal?
     */
    @Override
    public boolean isIdeal() {
        if (idealValue == null) {
            return true;
        }

        double current = this.getValue();
        String trimmed = idealValue.trim();

        if (trimmed.contains("-")) {
            String[] parts = trimmed.split("-");
            double minIdeal = Double.parseDouble(parts[0].trim());
            double maxIdeal = Double.parseDouble(parts[1].trim());
            return current >= minIdeal && current <= maxIdeal;
        }

        if (trimmed.startsWith(">")) {
            double threshold = Double.parseDouble(trimmed.substring(1).trim());
            return current > threshold;
        }

        if (trimmed.startsWith("<")) {
            double threshold = Double.parseDouble(trimmed.substring(1).trim());
            return current < threshold;
        }

        return current == Double.parseDouble(trimmed);
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
