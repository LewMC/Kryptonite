package net.lewmc.kryptonite.kos;

import net.lewmc.kryptonite.Kryptonite;
import net.lewmc.kryptonite.utils.LogUtil;
import org.bukkit.configuration.InvalidConfigurationException;

import java.io.File;
import java.io.IOException;

public class Pufferfish {
    private final Kryptonite plugin;
    private final File file = new File("pufferfish.yml");

    public Pufferfish(final Kryptonite plugin) {
        this.plugin = plugin;
        try {
            plugin.getConfig().load(this.file);
        } catch (IOException | InvalidConfigurationException e) {
            LogUtil log = new LogUtil(plugin);
            log.severe("Error whilst loading Pufferfish configuration:");
            log.severe(e.getMessage());
        }
    }

    public void maxLoadsPerProjectile(int value) {
        this.plugin.getConfig().set("projectile.max-loads-per-projectile", value);
    }

    public void dabEnabled(boolean value) {
        this.plugin.getConfig().set("dab.enabled", value);
    }

    public void dabMaxTickFreq(int value) {
        this.plugin.getConfig().set("dab.max-tick-freq", value);
    }

    public void dabActivationDistMod(int value) {
        this.plugin.getConfig().set("dab.activation-dist-mod", value);
    }

    public void enableAsyncMobSpawning(boolean value) {
        this.plugin.getConfig().set("enable-async-mob-spawning", value);
    }

    public void enableSuffocationOptimization(boolean value) {
        this.plugin.getConfig().set("enable-suffocation-optimization", value);
    }

    public void inactiveGoalSelectorThrottle(boolean value) {
        this.plugin.getConfig().set("inactive-goal-selector-throttle", value);
    }

    public void disableMethodProfiler(boolean value) {
        this.plugin.getConfig().set("misc.disable-method-profiler", value);
    }

    public void save() {
        try {
            this.plugin.getConfig().save(this.file);
        } catch (IOException e) {
            LogUtil log = new LogUtil(plugin);
            log.severe("Error whilst saving Pufferfish configuration:");
            log.severe(e.getMessage());
        }
    }
}
