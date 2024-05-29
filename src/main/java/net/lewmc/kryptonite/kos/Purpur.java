package net.lewmc.kryptonite.kos;

import net.lewmc.kryptonite.Kryptonite;
import net.lewmc.kryptonite.utils.LogUtil;
import org.bukkit.configuration.InvalidConfigurationException;

import java.io.File;
import java.io.IOException;

public class Purpur {
    private final Kryptonite plugin;
    private final File file = new File("purpur.yml");

    public Purpur(final Kryptonite plugin) {
        this.plugin = plugin;
        try {
            plugin.getConfig().load(this.file);
        } catch (IOException | InvalidConfigurationException e) {
            LogUtil log = new LogUtil(plugin);
            log.severe("Error whilst loading Purpur configuration:");
            log.severe(e.getMessage());
        }
    }

    public void useAlternativeKeepalive(boolean value) {
        this.plugin.getConfig().set("settings.use-alternate-keepalive", value);
    }

    public void zombieAggressiveTowardsVillagerWhenLagging(boolean value) {
        this.plugin.getConfig().set("world-settings.default.mobs.zombie.aggressive-towards-villager-when-lagging", value);
    }

    public void entitiesCanUsePortals(boolean value) {
        this.plugin.getConfig().set("world-settings.default.gameplay-mechanics.entities-can-use-portals", value);
    }

    public void villagerIsLobotomized(boolean value) {
        this.plugin.getConfig().set("world-settings.default.mobs.villager.lobotomize.enabled", value);
    }

    public void villagerSearchRadiusAcquirePoi(int value) {
        this.plugin.getConfig().set("world-settings.default.mobs.villager.search-radius.acquire-poi", value);
    }

    public void villagerSearchRadiusNearestBedSensor(int value) {
        this.plugin.getConfig().set("world-settings.default.mobs.villager.search-radius.nearest-bed-sensor", value);
    }

    public void dolphinDisableTreasureSearching(boolean value) {
        this.plugin.getConfig().set("world-settings.default.mobs.dolphin.disable-treasure-searching", value);
    }

    public void teleportIfOutsideBorder(boolean value) {
        this.plugin.getConfig().set("world-settings.default.gameplay-mechanics.player.teleport-if-outside-border", value);
    }

    public void laggingThreshold(double value) {
        this.plugin.getConfig().set("settings.lagging-threshold", value);
    }

    public void save() {
        try {
            this.plugin.getConfig().save(this.file);
        } catch (IOException e) {
            LogUtil log = new LogUtil(plugin);
            log.severe("Error whilst saving Purpur configuration:");
            log.severe(e.getMessage());
        }
    }
}
