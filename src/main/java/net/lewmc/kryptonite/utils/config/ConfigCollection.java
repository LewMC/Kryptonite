package net.lewmc.kryptonite.utils.config;

import java.util.LinkedHashMap;

/**
 * Configuration data template for config files.
 * @since 2.1.0
 */
public abstract class ConfigCollection {
    /**
     * Holds configuration data for the relevant file.
     */
    public LinkedHashMap<String, GenericConfigItem> values = new LinkedHashMap<>();
}