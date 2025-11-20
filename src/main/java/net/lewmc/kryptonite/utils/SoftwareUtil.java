package net.lewmc.kryptonite.utils;

import net.lewmc.kryptonite.Kryptonite;
import net.lewmc.kryptonite.config.PufferfishConfig;
import net.lewmc.kryptonite.utils.config.BooleanConfigItem;

public class SoftwareUtil {
    private final Kryptonite plugin;

    public SoftwareUtil(Kryptonite plugin) {
        this.plugin = plugin;
    }

    public boolean supportsMinecraft() {
        return this.plugin.SupportedConfigurations.contains(Kryptonite.ConfigurationOptions.MINECRAFT);
    }

    public boolean supportsCraftBukkit() {
        return this.plugin.SupportedConfigurations.contains(Kryptonite.ConfigurationOptions.BUKKIT);
    }

    public boolean supportsSpigot() {
        return this.plugin.SupportedConfigurations.contains(Kryptonite.ConfigurationOptions.SPIGOT);
    }

    public boolean supportsPaperWorld() {
        return this.plugin.SupportedConfigurations.contains(Kryptonite.ConfigurationOptions.PAPER_WORLD);
    }

    public boolean supportsPurpur() {
        return this.plugin.SupportedConfigurations.contains(Kryptonite.ConfigurationOptions.PURPUR);
    }

    public boolean supportsPufferfish() {
        return this.plugin.SupportedConfigurations.contains(Kryptonite.ConfigurationOptions.PUFFERFISH);
    }

    public boolean dabEnabled() {
        if (this.supportsPufferfish()) {
            return ((BooleanConfigItem)new PufferfishConfig(this.plugin).values.get(PufferfishConfig.Key.DAB_ENABLED.toString())).getValue();
        } else {
            return false;
        }
    }
}
