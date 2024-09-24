package net.lewmc.kryptonite.legacy.kos.config;

import net.lewmc.kryptonite.Kryptonite;
import net.lewmc.kryptonite.utils.LogUtil;
import org.bukkit.configuration.InvalidConfigurationException;

import java.io.File;
import java.io.IOException;

public class Spigot {
    private final Kryptonite plugin;
    private final File file = new File("spigot.yml");

    public Spigot(final Kryptonite plugin) {
        this.plugin = plugin;
        try {
            plugin.getConfig().load(this.file);
        } catch (IOException | InvalidConfigurationException e) {
            LogUtil log = new LogUtil(plugin);
            log.severe("Error whilst loading Spigot configuration:");
            log.severe(e.getMessage());
        }
    }

    public void viewDistance(Object value) {
        this.plugin.getConfig().set("world-settings.default.view-distance", value);
    }

    public void mobSpawnRange(int value) {
        this.plugin.getConfig().set("world-settings.default.mob-spawn-range", value);
    }

    public void entityActivationRange(int animals, int monsters, int raiders, int misc, int water, int villagers, int flying) {
        this.plugin.getConfig().set("world-settings.default.entity-activation-range.animals", animals);
        this.plugin.getConfig().set("world-settings.default.entity-activation-range.monsters", monsters);
        this.plugin.getConfig().set("world-settings.default.entity-activation-range.raiders", raiders);
        this.plugin.getConfig().set("world-settings.default.entity-activation-range.misc", misc);
        this.plugin.getConfig().set("world-settings.default.entity-activation-range.water", water);
        this.plugin.getConfig().set("world-settings.default.entity-activation-range.villagers", villagers);
        this.plugin.getConfig().set("world-settings.default.entity-activation-range.flying-monsters", flying);
    }

    public void entityTrackingRange(int players, int animals, int monsters, int misc, int other) {
        this.plugin.getConfig().set("world-settings.default.entity-tracking-range.players", players);
        this.plugin.getConfig().set("world-settings.default.entity-tracking-range.animals", animals);
        this.plugin.getConfig().set("world-settings.default.entity-tracking-range.monsters", monsters);
        this.plugin.getConfig().set("world-settings.default.entity-tracking-range.misc", misc);
        this.plugin.getConfig().set("world-settings.default.entity-tracking-range.other", other);
    }

    public void tickInacativeVillagers(boolean value) {
        this.plugin.getConfig().set("world-settings.default.entity-activation-range.tick-inactive-villagers", value);
    }

    public void nerfSpawnerMobs(boolean value) {
        this.plugin.getConfig().set("world-settings.default.nerf-spawner-mobs", value);
    }

    public void mergeRadius(double item, double exp) {
        this.plugin.getConfig().set("world-settings.default.merge-radius.item", item);
        this.plugin.getConfig().set("world-settings.default.merge-radius.exp", exp);
    }

    public void hopperTransfer(int value) {
        this.plugin.getConfig().set("world-settings.default.ticks-per.hopper-transfer", value);
    }

    public void hopperCheck(int value) {
        this.plugin.getConfig().set("world-settings.default.ticks-per.hopper-check", value);
    }

    public void save() {
        try {
            this.plugin.getConfig().save(this.file);
        } catch (IOException e) {
            LogUtil log = new LogUtil(plugin);
            log.severe("Error whilst saving Spigot configuration:");
            log.severe(e.getMessage());
        }
    }
}
