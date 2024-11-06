package net.lewmc.kryptonite.utils;

import net.lewmc.kryptonite.Kryptonite;
import net.lewmc.kryptonite.kos.config.Pufferfish;
import org.bukkit.command.CommandSender;

public class SoftwareUtil {
    private final Kryptonite plugin;

    public SoftwareUtil(Kryptonite plugin) {
        this.plugin = plugin;
    }

    public boolean supportsServerProperties() {
        return this.plugin.SupportedConfigurations.contains(Kryptonite.ConfigurationOptions.SERVER_PROPERTIES);
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

    public boolean dabEnabled(CommandSender cs) {
        if (this.supportsServerProperties()) {
            Pufferfish pf = new Pufferfish(this.plugin, cs);
            return pf.getBoolean(Pufferfish.Key.DAB_ENABLED);
        } else {
            return false;
        }
    }
}
